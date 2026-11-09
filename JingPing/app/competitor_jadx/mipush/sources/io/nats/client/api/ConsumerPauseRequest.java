package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class ConsumerPauseRequest implements JsonSerializable {
    private final ZonedDateTime pauseUntil;

    public ConsumerPauseRequest(ZonedDateTime zonedDateTime) {
        this.pauseUntil = zonedDateTime;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.PAUSE_UNTIL, this.pauseUntil);
        return JsonUtils.endJson(sbBeginJson).toString();
    }
}
