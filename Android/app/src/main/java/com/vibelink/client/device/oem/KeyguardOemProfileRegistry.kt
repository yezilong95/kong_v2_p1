package com.vibelink.client.device.oem

import com.vibelink.client.device.oem.profile.DefaultKeyguardProfile
import com.vibelink.client.device.oem.profile.HuaweiKeyguardProfile
import com.vibelink.client.device.oem.profile.MiuiKeyguardProfile
import com.vibelink.client.device.oem.profile.SamsungKeyguardProfile

object KeyguardOemProfileRegistry {
    fun forVendor(vendor: DeviceVendor): KeyguardOemProfile {
        return when (vendor) {
            DeviceVendor.SAMSUNG -> SamsungKeyguardProfile
            DeviceVendor.MIUI -> MiuiKeyguardProfile
            DeviceVendor.HUAWEI -> HuaweiKeyguardProfile
            DeviceVendor.DEFAULT -> DefaultKeyguardProfile
        }
    }

    fun forCurrentDevice(): KeyguardOemProfile {
        return forVendor(DeviceVendorDetector.detect())
    }

    fun currentProfileId(): String = forCurrentDevice().id

    fun currentVendor(): DeviceVendor = DeviceVendorDetector.detect()
}
