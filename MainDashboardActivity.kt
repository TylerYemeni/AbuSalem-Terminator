package com.abusalem.guard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.engine.ParallelReportSenderWithImages
import com.abusalem.guard.fake.FakeAccountManager
import com.abusalem.guard.ui.TargetNumberDialog
import com.abusalem.guard.ui.LogsViewerActivity

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var btnControlPanel: Button
    private lateinit var btnChangeTarget: Button
    private lateinit var btnViewLogs: Button
    private lateinit var btnLaunchAttack: Button
    private lateinit var statusText: TextView
    private var targetNumber: String = "+967712345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnControlPanel = findViewById(R.id.btnControlPanel)
        btnChangeTarget = findViewById(R.id.btnChangeTarget)
        btnViewLogs = findViewById(R.id.btnViewLogs)
        btnLaunchAttack = findViewById(R.id.btnLaunchAttack)
        statusText = findViewById(R.id.statusText)

        btnControlPanel.setOnClickListener {
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
            startActivity(Intent(this, LogsViewerActivity::class.java))
        }

        btnLaunchAttack.setOnClickListener {
            ParallelReportSenderWithImages.sendReportsInParallel(
                this,
                FakeAccountManager.getAllAccounts(),
                targetNumber
            )
        }

        statusText.text = "الهدف الحالي: $targetNumber"
    }
}
