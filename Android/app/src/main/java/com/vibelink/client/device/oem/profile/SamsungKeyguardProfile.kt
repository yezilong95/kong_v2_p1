package com.vibelink.client.device.oem.profile

import android.graphics.Rect
import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult
import com.vibelink.client.device.oem.AbstractKeyguardOemProfile
import com.vibelink.client.device.oem.KeyguardGestureHost
import com.vibelink.client.device.oem.KeyguardTiming

object SamsungKeyguardProfile : AbstractKeyguardOemProfile(
    id = "samsung",
    timing = KeyguardTiming(
        wakeSettleMs = 800L,
        dismissSettleMs = 1200L,
        tapPauseMs = 400L,
        swipeAfterPauseMs = 800L,
        pinPageSettleMs = 600L,
        digitIntervalMs = 280L,
        unlockReadyMs = 1200L,
        confirmAfterPinMs = 500L,
    ),
) {
    override fun wakeFallback(host: KeyguardGestureHost): ControlResult {
        val (screenW, screenH) = host.screenSize()
        host.tap(screenW / 2f, screenH * 0.50f)
        Thread.sleep(timing.tapPauseMs)
        host.tap(screenW / 2f, screenH * 0.50f)
        Thread.sleep(timing.tapPauseMs)
        // Same dismiss as mamimobi after wake.
        host.swipe(screenW / 2f, screenH * 0.80f, screenW / 2f, screenH / 3f, 600L)
        Thread.sleep(timing.swipeAfterPauseMs)
        return ControlResult(true)
    }

    override fun dismissToPinPage(host: KeyguardGestureHost): ControlResult {
        val (screenW, screenH) = host.screenSize()
        // Cover "滑动来解锁": mamimobi swipe + deeper bottom starts.
        val swipes = listOf(
            // mamimobi exact: (W/2, 4/5 H) → (W/2, H/3), 600ms
            Triple(screenH * 4f / 5f, screenH / 3f, 600L),
            Triple(screenH * 0.90f, screenH * 0.20f, 700L),
            Triple(screenH * 0.95f, screenH * 0.12f, 850L),
        )
        for ((y1, y2, duration) in swipes) {
            host.swipe(screenW / 2f, y1, screenW / 2f, y2, duration)
            Thread.sleep(timing.swipeAfterPauseMs)
            if (host.isPinKeypadVisible()) {
                return ControlResult(true)
            }
        }
        return performGenericSwipeUp(host)
    }

    override fun pinGridCoordinate(digit: Char, container: Rect): Pair<Float, Float> {
        val col = when (digit) {
            '1', '4', '7' -> 0.18f
            '2', '5', '8', '0' -> 0.50f
            '3', '6', '9' -> 0.82f
            else -> 0.50f
        }
        val row = when (digit) {
            '1', '2', '3' -> 0.20f
            '4', '5', '6' -> 0.43f
            '7', '8', '9' -> 0.66f
            '0' -> 0.88f
            else -> 0.43f
        }
        val x = container.left + container.width() * col
        val y = container.top + container.height() * row
        return x.toFloat() to y.toFloat()
    }
}
