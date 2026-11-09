package com.vibelink.client.screen

import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager

/**
 * Holds MediaProjection consent for the process lifetime.
 * On Android 14+, [MediaProjectionManager.getMediaProjection] may only be used once per consent.
 */
object MediaProjectionHolder {
    @Volatile
    private var resultCode: Int = 0

    @Volatile
    private var resultData: Intent? = null

    @Volatile
    private var projection: MediaProjection? = null

    @Volatile
    private var grantedAt: Long = 0L

    fun hasConsent(): Boolean = projection != null || (resultData != null && resultCode != 0)

    fun store(resultCode: Int, data: Intent) {
        clear()
        this.resultCode = resultCode
        this.resultData = Intent(data)
        this.grantedAt = System.currentTimeMillis()
    }

    fun clear() {
        runCatching { projection?.stop() }
        projection = null
        resultCode = 0
        resultData = null
        grantedAt = 0L
    }

    @Synchronized
    fun getOrCreateProjection(context: Context): MediaProjection? {
        projection?.let { return it }
        val data = resultData ?: return null
        if (resultCode == 0) {
            return null
        }
        val manager = context.getSystemService(MediaProjectionManager::class.java) ?: return null
        val created = runCatching {
            manager.getMediaProjection(resultCode, data)
        }.getOrNull() ?: return null
        projection = created
        // Intent can only create projection once on newer Android.
        resultData = null
        return created
    }

    fun createConsentIntent(context: Context): Intent {
        val manager = context.getSystemService(MediaProjectionManager::class.java)
            ?: error("media_projection_unavailable")
        return manager.createScreenCaptureIntent()
    }

    fun grantedAt(): Long = grantedAt
}
