package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeGCMSIV {
    public static native void dispose(long j);

    public static native int doFinal(long j, byte[] bArr, int i, byte[] bArr2, int i2);

    public static native byte[] getMac(long j);

    public static native int getOutputSize(long j, int i);

    public static native int getUpdateOutputSize(long j, int i, int i2);

    public static native void initNative(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native long makeInstance();

    public static native void processAADByte(long j, byte b);

    public static native void processAADBytes(long j, byte[] bArr, int i, int i2);

    private native void reset(long j);

    public static native void test_set_max_dl(long j, long j2);

    public final String toString() {
        return "GCMSIV[Native](AES[Native](not initialized))";
    }
}
