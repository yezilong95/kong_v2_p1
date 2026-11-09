package com.vibelink.client.accessibility

import android.graphics.Bitmap
import android.os.Build
import com.vibelink.client.service.RemoteAccessibilityService

object ScreenCaptureBridge {
    fun isSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    fun captureOnce(onComplete: (CaptureResult) -> Unit) {
        if (!isSupported()) {
            onComplete(CaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val service = AccessibilityControlBridge.getService()
            ?: run {
                onComplete(CaptureResult(false, error = "accessibility_service_not_connected"))
                return
            }

        service.captureScreenOnce(onComplete)
    }

    /** Fresh a11y capture, full-quality JPEG in memory. Never writes to gallery. */
    fun captureOnceFull(onComplete: (CaptureResult) -> Unit) {
        if (!isSupported()) {
            onComplete(CaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val service = AccessibilityControlBridge.getService()
            ?: run {
                onComplete(CaptureResult(false, error = "accessibility_service_not_connected"))
                return
            }

        service.captureScreenOnceFull(onComplete)
    }

    /**
     * Accessibility screenshot as ARGB bitmap for WebRTC.
     * Caller owns [BitmapCaptureResult.bitmap] and must recycle it.
     */
    fun captureOnceBitmap(onComplete: (BitmapCaptureResult) -> Unit) {
        if (!isSupported()) {
            onComplete(BitmapCaptureResult(false, error = "screen_capture_unsupported"))
            return
        }

        val service = AccessibilityControlBridge.getService()
            ?: run {
                onComplete(BitmapCaptureResult(false, error = "accessibility_service_not_connected"))
                return
            }

        service.captureScreenOnceBitmap(onComplete)
    }

    /** 当 WebRTC 会话失败重建时调用，防止上一次 takeScreenshot 的回调未返回导致锁死。 */
    fun forceReleaseLock(reason: String) {
        AccessibilityControlBridge.getService()?.forceReleaseCaptureLock(reason)
    }

    data class CaptureResult(
        val ok: Boolean,
        val data: String = "",
        val screenWidth: Int = 0,
        val screenHeight: Int = 0,
        val imageWidth: Int = 0,
        val imageHeight: Int = 0,
        val error: String? = null,
    )

    data class BitmapCaptureResult(
        val ok: Boolean,
        val bitmap: Bitmap? = null,
        val screenWidth: Int = 0,
        val screenHeight: Int = 0,
        val error: String? = null,
    )
}
