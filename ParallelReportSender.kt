package com.abusalem.guard.attack

import android.util.Log
import com.abusalem.guard.spoof.ImageSpoofer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

object ParallelReportSender {

    private val reportTypes = listOf(
        "محتوى غير لائق", "احتيال", "انتحال", "تهديد", "رسائل مزعجة"
    )

    fun launchParallelReports(
        targetNumber: String,
        fakeAccounts: List<String>,
        context: android.content.Context
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val jobs = fakeAccounts.map { accountId ->
                async {
                    try {
                        val type = reportTypes.random()
                        Log.i("ParallelReport", "[$accountId] إرسال بلاغ على $targetNumber: $type")

                        val imageSent = ImageSpoofer.sendRandomImage(context, accountId)
                        if (imageSent) {
                            Log.i("ParallelReport", "[$accountId] تم إرسال صورة داعمة للبلاغ")
                        }

                        delayHumanLike()
                        Log.i("ParallelReport", "[$accountId] بلاغ مكتمل.")
                        true
                    } catch (e: Exception) {
                        Log.e("ParallelReport", "[$accountId] فشل في البلاغ: ${e.message}")
                        false
                    }
                }
            }
            jobs.forEach { it.await() }
        }
    }

    private fun delayHumanLike() {
        Thread.sleep((3000..7000).random().toLong())
    }
}
