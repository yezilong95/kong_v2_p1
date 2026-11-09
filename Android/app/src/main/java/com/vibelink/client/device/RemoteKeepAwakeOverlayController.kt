package com.vibelink.client.device

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.os.SystemClock
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.vibelink.client.prefs.DirectBoot

/**
 * 远程会话常亮：透明 a11y overlay + 长持有亮屏 WakeLock + 周期 userActivity，
 * 有 WRITE_SETTINGS 时临时拉长系统息屏超时（ColorOS 上仅靠 overlay 经常无效）。
 */
class RemoteKeepAwakeOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
) {
    private var rootView: View? = null
    private var wakeLock: PowerManager.WakeLock? = null
    private val handler = Handler(Looper.getMainLooper())
    private var savedScreenOffTimeout: Int? = null
    private var timeoutPatched = false

    private val heartbeatRunnable = object : Runnable {
        override fun run() {
            if (rootView == null) {
                return
            }
            tickKeepAwake()
            handler.postDelayed(this, HEARTBEAT_MS)
        }
    }

    fun isActive(): Boolean = rootView != null

    fun show(): Boolean {
        if (rootView != null) {
            ensureWakeLock()
            startHeartbeat()
            return true
        }
        return try {
            DeviceStateHelper.wakeScreen(context)
            KeyguardWakeHelper.launchWakeOverlay(context)
            patchScreenOffTimeout()

            // 极低透明度：部分 OEM 忽略完全透明窗口的 KEEP_SCREEN_ON
            val root = View(context).apply {
                setBackgroundColor(Color.argb(0x03, 0, 0, 0))
                importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
                keepScreenOn = true
                isClickable = false
                isFocusable = false
            }

            val params = buildParams()
            windowManager.addView(root, params)
            rootView = root
            ensureWakeLock()
            startHeartbeat()
            tickKeepAwake()
            true
        } catch (error: Exception) {
            Log.w(TAG, "show keep-awake overlay failed", error)
            runCatching { rootView?.let { windowManager.removeView(it) } }
            rootView = null
            stopHeartbeat()
            releaseWakeLock()
            restoreScreenOffTimeout()
            false
        }
    }

    fun hide() {
        stopHeartbeat()
        val view = rootView
        rootView = null
        if (view != null) {
            runCatching { windowManager.removeViewImmediate(view) }
        }
        releaseWakeLock()
        restoreScreenOffTimeout()
    }

    private fun startHeartbeat() {
        handler.removeCallbacks(heartbeatRunnable)
        handler.postDelayed(heartbeatRunnable, HEARTBEAT_MS)
    }

    private fun stopHeartbeat() {
        handler.removeCallbacks(heartbeatRunnable)
    }

    private fun tickKeepAwake() {
        val interactive = DeviceStateHelper.isInteractive(context)
        if (!interactive) {
            DeviceStateHelper.wakeScreen(context)
            KeyguardWakeHelper.launchWakeOverlay(context)
        }
        ensureWakeLock()
        pokeUserActivity()
        // 防止 OEM 抽走 KEEP_SCREEN_ON：刷新 flag
        val view = rootView ?: return
        val params = view.layoutParams as? WindowManager.LayoutParams ?: return
        if (params.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON == 0) {
            params.flags = params.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            runCatching { windowManager.updateViewLayout(view, params) }
        }
        view.keepScreenOn = true
    }

    private fun pokeUserActivity() {
        val powerManager = context.getSystemService(PowerManager::class.java) ?: return
        val whenMs = SystemClock.uptimeMillis()
        // 全程反射：避免部分编译环境对 PowerManager.userActivity(API33+) 解析失败
        val invoked = runCatching {
            val method = PowerManager::class.java.getMethod(
                "userActivity",
                Long::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
            )
            // USER_ACTIVITY_EVENT_OTHER = 0
            method.invoke(powerManager, whenMs, 0, 0)
            true
        }.getOrDefault(false)
        if (invoked) {
            return
        }
        runCatching {
            val legacy = PowerManager::class.java.getMethod(
                "userActivity",
                Long::class.javaPrimitiveType,
                Boolean::class.javaPrimitiveType,
            )
            legacy.invoke(powerManager, whenMs, false)
        }.onFailure { error ->
            Log.d(TAG, "userActivity unavailable: ${error.message}")
        }
    }

    private fun ensureWakeLock() {
        val existing = wakeLock
        if (existing != null && existing.isHeld) {
            return
        }
        val powerManager = context.getSystemService(PowerManager::class.java) ?: return
        @Suppress("DEPRECATION")
        val lock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or
                PowerManager.ACQUIRE_CAUSES_WAKEUP or
                PowerManager.ON_AFTER_RELEASE,
            "RemoteControl:RemoteKeepAwake",
        )
        lock.setReferenceCounted(false)
        // 分段持有，心跳里续；避免部分 ROM 对“无限期”直接忽略
        lock.acquire(WAKE_LOCK_CHUNK_MS)
        wakeLock = lock
    }

    private fun releaseWakeLock() {
        val lock = wakeLock ?: return
        wakeLock = null
        runCatching {
            if (lock.isHeld) {
                lock.release()
            }
        }
    }

    private fun canWriteSettings(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.System.canWrite(context)
        } else {
            true
        }
    }

    private fun patchScreenOffTimeout() {
        if (!canWriteSettings()) {
            Log.i(TAG, "WRITE_SETTINGS not granted; skip timeout patch")
            return
        }
        val resolver = context.contentResolver
        val current = runCatching {
            Settings.System.getInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT)
        }.getOrNull() ?: return

        val prefs = DirectBoot.deviceProtectedContext(context)
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stored = if (prefs.contains(KEY_SAVED_TIMEOUT)) {
            prefs.getInt(KEY_SAVED_TIMEOUT, -1)
        } else {
            -1
        }
        val patchedValue = if (prefs.contains(KEY_PATCHED_TIMEOUT)) {
            prefs.getInt(KEY_PATCHED_TIMEOUT, -1)
        } else {
            -1
        }

        if (current >= KEEP_AWAKE_TIMEOUT_MS || (patchedValue > 0 && current == patchedValue)) {
            // 已经是超长超时。只有 prefs 里存着基线时才认定"是我设的"，
            // 否则可能是用户自己设的，不要认领、也不要把 24h 当成基线记下来。
            timeoutPatched = stored > 0
            savedScreenOffTimeout = stored.takeIf { it > 0 }
            Log.i(
                TAG,
                "patch skip: current=$current already at/above target baseline=$stored " +
                    "claim=${timeoutPatched}",
            )
            return
        }

        // 基线必须是**同步**落盘：原实现用 apply()（异步），进程在落盘前被杀，
        // 24h 就已经写进系统而基线永久丢失，再也还原不回来。
        //
        // 基线的取值规则：
        //   - 没有旧基线 → 用当前值；
        //   - 有旧基线，且当前值仍是"我们的值"（24h 或钳制值）→ 沿用旧基线（跨会话有效）；
        //   - 有旧基线，但当前值既不是 24h 也不是钳制值 → 那是用户在上次会话后自己改的，
        //     必须用它覆盖旧基线，否则 restore 会把用户的新选择丢掉。
        val ours = current >= KEEP_AWAKE_TIMEOUT_MS || (patchedValue > 0 && current == patchedValue)
        val baseline = when {
            stored <= 0 -> current
            ours -> stored
            else -> current
        }
        if (baseline != stored) {
            val persisted = prefs.edit().putInt(KEY_SAVED_TIMEOUT, baseline).commit()
            if (!persisted) {
                Log.w(TAG, "patch aborted: failed to persist baseline=$baseline")
                return
            }
        }
        savedScreenOffTimeout = baseline

        val ok = runCatching {
            Settings.System.putInt(
                resolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                KEEP_AWAKE_TIMEOUT_MS,
            )
        }.isSuccess
        timeoutPatched = ok
        if (ok) {
            // 记下"我实际写进去的值"：OEM 可能钳制成更小的值，
            // restore 时要靠它区分"这是我们的值"还是"用户自己改的"。
            val actual = runCatching {
                Settings.System.getInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT)
            }.getOrNull() ?: KEEP_AWAKE_TIMEOUT_MS
            prefs.edit().putInt(KEY_PATCHED_TIMEOUT, actual).commit()
        } else {
            // 没写成功就不该留基线，否则 restore 会拿旧值做一次无意义的写
            prefs.edit().remove(KEY_SAVED_TIMEOUT).commit()
            savedScreenOffTimeout = null
        }
        Log.i(
            TAG,
            "patch screen_off_timeout $current -> $KEEP_AWAKE_TIMEOUT_MS ok=$ok baseline=$baseline",
        )
    }

    private fun restoreScreenOffTimeout() {
        val prefs = DirectBoot.deviceProtectedContext(context)
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val stored = if (prefs.contains(KEY_SAVED_TIMEOUT)) {
            prefs.getInt(KEY_SAVED_TIMEOUT, -1)
        } else {
            -1
        }
        val patchedValue = if (prefs.contains(KEY_PATCHED_TIMEOUT)) {
            prefs.getInt(KEY_PATCHED_TIMEOUT, -1)
        } else {
            -1
        }
        // 内存值只是缓存；进程被杀后靠 prefs 兜底
        val target = savedScreenOffTimeout?.takeIf { it > 0 } ?: stored
        savedScreenOffTimeout = null
        timeoutPatched = false
        if (target <= 0) return

        if (!canWriteSettings()) {
            // 基线保留，等有权限时再还原
            Log.w(TAG, "restore skipped: no WRITE_SETTINGS, keep baseline=$target")
            return
        }

        // 会话期间若超时已被改成别的值（既不是我们的 24h，也不是我们实际写进去的钳制值），
        // 说明那是用户的新选择，不要用旧基线覆盖回去；只清掉基线。
        val current = runCatching {
            Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
        }.getOrNull()
        val stillOurs = current == null ||
            current >= KEEP_AWAKE_TIMEOUT_MS ||
            (patchedValue > 0 && current == patchedValue)
        if (!stillOurs) {
            prefs.edit().remove(KEY_SAVED_TIMEOUT).remove(KEY_PATCHED_TIMEOUT).commit()
            Log.i(TAG, "restore skipped: timeout changed to $current by other; drop baseline=$target")
            return
        }

        val ok = runCatching {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.SCREEN_OFF_TIMEOUT,
                target,
            )
        }.isSuccess
        // 只有写成功才清基线；失败就留着，下次还能再试
        if (ok) {
            prefs.edit().remove(KEY_SAVED_TIMEOUT).remove(KEY_PATCHED_TIMEOUT).commit()
        }
        Log.i(TAG, "restore screen_off_timeout -> $target ok=$ok")
    }

    private fun buildParams(): WindowManager.LayoutParams {
        val flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getRealMetrics(metrics)

        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            metrics.heightPixels,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            flags,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 0
            y = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
    }

    companion object {
        private const val TAG = "RemoteKeepAwake"
        private const val HEARTBEAT_MS = 8_000L
        private const val WAKE_LOCK_CHUNK_MS = 60_000L
        /** 24h；部分 OEM 会钳制，但远大于默认 15s */
        private const val KEEP_AWAKE_TIMEOUT_MS = 24 * 60 * 60 * 1000
        private const val PREFS_NAME = "remote_keep_awake"
        private const val KEY_SAVED_TIMEOUT = "saved_screen_off_timeout"
        /** patch 后**实际生效**的值（OEM 可能钳制），用于区分"我们的"和"用户改的" */
        private const val KEY_PATCHED_TIMEOUT = "patched_screen_off_timeout"

        /**
         * 进程在常亮期间被杀时，系统里会留着 24h 超时，而 [restoreScreenOffTimeout]
         * 只会在下一次 show/hide 时被调用——用户若不再触发常亮，这个值就永久留着了。
         *
         * 因此每次进程启动都做一次自愈：prefs 里有基线、且当前值确实还是"我们写的"，
         * 就还原。当前值已被用户改过则不碰，只清基线。
         *
         * 只会做一次系统写入；无 WRITE_SETTINGS 时保留基线，等下次启动再试。
         */
        fun recoverStaleScreenOffTimeout(context: Context) {
            val appContext = context.applicationContext
            val prefs = DirectBoot.deviceProtectedContext(appContext)
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val stored = if (prefs.contains(KEY_SAVED_TIMEOUT)) {
                prefs.getInt(KEY_SAVED_TIMEOUT, -1)
            } else {
                -1
            }
            if (stored <= 0) return

            val patchedValue = if (prefs.contains(KEY_PATCHED_TIMEOUT)) {
                prefs.getInt(KEY_PATCHED_TIMEOUT, -1)
            } else {
                -1
            }
            val current = runCatching {
                Settings.System.getInt(appContext.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
            }.getOrNull() ?: return

            val stillOurs = current >= KEEP_AWAKE_TIMEOUT_MS ||
                (patchedValue > 0 && current == patchedValue)
            if (!stillOurs) {
                prefs.edit().remove(KEY_SAVED_TIMEOUT).remove(KEY_PATCHED_TIMEOUT).commit()
                Log.i(
                    TAG,
                    "recover: timeout already changed to $current; drop stale baseline=$stored",
                )
                return
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                !Settings.System.canWrite(appContext)
            ) {
                Log.w(TAG, "recover skipped: no WRITE_SETTINGS, keep baseline=$stored")
                return
            }

            val ok = runCatching {
                Settings.System.putInt(
                    appContext.contentResolver,
                    Settings.System.SCREEN_OFF_TIMEOUT,
                    stored,
                )
            }.isSuccess
            if (ok) {
                prefs.edit().remove(KEY_SAVED_TIMEOUT).remove(KEY_PATCHED_TIMEOUT).commit()
            }
            Log.i(TAG, "recover stale screen_off_timeout $current -> $stored ok=$ok")
        }
    }
}
