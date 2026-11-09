package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class bk extends ar {
    public static final BigInteger V = fa0.K0(qm.c);
    public static final int[] W = {1242472624, -991028441, -1389370248, 792926214, 1039914919, 726466713, 1338105611, 730014848};
    public int[] U;

    public bk(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for Curve25519FieldElement");
        }
        int[] iArrK = fa0.K(bigInteger);
        while (true) {
            int[] iArr = qm.c;
            if (!fa0.U(iArrK, iArr)) {
                this.U = iArrK;
                return;
            }
            fa0.H0(iArr, iArrK);
        }
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[8];
        ma0.j(qm.c, ((bk) l21Var).U, iArr);
        qm.U(iArr, this.U, iArr);
        return new bk(iArr);
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
        int[] iArr2 = new int[8];
        qm.t0(iArr, iArr2);
        qm.U(iArr2, iArr, iArr2);
        qm.t0(iArr2, iArr2);
        qm.U(iArr2, iArr, iArr2);
        int[] iArr3 = new int[8];
        qm.t0(iArr2, iArr3);
        qm.U(iArr3, iArr, iArr3);
        int[] iArr4 = new int[8];
        qm.x0(3, iArr3, iArr4);
        qm.U(iArr4, iArr2, iArr4);
        qm.x0(4, iArr4, iArr2);
        qm.U(iArr2, iArr3, iArr2);
        qm.x0(4, iArr2, iArr4);
        qm.U(iArr4, iArr3, iArr4);
        qm.x0(15, iArr4, iArr3);
        qm.U(iArr3, iArr4, iArr3);
        qm.x0(30, iArr3, iArr4);
        qm.U(iArr4, iArr3, iArr4);
        qm.x0(60, iArr4, iArr3);
        qm.U(iArr3, iArr4, iArr3);
        qm.x0(11, iArr3, iArr4);
        qm.U(iArr4, iArr2, iArr4);
        qm.x0(120, iArr4, iArr2);
        qm.U(iArr2, iArr3, iArr2);
        qm.t0(iArr2, iArr2);
        qm.t0(iArr2, iArr3);
        if (fa0.E(iArr, iArr3)) {
            return new bk(iArr2);
        }
        qm.U(iArr2, W, iArr2);
        qm.t0(iArr2, iArr3);
        if (fa0.E(iArr, iArr3)) {
            return new bk(iArr2);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[8];
        qm.t0(this.U, iArr);
        return new bk(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[8];
        ma0.j(qm.c, this.U, iArr);
        return new bk(iArr);
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
        qm.D0(this.U, ((bk) l21Var).U, iArr);
        return new bk(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[8];
        fa0.b(this.U, ((bk) l21Var).U, iArr);
        if (fa0.U(iArr, qm.c)) {
            qm.C0(iArr);
        }
        return new bk(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof bk) {
            return fa0.E(this.U, ((bk) obj).U);
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
        fa0.Y(8, this.U, iArr);
        if (fa0.U(iArr, qm.c)) {
            qm.C0(iArr);
        }
        return new bk(iArr);
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
        qm.U(this.U, ((bk) l21Var).U, iArr);
        return new bk(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[8];
        int[] iArr2 = this.U;
        int iP = qm.P(iArr2);
        int[] iArr3 = qm.c;
        if (iP != 0) {
            fa0.C0(iArr3, iArr3, iArr);
        } else {
            fa0.C0(iArr3, iArr2, iArr);
        }
        return new bk(iArr);
    }

    public bk(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
