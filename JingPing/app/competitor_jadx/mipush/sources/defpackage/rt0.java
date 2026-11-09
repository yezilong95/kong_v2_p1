package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class rt0 extends zq {
    public final long[] U;

    public rt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.U = fa0.L(193, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 193;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = this.U;
        long jL0 = qm.L0(jArr[0]);
        long jL02 = qm.L0(jArr[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long j2 = (jL0 >>> 32) | (jL02 & (-4294967296L));
        long jL03 = qm.L0(jArr[2]);
        long j3 = jL03 >>> 32;
        return new rt0(new long[]{j ^ (j2 << 8), ((((4294967295L & jL03) ^ (jArr[3] << 32)) ^ (j3 << 8)) ^ (j2 >>> 56)) ^ (j2 << 33), (j2 >>> 31) ^ (j3 << 33), jL03 >>> 63});
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[4];
        long[] jArr2 = new long[8];
        o80.H(this.U, jArr2);
        o80.Z(jArr2, jArr);
        return new rt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[4];
        long[] jArr2 = this.U;
        if (fa0.j0(jArr2)) {
            throw new IllegalStateException();
        }
        long[] jArr3 = new long[4];
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[8];
        o80.H(jArr2, jArr5);
        o80.Z(jArr5, jArr3);
        o80.c0(1, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(1, jArr4, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(3, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(6, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(12, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(24, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(48, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr3);
        o80.c0(96, jArr3, jArr4);
        o80.V(jArr3, jArr4, jArr);
        return new rt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((rt0) l21Var).U;
        long[] jArr2 = ((rt0) l21Var2).U;
        long[] jArr3 = new long[8];
        long[] jArr4 = new long[8];
        o80.H(this.U, jArr4);
        o80.e(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        o80.F(jArr, jArr2, jArr5);
        o80.e(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[4];
        o80.Z(jArr3, jArr6);
        return new rt0(jArr6);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return fa0.g0(this.U);
    }

    @Override // defpackage.l21
    public final l21 b1(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArr = new long[4];
        o80.c0(i, this.U, jArr);
        return new rt0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.j0(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((rt0) l21Var).U;
        long[] jArr2 = this.U;
        return new rt0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2], jArr2[3] ^ jArr[3]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof rt0) {
            return fa0.F(this.U, ((rt0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return (this.U[0] & 1) != 0;
    }

    @Override // defpackage.l21
    public final l21 h() {
        long[] jArr = this.U;
        return new rt0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2], jArr[3]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.M0(this.U);
    }

    public final int hashCode() {
        return z91.G(this.U, 4) ^ 1930015;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[4];
        long[] jArr2 = new long[8];
        long[] jArr3 = this.U;
        fa0.w(jArr3, jArr);
        for (int i = 1; i < 193; i += 2) {
            o80.H(jArr, jArr2);
            o80.Z(jArr2, jArr);
            o80.H(jArr, jArr2);
            o80.Z(jArr2, jArr);
            jArr[0] = jArr[0] ^ jArr3[0];
            jArr[1] = jArr[1] ^ jArr3[1];
            jArr[2] = jArr[2] ^ jArr3[2];
            jArr[3] = jArr[3] ^ jArr3[3];
        }
        return new rt0(jArr);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[4];
        o80.V(this.U, ((rt0) l21Var).U, jArr);
        return new rt0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        return ((int) this.U[0]) & 1;
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((rt0) l21Var).U;
        long[] jArr2 = ((rt0) l21Var2).U;
        long[] jArr3 = ((rt0) l21Var3).U;
        long[] jArr4 = new long[8];
        long[] jArr5 = new long[8];
        o80.F(this.U, jArr, jArr5);
        o80.e(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        o80.F(jArr2, jArr3, jArr6);
        o80.e(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[4];
        o80.Z(jArr4, jArr7);
        return new rt0(jArr7);
    }

    public rt0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
