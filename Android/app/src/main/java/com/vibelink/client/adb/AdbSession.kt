package com.vibelink.client.adb

import android.os.Handler
import android.os.Looper
import android.util.Log
import java.util.concurrent.atomic.AtomicLong

/**
 * Web 运维会话：默认关闭。
 * 活跃时点侧栏 ADB / 开发者 / 配对会 begin（约 5 分钟），到期需再点续期。
 *
 * 注意：保护逻辑（[com.vibelink.client.accessibility.AppListProtectGuard]）**不读这个标记**，
 * 它跟着中继落盘并自动恢复，常驻时会一直是 active，那会让整段保护永久哑火。
 * 免弹回由按钮显式调用 `pauseKicks`，跟「人此刻在操作」绑定。
 */
object AdbSession {
    private const val TAG = "AdbSession"
    const val DEFAULT_TIMEOUT_MS = 5 * 60_000L
    private const val MIN_TIMEOUT_MS = 30_000L
    private const val MAX_TIMEOUT_MS = 10 * 60_000L

    private val activeUntilMs = AtomicLong(0L)
    private val mainHandler = Handler(Looper.getMainLooper())
    private val endRunnable = Runnable {
        if (isActive) {
            Log.i(TAG, "session auto-end by timeout")
        }
        end()
    }

    val isActive: Boolean
        get() = System.currentTimeMillis() < activeUntilMs.get()

    /**
     * 开/续一个会话窗口。
     *
     * 只延长、不缩短：调用方各有各的时长（点 ADB 传 5 分钟、自动点版本号传 3 分钟），
     * 先开 5 分钟再进 3 分钟的子流程时，直接覆盖会把窗口砍短。
     */
    fun begin(timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        val bounded = timeoutMs.coerceIn(MIN_TIMEOUT_MS, MAX_TIMEOUT_MS)
        val deadline = System.currentTimeMillis() + bounded
        if (deadline <= activeUntilMs.get()) {
            Log.i(TAG, "session keep remainingMs=${activeUntilMs.get() - System.currentTimeMillis()}")
            return
        }
        activeUntilMs.set(deadline)
        mainHandler.removeCallbacks(endRunnable)
        mainHandler.postDelayed(endRunnable, bounded)
        Log.i(TAG, "session begin timeoutMs=$bounded")
    }

    fun end() {
        activeUntilMs.set(0L)
        mainHandler.removeCallbacks(endRunnable)
        Log.i(TAG, "session end")
    }
}
