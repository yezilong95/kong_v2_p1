package com.vibelink.client.network

import android.net.Uri
import com.vibelink.client.BuildConfig
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

    fun applySwitch(prefs: ServerPrefs, next: ServerEndpoints): String? {
        val normalized = next.normalized()
        validate(normalized)?.let { return it }
        val current = current(prefs)
        prefs.prevApiBaseUrl = current.apiBaseUrl
        prefs.prevWsBaseUrl = current.wsBaseUrl
        prefs.prevWsScreenBaseUrl = current.wsScreenBaseUrl
        prefs.apiBaseUrl = normalized.apiBaseUrl
        prefs.wsBaseUrl = normalized.wsBaseUrl
        prefs.wsScreenBaseUrl = normalized.wsScreenBaseUrl
        prefs.switchAtMs = System.currentTimeMillis()
        prefs.rollbackPending = false
        return null
    }

    fun rollback(prefs: ServerPrefs): Boolean {
        val prevApi = prefs.prevApiBaseUrl
        val prevWs = prefs.prevWsBaseUrl
        val prevScreen = prefs.prevWsScreenBaseUrl
        if (prevApi.isNullOrBlank() || prevWs.isNullOrBlank() || prevScreen.isNullOrBlank()) {
            return false
        }
        prefs.apiBaseUrl = prevApi
        prefs.wsBaseUrl = prevWs
        prefs.wsScreenBaseUrl = prevScreen
        prefs.prevApiBaseUrl = null
        prefs.prevWsBaseUrl = null
        prefs.prevWsScreenBaseUrl = null
        prefs.switchAtMs = 0L
        prefs.rollbackPending = true
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
