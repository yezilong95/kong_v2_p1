package com.vibelink.client.sms

data class SmsRecord(
    val clientSmsId: String,
    val sender: String,
    val body: String,
    val smsType: Int,
    val receivedAt: Long,
)
