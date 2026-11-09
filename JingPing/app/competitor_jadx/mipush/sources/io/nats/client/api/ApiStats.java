package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class ApiStats {
    private final long errors;
    private final long inFlight;
    private final int level;
    private final long total;

    public ApiStats(JsonValue jsonValue) {
        this.level = JsonValueUtils.readInteger(jsonValue, ApiConstants.LEVEL, 0);
        this.total = JsonValueUtils.readLong(jsonValue, ApiConstants.TOTAL, 0L);
        this.errors = JsonValueUtils.readLong(jsonValue, ApiConstants.ERRORS, 0L);
        this.inFlight = JsonValueUtils.readLong(jsonValue, ApiConstants.INFLIGHT, 0L);
    }

    public long getErrorCount() {
        return this.errors;
    }

    @Deprecated
    public int getErrors() {
        return (int) this.errors;
    }

    public long getInFlight() {
        return this.inFlight;
    }

    public int getLevel() {
        return this.level;
    }

    @Deprecated
    public int getTotal() {
        return (int) this.total;
    }

    public long getTotalApiRequests() {
        return this.total;
    }
}
