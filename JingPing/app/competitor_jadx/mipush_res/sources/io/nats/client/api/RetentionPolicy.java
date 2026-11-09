package io.nats.client.api;

import io.nats.client.support.ApiConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum RetentionPolicy {
    Limits(ApiConstants.LIMITS),
    Interest("interest"),
    WorkQueue("workqueue");

    private static final Map<String, RetentionPolicy> strEnumHash = new HashMap();
    private final String policy;

    static {
        for (RetentionPolicy retentionPolicy : values()) {
            strEnumHash.put(retentionPolicy.toString(), retentionPolicy);
        }
    }

    RetentionPolicy(String str) {
        this.policy = str;
    }

    public static RetentionPolicy get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
