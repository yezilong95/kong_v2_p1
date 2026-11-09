package com.vibelink.client.webrtc

import android.content.Context
import org.webrtc.DefaultVideoDecoderFactory
import org.webrtc.DefaultVideoEncoderFactory
import org.webrtc.EglBase
import org.webrtc.PeerConnectionFactory
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Process-wide PeerConnectionFactory + shared EGL context.
 */
object WebRtcEngine {
    private val initialized = AtomicBoolean(false)

    @Volatile
    private var eglBase: EglBase? = null

    @Volatile
    private var factory: PeerConnectionFactory? = null

    fun ensureInitialized(context: Context) {
        if (initialized.get() && factory != null) {
            return
        }
        synchronized(this) {
            if (initialized.get() && factory != null) {
                return
            }
            val app = context.applicationContext
            PeerConnectionFactory.initialize(
                PeerConnectionFactory.InitializationOptions.builder(app)
                    .setEnableInternalTracer(false)
                    .createInitializationOptions(),
            )
            val egl = EglBase.create()
            eglBase = egl
            val encoderFactory = DefaultVideoEncoderFactory(
                egl.eglBaseContext,
                /* enableIntelVp8Encoder */ true,
                /* enableH264HighProfile */ true,
            )
            val decoderFactory = DefaultVideoDecoderFactory(egl.eglBaseContext)
            factory = PeerConnectionFactory.builder()
                .setVideoEncoderFactory(encoderFactory)
                .setVideoDecoderFactory(decoderFactory)
                .createPeerConnectionFactory()
            initialized.set(true)
        }
    }

    fun factory(context: Context): PeerConnectionFactory {
        ensureInitialized(context)
        return factory ?: error("webrtc_factory_missing")
    }

    fun egl(context: Context): EglBase {
        ensureInitialized(context)
        return eglBase ?: error("webrtc_egl_missing")
    }
}
