package com.vibelink.client.receiver

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.device.ZombieWakeController
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.service.ConnectionService
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ZombieWakeAlarm

/**
 * FCM 唤醒消息的统一落点。
 *
 * 两条入口共用：
 *  - [FcmWakeReceiver]：直接收原始 `com.google.android.c2dm.intent.RECEIVE` 广播
 *    （这是 Hiber 放行、能把冻结进程拽出来的那一步）；
 *  - [FirebaseWakeService]：`FirebaseMessagingService` 解析后的 data 消息。
 *
 * 解冻窗口只有 `frozenDelay=5000`（实测 5.1s），之后 Hiber 重新冻结。
 * 所以消息必须**自带** `wakeUntil`，不能回 HTTP 取参数——一次往返抖动就可能吃掉整个窗口。
 */
object FcmWakeHandler {
    private const val TAG = "ZombieWake"

    const val ACTION_C2DM_RECEIVE = "com.google.android.c2dm.intent.RECEIVE"
    /** FCM data 消息里携带的 flag 到期时刻（UTC ms） */
    const val EXTRA_WAKE_UNTIL = "wakeUntil"
    /** 可选：本机常亮时长，缺省走 ZombieWakeController 默认值 */
    const val EXTRA_KEEP_AWAKE_MS = "keepAwakeMs"

    /**
     * 去重窗口。同一条 FCM 消息会被**两个** receiver 收到：
     * 我们自己的 [FcmWakeReceiver] 和 Firebase 自带的 `FirebaseInstanceIdReceiver`
     * （两者都声明了 `com.google.android.c2dm.intent.RECEIVE`）。
     * 它们可能在两个 binder 线程上并发进入，不加锁会让 `apply()` 撞进 `sameFlag` 的竞态分支。
     */
    private const val DEDUPE_MS = 3_000L
    @Volatile
    private var lastHandledAt = 0L
    @Volatile
    private var lastHandledUntil = 0L

    fun handle(context: Context, wakeUntilRaw: Long, keepAwakeMsRaw: Long, source: String) {
        val appContext = context.applicationContext
        val wakeUntil = normalizeWakeUntil(wakeUntilRaw)
        val keepAwakeMs = keepAwakeMsRaw.takeIf { it > 0 } ?: 0L

        // 同一 flag 在极短时间内重复到达 → 直接吞掉（见 DEDUPE_MS 注释）
        if (wakeUntil > 0) {
            if (wakeUntil == lastHandledUntil &&
                SystemClock.elapsedRealtime() - lastHandledAt < DEDUPE_MS
            ) {
                Log.i(TAG, "c2dm duplicate ignored source=$source wakeUntil=$wakeUntil")
                return
            }
            lastHandledUntil = wakeUntil
            lastHandledAt = SystemClock.elapsedRealtime()
        }

        Log.i(
            TAG,
            "c2dm received source=$source wall=${System.currentTimeMillis()} " +
                "elapsed=${SystemClock.elapsedRealtime()} wakeUntil=$wakeUntil " +
                "keepAwakeMs=$keepAwakeMs serviceRunning=${ConnectionService.isRunning()}",
        )

        // 消息自带 flag 时直接点亮，省掉一次 HTTP 往返。
        // 注意：权威期必须在 apply 之前登记——apply 内部有 ~2s 的 settle/retry，
        // 期间并发的 poll 会先看到 needWake=false 并把会话 cancel 掉（实测必现）。
        if (wakeUntil > System.currentTimeMillis()) {
            ZombieWakeController.markBroadcastSession(wakeUntil)
            val ok = if (keepAwakeMs > 0) {
                ZombieWakeController.apply(appContext, wakeUntil, keepAwakeMs)
            } else {
                ZombieWakeController.apply(appContext, wakeUntil)
            }
            if (!ok) {
                ZombieWakeController.markBroadcastSession(0L)
            }
            Log.i(TAG, "c2dm apply direct ok=$ok")
        } else {
            // 没带 flag：退回到拉一次 HTTP（窗口更紧，但没有更好的选择）
            ZombieWakeAlarm.startPollService(appContext)
        }

        if (!ConnectionService.isRunning()) {
            if (DirectBoot.isUserUnlocked(appContext)) {
                ServiceStarter.tryStart(appContext)
            } else {
                ServiceStarter.tryStartDirectBoot(appContext)
            }
        }
        // 这条路径是唯一能在「进程被冻结」状态下被系统放行的入口。
        // 顺带补一次无障碍自愈：掉了的时候正是需要它的时候。
        if (DirectBoot.isUserUnlocked(appContext)) {
            AccessibilitySelfHeal.checkAndHealAsync(appContext, source = "FCM")
        }
        ZombieWakeAlarm.schedule(appContext)
    }

    /**
     * FCM data 消息的值**一律是字符串**（`getLongExtra` 会读到 0），
     * adb 的 `--el` 才是 long。两种都接受。
     *
     * 顺序必须是「先字符串后 long」：反过来在 data 消息上每次都会抛
     * `ClassCastException: String cannot be cast to Long`（框架内部捕获后返回 0），
     * 功能不受影响，但每次唤醒都会往 logcat 里刷一段栈，掩盖真正的异常。
     */
    fun readLong(intent: Intent, key: String): Long {
        val asString = intent.getStringExtra(key)?.trim()
        if (!asString.isNullOrEmpty()) {
            asString.toLongOrNull()?.let { return it }
        }
        return intent.getLongExtra(key, 0L)
    }

    /** 容忍服务端误发秒级时间戳（10 位）；毫秒（13 位）原样返回。 */
    private fun normalizeWakeUntil(raw: Long): Long = when {
        raw <= 0L -> 0L
        raw < 100_000_000_000L -> raw * 1000L
        else -> raw
    }
}
