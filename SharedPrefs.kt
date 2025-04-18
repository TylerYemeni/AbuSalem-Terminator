// SharedPrefs.kt
package com.abusalem.guard.utils

import android.content.Context

object SharedPrefs {

    private const val PREF_NAME = "AbuSalemPrefs"
    private const val KEY_TARGET = "TargetNumber"

    fun saveTargetNumber(context: Context, number: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_TARGET, number).apply()
    }

    fun getTargetNumber(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_TARGET, "+967712345678") ?: "+967712345678"
    }
}
