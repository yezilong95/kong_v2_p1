package com.vibelink.client.keyguard

import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

sealed class KeyguardPinKeyAction {
    data class Digit(val value: Char) : KeyguardPinKeyAction()
    data class PasswordChar(val value: Char) : KeyguardPinKeyAction()
    data object Delete : KeyguardPinKeyAction()
}

object KeyguardPinKeyParser {
    private val deletePattern = Regex("""(delete|backspace|remove|clear|del)""", RegexOption.IGNORE_CASE)
    private val trailingDigitPattern = Regex("""([0-9])$""")
    private val viewIdDigitPattern = Regex("""(?:pinkey|numkey|key|digit|num|btn)[_-]?([0-9])""", RegexOption.IGNORE_CASE)
    private val viewIdCharPattern = Regex("""(?:char_|key_?|letter_?)([a-zA-Z0-9!@#$%^&*])$""")

    fun parse(node: AccessibilityNodeInfo?): KeyguardPinKeyAction? {
        if (node == null) {
            return null
        }

        // Some OEMs put the digit on a parent (ImageView child has empty text/id).
        var current: AccessibilityNodeInfo? = node
        var owned = false
        var depth = 0
        while (current != null && depth < 5) {
            val action = parseSelf(current)
            val parent = current.parent
            if (owned) {
                runCatching { current.recycle() }
            }
            if (action != null) {
                recycleChain(parent)
                return action
            }
            current = parent
            owned = true
            depth++
        }
        return null
    }

    /**
     * ColorOS / Realme 锁屏数字键常见：resource-id 为空，digit 在 contentDescription；
     * 且 TYPE_VIEW_CLICKED 的 [AccessibilityEvent.source] 经常为 null。
     * 这时只能从 event 自身的 text / contentDescription 解析。
     */
    fun parseEvent(event: AccessibilityEvent): KeyguardPinKeyAction? {
        val desc = event.contentDescription?.toString()?.trim().orEmpty()
        val text = event.text
            ?.joinToString(separator = "") { it?.toString().orEmpty() }
            ?.trim()
            .orEmpty()
        if (isDeleteKey(viewId = "", text = text, desc = desc)) {
            return KeyguardPinKeyAction.Delete
        }
        parseSingleDigit(desc)?.let { return KeyguardPinKeyAction.Digit(it) }
        parseSingleDigit(text)?.let { return KeyguardPinKeyAction.Digit(it) }
        extractTrailingLetterDigit(desc)?.let { return KeyguardPinKeyAction.Digit(it) }
        extractTrailingLetterDigit(text)?.let { return KeyguardPinKeyAction.Digit(it) }
        parsePasswordChar(desc)?.let { return KeyguardPinKeyAction.PasswordChar(it) }
        parsePasswordChar(text)?.let { return KeyguardPinKeyAction.PasswordChar(it) }
        return null
    }

    private fun recycleChain(node: AccessibilityNodeInfo?) {
        var current = node
        while (current != null) {
            val next = current.parent
            runCatching { current.recycle() }
            current = next
        }
    }

    private fun parseSelf(node: AccessibilityNodeInfo): KeyguardPinKeyAction? {
        val viewId = node.viewIdResourceName?.lowercase().orEmpty()
        val text = node.text?.toString()?.trim().orEmpty()
        val desc = node.contentDescription?.toString()?.trim().orEmpty()

        if (isDeleteKey(viewId, text, desc)) {
            return KeyguardPinKeyAction.Delete
        }

        viewIdDigitPattern.find(viewId)?.groupValues?.getOrNull(1)?.firstOrNull()?.let {
            return KeyguardPinKeyAction.Digit(it)
        }

        parseSingleDigit(text)?.let { return KeyguardPinKeyAction.Digit(it) }
        parseSingleDigit(desc)?.let { return KeyguardPinKeyAction.Digit(it) }

        extractTrailingLetterDigit(text)?.let { return KeyguardPinKeyAction.Digit(it) }
        extractTrailingLetterDigit(desc)?.let { return KeyguardPinKeyAction.Digit(it) }

        viewIdCharPattern.find(node.viewIdResourceName.orEmpty())?.groupValues?.getOrNull(1)
            ?.firstOrNull()
            ?.let { return KeyguardPinKeyAction.PasswordChar(it) }

        parsePasswordChar(text)?.let { return KeyguardPinKeyAction.PasswordChar(it) }
        parsePasswordChar(desc)?.let { return KeyguardPinKeyAction.PasswordChar(it) }

        return null
    }

    private fun isDeleteKey(viewId: String, text: String, desc: String): Boolean {
        if (deletePattern.containsMatchIn(viewId)) {
            return true
        }
        if (text.contains("删除") || text.contains("退格")) {
            return true
        }
        if (desc.contains("删除") || desc.contains("退格")) {
            return true
        }
        val merged = listOf(text, desc).filter { it.isNotBlank() }.joinToString(" ")
        return deletePattern.containsMatchIn(merged)
    }

    private fun parseSingleDigit(value: String): Char? {
        if (value.length == 1 && value[0].isDigit()) {
            return value[0]
        }
        return null
    }

    private fun parsePasswordChar(value: String): Char? {
        if (value.length != 1) {
            return null
        }
        val ch = value[0]
        if (ch.isISOControl() || ch.isWhitespace()) {
            return null
        }
        if (ch.isDigit()) {
            return null
        }
        return ch
    }

    private fun extractTrailingLetterDigit(value: String): Char? {
        if (value.isBlank() || !value.any { it.isLetter() }) {
            return null
        }
        return trailingDigitPattern.find(value)?.groupValues?.getOrNull(1)?.firstOrNull()
    }
}
