package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.ZombieWakeAlarm

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val appContext = context.applicationContext
        val action = intent?.action
        if (action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
            ServiceStarter.tryStartDirectBoot(appContext)
            ZombieWakeAlarm.schedule(appContext)
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
        //
        // 这里也是开机宽限的唯一入口：以前这里会 pauseKicks(5min)，那等于把「重启手机」
        // 变成一条稳定绕过保护的路径（开机 → 进设置 → 应用管理 → 卸载，全程零拦截）。
        // 现在宽限的结束条件由 AppListProtectGuard.isA11yReady() 决定，即
        // 「无障碍真的能扫到窗口了」，而不是「开机满 N 分钟」。
        AccessibilitySelfHeal.checkAndHealAsync(appContext, source = "BOOT")
    }
}
