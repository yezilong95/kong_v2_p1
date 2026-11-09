package com.vibelink.client.device.oem

import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult

/**
 * 注意：构造参数不能与 override 方法同名，否则 Kotlin 会解析成递归自调用 → StackOverflow。
 * 00:05:18 真机崩溃根因：screenSize() 调到自己，进程被杀 → WS 断 → Web 显示离线。
 */
class KeyguardGestureHostImpl(
    private val interactiveProbe: () -> Boolean,
    private val screenSizeProbe: () -> Pair<Float, Float>,
    private val pinKeypadVisibleProbe: () -> Boolean,
    private val tapAction: (Float, Float) -> ControlResult,
    private val swipeAction: (Float, Float, Float, Float, Long) -> ControlResult,
) : KeyguardGestureHost {
    override fun isInteractive(): Boolean = interactiveProbe()

    override fun screenSize(): Pair<Float, Float> = screenSizeProbe()

    override fun isPinKeypadVisible(): Boolean = pinKeypadVisibleProbe()

    override fun tap(x: Float, y: Float): ControlResult = tapAction(x, y)

    override fun swipe(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
    ): ControlResult = swipeAction(x1, y1, x2, y2, durationMs)
}
