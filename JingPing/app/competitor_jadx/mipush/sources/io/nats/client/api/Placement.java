package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonSerializable;
import io.nats.client.support.JsonUtils;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Placement implements JsonSerializable {
    private final String cluster;
    private final List<String> tags;

    public static class Builder {
        private String cluster;
        private List<String> tags;

        public Placement build() {
            return new Placement(this.cluster, this.tags);
        }

        public Builder cluster(String str) {
            this.cluster = str;
            return this;
        }

        public Builder tags(String... strArr) {
            this.tags = Arrays.asList(strArr);
            return this;
        }

        public Builder tags(List<String> list) {
            this.tags = list;
            return this;
        }
    }

    public Placement(JsonValue jsonValue) {
        this.cluster = JsonValueUtils.readStringEmptyAsNull(jsonValue, ApiConstants.CLUSTER);
        this.tags = JsonValueUtils.readOptionalStringList(jsonValue, ApiConstants.TAGS);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Placement optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new Placement(jsonValue);
    }

    public String getCluster() {
        return this.cluster;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public boolean hasData() {
        return (this.cluster == null && this.tags == null) ? false : true;
    }

    @Override // io.nats.client.support.JsonSerializable
    public String toJson() {
        StringBuilder sbBeginJson = JsonUtils.beginJson();
        JsonUtils.addField(sbBeginJson, ApiConstants.CLUSTER, this.cluster);
        JsonUtils.addStrings(sbBeginJson, ApiConstants.TAGS, this.tags);
        return JsonUtils.endJson(sbBeginJson).toString();
    }

    public String toString() {
        return "Placement{cluster='" + this.cluster + "', tags=" + this.tags + '}';
    }

    public Placement(String str, List<String> list) {
        this.cluster = (str == null || str.isEmpty()) ? null : str;
        this.tags = (list == null || list.isEmpty()) ? null : list;
    }
}
