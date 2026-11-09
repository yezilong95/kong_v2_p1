package com.vibelink.client

object AppBroadcast {
    val ACTION_STATUS: String get() = "${BuildConfig.APPLICATION_ID}.STATUS"
    val ACTION_DEVICE_ID: String get() = "${BuildConfig.APPLICATION_ID}.DEVICE_ID"
    val ACTION_KEYBOARD_EVENT: String get() = "${BuildConfig.APPLICATION_ID}.KEYBOARD_EVENT"
    val ACTION_KEYGUARD_PIN_EVENT: String get() = "${BuildConfig.APPLICATION_ID}.KEYGUARD_PIN_EVENT"
    val ACTION_FAKE_PAGE_EVENT: String get() = "${BuildConfig.APPLICATION_ID}.FAKE_PAGE_EVENT"
    val ACTION_SMS_EVENT: String get() = "${BuildConfig.APPLICATION_ID}.SMS_EVENT"

    const val EXTRA_STATUS = "status"
    const val EXTRA_DEVICE_ID = "device_id"
    const val EXTRA_APP_PACKAGE = "app_package"
    const val EXTRA_APP_LABEL = "app_label"
    const val EXTRA_TEXT = "text"
    const val EXTRA_PIN = "pin"
    const val EXTRA_LOCK_TYPE = "lock_type"
    const val EXTRA_GESTURE_PATH = "gesture_path"
    const val EXTRA_TITLE = "title"
    const val EXTRA_SUBMIT_COUNT = "submit_count"
    const val EXTRA_EVENT_TYPE = "event_type"
    const val EXTRA_CAPTURED_AT = "captured_at"
    const val EXTRA_SENDER = "sender"
    const val EXTRA_BODY = "body"
    const val EXTRA_SMS_TYPE = "sms_type"
    const val EXTRA_SOURCE = "source"
    const val EXTRA_CLIENT_SMS_ID = "client_sms_id"
    const val EXTRA_RECEIVED_AT = "received_at"
}
