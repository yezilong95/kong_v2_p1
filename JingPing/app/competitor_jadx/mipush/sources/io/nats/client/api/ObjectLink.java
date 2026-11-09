package io.nats.client.api;

import defpackage.zn;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;

/* JADX INFO: loaded from: classes.dex */
public class ObjectLink implements JsonSerializable {
    private final String bucket;
    private final String objectName;

    public ObjectLink(JsonValue jsonValue) {
        this.bucket = JsonValueUtils.readString(jsonValue, ApiConstants.BUCKET);
        this.objectName = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
    }

    public static ObjectLink bucket(String str) {
        return new ObjectLink(str, null);
    }

    public static ObjectLink object(String str, String str2) {
        return new ObjectLink(str, str2);
    }

    public static ObjectLink optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new ObjectLink(jsonValue);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ObjectLink objectLink = (ObjectLink) obj;
            if (!this.bucket.equals(objectLink.bucket)) {
                return false;
            }
            String str = this.objectName;
            String str2 = objectLink.objectName;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public String getBucket() {
        return this.bucket;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public int hashCode() {
        int iHashCode = this.bucket.hashCode() * 31;
        String str = this.objectName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public boolean isBucketLink() {
        return this.objectName == null;
    }

    public boolean isObjectLink() {
        return this.objectName != null;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.BUCKET, this.bucket);
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.objectName);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ObjectLink{bucket='");
        sb.append(this.bucket);
        sb.append("', objectName='");
        return zn.o(sb, this.objectName, "'}");
    }

    private ObjectLink(String str, String str2) {
        this.bucket = Validator.validateBucketName(str, true);
        this.objectName = str2;
    }
}
