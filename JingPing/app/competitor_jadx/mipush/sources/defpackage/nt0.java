package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class nt0 extends zq {
    public final long[] U;

    public nt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 163) {
            throw new IllegalArgumentException("x value invalid for SecT163FieldElement");
        }
        this.U = fa0.L(163, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 163;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[3];
        long[] jArr2 = this.U;
        long jL0 = qm.L0(jArr2[0]);
        long jL02 = qm.L0(jArr2[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long jL03 = qm.L0(jArr2[2]);
        t40.F0(new long[]{(jL0 >>> 32) | (jL02 & (-4294967296L)), jL03 >>> 32, 0}, t40.r, jArr);
        jArr[0] = jArr[0] ^ j;
        jArr[1] = jArr[1] ^ (jL03 & 4294967295L);
        return new nt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[3];
        long[] jArr2 = new long[6];
        qm.o(3, this.U, jArr2);
        t40.W0(jArr2, jArr);
        return new nt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[3];
        long[] jArr2 = this.U;
        if (o80.P(jArr2)) {
            throw new IllegalStateException();
        }
        long[] jArr3 = new long[3];
        long[] jArr4 = new long[3];
        long[] jArr5 = new long[6];
        qm.o(3, jArr2, jArr5);
        t40.W0(jArr5, jArr3);
        t40.i1(1, jArr3, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(1, jArr4, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(3, jArr3, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(3, jArr4, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(9, jArr3, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(9, jArr4, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(27, jArr3, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(27, jArr4, jArr4);
        t40.F0(jArr3, jArr4, jArr3);
        t40.i1(81, jArr3, jArr4);
        t40.F0(jArr3, jArr4, jArr);
        return new nt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((nt0) l21Var).U;
        long[] jArr2 = ((nt0) l21Var2).U;
        long[] jArr3 = new long[6];
        long[] jArr4 = new long[6];
        qm.o(3, this.U, jArr4);
        t40.c(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        t40.s0(jArr, jArr2, jArr5);
        t40.c(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[3];
        t40.W0(jArr3, jArr6);
        return new nt0(jArr6);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return o80.M(this.U);
    }

    @Override // defpackage.l21
    public final l21 b1(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArr = new long[3];
        t40.i1(i, this.U, jArr);
        return new nt0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return o80.P(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((nt0) l21Var).U;
        long[] jArr2 = this.U;
        return new nt0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof nt0)) {
            return false;
        }
        long[] jArr = this.U;
        long[] jArr2 = ((nt0) obj).U;
        for (int i = 2; i >= 0; i--) {
            if (jArr[i] != jArr2[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return (this.U[0] & 1) != 0;
    }

    @Override // defpackage.l21
    public final l21 h() {
        long[] jArr = this.U;
        return new nt0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return o80.i0(this.U);
    }

    public final int hashCode() {
        return z91.G(this.U, 3) ^ 163763;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[6];
        long[] jArr2 = this.U;
        long[] jArr3 = {jArr2[0], jArr2[1], jArr2[2]};
        for (int i = 1; i < 163; i += 2) {
            qm.o(3, jArr3, jArr);
            t40.W0(jArr, jArr3);
            qm.o(3, jArr3, jArr);
            t40.W0(jArr, jArr3);
            jArr3[0] = jArr3[0] ^ jArr2[0];
            jArr3[1] = jArr3[1] ^ jArr2[1];
            jArr3[2] = jArr3[2] ^ jArr2[2];
        }
        return new nt0(jArr3);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[3];
        t40.F0(this.U, ((nt0) l21Var).U, jArr);
        return new nt0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        return ((int) ((jArr[2] >>> 29) ^ jArr[0])) & 1;
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((nt0) l21Var).U;
        long[] jArr2 = ((nt0) l21Var2).U;
        long[] jArr3 = ((nt0) l21Var3).U;
        long[] jArr4 = new long[6];
        long[] jArr5 = new long[8];
        t40.s0(this.U, jArr, jArr5);
        t40.c(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        t40.s0(jArr2, jArr3, jArr6);
        t40.c(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[3];
        t40.W0(jArr4, jArr7);
        return new nt0(jArr7);
    }

    public nt0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
