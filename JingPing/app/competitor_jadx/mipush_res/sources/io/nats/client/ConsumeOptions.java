package io.nats.client;

import io.nats.client.BaseConsumeOptions;

/* JADX INFO: loaded from: classes.dex */
public class ConsumeOptions extends BaseConsumeOptions {
    public static ConsumeOptions DEFAULT_CONSUME_OPTIONS = builder().build();

    public static class Builder extends BaseConsumeOptions.Builder<Builder, ConsumeOptions> {
        public Builder batchBytes(long j) {
            messages(-1);
            return bytes(j);
        }

        public Builder batchSize(int i) {
            messages(i);
            return bytes(-1L);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public Builder getThis() {
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.BaseConsumeOptions.Builder
        public ConsumeOptions build() {
            return new ConsumeOptions(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public long getBatchBytes() {
        return this.bytes;
    }

    public int getBatchSize() {
        return this.messages;
    }

    private ConsumeOptions(Builder builder) {
        super(builder);
    }
}
