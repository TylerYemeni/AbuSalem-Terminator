package com.abusalem.guardian

import android.content.Intent import android.os.Bundle import android.widget.* import androidx.appcompat.app.AppCompatActivity import com.abusalem.guardian.utils.NumberGenerator import com.abusalem.guardian.utils.NotificationHelper import com.abusalem.guardian.utils.ReportLogger import com.abusalem.guardian.utils.VPNManager import com.abusalem.guardian.ui.PasswordDialog

class MainActivity : AppCompatActivity() {

private lateinit var phoneInput: EditText
private lateinit var reportTypeSpinner: Spinner
private lateinit var sendReportButton: Button
private lateinit var vpnStatus: TextView

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    PasswordDialog.show(this) {
        initUI()
    }
}

private fun initUI() {
    phoneInput = findViewById(R.id.editPhoneNumber)
    reportTypeSpinner = findViewById(R.id.spinnerReportType)
    sendReportButton = findViewById(R.id.btnSendReport)
    vpnStatus = findViewById(R.id.vpnStatus)

    // إعداد نوع البلاغ
    val types = arrayOf("انتحال شخصية", "نشر محتوى غير لائق", "احتيال", "تهديد", "إرهاب")
    reportTypeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)

    // تبديل VPN تلقائي
    VPNManager.switchVPN(this) { newIp ->
        vpnStatus.text = "VPN مفعل: $newIp"
    }

    // زر الإرسال
    sendReportButton.setOnClickListener {
        val number = phoneInput.text.toString().trim()
        val type = reportTypeSpinner.selectedItem.toString()

        if (number.isNotEmpty()) {
            NotificationHelper.showNotification(this, "تم إرسال بلاغ", "$number - النوع: $type")
            ReportLogger.logReport(number, type)

            Toast.makeText(this, "تم إرسال البلاغ بنجاح", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "يرجى إدخال رقم صالح", Toast.LENGTH_SHORT).show()
        }
    }
}

}

