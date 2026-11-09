package io.nats.client;

import io.nats.client.FeatureOptions;

/* JADX INFO: loaded from: classes.dex */
public class KeyValueOptions extends FeatureOptions {

    public static class Builder extends FeatureOptions.Builder<Builder, KeyValueOptions> {
        public Builder() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.FeatureOptions.Builder
        public Builder getThis() {
            return this;
        }

        public Builder(KeyValueOptions keyValueOptions) {
            super(keyValueOptions);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.FeatureOptions.Builder
        public KeyValueOptions build() {
            return new KeyValueOptions(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private KeyValueOptions(Builder builder) {
        super(builder);
    }

    public static Builder builder(KeyValueOptions keyValueOptions) {
        return new Builder(keyValueOptions);
    }

    public static Builder builder(JetStreamOptions jetStreamOptions) {
        return new Builder().jetStreamOptions(jetStreamOptions);
    }
}
