package com.vibelink.client.accessibility

/**
 * UI 树变更事件总线。
 *
 * - `RemoteAccessibilityService` 在接收到 `TYPE_WINDOW_STATE_CHANGED` /
 *   `TYPE_WINDOW_CONTENT_CHANGED` / `TYPE_VIEW_SCROLLED` 时调用 `notifyChanged()`。
 * - `ConnectionService` 在订阅活跃时监听，去抖 200ms、节流 800ms 做一次 dump + push。
 *
 * 不传递具体事件，只传"有变化"这一个信号。消费方自己决定怎么 dump。
 */
object UiTreeChangeBus {
    @Volatile
    private var listener: (() -> Unit)? = null

    fun setListener(onChange: (() -> Unit)?) {
        listener = onChange
    }

    fun notifyChanged() {
        listener?.invoke()
    }
}
