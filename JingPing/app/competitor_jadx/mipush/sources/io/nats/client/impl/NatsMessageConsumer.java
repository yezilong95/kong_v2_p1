package io.nats.client.impl;

import io.nats.client.ConsumeOptions;
import io.nats.client.Dispatcher;
import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.PullRequestOptions;
import io.nats.client.api.ConsumerInfo;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
class NatsMessageConsumer extends NatsMessageConsumerBase implements PullManagerObserver {
    protected final ConsumeOptions consumeOpts;
    protected final SimplifiedSubscriptionMaker subscriptionMaker;
    protected final long thresholdBytes;
    protected final int thresholdMessages;
    protected final Dispatcher userDispatcher;
    protected final MessageHandler userMessageHandler;

    public NatsMessageConsumer(SimplifiedSubscriptionMaker simplifiedSubscriptionMaker, ConsumerInfo consumerInfo, ConsumeOptions consumeOptions, Dispatcher dispatcher, MessageHandler messageHandler) throws IOException, JetStreamApiException {
        super(consumerInfo);
        this.subscriptionMaker = simplifiedSubscriptionMaker;
        this.consumeOpts = consumeOptions;
        this.userDispatcher = dispatcher;
        this.userMessageHandler = messageHandler;
        int batchSize = consumeOptions.getBatchSize();
        long batchBytes = consumeOptions.getBatchBytes();
        int iMax = Math.max(1, (consumeOptions.getThresholdPercent() * batchSize) / 100);
        long jMax = batchBytes != 0 ? Math.max(1L, (((long) consumeOptions.getThresholdPercent()) * batchBytes) / 100) : 0L;
        this.thresholdMessages = batchSize - iMax;
        this.thresholdBytes = batchBytes == 0 ? -2147483648L : batchBytes - jMax;
        doSub();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$doSub$0(Message message) throws InterruptedException {
        this.userMessageHandler.onMessage(message);
        if (this.stopped.get() && this.pmm.noMorePending()) {
            this.finished.set(true);
        }
    }

    private void repull() {
        this.sub._pull(PullRequestOptions.builder(Math.max(1, this.consumeOpts.getBatchSize() - this.pmm.pendingMessages)).maxBytes(this.consumeOpts.getBatchBytes() != 0 ? this.consumeOpts.getBatchBytes() - this.pmm.pendingBytes : 0L).expiresIn(this.consumeOpts.getExpiresInMillis()).idleHeartbeat(this.consumeOpts.getIdleHeartbeat()).group(this.consumeOpts.getGroup()).minPending(this.consumeOpts.getMinPending()).minAckPending(this.consumeOpts.getMinAckPending()).build(), this.consumeOpts.raiseStatusWarnings(), this);
    }

    private void setupHbAlarmToTrigger() {
        this.pmm.resetTracking();
        this.pmm.initOrResetHeartbeatTimer();
    }

    public void doSub() throws IOException, JetStreamApiException {
        j jVar = this.userMessageHandler == null ? null : new j(this, 1);
        try {
            this.stopped.set(false);
            this.finished.set(false);
            super.initSub(this.subscriptionMaker.subscribe(jVar, this.userDispatcher, this.pmm, null));
            repull();
        } catch (JetStreamApiException | IOException unused) {
            setupHbAlarmToTrigger();
        }
    }

    @Override // io.nats.client.impl.PullManagerObserver
    public void heartbeatError() {
        try {
            if (this.stopped.get()) {
                fullClose();
            } else {
                shutdownSub();
                doSub();
            }
        } catch (JetStreamApiException | IOException unused) {
            setupHbAlarmToTrigger();
        }
    }

    @Override // io.nats.client.impl.PullManagerObserver
    public void pendingUpdated() {
        if (this.stopped.get()) {
            if (this.pmm.noMorePending()) {
                fullClose();
            }
        } else {
            PullMessageManager pullMessageManager = this.pmm;
            if (pullMessageManager.pendingMessages <= this.thresholdMessages || (pullMessageManager.trackingBytes && pullMessageManager.pendingBytes <= this.thresholdBytes)) {
                repull();
            }
        }
    }
}
