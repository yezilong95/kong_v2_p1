package org.bouncycastle.crypto;

import defpackage.fu;

/* JADX INFO: loaded from: classes.dex */
class NativeEntropySource implements fu {
    @Override // defpackage.fu
    public final byte[] b() {
        seedBuffer(new byte[0], false);
        throw new IllegalStateException("entropy source return array of len 0 where all elements are 0");
    }

    @Override // defpackage.fu
    public final int c() {
        return 0;
    }

    public native boolean isPredictionResistant();

    public native int modulus();

    public native void seedBuffer(byte[] bArr, boolean z);
}
