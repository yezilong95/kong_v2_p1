package com.vibelink.client.device.oem.profile

import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult
import com.vibelink.client.device.oem.AbstractKeyguardOemProfile
import com.vibelink.client.device.oem.KeyguardGestureHost
import com.vibelink.client.device.oem.KeyguardTiming

/**
 * 华为 / 荣耀锁屏策略占位：待 dump_keyguard_tree 后补 viewId 与坐标。
 */
object HuaweiKeyguardProfile : AbstractKeyguardOemProfile(
    id = "huawei",
    timing = KeyguardTiming(
        wakeSettleMs = 600L,
        dismissSettleMs = 800L,
        tapPauseMs = 400L,
    ),
) {
    override fun wakeFallback(host: KeyguardGestureHost): ControlResult {
        val (screenW, screenH) = host.screenSize()
        host.tap(screenW / 2f, screenH * 0.50f)
        Thread.sleep(timing.tapPauseMs)
        host.tap(screenW / 2f, screenH * 0.50f)
        Thread.sleep(timing.tapPauseMs)
        host.swipe(
            screenW / 2f,
            screenH * 0.92f,
            screenW / 2f,
            screenH * 0.65f,
            520L,
        )
        return ControlResult(true)
    }
}
