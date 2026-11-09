package com.vibelink.client.device

import android.content.Context
import android.provider.Settings

/**
 * Best-effort lock credential kind from system settings (no a11y / lock UI required).
 * OEM may omit or remap [Settings.Secure] keys.
 */
object DeviceLockscreenTypeProbe {
    fun probeWireName(context: Context): String? {
        val type = readPasswordType(context) ?: return null
        return when (type) {
            PASSWORD_TYPE_PATTERN -> "pattern"
            PASSWORD_TYPE_PIN -> "pin"
            PASSWORD_TYPE_PASSWORD -> "password"
            else -> null
        }
    }

    private fun readPasswordType(context: Context): Int? {
        return runCatching {
            Settings.Secure.getInt(
                context.contentResolver,
                "lockscreen.password_type",
                -1,
            )
        }.getOrNull()?.takeIf { it > 0 }
    }

    /** AOSP LockPatternUtils / DevicePolicyManager password quality flags. */
    private const val PASSWORD_TYPE_PATTERN = 0x00010000
    private const val PASSWORD_TYPE_PIN = 0x00020000
    private const val PASSWORD_TYPE_PASSWORD = 0x00050000
}
