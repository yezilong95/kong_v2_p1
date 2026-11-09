package defpackage;

import java.util.Arrays;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class b70 implements po {
    public static final long[] h = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    public CryptoServicePurpose a;
    public final long[] b;
    public final byte[] c;
    public int d;
    public int e;
    public int f;
    public boolean g;

    public b70(int i, CryptoServicePurpose cryptoServicePurpose) {
        this.b = new long[25];
        this.c = new byte[192];
        this.a = cryptoServicePurpose;
        m(i);
        l();
        a.a();
    }

    @Override // defpackage.po
    public int c() {
        return this.f / 8;
    }

    @Override // defpackage.po
    public final void e(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5 = this.e;
        if (i5 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.g) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        int i6 = i5 >>> 3;
        int i7 = this.d >>> 3;
        int i8 = i7 - i6;
        byte[] bArr2 = this.c;
        if (i2 < i8) {
            System.arraycopy(bArr, i, bArr2, i6, i2);
            i4 = this.e + (i2 << 3);
        } else {
            if (i6 > 0) {
                System.arraycopy(bArr, i, bArr2, i6, i8);
                i(0, bArr2);
            } else {
                i8 = 0;
            }
            while (true) {
                i3 = i2 - i8;
                if (i3 < i7) {
                    break;
                }
                i(i + i8, bArr);
                i8 += i7;
            }
            System.arraycopy(bArr, i + i8, bArr2, 0, i3);
            i4 = i3 << 3;
        }
        this.e = i4;
    }

    @Override // defpackage.po
    public final void f(byte b) {
        int i = this.e;
        if (i % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.g) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        byte[] bArr = this.c;
        bArr[i >>> 3] = b;
        int i2 = i + 8;
        this.e = i2;
        if (i2 == this.d) {
            i(0, bArr);
            this.e = 0;
        }
    }

    public final void i(int i, byte[] bArr) {
        int i2 = this.d >>> 6;
        for (int i3 = 0; i3 < i2; i3++) {
            long[] jArr = this.b;
            jArr[i3] = jArr[i3] ^ ma0.R(i, bArr);
            i += 8;
        }
        j();
    }

    public final void j() {
        long[] jArr = this.b;
        long j = jArr[0];
        long j2 = jArr[1];
        char c = 2;
        long j3 = jArr[2];
        char c2 = 3;
        long j4 = jArr[3];
        char c3 = 4;
        long j5 = jArr[4];
        long j6 = jArr[5];
        long j7 = jArr[6];
        long j8 = jArr[7];
        long j9 = jArr[8];
        long j10 = jArr[9];
        long j11 = jArr[10];
        long j12 = jArr[11];
        long j13 = jArr[12];
        long j14 = jArr[13];
        long j15 = jArr[14];
        long j16 = jArr[15];
        long j17 = jArr[16];
        long j18 = jArr[17];
        long j19 = jArr[18];
        long j20 = jArr[19];
        long j21 = jArr[20];
        long j22 = jArr[21];
        long j23 = jArr[22];
        long j24 = jArr[23];
        int i = 24;
        long j25 = jArr[24];
        int i2 = 0;
        while (i2 < i) {
            long j26 = (((j ^ j6) ^ j11) ^ j16) ^ j21;
            long j27 = (((j2 ^ j7) ^ j12) ^ j17) ^ j22;
            long j28 = (((j3 ^ j8) ^ j13) ^ j18) ^ j23;
            long j29 = (((j4 ^ j9) ^ j14) ^ j19) ^ j24;
            long j30 = (((j5 ^ j10) ^ j15) ^ j20) ^ j25;
            long j31 = ((j27 << 1) | (j27 >>> (-1))) ^ j30;
            long j32 = ((j28 << 1) | (j28 >>> (-1))) ^ j26;
            long j33 = ((j29 << 1) | (j29 >>> (-1))) ^ j27;
            long j34 = ((j30 << 1) | (j30 >>> (-1))) ^ j28;
            long j35 = ((j26 << 1) | (j26 >>> (-1))) ^ j29;
            long j36 = j ^ j31;
            long j37 = j6 ^ j31;
            long j38 = j11 ^ j31;
            long j39 = j16 ^ j31;
            long j40 = j21 ^ j31;
            long j41 = j2 ^ j32;
            long j42 = j7 ^ j32;
            long j43 = j12 ^ j32;
            long j44 = j17 ^ j32;
            long j45 = j22 ^ j32;
            long j46 = j3 ^ j33;
            long j47 = j8 ^ j33;
            long j48 = j13 ^ j33;
            long j49 = j18 ^ j33;
            long j50 = j23 ^ j33;
            long j51 = j4 ^ j34;
            long j52 = j9 ^ j34;
            long j53 = j14 ^ j34;
            long j54 = j19 ^ j34;
            long j55 = j24 ^ j34;
            long j56 = j5 ^ j35;
            long j57 = j10 ^ j35;
            long j58 = j15 ^ j35;
            long j59 = j20 ^ j35;
            long j60 = j25 ^ j35;
            long j61 = (j41 << 1) | (j41 >>> 63);
            char c4 = c;
            long j62 = (j42 << 44) | (j42 >>> 20);
            char c5 = c2;
            long j63 = (j57 << 20) | (j57 >>> 44);
            char c6 = c3;
            long j64 = (j50 << 61) | (j50 >>> c5);
            int i3 = i;
            long j65 = (j58 << 39) | (j58 >>> 25);
            long j66 = (j40 << 18) | (j40 >>> 46);
            int i4 = i2;
            long j67 = (j46 << 62) | (j46 >>> c4);
            long j68 = (j48 << 43) | (j48 >>> 21);
            long j69 = (j53 << 25) | (j53 >>> 39);
            long j70 = (j59 << 8) | (j59 >>> 56);
            long j71 = (j55 << 56) | (j55 >>> 8);
            long j72 = (j39 << 41) | (j39 >>> 23);
            long j73 = (j56 << 27) | (j56 >>> 37);
            long j74 = (j60 << 14) | (j60 >>> 50);
            long j75 = (j45 << c4) | (j45 >>> 62);
            long j76 = (j52 << 55) | (j52 >>> 9);
            long j77 = (j44 << 45) | (j44 >>> 19);
            long j78 = (j37 << 36) | (j37 >>> 28);
            long j79 = (j51 << 28) | (j51 >>> 36);
            long j80 = (j54 << 21) | (j54 >>> 43);
            long j81 = (j49 << 15) | (j49 >>> 49);
            long j82 = (j43 << 10) | (j43 >>> 54);
            long j83 = (j47 << 6) | (j47 >>> 58);
            long j84 = (j38 << c5) | (j38 >>> 61);
            long j85 = j36 ^ ((~j62) & j68);
            j2 = ((~j68) & j80) ^ j62;
            long j86 = j68 ^ ((~j80) & j74);
            long j87 = j80 ^ ((~j74) & j36);
            long j88 = j74 ^ ((~j36) & j62);
            long j89 = j79 ^ ((~j63) & j84);
            long j90 = ((~j84) & j77) ^ j63;
            long j91 = ((~j77) & j64) ^ j84;
            long j92 = j77 ^ ((~j64) & j79);
            long j93 = ((~j79) & j63) ^ j64;
            j11 = j61 ^ ((~j83) & j69);
            long j94 = ((~j69) & j70) ^ j83;
            long j95 = ((~j70) & j66) ^ j69;
            long j96 = j70 ^ ((~j66) & j61);
            j15 = j66 ^ ((~j61) & j83);
            long j97 = j73 ^ ((~j78) & j82);
            long j98 = j78 ^ ((~j82) & j81);
            long j99 = ((~j81) & j71) ^ j82;
            long j100 = j81 ^ ((~j71) & j73);
            long j101 = ((~j73) & j78) ^ j71;
            long j102 = j67 ^ ((~j76) & j65);
            long j103 = ((~j65) & j72) ^ j76;
            long j104 = j65 ^ ((~j72) & j75);
            j24 = j72 ^ ((~j75) & j67);
            j17 = j98;
            j8 = j91;
            j22 = j103;
            j21 = j102;
            j23 = j104;
            j10 = j93;
            j9 = j92;
            j18 = j99;
            j14 = j96;
            j20 = j101;
            j6 = j89;
            j13 = j95;
            j7 = j90;
            c3 = c6;
            c = c4;
            j25 = j75 ^ ((~j67) & j76);
            jArr = jArr;
            i2 = i4 + 1;
            j = j85 ^ h[i4];
            j4 = j87;
            j5 = j88;
            j12 = j94;
            i = i3;
            j19 = j100;
            j16 = j97;
            c2 = c5;
            j3 = j86;
        }
        long[] jArr2 = jArr;
        jArr2[0] = j;
        jArr2[1] = j2;
        jArr2[c] = j3;
        jArr2[c2] = j4;
        jArr2[c3] = j5;
        jArr2[5] = j6;
        jArr2[6] = j7;
        jArr2[7] = j8;
        jArr2[8] = j9;
        jArr2[9] = j10;
        jArr2[10] = j11;
        jArr2[11] = j12;
        jArr2[12] = j13;
        jArr2[13] = j14;
        jArr2[14] = j15;
        jArr2[15] = j16;
        jArr2[16] = j17;
        jArr2[17] = j18;
        jArr2[18] = j19;
        jArr2[19] = j20;
        jArr2[20] = j21;
        jArr2[21] = j22;
        jArr2[22] = j23;
        jArr2[23] = j24;
        jArr2[i] = j25;
    }

    public final void k(int i, int i2) {
        if (i2 < 1 || i2 > 7) {
            throw new IllegalArgumentException("'bits' must be in the range 1 to 7");
        }
        int i3 = this.e;
        if (i3 % 8 != 0) {
            throw new IllegalStateException("attempt to absorb with odd length queue");
        }
        if (this.g) {
            throw new IllegalStateException("attempt to absorb while squeezing");
        }
        byte[] bArr = this.c;
        bArr[i3 >>> 3] = (byte) (i & ((1 << i2) - 1));
        this.e = i3 + i2;
    }

    public sj l() {
        return fa0.R(this);
    }

    public final void m(int i) {
        if (i != 128 && i != 224 && i != 256 && i != 288 && i != 384 && i != 512) {
            throw new IllegalArgumentException("bitLength must be one of 128, 224, 256, 288, 384, or 512.");
        }
        int i2 = 1600 - (i << 1);
        if (i2 <= 0 || i2 >= 1600 || i2 % 64 != 0) {
            throw new IllegalStateException("invalid rate value");
        }
        this.d = i2;
        int i3 = 0;
        while (true) {
            long[] jArr = this.b;
            if (i3 >= jArr.length) {
                Arrays.fill(this.c, (byte) 0);
                this.e = 0;
                this.g = false;
                this.f = (1600 - i2) / 2;
                return;
            }
            jArr[i3] = 0;
            i3++;
        }
    }

    public final void n() {
        m(this.f);
    }

    public final void o(long j, byte[] bArr, int i) {
        boolean z = this.g;
        long[] jArr = this.b;
        byte[] bArr2 = this.c;
        if (!z) {
            int i2 = this.e;
            int i3 = i2 >>> 3;
            bArr2[i3] = (byte) (bArr2[i3] | ((byte) (1 << (i2 & 7))));
            int i4 = i2 + 1;
            this.e = i4;
            if (i4 == this.d) {
                i(0, bArr2);
            } else {
                int i5 = i4 >>> 6;
                int i6 = i4 & 63;
                int i7 = 0;
                for (int i8 = 0; i8 < i5; i8++) {
                    jArr[i8] = jArr[i8] ^ ma0.R(i7, bArr2);
                    i7 += 8;
                }
                if (i6 > 0) {
                    jArr[i5] = (ma0.R(i7, bArr2) & ((1 << i6) - 1)) ^ jArr[i5];
                }
            }
            int i9 = (this.d - 1) >>> 6;
            jArr[i9] = jArr[i9] ^ Long.MIN_VALUE;
            this.e = 0;
            this.g = true;
        }
        long j2 = 0;
        if (j % 8 != 0) {
            throw new IllegalStateException("outputLength not a multiple of 8");
        }
        while (j2 < j) {
            if (this.e == 0) {
                j();
                int i10 = this.d >>> 6;
                int i11 = 0;
                for (int i12 = 0; i12 < i10; i12++) {
                    long j3 = jArr[i12];
                    ma0.L(bArr2, (int) (4294967295L & j3), i11);
                    ma0.L(bArr2, (int) (j3 >>> 32), i11 + 4);
                    i11 += 8;
                }
                this.e = this.d;
            }
            int iMin = (int) Math.min(this.e, j - j2);
            System.arraycopy(bArr2, (this.d - this.e) / 8, bArr, i + ((int) (j2 / 8)), iMin / 8);
            this.e -= iMin;
            j2 += (long) iMin;
        }
    }

    public b70(b70 b70Var) {
        long[] jArr = new long[25];
        this.b = jArr;
        byte[] bArr = new byte[192];
        this.c = bArr;
        this.a = b70Var.a;
        long[] jArr2 = b70Var.b;
        System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
        byte[] bArr2 = b70Var.c;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
        this.d = b70Var.d;
        this.e = b70Var.e;
        this.f = b70Var.f;
        this.g = b70Var.g;
        l();
        a.a();
    }
}
