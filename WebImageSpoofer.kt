package com.abusalem.guard.spoof

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.*

object WebImageSpoofer {

    private val imageSources = listOf(
        "https://picsum.photos/300",           // صور عشوائية واقعية
        "https://source.unsplash.com/random/300x300", // صور من Unsplash
        "https://loremflickr.com/320/240/person", // صور عشوائية لأشخاص
        "https://placekitten.com/300/300"     // صور قطط (واقعية)
    )

    fun sendRandomImageFromWeb(context: Context, accountId: String): Boolean {
        return try {
            val imageUrl = imageSources.random()
            val connection = URL(imageUrl).openConnection()
            connection.connectTimeout = 5000
            connection.readTimeout = 5000

            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()

            val fileName = "spoof_${UUID.randomUUID()}.jpg"
            val file = File(context.cacheDir, fileName)
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
            outputStream.flush()
            outputStream.close()

            Log.i("WebImageSpoofer", "تم تحميل صورة واقعية للحساب [$accountId] من $imageUrl")

            true
        } catch (e: Exception) {
            Log.e("WebImageSpoofer", "فشل تحميل الصورة: ${e.message}")
            false
        }
    }
}
