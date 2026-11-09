package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ls0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFF"));
    public final int[] U;

    public ls0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP128R1FieldElement");
        }
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > 128) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[4];
        for (int i = 0; i < 4; i++) {
            iArr[i] = bigInteger.intValue();
            bigInteger = bigInteger.shiftRight(32);
        }
        if ((iArr[3] >>> 1) >= 2147483646) {
            int[] iArr2 = z91.Q;
            if (la0.I(iArr, iArr2)) {
                long j = (((long) iArr[0]) & 4294967295L) - (((long) iArr2[0]) & 4294967295L);
                iArr[0] = (int) j;
                long j2 = ((((long) iArr[1]) & 4294967295L) - (((long) iArr2[1]) & 4294967295L)) + (j >> 32);
                iArr[1] = (int) j2;
                long j3 = ((((long) iArr[2]) & 4294967295L) - (((long) iArr2[2]) & 4294967295L)) + (j2 >> 32);
                iArr[2] = (int) j3;
                iArr[3] = (int) (((((long) iArr[3]) & 4294967295L) - (((long) iArr2[3]) & 4294967295L)) + (j3 >> 32));
            }
        }
        this.U = iArr;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[4];
        ma0.j(z91.Q, ((ls0) l21Var).U, iArr);
        z91.R(iArr, this.U, iArr);
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (la0.T(iArr) || la0.Q(iArr)) {
            return this;
        }
        int[] iArr2 = new int[4];
        z91.n0(iArr, iArr2);
        z91.R(iArr2, iArr, iArr2);
        int[] iArr3 = new int[4];
        z91.p0(2, iArr2, iArr3);
        z91.R(iArr3, iArr2, iArr3);
        int[] iArr4 = new int[4];
        z91.p0(4, iArr3, iArr4);
        z91.R(iArr4, iArr3, iArr4);
        z91.p0(2, iArr4, iArr3);
        z91.R(iArr3, iArr2, iArr3);
        z91.p0(10, iArr3, iArr2);
        z91.R(iArr2, iArr3, iArr2);
        z91.p0(10, iArr2, iArr4);
        z91.R(iArr4, iArr3, iArr4);
        z91.n0(iArr4, iArr3);
        z91.R(iArr3, iArr, iArr3);
        z91.p0(95, iArr3, iArr3);
        z91.n0(iArr3, iArr4);
        for (int i = 3; i >= 0; i--) {
            if (iArr[i] != iArr4[i]) {
                return null;
            }
        }
        return new ls0(iArr3);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[4];
        z91.n0(this.U, iArr);
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[4];
        ma0.j(z91.Q, this.U, iArr);
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return la0.Q(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return la0.T(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[4];
        z91.r0(this.U, ((ls0) l21Var).U, iArr);
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[4];
        z91.c(this.U, ((ls0) l21Var).U, iArr);
        return new ls0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ls0)) {
            return false;
        }
        int[] iArr = this.U;
        int[] iArr2 = ((ls0) obj).U;
        for (int i = 3; i >= 0; i--) {
            if (iArr[i] != iArr2[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return (this.U[0] & 1) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[4];
        if (fa0.Y(4, this.U, iArr) != 0 || ((iArr[3] >>> 1) >= 2147483646 && la0.I(iArr, z91.Q))) {
            z91.f(iArr);
        }
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 4; i++) {
            int i2 = this.U[i];
            if (i2 != 0) {
                ma0.K(bArr, i2, (3 - i) << 2);
            }
        }
        return new BigInteger(1, bArr);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 4);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[4];
        z91.R(this.U, ((ls0) l21Var).U, iArr);
        return new ls0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[4];
        int[] iArr2 = this.U;
        int iK = z91.K(iArr2);
        int[] iArr3 = z91.Q;
        if (iK != 0) {
            la0.k0(iArr3, iArr3, iArr);
        } else {
            la0.k0(iArr3, iArr2, iArr);
        }
        return new ls0(iArr);
    }

    public ls0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
