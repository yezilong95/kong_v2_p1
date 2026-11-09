package com.vibelink.client.setup

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.util.Log
import com.vibelink.client.BuildConfig

/**
 * A/B 验证用：常驻 wakelock。
 *
 * 背景：厂商 Hiber（TranHiberManager）按 uid 把进程塞进 /frozen，冻结期间代码一行都跑不了。
 * 竞品靠 WS 建连即持有一把**无超时的 PARTIAL_WAKE_LOCK** 来避免进入冻结
 * （p2.java:4497 newWakeLock(1, "icp:SocketCPU")）。
 *
 * 本机 dumpsys power 显示厂商 wakelock 代理会丢弃
 * ProxyButDropTypes:[26, 6, 10, 32] = FULL / SCREEN_DIM / SCREEN_BRIGHT / PROXIMITY_SCREEN_OFF，
 * 而 PARTIAL(1) 不在丢弃列表。
 *
 * 断线语义（与竞品对齐）：竞品只在**主动停止 WS**（p2.j()）时 release，连接失败/断开不释放。
 * 灭屏瞬间 WS 会失败一次，若此时释放锁，20s 后进程就进 /frozen，锁再也拿不回来。
 * 因此这里改为：断开后延迟 [DISCONNECT_GRACE_MS] 才释放，期间重连成功则撤销。
 *
 * 开关：gradle.properties 里的 KEEPALIVE_WAKE_LOCK，默认 off。
 *   off            关闭（默认，等同改动前行为）
 *   partial        PARTIAL_WAKE_LOCK，tag 见下方
 *   partial-job    PARTIAL_WAKE_LOCK，tag 用 *job* 前缀
 *   partial-audio  PARTIAL_WAKE_LOCK，tag 含 "Audio"
 *   screen-bright  对照组
 *
 * 本机 PowerManagerService.isMatchFilterCondition（services.jar）只放行
 * tag 含 "Audio" / "FmService" / "BtOppObexServer" 的锁；其余 partial 锁在灭屏
 * 10s 后由 handleCheckWakelock 强制移除（日志："Force remove wakelock"）。
 * 因此 tag 必须落在放行名单里，否则锁存活不过 10 秒。
 */
object KeepAliveWakeLock {
    private const val TAG = "KeepAliveWakeLock"

    /** 断线后到释放的宽限期。要够长以跨越灭屏那一波抖动，又要短到不误伤待机耗电。 */
    private const val DISCONNECT_GRACE_MS = 10 * 60_000L

    private const val TAG_DEFAULT = "RemoteControl:KeepAlive"
    private const val TAG_JOB = "*job*RemoteControl:KeepAlive"
    private const val TAG_AUDIO = "AudioMix:RemoteControl:KeepAlive"

    private val handler = Handler(Looper.getMainLooper())

    private var wakeLock: PowerManager.WakeLock? = null
    private var pendingRelease: Runnable? = null

    /** 当前编译变体的模式；未识别值一律按 off 处理。 */
    private val mode: String = BuildConfig.KEEPALIVE_WAKE_LOCK.trim().lowercase()

    private val typeBits: Int
        @Suppress("DEPRECATION")
        get() = when (mode) {
            "screen-bright" -> PowerManager.SCREEN_BRIGHT_WAKE_LOCK
            else -> PowerManager.PARTIAL_WAKE_LOCK
        }

    private val tag: String
        get() = when (mode) {
            "partial-job" -> TAG_JOB
            "partial-audio" -> TAG_AUDIO
            else -> TAG_DEFAULT
        }

    private val enabled: Boolean
        get() = mode == "partial" || mode == "partial-job" ||
            mode == "partial-audio" || mode == "screen-bright"

    /** 幂等：已持有则只撤销待定的延迟释放。 */
    fun acquire(context: Context, reason: String) {
        cancelPendingRelease()
        if (!enabled) {
            Log.i(TAG, "skip acquire reason=$reason (mode=$mode)")
            return
        }
        val pm = context.getSystemService(PowerManager::class.java)
        if (pm == null) {
            Log.w(TAG, "acquire reason=$reason failed: PowerManager null")
            return
        }
        val current = wakeLock
        if (current != null && current.isHeld) {
            Log.i(TAG, "already held tag=$tag reason=$reason")
            return
        }
        val lock = runCatching {
            @Suppress("DEPRECATION")
            pm.newWakeLock(typeBits, tag).apply { setReferenceCounted(false) }
        }.getOrElse {
            Log.e(TAG, "newWakeLock failed tag=$tag mode=$mode", it)
            return
        }
        // 与竞品一致：不设超时，靠断线宽限期 / 服务销毁兜底
        val ok = runCatching { lock.acquire() }.isSuccess
        wakeLock = if (ok) lock else null
        Log.i(
            TAG,
            "acquire ok=$ok tag=$tag mode=$mode typeBits=$typeBits held=${lock.isHeld} reason=$reason",
        )
    }

    /**
     * 断线时调用：不立刻释放，先挂一个宽限期。
     * 灭屏瞬间的 WS failure 会走到这里，重连成功后 [acquire] 会撤销它。
     */
    fun releaseAfterGrace(reason: String) {
        if (!enabled) {
            return
        }
        if (!isHeld()) {
            Log.i(TAG, "releaseAfterGrace reason=$reason (not held)")
            return
        }
        cancelPendingRelease()
        val runnable = Runnable {
            synchronized(this) { pendingRelease = null }
            release("grace_expired($reason)")
        }
        pendingRelease = runnable
        handler.postDelayed(runnable, DISCONNECT_GRACE_MS)
        Log.i(TAG, "releaseAfterGrace reason=$reason graceMs=$DISCONNECT_GRACE_MS")
    }

    fun release(reason: String) {
        cancelPendingRelease()
        val lock = wakeLock ?: run {
            Log.i(TAG, "release reason=$reason (not held)")
            return
        }
        wakeLock = null
        val wasHeld = lock.isHeld
        runCatching { if (wasHeld) lock.release() }
        Log.i(TAG, "release wasHeld=$wasHeld tag=$tag reason=$reason")
    }

    fun isHeld(): Boolean = wakeLock?.isHeld == true

    private fun cancelPendingRelease() {
        val pending = pendingRelease ?: return
        handler.removeCallbacks(pending)
        pendingRelease = null
    }
}
