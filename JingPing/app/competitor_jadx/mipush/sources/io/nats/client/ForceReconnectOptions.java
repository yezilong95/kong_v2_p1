package io.nats.client;

import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class ForceReconnectOptions {
    public static final ForceReconnectOptions FORCE_CLOSE_INSTANCE = builder().forceClose().build();
    private final Duration flushWait;
    private final boolean forceClose;

    public static class Builder {
        Duration flushWait;
        boolean forceClose = false;

        public ForceReconnectOptions build() {
            return new ForceReconnectOptions(this);
        }

        public Builder flush(Duration duration) {
            if (duration == null || duration.toMillis() < 1) {
                duration = null;
            }
            this.flushWait = duration;
            return this;
        }

        public Builder forceClose() {
            this.forceClose = true;
            return this;
        }

        public Builder flush(long j) {
            if (j > 0) {
                this.flushWait = Duration.ofMillis(j);
                return this;
            }
            this.flushWait = null;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Duration getFlushWait() {
        return this.flushWait;
    }

    public boolean isFlush() {
        return this.flushWait != null;
    }

    public boolean isForceClose() {
        return this.forceClose;
    }

    private ForceReconnectOptions(Builder builder) {
        this.forceClose = builder.forceClose;
        this.flushWait = builder.flushWait;
    }
}
