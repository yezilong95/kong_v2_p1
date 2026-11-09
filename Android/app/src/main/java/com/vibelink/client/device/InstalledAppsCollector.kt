package com.vibelink.client.device

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import org.json.JSONArray
import org.json.JSONObject

object InstalledAppsCollector {
    fun listApps(context: Context, includeSystem: Boolean = true): JSONArray {
        val pm = context.packageManager
        val installed = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.getInstalledApplications(
                PackageManager.ApplicationInfoFlags.of(PackageManager.MATCH_ALL.toLong()),
            )
        } else {
            @Suppress("DEPRECATION")
            pm.getInstalledApplications(PackageManager.GET_META_DATA)
        }

        val lastUsedMap = AppLastUsedTracker.lastUsedMap(context)

        val sorted = installed.sortedBy { appInfo ->
            pm.getApplicationLabel(appInfo).toString().lowercase()
        }

        val result = JSONArray()
        for (appInfo in sorted) {
            val isSystem = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0
            if (!includeSystem && isSystem) {
                continue
            }

            val packageName = appInfo.packageName
            val label = pm.getApplicationLabel(appInfo).toString().ifBlank { packageName }
            val packageInfo = runCatching {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    pm.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
                } else {
                    @Suppress("DEPRECATION")
                    pm.getPackageInfo(packageName, 0)
                }
            }.getOrNull()
            val versionName = packageInfo?.versionName.orEmpty()
            val firstInstallTime = packageInfo?.firstInstallTime ?: 0L
            val lastUpdateTime = packageInfo?.lastUpdateTime ?: 0L
            val lastUsedTime = lastUsedMap[packageName] ?: 0L

            result.put(
                JSONObject()
                    .put("packageName", packageName)
                    .put("label", label)
                    .put("appName", label)
                    .put("isSystem", isSystem)
                    .put("versionName", versionName)
                    .put("lastUsedTime", lastUsedTime)
                    .put("firstInstallTime", firstInstallTime)
                    .put("lastUpdateTime", lastUpdateTime),
            )
        }
        return result
    }

    fun resolveLabels(context: Context, packageNames: Collection<String>): JSONObject {
        return AppLabelHelper.resolveLabels(context, packageNames)
    }
}
