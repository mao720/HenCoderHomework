package com.hencoder.multitouch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.hencoder.multitouch.dp
import com.hencoder.multitouch.getAvatar

class MyMultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var offsetX = 0f
    private var offsetY = 0f
    private var originOffsetX = 0f
    private var originOffsetY = 0f
    private var lastOffsetX = 0f
    private var lastOffsetY = 0f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(
            getAvatar(resources, 300.dp.toInt()),
            offsetX,
            offsetY,
            paint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var sumX = 0f
        var sumY = 0f
        val isRemove = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (index in 0 until event.pointerCount) {
            if (isRemove && event.actionIndex == index) {
                continue
            }
            sumX += event.getX(index)
            sumY += event.getY(index)
        }
        val focusX = sumX / (event.pointerCount - if (isRemove) 1 else 0)
        val focusY = sumY / (event.pointerCount - if (isRemove) 1 else 0)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                originOffsetX = focusX
                originOffsetY = focusY
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                originOffsetX = focusX
                originOffsetY = focusY
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                originOffsetX = focusX
                originOffsetY = focusY
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX = focusX - originOffsetX + lastOffsetX
                offsetY = focusY - originOffsetY + lastOffsetY
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
        }
        return true
    }
}