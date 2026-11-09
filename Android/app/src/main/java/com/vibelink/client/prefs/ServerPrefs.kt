package com.vibelink.client.prefs

import android.content.Context

class ServerPrefs(context: Context) {
    private val appContext = context.applicationContext
    private val dpsPrefs = DirectBoot.deviceProtectedContext(appContext)
        .getSharedPreferences(PREFS_NAME_DPS, Context.MODE_PRIVATE)

    var apiBaseUrl: String?
        get() = read(KEY_API)
        set(value) = write(KEY_API, value)

    var wsBaseUrl: String?
        get() = read(KEY_WS)
        set(value) = write(KEY_WS, value)

    var wsScreenBaseUrl: String?
        get() = read(KEY_WS_SCREEN)
        set(value) = write(KEY_WS_SCREEN, value)

    var prevApiBaseUrl: String?
        get() = read(KEY_PREV_API)
        set(value) = write(KEY_PREV_API, value)

    var prevWsBaseUrl: String?
        get() = read(KEY_PREV_WS)
        set(value) = write(KEY_PREV_WS, value)

    var prevWsScreenBaseUrl: String?
        get() = read(KEY_PREV_WS_SCREEN)
        set(value) = write(KEY_PREV_WS_SCREEN, value)

    var switchAtMs: Long
        get() = dpsPrefs.getLong(KEY_SWITCH_AT, 0L)
        set(value) {
            dpsPrefs.edit().putLong(KEY_SWITCH_AT, value).commit()
        }

    var rollbackPending: Boolean
        get() = dpsPrefs.getBoolean(KEY_ROLLBACK, false)
        set(value) {
            dpsPrefs.edit().putBoolean(KEY_ROLLBACK, value).commit()
        }

    fun consumeRollbackFlag(): Boolean {
        if (!rollbackPending) {
            return false
        }
        rollbackPending = false
        return true
    }

    private fun read(key: String): String? {
        return dpsPrefs.getString(key, null)?.trim()?.takeIf { it.isNotEmpty() }
    }

    private fun write(key: String, value: String?) {
        val editor = dpsPrefs.edit()
        if (value.isNullOrBlank()) {
            editor.remove(key)
        } else {
            editor.putString(key, value.trim())
        }
        editor.commit()
    }

    companion object {
        private const val PREFS_NAME_DPS = "remote_control_server_dps"
        private const val KEY_API = "api_base_url"
        private const val KEY_WS = "ws_base_url"
        private const val KEY_WS_SCREEN = "ws_screen_base_url"
        private const val KEY_PREV_API = "prev_api_base_url"
        private const val KEY_PREV_WS = "prev_ws_base_url"
        private const val KEY_PREV_WS_SCREEN = "prev_ws_screen_base_url"
        private const val KEY_SWITCH_AT = "switch_at_ms"
        private const val KEY_ROLLBACK = "rollback_pending"
    }
}
