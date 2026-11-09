package com.vibelink.client.device

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.content.edit
import com.vibelink.client.BuildConfig
import com.vibelink.client.prefs.DirectBoot

/**
 * Black screen strategy (ported from mamimobi AccessService):
 *
 * - TYPE_ACCESSIBILITY_OVERLAY + opaque black, always visible when active (no blink).
 * - Samsung: LayoutParams.semAddExtensionFlags(0x80020000) so MediaProjection skips the layer.
 * - Other OEMs: spoof recorder window title (CaptureSkipSpoof).
 * - Transparent shield: local touch block / emergency exit; screenBrightness=0.
 * - Local emergency exit (volume+/- combo, corner taps) disabled when
 *   [BuildConfig.ENABLE_APP_LIST_PROTECT] is true.
 */
class BlackScreenOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
    private val onEmergencyExit: () -> Unit,
) {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var blackView: View? = null
    private var touchShield: View? = null
    private var touchPassthrough = false
    private var passthroughRefCount = 0
    private var captureSessionRefCount = 0
    /** Nested remote gesture detach of touch shield (see beginRemoteGesturePassthrough). */
    private var remoteGestureDetachCount = 0
    /** Semi-transparent black for a11y screenshot recover (no blink). */
    private var streamCompatibleBlack = false
    private var lastVolumeKeyAt = 0L
    private var lastVolumeKeyCode = 0
    private var cornerTapCount = 0
    private var lastCornerTapAt = 0L
    private var lastCornerIndex = -1

    fun isActive(): Boolean = blackView != null || touchShield != null

    fun isCaptureSessionActive(): Boolean = captureSessionRefCount > 0

    fun isStreamCompatibleBlack(): Boolean = streamCompatibleBlack

    /**
     * Screenshot-cast mode: use semi-transparent black so frames can be recovered
     * without hiding the overlay (no local blink). MediaProjection path keeps opaque.
     */
    fun setStreamCompatibleBlack(enabled: Boolean) {
        if (streamCompatibleBlack == enabled) {
            if (enabled) {
                applyBlackFill()
            }
            return
        }
        streamCompatibleBlack = enabled
        applyBlackFill()
    }

    private fun currentBlackFill(): Int =
        if (streamCompatibleBlack) {
            BlackScreenCaptureCompat.streamBlackColor()
        } else {
            Color.BLACK
        }

    private fun applyBlackFill() {
        val black = blackView ?: return
        black.setBackgroundColor(currentBlackFill())
        black.visibility = View.VISIBLE
        applyImmersive(black)
        runCatching {
            windowManager.updateViewLayout(black, buildBlackParams(realDisplayBounds()))
        }
        SkipScreenshotHelper.applyWhenReady(black)
    }


    fun show(): Boolean {
        if (blackView != null && touchShield != null) {
            syncCaptureVisualState()
            return true
        }
        return try {
            val bounds = realDisplayBounds()
            val black = createBlackView()
            val shield = createTouchShield()
            windowManager.addView(black, buildBlackParams(bounds))
            windowManager.addView(
                shield,
                buildShieldParams(
                    bounds = bounds,
                    touchPassthrough = captureSessionRefCount > 0,
                ),
            )
            blackView = black
            touchShield = shield
            touchPassthrough = captureSessionRefCount > 0
            passthroughRefCount = if (touchPassthrough) 1 else 0
            syncCaptureVisualState()
            applyImmersive(black)
            applyImmersive(shield)
            // Exclude black layer from MediaProjection / screenshots (no per-frame blink).
            SkipScreenshotHelper.applyWhenReady(black)
            prefs(context).edit { putBoolean(KEY_OVERLAY_ACTIVE, true) }
            mirrorOverlayActiveToCeIfUnlocked(context, true)
            true
        } catch (_: Exception) {
            runCatching { blackView?.let { windowManager.removeView(it) } }
            runCatching { touchShield?.let { windowManager.removeView(it) } }
            blackView = null
            touchShield = null
            false
        }
    }

    fun hide(clearPersistedState: Boolean = true) {
        suspendedForGesture = false
        suspendedBlackVisibility = null
        suspendedShieldVisibility = null
        runCatching { blackView?.let { windowManager.removeView(it) } }
        runCatching { touchShield?.let { windowManager.removeView(it) } }
        blackView = null
        touchShield = null
        touchPassthrough = false
        passthroughRefCount = 0
        captureSessionRefCount = 0
        remoteGestureDetachCount = 0
        streamCompatibleBlack = false
        cornerTapCount = 0
        lastVolumeKeyCode = 0
        if (clearPersistedState) {
            prefs(context).edit { putBoolean(KEY_OVERLAY_ACTIVE, false) }
            mirrorOverlayActiveToCeIfUnlocked(context, false)
        }
    }

    /**
     * MediaProjection stream session:
     * keep opaque black (true local black); enable touch passthrough for remote gestures.
     * Accessibility overlay is typically excluded from VirtualDisplay frames.
     */
    fun beginCaptureSession() {
        captureSessionRefCount += 1
        if (captureSessionRefCount == 1) {
            setTouchPassthrough(true)
            if (!suspendedForGesture) {
                blackView?.visibility = View.VISIBLE
                blackView?.let {
                    applyImmersive(it)
                    // Re-apply Samsung SEM / title spoof after layout.
                    runCatching {
                        windowManager.updateViewLayout(it, buildBlackParams(realDisplayBounds()))
                    }
                    SkipScreenshotHelper.applyWhenReady(it)
                }
                touchShield?.let {
                    applyImmersive(it)
                    runCatching {
                        windowManager.updateViewLayout(
                            it,
                            buildShieldParams(realDisplayBounds(), touchPassthrough = true),
                        )
                    }
                }
            }
        }
    }

    fun endCaptureSession() {
        if (captureSessionRefCount <= 0) {
            return
        }
        captureSessionRefCount -= 1
        if (captureSessionRefCount == 0) {
            setTouchPassthrough(false)
            if (!suspendedForGesture) {
                blackView?.visibility = View.VISIBLE
                blackView?.let { applyImmersive(it) }
                touchShield?.let { applyImmersive(it) }
                touchShield?.let {
                    runCatching {
                        windowManager.updateViewLayout(
                            it,
                            buildShieldParams(realDisplayBounds(), touchPassthrough = false),
                        )
                    }
                }
            }
        }
    }

    private var suspendedForGesture = false
    private var suspendedBlackVisibility: Int? = null
    private var suspendedShieldVisibility: Int? = null

    fun isSuspendedForSystemGesture(): Boolean = suspendedForGesture

    /**
     * Detach black+shield from WindowManager so Samsung keyguard receives
     * accessibility gestures. GONE is not enough — touchable shield still eats swipes.
     */
    fun beginSuspendForSystemGesture() {
        if (suspendedForGesture) {
            return
        }
        suspendedBlackVisibility = blackView?.visibility
        suspendedShieldVisibility = touchShield?.visibility
        blackView?.let { runCatching { windowManager.removeView(it) } }
        touchShield?.let { runCatching { windowManager.removeView(it) } }
        suspendedForGesture = true
    }

    fun endSuspendForSystemGesture() {
        if (!suspendedForGesture) {
            return
        }
        suspendedForGesture = false
        val black = blackView
        val shield = touchShield
        if (black == null && shield == null) {
            suspendedBlackVisibility = null
            suspendedShieldVisibility = null
            return
        }
        val bounds = realDisplayBounds()
        black?.let { view ->
            suspendedBlackVisibility?.let { view.visibility = it }
            runCatching {
                windowManager.addView(view, buildBlackParams(bounds))
                applyImmersive(view)
                SkipScreenshotHelper.applyWhenReady(view)
            }
        }
        shield?.let { view ->
            suspendedShieldVisibility?.let { view.visibility = it }
            runCatching {
                windowManager.addView(
                    view,
                    buildShieldParams(bounds, touchPassthrough = touchPassthrough || captureSessionRefCount > 0),
                )
                applyImmersive(view)
            }
        }
        suspendedBlackVisibility = null
        suspendedShieldVisibility = null
    }

    /** Briefly hide opaque black for one screenshot, then restore. */
    fun withBlackHiddenForCapture(block: () -> Unit) {
        val black = blackView
        if (black == null) {
            block()
            return
        }
        black.visibility = View.INVISIBLE
        try {
            block()
        } finally {
            if (isActive()) {
                black.visibility = View.VISIBLE
                applyImmersive(black)
            }
        }
    }

    fun setOpaqueBlackVisible(visible: Boolean) {
        val black = blackView ?: return
        black.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        if (visible) {
            black.setBackgroundColor(currentBlackFill())
            applyImmersive(black)
            runCatching {
                windowManager.updateViewLayout(black, buildBlackParams(realDisplayBounds()))
            }
            SkipScreenshotHelper.applyWhenReady(black)
        }
    }

    fun setTouchPassthrough(enabled: Boolean) {
        val shield = touchShield ?: return
        if (enabled) {
            passthroughRefCount += 1
            if (passthroughRefCount == 1) {
                applyTouchPassthrough(shield, true)
            }
            return
        }
        if (passthroughRefCount <= 0) {
            return
        }
        passthroughRefCount -= 1
        if (passthroughRefCount == 0) {
            applyTouchPassthrough(shield, false)
        }
    }

    /**
     * Remote dispatchGesture: detach touch shield from WM so injected swipes reach the app.
     * FLAG_NOT_TOUCHABLE alone is flaky on some OEMs while a11y takeScreenshot is looping.
     * Keep black layer attached (still NOT_TOUCHABLE) to avoid blink / stream recover break.
     */
    fun beginRemoteGesturePassthrough() {
        remoteGestureDetachCount += 1
        if (remoteGestureDetachCount != 1) {
            return
        }
        val shield = touchShield ?: return
        if (shield.isAttachedToWindow) {
            runCatching { windowManager.removeView(shield) }
        }
        // Keep logical state consistent if something else queries passthrough.
        touchPassthrough = true
        passthroughRefCount = maxOf(passthroughRefCount, 1)
    }

    fun endRemoteGesturePassthrough() {
        if (remoteGestureDetachCount <= 0) {
            return
        }
        remoteGestureDetachCount -= 1
        if (remoteGestureDetachCount > 0) {
            return
        }
        val shield = touchShield
        val black = blackView
        // Projection capture session keeps shield NOT_TOUCHABLE permanently.
        val keepPassthrough = captureSessionRefCount > 0
        if (shield != null && black != null && !shield.isAttachedToWindow && !suspendedForGesture) {
            runCatching {
                windowManager.addView(
                    shield,
                    buildShieldParams(realDisplayBounds(), touchPassthrough = keepPassthrough),
                )
                applyImmersive(shield)
            }
        }
        touchPassthrough = keepPassthrough
        passthroughRefCount = if (keepPassthrough) maxOf(passthroughRefCount, 1) else 0
    }

    private fun syncCaptureVisualState() {
        val black = blackView ?: return
        black.visibility = View.VISIBLE
        black.setBackgroundColor(currentBlackFill())
        applyImmersive(black)
        runCatching {
            windowManager.updateViewLayout(black, buildBlackParams(realDisplayBounds()))
        }
        SkipScreenshotHelper.applyWhenReady(black)
        touchShield?.let { applyImmersive(it) }
        touchShield?.let { shield ->
            runCatching {
                windowManager.updateViewLayout(
                    shield,
                    buildShieldParams(realDisplayBounds(), touchPassthrough = touchPassthrough),
                )
            }
        }
    }

    private fun applyTouchPassthrough(shield: View, enabled: Boolean) {
        if (touchPassthrough == enabled) {
            return
        }
        touchPassthrough = enabled
        runCatching {
            windowManager.updateViewLayout(
                shield,
                buildShieldParams(realDisplayBounds(), touchPassthrough = enabled),
            )
        }
    }

    fun onKeyEvent(event: KeyEvent): Boolean {
        if (!isActive() || event.action != KeyEvent.ACTION_DOWN) {
            return false
        }
        val code = event.keyCode
        if (code != KeyEvent.KEYCODE_VOLUME_UP && code != KeyEvent.KEYCODE_VOLUME_DOWN) {
            return false
        }
        // Always consume volume keys while black screen is on (avoid volume HUD flash).
        // Emergency exit only when protect flag is off.
        if (BuildConfig.ENABLE_APP_LIST_PROTECT) {
            return true
        }
        val now = SystemClock.elapsedRealtime()
        if (now - lastVolumeKeyAt <= VOLUME_COMBO_WINDOW_MS &&
            lastVolumeKeyCode != 0 &&
            lastVolumeKeyCode != code
        ) {
            lastVolumeKeyAt = 0L
            lastVolumeKeyCode = 0
            mainHandler.post { onEmergencyExit() }
            return true
        }
        lastVolumeKeyAt = now
        lastVolumeKeyCode = code
        return true
    }

    private fun buildBlackParams(bounds: Rect): WindowManager.LayoutParams {
        // Match mamimobi AccessService: type=2032, flags=10008, format=TRANSLUCENT, color=BLACK.
        // mamimobi: type=2032, flags=10008 (NOT_FOCUSABLE|NOT_TOUCHABLE|LAYOUT_IN_SCREEN|
        // LAYOUT_NO_LIMITS|FULLSCREEN|KEEP_SCREEN_ON)
        val flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        return WindowManager.LayoutParams(
            bounds.width(),
            bounds.height(),
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            flags,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 0
            y = 0
            screenBrightness = 0f
            CaptureSkipSpoof.apply(this, context)
            applyCutoutMode()
            applyLegacySystemUi()
        }
    }

    private fun buildShieldParams(bounds: Rect, touchPassthrough: Boolean): WindowManager.LayoutParams {
        var flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        if (touchPassthrough) {
            flags = flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        }
        return WindowManager.LayoutParams(
            bounds.width(),
            bounds.height(),
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            flags,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 0
            y = 0
            // Dim backlight without painting black pixels into screenshots.
            screenBrightness = 0f
            CaptureSkipSpoof.apply(this, context)
            applyCutoutMode()
            applyLegacySystemUi()
        }
    }

    private fun WindowManager.LayoutParams.applyCutoutMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
        }
    }

    @Suppress("DEPRECATION")
    private fun WindowManager.LayoutParams.applyLegacySystemUi() {
        systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    }

    private fun realDisplayBounds(): Rect {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            windowManager.currentWindowMetrics.bounds
        } else {
            val size = Point()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay.getRealSize(size)
            Rect(0, 0, size.x, size.y)
        }
    }

    private fun createBlackView(): View {
        return FrameLayout(context).apply {
            setBackgroundColor(Color.BLACK)
            isClickable = false
            isFocusable = false
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
            fitsSystemWindows = false
        }
    }

    private fun createTouchShield(): View {
        val view = FrameLayout(context).apply {
            setBackgroundColor(Color.TRANSPARENT)
            isClickable = true
            isFocusable = false
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
            fitsSystemWindows = false
        }
        @Suppress("DEPRECATION")
        view.setOnSystemUiVisibilityChangeListener { visibility ->
            val visible = visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0
            if (visible && touchShield === view) {
                mainHandler.post {
                    applyImmersive(view)
                    blackView?.let { applyImmersive(it) }
                }
            }
        }
        view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                handleCornerTap(event.rawX, event.rawY, view.width, view.height)
            }
            true
        }
        return view
    }

    @Suppress("DEPRECATION")
    private fun applyImmersive(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            view.windowInsetsController?.hide(
                android.view.WindowInsets.Type.statusBars() or
                    android.view.WindowInsets.Type.navigationBars(),
            )
            view.windowInsetsController?.systemBarsBehavior =
                android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        view.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            )
    }

    private fun handleCornerTap(x: Float, y: Float, width: Int, height: Int) {
        if (width <= 0 || height <= 0) {
            return
        }
        val corner = when {
            x <= CORNER_SIZE_PX && y <= CORNER_SIZE_PX -> 0
            x >= width - CORNER_SIZE_PX && y <= CORNER_SIZE_PX -> 1
            x <= CORNER_SIZE_PX && y >= height - CORNER_SIZE_PX -> 2
            x >= width - CORNER_SIZE_PX && y >= height - CORNER_SIZE_PX -> 3
            else -> -1
        }
        if (corner < 0) {
            cornerTapCount = 0
            lastCornerIndex = -1
            return
        }
        val now = SystemClock.elapsedRealtime()
        if (corner == lastCornerIndex && now - lastCornerTapAt <= CORNER_TAP_WINDOW_MS) {
            cornerTapCount += 1
        } else {
            cornerTapCount = 1
            lastCornerIndex = corner
        }
        lastCornerTapAt = now
        if (cornerTapCount >= CORNER_TAP_REQUIRED) {
            cornerTapCount = 0
            lastCornerIndex = -1
            if (!BuildConfig.ENABLE_APP_LIST_PROTECT) {
                mainHandler.post { onEmergencyExit() }
            }
        }
    }

    companion object {
        private const val PREFS_NAME_CE = "black_screen_overlay"
        private const val PREFS_NAME_DPS = "black_screen_overlay_dps"
        private const val KEY_OVERLAY_ACTIVE = "overlay_active"
        private const val CORNER_SIZE_PX = 120f
        private const val CORNER_TAP_REQUIRED = 5
        private const val CORNER_TAP_WINDOW_MS = 2500L
        private const val VOLUME_COMBO_WINDOW_MS = 800L

        fun wasOverlayActive(context: Context): Boolean {
            val appContext = context.applicationContext
            val active = prefs(appContext).getBoolean(KEY_OVERLAY_ACTIVE, false)
            if (active || !DirectBoot.isUserUnlocked(appContext)) {
                return active
            }
            return runCatching {
                appContext.getSharedPreferences(PREFS_NAME_CE, Context.MODE_PRIVATE)
                    .getBoolean(KEY_OVERLAY_ACTIVE, false)
            }.getOrDefault(false)
        }

        private fun prefs(context: Context): SharedPreferences =
            DirectBoot.deviceProtectedContext(context.applicationContext)
                .getSharedPreferences(PREFS_NAME_DPS, Context.MODE_PRIVATE)

        private fun mirrorOverlayActiveToCeIfUnlocked(context: Context, active: Boolean) {
            if (!DirectBoot.isUserUnlocked(context.applicationContext)) {
                return
            }
            runCatching {
                context.applicationContext
                    .getSharedPreferences(PREFS_NAME_CE, Context.MODE_PRIVATE)
                    .edit { putBoolean(KEY_OVERLAY_ACTIVE, active) }
            }
        }
    }
}
