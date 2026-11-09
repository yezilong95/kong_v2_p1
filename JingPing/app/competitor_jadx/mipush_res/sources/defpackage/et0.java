package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class et0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFF0000000000000000FFFFFFFF"));
    public final int[] U;

    public et0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP384R1FieldElement");
        }
        int[] iArrJ = fa0.J(384, bigInteger);
        if (iArrJ[11] == -1) {
            int[] iArr = j31.G;
            if (fa0.T(12, iArrJ, iArr)) {
                fa0.G0(12, iArr, iArrJ);
            }
        }
        this.U = iArrJ;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[12];
        ma0.j(j31.G, ((et0) l21Var).U, iArr);
        int[] iArr2 = new int[24];
        ma0.X(iArr, this.U, iArr2);
        j31.S(iArr2, iArr);
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (fa0.i0(iArr, 12) || fa0.f0(iArr, 12)) {
            return this;
        }
        int[] iArr2 = new int[24];
        int[] iArr3 = new int[12];
        int[] iArr4 = new int[12];
        int[] iArr5 = new int[12];
        int[] iArr6 = new int[12];
        ma0.r0(iArr, iArr2);
        j31.S(iArr2, iArr3);
        ma0.X(iArr3, iArr, iArr2);
        j31.S(iArr2, iArr3);
        j31.d0(2, iArr3, iArr4, iArr2);
        ma0.X(iArr4, iArr3, iArr2);
        j31.S(iArr2, iArr4);
        ma0.r0(iArr4, iArr2);
        j31.S(iArr2, iArr4);
        ma0.X(iArr4, iArr, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(5, iArr4, iArr5, iArr2);
        ma0.X(iArr5, iArr4, iArr2);
        j31.S(iArr2, iArr5);
        j31.d0(5, iArr5, iArr6, iArr2);
        ma0.X(iArr6, iArr4, iArr2);
        j31.S(iArr2, iArr6);
        j31.d0(15, iArr6, iArr4, iArr2);
        ma0.X(iArr4, iArr6, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(2, iArr4, iArr5, iArr2);
        ma0.X(iArr3, iArr5, iArr2);
        j31.S(iArr2, iArr3);
        j31.d0(28, iArr5, iArr5, iArr2);
        ma0.X(iArr4, iArr5, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(60, iArr4, iArr5, iArr2);
        ma0.X(iArr5, iArr4, iArr2);
        j31.S(iArr2, iArr5);
        j31.d0(120, iArr5, iArr4, iArr2);
        ma0.X(iArr4, iArr5, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(15, iArr4, iArr4, iArr2);
        ma0.X(iArr4, iArr6, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(33, iArr4, iArr4, iArr2);
        ma0.X(iArr4, iArr3, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(64, iArr4, iArr4, iArr2);
        ma0.X(iArr4, iArr, iArr2);
        j31.S(iArr2, iArr4);
        j31.d0(30, iArr4, iArr3, iArr2);
        ma0.r0(iArr3, iArr2);
        j31.S(iArr2, iArr4);
        if (fa0.D(12, iArr, iArr4)) {
            return new et0(iArr3);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[12];
        int[] iArr2 = new int[24];
        ma0.r0(this.U, iArr2);
        j31.S(iArr2, iArr);
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[12];
        ma0.j(j31.G, this.U, iArr);
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return fa0.f0(this.U, 12);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.i0(this.U, 12);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[12];
        j31.j0(this.U, ((et0) l21Var).U, iArr);
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[12];
        if (fa0.a(12, this.U, ((et0) l21Var).U, iArr) != 0 || (iArr[11] == -1 && fa0.T(12, iArr, j31.G))) {
            j31.f(iArr);
        }
        return new et0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof et0) {
            return fa0.D(12, this.U, ((et0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return fa0.N(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[12];
        if (fa0.Y(12, this.U, iArr) != 0 || (iArr[11] == -1 && fa0.T(12, iArr, j31.G))) {
            j31.f(iArr);
        }
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.L0(this.U, 12);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 12);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[12];
        int[] iArr2 = new int[24];
        ma0.X(this.U, ((et0) l21Var).U, iArr2);
        j31.S(iArr2, iArr);
        return new et0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[12];
        int[] iArr2 = this.U;
        int iB = j31.B(iArr2);
        int[] iArr3 = j31.G;
        if (iB != 0) {
            fa0.B0(12, iArr3, iArr3, iArr);
        } else {
            fa0.B0(12, iArr3, iArr2, iArr);
        }
        return new et0(iArr);
    }

    public et0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
