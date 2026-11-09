package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
class AESNativeCCM {
    public static native void dispose(long j);

    public static native byte[] getMac(long j);

    public static native int getOutputSize(long j, int i);

    public static native void initNative(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2);

    public static native long makeInstance();

    public static native int processPacket(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4, byte[] bArr3, int i5);

    private native void reset(long j, boolean z);

    public final String toString() {
        return "CCM[Native](AES[Native](not initialized))";
    }
}
