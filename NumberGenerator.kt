package com.abusalem.generator

import java.util.*

object NumberGenerator {
    private val countryCodes = listOf("+966", "+20", "+1", "+971", "+218", "+44")

    fun generateRandomNumber(): String {
        val random = Random()
        val code = countryCodes[random.nextInt(countryCodes.size)]
        val number = (100000000..999999999).random()
        return "$code$number"
    }
}
