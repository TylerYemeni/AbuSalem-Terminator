package com.abusalem.guard.ui

import android.content.Context
import android.view.View
import android.widget.*
import com.abusalem.guard.controller.AttackController

class ControlPanelUi(
    private val context: Context,
    private val rootView: View,
    private val onControlChanged: (Int, Int, Int) -> Unit
) {
    private lateinit var accountsInput: EditText
    private lateinit var speedInput: EditText
    private lateinit var imagesPerReportInput: EditText
    private lateinit var applyButton: Button

    fun init() {
        accountsInput = rootView.findViewById(R.id.inputAccounts)
        speedInput = rootView.findViewById(R.id.inputSpeed)
        imagesPerReportInput = rootView.findViewById(R.id.inputImagesPerReport)
        applyButton = rootView.findViewById(R.id.btnApplyControl)

        applyButton.setOnClickListener {
            val accounts = accountsInput.text.toString().toIntOrNull() ?: 5
            val speed = speedInput.text.toString().toIntOrNull() ?: 60
            val imagesCount = imagesPerReportInput.text.toString().toIntOrNull() ?: 1

            Toast.makeText(context, "تم تطبيق الإعدادات", Toast.LENGTH_SHORT).show()
            onControlChanged(accounts, speed, imagesCount)

            // إرسال الإعدادات إلى AttackController
            AttackController.updateConfig(
                accountCount = accounts,
                attackSpeedPerMinute = speed,
                imagesPerReport = imagesCount
            )
        }
    }
}
