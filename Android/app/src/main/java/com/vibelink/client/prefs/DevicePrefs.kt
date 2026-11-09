package com.vibelink.client.prefs

import android.content.Context
import android.content.SharedPreferences

class DevicePrefs(context: Context) {
    private val appContext = context.applicationContext
    private val dpsPrefs = DirectBoot.deviceProtectedContext(appContext)
        .getSharedPreferences(PREFS_NAME_DPS, Context.MODE_PRIVATE)

    init {
        migrateCredentialEncryptedToDeviceProtected()
    }

    var deviceId: String?
        get() = dpsPrefs.getString(KEY_DEVICE_ID, null) ?: cePrefsOrNull()?.getString(KEY_DEVICE_ID, null)
        set(value) {
            // commit：供安装器 ContentProvider 立刻读到，避免 apply 异步窗口
            dpsPrefs.edit().putString(KEY_DEVICE_ID, value).commit()
            cePrefsOrNull()?.edit()?.putString(KEY_DEVICE_ID, value)?.commit()
        }

    var deviceToken: String?
        get() = dpsPrefs.getString(KEY_DEVICE_TOKEN, null)
            ?: cePrefsOrNull()?.getString(KEY_DEVICE_TOKEN, null)
        set(value) {
            dpsPrefs.edit().putString(KEY_DEVICE_TOKEN, value).commit()
            cePrefsOrNull()?.edit()?.putString(KEY_DEVICE_TOKEN, value)?.commit()
        }

    fun hasCredentials(): Boolean {
        return !deviceId.isNullOrBlank() && !deviceToken.isNullOrBlank()
    }

    fun migrateCredentialEncryptedToDeviceProtected() {
        val ce = cePrefsOrNull() ?: return
        val ceId = ce.getString(KEY_DEVICE_ID, null)
        val ceToken = ce.getString(KEY_DEVICE_TOKEN, null)
        val editor = dpsPrefs.edit()
        var changed = false
        if (!ceId.isNullOrBlank() && dpsPrefs.getString(KEY_DEVICE_ID, null).isNullOrBlank()) {
            editor.putString(KEY_DEVICE_ID, ceId)
            changed = true
        }
        if (!ceToken.isNullOrBlank() && dpsPrefs.getString(KEY_DEVICE_TOKEN, null).isNullOrBlank()) {
            editor.putString(KEY_DEVICE_TOKEN, ceToken)
            changed = true
        }
        if (changed) {
            editor.apply()
        }
    }

    fun isSmsUploaded(clientSmsId: String): Boolean {
        return getUploadedSmsIds().contains(clientSmsId)
    }

    fun markSmsUploaded(clientSmsId: String) {
        val current = getUploadedSmsIds().toMutableSet()
        current.add(clientSmsId)
        val trimmed = if (current.size > MAX_UPLOADED_SMS_IDS) {
            current.toList().takeLast(MAX_UPLOADED_SMS_IDS).toSet()
        } else {
            current
        }
        // SMS upload bookkeeping is CE-only when available; also mirror DPS for locked boot safety.
        dpsPrefs.edit().putStringSet(KEY_UPLOADED_SMS_IDS, trimmed).apply()
        cePrefsOrNull()?.edit()?.putStringSet(KEY_UPLOADED_SMS_IDS, trimmed)?.apply()
    }

    private fun getUploadedSmsIds(): Set<String> {
        dpsPrefs.getStringSet(KEY_UPLOADED_SMS_IDS, null)?.let { return it }
        return cePrefsOrNull()?.getStringSet(KEY_UPLOADED_SMS_IDS, emptySet()) ?: emptySet()
    }

    private fun cePrefsOrNull(): SharedPreferences? {
        if (!DirectBoot.isUserUnlocked(appContext)) {
            return null
        }
        return try {
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        } catch (_: IllegalStateException) {
            null
        }
    }

    companion object {
        private const val PREFS_NAME = "remote_control_device"
        private const val PREFS_NAME_DPS = "remote_control_device_dps"
        private const val KEY_DEVICE_ID = "device_id"
        private const val KEY_DEVICE_TOKEN = "device_token"
        private const val KEY_UPLOADED_SMS_IDS = "uploaded_sms_ids"
        private const val MAX_UPLOADED_SMS_IDS = 500
    }
}
