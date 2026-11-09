package io.nats.client.impl;

import io.nats.client.support.ByteArrayBuilder;

/* JADX INFO: loaded from: classes.dex */
class ProtocolMessage extends NatsPublishableMessage {
    final boolean filterOnStop;

    public ProtocolMessage(ByteArrayBuilder byteArrayBuilder, boolean z) {
        super(false);
        this.protocolBab = byteArrayBuilder;
        int length = byteArrayBuilder.length() + 2;
        this.controlLineLength = length;
        this.sizeInBytes = length;
        this.filterOnStop = z;
    }

    @Override // io.nats.client.impl.NatsMessage
    public int copyNotEmptyHeaders(int i, byte[] bArr) {
        return 0;
    }

    @Override // io.nats.client.impl.NatsMessage
    public boolean isProtocol() {
        return true;
    }

    @Override // io.nats.client.impl.NatsMessage
    public boolean isProtocolFilterOnStop() {
        return this.filterOnStop;
    }

    public ProtocolMessage(byte[] bArr) {
        super(false);
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(bArr);
        this.protocolBab = byteArrayBuilder;
        int length = byteArrayBuilder.length() + 2;
        this.controlLineLength = length;
        this.sizeInBytes = length;
        this.filterOnStop = true;
    }

    public ProtocolMessage(ProtocolMessage protocolMessage) {
        super(false);
        this.protocolBab = protocolMessage.protocolBab;
        int i = protocolMessage.sizeInBytes;
        this.controlLineLength = i;
        this.sizeInBytes = i;
        this.filterOnStop = protocolMessage.filterOnStop;
    }
}
