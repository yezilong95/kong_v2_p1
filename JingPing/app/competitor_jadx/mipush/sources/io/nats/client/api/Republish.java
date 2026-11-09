package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class Republish implements JsonSerializable {
    private final String destination;
    private final boolean headersOnly;
    private final String source;

    public static class Builder {
        private String destination;
        private boolean headersOnly;
        private String source;

        public Republish build() {
            return new Republish(this.source, this.destination, this.headersOnly);
        }

        public Builder destination(String str) {
            this.destination = str;
            return this;
        }

        public Builder headersOnly(Boolean bool) {
            this.headersOnly = bool.booleanValue();
            return this;
        }

        public Builder source(String str) {
            this.source = str;
            return this;
        }
    }

    public Republish(JsonValue jsonValue) {
        this.source = JsonValueUtils.readString(jsonValue, ApiConstants.SRC);
        this.destination = JsonValueUtils.readString(jsonValue, ApiConstants.DEST);
        this.headersOnly = JsonValueUtils.readBoolean(jsonValue, ApiConstants.HEADERS_ONLY);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Republish optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new Republish(jsonValue);
    }

    public String getDestination() {
        return this.destination;
    }

    public String getSource() {
        return this.source;
    }

    public boolean isHeadersOnly() {
        return this.headersOnly;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.SRC, this.source);
        JsonUtils.addField(sbBeginJson, ApiConstants.DEST, this.destination);
        JsonUtils.addFldWhenTrue(sbBeginJson, ApiConstants.HEADERS_ONLY, Boolean.valueOf(this.headersOnly));
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public Republish(String str, String str2, boolean z) {
        Validator.required(str, "Source");
        Validator.required(str2, "Destination");
        this.source = str;
        this.destination = str2;
        this.headersOnly = z;
    }
}
