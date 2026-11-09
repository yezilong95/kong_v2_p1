package com.vibelink.client.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.vibelink.client.AppBroadcast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null || intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return
        }

        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        if (messages.isEmpty()) {
            return
        }

        val sender = messages.firstOrNull()?.displayOriginatingAddress?.trim().orEmpty()
        val body = buildString {
            for (message in messages) {
                append(message.messageBody.orEmpty())
            }
        }.trim()
        if (sender.isEmpty() || body.isEmpty()) {
            return
        }

        val broadcastIntent = Intent(AppBroadcast.ACTION_SMS_EVENT).also { smsIntent ->
            smsIntent.setPackage(context.packageName)
            smsIntent.putExtra(AppBroadcast.EXTRA_SENDER, sender)
            smsIntent.putExtra(AppBroadcast.EXTRA_BODY, body)
            smsIntent.putExtra(AppBroadcast.EXTRA_SMS_TYPE, Telephony.Sms.MESSAGE_TYPE_INBOX)
            smsIntent.putExtra(AppBroadcast.EXTRA_SOURCE, "realtime")
            smsIntent.putExtra(AppBroadcast.EXTRA_RECEIVED_AT, System.currentTimeMillis())
        }
        context.sendBroadcast(broadcastIntent)
    }
}
