package io.nats.client.api;

import defpackage.zn;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class External implements JsonSerializable {
    private final String api;
    private final String deliver;

    public static class Builder {
        private String api;
        private String deliver;

        public Builder api(String str) {
            this.api = str;
            return this;
        }

        public External build() {
            return new External(this.api, this.deliver);
        }

        public Builder deliver(String str) {
            this.deliver = str;
            return this;
        }
    }

    public External(JsonValue jsonValue) {
        this.api = JsonValueUtils.readString(jsonValue, ApiConstants.API);
        this.deliver = JsonValueUtils.readString(jsonValue, ApiConstants.DELIVER);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static External optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new External(jsonValue);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            External external = (External) obj;
            String str = this.api;
            if (str == null ? external.api != null : !str.equals(external.api)) {
                return false;
            }
            String str2 = this.deliver;
            String str3 = external.deliver;
            if (str2 != null) {
                return str2.equals(str3);
            }
            if (str3 == null) {
                return true;
            }
        }
        return false;
    }

    public String getApi() {
        return this.api;
    }

    public String getDeliver() {
        return this.deliver;
    }

    public int hashCode() {
        String str = this.api;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.deliver;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.API, this.api);
        JsonUtils.addField(sbBeginJson, ApiConstants.DELIVER, this.deliver);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("External{api='");
        sb.append(this.api);
        sb.append("', deliver='");
        return zn.o(sb, this.deliver, "'}");
    }

    public External(String str, String str2) {
        this.api = str;
        this.deliver = str2;
    }
}
