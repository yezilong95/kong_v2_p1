package com.vibelink.client.accessibility

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.accessibility.AccessibilityManager
import com.vibelink.client.R
import com.vibelink.client.service.RemoteAccessibilityService

object AccessibilityHelper {
    private const val TAG = "AccessibilityHelper"

    private enum class HintKind {
        HIGHLIGHT_LIST,
        TOGGLE_SWITCH,
        MANUAL_LIST,
    }

    fun isServiceEnabled(context: Context): Boolean {
        val expectedService =
            "${context.packageName}/${RemoteAccessibilityService::class.java.name}"
        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        ) ?: return false

        val splitter = TextUtils.SimpleStringSplitter(':')
        splitter.setString(enabledServices)
        while (splitter.hasNext()) {
            if (splitter.next().equals(expectedService, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    fun isAccessibilityManagerEnabled(context: Context): Boolean {
        val manager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        return manager.isEnabled
    }

    fun getServiceStatus(context: Context): String {
        return when {
            !isAccessibilityManagerEnabled(context) -> "disabled"
            isServiceEnabled(context) -> "enabled"
            else -> "not_bound"
        }
    }

    /**
     * 三星：直达服务详情（二级页）。
     * ColorOS / 真我：直达「已下载的应用」并高亮本服务（等价于三星二级列表）。
     * 其余：无障碍列表 + fragment_args_key 高亮。不走 REWARD / AOSP DETAILS（秒关）。
     */
    fun openServiceSettings(context: Context) {
        val component = ComponentName(context, RemoteAccessibilityService::class.java)
        val componentKey = component.flattenToString()

        if (isSamsungFamily()) {
            if (tryOpenSamsungDetails(context, componentKey)) {
                showHint(context, HintKind.TOGGLE_SWITCH)
                return
            }
            if (tryOpenSamsungInstalledService(context, componentKey)) {
                showHint(context, HintKind.HIGHLIGHT_LIST)
                return
            }
        }

        if (tryOpenOplusDownloadedList(context, componentKey)) {
            showHint(context, HintKind.HIGHLIGHT_LIST)
            return
        }

        if (tryOpenHighlightedList(context, componentKey)) {
            showHint(context, HintKind.HIGHLIGHT_LIST)
            return
        }

        Log.w(TAG, "fallback to ACCESSIBILITY_SETTINGS list")
        runCatching {
            context.startActivity(
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(LIST_LAUNCH_FLAGS),
            )
            showHint(context, HintKind.MANUAL_LIST)
        }
    }

    private fun showHint(context: Context, kind: HintKind) {
        val res = when (kind) {
            HintKind.HIGHLIGHT_LIST -> R.string.accessibility_click_highlight_hint
            HintKind.TOGGLE_SWITCH -> R.string.accessibility_click_toggle_hint
            HintKind.MANUAL_LIST -> R.string.accessibility_open_manual_hint
        }
        AccessibilityClickHint.showAfterOpen(context, res)
    }

    private fun applyHighlightExtras(intent: Intent, componentKey: String) {
        val args = Bundle().apply {
            putString(EXTRA_FRAGMENT_ARG_KEY, componentKey)
        }
        intent.putExtra(EXTRA_FRAGMENT_ARG_KEY, componentKey)
        intent.putExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS, args)
        intent.putExtra(Intent.EXTRA_COMPONENT_NAME, componentKey)
    }

    /**
     * ColorOS / 真我：exported 二级页「已下载的应用」。
     * activity permission 是 prot=normal 的 LAUNCH_FOR_EXPORT；ADB shell 没有，应用声明即可。
     */
    private fun tryOpenOplusDownloadedList(context: Context, componentKey: String): Boolean {
        if (!hasPermission(context, PERMISSION_OPLUS_LAUNCH_EXPORT)) {
            Log.w(TAG, "missing $PERMISSION_OPLUS_LAUNCH_EXPORT")
            return false
        }
        return runCatching {
            val intent = Intent(ACTION_OPLUS_A11Y_DOWNLOAD).apply {
                addFlags(LIST_LAUNCH_FLAGS)
                applyHighlightExtras(this, componentKey)
            }
            if (intent.resolveActivity(context.packageManager) == null) {
                return false
            }
            context.startActivity(intent)
            Log.i(TAG, "opened Oplus ACCESSIBILITY_DOWNLOAD key=$componentKey")
            true
        }.onFailure { Log.w(TAG, "Oplus ACCESSIBILITY_DOWNLOAD failed", it) }.getOrDefault(false)
    }

    private fun tryOpenSamsungDetails(context: Context, componentKey: String): Boolean {
        return runCatching {
            val intent = Intent(ACTION_SAMSUNG_A11Y_DETAILS).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(Intent.EXTRA_COMPONENT_NAME, componentKey)
            }
            if (intent.resolveActivity(context.packageManager) == null) {
                return false
            }
            context.startActivity(intent)
            Log.i(TAG, "opened Samsung ACCESSIBILITY_DETAILS_SETTINGS component=$componentKey")
            true
        }.onFailure { Log.w(TAG, "Samsung DETAILS failed", it) }.getOrDefault(false)
    }

    private fun tryOpenSamsungInstalledService(context: Context, componentKey: String): Boolean {
        for (action in listOf(ACTION_SAMSUNG_INSTALLED_SERVICE, ACTION_SAMSUNG_INSTALLED_SERVICES)) {
            val ok = runCatching {
                val intent = Intent(action).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    applyHighlightExtras(this, componentKey)
                }
                if (intent.resolveActivity(context.packageManager) == null) {
                    return@runCatching false
                }
                context.startActivity(intent)
                Log.i(TAG, "opened Samsung installed services action=$action")
                true
            }.onFailure { Log.w(TAG, "Samsung installed services failed action=$action", it) }
                .getOrDefault(false)
            if (ok) {
                return true
            }
        }
        return false
    }

