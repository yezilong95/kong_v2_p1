package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class zt0 extends zq {
    public final long[] U;

    public zt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 283) {
            throw new IllegalArgumentException("x value invalid for SecT283FieldElement");
        }
        this.U = fa0.L(283, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 283;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[5];
        long[] jArr2 = this.U;
        long jL0 = qm.L0(jArr2[0]);
        long jL02 = qm.L0(jArr2[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long jL03 = qm.L0(jArr2[2]);
        long jL04 = qm.L0(jArr2[3]);
        long j2 = (jL03 & 4294967295L) | (jL04 << 32);
        long jL05 = qm.L0(jArr2[4]);
        l21.n0(new long[]{(jL0 >>> 32) | (jL02 & (-4294967296L)), (jL03 >>> 32) | (jL04 & (-4294967296L)), jL05 >>> 32, 0, 0}, l21.R, jArr);
        jArr[0] = jArr[0] ^ j;
        jArr[1] = jArr[1] ^ j2;
        jArr[2] = jArr[2] ^ (4294967295L & jL05);
        return new zt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[5];
        l21.W0(this.U, jArr);
        return new zt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[5];
        long[] jArr2 = this.U;
        for (int i = 0; i < 5; i++) {
            if (jArr2[i] != 0) {
                long[] jArr3 = new long[5];
                long[] jArr4 = new long[5];
                l21.W0(jArr2, jArr3);
                l21.n0(jArr3, jArr2, jArr3);
                l21.Z0(2, jArr3, jArr4);
                l21.n0(jArr4, jArr3, jArr4);
                l21.Z0(4, jArr4, jArr3);
                l21.n0(jArr3, jArr4, jArr3);
                l21.Z0(8, jArr3, jArr4);
                l21.n0(jArr4, jArr3, jArr4);
                l21.W0(jArr4, jArr4);
                l21.n0(jArr4, jArr2, jArr4);
                l21.Z0(17, jArr4, jArr3);
                l21.n0(jArr3, jArr4, jArr3);
                l21.W0(jArr3, jArr3);
                l21.n0(jArr3, jArr2, jArr3);
                l21.Z0(35, jArr3, jArr4);
                l21.n0(jArr4, jArr3, jArr4);
                l21.Z0(70, jArr4, jArr3);
                l21.n0(jArr3, jArr4, jArr3);
                l21.W0(jArr3, jArr3);
                l21.n0(jArr3, jArr2, jArr3);
                l21.Z0(141, jArr3, jArr4);
                l21.n0(jArr4, jArr3, jArr4);
                l21.W0(jArr4, jArr);
                return new zt0(jArr);
            }
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((zt0) l21Var).U;
        long[] jArr2 = ((zt0) l21Var2).U;
        long[] jArr3 = new long[9];
        long[] jArr4 = new long[9];
        l21.V(this.U, jArr4);
        l21.g(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[10];
        l21.S(jArr, jArr2, jArr5);
        l21.g(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[5];
        l21.I0(jArr3, jArr6);
        return new zt0(jArr6);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        long[] jArr = this.U;
        if (jArr[0] == 1) {
            for (int i = 1; i < 5; i++) {
                if (jArr[i] == 0) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // defpackage.l21
    public final l21 b1(int i) {
        if (i < 1) {
            return this;
        }
        long[] jArr = new long[5];
        l21.Z0(i, this.U, jArr);
        return new zt0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        long[] jArr = this.U;
        for (int i = 0; i < 5; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((zt0) l21Var).U;
        long[] jArr2 = this.U;
        return new zt0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2], jArr2[3] ^ jArr[3], jArr2[4] ^ jArr[4]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zt0)) {
            return false;
        }
        long[] jArr = ((zt0) obj).U;
        for (int i = 4; i >= 0; i--) {
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
        return new zt0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2], jArr[3], jArr[4]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        byte[] bArr = new byte[40];
        for (int i = 0; i < 5; i++) {
            long j = this.U[i];
            if (j != 0) {
                ma0.S(j, bArr, (4 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public final int hashCode() {
        return z91.G(this.U, 5) ^ 2831275;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[9];
        long[] jArr2 = this.U;
        long[] jArr3 = {jArr2[0], jArr2[1], jArr2[2], jArr2[3], jArr2[4]};
        for (int i = 1; i < 283; i += 2) {
            l21.V(jArr3, jArr);
            l21.I0(jArr, jArr3);
            l21.V(jArr3, jArr);
            l21.I0(jArr, jArr3);
            jArr3[0] = jArr3[0] ^ jArr2[0];
            jArr3[1] = jArr3[1] ^ jArr2[1];
            jArr3[2] = jArr3[2] ^ jArr2[2];
            jArr3[3] = jArr3[3] ^ jArr2[3];
            jArr3[4] = jArr3[4] ^ jArr2[4];
        }
        return new zt0(jArr3);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[5];
        l21.n0(this.U, ((zt0) l21Var).U, jArr);
        return new zt0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        return ((int) ((jArr[4] >>> 15) ^ jArr[0])) & 1;
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((zt0) l21Var).U;
        long[] jArr2 = ((zt0) l21Var2).U;
        long[] jArr3 = ((zt0) l21Var3).U;
        long[] jArr4 = new long[9];
        long[] jArr5 = new long[10];
        l21.S(this.U, jArr, jArr5);
        l21.g(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[10];
        l21.S(jArr2, jArr3, jArr6);
        l21.g(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[5];
        l21.I0(jArr4, jArr7);
        return new zt0(jArr7);
    }

    public zt0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
