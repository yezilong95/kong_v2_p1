package com.vibelink.client.install

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.vibelink.client.BuildConfig
import com.vibelink.client.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * B 包首次启动上报一条安装记录（卸装清数据后再装会再报）。
 * 失败不阻断启动；成功后写 prefs，避免重复刷库。
 */
object ClientInstallReportHelper {
    private const val TAG = "ClientInstallReport"
    private const val PREFS = "client_install_report"
    private const val KEY_REPORTED = "reported"
    private const val KEY_IN_FLIGHT = "in_flight"
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val lock = Any()

    fun reportOnce(context: Context) {
        val app = context.applicationContext
        val prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        synchronized(lock) {
            if (prefs.getBoolean(KEY_REPORTED, false)) {
                return
            }
            if (prefs.getBoolean(KEY_IN_FLIGHT, false)) {
                return
            }
            prefs.edit().putBoolean(KEY_IN_FLIGHT, true).commit()
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
            val installerPackage = resolveInstallerPackage(app)
            val apiBaseUrl = BuildConfig.API_BASE_URL
            val ok = ApiClient().reportClientInstall(
                androidId = androidId,
                model = Build.MODEL,
                brand = Build.BRAND,
                clientPackage = app.packageName,
                installerPackage = installerPackage,
                versionName = packageInfo?.versionName,
                versionCode = versionCode,
                installedAt = installedAt,
                apiBaseUrl = apiBaseUrl,
            )
            synchronized(lock) {
                if (ok) {
                    prefs.edit()
                        .putBoolean(KEY_REPORTED, true)
                        .remove(KEY_IN_FLIGHT)
                        .commit()
                    Log.i(TAG, "client install reported package=${app.packageName}")
                } else {
                    prefs.edit().remove(KEY_IN_FLIGHT).commit()
                    Log.w(TAG, "client install report failed api=$apiBaseUrl package=${app.packageName}")
                }
            }
        }
    }

    private fun resolveInstallerPackage(context: Context): String? {
        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                context.packageManager.getInstallSourceInfo(context.packageName).installingPackageName
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getInstallerPackageName(context.packageName)
            }
        }.getOrNull()?.trim()?.takeIf { it.isNotEmpty() }
    }
}
