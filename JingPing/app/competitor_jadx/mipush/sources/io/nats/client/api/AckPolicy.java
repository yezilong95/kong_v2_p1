package io.nats.client.api;

import io.nats.client.support.NatsJetStreamConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum AckPolicy {
    None("none"),
    All(NatsJetStreamConstants.ROLLUP_HDR_ALL),
    Explicit("explicit");

    private static final Map<String, AckPolicy> strEnumHash = new HashMap();
    private final String policy;

    static {
        for (AckPolicy ackPolicy : values()) {
            strEnumHash.put(ackPolicy.toString(), ackPolicy);
        }
    }

    AckPolicy(String str) {
        this.policy = str;
    }

    public static AckPolicy get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
