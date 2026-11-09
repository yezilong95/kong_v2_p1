package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class kk extends g0 {
    public final char[] E;

    static {
        new o(0, kk.class);
    }

    public kk(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("'string' cannot be null");
        }
        int length = bArr.length;
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("malformed BMPString encoding encountered");
        }
        int i = length / 2;
        char[] cArr = new char[i];
        for (int i2 = 0; i2 != i; i2++) {
            int i3 = i2 * 2;
            cArr[i2] = (char) ((bArr[i3 + 1] & 255) | (bArr[i3] << 8));
        }
        this.E = cArr;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof kk)) {
            return false;
        }
        return Arrays.equals(this.E, ((kk) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        char[] cArr = this.E;
        int length = cArr.length;
        p50Var.J(30, z);
        p50Var.E(length * 2);
        byte[] bArr = new byte[8];
        int i = length & (-4);
        int i2 = 0;
        while (i2 < i) {
            char c = cArr[i2];
            char c2 = cArr[i2 + 1];
            char c3 = cArr[i2 + 2];
            char c4 = cArr[i2 + 3];
            i2 += 4;
            bArr[0] = (byte) (c >> '\b');
            bArr[1] = (byte) c;
            bArr[2] = (byte) (c2 >> '\b');
            bArr[3] = (byte) c2;
            bArr[4] = (byte) (c3 >> '\b');
            bArr[5] = (byte) c3;
            bArr[6] = (byte) (c4 >> '\b');
            bArr[7] = (byte) c4;
            p50Var.D(bArr, 0, 8);
        }
        if (i2 < length) {
            int i3 = 0;
            do {
                char c5 = cArr[i2];
                i2++;
                int i4 = i3 + 1;
                bArr[i3] = (byte) (c5 >> '\b');
                i3 += 2;
                bArr[i4] = (byte) c5;
            } while (i2 < length);
            p50Var.D(bArr, 0, i3);
        }
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        char[] cArr = this.E;
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        while (true) {
            length--;
            if (length < 0) {
                return i;
            }
            i = (i * 257) ^ cArr[length];
        }
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(this.E.length * 2, z);
    }

    public final String toString() {
        return new String(this.E);
    }

    public kk(char[] cArr) {
        this.E = cArr;
    }
}
