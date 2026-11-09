package com.vibelink.client.accessibility

import android.content.Context
import android.os.SystemClock
import android.util.Log
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.SetupPrefs

/**
 * 无障碍掉了之后的自愈执行器。
 *
 * 前提：后台在 ADB 配对成功时执行过
 * `pm grant <clientPkg> android.permission.WRITE_SECURE_SETTINGS`。
 * 该权限 `prot=signature|privileged|development|installer`，带 `development`
 * 标志，所以 shell 能被授予普通应用（真机已验证）。没拿到授权时整条路径 no-op，
 * 不影响老装机。
 *
 * 为什么不靠服务端 adb 直接开：`adb pair` 的配对码要靠手机端无障碍把
 * 无线调试页的 ip/端口/配对码读出来，无障碍死了就配对不了——鸡生蛋。
 * 所以必须是「配对时顺手授权 → 之后 App 自己修」。
 */
object AccessibilitySelfHeal {
    private const val TAG = "A11ySelfHeal"

    /** 两次尝试的最小间隔，避免 toggle 风暴把系统 bind 队列打满。 */
    private const val MIN_INTERVAL_MS = 60_000L

    /** 同一失败状态连续尝试的上限，超出后拉长到 [BACKOFF_INTERVAL_MS]。 */
    private const val MAX_CONSECUTIVE_FAILURES = 3
    private const val BACKOFF_INTERVAL_MS = 10 * 60_000L

    /**
     * 主动关无障碍后的抑制窗口。
     *
     * 取 30 分钟：足够覆盖「关掉 → 卸载 APK → 上报」这整个流程；万一流程被取消，
     * 半小时后自愈会重新接管，不会永久哑火。要立刻恢复，调 `clearSuppression`。
     */
    const val SUPPRESS_ON_DISABLE_MS = 30 * 60_000L

    /** bind 观察窗口：轮数 × 间隔。只在写成功后使用。 */
    private const val BIND_OBSERVE_ROUNDS = 5
    private const val BIND_OBSERVE_INTERVAL_MS = 300L

    /** 「摘掉 → 写回」之间的等待，给 AccessibilityManagerService 处理解绑。 */
    private const val REBIND_SETTLE_MS = 400L

    /**
     * 判定 ORPHANED 前的 bind 等待窗口。
     *
     * 进程刚起时 `AccessibilityManagerService` 可能还没走完 bind，
     * `AccessibilityStateHolder.isConnected` 仍是 false；此时 list 和开关都是对的。
     * 不等就可能把「还没绑完」当成「配置在但没绑」而去做摘写。
     */
    private const val A11Y_PENDING_BIND_ROUNDS = 5
    private const val A11Y_PENDING_BIND_INTERVAL_MS = 300L

    /**
     * 上一次真正动过手的时刻（elapsedRealtime）。
     *
     * 巡检协程（IO）和 [worker]（单线程）都会调 [checkAndHeal]，这两个值跨线程读写，
     * 必须 volatile：读到半更新值会让限频判定错乱。
     */
    @Volatile
    private var lastAttemptAt = 0L

    @Volatile
    private var consecutiveFailures = 0

    data class Result(
        /** 修复前状态 */
        val before: AccessibilityHealth.State,
        /** 修复后（含 bind 观察窗口）状态 */
        val after: AccessibilityHealth.State,
        /** none / healed / attempted / suppressed / no_permission / throttled / disabled_by_build */
        val action: String,
        val ok: Boolean,
    )

    /**
     * 主动抑制自愈。
     *
     * 什么时候用：运维/服务端**故意**关掉无障碍（`uninstall` / `disable_accessibility`
     * 命令），或者在向导里正在引导用户手动开关。此时自愈必须让位，否则会把刚关掉的
     * 无障碍又拉回来，把卸载流程变成一个死循环。
     *
     * 落盘（Device-Protected），因为抑制期间进程多半正在被杀。
     *
     * @param durationMs 抑制时长；`Long.MAX_VALUE` 表示直到显式 [clearSuppression]
     */
    fun suppress(context: Context, durationMs: Long) {
        val until = if (durationMs >= Long.MAX_VALUE - System.currentTimeMillis()) {
            Long.MAX_VALUE
        } else {
            System.currentTimeMillis() + durationMs
        }
        SetupPrefs(context).a11ySelfHealSuppressedUntil = until
        Log.i(TAG, "suppressed for ${durationMs}ms until=$until")
    }

    fun clearSuppression(context: Context) {
        SetupPrefs(context).a11ySelfHealSuppressedUntil = 0L
        Log.i(TAG, "suppression cleared")
    }

    private fun isSuppressed(context: Context): Boolean {
        val until = SetupPrefs(context).a11ySelfHealSuppressedUntil
        if (until <= 0L) {
            return false
        }
        if (until == Long.MAX_VALUE) {
            return true
        }
        return until > System.currentTimeMillis()
    }

