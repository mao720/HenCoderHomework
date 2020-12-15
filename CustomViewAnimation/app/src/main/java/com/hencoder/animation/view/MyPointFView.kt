package com.hencoder.animation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.hencoder.animation.dp

/**
 * Description:
 *
 * 2020/12/15 (https://github.com/mao720)
 */
class MyPointFView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var pointF = PointF()
        set(value) {
            field = value
            invalidate()
        }

    init {
        paint.strokeWidth = 20.dp
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPoint(pointF.x, pointF.y, paint)
    }
}