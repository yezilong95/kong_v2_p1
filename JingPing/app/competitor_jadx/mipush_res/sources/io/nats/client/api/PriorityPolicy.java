package io.nats.client.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum PriorityPolicy {
    None("none"),
    Overflow("overflow"),
    PinnedClient("pinned_client");

    private static final Map<String, PriorityPolicy> strEnumHash = new HashMap();
    private final String policy;

    static {
        for (PriorityPolicy priorityPolicy : values()) {
            strEnumHash.put(priorityPolicy.toString(), priorityPolicy);
        }
    }

    PriorityPolicy(String str) {
        this.policy = str;
    }

    public static PriorityPolicy get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
