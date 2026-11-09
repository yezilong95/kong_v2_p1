package io.nats.client.impl;

import defpackage.zn;
import io.nats.client.support.NatsConstants;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class NatsJetStreamMetaData {
    private final String accountHash;
    private final String consumer;
    private final long consumerSeq;
    private final long delivered;
    private final String domain;
    private final long pending;
    private final String prefix;
    private final String stream;
    private final long streamSeq;
    private final ZonedDateTime timestamp;

    /* JADX WARN: Removed duplicated region for block: B:20:0x0042 A[Catch: Exception -> 0x0091, TryCatch #0 {Exception -> 0x0091, blocks: (B:18:0x003b, B:20:0x0042, B:22:0x0046, B:24:0x004a, B:25:0x004d, B:27:0x0083, B:29:0x008e), top: B:39:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a A[Catch: Exception -> 0x0091, TryCatch #0 {Exception -> 0x0091, blocks: (B:18:0x003b, B:20:0x0042, B:22:0x0046, B:24:0x004a, B:25:0x004d, B:27:0x0083, B:29:0x008e), top: B:39:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0083 A[Catch: Exception -> 0x0091, TryCatch #0 {Exception -> 0x0091, blocks: (B:18:0x003b, B:20:0x0042, B:22:0x0046, B:24:0x004a, B:25:0x004d, B:27:0x0083, B:29:0x008e), top: B:39:0x003b }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NatsJetStreamMetaData(io.nats.client.impl.NatsMessage r7) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsJetStreamMetaData.<init>(io.nats.client.impl.NatsMessage):void");
    }

    private String notAJetStreamMessage(String str) {
        return zn.n("Message is not a JetStream message.  ReplySubject: <", str, NatsConstants.GREATER_THAN);
    }

    public long consumerSequence() {
        return this.consumerSeq;
    }

    public long deliveredCount() {
        return this.delivered;
    }

    public String getAccountHash() {
        return this.accountHash;
    }

    public String getConsumer() {
        return this.consumer;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getStream() {
        return this.stream;
    }

    public long pendingCount() {
        return this.pending;
    }

    public long streamSequence() {
        return this.streamSeq;
    }

    public ZonedDateTime timestamp() {
        return this.timestamp;
    }

    public String toString() {
        return "NatsJetStreamMetaData{prefix='" + this.prefix + "', domain='" + this.domain + "', stream='" + this.stream + "', consumer='" + this.consumer + "', delivered=" + this.delivered + ", streamSeq=" + this.streamSeq + ", consumerSeq=" + this.consumerSeq + ", timestamp=" + this.timestamp + ", pending=" + this.pending + '}';
    }
}
