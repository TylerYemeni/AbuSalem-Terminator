package com.abusalem.guard

import android.util.Log

data class FakeAccount(val number: String, val country: String, val imagePath: String?)

object FakeAccountManager {
    private val countries = listOf("اليمن", "السعودية", "مصر", "أمريكا", "ألمانيا")
    private val prefixMap = mapOf(
        "اليمن" to "+9677",
        "السعودية" to "+9665",
        "مصر" to "+2010",
        "أمريكا" to "+1201",
        "ألمانيا" to "+4915"
    )

    fun generateAccount(): FakeAccount {
        val country = countries.random()
        val prefix = prefixMap[country] ?: "+000"
        val number = prefix + (1000000..9999999).random()
        Log.d("FakeAccountManager", "تم توليد حساب وهمي: $number ($country)")
        return FakeAccount(number, country, null)
    }
}
