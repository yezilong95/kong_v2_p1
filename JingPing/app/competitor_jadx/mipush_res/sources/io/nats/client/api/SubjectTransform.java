package io.nats.client.api;

import defpackage.zn;
import defpackage.zt;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class SubjectTransform implements JsonSerializable {
    private final String destination;
    private final String source;

    public static class Builder {
        private String destination;
        private String source;

        public SubjectTransform build() {
            return new SubjectTransform(this.source, this.destination);
        }

        public Builder destination(String str) {
            this.destination = str;
            return this;
        }

        public Builder source(String str) {
            this.source = str;
            return this;
        }
    }

    public SubjectTransform(JsonValue jsonValue) {
        this.source = JsonValueUtils.readString(jsonValue, ApiConstants.SRC);
        this.destination = JsonValueUtils.readString(jsonValue, ApiConstants.DEST);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SubjectTransform optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new SubjectTransform(jsonValue);
    }

    public static List<SubjectTransform> optionalListOf(JsonValue jsonValue) {
        return JsonValueUtils.optionalListOf(jsonValue, new zt(12));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SubjectTransform subjectTransform = (SubjectTransform) obj;
        if (Objects.equals(this.source, subjectTransform.source)) {
            return Objects.equals(this.destination, subjectTransform.destination);
        }
        return false;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getSource() {
        return this.source;
    }

    public int hashCode() {
        String str = this.source;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.destination;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.SRC, this.source);
        JsonUtils.addField(sbBeginJson, ApiConstants.DEST, this.destination);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SubjectTransform{source='");
        sb.append(this.source);
        sb.append("', destination='");
        return zn.o(sb, this.destination, "'}");
    }

    public SubjectTransform(String str, String str2) {
        this.source = Validator.required(str, "Source");
        this.destination = Validator.required(str2, "Destination");
    }
}
