package io.nats.client.api;

import io.nats.client.api.ObjectMetaOptions;
import io.nats.client.impl.Headers;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class ObjectMeta implements JsonSerializable {
    private final String description;
    private final Headers headers;
    private final ObjectMetaOptions objectMetaOptions;
    private final String objectName;

    public static Builder builder(String str) {
        return new Builder(str);
    }

    public static ObjectMeta objectName(String str) {
        return new Builder(str).build();
    }

    public void embedJson(StringBuilder sb) {
        JsonUtils.addField(sb, ApiConstants.NAME, this.objectName);
        JsonUtils.addField(sb, ApiConstants.DESCRIPTION, this.description);
        JsonUtils.addField(sb, ApiConstants.HEADERS, this.headers);
        if (this.objectMetaOptions.hasData()) {
            JsonUtils.addField(sb, ApiConstants.OPTIONS, this.objectMetaOptions);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ObjectMeta objectMeta = (ObjectMeta) obj;
        if (!this.objectName.equals(objectMeta.objectName)) {
            return false;
        }
        String str = this.description;
        if (str == null ? objectMeta.description != null : !str.equals(objectMeta.description)) {
            return false;
        }
        if (this.headers.equals(objectMeta.headers)) {
            return this.objectMetaOptions.equals(objectMeta.objectMetaOptions);
        }
        return false;
    }

    public String getDescription() {
        return this.description;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public ObjectMetaOptions getObjectMetaOptions() {
        return this.objectMetaOptions;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public int hashCode() {
        int iHashCode = this.objectName.hashCode() * 31;
        String str = this.description;
        return this.objectMetaOptions.hashCode() + ((this.headers.hashCode() + ((iHashCode + (str != null ? str.hashCode() : 0)) * 31)) * 31);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        embedJson(sbBeginJson);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "ObjectMeta{objectName='" + this.objectName + "', description='" + this.description + "', headers?" + this.headers.size() + ", objectMetaOptions=" + this.objectMetaOptions + '}';
    }

    private ObjectMeta(Builder builder) {
        this.objectName = builder.objectName;
        this.description = builder.description;
        this.headers = builder.headers;
        this.objectMetaOptions = builder.metaOptionsBuilder.build();
    }

    public static Builder builder(ObjectMeta objectMeta) {
        return new Builder(objectMeta);
    }

    public static class Builder {
        String description;
        Headers headers;
        ObjectMetaOptions.Builder metaOptionsBuilder;
        String objectName;

        public Builder(String str) {
            this.headers = new Headers();
            this.metaOptionsBuilder = ObjectMetaOptions.builder();
            objectName(str);
        }

        public ObjectMeta build() {
            return new ObjectMeta(this);
        }

        public Builder chunkSize(int i) {
            this.metaOptionsBuilder.chunkSize(i);
            return this;
        }

        public Builder description(String str) {
            this.description = str;
            return this;
        }

        public Builder headers(Headers headers) {
            if (headers == null) {
                this.headers.clear();
                return this;
            }
            this.headers = headers;
            return this;
        }

        public Builder link(ObjectLink objectLink) {
            this.metaOptionsBuilder.link(objectLink);
            return this;
        }

        public Builder objectName(String str) {
            this.objectName = Validator.validateNotNull(str, "Object Name");
            return this;
        }

        public Builder options(ObjectMetaOptions objectMetaOptions) {
            this.metaOptionsBuilder = ObjectMetaOptions.builder(objectMetaOptions);
            return this;
        }

        public Builder(ObjectMeta objectMeta) {
            this.objectName = objectMeta.objectName;
            this.description = objectMeta.description;
            this.headers = objectMeta.headers;
            this.metaOptionsBuilder = ObjectMetaOptions.builder(objectMeta.objectMetaOptions);
        }
    }

    public ObjectMeta(JsonValue jsonValue) {
        this.objectName = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.description = JsonValueUtils.readString(jsonValue, ApiConstants.DESCRIPTION);
        this.headers = new Headers();
        JsonValue object = JsonValueUtils.readObject(jsonValue, ApiConstants.HEADERS);
        for (String str : object.map.keySet()) {
            this.headers.put(str, JsonValueUtils.readStringList(object, str));
        }
        this.objectMetaOptions = new ObjectMetaOptions(JsonValueUtils.readObject(jsonValue, ApiConstants.OPTIONS));
    }
}
