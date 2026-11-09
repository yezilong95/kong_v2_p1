package io.nats.client.impl;

import io.nats.client.Message;
import io.nats.client.SubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.impl.MessageManager;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
class PullOrderedMessageManager extends PullMessageManager {
    protected long expectedExternalConsumerSeq;
    protected final NatsJetStream js;
    protected final ConsumerConfiguration originalCc;
    protected final String stream;
    protected final AtomicReference<String> targetSid;

    public PullOrderedMessageManager(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z) {
        super(natsConnection, subscribeOptions, z);
        this.js = natsJetStream;
        this.stream = str;
        this.originalCc = consumerConfiguration;
        this.expectedExternalConsumerSeq = 1L;
        this.targetSid = new AtomicReference<>();
    }

    @Override // io.nats.client.impl.PullMessageManager, io.nats.client.impl.MessageManager
    public MessageManager.ManageResult manage(Message message) {
        if (!message.getSID().equals(this.targetSid.get())) {
            return MessageManager.ManageResult.STATUS_HANDLED;
        }
        if (!message.isJetStream()) {
            return manageStatus(message);
        }
        if (this.expectedExternalConsumerSeq == message.metaData().consumerSequence()) {
            trackJsMessage(message);
            this.expectedExternalConsumerSeq++;
            return MessageManager.ManageResult.MESSAGE;
        }
        this.targetSid.set(null);
        this.expectedExternalConsumerSeq = 1L;
        resetTracking();
        PullManagerObserver pullManagerObserver = this.pullManagerObserver;
        if (pullManagerObserver != null) {
            pullManagerObserver.heartbeatError();
        }
        return MessageManager.ManageResult.STATUS_HANDLED;
    }

    @Override // io.nats.client.impl.PullMessageManager, io.nats.client.impl.MessageManager
    public void startup(NatsJetStreamSubscription natsJetStreamSubscription) {
        this.expectedExternalConsumerSeq = 1L;
        super.startup(natsJetStreamSubscription);
        this.targetSid.set(natsJetStreamSubscription.getSID());
    }
}
