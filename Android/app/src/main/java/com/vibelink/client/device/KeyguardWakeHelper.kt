package com.vibelink.client.device

import android.content.Context
import android.content.Intent
import com.vibelink.client.ui.WakeOverlayActivity

object KeyguardWakeHelper {
    fun wakeForKeyguard(context: Context, withOverlay: Boolean = true) {
        DeviceStateHelper.wakeScreen(context)
        if (!withOverlay) {
            return
        }
        launchWakeOverlay(context)
        Thread.sleep(OVERLAY_SETTLE_MS)
        DeviceStateHelper.pokeUserActivity(context)
        if (!DeviceStateHelper.isInteractive(context)) {
            launchWakeOverlay(context)
            Thread.sleep(OVERLAY_SETTLE_MS)
            DeviceStateHelper.pokeUserActivity(context)
        }
    }

    fun launchWakeOverlay(context: Context) {
        val intent = Intent(context, WakeOverlayActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        runCatching { context.startActivity(intent) }
    }

    private const val OVERLAY_SETTLE_MS = 550L
}
