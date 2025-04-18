package com.abusalem.guardian.security

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.abusalem.guardian.ui.PasswordDialog

object AutoLockManager {
    private const val LOCK_TIMEOUT = 120000L // قفل بعد دقيقتين
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    fun start(activity: Activity) {
        stop() // أوقف أي مؤقت سابق
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            PasswordDialog.show(activity) {}
        }
        handler?.postDelayed(runnable!!, LOCK_TIMEOUT)
    }

    fun stop() {
        handler?.removeCallbacks(runnable!!)
        handler = null
        runnable = null
    }
}
