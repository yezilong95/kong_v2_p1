package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class SuccessApiResponse extends ApiResponse<SuccessApiResponse> {
    boolean success;

    public SuccessApiResponse(Message message) {
        super(message);
        this.success = JsonValueUtils.readBoolean(this.jv, ApiConstants.SUCCESS);
    }

    public boolean getSuccess() {
        return this.success;
    }
}
