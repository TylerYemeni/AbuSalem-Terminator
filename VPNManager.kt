package com.abusalem.guard

import android.content.Context
import android.content.Intent
import android.net.VpnService
import android.util.Log

object VPNManager {
    fun switchVPN(context: Context) {
        try {
            val intent = Intent("com.example.vpn.ACTION_SWITCH") // اسم وهمي لتطبيق VPN
            intent.setPackage("org.torproject.android") // مثل Orbot (يمكن تغييره حسب التطبيق المثبت)
            context.sendBroadcast(intent)
            Log.d("VPNManager", "تم إرسال أمر تغيير الـ VPN")
        } catch (e: Exception) {
            Log.e("VPNManager", "فشل في تغيير VPN: ${e.message}")
        }
    }
}
