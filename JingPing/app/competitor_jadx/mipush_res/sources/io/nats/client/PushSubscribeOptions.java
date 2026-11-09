package io.nats.client;

import io.nats.client.SubscribeOptions;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class PushSubscribeOptions extends SubscribeOptions {
    public static final PushSubscribeOptions DEFAULT_PUSH_OPTS = builder().build();

    public static class Builder extends SubscribeOptions.Builder<Builder, PushSubscribeOptions> {
        private String deliverGroup;
        private String deliverSubject;
        private long pendingMessageLimit = Consumer.DEFAULT_MAX_MESSAGES;
        private long pendingByteLimit = Consumer.DEFAULT_MAX_BYTES;

        public Builder deliverGroup(String str) {
            this.deliverGroup = Validator.emptyAsNull(str);
            return this;
        }

        public Builder deliverSubject(String str) {
            this.deliverSubject = Validator.emptyAsNull(str);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.SubscribeOptions.Builder
        public Builder getThis() {
            return this;
        }

        public Builder ordered(boolean z) {
            this.ordered = z;
            return this;
        }

        public Builder pendingByteLimit(long j) {
            this.pendingByteLimit = j;
            return this;
        }

        public Builder pendingMessageLimit(long j) {
            this.pendingMessageLimit = j;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.nats.client.SubscribeOptions.Builder
        public PushSubscribeOptions build() {
            return new PushSubscribeOptions(this, this.deliverSubject, this.deliverGroup, this.pendingMessageLimit, this.pendingByteLimit);
        }
    }

    @Deprecated
    public static PushSubscribeOptions bind(String str) {
        return stream(str);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static PushSubscribeOptions stream(String str) {
        return new Builder().stream(str).build();
    }

    public String getDeliverGroup() {
        return this.consumerConfig.getDeliverGroup();
    }

    public String getDeliverSubject() {
        return this.consumerConfig.getDeliverSubject();
    }

    private PushSubscribeOptions(Builder builder, String str, String str2, long j, long j2) {
        super(builder, false, str, str2, j, j2);
    }

    public static PushSubscribeOptions bind(String str, String str2) {
        return new Builder().stream(str).name(str2).bind(true).build();
    }
}
