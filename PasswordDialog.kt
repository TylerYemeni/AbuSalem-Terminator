package com.abusalem.security

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.Toast

object PasswordDialog {
    private const val correctPassword = "AbuSalemX2025"

    fun show(context: Context, onSuccess: () -> Unit) {
        val input = EditText(context)
        input.hint = "أدخل كلمة السر"
        input.setSingleLine()

        AlertDialog.Builder(context)
            .setTitle("كلمة مرور الدخول")
            .setMessage("هذا التطبيق محمي. الرجاء إدخال كلمة المرور.")
            .setView(input)
            .setCancelable(false)
            .setPositiveButton("دخول") { _, _ ->
                val entered = input.text.toString()
                if (entered == correctPassword) {
                    onSuccess()
                } else {
                    Toast.makeText(context, "كلمة مرور خاطئة!", Toast.LENGTH_SHORT).show()
                    android.os.Handler().postDelayed({ show(context, onSuccess) }, 1000)
                }
            }
            .setNegativeButton("خروج") { _, _ ->
                android.os.Process.killProcess(android.os.Process.myPid())
            }
            .show()
    }
}
