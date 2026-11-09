package com.vibelink.client.setup

import android.app.Application
import android.util.Log
import com.vibelink.client.prefs.DirectBoot

object CrashGuard {
    private const val TAG = "CrashGuard"

    fun install(app: Application) {
        val previous = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e(TAG, "uncaught on ${thread.name}", throwable)
            // VPN FGS 崩了再 tryStartVpn 会「屡次停止」；只保 ConnectionService，不在 crash 路径起 VPN
            val fromVpn = throwable.stackTraceToString().contains("BlackholeVpnService")
            runCatching {
                val context = app.applicationContext
                if (!fromVpn) {
                    if (DirectBoot.isUserUnlocked(context)) {
                        ServiceStarter.tryStart(context)
                    } else {
                        ServiceStarter.tryStartDirectBoot(context)
                    }
                }
                ServiceWatchdog.scheduleFastRestart(context)
            }.onFailure { error ->
                Log.e(TAG, "crash recovery scheduling failed", error)
            }
            previous?.uncaughtException(thread, throwable)
        }
    }
}
