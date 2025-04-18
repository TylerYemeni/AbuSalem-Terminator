package com.abusalem.guardian.ui

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import java.security.MessageDigest

object PasswordDialog {

    // كلمة السر الأصلية (يمكن تغييرها لاحقًا)
    private const val hashedPassword = "2bb80d537b1da3e38bd30361aa855686bde0ba4ad3faad92e2f3f0b2ec7b5a77" // = AbuSalemX2025

    fun show(context: Context, onSuccess: () -> Unit) {
        val input = EditText(context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            hint = "أدخل كلمة المرور"
        }

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
            addView(input)
        }

        val dialog = AlertDialog.Builder(context)
            .setTitle("حماية الوصول")
            .setMessage("يرجى إدخال كلمة المرور للدخول:")
            .setView(layout)
            .setCancelable(false)
            .setPositiveButton("دخول") { _, _ ->
                val entered = input.text.toString()
                if (hash(entered) == hashedPassword) {
                    onSuccess()
                } else {
                    Toast.makeText(context, "كلمة المرور غير صحيحة!", Toast.LENGTH_SHORT).show()
                    show(context, onSuccess) // إعادة المحاولة
                }
            }
            .create()

        dialog.show()
    }

    private fun hash(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it) }
    }
}
