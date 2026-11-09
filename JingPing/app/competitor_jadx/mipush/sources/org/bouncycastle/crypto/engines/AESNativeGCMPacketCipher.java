package org.bouncycastle.crypto.engines;

import javax.security.auth.Destroyable;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeGCMPacketCipher implements Destroyable {
    public static native int getOutputSize(boolean z, int i, int i2);

    public static native int processPacket(boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, byte[] bArr5, int i4, int i5);

    @Override // javax.security.auth.Destroyable
    public final void destroy() {
    }

    @Override // javax.security.auth.Destroyable
    public final boolean isDestroyed() {
        return true;
    }

    public final String toString() {
        return "GCM-PS[Native](AES[Native])";
    }
}
