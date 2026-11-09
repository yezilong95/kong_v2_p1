package com.vibelink.client.webrtc

import android.content.Context
import android.graphics.Bitmap
import android.os.SystemClock
import org.webrtc.CapturerObserver
import org.webrtc.JavaI420Buffer
import org.webrtc.SurfaceTextureHelper
import org.webrtc.VideoCapturer
import org.webrtc.VideoFrame
import java.nio.ByteBuffer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Accepts ARGB [Bitmap] frames (screenshot / UI-tree fake screen) and feeds WebRTC.
 */
class PushBitmapVideoCapturer : VideoCapturer {
    private var capturerObserver: CapturerObserver? = null
    private val running = AtomicBoolean(false)
    private var width = 720
    private var height = 1280

    override fun initialize(
        surfaceTextureHelper: SurfaceTextureHelper?,
        context: Context?,
        capturerObserver: CapturerObserver?,
    ) {
        this.capturerObserver = capturerObserver
    }

    override fun startCapture(width: Int, height: Int, framerate: Int) {
        this.width = width.coerceAtLeast(2)
        this.height = height.coerceAtLeast(2)
        running.set(true)
        capturerObserver?.onCapturerStarted(true)
    }

    override fun stopCapture() {
        if (!running.getAndSet(false)) {
            return
        }
        capturerObserver?.onCapturerStopped()
    }

    override fun changeCaptureFormat(width: Int, height: Int, framerate: Int) {
        this.width = width.coerceAtLeast(2)
        this.height = height.coerceAtLeast(2)
    }

    override fun dispose() {
        running.set(false)
        capturerObserver = null
    }

    override fun isScreencast(): Boolean = true

    /**
     * Push one frame. Recycles [bitmap] only if [recycleAfter] is true.
     */
    fun offerBitmap(bitmap: Bitmap, recycleAfter: Boolean = false) {
        if (!running.get()) {
            if (recycleAfter && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            return
        }
        val observer = capturerObserver ?: run {
            if (recycleAfter && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            return
        }
        val frameBitmap = if (bitmap.config == Bitmap.Config.ARGB_8888) {
            bitmap
        } else {
            bitmap.copy(Bitmap.Config.ARGB_8888, false) ?: run {
                if (recycleAfter && !bitmap.isRecycled) {
                    bitmap.recycle()
                }
                return
            }
        }
        val copied = frameBitmap !== bitmap
        try {
            val w = frameBitmap.width and 1.inv()
            val h = frameBitmap.height and 1.inv()
            if (w < 2 || h < 2) {
                return
            }
            // Bail if stop/dispose raced in after the running check above.
            if (!running.get()) {
                return
            }
            val i420 = argbBitmapToI420(frameBitmap, w, h) ?: return
            if (!running.get()) {
                i420.release()
                return
            }
            val frame = VideoFrame(i420, 0, SystemClock.elapsedRealtimeNanos())
            try {
                observer.onFrameCaptured(frame)
            } catch (_: Throwable) {
                // VideoSource may already be disposed (ICE timeout / hangup race).
            } finally {
                frame.release()
            }
            width = w
            height = h
        } finally {
            if (copied && !frameBitmap.isRecycled) {
                frameBitmap.recycle()
            }
            if (recycleAfter && !bitmap.isRecycled) {
                bitmap.recycle()
            }
        }
    }

    companion object {
        private fun argbBitmapToI420(bitmap: Bitmap, width: Int, height: Int): JavaI420Buffer? {
            return try {
                val argb = IntArray(width * height)
                bitmap.getPixels(argb, 0, width, 0, 0, width, height)
                val buffer = JavaI420Buffer.allocate(width, height)
                val yBuf = buffer.dataY
                val uBuf = buffer.dataU
                val vBuf = buffer.dataV
                val yStride = buffer.strideY
                val uStride = buffer.strideU
                val vStride = buffer.strideV
                fillI420(argb, width, height, yBuf, uBuf, vBuf, yStride, uStride, vStride)
                buffer
            } catch (_: Throwable) {
                null
            }
        }

        private fun fillI420(
            argb: IntArray,
            width: Int,
            height: Int,
            yBuf: ByteBuffer,
            uBuf: ByteBuffer,
            vBuf: ByteBuffer,
            yStride: Int,
            uStride: Int,
            vStride: Int,
        ) {
            val yRow = ByteArray(yStride)
            val uRow = ByteArray(uStride)
            val vRow = ByteArray(vStride)
            var index = 0
            for (y in 0 until height) {
                for (x in 0 until width) {
                    val color = argb[index++]
                    val r = (color shr 16) and 0xff
                    val g = (color shr 8) and 0xff
                    val b = color and 0xff
                    // BT.601 full range-ish
                    val yVal = ((66 * r + 129 * g + 25 * b + 128) shr 8) + 16
                    yRow[x] = yVal.coerceIn(0, 255).toByte()
                    if (y % 2 == 0 && x % 2 == 0) {
                        val uVal = ((-38 * r - 74 * g + 112 * b + 128) shr 8) + 128
                        val vVal = ((112 * r - 94 * g - 18 * b + 128) shr 8) + 128
                        val uvIndex = x / 2
                        uRow[uvIndex] = uVal.coerceIn(0, 255).toByte()
                        vRow[uvIndex] = vVal.coerceIn(0, 255).toByte()
                    }
                }
                yBuf.position(y * yStride)
                yBuf.put(yRow, 0, width)
                if (y % 2 == 0) {
                    val uvRow = y / 2
                    uBuf.position(uvRow * uStride)
                    uBuf.put(uRow, 0, width / 2)
                    vBuf.position(uvRow * vStride)
                    vBuf.put(vRow, 0, width / 2)
                }
            }
            yBuf.position(0)
            uBuf.position(0)
            vBuf.position(0)
        }
    }
}
