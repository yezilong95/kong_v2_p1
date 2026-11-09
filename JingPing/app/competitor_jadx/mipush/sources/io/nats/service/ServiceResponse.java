package io.nats.service;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonParseException;
import io.nats.client.support.JsonParser;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceResponse implements JsonSerializable {
    protected final String id;
    protected final Map<String, String> metadata;
    protected final String name;
    protected final AtomicReference<byte[]> serialized;
    protected final String type;
    protected final String version;

    public ServiceResponse(String str, String str2, String str3, String str4, Map<String, String> map) {
        this.type = str;
        this.id = str2;
        this.name = str3;
        this.version = str4;
        this.metadata = (map == null || map.isEmpty()) ? null : map;
        this.serialized = new AtomicReference<>();
    }

    public static JsonValue parseMessage(byte[] bArr) {
        try {
            return JsonParser.parse(bArr);
        } catch (JsonParseException unused) {
            return JsonValue.EMPTY_MAP;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServiceResponse serviceResponse = (ServiceResponse) obj;
        if (Objects.equals(this.type, serviceResponse.type) && Objects.equals(this.name, serviceResponse.name) && Objects.equals(this.id, serviceResponse.id) && Objects.equals(this.version, serviceResponse.version)) {
            return JsonUtils.mapEquals(this.metadata, serviceResponse.metadata);
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public Map<String, String> getMetadata() {
        if (this.metadata == null) {
            return null;
        }
        return new HashMap(this.metadata);
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.type;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.id;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.version;
        int iHashCode4 = (iHashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Map<String, String> map = this.metadata;
        return iHashCode4 + (map != null ? map.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public byte[] serialize() {
        if (this.serialized.get() == null) {
            this.serialized.set(toJson().getBytes(StandardCharsets.UTF_8));
        }
        return this.serialized.get();
    }

    public void subToJson(StringBuilder sb) {
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.ID, this.id);
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addField(sbBeginJson, ApiConstants.VERSION, this.version);
        subToJson(sbBeginJson);
        JsonUtils.addField(sbBeginJson, ApiConstants.TYPE, this.type);
        JsonUtils.addField(sbBeginJson, ApiConstants.METADATA, this.metadata);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return JsonUtils.toKey(getClass()) + toJson();
    }

    public ServiceResponse(String str, ServiceResponse serviceResponse) {
        this(str, serviceResponse.id, serviceResponse.name, serviceResponse.version, serviceResponse.metadata);
    }

    public ServiceResponse(String str, JsonValue jsonValue) {
        String string = JsonValueUtils.readString(jsonValue, ApiConstants.TYPE);
        if (Validator.emptyAsNull(string) != null) {
            if (str.equals(string)) {
                this.type = str;
                this.id = Validator.required(JsonValueUtils.readString(jsonValue, ApiConstants.ID), "Id");
                this.name = Validator.required(JsonValueUtils.readString(jsonValue, ApiConstants.NAME), "Name");
                this.version = Validator.required(JsonValueUtils.readString(jsonValue, ApiConstants.VERSION), "Version");
                this.metadata = JsonValueUtils.readStringStringMap(jsonValue, ApiConstants.METADATA);
                this.serialized = new AtomicReference<>();
                return;
            }
            throw new IllegalArgumentException("Invalid type for " + getClass().getSimpleName() + ". Expecting: " + str + ". Received " + string);
        }
        throw new IllegalArgumentException("Type cannot be null or empty.");
    }
}
