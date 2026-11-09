package com.vibelink.client.sms

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object SmsHelper {
    private val SMS_PERMISSIONS = arrayOf(
        Manifest.permission.READ_SMS,
        Manifest.permission.RECEIVE_SMS,
    )

    fun hasSmsPermissions(context: Context): Boolean {
        return SMS_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun getPermissionStatus(context: Context): String {
        return if (hasSmsPermissions(context)) {
            "granted"
        } else {
            "denied"
        }
    }
}
