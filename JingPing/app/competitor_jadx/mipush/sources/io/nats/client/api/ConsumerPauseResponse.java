package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class ConsumerPauseResponse extends ApiResponse<ConsumerPauseResponse> {
    private final Duration pauseRemaining;
    private final ZonedDateTime pauseUntil;
    private final boolean paused;

    public ConsumerPauseResponse(Message message) {
        super(message);
        this.paused = JsonValueUtils.readBoolean(this.jv, ApiConstants.PAUSED);
        this.pauseUntil = JsonValueUtils.readDate(this.jv, ApiConstants.PAUSE_UNTIL);
        this.pauseRemaining = JsonValueUtils.readNanos(this.jv, ApiConstants.PAUSE_REMAINING);
    }

    public Duration getPauseRemaining() {
        return this.pauseRemaining;
    }

    public ZonedDateTime getPauseUntil() {
        return this.pauseUntil;
    }

    public boolean isPaused() {
        return this.paused;
    }
}
