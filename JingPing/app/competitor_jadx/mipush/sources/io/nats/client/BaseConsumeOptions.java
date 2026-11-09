package io.nats.client;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class BaseConsumeOptions implements JsonSerializable {
    public static final long DEFAULT_EXPIRES_IN_MILLIS = 30000;
    public static final int DEFAULT_MESSAGE_COUNT = 500;
    public static final int DEFAULT_MESSAGE_COUNT_WHEN_BYTES = 1000000;
    public static final int DEFAULT_THRESHOLD_PERCENT = 25;
    public static final long MAX_HEARTBEAT_MILLIS = 30000;
    public static final int MAX_IDLE_HEARTBEAT_PERCENT = 50;
    public static final long MIN_EXPIRES_MILLS = 1000;
    protected final long bytes;
    protected final long expiresIn;
    protected final String group;
    protected final long idleHeartbeat;
    protected final int messages;
    protected final long minAckPending;
    protected final long minPending;
    protected final boolean raiseStatusWarnings;
    protected final int thresholdPercent;

    public BaseConsumeOptions(Builder<?, ?> builder) {
        long j = builder.bytes;
        this.bytes = j;
        if (j > 0) {
            int i = builder.messages;
            this.messages = i < 0 ? DEFAULT_MESSAGE_COUNT_WHEN_BYTES : i;
        } else {
            int i2 = builder.messages;
            this.messages = i2 < 0 ? DEFAULT_MESSAGE_COUNT : i2;
        }
        long j2 = builder.expiresIn;
        this.expiresIn = j2;
        this.thresholdPercent = builder.thresholdPercent;
        this.idleHeartbeat = Math.min(30000L, (j2 * 50) / 100);
        this.group = builder.group;
        this.minPending = builder.minPending;
        this.minAckPending = builder.minAckPending;
        this.raiseStatusWarnings = builder.raiseStatusWarnings;
    }

    public long getExpiresInMillis() {
        return this.expiresIn;
    }

    public String getGroup() {
        return this.group;
    }

    public long getIdleHeartbeat() {
        return this.idleHeartbeat;
    }

    public long getMinAckPending() {
        return this.minAckPending;
    }

    public long getMinPending() {
        return this.minPending;
    }

    public int getThresholdPercent() {
        return this.thresholdPercent;
    }

    public boolean raiseStatusWarnings() {
        return this.raiseStatusWarnings;
    }

    public void subclassSpecificToJson(StringBuilder sb) {
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.MESSAGES, Integer.valueOf(this.messages));
        JsonUtils.addField(sbBeginJson, ApiConstants.BYTES, Long.valueOf(this.bytes));
        JsonUtils.addField(sbBeginJson, ApiConstants.EXPIRES_IN, Long.valueOf(this.expiresIn));
        JsonUtils.addField(sbBeginJson, ApiConstants.IDLE_HEARTBEAT, Long.valueOf(this.idleHeartbeat));
        JsonUtils.addField(sbBeginJson, ApiConstants.THRESHOLD_PERCENT, Integer.valueOf(this.thresholdPercent));
        JsonUtils.addField(sbBeginJson, ApiConstants.GROUP, this.group);
        JsonUtils.addField(sbBeginJson, ApiConstants.MIN_PENDING, Long.valueOf(this.minPending));
        JsonUtils.addField(sbBeginJson, ApiConstants.MIN_ACK_PENDING, Long.valueOf(this.minAckPending));
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.RAISE_STATUS_WARNINGS, Boolean.valueOf(this.raiseStatusWarnings));
        subclassSpecificToJson(sbBeginJson);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public static abstract class Builder<B, CO> {
        protected String group;
        protected int messages = -1;
        protected long bytes = 0;
        protected int thresholdPercent = 25;
        protected long expiresIn = 30000;
        protected boolean raiseStatusWarnings = false;
        protected long minPending = -1;
        protected long minAckPending = -1;

        public abstract CO build();

        public B bytes(long j) {
            if (j < 1) {
                j = 0;
            }
            this.bytes = j;
            return getThis();
        }

        public B expiresIn(long j) {
            if (j < 1) {
                this.expiresIn = 30000L;
            } else {
                if (j < 1000) {
                    throw new IllegalArgumentException("Expires must be greater than or equal to 1000");
                }
                this.expiresIn = j;
            }
            return getThis();
        }

        public abstract B getThis();

        public B group(String str) {
            this.group = str;
            return getThis();
        }

        public B json(String str) throws JsonParseException {
            return jsonValue(JsonParser.parse(str));
        }

        public B jsonValue(JsonValue jsonValue) {
            messages(JsonValueUtils.readInteger(jsonValue, ApiConstants.MESSAGES, -1));
            bytes(JsonValueUtils.readLong(jsonValue, ApiConstants.BYTES, -1L));
            expiresIn(JsonValueUtils.readLong(jsonValue, ApiConstants.EXPIRES_IN, 30000L));
            thresholdPercent(JsonValueUtils.readInteger(jsonValue, ApiConstants.THRESHOLD_PERCENT, -1));
            raiseStatusWarnings(JsonValueUtils.readBoolean(jsonValue, ApiConstants.RAISE_STATUS_WARNINGS, Boolean.FALSE).booleanValue());
            group(JsonValueUtils.readStringEmptyAsNull(jsonValue, ApiConstants.GROUP));
            minPending(JsonValueUtils.readLong(jsonValue, ApiConstants.MIN_PENDING, -1L));
            minAckPending(JsonValueUtils.readLong(jsonValue, ApiConstants.MIN_ACK_PENDING, -1L));
            return getThis();
        }

        public B messages(int i) {
            if (i < 1) {
                i = -1;
            }
            this.messages = i;
            return getThis();
        }

        public B minAckPending(long j) {
            if (j < 1) {
                j = -1;
            }
            this.minAckPending = j;
            return getThis();
        }

        public B minPending(long j) {
            if (j < 1) {
                j = -1;
            }
            this.minPending = j;
            return getThis();
        }

        public B raiseStatusWarnings() {
            this.raiseStatusWarnings = true;
            return getThis();
        }

        public B thresholdPercent(int i) {
            this.thresholdPercent = i < 1 ? 25 : Math.min(100, i);
            return getThis();
        }

        public B raiseStatusWarnings(boolean z) {
            this.raiseStatusWarnings = z;
            return getThis();
        }
    }
}
