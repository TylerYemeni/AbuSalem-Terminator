package com.abusalem.guard.sender

import android.content.Context
import android.util.Log
import com.abusalem.guard.protection.*
import com.abusalem.guard.spoof.WebImageSpoofer
import kotlinx.coroutines.*
import java.util.*

object ParallelReportSenderWithImages {

    private var isRunning = false

    fun sendReportsInParallel(context: Context, originalAccountIds: List<String>, targetNumber: String) {
        if (isRunning) {
            Log.w("ReportSender", "يوجد هجوم قيد التشغيل بالفعل!")
            return
        }

        isRunning = true
        VPNUsageTracker.init(100) // تحديد أقصى عدد بلاغات لكل IP
        AutoVPNRotator.startAutoRotation(context, intervalMinutes = 4)

        val accountIds = StealthMode.randomizedAccountOrder(originalAccountIds)

        for (accountId in accountIds) {
            CoroutineScope(Dispatchers.IO).launch {
                if (StealthMode.shouldSkipReport(5)) {
                    Log.i("ReportSender", "[$accountId] تم تخطي البلاغ لمحاكاة واقعية")
                    return@launch
                }

                StealthMode.applyHumanDelay()

                val reportId = UUID.randomUUID().toString()
                Log.i("ReportSender", "[$reportId][$accountId] بدء إرسال بلاغ على $targetNumber")

                val imageLoaded = WebImageSpoofer.sendRandomImageFromWeb(context, accountId)
                if (imageLoaded) {
                    Log.i("ReportSender", "[$reportId][$accountId] تم إرفاق صورة واقعية للبلاغ")
                }

                simulateRealisticReport(accountId, targetNumber)

                Log.i("ReportSender", "[$reportId][$accountId] تم إرسال البلاغ بنجاح")

                if (VPNUsageTracker.increment()) {
                    Log.w("ReportSender", "[$accountId] الحد الأقصى للبلاغات تحقق، تغيير IP")
                    AdvancedVPNManager.rotateConnection(context)
                    VPNUsageTracker.reset()
                }

                if (Random.nextInt(0, 10) < 2) {
                    AntiBanGuardian.flagSuspiciousActivity("بلاغات متكررة بسرعة")
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            delay(10000)
            isRunning = false
        }
    }

    private fun simulateRealisticReport(accountId: String, target: String) {
        val delay = (2000..6000).random()
        Thread.sleep(delay.toLong())
        Log.d("Simulator", "[$accountId] بلاغ تم على $target بعد تأخير $delay مللي ثانية")
    }
}
