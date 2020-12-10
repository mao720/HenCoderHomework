package com.hencoder.xfermode.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.xfermode.R
import com.hencoder.xfermode.px

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
class MyAvatarView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var bitmap: Bitmap
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = 200f.px.toInt()
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }

    override fun onDraw(canvas: Canvas) {
        val saveLayer = canvas.saveLayer(90f.px, 90f.px, 310f.px, 310f.px, paint)
        canvas.drawOval(100f.px, 100f.px, 300f.px, 300f.px, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmap, 100f.px, 100f.px, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OVER)
        canvas.drawOval(90f.px, 90f.px, 310f.px, 310f.px, paint)
        paint.xfermode = null
        canvas.restoreToCount(saveLayer)
    }
}