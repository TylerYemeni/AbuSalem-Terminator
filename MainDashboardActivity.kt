package com.abusalem.guard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.R
import com.abusalem.guard.dialogs.PasswordDialog
import com.abusalem.guard.dialogs.TargetNumberDialog
import com.abusalem.guard.logger.ReportLogger

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var btnStartAttack: Button
    private lateinit var btnChangeTarget: Button
    private lateinit var btnViewLogs: Button
    private lateinit var btnAdvancedControl: Button
    private lateinit var btnQuickAttack: Button
    private lateinit var btnVPNControl: Button

    private var targetNumber: String = "+967712345678"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        statusText = findViewById(R.id.statusText)
        btnStartAttack = findViewById(R.id.btnStartAttack)
        btnChangeTarget = findViewById(R.id.btnChangeTarget)
        btnViewLogs = findViewById(R.id.btnViewLogs)
        btnAdvancedControl = findViewById(R.id.btnAdvancedControl)
        btnQuickAttack = findViewById(R.id.btnQuickAttack)
        btnVPNControl = findViewById(R.id.btnVPNControl)

        statusText.text = "الهدف الحالي: $targetNumber"

        btnStartAttack.setOnClickListener {
            Toast.makeText(this, "بدء الهجوم على $targetNumber", Toast.LENGTH_SHORT).show()
            // ابدأ الهجوم الفعلي من هنا
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

        btnAdvancedControl.setOnClickListener {
            startActivity(Intent(this, AttackControlActivity::class.java))
        }

        btnQuickAttack.setOnClickListener {
            Toast.makeText(this, "الهجوم السريع غير مفعل بعد", Toast.LENGTH_SHORT).show()
        }

        btnVPNControl.setOnClickListener {
            Toast.makeText(this, "تبديل VPN...", Toast.LENGTH_SHORT).show()
            // استدعاء VPNManager للتبديل
        }

        PasswordDialog(this) {
            Toast.makeText(this, "تم الدخول بنجاح!", Toast.LENGTH_SHORT).show()
        }.show()
    }
}
