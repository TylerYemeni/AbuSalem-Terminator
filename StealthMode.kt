package com.abusalem.guard.protection

import android.util.Log
import kotlinx.coroutines.delay
import kotlin.random.Random

object StealthMode {

    suspend fun applyHumanDelay(minMs: Int = 1000, maxMs: Int = 4000) {
        val delayTime = Random.nextInt(minMs, maxMs)
        Log.d("StealthMode", "تأخير واقعي: $delayTime مللي ثانية")
        delay(delayTime.toLong())
    }

    fun randomizedAccountOrder(accounts: List<String>): List<String> {
        return accounts.shuffled()
    }

    fun shouldSkipReport(chancePercent: Int = 5): Boolean {
        return Random.nextInt(0, 100) < chancePercent
    }
}
