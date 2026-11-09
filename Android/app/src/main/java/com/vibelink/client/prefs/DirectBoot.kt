package com.vibelink.client.prefs

import android.content.Context
import android.os.Build
import android.os.UserManager

object DirectBoot {
    fun deviceProtectedContext(context: Context): Context {
        return context.applicationContext.createDeviceProtectedStorageContext()
    }

    fun isUserUnlocked(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return true
        }
        val userManager = context.applicationContext.getSystemService(UserManager::class.java)
        return userManager?.isUserUnlocked == true
    }
}
