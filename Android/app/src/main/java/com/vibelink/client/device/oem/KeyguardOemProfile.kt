package com.vibelink.client.device.oem

import android.graphics.Rect
import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult

interface KeyguardOemProfile {
    val id: String

    val timing: KeyguardTiming

  /** WakeLock + Overlay 之后仍黑屏时的机型 fallback（手势等） */
    fun wakeFallback(host: KeyguardGestureHost): ControlResult

  /** 锁屏封面 → PIN 输入页 */
    fun dismissToPinPage(host: KeyguardGestureHost): ControlResult

    fun pinGridCoordinate(digit: Char, container: Rect): Pair<Float, Float>

    fun pinScreenCoordinate(digit: Char, screenW: Int, screenH: Int): Pair<Float, Float>

    fun preferGridGestureWhenCalibrated(): Boolean
}
