package com.abusalem.guard.protection

import android.util.Log

object VPNUsageTracker {

    private var reportCount = 0
    private var maxReportsPerIP = 100

    fun init(maxReports: Int) {
        reportCount = 0
        maxReportsPerIP = maxReports
    }

    fun increment(): Boolean {
        reportCount++
        Log.d("VPNUsageTracker", "عدد البلاغات الحالية: $reportCount/$maxReportsPerIP")
        return reportCount >= maxReportsPerIP
    }

    fun reset() {
        reportCount = 0
    }
}
