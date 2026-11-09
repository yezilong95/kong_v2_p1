package defpackage;

import java.math.BigInteger;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class cr extends ar {
    public final BigInteger U;
    public final BigInteger V;
    public final BigInteger W;

    public cr(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(23);
        this.U = bigInteger;
        this.V = bigInteger2;
        this.W = bigInteger3;
    }

    @Override // defpackage.l21
    public final l21 A(l21 l21Var) {
        return new cr(this.U, this.V, j1(this.W, i1(l21Var.h1())));
    }

    @Override // defpackage.l21
    public final int N() {
        return this.U.bitLength();
    }

    @Override // defpackage.l21
    public final l21 T0() {
        BigInteger bigInteger;
        if (d0() || b0()) {
            return this;
        }
        BigInteger bigInteger2 = this.U;
        int i = 0;
        if (!bigInteger2.testBit(0)) {
            throw new RuntimeException("not done yet");
        }
        int i2 = 1;
        boolean zTestBit = bigInteger2.testBit(1);
        BigInteger bigInteger3 = this.V;
        BigInteger bigInteger4 = this.W;
        l21 l21Var = null;
        BigInteger bigInteger5 = qq.b;
        if (zTestBit) {
            cr crVar = new cr(bigInteger2, bigInteger3, bigInteger4.modPow(bigInteger2.shiftRight(2).add(bigInteger5), bigInteger2));
            if (crVar.U0().equals(this)) {
                return crVar;
            }
            return null;
        }
        boolean zTestBit2 = bigInteger2.testBit(2);
        BigInteger bigIntegerK1 = qq.c;
        if (zTestBit2) {
            BigInteger bigIntegerModPow = bigInteger4.modPow(bigInteger2.shiftRight(3), bigInteger2);
            BigInteger bigIntegerJ1 = j1(bigIntegerModPow, bigInteger4);
            if (j1(bigIntegerJ1, bigIntegerModPow).equals(bigInteger5)) {
                cr crVar2 = new cr(bigInteger2, bigInteger3, bigIntegerJ1);
                if (crVar2.U0().equals(this)) {
                    return crVar2;
                }
                return null;
            }
            cr crVar3 = new cr(bigInteger2, bigInteger3, k1(bigIntegerJ1.multiply(bigIntegerK1.modPow(bigInteger2.shiftRight(2), bigInteger2))));
            if (crVar3.U0().equals(this)) {
                return crVar3;
            }
            return null;
        }
        BigInteger bigIntegerShiftRight = bigInteger2.shiftRight(1);
        if (!bigInteger4.modPow(bigIntegerShiftRight, bigInteger2).equals(bigInteger5)) {
            return null;
        }
        BigInteger bigIntegerShiftLeft = bigInteger4.shiftLeft(1);
        BigInteger bigInteger6 = this.U;
        if (bigIntegerShiftLeft.compareTo(bigInteger6) >= 0) {
            bigIntegerShiftLeft = bigIntegerShiftLeft.subtract(bigInteger6);
        }
        BigInteger bigIntegerShiftLeft2 = bigIntegerShiftLeft.shiftLeft(1);
        BigInteger bigInteger7 = this.U;
        if (bigIntegerShiftLeft2.compareTo(bigInteger7) >= 0) {
            bigIntegerShiftLeft2 = bigIntegerShiftLeft2.subtract(bigInteger7);
        }
        BigInteger bigIntegerAdd = bigIntegerShiftRight.add(bigInteger5);
        BigInteger bigIntegerSubtract = bigInteger2.subtract(bigInteger5);
        Random random = new Random();
        while (true) {
            BigInteger bigInteger8 = new BigInteger(bigInteger2.bitLength(), random);
            if (bigInteger8.compareTo(bigInteger2) < 0 && k1(bigInteger8.multiply(bigInteger8).subtract(bigIntegerShiftLeft2)).modPow(bigIntegerShiftRight, bigInteger2).equals(bigIntegerSubtract)) {
                int iBitLength = bigIntegerAdd.bitLength();
                l21 l21Var2 = l21Var;
                int lowestSetBit = bigIntegerAdd.getLowestSetBit();
                int i3 = iBitLength - i2;
                int i4 = i;
                BigInteger bigInteger9 = bigIntegerShiftRight;
                BigInteger bigIntegerJ12 = bigInteger5;
                BigInteger bigIntegerJ13 = bigIntegerJ12;
                BigInteger bigInteger10 = bigIntegerJ13;
                BigInteger bigInteger11 = bigIntegerK1;
                Random random2 = random;
                BigInteger bigIntegerK12 = bigInteger8;
                while (true) {
                    bigInteger = bigIntegerSubtract;
                    int i5 = lowestSetBit + 1;
                    bigIntegerJ12 = j1(bigIntegerJ12, bigIntegerJ13);
                    if (i3 < i5) {
                        break;
                    }
                    if (bigIntegerAdd.testBit(i3)) {
                        bigIntegerJ13 = j1(bigIntegerJ12, bigInteger4);
                        BigInteger bigIntegerJ14 = j1(bigInteger10, bigIntegerK12);
                        bigIntegerK1 = k1(bigIntegerK12.multiply(bigIntegerK1).subtract(bigInteger8.multiply(bigIntegerJ12)));
                        bigIntegerK12 = k1(bigIntegerK12.multiply(bigIntegerK12).subtract(bigIntegerJ13.shiftLeft(1)));
                        bigInteger10 = bigIntegerJ14;
                    } else {
                        BigInteger bigIntegerK13 = k1(bigInteger10.multiply(bigIntegerK1).subtract(bigIntegerJ12));
                        BigInteger bigIntegerK14 = k1(bigIntegerK12.multiply(bigIntegerK1).subtract(bigInteger8.multiply(bigIntegerJ12)));
                        bigIntegerK1 = k1(bigIntegerK1.multiply(bigIntegerK1).subtract(bigIntegerJ12.shiftLeft(1)));
                        bigIntegerK12 = bigIntegerK14;
                        bigInteger10 = bigIntegerK13;
                        bigIntegerJ13 = bigIntegerJ12;
                    }
                    i3--;
                    bigIntegerSubtract = bigInteger;
                }
                BigInteger bigIntegerJ15 = j1(bigIntegerJ12, bigInteger4);
                BigInteger bigIntegerK15 = k1(bigInteger10.multiply(bigIntegerK1).subtract(bigIntegerJ12));
                BigInteger bigIntegerK16 = k1(bigIntegerK12.multiply(bigIntegerK1).subtract(bigInteger8.multiply(bigIntegerJ12)));
                BigInteger bigIntegerK17 = k1(bigIntegerJ12.multiply(bigIntegerJ15));
                for (int i6 = 1; i6 <= lowestSetBit; i6++) {
                    bigIntegerK15 = j1(bigIntegerK15, bigIntegerK16);
                    bigIntegerK16 = k1(bigIntegerK16.multiply(bigIntegerK16).subtract(bigIntegerK17.shiftLeft(1)));
                    bigIntegerK17 = k1(bigIntegerK17.multiply(bigIntegerK17));
                }
                BigInteger[] bigIntegerArr = {bigIntegerK15, bigIntegerK16};
                BigInteger bigInteger12 = bigIntegerArr[i4];
                BigInteger bigIntegerSubtract2 = bigIntegerArr[1];
                if (j1(bigIntegerSubtract2, bigIntegerSubtract2).equals(bigIntegerShiftLeft2)) {
                    if (bigIntegerSubtract2.testBit(i4)) {
                        bigIntegerSubtract2 = bigInteger2.subtract(bigIntegerSubtract2);
                    }
                    return new cr(bigInteger2, bigInteger3, bigIntegerSubtract2.shiftRight(1));
                }
                if (bigInteger12.equals(bigInteger5)) {
                    i = i4;
                    i2 = 1;
                    l21Var = l21Var2;
                    bigIntegerShiftRight = bigInteger9;
                    bigIntegerK1 = bigInteger11;
                    random = random2;
                    bigIntegerSubtract = bigInteger;
                } else {
                    if (!bigInteger12.equals(bigInteger)) {
                        return l21Var2;
                    }
                    bigIntegerSubtract = bigInteger;
                    i = i4;
                    i2 = 1;
                    l21Var = l21Var2;
                    bigIntegerShiftRight = bigInteger9;
                    bigIntegerK1 = bigInteger11;
                    random = random2;
                }
            }
        }
    }

    @Override // defpackage.l21
    public final l21 U0() {
        BigInteger bigInteger = this.W;
        return new cr(this.U, this.V, j1(bigInteger, bigInteger));
    }

    @Override // defpackage.l21
    public final l21 Y() {
        return new cr(this.U, this.V, i1(this.W));
    }

    @Override // defpackage.l21
    public final l21 a1(l21 l21Var, l21 l21Var2) {
        BigInteger bigIntegerH1 = l21Var.h1();
        BigInteger bigIntegerH12 = l21Var2.h1();
        BigInteger bigInteger = this.W;
        return new cr(this.U, this.V, k1(bigInteger.multiply(bigInteger).add(bigIntegerH1.multiply(bigIntegerH12))));
    }

    @Override // defpackage.l21
    public final l21 d1(l21 l21Var) {
        BigInteger bigIntegerSubtract = this.W.subtract(l21Var.h1());
        int iSignum = bigIntegerSubtract.signum();
        BigInteger bigInteger = this.U;
        if (iSignum < 0) {
            bigIntegerSubtract = bigIntegerSubtract.add(bigInteger);
        }
        return new cr(bigInteger, this.V, bigIntegerSubtract);
    }

    @Override // defpackage.l21
    public final l21 e(l21 l21Var) {
        BigInteger bigIntegerAdd = this.W.add(l21Var.h1());
        BigInteger bigInteger = this.U;
        if (bigIntegerAdd.compareTo(bigInteger) >= 0) {
            bigIntegerAdd = bigIntegerAdd.subtract(bigInteger);
        }
        return new cr(bigInteger, this.V, bigIntegerAdd);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof cr)) {
            return false;
        }
        cr crVar = (cr) obj;
        return this.U.equals(crVar.U) && this.W.equals(crVar.W);
    }

    @Override // defpackage.l21
    public final l21 h() {
        BigInteger bigIntegerAdd = this.W.add(qq.b);
        BigInteger bigInteger = this.U;
        if (bigIntegerAdd.compareTo(bigInteger) == 0) {
            bigIntegerAdd = qq.a;
        }
        return new cr(bigInteger, this.V, bigIntegerAdd);
    }

    @Override // defpackage.l21
    public final BigInteger h1() {
        return this.W;
    }

    public final int hashCode() {
        return this.U.hashCode() ^ this.W.hashCode();
    }

    public final BigInteger i1(BigInteger bigInteger) {
        BigInteger bigInteger2 = oa.a;
        BigInteger bigInteger3 = this.U;
        if (!bigInteger3.testBit(0)) {
            throw new IllegalArgumentException("'M' must be odd");
        }
        if (bigInteger3.signum() != 1) {
            throw new ArithmeticException("BigInteger: modulus not positive");
        }
        if (bigInteger.signum() < 0 || bigInteger.bitLength() > bigInteger3.bitLength()) {
            bigInteger = bigInteger.mod(bigInteger3);
        }
        int iBitLength = bigInteger3.bitLength();
        int[] iArrJ = fa0.J(iBitLength, bigInteger3);
        int[] iArrJ2 = fa0.J(iBitLength, bigInteger);
        int length = iArrJ.length;
        int[] iArr = new int[length];
        if (ma0.T(iArrJ, iArrJ2, iArr) != 0) {
            return fa0.L0(iArr, length);
        }
        throw new ArithmeticException("BigInteger not invertible.");
    }

    public final BigInteger j1(BigInteger bigInteger, BigInteger bigInteger2) {
        return k1(bigInteger.multiply(bigInteger2));
    }

    @Override // defpackage.l21
    public final l21 k0(l21 l21Var) {
        return new cr(this.U, this.V, j1(this.W, l21Var.h1()));
    }

    public final BigInteger k1(BigInteger bigInteger) {
        BigInteger bigInteger2 = this.U;
        BigInteger bigInteger3 = this.V;
        if (bigInteger3 == null) {
            return bigInteger.mod(bigInteger2);
        }
        boolean z = bigInteger.signum() < 0;
        if (z) {
            bigInteger = bigInteger.abs();
        }
        int iBitLength = bigInteger2.bitLength();
        boolean zEquals = bigInteger3.equals(qq.b);
        while (bigInteger.bitLength() > iBitLength + 1) {
            BigInteger bigIntegerShiftRight = bigInteger.shiftRight(iBitLength);
            BigInteger bigIntegerSubtract = bigInteger.subtract(bigIntegerShiftRight.shiftLeft(iBitLength));
            if (!zEquals) {
                bigIntegerShiftRight = bigIntegerShiftRight.multiply(bigInteger3);
            }
            bigInteger = bigIntegerShiftRight.add(bigIntegerSubtract);
        }
        while (bigInteger.compareTo(bigInteger2) >= 0) {
            bigInteger = bigInteger.subtract(bigInteger2);
        }
        return (!z || bigInteger.signum() == 0) ? bigInteger : bigInteger2.subtract(bigInteger);
    }

    @Override // defpackage.l21
    public final l21 o0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return new cr(this.U, this.V, k1(this.W.multiply(l21Var.h1()).subtract(l21Var2.h1().multiply(l21Var3.h1()))));
    }

    @Override // defpackage.l21
    public final l21 p0(l21 l21Var, l21 l21Var2, l21 l21Var3) {
        return new cr(this.U, this.V, k1(this.W.multiply(l21Var.h1()).add(l21Var2.h1().multiply(l21Var3.h1()))));
    }

    @Override // defpackage.l21
    public final l21 q0() {
        BigInteger bigInteger = this.W;
        if (bigInteger.signum() == 0) {
            return this;
        }
        BigInteger bigInteger2 = this.V;
        BigInteger bigInteger3 = this.U;
        return new cr(bigInteger3, bigInteger2, bigInteger3.subtract(bigInteger));
    }
}
