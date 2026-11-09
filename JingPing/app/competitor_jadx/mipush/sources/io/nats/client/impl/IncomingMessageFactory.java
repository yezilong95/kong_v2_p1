package io.nats.client.impl;

import io.nats.client.support.IncomingHeadersProcessor;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
class IncomingMessageFactory {
    private byte[] data;
    private int headerLen;
    private Headers headers;
    private final int protocolLineLength;
    private final String replyTo;
    private final String sid;
    private Status status;
    private final String subject;
    private final boolean utf8mode;

    public IncomingMessageFactory(String str, String str2, String str3, int i, boolean z) {
        this.sid = str;
        this.subject = str2;
        this.replyTo = str3;
        this.protocolLineLength = i;
        this.utf8mode = z;
    }

    public NatsMessage getMessage() {
        NatsMessage incomingMessage;
        if (this.status != null) {
            incomingMessage = new StatusMessage(this.status);
        } else {
            String str = this.replyTo;
            incomingMessage = (str == null || !str.startsWith(NatsJetStreamConstants.JS_ACK_SUBJECT_PREFIX)) ? new IncomingMessage(this.data) : new NatsJetStreamMessage(this.data);
        }
        incomingMessage.sid = this.sid;
        incomingMessage.subject = this.subject;
        incomingMessage.replyTo = this.replyTo;
        incomingMessage.headers = this.headers;
        int i = this.headerLen;
        incomingMessage.headerLen = i;
        incomingMessage.sizeInBytes = this.protocolLineLength + i + incomingMessage.dataLen + 4;
        return incomingMessage;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }

    public void setHeaders(IncomingHeadersProcessor incomingHeadersProcessor) {
        this.headers = incomingHeadersProcessor.getHeaders();
        this.status = incomingHeadersProcessor.getStatus();
        this.headerLen = incomingHeadersProcessor.getSerializedLength();
    }
}
