package com.abusalem.guard.protection

import android.content.Context
import android.util.Log
import com.abusalem.guard.vpn.AdvancedVPNManager
import kotlinx.coroutines.*

object AutoVPNRotator {

    private var rotationJob: Job? = null

    fun startAutoRotation(context: Context, intervalMinutes: Int = 3) {
        rotationJob?.cancel()
        rotationJob = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay((intervalMinutes * 60 * 1000).toLong())
                Log.i("AutoVPNRotator", "تبديل VPN تلقائي بعد $intervalMinutes دقائق")
                AdvancedVPNManager.rotateConnection(context)
            }
        }
    }

    fun stopRotation() {
        rotationJob?.cancel()
    }
}
