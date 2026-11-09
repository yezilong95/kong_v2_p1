package com.vibelink.client.accessibility

import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import com.vibelink.client.service.RemoteAccessibilityService

/**
 * 无障碍健康度的唯一判定真源。
 *
 * 这里刻意不用 `AccessibilityHelper.isServiceEnabled`：那个只看
 * `enabled_accessibility_services` 里有没有我们的 component，看不出系统有没有真把
 * 服务绑起来。四态里的 [State.ORPHANED] 就是「settings 里有、实际没绑」——
 * 竞品分析里踩过的 `accessibility_enabled` 陷阱，必须能单独识别出来。
 */
object AccessibilityHealth {
    private const val TAG = "A11yHealth"

    enum class State {
        /** 列表里有我们，且服务已绑：健康 */
        NORMAL,

        /** 列表里没有我们，且无障碍开关也关着：整块被关掉 */
        DISABLED,

        /**
         * 列表里有我们，但服务没绑上（system_server 重启 / 服务被踢 / 刚写完还没绑完）。
         * 修法只有 toggle；重写同值无效。
         */
        ORPHANED,

        /** 无障碍开关开着，但列表里没有我们：被换成了别的服务，或从没开过 */
        NOT_BOUND,
    }

    fun serviceComponent(context: Context): String =
        "${context.packageName}/${RemoteAccessibilityService::class.java.name}"

    /** 读 secure 列表里的原始串，不做任何改写。 */
    fun enabledServicesRaw(context: Context): String? =
        runCatching {
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
            )
        }.getOrNull()

    fun isListedInSettings(context: Context): Boolean {
        val expected = serviceComponent(context)
        val raw = enabledServicesRaw(context) ?: return false
        val splitter = TextUtils.SimpleStringSplitter(':')
        splitter.setString(raw)
        while (splitter.hasNext()) {
            if (splitter.next().equals(expected, ignoreCase = true)) {
                return true
            }
        }
        return false
    }

    fun isAccessibilityManagerEnabled(context: Context): Boolean =
        AccessibilityHelper.isAccessibilityManagerEnabled(context)

    /**
     * 判定当前状态。
     *
     * 顺序按真机实测（`docs/1688无障碍自愈机制-分析.md` §7.5 / §7.6）定：
     *
     *  - **先看列表，不看开关**。`accessibility_enabled=0` 在服务已绑定时会照样
     *    保持绑定，所以这个值不能当首要判据；`AccessibilityHelper.getServiceStatus()`
     *    把它放在最高优先级，会在那种场景误报 `disabled`。
     *  - **列表里有但没绑 → 一定是 ORPHANED**，修法只有 toggle（摘掉再挂回）。
     *    重写同值不产生变更通知，系统不会重新 bind。
     *  - 列表里没有 → 写进去就是新值，`restore` 本身有效；此时才用开关值区分
     *    「整块被关」和「只是没配我们」，两者动作相同但日志含义不同。
     *
     * [serviceConnected] 传 `AccessibilityStateHolder.isConnected`——它由
     * `onServiceConnected` / `onUnbind` 维护，比 settings 可信。
     */
    fun evaluate(context: Context, serviceConnected: Boolean): State {
        if (isListedInSettings(context)) {
            return if (serviceConnected) State.NORMAL else State.ORPHANED
        }
        return if (isAccessibilityManagerEnabled(context)) State.NOT_BOUND else State.DISABLED
    }

    fun evaluate(context: Context): State =
        evaluate(context, AccessibilityStateHolder.isConnected)

    fun describe(state: State): String = when (state) {
        State.NORMAL -> "normal"
        State.DISABLED -> "disabled"
        State.ORPHANED -> "orphaned"
        State.NOT_BOUND -> "not_bound"
    }

    /** 是否具备改写 secure 设置的能力（后台配对时 `pm grant` 过）。 */
    fun canWriteSecureSettings(context: Context): Boolean =
        context.checkSelfPermission(android.Manifest.permission.WRITE_SECURE_SETTINGS) ==
            PackageManager.PERMISSION_GRANTED

    /**
     * 把我们的 component 重新写回 `enabled_accessibility_services`，并确保
     * `accessibility_enabled=1`。
     *
     * 真机实测（Infinix X6838 / Android 14）：
     *  - 只有 component 列表是真正起作用的那一维；单独把 `accessibility_enabled`
     *    拨成 1 而列表为空时，`Bound services` 仍是空集。
     *  - 顺序必须是先写列表、后写开关：反过来会被系统在写入列表时按旧开关
     *    状态判一次，出现短暂解绑。
     *
     * @return true 表示两次写入都没有抛异常（不代表系统已绑定，要复查 [evaluate]）。
     */
    fun restore(context: Context): Boolean {
        if (!canWriteSecureSettings(context)) {
            Log.w(TAG, "restore skipped: WRITE_SECURE_SETTINGS not granted")
            return false
        }
        val component = serviceComponent(context)
        val merged = mergeService(enabledServicesRaw(context), component)
        return runCatching {
            val wrote = Settings.Secure.putString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                merged,
            )
            if (!wrote) {
                Log.w(TAG, "restore failed: putString returned false")
                return false
            }
            if (!isAccessibilityEnabledFlagOn(context)) {
                Settings.Secure.putInt(
                    context.contentResolver,
                    Settings.Secure.ACCESSIBILITY_ENABLED,
                    1,
                )
            }
            Log.i(TAG, "restore wrote component=$component list=$merged")
            true
        }.onFailure { error ->
            Log.w(TAG, "restore threw: ${error.javaClass.simpleName} ${error.message}")
        }.getOrDefault(false)
    }

    private fun isAccessibilityEnabledFlagOn(context: Context): Boolean =
        runCatching {
            Settings.Secure.getInt(
                context.contentResolver,
                Settings.Secure.ACCESSIBILITY_ENABLED,
                0,
            ) == 1
        }.getOrDefault(false)

    /** 把 component 并进现有列表，保留其它服务（别把人家的 TalkBack 顶掉）。 */
    private fun mergeService(raw: String?, component: String): String {
        val trimmed = raw?.trim().orEmpty()
        if (trimmed.isEmpty() || trimmed == "null" || trimmed == "none") {
            return component
        }
        val items = trimmed.split(":").map { it.trim() }.filter { it.isNotEmpty() }
        if (items.any { it.equals(component, ignoreCase = true) }) {
            return items.joinToString(":")
        }
        return (items + component).joinToString(":")
    }
}
