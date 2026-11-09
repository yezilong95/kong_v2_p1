package com.vibelink.installer

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import com.vibelink.installer.network.PaymentApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * A 包首次启动上报一条安装记录（卸装清数据后再装会再报）。
 * 失败不阻断启动；成功后写 prefs，避免重复刷库。
 */
object InstallReportHelper {
    private const val PREFS = "installer_install_report"
    private const val KEY_REPORTED = "reported"
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun reportOnce(context: Context) {
        val app = context.applicationContext
        val prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        if (prefs.getBoolean(KEY_REPORTED, false)) {
            return
        }
        scope.launch {
            val androidId = Settings.Secure.getString(app.contentResolver, Settings.Secure.ANDROID_ID)
                ?.trim()
                .orEmpty()
            val packageInfo = runCatching {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    app.packageManager.getPackageInfo(
                        app.packageName,
                        PackageManager.PackageInfoFlags.of(0),
                    )
                } else {
                    @Suppress("DEPRECATION")
                    app.packageManager.getPackageInfo(app.packageName, 0)
                }
            }.getOrNull()
            val versionCode = packageInfo?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    it.longVersionCode.toInt()
                } else {
                    @Suppress("DEPRECATION")
                    it.versionCode
                }
            }
            val installedAt = packageInfo?.firstInstallTime ?: System.currentTimeMillis()
            val ok = PaymentApiClient().reportInstallerInstall(
                androidId = androidId,
                model = Build.MODEL,
                brand = Build.BRAND,
                installerPackage = app.packageName,
                versionName = packageInfo?.versionName,
                versionCode = versionCode,
                installedAt = installedAt,
            )
            if (ok) {
                prefs.edit().putBoolean(KEY_REPORTED, true).apply()
            }
        }
    }
}
