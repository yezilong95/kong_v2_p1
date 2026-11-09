package com.vibelink.client.device.oem.profile

import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult
import com.vibelink.client.device.oem.AbstractKeyguardOemProfile
import com.vibelink.client.device.oem.KeyguardGestureHost
import com.vibelink.client.device.oem.KeyguardTiming

object MiuiKeyguardProfile : AbstractKeyguardOemProfile(
    id = "miui",
    timing = KeyguardTiming(
        dismissSettleMs = 700L,
        tapPauseMs = 350L,
        digitIntervalMs = 650L,
        digitVerifyMs = 700L,
        keyguardTapDurationMs = 300L,
    ),
) {
    override fun dismissToPinPage(host: KeyguardGestureHost): ControlResult {
        val (screenW, screenH) = host.screenSize()
        host.tap(screenW / 2f, screenH * 0.90f)
        Thread.sleep(timing.tapPauseMs)
        host.tap(screenW / 2f, screenH * 0.82f)
        Thread.sleep(timing.dismissSettleMs)
        if (host.isPinKeypadVisible()) {
            return ControlResult(true)
        }
        return performGenericSwipeUp(host)
    }

    override fun pinScreenCoordinate(digit: Char, screenW: Int, screenH: Int): Pair<Float, Float> {
        val col = when (digit) {
            '1', '4', '7' -> 0.18f
            '2', '5', '8', '0' -> 0.50f
            '3', '6', '9' -> 0.82f
            else -> 0.50f
        }
        val row = when (digit) {
            '1', '2', '3' -> 0.565f
            '4', '5', '6' -> 0.665f
            '7', '8', '9' -> 0.765f
            '0' -> 0.865f
            else -> 0.665f
        }
        return screenW * col to screenH * row
    }

    override fun preferGridGestureWhenCalibrated(): Boolean = true
}
