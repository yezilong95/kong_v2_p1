package defpackage;

import io.nats.client.support.Status;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class cu0 extends zq {
    public final long[] U;

    public cu0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 409) {
            throw new IllegalArgumentException("x value invalid for SecT409FieldElement");
        }
        this.U = fa0.L(Status.CONFLICT_CODE, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return Status.CONFLICT_CODE;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = this.U;
        long jL0 = qm.L0(jArr[0]);
        long jL02 = qm.L0(jArr[1]);
        long j = (jL0 & 4294967295L) | (jL02 << 32);
        long j2 = (jL0 >>> 32) | (jL02 & (-4294967296L));
        long jL03 = qm.L0(jArr[2]);
        long jL04 = qm.L0(jArr[3]);
        long j3 = (jL03 & 4294967295L) | (jL04 << 32);
        long j4 = (jL03 >>> 32) | (jL04 & (-4294967296L));
        long jL05 = qm.L0(jArr[4]);
        long jL06 = qm.L0(jArr[5]);
        long j5 = (jL05 & 4294967295L) | (jL06 << 32);
        long j6 = (jL05 >>> 32) | (jL06 & (-4294967296L));
        long jL07 = qm.L0(jArr[6]);
        long j7 = jL07 >>> 32;
        return new cu0(new long[]{j ^ (j2 << 44), (j3 ^ (j4 << 44)) ^ (j2 >>> 20), (j5 ^ (j6 << 44)) ^ (j4 >>> 20), (((jL07 & 4294967295L) ^ (j7 << 44)) ^ (j6 >>> 20)) ^ (j2 << 13), ((jL07 >>> 52) ^ (j4 << 13)) ^ (j2 >>> 51), (j6 << 13) ^ (j4 >>> 51), (j7 << 13) ^ (j6 >>> 51)});
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[7];
        long[] jArr2 = new long[13];
        la0.N(this.U, jArr2);
        la0.b0(jArr2, jArr);
        return new cu0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[7];
        long[] jArr2 = this.U;
        for (int i = 0; i < 7; i++) {
            if (jArr2[i] != 0) {
                long[] jArr3 = new long[7];
                long[] jArr4 = new long[7];
                long[] jArr5 = new long[7];
                long[] jArr6 = new long[13];
                la0.N(jArr2, jArr6);
                la0.b0(jArr6, jArr3);
                la0.j0(1, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(1, jArr4, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(3, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(6, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(12, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr5);
                la0.j0(24, jArr5, jArr3);
                la0.j0(24, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(48, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(96, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.j0(192, jArr3, jArr4);
                la0.W(jArr3, jArr4, jArr3);
                la0.W(jArr3, jArr5, jArr);
                return new cu0(jArr);
            }
        }
        throw new IllegalStateException();
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((cu0) l21Var).U;
        long[] jArr2 = ((cu0) l21Var2).U;
        long[] jArr3 = new long[13];
        long[] jArr4 = new long[13];
        la0.N(this.U, jArr4);
        for (int i = 0; i < 13; i++) {
            jArr3[i] = jArr3[i] ^ jArr4[i];
        }
        long[] jArr5 = new long[14];
        la0.L(jArr, jArr2, jArr5);
        for (int i2 = 0; i2 < 13; i2++) {
            jArr3[i2] = jArr3[i2] ^ jArr5[i2];
        }
        long[] jArr6 = new long[7];
        la0.b0(jArr3, jArr6);
        return new cu0(jArr6);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        long[] jArr = this.U;
        if (jArr[0] == 1) {
            for (int i = 1; i < 7; i++) {
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
        long[] jArr = new long[7];
        la0.j0(i, this.U, jArr);
        return new cu0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        long[] jArr = this.U;
        for (int i = 0; i < 7; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = ((cu0) l21Var).U;
        long[] jArr2 = this.U;
        return new cu0(new long[]{jArr2[0] ^ jArr[0], jArr2[1] ^ jArr[1], jArr2[2] ^ jArr[2], jArr2[3] ^ jArr[3], jArr2[4] ^ jArr[4], jArr2[5] ^ jArr[5], jArr2[6] ^ jArr[6]});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cu0)) {
            return false;
        }
        long[] jArr = ((cu0) obj).U;
        for (int i = 6; i >= 0; i--) {
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
        return new cu0(new long[]{jArr[0] ^ 1, jArr[1], jArr[2], jArr[3], jArr[4], jArr[5], jArr[6]});
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        byte[] bArr = new byte[56];
        for (int i = 0; i < 7; i++) {
            long j = this.U[i];
            if (j != 0) {
                ma0.S(j, bArr, (6 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public final int hashCode() {
        return z91.G(this.U, 7) ^ 4090087;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[13];
        long[] jArr2 = this.U;
        long[] jArr3 = {jArr2[0], jArr2[1], jArr2[2], jArr2[3], jArr2[4], jArr2[5], jArr2[6]};
        for (int i = 1; i < 409; i += 2) {
            la0.N(jArr3, jArr);
            la0.b0(jArr, jArr3);
            la0.N(jArr3, jArr);
            la0.b0(jArr, jArr3);
            jArr3[0] = jArr3[0] ^ jArr2[0];
            jArr3[1] = jArr3[1] ^ jArr2[1];
            jArr3[2] = jArr3[2] ^ jArr2[2];
            jArr3[3] = jArr3[3] ^ jArr2[3];
            jArr3[4] = jArr3[4] ^ jArr2[4];
            jArr3[5] = jArr3[5] ^ jArr2[5];
            jArr3[6] = jArr3[6] ^ jArr2[6];
        }
        return new cu0(jArr3);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[7];
        la0.W(this.U, ((cu0) l21Var).U, jArr);
        return new cu0(jArr);
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
        long[] jArr = ((cu0) l21Var).U;
        long[] jArr2 = ((cu0) l21Var2).U;
        long[] jArr3 = ((cu0) l21Var3).U;
        long[] jArr4 = new long[13];
        long[] jArr5 = new long[14];
        la0.L(this.U, jArr, jArr5);
        for (int i = 0; i < 13; i++) {
            jArr4[i] = jArr4[i] ^ jArr5[i];
        }
        long[] jArr6 = new long[14];
        la0.L(jArr2, jArr3, jArr6);
        for (int i2 = 0; i2 < 13; i2++) {
            jArr4[i2] = jArr4[i2] ^ jArr6[i2];
        }
        long[] jArr7 = new long[7];
        la0.b0(jArr4, jArr7);
        return new cu0(jArr7);
    }

    public cu0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
