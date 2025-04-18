package com.abusalem.guard.engine

import android.content.Context
import android.util.Log
import com.abusalem.guard.logger.ReportLogger
import com.abusalem.guard.spoof.WebImageSpoofer
import kotlinx.coroutines.*

object AdvancedReportHandler {

    fun handleReport(context: Context, accountId: String, targetNumber: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                WebImageSpoofer.sendRandomImageFromWeb(context, accountId)
                RealisticReportEngine.simulateReport(accountId, targetNumber)
                ReportLogger.log("[$accountId] تم تنفيذ البلاغ على $targetNumber")
            } catch (e: Exception) {
                Log.e("AdvancedReport", "فشل بلاغ $accountId على $targetNumber: ${e.message}")
            }
        }
    }
}
