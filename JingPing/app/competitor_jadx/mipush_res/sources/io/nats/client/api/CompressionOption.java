package io.nats.client.api;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum CompressionOption {
    None("none"),
    S2("s2");

    private static final Map<String, CompressionOption> strEnumHash = new HashMap();
    private final String policy;

    static {
        for (CompressionOption compressionOption : values()) {
            strEnumHash.put(compressionOption.toString(), compressionOption);
        }
    }

    CompressionOption(String str) {
        this.policy = str;
    }

    public static CompressionOption get(String str) {
        return strEnumHash.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.policy;
    }
}
