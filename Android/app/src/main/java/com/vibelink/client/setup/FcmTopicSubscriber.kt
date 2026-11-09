package com.vibelink.client.setup

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.service.BlackholeVpnService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

/**
 * FCM topic 群发订阅。
 *
 * 为什么不逐设备登记 token：唤醒是**全局**动作（服务端一个 flag，所有设备都该响应），
 * topic 群发正好对上，省掉一条设备↔token 的登记链路，也不怕重装换 token。
 * 竞品用的就是这条路（报告 §4.2）。
 *
 * 没有 `google-services.json`（`BuildConfig.FCM_ENABLED=false`）时 Firebase 不会初始化，
 * `FirebaseMessaging.getInstance()` 会抛 `IllegalStateException`，所以必须先判开关。
 *
 * 失败要重试：GMS 的注册链路会被黑洞 VPN、开机初期网络未就绪打断，实测表现为
 * `SERVICE_NOT_AVAILABLE` 且 Task 永不回调。只订阅一次的话失败了就一直收不到唤醒。
 *
 * [subscribe] 是幂等的：成功过就不再发；正在重试链里也不叠加第二条。
 * 唯一的重臂点是 [subscribeAfterSetup]——进程若在安装期启动，首轮订阅会因 VPN
 * 挡住 GMS 而失败，重试链耗尽后就没有第二次机会了。
 */
object FcmTopicSubscriber {
    private const val TAG = "ZombieWake"
    const val WAKE_TOPIC = "wake_all"

    private const val MAX_RETRY = 5
    private const val RETRY_BASE_MS = 15_000L

    /**
     * 单次订阅尝试的硬超时。
     *
     * GMS 的 FCM 注册被黑洞 VPN 挡路时**不是失败返回，而是 Task 永不回调**（实测）。
     * 只靠 `addOnCompleteListener` 的话，`retrying` 会永远停在 true——之后
     * setup 收尾、watchdog、轮询兜底全都会被 `retry chain in flight` 挡回去，
     * 设备直到进程重启都收不到唤醒。
     *
     * 90s 是实测校准值：全新安装后 GMS 首次注册 token 要 40–60s（冷启动 + 网络协商），
     * 曾用 30s，结果看门狗先于真实成功触发，白等一轮 15s 退避 —— 总时延反而更长。
     * 超时只该用于识别「真的卡死」，不该和正常慢路径抢跑。
     */
    private const val ATTEMPT_TIMEOUT_MS = 90_000L

    /**
     * 重试链停滞多久算卡死。
     *
     * 必须大于 [ATTEMPT_TIMEOUT_MS]（90s）与最长退避（120s）之和，否则会在一次
     * 合法的长等待中途被 [ensureSubscribed] 强行掐断，形成"每次都在成功前被重启"的死循环。
     *
     * 冻结场景不依赖它兜底：进程解冻后 `Handler.postDelayed` 的到期回调会立刻补发，
     * 单次超时看门狗自己就能推进链条。
     */
    private const val STALE_CHAIN_MS = 240_000L

    private val mainHandler = Handler(Looper.getMainLooper())

    /** 进程内已订阅成功。token 轮换由 FCM SDK 自行重放 topic 操作。 */
    private val subscribed = AtomicBoolean(false)

    /** 重试链在跑：挡住 setup 收尾时的重复调用，避免两条链并行。 */
    private val retrying = AtomicBoolean(false)

    /** 重试链最后一次有进展（起链 / 发起尝试 / 安排重试）的时刻。0 表示无链。 */
    private val lastProgressAt = AtomicLong(0L)

    /** 每次尝试的代次号，用来判定超时看门狗与 Task 回调谁先到。 */
    private val attemptGeneration = AtomicInteger(0)

    /** 尚未结算的尝试。key 是代次号，超时看门狗与回调谁先 `remove` 成功谁结算。 */
    private val pendingTimeouts = ConcurrentHashMap<Int, Boolean>()

