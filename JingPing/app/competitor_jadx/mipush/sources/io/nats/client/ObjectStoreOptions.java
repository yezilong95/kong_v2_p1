package io.nats.client;

import io.nats.client.FeatureOptions;

/* JADX INFO: loaded from: classes.dex */
public class ObjectStoreOptions extends FeatureOptions {

    public static class Builder extends FeatureOptions.Builder<Builder, ObjectStoreOptions> {
        public Builder() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.FeatureOptions.Builder
        public Builder getThis() {
            return this;
        }

        public Builder(ObjectStoreOptions objectStoreOptions) {
            super(objectStoreOptions);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.FeatureOptions.Builder
        public ObjectStoreOptions build() {
            return new ObjectStoreOptions(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private ObjectStoreOptions(Builder builder) {
        super(builder);
    }

    public static Builder builder(ObjectStoreOptions objectStoreOptions) {
        return new Builder(objectStoreOptions);
    }

    public static Builder builder(JetStreamOptions jetStreamOptions) {
        return new Builder().jetStreamOptions(jetStreamOptions);
    }
}