    private fun tryOpenHighlightedList(context: Context, componentKey: String): Boolean {
        return runCatching {
            val samsungHome = Intent(ACTION_SAMSUNG_A11Y_HOME).apply {
                addFlags(LIST_LAUNCH_FLAGS)
                applyHighlightExtras(this, componentKey)
            }
            val intent = if (samsungHome.resolveActivity(context.packageManager) != null) {
                samsungHome
            } else {
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                    addFlags(LIST_LAUNCH_FLAGS)
                    applyHighlightExtras(this, componentKey)
                }
            }
            if (intent.resolveActivity(context.packageManager) == null) {
                return false
            }
            context.startActivity(intent)
            Log.i(TAG, "opened highlighted a11y list key=$componentKey action=${intent.action}")
            true
        }.onFailure { Log.w(TAG, "highlighted a11y list failed", it) }.getOrDefault(false)
    }

    private fun hasPermission(context: Context, permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun isSamsungFamily(): Boolean = matchesBrandFamily("samsung")

    private fun matchesBrandFamily(vararg tokens: String): Boolean {
        val brand = Build.BRAND.orEmpty().lowercase()
        val manufacturer = Build.MANUFACTURER.orEmpty().lowercase()
        return tokens.any { token ->
            brand.contains(token) || manufacturer.contains(token)
        }
    }

    private const val ACTION_OPLUS_A11Y_DOWNLOAD =
        "oplus.intent.action.settings.ACCESSIBILITY_DOWNLOAD"
    private const val PERMISSION_OPLUS_LAUNCH_EXPORT =
        "oplus.permission.settings.LAUNCH_FOR_EXPORT"

    private const val ACTION_SAMSUNG_INSTALLED_SERVICE =
        "com.samsung.accessibility.installed_service"
    private const val ACTION_SAMSUNG_INSTALLED_SERVICES =
        "com.samsung.accessibility.INSTALLED_SERVICES"
    private const val ACTION_SAMSUNG_A11Y_DETAILS =
        "com.samsung.accessibility.ACCESSIBILITY_DETAILS_SETTINGS"
    private const val ACTION_SAMSUNG_A11Y_HOME =
        "com.samsung.accessibility.ACCESSIBILITY_SETTINGS"

    private const val EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key"
    private const val EXTRA_SHOW_FRAGMENT_ARGUMENTS = ":settings:show_fragment_args"

    private const val LIST_LAUNCH_FLAGS =
        Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_SINGLE_TOP
}
