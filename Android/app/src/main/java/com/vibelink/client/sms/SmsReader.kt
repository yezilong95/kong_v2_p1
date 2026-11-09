package com.vibelink.client.sms

import android.content.Context
import android.provider.Telephony

class SmsReader(
    private val context: Context,
) {
    fun readRecent(limit: Int = 50): List<SmsRecord> {
        if (!SmsHelper.hasSmsPermissions(context)) {
            return emptyList()
        }

        val result = mutableListOf<SmsRecord>()
        val projection = arrayOf(
            Telephony.Sms._ID,
            Telephony.Sms.ADDRESS,
            Telephony.Sms.BODY,
            Telephony.Sms.DATE,
            Telephony.Sms.TYPE,
        )

        context.contentResolver.query(
            Telephony.Sms.CONTENT_URI,
            projection,
            null,
            null,
            "${Telephony.Sms.DATE} DESC",
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndexOrThrow(Telephony.Sms._ID)
            val addressIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS)
            val bodyIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.BODY)
            val dateIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.DATE)
            val typeIndex = cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE)

            while (cursor.moveToNext() && result.size < limit) {
                val body = cursor.getString(bodyIndex)?.trim().orEmpty()
                val sender = cursor.getString(addressIndex)?.trim().orEmpty()
                if (body.isBlank() || sender.isBlank()) {
                    continue
                }

                result.add(
                    SmsRecord(
                        clientSmsId = cursor.getString(idIndex),
                        sender = sender,
                        body = body,
                        smsType = cursor.getInt(typeIndex),
                        receivedAt = cursor.getLong(dateIndex),
                    ),
                )
            }
        }

        return result
    }
}
