package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LostStreamData {
    private final Long bytes;
    private final List<Long> messages;

    public LostStreamData(JsonValue jsonValue) {
        this.messages = JsonValueUtils.readLongList(jsonValue, ApiConstants.MSGS);
        this.bytes = JsonValueUtils.readLong(jsonValue, ApiConstants.BYTES);
    }

    public static LostStreamData optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new LostStreamData(jsonValue);
    }

    public Long getBytes() {
        return this.bytes;
    }

    public List<Long> getMessages() {
        return this.messages;
    }

    public String toString() {
        return "LostStreamData{messages=" + this.messages + ", bytes=" + this.bytes + '}';
    }
}
