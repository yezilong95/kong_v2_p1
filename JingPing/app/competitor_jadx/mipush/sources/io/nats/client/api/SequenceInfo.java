package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class SequenceInfo extends SequencePair {
    private final ZonedDateTime lastActive;

    public SequenceInfo(JsonValue jsonValue) {
        super(jsonValue);
        this.lastActive = JsonValueUtils.readDate(jsonValue, ApiConstants.LAST_ACTIVE);
    }

    public ZonedDateTime getLastActive() {
        return this.lastActive;
    }

    public String toString() {
        return "SequenceInfo{consumerSeq=" + this.consumerSeq + ", streamSeq=" + this.streamSeq + ", lastActive=" + this.lastActive + '}';
    }
}
