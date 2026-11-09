package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class at0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F"));
    public final int[] U;

    public at0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP256K1FieldElement");
        }
        int[] iArrK = fa0.K(bigInteger);
        if (iArrK[7] == -1) {
            int[] iArr = t40.o;
            if (fa0.U(iArrK, iArr)) {
                fa0.H0(iArr, iArrK);
            }
        }
        this.U = iArrK;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[8];
        ma0.j(t40.o, ((at0) l21Var).U, iArr);
        int[] iArr2 = new int[16];
        fa0.m0(iArr, this.U, iArr2);
        t40.X0(iArr2, iArr);
        return new at0(iArr);
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
        fa0.y0(iArr, iArr2);
        t40.X0(iArr2, iArr3);
        fa0.m0(iArr3, iArr, iArr2);
        t40.X0(iArr2, iArr3);
        int[] iArr4 = new int[8];
        fa0.y0(iArr3, iArr2);
        t40.X0(iArr2, iArr4);
        fa0.m0(iArr4, iArr, iArr2);
        t40.X0(iArr2, iArr4);
        int[] iArr5 = new int[8];
        t40.h1(3, iArr4, iArr5, iArr2);
        fa0.m0(iArr5, iArr4, iArr2);
        t40.X0(iArr2, iArr5);
        t40.h1(3, iArr5, iArr5, iArr2);
        fa0.m0(iArr5, iArr4, iArr2);
        t40.X0(iArr2, iArr5);
        t40.h1(2, iArr5, iArr5, iArr2);
        fa0.m0(iArr5, iArr3, iArr2);
        t40.X0(iArr2, iArr5);
        int[] iArr6 = new int[8];
        t40.h1(11, iArr5, iArr6, iArr2);
        fa0.m0(iArr6, iArr5, iArr2);
        t40.X0(iArr2, iArr6);
        t40.h1(22, iArr6, iArr5, iArr2);
        fa0.m0(iArr5, iArr6, iArr2);
        t40.X0(iArr2, iArr5);
        int[] iArr7 = new int[8];
        t40.h1(44, iArr5, iArr7, iArr2);
        fa0.m0(iArr7, iArr5, iArr2);
        t40.X0(iArr2, iArr7);
        int[] iArr8 = new int[8];
        t40.h1(88, iArr7, iArr8, iArr2);
        fa0.m0(iArr8, iArr7, iArr2);
        t40.X0(iArr2, iArr8);
        t40.h1(44, iArr8, iArr7, iArr2);
        fa0.m0(iArr7, iArr5, iArr2);
        t40.X0(iArr2, iArr7);
        t40.h1(3, iArr7, iArr5, iArr2);
        fa0.m0(iArr5, iArr4, iArr2);
        t40.X0(iArr2, iArr5);
        t40.h1(23, iArr5, iArr5, iArr2);
        fa0.m0(iArr5, iArr6, iArr2);
        t40.X0(iArr2, iArr5);
        t40.h1(6, iArr5, iArr5, iArr2);
        fa0.m0(iArr5, iArr3, iArr2);
        t40.X0(iArr2, iArr5);
        t40.h1(2, iArr5, iArr5, iArr2);
        fa0.y0(iArr5, iArr2);
        t40.X0(iArr2, iArr3);
        if (fa0.E(iArr, iArr3)) {
            return new at0(iArr5);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[8];
        int[] iArr2 = new int[16];
        fa0.y0(this.U, iArr2);
        t40.X0(iArr2, iArr);
        return new at0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[8];
        ma0.j(t40.o, this.U, iArr);
        return new at0(iArr);
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
        t40.m1(this.U, ((at0) l21Var).U, iArr);
        return new at0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[8];
        if (fa0.b(this.U, ((at0) l21Var).U, iArr) != 0 || (iArr[7] == -1 && fa0.U(iArr, t40.o))) {
            fa0.c(8, 977, iArr);
        }
        return new at0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof at0) {
            return fa0.E(this.U, ((at0) obj).U);
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
        if (fa0.Y(8, this.U, iArr) != 0 || (iArr[7] == -1 && fa0.U(iArr, t40.o))) {
            fa0.c(8, 977, iArr);
        }
        return new at0(iArr);
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
        fa0.m0(this.U, ((at0) l21Var).U, iArr2);
        t40.X0(iArr2, iArr);
        return new at0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[8];
        int[] iArr2 = this.U;
        int iY0 = t40.y0(iArr2);
        int[] iArr3 = t40.o;
        if (iY0 != 0) {
            fa0.C0(iArr3, iArr3, iArr);
        } else {
            fa0.C0(iArr3, iArr2, iArr);
        }
        return new at0(iArr);
    }

    public at0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
