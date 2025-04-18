package com.abusalem.whatsapp_guardian.core

import kotlinx.coroutines.*
import kotlin.random.Random

object NumberFlooder {
    private val countryCodes = listOf("+967", "+966", "+20", "+212", "+218", "+962", "+964", "+961", "+90", "+1")
    
    fun generateFakeNumber(): String {
        val code = countryCodes.random()
        val number = (100000000..999999999).random()
        return "$code$number"
    }

    suspend fun startFlooding(
        reportFunction: suspend (String) -> Unit,
        count: Int = 100,
        delayRange: LongRange = 500L..2000L
    ) = coroutineScope {
        repeat(count) {
            launch {
                val number = generateFakeNumber()
                reportFunction(number)
                delay(Random.nextLong(delayRange.first, delayRange.last))
            }
        }
    }
}
