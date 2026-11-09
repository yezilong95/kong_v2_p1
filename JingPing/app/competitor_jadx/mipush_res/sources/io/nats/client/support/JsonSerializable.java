package io.nats.client.support;

import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public interface JsonSerializable {
    default byte[] serialize() {
        return toJson().getBytes(StandardCharsets.UTF_8);
    }

    String toJson();

    default JsonValue toJsonValue() {
        return JsonParser.parseUnchecked(toJson());
    }
}
