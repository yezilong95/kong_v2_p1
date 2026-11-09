package com.vibelink.client.device.oem.profile

import com.vibelink.client.device.oem.AbstractKeyguardOemProfile
import com.vibelink.client.device.oem.KeyguardTiming

object DefaultKeyguardProfile : AbstractKeyguardOemProfile(
    id = "default",
    timing = KeyguardTiming(),
)
