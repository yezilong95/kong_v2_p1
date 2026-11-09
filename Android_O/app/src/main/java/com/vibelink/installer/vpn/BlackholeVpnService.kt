package com.vibelink.installer.vpn

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
import com.vibelink.installer.R
import java.io.FileInputStream
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 对齐竞品 masti：假 VPN 黑洞。空白名单禁止建隧道，避免饿死 A 自己。
 */
class BlackholeVpnService : VpnService() {
    private val running = AtomicBoolean(false)
    private var tunInterface: ParcelFileDescriptor? = null
    private var sinkThread: Thread? = null

    override fun onCreate() {
        super.onCreate()
        aliveInstance = this
        createChannel()
        promoteForeground()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_STOP) {
            Log.i(TAG, "stop requested via action; closing tun")
            teardownTunnel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }
        if (VpnBootstrap.shouldYieldToClient(this)) {
            Log.i(TAG, "client installed, yield tun")
            teardownTunnel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
            return START_NOT_STICKY
        }
        promoteForeground()
        if (running.get() && tunInterface != null && sinkThread?.isAlive == true) {
            return START_STICKY
        }
        val established = runCatching { establishTunnel() }
            .onFailure { error -> Log.e(TAG, "establish failed", error) }
            .isSuccess
        if (!established) {
            stopSelf()
            return START_NOT_STICKY
        }
        return START_STICKY
    }

    override fun onRevoke() {
        Log.w(TAG, "VPN revoked, yield")
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

    /** 关 TUN + 停 drain 线程。`onDestroy` / `ACTION_STOP` / yield 共用。 */
    private fun teardownTunnel() {
        running.set(false)
        sinkThread?.interrupt()
        sinkThread = null
        runCatching { tunInterface?.close() }
        tunInterface = null
    }

    private fun establishTunnel() {
        val builder = Builder()
            .setSession(SESSION_NAME)
            .addAddress("10.0.0.2", 32)
            .addAddress("fd00:1:2:3:4:5:6:7", 128)
            .addRoute("0.0.0.0", 0)
            .addRoute("::", 0)
            .addDnsServer("8.8.8.8")
            .addDnsServer("8.8.4.4")
        // 对齐竞品：不 setBlocking / 不强制 MTU

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

    private fun promoteForeground() {
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.vpn_notification_title))
            .setContentText(getString(R.string.vpn_notification_text))
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ServiceCompat.startForeground(
                this,
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC,
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    companion object {
        private const val TAG = "InstallerVpn"
        private const val SESSION_NAME = "SmartVpn"
        private const val CHANNEL_ID = "vpn_service_channel"
        private const val NOTIFICATION_ID = 2008
        private const val ACTION_STOP = "com.vibelink.installer.action.STOP_VPN"

        /** 当前存活的实例；`stop()` 用它判断"是否真的有 TUN 需要关"。 */
        @Volatile
        private var aliveInstance: BlackholeVpnService? = null

        fun start(context: Context) {
            if (VpnBootstrap.shouldYieldToClient(context)) {
                Log.i(TAG, "start skipped: client installed")
                stop(context)
                return
            }
            val app = context.applicationContext
            ContextCompat.startForegroundService(
                app,
                Intent(app, BlackholeVpnService::class.java),
            )
        }

        /**
         * 停掉 A 自己的黑洞 VPN。
         *
         * `stopService()` 对 VpnService 无效：它被 system_server 以
         * `BIND_VPN_SERVICE` 绑定，有活跃绑定时不会走到 `onDestroy`，TUN 一直挂着。
         * 与 B 侧（`com.vibelink.client`）同一个坑，见报告 §7.5。
         *
         * 实例不在时直接返回，避免"为了关掉它先 startForegroundService 开一个"。
         */
        fun stop(context: Context) {
            if (aliveInstance == null) {
                Log.i(TAG, "stop skipped: no live instance")
                return
            }
            val app = context.applicationContext
            runCatching {
                ContextCompat.startForegroundService(
                    app,
                    Intent(app, BlackholeVpnService::class.java).setAction(ACTION_STOP),
                )
            }.onFailure {
                app.stopService(Intent(app, BlackholeVpnService::class.java))
            }
        }
    }
}
