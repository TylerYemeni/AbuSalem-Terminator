package com.abusalem.guard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.R
import com.abusalem.guard.logs.ReportLogger
import com.abusalem.guard.manager.ControlPanelDialog
import com.abusalem.guard.sender.ParallelReportSenderWithImages

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var btnStartAttack: Button
    private lateinit var btnSettings: Button
    private lateinit var btnChangeTarget: Button
    private lateinit var btnViewLogs: Button
    private lateinit var statusText: TextView

    private var targetNumber: String = "+967712345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnStartAttack = findViewById(R.id.btnStartAttack)
        btnSettings = findViewById(R.id.btnSettings)
        btnChangeTarget = findViewById(R.id.btnChangeTarget)
        btnViewLogs = findViewById(R.id.btnViewLogs)
        statusText = findViewById(R.id.statusText)

        btnStartAttack.setOnClickListener {
            ParallelReportSenderWithImages.sendReportsInParallel(
                this,
                listOf("acc01", "acc02", "acc03"), // استبدل بقائمة حساباتك الوهمية
                targetNumber
            )
            statusText.text = "جاري إرسال البلاغات على $targetNumber"
            ReportLogger.log("بدأت عملية البلاغات على $targetNumber")
        }

        btnSettings.setOnClickListener {
            val dialog = ControlPanelDialog(this)
            dialog.show()
        }

        btnChangeTarget.setOnClickListener {
            val dialog = TargetNumberDialog(this) { newNumber ->
                targetNumber = newNumber
                statusText.text = "تم تعيين الهدف: $targetNumber"
                ReportLogger.log("تم تغيير الهدف إلى $targetNumber")
            }
            dialog.show()
        }

        btnViewLogs.setOnClickListener {
            val intent = Intent(this, LogsViewerActivity::class.java)
            startActivity(intent)
        }
    }
}
