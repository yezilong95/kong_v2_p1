package io.nats.client;

import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public abstract class SubscribeOptions {
    public static final long DEFAULT_ORDERED_HEARTBEAT = 5000;
    protected final boolean bind;
    protected final ConsumerConfiguration consumerConfig;
    protected final boolean fastBind;
    protected final long messageAlarmTime;
    protected final String name;
    protected final boolean ordered;
    protected final long pendingByteLimit;
    protected final long pendingMessageLimit;
    protected final boolean pull;
    protected final String stream;

    public static abstract class Builder<B, SO> {
        protected boolean bind;
        protected ConsumerConfiguration cc;
        protected String durable;
        protected boolean fastBind;
        protected long messageAlarmTime = -1;
        protected String name;
        protected boolean ordered;
        protected String stream;

        public B bind(boolean z) {
            this.bind = z;
            return getThis();
        }

        public abstract SO build();

        public B configuration(ConsumerConfiguration consumerConfiguration) {
            this.cc = consumerConfiguration;
            return getThis();
        }

        public B durable(String str) {
            this.durable = Validator.validateDurable(str, false);
            return getThis();
        }

        public abstract B getThis();

        public B messageAlarmTime(long j) {
            this.messageAlarmTime = j;
            return getThis();
        }

        public B name(String str) {
            this.name = Validator.validateConsumerName(str, false);
            return getThis();
        }

        public B stream(String str) {
            this.stream = Validator.validateStreamName(str, false);
            return getThis();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public SubscribeOptions(io.nats.client.SubscribeOptions.Builder<?, ?> r10, boolean r11, java.lang.String r12, java.lang.String r13, long r14, long r16) {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.SubscribeOptions.<init>(io.nats.client.SubscribeOptions$Builder, boolean, java.lang.String, java.lang.String, long, long):void");
    }

    public ConsumerConfiguration getConsumerConfiguration() {
        return this.consumerConfig;
    }

    public String getDurable() {
        return this.consumerConfig.getDurable();
    }

    public long getMessageAlarmTime() {
        return this.messageAlarmTime;
    }

    public String getName() {
        return this.name;
    }

    public long getPendingByteLimit() {
        return this.pendingByteLimit;
    }

    public long getPendingMessageLimit() {
        return this.pendingMessageLimit;
    }

    public String getStream() {
        return this.stream;
    }

    public boolean isBind() {
        return this.bind;
    }

    public boolean isFastBind() {
        return this.fastBind;
    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public boolean isPull() {
        return this.pull;
    }

    public String toString() {
        return getClass().getSimpleName() + "{name='" + this.name + "', stream='" + this.stream + "', pull='" + this.pull + "', bind=" + this.bind + ", fastBind=" + this.fastBind + ", ordered=" + this.ordered + ", messageAlarmTime=" + this.messageAlarmTime + ", " + this.consumerConfig + '}';
    }
}
