package io.nats.client;

import io.nats.client.api.ApiResponse;
import io.nats.client.api.Error;

/* JADX INFO: loaded from: classes.dex */
public class JetStreamApiException extends Exception {
    private final Error error;

    public JetStreamApiException(ApiResponse<?> apiResponse) {
        this(apiResponse.getErrorObject());
    }

    public int getApiErrorCode() {
        return this.error.getApiErrorCode();
    }

    public int getErrorCode() {
        return this.error.getCode();
    }

    public String getErrorDescription() {
        return this.error.getDescription();
    }

    public JetStreamApiException(Error error) {
        super(error.toString());
        this.error = error;
    }
}
