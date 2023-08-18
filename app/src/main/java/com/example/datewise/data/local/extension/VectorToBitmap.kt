package com.example.datewise.data.local.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.graphics.createBitmap

/*fun Context.vectorToBitmap(drawableId:Int): Bitmap?{
    val drawable = getDrawable(drawableId ) ?: return null
    val bitmap = createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas= Canvas(bitmap)
    drawable.setBounds(0,0,canvas.width,canvas.height)
    drawable.draw(canvas)
    return bitmap
}*/
fun Context.vectorToBitmap(drawableId: Int): Bitmap? {
    val drawable = getDrawable(drawableId) ?: return null

    val bitmap = createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )

    bitmap.let { // Elvis operatörü kullanımı kaldırıldı
        val canvas = Canvas(it)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
    }

    return bitmap
}