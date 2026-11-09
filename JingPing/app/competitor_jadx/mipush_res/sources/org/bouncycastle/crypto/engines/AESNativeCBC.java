package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
class AESNativeCBC {
    public static native void dispose(long j);

    private static native int getBlockSize(long j);

    private static native int getMultiBlockSize(long j);

    public static native long makeNative(int i, boolean z);

    private static native int process(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    private static native void reset(long j);

    public native void init(long j, byte[] bArr, byte[] bArr2);

    public final String toString() {
        return "CBC[Native](AES[Native](0)";
    }
}
