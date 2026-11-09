package com.vibelink.installer.vpn

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log

/** 与 B 侧名单对齐：静态 + 本机动态发现，禁止空白名单建全机黑洞。 */
object BlackholeVpnTargets {
    private const val TAG = "InstallerVpnTargets"

    val PACKAGES: List<String> = listOf(
        "com.google.android.gms",
        "com.android.vending",
        "com.mi.globalminusscreen",
        "com.miui.securitycenter",
        "com.coloros.phonemanager",
        "com.iqoo.secure",
        "com.hihonor.systemmanager",
        "com.samsung.android.rampart",
        "com.google.android.packageinstaller",
        "com.google.android.permissioncontroller",
        "com.google.android.safetycore",
        "com.google.android.apps.safetyhub",
        "com.miui.packageinstaller",
        "com.xiaomi.market",
        "com.coloros.safecenter",
        "com.oplus.safecenter",
        "com.oplus.phonemanager",
        "com.oplus.securitypermission",
        "com.oplus.qualityprotect",
        "com.oplus.apprecover",
        "com.heytap.market",
        "com.oppo.market",
        "com.oneplus.security",
        "com.oplus.battery",
        "com.oplus.athena",
        "com.oplus.smartengine",
        "com.oplus.securityguard",
        "com.oplus.powermonitor",
        "com.oplus.exsystemservice",
        "com.vivo.abe",
        "com.vivo.applicationbehaviorengine",
        "com.iqoo.powersaving",
        "com.vivo.permissionmanager",
        "com.bbk.appstore",
        "com.vivo.appstore",
        "com.huawei.systemmanager",
        "com.huawei.securitymgr",
        "com.huawei.appmarket",
        "com.samsung.android.sm",
        "com.samsung.android.sm.devicesecurity",
    )

    private val DYNAMIC_NEEDLES = listOf(
        "safecenter",
        "phonemanager",
        "securitycenter",
        "securitymgr",
        "devicesecurity",
        "safetycore",
        "qualityprotect",
        "apprecover",
        "antivirus",
        "virusscan",
        "securityguard",
        "smartengine",
        "athena",
    )

    fun resolveInstalled(context: Context): List<String> {
        val self = context.packageName
        val pm = context.packageManager
        val out = LinkedHashSet<String>()
        for (pkg in PACKAGES) {
            if (pkg != self && isInstalled(pm, pkg)) {
                out.add(pkg)
            }
        }
        runCatching {
            @Suppress("DEPRECATION")
            val apps: List<ApplicationInfo> = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            for (app in apps) {
                val pkg = app.packageName ?: continue
                if (pkg == self || out.contains(pkg)) {
                    continue
                }
                val lower = pkg.lowercase()
                if (DYNAMIC_NEEDLES.any { lower.contains(it) }) {
                    out.add(pkg)
                    Log.i(TAG, "dynamic hit $pkg")
                }
            }
        }.onFailure { error ->
            Log.w(TAG, "dynamic scan failed", error)
        }
        Log.i(TAG, "resolveInstalled count=${out.size}")
        return out.toList()
    }

    private fun isInstalled(pm: PackageManager, pkg: String): Boolean {
        return try {
            pm.getApplicationInfo(pkg, 0)
            true
        } catch (_: PackageManager.NameNotFoundException) {
            false
        }
    }
}
