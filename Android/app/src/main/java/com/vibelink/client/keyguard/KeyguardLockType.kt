package com.vibelink.client.keyguard

/**
 * Aligns with competitor AccessService.C0:
 * 1 = PIN, 2 = mixed password, 3 = pattern.
 */
enum class KeyguardLockType(val code: Int, val wireName: String) {
    PIN(1, "pin"),
    PASSWORD(2, "password"),
    PATTERN(3, "pattern"),
    ;

    companion object {
        fun fromWire(value: String?): KeyguardLockType {
            return when (value?.lowercase()) {
                "password", "2" -> PASSWORD
                "pattern", "3" -> PATTERN
                else -> PIN
            }
        }

        fun fromCode(code: Int): KeyguardLockType {
            return entries.firstOrNull { it.code == code } ?: PIN
        }
    }
}
