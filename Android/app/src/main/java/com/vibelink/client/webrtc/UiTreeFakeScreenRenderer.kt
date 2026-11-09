package com.vibelink.client.webrtc

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.view.accessibility.AccessibilityNodeInfo
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.max
import kotlin.math.min

/**
 * Competitor-style ScreenHelper: draw accessibility leaf bounds + labels onto a bitmap.
 */
object UiTreeFakeScreenRenderer {
    private val boxPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        color = Color.parseColor("#4CAF50")
    }
    private val textBgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.argb(160, 0, 0, 0)
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 22f
        typeface = Typeface.DEFAULT
    }

    fun renderFromDump(
        dump: JSONObject,
        outWidth: Int = 720,
        outHeight: Int = 1280,
    ): Bitmap {
        val screenWidth = dump.optInt("screenWidth").takeIf { it > 0 } ?: outWidth
        val screenHeight = dump.optInt("screenHeight").takeIf { it > 0 } ?: outHeight
        val nodes = dump.optJSONArray("nodes") ?: JSONArray()
        val bitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.BLACK)
        val scaleX = outWidth.toFloat() / screenWidth.toFloat()
        val scaleY = outHeight.toFloat() / screenHeight.toFloat()

        for (i in 0 until nodes.length()) {
            val node = nodes.optJSONObject(i) ?: continue
            if (node.optBoolean("ignored", false)) {
                continue
            }
            val bounds = node.optJSONObject("bounds") ?: continue
            val left = bounds.optInt("left")
            val top = bounds.optInt("top")
            val right = bounds.optInt("right")
            val bottom = bounds.optInt("bottom")
            if (right <= left || bottom <= top) {
                continue
            }
            val rect = Rect(
                (left * scaleX).toInt(),
                (top * scaleY).toInt(),
                (right * scaleX).toInt(),
                (bottom * scaleY).toInt(),
            )
            if (rect.width() < 2 || rect.height() < 2) {
                continue
            }
            canvas.drawRect(rect, boxPaint)
            val label = nodeLabel(node)
            if (label.isBlank()) {
                continue
            }
            drawLabel(canvas, rect, label)
        }
        return bitmap
    }

    fun renderFromRoot(
        root: AccessibilityNodeInfo?,
        screenWidth: Int,
        screenHeight: Int,
        outWidth: Int = 720,
        outHeight: Int = 1280,
    ): Bitmap {
        val bitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.BLACK)
        if (root == null || screenWidth <= 0 || screenHeight <= 0) {
            return bitmap
        }
        val scaleX = outWidth.toFloat() / screenWidth.toFloat()
        val scaleY = outHeight.toFloat() / screenHeight.toFloat()
        val visited = HashSet<Int>()
        drawNode(root, canvas, scaleX, scaleY, visited)
        return bitmap
    }

    private fun drawNode(
        node: AccessibilityNodeInfo,
        canvas: Canvas,
        scaleX: Float,
        scaleY: Float,
        visited: HashSet<Int>,
        inheritedLabel: String = "",
    ) {
        val id = System.identityHashCode(node)
        if (!visited.add(id)) {
            return
        }
        val ownLabel = node.text?.toString()?.trim().orEmpty()
            .ifEmpty { node.contentDescription?.toString()?.trim().orEmpty() }
        val effectiveLabel = ownLabel.ifEmpty { inheritedLabel }
        val downLabel = resolvePropagatedLabel(node, ownLabel, inheritedLabel)

        val childCount = node.childCount
        if (childCount > 0 && ownLabel.isNotBlank() && node.isVisibleToUser) {
            drawLabeledBounds(node, canvas, scaleX, scaleY, ownLabel)
        }
        for (i in 0 until childCount) {
            val child = node.getChild(i) ?: continue
            if (child.isVisibleToUser) {
                drawNode(child, canvas, scaleX, scaleY, visited, downLabel)
            }
            child.recycle()
        }
        if (childCount > 0) {
            return
        }
        if (!node.isVisibleToUser) {
            return
        }
        drawLabeledBounds(node, canvas, scaleX, scaleY, effectiveLabel)
    }

    private fun resolvePropagatedLabel(
        node: AccessibilityNodeInfo,
        ownLabel: String,
        inheritedLabel: String,
    ): String {
        if (ownLabel.isEmpty()) {
            return inheritedLabel
        }
        val interactive = node.isClickable || node.isCheckable || node.isFocusable
        if (!interactive && ownLabel.length > 16) {
            return inheritedLabel
        }
        return ownLabel
    }

    private fun drawLabeledBounds(
        node: AccessibilityNodeInfo,
        canvas: Canvas,
        scaleX: Float,
        scaleY: Float,
        label: String,
    ) {
        val bounds = Rect()
        node.getBoundsInScreen(bounds)
        if (bounds.isEmpty) {
            return
        }
        val rect = Rect(
            (bounds.left * scaleX).toInt(),
            (bounds.top * scaleY).toInt(),
            (bounds.right * scaleX).toInt(),
            (bounds.bottom * scaleY).toInt(),
        )
        if (rect.width() < 2 || rect.height() < 2) {
            return
        }
        canvas.drawRect(rect, boxPaint)
        if (label.isNotBlank()) {
            drawLabel(canvas, rect, label)
        }
    }

    private fun nodeLabel(node: JSONObject): String {
        val text = node.optString("text").trim()
        if (text.isNotEmpty()) {
            return text
        }
        val desc = node.optString("desc").ifBlank { node.optString("contentDescription") }.trim()
        if (desc.isNotEmpty()) {
            return desc
        }
        return node.optString("viewId").substringAfter('/').takeIf { it.isNotBlank() } ?: ""
    }

    private fun drawLabel(canvas: Canvas, rect: Rect, label: String) {
        val clipped = if (label.length > 28) label.take(27) + "…" else label
        textPaint.textSize = 22f
        var textWidth = textPaint.measureText(clipped)
        if (textWidth > rect.width() && rect.width() > 0) {
            textPaint.textSize = max(12f, rect.width() * 22f / textWidth)
            textWidth = textPaint.measureText(clipped)
        }
        val fm = textPaint.fontMetrics
        val pad = 6f
        val left = max(4f, min(rect.left + 4f, canvas.width - textWidth - pad * 2))
        val cy = rect.exactCenterY()
        val top = cy + (fm.ascent + fm.descent) / 2f - (fm.descent - fm.ascent) / 2f - pad
        val bg = Rect(
            left.toInt(),
            top.toInt(),
            (left + textWidth + pad * 2).toInt(),
            (top + (fm.descent - fm.ascent) + pad * 2).toInt(),
        )
        canvas.drawRect(bg, textBgPaint)
        canvas.drawText(clipped, left + pad, cy - (fm.ascent + fm.descent) / 2f, textPaint)
    }
}
