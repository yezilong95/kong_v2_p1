package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeCCMPacketCipher {
    public static native int getOutputSize(boolean z, int i, int i2);

    public static native int processPacket(boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2, int i3, byte[] bArr5, int i4, int i5);

    public final String toString() {
        return "CCM-PS[Native](AES[Native])";
    }
}