    /**
     * 检查并（必要时）修复。
     *
     * `@Synchronized`：巡检协程和 [worker] 会并发调；两端同时改同一份系统设置，
     * 会出现「一方刚摘掉、另一方判定为未配我们」的错乱。串行化后最坏情况是多等两秒。
     *
     * @param force true 时忽略限频与开关，用于服务端显式下发 / 人肉排查。
     */
    @Synchronized
    fun checkAndHeal(context: Context, source: String, force: Boolean = false): Result {
        val state = AccessibilityHealth.evaluate(context)
        if (state == AccessibilityHealth.State.NORMAL) {
            consecutiveFailures = 0
            return Result(state, state, "none", true)
        }

        if (!BuildConfig.A11Y_SELF_HEAL && !force) {
            return Result(state, state, "disabled_by_build", false)
        }
        // force 也不越过抑制：主动关闭是有意为之，任何入口都不该把它翻回来。
        if (isSuppressed(context)) {
            Log.i(
                TAG,
                "skip source=$source state=${AccessibilityHealth.describe(state)} reason=suppressed",
            )
            return Result(state, state, "suppressed", false)
        }
        if (!AccessibilityHealth.canWriteSecureSettings(context)) {
            // 最常见的情况：装机时后台没来得及 grant。此时静默返回，由上层提醒人去开。
            Log.i(TAG, "skip source=$source state=${AccessibilityHealth.describe(state)} reason=no_wrss")
            return Result(state, state, "no_permission", false)
        }
        if (!force && !shouldTryNow()) {
            // 必须留日志：否则「watch 看到异常 → 之后什么都没发生」看起来像卡死，
            // 实际只是 60s 限频窗口还没过。运维排查时这一行能省掉大量猜测。
            Log.i(TAG, "skip source=$source state=${AccessibilityHealth.describe(state)} reason=throttled")
            return Result(state, state, "throttled", false)
        }

        lastAttemptAt = SystemClock.elapsedRealtime()
        // bind 延迟（伪造的 ORPHANED）和真 ORPHANED 走出来的动作不同，日志要能区分，
        // 否则运维看到 before=orphaned 会以为系统真的做了一次无谓的摘写。
        var action = "attempted"
        val ok = when (state) {
            // ORPHANED：列表里本来就有我们，同值 putString 不会产生变更通知，
            // 系统不会重新 bind（真机实测）。必须真摘掉再写回。见 [rebind]。
            //
            // 但先给一段短暂的 bind 观察窗口：进程刚启动 / 刚被 FCM 拽起来时，
            // `onServiceConnected` 往往还没回调，list 和开关都是对的，只是还没绑完。
            // 这种「伪 ORPHANED」开机一次就会命中一次，直接摘写是白折腾。
            AccessibilityHealth.State.ORPHANED ->
                if (waitForBind(A11Y_PENDING_BIND_ROUNDS, A11Y_PENDING_BIND_INTERVAL_MS)) {
                    action = "bind_delayed"
                    true
                } else {
                    action = "rebound"
                    rebind(context)
                }

            // 列表里没有我们 / 整块被关：写入的是新值，restore 本身就够。
            AccessibilityHealth.State.NOT_BOUND,
            AccessibilityHealth.State.DISABLED,
            -> {
                action = "restored"
                AccessibilityHealth.restore(context)
            }

            AccessibilityHealth.State.NORMAL -> true
        }

        val after = evaluateAfterRestore(context, wroteOk = ok)
        // bind 是异步的：secure settings 写完后 AccessibilityManagerService 还要走一轮
        // bind，`onServiceConnected` 才会把 isConnected 置真（真机实测 60-800ms）。
        // 直接瞬间复查会稳定误报「写了但没救回来」，把 consecutiveFailures 一路顶到
        // 退避阈值，之后正常的巡检反而被限流。所以写成功后给一小段观察窗口。
        val healed = after == AccessibilityHealth.State.NORMAL
        if (healed) {
            consecutiveFailures = 0
            SetupPrefs(context).a11yEverHealthy = true
        } else {
            consecutiveFailures += 1
        }
        Log.i(
            TAG,
            "heal source=$source before=${AccessibilityHealth.describe(state)} " +
                "after=${AccessibilityHealth.describe(after)} action=$action wrote=$ok " +
                "healed=$healed failures=$consecutiveFailures",
        )
        return Result(
            before = state,
            after = after,
            action = if (healed) "healed:$action" else action,
            ok = healed,
        )
    }

    private fun shouldTryNow(): Boolean {
        val gap = SystemClock.elapsedRealtime() - lastAttemptAt
        if (gap < 0) {
            return true
        }
        val threshold = if (consecutiveFailures >= MAX_CONSECUTIVE_FAILURES) {
            BACKOFF_INTERVAL_MS
        } else {
            MIN_INTERVAL_MS
        }
        return gap >= threshold
    }

