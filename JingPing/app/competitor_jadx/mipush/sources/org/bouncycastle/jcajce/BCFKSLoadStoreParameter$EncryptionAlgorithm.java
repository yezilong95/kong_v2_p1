package org.bouncycastle.jcajce;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BCFKSLoadStoreParameter$EncryptionAlgorithm {
    public static final BCFKSLoadStoreParameter$EncryptionAlgorithm AES256_CCM;
    public static final BCFKSLoadStoreParameter$EncryptionAlgorithm AES256_KWP;
    public static final /* synthetic */ BCFKSLoadStoreParameter$EncryptionAlgorithm[] E;

    static {
        BCFKSLoadStoreParameter$EncryptionAlgorithm bCFKSLoadStoreParameter$EncryptionAlgorithm = new BCFKSLoadStoreParameter$EncryptionAlgorithm("AES256_CCM", 0);
        AES256_CCM = bCFKSLoadStoreParameter$EncryptionAlgorithm;
        BCFKSLoadStoreParameter$EncryptionAlgorithm bCFKSLoadStoreParameter$EncryptionAlgorithm2 = new BCFKSLoadStoreParameter$EncryptionAlgorithm("AES256_KWP", 1);
        AES256_KWP = bCFKSLoadStoreParameter$EncryptionAlgorithm2;
        E = new BCFKSLoadStoreParameter$EncryptionAlgorithm[]{bCFKSLoadStoreParameter$EncryptionAlgorithm, bCFKSLoadStoreParameter$EncryptionAlgorithm2};
    }

    public static BCFKSLoadStoreParameter$EncryptionAlgorithm valueOf(String str) {
        return (BCFKSLoadStoreParameter$EncryptionAlgorithm) Enum.valueOf(BCFKSLoadStoreParameter$EncryptionAlgorithm.class, str);
    }

    public static BCFKSLoadStoreParameter$EncryptionAlgorithm[] values() {
        return (BCFKSLoadStoreParameter$EncryptionAlgorithm[]) E.clone();
    }
}
