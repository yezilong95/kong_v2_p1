package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.NatsJetStreamConstants;
import io.nats.client.support.Status;

/* JADX INFO: loaded from: classes.dex */
public class Error implements JsonSerializable {
    public static final Error JsBadRequestErr = new Error(Status.BAD_REQUEST_CODE, 10003, "bad request");
    public static final Error JsNoMessageFoundErr = new Error(Status.NOT_FOUND_CODE, NatsJetStreamConstants.JS_NO_MESSAGE_FOUND_ERR, "no message found");
    public static final int NOT_SET = -1;
    private final JsonValue jv;

    public Error(JsonValue jsonValue) {
        this.jv = jsonValue;
    }

    public static Error convert(Status status) {
        int code = status.getCode();
        return code != 404 ? code != 408 ? new Error(status.getCode(), -1, status.getMessage()) : JsBadRequestErr : JsNoMessageFoundErr;
    }

    public static Error optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new Error(jsonValue);
    }

    public int getApiErrorCode() {
        return JsonValueUtils.readInteger(this.jv, ApiConstants.ERR_CODE, -1);
    }

    public int getCode() {
        return JsonValueUtils.readInteger(this.jv, ApiConstants.CODE, -1);
    }

    public String getDescription() {
        return JsonValueUtils.readString(this.jv, ApiConstants.DESCRIPTION, "Unknown JetStream Error");
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        return this.jv.toJson();
    }

    @Override // io.nats.client.support.JsonSerializable
    public JsonValue toJsonValue() {
        return this.jv;
    }

    public String toString() {
        int apiErrorCode = getApiErrorCode();
        int code = getCode();
        if (apiErrorCode == -1) {
            if (code == -1) {
                return getDescription();
            }
            return getDescription() + " (" + code + ")";
        }
        if (code == -1) {
            return getDescription();
        }
        return getDescription() + " [" + apiErrorCode + "]";
    }

    public Error(int i, String str) {
        this(i, -1, str);
    }

    public Error(int i, int i2, String str) {
        this.jv = JsonValueUtils.mapBuilder().put(ApiConstants.CODE, Integer.valueOf(i)).put(ApiConstants.ERR_CODE, Integer.valueOf(i2)).put(ApiConstants.DESCRIPTION, str).toJsonValue();
    }
}
