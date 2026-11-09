package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class qs0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFAC73"));
    public final int[] U;

    public qs0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R2FieldElement");
        }
        int[] iArrB = ma0.B(bigInteger);
        if (iArrB[4] == -1) {
            int[] iArr = t40.l;
            if (ma0.F(iArrB, iArr)) {
                ma0.u0(iArr, iArrB);
            }
        }
        this.U = iArrB;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[5];
        ma0.j(t40.l, ((qs0) l21Var).U, iArr);
        t40.D0(iArr, this.U, iArr);
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (ma0.O(iArr) || ma0.N(iArr)) {
            return this;
        }
        int[] iArr2 = new int[5];
        t40.f1(iArr, iArr2);
        t40.D0(iArr2, iArr, iArr2);
        int[] iArr3 = new int[5];
        t40.f1(iArr2, iArr3);
        t40.D0(iArr3, iArr, iArr3);
        int[] iArr4 = new int[5];
        t40.f1(iArr3, iArr4);
        t40.D0(iArr4, iArr, iArr4);
        int[] iArr5 = new int[5];
        t40.g1(3, iArr4, iArr5);
        t40.D0(iArr5, iArr3, iArr5);
        t40.g1(7, iArr5, iArr4);
        t40.D0(iArr4, iArr5, iArr4);
        t40.g1(3, iArr4, iArr5);
        t40.D0(iArr5, iArr3, iArr5);
        int[] iArr6 = new int[5];
        t40.g1(14, iArr5, iArr6);
        t40.D0(iArr6, iArr4, iArr6);
        t40.g1(31, iArr6, iArr4);
        t40.D0(iArr4, iArr6, iArr4);
        t40.g1(62, iArr4, iArr6);
        t40.D0(iArr6, iArr4, iArr6);
        t40.g1(3, iArr6, iArr4);
        t40.D0(iArr4, iArr3, iArr4);
        t40.g1(18, iArr4, iArr4);
        t40.D0(iArr4, iArr5, iArr4);
        t40.g1(2, iArr4, iArr4);
        t40.D0(iArr4, iArr, iArr4);
        t40.g1(3, iArr4, iArr4);
        t40.D0(iArr4, iArr2, iArr4);
        t40.g1(6, iArr4, iArr4);
        t40.D0(iArr4, iArr3, iArr4);
        t40.g1(2, iArr4, iArr4);
        t40.D0(iArr4, iArr, iArr4);
        t40.f1(iArr4, iArr2);
        if (ma0.x(iArr, iArr2)) {
            return new qs0(iArr4);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[5];
        t40.f1(this.U, iArr);
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[5];
        ma0.j(t40.l, this.U, iArr);
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return ma0.N(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return ma0.O(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[5];
        t40.l1(this.U, ((qs0) l21Var).U, iArr);
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[5];
        if (ma0.a(this.U, ((qs0) l21Var).U, iArr) != 0 || (iArr[4] == -1 && ma0.F(iArr, t40.l))) {
            fa0.c(5, 21389, iArr);
        }
        return new qs0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof qs0) {
            return ma0.x(this.U, ((qs0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return ma0.C(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[5];
        if (fa0.Y(5, this.U, iArr) != 0 || (iArr[4] == -1 && ma0.F(iArr, t40.l))) {
            fa0.c(5, 21389, iArr);
        }
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return ma0.v0(this.U);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 5);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[5];
        t40.D0(this.U, ((qs0) l21Var).U, iArr);
        return new qs0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[5];
        int[] iArr2 = this.U;
        int iX0 = t40.x0(iArr2);
        int[] iArr3 = t40.l;
        if (iX0 != 0) {
            ma0.t0(iArr3, iArr3, iArr);
        } else {
            ma0.t0(iArr3, iArr2, iArr);
        }
        return new qs0(iArr);
    }

    public qs0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
