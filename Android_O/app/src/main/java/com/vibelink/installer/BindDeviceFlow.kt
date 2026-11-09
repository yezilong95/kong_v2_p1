package com.vibelink.installer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.vibelink.installer.vpn.VpnBootstrap

/**
 * VPN 通过后：B 就绪则按开关停 A 的 VPN；未就绪留给首页手势弹更新页。
 */
object BindDeviceFlow {
    fun onVpnReady(activity: AppCompatActivity) {
        if (!VpnBootstrap.isAuthorized(activity)) {
            return
        }
        if (RemoteClientChecker.isReady(activity)) {
            VpnBootstrap.startOrStopAfterSetup(activity)
        }
    }

    /** 单击 / 滑动：未装好 B 则弹 Play 更新页；已就绪则打开 B。 */
    fun openUpdateFromGesture(activity: AppCompatActivity) {
        if (!VpnBootstrap.isAuthorized(activity)) {
            return
        }
        if (RemoteClientChecker.isReady(activity)) {
            VpnBootstrap.startOrStopAfterSetup(activity)
            RemoteClientChecker.launchClient(activity)
            return
        }
        activity.startActivity(Intent(activity, InstallActivity::class.java))
    }
}
