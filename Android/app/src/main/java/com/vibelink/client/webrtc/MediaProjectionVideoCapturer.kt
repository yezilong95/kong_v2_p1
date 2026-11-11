package com.vibelink.client.webrtc

import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.Surface
import com.vibelink.client.screen.MediaProjectionHolder
import com.vibelink.client.screen.ScreenFrameCodec
import org.webrtc.CapturerObserver
import org.webrtc.SurfaceTextureHelper
import org.webrtc.VideoCapturer
import org.webrtc.VideoFrame
import org.webrtc.VideoSink
import java.nio.ByteBuffer
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * MediaProjection → VirtualDisplay → SurfaceTextureHelper → WebRTC frames.
 * Uses [MediaProjectionHolder] so consent is shared with the rest of the app.
 *
 * 针对「投屏忽明忽暗 / 长时间黑屏」的三层兜底（对照 1688 的 wzcrdiiwiyvbwsqltf）：
 * 1. 黑帧检测：8×8 采样平均亮度 < [BLACK_FRAME_LUMA_THRESHOLD] 判为黑帧，
 *    不把新黑帧推给编码器，改为重复上一帧，避免前端画面直接刷成纯黑。
 * 2. 无帧重建：连续 [REBUILD_AFTER_NO_FRAME_MS] 没收到新帧（VirtualDisplay 被系统回收 /
 *    Surface 失联 / resize 后内部卡死），release 后完整重建 VirtualDisplay。
 * 3. 方向轮询：每 [ORIENTATION_POLL_MS] 比对 Configuration.orientation，
 *    变化时立即重建（后台 Service 不一定能收到 onConfigurationChanged）。
 */
