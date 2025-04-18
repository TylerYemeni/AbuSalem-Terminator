package com.abusalem.guard.scheduler

import android.content.Context
import kotlinx.coroutines.*
import kotlin.random.Random
import com.abusalem.guard.attack.AttackController

object AutoScheduler {

    private val scope = CoroutineScope(Dispatchers.IO)

    fun startSmartAttack(context: Context, target: String, type: String) {
        scope.launch {
            while (true) {
                val randomDelay = Random.nextLong(5000L, 15000L)
                delay(randomDelay)
                AttackController.launchMassAttack(context, target, type)
            }
        }
    }
}
