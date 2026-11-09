package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.time.Duration;

/* JADX INFO: loaded from: classes.dex */
public class PeerInfo {
    private final Duration active;
    private final boolean current;
    private final long lag;
    private final String name;
    private final boolean offline;

    public PeerInfo(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.current = JsonValueUtils.readBoolean(jsonValue, ApiConstants.CURRENT);
        this.offline = JsonValueUtils.readBoolean(jsonValue, ApiConstants.OFFLINE);
        this.active = JsonValueUtils.readNanos(jsonValue, ApiConstants.ACTIVE, Duration.ZERO);
        this.lag = JsonValueUtils.readLong(jsonValue, ApiConstants.LAG, 0L);
    }

    public Duration getActive() {
        return this.active;
    }

    public long getLag() {
        return this.lag;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCurrent() {
        return this.current;
    }

    public boolean isOffline() {
        return this.offline;
    }
}
