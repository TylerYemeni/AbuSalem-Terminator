// AttackControlActivity.kt
package com.abusalem.guard.control

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.abusalem.guard.R
import com.abusalem.guard.sender.ParallelReportSenderWithImages
import com.abusalem.guard.utils.SharedPrefs
import kotlinx.coroutines.*

class AttackControlActivity : Activity() {

    private lateinit var edtAccountCount: EditText
    private lateinit var edtReportsPerMinute: EditText
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    private var attackJob: Job? = null
    private var isRunning = false
    private val dummyAccounts = listOf("acc1", "acc2", "acc3", "acc4", "acc5", "acc6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attack_control)

        edtAccountCount = findViewById(R.id.edtAccountCount)
        edtReportsPerMinute = findViewById(R.id.edtReportsPerMinute)
        btnStart = findViewById(R.id.btnStartAttack)
        btnStop = findViewById(R.id.btnStopAttack)

        btnStart.setOnClickListener {
            if (!isRunning) startAttack()
        }

        btnStop.setOnClickListener {
            stopAttack()
        }
    }

    private fun startAttack() {
        val count = edtAccountCount.text.toString().toIntOrNull() ?: 0
        val rpm = edtReportsPerMinute.text.toString().toIntOrNull() ?: 0

        if (count <= 0 || rpm <= 0) {
            Toast.makeText(this, "أدخل قيم صحيحة", Toast.LENGTH_SHORT).show()
            return
        }

        val targetNumber = SharedPrefs.getTargetNumber(this)
        val selectedAccounts = dummyAccounts.shuffled().take(count)
        val interval = 60000L / rpm

        isRunning = true
        attackJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                ParallelReportSenderWithImages.sendReportsInParallel(this@AttackControlActivity, selectedAccounts, targetNumber)
                delay(interval)
            }
        }

        Toast.makeText(this, "تم بدء الهجوم", Toast.LENGTH_SHORT).show()
    }

    private fun stopAttack() {
        isRunning = false
        attackJob?.cancel()
        Toast.makeText(this, "تم إيقاف الهجوم", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        attackJob?.cancel()
    }
}
