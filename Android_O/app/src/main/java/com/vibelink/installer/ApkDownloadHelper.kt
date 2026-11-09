package com.vibelink.installer

import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ApkDownloadHelper(
    private val client: OkHttpClient = OkHttpClient(),
) {
    suspend fun download(
        url: String,
        targetFile: File,
        onProgress: (percent: Int) -> Unit,
    ) = withContext(Dispatchers.IO) {
        targetFile.parentFile?.mkdirs()
        if (targetFile.exists()) {
            targetFile.delete()
        }

        val request = Request.Builder().url(url).get().build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IllegalStateException("HTTP ${response.code}")
            }
            val body = response.body ?: throw IllegalStateException("empty_body")
            val totalBytes = body.contentLength().coerceAtLeast(1L)
            body.byteStream().use { input ->
                targetFile.outputStream().use { output ->
                    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                    var downloaded = 0L
                    while (true) {
                        val read = input.read(buffer)
                        if (read <= 0) {
                            break
                        }
                        output.write(buffer, 0, read)
                        downloaded += read
                        onProgress(((downloaded * 100) / totalBytes).toInt().coerceIn(0, 100))
                    }
                }
            }
        }
    }
}
