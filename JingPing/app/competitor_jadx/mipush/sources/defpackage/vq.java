package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class vq extends sq {
    public int g;
    public int h;
    public int i;
    public int j;
    public nr k;

    public vq(int i, int i2, int i3, int i4, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        super(i, i2, i3, i4);
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = i4;
        this.d = bigInteger3;
        this.e = bigInteger4;
        l21 l21Var = null;
        this.k = new nr(this, l21Var, l21Var, 0);
        this.b = h(bigInteger);
        this.c = h(bigInteger2);
        this.f = 6;
    }

    @Override // defpackage.xq
    public final xq a() {
        l21 l21Var = this.b;
        l21 l21Var2 = this.c;
        BigInteger bigInteger = this.d;
        BigInteger bigInteger2 = this.e;
        int i = this.g;
        int i2 = this.h;
        int i3 = this.i;
        int i4 = this.j;
        vq vqVar = new vq(i, i2, i3, i4);
        vqVar.g = i;
        vqVar.h = i2;
        vqVar.i = i3;
        vqVar.j = i4;
        vqVar.d = bigInteger;
        vqVar.e = bigInteger2;
        l21 l21Var3 = null;
        vqVar.k = new nr(vqVar, l21Var3, l21Var3, 0);
        vqVar.b = l21Var;
        vqVar.c = l21Var2;
        vqVar.f = 6;
        return vqVar;
    }

    @Override // defpackage.xq
    public final l21 b(or[] orVarArr, int i) {
        int i2 = (this.g + 63) >>> 6;
        int i3 = this.h;
        int i4 = this.j;
        int i5 = this.i;
        int[] iArr = (i5 == 0 && i4 == 0) ? new int[]{i3} : new int[]{i3, i5, i4};
        long[] jArr = new long[i * i2 * 2];
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            or orVar = orVarArr[i7];
            long[] jArr2 = ((br) orVar.b).X.E;
            System.arraycopy(jArr2, 0, jArr, i6, jArr2.length);
            int i8 = i6 + i2;
            long[] jArr3 = ((br) orVar.c).X.E;
            System.arraycopy(jArr3, 0, jArr, i8, jArr3.length);
            i6 = i8 + i2;
        }
        return new uq(this, i, i2, jArr, iArr);
    }

    @Override // defpackage.xq
    public final or d(l21 l21Var, l21 l21Var2) {
        return new nr(this, l21Var, l21Var2, 0);
    }

    @Override // defpackage.xq
    public final l21 h(BigInteger bigInteger) {
        int i;
        byte[] bArr;
        if (bigInteger != null && bigInteger.signum() >= 0) {
            int iBitLength = bigInteger.bitLength();
            int i2 = this.g;
            if (iBitLength <= i2) {
                int i3 = this.i;
                int i4 = this.j;
                int i5 = i3 | i4;
                char c = 2;
                int i6 = this.h;
                int[] iArr = i5 == 0 ? new int[]{i6} : new int[]{i6, i3, i4};
                za0 za0Var = new za0();
                if (bigInteger.signum() < 0) {
                    throw new IllegalArgumentException("invalid F2m field value");
                }
                if (bigInteger.signum() == 0) {
                    za0Var.E = new long[]{0};
                } else {
                    byte[] byteArray = bigInteger.toByteArray();
                    int length = byteArray.length;
                    if (byteArray[0] == 0) {
                        length--;
                        i = 1;
                    } else {
                        i = 0;
                    }
                    int i7 = (length + 7) / 8;
                    za0Var.E = new long[i7];
                    int i8 = i7 - 1;
                    int i9 = (length % 8) + i;
                    if (i < i9) {
                        long j = 0;
                        while (i < i9) {
                            j = (j << 8) | ((long) (byteArray[i] & 255));
                            i++;
                            byteArray = byteArray;
                            c = c;
                        }
                        bArr = byteArray;
                        za0Var.E[i8] = j;
                        i8 = i7 - 2;
                    } else {
                        bArr = byteArray;
                    }
                    while (i8 >= 0) {
                        long j2 = 0;
                        int i10 = 0;
                        while (i10 < 8) {
                            j2 = (j2 << 8) | ((long) (bArr[i] & 255));
                            i10++;
                            i++;
                        }
                        za0Var.E[i8] = j2;
                        i8--;
                    }
                }
                return new br(i2, iArr, za0Var);
            }
        }
        throw new IllegalArgumentException("x value invalid in F2m field element");
    }

    @Override // defpackage.xq
    public final int j() {
        return this.g;
    }

    @Override // defpackage.xq
    public final or k() {
        return this.k;
    }

    @Override // defpackage.xq
    public final boolean p(int i) {
        return i == 0 || i == 1 || i == 6;
    }

    public vq(int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this(i, i2, 0, 0, bigInteger, bigInteger2, bigInteger3, bigInteger4);
    }
}
