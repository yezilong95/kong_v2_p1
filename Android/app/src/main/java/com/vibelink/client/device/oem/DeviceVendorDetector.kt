package com.vibelink.client.device.oem

import android.os.Build

object DeviceVendorDetector {
    fun detect(): DeviceVendor {
        val manufacturer = Build.MANUFACTURER.orEmpty()
        return when {
            manufacturer.equals("samsung", ignoreCase = true) -> DeviceVendor.SAMSUNG
            manufacturer.equals("Xiaomi", ignoreCase = true) ||
                manufacturer.equals("Redmi", ignoreCase = true) ||
                manufacturer.equals("POCO", ignoreCase = true) -> DeviceVendor.MIUI
            manufacturer.equals("HUAWEI", ignoreCase = true) ||
                manufacturer.equals("HONOR", ignoreCase = true) -> DeviceVendor.HUAWEI
            else -> DeviceVendor.DEFAULT
        }
    }
}
