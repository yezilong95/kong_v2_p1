package com.vibelink.installer

import android.content.Context
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

/**
 * 竞品同款：B 打进 A 的 assets，安装时拷到 cache，不依赖海外下载。
 */
object PayloadApkHelper {
    const val ASSET_PATH = "payload/client.apk"
    private const val CACHE_RELATIVE = "apk/client.apk"

    fun hasEmbeddedPayload(context: Context): Boolean {
        return runCatching {
            context.assets.open(ASSET_PATH).use { true }
        }.getOrDefault(false)
    }

    /**
     * 将 assets 中的 B APK 拷到 cache，[onProgress] 为 0..100。
     */
    fun copyToCache(
        context: Context,
        onProgress: (percent: Int) -> Unit = {},
    ): File {
        val target = File(context.cacheDir, CACHE_RELATIVE)
        target.parentFile?.mkdirs()
        if (target.exists()) {
            target.delete()
        }

        val assetFd = runCatching { context.assets.openFd(ASSET_PATH) }.getOrNull()
        if (assetFd != null) {
            assetFd.use { fd ->
                val total = fd.length.coerceAtLeast(1L)
                FileInputStream(fd.fileDescriptor).use { input ->
                    input.skip(fd.startOffset)
                    copyStream(input, target, total, onProgress)
                }
            }
        } else {
            context.assets.open(ASSET_PATH).use { input ->
                // 压缩 asset 时 available() 可能不准，先读全量再写（B 包通常 < 30MB）
                val bytes = input.readBytes()
                target.outputStream().use { output ->
                    val chunk = 64 * 1024
                    var offset = 0
                    while (offset < bytes.size) {
                        val end = minOf(offset + chunk, bytes.size)
                        output.write(bytes, offset, end - offset)
                        offset = end
                        onProgress(((offset.toLong() * 100) / bytes.size.coerceAtLeast(1)).toInt())
                    }
                    output.flush()
                }
            }
        }

        if (!target.exists() || target.length() < 1024L) {
            throw IOException("embedded_payload_invalid")
        }
        onProgress(100)
        return target
    }

    private fun copyStream(
        input: InputStream,
        target: File,
        total: Long,
        onProgress: (percent: Int) -> Unit,
    ) {
        target.outputStream().use { output ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var copied = 0L
            var remaining = total
            while (remaining > 0) {
                val toRead = minOf(buffer.size.toLong(), remaining).toInt()
                val read = input.read(buffer, 0, toRead)
                if (read <= 0) {
                    break
                }
                output.write(buffer, 0, read)
                copied += read
                remaining -= read
                onProgress(((copied * 100) / total).toInt().coerceIn(0, 100))
            }
            output.flush()
        }
    }
}
