package com.abusalem.guard.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.abusalem.guard.R
import com.abusalem.guard.security.PasswordProtectionManager

class PasswordDialog(
    context: Context,
    onSuccess: () -> Unit
) {
    private val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_password, null)
    private val passwordInput = dialogView.findViewById<EditText>(R.id.passwordInput)

    private val dialog = AlertDialog.Builder(context)
        .setTitle("دخول محمي")
        .setView(dialogView)
        .setCancelable(false)
        .setPositiveButton("دخول") { _, _ ->
            val input = passwordInput.text.toString()
            if (PasswordProtectionManager.isPasswordValid(input)) {
                onSuccess()
            } else {
                Toast.makeText(context, "كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show()
                show() // إعادة المحاولة
            }
        }
        .create()

    fun show() = dialog.show()
}
