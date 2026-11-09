package com.google.gson;

import defpackage.w50;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class LongSerializationPolicy {
    public static final LongSerializationPolicy DEFAULT;
    public static final /* synthetic */ LongSerializationPolicy[] E;
    public static final LongSerializationPolicy STRING;

    static {
        LongSerializationPolicy longSerializationPolicy = new LongSerializationPolicy() { // from class: ab0
            @Override // com.google.gson.LongSerializationPolicy
            public final w50 serialize(Long l) {
                return new z50(l);
            }
        };
        DEFAULT = longSerializationPolicy;
        LongSerializationPolicy longSerializationPolicy2 = new LongSerializationPolicy() { // from class: bb0
            @Override // com.google.gson.LongSerializationPolicy
            public final w50 serialize(Long l) {
                return new z50(String.valueOf(l));
            }
        };
        STRING = longSerializationPolicy2;
        E = new LongSerializationPolicy[]{longSerializationPolicy, longSerializationPolicy2};
    }

    public static LongSerializationPolicy valueOf(String str) {
        return (LongSerializationPolicy) Enum.valueOf(LongSerializationPolicy.class, str);
    }

    public static LongSerializationPolicy[] values() {
        return (LongSerializationPolicy[]) E.clone();
    }

    public abstract w50 serialize(Long l);
}
