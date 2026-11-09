package com.vibelink.installer

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager

object LauncherVisibilityGuard {
    fun ensureVisible(context: Context) {
        context.packageManager.setComponentEnabledSetting(
            ComponentName(context, MainActivity::class.java),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP,
        )
    }
}
