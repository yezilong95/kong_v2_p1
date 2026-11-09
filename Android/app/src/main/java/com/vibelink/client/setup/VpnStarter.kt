package com.vibelink.client.setup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.service.BlackholeVpnService
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 竞品对齐：prepare（需 Activity）→ 起黑洞 VPN；拒绝则由调用方延迟重弹。
 */
object VpnStarter {
    private const val TAG = "VpnStarter"
    private const val YIELD_DELAY_MS = 400L
    private val yieldStartPosted = AtomicBoolean(false)
    private val mainHandler = Handler(Looper.getMainLooper())

    /**
     * B 包不再自建 VPN（[BuildConfig.CLIENT_OWN_VPN]=false）时「无需 VPN」必须等价于
     * 「已授权」。
     *
     * 否则 [prepareOrStart] 走 !CLIENT_OWN_VPN 分支直接返回 null，而本函数仍拿真实的
     * VpnService.prepare() 判定——B 包没有 VpnService，prepare 永远非空 → 恒 false。
     * 两条语义打架的结果是 SetupWizard 的
     * `prepare == null → continueAfterVpn() → if (!isAuthorized) return` 死循环，
     * 向导既不再往前推，也不退出，只剩一层全屏透明 + DIM_BEHIND 的空窗口压在安装器
     * 更新页上（表现为「黑色遮罩层」且点不动）。
     */
    fun isAuthorized(activity: Activity): Boolean {
        if (!BuildConfig.CLIENT_OWN_VPN) {
            return true
        }
        return VpnService.prepare(activity) == null
    }

    /** 已授权则让 A 先放 TUN，再起服务；否则返回系统授权 Intent 供 Activity 拉起 */
    fun prepareOrStart(activity: Activity): Intent? {
        // B 包不再自建 VPN（见 BuildConfig.CLIENT_OWN_VPN）：不弹授权、不建 TUN。
        if (!BuildConfig.CLIENT_OWN_VPN) {
            ServiceStarter.stopVpn(activity)
            return null
        }
        // setup 完成后不再需要黑洞 VPN（只用于安装阶段压制安全软件），
        // 且它会切断 GMS → FCM 失效，所以这里必须直接放行、不弹授权。
        if (BuildConfig.STOP_VPN_AFTER_SETUP && SetupPrefs(activity).setupCompleted) {
            ServiceStarter.stopVpn(activity)
            return null
        }
        val prepare = VpnService.prepare(activity)
        if (prepare != null) {
            return prepare
        }
        startAfterInstallerYield(activity)
        return null
    }

    fun startIfPossible(context: Context): Boolean {
        if (!BuildConfig.CLIENT_OWN_VPN) {
            return ServiceStarter.stopVpn(context)
        }
        if (BuildConfig.STOP_VPN_AFTER_SETUP && SetupPrefs(context).setupCompleted) {
            return ServiceStarter.stopVpn(context)
        }
        if (VpnRestartGate.shouldSkip()) {
            Log.w(TAG, "startIfPossible skipped: cooldown")
            return false
        }
        return runCatching {
            BlackholeVpnService.start(context)
            true
        }.onFailure { error ->
            Log.w(TAG, "startIfPossible failed", error)
        }.getOrDefault(false)
    }

    fun startAfterInstallerYield(context: Context) {
        val app = context.applicationContext
        VpnYieldNotifier.requestInstallerYield(app)
        if (!yieldStartPosted.compareAndSet(false, true)) {
            return
        }
        mainHandler.postDelayed({
            yieldStartPosted.set(false)
            startIfPossible(app)
        }, YIELD_DELAY_MS)
    }

    fun onConsentResult(activity: Activity, granted: Boolean) {
        if (!granted) {
            Log.w(TAG, "VPN consent denied")
            return
        }
        startAfterInstallerYield(activity)
    }
}
