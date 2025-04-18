package com.abusalem.whatsapp_guardian.security

import kotlinx.coroutines.*
import kotlin.random.Random

object HumanBehaviorSimulator {
    private val delays = listOf(800L, 1200L, 2000L, 3500L)
    private val messages = listOf("محتوى مسيء", "رسائل تهديد", "مزعج جدًا", "احتيال", "تحرش")

    suspend fun simulateUser(reportAction: suspend (String, String) -> Unit, phoneNumber: String) {
        val delayTime = delays.random()
        delay(delayTime)

        val message = messages.random()
        reportAction(phoneNumber, message)

        // احتمال يرسل صورة كتمويه
        if (Random.nextBoolean()) {
            delay(1000)
            simulateImageSending(phoneNumber)
        }
    }

    private fun simulateImageSending(number: String) {
        println("تم إرسال صورة تمويهية إلى $number (وهمية)")
    }
}
