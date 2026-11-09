package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
class AESNativeGCM {
    public static native void dispose(long j);

    private static native int doFinal(long j, byte[] bArr, int i);

    public static native byte[] getMac(long j);

    private static native int getOutputSize(long j, int i);

    private static native int getUpdateOutputSize(long j, int i);

    public static native void initNative(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, int i);

    public static native long makeInstance(int i, boolean z);

    private static native void processAADByte(long j, byte b);

    private static native void processAADBytes(long j, byte[] bArr, int i, int i2);

    private static native int processByte(long j, byte b, byte[] bArr, int i);

    private static native int processBytes(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    private native void reset(long j);

    private native void setBlocksRemainingDown(long j, long j2);

    public final String toString() {
        return "GCM[Native](AES[Native](not initialized))";
    }
}
