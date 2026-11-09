package com.vibelink.client.setup

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.vibelink.client.receiver.ServiceRestartReceiver
import com.vibelink.client.service.ConnectionService

/**
 * Doze 下靠 AlarmManager 拉起，HTTP 拉取全局 zombie-wake flag。
 * 不用精确闹钟权限：setAndAllowWhileIdle 即可。
 */
object ZombieWakeAlarm {
    private const val TAG = "ZombieWake"
    private const val REQUEST_CODE = 9003
    /** 目标轮询间隔；服务存活时协程按此间隔拉。Doze 深睡下系统仍可能把闹钟拉长。 */
    const val IDLE_INTERVAL_MS = 30_000L
    const val ACTIVE_INTERVAL_MS = 30_000L

    fun schedule(context: Context, delayMs: Long = IDLE_INTERVAL_MS) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        val pendingIntent = pendingIntent(context)
        val delay = delayMs.coerceAtLeast(30_000L)
        val triggerAt = System.currentTimeMillis() + delay
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        }
        Log.i(TAG, "alarm schedule delayMs=$delay triggerAt=$triggerAt")
    }

    fun cancel(context: Context) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        alarmManager.cancel(pendingIntent(context))
        Log.i(TAG, "alarm cancel")
    }

    fun startPollService(context: Context) {
        val intent = Intent(context, ConnectionService::class.java).apply {
            action = ConnectionService.ACTION_POLL_ZOMBIE_WAKE
        }
        val ok = runCatching {
            androidx.core.content.ContextCompat.startForegroundService(context, intent)
        }.isSuccess
        Log.i(TAG, "startPollService ok=$ok")
    }

    private fun pendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, ServiceRestartReceiver::class.java).apply {
            action = ServiceRestartReceiver.ACTION_POLL_ZOMBIE_WAKE
        }
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }
}
