package com.vibelink.client.device

import android.graphics.Bitmap
import android.graphics.Color

/**
 * Samsung One UI (and similar OEMs) composite accessibility overlays into MediaProjection.
 * Title-spoof / setSkipScreenshot often fail on One UI 5.x (e.g. SM-A145P).
 *
 * Stream-compatible black: semi-transparent black layer stays visible locally
 * (with screenBrightness=0). MediaProjection sees darkened frames; we reverse the
 * alpha blend to recover the underlying UI for Web — no per-frame blink.
 */
object BlackScreenCaptureCompat {
    /** Must match [BlackScreenOverlayController] stream-mode overlay alpha. */
    const val STREAM_BLACK_ALPHA = 200

    fun streamBlackColor(): Int = Color.argb(STREAM_BLACK_ALPHA, 0, 0, 0)

    /**
     * Recover UI from a frame composited under a fullscreen black overlay of [STREAM_BLACK_ALPHA].
     * composed = bg * (1 - a)  →  bg = composed / (1 - a)
     */
    fun recoverFromStreamBlackOverlay(source: Bitmap): Bitmap {
        val alpha = STREAM_BLACK_ALPHA / 255f
        val inv = 1f / (1f - alpha).coerceAtLeast(0.08f)
        val mutable = if (source.isMutable) source else source.copy(Bitmap.Config.ARGB_8888, true)
        val width = mutable.width
        val height = mutable.height
        val pixels = IntArray(width * height)
        mutable.getPixels(pixels, 0, width, 0, 0, width, height)
        for (i in pixels.indices) {
            val c = pixels[i]
            val r = ((((c ushr 16) and 0xff) * inv).toInt()).coerceIn(0, 255)
            val g = ((((c ushr 8) and 0xff) * inv).toInt()).coerceIn(0, 255)
            val b = (((c and 0xff) * inv).toInt()).coerceIn(0, 255)
            pixels[i] = (0xff shl 24) or (r shl 16) or (g shl 8) or b
        }
        mutable.setPixels(pixels, 0, width, 0, 0, width, height)
        return mutable
    }
}
