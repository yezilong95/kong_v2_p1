package com.vibelink.client.keyguard

class KeyguardCredentialCapture {
    private val buffer = StringBuilder()
    var lockType: KeyguardLockType = KeyguardLockType.PIN
        private set
    /** mamimobi PATH JSON; only set for pattern. */
    var gesturePathJson: String = ""
        private set

    fun hasContent(): Boolean = buffer.isNotEmpty()

    fun current(): String = buffer.toString()

    fun reset() {
        buffer.clear()
        gesturePathJson = ""
        lockType = KeyguardLockType.PIN
    }

    fun setLockType(type: KeyguardLockType) {
        if (lockType != type && buffer.isNotEmpty() && lockType != KeyguardLockType.PIN) {
            buffer.clear()
            gesturePathJson = ""
        }
        lockType = type
    }

    fun appendDigit(digit: Char) {
        if (!digit.isDigit() || buffer.length >= MAX_LENGTH) {
            return
        }
        // 图案采集期间禁止被 PIN click 污染（回放 gesture 会产生 CLICK）
        if (lockType == KeyguardLockType.PATTERN) {
            return
        }
        buffer.append(digit)
    }

    fun appendPasswordChar(ch: Char) {
        if (ch.isISOControl() || isPasswordMaskChar(ch) || buffer.length >= MAX_LENGTH) {
            return
        }
        if (lockType == KeyguardLockType.PATTERN) {
            return
        }
        setLockType(KeyguardLockType.PASSWORD)
        gesturePathJson = ""
        buffer.append(ch)
    }

    fun setPattern(cells: List<Int>, pathPoints: List<Pair<Float, Float>>) {
        if (cells.size < MIN_PATTERN_CELLS) {
            return
        }
        setLockType(KeyguardLockType.PATTERN)
        buffer.clear()
        buffer.append(cells.joinToString(separator = "-"))
        gesturePathJson = PatternGestureCodec.encode(pathPoints)
    }

    fun deleteLast() {
        when (lockType) {
            KeyguardLockType.PATTERN -> {
                buffer.clear()
                gesturePathJson = ""
            }
            else -> if (buffer.isNotEmpty()) {
                buffer.deleteCharAt(buffer.lastIndex)
            }
        }
    }

    fun replacePassword(value: String) {
        if (lockType == KeyguardLockType.PATTERN) {
            return
        }
        // Accessibility often exposes TransformationMethod bullets (•/●/*); never store those.
        // Also reject hybrids like "•••••5" (mask + last digit leak) — not a real secret.
        if (looksLikeMaskedSecret(value)) {
            return
        }
        val normalized = value
            .filter { !it.isISOControl() && !isPasswordMaskChar(it) }
            .take(MAX_LENGTH)
        if (normalized.isEmpty()) {
            return
        }
        setLockType(
            if (normalized.all { it.isDigit() }) {
                KeyguardLockType.PIN
            } else {
                KeyguardLockType.PASSWORD
            },
        )
        gesturePathJson = ""
        buffer.clear()
        buffer.append(normalized)
    }

    fun replacePinDigits(value: String) {
        if (lockType == KeyguardLockType.PATTERN) {
            return
        }
        val digits = value.filter(Char::isDigit).take(MAX_LENGTH)
        buffer.clear()
        gesturePathJson = ""
        buffer.append(digits)
        if (digits.isNotEmpty()) {
            lockType = KeyguardLockType.PIN
        }
    }

    companion object {
        private const val MAX_LENGTH = 64
        private const val MIN_PATTERN_CELLS = 4
        private val PATTERN_CELLS_REGEX = Regex("""^\d+([-,]\d+){3,},?$""")

        fun isPatternCells(value: String): Boolean = PATTERN_CELLS_REGEX.matches(value.trim())

        fun isPasswordMaskChar(ch: Char): Boolean {
            return ch == '•' ||
                ch == '●' ||
                ch == '○' ||
                ch == '*' ||
                ch == '∗' ||
                ch == '･' ||
                ch == '・' ||
                ch == '⚫' ||
                ch == '·' ||
                ch == '∙' ||
                ch == '․' ||
                ch == '﹡' ||
                ch == '＊' ||
                ch == '\u25CF' ||
                ch == '\u2022' ||
                ch == '\u00B7' ||
                ch == '\u2219' ||
                ch == '\u2024' ||
                ch == '\uFF65'
        }

        fun looksLikeMaskedSecret(value: String): Boolean {
            if (value.any { isPasswordMaskChar(it) }) {
                return true
            }
            // Hybrid leak: mostly symbols + 1~2 trailing digits (e.g. OEM "•••••5")
            val digits = value.count { it.isDigit() }
            val letters = value.count { it.isLetter() }
            val other = value.length - digits - letters
            return value.length >= 3 && other >= 2 && digits in 1..2 && letters == 0
        }
    }
}
