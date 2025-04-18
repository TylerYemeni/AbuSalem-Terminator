package com.abusalem.guard.dialog

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class TargetNumberDialog(
    context: Context,
    private val onNumberChanged: (String) -> Unit
) {
    private val editText = EditText(context).apply {
        hint = "أدخل الرقم المستهدف (مثلاً: +9677XXXXXXX)"
    }

    fun show() {
        val container = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 20, 50, 10)
            addView(editText)
        }

        AlertDialog.Builder(context)
            .setTitle("تغيير الرقم المستهدف")
            .setView(container)
            .setPositiveButton("موافق") { _, _ ->
                val input = editText.text.toString().trim()
                if (input.startsWith("+") && input.length > 7) {
                    onNumberChanged(input)
                    Toast.makeText(context, "تم تحديث الرقم", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "الرقم غير صالح", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("إلغاء", null)
            .show()
    }
}
