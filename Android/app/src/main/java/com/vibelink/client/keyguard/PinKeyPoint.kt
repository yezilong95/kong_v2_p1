package com.vibelink.client.keyguard

import android.graphics.Rect

/**
 * 对齐竞品 mamimobi ScreenPoints / AccessService.P0：
 * keyId = "0".."9" | "d"(删除) | "e"(确认/OK)
 */
data class PinKeyPoint(
    val keyId: String,
    val rect: Rect,
    val resId: String? = null,
) {
    fun copyRect(): PinKeyPoint = copy(rect = Rect(rect))

    val isDigit: Boolean get() = keyId.length == 1 && keyId[0].isDigit()
    val isDelete: Boolean get() = keyId == KEY_DELETE
    val isEnter: Boolean get() = keyId == KEY_ENTER

    companion object {
        const val KEY_DELETE = "d"
        const val KEY_ENTER = "e"
    }
}
