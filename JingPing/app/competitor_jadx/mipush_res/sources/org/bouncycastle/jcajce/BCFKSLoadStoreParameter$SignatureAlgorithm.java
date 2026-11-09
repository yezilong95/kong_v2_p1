package org.bouncycastle.jcajce;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BCFKSLoadStoreParameter$SignatureAlgorithm {
    public static final /* synthetic */ BCFKSLoadStoreParameter$SignatureAlgorithm[] E;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA3_512withDSA;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA3_512withECDSA;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA3_512withRSA;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA512withDSA;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA512withECDSA;
    public static final BCFKSLoadStoreParameter$SignatureAlgorithm SHA512withRSA;

    static {
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA512withDSA", 0);
        SHA512withDSA = bCFKSLoadStoreParameter$SignatureAlgorithm;
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm2 = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA3_512withDSA", 1);
        SHA3_512withDSA = bCFKSLoadStoreParameter$SignatureAlgorithm2;
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm3 = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA512withECDSA", 2);
        SHA512withECDSA = bCFKSLoadStoreParameter$SignatureAlgorithm3;
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm4 = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA3_512withECDSA", 3);
        SHA3_512withECDSA = bCFKSLoadStoreParameter$SignatureAlgorithm4;
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm5 = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA512withRSA", 4);
        SHA512withRSA = bCFKSLoadStoreParameter$SignatureAlgorithm5;
        BCFKSLoadStoreParameter$SignatureAlgorithm bCFKSLoadStoreParameter$SignatureAlgorithm6 = new BCFKSLoadStoreParameter$SignatureAlgorithm("SHA3_512withRSA", 5);
        SHA3_512withRSA = bCFKSLoadStoreParameter$SignatureAlgorithm6;
        E = new BCFKSLoadStoreParameter$SignatureAlgorithm[]{bCFKSLoadStoreParameter$SignatureAlgorithm, bCFKSLoadStoreParameter$SignatureAlgorithm2, bCFKSLoadStoreParameter$SignatureAlgorithm3, bCFKSLoadStoreParameter$SignatureAlgorithm4, bCFKSLoadStoreParameter$SignatureAlgorithm5, bCFKSLoadStoreParameter$SignatureAlgorithm6};
    }

    public static BCFKSLoadStoreParameter$SignatureAlgorithm valueOf(String str) {
        return (BCFKSLoadStoreParameter$SignatureAlgorithm) Enum.valueOf(BCFKSLoadStoreParameter$SignatureAlgorithm.class, str);
    }

    public static BCFKSLoadStoreParameter$SignatureAlgorithm[] values() {
        return (BCFKSLoadStoreParameter$SignatureAlgorithm[]) E.clone();
    }
}
