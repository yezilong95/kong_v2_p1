package com.vibelink.client.accessibility

import java.util.concurrent.atomic.AtomicBoolean

/** Signals on-demand screen capture while black-screen mode is active. */
object ScreenRefreshBus {
    private val pending = AtomicBoolean(false)

    fun request() {
        pending.set(true)
    }

    fun consume(): Boolean = pending.compareAndSet(true, false)
}
