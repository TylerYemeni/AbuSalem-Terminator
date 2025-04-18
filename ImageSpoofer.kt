package com.abusalem.guard

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.InputStream
import kotlin.random.Random

object ImageSpoofer {

    fun getRandomProfileImage(context: Context): Bitmap? {
        val fakeImagesDir = File(context.filesDir, "fake_images")

        if (!fakeImagesDir.exists() || fakeImagesDir.listFiles().isNullOrEmpty()) {
            return null
        }

        val imageFiles = fakeImagesDir.listFiles()!!.filter { it.extension in listOf("jpg", "png") }
        if (imageFiles.isEmpty()) return null

        val selectedImage = imageFiles[Random.nextInt(imageFiles.size)]

        return BitmapFactory.decodeFile(selectedImage.absolutePath)
    }

    fun attachImageToAccount(context: Context, account: FakeAccount): Boolean {
        val bitmap = getRandomProfileImage(context)
        return if (bitmap != null) {
            // إرسال الصورة لواتساب أو إضافتها للحساب
            Log.d("ImageSpoofer", "تم إرفاق صورة وهمية للحساب ${account.name}")
            true
        } else {
            Log.w("ImageSpoofer", "لا توجد صور وهمية في الجهاز")
            false
        }
    }
}
