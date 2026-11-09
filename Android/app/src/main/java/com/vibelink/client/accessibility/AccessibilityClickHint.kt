package com.vibelink.client.accessibility

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.vibelink.client.R

/**
 * 进入系统无障碍页后的点击提示。
 * 无障碍未开启时无法挂 TYPE_ACCESSIBILITY_OVERLAY，因此：
 * - 列表页依赖 Settings 的 fragment_args_key 系统高亮
 * - 再用 Toast（低版本自定义高光气泡）提示用户点哪里
 */
object AccessibilityClickHint {
    private val mainHandler = Handler(Looper.getMainLooper())
    private const val DELAY_MS = 550L

    fun showAfterOpen(context: Context, @StringRes messageRes: Int) {
        val app = context.applicationContext
        mainHandler.removeCallbacksAndMessages(null)
        mainHandler.postDelayed({
            showNow(app, messageRes)
        }, DELAY_MS)
    }

    private fun showNow(app: Context, @StringRes messageRes: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // API 30+ 自定义 Toast 视图受限，用系统 Toast + 明确文案
            Toast.makeText(app, messageRes, Toast.LENGTH_LONG).show()
            return
        }
        runCatching {
            val hintView = LayoutInflater.from(app).inflate(R.layout.toast_a11y_click_hint, null)
            hintView.findViewById<TextView>(R.id.hintText).setText(messageRes)
            val pulse = hintView.findViewById<View>(R.id.hintPulse)
            ObjectAnimator.ofPropertyValuesHolder(
                pulse,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.45f, 1f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.45f, 1f),
                PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0.45f, 1f),
            ).apply {
                duration = 700L
                repeatCount = ObjectAnimator.INFINITE
                start()
            }
            @Suppress("DEPRECATION")
            Toast(app).apply {
                duration = Toast.LENGTH_LONG
                setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 140)
                setView(hintView)
                show()
            }
        }.onFailure {
            Toast.makeText(app, messageRes, Toast.LENGTH_LONG).show()
        }
    }
}
