package io.nats.client.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum ReplayPolicy {
    Instant("instant"),
    Original("original");

    private static final Map<String, ReplayPolicy> strEnumHash = new HashMap();
    private String policy;

    static {
        for (ReplayPolicy replayPolicy : values()) {
            strEnumHash.put(replayPolicy.toString(), replayPolicy);
        }
    }

    ReplayPolicy(String str) {
        this.policy = str;
    }

    public static ReplayPolicy get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
