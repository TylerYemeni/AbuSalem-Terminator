package com.abusalem.guard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abusalem.guard.ui.PasswordDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // التحقق من كلمة المرور قبل عرض الواجهة الرئيسية
        PasswordDialog(this) {
            startActivity(Intent(this, MainDashboardActivity::class.java))
            finish()
        }.show()
    }
}
