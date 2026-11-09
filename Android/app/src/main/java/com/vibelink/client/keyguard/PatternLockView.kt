package com.vibelink.client.keyguard

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.os.Build
import android.os.Vibrator
import android.os.VibratorManager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import kotlin.math.hypot

/**
 * mamimobi PatternLockView port — self-drawn 3x3 pattern used as a11y overlay.
 */
class PatternLockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : View(context, attrs) {
    enum class DisplayMode { NORMAL, CORRECT, WRONG, ANIMATE }

    enum class DotVerticalAlign {
        ALIGN_TOP,
        ALIGN_CENTER,
        ALIGN_BOTTOM,
        ;

        fun calculateYOffset(cellHeight: Float, selectedDotSize: Int): Float =
            when (this) {
                ALIGN_TOP -> -selectedDotSize * 0.3f
                ALIGN_BOTTOM -> selectedDotSize * 0.3f
                ALIGN_CENTER -> 0f
            }
    }

    data class PatternCell(
        val row: Int,
        val col: Int,
        val id: Int,
        val absX: Float,
        val absY: Float,
    )

    fun interface PatternListener {
        fun onPatternCompleted(cells: List<PatternCell>, pathPoints: List<Pair<Float, Float>>)
    }

    private var gridSize = 3
    private var inputEnabled = true
    private var stealthMode = false
    private var hapticEnabled = true
    private var aspectRatioEnabled = true
    private var aspectRatio = 1f

    private var normalColor = -5592406
    private var correctColor = -16725933
    private var wrongColor = -765666
    private var pathColor = -12285185
    private var pathWidth = 8
    private var normalDotSize = 12
    private var selectedDotSize = 28
    private var dotAnimDuration = 190
    private var pathEndAnimDuration = 100
    private var dotVerticalAlign = DotVerticalAlign.ALIGN_CENTER
    private var displayMode = DisplayMode.NORMAL

    private val listeners = ArrayList<PatternListener>()
    private val selectedCells = ArrayList<PatternCell>()
    private val hitMap = Array(gridSize) { BooleanArray(gridSize) }
    private val dotRadii = Array(gridSize) { FloatArray(gridSize) { normalDotSize.toFloat() } }
    private val animators = Array(gridSize) { arrayOfNulls<ValueAnimator>(gridSize) }

