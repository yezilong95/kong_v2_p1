package io.nats.client;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.Validator;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class PullRequestOptions implements JsonSerializable {
    private final int batchSize;
    private final Duration expiresIn;
    private final String group;
    private final Duration idleHeartbeat;
    private final long maxBytes;
    private final long minAckPending;
    private final long minPending;
    private final boolean noWait;

    public static class Builder {
        private int batchSize;
        private Duration expiresIn;
        private String group;
        private Duration idleHeartbeat;
        private long maxBytes;
        private boolean noWait;
        private long minPending = -1;
        private long minAckPending = -1;

        public Builder batchSize(int i) {
            this.batchSize = i;
            return this;
        }

        public PullRequestOptions build() {
            Validator.validateGtZero(this.batchSize, "Pull batch size");
            Duration duration = this.idleHeartbeat;
            if (duration != null) {
                long nanos = duration.toNanos() * 2;
                if (nanos > 0) {
                    Duration duration2 = this.expiresIn;
                    if (duration2 == null) {
                        throw new IllegalArgumentException("Idle Heartbeat not allowed without expiration.");
                    }
                    if (nanos > duration2.toNanos()) {
                        throw new IllegalArgumentException("Idle Heartbeat cannot be more than half the expiration.");
                    }
                }
            }
            return new PullRequestOptions(this);
        }

        public Builder expiresIn(long j) {
            this.expiresIn = Duration.ofMillis(j);
            return this;
        }

        public Builder group(String str) {
            this.group = str;
            return this;
        }

        public Builder idleHeartbeat(long j) {
            this.idleHeartbeat = Duration.ofMillis(j);
            return this;
        }

        public Builder maxBytes(long j) {
            this.maxBytes = j;
            return this;
        }

        public Builder minAckPending(long j) {
            if (j < 1) {
                j = -1;
            }
            this.minAckPending = j;
            return this;
        }

        public Builder minPending(long j) {
            if (j < 1) {
                j = -1;
            }
            this.minPending = j;
            return this;
        }

        public Builder noWait() {
            this.noWait = true;
            return this;
        }

        public Builder expiresIn(Duration duration) {
            this.expiresIn = duration;
            return this;
        }

        public Builder idleHeartbeat(Duration duration) {
            this.idleHeartbeat = duration;
            return this;
        }

        public Builder noWait(boolean z) {
            this.noWait = z;
            return this;
        }
    }

    public PullRequestOptions(Builder builder) {
        this.batchSize = builder.batchSize;
        this.maxBytes = builder.maxBytes;
        this.noWait = builder.noWait;
        this.expiresIn = builder.expiresIn;
        this.idleHeartbeat = builder.idleHeartbeat;
        this.group = builder.group;
        this.minPending = builder.minPending < 0 ? -1L : builder.minPending;
        this.minAckPending = builder.minAckPending >= 0 ? builder.minAckPending : -1L;
    }

    public static Builder builder(int i) {
        return new Builder().batchSize(i);
    }

    public static Builder noWait(int i) {
        return new Builder().batchSize(i).noWait();
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public Duration getExpiresIn() {
        return this.expiresIn;
    }

    public String getGroup() {
        return this.group;
    }

    public Duration getIdleHeartbeat() {
        return this.idleHeartbeat;
    }

    public long getMaxBytes() {
        return this.maxBytes;
    }

    public long getMinAckPending() {
        return this.minAckPending;
    }

    public long getMinPending() {
        return this.minPending;
    }

    public boolean isNoWait() {
        return this.noWait;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.BATCH, Integer.valueOf(this.batchSize));
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_BYTES, Long.valueOf(this.maxBytes));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.NO_WAIT, Boolean.valueOf(this.noWait));
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.EXPIRES, this.expiresIn);
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.IDLE_HEARTBEAT, this.idleHeartbeat);
        JsonUtils.addField(sbBeginJson, ApiConstants.GROUP, this.group);
        JsonUtils.addField(sbBeginJson, ApiConstants.MIN_PENDING, Long.valueOf(this.minPending));
        JsonUtils.addField(sbBeginJson, ApiConstants.MIN_ACK_PENDING, Long.valueOf(this.minAckPending));
        return JsonUtils.endJson(sbBeginJson).toString();
    }
}
