package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ss0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFEE37"));
    public final int[] U;

    public ss0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192K1FieldElement");
        }
        int[] iArrY = o80.y(bigInteger);
        if (iArrY[5] == -1) {
            int[] iArr = l21.M;
            if (o80.D(iArrY, iArr)) {
                o80.f0(iArr, iArrY);
            }
        }
        this.U = iArrY;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[6];
        ma0.j(l21.M, ((ss0) l21Var).U, iArr);
        l21.l0(iArr, this.U, iArr);
        return new ss0(iArr);
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
        l21.V0(iArr, iArr2);
        l21.l0(iArr2, iArr, iArr2);
        int[] iArr3 = new int[6];
        l21.V0(iArr2, iArr3);
        l21.l0(iArr3, iArr, iArr3);
        int[] iArr4 = new int[6];
        l21.X0(3, iArr3, iArr4);
        l21.l0(iArr4, iArr3, iArr4);
        l21.X0(2, iArr4, iArr4);
        l21.l0(iArr4, iArr2, iArr4);
        l21.X0(8, iArr4, iArr2);
        l21.l0(iArr2, iArr4, iArr2);
        l21.X0(3, iArr2, iArr4);
        l21.l0(iArr4, iArr3, iArr4);
        int[] iArr5 = new int[6];
        l21.X0(16, iArr4, iArr5);
        l21.l0(iArr5, iArr2, iArr5);
        l21.X0(35, iArr5, iArr2);
        l21.l0(iArr2, iArr5, iArr2);
        l21.X0(70, iArr2, iArr5);
        l21.l0(iArr5, iArr2, iArr5);
        l21.X0(19, iArr5, iArr2);
        l21.l0(iArr2, iArr4, iArr2);
        l21.X0(20, iArr2, iArr2);
        l21.l0(iArr2, iArr4, iArr2);
        l21.X0(4, iArr2, iArr2);
        l21.l0(iArr2, iArr3, iArr2);
        l21.X0(6, iArr2, iArr2);
        l21.l0(iArr2, iArr3, iArr2);
        l21.V0(iArr2, iArr2);
        l21.V0(iArr2, iArr3);
        if (o80.x(iArr, iArr3)) {
            return new ss0(iArr2);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[6];
        l21.V0(this.U, iArr);
        return new ss0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[6];
        ma0.j(l21.M, this.U, iArr);
        return new ss0(iArr);
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
        l21.e1(this.U, ((ss0) l21Var).U, iArr);
        return new ss0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[6];
        if (o80.a(this.U, ((ss0) l21Var).U, iArr) != 0 || (iArr[5] == -1 && o80.D(iArr, l21.M))) {
            fa0.c(6, 4553, iArr);
        }
        return new ss0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ss0) {
            return o80.x(this.U, ((ss0) obj).U);
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
        if (fa0.Y(6, this.U, iArr) != 0 || (iArr[5] == -1 && o80.D(iArr, l21.M))) {
            fa0.c(6, 4553, iArr);
        }
        return new ss0(iArr);
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
        l21.l0(this.U, ((ss0) l21Var).U, iArr);
        return new ss0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[6];
        int[] iArr2 = this.U;
        int iC0 = l21.c0(iArr2);
        int[] iArr3 = l21.M;
        if (iC0 != 0) {
            o80.d0(iArr3, iArr3, iArr);
        } else {
            o80.d0(iArr3, iArr2, iArr);
        }
        return new ss0(iArr);
    }

    public ss0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
