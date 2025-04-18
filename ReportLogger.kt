package com.abusalem.guardian.utils

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object ReportLogger {
    fun logReport(context: Context, phone: String, reason: String, proxyUsed: String) {
        val fileName = "report_log.txt"
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val logEntry = """
            [الوقت]: $time
            [الرقم]: $phone
            [السبب]: $reason
            [البروكسي]: $proxyUsed
            ------------------------------
        """.trimIndent()

        val file = File(context.filesDir, fileName)
        file.appendText("$logEntry\n")
    }

    fun getLogFile(context: Context): File {
        return File(context.filesDir, "report_log.txt")
    }
}
