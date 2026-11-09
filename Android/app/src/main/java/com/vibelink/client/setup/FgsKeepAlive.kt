package com.vibelink.client.setup

import android.app.Service
import android.content.pm.ServiceInfo
import android.os.Build
import android.util.Log
import androidx.core.app.ServiceCompat

/**
 * targetSdk 35：dataSync FGS 每 24h 合计最多 6h。
 * 超时后系统调 [Service.onTimeout]；几秒内必须摘掉 dataSync，
 * 再 startForeground(dataSync) 会抛 Time limit already exhausted。
 *
 * API 34+ 长驻改 specialUse / VpnService 用 systemExempted，避开这套配额。
 */
object FgsKeepAlive {
    private const val TAG = "FgsKeepAlive"

    @Volatile
    var dataSyncExhausted: Boolean = false
        private set

    fun markDataSyncExhausted() {
        dataSyncExhausted = true
        Log.w(TAG, "dataSync FGS quota exhausted")
    }

    fun connectionType(withMediaProjection: Boolean, withCamera: Boolean): Int {
        var type = longRunningBaseType()
        if (withMediaProjection) {
            type = type or ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
        }
        if (withCamera) {
            type = type or ServiceInfo.FOREGROUND_SERVICE_TYPE_CAMERA
        }
        return type
    }

    fun vpnType(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return ServiceInfo.FOREGROUND_SERVICE_TYPE_SYSTEM_EXEMPTED
        }
        return dataSyncIfAllowed()
    }

    fun vpnFallbackType(): Int = dataSyncIfAllowed()

    fun accessibilityType(): Int = longRunningBaseType()

    fun longRunningBaseType(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
        }
        return dataSyncIfAllowed()
    }

    fun dataSyncIfAllowed(): Int {
        if (dataSyncExhausted) {
            return 0
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
        }
        return 0
    }

    fun onTimeout(
        service: Service,
        startId: Int,
        fgsType: Int,
        tag: String,
        reenterForeground: () -> Unit,
        mayStopSelf: Boolean,
    ) {
        Log.w(tag, "FGS timeout startId=$startId type=$fgsType")
        if (fgsType and ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC != 0) {
            markDataSyncExhausted()
        }
        val reentered = runCatching { reenterForeground() }
            .onFailure { error -> Log.e(tag, "reenter FGS after timeout failed", error) }
            .isSuccess
        if (reentered) {
            ServiceWatchdog.scheduleNextCheck(service)
            return
        }
        runCatching {
            ServiceCompat.stopForeground(service, ServiceCompat.STOP_FOREGROUND_DETACH)
        }
        ServiceWatchdog.scheduleFastRestart(service)
        if (mayStopSelf) {
            service.stopSelf(startId)
        }
    }
}
