package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ut0 extends zq {
    public final long[] U;

    public ut0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.U = fa0.L(233, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 233;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[4];
        long[] jArr2 = this.U;
        long jL0 = qm.L0(jArr2[0]);
        long jL02 = qm.L0(jArr2[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long j2 = (jL0 >>> 32) | (jL02 & (-4294967296L));
        long jL03 = qm.L0(jArr2[2]);
        long jL04 = qm.L0(jArr2[3]);
        long j3 = (4294967295L & jL03) | (jL04 << 32);
        long j4 = (jL03 >>> 32) | (jL04 & (-4294967296L));
        long j5 = j4 >>> 27;
        long j6 = j4 ^ ((j2 >>> 27) | (j4 << 37));
        long j7 = j2 ^ (j2 << 37);
        long[] jArr3 = new long[8];
        int[] iArr = {32, 117, 191};
        int i = 0;
        for (int i2 = 3; i < i2; i2 = 3) {
            int i3 = iArr[i];
            int i4 = i3 >>> 6;
            int i5 = i3 & 63;
            jArr3[i4] = jArr3[i4] ^ (j7 << i5);
            int i6 = i4 + 1;
            int i7 = -i5;
            jArr3[i6] = jArr3[i6] ^ ((j6 << i5) | (j7 >>> i7));
            int i8 = i4 + 2;
            jArr3[i8] = jArr3[i8] ^ ((j5 << i5) | (j6 >>> i7));
            int i9 = i4 + 3;
            jArr3[i9] = jArr3[i9] ^ (j5 >>> i7);
            i++;
        }
        s90.Q(jArr3, jArr);
        jArr[0] = jArr[0] ^ j;
        jArr[1] = jArr[1] ^ j3;
        return new ut0(jArr);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[4];
        s90.Z(this.U, jArr);
        return new ut0(jArr);
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
        s90.Z(jArr2, jArr3);
        s90.N(jArr3, jArr2, jArr3);
        s90.Z(jArr3, jArr3);
        s90.N(jArr3, jArr2, jArr3);
        s90.a0(3, jArr3, jArr4);
        s90.N(jArr4, jArr3, jArr4);
        s90.Z(jArr4, jArr4);
        s90.N(jArr4, jArr2, jArr4);
        s90.a0(7, jArr4, jArr3);
        s90.N(jArr3, jArr4, jArr3);
        s90.a0(14, jArr3, jArr4);
        s90.N(jArr4, jArr3, jArr4);
        s90.Z(jArr4, jArr4);
        s90.N(jArr4, jArr2, jArr4);
        s90.a0(29, jArr4, jArr3);
        s90.N(jArr3, jArr4, jArr3);
        s90.a0(58, jArr3, jArr4);
        s90.N(jArr4, jArr3, jArr4);
        s90.a0(116, jArr4, jArr3);
        s90.N(jArr3, jArr4, jArr3);
        s90.Z(jArr3, jArr);
        return new ut0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((ut0) l21Var).U;
        long[] jArr2 = ((ut0) l21Var2).U;
        long[] jArr3 = new long[8];
        long[] jArr4 = new long[8];
        qm.o(4, this.U, jArr4);
        s90.j(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        s90.C(jArr, jArr2, jArr5);
        s90.j(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[4];
        s90.Q(jArr3, jArr6);
        return new ut0(jArr6);
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
        s90.a0(i, this.U, jArr);
        return new ut0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return fa0.j0(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((ut0) l21Var).U;
        long[] jArr2 = this.U;
        return new ut0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2], jArr2[3] ^ jArr[3]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ut0) {
            return fa0.F(this.U, ((ut0) obj).U);
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
        return new ut0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2], jArr[3]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return fa0.M0(this.U);
    }

    public final int hashCode() {
        return z91.G(this.U, 4) ^ 2330074;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[4];
        long[] jArr2 = new long[8];
        long[] jArr3 = this.U;
        fa0.w(jArr3, jArr);
        for (int i = 1; i < 233; i += 2) {
            qm.o(4, jArr, jArr2);
            s90.Q(jArr2, jArr);
            qm.o(4, jArr, jArr2);
            s90.Q(jArr2, jArr);
            jArr[0] = jArr[0] ^ jArr3[0];
            jArr[1] = jArr[1] ^ jArr3[1];
            jArr[2] = jArr[2] ^ jArr3[2];
            jArr[3] = jArr[3] ^ jArr3[3];
        }
        return new ut0(jArr);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[4];
        s90.N(this.U, ((ut0) l21Var).U, jArr);
        return new ut0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        return ((int) ((jArr[2] >>> 31) ^ jArr[0])) & 1;
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((ut0) l21Var).U;
        long[] jArr2 = ((ut0) l21Var2).U;
        long[] jArr3 = ((ut0) l21Var3).U;
        long[] jArr4 = new long[8];
        long[] jArr5 = new long[8];
        s90.C(this.U, jArr, jArr5);
        s90.j(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        s90.C(jArr2, jArr3, jArr6);
        s90.j(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[4];
        s90.Q(jArr4, jArr7);
        return new ut0(jArr7);
    }

    public ut0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
