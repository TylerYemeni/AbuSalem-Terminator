package com.abusalem.guard

import android.util.Log
import kotlinx.coroutines.*
import kotlin.random.Random

object ReportSender {

    // عدد البلاغات لكل حساب
    var reportsPerAccount = 5

    // تأخير عشوائي بين كل بلاغ (بالمللي ثانية)
    private fun randomDelay() = Random.nextLong(1000L, 3000L)

    fun startAttack(
        targetNumber: String,
        accounts: List<FakeAccount>,
        reportType: String,
        onProgress: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            accounts.forEachIndexed { index, account ->
                onProgress("تبديل VPN للحساب ${index + 1}")
                VPNManager.switchVPN(account.vpnProfile)

                repeat(reportsPerAccount) { i ->
                    delay(randomDelay())

                    onProgress("إرسال بلاغ $i من حساب: ${account.name}")
                    Log.d("ReportSender", "إرسال بلاغ من ${account.phone} على $targetNumber بنوع $reportType")

                    // محاكاة إرسال البلاغ
                    FakeNetwork.sendReport(account, targetNumber, reportType)
                }
            }

            onProgress("الهجوم على $targetNumber انتهى.")
        }
    }
}
