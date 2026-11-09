package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class xt0 extends zq {
    public final long[] U;

    public xt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 239) {
            throw new IllegalArgumentException("x value invalid for SecT239FieldElement");
        }
        this.U = fa0.L(239, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 239;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[4];
        long[] jArr2 = this.U;
        boolean z = false;
        long jL0 = qm.L0(jArr2[0]);
        boolean z2 = true;
        long jL02 = qm.L0(jArr2[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long j2 = (jL0 >>> 32) | (jL02 & (-4294967296L));
        long jL03 = qm.L0(jArr2[2]);
        long jL04 = qm.L0(jArr2[3]);
        long j3 = (jL03 & 4294967295L) | (jL04 << 32);
        long j4 = (jL03 >>> 32) | (jL04 & (-4294967296L));
        long j5 = j4 >>> 49;
        long j6 = (j2 >>> 49) | (j4 << 15);
        long j7 = j4 ^ (j2 << 15);
        long[] jArr3 = new long[8];
        int[] iArr = {39, 120};
        int i = 0;
        while (i < 2) {
            int i2 = iArr[i];
            int i3 = i2 >>> 6;
            boolean z3 = z;
            int i4 = i2 & 63;
            jArr3[i3] = jArr3[i3] ^ (j2 << i4);
            int i5 = i3 + 1;
            boolean z4 = z2;
            int i6 = -i4;
            jArr3[i5] = jArr3[i5] ^ ((j7 << i4) | (j2 >>> i6));
            int i7 = i3 + 2;
            jArr3[i7] = jArr3[i7] ^ ((j6 << i4) | (j7 >>> i6));
            int i8 = i3 + 3;
            jArr3[i8] = jArr3[i8] ^ ((j5 << i4) | (j6 >>> i6));
            int i9 = i3 + 4;
            jArr3[i9] = jArr3[i9] ^ (j5 >>> i6);
            i++;
            z = z3;
            z2 = z4;
        }
        boolean z5 = z;
        boolean z6 = z2;
        fa0.s0(jArr3, jArr);
        jArr[z5 ? 1 : 0] = jArr[z5 ? 1 : 0] ^ j;
        jArr[z6 ? 1 : 0] = jArr[z6 ? 1 : 0] ^ j3;
        return new xt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[4];
        fa0.z0(this.U, jArr);
        return new xt0(jArr);
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
        fa0.z0(jArr2, jArr3);
        fa0.o0(jArr3, jArr2, jArr3);
        fa0.z0(jArr3, jArr3);
        fa0.o0(jArr3, jArr2, jArr3);
        fa0.A0(3, jArr3, jArr4);
        fa0.o0(jArr4, jArr3, jArr4);
        fa0.z0(jArr4, jArr4);
        fa0.o0(jArr4, jArr2, jArr4);
        fa0.A0(7, jArr4, jArr3);
        fa0.o0(jArr3, jArr4, jArr3);
        fa0.A0(14, jArr3, jArr4);
        fa0.o0(jArr4, jArr3, jArr4);
        fa0.z0(jArr4, jArr4);
        fa0.o0(jArr4, jArr2, jArr4);
        fa0.A0(29, jArr4, jArr3);
        fa0.o0(jArr3, jArr4, jArr3);
        fa0.z0(jArr3, jArr3);
        fa0.o0(jArr3, jArr2, jArr3);
        fa0.A0(59, jArr3, jArr4);
        fa0.o0(jArr4, jArr3, jArr4);
        fa0.z0(jArr4, jArr4);
        fa0.o0(jArr4, jArr2, jArr4);
        fa0.A0(119, jArr4, jArr3);
        fa0.o0(jArr3, jArr4, jArr3);
        fa0.z0(jArr3, jArr);
        return new xt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((xt0) l21Var).U;
        long[] jArr2 = ((xt0) l21Var2).U;
        long[] jArr3 = new long[8];
        long[] jArr4 = new long[8];
        qm.o(4, this.U, jArr4);
        fa0.f(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        fa0.W(jArr, jArr2, jArr5);
        fa0.f(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[4];
        fa0.s0(jArr3, jArr6);
        return new xt0(jArr6);
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
        fa0.A0(i, this.U, jArr);
        return new xt0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.j0(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((xt0) l21Var).U;
        long[] jArr2 = this.U;
        return new xt0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2], jArr2[3] ^ jArr[3]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof xt0) {
            return fa0.F(this.U, ((xt0) obj).U);
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
        return new xt0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2], jArr[3]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.M0(this.U);
    }

    public final int hashCode() {
        return z91.G(this.U, 4) ^ 23900158;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[4];
        long[] jArr2 = new long[8];
        long[] jArr3 = this.U;
        fa0.w(jArr3, jArr);
        for (int i = 1; i < 239; i += 2) {
            qm.o(4, jArr, jArr2);
            fa0.s0(jArr2, jArr);
            qm.o(4, jArr, jArr2);
            fa0.s0(jArr2, jArr);
            jArr[0] = jArr[0] ^ jArr3[0];
            jArr[1] = jArr[1] ^ jArr3[1];
            jArr[2] = jArr[2] ^ jArr3[2];
            jArr[3] = jArr[3] ^ jArr3[3];
        }
        return new xt0(jArr);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[4];
        fa0.o0(this.U, ((xt0) l21Var).U, jArr);
        return new xt0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        return 1 & ((int) ((jArr[0] ^ (jArr[1] >>> 17)) ^ (jArr[2] >>> 34)));
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((xt0) l21Var).U;
        long[] jArr2 = ((xt0) l21Var2).U;
        long[] jArr3 = ((xt0) l21Var3).U;
        long[] jArr4 = new long[8];
        long[] jArr5 = new long[8];
        fa0.W(this.U, jArr, jArr5);
        fa0.f(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        fa0.W(jArr2, jArr3, jArr6);
        fa0.f(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[4];
        fa0.s0(jArr4, jArr7);
        return new xt0(jArr7);
    }

    public xt0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
