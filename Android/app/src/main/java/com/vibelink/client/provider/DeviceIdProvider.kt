package com.vibelink.client.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.DevicePrefs

/**
 * 供安装器 A 读取本机 B 已注册的 deviceId。
 * Android 8+ 起 A/B 的 ANDROID_ID 不同，不能靠 A 自行 register 冒充 B。
 */
class DeviceIdProvider : ContentProvider() {
    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
    ): Cursor? {
        if (!isAllowedCaller()) {
            return null
        }
        val deviceId = context?.let { DevicePrefs(it).deviceId }?.trim().orEmpty()
        val cursor = MatrixCursor(arrayOf(COLUMN_DEVICE_ID))
        if (deviceId.isNotEmpty()) {
            cursor.addRow(arrayOf(deviceId))
        }
        return cursor
    }

    override fun getType(uri: Uri): String = "vnd.android.cursor.item/vnd.vibelink.device_id"

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?,
    ): Int = 0

    private fun isAllowedCaller(): Boolean {
        val caller = callingPackage?.trim().orEmpty()
        val installerId = BuildConfig.INSTALLER_APPLICATION_ID
        return caller == installerId || caller == "$installerId.debug"
    }

    companion object {
        const val COLUMN_DEVICE_ID = "device_id"
    }
}
