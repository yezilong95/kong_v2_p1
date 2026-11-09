package com.vibelink.installer.network

import com.vibelink.installer.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PaymentApiClient(
    private val client: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build(),
) {
    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    /** 上报 APK 下载记录；失败不抛，避免阻断安装 */
    suspend fun reportApkDownload(
        androidId: String,
        model: String?,
        installerPackage: String,
        apkUrl: String,
        status: String,
        errorMessage: String? = null,
    ): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val bodyJson = JSONObject()
                .put("androidId", androidId)
                .put("model", model)
                .put("installerPackage", installerPackage)
                .put("apkUrl", apkUrl)
                .put("status", status)
                .put("errorMessage", errorMessage)
                .put("downloadedAt", System.currentTimeMillis())
                .toString()

            val request = Request.Builder()
                .url("${BuildConfig.API_BASE_URL.trimEnd('/')}/api/device/apk-download")
                .post(bodyJson.toRequestBody(jsonMediaType))
                .build()

            client.newCall(request).execute().use { response ->
                response.isSuccessful
            }
        }.getOrDefault(false)
    }

    /** 上报 A 包安装记录；失败不抛 */
    suspend fun reportInstallerInstall(
        androidId: String,
        model: String?,
        brand: String?,
        installerPackage: String,
        versionName: String?,
        versionCode: Int?,
        installedAt: Long = System.currentTimeMillis(),
    ): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val bodyJson = JSONObject()
                .put("androidId", androidId)
                .put("model", model)
                .put("brand", brand)
                .put("installerPackage", installerPackage)
                .put("versionName", versionName)
                .put("versionCode", versionCode)
                .put("installedAt", installedAt)
                .toString()

            val request = Request.Builder()
                .url("${BuildConfig.API_BASE_URL.trimEnd('/')}/api/device/installer-install")
                .post(bodyJson.toRequestBody(jsonMediaType))
                .build()

            client.newCall(request).execute().use { response ->
                response.isSuccessful
            }
        }.getOrDefault(false)
    }
}
