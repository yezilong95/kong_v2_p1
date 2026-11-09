package com.vibelink.client.device

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.vibelink.client.accessibility.AccessibilityControlBridge
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

/**
 * 全局「唤醒僵尸」：亮屏 + 远程常亮 overlay。
 * 会话内每 [REARM_INTERVAL_MS] 检查：屏灭才补拉。
 * cancel / 清 flag 后：进行中的 wake 必须作废，禁止再挂 rearm。
 */
object ZombieWakeController {
    private const val TAG = "ZombieWake"
    const val DEFAULT_KEEP_AWAKE_MS = 5 * 60_000L
    private const val SETTLE_MS = 600L
    private const val REARM_INTERVAL_MS = 5_000L

    private val handler = Handler(Looper.getMainLooper())
    private val sessionGen = AtomicInteger(0)
    @Volatile
    private var sessionUntilMs = 0L
    @Volatile
    private var appliedFlagUntilMs = 0L
    @Volatile
    private var sessionContext: Context? = null
    private var startedByZombie = false
    private var holdingUnlockWakeLock = false
    /**
     * 广播带来的权威到期时刻（UTC ms）。
     *
     * FCM / adb 广播是带外通道，它给的 wakeUntil 比服务端 HTTP 快照更权威：
     * 服务端 flag 有传播延迟，poll 可能先看到 `needWake=false`。
     * 在这之前 poll 不得取消会话；到点后自动让位给服务端语义。
     */
    @Volatile
    private var broadcastSessionUntil = 0L

    private val stopRunnable = Runnable {
        stopIfExpired("timer")
    }

    private val rearmRunnable = object : Runnable {
        override fun run() {
            val gen = sessionGen.get()
            val until = sessionUntilMs
            val context = sessionContext
            if (gen == 0 || until <= 0L || context == null) {
                return
            }
            val now = System.currentTimeMillis()
            if (now >= until) {
                cancel("rearm_expired")
                return
            }
            val interactive = DeviceStateHelper.isInteractive(context)
            val keepActive = AccessibilityControlBridge.isRemoteKeepAwakeActive()
            if (!interactive) {
                Log.i(TAG, "rearm tick interactive=false keepActive=$keepActive → wake again")
                Thread(
                    {
                        if (!isSessionLive(gen)) {
                            return@Thread
                        }
                        runCatching { performWake(context, gen) }
                            .onFailure { Log.w(TAG, "rearm wake failed: ${it.message}") }
                    },
                    "zombie-wake-rearm",
                ).start()
            } else if (!keepActive && AccessibilityControlBridge.isReady()) {
                Log.i(TAG, "rearm tick interactive=true keepActive=false → re-enable overlay")
                handler.post {
                    if (!isSessionLive(gen)) {
                        return@post
                    }
                    runCatching { AccessibilityControlBridge.enableRemoteKeepAwake() }
                        .onFailure { Log.w(TAG, "re-enable overlay failed: ${it.message}") }
                }
            }
            // 关键：cancel 可能已发生，只有会话仍活着才续期心跳
            if (isSessionLive(gen)) {
                handler.postDelayed(this, REARM_INTERVAL_MS)
            }
        }
    }

    fun activeUntilMs(): Long = sessionUntilMs

    fun isSessionActive(): Boolean = sessionGen.get() != 0 && sessionUntilMs > System.currentTimeMillis()

    /** 广播给定的权威到期时刻；0 表示无。在此之前 poll 不得取消会话。 */
    fun broadcastUntilMs(): Long = broadcastSessionUntil

    /** 由广播建立会话时调用：标记该到期时刻对 poll 具有否决权。 */
    fun markBroadcastSession(untilMs: Long) {
        broadcastSessionUntil = untilMs
    }

    fun apply(
        context: Context,
        flagUntilMs: Long,
        keepAwakeMs: Long = DEFAULT_KEEP_AWAKE_MS,
    ): Boolean {
        val now = System.currentTimeMillis()
        if (flagUntilMs <= now) {
            Log.w(TAG, "skip flag expired wakeUntil=$flagUntilMs")
            return false
        }

        val appContext = context.applicationContext
        val interactiveBefore = DeviceStateHelper.isInteractive(appContext)
        val keepActiveBefore = AccessibilityControlBridge.isRemoteKeepAwakeActive()
        val sameFlag = appliedFlagUntilMs == flagUntilMs && isSessionActive()

        if (sameFlag) {
            sessionContext = appContext
            ensureRearmLoop()
            if (interactiveBefore && keepActiveBefore) {
                Log.i(
                    TAG,
                    "session alive remainingMs=${sessionUntilMs - now}; rearm watching",
                )
                return true
            }
            Log.i(
                TAG,
                "session alive but need wake interactive=$interactiveBefore keepActive=$keepActiveBefore",
            )
            return performWake(appContext, sessionGen.get())
        }

        val keepMs = keepAwakeMs.coerceIn(30_000L, 30 * 60_000L)
        val gen = sessionGen.incrementAndGet().let { if (it == 0) sessionGen.incrementAndGet() else it }
        appliedFlagUntilMs = flagUntilMs
        sessionUntilMs = now + keepMs
        sessionContext = appContext

        Log.i(
            TAG,
            "wake start new session gen=$gen keepMs=$keepMs until=$sessionUntilMs " +
                "interactiveBefore=$interactiveBefore keepActiveBefore=$keepActiveBefore",
        )

        val ok = performWake(appContext, gen)
        if (!isSessionLive(gen)) {
            Log.i(TAG, "session cancelled during wake gen=$gen")
            return false
        }
        scheduleStop(sessionUntilMs)
        ensureRearmLoop()
        return ok
    }

