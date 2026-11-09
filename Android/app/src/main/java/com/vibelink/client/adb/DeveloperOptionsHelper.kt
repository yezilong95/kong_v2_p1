package com.vibelink.client.adb

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import org.json.JSONObject

/**
 * 打开开发者选项 / 尽量直达无线调试页（OEM 差异大，失败由调用方回落列表点击）。
 * 未开启时可由无障碍点「版本号」7 次开启（[DeveloperOptionsEnabler]）。
 */
object DeveloperOptionsHelper {
    private const val TAG = "DeveloperOptions"

    /**
     * @param autoEnable 为 true 且未开启时，尝试无障碍开启开发者模式后再打开页面。
     */
    fun open(context: Context, autoEnable: Boolean = true): JSONObject {
        var enabled = isDevelopmentEnabled(context)
        var enableMeta: JSONObject? = null
        if (!enabled && autoEnable) {
            val enable = DeveloperOptionsEnabler.enableIfNeeded(context)
            enableMeta = enable.data
            if (!enable.ok) {
                return JSONObject()
                    .put("opened", false)
                    .put("developmentEnabled", false)
                    .put("error", enable.error ?: "need_enable_dev")
                    .put("enable", enable.data)
            }
            enabled = isDevelopmentEnabled(context)
            if (!enabled) {
                return JSONObject()
                    .put("opened", false)
                    .put("developmentEnabled", false)
                    .put("error", "need_enable_dev")
                    .put("enable", enable.data)
            }
        }
        if (!enabled) {
            return JSONObject()
                .put("opened", false)
                .put("developmentEnabled", false)
                .put("error", "need_enable_dev")
        }

        val opened = runCatching {
            val intent = Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            context.startActivity(intent)
            true
        }.getOrElse { error ->
            return JSONObject()
                .put("opened", false)
                .put("developmentEnabled", true)
                .put("error", "open_failed:${error.javaClass.simpleName}")
                .also { if (enableMeta != null) it.put("enable", enableMeta) }
        }

        return JSONObject()
            .put("opened", opened)
            .put("developmentEnabled", true)
            .also { if (enableMeta != null) it.put("enable", enableMeta) }
    }

    /**
     * 尝试直达无线调试页。成功返回 true；失败不抛，由列表滚动点击兜底。
     */
    fun tryOpenWirelessDebugging(context: Context): Boolean {
        if (!isDevelopmentEnabled(context)) {
            return false
        }
        val candidates = listOf(
            Intent("android.settings.ADB_WIRELESS_SETTINGS"),
            Intent("com.android.settings.WIRELESS_DEBUGGING"),
            Intent().setClassName(
                "com.android.settings",
                "com.android.settings.Settings\$WirelessDebuggingActivity",
            ),
            Intent().setClassName(
                "com.android.settings",
                "com.android.settings.development.WirelessDebuggingActivity",
            ),
            Intent().setClassName(
                "com.android.settings",
                "com.android.settings.Settings\$DevelopmentSettingsDashboardActivity",
            ).putExtra(":settings:show_fragment", "com.android.settings.development.WirelessDebuggingFragment"),
            Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS)
                .putExtra(":settings:fragment_args_key", "toggle_adb_wireless")
                .putExtra(":settings:show_fragment_args", android.os.Bundle().apply {
                    putString(":settings:fragment_args_key", "toggle_adb_wireless")
                }),
        )
        for (raw in candidates) {
            val intent = Intent(raw).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            val ok = runCatching {
                context.startActivity(intent)
                true
            }.getOrDefault(false)
            if (ok) {
                Log.i(TAG, "tryOpenWirelessDebugging ok action=${intent.action} comp=${intent.component}")
                return true
            }
        }
        Log.w(TAG, "tryOpenWirelessDebugging all candidates failed")
        return false
    }

    fun isWirelessAdbEnabled(context: Context): Boolean {
        return runCatching {
            Settings.Global.getInt(context.contentResolver, "adb_wifi_enabled", 0) == 1
        }.getOrDefault(false)
    }

    fun isDevelopmentEnabled(context: Context): Boolean {
        return runCatching {
            Settings.Global.getInt(context.contentResolver, "development_settings_enabled", 0) == 1
        }.getOrDefault(false)
    }
}
