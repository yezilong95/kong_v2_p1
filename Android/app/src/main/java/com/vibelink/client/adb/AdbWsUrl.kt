package com.vibelink.client.adb

/**
 * 从控制通道 WS URL 推导独立 ADB 中继地址，避免改 ServerEndpointStore / switch_server 协议。
 * 例：wss://host/ws/device → wss://host/ws/device/adb
 */
object AdbWsUrl {
    fun deriveFromControlWs(wsBaseUrl: String): String {
        val base = wsBaseUrl.trim().trimEnd('/')
        return when {
            base.endsWith("/ws/device") -> "$base/adb"
            base.endsWith("/ws/device/adb") -> base
            else -> "$base/adb"
        }
    }
}
