package defpackage;

import io.nats.client.support.Digester;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public final class cq0 implements po, vd0 {
    public static final int[] n = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    public final byte[] a;
    public int b;
    public long c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int[] l;
    public int m;

    public cq0() {
        this.a = new byte[4];
        this.b = 0;
        this.l = new int[64];
        fa0.R(this);
        a.a();
        p();
    }

    public static int i(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    public static int j(int i, int i2, int i3) {
        return ((i ^ i2) & i3) | (i & i2);
    }

    public static int k(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    public static int l(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    @Override // defpackage.vd0
    public final vd0 a() {
        cq0 cq0Var = new cq0(this);
        cq0Var.l = new int[64];
        cq0Var.m(this);
        return cq0Var;
    }

    @Override // defpackage.po
    public final String b() {
        return Digester.DEFAULT_DIGEST_ALGORITHM;
    }

    @Override // defpackage.po
    public final int c() {
        return 32;
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        long j = this.c << 3;
        byte b = -128;
        while (true) {
            f(b);
            if (this.b == 0) {
                break;
            }
            b = 0;
        }
        if (this.m > 14) {
            n();
        }
        int[] iArr = this.l;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
        n();
        ma0.K(bArr, this.d, i);
        ma0.K(bArr, this.e, i + 4);
        ma0.K(bArr, this.f, i + 8);
        ma0.K(bArr, this.g, i + 12);
        ma0.K(bArr, this.h, i + 16);
        ma0.K(bArr, this.i, i + 20);
        ma0.K(bArr, this.j, i + 24);
        ma0.K(bArr, this.k, i + 28);
        p();
        return 32;
    }

    @Override // defpackage.po
    public final void e(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int iMax = Math.max(0, i2);
        int i4 = this.b;
        byte[] bArr2 = this.a;
        if (i4 != 0) {
            int i5 = 0;
            while (true) {
                if (i5 >= iMax) {
                    i3 = i5;
                    break;
                }
                int i6 = this.b;
                int i7 = i6 + 1;
                this.b = i7;
                int i8 = i5 + 1;
                bArr2[i6] = bArr[i5 + i];
                if (i7 == 4) {
                    o(0, bArr2);
                    this.b = 0;
                    i3 = i8;
                    break;
                }
                i5 = i8;
            }
        }
        int i9 = iMax - 3;
        while (i3 < i9) {
            o(i + i3, bArr);
            i3 += 4;
        }
        while (i3 < iMax) {
            int i10 = this.b;
            this.b = i10 + 1;
            bArr2[i10] = bArr[i3 + i];
            i3++;
        }
        this.c += (long) iMax;
    }

    @Override // defpackage.po
    public final void f(byte b) {
        int i = this.b;
        int i2 = i + 1;
        this.b = i2;
        byte[] bArr = this.a;
        bArr[i] = b;
        if (i2 == bArr.length) {
            o(0, bArr);
            this.b = 0;
        }
        this.c++;
    }

    @Override // defpackage.vd0
    public final void g(vd0 vd0Var) {
        m((cq0) vd0Var);
    }

    public final void m(cq0 cq0Var) {
        byte[] bArr = cq0Var.a;
        System.arraycopy(bArr, 0, this.a, 0, bArr.length);
        this.b = cq0Var.b;
        this.c = cq0Var.c;
        this.d = cq0Var.d;
        this.e = cq0Var.e;
        this.f = cq0Var.f;
        this.g = cq0Var.g;
        this.h = cq0Var.h;
        this.i = cq0Var.i;
        this.j = cq0Var.j;
        this.k = cq0Var.k;
        int[] iArr = this.l;
        int[] iArr2 = cq0Var.l;
        System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
        this.m = cq0Var.m;
    }

    public final void n() {
        int[] iArr;
        int i = 16;
        while (true) {
            iArr = this.l;
            if (i > 63) {
                break;
            }
            int i2 = iArr[i - 2];
            int i3 = ((i2 >>> 10) ^ (((i2 >>> 17) | (i2 << 15)) ^ ((i2 >>> 19) | (i2 << 13)))) + iArr[i - 7];
            int i4 = iArr[i - 15];
            iArr[i] = i3 + ((i4 >>> 3) ^ (((i4 >>> 7) | (i4 << 25)) ^ ((i4 >>> 18) | (i4 << 14)))) + iArr[i - 16];
            i++;
        }
        int iJ = this.d;
        int iJ2 = this.e;
        int iJ3 = this.f;
        int iJ4 = this.g;
        int i5 = this.h;
        int i6 = this.i;
        int i7 = this.j;
        int i8 = this.k;
        int i9 = 0;
        for (int i10 = 0; i10 < 8; i10++) {
            int i11 = i(i5, i6, i7) + l(i5);
            int[] iArr2 = n;
            int i12 = i11 + iArr2[i9] + iArr[i9] + i8;
            int i13 = iJ4 + i12;
            int iJ5 = j(iJ, iJ2, iJ3) + k(iJ) + i12;
            int i14 = i9 + 1;
            int i15 = i(i13, i5, i6) + l(i13) + iArr2[i14] + iArr[i14] + i7;
            int i16 = iJ3 + i15;
            int iJ6 = j(iJ5, iJ, iJ2) + k(iJ5) + i15;
            int i17 = i9 + 2;
            int i18 = i(i16, i13, i5) + l(i16) + iArr2[i17] + iArr[i17] + i6;
            int i19 = iJ2 + i18;
            int iJ7 = j(iJ6, iJ5, iJ) + k(iJ6) + i18;
            int i20 = i9 + 3;
            int i21 = i(i19, i16, i13) + l(i19) + iArr2[i20] + iArr[i20] + i5;
            int i22 = iJ + i21;
            int iJ8 = j(iJ7, iJ6, iJ5) + k(iJ7) + i21;
            int i23 = i9 + 4;
            int i24 = i(i22, i19, i16) + l(i22) + iArr2[i23] + iArr[i23] + i13;
            i8 = iJ5 + i24;
            iJ4 = j(iJ8, iJ7, iJ6) + k(iJ8) + i24;
            int i25 = i9 + 5;
            int i26 = i(i8, i22, i19) + l(i8) + iArr2[i25] + iArr[i25] + i16;
            i7 = iJ6 + i26;
            iJ3 = j(iJ4, iJ8, iJ7) + k(iJ4) + i26;
            int i27 = i9 + 6;
            int i28 = i(i7, i8, i22) + l(i7) + iArr2[i27] + iArr[i27] + i19;
            i6 = iJ7 + i28;
            iJ2 = j(iJ3, iJ4, iJ8) + k(iJ3) + i28;
            int i29 = i9 + 7;
            int i30 = i(i6, i7, i8) + l(i6) + iArr2[i29] + iArr[i29] + i22;
            i5 = iJ8 + i30;
            iJ = j(iJ2, iJ3, iJ4) + k(iJ2) + i30;
            i9 += 8;
        }
        this.d += iJ;
        this.e += iJ2;
        this.f += iJ3;
        this.g += iJ4;
        this.h += i5;
        this.i += i6;
        this.j += i7;
        this.k += i8;
        this.m = 0;
        for (int i31 = 0; i31 < 16; i31++) {
            iArr[i31] = 0;
        }
    }

    public final void o(int i, byte[] bArr) {
        this.l[this.m] = ma0.g(i, bArr);
        int i2 = this.m + 1;
        this.m = i2;
        if (i2 == 16) {
            n();
        }
    }

    public final void p() {
        this.c = 0L;
        this.b = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.a;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        this.d = 1779033703;
        this.e = -1150833019;
        this.f = 1013904242;
        this.g = -1521486534;
        this.h = 1359893119;
        this.i = -1694144372;
        this.j = 528734635;
        this.k = 1541459225;
        this.m = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.l;
            if (i2 == iArr.length) {
                return;
            }
            iArr[i2] = 0;
            i2++;
        }
    }

    public final String toString() {
        return "SHA256[Java]()";
    }

    public cq0(cq0 cq0Var) {
        byte[] bArr = new byte[4];
        this.a = bArr;
        byte[] bArr2 = cq0Var.a;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        this.b = cq0Var.b;
        this.c = cq0Var.c;
    }
}
