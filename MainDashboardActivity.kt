package com.abusalem.guardian

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guardian.modules.*
import com.abusalem.guardian.utils.*

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var btnControl: Button
    private lateinit var btnChangeTarget: Button
    private lateinit var btnViewLogs: Button
    private lateinit var btnLaunchAttack: Button
    private lateinit var statusText: TextView
    private var targetNumber: String = "+967712345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnStart = findViewById(R.id.btnStart)
        btnControl = findViewById(R.id.btnControl)
        btnChangeTarget = findViewById(R.id.btnChangeTarget)
        btnViewLogs = findViewById(R.id.btnViewLogs)
        btnLaunchAttack = findViewById(R.id.btnLaunchAttack)
        statusText = findViewById(R.id.statusText)

        btnStart.setOnClickListener {
            statusText.text = "جاري تشغيل الأنظمة..."
            AutoScheduler.scheduleAll(this)
        }

        btnControl.setOnClickListener {
            startActivity(Intent(this, ControlPanelActivity::class.java))
        }

        btnChangeTarget.setOnClickListener {
            val dialog = TargetNumberDialog(this) { newNumber ->
                targetNumber = newNumber
                statusText.text = "تم تعيين الهدف: $targetNumber"
            }
            dialog.show()
        }

        btnViewLogs.setOnClickListener {
            val intent = Intent(this, LogsViewerActivity::class.java)
            startActivity(intent)
        }

        btnLaunchAttack.setOnClickListener {
            ParallelReportSenderWithImages.sendReportsInParallel(this, FakeAccountManager.getAllAccounts(), targetNumber)
        }
    }
}
