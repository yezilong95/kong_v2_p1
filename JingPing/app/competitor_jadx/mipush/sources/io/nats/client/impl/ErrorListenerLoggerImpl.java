package io.nats.client.impl;

import defpackage.iu;
import defpackage.ju;
import defpackage.o31;
import io.nats.client.Connection;
import io.nats.client.Consumer;
import io.nats.client.ErrorListener;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.support.Status;
import java.util.function.Supplier;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class ErrorListenerLoggerImpl implements ErrorListener {
    private static final Logger LOGGER = Logger.getLogger(ErrorListenerLoggerImpl.class.getName());

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$errorOccurred$0(Connection connection, String str) {
        return supplyMessage("errorOccurred", connection, null, null, "Error: ", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$exceptionOccurred$1(Connection connection, Exception exc) {
        return supplyMessage("exceptionOccurred", connection, null, null, "Exception: ", exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$flowControlProcessed$8(Connection connection, JetStreamSubscription jetStreamSubscription, ErrorListener.FlowControlSource flowControlSource) {
        return supplyMessage("flowControlProcessed", connection, null, jetStreamSubscription, "FlowControlSource:", flowControlSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$heartbeatAlarm$4(Connection connection, JetStreamSubscription jetStreamSubscription, long j, long j2) {
        return supplyMessage("heartbeatAlarm", connection, null, jetStreamSubscription, "lastStreamSequence: ", Long.valueOf(j), "lastConsumerSequence: ", Long.valueOf(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$messageDiscarded$3(Connection connection, Message message) {
        return supplyMessage("messageDiscarded", connection, null, null, "Message: ", message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$pullStatusError$7(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        return supplyMessage("pullStatusError", connection, null, jetStreamSubscription, "Status:", status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$pullStatusWarning$6(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        return supplyMessage("pullStatusWarning", connection, null, jetStreamSubscription, "Status:", status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$slowConsumerDetected$2(Connection connection, Consumer consumer) {
        return supplyMessage("slowConsumerDetected", connection, consumer, null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$socketWriteTimeout$9(Connection connection) {
        return supplyMessage("socketWriteTimeout", connection, null, null, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$unhandledStatus$5(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        return supplyMessage("unhandledStatus", connection, null, jetStreamSubscription, "Status:", status);
    }

    @Override // io.nats.client.ErrorListener
    public void errorOccurred(Connection connection, String str) {
        LOGGER.severe(new iu(this, connection, str, 3));
    }

    @Override // io.nats.client.ErrorListener
    public void exceptionOccurred(Connection connection, Exception exc) {
        LOGGER.severe(new iu(this, connection, exc, 2));
    }

    @Override // io.nats.client.ErrorListener
    public void flowControlProcessed(Connection connection, JetStreamSubscription jetStreamSubscription, String str, ErrorListener.FlowControlSource flowControlSource) {
        LOGGER.info(new ju(this, connection, jetStreamSubscription, flowControlSource, 3));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [ku] */
    @Override // io.nats.client.ErrorListener
    public void heartbeatAlarm(final Connection connection, final JetStreamSubscription jetStreamSubscription, final long j, final long j2) {
        LOGGER.severe((Supplier<String>) new Supplier() { // from class: ku
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.a.lambda$heartbeatAlarm$4(connection, jetStreamSubscription, j, j2);
            }
        });
    }

    @Override // io.nats.client.ErrorListener
    public void messageDiscarded(Connection connection, Message message) {
        LOGGER.info(new iu(this, connection, message, 1));
    }

    @Override // io.nats.client.ErrorListener
    public void pullStatusError(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        LOGGER.severe(new ju(this, connection, jetStreamSubscription, status, 2));
    }

    @Override // io.nats.client.ErrorListener
    public void pullStatusWarning(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        LOGGER.warning(new ju(this, connection, jetStreamSubscription, status, 0));
    }

    @Override // io.nats.client.ErrorListener
    public void slowConsumerDetected(Connection connection, Consumer consumer) {
        LOGGER.warning(new iu(this, connection, consumer, 0));
    }

    @Override // io.nats.client.ErrorListener
    public void socketWriteTimeout(Connection connection) {
        LOGGER.severe(new o31(this, 9, connection));
    }

    @Override // io.nats.client.ErrorListener
    public void unhandledStatus(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        LOGGER.warning(new ju(this, connection, jetStreamSubscription, status, 1));
    }
}
