package com.abusalem.guard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.R
import com.abusalem.guard.control.ControlPanelManager
import com.abusalem.guard.sender.ParallelReportSenderWithImages
import com.abusalem.guard.vpn.VPNManager
import com.abusalem.guard.accounts.FakeAccountManager

class MainDashboardActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var accountCountText: TextView
    private lateinit var vpnStatusText: TextView
    private lateinit var launchButton: Button
    private lateinit var openSettingsButton: Button
    private val targetNumber = "+967712345678" // يمكن جعله ديناميكي لاحقًا

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        statusText = findViewById(R.id.statusText)
        accountCountText = findViewById(R.id.accountCountText)
        vpnStatusText = findViewById(R.id.vpnStatusText)
        launchButton = findViewById(R.id.btnLaunch)
        openSettingsButton = findViewById(R.id.btnSettings)

        refreshStatus()

        launchButton.setOnClickListener {
            val accounts = FakeAccountManager.getAvailableAccounts()
            ParallelReportSenderWithImages.sendReportsInParallel(this, accounts, targetNumber)
            statusText.text = "تم بدء الهجوم على $targetNumber"
        }

        openSettingsButton.setOnClickListener {
            val intent = Intent(this, ControlPanelUI::class.java)
            startActivity(intent)
        }
    }

    private fun refreshStatus() {
        vpnStatusText.text = if (VPNManager.isConnected(this)) "VPN: متصل" else "VPN: غير متصل"
        accountCountText.text = "الحسابات الجاهزة: ${FakeAccountManager.getAvailableAccounts().size}"
    }
}
