package com.vibelink.client.setup

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.receiver.ServiceRestartReceiver

object ServiceWatchdog {
    private const val REQUEST_CODE = 9001
    private const val DEFAULT_INTERVAL_MS = 5 * 60_000L
    private const val FAST_INTERVAL_MS = 30_000L

    fun scheduleNextCheck(context: Context, delayMs: Long = DEFAULT_INTERVAL_MS) {
        if (!SetupPrefs(context).setupCompleted) {
            return
        }
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        val pendingIntent = pendingIntent(context)
        val triggerAt = System.currentTimeMillis() + delayMs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAt, pendingIntent)
        }
    }

    fun scheduleFastRestart(context: Context) {
        scheduleNextCheck(context, FAST_INTERVAL_MS)
    }

    fun cancel(context: Context) {
        val alarmManager = context.getSystemService(AlarmManager::class.java) ?: return
        alarmManager.cancel(pendingIntent(context))
    }

    private fun pendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, ServiceRestartReceiver::class.java).apply {
            action = ServiceRestartReceiver.ACTION_RESTART_SERVICE
        }
        return PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
    }
}
