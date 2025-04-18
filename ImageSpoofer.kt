package com.abusalem.guard.spoof

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object ImageSpoofer {

    suspend fun sendRandomImage(context: Context, fakeAccountId: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val imageUri = getRandomImageFromGallery(context)
                if (imageUri != null) {
                    val caption = getRandomCaption()
                    // هنا ترسل الصورة من خلال الحساب الوهمي
                    Log.i("Spoofer", "[$fakeAccountId] إرسال صورة: $imageUri مع وصف: $caption")
                    
                    delayRealistic()
                    true
                } else {
                    Log.w("Spoofer", "[$fakeAccountId] لم يتم العثور على صورة")
                    false
                }
            } catch (e: Exception) {
                Log.e("Spoofer", "[$fakeAccountId] فشل في إرسال صورة: ${e.message}")
                false
            }
        }
    }

    private fun getRandomImageFromGallery(context: Context): Uri? {
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection, null, null, null
        )
        cursor?.use {
            if (it.count > 0) {
                val randomIndex = Random().nextInt(it.count)
                it.moveToPosition(randomIndex)
                val id = it.getLong(0)
                return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
            }
        }
        return null
    }

    private fun getRandomCaption(): String {
        val captions = listOf("تحقق من هذا", "صورة جديدة", "من يومياتي", "لقطة مميزة")
        return captions.random()
    }

    private fun delayRealistic() {
        Thread.sleep((2000..5000).random().toLong())
    }
}
