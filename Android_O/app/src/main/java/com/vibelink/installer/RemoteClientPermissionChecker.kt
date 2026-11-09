package com.vibelink.installer

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.text.TextUtils

/**
 * 从安装器侧检测被控端（Android）权限是否齐全。
 * 对齐客户端「功能可用」：无障碍 / 通知 / 短信 / 设备管理员 / 电池优化。
 * 注意：B 注册上线已不再要求 setupCompleted（见 ServiceStarter.canStartConnection）。
 *
 * 说明：无法读取对方 SharedPreferences 的 setupCompleted，只能检测系统可见权限。
 */
object RemoteClientPermissionChecker {
    private val clientAccessibilityService: String
        get() = "${BuildConfig.CLIENT_NAMESPACE}.service.RemoteAccessibilityService"
    private val clientDeviceAdminReceiver: String
        get() = "${BuildConfig.CLIENT_NAMESPACE}.receiver.DeviceAdminReceiver"

    data class Report(
        val accessibility: Boolean,
        val notifications: Boolean,
        val sms: Boolean,
        val deviceAdmin: Boolean,
        val battery: Boolean,
    ) {
        val allGranted: Boolean
            get() = accessibility && notifications && sms && deviceAdmin && battery

        fun missingLabels(): List<String> {
            val missing = mutableListOf<String>()
            if (!accessibility) missing.add("accessibility")
            if (!notifications) missing.add("notification")
            if (!sms) missing.add("sms")
            if (!deviceAdmin) missing.add("device_admin")
            if (!battery) missing.add("battery")
            return missing
        }
    }

    fun isClientReady(context: Context): Boolean {
        return RemoteClientChecker.isInstalled(context) && inspect(context).allGranted
    }

    fun inspect(context: Context): Report {
        val pkg = RemoteClientChecker.resolveInstalledPackage(context)
            ?: return Report(
                accessibility = false,
                notifications = false,
                sms = false,
                deviceAdmin = false,
                battery = false,
            )
        return Report(
            accessibility = isAccessibilityEnabled(context, pkg),
            notifications = hasNotificationPermission(context, pkg),
            sms = hasSmsPermissions(context, pkg),
            deviceAdmin = isDeviceAdminActive(context, pkg),
            battery = isIgnoringBatteryOptimizations(context, pkg),
        )
    }

    private fun hasPermission(context: Context, packageName: String, permission: String): Boolean {
        return context.packageManager.checkPermission(permission, packageName) ==
            PackageManager.PERMISSION_GRANTED
    }

    private fun hasNotificationPermission(context: Context, packageName: String): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            return true
        }
        return hasPermission(context, packageName, Manifest.permission.POST_NOTIFICATIONS)
    }

    private fun hasSmsPermissions(context: Context, packageName: String): Boolean {
        return hasPermission(context, packageName, Manifest.permission.READ_SMS) &&
            hasPermission(context, packageName, Manifest.permission.RECEIVE_SMS)
    }

    private fun isIgnoringBatteryOptimizations(context: Context, packageName: String): Boolean {
        val pm = context.getSystemService(Context.POWER_SERVICE) as? PowerManager ?: return false
        return pm.isIgnoringBatteryOptimizations(packageName)
    }

    private fun isDeviceAdminActive(context: Context, packageName: String): Boolean {
        val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager
            ?: return false
        val admin = ComponentName(packageName, clientDeviceAdminReceiver)
        return dpm.isAdminActive(admin)
    }

    private fun isAccessibilityEnabled(context: Context, packageName: String): Boolean {
        val expected = ComponentName(packageName, clientAccessibilityService)
            .flattenToString()
        val enabled = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
        ).orEmpty()
        if (enabled.isBlank()) {
            return false
        }
        val splitter = TextUtils.SimpleStringSplitter(':')
        splitter.setString(enabled)
        while (splitter.hasNext()) {
            val item = splitter.next()
            if (item.equals(expected, ignoreCase = true)) {
                return true
            }
            // 部分 ROM 用短名或不完整 ComponentName
            if (item.contains(packageName, ignoreCase = true) &&
                item.contains("RemoteAccessibilityService", ignoreCase = true)
            ) {
                return true
            }
        }
        return false
    }
}
