package com.vibelink.client.adb

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.vibelink.client.network.DeviceAuthRejection
import com.vibelink.client.prefs.DirectBoot
import org.json.JSONObject
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

/**
 * 管理 ADB 中继 WS + 多路 TCP。
 *
 * 默认不连 WS；TCP 仅在收到服务端 OPEN 帧后建立，避免抢占无线配对端口。
 *
 * 一旦 [start] 被调用过，这里会自己维持连接：服务器重启、网络抖动导致
 * WS 断开时按指数退避重连，直到 [stop]（用户/后台主动停）为止。
 * 重连后不需要客户端重开 TCP 流——服务端发现中继 WS 回来却没有 hub，
 * 会重建流并发 OPEN 帧，客户端照常按帧建连即可。
 */
object AdbRelayController {
    private const val TAG = "AdbRelayCtrl"
    /** 重连退避：2s 起步，封顶 60s。服务器重启通常几秒内就能连上。 */
    private const val RECONNECT_BASE_MS = 2_000L
    private const val RECONNECT_MAX_MS = 60_000L
    private const val PREFS_NAME = "vibelink_adb_relay"
    private const val KEY_DESIRED = "relay_desired"

    @Volatile
    private var deviceToken: String? = null

    @Volatile
    private var wsAdbBaseUrl: String? = null

    /** 只有落盘用；不持有 Activity/Service 引用。 */
    @Volatile
    private var appContext: Context? = null

    private var client: DeviceAdbRelayClient? = null
    private val bridges = ConcurrentHashMap<Int, AdbTcpBridge>()
    private val pendingData = ConcurrentHashMap<Int, CopyOnWriteArrayList<ByteArray>>()
    private val ioExecutor = Executors.newCachedThreadPool()

    private val mainHandler = Handler(Looper.getMainLooper())

    /**
     * token 被服务端拒绝（4401 invalid_token）时的回调，由宿主去清凭据重注册。
     *
     * 中继自己没法做这件事：它不持有 DevicePrefs，也不知道当前端点的注册接口。
     */
    @Volatile
    private var authRejectedListener: (() -> Unit)? = null

    fun setAuthRejectedListener(listener: (() -> Unit)?) {
        authRejectedListener = listener
    }

    /** 是否应该保持中继在线。只有 start() 置 true，stop() 置 false。 */
    @Volatile
    private var desired = false

    private var reconnectAttempt = 0
    /** 每次新建 client 递增。用来忽略「旧 client 的迟到回调」，避免把新连接顶掉。 */
    private val clientGeneration = java.util.concurrent.atomic.AtomicInteger(0)
    private val reconnectRunnable = Runnable { connectAndHold() }

    data class StartResult(
        val ok: Boolean,
        val data: JSONObject = JSONObject(),
        val error: String? = null,
    )

    fun bind(token: String, wsAdbBaseUrl: String) {
        this.deviceToken = token
        this.wsAdbBaseUrl = wsAdbBaseUrl.trimEnd('/')
    }

    fun bindContext(context: Context) {
        appContext = context.applicationContext
    }

    /**
     * 进程被杀/重启后，若上次是「启用中」，把中继接回来。
     *
     * 为什么需要：桌面进程常被厂商冻结或回收，中继一断管理端就显示未连接。
     * 存这个标记后，只要用户没主动停过，重启也能自动挂回去。
     */
    fun resumeIfDesired(): Boolean {
        val context = appContext ?: return false
        val prefs = DirectBoot.deviceProtectedContext(context)
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        if (!prefs.getBoolean(KEY_DESIRED, false)) {
            return false
        }
        if (deviceToken.isNullOrBlank() || wsAdbBaseUrl.isNullOrBlank()) {
            return false
        }
        if (desired) {
            return false
        }
        Log.i(TAG, "resume relay from persisted desired flag")
        start(JSONObject().put("openNow", false))
        return true
    }

