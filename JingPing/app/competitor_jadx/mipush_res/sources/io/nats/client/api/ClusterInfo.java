package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClusterInfo {
    private final String leader;
    private final String name;
    private final List<Replica> replicas;

    public ClusterInfo(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.leader = JsonValueUtils.readString(jsonValue, ApiConstants.LEADER);
        this.replicas = Replica.optionalListOf(JsonValueUtils.readValue(jsonValue, ApiConstants.REPLICAS));
    }

    public static ClusterInfo optionalInstance(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return new ClusterInfo(jsonValue);
    }

    public String getLeader() {
        return this.leader;
    }

    public String getName() {
        return this.name;
    }

    public List<Replica> getReplicas() {
        return this.replicas;
    }

    public String toString() {
        return "ClusterInfo{name='" + this.name + "', leader='" + this.leader + "', replicas=" + this.replicas + '}';
    }
}
