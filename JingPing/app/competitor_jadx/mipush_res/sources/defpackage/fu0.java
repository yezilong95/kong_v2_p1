package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class fu0 extends zq {
    public long[] U;

    public fu0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.U = fa0.L(571, bigInteger);
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return k0(l21Var.Y());
    }

    @Override // defpackage.l21
    public final int N() {
        return 571;
    }

    @Override // defpackage.l21
    public final l21 T0() {
        long[] jArr = new long[9];
        long[] jArr2 = new long[9];
        long[] jArr3 = new long[9];
        int i = 0;
        int i2 = 0;
        while (true) {
            long[] jArr4 = this.U;
            if (i >= 4) {
                long jL0 = qm.L0(jArr4[i2]);
                jArr2[4] = jL0 & 4294967295L;
                jArr3[4] = jL0 >>> 32;
                j31.F(jArr3, j31.J, jArr);
                j31.a(jArr, jArr2, jArr);
                return new fu0(jArr);
            }
            int i3 = i2 + 1;
            long jL02 = qm.L0(jArr4[i2]);
            i2 += 2;
            long jL03 = qm.L0(jArr4[i3]);
            jArr2[i] = (jL02 & 4294967295L) | (jL03 << 32);
            jArr3[i] = (jL02 >>> 32) | ((-4294967296L) & jL03);
            i++;
        }
    }

    @Override // defpackage.l21
    public final l21 U0() {
        long[] jArr = new long[9];
        j31.b0(this.U, jArr);
        return new fu0(jArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        long[] jArr = new long[9];
        long[] jArr2 = this.U;
        if (s90.H(jArr2)) {
            throw new IllegalStateException();
        }
        long[] jArr3 = new long[9];
        long[] jArr4 = new long[9];
        long[] jArr5 = new long[9];
        j31.b0(jArr2, jArr5);
        j31.b0(jArr5, jArr3);
        j31.b0(jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(2, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.F(jArr3, jArr5, jArr3);
        j31.e0(5, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(5, jArr4, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(15, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr5);
        j31.e0(30, jArr5, jArr3);
        j31.e0(30, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(60, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(60, jArr4, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(180, jArr3, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.e0(180, jArr4, jArr4);
        j31.F(jArr3, jArr4, jArr3);
        j31.F(jArr3, jArr5, jArr);
        return new fu0(jArr);
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        long[] jArr = ((fu0) l21Var).U;
        long[] jArr2 = ((fu0) l21Var2).U;
        long[] jArr3 = new long[18];
        long[] jArr4 = new long[18];
        j31.w(this.U, jArr4);
        j31.d(jArr3, jArr4, jArr3);
        j31.G(jArr, jArr2, jArr3);
        long[] jArr5 = new long[9];
        j31.R(jArr3, jArr5);
        return new fu0(jArr5);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        long[] jArr = this.U;
        if (jArr[0] == 1) {
            for (int i = 1; i < 9; i++) {
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
        long[] jArr = new long[9];
        j31.e0(i, this.U, jArr);
        return new fu0(jArr);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return s90.H(this.U);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        long[] jArr = new long[9];
        j31.a(this.U, ((fu0) l21Var).U, jArr);
        return new fu0(jArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fu0)) {
            return false;
        }
        long[] jArr = ((fu0) obj).U;
        for (int i = 8; i >= 0; i--) {
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
        long[] jArr = new long[9];
        long[] jArr2 = this.U;
        jArr[0] = jArr2[0] ^ 1;
        for (int i = 1; i < 9; i++) {
            jArr[i] = jArr2[i];
        }
        return new fu0(jArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        byte[] bArr = new byte[72];
        for (int i = 0; i < 9; i++) {
            long j = this.U[i];
            if (j != 0) {
                ma0.S(j, bArr, (8 - i) << 3);
            }
        }
        return new BigInteger(1, bArr);
    }

    public final int hashCode() {
        return z91.G(this.U, 9) ^ 5711052;
    }

    @Override // defpackage.zq
    public final l21 i1() {
        long[] jArr = new long[9];
        long[] jArr2 = new long[18];
        long[] jArr3 = this.U;
        jArr[0] = jArr3[0];
        jArr[1] = jArr3[1];
        jArr[2] = jArr3[2];
        jArr[3] = jArr3[3];
        jArr[4] = jArr3[4];
        jArr[5] = jArr3[5];
        jArr[6] = jArr3[6];
        jArr[7] = jArr3[7];
        jArr[8] = jArr3[8];
        for (int i = 1; i < 571; i += 2) {
            j31.w(jArr, jArr2);
            j31.R(jArr2, jArr);
            j31.w(jArr, jArr2);
            j31.R(jArr2, jArr);
            for (int i2 = 0; i2 < 9; i2++) {
                jArr[i2] = jArr[i2] ^ jArr3[i2];
            }
        }
        return new fu0(jArr);
    }

    @Override // defpackage.zq
    public final boolean j1() {
        return true;
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        long[] jArr = new long[9];
        j31.F(this.U, ((fu0) l21Var).U, jArr);
        return new fu0(jArr);
    }

    @Override // defpackage.zq
    public final int k1() {
        long[] jArr = this.U;
        long j = jArr[0];
        long j2 = jArr[8];
        return ((int) ((j2 >>> 57) ^ (j ^ (j2 >>> 49)))) & 1;
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return p0(l21Var, l21Var2, l21Var3);
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        long[] jArr = ((fu0) l21Var).U;
        long[] jArr2 = ((fu0) l21Var2).U;
        long[] jArr3 = ((fu0) l21Var3).U;
        long[] jArr4 = new long[18];
        j31.G(this.U, jArr, jArr4);
        j31.G(jArr2, jArr3, jArr4);
        long[] jArr5 = new long[9];
        j31.R(jArr4, jArr5);
        return new fu0(jArr5);
    }

    public fu0(long[] jArr) {
        super(23);
        this.U = jArr;
    }

    @Override // defpackage.l21
    public final l21 q0() {
        return this;
    }
}
