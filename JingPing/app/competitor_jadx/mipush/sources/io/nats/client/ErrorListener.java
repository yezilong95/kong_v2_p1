package io.nats.client;

import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public interface ErrorListener {

    public enum FlowControlSource {
        FLOW_CONTROL,
        HEARTBEAT
    }

    default void errorOccurred(Connection connection, String str) {
    }

    default void exceptionOccurred(Connection connection, Exception exc) {
    }

    default void flowControlProcessed(Connection connection, JetStreamSubscription jetStreamSubscription, String str, FlowControlSource flowControlSource) {
    }

    default void heartbeatAlarm(Connection connection, JetStreamSubscription jetStreamSubscription, long j, long j2) {
    }

    default void messageDiscarded(Connection connection, Message message) {
    }

    default void pullStatusError(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
    }

    default void pullStatusWarning(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
    }

    default void slowConsumerDetected(Connection connection, Consumer consumer) {
    }

    default void socketWriteTimeout(Connection connection) {
    }

    default String supplyMessage(String str, Connection connection, Consumer consumer, Subscription subscription, Object... objArr) {
        if (str == null) {
            str = "";
        }
        StringBuilder sb = new StringBuilder(str);
        if (connection != null && connection.getServerInfo() != null) {
            sb.append(", Connection: ");
            sb.append(connection.getServerInfo().getClientId());
        }
        if (consumer != null) {
            sb.append(", Consumer: ");
            sb.append(consumer.hashCode());
        }
        if (subscription != null) {
            sb.append(", Subscription: ");
            sb.append(subscription.hashCode());
            if (subscription instanceof JetStreamSubscription) {
                sb.append(", Consumer Name: ");
                sb.append(((JetStreamSubscription) subscription).getConsumerName());
            }
        }
        for (int i = 0; i < objArr.length; i += 2) {
            sb.append(", ");
            sb.append(objArr[i]);
            sb.append(objArr[i + 1]);
        }
        return sb.toString();
    }

    default void unhandledStatus(Connection connection, JetStreamSubscription jetStreamSubscription, Status status) {
    }
}
