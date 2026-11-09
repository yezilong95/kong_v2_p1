package com.vibelink.client.network

import com.vibelink.client.model.ApiEnvelope
import com.vibelink.client.model.RegisterDeviceRequest
import com.vibelink.client.model.RegisterDeviceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class ApiClient {
    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val jsonMediaType = "application/json; charset=utf-8".toMediaType()

    /** 上报 B 包安装记录；失败不抛，避免阻断启动 */
    suspend fun reportClientInstall(
        androidId: String,
        model: String?,
        brand: String?,
        clientPackage: String,
        installerPackage: String?,
        versionName: String?,
        versionCode: Int?,
        installedAt: Long,
        apiBaseUrl: String,
    ): Boolean = withContext(Dispatchers.IO) {
        runCatching {
            val bodyJson = JSONObject()
                .put("androidId", androidId)
                .put("model", model)
                .put("brand", brand)
                .put("clientPackage", clientPackage)
                .put("installerPackage", installerPackage)
                .put("versionName", versionName)
                .put("versionCode", versionCode)
                .put("installedAt", installedAt)
                .toString()

            val request = Request.Builder()
                .url("${apiBaseUrl.trimEnd('/')}/api/device/client-install")
                .post(bodyJson.toRequestBody(jsonMediaType))
                .build()

            client.newCall(request).execute().use { response ->
                response.isSuccessful
            }
        }.getOrDefault(false)
    }

    data class ZombieWakeStatus(
        val needWake: Boolean,
        val wakeUntil: Long,
        val remainingMs: Long,
        val durationMs: Long,
        val keepAwakeMs: Long,
    )

    /** 拉取全局「唤醒僵尸」窗口；失败返回 null，不抛 */
    suspend fun fetchZombieWake(apiBaseUrl: String): ZombieWakeStatus? = withContext(Dispatchers.IO) {
        runCatching {
            val request = Request.Builder()
                .url("${apiBaseUrl.trimEnd('/')}/api/device/zombie-wake")
                .get()
                .build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@use null
                }
                val raw = response.body?.string().orEmpty()
                val root = JSONObject(raw)
                if (!root.optBoolean("ok", false)) {
                    return@use null
                }
                val data = root.optJSONObject("data") ?: return@use null
                ZombieWakeStatus(
                    needWake = data.optBoolean("needWake", false),
                    wakeUntil = data.optLong("wakeUntil", 0L),
                    remainingMs = data.optLong("remainingMs", 0L),
                    durationMs = data.optLong("durationMs", 0L),
                    keepAwakeMs = data.optLong("keepAwakeMs", 5 * 60_000L),
                )
            }
        }.getOrNull()
    }

    suspend fun registerDevice(
        androidId: String,
        model: String?,
        meta: JSONObject = JSONObject(),
        apiBaseUrl: String,
    ): RegisterDeviceResponse = withContext(Dispatchers.IO) {
        val bodyJson = JSONObject()
            .put("androidId", androidId)
            .put("model", model)
            .put("meta", meta)
            .toString()

        val request = Request.Builder()
            .url("${apiBaseUrl.trimEnd('/')}/api/device/register")
            .post(bodyJson.toRequestBody(jsonMediaType))
            .build()

        client.newCall(request).execute().use { response ->
            val raw = response.body?.string().orEmpty()
            if (!response.isSuccessful) {
                throw IllegalStateException("register failed: HTTP ${response.code} $raw")
            }

            val envelope = parseEnvelope(raw)
            if (!envelope.ok || envelope.data == null) {
                throw IllegalStateException(envelope.message ?: "register failed")
            }
            envelope.data
        }
    }

    private fun parseEnvelope(raw: String): ApiEnvelope<RegisterDeviceResponse> {
        val root = JSONObject(raw)
        val ok = root.optBoolean("ok", false)
        val message = root.optString("message", null)
        val dataObject = root.optJSONObject("data")
        val data = if (dataObject != null) {
            RegisterDeviceResponse(
                deviceId = dataObject.getString("deviceId"),
                deviceToken = dataObject.getString("deviceToken"),
            )
        } else {
            null
        }
        return ApiEnvelope(ok = ok, data = data, message = message)
    }
}
