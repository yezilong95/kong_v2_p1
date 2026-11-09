package com.vibelink.client.webrtc

import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.projection.MediaProjection
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import com.vibelink.client.screen.MediaProjectionHolder
import org.webrtc.CapturerObserver
import org.webrtc.SurfaceTextureHelper
import org.webrtc.VideoCapturer
import org.webrtc.VideoFrame
import org.webrtc.VideoSink
import java.util.concurrent.atomic.AtomicBoolean

/**
 * MediaProjection → VirtualDisplay → SurfaceTextureHelper → WebRTC frames.
 * Uses [MediaProjectionHolder] so consent is shared with the rest of the app.
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
    private val running = AtomicBoolean(false)

    private val projectionCallback = object : MediaProjection.Callback() {
        override fun onStop() {
            // System revoked projection — stop quietly; session layer will error/fallback.
            running.set(false)
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
        virtualDisplay = projection.createVirtualDisplay(
            "WebRTC_ScreenCapture",
            this.width,
            this.height,
            VIRTUAL_DISPLAY_DPI,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
            Surface(helper.surfaceTexture),
            null,
            null,
        )
        observer.onCapturerStarted(true)
    }

    override fun stopCapture() {
        if (!running.getAndSet(false)) {
            return
        }
        surfaceTextureHelper?.stopListening()
        virtualDisplay?.release()
        virtualDisplay = null
        mediaProjection?.unregisterCallback(projectionCallback)
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
        this.width = width.coerceAtLeast(160)
        this.height = height.coerceAtLeast(160)
        surfaceTextureHelper?.setTextureSize(this.width, this.height)
        virtualDisplay?.resize(this.width, this.height, VIRTUAL_DISPLAY_DPI)
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
        capturerObserver?.onFrameCaptured(frame)
    }

    companion object {
        private const val VIRTUAL_DISPLAY_DPI = 400
    }
}
