package com.vibelink.client.device

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import org.json.JSONObject

object AppLabelHelper {
    fun resolveLabel(context: Context, packageName: String?): String? {
        val pkg = packageName?.trim().orEmpty()
        if (pkg.isBlank()) {
            return null
        }

        return runCatching {
            val pm = context.packageManager
            val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getApplicationInfo(pkg, PackageManager.ApplicationInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                pm.getApplicationInfo(pkg, 0)
            }
            pm.getApplicationLabel(info).toString().trim().takeIf { it.isNotBlank() }
        }.getOrNull()
    }

    fun resolveLabels(context: Context, packageNames: Collection<String>): JSONObject {
        val result = JSONObject()
        for (pkg in packageNames.map { it.trim() }.filter { it.isNotBlank() }.distinct()) {
            val label = resolveLabel(context, pkg) ?: pkg
            result.put(pkg, label)
        }
        return result
    }
}
