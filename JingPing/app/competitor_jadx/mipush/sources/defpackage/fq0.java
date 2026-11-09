package defpackage;

import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.a;

/* JADX INFO: loaded from: classes.dex */
public final class fq0 implements po, vd0 {
    public static final long[] p = {4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};
    public final CryptoServicePurpose a;
    public final byte[] b;
    public int c;
    public long d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public long j;
    public long k;
    public long l;
    public long m;
    public final long[] n;
    public int o;

    public fq0() {
        CryptoServicePurpose cryptoServicePurpose = CryptoServicePurpose.ANY;
        this.b = new byte[8];
        this.n = new long[80];
        this.a = cryptoServicePurpose;
        this.c = 0;
        p();
        fa0.R(this);
        a.a();
        p();
    }

    public static long i(long j, long j2, long j3) {
        return ((~j) & j3) ^ (j2 & j);
    }

    public static long j(long j, long j2, long j3) {
        return ((j & j3) ^ (j & j2)) ^ (j2 & j3);
    }

    public static long k(long j) {
        return ((j >>> 39) | (j << 25)) ^ (((j << 36) | (j >>> 28)) ^ ((j << 30) | (j >>> 34)));
    }

    public static long l(long j) {
        return ((j >>> 41) | (j << 23)) ^ (((j << 50) | (j >>> 14)) ^ ((j << 46) | (j >>> 18)));
    }

    @Override // defpackage.vd0
    public final vd0 a() {
        return new fq0(this);
    }

    @Override // defpackage.po
    public final String b() {
        return "SHA-512";
    }

    @Override // defpackage.po
    public final int c() {
        return 64;
    }

    @Override // defpackage.po
    public final int d(int i, byte[] bArr) {
        long j = this.d;
        if (j > 2305843009213693951L) {
            this.e += j >>> 61;
            this.d = j & 2305843009213693951L;
        }
        long j2 = this.d << 3;
        long j3 = this.e;
        byte b = -128;
        while (true) {
            f(b);
            if (this.c == 0) {
                break;
            }
            b = 0;
        }
        if (this.o > 14) {
            n();
        }
        long[] jArr = this.n;
        jArr[14] = j3;
        jArr[15] = j2;
        n();
        ma0.S(this.f, bArr, i);
        ma0.S(this.g, bArr, i + 8);
        ma0.S(this.h, bArr, i + 16);
        ma0.S(this.i, bArr, i + 24);
        ma0.S(this.j, bArr, i + 32);
        ma0.S(this.k, bArr, i + 40);
        ma0.S(this.l, bArr, i + 48);
        ma0.S(this.m, bArr, i + 56);
        p();
        return 64;
    }

    @Override // defpackage.po
    public final void e(byte[] bArr, int i, int i2) {
        while (this.c != 0 && i2 > 0) {
            f(bArr[i]);
            i++;
            i2--;
        }
        while (true) {
            byte[] bArr2 = this.b;
            if (i2 < bArr2.length) {
                break;
            }
            o(i, bArr);
            i += bArr2.length;
            i2 -= bArr2.length;
            this.d += (long) bArr2.length;
        }
        while (i2 > 0) {
            f(bArr[i]);
            i++;
            i2--;
        }
    }

    @Override // defpackage.po
    public final void f(byte b) {
        int i = this.c;
        int i2 = i + 1;
        this.c = i2;
        byte[] bArr = this.b;
        bArr[i] = b;
        if (i2 == bArr.length) {
            o(0, bArr);
            this.c = 0;
        }
        this.d++;
    }

    @Override // defpackage.vd0
    public final void g(vd0 vd0Var) {
        m((fq0) vd0Var);
    }

    public final void m(fq0 fq0Var) {
        byte[] bArr = fq0Var.b;
        System.arraycopy(bArr, 0, this.b, 0, bArr.length);
        this.c = fq0Var.c;
        this.d = fq0Var.d;
        this.e = fq0Var.e;
        this.f = fq0Var.f;
        this.g = fq0Var.g;
        this.h = fq0Var.h;
        this.i = fq0Var.i;
        this.j = fq0Var.j;
        this.k = fq0Var.k;
        this.l = fq0Var.l;
        this.m = fq0Var.m;
        long[] jArr = this.n;
        long[] jArr2 = fq0Var.n;
        System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
        this.o = fq0Var.o;
    }

