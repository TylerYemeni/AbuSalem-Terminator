package com.abusalem.guardian.auth

import android.app.AlertDialog import android.content.Context import android.widget.EditText import android.widget.Toast

object PasswordDialog { private const val MASTER_PASSWORD = "AbuSalemX2025"

fun show(context: Context, onSuccess: () -> Unit) {
    val input = EditText(context)
    input.hint = "أدخل كلمة السر"

    AlertDialog.Builder(context)
        .setTitle("الدخول إلى أداة أبو سالم")
        .setMessage("يرجى إدخال كلمة السر للوصول")
        .setView(input)
        .setPositiveButton("دخول") { _, _ ->
            if (input.text.toString() == MASTER_PASSWORD) {
                Toast.makeText(context, "تم الدخول بنجاح", Toast.LENGTH_SHORT).show()
                onSuccess()
            } else {
                Toast.makeText(context, "كلمة السر غير صحيحة", Toast.LENGTH_LONG).show()
            }
        }
        .setCancelable(false)
        .show()
}

}

