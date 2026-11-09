package io.nats.client.impl;

import io.nats.client.Connection;
import io.nats.client.support.NatsConstants;
import io.nats.client.support.Validator;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
class NatsJetStreamMessage extends IncomingMessage {
    private NatsJetStreamMetaData jsMetaData;

    public NatsJetStreamMessage(byte[] bArr) {
        super(bArr);
        this.jsMetaData = null;
    }

    private boolean ackHasntBeenTermed() {
        AckType ackType = this.lastAck;
        return ackType == null || !ackType.terminal;
    }

    private void ackReply(AckType ackType, long j) {
        if (ackHasntBeenTermed()) {
            getJetStreamValidatedConnection().publish(this.replyTo, ackType.bodyBytes(j));
            this.lastAck = ackType;
        }
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void ack() {
        ackReply(AckType.AckAck, -1L);
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void ackSync(Duration duration) throws InterruptedException, TimeoutException {
        if (ackHasntBeenTermed()) {
            Validator.validateDurationRequired(duration);
            Connection jetStreamValidatedConnection = getJetStreamValidatedConnection();
            String str = this.replyTo;
            AckType ackType = AckType.AckAck;
            if (jetStreamValidatedConnection.request(str, ackType.bytes, duration) == null) {
                throw new TimeoutException("Ack response timed out.");
            }
            this.lastAck = ackType;
        }
    }

    public Connection getJetStreamValidatedConnection() {
        if (getSubscription() == null) {
            throw new IllegalStateException("Message is not bound to a subscription.");
        }
        Connection connection = getConnection();
        if (connection != null) {
            return connection;
        }
        throw new IllegalStateException("Message is not bound to a connection");
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void inProgress() {
        ackReply(AckType.AckProgress, -1L);
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public boolean isJetStream() {
        return true;
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public NatsJetStreamMetaData metaData() {
        if (this.jsMetaData == null) {
            this.jsMetaData = new NatsJetStreamMetaData(this);
        }
        return this.jsMetaData;
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void nak() {
        ackReply(AckType.AckNak, -1L);
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void nakWithDelay(Duration duration) {
        ackReply(AckType.AckNak, duration.toNanos());
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void term() {
        ackReply(AckType.AckTerm, -1L);
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public void nakWithDelay(long j) {
        ackReply(AckType.AckNak, j * NatsConstants.NANOS_PER_MILLI);
    }
}
