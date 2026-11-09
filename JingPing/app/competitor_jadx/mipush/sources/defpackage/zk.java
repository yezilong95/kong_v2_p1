package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zk extends g0 {
    public final byte[] E;

    static {
        new o(21, zk.class);
    }

    public zk(byte[] bArr) {
        this.E = bArr;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof zk)) {
            return false;
        }
        return Arrays.equals(this.E, ((zk) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(12, z, this.E);
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
        String str = jx0.a;
        byte[] bArr = this.E;
        int length = bArr.length;
        char[] cArr = new char[length];
        short[] sArr = v21.a;
        int length2 = bArr.length;
        int i = 0;
        int i2 = 0;
        loop0: while (i < length2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b < 0) {
                short s = v21.a[b & 127];
                int i4 = s >>> 8;
                byte b2 = (byte) s;
                while (true) {
                    if (b2 >= 0) {
                        if (i3 >= length2) {
                            break loop0;
                        }
                        int i5 = i3 + 1;
                        byte b3 = bArr[i3];
                        i4 = (i4 << 6) | (b3 & 63);
                        b2 = v21.b[b2 + ((b3 & 255) >>> 4)];
                        i3 = i5;
                    } else if (b2 != -2) {
                        if (i4 <= 65535) {
                            if (i2 < length) {
                                cArr[i2] = (char) i4;
                                i2++;
                                i = i3;
                            }
                        } else if (i2 < length - 1) {
                            int i6 = i2 + 1;
                            cArr[i2] = (char) ((i4 >>> 10) + 55232);
                            i2 += 2;
                            cArr[i6] = (char) ((i4 & 1023) | 56320);
                            i = i3;
                        }
                    }
                }
                i2 = -1;
                break;
            }
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            cArr[i2] = (char) b;
            i = i3;
            i2++;
        }
        if (i2 >= 0) {
            return new String(cArr, 0, i2);
        }
        throw new IllegalArgumentException("Invalid UTF-8 input");
    }
}
