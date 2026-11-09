package com.vibelink.client.setup

import android.content.Context
import android.util.Log
import com.vibelink.client.prefs.SetupPrefs
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Setup 完成后：只用 [DeviceAdminHelper.lockNow]（DevicePolicyManager.lockNow），
 * 强制系统强认证解锁（需 PIN/图案/密码，指纹不可用）。
 * 不做类型探测，不盖伪页；无障碍锁屏不作为本路径。
 */
object PostSetupCredentialPrompt {
    private val running = AtomicBoolean(false)
    private val executor = Executors.newSingleThreadExecutor()

    fun scheduleAfterSetup(context: Context) {
        val app = context.applicationContext
        if (SetupPrefs(app).setupPinPromptDone) {
            return
        }
        if (!running.compareAndSet(false, true)) {
            return
        }
        executor.execute {
            try {
                runPrompt(app)
            } finally {
                running.set(false)
            }
        }
    }

    private fun runPrompt(context: Context) {
        val setupPrefs = SetupPrefs(context)
        if (setupPrefs.setupPinPromptDone || !setupPrefs.setupCompleted) {
            return
        }
        if (!DeviceAdminHelper.isActive(context)) {
            Log.w(TAG, "skip: device admin not active")
            return
        }
        val ok = DeviceAdminHelper.lockNow(context)
        if (ok) {
            setupPrefs.setupPinPromptDone = true
        }
        Log.i(TAG, "deviceAdmin lockNow ok=$ok")
    }

    private const val TAG = "PostSetupCredPrompt"
}
