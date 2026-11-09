package com.vibelink.client.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class DeviceScreenWebSocketClient(
    private val scope: CoroutineScope,
    private val deviceToken: String,
    private val wsScreenBaseUrl: String,
    private val listener: Listener,
) {
    interface Listener {
        fun onConnected()
        fun onDisconnected(code: Int, reason: String)
        fun onCommand(id: String, cmd: String, params: JSONObject)
        fun onSignaling(type: String, payload: JSONObject) {}
        fun onFailure(message: String)
    }

    private val client = OkHttpClient.Builder()
        .pingInterval(30, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    /** 连接代次：作废旧 socket 后，它迟到/取消产生的回调一律丢弃。见 DeviceWebSocketClient。 */
    @Volatile
    private var generation = 0

    @Volatile
    private var webSocket: WebSocket? = null
    private var reconnectJob: Job? = null

    @Volatile
    private var isSocketOpen = false

    @Volatile
    private var isHandshaking = false

    @Volatile
    private var connectStartedAtMs = 0L

    fun connect() {
        closeCurrentSocket()

        val gen = generation
        isHandshaking = true
        connectStartedAtMs = System.currentTimeMillis()

        val request = Request.Builder()
            .url("${wsScreenBaseUrl.trimEnd('/')}?token=$deviceToken")
            .build()

        val socket = client.newWebSocket(
            request,
            object : WebSocketListener() {
                private fun superseded(): Boolean = gen != generation

                override fun onOpen(webSocket: WebSocket, response: Response) {
                    if (superseded()) {
                        runCatching { webSocket.close(1000, "superseded") }
                        return
                    }
                    isHandshaking = false
                    isSocketOpen = true
                    listener.onConnected()
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    if (superseded()) {
                        return
                    }
                    val json = runCatching { JSONObject(text) }.getOrNull() ?: return
                    val type = json.optString("type")
                    when (type) {
                        "command" -> {
                            val commandId = json.optString("id")
                            val cmd = json.optString("cmd")
                            val params = json.optJSONObject("params") ?: JSONObject()
                            if (commandId.isNotBlank() && cmd.isNotBlank()) {
                                listener.onCommand(commandId, cmd, params)
                            }
                        }
                        "webrtc_offer", "webrtc_answer", "webrtc_ice", "webrtc_hangup" -> {
                            listener.onSignaling(
                                type,
                                json.optJSONObject("payload") ?: JSONObject(),
                            )
                        }
                    }
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    if (superseded()) {
                        return
                    }
                    webSocket.close(code, reason)
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    if (superseded()) {
                        return
                    }
                    isHandshaking = false
                    isSocketOpen = false
                    listener.onDisconnected(code, reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    if (superseded()) {
                        return
                    }
                    isHandshaking = false
                    isSocketOpen = false
                    listener.onFailure(t.message ?: "screen websocket failure")
                }
            },
        )
        if (gen == generation) {
            webSocket = socket
        } else {
            runCatching { socket.cancel() }
        }
    }

    fun disconnect(suppressListener: Boolean = false) {
        // 代次机制已保证旧 socket 的回调不落到 listener，suppressListener 仅保留兼容
        closeCurrentSocket()
    }

    private fun closeCurrentSocket() {
        reconnectJob?.cancel()
        reconnectJob = null
        generation++
        isHandshaking = false
        isSocketOpen = false
        connectStartedAtMs = 0L
        val socket = webSocket
        webSocket = null
        runCatching { socket?.cancel() }
    }

    fun isConnected(): Boolean = isSocketOpen

    fun isConnecting(): Boolean {
        if (!isHandshaking) {
            return false
        }
        return System.currentTimeMillis() - connectStartedAtMs < HANDSHAKE_TIMEOUT_MS
    }

    fun scheduleReconnect(delayMs: Long = RECONNECT_DELAY_MS) {
        if (reconnectJob?.isActive == true) {
            return
        }
        reconnectJob = scope.launch {
            delay(delayMs)
            connect()
        }
    }

    fun sendScreenFrame(
        data: String,
        screenWidth: Int,
        screenHeight: Int,
        source: String? = null,
        imageWidth: Int = screenWidth,
        imageHeight: Int = screenHeight,
    ): Boolean {
        val payload = JSONObject()
            .put("data", data)
            .put("screenWidth", screenWidth)
            .put("screenHeight", screenHeight)
            .put("imageWidth", imageWidth)
            .put("imageHeight", imageHeight)
            .put("capturedAt", System.currentTimeMillis())
        if (!source.isNullOrBlank()) {
            payload.put("source", source)
        }

        val message = JSONObject()
            .put("type", "screen_frame")
            .put("payload", payload)
            .toString()

        return sendRaw(message, droppable = true)
    }

    /**
     * UI 树主动推送。走画面通道，天然与控制命令分离。
     * droppable=true：水位高时丢弃本帧，下次 push 会补上。
     */
    fun sendUiTreeSnapshot(seq: Long, snapshot: JSONObject): Boolean {
        val payload = JSONObject()
            .put("seq", seq)
            .put("snapshot", snapshot)
            .put("pushedAt", System.currentTimeMillis())
        val message = JSONObject()
            .put("type", "ui_tree_push")
            .put("payload", payload)
            .toString()
        return sendRaw(message, droppable = true)
    }

    fun sendScreenError(errorCode: Int, message: String? = null): Boolean {
        val payload = JSONObject().put("errorCode", errorCode)
        if (!message.isNullOrBlank()) {
            payload.put("message", message)
        }
        val envelope = JSONObject()
            .put("type", "screen_error")
            .put("payload", payload)
            .toString()
        return sendRaw(envelope)
    }

    fun sendScreenStatus(status: String, detail: String? = null): Boolean {
        val payload = JSONObject().put("status", status)
        if (!detail.isNullOrBlank()) {
            payload.put("detail", detail)
        }
        val envelope = JSONObject()
            .put("type", "screen_status")
            .put("payload", payload)
            .toString()
        return sendRaw(envelope)
    }

    fun sendCommandResult(
        commandId: String,
        ok: Boolean,
        data: JSONObject = JSONObject(),
        error: String? = null,
    ): Boolean {
        val payload = JSONObject()
            .put("id", commandId)
            .put("ok", ok)
            .put("data", data)
        if (!ok && !error.isNullOrBlank()) {
            payload.put("error", error)
        }

        val message = JSONObject()
            .put("type", "command_result")
            .put("payload", payload)
            .toString()

        return sendRaw(message)
    }

    fun sendSignaling(type: String, payload: JSONObject): Boolean {
        val message = JSONObject()
            .put("type", type)
            .put("payload", payload)
            .toString()
        return sendRaw(message)
    }

    /**
     * droppable=true 仅允许用于可丢快照（screen_frame / ui_tree_push）。
     * 信令（webrtc_offer/answer/ice/hangup）、command_result、screen_error/status 一律走必达路径。
     * 这里再做一层硬校验：即使调用方误传 droppable=true，只要 type 不在白名单内，也不会被丢。
     */
    private fun sendRaw(message: String, droppable: Boolean = false): Boolean {
        val socket = webSocket ?: return false
        if (!isSocketOpen) {
            return false
        }
        if (droppable && isDroppableType(message)) {
            val queued = runCatching { socket.queueSize() }.getOrDefault(0L)
            if (queued > DROP_QUEUE_BYTES) {
                android.util.Log.w(
                    TAG,
                    "drop_stale_frame queue=$queued limit=$DROP_QUEUE_BYTES size=${message.length}",
                )
                return false
            }
        }
        return runCatching { socket.send(message) }.getOrDefault(false)
    }

    private fun isDroppableType(message: String): Boolean {
        // 只扫前 128 字节，避免对大帧做全量解析
        val head = if (message.length > 128) message.substring(0, 128) else message
        return head.contains("\"type\":\"screen_frame\"") ||
            head.contains("\"type\":\"ui_tree_push\"")
    }

    companion object {
        private const val TAG = "DeviceScreenWebSocketClient"
        private const val RECONNECT_DELAY_MS = 2_000L
        private const val HANDSHAKE_TIMEOUT_MS = 20_000L
        /** 水位阈值：可丢消息（画面帧、UI 树）在写队列超过该值时丢弃。 */
        private const val DROP_QUEUE_BYTES = 128L * 1024L
    }
}
