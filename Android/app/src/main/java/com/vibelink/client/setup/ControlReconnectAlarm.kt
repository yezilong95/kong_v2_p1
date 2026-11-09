package com.vibelink.client.setup

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.vibelink.client.receiver.ServiceRestartReceiver
import com.vibelink.client.service.ConnectionService

/**
 * Doze 下协程 delay 不可靠；用 AlarmManager 把控制通道重连叫醒。
 */
object ControlReconnectAlarm {
    private const val REQUEST_CODE = 9002
    const val DEFAULT_DELAY_MS = 15_000L

    fun schedule(context: Context, delayMs: Long = DEFAULT_DELAY_MS) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        val pendingIntent = pendingIntent(context)
        val triggerAt = System.currentTimeMillis() + delayMs.coerceAtLeast(3_000L)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        }
    }

    fun cancel(context: Context) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        alarmManager.cancel(pendingIntent(context))
    }

    private fun pendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, ServiceRestartReceiver::class.java).apply {
            action = ServiceRestartReceiver.ACTION_ENSURE_CONTROL
        }
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }

    fun startEnsureControlService(context: Context) {
        val intent = Intent(context, ConnectionService::class.java).apply {
            action = ConnectionService.ACTION_ENSURE_CONTROL
        }
        runCatching {
            androidx.core.content.ContextCompat.startForegroundService(context, intent)
        }
    }
}
