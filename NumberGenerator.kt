package com.abusalem.guardian.utils

object NumberGenerator {
    private val arabCountries = mapOf(
        "+967" to "اليمن",
        "+966" to "السعودية",
        "+20"  to "مصر",
        "+963" to "سوريا",
        "+964" to "العراق",
        "+218" to "ليبيا",
        "+212" to "المغرب",
        "+971" to "الإمارات",
        "+973" to "البحرين",
        "+962" to "الأردن",
        "+213" to "الجزائر",
        "+216" to "تونس"
    )

    fun generateRandomArabicNumber(): String {
        val (code, _) = arabCountries.entries.random()
        val number = (100000000..999999999).random()
        return "$code$number"
    }

    fun generateNumberByCode(code: String): String {
        val validCode = if (arabCountries.containsKey(code)) code else "+967"
        val number = (100000000..999999999).random()
        return "$validCode$number"
    }
}
