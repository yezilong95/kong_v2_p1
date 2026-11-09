package org.bouncycastle.crypto.engines;

/* JADX INFO: loaded from: classes.dex */
public class AESNativeCFBPacketCipher {
    public static native int getOutputSize(int i);

    public static native int processPacket(boolean z, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, byte[] bArr4, int i3, int i4);

    public final String toString() {
        return "CFB-PS[Native](AES[Native])";
    }
}
