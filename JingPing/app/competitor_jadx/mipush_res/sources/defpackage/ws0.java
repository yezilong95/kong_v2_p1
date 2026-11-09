package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ws0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D"));
    public static final int[] W = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};
    public final int[] U;

    public ws0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        int[] iArrU = s90.u(bigInteger);
        if (iArrU[6] == -1 && s90.z(iArrU, z91.T)) {
            fa0.c(7, 6803, iArrU);
        }
        this.U = iArrU;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[7];
        ma0.j(z91.T, ((ws0) l21Var).U, iArr);
        z91.T(iArr, this.U, iArr);
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (s90.G(iArr) || s90.F(iArr)) {
            return this;
        }
        int[] iArr2 = new int[7];
        z91.o0(iArr, iArr2);
        z91.T(iArr2, iArr, iArr2);
        z91.o0(iArr2, iArr2);
        z91.T(iArr2, iArr, iArr2);
        int[] iArr3 = new int[7];
        z91.o0(iArr2, iArr3);
        z91.T(iArr3, iArr, iArr3);
        int[] iArr4 = new int[7];
        z91.q0(4, iArr3, iArr4);
        z91.T(iArr4, iArr3, iArr4);
        int[] iArr5 = new int[7];
        z91.q0(3, iArr4, iArr5);
        z91.T(iArr5, iArr2, iArr5);
        z91.q0(8, iArr5, iArr5);
        z91.T(iArr5, iArr4, iArr5);
        z91.q0(4, iArr5, iArr4);
        z91.T(iArr4, iArr3, iArr4);
        z91.q0(19, iArr4, iArr3);
        z91.T(iArr3, iArr5, iArr3);
        int[] iArr6 = new int[7];
        z91.q0(42, iArr3, iArr6);
        z91.T(iArr6, iArr3, iArr6);
        z91.q0(23, iArr6, iArr3);
        z91.T(iArr3, iArr4, iArr3);
        z91.q0(84, iArr3, iArr4);
        z91.T(iArr4, iArr6, iArr4);
        z91.q0(20, iArr4, iArr4);
        z91.T(iArr4, iArr5, iArr4);
        z91.q0(3, iArr4, iArr4);
        z91.T(iArr4, iArr, iArr4);
        z91.q0(2, iArr4, iArr4);
        z91.T(iArr4, iArr, iArr4);
        z91.q0(4, iArr4, iArr4);
        z91.T(iArr4, iArr2, iArr4);
        z91.o0(iArr4, iArr4);
        z91.o0(iArr4, iArr6);
        if (s90.t(iArr, iArr6)) {
            return new ws0(iArr4);
        }
        z91.T(iArr4, W, iArr4);
        z91.o0(iArr4, iArr6);
        if (s90.t(iArr, iArr6)) {
            return new ws0(iArr4);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[7];
        z91.o0(this.U, iArr);
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[7];
        ma0.j(z91.T, this.U, iArr);
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return s90.F(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return s90.G(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[7];
        z91.s0(this.U, ((ws0) l21Var).U, iArr);
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[7];
        if (s90.h(this.U, ((ws0) l21Var).U, iArr) != 0 || (iArr[6] == -1 && s90.z(iArr, z91.T))) {
            fa0.c(7, 6803, iArr);
        }
        return new ws0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ws0) {
            return s90.t(this.U, ((ws0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return s90.v(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[7];
        if (fa0.Y(7, this.U, iArr) != 0 || (iArr[6] == -1 && s90.z(iArr, z91.T))) {
            fa0.c(7, 6803, iArr);
        }
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return s90.c0(this.U);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 7);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[7];
        z91.T(this.U, ((ws0) l21Var).U, iArr);
        return new ws0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[7];
        int[] iArr2 = this.U;
        int iL = z91.L(iArr2);
        int[] iArr3 = z91.T;
        if (iL != 0) {
            s90.b0(iArr3, iArr3, iArr);
        } else {
            s90.b0(iArr3, iArr2, iArr);
        }
        return new ws0(iArr);
    }

    public ws0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