    private val pathPoints = ArrayList<Pair<Float, Float>>(64)
    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = pathWidth.toFloat()
    }
    private val drawPath = Path()
    private var interpolator: Interpolator? = null
    private var drawing = false
    private var fingerX = -1f
    private var fingerY = -1f

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        (context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager)?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    init {
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                dotRadii[r][c] = normalDotSize.toFloat()
            }
        }
        runCatching {
            interpolator = AnimationUtils.loadInterpolator(
                context,
                android.R.interpolator.fast_out_slow_in,
            )
        }
    }

    fun addPatternListener(listener: PatternListener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removePatternListener(listener: PatternListener) {
        listeners.remove(listener)
    }

    fun clearPattern() {
        cancelAnimations()
        selectedCells.clear()
        pathPoints.clear()
        for (r in 0 until gridSize) {
            hitMap[r].fill(false)
            for (c in 0 until gridSize) {
                dotRadii[r][c] = normalDotSize.toFloat()
            }
        }
        drawing = false
        fingerX = -1f
        fingerY = -1f
        invalidate()
    }

    fun setAspectRatioEnabled(enabled: Boolean) {
        aspectRatioEnabled = enabled
        requestLayout()
    }

    fun setAspectRatio(ratio: Float) {
        aspectRatio = ratio
        requestLayout()
    }

    fun setInputEnabled(enabled: Boolean) {
        inputEnabled = enabled
        if (!enabled) {
            clearPattern()
        }
    }

    fun setDotCount(count: Int) {
        require(count in 2..6)
        gridSize = count
        requestLayout()
        invalidate()
    }

    fun setNormalColor(color: Int) {
        normalColor = color
        invalidate()
    }

    fun setCorrectColor(color: Int) {
        correctColor = color
        invalidate()
    }

    fun setWrongColor(color: Int) {
        wrongColor = color
        invalidate()
    }

    fun setPathColor(color: Int) {
        pathColor = color
        invalidate()
    }

    fun setPathWidth(width: Int) {
        pathWidth = width
        pathPaint.strokeWidth = width.toFloat()
        invalidate()
    }

    fun setNormalDotSize(size: Int) {
        normalDotSize = size
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                if (animators[r][c]?.isRunning != true) {
                    dotRadii[r][c] = size.toFloat()
                }
            }
        }
        invalidate()
    }

    fun setSelectedDotSize(size: Int) {
        selectedDotSize = size
        invalidate()
    }

    fun setDotVerticalAlign(align: DotVerticalAlign) {
        dotVerticalAlign = align
        invalidate()
    }

    fun setDotAnimationDuration(ms: Int) {
        dotAnimDuration = ms.coerceAtLeast(50)
    }

    fun setPathEndAnimationDuration(ms: Int) {
        pathEndAnimDuration = ms.coerceAtLeast(50)
    }

    fun setStealthMode(enabled: Boolean) {
        stealthMode = enabled
    }

    override fun setHapticFeedbackEnabled(enabled: Boolean) {
        super.setHapticFeedbackEnabled(enabled)
        hapticEnabled = enabled
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelAnimations()
        listeners.clear()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (!aspectRatioEnabled) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        // mamimobi: height = width / aspectRatio
        val width = when (MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(widthMeasureSpec)
            else -> (resources.displayMetrics.density * 300f).toInt()
        }.coerceAtLeast(1)
        val measuredH = if (aspectRatio > 0f) {
            (width / aspectRatio).toInt().coerceAtLeast(1)
        } else {
            width
        }
        setMeasuredDimension(width, measuredH)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cellW = cellWidth()
        val cellH = cellHeight()
        if (cellW <= 0f || cellH <= 0f) {
            return
        }
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                val cx = paddingLeft + (c + 0.5f) * cellW
                val cy = paddingTop + (r + 0.5f) * cellH +
                    dotVerticalAlign.calculateYOffset(cellH, selectedDotSize)
                dotPaint.color = when {
                    !hitMap[r][c] -> normalColor
                    displayMode == DisplayMode.WRONG -> wrongColor
                    else -> correctColor
                }
                canvas.drawCircle(cx, cy, dotRadii[r][c], dotPaint)
            }
        }
        if (selectedCells.isEmpty()) {
            return
        }
        pathPaint.color = when (displayMode) {
            DisplayMode.WRONG -> wrongColor
            DisplayMode.CORRECT -> correctColor
            else -> pathColor
        }
        drawPath.reset()
        val first = selectedCells.first()
        drawPath.moveTo(
            paddingLeft + (first.col + 0.5f) * cellW,
            paddingTop + (first.row + 0.5f) * cellH +
                dotVerticalAlign.calculateYOffset(cellH, selectedDotSize),
        )
        for (i in 1 until selectedCells.size) {
            val cell = selectedCells[i]
            drawPath.lineTo(
                paddingLeft + (cell.col + 0.5f) * cellW,
                paddingTop + (cell.row + 0.5f) * cellH +
                    dotVerticalAlign.calculateYOffset(cellH, selectedDotSize),
            )
        }
        if (drawing && fingerX >= 0f && fingerY >= 0f) {
            drawPath.lineTo(fingerX, fingerY)
        }
        canvas.drawPath(drawPath, pathPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!inputEnabled) {
            return false
        }
        val x = event.x
        val y = event.y
        val rawX = event.rawX
        val rawY = event.rawY
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                clearPattern()
                drawing = true
                appendPathPoint(rawX, rawY, force = true)
                hitAt(x, y)
                if (hapticEnabled && vibrator?.hasVibrator() == true) {
                    performHapticFeedback(android.view.HapticFeedbackConstants.VIRTUAL_KEY)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (!drawing) {
                    return true
                }
                appendPathPoint(rawX, rawY, force = false)
                hitAt(x, y)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                appendPathPoint(rawX, rawY, force = true)
                if (drawing) {
                    drawing = false
                    fingerX = -1f
                    fingerY = -1f
                    val cells = selectedCells.toList()
                    val points = pathPoints.toList()
                    listeners.toList().forEach { listener ->
                        runCatching { listener.onPatternCompleted(cells, points) }
                    }
                    invalidate()
                }
            }
        }
        return true
    }

    private fun hitAt(x: Float, y: Float) {
        fingerX = x
        fingerY = y
        val cellW = cellWidth()
        val cellH = cellHeight()
        if (cellW <= 0f || cellH <= 0f) {
            invalidate()
            return
        }
        // 对齐 AOSP：距圆心阈值内才算点；整格矩形会在 8→6 斜线误采 9
        val hit = findCellNearCenter(x, y, cellW, cellH) ?: run {
            invalidate()
            return
        }
        val (row, col) = hit
        if (hitMap[row][col]) {
            invalidate()
            return
        }
        // 对齐 AOSP detectAndAddHit：直线跨两格补中间（1→3 补 2，1→9 补 5）
        val last = selectedCells.lastOrNull()
        if (last != null) {
            val dRow = row - last.row
            val dCol = col - last.col
            val skipTwo =
                (kotlin.math.abs(dRow) == 2 && dCol == 0) ||
                    (kotlin.math.abs(dCol) == 2 && dRow == 0) ||
                    (kotlin.math.abs(dRow) == 2 && kotlin.math.abs(dCol) == 2)
            if (skipTwo) {
                addCellIfNew(last.row + dRow / 2, last.col + dCol / 2, cellW, cellH)
            }
        }
        addCellIfNew(row, col, cellW, cellH)
        invalidate()
    }

    /**
     * 最近圆心且距离 < HIT_FACTOR * min(cellW,cellH)/2 才命中。
     * 斜划 8→6 时路径中点落在四格交界，不会误进 9。
     */
    private fun findCellNearCenter(
        x: Float,
        y: Float,
        cellW: Float,
        cellH: Float,
    ): Pair<Int, Int>? {
        val radius = HIT_FACTOR * minOf(cellW, cellH) / 2f
        var bestRow = -1
        var bestCol = -1
        var bestDist = Float.MAX_VALUE
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                val cx = paddingLeft + (c + 0.5f) * cellW
                val cy = paddingTop + (r + 0.5f) * cellH +
                    dotVerticalAlign.calculateYOffset(cellH, selectedDotSize)
                val dist = hypot((x - cx).toDouble(), (y - cy).toDouble()).toFloat()
                if (dist <= radius && dist < bestDist) {
                    bestDist = dist
                    bestRow = r
                    bestCol = c
                }
            }
        }
        if (bestRow < 0) {
            return null
        }
        return bestRow to bestCol
    }

    private fun addCellIfNew(row: Int, col: Int, cellW: Float, cellH: Float) {
        if (row !in 0 until gridSize || col !in 0 until gridSize) {
            return
        }
        if (hitMap[row][col]) {
            return
        }
        hitMap[row][col] = true
        val loc = IntArray(2)
        getLocationOnScreen(loc)
        val absX = loc[0] + paddingLeft + (col + 0.5f) * cellW
        val absY = loc[1] + paddingTop + (row + 0.5f) * cellH +
            dotVerticalAlign.calculateYOffset(cellH, selectedDotSize)
        val id = row * gridSize + col + 1
        selectedCells.add(PatternCell(row, col, id, absX, absY))
        if (!stealthMode) {
            animateDot(row, col)
        }
        if (hapticEnabled && vibrator?.hasVibrator() == true) {
            performHapticFeedback(android.view.HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }

    private fun animateDot(row: Int, col: Int) {
        animators[row][col]?.cancel()
        val anim = ValueAnimator.ofFloat(normalDotSize.toFloat(), selectedDotSize.toFloat()).apply {
            duration = dotAnimDuration.toLong()
            interpolator = this@PatternLockView.interpolator
            addUpdateListener {
                dotRadii[row][col] = it.animatedValue as Float
                invalidate()
            }
        }
        animators[row][col] = anim
        anim.start()
    }

    private fun appendPathPoint(rawX: Float, rawY: Float, force: Boolean) {
        val last = pathPoints.lastOrNull()
        if (!force && last != null &&
            hypot((rawX - last.first).toDouble(), (rawY - last.second).toDouble()) < 6.0
        ) {
            return
        }
        pathPoints.add(rawX to rawY)
    }

    private fun cellWidth(): Float {
        val w = (width - paddingLeft - paddingRight).coerceAtLeast(0)
        return if (w > 0) w * 1f / gridSize else 0f
    }

    private fun cellHeight(): Float {
        val h = (height - paddingTop - paddingBottom).coerceAtLeast(0)
        return if (h > 0) h * 1f / gridSize else 0f
    }

    private fun cancelAnimations() {
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                animators[r][c]?.cancel()
                animators[r][c] = null
            }
        }
    }

    companion object {
        /** AOSP LockPatternView mHitFactor：圆心附近才算命中，避免斜划蹭到对角格 */
        private const val HIT_FACTOR = 0.6f
    }
}
