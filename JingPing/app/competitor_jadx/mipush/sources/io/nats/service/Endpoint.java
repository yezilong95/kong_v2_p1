package io.nats.service;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import io.nats.client.support.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class Endpoint implements JsonSerializable {
    public static final String DEFAULT_QGROUP = "q";
    private final Map<String, String> metadata;
    private final String name;
    private final String queueGroup;
    private final String subject;

    public static class Builder {
        private Map<String, String> metadata;
        private String name;
        private String queueGroup = Endpoint.DEFAULT_QGROUP;
        private String subject;

        public Endpoint build() {
            return new Endpoint(this);
        }

        public Builder endpoint(Endpoint endpoint) {
            name(endpoint.getName()).subject(endpoint.getSubject()).metadata(endpoint.getMetadata());
            return endpoint.queueGroup == null ? noQueueGroup() : queueGroup(endpoint.getQueueGroup());
        }

        public Builder metadata(Map<String, String> map) {
            if (map == null || map.isEmpty()) {
                this.metadata = null;
                return this;
            }
            this.metadata = new HashMap(map);
            return this;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder noQueueGroup() {
            this.queueGroup = null;
            return this;
        }

        public Builder queueGroup(String str) {
            if (str == null) {
                str = Endpoint.DEFAULT_QGROUP;
            }
            this.queueGroup = str;
            return this;
        }

        public Builder subject(String str) {
            this.subject = str;
            return this;
        }
    }

    public Endpoint(String str) {
        this(str, null, DEFAULT_QGROUP, null, true);
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Endpoint endpoint = (Endpoint) obj;
        if (Objects.equals(this.name, endpoint.name) && Objects.equals(this.subject, endpoint.subject) && Objects.equals(this.queueGroup, endpoint.queueGroup)) {
            return Objects.equals(this.metadata, endpoint.metadata);
        }
        return false;
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

    public String getQueueGroup() {
        return this.queueGroup;
    }

    public String getSubject() {
        return this.subject;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.subject;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.queueGroup;
        int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Map<String, String> map = this.metadata;
        return iHashCode3 + (map != null ? map.hashCode() : 0);
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.NAME, this.name);
        JsonUtils.addField(sbBeginJson, ApiConstants.SUBJECT, this.subject);
        JsonUtils.addField(sbBeginJson, ApiConstants.QUEUE_GROUP, this.queueGroup);
        JsonUtils.addField(sbBeginJson, ApiConstants.METADATA, this.metadata);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return JsonUtils.toKey(getClass()) + toJson();
    }

    public Endpoint(String str, Map<String, String> map) {
        this(str, null, DEFAULT_QGROUP, map, true);
    }

    public Endpoint(String str, String str2) {
        this(str, str2, DEFAULT_QGROUP, null, true);
    }

    public Endpoint(String str, String str2, Map<String, String> map) {
        this(str, str2, DEFAULT_QGROUP, map, true);
    }

    public Endpoint(String str, String str2, String str3, Map<String, String> map) {
        this(str, str2, str3, map, true);
    }

    public Endpoint(String str, String str2, String str3, Map<String, String> map, boolean z) {
        if (z) {
            String strValidateIsRestrictedTerm = Validator.validateIsRestrictedTerm(str, "Endpoint Name", true);
            this.name = strValidateIsRestrictedTerm;
            if (str2 == null) {
                this.subject = strValidateIsRestrictedTerm;
            } else {
                this.subject = Validator.validateSubjectTerm(str2, "Endpoint Subject", false);
            }
            this.queueGroup = str3 == null ? null : Validator.validateSubjectTerm(str3, "Endpoint Queue Group", true);
        } else {
            this.name = str;
            this.subject = str2;
            this.queueGroup = str3;
        }
        this.metadata = (map == null || map.isEmpty()) ? null : map;
    }

    public Endpoint(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.subject = JsonValueUtils.readString(jsonValue, ApiConstants.SUBJECT);
        this.queueGroup = JsonValueUtils.readString(jsonValue, ApiConstants.QUEUE_GROUP);
        this.metadata = JsonValueUtils.readStringStringMap(jsonValue, ApiConstants.METADATA);
    }

    public Endpoint(Builder builder) {
        this(builder.name, builder.subject, builder.queueGroup, builder.metadata, true);
    }
}
