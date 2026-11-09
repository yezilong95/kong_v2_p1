package io.nats.client.impl;

import io.nats.client.support.JsonUtils;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public enum AckType {
    AckAck("+ACK", true),
    AckNak("-NAK", true),
    AckProgress("+WPI", false),
    AckTerm("+TERM", true),
    AckNext("+NXT", false);

    public final byte[] bytes;
    public final boolean terminal;
    public final String text;

    AckType(String str, boolean z) {
        this.text = str;
        this.bytes = str.getBytes(StandardCharsets.US_ASCII);
        this.terminal = z;
    }

    public byte[] bodyBytes(long j) {
        if (j < 1) {
            return this.bytes;
        }
        return (this.text + " {\"delay\": " + j + JsonUtils.CLOSE).getBytes(StandardCharsets.US_ASCII);
    }
}
