package io.nats.client.impl;

import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public class StatusMessage extends IncomingMessage {
    private final Status status;

    public StatusMessage(Status status) {
        this.status = status;
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public Status getStatus() {
        return this.status;
    }

    @Override // io.nats.client.impl.NatsMessage, io.nats.client.Message
    public boolean isStatusMessage() {
        return true;
    }

    @Override // io.nats.client.impl.NatsMessage
    public String toString() {
        return "StatusMessage{code=" + this.status.getCode() + ", message='" + this.status.getMessage() + "'}";
    }
}
