package com.abusalem.guard.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.R
import com.abusalem.guard.logger.ReportLogger

class LogsViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs_viewer)

        val logsTextView: TextView = findViewById(R.id.logsTextView)
        val logs = ReportLogger.getLogs()
        logsTextView.text = logs.joinToString("\n\n") { log -> "- $log" }
    }
}
