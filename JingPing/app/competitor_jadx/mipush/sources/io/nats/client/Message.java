package io.nats.client;

import io.nats.client.impl.AckType;
import io.nats.client.impl.Headers;
import io.nats.client.impl.NatsJetStreamMetaData;
import io.nats.client.support.Status;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public interface Message {
    void ack();

    void ackSync(Duration duration) throws InterruptedException, TimeoutException;

    default long consumeByteCount() {
        return -1L;
    }

    Connection getConnection();

    byte[] getData();

    Headers getHeaders();

    String getReplyTo();

    String getSID();

    Status getStatus();

    String getSubject();

    Subscription getSubscription();

    boolean hasHeaders();

    void inProgress();

    boolean isJetStream();

    boolean isStatusMessage();

    boolean isUtf8mode();

    AckType lastAck();

    NatsJetStreamMetaData metaData();

    void nak();

    void nakWithDelay(long j);

    void nakWithDelay(Duration duration);

    void term();
}
