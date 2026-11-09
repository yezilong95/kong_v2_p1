package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeGCMSIVPacketCipher {
    public static native int getOutputSize(boolean z, int i);

    public static native int processPacket(boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2, byte[] bArr5, int i3, int i4);

    public final String toString() {
        return "GCMSIV-PS[Native](AES[Native])";
    }
}
