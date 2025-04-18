package com.abusalem.guard.sender

import android.content.Context
import android.util.Log
import com.abusalem.guard.engine.RealisticReportEngine
import com.abusalem.guard.spoof.WebImageSpoofer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

object ParallelReportSenderWithImages {

    fun sendReportsInParallel(context: Context, accountIds: List<String>, targetNumber: String) {
        for (accountId in accountIds) {
            CoroutineScope(Dispatchers.IO).launch {
                val reportId = UUID.randomUUID().toString()

                Log.i("ReportSender", "[$reportId][$accountId] بدء إرسال بلاغ على $targetNumber")

                val imageLoaded = WebImageSpoofer.sendRandomImageFromWeb(context, accountId)

                if (imageLoaded) {
                    Log.i("ReportSender", "[$reportId][$accountId] تم إرفاق صورة واقعية للبلاغ")
                } else {
                    Log.w("ReportSender", "[$reportId][$accountId] فشل تحميل الصورة، المتابعة بدونها")
                }

                val success = RealisticReportEngine.performReport(accountId, targetNumber)

                if (success) {
                    Log.i("ReportSender", "[$reportId][$accountId] البلاغ تم بنجاح")
                } else {
                    Log.e("ReportSender", "[$reportId][$accountId] فشل إرسال البلاغ")
                }
            }
        }
    }
}
