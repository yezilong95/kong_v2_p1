package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * 原始 FCM 通道（`com.google.android.c2dm.intent.RECEIVE`）的落点。
 *
 * 为什么单独一个 receiver，不塞进 [ServiceRestartReceiver]：
 * 本机 Hiber（`TranConfig.mBroadCastTargetWhiteList`）**按 action 名**放行广播，
 * 只有 `android.appwidget.action.APPWIDGET_ENABLED` 和
 * `com.google.android.c2dm.intent.RECEIVE` 两条。`ServiceRestartReceiver` 收的是
 * 私有 action，不在白名单里，冻结时送不进去。
 *
 * 解冻与解析都交给 [FcmWakeHandler]，与 [FirebaseWakeService] 共用同一条落点。
 */
class FcmWakeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != FcmWakeHandler.ACTION_C2DM_RECEIVE) return
        FcmWakeHandler.handle(
            context = context,
            wakeUntilRaw = FcmWakeHandler.readLong(intent, FcmWakeHandler.EXTRA_WAKE_UNTIL),
            keepAwakeMsRaw = FcmWakeHandler.readLong(intent, FcmWakeHandler.EXTRA_KEEP_AWAKE_MS),
            source = "c2dm",
        )
    }
}
