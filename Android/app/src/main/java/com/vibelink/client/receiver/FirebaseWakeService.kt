package com.vibelink.client.receiver

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.vibelink.client.BuildConfig

/**
 * FCM data 消息落点。
 *
 * 分工：真正的“解冻”由 [FcmWakeReceiver] 收 `com.google.android.c2dm.intent.RECEIVE`
 * 完成（那是 Hiber 白名单放行的 action），本类是 Firebase 解析完 payload 后的第二跳，
 * 用于拿 data 里的 `wakeUntil` / `keepAwakeMs`。
 *
 * 注意 `BuildConfig.FCM_ENABLED`：没有 `google-services.json`（或包名不匹配）时
 * 不 apply google-services 插件，`google_app_id` 资源缺失，Firebase 无法初始化。
 * 此时本类不会被系统实例化（没订阅就不会有消息），但仍做一次防御性跳过，
 * 避免任何路径下抛异常。
 */
class FirebaseWakeService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        if (!BuildConfig.FCM_ENABLED) {
            Log.i(TAG, "fcm message ignored: FCM_ENABLED=false")
            return
        }
        // data 消息的值全是字符串；notification 消息在这里读不到 payload
        val wakeUntil = message.data[FcmWakeHandler.EXTRA_WAKE_UNTIL]?.trim()?.toLongOrNull() ?: 0L
        val keepAwakeMs = message.data[FcmWakeHandler.EXTRA_KEEP_AWAKE_MS]?.trim()?.toLongOrNull() ?: 0L
        Log.i(TAG, "fcm message id=${message.messageId} data=${message.data.keys}")
        FcmWakeHandler.handle(this, wakeUntil, keepAwakeMs, source = "fcm")
    }

    /**
     * token 变化（重装、清数据、服务端撤销）时回调。
     * 目前只落日志：上报通道还没定，登记到服务端属于另一件事。
     */
    override fun onNewToken(token: String) {
        Log.i(TAG, "fcm token refreshed len=${token.length} enabled=${BuildConfig.FCM_ENABLED}")
    }

    companion object {
        private const val TAG = "ZombieWake"
    }
}
