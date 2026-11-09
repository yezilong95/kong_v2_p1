package io.nats.client;

import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class FetchConsumeOptions extends BaseConsumeOptions {
    public static FetchConsumeOptions DEFAULT_FETCH_OPTIONS = builder().build();
    private final boolean noWait;

    public static class Builder extends BaseConsumeOptions.Builder<Builder, FetchConsumeOptions> {
        protected boolean noWait = false;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public Builder getThis() {
            return this;
        }

        public Builder max(int i, int i2) {
            messages(i2);
            return bytes(i);
        }

        public Builder maxBytes(long j) {
            return (Builder) super.bytes(j);
        }

        public Builder maxMessages(int i) {
            messages(i);
            return bytes(-1L);
        }

        public Builder noWait() {
            this.noWait = true;
            this.expiresIn = -1L;
            return this;
        }

        public Builder noWaitExpiresIn(long j) {
            this.noWait = true;
            return expiresIn(j);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public FetchConsumeOptions build() {
            return new FetchConsumeOptions(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public Builder expiresIn(long j) {
            if (!this.noWait || j >= 1) {
                return (Builder) super.expiresIn(j);
            }
            this.expiresIn = -1L;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public Builder jsonValue(JsonValue jsonValue) {
            super.jsonValue(jsonValue);
            if (JsonValueUtils.readBoolean(jsonValue, ApiConstants.NO_WAIT, Boolean.FALSE).booleanValue()) {
                noWaitExpiresIn(JsonValueUtils.readLong(jsonValue, ApiConstants.EXPIRES_IN, -1L));
            }
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getMaxBytes() {
        return this.bytes;
    }

    public int getMaxMessages() {
        return this.messages;
    }

    public boolean isNoWait() {
        return this.noWait;
    }

    @Override // io.nats.client.BaseConsumeOptions
    public void subclassSpecificToJson(StringBuilder sb) {
        JsonUtils.addFldWhenTrue(sb, ApiConstants.NO_WAIT, Boolean.valueOf(this.noWait));
    }

    private FetchConsumeOptions(Builder builder) {
        super(builder);
        this.noWait = builder.noWait;
    }
}
