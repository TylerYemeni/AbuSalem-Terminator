package com.abusalem.logger

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object ReportLogger {
    fun log(context: Context, number: String, type: String) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val content = "$timestamp - بلاغ على الرقم: $number - النوع: $type\n"
        val file = File(context.filesDir, "log.txt")
        file.appendText(content)
    }

    fun getAllLogs(context: Context): String {
        val file = File(context.filesDir, "log.txt")
        return if (file.exists()) file.readText() else "لا توجد بلاغات بعد."
    }
}
