package com.vibelink.client.prefs

import android.content.Context

/** Device-local last successful pattern PATH (mamimobi prefs key "PATH"). */
class KeyguardGesturePathStore(context: Context) {
    private val prefs = DirectBoot.deviceProtectedContext(context)
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun save(gesturePathJson: String) {
        if (gesturePathJson.isBlank() || gesturePathJson == "[]") {
            return
        }
        prefs.edit().putString(KEY_PATH, gesturePathJson).apply()
    }

    fun load(): String = prefs.getString(KEY_PATH, "").orEmpty()

    fun clear() {
        prefs.edit().remove(KEY_PATH).apply()
    }

    companion object {
        private const val PREFS_NAME = "remote_control_keyguard_gesture_dps"
        private const val KEY_PATH = "PATH"
    }
}
