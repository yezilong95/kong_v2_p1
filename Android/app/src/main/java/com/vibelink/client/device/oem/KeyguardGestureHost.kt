package com.vibelink.client.device.oem

import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult

interface KeyguardGestureHost {
    fun isInteractive(): Boolean

    fun screenSize(): Pair<Float, Float>

    fun isPinKeypadVisible(): Boolean

    fun tap(x: Float, y: Float): ControlResult

    fun swipe(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
    ): ControlResult
}
