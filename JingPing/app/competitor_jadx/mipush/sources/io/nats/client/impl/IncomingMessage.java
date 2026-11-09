package io.nats.client.impl;

import io.nats.client.support.ByteArrayBuilder;

/* JADX INFO: loaded from: classes.dex */
public class IncomingMessage extends NatsMessage {
    public IncomingMessage() {
        super((byte[]) null);
    }

    @Override // io.nats.client.impl.NatsMessage
    public void calculate() {
    }

    @Override // io.nats.client.impl.NatsMessage
    public int getControlLineLength() {
        throw new IllegalStateException("getControlLineLength not supported for this type of message.");
    }

    @Override // io.nats.client.impl.NatsMessage
    public ByteArrayBuilder getProtocolBab() {
        throw new IllegalStateException("getProtocolBab not supported for this type of message.");
    }

    @Override // io.nats.client.impl.NatsMessage
    public byte[] getProtocolBytes() {
        throw new IllegalStateException("getProtocolBytes not supported for this type of message.");
    }

    public IncomingMessage(byte[] bArr) {
        super(bArr);
    }
}