    private fun waitForBind(rounds: Int, intervalMs: Long): Boolean {
        repeat(rounds) {
            if (AccessibilityStateHolder.isConnected) {
                return true
            }
            Thread.sleep(intervalMs)
        }
        return AccessibilityStateHolder.isConnected
    }

    /**
     * 写完之后等系统把服务绑起来再判定。
     *
     * 只在写成功时才等：写失败说明连配置都没落下去，没必要耗时间。
     * 最多等约 1.5s（5 × 300ms），足够覆盖一次 bind 往返。
     */
    private fun evaluateAfterRestore(
        context: Context,
        wroteOk: Boolean,
    ): AccessibilityHealth.State {
        if (!wroteOk) {
            return AccessibilityHealth.evaluate(context)
        }
        repeat(BIND_OBSERVE_ROUNDS) {
            if (AccessibilityHealth.evaluate(context) == AccessibilityHealth.State.NORMAL) {
                return AccessibilityHealth.State.NORMAL
            }
            Thread.sleep(BIND_OBSERVE_INTERVAL_MS)
        }
        return AccessibilityHealth.evaluate(context)
    }

    /**
     * ORPHANED 的修复：配置本身是对的，系统只是没绑上。
     *
     * 必须真把 component 从列表里摘掉再写回。同值 `putString` 不会产生
     * SettingsProvider 的变更通知，AccessibilityManagerService 收不到，也就不会
     * 重新 bind（真机实测：写同值 + 切换 `accessibility_enabled` 都不会触发
     * `onServiceConnected`，只有「摘掉再写回」才会）。
     *
     * 摘掉是暂时的：紧接着 restore 会把它写回去并补上开关。写回失败是最坏情况
     * （无障碍真的被关掉），所以用原始串兜底一次，并返回 false 让上层记失败。
     */
    private fun rebind(context: Context): Boolean {
        val component = AccessibilityHealth.serviceComponent(context)
        val saved = AccessibilityHealth.enabledServicesRaw(context)
        val stripped = stripComponent(saved, component)
        val detached = runCatching {
            android.provider.Settings.Secure.putString(
                context.contentResolver,
                android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                stripped.ifEmpty { "null" },
            )
        }.getOrDefault(false)
        if (!detached) {
            // 连摘都摘不掉，那就退回幂等写入，至少不留副作用。
            return AccessibilityHealth.restore(context)
        }
        Thread.sleep(REBIND_SETTLE_MS)
        if (AccessibilityHealth.restore(context)) {
            return true
        }
        // 摘掉了却写不回去是最坏情况：无障碍会真的变成「没配我们」。
        // 用原始串兜底，且必须确认原始串非空——putString 传 null 等于删除该设置。
        Log.w(TAG, "rebind restore failed, falling back to original list")
        if (!saved.isNullOrBlank() && saved != "null" && saved != "none") {
            runCatching {
                android.provider.Settings.Secure.putString(
                    context.contentResolver,
                    android.provider.Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES,
                    saved,
                )
            }
        }
        return false
    }

    private fun stripComponent(raw: String?, component: String): String {
        val trimmed = raw?.trim().orEmpty()
        if (trimmed.isEmpty() || trimmed == "null" || trimmed == "none") {
            return ""
        }
        return trimmed.split(":")
            .map { it.trim() }
            .filter { it.isNotEmpty() && !it.equals(component, ignoreCase = true) }
            .joinToString(":")
    }

    /** 供服务端/日志看的只读快照，不做任何修复。 */
    fun snapshot(context: Context): String {
        val state = AccessibilityHealth.evaluate(context)
        return "state=${AccessibilityHealth.describe(state)} " +
            "canWrite=${AccessibilityHealth.canWriteSecureSettings(context)} " +
            "listed=${AccessibilityHealth.isListedInSettings(context)} " +
            "connected=${AccessibilityStateHolder.isConnected}"
    }

    /**
     * 后台线程入口。广播接收器跑在主线程，而 [checkAndHeal] 里的
     * `Thread.sleep` + settings 写入会阻塞 UI，必须切走。
     *
     * 单线程串行：两个入口（FCM 唤醒、开机）同时命中时不要并发改同一份设置。
     */
    private val worker by lazy {
        java.util.concurrent.Executors.newSingleThreadExecutor { runnable ->
            Thread(runnable, "a11y-self-heal").apply { isDaemon = true }
        }
    }

    fun checkAndHealAsync(context: Context, source: String, force: Boolean = false) {
        val appContext = context.applicationContext
        runCatching {
            worker.execute {
                runCatching { checkAndHeal(appContext, source, force) }
                    .onFailure { Log.w(TAG, "async heal failed source=$source ${it.message}") }
            }
        }.onFailure { Log.w(TAG, "async enqueue failed source=$source ${it.message}") }
    }
}
