package com.vibelink.client.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.Image
import android.media.ImageReader
import android.os.Handler
import android.os.HandlerThread
import android.util.Base64
import android.util.Size
import androidx.core.content.ContextCompat
import com.vibelink.client.accessibility.ScreenCaptureBridge
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.abs

class CameraStreamCapturer(
    private val context: Context,
) {
    private val cameraManager = context.getSystemService(CameraManager::class.java)
    private var cameraDevice: CameraDevice? = null
    private var captureSession: CameraCaptureSession? = null
    private var imageReader: ImageReader? = null
    private var handlerThread: HandlerThread? = null
    private var handler: Handler? = null
    private val running = AtomicBoolean(false)

    @Volatile
    private var latestJpeg: ByteArray? = null

    @Volatile
    private var frameWidth = 0

    @Volatile
    private var frameHeight = 0

    @Volatile
    private var currentFacing = ""

    fun isRunning(): Boolean = running.get()

    fun currentFacing(): String = currentFacing

    fun start(facing: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        val manager = cameraManager ?: return false

        return runCatching {
            stop()

            val cameraId = selectCameraId(manager, facing) ?: return false
            val thread = HandlerThread("camera_stream").apply { start() }
            handlerThread = thread
            val bgHandler = Handler(thread.looper)
            handler = bgHandler

            val characteristics = manager.getCameraCharacteristics(cameraId)
            val map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                ?: return false
            val sizes = map.getOutputSizes(ImageFormat.YUV_420_888) ?: return false
            val size = chooseSize(sizes)

            val reader = ImageReader.newInstance(
                size.width,
                size.height,
                ImageFormat.YUV_420_888,
                2,
            )
            imageReader = reader
            reader.setOnImageAvailableListener({ imageReader ->
                runCatching {
                    if (!running.get()) {
                        return@runCatching
                    }
                    val image = imageReader.acquireLatestImage() ?: return@runCatching
                    try {
                        val jpeg = yuvImageToJpeg(image, JPEG_QUALITY) ?: return@runCatching
                        latestJpeg = jpeg
                        frameWidth = image.width
                        frameHeight = image.height
                    } finally {
                        image.close()
                    }
                }
            }, bgHandler)

            val latch = CountDownLatch(1)
            var opened = false
            try {
                manager.openCamera(
                    cameraId,
                    object : CameraDevice.StateCallback() {
                        override fun onOpened(camera: CameraDevice) {
                            cameraDevice = camera
                            createSession(camera, bgHandler) { ok ->
                                opened = ok
                                if (ok) {
                                    running.set(true)
                                    currentFacing = facing
                                }
                                latch.countDown()
                            }
                        }

                        override fun onDisconnected(camera: CameraDevice) {
                            runCatching { camera.close() }
                            cameraDevice = null
                            latch.countDown()
                        }

                        override fun onError(camera: CameraDevice, error: Int) {
                            runCatching { camera.close() }
                            cameraDevice = null
                            latch.countDown()
                        }
                    },
                    bgHandler,
                )
            } catch (_: SecurityException) {
                stop()
                return false
            }

            latch.await(8, TimeUnit.SECONDS)
            opened && running.get()
        }.getOrDefault(false)
    }

    fun captureOnce(): ScreenCaptureBridge.CaptureResult {
        if (!running.get()) {
            return ScreenCaptureBridge.CaptureResult(false, error = "camera_not_running")
        }

        val jpeg = latestJpeg
        if (jpeg == null || jpeg.isEmpty()) {
            return ScreenCaptureBridge.CaptureResult(false, error = "no_frame")
        }

        val width = frameWidth
        val height = frameHeight
        val base64 = Base64.encodeToString(jpeg, Base64.NO_WRAP)
        return ScreenCaptureBridge.CaptureResult(
            ok = true,
            data = base64,
            screenWidth = width,
            screenHeight = height,
            imageWidth = width,
            imageHeight = height,
        )
    }

    fun stop() {
        running.set(false)
        currentFacing = ""
        runCatching { captureSession?.close() }
        runCatching { cameraDevice?.close() }
        runCatching { imageReader?.close() }
        captureSession = null
        cameraDevice = null
        imageReader = null
        handlerThread?.quitSafely()
        handlerThread = null
        handler = null
        latestJpeg = null
        frameWidth = 0
        frameHeight = 0
    }

    private fun createSession(
        camera: CameraDevice,
        bgHandler: Handler,
        onDone: (Boolean) -> Unit,
    ) {
        val reader = imageReader ?: run {
            onDone(false)
            return
        }

        try {
            camera.createCaptureSession(
                listOf(reader.surface),
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigured(session: CameraCaptureSession) {
                        captureSession = session
                        try {
                            val request = camera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW).apply {
                                addTarget(reader.surface)
                                set(CaptureRequest.CONTROL_MODE, CaptureRequest.CONTROL_MODE_AUTO)
                            }.build()
                            session.setRepeatingRequest(request, null, bgHandler)
                            onDone(true)
                        } catch (_: Exception) {
                            onDone(false)
                        }
                    }

                    override fun onConfigureFailed(session: CameraCaptureSession) {
                        onDone(false)
                    }
                },
                bgHandler,
            )
        } catch (_: Exception) {
            onDone(false)
        }
    }

    private fun selectCameraId(manager: CameraManager, facing: String): String? {
        val wantFront = facing == "front"
        for (id in manager.cameraIdList) {
            val lensFacing = manager.getCameraCharacteristics(id)
                .get(CameraCharacteristics.LENS_FACING)
            if (wantFront && lensFacing == CameraCharacteristics.LENS_FACING_FRONT) {
                return id
            }
            if (!wantFront && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id
            }
        }
        return null
    }

    private fun chooseSize(sizes: Array<Size>): Size {
        val metrics = context.resources.displayMetrics
        val displayW = metrics.widthPixels.coerceAtLeast(1)
        val displayH = metrics.heightPixels.coerceAtLeast(1)
        val targetRatio = displayW.toFloat() / displayH

        return sizes
            .filter { it.width <= MAX_CAPTURE_WIDTH && it.height <= MAX_CAPTURE_HEIGHT }
            .minByOrNull { size ->
                val ratio = size.width.toFloat() / size.height
                abs(ratio - targetRatio) * 10_000 + abs(size.width - TARGET_WIDTH)
            }
            ?: sizes.maxByOrNull { it.width * it.height }
            ?: sizes.first()
    }

    private fun yuvImageToJpeg(image: Image, quality: Int): ByteArray? {
        if (image.format != ImageFormat.YUV_420_888) {
            return null
        }

        val nv21 = yuv420888ToNv21(image) ?: return null
        val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        val output = ByteArrayOutputStream()
        val ok = yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), quality, output)
        if (!ok) {
            return null
        }
        return output.toByteArray()
    }

    private fun yuv420888ToNv21(image: Image): ByteArray? {
        val planes = image.planes
        if (planes.size < 3) {
            return null
        }

        val width = image.width
        val height = image.height
        val yPlane = planes[0]
        val uPlane = planes[1]
        val vPlane = planes[2]
        val yBuffer = yPlane.buffer.duplicate()
        val uBuffer = uPlane.buffer.duplicate()
        val vBuffer = vPlane.buffer.duplicate()
        val nv21 = ByteArray(width * height + width * height / 2)
        copyPlane(yBuffer, yPlane.rowStride, yPlane.pixelStride, width, height, nv21, 0, 1)

        val chromaHeight = height / 2
        val chromaWidth = width / 2
        var offset = width * height
        for (row in 0 until chromaHeight) {
            for (col in 0 until chromaWidth) {
                val vuIndex = row * vPlane.rowStride + col * vPlane.pixelStride
                nv21[offset++] = vBuffer.get(vuIndex)
                nv21[offset++] = uBuffer.get(row * uPlane.rowStride + col * uPlane.pixelStride)
            }
        }
        return nv21
    }

    private fun copyPlane(
        buffer: ByteBuffer,
        rowStride: Int,
        pixelStride: Int,
        width: Int,
        height: Int,
        output: ByteArray,
        offset: Int,
        outputPixelStride: Int,
    ) {
        var outputIndex = offset
        for (row in 0 until height) {
            var inputIndex = row * rowStride
            for (col in 0 until width) {
                output[outputIndex] = buffer.get(inputIndex)
                outputIndex += outputPixelStride
                inputIndex += pixelStride
            }
        }
    }

    companion object {
        private const val TARGET_WIDTH = 1280
        private const val MAX_CAPTURE_WIDTH = 1920
        private const val MAX_CAPTURE_HEIGHT = 1920
        private const val JPEG_QUALITY = 75
    }
}
