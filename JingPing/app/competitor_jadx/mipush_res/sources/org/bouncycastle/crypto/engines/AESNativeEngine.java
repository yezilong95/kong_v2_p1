package org.bouncycastle.crypto.engines;

import defpackage.j31;

/* JADX INFO: loaded from: classes.dex */
class AESNativeEngine extends j31 {
    public static native void dispose(long j);

    private static native int getBlockSize(long j);

    private static native int getMultiBlockSize(long j);

    public static native void init(long j, byte[] bArr);

    public static native long makeInstance(int i, boolean z);

    private static native int process(long j, byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    private static native void reset(long j);

    public final String toString() {
        return "AES[Native](0)";
    }
}
