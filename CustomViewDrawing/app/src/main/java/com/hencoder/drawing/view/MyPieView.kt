package com.hencoder.drawing.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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

class MyPieView(context: Context, attr: AttributeSet?) : View(context, attr) {
    private val degrees = floatArrayOf(100f, 150f, 260f, 360f)
    private val colors = intArrayOf(
        Color.parseColor("#FFBB33"),
        Color.parseColor("#FF4444"),
        Color.parseColor("#AA66CC"),
        Color.parseColor("#33B5E5")
    )
    private val paint = Paint()

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        for ((index, degree) in degrees.withIndex()) {
            paint.color = colors[index]
            canvas.save()
            canvas.translate(
                (10f.px * cos(Math.toRadians(((degree - startAngle) / 2 + startAngle).toDouble()))).toFloat(),
                (10f.px * sin(Math.toRadians(((degree - startAngle) / 2 + startAngle).toDouble()))).toFloat()
            )
            canvas.drawArc(
                width / 2 - RADIUS,
                height / 2 - RADIUS,
                width / 2 + RADIUS,
                height / 2 + RADIUS,
                startAngle,
                degree - startAngle,
                true,
                paint
            )
            canvas.restore()
            startAngle = degree
        }
    }
}