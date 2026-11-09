package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class ConsumerLimits implements JsonSerializable {
    private final Duration inactiveThreshold;
    private final Integer maxAckPending;

    public static class Builder {
        private Duration inactiveThreshold;
        private Integer maxAckPending;

        public ConsumerLimits build() {
            return new ConsumerLimits(this);
        }

        public Builder inactiveThreshold(Duration duration) {
            this.inactiveThreshold = ConsumerConfiguration.normalize(duration);
            return this;
        }

        public Builder maxAckPending(Long l) {
            this.maxAckPending = ConsumerConfiguration.normalize(l, 0);
            return this;
        }

        public Builder inactiveThreshold(long j) {
            this.inactiveThreshold = ConsumerConfiguration.normalizeDuration(j);
            return this;
        }

        public Builder maxAckPending(long j) {
            this.maxAckPending = ConsumerConfiguration.normalize(Long.valueOf(j), 0);
            return this;
        }
    }

    public ConsumerLimits(JsonValue jsonValue) {
        this.inactiveThreshold = JsonValueUtils.readNanos(jsonValue, ApiConstants.INACTIVE_THRESHOLD);
        this.maxAckPending = JsonValueUtils.readInteger(jsonValue, ApiConstants.MAX_ACK_PENDING);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ConsumerLimits optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new ConsumerLimits(jsonValue);
    }

    public Duration getInactiveThreshold() {
        return this.inactiveThreshold;
    }

    public long getMaxAckPending() {
        return ConsumerConfiguration.getOrUnset(this.maxAckPending);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addFieldAsNanos(sbBeginJson, ApiConstants.INACTIVE_THRESHOLD, this.inactiveThreshold);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_ACK_PENDING, this.maxAckPending);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public ConsumerLimits(Builder builder) {
        this.inactiveThreshold = builder.inactiveThreshold;
        this.maxAckPending = builder.maxAckPending;
    }
}
