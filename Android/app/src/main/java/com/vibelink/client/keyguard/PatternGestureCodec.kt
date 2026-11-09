package com.vibelink.client.keyguard

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.graphics.Rect
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

/**
 * mamimobi-compatible absolute gesture PATH:
 * `[{ "x": float, "y": float, "action": "d"|"m" }, ...]`
 */
object PatternGestureCodec {
    private const val TAG = "PatternCapture"
    /** mamimobi pk0.a stroke duration */
    const val DEFAULT_REPLAY_DURATION_MS = 350L
    private const val MIN_POINTS = 2
    private const val MIN_CELLS = 4

    fun encode(points: List<Pair<Float, Float>>): String {
        if (points.size < MIN_POINTS) {
            return "[]"
        }
        val array = JSONArray()
        points.forEachIndexed { index, (x, y) ->
            array.put(
                JSONObject()
                    .put("x", x.toDouble())
                    .put("y", y.toDouble())
                    .put("action", if (index == 0) "d" else "m"),
            )
        }
        return array.toString()
    }

    fun decode(raw: String?): List<Pair<Float, Float>> {
        if (raw.isNullOrBlank() || raw == "[]") {
            return emptyList()
        }
        return runCatching {
            val array = JSONArray(raw)
            buildList(array.length()) {
                for (index in 0 until array.length()) {
                    val item = array.optJSONObject(index) ?: continue
                    add(item.optDouble("x").toFloat() to item.optDouble("y").toFloat())
                }
            }
        }.getOrDefault(emptyList())
    }

    fun parseCells(raw: String?): List<Int> {
        if (raw.isNullOrBlank()) {
            return emptyList()
        }
        return raw.split('-', ',', ' ')
            .mapNotNull { it.trim().toIntOrNull() }
            .filter { it in 1..9 }
            .fold(mutableListOf()) { acc, cell ->
                if (acc.lastOrNull() != cell) {
                    acc.add(cell)
                }
                acc
            }
    }

    fun pointsFromCells(bounds: Rect, cells: List<Int>): List<Pair<Float, Float>> {
        if (cells.size < MIN_CELLS || bounds.width() <= 0 || bounds.height() <= 0) {
            return emptyList()
        }
        val cellW = bounds.width() / 3f
        val cellH = bounds.height() / 3f
        return cells.map { cell ->
            val index = (cell - 1).coerceIn(0, 8)
            val col = index % 3
            val row = index / 3
            val x = bounds.left + (col + 0.5f) * cellW
            val y = bounds.top + (row + 0.5f) * cellH
            x to y
        }
    }

    fun replayDurationMs(pointCount: Int): Long {
        return maxOf(DEFAULT_REPLAY_DURATION_MS, pointCount * 90L).coerceAtMost(1200L)
    }

    fun replay(
        service: AccessibilityService,
        gesturePathJson: String,
        durationMs: Long = DEFAULT_REPLAY_DURATION_MS,
    ): Boolean {
        return replayPoints(service, decode(gesturePathJson), durationMs)
    }

    fun replayPoints(
        service: AccessibilityService,
        points: List<Pair<Float, Float>>,
        durationMs: Long = DEFAULT_REPLAY_DURATION_MS,
    ): Boolean {
        if (points.size < MIN_POINTS) {
            Log.w(TAG, "gesture path too short size=${points.size}")
            return false
        }
        val path = Path().apply {
            moveTo(points.first().first, points.first().second)
            for (index in 1 until points.size) {
                lineTo(points[index].first, points[index].second)
            }
        }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(path, 0L, durationMs))
            .build()
        return runCatching {
            val ok = service.dispatchGesture(gesture, null, null)
            Log.i(
                TAG,
                "pattern gesture replayed points=${points.size} durationMs=$durationMs ok=$ok",
            )
            ok
        }.getOrElse {
            Log.w(TAG, "pattern gesture replay failed", it)
            false
        }
    }
}
