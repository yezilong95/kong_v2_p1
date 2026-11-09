package io.nats.client.api;

import io.nats.client.api.SourceBase;
import io.nats.client.support.JsonValue;

/* JADX INFO: loaded from: classes.dex */
public class Mirror extends SourceBase {

    public static class Builder extends SourceBase.SourceBaseBuilder<Builder> {
        public Builder() {
        }

        public Mirror build() {
            return new Mirror(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.SourceBase.SourceBaseBuilder
        public Builder getThis() {
            return this;
        }

        public Builder(Mirror mirror) {
            super(mirror);
        }
    }

    public Mirror(JsonValue jsonValue) {
        super(jsonValue);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Mirror optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new Mirror(jsonValue);
    }

    public Mirror(Builder builder) {
        super(builder);
    }

    public static Builder builder(Mirror mirror) {
        return new Builder(mirror);
    }
}
