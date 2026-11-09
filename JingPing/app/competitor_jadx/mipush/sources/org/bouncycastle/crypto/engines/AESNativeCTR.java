package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeCTR {
    public static native void dispose(long j);

    private static native int getMultiBlockSize(long j);

    private static native long getPosition(long j);

    public static native void init(long j, byte[] bArr, byte[] bArr2);

    public static native long makeCTRInstance();

    private static native int processBytes(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    private static native void reset(long j);

    private static native byte returnByte(long j, byte b);

    private static native long seekTo(long j, long j2);

    private static native long skip(long j, long j2);

    public final String toString() {
        return "CTR[Native](AES[Native](not initialized))";
    }
}
