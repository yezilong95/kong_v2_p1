package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.Consumer;
import io.nats.client.ErrorListener;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public class ErrorListenerConsoleImpl implements ErrorListener {
    @Override // io.nats.client.ErrorListener
    public void errorOccurred(Connection connection, String str) {
        System.out.println(supplyMessage("[SEVERE] errorOccurred", connection, null, null, "Error: ", str));
    }

    @Override // io.nats.client.ErrorListener
    public void exceptionOccurred(Connection connection, Exception exc) {
        System.out.println(supplyMessage("[SEVERE] exceptionOccurred", connection, null, null, "Exception: ", exc));
    }

    @Override // io.nats.client.ErrorListener
    public void flowControlProcessed(Connection connection, JetStreamSubscription jetStreamSubscription, String str, ErrorListener.FlowControlSource flowControlSource) {
        System.out.println(supplyMessage("[INFO] flowControlProcessed", connection, null, jetStreamSubscription, "FlowControlSource: ", flowControlSource));
    }

    @Override // io.nats.client.ErrorListener
    public void heartbeatAlarm(Connection connection, JetStreamSubscription jetStreamSubscription, long j, long j2) {
        System.out.println(supplyMessage("[SEVERE] heartbeatAlarm", connection, null, jetStreamSubscription, "lastStreamSequence: ", Long.valueOf(j), "lastConsumerSequence: ", Long.valueOf(j2)));
    }

    @Override // io.nats.client.ErrorListener
    public void messageDiscarded(Connection connection, Message message) {
        System.out.println(supplyMessage("[INFO] messageDiscarded", connection, null, null, "Message: ", message));
    }

    @Override // io.nats.client.ErrorListener
    public void pullStatusError(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        System.out.println(supplyMessage("[SEVERE] pullStatusError", connection, null, jetStreamSubscription, "Status: ", status));
    }

    @Override // io.nats.client.ErrorListener
    public void pullStatusWarning(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        System.out.println(supplyMessage("[WARN] pullStatusWarning", connection, null, jetStreamSubscription, "Status: ", status));
    }

    @Override // io.nats.client.ErrorListener
    public void slowConsumerDetected(Connection connection, Consumer consumer) {
        System.out.println(supplyMessage("[WARN] slowConsumerDetected", connection, consumer, null, new Object[0]));
    }

    @Override // io.nats.client.ErrorListener
    public void socketWriteTimeout(Connection connection) {
        System.out.println(supplyMessage("[SEVERE] socketWriteTimeout", connection, null, null, new Object[0]));
    }

    @Override // io.nats.client.ErrorListener
    public void unhandledStatus(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
        System.out.println(supplyMessage("[WARN] unhandledStatus", connection, null, jetStreamSubscription, "Status: ", status));
    }
}
