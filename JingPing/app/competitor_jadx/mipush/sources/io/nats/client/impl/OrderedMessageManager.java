package io.nats.client.impl;

import io.nats.client.Message;
import io.nats.client.SubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.api.ConsumerCreateRequest;
import io.nats.client.impl.MessageManager;
import io.nats.client.support.NatsJetStreamUtil;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
class OrderedMessageManager extends PushMessageManager {
    protected long expectedExternalConsumerSeq;
    protected final AtomicReference<String> targetSid;

    public OrderedMessageManager(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2) {
        super(natsConnection, natsJetStream, str, subscribeOptions, consumerConfiguration, z, z2);
        this.expectedExternalConsumerSeq = 1L;
        this.targetSid = new AtomicReference<>();
    }

    private void handleErrorCondition() {
        try {
            this.targetSid.set(null);
            this.expectedExternalConsumerSeq = 1L;
            String strCreateInbox = this.sub.connection.createInbox();
            this.sub.reSubscribe(strCreateInbox);
            this.targetSid.set(this.sub.getSID());
            ConsumerConfiguration.Builder builderConsumerConfigurationForOrdered = this.js.consumerConfigurationForOrdered(this.initialCc, this.lastStreamSeq, strCreateInbox, null);
            if (this.initialCc.getName() != null) {
                builderConsumerConfigurationForOrdered.name(NatsJetStreamUtil.generateConsumerName(this.initialCc.getName()));
            }
            this.sub.setConsumerName(this.js._createConsumer(this.stream, builderConsumerConfigurationForOrdered.build(), ConsumerCreateRequest.Action.Create).getName());
            startup(this.sub);
        } catch (Exception e) {
            try {
                this.js.conn.processException(e);
            } catch (Exception unused) {
            }
            initOrResetHeartbeatTimer();
        }
    }

    @Override // io.nats.client.impl.MessageManager
    public void handleHeartbeatError() {
        super.handleHeartbeatError();
        handleErrorCondition();
    }

    @Override // io.nats.client.impl.PushMessageManager, io.nats.client.impl.MessageManager
    public MessageManager.ManageResult manage(Message message) {
        if (!message.getSID().equals(this.targetSid.get())) {
            return MessageManager.ManageResult.STATUS_HANDLED;
        }
        if (!message.isJetStream()) {
            return manageStatus(message);
        }
        if (this.expectedExternalConsumerSeq != message.metaData().consumerSequence()) {
            handleErrorCondition();
            return MessageManager.ManageResult.STATUS_HANDLED;
        }
        trackJsMessage(message);
        this.expectedExternalConsumerSeq++;
        return MessageManager.ManageResult.MESSAGE;
    }

    @Override // io.nats.client.impl.PushMessageManager, io.nats.client.impl.MessageManager
    public void startup(NatsJetStreamSubscription natsJetStreamSubscription) {
        super.startup(natsJetStreamSubscription);
        this.targetSid.set(natsJetStreamSubscription.getSID());
    }
}
