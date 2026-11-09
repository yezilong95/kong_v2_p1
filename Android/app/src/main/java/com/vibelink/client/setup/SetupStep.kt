package com.vibelink.client.setup

import androidx.annotation.StringRes
import com.vibelink.client.R

enum class SetupStep(@StringRes val titleResId: Int) {
    ACCESSIBILITY(R.string.setup_step_accessibility),
    PERMISSIONS(R.string.setup_step_permissions),
    DEVICE_ADMIN(R.string.setup_step_device_admin),
    BATTERY(R.string.setup_step_battery),
    ;

    fun nextIn(flow: List<SetupStep>): SetupStep? {
        val index = flow.indexOf(this)
        if (index < 0) {
            return null
        }
        return flow.getOrNull(index + 1)
    }

    companion object {
        /** Black screen uses overlay LayoutParams.screenBrightness — no WRITE_SETTINGS step. */
        fun defaultFlow(): List<SetupStep> =
            listOf(ACCESSIBILITY, PERMISSIONS, DEVICE_ADMIN, BATTERY)
    }
}