    public final void n() {
        long[] jArr;
        long j = this.d;
        if (j > 2305843009213693951L) {
            this.e += j >>> 61;
            this.d = j & 2305843009213693951L;
        }
        int i = 16;
        while (true) {
            jArr = this.n;
            if (i > 79) {
                break;
            }
            long j2 = jArr[i - 2];
            long j3 = ((j2 >>> 6) ^ (((j2 << 45) | (j2 >>> 19)) ^ ((j2 << 3) | (j2 >>> 61)))) + jArr[i - 7];
            long j4 = jArr[i - 15];
            jArr[i] = j3 + ((((j4 >>> 8) | (j4 << 56)) ^ ((j4 << 63) | (j4 >>> 1))) ^ (j4 >>> 7)) + jArr[i - 16];
            i++;
        }
        long j5 = this.f;
        long j6 = this.g;
        long j7 = this.h;
        long j8 = this.i;
        long j9 = this.j;
        long j10 = this.k;
        long j11 = this.l;
        long j12 = this.m;
        long j13 = j11;
        long j14 = j10;
        int i2 = 0;
        int i3 = 0;
        long j15 = j8;
        long j16 = j9;
        long j17 = j6;
        long j18 = j7;
        long jK = j5;
        while (i2 < 10) {
            long jL = l(j16) + i(j16, j14, j13);
            long[] jArr2 = p;
            int i4 = i3 + 1;
            long j19 = jL + jArr2[i3] + jArr[i3] + j12;
            long j20 = j15 + j19;
            long j21 = jK;
            long j22 = j17;
            long jK2 = k(jK) + j(jK, j17, j18) + j19;
            int i5 = i3 + 2;
            long jL2 = l(j20) + i(j20, j16, j14) + jArr2[i4] + jArr[i4] + j13;
            long j23 = j18 + jL2;
            long jK3 = k(jK2) + j(jK2, j21, j22) + jL2;
            int i6 = i3 + 3;
            long jL3 = l(j23) + i(j23, j20, j16) + jArr2[i5] + jArr[i5] + j14;
            long j24 = j22 + jL3;
            long jK4 = k(jK3) + j(jK3, jK2, j21) + jL3;
            int i7 = i3 + 4;
            long jL4 = l(j24) + i(j24, j23, j20) + jArr2[i6] + jArr[i6] + j16;
            long j25 = j21 + jL4;
            long jK5 = k(jK4) + j(jK4, jK3, jK2) + jL4;
            int i8 = i3 + 5;
            long jL5 = l(j25) + i(j25, j24, j23) + jArr2[i7] + jArr[i7] + j20;
            long j26 = jK2 + jL5;
            long jK6 = k(jK5) + j(jK5, jK4, jK3) + jL5;
            int i9 = i3 + 6;
            long jL6 = l(j26) + i(j26, j25, j24) + jArr2[i8] + jArr[i8] + j23;
            long j27 = jK3 + jL6;
            long jK7 = k(jK6) + j(jK6, jK5, jK4) + jL6;
            int i10 = i3 + 7;
            long jL7 = l(j27) + i(j27, j26, j25) + jArr2[i9] + jArr[i9] + j24;
            long j28 = jK4 + jL7;
            long jK8 = k(jK7) + j(jK7, jK6, jK5) + jL7;
            i3 += 8;
            long jL8 = l(j28) + i(j28, j27, j26) + jArr2[i10] + jArr[i10] + j25;
            j16 = jK5 + jL8;
            j18 = jK7;
            jK = k(jK8) + j(jK8, jK7, jK6) + jL8;
            i2++;
            j17 = jK8;
            j15 = jK6;
            j14 = j28;
            j13 = j27;
            j12 = j26;
        }
        this.f += jK;
        this.g += j17;
        this.h += j18;
        this.i += j15;
        this.j += j16;
        this.k += j14;
        this.l += j13;
        this.m += j12;
        this.o = 0;
        for (int i11 = 0; i11 < 16; i11++) {
            jArr[i11] = 0;
        }
    }

    public final void o(int i, byte[] bArr) {
        int i2 = this.o;
        int iG = ma0.g(i, bArr);
        this.n[i2] = (((long) ma0.g(i + 4, bArr)) & 4294967295L) | ((((long) iG) & 4294967295L) << 32);
        int i3 = this.o + 1;
        this.o = i3;
        if (i3 == 16) {
            n();
        }
    }

    public final void p() {
        this.d = 0L;
        this.e = 0L;
        int i = 0;
        this.c = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = 0;
            i2++;
        }
        this.o = 0;
        while (true) {
            long[] jArr = this.n;
            if (i == jArr.length) {
                this.f = 7640891576956012808L;
                this.g = -4942790177534073029L;
                this.h = 4354685564936845355L;
                this.i = -6534734903238641935L;
                this.j = 5840696475078001361L;
                this.k = -7276294671716946913L;
                this.l = 2270897969802886507L;
                this.m = 6620516959819538809L;
                return;
            }
            jArr[i] = 0;
            i++;
        }
    }

    public final String toString() {
        return "SHA512[Java]()";
    }

    public fq0(fq0 fq0Var) {
        this.b = new byte[8];
        this.n = new long[80];
        this.a = fq0Var.a;
        m(fq0Var);
        fa0.R(this);
        a.a();
    }
}
