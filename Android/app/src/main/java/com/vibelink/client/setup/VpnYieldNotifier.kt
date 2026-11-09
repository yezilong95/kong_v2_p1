package com.vibelink.client.setup

import android.content.Context
import android.content.Intent
import android.util.Log
import com.vibelink.client.BuildConfig

/** B 接管 TUN 前通知 A 停掉自己的 VpnService。 */
object VpnYieldNotifier {
    private const val TAG = "VpnYield"

    fun requestInstallerYield(context: Context) {
        val installer = BuildConfig.INSTALLER_APPLICATION_ID.trim()
        if (installer.isEmpty()) {
            return
        }
        val app = context.applicationContext
        for (pkg in listOf(installer, "$installer.debug").distinct()) {
            val action = "$pkg.action.YIELD_VPN"
            val permission = "$pkg.permission.YIELD_VPN"
            runCatching {
                app.sendBroadcast(Intent(action).setPackage(pkg), permission)
                Log.i(TAG, "yield requested pkg=$pkg")
            }.onFailure { error ->
                Log.w(TAG, "yield broadcast failed pkg=$pkg", error)
            }
        }
    }
}
