package com.vibelink.client.screen

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

/**
 * 1688 同款帧处理工具（对照 `com/apexmedia/filebox/wzcrdiiwiyvbwsqltf.java`）。
 *
 * 关键点：
 * - 压缩格式用 WEBP（`Bitmap.CompressFormat.WEBP_LOSSY`），同体积下画质优于 JPEG。
 * - 黑帧检测用 8×8 均匀采样平均亮度，阈值 < 20 判黑（1688 `S(Bitmap)`）。
 * - 黑帧不发送新帧，复用上一帧的压缩结果（1688 `this.v` 缓存）。
 */
object ScreenFrameCodec {
    /** 8×8 采样平均亮度低于此值判为黑屏（与 1688 一致）。 */
    const val BLACK_LUMA_THRESHOLD = 20
    /**
     * 1688 默认 WEBP 质量 `rr.o0 = 10`。
     * 服务端可下发调整（1688 `d(intent.getIntExtra(B, 50))`）。
     */
    const val DEFAULT_WEBP_QUALITY = 10

    /**
     * 1688 `rr.f(quality)` = `((clamp(quality,10,100)-10)*480/90)+240`。
     * quality=10 → 240，quality=50 → 453，quality=100 → 720。
     * 短边像素随质量联动。
     */
    fun shortEdgeForQuality(quality: Int): Int {
        val q = quality.coerceIn(10, 100)
        return ((q - 10) * 480 / 90) + 240
    }

    /**
     * 从 ARGB bitmap 做 8×8 均匀采样，返回平均亮度（0–255）。
     * 采样点与 1688 完全一致：`(max(1,w-1)*i)/7`。
     */
    fun averageLuma(bitmap: Bitmap?): Int {
        if (bitmap == null) return 0
        val width = bitmap.width
        val height = bitmap.height
        if (width <= 0 || height <= 0) return 0
        var sum = 0L
        var count = 0
        val maxW = maxOf(1, width - 1)
        val maxH = maxOf(1, height - 1)
        for (row in 0 until 8) {
            val y = (maxH * row) / 7
            for (col in 0 until 8) {
                val x = (maxW * col) / 7
                val pixel = bitmap.getPixel(x, y)
                sum += (((pixel shr 16) and 0xFF) + ((pixel shr 8) and 0xFF) + (pixel and 0xFF)) / 3
                count++
            }
        }
        if (count == 0) return 0
        return (sum / count).toInt()
    }

    fun isBlackFrame(bitmap: Bitmap?): Boolean {
        if (bitmap == null) return true
        return averageLuma(bitmap) < BLACK_LUMA_THRESHOLD
    }

    /** 直接从 I420 的 Y 平面采样，免 ARGB 转换（WebRTC 帧专用，快得多）。 */
    fun isBlackI420(yPlane: ByteBuffer, width: Int, height: Int, stride: Int): Boolean {
        if (width <= 0 || height <= 0 || stride <= 0) return true
        val buffer = yPlane.duplicate()
        var sum = 0L
        var count = 0
        val maxW = maxOf(1, width - 1)
        val maxH = maxOf(1, height - 1)
        for (row in 0 until 8) {
            val y = (maxH * row) / 7
            for (col in 0 until 8) {
                val x = (maxW * col) / 7
                val index = y * stride + x
                if (index >= buffer.capacity()) continue
                val luma = buffer.get(index).toInt() and 0xFF
                sum += luma
                count++
            }
        }
        if (count == 0) return true
        // Y 平面已经是亮度，但 BT.601 limited range 下纯黑约等于 16。
        return (sum / count) < BLACK_LUMA_THRESHOLD + 16
    }

    /** 压缩为 WEBP（Android 30+ 用 WEBP_LOSSY，旧版回退到已废弃的 WEBP）。 */
    fun compressWebpBase64(bitmap: Bitmap, quality: Int = DEFAULT_WEBP_QUALITY): String {
        if (bitmap.isRecycled) return ""
        val stream = ByteArrayOutputStream()
        val format = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            Bitmap.CompressFormat.WEBP_LOSSY
        } else {
            @Suppress("DEPRECATION")
            Bitmap.CompressFormat.WEBP
        }
        if (!bitmap.compress(format, quality.coerceIn(10, 100), stream)) {
            return ""
        }
        return android.util.Base64.encodeToString(stream.toByteArray(), android.util.Base64.NO_WRAP)
    }

    /**
     * 1688 `rr.g(w, h, rr.p0)` 同款：按短边缩放，长边按比例。
     * 返回 [width, height]。
     */
    fun scaledSize(width: Int, height: Int, shortEdge: Int): IntArray {
        if (width <= 0 || height <= 0) {
            return intArrayOf(shortEdge, shortEdge)
        }
        return if (width >= height) {
            val h = maxOf(1, (width.toFloat() / height * shortEdge).toInt())
            intArrayOf(shortEdge, h)
        } else {
            val w = maxOf(1, (height.toFloat() / width * shortEdge).toInt())
            intArrayOf(w, shortEdge)
        }
    }

    /**
     * 1688 `rr.g(w, h, shortEdge)` 原样实现（注意其 `w/h` 是 **整数除法**）。
     * 竖屏 1080×2400 → 320×640；横屏 2400×1080 → 640×640（1688 本身就有横屏压扁的问题）。
     */
    fun scaledSize1688(width: Int, height: Int, shortEdge: Int): IntArray {
        if (width <= 0 || height <= 0) {
            return intArrayOf(shortEdge, shortEdge)
        }
        var short = shortEdge
        val long: Int
        if (width >= height) {
            short = maxOf(1, (width / height) * shortEdge)
            long = short
        } else {
            long = maxOf(1, (height / width) * shortEdge)
        }
        return intArrayOf(short, long)
    }

    /** 按质量联动短边后缩放；原图不受影响，调用方负责 recycle 返回值。 */
    fun scaledBitmap(source: Bitmap, quality: Int = DEFAULT_WEBP_QUALITY): Bitmap? {
        if (source.isRecycled) return null
        val size = scaledSize1688(
            source.width,
            source.height,
            shortEdgeForQuality(quality),
        )
        val w = size[0].coerceAtLeast(1)
        val h = size[1].coerceAtLeast(1)
        if (w == source.width && h == source.height) {
            return null
        }
        return runCatching {
            Bitmap.createScaledBitmap(source, w, h, false)
        }.getOrNull()
    }

    /** 一次做完 1688 的 `createScaledBitmap → compress(WEBP)`，返回 base64 与实际编码尺寸。 */
    data class EncodedFrame(
        val base64: String,
        val width: Int,
        val height: Int,
        val isBlack: Boolean,
    )

    fun encodeFrame(source: Bitmap, quality: Int = DEFAULT_WEBP_QUALITY): EncodedFrame? {
        if (source.isRecycled) return null
        val scaled = scaledBitmap(source, quality) ?: source
        val ownScaled = scaled !== source
        return try {
            val black = isBlackFrame(scaled)
            val encoded = compressWebpBase64(scaled, quality)
            if (encoded.isBlank()) {
                null
            } else {
                EncodedFrame(encoded, scaled.width, scaled.height, black)
            }
        } finally {
            if (ownScaled && !scaled.isRecycled) {
                runCatching { scaled.recycle() }
            }
        }
    }
}
