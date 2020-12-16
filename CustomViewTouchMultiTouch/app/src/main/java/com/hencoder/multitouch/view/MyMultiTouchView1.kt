package com.hencoder.multitouch.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.hencoder.multitouch.dp
import com.hencoder.multitouch.getAvatar

class MyMultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
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

    private var trackingId = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingId = event.getPointerId(0)
                originOffsetX = event.getX(event.findPointerIndex(trackingId))
                originOffsetY = event.getY(event.findPointerIndex(trackingId))
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                trackingId = event.getPointerId(event.actionIndex)
                originOffsetX = event.getX(event.findPointerIndex(trackingId))
                originOffsetY = event.getY(event.findPointerIndex(trackingId))
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                if (trackingId != event.getPointerId(event.actionIndex)) return true
                var pointerCount = event.pointerCount
                if (event.actionIndex == event.getPointerId(pointerCount - 1)) {
                    pointerCount--
                }
                trackingId = event.getPointerId(pointerCount - 1)
                originOffsetX = event.getX(event.findPointerIndex(trackingId))
                originOffsetY = event.getY(event.findPointerIndex(trackingId))
                lastOffsetX = offsetX
                lastOffsetY = offsetY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetX =
                    event.getX(event.findPointerIndex(trackingId)) - originOffsetX + lastOffsetX
                offsetY =
                    event.getY(event.findPointerIndex(trackingId)) - originOffsetY + lastOffsetY
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