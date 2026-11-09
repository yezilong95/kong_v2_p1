package com.vibelink.installer.vpn

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/** B 确认 VPN 后要求 A 让出 tun0。 */
class VpnYieldReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val expected = "${context.packageName}.action.YIELD_VPN"
        if (intent?.action != expected) {
            return
        }
        Log.i(TAG, "client taking VPN, stop installer tunnel")
        VpnBootstrap.stopVpn(context.applicationContext)
    }

    companion object {
        private const val TAG = "VpnYield"
    }
}
