package com.hencoder.clipandcamera.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hencoder.clipandcamera.R
import com.hencoder.clipandcamera.dp

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
class MyCameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        rotateX(30f)
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        canvas.translate(200.dp, 200.dp)
        canvas.rotate(-30f)
        canvas.clipRect((-200).dp, (-200).dp, 200.dp, 0f)
        canvas.rotate(30f)
        canvas.translate((-200).dp, (-200).dp)
        canvas.drawBitmap(getAvatar(200.dp.toInt()), 100.dp, 100.dp, paint)
        canvas.restore()

        canvas.save()
        canvas.translate(200.dp, 200.dp)
        canvas.rotate(-30f)
        camera.applyToCanvas(canvas)
        canvas.clipRect((-200).dp, 0f, 200.dp, 200.dp)
        canvas.rotate(30f)
        canvas.translate((-200).dp, (-200).dp)
        canvas.drawBitmap(getAvatar(200.dp.toInt()), 100.dp, 100.dp, paint)
        canvas.restore()
    }

    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }
}