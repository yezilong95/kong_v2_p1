package com.google.gson;

import defpackage.oz;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class LongSerializationPolicy {
    public static final LongSerializationPolicy DEFAULT;
    public static final LongSerializationPolicy STRING;
    public static final /* synthetic */ LongSerializationPolicy[] b;

    static {
        LongSerializationPolicy longSerializationPolicy = new LongSerializationPolicy() { // from class: r30
            @Override // com.google.gson.LongSerializationPolicy
            public final oz serialize(Long l) {
                pz pzVar = new pz();
                pzVar.c(l);
                return pzVar;
            }
        };
        DEFAULT = longSerializationPolicy;
        LongSerializationPolicy longSerializationPolicy2 = new LongSerializationPolicy() { // from class: s30
            @Override // com.google.gson.LongSerializationPolicy
            public final oz serialize(Long l) {
                String strValueOf = String.valueOf(l);
                pz pzVar = new pz();
                pzVar.c(strValueOf);
                return pzVar;
            }
        };
        STRING = longSerializationPolicy2;
        b = new LongSerializationPolicy[]{longSerializationPolicy, longSerializationPolicy2};
    }

    public static LongSerializationPolicy valueOf(String str) {
        return (LongSerializationPolicy) Enum.valueOf(LongSerializationPolicy.class, str);
    }

    public static LongSerializationPolicy[] values() {
        return (LongSerializationPolicy[]) b.clone();
    }

    public abstract oz serialize(Long l);
}
