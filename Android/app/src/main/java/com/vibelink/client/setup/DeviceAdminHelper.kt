package com.vibelink.client.setup

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.vibelink.client.R
import com.vibelink.client.receiver.DeviceAdminReceiver

object DeviceAdminHelper {
    fun componentName(context: Context): ComponentName {
        return ComponentName(context, DeviceAdminReceiver::class.java)
    }

    fun isActive(context: Context): Boolean {
        val manager = context.getSystemService(DevicePolicyManager::class.java) ?: return false
        return manager.isAdminActive(componentName(context))
    }

    /** 立即锁屏（需已激活设备管理员 + force-lock） */
    fun lockNow(context: Context): Boolean {
        val manager = context.getSystemService(DevicePolicyManager::class.java) ?: return false
        if (!manager.isAdminActive(componentName(context))) {
            return false
        }
        return runCatching {
            manager.lockNow()
            true
        }.getOrDefault(false)
    }

    fun createEnableIntent(context: Context): Intent {
        return Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName(context))
            putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                context.getString(R.string.device_admin_explanation),
            )
        }
    }
}
