package com.abusalem.guardian.security

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.Toast

object PasswordDialog {
    private const val CORRECT_PASSWORD = "AbuSalemX2025"

    fun show(context: Context, onSuccess: () -> Unit) {
        val input = EditText(context)
        input.hint = "أدخل كلمة السر"
        input.setHintTextColor(0xFFAAAAAA.toInt())
        input.setTextColor(0xFFFFFFFF.toInt())
        input.setBackgroundColor(0xFF222222.toInt())

        val dialog = AlertDialog.Builder(context)
            .setTitle("كلمة السر")
            .setMessage("دخول محمي، أدخل كلمة المرور")
            .setView(input)
            .setCancelable(false)
            .setPositiveButton("دخول") { _, _ ->
                val enteredPassword = input.text.toString()
                if (enteredPassword == CORRECT_PASSWORD) {
                    onSuccess()
                } else {
                    Toast.makeText(context, "كلمة السر خاطئة!", Toast.LENGTH_SHORT).show()
                    show(context, onSuccess)
                }
            }
            .create()

        dialog.show()
    }
}
