package defpackage;

import java.math.BigInteger;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class ys0 extends ar {
    public static final BigInteger V = new BigInteger(1, c30.b("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF000000000000000000000001"));
    public final int[] U;

    public ys0(BigInteger bigInteger) {
        super(23);
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(V) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224R1FieldElement");
        }
        int[] iArrU = s90.u(bigInteger);
        if (iArrU[6] == -1) {
            int[] iArr = qm.n;
            if (s90.z(iArrU, iArr)) {
                long j = (((long) iArrU[0]) & 4294967295L) - (((long) iArr[0]) & 4294967295L);
                iArrU[0] = (int) j;
                long j2 = ((((long) iArrU[1]) & 4294967295L) - (((long) iArr[1]) & 4294967295L)) + (j >> 32);
                iArrU[1] = (int) j2;
                long j3 = ((((long) iArrU[2]) & 4294967295L) - (((long) iArr[2]) & 4294967295L)) + (j2 >> 32);
                iArrU[2] = (int) j3;
                long j4 = ((((long) iArrU[3]) & 4294967295L) - (((long) iArr[3]) & 4294967295L)) + (j3 >> 32);
                iArrU[3] = (int) j4;
                long j5 = ((((long) iArrU[4]) & 4294967295L) - (((long) iArr[4]) & 4294967295L)) + (j4 >> 32);
                iArrU[4] = (int) j5;
                long j6 = ((((long) iArrU[5]) & 4294967295L) - (((long) iArr[5]) & 4294967295L)) + (j5 >> 32);
                iArrU[5] = (int) j6;
                iArrU[6] = (int) (((((long) iArrU[6]) & 4294967295L) - (((long) iArr[6]) & 4294967295L)) + (j6 >> 32));
            }
        }
        this.U = iArrU;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        int[] iArr = new int[7];
        ma0.j(qm.n, ((ys0) l21Var).U, iArr);
        qm.Y(iArr, this.U, iArr);
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final int N() {
        return V.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        int i;
        int[] iArr;
        int[] iArr2;
        int[] iArr3 = this.U;
        if (s90.G(iArr3) || s90.F(iArr3)) {
            return this;
        }
        int[] iArr4 = new int[7];
        int iS = qm.S(iArr3);
        int[] iArr5 = qm.n;
        if (iS != 0) {
            s90.b0(iArr5, iArr5, iArr4);
        } else {
            s90.b0(iArr5, iArr3, iArr4);
        }
        Random random = new Random();
        int[] iArr6 = new int[7];
        char c = 6;
        int i2 = iArr5[6];
        int i3 = i2 | (i2 >>> 1);
        int i4 = i3 | (i3 >>> 2);
        int i5 = i4 | (i4 >>> 4);
        int i6 = i5 | (i5 >>> 8);
        int i7 = i6 | (i6 >>> 16);
        do {
            i = 0;
            for (int i8 = 0; i8 != 7; i8++) {
                iArr6[i8] = random.nextInt();
            }
            iArr6[6] = iArr6[6] & i7;
        } while (fa0.T(7, iArr6, iArr5));
        int[] iArr7 = new int[7];
        int[] iArr8 = new int[7];
        int[] iArr9 = new int[7];
        s90.p(iArr3, iArr8);
        for (int i9 = 0; i9 < 7; i9++) {
            s90.p(iArr8, iArr9);
            int i10 = 1 << i9;
            int[] iArr10 = new int[14];
            do {
                s90.Y(iArr8, iArr10);
                qm.k0(iArr10, iArr8);
                i10--;
            } while (i10 > 0);
            qm.Y(iArr8, iArr9, iArr8);
        }
        int[] iArr11 = new int[14];
        s90.Y(iArr8, iArr11);
        qm.k0(iArr11, iArr8);
        int i11 = 95;
        while (true) {
            i11--;
            if (i11 <= 0) {
                break;
            }
            s90.Y(iArr8, iArr11);
            qm.k0(iArr11, iArr8);
        }
        if (!s90.F(iArr8)) {
            return null;
        }
        loop5: while (true) {
            int[] iArr12 = new int[7];
            s90.p(iArr6, iArr12);
            int[] iArr13 = new int[7];
            iArr13[i] = 1;
            int[] iArr14 = new int[7];
            s90.p(iArr4, iArr14);
            int[] iArr15 = new int[7];
            int[] iArr16 = new int[7];
            char c2 = c;
            for (int i12 = i; i12 < 7; i12++) {
                s90.p(iArr12, iArr15);
                s90.p(iArr13, iArr16);
                int i13 = 1 << i12;
                while (true) {
                    i13--;
                    if (i13 >= 0) {
                        qm.Y(iArr13, iArr12, iArr13);
                        qm.J0(iArr13, iArr13);
                        qm.w0(iArr12, iArr7);
                        qm.b(iArr14, iArr7, iArr12);
                        qm.Y(iArr14, iArr7, iArr14);
                        qm.o0(iArr14, fa0.x0(iArr14, 7));
                    }
                }
                qm.Y(iArr13, iArr16, iArr7);
                qm.Y(iArr7, iArr4, iArr7);
                qm.Y(iArr12, iArr15, iArr14);
                qm.b(iArr14, iArr7, iArr14);
                qm.Y(iArr12, iArr16, iArr7);
                s90.p(iArr14, iArr12);
                qm.Y(iArr13, iArr15, iArr13);
                qm.b(iArr13, iArr7, iArr13);
                qm.w0(iArr13, iArr14);
                qm.Y(iArr14, iArr4, iArr14);
            }
            iArr = new int[7];
            iArr2 = new int[7];
            for (int i14 = 1; i14 < 96; i14++) {
                s90.p(iArr12, iArr);
                s90.p(iArr13, iArr2);
                qm.Y(iArr13, iArr12, iArr13);
                qm.J0(iArr13, iArr13);
                qm.w0(iArr12, iArr7);
                qm.b(iArr14, iArr7, iArr12);
                qm.Y(iArr14, iArr7, iArr14);
                qm.o0(iArr14, fa0.x0(iArr14, 7));
                if (s90.G(iArr12)) {
                    break loop5;
                }
            }
            if (fa0.Y(7, iArr6, iArr6) != 0 || (iArr6[c2] == -1 && s90.z(iArr6, iArr5))) {
                qm.e(iArr6);
            }
            c = c2;
            i = 0;
        }
        ma0.j(iArr5, iArr2, iArr7);
        qm.Y(iArr7, iArr, iArr7);
        qm.w0(iArr7, iArr6);
        if (s90.t(iArr3, iArr6)) {
            return new ys0(iArr7);
        }
        return null;
    }

    @Override // defpackage.l21
    public final l21 U0() {
        int[] iArr = new int[7];
        qm.w0(this.U, iArr);
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final l21 Y() {
        int[] iArr = new int[7];
        ma0.j(qm.n, this.U, iArr);
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final boolean b0() {
        return s90.F(this.U);
    }

    @Override // defpackage.l21
    public final boolean d0() {
        return s90.G(this.U);
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        int[] iArr = new int[7];
        qm.G0(this.U, ((ys0) l21Var).U, iArr);
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        int[] iArr = new int[7];
        qm.b(this.U, ((ys0) l21Var).U, iArr);
        return new ys0(iArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ys0) {
            return s90.t(this.U, ((ys0) obj).U);
        }
        return false;
    }

    @Override // defpackage.l21
    public final boolean g1() {
        return s90.v(this.U) == 1;
    }

    @Override // defpackage.l21
    public final l21 h() {
        int[] iArr = new int[7];
        if (fa0.Y(7, this.U, iArr) != 0 || (iArr[6] == -1 && s90.z(iArr, qm.n))) {
            qm.e(iArr);
        }
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return s90.c0(this.U);
    }

    public final int hashCode() {
        return V.hashCode() ^ z91.F(this.U, 7);
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        int[] iArr = new int[7];
        qm.Y(this.U, ((ys0) l21Var).U, iArr);
        return new ys0(iArr);
    }

    @Override // defpackage.l21
    public final l21 q0() {
        int[] iArr = new int[7];
        int[] iArr2 = this.U;
        int iS = qm.S(iArr2);
        int[] iArr3 = qm.n;
        if (iS != 0) {
            s90.b0(iArr3, iArr3, iArr);
        } else {
            s90.b0(iArr3, iArr2, iArr);
        }
        return new ys0(iArr);
    }

    public ys0(int[] iArr) {
        super(23);
        this.U = iArr;
    }
}
