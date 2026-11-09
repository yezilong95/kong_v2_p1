package defpackage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class y extends g0 {
    public static final o G = new o(9, y.class);
    public final byte[] E;
    public final int F;

    public y(long j) {
        this.E = BigInteger.valueOf(j).toByteArray();
        this.F = 0;
    }

    public static y o(Object obj) {
        if (obj == null || (obj instanceof y)) {
            return (y) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: ".concat(obj.getClass().getName()));
        }
        try {
            return (y) G.b((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static int s(byte[] bArr, int i, int i2) {
        int length = bArr.length;
        int iMax = Math.max(i, length - 4);
        int i3 = i2 & bArr[iMax];
        while (true) {
            iMax++;
            if (iMax >= length) {
                return i3;
            }
            i3 = (i3 << 8) | (bArr[iMax] & 255);
        }
    }

    public static boolean u(byte[] bArr) {
        int length = bArr.length;
        if (length != 0) {
            return (length == 1 || bArr[0] != (bArr[1] >> 7) || cm0.c("org.bouncycastle.asn1.allow_unsafe_integer")) ? false : true;
        }
        return true;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof y)) {
            return false;
        }
        return Arrays.equals(this.E, ((y) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(2, z, this.E);
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

    public final BigInteger p() {
        return new BigInteger(1, this.E);
    }

    public final BigInteger q() {
        return new BigInteger(this.E);
    }

    public final int r() {
        byte[] bArr = this.E;
        int length = bArr.length;
        int i = this.F;
        int i2 = length - i;
        if (i2 > 4 || (i2 == 4 && (bArr[i] & 128) != 0)) {
            throw new ArithmeticException("ASN.1 Integer out of positive int range");
        }
        return s(bArr, i, 255);
    }

    public final int t() {
        byte[] bArr = this.E;
        int length = bArr.length;
        int i = this.F;
        if (length - i <= 4) {
            return s(bArr, i, -1);
        }
        throw new ArithmeticException("ASN.1 Integer out of int range");
    }

    public final String toString() {
        return q().toString();
    }

    public y(BigInteger bigInteger) {
        this.E = bigInteger.toByteArray();
        this.F = 0;
    }

    public y(byte[] bArr) {
        if (u(bArr)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.E = bArr;
        int length = bArr.length - 1;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            if (bArr[i] != (bArr[i2] >> 7)) {
                break;
            } else {
                i = i2;
            }
        }
        this.F = i;
    }
}
