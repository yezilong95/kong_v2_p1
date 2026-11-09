package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.ErrorListener;
import io.nats.client.Message;
import io.nats.client.SubscribeOptions;
import io.nats.client.api.ConsumerConfiguration;
import io.nats.client.impl.MessageManager;
import io.nats.client.impl.NatsConnection;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
class PushMessageManager extends MessageManager {
    protected final boolean fc;
    protected final ConsumerConfiguration initialCc;
    protected final NatsJetStream js;
    protected String lastFcSubject;
    protected final boolean queueMode;
    protected final String stream;

    public PushMessageManager(NatsConnection natsConnection, NatsJetStream natsJetStream, String str, SubscribeOptions subscribeOptions, ConsumerConfiguration consumerConfiguration, boolean z, boolean z2) {
        super(natsConnection, subscribeOptions, z2);
        this.js = natsJetStream;
        this.stream = str;
        this.initialCc = consumerConfiguration;
        this.queueMode = z;
        boolean z3 = false;
        if (z) {
            this.fc = false;
            return;
        }
        configureIdleHeartbeat(consumerConfiguration.getIdleHeartbeat(), subscribeOptions.getMessageAlarmTime());
        if (this.hb.get() && consumerConfiguration.isFlowControl()) {
            z3 = true;
        }
        this.fc = z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$manageStatus$0(Status status, Connection connection, ErrorListener errorListener) {
        errorListener.unhandledStatus(connection, this.sub, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processFlowControl$1(String str, ErrorListener.FlowControlSource flowControlSource, Connection connection, ErrorListener errorListener) {
        errorListener.flowControlProcessed(connection, this.sub, str, flowControlSource);
    }

    private void processFlowControl(final String str, final ErrorListener.FlowControlSource flowControlSource) {
        if (str == null || str.equals(this.lastFcSubject)) {
            return;
        }
        this.conn.publishInternal(str, null, null, null, false, false);
        this.lastFcSubject = str;
        this.conn.executeCallback(new NatsConnection.ErrorListenerCaller() { // from class: io.nats.client.impl.v
            @Override // io.nats.client.impl.NatsConnection.ErrorListenerCaller
            public final void call(Connection connection, ErrorListener errorListener) {
                this.a.lambda$processFlowControl$1(str, flowControlSource, connection, errorListener);
            }
        });
    }

    @Override // io.nats.client.impl.MessageManager
    public Boolean beforeQueueProcessorImpl(NatsMessage natsMessage) {
        if (this.hb.get()) {
            updateLastMessageReceived();
            Status status = natsMessage.getStatus();
            if (status != null && status.isHeartbeat()) {
                return Boolean.valueOf(hasFcSubject(natsMessage));
            }
        }
        return Boolean.TRUE;
    }

    public String extractFcSubject(Message message) {
        if (message.getHeaders() == null) {
            return null;
        }
        return message.getHeaders().getFirst(NatsJetStreamConstants.CONSUMER_STALLED_HDR);
    }

    public String getLastFcSubject() {
        return this.lastFcSubject;
    }

    public boolean hasFcSubject(Message message) {
        return message.getHeaders() != null && message.getHeaders().containsKey(NatsJetStreamConstants.CONSUMER_STALLED_HDR);
    }

    public boolean isFc() {
        return this.fc;
    }

    public boolean isQueueMode() {
        return this.queueMode;
    }

    @Override // io.nats.client.impl.MessageManager
    public MessageManager.ManageResult manage(Message message) {
        if (message.getStatus() != null) {
            return manageStatus(message);
        }
        trackJsMessage(message);
        return MessageManager.ManageResult.MESSAGE;
    }

    public MessageManager.ManageResult manageStatus(Message message) {
        Status status = message.getStatus();
        if (this.fc) {
            boolean zIsFlowControl = status.isFlowControl();
            String replyTo = zIsFlowControl ? message.getReplyTo() : extractFcSubject(message);
            if (replyTo != null) {
                processFlowControl(replyTo, zIsFlowControl ? ErrorListener.FlowControlSource.FLOW_CONTROL : ErrorListener.FlowControlSource.HEARTBEAT);
                return MessageManager.ManageResult.STATUS_HANDLED;
            }
        }
        this.conn.executeCallback(new t(this, status, 3));
        return MessageManager.ManageResult.STATUS_ERROR;
    }

    @Override // io.nats.client.impl.MessageManager
    public void startup(NatsJetStreamSubscription natsJetStreamSubscription) {
        super.startup(natsJetStreamSubscription);
        natsJetStreamSubscription.setBeforeQueueProcessor(new u(this, 1));
        if (this.hb.get()) {
            initOrResetHeartbeatTimer();
        }
    }
}
