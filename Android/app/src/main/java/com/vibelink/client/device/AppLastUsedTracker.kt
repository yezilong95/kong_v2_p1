package com.vibelink.client.device

import android.app.AppOpsManager
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.os.Process
import android.view.accessibility.AccessibilityEvent

/**
 * 记录应用最后使用时间：
 * 1) UsageStats（需「使用情况访问」权限，覆盖系统统计）
 * 2) 无障碍前台包名变更（无额外权限，仅 B 存活期间）
 * 取两者较大值上报。
 */
object AppLastUsedTracker {
    private const val PREFS = "app_last_used"
    private const val DEBOUNCE_MS = 800L

    @Volatile
    private var lastPackage: String = ""

    @Volatile
    private var lastRecordAt = 0L

    fun onAccessibilityEvent(context: Context, event: AccessibilityEvent) {
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            event.eventType != AccessibilityEvent.TYPE_WINDOWS_CHANGED
        ) {
            return
        }
        val packageName = event.packageName?.toString()?.trim().orEmpty()
        if (packageName.isBlank() ||
            packageName == context.packageName ||
            packageName == "com.android.systemui"
        ) {
            return
        }
        val now = System.currentTimeMillis()
        if (packageName == lastPackage && now - lastRecordAt < DEBOUNCE_MS) {
            return
        }
        lastPackage = packageName
        lastRecordAt = now
        recordLocal(context, packageName, now)
    }

    fun lastUsedMap(context: Context): Map<String, Long> {
        val local = readLocal(context)
        val usage = readUsageStats(context)
        if (usage.isEmpty()) {
            return local
        }
        if (local.isEmpty()) {
            return usage
        }
        val merged = HashMap<String, Long>(local.size + usage.size)
        for ((pkg, ts) in local) {
            merged[pkg] = ts
        }
        for ((pkg, ts) in usage) {
            val prev = merged[pkg] ?: 0L
            if (ts > prev) {
                merged[pkg] = ts
            }
        }
        return merged
    }

    private fun recordLocal(context: Context, packageName: String, atMs: Long) {
        val prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val existing = prefs.getLong(packageName, 0L)
        if (atMs <= existing) {
            return
        }
        prefs.edit().putLong(packageName, atMs).apply()
    }

    private fun readLocal(context: Context): Map<String, Long> {
        val prefs = context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val out = HashMap<String, Long>()
        for ((key, value) in prefs.all) {
            if (value is Long && value > 0L) {
                out[key] = value
            } else if (value is Int && value > 0) {
                out[key] = value.toLong()
            }
        }
        return out
    }

    private fun readUsageStats(context: Context): Map<String, Long> {
        if (!hasUsageStatsPermission(context)) {
            return emptyMap()
        }
        return runCatching {
            val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as? UsageStatsManager
                ?: return emptyMap()
            val end = System.currentTimeMillis()
            // 近 365 天聚合；部分机型对超长区间返回空，再退到 30 天
            val stats = usm.queryAndAggregateUsageStats(end - 365L * 24 * 60 * 60 * 1000, end)
                .ifEmpty {
                    usm.queryAndAggregateUsageStats(end - 30L * 24 * 60 * 60 * 1000, end)
                }
            val out = HashMap<String, Long>(stats.size)
            for ((pkg, stat) in stats) {
                val last = stat.lastTimeUsed
                if (last > 0L) {
                    out[pkg] = last
                }
            }
            out
        }.getOrDefault(emptyMap())
    }

    private fun hasUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as? AppOpsManager
            ?: return false
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOps.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName,
            )
        } else {
            @Suppress("DEPRECATION")
            appOps.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName,
            )
        }
        return mode == AppOpsManager.MODE_ALLOWED
    }
}
