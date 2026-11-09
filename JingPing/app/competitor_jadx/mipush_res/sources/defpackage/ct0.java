package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ct0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFF"));
    public final int[] U;

    public ct0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256R1FieldElement");
        }
        int[] iArrK = fa0.K(bigInteger);
        if (iArrK[7] == -1) {
            int[] iArr = l21.P;
            if (fa0.U(iArrK, iArr)) {
                fa0.H0(iArr, iArrK);
            }
        }
        this.U = iArrK;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[8];
        ma0.j(l21.P, ((ct0) l21Var).U, iArr);
        int[] iArr2 = new int[16];
        fa0.m0(iArr, this.U, iArr2);
        l21.J0(iArr2, iArr);
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (fa0.h0(iArr) || fa0.e0(iArr)) {
            return this;
        }
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[8];
        int[] iArr4 = new int[8];
        fa0.y0(iArr, iArr2);
        l21.J0(iArr2, iArr3);
        fa0.m0(iArr3, iArr, iArr2);
        l21.J0(iArr2, iArr3);
        l21.Y0(2, iArr3, iArr4, iArr2);
        fa0.m0(iArr4, iArr3, iArr2);
        l21.J0(iArr2, iArr4);
        l21.Y0(4, iArr4, iArr3, iArr2);
        fa0.m0(iArr3, iArr4, iArr2);
        l21.J0(iArr2, iArr3);
        l21.Y0(8, iArr3, iArr4, iArr2);
        fa0.m0(iArr4, iArr3, iArr2);
        l21.J0(iArr2, iArr4);
        l21.Y0(16, iArr4, iArr3, iArr2);
        fa0.m0(iArr3, iArr4, iArr2);
        l21.J0(iArr2, iArr3);
        l21.Y0(32, iArr3, iArr3, iArr2);
        fa0.m0(iArr3, iArr, iArr2);
        l21.J0(iArr2, iArr3);
        l21.Y0(96, iArr3, iArr3, iArr2);
        fa0.m0(iArr3, iArr, iArr2);
        l21.J0(iArr2, iArr3);
        l21.Y0(94, iArr3, iArr3, iArr2);
        fa0.y0(iArr3, iArr2);
        l21.J0(iArr2, iArr4);
        if (fa0.E(iArr, iArr4)) {
            return new ct0(iArr3);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[8];
        int[] iArr2 = new int[16];
        fa0.y0(this.U, iArr2);
        l21.J0(iArr2, iArr);
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[8];
        ma0.j(l21.P, this.U, iArr);
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return fa0.e0(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.h0(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[8];
        l21.f1(this.U, ((ct0) l21Var).U, iArr);
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[8];
        if (fa0.b(this.U, ((ct0) l21Var).U, iArr) != 0 || (iArr[7] == -1 && fa0.U(iArr, l21.P))) {
            l21.i(iArr);
        }
        return new ct0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ct0) {
            return fa0.E(this.U, ((ct0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return fa0.M(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[8];
        if (fa0.Y(8, this.U, iArr) != 0 || (iArr[7] == -1 && fa0.U(iArr, l21.P))) {
            l21.i(iArr);
        }
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.K0(this.U);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 8);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[8];
        int[] iArr2 = new int[16];
        fa0.m0(this.U, ((ct0) l21Var).U, iArr2);
        l21.J0(iArr2, iArr);
        return new ct0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[8];
        int[] iArr2 = this.U;
        int iE0 = l21.e0(iArr2);
        int[] iArr3 = l21.P;
        if (iE0 != 0) {
            fa0.C0(iArr3, iArr3, iArr);
        } else {
            fa0.C0(iArr3, iArr2, iArr);
        }
        return new ct0(iArr);
    }

    public ct0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
