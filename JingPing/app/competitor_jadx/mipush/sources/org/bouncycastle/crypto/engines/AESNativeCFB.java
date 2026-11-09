package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.DataLengthException;

/* JADX INFO: loaded from: classes.dex */
class AESNativeCFB {
    public static native void dispose(long j);

    public static native int getNativeMultiBlockSize();

    public static native long makeNative(boolean z, int i);

    private static native byte processByte(long j, byte b);

    private static native int processBytes(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws DataLengthException;

    private static native void reset(long j);

    public native void init(long j, byte[] bArr, byte[] bArr2);

    public final String toString() {
        return "CFB[Native](AES[Native](not initialized))";
    }
}
