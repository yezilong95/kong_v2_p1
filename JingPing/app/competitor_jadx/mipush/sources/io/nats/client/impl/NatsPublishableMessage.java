package io.nats.client.impl;

import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
class NatsPublishableMessage extends NatsMessage {
    final boolean hasHeaders;

    public NatsPublishableMessage(boolean z) {
        this.hasHeaders = z;
        this.flushImmediatelyAfterPublish = false;
    }

    @Override // io.nats.client.impl.NatsMessage
    public void calculate() {
    }

    public NatsPublishableMessage(String str, String str2, Headers headers, byte[] bArr, boolean z, boolean z2) {
        super(bArr);
        this.flushImmediatelyAfterPublish = z2;
        if (z) {
            this.subject = Validator.validateSubject(str, true);
            this.replyTo = Validator.validateReplyTo(str2, false);
        } else {
            this.subject = str;
            this.replyTo = str2;
        }
        if (headers != null && !headers.isEmpty()) {
            this.hasHeaders = true;
            this.headers = headers.isReadOnly() ? headers : new Headers(headers, true, null);
        } else {
            this.hasHeaders = false;
        }
        super.calculate();
    }
}
