package com.abusalem.guard.controller

import android.content.Context
import com.abusalem.guard.accounts.FakeAccountManager
import com.abusalem.guard.sender.ParallelReportSenderWithImages

object AttackController {

    fun launchMassiveAttack(
        context: Context,
        targetNumber: String,
        accountsCount: Int = 50,
        reportsPerMinute: Int = 100
    ) {
        val fakeAccounts = FakeAccountManager.generateFakeAccounts(accountsCount)
        val reportsPerSecond = reportsPerMinute / 60.0
        val delayMillis = (1000 / reportsPerSecond).toLong()

        for (i in 0 until fakeAccounts.size) {
            val accountId = fakeAccounts[i]
            android.os.Handler().postDelayed({
                ParallelReportSenderWithImages.sendReportsInParallel(
                    context,
                    listOf(accountId),
                    targetNumber
                )
            }, i * delayMillis)
        }
    }
}
