package com.abusalem.guard.security

object PasswordProtectionManager {
    private const val correctPassword = "AbuSalemX2025"

    fun isPasswordValid(input: String): Boolean {
        return input == correctPassword
    }
}
