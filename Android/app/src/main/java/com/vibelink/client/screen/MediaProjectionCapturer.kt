package com.vibelink.client.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.Image
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock
import android.util.Base64
import android.util.DisplayMetrics
import android.view.WindowManager
import com.vibelink.client.accessibility.ScreenCaptureBridge
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * Continuous screen capture via MediaProjection + VirtualDisplay.
 * Accessibility overlays are typically NOT included — black-screen overlay can stay up.
 */
class MediaProjectionCapturer(
    private val context: Context,
) {
    private var mediaProjection: android.media.projection.MediaProjection? = null
    private var virtualDisplay: VirtualDisplay? = null
    private var imageReader: ImageReader? = null
    private var captureThread: HandlerThread? = null
    private var captureHandler: Handler? = null
    private val running = AtomicBoolean(false)
    private val latestGeneration = AtomicInteger(0)
    private var lastConsumedGeneration = -1

    @Volatile
    private var latestResult: ScreenCaptureBridge.CaptureResult? = null

    @Volatile
    private var screenWidth = 0

    @Volatile
    private var screenHeight = 0

    fun isRunning(): Boolean = running.get()

    fun start(): Boolean {
        if (running.get()) {
            return true
        }
        if (!MediaProjectionHolder.hasConsent()) {
            return false
        }
        val projection = MediaProjectionHolder.getOrCreateProjection(context) ?: return false
        mediaProjection = projection

        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        @Suppress("DEPRECATION")
        wm.defaultDisplay.getRealMetrics(metrics)
        screenWidth = metrics.widthPixels
        screenHeight = metrics.heightPixels
        val density = metrics.densityDpi

        val captureW = (screenWidth * CAPTURE_SCALE).toInt().coerceAtLeast(180).coerceAtMost(MAX_CAPTURE_WIDTH)
        val captureH = (screenHeight * captureW / screenWidth.toFloat()).toInt().coerceAtLeast(320)

        val thread = HandlerThread("media_projection_capture").apply { start() }
        captureThread = thread
        val handler = Handler(thread.looper)
        captureHandler = handler

        val reader = ImageReader.newInstance(captureW, captureH, PixelFormat.RGBA_8888, IMAGE_BUFFER_COUNT)
        imageReader = reader
        reader.setOnImageAvailableListener({ available ->
            consumeAvailableImage(available)
        }, handler)

        projection.registerCallback(
            object : android.media.projection.MediaProjection.Callback() {
                override fun onStop() {
                    running.set(false)
                    releaseCapturePipeline()
                    mediaProjection = null
                    MediaProjectionHolder.clear()
                }
            },
            handler,
        )

        virtualDisplay = createMirrorDisplay(projection, captureW, captureH, density, reader)
        if (virtualDisplay == null) {
            releaseCapturePipeline()
            mediaProjection = null
            return false
        }

        running.set(true)
        return true
    }

    fun stop(releaseProjection: Boolean = false) {
        running.set(false)
        releaseCapturePipeline()
        if (releaseProjection) {
            runCatching { mediaProjection?.stop() }
            mediaProjection = null
            MediaProjectionHolder.clear()
        } else {
            mediaProjection = null
        }
    }

    fun captureOnce(): ScreenCaptureBridge.CaptureResult {
        if (!running.get()) {
            if (!start()) {
                return ScreenCaptureBridge.CaptureResult(false, error = "projection_not_ready")
            }
        }
        val timeoutAt = SystemClock.elapsedRealtime() + FRAME_WAIT_MS
        while (SystemClock.elapsedRealtime() < timeoutAt) {
            val generation = latestGeneration.get()
            val current = latestResult
            if (current != null && current.ok && generation != lastConsumedGeneration) {
                lastConsumedGeneration = generation
                return current
            }
            SystemClock.sleep(20L)
        }
        val fallback = latestResult
        if (fallback != null && fallback.ok && lastConsumedGeneration < 0) {
            lastConsumedGeneration = latestGeneration.get()
            return fallback
        }
        return ScreenCaptureBridge.CaptureResult(false, error = "no_frame")
    }

    private fun consumeAvailableImage(reader: ImageReader) {
        var image: Image? = null
        try {
            while (true) {
                val next = reader.acquireLatestImage() ?: break
                image?.close()
                image = next
            }
            val current = image ?: return
            image = null
            val bitmap = imageToBitmap(current)
            current.close()
            if (bitmap == null) {
                return
            }
            val scaled = scaleBitmap(bitmap)
            if (scaled !== bitmap) {
                bitmap.recycle()
            }
            val jpeg = bitmapToJpegBase64(scaled)
            val imageWidth = scaled.width
            val imageHeight = scaled.height
            scaled.recycle()
            if (jpeg.isBlank()) {
                return
            }
            latestResult = ScreenCaptureBridge.CaptureResult(
                ok = true,
                data = jpeg,
                screenWidth = screenWidth,
                screenHeight = screenHeight,
                imageWidth = imageWidth,
                imageHeight = imageHeight,
            )
            latestGeneration.incrementAndGet()
        } catch (_: Exception) {
            // 下一帧再试，避免打断 VirtualDisplay
        } finally {
            image?.close()
        }
    }

    private fun createMirrorDisplay(
        projection: android.media.projection.MediaProjection,
        width: Int,
        height: Int,
        density: Int,
        reader: ImageReader,
    ): VirtualDisplay? {
        val attempts = intArrayOf(
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC or DisplayManager.VIRTUAL_DISPLAY_FLAG_PRESENTATION,
            0,
        )
        for (flags in attempts) {
            val display = runCatching {
                projection.createVirtualDisplay(
                    "remote_control_cast",
                    width,
                    height,
                    density,
                    flags,
                    reader.surface,
                    null,
                    captureHandler,
                )
            }.getOrNull()
            if (display != null) {
                return display
            }
        }
        return null
    }

    private fun releaseCapturePipeline() {
        runCatching { imageReader?.setOnImageAvailableListener(null, null) }
        runCatching { virtualDisplay?.release() }
        runCatching { imageReader?.close() }
        virtualDisplay = null
        imageReader = null
        latestResult = null
        lastConsumedGeneration = -1
        latestGeneration.set(0)
        captureThread?.quitSafely()
        captureThread = null
        captureHandler = null
    }

    private fun imageToBitmap(image: Image): Bitmap? {
        val plane = image.planes.firstOrNull() ?: return null
        val buffer: ByteBuffer = plane.buffer
        val pixelStride = plane.pixelStride
        val rowStride = plane.rowStride
        val rowPadding = rowStride - pixelStride * image.width
        val bitmap = Bitmap.createBitmap(
            image.width + rowPadding / pixelStride,
            image.height,
            Bitmap.Config.ARGB_8888,
        )
        bitmap.copyPixelsFromBuffer(buffer.apply { rewind() })
        return if (rowPadding == 0) {
            bitmap
        } else {
            val cropped = Bitmap.createBitmap(bitmap, 0, 0, image.width, image.height)
            bitmap.recycle()
            cropped
        }
    }

    private fun scaleBitmap(source: Bitmap): Bitmap {
        if (source.width <= MAX_FRAME_WIDTH) {
            return source
        }
        val ratio = MAX_FRAME_WIDTH.toFloat() / source.width
        val targetH = (source.height * ratio).toInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(source, MAX_FRAME_WIDTH, targetH, true)
    }

    private fun bitmapToJpegBase64(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, JPEG_QUALITY, stream)) {
            return ""
        }
        return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
    }

    companion object {
        private const val CAPTURE_SCALE = 0.35f
        private const val MAX_CAPTURE_WIDTH = 540
        private const val MAX_FRAME_WIDTH = 320
        private const val JPEG_QUALITY = 28
        private const val FRAME_WAIT_MS = 180L
        private const val IMAGE_BUFFER_COUNT = 4
    }
}
