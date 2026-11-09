package com.vibelink.client.install

import kotlinx.coroutines.CompletableDeferred
import java.util.concurrent.atomic.AtomicReference

object ApkInstallBridge {
    data class Outcome(
        val code: String,
        val message: String? = null,
    )

    private val waiter = AtomicReference<CompletableDeferred<Outcome>?>(null)

    fun reset(): CompletableDeferred<Outcome> {
        val next = CompletableDeferred<Outcome>()
        waiter.getAndSet(next)?.cancel()
        return next
    }

    fun complete(outcome: Outcome) {
        waiter.getAndSet(null)?.complete(outcome)
    }
}
