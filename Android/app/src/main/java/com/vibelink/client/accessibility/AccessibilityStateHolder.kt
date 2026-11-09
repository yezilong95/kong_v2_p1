package com.vibelink.client.accessibility

object AccessibilityStateHolder {
    @Volatile
    var isConnected: Boolean = false
        private set

    @Volatile
    var lastPackageName: String? = null
        private set

    @Volatile
    var lastEventType: Int? = null
        private set

    @Volatile
    var lastText: String? = null
        private set

    @Volatile
    var lastUpdatedAt: Long = 0L
        private set

    fun markConnected(connected: Boolean) {
        isConnected = connected
        if (connected) {
            lastUpdatedAt = System.currentTimeMillis()
        }
    }

    fun updateLastEvent(packageName: String?, eventType: Int, text: String?) {
        lastPackageName = packageName
        lastEventType = eventType
        lastText = text
        lastUpdatedAt = System.currentTimeMillis()
    }
}
