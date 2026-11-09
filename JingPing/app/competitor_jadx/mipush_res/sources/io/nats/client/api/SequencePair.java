package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class SequencePair {
    protected final long consumerSeq;
    protected final long streamSeq;

    public SequencePair(JsonValue jsonValue) {
        this.consumerSeq = JsonValueUtils.readLong(jsonValue, ApiConstants.CONSUMER_SEQ, 0L);
        this.streamSeq = JsonValueUtils.readLong(jsonValue, ApiConstants.STREAM_SEQ, 0L);
    }

    public long getConsumerSequence() {
        return this.consumerSeq;
    }

    public long getStreamSequence() {
        return this.streamSeq;
    }
}
