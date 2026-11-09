package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ht0 extends zq {
    public final long[] U;

    public ht0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 113) {
            throw new IllegalArgumentException("x value invalid for SecT113FieldElement");
        }
        this.U = fa0.L(113, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 113;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = this.U;
        long jL0 = qm.L0(jArr[0]);
        long jL02 = qm.L0(jArr[1]);
        long j = (jL0 >>> 32) | (jL02 & (-4294967296L));
        return new ht0(new long[]{((j << 57) ^ ((4294967295L & jL0) | (jL02 << 32))) ^ (j << 5), (j >>> 59) ^ (j >>> 7)});
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[2];
        long[] jArr2 = new long[4];
        qm.o(2, this.U, jArr2);
        ma0.l0(jArr2, jArr);
        return new ht0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[2];
        long[] jArr2 = this.U;
        for (int i = 0; i < 2; i++) {
            if (jArr2[i] != 0) {
                long[] jArr3 = new long[2];
                long[] jArr4 = new long[2];
                long[] jArr5 = new long[4];
                qm.o(2, jArr2, jArr5);
                ma0.l0(jArr5, jArr3);
                ma0.Z(jArr3, jArr2, jArr3);
                long[] jArr6 = new long[4];
                qm.o(2, jArr3, jArr6);
                ma0.l0(jArr6, jArr3);
                ma0.Z(jArr3, jArr2, jArr3);
                ma0.s0(3, jArr3, jArr4);
                ma0.Z(jArr4, jArr3, jArr4);
                long[] jArr7 = new long[4];
                qm.o(2, jArr4, jArr7);
                ma0.l0(jArr7, jArr4);
                ma0.Z(jArr4, jArr2, jArr4);
                ma0.s0(7, jArr4, jArr3);
                ma0.Z(jArr3, jArr4, jArr3);
                ma0.s0(14, jArr3, jArr4);
                ma0.Z(jArr4, jArr3, jArr4);
                ma0.s0(28, jArr4, jArr3);
                ma0.Z(jArr3, jArr4, jArr3);
                ma0.s0(56, jArr3, jArr4);
                ma0.Z(jArr4, jArr3, jArr4);
                long[] jArr8 = new long[4];
                qm.o(2, jArr4, jArr8);
                ma0.l0(jArr8, jArr);
                return new ht0(jArr);
            }
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((ht0) l21Var).U;
        long[] jArr2 = ((ht0) l21Var2).U;
        long[] jArr3 = new long[4];
        long[] jArr4 = new long[4];
        qm.o(2, this.U, jArr4);
        ma0.d(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        ma0.G(jArr, jArr2, jArr5);
        ma0.d(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[2];
        ma0.l0(jArr3, jArr6);
        return new ht0(jArr6);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        long[] jArr = this.U;
        return jArr[0] == 1 && jArr[1] == 0;
    }

    @Override // defpackage.l21
    public final l21 b1(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArr = new long[2];
        ma0.s0(i, this.U, jArr);
        return new ht0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        long[] jArr = this.U;
        for (int i = 0; i < 2; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((ht0) l21Var).U;
        long[] jArr2 = this.U;
        return new ht0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ht0)) {
            return false;
        }
        long[] jArr = ((ht0) obj).U;
        for (int i = 1; i >= 0; i--) {
            if (this.U[i] != jArr[i]) {
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
        return new ht0(new long[]{jArr[0] ^ 1, jArr[1]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 2; i++) {
            long j = this.U[i];
            if (j != 0) {
                ma0.S(j, bArr, (1 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public final int hashCode() {
        return z91.G(this.U, 2) ^ 113009;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[4];
        long[] jArr2 = this.U;
        long[] jArr3 = {jArr2[0], jArr2[1]};
        for (int i = 1; i < 113; i += 2) {
            qm.o(2, jArr3, jArr);
            ma0.l0(jArr, jArr3);
            qm.o(2, jArr3, jArr);
            ma0.l0(jArr, jArr3);
            jArr3[0] = jArr3[0] ^ jArr2[0];
            jArr3[1] = jArr3[1] ^ jArr2[1];
        }
        return new ht0(jArr3);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[2];
        ma0.Z(this.U, ((ht0) l21Var).U, jArr);
        return new ht0(jArr);
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
        long[] jArr = ((ht0) l21Var).U;
        long[] jArr2 = ((ht0) l21Var2).U;
        long[] jArr3 = ((ht0) l21Var3).U;
        long[] jArr4 = new long[4];
        long[] jArr5 = new long[8];
        ma0.G(this.U, jArr, jArr5);
        ma0.d(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        ma0.G(jArr2, jArr3, jArr6);
        ma0.d(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[2];
        ma0.l0(jArr4, jArr7);
        return new ht0(jArr7);
    }

    public ht0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
