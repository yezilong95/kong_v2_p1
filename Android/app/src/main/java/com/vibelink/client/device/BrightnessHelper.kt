package com.vibelink.client.device

import android.content.Context
import android.provider.Settings
import org.json.JSONObject

object BrightnessHelper {
    /** System brightness scale is 0–255; keep 1 so some OEMs don't treat 0 as “off”. */
    private const val MIN_BRIGHTNESS = 1

    data class SetResult(
        val ok: Boolean,
        val error: String? = null,
        val brightness: Int = -1,
        val mode: Int = -1,
        val writeSettingsGranted: Boolean = false,
    )

    fun canWriteSettings(context: Context): Boolean = RotationHelper.canWriteSettings(context)

    fun setMinimum(context: Context): SetResult {
        val writeGranted = canWriteSettings(context)
        if (!writeGranted) {
            return SetResult(
                ok = false,
                error = "write_settings_required",
                writeSettingsGranted = false,
            )
        }
        return runCatching {
            val resolver = context.contentResolver
            Settings.System.putInt(
                resolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL,
            )
            Settings.System.putInt(
                resolver,
                Settings.System.SCREEN_BRIGHTNESS,
                MIN_BRIGHTNESS,
            )
            val brightness = Settings.System.getInt(
                resolver,
                Settings.System.SCREEN_BRIGHTNESS,
                MIN_BRIGHTNESS,
            )
            val mode = Settings.System.getInt(
                resolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL,
            )
            // Some OEMs clamp floor above 1; accept any value at/near minimum.
            if (brightness <= 10 && mode == Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL) {
                SetResult(
                    ok = true,
                    brightness = brightness,
                    mode = mode,
                    writeSettingsGranted = true,
                )
            } else {
                SetResult(
                    ok = false,
                    error = "brightness_set_failed",
                    brightness = brightness,
                    mode = mode,
                    writeSettingsGranted = true,
                )
            }
        }.getOrElse { error ->
            SetResult(
                ok = false,
                error = error.message ?: "brightness_set_failed",
                writeSettingsGranted = writeGranted,
            )
        }
    }

    fun toJson(result: SetResult): JSONObject {
        return JSONObject()
            .put("action", "set_brightness_min")
            .put("brightness", result.brightness)
            .put("mode", result.mode)
            .put("writeSettingsGranted", result.writeSettingsGranted)
    }
}
