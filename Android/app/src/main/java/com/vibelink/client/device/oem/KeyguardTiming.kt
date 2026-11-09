package com.vibelink.client.device.oem

data class KeyguardTiming(
    val wakeSettleMs: Long = 400L,
    val dismissSettleMs: Long = 700L,
    val tapPauseMs: Long = 400L,
    val digitIntervalMs: Long = 480L,
    val digitVerifyMs: Long = 450L,
    val keyguardTapDurationMs: Long = 220L,
    val swipeAfterPauseMs: Long = 650L,
    val pinPageSettleMs: Long = 500L,
    /** Extra wait after dismiss before tapping digits (mamimobi uses ~3000ms total). */
    val unlockReadyMs: Long = 1800L,
    val confirmAfterPinMs: Long = 400L,
)
