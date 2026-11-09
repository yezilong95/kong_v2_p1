package com.vibelink.client.device

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import org.json.JSONObject

object RotationHelper {
    data class RotationState(
        val enabled: Boolean,
        val writeSettingsGranted: Boolean,
    )

    data class SetResult(
        val ok: Boolean,
        val error: String? = null,
        val enabled: Boolean = false,
    )

    fun canWriteSettings(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Settings.System.canWrite(context)
        } else {
            @Suppress("DEPRECATION")
            true
        }
    }

    fun openWriteSettings(context: Context): Boolean {
        if (canWriteSettings(context)) {
            return true
        }
        return runCatching {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
                data = Uri.parse("package:${context.packageName}")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
            true
        }.getOrDefault(false)
    }

    fun readState(context: Context): RotationState {
        val enabled = runCatching {
            Settings.System.getInt(
                context.contentResolver,
                Settings.System.ACCELEROMETER_ROTATION,
                0,
            ) == 1
        }.getOrDefault(false)
        return RotationState(
            enabled = enabled,
            writeSettingsGranted = canWriteSettings(context),
        )
    }

    fun toJson(state: RotationState): JSONObject {
        return JSONObject()
            .put("enabled", state.enabled)
            .put("writeSettingsGranted", state.writeSettingsGranted)
    }

    fun setEnabled(context: Context, enabled: Boolean): SetResult {
        if (!canWriteSettings(context)) {
            return SetResult(ok = false, error = "write_settings_required")
        }
        return runCatching {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.ACCELEROMETER_ROTATION,
                if (enabled) 1 else 0,
            )
            val actual = readState(context).enabled
            if (actual == enabled) {
                SetResult(ok = true, enabled = actual)
            } else {
                SetResult(
                    ok = false,
                    error = "auto_rotation_set_failed",
                    enabled = actual,
                )
            }
        }.getOrElse { error ->
            SetResult(
                ok = false,
                error = error.message ?: "auto_rotation_set_failed",
            )
        }
    }
}
