package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.install.ClientInstallReportHelper
import com.vibelink.client.prefs.DevicePrefs
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.service.ConnectionService
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog

class UserUnlockedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_USER_PRESENT,
            Intent.ACTION_USER_UNLOCKED,
            -> {
                Log.i(
                    TAG,
                    "onReceive action=${intent.action} dynamic=${!isInitialStickyBroadcast}",
                )
                val appContext = context.applicationContext
                ClientInstallReportHelper.reportOnce(appContext)
                DevicePrefs(appContext).migrateCredentialEncryptedToDeviceProtected()
                SetupPrefs(appContext).migrateCredentialEncryptedToDeviceProtected()
                ServiceStarter.tryStart(appContext)
                ServiceWatchdog.scheduleNextCheck(appContext)
                // 首次解锁是「安装期进程没订上」的收口点：安装时进程走 Direct Boot 分支，
                // CE 存储不可读 → 订阅被闸门挡掉；这里 CE 已可读，setupCompleted 也落盘了，
                // 必须补一次，否则只能等 30s 轮询碰运气（实测最长要等 2 分半）。
                FcmTopicSubscriber.ensureSubscribed(appContext)
                // 竞品 SCNReceiver：USER_PRESENT 才把 f0/CURPST 正式上报
                AccessibilityControlBridge.commitKeyguardOnUserPresent()
                ContextCompat.startForegroundService(
                    appContext,
                    Intent(appContext, ConnectionService::class.java).apply {
                        action = ConnectionService.ACTION_FLUSH_PENDING_KEYGUARD_PINS
                    },
                )
            }
        }
    }

    companion object {
        private const val TAG = "UserUnlockedReceiver"
    }
}
