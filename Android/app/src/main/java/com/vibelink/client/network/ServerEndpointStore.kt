package com.vibelink.client.network

import android.net.Uri
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.DevicePrefs
import com.vibelink.client.prefs.ServerPrefs
import org.json.JSONObject

data class ServerEndpoints(
    val apiBaseUrl: String,
    val wsBaseUrl: String,
    val wsScreenBaseUrl: String,
) {
    fun normalized(): ServerEndpoints {
        return copy(
            apiBaseUrl = apiBaseUrl.trim().trimEnd('/'),
            wsBaseUrl = wsBaseUrl.trim().trimEnd('/'),
            wsScreenBaseUrl = wsScreenBaseUrl.trim().trimEnd('/'),
        )
    }
}

object ServerEndpointStore {
    fun current(prefs: ServerPrefs): ServerEndpoints {
        return ServerEndpoints(
            apiBaseUrl = prefs.apiBaseUrl ?: BuildConfig.API_BASE_URL,
            wsBaseUrl = prefs.wsBaseUrl ?: BuildConfig.WS_BASE_URL,
            wsScreenBaseUrl = prefs.wsScreenBaseUrl ?: BuildConfig.WS_SCREEN_BASE_URL,
        ).normalized()
    }

    fun parse(params: JSONObject): ServerEndpoints? {
        val api = params.optString("apiBaseUrl").trim()
        val ws = params.optString("wsBaseUrl").trim()
        val screen = params.optString("wsScreenBaseUrl").trim()
        if (api.isBlank() || ws.isBlank() || screen.isBlank()) {
            return null
        }
        return ServerEndpoints(api, ws, screen).normalized()
    }

    fun validate(endpoints: ServerEndpoints): String? {
        validateHttp(endpoints.apiBaseUrl)?.let { return it }
        validateWs(endpoints.wsBaseUrl)?.let { return it }
        validateWs(endpoints.wsScreenBaseUrl)?.let { return it }
        return null
    }

    /**
     * 切换服务器端点。
     *
     * 传了 [devicePrefs] 时，若 `apiBaseUrl` 发生变化就把本地凭据一并清掉：
     * `deviceId`/`deviceToken` 是**按库签发**的，换一台服务器它们就必然对不上，
     * 留着只会拿旧 token 去连新库、被 4401 踢回来（见 DeviceAuthRejection）。
     * 清掉后下一次 ensureRegistered 会走全新注册，代价只是一次 register 请求。
     *
     * 只比 `apiBaseUrl`：它标识的是哪一套库。ws 地址变了而库没变的场景
     * （同库换网关）凭据仍然有效，不该多付一次注册。
     */
    fun applySwitch(
        prefs: ServerPrefs,
        next: ServerEndpoints,
        devicePrefs: DevicePrefs? = null,
    ): String? {
        val normalized = next.normalized()
        validate(normalized)?.let { return it }
        val current = current(prefs)
        val apiChanged = current.apiBaseUrl != normalized.apiBaseUrl
        prefs.prevApiBaseUrl = current.apiBaseUrl
        prefs.prevWsBaseUrl = current.wsBaseUrl
        prefs.prevWsScreenBaseUrl = current.wsScreenBaseUrl
        prefs.apiBaseUrl = normalized.apiBaseUrl
        prefs.wsBaseUrl = normalized.wsBaseUrl
        prefs.wsScreenBaseUrl = normalized.wsScreenBaseUrl
        prefs.switchAtMs = System.currentTimeMillis()
        prefs.rollbackPending = false
        if (apiChanged) {
            devicePrefs?.clearCredentials()
        }
        return null
    }

    /**
     * 回滚到上一个端点。语义同 [applySwitch]：只要库换了，本地凭据就不再成立。
     */
    fun rollback(prefs: ServerPrefs, devicePrefs: DevicePrefs? = null): Boolean {
        val prevApi = prefs.prevApiBaseUrl
        val prevWs = prefs.prevWsBaseUrl
        val prevScreen = prefs.prevWsScreenBaseUrl
        if (prevApi.isNullOrBlank() || prevWs.isNullOrBlank() || prevScreen.isNullOrBlank()) {
            return false
        }
        val apiChanged = current(prefs).apiBaseUrl != prevApi.trim().trimEnd('/')
        prefs.apiBaseUrl = prevApi
        prefs.wsBaseUrl = prevWs
        prefs.wsScreenBaseUrl = prevScreen
        prefs.prevApiBaseUrl = null
        prefs.prevWsBaseUrl = null
        prefs.prevWsScreenBaseUrl = null
        prefs.switchAtMs = 0L
        prefs.rollbackPending = true
        if (apiChanged) {
            devicePrefs?.clearCredentials()
        }
        return true
    }

    fun clearPrev(prefs: ServerPrefs) {
        prefs.prevApiBaseUrl = null
        prefs.prevWsBaseUrl = null
        prefs.prevWsScreenBaseUrl = null
        prefs.switchAtMs = 0L
    }

    private fun validateHttp(url: String): String? {
        val uri = Uri.parse(url)
        val scheme = uri.scheme?.lowercase()
        if (scheme != "http" && scheme != "https") {
            return "api_url_must_be_http"
        }
        if (uri.host.isNullOrBlank()) {
            return "api_url_host_required"
        }
        return null
    }

    private fun validateWs(url: String): String? {
        val uri = Uri.parse(url)
        val scheme = uri.scheme?.lowercase()
        if (scheme != "ws" && scheme != "wss") {
            return "ws_url_must_be_ws"
        }
        if (uri.host.isNullOrBlank()) {
            return "ws_url_host_required"
        }
        return null
    }
}