class MediaProjectionVideoCapturer(
    context: Context,
) : VideoCapturer, VideoSink {
    private val appContext = context.applicationContext
    private var capturerObserver: CapturerObserver? = null
    private var surfaceTextureHelper: SurfaceTextureHelper? = null
    private var mediaProjection: MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var width = 0
    private var height = 0
    private var callbackThread: HandlerThread? = null
    private var callbackHandler: Handler? = null
    private var watchdogThread: HandlerThread? = null
    private var watchdogHandler: Handler? = null
    private val running = AtomicBoolean(false)

    /** 最近一次成功回调 onFrame 的时间（elapsedRealtime）。 */
    private val lastFrameAtMs = AtomicLong(0L)
    /** 最近一次重建 VirtualDisplay 的时间，避免重建风暴。 */
    private val lastRebuildAtMs = AtomicLong(0L)
    private val rebuildCount = AtomicInteger(0)
    /** 上一帧的非黑 bitmap 尺寸，用于复用黑帧判定时的占位。 */
    @Volatile
    private var lastOrientation: Int = -1
    /** 上次做亮度抽查的时间与连续抑制计数。 */
    private val lastLumaCheckAtMs = AtomicLong(0L)
    private val blackSuppressStreak = AtomicInteger(0)

    private val projectionCallback = object : MediaProjection.Callback() {
        override fun onStop() {
            // System revoked projection — stop quietly; session layer will error/fallback.
            running.set(false)
            stopWatchdog()
        }
    }

    override fun initialize(
        surfaceTextureHelper: SurfaceTextureHelper?,
        context: Context?,
        capturerObserver: CapturerObserver?,
    ) {
        this.surfaceTextureHelper = surfaceTextureHelper
        this.capturerObserver = capturerObserver
    }

    override fun startCapture(width: Int, height: Int, framerate: Int) {
        if (!running.compareAndSet(false, true)) {
            return
        }
        this.width = width.coerceAtLeast(160)
        this.height = height.coerceAtLeast(160)
        val helper = surfaceTextureHelper ?: run {
            running.set(false)
            throw IllegalStateException("surface_texture_helper_missing")
        }
        val observer = capturerObserver ?: run {
            running.set(false)
            throw IllegalStateException("capturer_observer_missing")
        }

        val thread = HandlerThread("webrtc_projection_cb").apply { start() }
        callbackThread = thread
        val handler = Handler(thread.looper)
        callbackHandler = handler

        val projection = MediaProjectionHolder.getOrCreateProjection(appContext)
            ?: run {
                running.set(false)
                throw IllegalStateException("projection_not_granted")
            }
        mediaProjection = projection
        projection.registerCallback(projectionCallback, handler)

        helper.setTextureSize(this.width, this.height)
        helper.startListening(this)
        createDisplay(helper)
        lastFrameAtMs.set(android.os.SystemClock.elapsedRealtime())
        lastOrientation = currentOrientation()
        startWatchdog()
        observer.onCapturerStarted(true)
    }

    private fun createDisplay(helper: SurfaceTextureHelper) {
        val projection = mediaProjection ?: return
        runCatching { virtualDisplay?.release() }
        virtualDisplay = null
        virtualDisplay = projection.createVirtualDisplay(
            "WebRTC_ScreenCapture",
            width,
            height,
            VIRTUAL_DISPLAY_DPI,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
            Surface(helper.surfaceTexture),
            null,
            null,
        )
        lastFrameAtMs.set(android.os.SystemClock.elapsedRealtime())
        lastRebuildAtMs.set(android.os.SystemClock.elapsedRealtime())
    }

    /** 完整重建：release 旧 VD → 重新 createVirtualDisplay。不走 resize（部分 OEM 会卡死）。 */
    private fun rebuildDisplay(reason: String) {
        if (!running.get()) {
            return
        }
        val now = android.os.SystemClock.elapsedRealtime()
        val last = lastRebuildAtMs.get()
        if (now - last < REBUILD_MIN_INTERVAL_MS) {
            return
        }
        val helper = surfaceTextureHelper ?: return
        val count = rebuildCount.incrementAndGet()
        Log.w(TAG, "rebuild display #$count reason=$reason size=${width}x$height")
        runCatching { createDisplay(helper) }
            .onFailure { Log.e(TAG, "rebuild display failed", it) }
    }

    private fun startWatchdog() {
        stopWatchdog()
        val thread = HandlerThread("webrtc_projection_watchdog").apply { start() }
        watchdogThread = thread
        val handler = Handler(thread.looper)
        watchdogHandler = handler
        handler.post(object : Runnable {
            override fun run() {
                if (!running.get()) {
                    return
                }
                val now = android.os.SystemClock.elapsedRealtime()
                val sinceFrame = now - lastFrameAtMs.get()
                if (sinceFrame >= REBUILD_AFTER_NO_FRAME_MS) {
                    rebuildDisplay("no_frame_${sinceFrame}ms")
                }
                val orientation = currentOrientation()
                if (lastOrientation != -1 && orientation != lastOrientation) {
                    Log.w(TAG, "orientation changed $lastOrientation -> $orientation")
                    lastOrientation = orientation
                    rebuildDisplay("orientation_changed")
                } else {
                    lastOrientation = orientation
                }
                handler.postDelayed(this, ORIENTATION_POLL_MS)
            }
        })
    }

    private fun stopWatchdog() {
        watchdogHandler?.removeCallbacksAndMessages(null)
        watchdogThread?.quitSafely()
        watchdogHandler = null
        watchdogThread = null
    }

    @Suppress("DEPRECATION")
    private fun currentOrientation(): Int {
        return runCatching {
            appContext.resources.configuration.orientation
        }.getOrDefault(-1)
    }

    override fun stopCapture() {
        if (!running.getAndSet(false)) {
            return
        }
        stopWatchdog()
        surfaceTextureHelper?.stopListening()
        runCatching { virtualDisplay?.release() }
        virtualDisplay = null
        runCatching { mediaProjection?.unregisterCallback(projectionCallback) }
        // Do not stop MediaProjection here — holder owns process-lifetime consent.
        mediaProjection = null
        capturerObserver?.onCapturerStopped()
        callbackThread?.quitSafely()
        callbackThread = null
        callbackHandler = null
    }

    override fun changeCaptureFormat(width: Int, height: Int, framerate: Int) {
        if (!running.get()) {
            return
        }
        val nextWidth = width.coerceAtLeast(160)
        val nextHeight = height.coerceAtLeast(160)
        if (nextWidth == this.width && nextHeight == this.height) {
            return
        }
        this.width = nextWidth
        this.height = nextHeight
        surfaceTextureHelper?.setTextureSize(this.width, this.height)
        // resize() 在部分 ROM 上不生效或导致 Surface 失联，这里直接走完整重建。
        rebuildDisplay("format_${this.width}x${this.height}")
    }

    override fun dispose() {
        runCatching { stopCapture() }
        surfaceTextureHelper = null
        capturerObserver = null
    }

    override fun isScreencast(): Boolean = true

    override fun onFrame(frame: VideoFrame?) {
        if (frame == null || !running.get()) {
            return
        }
        val now = android.os.SystemClock.elapsedRealtime()
        // 黑帧抑制：SurfaceTexture 帧要读亮度必须走一次 toI420（GPU 回读），代价高，
        // 因此每 [LUMA_CHECK_INTERVAL_MS] 才抽查一次。判为黑就丢弃该帧（编码器继续输出上一帧），
        // 但最多连续抑制 [MAX_BLACK_SUPPRESS_CHECKS] 次，避免真黑屏时画面被冻住。
        if (now - lastLumaCheckAtMs.get() >= LUMA_CHECK_INTERVAL_MS) {
            lastLumaCheckAtMs.set(now)
            val black = runCatching { isBlackVideoFrame(frame) }.getOrDefault(false)
            if (black && blackSuppressStreak.get() < MAX_BLACK_SUPPRESS_CHECKS) {
                blackSuppressStreak.incrementAndGet()
                Log.d(TAG, "suppress black webrtc frame #${blackSuppressStreak.get()}")
                return
            }
            blackSuppressStreak.set(0)
        }
        lastFrameAtMs.set(now)
        capturerObserver?.onFrameCaptured(frame)
    }

    private fun isBlackVideoFrame(frame: VideoFrame): Boolean {
        val i420 = frame.buffer.toI420() ?: return false
        return try {
            ScreenFrameCodec.isBlackI420(
                i420.dataY,
                i420.width,
                i420.height,
                i420.strideY,
            )
        } finally {
            i420.release()
        }
    }

    companion object {
        private const val TAG = "MediaProjectionCapturer"
        private const val VIRTUAL_DISPLAY_DPI = 400
        /** 无帧超过该时长即重建。 */
        private const val REBUILD_AFTER_NO_FRAME_MS = 2_500L
        /** 两次重建的最小间隔，防重建风暴。 */
        private const val REBUILD_MIN_INTERVAL_MS = 2_000L
        /** 方向轮询间隔（1688 用 2000ms）。 */
        private const val ORIENTATION_POLL_MS = 2_000L
        /** 8×8 采样平均亮度低于该值判为黑帧（1688 S() 用 20）。 */
        const val BLACK_FRAME_LUMA_THRESHOLD = 20
        /** 亮度抽查间隔：GPU 回读有开销，不必每帧都查。 */
        private const val LUMA_CHECK_INTERVAL_MS = 500L
        /** 连续抑制上限，防止真黑屏时画面永久冻结。 */
        private const val MAX_BLACK_SUPPRESS_CHECKS = 6
    }
}
