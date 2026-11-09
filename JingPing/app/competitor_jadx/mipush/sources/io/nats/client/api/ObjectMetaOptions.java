package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;

/* JADX INFO: loaded from: classes.dex */
public class ObjectMetaOptions implements JsonSerializable {
    private final int chunkSize;
    private final ObjectLink link;

    public static class Builder {
        int chunkSize;
        ObjectLink link;

        public Builder() {
        }

        public ObjectMetaOptions build() {
            return new ObjectMetaOptions(this);
        }

        public Builder chunkSize(int i) {
            this.chunkSize = i;
            return this;
        }

        public Builder link(ObjectLink objectLink) {
            this.link = objectLink;
            return this;
        }

        public Builder(ObjectMetaOptions objectMetaOptions) {
            this.link = objectMetaOptions.link;
            this.chunkSize = objectMetaOptions.chunkSize;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ObjectMetaOptions objectMetaOptions = (ObjectMetaOptions) obj;
            if (this.chunkSize != objectMetaOptions.chunkSize) {
                return false;
            }
            ObjectLink objectLink = this.link;
            ObjectLink objectLink2 = objectMetaOptions.link;
            if (objectLink != null) {
                return objectLink.equals(objectLink2);
            }
            if (objectLink2 == null) {
                return true;
            }
        }
        return false;
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public ObjectLink getLink() {
        return this.link;
    }

    public boolean hasData() {
        return this.link != null || this.chunkSize > 0;
    }

    public int hashCode() {
        ObjectLink objectLink = this.link;
        return ((objectLink != null ? objectLink.hashCode() : 0) * 31) + this.chunkSize;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.LINK, this.link);
        JsonUtils.addField(sbBeginJson, ApiConstants.MAX_CHUNK_SIZE, Integer.valueOf(this.chunkSize));
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "ObjectMetaOptions{link=" + this.link + ", chunkSize=" + this.chunkSize + '}';
    }

    private ObjectMetaOptions(Builder builder) {
        this.link = builder.link;
        this.chunkSize = builder.chunkSize;
    }

    public static Builder builder(ObjectMetaOptions objectMetaOptions) {
        return new Builder(objectMetaOptions);
    }

    public ObjectMetaOptions(JsonValue jsonValue) {
        this.link = ObjectLink.optionalInstance(JsonValueUtils.readValue(jsonValue, ApiConstants.LINK));
        this.chunkSize = JsonValueUtils.readInteger(jsonValue, ApiConstants.MAX_CHUNK_SIZE, -1);
    }
}
