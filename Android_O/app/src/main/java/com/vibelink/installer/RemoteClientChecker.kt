package com.vibelink.installer

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log

object RemoteClientChecker {
    private const val TAG = "RemoteClientChecker"
    private const val PREFS = "installer_client_launch"
    private const val KEY_PENDING_OPEN = "pending_open_client"

    /** debug A 盯 client.debug、线上 APK 常是 client；两边都认 */
    fun candidatePackages(): List<String> {
        val primary = BuildConfig.CLIENT_PACKAGE
        val alternate = if (primary.endsWith(".debug")) {
            primary.removeSuffix(".debug")
        } else {
            "$primary.debug"
        }
        return listOf(primary, alternate).distinct()
    }

    fun resolveInstalledPackage(context: Context): String? {
        return candidatePackages().firstOrNull { packageName ->
            isPackageInstalled(context, packageName)
        }
    }

    fun isInstalled(context: Context): Boolean = resolveInstalledPackage(context) != null

    fun buildLaunchIntent(context: Context): Intent? {
        val packageName = resolveInstalledPackage(context) ?: return null
        val explicit = Intent().apply {
            setClassName(packageName, BuildConfig.CLIENT_SETUP_ACTIVITY)
        }
        if (context.packageManager.resolveActivity(explicit, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            return explicit
        }
        return context.packageManager.getLaunchIntentForPackage(packageName)
    }

    fun launchClient(context: Context): Boolean {
        val launchIntent = buildLaunchIntent(context)
        if (launchIntent == null) {
            Log.w(
                TAG,
                "launchClient failed: none of ${candidatePackages()} installed/resolvable",
            )
            return false
        }
        launchIntent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_SINGLE_TOP,
        )
        return runCatching {
            context.startActivity(launchIntent)
            true
        }.getOrElse { error ->
            Log.e(TAG, "launchClient startActivity failed", error)
            false
        }
    }

    fun markPendingOpenClient(context: Context) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(KEY_PENDING_OPEN, true)
            .apply()
    }

    fun consumePendingOpenClient(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val pending = prefs.getBoolean(KEY_PENDING_OPEN, false)
        if (pending) {
            prefs.edit().putBoolean(KEY_PENDING_OPEN, false).apply()
        }
        return pending
    }

    /** 已安装且权限齐全（可连后台） */
    fun isReady(context: Context): Boolean = RemoteClientPermissionChecker.isClientReady(context)

    private fun isPackageInstalled(context: Context, packageName: String): Boolean {
        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.packageManager.getPackageInfo(
                    packageName,
                    PackageManager.PackageInfoFlags.of(0),
                )
            } else {
                @Suppress("DEPRECATION")
                context.packageManager.getPackageInfo(packageName, 0)
            }
            true
        }.getOrDefault(false)
    }
}
