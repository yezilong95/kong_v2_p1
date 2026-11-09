package com.vibelink.client.device

import android.content.Context
import android.os.Build
import android.view.WindowManager

/**
 * Port of mamimobi AccessService overlay skip logic.
 *
 * Samsung (One UI): [WindowManager.LayoutParams.semAddExtensionFlags] with
 * `0x80020000` (-2147352576) — this is what excludes the overlay from MediaProjection.
 * Other OEMs: spoof window title to their screen-recorder float component.
 */
object CaptureSkipSpoof {
    private const val TAG = "CaptureSkipSpoof"

    /** Same constant mamimobi passes to semAddExtensionFlags. */
    private const val SAMSUNG_SEM_EXTENSION_SKIP_CAPTURE = -2147352576 // 0x80020000

    private const val SAMSUNG_SMART_CAPTURE = "com.samsung.android.app.smartcapture"
    private const val SAMSUNG_SCREEN_RECORDER = "com.samsung.android.app.screenrecorder"

    fun apply(params: WindowManager.LayoutParams, context: Context? = null) {
        params.dimAmount = 0f
        val manufacturer = Build.MANUFACTURER.orEmpty().lowercase()
        val brand = Build.BRAND.orEmpty().lowercase()
        val haystack = "$manufacturer $brand"

        when {
            "samsung" in haystack -> applySamsung(params, context)
            "xiaomi" in haystack || "redmi" in haystack || "poco" in haystack ||
                hasProp("ro.miui.ui.version.name") -> {
                params.flags = params.flags or 0x2000
                params.title = "com.miui.screenrecorder"
            }
            "oneplus" in haystack || "oppo" in haystack || "realme" in haystack ||
                hasProp("ro.oxygen.version") || hasProp("ro.build.version.opporom") -> {
                // mamimobi uses oplus title for OnePlus/Realme; coloros for Oppo.
                params.title = if ("oppo" in haystack && "oneplus" !in haystack && "realme" !in haystack) {
                    "com.coloros.screenrecorder.FloatView"
                } else {
                    "com.oplus.screenrecorder.FloatView"
                }
            }
            "vivo" in haystack || hasProp("ro.vivo.os.version") ->
                params.title = "screen_record_menu"
            "huawei" in haystack || "honor" in haystack || hasProp("ro.build.version.emui") ->
                params.title = "ScreenRecorderTimer"
            "meizu" in haystack || "flyme" in Build.DISPLAY.orEmpty().lowercase() ->
                params.title = "SysScreenRecorder"
            "nubia" in haystack -> params.title = "NubiaScreenDecorOverlay"
            "blackshark" in haystack -> params.title = "com.blackshark.screenrecorder"
            "asus" in haystack -> {
                params.title = "com.asus.force.layer.transparent.SR.floatingpanel"
                runCatching { params.memoryType = params.memoryType or 0x10000000 }
            }
            else -> params.title = "FloatWindow"
        }
    }

    /**
     * Exact Samsung path from mamimobi AccessService.c() case 6.
     */
    private fun applySamsung(params: WindowManager.LayoutParams, context: Context?) {
        // mamimobi case 6: keep default title "FloatWindow"; only SEM flags matter.
        params.title = "FloatWindow"
        try {
            val intCls = Integer.TYPE
            val semAddExtensionFlags = params.javaClass.getMethod("semAddExtensionFlags", intCls)
            val semAddPrivateFlags = params.javaClass.getMethod("semAddPrivateFlags", intCls)
            semAddExtensionFlags.invoke(params, SAMSUNG_SEM_EXTENSION_SKIP_CAPTURE)
            // Exact invoke signature from AccessService: Integer.valueOf(layoutParams.flags)
            semAddPrivateFlags.invoke(params, Integer.valueOf(params.flags))
            android.util.Log.i(TAG, "samsung semAddExtensionFlags(0x80020000) ok")
        } catch (t: Throwable) {
            android.util.Log.w(TAG, "samsung SEM flags failed: ${t.message}")
            // Non-Sem builds: title spoof fallback (SmartCapture / ScreenRecorder).
            params.title = when {
                context != null && isPackageInstalled(context, SAMSUNG_SMART_CAPTURE) ->
                    SAMSUNG_SMART_CAPTURE
                else -> SAMSUNG_SCREEN_RECORDER
            }
        }
    }

    private fun isPackageInstalled(context: Context, packageName: String): Boolean {
        return runCatching {
            context.packageManager.getPackageInfo(packageName, 0)
            true
        }.getOrDefault(false)
    }

    private fun hasProp(key: String): Boolean {
        return runCatching {
            val process = Runtime.getRuntime().exec(arrayOf("getprop", key))
            process.inputStream.bufferedReader().use { it.readLine().orEmpty() }.isNotBlank()
        }.getOrDefault(false)
    }
}
