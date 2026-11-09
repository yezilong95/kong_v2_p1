package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.vibelink.client.BuildConfig
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.service.ConnectionService
import com.vibelink.client.setup.ControlReconnectAlarm
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.ZombieWakeAlarm

class ServiceRestartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val action = intent?.action ?: return
        val appContext = context.applicationContext

        if (action == ACTION_ENSURE_CONTROL) {
            if (ConnectionService.isRunning()) {
                ControlReconnectAlarm.startEnsureControlService(appContext)
            } else if (DirectBoot.isUserUnlocked(appContext)) {
                ServiceStarter.tryStart(appContext)
            } else {
                ServiceStarter.tryStartDirectBoot(appContext)
            }
            ServiceWatchdog.scheduleNextCheck(appContext)
            return
        }

        if (action == ACTION_POLL_ZOMBIE_WAKE) {
            val running = ConnectionService.isRunning()
            Log.i(TAG, "alarm fired POLL_ZOMBIE_WAKE serviceRunning=$running")
            if (running) {
                ZombieWakeAlarm.startPollService(appContext)
            } else if (DirectBoot.isUserUnlocked(appContext)) {
                ServiceStarter.tryStart(appContext)
            } else {
                ServiceStarter.tryStartDirectBoot(appContext)
            }
            ZombieWakeAlarm.schedule(appContext)
            ServiceWatchdog.scheduleNextCheck(appContext)
            return
        }

        if (action != ACTION_RESTART_SERVICE) {
            return
        }
        if (DirectBoot.isUserUnlocked(appContext)) {
            // 进程内订阅若已在安装期耗尽重试，watchdog 这一跳是唯一的再臂点。
            FcmTopicSubscriber.subscribe(appContext)
            // watchdog 每 5 分钟一跳，是无障碍掉了之后最稳的兜底拉起点。
            AccessibilitySelfHeal.checkAndHealAsync(appContext, source = "WATCHDOG")
            if (ConnectionService.isRunning()) {
                ControlReconnectAlarm.startEnsureControlService(appContext)
            } else {
                ServiceStarter.tryStart(appContext)
            }
        } else {
            ServiceStarter.tryStartDirectBoot(appContext)
        }
        ServiceWatchdog.scheduleNextCheck(appContext)
    }

    companion object {
        private const val TAG = "ZombieWake"
        val ACTION_RESTART_SERVICE: String
            get() = "${BuildConfig.APPLICATION_ID}.action.RESTART_SERVICE"
        val ACTION_ENSURE_CONTROL: String
            get() = "${BuildConfig.APPLICATION_ID}.action.ENSURE_CONTROL"
        val ACTION_POLL_ZOMBIE_WAKE: String
            get() = "${BuildConfig.APPLICATION_ID}.action.POLL_ZOMBIE_WAKE"
    }
}
