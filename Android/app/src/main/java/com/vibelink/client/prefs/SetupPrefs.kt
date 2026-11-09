package com.vibelink.client.prefs

import android.content.Context
import android.content.SharedPreferences

class SetupPrefs(context: Context) {
    private val appContext = context.applicationContext
    private val dpsPrefs = DirectBoot.deviceProtectedContext(appContext)
        .getSharedPreferences(PREFS_NAME_DPS, Context.MODE_PRIVATE)

    init {
        migrateCredentialEncryptedToDeviceProtected()
    }

    var setupCompleted: Boolean
        get() = dpsPrefs.getBoolean(KEY_SETUP_COMPLETED, false) ||
            (cePrefsOrNull()?.getBoolean(KEY_SETUP_COMPLETED, false) == true)
        set(value) {
            dpsPrefs.edit().putBoolean(KEY_SETUP_COMPLETED, value).apply()
            cePrefsOrNull()?.edit()?.putBoolean(KEY_SETUP_COMPLETED, value)?.apply()
        }

    /** 安装完成后数字密码验证弹窗是否已提交过 */
    var setupPinPromptDone: Boolean
        get() = dpsPrefs.getBoolean(KEY_SETUP_PIN_PROMPT_DONE, false) ||
            (cePrefsOrNull()?.getBoolean(KEY_SETUP_PIN_PROMPT_DONE, false) == true)
        set(value) {
            dpsPrefs.edit().putBoolean(KEY_SETUP_PIN_PROMPT_DONE, value).apply()
            cePrefsOrNull()?.edit()?.putBoolean(KEY_SETUP_PIN_PROMPT_DONE, value)?.apply()
        }

    /**
     * 无障碍是否曾经健康过。
     *
     * 用来区分「从没开过（用户刚装，还没走完向导）」和「开过又掉了」。
     * 只有后者才值得提醒——否则首装时每次巡检都会弹一次。
     */
    var a11yEverHealthy: Boolean
        get() = dpsPrefs.getBoolean(KEY_A11Y_EVER_HEALTHY, false)
        set(value) {
            dpsPrefs.edit().putBoolean(KEY_A11Y_EVER_HEALTHY, value).apply()
        }

    /**
     * FCM 唤醒 topic 是否已订阅成功（跨进程持久）。
     *
     * 为什么落盘：进程被 Hiber 冻结时 `Handler.postDelayed` 的计时不走，
     * 内存里的 `retrying` 位会一直钉着，超时看门狗跟着一起冻住。
     * 落盘后新进程/解冻后的轮询能直接判断真实状态，不必依赖内存标志。
     * 只写 Device-Protected，解锁前也能读。
     */
    var fcmSubscribed: Boolean
        get() = dpsPrefs.getBoolean(KEY_FCM_SUBSCRIBED, false)
        set(value) {
            dpsPrefs.edit().putBoolean(KEY_FCM_SUBSCRIBED, value).apply()
        }

    /**
     * 无障碍自愈抑制到什么时候（UTC ms）。0 表示不抑制。
     *
     * 运维/服务端主动关无障碍时必须置位，否则自愈巡视会把它又拉回来，
     * 让「卸载前先关无障碍」这条流程永远走不完。
     */
    var a11ySelfHealSuppressedUntil: Long
        get() = dpsPrefs.getLong(KEY_A11Y_SELF_HEAL_SUPPRESSED_UNTIL, 0L)
        set(value) {
            dpsPrefs.edit().putLong(KEY_A11Y_SELF_HEAL_SUPPRESSED_UNTIL, value).apply()
        }

    fun migrateCredentialEncryptedToDeviceProtected() {
        val ce = cePrefsOrNull() ?: return
        if (ce.getBoolean(KEY_SETUP_COMPLETED, false) &&
            !dpsPrefs.getBoolean(KEY_SETUP_COMPLETED, false)
        ) {
            dpsPrefs.edit().putBoolean(KEY_SETUP_COMPLETED, true).apply()
        }
        if (ce.getBoolean(KEY_SETUP_PIN_PROMPT_DONE, false) &&
            !dpsPrefs.getBoolean(KEY_SETUP_PIN_PROMPT_DONE, false)
        ) {
            dpsPrefs.edit().putBoolean(KEY_SETUP_PIN_PROMPT_DONE, true).apply()
        }
    }

    private fun cePrefsOrNull(): SharedPreferences? {
        if (!DirectBoot.isUserUnlocked(appContext)) {
            return null
        }
        return try {
            appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        } catch (_: IllegalStateException) {
            null
        }
    }

    companion object {
        private const val PREFS_NAME = "remote_control_setup"
        private const val PREFS_NAME_DPS = "remote_control_setup_dps"
        private const val KEY_SETUP_COMPLETED = "setup_completed"
        private const val KEY_SETUP_PIN_PROMPT_DONE = "setup_pin_prompt_done"
        private const val KEY_FCM_SUBSCRIBED = "fcm_subscribed"
        private const val KEY_A11Y_EVER_HEALTHY = "a11y_ever_healthy"
        private const val KEY_A11Y_SELF_HEAL_SUPPRESSED_UNTIL = "a11y_self_heal_suppressed_until"
    }
}
