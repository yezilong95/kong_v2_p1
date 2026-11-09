package com.vibelink.client.webrtc

import android.content.Context
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import org.json.JSONArray
import org.json.JSONObject
import org.webrtc.DataChannel
import org.webrtc.IceCandidate
import org.webrtc.MediaConstraints
import org.webrtc.MediaStream
import org.webrtc.PeerConnection
import org.webrtc.RtpReceiver
import org.webrtc.RtpTransceiver
import org.webrtc.SdpObserver
import org.webrtc.SessionDescription
import org.webrtc.SurfaceTextureHelper
import org.webrtc.VideoCapturer
import org.webrtc.VideoTrack
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Device-side sendonly screen PeerConnection. Signaling goes out via [SignalingSink].
 */
class ScreenWebRtcSession(
    context: Context,
    private val signaling: SignalingSink,
) {
    interface SignalingSink {
        fun sendOffer(sdp: String)
        fun sendIce(candidate: JSONObject)
        fun onSessionFailed(message: String)
        fun onSessionConnected()
    }

    private val appContext = context.applicationContext
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val closed = AtomicBoolean(false)
    private val connectedOnce = AtomicBoolean(false)
    private val failedOnce = AtomicBoolean(false)

    private var peerConnection: PeerConnection? = null
    private var videoCapturer: VideoCapturer? = null
    private var pushCapturer: PushBitmapVideoCapturer? = null
    private var surfaceTextureHelper: SurfaceTextureHelper? = null
    private var localVideoTrack: VideoTrack? = null
    private var videoSource: org.webrtc.VideoSource? = null
    private var iceTimeoutFuture: ScheduledFuture<*>? = null
    @Volatile
    private var usingRelayOnly: Boolean = true

    fun startProjection(iceServersJson: JSONArray?, captureFps: Int) {
        executor.execute {
            runCatching {
                if (closed.get()) {
                    return@execute
                }
                val metrics = displaySize()
                val captureWidth = TARGET_WIDTH
                val captureHeight = (metrics.second * captureWidth / metrics.first.toFloat())
                    .toInt()
                    .coerceAtLeast(320)
                val capturer = MediaProjectionVideoCapturer(appContext)
                beginSession(
                    iceServersJson = iceServersJson,
                    capturer = capturer,
                    width = captureWidth,
                    height = captureHeight,
                    fps = captureFps.coerceIn(4, 20),
                    needsSurfaceHelper = true,
                )
            }.onFailure { error ->
                Log.e(TAG, "startProjection failed", error)
                failOnce(error.message ?: "webrtc_start_failed")
                releaseInternal()
            }
        }
    }

    /**
     * Bitmap-push path for accessibility screenshot / UI-tree fake screen.
     */
    fun startBitmapPush(iceServersJson: JSONArray?, width: Int, height: Int, fps: Int) {
        executor.execute {
            runCatching {
                if (closed.get()) {
                    return@execute
                }
                val capturer = PushBitmapVideoCapturer()
                pushCapturer = capturer
                beginSession(
                    iceServersJson = iceServersJson,
                    capturer = capturer,
                    width = width.coerceAtLeast(160),
                    height = height.coerceAtLeast(160),
                    fps = fps.coerceIn(1, 15),
                    needsSurfaceHelper = false,
                )
            }.onFailure { error ->
                Log.e(TAG, "startBitmapPush failed", error)
                failOnce(error.message ?: "webrtc_start_failed")
                releaseInternal()
            }
        }
    }

    fun offerBitmap(bitmap: Bitmap, recycleAfter: Boolean = true) {
        if (closed.get()) {
            if (recycleAfter && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            return
        }
        val capturer = pushCapturer
        if (capturer == null || closed.get()) {
            if (recycleAfter && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            return
        }
        capturer.offerBitmap(bitmap, recycleAfter = recycleAfter)
    }

    fun onRemoteAnswer(sdp: String) {
        executor.execute {
            val pc = peerConnection ?: return@execute
            val answer = SessionDescription(SessionDescription.Type.ANSWER, sdp)
            pc.setRemoteDescription(SdpAdapter("setRemoteAnswer"), answer)
        }
    }

    fun onRemoteIce(candidateJson: JSONObject) {
        executor.execute {
            val pc = peerConnection ?: return@execute
            val candidate = IceCandidate(
                candidateJson.optString("sdpMid"),
                candidateJson.optInt("sdpMLineIndex"),
                candidateJson.optString("candidate"),
            )
            pc.addIceCandidate(candidate)
        }
    }

    fun stop() {
        if (!closed.compareAndSet(false, true)) {
            return
        }
        // Disable bitmap push on the caller thread before native teardown races.
        runCatching { pushCapturer?.stopCapture() }
        pushCapturer = null
        executor.execute {
            releaseInternal()
            executor.shutdown()
        }
    }

    private fun beginSession(
        iceServersJson: JSONArray?,
        capturer: VideoCapturer,
        width: Int,
        height: Int,
        fps: Int,
        needsSurfaceHelper: Boolean,
    ) {
        WebRtcEngine.ensureInitialized(appContext)
        val factory = WebRtcEngine.factory(appContext)
        val egl = WebRtcEngine.egl(appContext)

        val iceServers = parseIceServers(iceServersJson)
        val useRelayOnly = !relayFallbackActive.get()
        val rtcConfig = PeerConnection.RTCConfiguration(iceServers).apply {
            sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN
            continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY
            // 默认强制 TURN relay：UDP NAT 映射常被路由器 20-30s 回收，走 relay 维持稳定长连。
            // 连续 ICE 失败达到阈值后翻为 ALL（见 failOnce），本进程生命周期内保持降级。
            iceTransportsType = if (useRelayOnly) {
                PeerConnection.IceTransportsType.RELAY
            } else {
                PeerConnection.IceTransportsType.ALL
            }
        }
        this.usingRelayOnly = useRelayOnly

        val pc = factory.createPeerConnection(rtcConfig, pcObserver)
            ?: throw IllegalStateException("peer_connection_create_failed")
        peerConnection = pc
        videoCapturer = capturer

        val helper = if (needsSurfaceHelper) {
            SurfaceTextureHelper.create("ScreenCaptureThread", egl.eglBaseContext).also {
                surfaceTextureHelper = it
            }
        } else {
            null
        }
        val source = factory.createVideoSource(capturer.isScreencast)
        videoSource = source
        capturer.initialize(helper, appContext, source.capturerObserver)
        capturer.startCapture(width, height, fps)

        val track = factory.createVideoTrack(VIDEO_TRACK_ID, source)
        track.setEnabled(true)
        localVideoTrack = track
        pc.addTrack(track, listOf(STREAM_ID))

        val constraints = MediaConstraints().apply {
            mandatory.add(MediaConstraints.KeyValuePair("OfferToReceiveAudio", "false"))
            mandatory.add(MediaConstraints.KeyValuePair("OfferToReceiveVideo", "false"))
        }
        pc.createOffer(object : SdpAdapter("createOffer") {
            override fun onCreateSuccess(desc: SessionDescription?) {
                if (desc == null || closed.get()) {
                    return
                }
                pc.setLocalDescription(object : SdpAdapter("setLocalOffer") {
                    override fun onSetSuccess() {
                        signaling.sendOffer(desc.description)
                        scheduleIceConnectTimeout()
                    }
                }, desc)
            }
        }, constraints)
    }

    private fun scheduleIceConnectTimeout() {
        iceTimeoutFuture?.cancel(false)
        iceTimeoutFuture = executor.schedule(
            {
                if (closed.get() || connectedOnce.get() || failedOnce.get()) {
                    return@schedule
                }
                Log.w(TAG, "ICE connect timeout after ${ICE_CONNECT_TIMEOUT_MS}ms")
                failOnce("ice_timeout")
            },
            ICE_CONNECT_TIMEOUT_MS,
            TimeUnit.MILLISECONDS,
        )
    }

    private fun cancelIceConnectTimeout() {
        iceTimeoutFuture?.cancel(false)
        iceTimeoutFuture = null
    }

    private fun failOnce(message: String) {
        if (!failedOnce.compareAndSet(false, true)) {
            return
        }
        cancelIceConnectTimeout()
        // 仅在 relay-only 模式下累计失败；一旦已降级就不再触发统计。
        if (usingRelayOnly &&
            (message == "ice_timeout" || message == "ice_failed" || message == "pc_failed")
        ) {
            val count = relayFailStreak.incrementAndGet()
            if (count >= RELAY_FAIL_FALLBACK_THRESHOLD &&
                relayFallbackActive.compareAndSet(false, true)
            ) {
                Log.w(
                    TAG,
                    "relay failed $count times, fallback iceTransportsType=ALL for this process",
                )
            }
        } else if (!usingRelayOnly) {
            // 非 relay 模式下一次成功或失败都不再影响全局开关，忽略。
        }
        signaling.onSessionFailed(message)
    }

    private fun displaySize(): Pair<Int, Int> {
        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        (appContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
            .defaultDisplay.getRealMetrics(metrics)
        return metrics.widthPixels to metrics.heightPixels
    }

    private fun releaseInternal() {
        cancelIceConnectTimeout()
        runCatching { videoCapturer?.stopCapture() }
        runCatching { videoCapturer?.dispose() }
        videoCapturer = null
        pushCapturer = null
        runCatching { localVideoTrack?.dispose() }
        localVideoTrack = null
        runCatching { videoSource?.dispose() }
        videoSource = null
        runCatching { surfaceTextureHelper?.dispose() }
        surfaceTextureHelper = null
        runCatching { peerConnection?.dispose() }
        peerConnection = null
    }

    private val pcObserver = object : PeerConnection.Observer {
        override fun onSignalingChange(state: PeerConnection.SignalingState?) = Unit
        override fun onIceConnectionChange(state: PeerConnection.IceConnectionState?) {
            if (state == PeerConnection.IceConnectionState.CONNECTED ||
                state == PeerConnection.IceConnectionState.COMPLETED
            ) {
                connectedOnce.set(true)
                // relay-only 跑通过一次就视为 TURN 服务器健康，清掉之前的失败计数。
                if (usingRelayOnly) {
                    relayFailStreak.set(0)
                }
                cancelIceConnectTimeout()
                signaling.onSessionConnected()
            } else if (state == PeerConnection.IceConnectionState.FAILED) {
                failOnce("ice_failed")
            }
        }

        override fun onIceConnectionReceivingChange(receiving: Boolean) = Unit
        override fun onIceGatheringChange(state: PeerConnection.IceGatheringState?) = Unit

        override fun onIceCandidate(candidate: IceCandidate?) {
            if (candidate == null || closed.get()) {
                return
            }
            signaling.sendIce(
                JSONObject()
                    .put("candidate", candidate.sdp)
                    .put("sdpMid", candidate.sdpMid)
                    .put("sdpMLineIndex", candidate.sdpMLineIndex),
            )
        }

        override fun onIceCandidatesRemoved(candidates: Array<out IceCandidate>?) = Unit
        override fun onAddStream(stream: MediaStream?) = Unit
        override fun onRemoveStream(stream: MediaStream?) = Unit
        override fun onDataChannel(channel: DataChannel?) = Unit
        override fun onRenegotiationNeeded() = Unit
        override fun onAddTrack(receiver: RtpReceiver?, streams: Array<out MediaStream>?) = Unit
        override fun onTrack(transceiver: RtpTransceiver?) = Unit
        override fun onConnectionChange(newState: PeerConnection.PeerConnectionState?) {
            if (newState == PeerConnection.PeerConnectionState.FAILED) {
                failOnce("pc_failed")
            }
        }
    }

    private open class SdpAdapter(private val label: String) : SdpObserver {
        override fun onCreateSuccess(desc: SessionDescription?) = Unit
        override fun onSetSuccess() = Unit
        override fun onCreateFailure(error: String?) {
            Log.e(TAG, "$label create failed: $error")
        }

        override fun onSetFailure(error: String?) {
            Log.e(TAG, "$label set failed: $error")
        }
    }

    companion object {
        private const val TAG = "ScreenWebRtcSession"
        private const val VIDEO_TRACK_ID = "screen_video"
        private const val STREAM_ID = "screen_stream"
        private const val TARGET_WIDTH = 720
        /** Cross-NAT without TURN often hangs in checking; force JPEG/fallback. */
        private const val ICE_CONNECT_TIMEOUT_MS = 12_000L

        /** 连续 ICE 失败达到此阈值后，当前进程后续所有 session 从 RELAY 降级为 ALL。 */
        private const val RELAY_FAIL_FALLBACK_THRESHOLD = 2

        /** 进程级的 relay-only 降级开关；一旦翻 true，直到进程重启都不再尝试 relay-only。 */
        private val relayFallbackActive = AtomicBoolean(false)

        /** 连续 relay-only 失败计数；CONNECTED 时清零。 */
        private val relayFailStreak = AtomicInteger(0)

        fun parseIceServers(raw: JSONArray?): List<PeerConnection.IceServer> {
            val out = mutableListOf<PeerConnection.IceServer>()
            if (raw != null) {
                for (i in 0 until raw.length()) {
                    val item = raw.optJSONObject(i) ?: continue
                    val urlsValue = item.opt("urls")
                    val urls = when (urlsValue) {
                        is JSONArray -> buildList {
                            for (j in 0 until urlsValue.length()) {
                                val u = urlsValue.optString(j)
                                if (u.isNotBlank()) add(u)
                            }
                        }
                        is String -> listOf(urlsValue).filter { it.isNotBlank() }
                        else -> emptyList()
                    }
                    if (urls.isEmpty()) continue
                    val builder = PeerConnection.IceServer.builder(urls)
                    val username = item.optString("username")
                    val credential = item.optString("credential")
                    if (username.isNotBlank()) {
                        builder.setUsername(username)
                    }
                    if (credential.isNotBlank()) {
                        builder.setPassword(credential)
                    }
                    out.add(builder.createIceServer())
                }
            }
            if (out.isEmpty()) {
                out.add(
                    PeerConnection.IceServer.builder("stun:stun.l.google.com:19302")
                        .createIceServer(),
                )
            }
            return out
        }
    }
}
