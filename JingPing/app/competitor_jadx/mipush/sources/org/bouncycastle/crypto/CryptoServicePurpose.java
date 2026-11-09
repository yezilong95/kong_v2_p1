package org.bouncycastle.crypto;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class CryptoServicePurpose {
    public static final CryptoServicePurpose AGREEMENT;
    public static final CryptoServicePurpose ANY;
    public static final CryptoServicePurpose AUTHENTICATION;
    public static final CryptoServicePurpose DECRYPTION;
    public static final /* synthetic */ CryptoServicePurpose[] E;
    public static final CryptoServicePurpose ENCRYPTION;
    public static final CryptoServicePurpose KEYGEN;
    public static final CryptoServicePurpose PRF;
    public static final CryptoServicePurpose SIGNING;
    public static final CryptoServicePurpose VERIFICATION;
    public static final CryptoServicePurpose VERIFYING;

    static {
        CryptoServicePurpose cryptoServicePurpose = new CryptoServicePurpose("AGREEMENT", 0);
        AGREEMENT = cryptoServicePurpose;
        CryptoServicePurpose cryptoServicePurpose2 = new CryptoServicePurpose("ENCRYPTION", 1);
        ENCRYPTION = cryptoServicePurpose2;
        CryptoServicePurpose cryptoServicePurpose3 = new CryptoServicePurpose("DECRYPTION", 2);
        DECRYPTION = cryptoServicePurpose3;
        CryptoServicePurpose cryptoServicePurpose4 = new CryptoServicePurpose("KEYGEN", 3);
        KEYGEN = cryptoServicePurpose4;
        CryptoServicePurpose cryptoServicePurpose5 = new CryptoServicePurpose("SIGNING", 4);
        SIGNING = cryptoServicePurpose5;
        CryptoServicePurpose cryptoServicePurpose6 = new CryptoServicePurpose("VERIFYING", 5);
        VERIFYING = cryptoServicePurpose6;
        CryptoServicePurpose cryptoServicePurpose7 = new CryptoServicePurpose("AUTHENTICATION", 6);
        AUTHENTICATION = cryptoServicePurpose7;
        CryptoServicePurpose cryptoServicePurpose8 = new CryptoServicePurpose("VERIFICATION", 7);
        VERIFICATION = cryptoServicePurpose8;
        CryptoServicePurpose cryptoServicePurpose9 = new CryptoServicePurpose("PRF", 8);
        PRF = cryptoServicePurpose9;
        CryptoServicePurpose cryptoServicePurpose10 = new CryptoServicePurpose("ANY", 9);
        ANY = cryptoServicePurpose10;
        E = new CryptoServicePurpose[]{cryptoServicePurpose, cryptoServicePurpose2, cryptoServicePurpose3, cryptoServicePurpose4, cryptoServicePurpose5, cryptoServicePurpose6, cryptoServicePurpose7, cryptoServicePurpose8, cryptoServicePurpose9, cryptoServicePurpose10};
    }

    public static CryptoServicePurpose valueOf(String str) {
        return (CryptoServicePurpose) Enum.valueOf(CryptoServicePurpose.class, str);
    }

    public static CryptoServicePurpose[] values() {
        return (CryptoServicePurpose[]) E.clone();
    }
}
