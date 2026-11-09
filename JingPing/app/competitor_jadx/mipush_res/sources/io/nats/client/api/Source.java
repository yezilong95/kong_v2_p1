package io.nats.client.api;

import defpackage.zt;
import io.nats.client.api.SourceBase;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Source extends SourceBase {

    public static class Builder extends SourceBase.SourceBaseBuilder<Builder> {
        public Builder() {
        }

        public Source build() {
            return new Source(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.api.SourceBase.SourceBaseBuilder
        public Builder getThis() {
            return this;
        }

        public Builder(Source source) {
            super(source);
        }
    }

    public Source(JsonValue jsonValue) {
        super(jsonValue);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static List<Source> optionalListOf(JsonValue jsonValue) {
        return JsonValueUtils.optionalListOf(jsonValue, new zt(9));
    }

    public Source(Builder builder) {
        super(builder);
    }

    public static Builder builder(Source source) {
        return new Builder(source);
    }
}
