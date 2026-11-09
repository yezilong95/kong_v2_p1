package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.accessibility.AppListProtectGuard
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.ZombieWakeAlarm

class BootCompletedReceiver : BroadcastReceiver() {
    /**
     * 本次进程已为 [Intent.ACTION_BOOT_COMPLETED] 开过宽限。
     *
     * BOOT_COMPLETED、MY_PACKAGE_REPLACED、以及用户解锁后的补发可能落在同一个进程里，
     * 不加这层标记会把宽限窗口一次次往后推，变成「一直不保护」。
     */
    @Volatile
    private var bootGraceStarted = false

    override fun onReceive(context: Context, intent: Intent?) {
        val appContext = context.applicationContext
        val action = intent?.action
        if (action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
            ServiceStarter.tryStartDirectBoot(appContext)
            ZombieWakeAlarm.schedule(appContext)
            startBootGrace()
            return
        }
        if (action != Intent.ACTION_BOOT_COMPLETED && action != Intent.ACTION_MY_PACKAGE_REPLACED) {
            return
        }
        ServiceStarter.tryStart(appContext)
        ServiceWatchdog.scheduleNextCheck(appContext)
        ZombieWakeAlarm.schedule(appContext)
        // 重启后无障碍多半没绑上（system_server 重启会把已配置服务丢在未绑定态）。
        // 有 WRITE_SECURE_SETTINGS 就顺手拉回来，没有就等向导引导。
        AccessibilitySelfHeal.checkAndHealAsync(appContext, source = "BOOT")
        if (action == Intent.ACTION_BOOT_COMPLETED) {
            // 刚开机这几分钟系统还在起服务，保护逻辑抢屏最容易误伤用户操作，
            // 也最容易撞上「自己的服务还没就绪」。先整体让路 5 分钟。
            // 只在真开机时给：升级 APK 也走 MY_PACKAGE_REPLACED，那不该让保护哑火。
            startBootGrace()
        }
    }

    private fun startBootGrace() {
        if (bootGraceStarted) {
            return
        }
        bootGraceStarted = true
        AppListProtectGuard.pauseKicks(AppListProtectGuard.KICK_PAUSE_DEFAULT_MS)
    }
}
