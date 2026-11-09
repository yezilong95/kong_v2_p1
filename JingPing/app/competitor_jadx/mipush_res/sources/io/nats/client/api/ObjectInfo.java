package io.nats.client.api;

import io.nats.client.Message;
import io.nats.client.api.ObjectMeta;
import io.nats.client.impl.Headers;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.DateTimeUtils;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes.dex */
public class ObjectInfo implements JsonSerializable {
    private final String bucket;
    private final long chunks;
    private final boolean deleted;
    private final String digest;
    private final ZonedDateTime modified;
    private final String nuid;
    private final ObjectMeta objectMeta;
    private final long size;

    public static Builder builder(String str, String str2) {
        return new Builder(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ObjectInfo objectInfo = (ObjectInfo) obj;
        if (this.size != objectInfo.size || this.chunks != objectInfo.chunks || this.deleted != objectInfo.deleted || !this.bucket.equals(objectInfo.bucket)) {
            return false;
        }
        String str = this.nuid;
        if (str != null) {
            if (!str.equals(objectInfo.nuid)) {
                return false;
            }
        } else if (objectInfo.nuid != null) {
            return false;
        }
        ZonedDateTime zonedDateTime = this.modified;
        if (zonedDateTime != null) {
            if (!zonedDateTime.equals(objectInfo.modified)) {
                return false;
            }
        } else if (objectInfo.modified != null) {
            return false;
        }
        String str2 = this.digest;
        if (str2 != null) {
            if (!str2.equals(objectInfo.digest)) {
                return false;
            }
        } else if (objectInfo.digest != null) {
            return false;
        }
        return this.objectMeta.equals(objectInfo.objectMeta);
    }

    public String getBucket() {
        return this.bucket;
    }

    public long getChunks() {
        return this.chunks;
    }

    public String getDescription() {
        return this.objectMeta.getDescription();
    }

    public String getDigest() {
        return this.digest;
    }

    public Headers getHeaders() {
        return this.objectMeta.getHeaders();
    }

    public ObjectLink getLink() {
        return this.objectMeta.getObjectMetaOptions().getLink();
    }

    public ZonedDateTime getModified() {
        return this.modified;
    }

    public String getNuid() {
        return this.nuid;
    }

    public ObjectMeta getObjectMeta() {
        return this.objectMeta;
    }

    public String getObjectName() {
        return this.objectMeta.getObjectName();
    }

    public long getSize() {
        return this.size;
    }

    public int hashCode() {
        int iHashCode = this.bucket.hashCode() * 31;
        String str = this.nuid;
        int iHashCode2 = str != null ? str.hashCode() : 0;
        long j = this.size;
        int i = (((iHashCode + iHashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        ZonedDateTime zonedDateTime = this.modified;
        int iHashCode3 = (i + (zonedDateTime != null ? zonedDateTime.hashCode() : 0)) * 31;
        long j2 = this.chunks;
        int i2 = (iHashCode3 + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str2 = this.digest;
        return this.objectMeta.hashCode() + ((((i2 + (str2 != null ? str2.hashCode() : 0)) * 31) + (this.deleted ? 1 : 0)) * 31);
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean isLink() {
        return this.objectMeta.getObjectMetaOptions().getLink() != null;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        this.objectMeta.embedJson(sbBeginJson);
        JsonUtils.addField(sbBeginJson, ApiConstants.BUCKET, this.bucket);
        JsonUtils.addField(sbBeginJson, ApiConstants.NUID, this.nuid);
        JsonUtils.addField(sbBeginJson, ApiConstants.SIZE, Long.valueOf(this.size));
        JsonUtils.addField(sbBeginJson, ApiConstants.CHUNKS, Long.valueOf(this.chunks));
        JsonUtils.addField(sbBeginJson, ApiConstants.DIGEST, this.digest);
        JsonUtils.addField(sbBeginJson, ApiConstants.DELETED, Boolean.valueOf(this.deleted));
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "ObjectInfo{bucket='" + this.bucket + "', nuid='" + this.nuid + "', size=" + this.size + ", modified=" + this.modified + ", chunks=" + this.chunks + ", digest='" + this.digest + "', deleted=" + this.deleted + ", objectMeta=" + this.objectMeta + '}';
    }

    private ObjectInfo(Builder builder) {
        this.bucket = builder.bucket;
        this.nuid = builder.nuid;
        this.size = builder.size;
        this.modified = builder.modified;
        this.chunks = builder.chunks;
        this.digest = builder.digest;
        this.deleted = builder.deleted;
        this.objectMeta = builder.metaBuilder.build();
    }

    public static Builder builder(String str, ObjectMeta objectMeta) {
        return new Builder(str, objectMeta);
    }

    public static class Builder {
        String bucket;
        long chunks;
        boolean deleted;
        String digest;
        ObjectMeta.Builder metaBuilder;
        ZonedDateTime modified;
        String nuid;
        long size;

        public Builder(String str, String str2) {
            this.metaBuilder = ObjectMeta.builder(str2);
            bucket(str);
        }

        public Builder bucket(String str) {
            this.bucket = Validator.validateBucketName(str, true);
            return this;
        }

        public Builder bucketLink(String str) {
            this.metaBuilder.link(ObjectLink.bucket(str));
            return this;
        }

        public ObjectInfo build() {
            return new ObjectInfo(this);
        }

        public Builder chunkSize(int i) {
            this.metaBuilder.chunkSize(i);
            return this;
        }

        public Builder chunks(long j) {
            this.chunks = j;
            return this;
        }

        public Builder deleted(boolean z) {
            this.deleted = z;
            return this;
        }

        public Builder description(String str) {
            this.metaBuilder.description(str);
            return this;
        }

        public Builder digest(String str) {
            this.digest = str;
            return this;
        }

        public Builder headers(Headers headers) {
            this.metaBuilder.headers(headers);
            return this;
        }

        public Builder link(ObjectLink objectLink) {
            this.metaBuilder.link(objectLink);
            return this;
        }

        public Builder modified(ZonedDateTime zonedDateTime) {
            this.modified = zonedDateTime;
            return this;
        }

        public Builder nuid(String str) {
            this.nuid = str;
            return this;
        }

        public Builder objectLink(String str, String str2) {
            this.metaBuilder.link(ObjectLink.object(str, str2));
            return this;
        }

        public Builder objectName(String str) {
            this.metaBuilder.objectName(str);
            return this;
        }

        public Builder options(ObjectMetaOptions objectMetaOptions) {
            this.metaBuilder.options(objectMetaOptions);
            return this;
        }

        public Builder size(long j) {
            this.size = j;
            return this;
        }

        public Builder(String str, ObjectMeta objectMeta) {
            this.metaBuilder = ObjectMeta.builder(objectMeta);
            bucket(str);
        }

        public Builder(ObjectInfo objectInfo) {
            this.bucket = objectInfo.bucket;
            this.nuid = objectInfo.nuid;
            this.size = objectInfo.size;
            this.modified = objectInfo.modified;
            this.chunks = objectInfo.chunks;
            this.digest = objectInfo.digest;
            this.deleted = objectInfo.deleted;
            this.metaBuilder = ObjectMeta.builder(objectInfo.objectMeta);
        }
    }

    public static Builder builder(ObjectInfo objectInfo) {
        return new Builder(objectInfo);
    }

    public ObjectInfo(MessageInfo messageInfo) {
        this(messageInfo.getData(), messageInfo.getTime());
    }

    public ObjectInfo(Message message) {
        this(message.getData(), message.metaData().timestamp());
    }

    public ObjectInfo(byte[] bArr, ZonedDateTime zonedDateTime) {
        JsonValue unchecked = JsonParser.parseUnchecked(bArr);
        this.objectMeta = new ObjectMeta(unchecked);
        this.bucket = JsonValueUtils.readString(unchecked, ApiConstants.BUCKET);
        this.nuid = JsonValueUtils.readString(unchecked, ApiConstants.NUID);
        this.size = JsonValueUtils.readLong(unchecked, ApiConstants.SIZE, 0L);
        this.modified = DateTimeUtils.toGmt(zonedDateTime);
        this.chunks = JsonValueUtils.readLong(unchecked, ApiConstants.CHUNKS, 0L);
        this.digest = JsonValueUtils.readString(unchecked, ApiConstants.DIGEST);
        this.deleted = JsonValueUtils.readBoolean(unchecked, ApiConstants.DELETED);
    }
}
