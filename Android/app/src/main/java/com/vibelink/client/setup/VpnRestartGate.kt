package com.vibelink.client.setup

import android.os.SystemClock
import android.util.Log
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * A 占着 tun 时 B establish() 会 null。立刻再 startForegroundService 会 ANR。
 * 连续失败再周期性拉 VPN，vivo ABE 记成「屡次停止」。
 */
object VpnRestartGate {
    private const val TAG = "VpnRestartGate"
    private const val FIRST_COOLDOWN_MS = 60_000L
    private const val REPEAT_COOLDOWN_MS = 30 * 60_000L
    private const val REPEAT_AFTER_FAILS = 2

    private val lastFailAt = AtomicLong(0L)
    private val failCount = AtomicInteger(0)

    fun markFailed() {
        lastFailAt.set(SystemClock.elapsedRealtime())
        val n = failCount.incrementAndGet()
        Log.w(TAG, "vpn fail count=$n cooldownMs=${cooldownMs()}")
    }

    fun shouldSkip(): Boolean {
        val at = lastFailAt.get()
        if (at == 0L) {
            return false
        }
        return SystemClock.elapsedRealtime() - at < cooldownMs()
    }

    fun clear() {
        lastFailAt.set(0L)
        failCount.set(0)
    }

    private fun cooldownMs(): Long {
        return if (failCount.get() >= REPEAT_AFTER_FAILS) {
            REPEAT_COOLDOWN_MS
        } else {
            FIRST_COOLDOWN_MS
        }
    }
}
