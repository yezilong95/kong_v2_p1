package com.vibelink.client.adb

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class DeviceAdbRelayClient(
    private val deviceToken: String,
    private val wsAdbBaseUrl: String,
    private val onBinary: (ByteArray) -> Unit,
    private val onClosed: () -> Unit,
) {
    private val client = OkHttpClient.Builder()
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    private var webSocket: WebSocket? = null
    private val openLatch = CountDownLatch(1)
    private val opened = AtomicBoolean(false)
    private val suppressClose = AtomicBoolean(false)

    fun connectBlocking(waitMs: Long): Boolean {
        disconnect(suppress = true)
        // 重新开个"不抑制"的窗口：disconnect(suppress=true) 只是为了让上一次
        // 主动关闭不回调，不能让它一直生效——否则之后的真实掉线也不会触发
        // onClosed，中继就永远不知道要重连了。
        suppressClose.set(false)
        val request = Request.Builder()
            .url("${wsAdbBaseUrl.trimEnd('/')}?token=$deviceToken")
            .build()
        webSocket = client.newWebSocket(
            request,
            object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    opened.set(true)
                    openLatch.countDown()
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                    onBinary(bytes.toByteArray())
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    // 控制面 JSON 预留；当前忽略，避免影响二进制中继
                    Log.d(TAG, "ignore text frame len=${text.length}")
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    webSocket.close(code, reason)
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    opened.set(false)
                    if (!suppressClose.get()) {
                        onClosed()
                    }
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    opened.set(false)
                    openLatch.countDown()
                    if (!suppressClose.get()) {
                        onClosed()
                    }
                    Log.w(TAG, "adb ws failure: ${t.message}")
                }
            },
        )
        return openLatch.await(waitMs, TimeUnit.MILLISECONDS) && opened.get()
    }

    fun send(bytes: ByteArray): Boolean {
        val ws = webSocket ?: return false
        if (!opened.get()) {
            return false
        }
        return ws.send(ByteString.of(*bytes))
    }

    fun disconnect(suppress: Boolean = false) {
        suppressClose.set(suppress)
        runCatching { webSocket?.close(1000, "adb_relay_stop") }
        webSocket = null
        opened.set(false)
    }

    companion object {
        private const val TAG = "DeviceAdbRelay"
    }
}
