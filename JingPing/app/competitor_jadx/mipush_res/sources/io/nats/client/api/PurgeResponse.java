package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class PurgeResponse extends ApiResponse<PurgeResponse> {
    private final long purged;
    private final boolean success;

    public PurgeResponse(Message message) {
        super(message);
        this.success = JsonValueUtils.readBoolean(this.jv, ApiConstants.SUCCESS);
        this.purged = JsonValueUtils.readLong(this.jv, ApiConstants.PURGED, 0L);
    }

    public long getPurged() {
        return this.purged;
    }

    @Deprecated
    public int getPurgedCount() {
        return new Long(this.purged).intValue();
    }

    public boolean isSuccess() {
        return this.success;
    }
}
