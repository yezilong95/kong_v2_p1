package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class kt0 extends zq {
    public final long[] U;

    public kt0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 131) {
            throw new IllegalArgumentException("x value invalid for SecT131FieldElement");
        }
        this.U = fa0.L(131, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 131;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[3];
        long[] jArr2 = this.U;
        long jL0 = qm.L0(jArr2[0]);
        long jL02 = qm.L0(jArr2[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long jL03 = qm.L0(jArr2[2]);
        qm.V(new long[]{(jL0 >>> 32) | (jL02 & (-4294967296L)), jL03 >>> 32, 0}, qm.q, jArr);
        jArr[0] = jArr[0] ^ j;
        jArr[1] = jArr[1] ^ (jL03 & 4294967295L);
        return new kt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[3];
        long[] jArr2 = new long[5];
        qm.N(this.U, jArr2);
        qm.h0(jArr2, jArr);
        return new kt0(jArr);
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
        long[] jArr5 = new long[5];
        qm.N(jArr2, jArr5);
        qm.h0(jArr5, jArr3);
        qm.V(jArr3, jArr2, jArr3);
        qm.y0(2, jArr3, jArr4);
        qm.V(jArr4, jArr3, jArr4);
        qm.y0(4, jArr4, jArr3);
        qm.V(jArr3, jArr4, jArr3);
        qm.y0(8, jArr3, jArr4);
        qm.V(jArr4, jArr3, jArr4);
        qm.y0(16, jArr4, jArr3);
        qm.V(jArr3, jArr4, jArr3);
        qm.y0(32, jArr3, jArr4);
        qm.V(jArr4, jArr3, jArr4);
        long[] jArr6 = new long[5];
        qm.N(jArr4, jArr6);
        qm.h0(jArr6, jArr4);
        qm.V(jArr4, jArr2, jArr4);
        qm.y0(65, jArr4, jArr3);
        qm.V(jArr3, jArr4, jArr3);
        long[] jArr7 = new long[5];
        qm.N(jArr3, jArr7);
        qm.h0(jArr7, jArr);
        return new kt0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((kt0) l21Var).U;
        long[] jArr2 = ((kt0) l21Var2).U;
        long[] jArr3 = new long[5];
        long[] jArr4 = new long[5];
        qm.N(this.U, jArr4);
        qm.c(jArr3, jArr4, jArr3);
        long[] jArr5 = new long[8];
        qm.L(jArr, jArr2, jArr5);
        qm.c(jArr3, jArr5, jArr3);
        long[] jArr6 = new long[3];
        qm.h0(jArr3, jArr6);
        return new kt0(jArr6);
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
        qm.y0(i, this.U, jArr);
        return new kt0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return o80.P(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((kt0) l21Var).U;
        long[] jArr2 = this.U;
        return new kt0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kt0)) {
            return false;
        }
        long[] jArr = this.U;
        long[] jArr2 = ((kt0) obj).U;
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
        return new kt0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return o80.i0(this.U);
    }

    public final int hashCode() {
        return z91.G(this.U, 3) ^ 131832;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[5];
        long[] jArr2 = this.U;
        long[] jArr3 = {jArr2[0], jArr2[1], jArr2[2]};
        for (int i = 1; i < 131; i += 2) {
            qm.N(jArr3, jArr);
            qm.h0(jArr, jArr3);
            qm.N(jArr3, jArr);
            qm.h0(jArr, jArr3);
            jArr3[0] = jArr3[0] ^ jArr2[0];
            jArr3[1] = jArr3[1] ^ jArr2[1];
            jArr3[2] = jArr3[2] ^ jArr2[2];
        }
        return new kt0(jArr3);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[3];
        qm.V(this.U, ((kt0) l21Var).U, jArr);
        return new kt0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        return 1 & ((int) ((jArr[0] ^ (jArr[1] >>> 59)) ^ (jArr[2] >>> 1)));
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((kt0) l21Var).U;
        long[] jArr2 = ((kt0) l21Var2).U;
        long[] jArr3 = ((kt0) l21Var3).U;
        long[] jArr4 = new long[5];
        long[] jArr5 = new long[8];
        qm.L(this.U, jArr, jArr5);
        qm.c(jArr4, jArr5, jArr4);
        long[] jArr6 = new long[8];
        qm.L(jArr2, jArr3, jArr6);
        qm.c(jArr4, jArr6, jArr4);
        long[] jArr7 = new long[3];
        qm.h0(jArr4, jArr7);
        return new kt0(jArr7);
    }

    public kt0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
