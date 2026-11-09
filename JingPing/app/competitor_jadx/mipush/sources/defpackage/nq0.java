package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class nq0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"));
    public final int[] U;

    public nq0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        int[] iArrK = fa0.K(bigInteger);
        if ((iArrK[7] >>> 1) >= Integer.MAX_VALUE) {
            int[] iArr = qm.i;
            if (fa0.U(iArrK, iArr)) {
                fa0.H0(iArr, iArrK);
            }
        }
        this.U = iArrK;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[8];
        ma0.j(qm.i, ((nq0) l21Var).U, iArr);
        qm.W(iArr, this.U, iArr);
        return new nq0(iArr);
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
        qm.u0(iArr, iArr2);
        qm.W(iArr2, iArr, iArr2);
        int[] iArr3 = new int[8];
        qm.z0(2, iArr2, iArr3);
        qm.W(iArr3, iArr2, iArr3);
        int[] iArr4 = new int[8];
        qm.z0(2, iArr3, iArr4);
        qm.W(iArr4, iArr2, iArr4);
        qm.z0(6, iArr4, iArr2);
        qm.W(iArr2, iArr4, iArr2);
        int[] iArr5 = new int[8];
        qm.z0(12, iArr2, iArr5);
        qm.W(iArr5, iArr2, iArr5);
        qm.z0(6, iArr5, iArr2);
        qm.W(iArr2, iArr4, iArr2);
        qm.u0(iArr2, iArr4);
        qm.W(iArr4, iArr, iArr4);
        qm.z0(31, iArr4, iArr5);
        qm.W(iArr5, iArr4, iArr2);
        qm.z0(32, iArr5, iArr5);
        qm.W(iArr5, iArr2, iArr5);
        qm.z0(62, iArr5, iArr5);
        qm.W(iArr5, iArr2, iArr5);
        qm.z0(4, iArr5, iArr5);
        qm.W(iArr5, iArr3, iArr5);
        qm.z0(32, iArr5, iArr5);
        qm.W(iArr5, iArr, iArr5);
        qm.z0(62, iArr5, iArr5);
        qm.u0(iArr5, iArr3);
        if (fa0.E(iArr, iArr3)) {
            return new nq0(iArr5);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[8];
        qm.u0(this.U, iArr);
        return new nq0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[8];
        ma0.j(qm.i, this.U, iArr);
        return new nq0(iArr);
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
        qm.E0(this.U, ((nq0) l21Var).U, iArr);
        return new nq0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[8];
        qm.a(this.U, ((nq0) l21Var).U, iArr);
        return new nq0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof nq0) {
            return fa0.E(this.U, ((nq0) obj).U);
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
        if (fa0.Y(8, this.U, iArr) != 0 || ((iArr[7] >>> 1) >= Integer.MAX_VALUE && fa0.U(iArr, qm.i))) {
            qm.d(iArr);
        }
        return new nq0(iArr);
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
        qm.W(this.U, ((nq0) l21Var).U, iArr);
        return new nq0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[8];
        int[] iArr2 = this.U;
        int iQ = qm.Q(iArr2);
        int[] iArr3 = qm.i;
        if (iQ != 0) {
            fa0.C0(iArr3, iArr3, iArr);
        } else {
            fa0.C0(iArr3, iArr2, iArr);
        }
        return new nq0(iArr);
    }

    public nq0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
