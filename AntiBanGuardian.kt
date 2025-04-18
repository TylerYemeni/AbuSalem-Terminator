package com.abusalem.guard.protection

import android.util.Log

object AntiBanGuardian {

    private var suspiciousActivityCount = 0
    private val maxSuspicion = 5

    fun flagSuspiciousActivity(reason: String) {
        suspiciousActivityCount++
        Log.w("AntiBanGuardian", "نشاط مشبوه: $reason ($suspiciousActivityCount/$maxSuspicion)")

        if (suspiciousActivityCount >= maxSuspicion) {
            Log.e("AntiBanGuardian", "تحذير: قد يتم حظرك قريبًا! إيقاف الأنشطة الوقائية")
            // هنا يمكنك إضافة إجراء لإيقاف الهجوم مؤقتًا أو تبديل VPN إلزاميًا
            VPNUsageTracker.reset()
            AutoVPNRotator.stopRotation()
        }
    }

    fun reset() {
        suspiciousActivityCount = 0
    }
}
