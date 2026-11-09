package io.nats.client.impl;

import io.nats.client.JetStreamApiException;
import io.nats.client.MessageConsumer;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
class NatsMessageConsumerBase implements MessageConsumer {
    protected ConsumerInfo cachedConsumerInfo;
    protected String consumerName;
    protected final AtomicBoolean finished;
    protected PullMessageManager pmm;
    protected final AtomicBoolean stopped;
    protected NatsJetStreamPullSubscription sub;

    public NatsMessageConsumerBase(ConsumerInfo consumerInfo) {
        this.cachedConsumerInfo = consumerInfo;
        if (consumerInfo != null) {
            this.consumerName = consumerInfo.getName();
        }
        this.stopped = new AtomicBoolean(false);
        this.finished = new AtomicBoolean(false);
    }

    @Override // io.nats.client.MessageConsumer, java.lang.AutoCloseable
    public void close() throws Exception {
        this.stopped.set(true);
        shutdownSub();
    }

    public void fullClose() {
        this.stopped.set(true);
        this.finished.set(true);
        shutdownSub();
    }

    @Override // io.nats.client.MessageConsumer
    public ConsumerInfo getCachedConsumerInfo() {
        return this.cachedConsumerInfo;
    }

    @Override // io.nats.client.MessageConsumer
    public ConsumerInfo getConsumerInfo() throws IOException, JetStreamApiException {
        if (this.cachedConsumerInfo == null) {
            ConsumerInfo consumerInfo = this.sub.getConsumerInfo();
            this.cachedConsumerInfo = consumerInfo;
            this.consumerName = consumerInfo.getName();
        }
        return this.cachedConsumerInfo;
    }

    @Override // io.nats.client.MessageConsumer
    public String getConsumerName() {
        if (this.consumerName == null) {
            this.consumerName = this.cachedConsumerInfo.getName();
        }
        return this.consumerName;
    }

    public void initSub(NatsJetStreamPullSubscription natsJetStreamPullSubscription) {
        this.sub = natsJetStreamPullSubscription;
        this.pmm = (PullMessageManager) natsJetStreamPullSubscription.manager;
    }

    @Override // io.nats.client.MessageConsumer
    public boolean isFinished() {
        return this.finished.get();
    }

    @Override // io.nats.client.MessageConsumer
    public boolean isStopped() {
        return this.stopped.get();
    }

    public void setConsumerName(String str) {
        this.consumerName = str;
    }

    public void shutdownSub() {
        try {
            if (this.sub.isActive()) {
                if (this.sub.getNatsDispatcher() != null) {
                    this.sub.getDispatcher().unsubscribe(this.sub);
                } else {
                    this.sub.unsubscribe();
                }
            }
        } catch (Throwable unused) {
        }
        PullMessageManager pullMessageManager = this.pmm;
        if (pullMessageManager != null) {
            try {
                pullMessageManager.shutdownHeartbeatTimer();
            } catch (Throwable unused2) {
            }
        }
    }

    @Override // io.nats.client.MessageConsumer
    public void stop() {
        this.stopped.set(true);
    }
}
