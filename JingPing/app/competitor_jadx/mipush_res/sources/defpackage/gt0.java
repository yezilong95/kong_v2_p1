package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class gt0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));
    public final int[] U;

    public gt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        int[] iArrJ = fa0.J(521, bigInteger);
        if (fa0.D(17, iArrJ, z91.W)) {
            for (int i = 0; i < 17; i++) {
                iArrJ[i] = 0;
            }
        }
        this.U = iArrJ;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[17];
        ma0.j(z91.W, ((gt0) l21Var).U, iArr);
        int[] iArr2 = new int[33];
        z91.H(iArr, this.U, iArr2);
        z91.f0(iArr2, iArr);
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (fa0.i0(iArr, 17) || fa0.f0(iArr, 17)) {
            return this;
        }
        int[] iArr2 = new int[33];
        int[] iArr3 = new int[17];
        int[] iArr4 = new int[17];
        z91.I(iArr, iArr2);
        z91.f0(iArr2, iArr3);
        int i = 519;
        while (true) {
            i--;
            if (i <= 0) {
                break;
            }
            z91.I(iArr3, iArr2);
            z91.f0(iArr2, iArr3);
        }
        z91.I(iArr3, iArr2);
        z91.f0(iArr2, iArr4);
        if (fa0.D(17, iArr, iArr4)) {
            return new gt0(iArr3);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[17];
        int[] iArr2 = new int[33];
        z91.I(this.U, iArr2);
        z91.f0(iArr2, iArr);
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[17];
        ma0.j(z91.W, this.U, iArr);
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return fa0.f0(this.U, 17);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.i0(this.U, 17);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[17];
        z91.t0(this.U, ((gt0) l21Var).U, iArr);
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[17];
        z91.e(this.U, ((gt0) l21Var).U, iArr);
        return new gt0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gt0) {
            return fa0.D(17, this.U, ((gt0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return fa0.N(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[17];
        int[] iArr2 = this.U;
        int iY = fa0.Y(16, iArr2, iArr) + iArr2[16];
        if (iY > 511 || (iY == 511 && fa0.D(16, iArr, z91.W))) {
            iY = (fa0.Z(iArr) + iY) & 511;
        }
        iArr[16] = iY;
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.L0(this.U, 17);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 17);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[17];
        int[] iArr2 = new int[33];
        z91.H(this.U, ((gt0) l21Var).U, iArr2);
        z91.f0(iArr2, iArr);
        return new gt0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[17];
        int[] iArr2 = this.U;
        int iM = z91.M(iArr2);
        int[] iArr3 = z91.W;
        if (iM != 0) {
            fa0.B0(17, iArr3, iArr3, iArr);
        } else {
            fa0.B0(17, iArr3, iArr2, iArr);
        }
        return new gt0(iArr);
    }

    public gt0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
