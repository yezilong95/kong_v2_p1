package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class us0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFF"));
    public final int[] U;

    public us0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        int[] iArrY = o80.y(bigInteger);
        if (iArrY[5] == -1) {
            int[] iArr = j31.D;
            if (o80.D(iArrY, iArr)) {
                o80.f0(iArr, iArrY);
            }
        }
        this.U = iArrY;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[6];
        ma0.j(j31.D, ((us0) l21Var).U, iArr);
        j31.D(iArr, this.U, iArr);
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int[] iArr = this.U;
        if (o80.O(iArr) || o80.L(iArr)) {
            return this;
        }
        int[] iArr2 = new int[6];
        int[] iArr3 = new int[6];
        j31.a0(iArr, iArr2);
        j31.D(iArr2, iArr, iArr2);
        j31.c0(2, iArr2, iArr3);
        j31.D(iArr3, iArr2, iArr3);
        j31.c0(4, iArr3, iArr2);
        j31.D(iArr2, iArr3, iArr2);
        j31.c0(8, iArr2, iArr3);
        j31.D(iArr3, iArr2, iArr3);
        j31.c0(16, iArr3, iArr2);
        j31.D(iArr2, iArr3, iArr2);
        j31.c0(32, iArr2, iArr3);
        j31.D(iArr3, iArr2, iArr3);
        j31.c0(64, iArr3, iArr2);
        j31.D(iArr2, iArr3, iArr2);
        j31.c0(62, iArr2, iArr2);
        j31.a0(iArr2, iArr3);
        if (o80.x(iArr, iArr3)) {
            return new us0(iArr2);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[6];
        j31.a0(this.U, iArr);
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[6];
        ma0.j(j31.D, this.U, iArr);
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return o80.L(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return o80.O(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[6];
        j31.i0(this.U, ((us0) l21Var).U, iArr);
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[6];
        if (o80.a(this.U, ((us0) l21Var).U, iArr) != 0 || (iArr[5] == -1 && o80.D(iArr, j31.D))) {
            j31.e(iArr);
        }
        return new us0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof us0) {
            return o80.x(this.U, ((us0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return o80.z(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[6];
        if (fa0.Y(6, this.U, iArr) != 0 || (iArr[5] == -1 && o80.D(iArr, j31.D))) {
            j31.e(iArr);
        }
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return o80.h0(this.U);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 6);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[6];
        j31.D(this.U, ((us0) l21Var).U, iArr);
        return new us0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[6];
        int[] iArr2 = this.U;
        int iA = j31.A(iArr2);
        int[] iArr3 = j31.D;
        if (iA != 0) {
            o80.d0(iArr3, iArr3, iArr);
        } else {
            o80.d0(iArr3, iArr2, iArr);
        }
        return new us0(iArr);
    }

    public us0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
