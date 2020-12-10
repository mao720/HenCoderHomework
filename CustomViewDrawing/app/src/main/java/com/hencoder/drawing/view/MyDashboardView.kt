package com.hencoder.drawing.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
private val RADIUS = 150f.px

class MyDashboardView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val dash = Path()
    private val pathMeasure = PathMeasure()
    private lateinit var pathDashPathEffect: PathDashPathEffect

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f.px
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.addArc(
            width / 2 - RADIUS,
            height / 2 - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            150f,
            240f
        )
        pathMeasure.setPath(path, false)
        dash.addRect(0f, 0f, 5f.px, 15f.px, Path.Direction.CW)
        pathDashPathEffect =
            PathDashPathEffect(
                dash,
                (pathMeasure.length - (5f.px)) / 20,
                0f,
                PathDashPathEffect.Style.ROTATE
            )
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        canvas.drawLine(
            width / 2f,
            height / 2f,
            width / 2 + (120f.px * cos(Math.toRadians((150 + 3 * 12).toDouble()))).toFloat(),
            height / 2 + (120f.px * sin(Math.toRadians((150 + 3 * 12).toDouble()))).toFloat(),
            paint
        )
    }
}