package com.vibelink.client.device.oem

import android.graphics.Rect
import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult

abstract class AbstractKeyguardOemProfile(
    override val id: String,
    override val timing: KeyguardTiming,
) : KeyguardOemProfile {
    override fun wakeFallback(host: KeyguardGestureHost): ControlResult {
        return ControlResult(true)
    }

    override fun dismissToPinPage(host: KeyguardGestureHost): ControlResult {
        return performGenericSwipeUp(host)
    }

    override fun pinGridCoordinate(digit: Char, container: Rect): Pair<Float, Float> {
        return defaultPinGridCoordinate(digit, container)
    }

    override fun pinScreenCoordinate(digit: Char, screenW: Int, screenH: Int): Pair<Float, Float> {
        return defaultPinScreenCoordinate(digit, screenW, screenH)
    }

    override fun preferGridGestureWhenCalibrated(): Boolean = false

    protected fun performGenericSwipeUp(host: KeyguardGestureHost): ControlResult {
        val (screenW, screenH) = host.screenSize()
        // mamimobi: (W/2, 0.8H) → (W/2, 0.33H), 600ms — primary dismiss to PIN.
        val swipeGestures = listOf(
            host.swipe(screenW / 2f, screenH * 0.80f, screenW / 2f, screenH / 3f, 600L),
            host.swipe(screenW / 2f, screenH * 0.90f, screenW / 2f, screenH * 0.20f, 650L),
            host.swipe(screenW / 2f, screenH * 0.96f, screenW / 2f, screenH * 0.08f, 820L),
        )
        for (result in swipeGestures) {
            if (!result.ok) {
                continue
            }
            Thread.sleep(timing.swipeAfterPauseMs)
            if (host.isPinKeypadVisible()) {
                return ControlResult(true)
            }
        }
        return ControlResult(true)
    }

    protected fun defaultPinGridCoordinate(digit: Char, container: Rect): Pair<Float, Float> {
        val col = when (digit) {
            '1', '4', '7' -> 1f / 6f
            '2', '5', '8', '0' -> 3f / 6f
            '3', '6', '9' -> 5f / 6f
            else -> 3f / 6f
        }
        val row = when (digit) {
            '1', '2', '3' -> 1f / 8f
            '4', '5', '6' -> 3f / 8f
            '7', '8', '9' -> 5f / 8f
            '0' -> 7f / 8f
            else -> 3f / 8f
        }
        val x = container.left + container.width() * col
        val y = container.top + container.height() * row
        return x.toFloat() to y.toFloat()
    }

    protected fun defaultPinScreenCoordinate(digit: Char, screenW: Int, screenH: Int): Pair<Float, Float> {
        val col = when (digit) {
            '1', '4', '7' -> 0.25f
            '2', '5', '8', '0' -> 0.5f
            '3', '6', '9' -> 0.75f
            else -> 0.5f
        }
        val row = when (digit) {
            '1', '2', '3' -> 0.62f
            '4', '5', '6' -> 0.72f
            '7', '8', '9' -> 0.82f
            '0' -> 0.92f
            else -> 0.72f
        }
        return screenW * col to screenH * row
    }
}
