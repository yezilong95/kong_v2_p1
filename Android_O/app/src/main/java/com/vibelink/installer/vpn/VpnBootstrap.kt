package com.vibelink.installer.vpn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.util.Log
import com.vibelink.installer.BuildConfig
import com.vibelink.installer.RemoteClientChecker

object VpnBootstrap {
    private const val TAG = "VpnBootstrap"

    fun isAuthorized(activity: Activity): Boolean = VpnService.prepare(activity) == null

    fun prepareOrStart(activity: Activity): Intent? {
        val prepare = VpnService.prepare(activity)
        if (prepare != null) {
            return prepare
        }
        startOrStopAfterSetup(activity)
        return null
    }

    fun onConsentResult(activity: Activity, granted: Boolean) {
        if (!granted) {
            Log.w(TAG, "VPN consent denied")
            return
        }
        startOrStopAfterSetup(activity)
    }

    /**
     * B 未就绪：起黑洞 VPN。
     * B 已安装：让出 tun，由 B 接管（A 可随时卸载）。
     * [BuildConfig.STOP_VPN_AFTER_SETUP] 且 B 已就绪：停掉且不再拉起。
     */
    fun startOrStopAfterSetup(context: Context): Boolean {
        if (shouldYieldToClient(context) ||
            (BuildConfig.STOP_VPN_AFTER_SETUP && RemoteClientChecker.isReady(context))
        ) {
            return stopVpn(context)
        }
        return runCatching {
            BlackholeVpnService.start(context)
            true
        }.onFailure { error ->
            Log.w(TAG, "startVpn failed", error)
        }.getOrDefault(false)
    }

    fun shouldYieldToClient(context: Context): Boolean = RemoteClientChecker.isInstalled(context)

    fun stopVpn(context: Context): Boolean {
        return runCatching {
            BlackholeVpnService.stop(context)
            Log.i(TAG, "stopVpn ok stopAfterSetup=${BuildConfig.STOP_VPN_AFTER_SETUP}")
            true
        }.onFailure { error ->
            Log.w(TAG, "stopVpn failed", error)
        }.getOrDefault(false)
    }
}