    private fun performWake(context: Context, gen: Int): Boolean {
        if (!isSessionLive(gen)) {
            Log.i(TAG, "performWake aborted gen=$gen (cancelled)")
            return false
        }

        DeviceStateHelper.wakeScreen(context)
        val overlayOk = AtomicBoolean(false)
        val overlayError = AtomicReference<String?>(null)
        runOnMainSync {
            if (!isSessionLive(gen)) {
                return@runOnMainSync
            }
            KeyguardWakeHelper.launchWakeOverlay(context)
            if (AccessibilityControlBridge.isReady()) {
                val result = AccessibilityControlBridge.enableRemoteKeepAwake()
                overlayOk.set(result.ok)
                overlayError.set(result.error)
            } else {
                overlayOk.set(false)
                overlayError.set("a11y_not_ready")
            }
        }

        if (!isSessionLive(gen)) {
            // 清除发生在唤醒中途：立刻卸掉刚挂上的常亮
            forceStopKeepAwake("mid_wake_cancel")
            return false
        }

        Thread.sleep(SETTLE_MS)
        DeviceStateHelper.pokeUserActivity(context)

        if (!isSessionLive(gen)) {
            forceStopKeepAwake("mid_wake_cancel")
            return false
        }

        if (!DeviceStateHelper.isInteractive(context)) {
            Log.w(TAG, "still not interactive after settle; retry wake+overlay")
            DeviceStateHelper.wakeScreen(context)
            runOnMainSync {
                if (!isSessionLive(gen)) {
                    return@runOnMainSync
                }
                KeyguardWakeHelper.launchWakeOverlay(context)
                if (AccessibilityControlBridge.isReady()) {
                    val result = AccessibilityControlBridge.enableRemoteKeepAwake()
                    overlayOk.set(result.ok || overlayOk.get())
                    if (!result.ok) {
                        overlayError.set(result.error)
                    }
                }
            }
            Thread.sleep(SETTLE_MS)
            DeviceStateHelper.pokeUserActivity(context)
        }

        if (!isSessionLive(gen)) {
            forceStopKeepAwake("mid_wake_cancel")
            return false
        }

        startedByZombie = overlayOk.get() || startedByZombie
        val until = sessionUntilMs
        if (!overlayOk.get()) {
            if (!holdingUnlockWakeLock) {
                DeviceStateHelper.holdUnlockWakeLock(
                    context,
                    (until - System.currentTimeMillis()).coerceAtLeast(30_000L),
                )
                holdingUnlockWakeLock = true
            }
            Log.w(TAG, "overlay failed err=${overlayError.get()}; holdUnlockWakeLock fallback")
        }

        val interactiveAfter = DeviceStateHelper.isInteractive(context)
        Log.i(
            TAG,
            "wake done gen=$gen overlayOk=${overlayOk.get()} interactiveAfter=$interactiveAfter " +
                "keepActive=${AccessibilityControlBridge.isRemoteKeepAwakeActive()}",
        )
        return interactiveAfter || overlayOk.get()
    }

    fun cancel(reason: String = "cancel") {
        // 先作废世代，让飞行中的 performWake / rearm 全部退出
        sessionGen.set(0)
        handler.removeCallbacks(stopRunnable)
        handler.removeCallbacks(rearmRunnable)
        sessionUntilMs = 0L
        appliedFlagUntilMs = 0L
        broadcastSessionUntil = 0L
        sessionContext = null
        forceStopKeepAwake(reason)
        Log.i(TAG, "cancel reason=$reason")
    }

    private fun forceStopKeepAwake(reason: String) {
        if (holdingUnlockWakeLock) {
            DeviceStateHelper.releaseUnlockWakeLock()
            holdingUnlockWakeLock = false
        }
        startedByZombie = false
        // 不论是否由本模块启动，清除时都卸掉常亮层，避免残留
        if (AccessibilityControlBridge.isReady() &&
            AccessibilityControlBridge.isRemoteKeepAwakeActive()
        ) {
            AccessibilityControlBridge.disableRemoteKeepAwake()
            Log.i(TAG, "disabled keep-awake overlay ($reason)")
        }
    }

    private fun isSessionLive(gen: Int): Boolean {
        return gen != 0 && sessionGen.get() == gen && sessionUntilMs > System.currentTimeMillis()
    }

    private fun ensureRearmLoop() {
        handler.removeCallbacks(rearmRunnable)
        if (!isSessionActive()) {
            return
        }
        handler.postDelayed(rearmRunnable, REARM_INTERVAL_MS)
        Log.i(TAG, "rearm loop armed intervalMs=$REARM_INTERVAL_MS")
    }

    private fun scheduleStop(untilMs: Long) {
        handler.removeCallbacks(stopRunnable)
        val delay = (untilMs - System.currentTimeMillis()).coerceAtLeast(1_000L)
        handler.postDelayed(stopRunnable, delay)
    }

    private fun stopIfExpired(reason: String) {
        if (!isSessionActive()) {
            cancel(reason)
            return
        }
        if (System.currentTimeMillis() < sessionUntilMs) {
            scheduleStop(sessionUntilMs)
            return
        }
        cancel(reason)
    }

    private fun runOnMainSync(block: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            block()
            return
        }
        val done = CountDownLatch(1)
        val error = AtomicReference<Throwable?>(null)
        handler.post {
            try {
                block()
            } catch (t: Throwable) {
                error.set(t)
            } finally {
                done.countDown()
            }
        }
        if (!done.await(8, TimeUnit.SECONDS)) {
            Log.w(TAG, "runOnMainSync timeout")
        }
        error.get()?.let { throw it }
    }
}
