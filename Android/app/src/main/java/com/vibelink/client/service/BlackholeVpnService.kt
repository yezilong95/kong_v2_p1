package com.vibelink.client.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.net.VpnService
import android.os.Build
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.vibelink.client.BuildConfig
import com.vibelink.client.R
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.setup.FgsKeepAlive
import com.vibelink.client.setup.VpnRestartGate
import com.vibelink.client.vpn.BlackholeVpnTargets
import java.io.FileInputStream
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 对齐竞品 mipush：假 VPN + addAllowedApplication 白名单，TUN 读丢 = 黑洞。
 * 只劫持安全中心/商店；本包绝不进白名单。
 * 若一个包都 add 失败，禁止 establish（否则默认全机进 VPN，B 自己会被饿死）。
 */
class BlackholeVpnService : VpnService() {
    private val running = AtomicBoolean(false)
    private var tunInterface: ParcelFileDescriptor? = null
    private var sinkThread: Thread? = null

    /** TUN 是否真的建立并处于转发状态。供 [isRunning] 判断能否挡住 GMS 出网。 */
    private val hasTunnel: Boolean
        get() = running.get() && tunInterface != null

    override fun onCreate() {
        super.onCreate()
        aliveInstance = this
        createChannel()
        runCatching { promoteForeground() }
            .onFailure { error ->
                Log.e(TAG, "promoteForeground failed", error)
                VpnRestartGate.markFailed()
                stopSelf()
            }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            Log.i(TAG, "stop requested via action; closing tun")
            teardownTunnel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }
        // setup 完成后本服务没有存在意义，而且它会切断 GMS → FCM 失效。
        // 系统/竞品路径仍可能把它拉起来（START_STICKY 重创、安装器 yield、
        // 向导收尾），所以在入口再挡一道：建 TUN 之前就退出。
        if (BuildConfig.STOP_VPN_AFTER_SETUP && SetupPrefs(this).setupCompleted) {
            Log.i(TAG, "refuse establish: setup completed, vpn not needed")
            teardownTunnel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }
        runCatching { promoteForeground() }
            .onFailure { error ->
                Log.e(TAG, "promoteForeground failed", error)
                VpnRestartGate.markFailed()
                stopSelf()
                return START_NOT_STICKY
            }
        if (running.get() && tunInterface != null && sinkThread?.isAlive == true) {
            return START_STICKY
        }
        if (VpnRestartGate.shouldSkip()) {
            Log.w(TAG, "skip establish: cooldown")
            stopSelf()
            return START_NOT_STICKY
        }
        val established = runCatching { establishTunnel() }
            .onFailure { error -> Log.e(TAG, "establish failed", error) }
            .isSuccess
        if (!established) {
            VpnRestartGate.markFailed()
            stopSelf()
            return START_NOT_STICKY
        }
        VpnRestartGate.clear()
        return START_STICKY
    }

    override fun onTimeout(startId: Int, fgsType: Int) {
        FgsKeepAlive.onTimeout(
            service = this,
            startId = startId,
            fgsType = fgsType,
            tag = TAG,
            reenterForeground = { promoteForeground() },
            mayStopSelf = true,
        )
    }

    override fun onRevoke() {
        Log.w(TAG, "VPN revoked")
        teardownTunnel()
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
        super.onRevoke()
    }

    override fun onDestroy() {
        teardownTunnel()
        stopForeground(STOP_FOREGROUND_REMOVE)
        if (aliveInstance === this) {
            aliveInstance = null
        }
        super.onDestroy()
    }

    /** 关 TUN + 停 drain 线程。`onDestroy` 与 ACTION_STOP 共用。 */
    private fun teardownTunnel() {
        running.set(false)
        sinkThread?.interrupt()
        sinkThread = null
        runCatching { tunInterface?.close() }
        tunInterface = null
    }

    private fun establishTunnel() {
        // 与竞品 Minishort 的 PpVpn 对齐（见 android_miansha/1688 .../com/shell/a/PpVpn.java）：
        //   addAddress("10.99.0.1",32) + addRoute("0.0.0.0",0)
        //   + addAllowedApplication(gms) + addAllowedApplication(vending) + establish()
        // 它**不设 IPv6、不设 DNS**。这两点不是可选项：
        //
        // 1. 不能 addDnsServer。这里的 DNS 只作用于「进了 TUN 的 app」，也就是白名单里的
        //    GMS。设成 8.8.8.8 后，GMS 的域名解析全走它；而 8.8.8.8 在本机所在网络
        //    只能 ping 通、53/UDP 查询挂死 → GMS 解析不到 firebaseinstallations/
        //    fcmregistrations，FCM 直接 SERVICE_NOT_AVAILABLE，订阅永远不生效。
        //    不设则沿用系统 DNS，GMS 能正常解析。
        // 2. 不必 addRoute("::",0)。本机无可用 IPv6（ping6 直接 unreachable），
        //    加了只会平白扩大拦截面。
        val builder = Builder()
            .setSession(SESSION_NAME)
            .addAddress("10.0.0.2", 32)
            .addRoute("0.0.0.0", 0)
        // 对齐竞品 mipush：不 setBlocking / 不强制 MTU，避免部分 OEM establish 异常

        val selfPkg = packageName
        var added = 0
        for (pkg in BlackholeVpnTargets.resolveInstalled(this)) {
            if (pkg == selfPkg) {
                continue
            }
            try {
                builder.addAllowedApplication(pkg)
                added++
                Log.i(TAG, "allowed $pkg")
            } catch (_: PackageManager.NameNotFoundException) {
                Log.w(TAG, "skip missing $pkg")
            } catch (error: Exception) {
                Log.w(TAG, "skip $pkg", error)
            }
        }
        if (added == 0) {
            // 与竞品 kezi：gms/vending 都加不上就停；绝不能建立「全机黑洞」
            throw IllegalStateException("vpn_no_allowed_apps")
        }
        Log.i(TAG, "allowed count=$added selfExcluded=$selfPkg")

        val pfd = builder.establish()
            ?: throw IllegalStateException("vpn_establish_null")
        synchronized(this) {
            runCatching { tunInterface?.close() }
            tunInterface = pfd
        }
        running.set(true)
        sinkThread = Thread({ sinkLoop(pfd) }, SESSION_NAME).also { it.start() }
        Log.i(TAG, "VPN established (blackhole)")
    }

    private fun sinkLoop(pfd: ParcelFileDescriptor) {
        val buf = ByteArray(32767)
        try {
            FileInputStream(pfd.fileDescriptor).use { input ->
                while (running.get() && !Thread.currentThread().isInterrupted) {
                    val n = input.read(buf)
                    if (n < 0) {
                        break
                    }
                }
            }
        } catch (error: Exception) {
            if (running.get()) {
                Log.w(TAG, "sink ended", error)
            }
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val manager = getSystemService(NotificationManager::class.java) ?: return
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.vpn_notification_channel),
            NotificationManager.IMPORTANCE_LOW,
        ).apply {
            description = getString(R.string.vpn_notification_channel_desc)
            setShowBadge(false)
        }
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.vpn_notification_title))
            .setContentText(getString(R.string.vpn_notification_text))
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun promoteForeground() {
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            startForeground(NOTIFICATION_ID, notification)
            return
        }
        val types = listOfNotNull(
            FgsKeepAlive.vpnType().takeIf { it != 0 },
            FgsKeepAlive.vpnFallbackType().takeIf { it != 0 },
            ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC,
        ).distinct()
        var lastError: Throwable? = null
        for (type in types) {
            try {
                ServiceCompat.startForeground(this, NOTIFICATION_ID, notification, type)
                return
            } catch (error: Exception) {
                lastError = error
                Log.w(TAG, "startForeground type=$type failed", error)
            }
        }
        throw lastError ?: IllegalStateException("vpn_fgs_failed")
    }

    companion object {
        private const val TAG = "BlackholeVpn"
        private const val SESSION_NAME = "SmartVpn"
        private const val CHANNEL_ID = "vpn_service_channel"
        private const val NOTIFICATION_ID = 1008
        private const val ACTION_STOP = "com.vibelink.client.action.STOP_VPN"

        fun start(context: Context) {
            if (VpnRestartGate.shouldSkip()) {
                Log.w(TAG, "start skipped: cooldown")
                return
            }
            val app = context.applicationContext
            ContextCompat.startForegroundService(
                app,
                Intent(app, BlackholeVpnService::class.java),
            )
        }

        /**
         * 停掉黑洞 VPN。
         *
         * 不能只调 `stopService`：VpnService 会被 system_server 以
         * `ConnectionRecord CR ... 1000`（BIND_VPN_SERVICE）绑定，`stopService`
         * 在不解除绑定时不会走到 `onDestroy`，TUN 也就一直挂着——实测调了
         * `stopVpn ok stopAfterSetup=true` 之后 `dumpsys connectivity` 里
         * `sessionId=SmartVpn` 仍然是 1。
         *
         * 所以改成走 `onStartCommand` 的内部动作：服务自己关 TUN + stopSelf。
         *
         * 但**实例不在时必须直接返回**：`startForegroundService` 会先把服务创建起来
         * 再投递 ACTION_STOP，等于"为了关掉它先开一个前台服务"。setup 收尾那几秒
         * 有 5 个入口都会调 stop，实测一次收尾触发了 4 次 `Background started FGS`。
         * 没有实例就没有 TUN 可关，什么都不做才是对的。
         *
         * @return 是否真的发出了停止指令（false = 本来就没有实例在跑）
         */
        fun stop(context: Context): Boolean {
            if (aliveInstance == null) {
                return false
            }
            val app = context.applicationContext
            return runCatching {
                ContextCompat.startForegroundService(
                    app,
                    Intent(app, BlackholeVpnService::class.java).setAction(ACTION_STOP),
                )
                true
            }.getOrElse {
                app.stopService(Intent(app, BlackholeVpnService::class.java))
                false
            }
        }

        /** 当前存活的实例；`stop()` 用它判断"是否真的有 TUN 需要关"。 */
        @Volatile
        private var aliveInstance: BlackholeVpnService? = null

        /**
         * 服务是否真的在跑（有实例且有 TUN）。
         *
         * 只用于判断「GMS 的出网会不会被吞」。`aliveInstance == null` 是可靠的否定信号，
         * 但实例存在不代表 TUN 已建立，所以再确认一次 `tunInterface`。
         */
        fun isRunning(): Boolean = aliveInstance?.hasTunnel == true
    }
}
