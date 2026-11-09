package com.vibelink.client

import android.app.Application
import com.vibelink.client.device.RemoteKeepAwakeOverlayController
import com.vibelink.client.install.ClientInstallReportHelper
import com.vibelink.client.prefs.DevicePrefs
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.setup.CrashGuard
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.ServiceStarter

class RemoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashGuard.install(this)
        if (DirectBoot.isUserUnlocked(this)) {
            ClientInstallReportHelper.reportOnce(this)
            DevicePrefs(this).migrateCredentialEncryptedToDeviceProtected()
            SetupPrefs(this).migrateCredentialEncryptedToDeviceProtected()
            // 常亮期间进程被杀会留下 24h 的 screen_off_timeout，启动时自愈
            RemoteKeepAwakeOverlayController.recoverStaleScreenOffTimeout(this)
            // 必须先于订阅：黑洞 VPN 若已建立，GMS 的 FCM 注册会 SERVICE_NOT_AVAILABLE，
            // 订阅直接失败（实测）。setup 完成后不该再有 VPN，这里提前收一次。
            ServiceStarter.stopVpnIfSetupCompleted(this)
            FcmTopicSubscriber.subscribe(this)
            ServiceStarter.tryStart(this)
        } else {
            // Direct Boot（安装后进程先于用户解锁启动）：此时 CE 存储不可读，
            // SetupPrefs 拿不到 setupCompleted，订阅会在闸门前被挡或直接失败，
            // 所以这里不起订阅。真正的补订阅点是 UserUnlockedReceiver——
            // 少了它就会出现"新装后长时间唤不醒，直到某次轮询碰巧兜住"。
            ServiceStarter.tryStartDirectBoot(this)
        }
    }
}
