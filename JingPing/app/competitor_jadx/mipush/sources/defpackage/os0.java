package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class os0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF7FFFFFFF"));
    public final int[] U;

    public os0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP160R1FieldElement");
        }
        int[] iArrB = ma0.B(bigInteger);
        if (iArrB[4] == -1) {
            int[] iArr = qm.k;
            if (ma0.F(iArrB, iArr)) {
                ma0.u0(iArr, iArrB);
            }
        }
        this.U = iArrB;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[5];
        ma0.j(qm.k, ((os0) l21Var).U, iArr);
        qm.X(iArr, this.U, iArr);
        return new os0(iArr);
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
        qm.v0(iArr, iArr2);
        qm.X(iArr2, iArr, iArr2);
        int[] iArr3 = new int[5];
        qm.A0(2, iArr2, iArr3);
        qm.X(iArr3, iArr2, iArr3);
        qm.A0(4, iArr3, iArr2);
        qm.X(iArr2, iArr3, iArr2);
        qm.A0(8, iArr2, iArr3);
        qm.X(iArr3, iArr2, iArr3);
        qm.A0(16, iArr3, iArr2);
        qm.X(iArr2, iArr3, iArr2);
        qm.A0(32, iArr2, iArr3);
        qm.X(iArr3, iArr2, iArr3);
        qm.A0(64, iArr3, iArr2);
        qm.X(iArr2, iArr3, iArr2);
        qm.v0(iArr2, iArr3);
        qm.X(iArr3, iArr, iArr3);
        qm.A0(29, iArr3, iArr3);
        qm.v0(iArr3, iArr2);
        if (ma0.x(iArr, iArr2)) {
            return new os0(iArr3);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[5];
        qm.v0(this.U, iArr);
        return new os0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[5];
        ma0.j(qm.k, this.U, iArr);
        return new os0(iArr);
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
        qm.F0(this.U, ((os0) l21Var).U, iArr);
        return new os0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[5];
        if (ma0.a(this.U, ((os0) l21Var).U, iArr) != 0 || (iArr[4] == -1 && ma0.F(iArr, qm.k))) {
            fa0.l(5, -2147483647, iArr);
        }
        return new os0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof os0) {
            return ma0.x(this.U, ((os0) obj).U);
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
        if (fa0.Y(5, this.U, iArr) != 0 || (iArr[4] == -1 && ma0.F(iArr, qm.k))) {
            fa0.l(5, -2147483647, iArr);
        }
        return new os0(iArr);
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
        qm.X(this.U, ((os0) l21Var).U, iArr);
        return new os0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[5];
        int[] iArr2 = this.U;
        int iR = qm.R(iArr2);
        int[] iArr3 = qm.k;
        if (iR != 0) {
            ma0.t0(iArr3, iArr3, iArr);
        } else {
            ma0.t0(iArr3, iArr2, iArr);
        }
        return new os0(iArr);
    }

    public os0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