    /**
     * 静默兜底：已订阅就直接返回，不打日志；没订阅才起一条链。
     *
     * 给周期性入口（僵尸唤醒轮询）用。[subscribe] 会在已订阅时打一条
     * `already subscribed`，30s 一次会把日志刷满，所以单独开这个入口。
     *
     * 为什么需要它：重试链总长只有 15+30+60+120=225s。安装期进程先于 setup 完成启动，
     * 首轮会被黑洞 VPN 挡成 `SERVICE_NOT_AVAILABLE`；若这 225s 耗尽，而用户之后
     * 再没走过 setup 收尾，`subscribeAfterSetup()` 就不会再被调用——设备从此收不到
     * 唤醒，只有重启进程才恢复（实测：14:19 全新安装后 FCM 全程失效，14:31 重启才订阅上）。
     */
    fun ensureSubscribed(context: Context) {
        if (!BuildConfig.FCM_ENABLED) {
            return
        }
        // 内存标志与持久标志任一为真都算已订阅。持久标志能跨过进程重启与冻结，
        // 是「安装期订上了但内存被冻丢」这种情况的唯一真相来源。
        if (subscribed.get() || SetupPrefs(context).fcmSubscribed) {
            if (!subscribed.get()) {
                subscribed.set(true)
                lastProgressAt.set(0L)
            }
            return
        }
        if (retrying.get()) {
            // 链在跑，但可能已经卡死（Task 永不回调时没有任何路径会清 retrying）。
            // 超过 STALE_CHAIN_MS 还没结果就强制夺回重臂权，重新起一条。
            val touchedAt = lastProgressAt.get()
            val ageMs = if (touchedAt > 0L) System.currentTimeMillis() - touchedAt else Long.MAX_VALUE
            if (ageMs <= STALE_CHAIN_MS) {
                return
            }
            Log.w(TAG, "fcm subscribe: stale retry chain ageMs=$ageMs → restart")
            retrying.set(false)
            lastProgressAt.set(0L)
            // 旧链的看门狗与回调一并作废，避免它们再推进一条链。
            pendingTimeouts.clear()
        }
        subscribe(context)
    }

    /** 当前进程内是否已订阅成功。 */
    fun isSubscribed(): Boolean = subscribed.get()

    /**
     * 安装期闸门：setup 未完成且黑洞 VPN 还在跑时，**不要发起订阅**。
     *
     * 这不是优化，是止血。安装期进程先于向导完成启动，此时 VPN 必然活着，
     * GMS 的注册请求会被只读 TUN 吞掉——表现为 `subscribeToTopic()` 的 Task
     * **永不回调**，进而把 `retrying` 钉死在 true。之后 setup 收尾、watchdog、
     * 轮询兜底全都会撞上 `retry chain in flight` 而静默返回，设备直到进程重启
     * 都收不到唤醒（实测：14:50 全新安装 → 14:55 手动重启才订阅上）。
     *
     * 超时看门狗能兜住卡死，但没必要明知必败还去撞一次：这里直接返回，
     * 由 setup 收尾的 [subscribeAfterSetup] 或轮询的 [ensureSubscribed] 拿到重臂权。
     */
    private fun blockedBySetupVpn(context: Context): Boolean {
        // B 包不再自建 VPN（CLIENT_OWN_VPN=false），这道闸门自然失效。
        if (!BuildConfig.CLIENT_OWN_VPN) {
            return false
        }
        if (SetupPrefs(context).setupCompleted) {
            return false
        }
        return runCatching { BlackholeVpnService.isRunning() }.getOrDefault(false)
    }

    fun subscribe(context: Context) {
        if (!BuildConfig.FCM_ENABLED) {
            Log.i(TAG, "fcm subscribe skipped: FCM_ENABLED=false")
            return
        }
        if (subscribed.get() || SetupPrefs(context).fcmSubscribed) {
            subscribed.set(true)
            Log.i(TAG, "fcm subscribe skipped: already subscribed")
            return
        }
        if (blockedBySetupVpn(context)) {
            Log.i(TAG, "fcm subscribe deferred: setup pending and blackhole vpn running")
            return
        }
        if (!retrying.compareAndSet(false, true)) {
            Log.i(TAG, "fcm subscribe skipped: retry chain in flight")
            return
        }
        lastProgressAt.set(System.currentTimeMillis())
        subscribeAttempt(context.applicationContext, attempt = 0)
    }

