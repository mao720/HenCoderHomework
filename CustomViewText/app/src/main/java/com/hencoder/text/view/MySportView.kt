package com.hencoder.text.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hencoder.text.dp

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
private val PADDING = 100.dp
private val RADIUS = 100.dp

class MySportView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#BBBBBB")
        strokeWidth = 15.dp
        style = Paint.Style.STROKE
        textSize = 80.dp
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawOval(PADDING, PADDING, 2 * RADIUS + PADDING, 2 * RADIUS + PADDING, paint)
        paint.color = Color.parseColor("#FF4444")
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            PADDING,
            PADDING,
            2 * RADIUS + PADDING,
            2 * RADIUS + PADDING,
            240f,
            240f,
            false,
            paint
        )
        paint.style = Paint.Style.FILL
        val fontMetrics = paint.fontMetrics
        println("MMM" + fontMetrics.top)
        println("MMM" + fontMetrics.ascent)
        println("MMM" + fontMetrics.leading)
        println("MMM" + fontMetrics.descent)
        println("MMM" + fontMetrics.bottom)
        canvas.drawText(
            "abab",
            PADDING + RADIUS,
            PADDING + RADIUS - (fontMetrics.descent + fontMetrics.ascent) / 2,
            paint
        )
    }
}