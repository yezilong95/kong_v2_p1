package org.bouncycastle.jcajce;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BCFKSLoadStoreParameter$MacAlgorithm {
    public static final /* synthetic */ BCFKSLoadStoreParameter$MacAlgorithm[] E;
    public static final BCFKSLoadStoreParameter$MacAlgorithm HmacSHA3_512;
    public static final BCFKSLoadStoreParameter$MacAlgorithm HmacSHA512;

    static {
        BCFKSLoadStoreParameter$MacAlgorithm bCFKSLoadStoreParameter$MacAlgorithm = new BCFKSLoadStoreParameter$MacAlgorithm("HmacSHA512", 0);
        HmacSHA512 = bCFKSLoadStoreParameter$MacAlgorithm;
        BCFKSLoadStoreParameter$MacAlgorithm bCFKSLoadStoreParameter$MacAlgorithm2 = new BCFKSLoadStoreParameter$MacAlgorithm("HmacSHA3_512", 1);
        HmacSHA3_512 = bCFKSLoadStoreParameter$MacAlgorithm2;
        E = new BCFKSLoadStoreParameter$MacAlgorithm[]{bCFKSLoadStoreParameter$MacAlgorithm, bCFKSLoadStoreParameter$MacAlgorithm2};
    }

    public static BCFKSLoadStoreParameter$MacAlgorithm valueOf(String str) {
        return (BCFKSLoadStoreParameter$MacAlgorithm) Enum.valueOf(BCFKSLoadStoreParameter$MacAlgorithm.class, str);
    }

    public static BCFKSLoadStoreParameter$MacAlgorithm[] values() {
        return (BCFKSLoadStoreParameter$MacAlgorithm[]) E.clone();
    }
}
