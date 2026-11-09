package io.nats.client.api;

import io.nats.client.BaseConsumeOptions;
import io.nats.client.JetStreamApiException;
import io.nats.client.Message;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class ApiResponse<T> {
    public static final String NO_TYPE = "io.nats.jetstream.api.v1.no_type";
    public static final String PARSE_ERROR_TYPE = "io.nats.client.api.parse_error";
    private final Error error;
    protected final JsonValue jv;
    private final String type;

    public ApiResponse(Message message) {
        this(parseMessage(message));
    }

    public static JsonValue parseMessage(Message message) {
        if (message == null) {
            return null;
        }
        try {
            return JsonParser.parse(message.getData());
        } catch (JsonParseException e) {
            return JsonValueUtils.mapBuilder().put(ApiConstants.ERROR, new Error(BaseConsumeOptions.DEFAULT_MESSAGE_COUNT, "Error parsing: " + e.getMessage())).put(ApiConstants.TYPE, PARSE_ERROR_TYPE).toJsonValue();
        }
    }

    public int getApiErrorCode() {
        Error error = this.error;
        if (error == null) {
            return -1;
        }
        return error.getApiErrorCode();
    }

    public String getDescription() {
        Error error = this.error;
        if (error == null) {
            return null;
        }
        return error.getDescription();
    }

    public String getError() {
        Error error = this.error;
        if (error == null) {
            return null;
        }
        return error.toString();
    }

    public int getErrorCode() {
        Error error = this.error;
        if (error == null) {
            return -1;
        }
        return error.getCode();
    }

    public Error getErrorObject() {
        return this.error;
    }

    public JsonValue getJv() {
        return this.jv;
    }

    public String getType() {
        return this.type;
    }

    public boolean hasError() {
        return this.error != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T throwOnHasError() throws JetStreamApiException {
        if (hasError()) {
            throw new JetStreamApiException((ApiResponse<?>) this);
        }
        return this;
    }

    public String toString() {
        JsonValue jsonValue = this.jv;
        if (jsonValue != null) {
            return jsonValue.toString(getClass());
        }
        return JsonUtils.toKey(getClass()) + "\":null";
    }

    public ApiResponse(JsonValue jsonValue) {
        this.jv = jsonValue;
        if (jsonValue == null) {
            this.error = null;
            this.type = null;
            return;
        }
        this.error = Error.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.ERROR));
        String string = JsonValueUtils.readString(jsonValue, ApiConstants.TYPE);
        if (string == null) {
            this.type = NO_TYPE;
        } else {
            this.type = string;
            jsonValue.map.remove(ApiConstants.TYPE);
        }
    }

    public ApiResponse() {
        this.jv = null;
        this.error = null;
        this.type = NO_TYPE;
    }

    public ApiResponse(Error error) {
        this.jv = null;
        this.error = error;
        this.type = NO_TYPE;
    }
}
