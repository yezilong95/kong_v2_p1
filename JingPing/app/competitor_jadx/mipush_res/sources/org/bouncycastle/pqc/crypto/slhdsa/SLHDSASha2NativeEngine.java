package org.bouncycastle.pqc.crypto.slhdsa;

import defpackage.fa0;

/* JADX INFO: loaded from: classes.dex */
class SLHDSASha2NativeEngine extends fa0 {
    public static native void bitmask(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6);

    public static native void dispose(long j);

    public static native void initMemoStates(long j, byte[] bArr, byte[] bArr2, int i, int i2);

    public static native long makeInstance();

    public static native byte[] msgDigestAndReturnRange(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7);

    public static native byte[] sha256DigestAndReturnRange(long j, boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6);

    public final String toString() {
        return "SLHDSA[Native](SHA252[Native]())";
    }
}
