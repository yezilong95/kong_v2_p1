package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class al extends g0 {
    public static final char[] F;
    public final byte[] E;

    static {
        new o(22, al.class);
        F = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public al(byte[] bArr) {
        this.E = bArr;
    }

    public static void o(StringBuffer stringBuffer, int i) {
        char[] cArr = F;
        stringBuffer.append(cArr[(i >>> 4) & 15]);
        stringBuffer.append(cArr[i & 15]);
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof al)) {
            return false;
        }
        return Arrays.equals(this.E, ((al) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(28, z, this.E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return z91.E(this.E);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(this.E.length, z);
    }

    public final String toString() {
        int i;
        byte[] bArr = this.E;
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((p50.w(length) + length) * 2) + 3);
        stringBuffer.append("#1C");
        if (length < 128) {
            o(stringBuffer, length);
        } else {
            byte[] bArr2 = new byte[5];
            int i2 = length;
            int i3 = 5;
            while (true) {
                i = i3 - 1;
                bArr2[i] = (byte) i2;
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3 = i;
            }
            int i4 = i3 - 2;
            bArr2[i4] = (byte) (128 | (5 - i));
            while (true) {
                int i5 = i4 + 1;
                o(stringBuffer, bArr2[i4]);
                if (i5 >= 5) {
                    break;
                }
                i4 = i5;
            }
        }
        for (byte b : bArr) {
            o(stringBuffer, b);
        }
        return stringBuffer.toString();
    }
}