    private fun persistDesired(value: Boolean) {
        val context = appContext ?: return
        runCatching {
            DirectBoot.deviceProtectedContext(context)
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_DESIRED, value)
                .apply()
        }
    }

    fun unbind() {
        // 只断连接，不清落盘的 desired：
        // onDestroy 是重启循环（停完立刻 ServiceStarter.tryStart），
        // 换服务器也要用新地址重连。只有用户/后台主动 stop 才该清标记。
        desired = false
        mainHandler.removeCallbacks(reconnectRunnable)
        reconnectAttempt = 0
        stopBridges()
        stopClientOnly()
        pendingData.clear()
        deviceToken = null
        wsAdbBaseUrl = null
    }

    fun start(params: JSONObject): StartResult {
        val token = deviceToken
        val base = wsAdbBaseUrl
        if (token.isNullOrBlank() || base.isNullOrBlank()) {
            return StartResult(ok = false, error = "adb_relay_not_bound")
        }

        desired = true
        // 落盘：进程被系统回收后还能自己接回来，不用运维重新点匹配
        persistDesired(true)
        mainHandler.removeCallbacks(reconnectRunnable)
        stopClientOnly()
        pendingData.clear()
        reconnectAttempt = 0

        val waitMs = params.optLong("connectWaitMs", 8_000L).coerceIn(2_000L, 30_000L)
        val fresh = createClient()
        if (!fresh.connectBlocking(waitMs)) {
            // 首次连不上也保留 desired：服务器可能正在重启，后台继续重试
            scheduleReconnect()
            return StartResult(ok = false, error = "adb_ws_connect_failed")
        }

        val openNow = params.optBoolean("openNow", false)
        var opened = 0
        val streams = params.optJSONArray("streams")
        if (openNow && streams != null) {
            for (i in 0 until streams.length()) {
                val item = streams.optJSONObject(i) ?: continue
                val streamId = item.optInt("streamId", i + 1)
                val host = item.optString("host").trim()
                val port = item.optInt("port", 0)
                if (host.isBlank() || port <= 0) {
                    continue
                }
                if (openBridge(streamId, host, port)) {
                    opened++
                }
            }
        }

        return StartResult(
            ok = true,
            data = JSONObject()
                .put("wsConnected", true)
                .put("opened", opened)
                .put("awaitServerOpen", !openNow),
        )
    }

    fun stop() {
        desired = false
        persistDesired(false)
        mainHandler.removeCallbacks(reconnectRunnable)
        reconnectAttempt = 0
        stopBridges()
        stopClientOnly()
        pendingData.clear()
    }

    private fun createClient(): DeviceAdbRelayClient {
        val generation = clientGeneration.incrementAndGet()
        val fresh = DeviceAdbRelayClient(
            deviceToken = deviceToken ?: "",
            wsAdbBaseUrl = wsAdbBaseUrl ?: "",
            onBinary = { bytes -> onFrame(bytes) },
            onClosed = { code, reason ->
                // 旧实例的迟到回调不能影响新连接：服务器重启时先断后连，
                // 不加这层会把自己刚建好的 WS 又当成断线处理一遍。
                if (generation == clientGeneration.get()) {
                    onRelayClosed(code, reason)
                }
            },
        )
        client = fresh
        return fresh
    }

    /** WS 掉了：清掉已死的 TCP 流，然后按退避重连。 */
    private fun onRelayClosed(code: Int, reason: String) {
        stopBridges()
        client = null
        if (!desired) {
            return
        }
        if (DeviceAuthRejection.isInvalidToken(code, reason)) {
            // token 被服务端否掉，按退避重连多少次都是同一个 4401。
            // 交给宿主去清凭据重注册，这里只停掉重连、保留 desired 标记，
            // 好让新 token 绑进来之后还能自动接回去。
            Log.w(TAG, "relay rejected token code=$code reason=$reason")
            mainHandler.removeCallbacks(reconnectRunnable)
            reconnectAttempt = 0
            authRejectedListener?.invoke()
            return
        }
        Log.i(TAG, "relay closed, scheduling reconnect attempt=${reconnectAttempt + 1}")
        scheduleReconnect()
    }

    private fun scheduleReconnect() {
        if (!desired) {
            return
        }
        val delay = backoffMs(reconnectAttempt)
        reconnectAttempt += 1
        mainHandler.removeCallbacks(reconnectRunnable)
        mainHandler.postDelayed(reconnectRunnable, delay)
    }

    private fun backoffMs(attempt: Int): Long {
        var value = RECONNECT_BASE_MS
        repeat(attempt.coerceAtMost(5)) {
            value *= 2
            if (value >= RECONNECT_MAX_MS) {
                return RECONNECT_MAX_MS
            }
        }
        return value.coerceAtMost(RECONNECT_MAX_MS)
    }

    /**
     * 重连一次。
     *
     * 成功后不主动重开 TCP 流：服务端 rebind 时会发 OPEN 帧，客户端按帧建连。
     * 这样端口由服务端掌握，客户端不需要记住上次的 host/port。
     */
    private fun connectAndHold() {
        if (!desired) {
            return
        }
        if (deviceToken.isNullOrBlank() || wsAdbBaseUrl.isNullOrBlank()) {
            return
        }
        stopBridges()
        stopClientOnly()
        val fresh = createClient()
        if (fresh.connectBlocking(8_000L)) {
            reconnectAttempt = 0
            Log.i(TAG, "relay reconnected")
            return
        }
        client = null
        scheduleReconnect()
    }

    private fun stopClientOnly() {
        runCatching { client?.disconnect(suppress = true) }
        client = null
    }

    private fun stopBridges() {
        val ids = bridges.keys.toList()
        for (id in ids) {
            bridges.remove(id)?.close()
        }
    }

    private fun openBridge(streamId: Int, host: String, port: Int): Boolean {
        bridges.remove(streamId)?.close()
        val hosts = linkedSetOf("127.0.0.1", host)
        var openedHost: String? = null
        var bridge: AdbTcpBridge? = null
        for (candidate in hosts) {
            if (candidate.isBlank()) {
                continue
            }
            val next = AdbTcpBridge(
                streamId = streamId,
                host = candidate,
                port = port,
                sendData = { id, payload ->
                    client?.send(AdbFrameCodec.encode(AdbFrameCodec.TYPE_DATA, id, payload))
                },
                onClosed = { id ->
                    bridges.remove(id)
                    client?.send(AdbFrameCodec.encode(AdbFrameCodec.TYPE_CLOSE, id))
                },
            )
            if (next.open()) {
                bridge = next
                openedHost = candidate
                break
            }
            Log.w(TAG, "tcp open failed stream=$streamId $candidate:$port, try next")
        }
        if (bridge == null || openedHost == null) {
            client?.send(
                AdbFrameCodec.encode(
                    AdbFrameCodec.TYPE_ERR,
                    streamId,
                    """{"error":"tcp_open_failed","host":"$host","port":$port,"ok":false}""".toByteArray(Charsets.UTF_8),
                ),
            )
            return false
        }
        bridges[streamId] = bridge
        flushPending(streamId, bridge)
        val openPayload =
            """{"host":"$openedHost","port":$port,"ok":true}""".toByteArray(Charsets.UTF_8)
        client?.send(AdbFrameCodec.encode(AdbFrameCodec.TYPE_OPEN, streamId, openPayload))
        Log.i(TAG, "tcp open ok stream=$streamId $openedHost:$port")
        return true
    }

    private fun flushPending(streamId: Int, bridge: AdbTcpBridge) {
        val queued = pendingData.remove(streamId) ?: return
        for (payload in queued) {
            bridge.write(payload)
        }
    }

    private fun onFrame(bytes: ByteArray) {
        val frame = AdbFrameCodec.tryDecode(bytes) ?: return
        when (frame.type) {
            AdbFrameCodec.TYPE_DATA -> {
                val bridge = bridges[frame.streamId]
                if (bridge != null) {
                    bridge.write(frame.payload)
                } else {
                    pendingData
                        .getOrPut(frame.streamId) { CopyOnWriteArrayList() }
                        .add(frame.payload)
                }
            }
            AdbFrameCodec.TYPE_CLOSE, AdbFrameCodec.TYPE_ERR -> {
                pendingData.remove(frame.streamId)
                bridges.remove(frame.streamId)?.close()
            }
            AdbFrameCodec.TYPE_OPEN -> {
                val text = frame.payload.toString(Charsets.UTF_8)
                val json = runCatching { JSONObject(text) }.getOrNull() ?: return
                val host = json.optString("host").trim()
                val port = json.optInt("port", 0)
                if (host.isNotBlank() && port > 0) {
                    ioExecutor.execute {
                        openBridge(frame.streamId, host, port)
                    }
                }
            }
            else -> Log.d(TAG, "ignore frame type=${frame.type}")
        }
    }
}
