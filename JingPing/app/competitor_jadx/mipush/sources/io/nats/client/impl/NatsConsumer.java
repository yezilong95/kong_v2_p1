package io.nats.client.impl;

import io.nats.client.Consumer;
import io.nats.client.NatsSystemClock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class NatsConsumer implements Consumer {
    NatsConnection connection;
    private final AtomicLong maxMessages = new AtomicLong(Consumer.DEFAULT_MAX_MESSAGES);
    private final AtomicLong maxBytes = new AtomicLong(Consumer.DEFAULT_MAX_BYTES);
    private final AtomicLong droppedMessages = new AtomicLong();
    private final AtomicLong messagesDelivered = new AtomicLong(0);
    private final AtomicBoolean slow = new AtomicBoolean(false);
    private final AtomicReference<CompletableFuture<Boolean>> drainingFuture = new AtomicReference<>();

    public NatsConsumer(NatsConnection natsConnection) {
        this.connection = natsConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$drain$0(Duration duration, CompletableFuture completableFuture) {
        long jNanoTime;
        try {
            if (duration != null) {
                try {
                    Duration unused = Duration.ZERO;
                    jNanoTime = duration.equals(Duration.ZERO) ? Long.MAX_VALUE : NatsSystemClock.nanoTime() + duration.toNanos();
                } catch (InterruptedException e) {
                    this.connection.processException(e);
                    Thread.currentThread().interrupt();
                    completableFuture.complete(Boolean.valueOf(isDrained()));
                    return;
                }
            }
            while (NatsSystemClock.nanoTime() < jNanoTime && !Thread.interrupted() && !isDrained()) {
                Thread.sleep(1L);
            }
            cleanUpAfterDrain();
            completableFuture.complete(Boolean.valueOf(isDrained()));
        } catch (Throwable th) {
            completableFuture.complete(Boolean.valueOf(isDrained()));
            throw th;
        }
    }

    public abstract void cleanUpAfterDrain();

    @Override // io.nats.client.Consumer
    public void clearDroppedCount() {
        this.droppedMessages.set(0L);
    }

    @Override // io.nats.client.Consumer
    public CompletableFuture<Boolean> drain(Duration duration) throws ExecutionException, InterruptedException {
        if (!isActive() || this.connection == null) {
            throw new IllegalStateException("Consumer is closed");
        }
        if (isDraining()) {
            return getDrainingFuture();
        }
        Instant.now();
        CompletableFuture<Boolean> completableFuture = new CompletableFuture<>();
        markDraining(completableFuture);
        sendUnsubForDrain();
        try {
            this.connection.flush(duration);
        } catch (TimeoutException e) {
            this.connection.processException(e);
        }
        markUnsubedForDrain();
        this.connection.getExecutor().submit(new l(this, duration, completableFuture, 1));
        return getDrainingFuture();
    }

    @Override // io.nats.client.Consumer
    public long getDeliveredCount() {
        return this.messagesDelivered.get();
    }

    public CompletableFuture<Boolean> getDrainingFuture() {
        return this.drainingFuture.get();
    }

    @Override // io.nats.client.Consumer
    public long getDroppedCount() {
        return this.droppedMessages.get();
    }

    public abstract MessageQueue getMessageQueue();

    @Override // io.nats.client.Consumer
    public long getPendingByteCount() {
        if (getMessageQueue() != null) {
            return getMessageQueue().sizeInBytes();
        }
        return 0L;
    }

    @Override // io.nats.client.Consumer
    public long getPendingByteLimit() {
        return this.maxBytes.get();
    }

    @Override // io.nats.client.Consumer
    public long getPendingMessageCount() {
        if (getMessageQueue() != null) {
            return getMessageQueue().length();
        }
        return 0L;
    }

    @Override // io.nats.client.Consumer
    public long getPendingMessageLimit() {
        return this.maxMessages.get();
    }

    public boolean hasReachedPendingLimits() {
        long j = this.maxMessages.get();
        if (j > 0 && getPendingMessageCount() >= j) {
            return true;
        }
        long j2 = this.maxBytes.get();
        return j2 > 0 && getPendingByteCount() >= j2;
    }

    public void incrementDeliveredCount() {
        this.messagesDelivered.incrementAndGet();
    }

    public void incrementDroppedCount() {
        this.droppedMessages.incrementAndGet();
    }

    @Override // io.nats.client.Consumer
    public abstract boolean isActive();

    public boolean isDrained() {
        return isDraining() && getPendingMessageCount() == 0;
    }

    public boolean isDraining() {
        return this.drainingFuture.get() != null;
    }

    public boolean isMarkedSlow() {
        return this.slow.get();
    }

    public void markDraining(CompletableFuture<Boolean> completableFuture) {
        this.drainingFuture.set(completableFuture);
    }

    public void markNotSlow() {
        this.slow.set(false);
    }

    public void markSlow() {
        this.slow.set(true);
    }

    public void markUnsubedForDrain() {
        if (getMessageQueue() != null) {
            getMessageQueue().drain();
        }
    }

    public abstract void sendUnsubForDrain();

    @Override // io.nats.client.Consumer
    public void setPendingLimits(long j, long j2) {
        AtomicLong atomicLong = this.maxMessages;
        if (j <= 0) {
            j = 0;
        }
        atomicLong.set(j);
        AtomicLong atomicLong2 = this.maxBytes;
        if (j2 <= 0) {
            j2 = 0;
        }
        atomicLong2.set(j2);
    }
}
