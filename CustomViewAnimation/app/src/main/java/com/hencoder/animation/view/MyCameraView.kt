package com.hencoder.animation.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.hencoder.animation.R
import com.hencoder.animation.dp

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
class MyCameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera = Camera().apply {
        setLocation(0f, 0f, -6 * resources.displayMetrics.density)
    }
    var cameraRotateTop = 0f
        set(value) {
            field = value
            invalidate()
        }
    var cameraRotateBottom = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotate = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        canvas.withSave {
            canvas.translate(200.dp, 200.dp)
            canvas.rotate(-flipRotate)
            camera.save()
            camera.rotateX(cameraRotateTop)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect((-200).dp, (-200).dp, 200.dp, 0f)
            canvas.rotate(flipRotate)
            canvas.translate((-200).dp, (-200).dp)
            canvas.drawBitmap(getAvatar(200.dp.toInt()), 100.dp, 100.dp, paint)
        }

        canvas.withSave {
            canvas.translate(200.dp, 200.dp)
            canvas.rotate(-flipRotate)
            camera.save()
            camera.rotateX(cameraRotateBottom)
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect((-200).dp, 0f, 200.dp, 200.dp)
            canvas.rotate(flipRotate)
            canvas.translate((-200).dp, (-200).dp)
            canvas.drawBitmap(getAvatar(200.dp.toInt()), 100.dp, 100.dp, paint)
        }
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