package io.nats.client.api;

import io.nats.client.support.NatsJetStreamConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum DeliverPolicy {
    All(NatsJetStreamConstants.ROLLUP_HDR_ALL),
    Last("last"),
    New("new"),
    ByStartSequence("by_start_sequence"),
    ByStartTime("by_start_time"),
    LastPerSubject("last_per_subject");

    private static final Map<String, DeliverPolicy> strEnumHash = new HashMap();
    private final String policy;

    static {
        for (DeliverPolicy deliverPolicy : values()) {
            strEnumHash.put(deliverPolicy.toString(), deliverPolicy);
        }
    }

    DeliverPolicy(String str) {
        this.policy = str;
    }

    public static DeliverPolicy get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
