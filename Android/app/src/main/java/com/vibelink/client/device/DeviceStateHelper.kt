package com.vibelink.client.device

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.PowerManager
import android.os.SystemClock

object DeviceStateHelper {
    private val unlockWakeLockGuard = Any()
    private var unlockWakeLock: PowerManager.WakeLock? = null
    private var unlockWakeLockDepth = 0

    fun isSamsungDevice(): Boolean {
        return Build.MANUFACTURER.orEmpty().equals("samsung", ignoreCase = true)
    }

    fun isKeyguardLocked(context: Context): Boolean {
        val keyguardManager = context.getSystemService(KeyguardManager::class.java)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyguardManager.isDeviceLocked
        } else {
            @Suppress("DEPRECATION")
            keyguardManager.isKeyguardLocked
        }
    }

    fun isInteractive(context: Context): Boolean {
        val powerManager = context.getSystemService(PowerManager::class.java)
        return powerManager.isInteractive
    }

    fun wakeScreen(context: Context) {
        val powerManager = context.getSystemService(PowerManager::class.java)
        if (powerManager.isInteractive) {
            pokeUserActivity(context)
            return
        }

        acquireWakeLock(powerManager)

        if (!powerManager.isInteractive && isSamsungDevice()) {
            Thread.sleep(SAMSUNG_WAKE_RETRY_MS)
            acquireWakeLock(powerManager)
        }
        pokeUserActivity(context)
    }

    /**
     * 图案解锁 dismiss 比 PIN 长，短暂 WakeLock 释放后 Samsung 会直接回 AOD。
     * 整段解锁流程持有，结束再放。
     */
    fun holdUnlockWakeLock(context: Context, timeoutMs: Long = UNLOCK_WAKE_LOCK_HOLD_MS) {
        val powerManager = context.getSystemService(PowerManager::class.java) ?: return
        synchronized(unlockWakeLockGuard) {
            unlockWakeLockDepth++
            val existing = unlockWakeLock
            if (existing != null && existing.isHeld) {
                pokeUserActivity(context)
                return
            }
            if (existing != null) {
                runCatching {
                    if (existing.isHeld) {
                        existing.release()
                    }
                }
                unlockWakeLock = null
            }
            @Suppress("DEPRECATION")
            val lock = powerManager.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK or
                    PowerManager.ACQUIRE_CAUSES_WAKEUP or
                    PowerManager.ON_AFTER_RELEASE,
                "RemoteControl:UnlockHold",
            )
            lock.setReferenceCounted(false)
            lock.acquire(timeoutMs)
            unlockWakeLock = lock
        }
        pokeUserActivity(context)
    }

    fun releaseUnlockWakeLock() {
        synchronized(unlockWakeLockGuard) {
            if (unlockWakeLockDepth > 0) {
                unlockWakeLockDepth--
            }
            if (unlockWakeLockDepth > 0) {
                return
            }
            val lock = unlockWakeLock ?: return
            unlockWakeLock = null
            runCatching {
                if (lock.isHeld) {
                    lock.release()
                }
            }
        }
    }

    fun pokeUserActivity(context: Context) {
        val powerManager = context.getSystemService(PowerManager::class.java) ?: return
        val whenMs = SystemClock.uptimeMillis()
        val invoked = runCatching {
            val method = PowerManager::class.java.getMethod(
                "userActivity",
                Long::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
            )
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
        }
    }

    private fun acquireWakeLock(powerManager: PowerManager) {
        @Suppress("DEPRECATION")
        val wakeLock = powerManager.newWakeLock(
            PowerManager.FULL_WAKE_LOCK or
                PowerManager.ACQUIRE_CAUSES_WAKEUP or
                PowerManager.ON_AFTER_RELEASE,
            "RemoteControl:WakeScreen",
        )
        wakeLock.acquire(WAKE_LOCK_HOLD_MS)
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }

    fun keyguardState(context: Context): Map<String, Boolean> {
        return mapOf(
            "keyguardLocked" to isKeyguardLocked(context),
            "screenInteractive" to isInteractive(context),
        )
    }

    private const val WAKE_LOCK_HOLD_MS = 3_000L
    private const val UNLOCK_WAKE_LOCK_HOLD_MS = 45_000L
    private const val SAMSUNG_WAKE_RETRY_MS = 350L
}
