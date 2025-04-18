package com.abusalem.guard.login

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.util.*

object FakeLoginManager {

    data class FakeAccount(
        val phoneNumber: String,
        val displayName: String,
        val profileImageUri: Uri,
        val country: String
    )

    private val countries = listOf("اليمن", "السعودية", "مصر", "الأردن", "تونس", "الجزائر")

    fun generateFakeAccount(context: Context): FakeAccount {
        val phone = "+9677${(1000000..9999999).random()}"
        val name = listOf("أبو سالم", "هكر اليمن", "محقق خاص", "صقر العرب", "فهد الليل").random()
        val imageFile = File(context.filesDir, "default_profile.jpg")
        val imageUri = Uri.fromFile(imageFile)
        val country = countries.random()

        return FakeAccount(phone, name, imageUri, country)
    }
}
