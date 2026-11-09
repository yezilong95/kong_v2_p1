package com.vibelink.client.device

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Base64
import org.json.JSONObject
import java.io.ByteArrayOutputStream

object AppIconHelper {
    fun loadIconBase64(context: Context, packageName: String, sizePx: Int = 192): JSONObject? {
        val pkg = packageName.trim()
        if (pkg.isBlank()) {
            return null
        }
        val pm = context.packageManager
        return runCatching {
            val appInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pm.getApplicationInfo(pkg, android.content.pm.PackageManager.ApplicationInfoFlags.of(0))
            } else {
                @Suppress("DEPRECATION")
                pm.getApplicationInfo(pkg, 0)
            }
            val label = pm.getApplicationLabel(appInfo).toString().ifBlank { pkg }
            val drawable = pm.getApplicationIcon(appInfo)
            val bitmap = drawableToBitmap(drawable, sizePx)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val base64 = Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
            JSONObject()
                .put("packageName", pkg)
                .put("label", label)
                .put("icon", base64)
                .put("mimeType", "image/png")
                .put("size", sizePx)
        }.getOrNull()
    }

    private fun drawableToBitmap(drawable: Drawable, sizePx: Int): Bitmap {
        if (drawable is BitmapDrawable && drawable.bitmap != null) {
            return Bitmap.createScaledBitmap(drawable.bitmap, sizePx, sizePx, true)
        }
        val bitmap = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, sizePx, sizePx)
        drawable.draw(canvas)
        return bitmap
    }
}
