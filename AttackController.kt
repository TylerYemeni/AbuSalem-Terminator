package com.abusalem.guard.attack

import android.content.Context
import kotlinx.coroutines.*
import com.abusalem.guard.login.FakeLoginManager
import com.abusalem.guard.vpn.VPNManager

object AttackController {

    var attackPerMinute = 100
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun launchMassAttack(context: Context, targetNumber: String, reportType: String) {
        repeat(attackPerMinute) {
            coroutineScope.launch {
                val fakeAccount = FakeLoginManager.generateFakeAccount(context)
                VPNManager.changeVPN()

                ReportSender.sendReport(
                    context,
                    targetNumber,
                    reportType,
                    fakeAccount.phoneNumber,
                    fakeAccount.displayName,
                    fakeAccount.profileImageUri
                )
            }
            delay((60000L / attackPerMinute))
        }
    }
}
