package io.nats.client.support;

import defpackage.zn;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public class ScheduledTask implements Runnable {
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
    protected final AtomicBoolean executing;
    private final String id;
    protected final long initialDelayNanos;
    protected final AtomicBoolean notShutdown;
    protected final long periodNanos;
    private final Runnable runnable;
    protected final AtomicReference<ScheduledFuture<?>> scheduledFutureRef;

    public ScheduledTask(ScheduledExecutorService scheduledExecutorService, long j, Runnable runnable) {
        this(null, scheduledExecutorService, j, j, TimeUnit.MILLISECONDS, runnable);
    }

    public String getId() {
        return this.id;
    }

    public long getInitialDelayNanos() {
        return this.initialDelayNanos;
    }

    public long getPeriodNanos() {
        return this.periodNanos;
    }

    public boolean isDone() {
        ScheduledFuture<?> scheduledFuture = this.scheduledFutureRef.get();
        return scheduledFuture == null || scheduledFuture.isDone();
    }

    public boolean isExecuting() {
        return this.executing.get();
    }

    public boolean isShutdown() {
        return !this.notShutdown.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.notShutdown.get()) {
                this.executing.set(true);
                this.runnable.run();
            }
        } finally {
            this.executing.set(false);
        }
    }

    public void shutdown() {
        try {
            this.notShutdown.set(false);
            ScheduledFuture<?> scheduledFuture = this.scheduledFutureRef.get();
            if (scheduledFuture != null) {
                this.scheduledFutureRef.set(null);
                if (scheduledFuture.isDone()) {
                    return;
                }
                scheduledFuture.cancel(false);
            }
        } catch (Exception unused) {
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.id);
        if (this.notShutdown.get()) {
            sb.append(" [live");
        } else {
            sb.append(" [shutdown");
        }
        sb.append(isDone() ? "/done" : "/!done");
        return zn.o(sb, this.executing.get() ? "/executing" : "/!executing", "]");
    }

    public ScheduledTask(String str, ScheduledExecutorService scheduledExecutorService, long j, Runnable runnable) {
        this(str, scheduledExecutorService, j, j, TimeUnit.MILLISECONDS, runnable);
    }

    public ScheduledTask(ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit, Runnable runnable) {
        this(null, scheduledExecutorService, j, j, timeUnit, runnable);
    }

    public ScheduledTask(String str, ScheduledExecutorService scheduledExecutorService, long j, TimeUnit timeUnit, Runnable runnable) {
        this(str, scheduledExecutorService, j, j, timeUnit, runnable);
    }

    public ScheduledTask(ScheduledExecutorService scheduledExecutorService, long j, long j2, TimeUnit timeUnit, Runnable runnable) {
        this(null, scheduledExecutorService, j, j2, timeUnit, runnable);
    }

    public ScheduledTask(String str, ScheduledExecutorService scheduledExecutorService, long j, long j2, TimeUnit timeUnit, Runnable runnable) {
        if (str == null || str.isEmpty()) {
            str = "st-" + ID_GENERATOR.getAndIncrement();
        }
        this.id = str;
        this.runnable = runnable;
        this.notShutdown = new AtomicBoolean(true);
        this.executing = new AtomicBoolean(false);
        this.initialDelayNanos = timeUnit.toNanos(j);
        this.periodNanos = timeUnit.toNanos(j2);
        this.scheduledFutureRef = new AtomicReference<>(scheduledExecutorService.scheduleAtFixedRate(this, j, j2, timeUnit));
    }
}
