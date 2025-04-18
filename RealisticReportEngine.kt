package com.abusalem.guard.engine

import android.util.Log
import kotlin.random.Random

object RealisticReportEngine {

    fun simulateReport(accountId: String, target: String) {
        val delay = Random.nextInt(3000, 7000)
        Thread.sleep(delay.toLong())
        Log.d("RealisticEngine", "[$accountId] بلاغ واقعي تم على $target بعد تأخير $delay مللي")
    }
}
