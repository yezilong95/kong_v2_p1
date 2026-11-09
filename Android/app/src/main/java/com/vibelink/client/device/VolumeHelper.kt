package com.vibelink.client.device

import android.content.Context
import android.media.AudioManager
import android.os.Build
import org.json.JSONObject

object VolumeHelper {
    data class MuteResult(
        val ok: Boolean,
        val error: String? = null,
        val snapshot: JSONObject = JSONObject(),
    )

    fun muteAll(context: Context): MuteResult {
        val audioManager = context.getSystemService(AudioManager::class.java)
            ?: return MuteResult(ok = false, error = "audio_manager_unavailable")

        val streams = linkedMapOf(
            AudioManager.STREAM_MUSIC to "music",
            AudioManager.STREAM_RING to "ring",
            AudioManager.STREAM_NOTIFICATION to "notification",
            AudioManager.STREAM_ALARM to "alarm",
            AudioManager.STREAM_SYSTEM to "system",
            AudioManager.STREAM_VOICE_CALL to "voiceCall",
        )

        val levels = JSONObject()
        val errors = mutableListOf<String>()

        for ((stream, name) in streams) {
            val muted = runCatching {
                muteStream(audioManager, stream)
                true
            }.getOrElse { error ->
                errors.add("$name:${error.message ?: "failed"}")
                false
            }
            levels.put(
                name,
                JSONObject()
                    .put("volume", audioManager.getStreamVolume(stream))
                    .put("muted", muted),
            )
        }

        val ringAtMin = isStreamAtMin(audioManager, AudioManager.STREAM_RING)
        val musicAtMin = isStreamAtMin(audioManager, AudioManager.STREAM_MUSIC)
        val notificationAtMin = isStreamAtMin(audioManager, AudioManager.STREAM_NOTIFICATION)
        val ok = ringAtMin && musicAtMin && notificationAtMin

        return MuteResult(
            ok = ok,
            error = if (ok) {
                null
            } else {
                errors.joinToString("; ").ifBlank { "mute_incomplete" }
            },
            snapshot = JSONObject()
                .put("ringerMode", audioManager.ringerMode)
                .put("streams", levels),
        )
    }

    private fun muteStream(audioManager: AudioManager, stream: Int) {
        // 只调各 stream 音量，不改 ringerMode / DND，避免 Notification Policy 权限报错。
        val minVolume = streamMinVolume(audioManager, stream)
        audioManager.setStreamVolume(stream, minVolume, 0)
    }

    private fun isStreamAtMin(audioManager: AudioManager, stream: Int): Boolean {
        return audioManager.getStreamVolume(stream) <= streamMinVolume(audioManager, stream)
    }

    private fun streamMinVolume(audioManager: AudioManager, stream: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            audioManager.getStreamMinVolume(stream)
        } else {
            0
        }
    }
}
