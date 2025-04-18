package com.abusalem.guard.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.R
import com.abusalem.guard.control.ControlPanelManager

class ControlPanelUI : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_panel)

        val attacksInput = findViewById<EditText>(R.id.inputAttacksPerMinute)
        val accountsInput = findViewById<EditText>(R.id.inputAccountsPerAttack)
        val delayInput = findViewById<EditText>(R.id.inputDelayMillis)
        val applyButton = findViewById<Button>(R.id.btnApplySettings)

        applyButton.setOnClickListener {
            val attacks = attacksInput.text.toString().toIntOrNull() ?: 100
            val accounts = accountsInput.text.toString().toIntOrNull() ?: 20
            val delay = delayInput.text.toString().toLongOrNull() ?: 60000L

            ControlPanelManager.configureControlPanel(attacks, accounts, delay)
        }
    }
}
