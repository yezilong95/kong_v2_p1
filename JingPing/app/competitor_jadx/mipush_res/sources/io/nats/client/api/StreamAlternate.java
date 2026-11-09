package io.nats.client.api;

import defpackage.zt;
import io.nats.client.support.ApiConstants;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StreamAlternate {
    private final String cluster;
    private final String domain;
    private final String name;

    public StreamAlternate(JsonValue jsonValue) {
        this.name = JsonValueUtils.readString(jsonValue, ApiConstants.NAME);
        this.domain = JsonValueUtils.readString(jsonValue, ApiConstants.DOMAIN);
        this.cluster = JsonValueUtils.readString(jsonValue, ApiConstants.CLUSTER);
    }

    public static List<StreamAlternate> optionalListOf(JsonValue jsonValue) {
        return JsonValueUtils.optionalListOf(jsonValue, new zt(11));
    }

    public String getCluster() {
        return this.cluster;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getName() {
        return this.name;
    }
}
