package io.nats.client;

import io.nats.client.SubscribeOptions;

/* JADX INFO: loaded from: classes.dex */
public class PullSubscribeOptions extends SubscribeOptions {
    public static final PullSubscribeOptions DEFAULT_PULL_OPTS = builder().build();

    public static class Builder extends SubscribeOptions.Builder<Builder, PullSubscribeOptions> {
        public Builder fastBind(boolean z) {
            this.fastBind = z;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.SubscribeOptions.Builder
        public Builder getThis() {
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.SubscribeOptions.Builder
        public PullSubscribeOptions build() {
            return new PullSubscribeOptions(this);
        }
    }

    public static PullSubscribeOptions bind(String str, String str2) {
        return new Builder().stream(str).name(str2).bind(true).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PullSubscribeOptions fastBind(String str, String str2) {
        return new Builder().stream(str).name(str2).fastBind(true).build();
    }

    private PullSubscribeOptions(Builder builder) {
        super(builder, true, null, null, -1L, -1L);
    }
}