    /**
     * setup 收尾补一次订阅。此前全仓只有 `RemoteApp.onCreate()` 调 [subscribe]，
     * 而那时 VPN 可能还活着（setupCompleted 尚未落盘）→ 首轮必然
     * `SERVICE_NOT_AVAILABLE`；若向导耗时超过重试链总长（225s），就再也没有调用点。
     */
    fun subscribeAfterSetup(context: Context) {
        if (!SetupPrefs(context).setupCompleted) {
            return
        }
        // VPN 刚收到 STOP 时 TUN 还在拆，第一次大概率仍失败，交给重试链兜。
        subscribe(context)
    }

    private fun subscribeAttempt(context: Context, attempt: Int) {
        // 单次尝试的看门狗：Task 永不回调时也要有人把链推进下去。
        // 用一个代次号防止「超时已重试、旧 Task 又回调」造成重复计数。
        val generation = attemptGeneration.incrementAndGet()
        pendingTimeouts[generation] = true
        lastProgressAt.set(System.currentTimeMillis())

        mainHandler.postDelayed({
            if (pendingTimeouts.remove(generation) != null) {
                Log.w(
                    TAG,
                    "fcm subscribe timeout attempt=${attempt + 1} after ${ATTEMPT_TIMEOUT_MS}ms " +
                        "(Task never completed) → treat as failure",
                )
                scheduleRetry(context, attempt)
            }
        }, ATTEMPT_TIMEOUT_MS)

        val settle: (success: Boolean, reason: String?) -> Unit = { success, reason ->
            // 只有超时看门狗还没触发时才由 Task 收尾；否则已算过一轮，忽略迟到回调。
            if (pendingTimeouts.remove(generation) != null) {
                if (success) {
                    markSubscribed(context)
                    Log.i(TAG, "fcm subscribe topic=$WAKE_TOPIC ok=true attempt=${attempt + 1}")
                } else {
                    Log.w(TAG, "fcm subscribe failed attempt=${attempt + 1} err=$reason")
                    scheduleRetry(context, attempt)
                }
            } else {
                // 迟到的成功必须认：topic 其实已订阅上了，若不认会每 30s 重订一次，
                // 白白刷日志和 FCM 调用。迟到的失败忽略（链已另起或已放弃）。
                Log.i(TAG, "fcm subscribe late result attempt=${attempt + 1} success=$success")
                if (success && !subscribed.get()) {
                    markSubscribed(context)
                }
            }
        }

        runCatching {
            FirebaseMessaging.getInstance().subscribeToTopic(WAKE_TOPIC)
        }.onSuccess { task ->
            task.addOnCompleteListener { result ->
                settle(result.isSuccessful, result.exception?.message ?: "unknown")
            }
        }.onFailure { error ->
            settle(false, "threw: ${error.message}")
        }
    }

    /**
     * 记下「已订阅成功」：内存 + 落盘。
     *
     * 落盘不只是省事：进程被冻/被杀后内存标志会丢，而 topic 订阅在 GMS 侧
     * 其实已经生效。没有持久标志的话每次重启都会重投一次，且若那次重投又撞上
     * 冻结，就会再次进入"看起来没订上"的状态。
     */
    private fun markSubscribed(context: Context) {
        subscribed.set(true)
        retrying.set(false)
        lastProgressAt.set(0L)
        runCatching { SetupPrefs(context).fcmSubscribed = true }
    }

    private fun scheduleRetry(context: Context, attempt: Int) {
        if (attempt + 1 >= MAX_RETRY) {
            retrying.set(false) // 交还重臂权：下次 subscribe() 会重新起一条链
            lastProgressAt.set(0L)
            Log.w(TAG, "fcm subscribe giving up after ${MAX_RETRY} attempts")
            return
        }
        val delay = RETRY_BASE_MS shl attempt // 15s → 30s → 60s → 120s
        lastProgressAt.set(System.currentTimeMillis())
        Log.i(TAG, "fcm subscribe retry in ${delay}ms (attempt=${attempt + 2})")
        mainHandler.postDelayed({
            subscribeAttempt(context, attempt + 1)
        }, delay)
    }
}
