package io.nats.client.api;

import defpackage.zt;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Replica extends PeerInfo {
    public Replica(JsonValue jsonValue) {
        super(jsonValue);
    }

    public static List<Replica> optionalListOf(JsonValue jsonValue) {
        return JsonValueUtils.optionalListOf(jsonValue, new zt(8));
    }

    public String toString() {
        return "Replica{name='" + getName() + "', current=" + isCurrent() + ", offline=" + isOffline() + ", active=" + getActive() + ", lag=" + getLag() + '}';
    }
}
