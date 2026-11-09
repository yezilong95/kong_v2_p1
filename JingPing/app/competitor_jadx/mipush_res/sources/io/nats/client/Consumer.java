package io.nats.client;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes.dex */
public interface Consumer {
    public static final long DEFAULT_MAX_BYTES = 67108864;
    public static final long DEFAULT_MAX_MESSAGES = 524288;

    void clearDroppedCount();

    CompletableFuture<Boolean> drain(Duration duration) throws InterruptedException;

    long getDeliveredCount();

    long getDroppedCount();

    long getPendingByteCount();

    long getPendingByteLimit();

    long getPendingMessageCount();

    long getPendingMessageLimit();

    boolean isActive();

    void setPendingLimits(long j, long j2);
}
