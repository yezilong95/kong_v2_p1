package defpackage;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.uuid.Uuid;
import okio.ByteString;
import okio.SegmentedByteString;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class p9 implements w9, v9, Cloneable, ByteChannel {
    public th0 b;
    public long c;

    @Override // defpackage.w9
    public final String A(Charset charset) {
        return H(this.c, charset);
    }

    public final byte C() {
        if (this.c == 0) {
            throw new EOFException();
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        int i = th0Var.b;
        int i2 = th0Var.c;
        int i3 = i + 1;
        byte b = th0Var.a[i];
        this.c--;
        if (i3 != i2) {
            th0Var.b = i3;
            return b;
        }
        this.b = th0Var.a();
        vh0.a(th0Var);
        return b;
    }

    public final byte[] D(long j) throws EOFException {
        int iMin;
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (this.c < j) {
            throw new EOFException();
        }
        int i = (int) j;
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i - i2;
            xi.f(i, i2, i3);
            th0 th0Var = this.b;
            if (th0Var == null) {
                iMin = -1;
            } else {
                iMin = Math.min(i3, th0Var.c - th0Var.b);
                int i4 = th0Var.b;
                g7.X(th0Var.a, i2, bArr, i4, i4 + iMin);
                int i5 = th0Var.b + iMin;
                th0Var.b = i5;
                this.c -= (long) iMin;
                if (i5 == th0Var.c) {
                    this.b = th0Var.a();
                    vh0.a(th0Var);
                }
            }
            if (iMin == -1) {
                throw new EOFException();
            }
            i2 += iMin;
        }
        return bArr;
    }

    public final ByteString E(long j) throws EOFException {
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (this.c < j) {
            throw new EOFException();
        }
        if (j < 4096) {
            return new ByteString(D(j));
        }
        ByteString byteStringJ = J((int) j);
        I(j);
        return byteStringJ;
    }

    public final int F() throws EOFException {
        if (this.c < 4) {
            throw new EOFException();
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        int i = th0Var.b;
        int i2 = th0Var.c;
        if (i2 - i < 4) {
            return ((C() & 255) << 24) | ((C() & 255) << 16) | ((C() & 255) << 8) | (C() & 255);
        }
        byte[] bArr = th0Var.a;
        int i3 = i + 3;
        int i4 = ((bArr[i + 1] & 255) << 16) | ((bArr[i] & 255) << 24) | ((bArr[i + 2] & 255) << 8);
        int i5 = i + 4;
        int i6 = i4 | (bArr[i3] & 255);
        this.c -= 4;
        if (i5 != i2) {
            th0Var.b = i5;
            return i6;
        }
        this.b = th0Var.a();
        vh0.a(th0Var);
        return i6;
    }

    public final short G() throws EOFException {
        if (this.c < 2) {
            throw new EOFException();
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        int i = th0Var.b;
        int i2 = th0Var.c;
        if (i2 - i < 2) {
            return (short) (((C() & 255) << 8) | (C() & 255));
        }
        int i3 = i + 1;
        byte[] bArr = th0Var.a;
        int i4 = (bArr[i] & 255) << 8;
        int i5 = i + 2;
        int i6 = (bArr[i3] & 255) | i4;
        this.c -= 2;
        if (i5 == i2) {
            this.b = th0Var.a();
            vh0.a(th0Var);
        } else {
            th0Var.b = i5;
        }
        return (short) i6;
    }

    public final String H(long j, Charset charset) throws EOFException {
        oy.j(charset, "charset");
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j).toString());
        }
        if (this.c < j) {
            throw new EOFException();
        }
        if (j == 0) {
            return ExtensionRequestData.EMPTY_VALUE;
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        int i = th0Var.b;
        if (((long) i) + j > th0Var.c) {
            return new String(D(j), charset);
        }
        int i2 = (int) j;
        String str = new String(th0Var.a, i, i2, charset);
        int i3 = th0Var.b + i2;
        th0Var.b = i3;
        this.c -= j;
        if (i3 == th0Var.c) {
            this.b = th0Var.a();
            vh0.a(th0Var);
        }
        return str;
    }

    public final void I(long j) throws EOFException {
        while (j > 0) {
            th0 th0Var = this.b;
            if (th0Var == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, th0Var.c - th0Var.b);
            long j2 = iMin;
            this.c -= j2;
            j -= j2;
            int i = th0Var.b + iMin;
            th0Var.b = i;
            if (i == th0Var.c) {
                this.b = th0Var.a();
                vh0.a(th0Var);
            }
        }
    }

    public final ByteString J(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        xi.f(this.c, 0L, i);
        th0 th0Var = this.b;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            oy.g(th0Var);
            int i5 = th0Var.c;
            int i6 = th0Var.b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            th0Var = th0Var.f;
        }
        byte[][] bArr = new byte[i4][];
        int[] iArr = new int[i4 * 2];
        th0 th0Var2 = this.b;
        int i7 = 0;
        while (i2 < i) {
            oy.g(th0Var2);
            bArr[i7] = th0Var2.a;
            i2 += th0Var2.c - th0Var2.b;
            iArr[i7] = Math.min(i2, i);
            iArr[i7 + i4] = th0Var2.b;
            th0Var2.d = true;
            i7++;
            th0Var2 = th0Var2.f;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    public final th0 K(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        th0 th0Var = this.b;
        if (th0Var == null) {
            th0 th0VarB = vh0.b();
            this.b = th0VarB;
            th0VarB.g = th0VarB;
            th0VarB.f = th0VarB;
            return th0VarB;
        }
        th0 th0Var2 = th0Var.g;
        oy.g(th0Var2);
        if (th0Var2.c + i <= 8192 && th0Var2.e) {
            return th0Var2;
        }
        th0 th0VarB2 = vh0.b();
        th0Var2.b(th0VarB2);
        return th0VarB2;
    }

    public final void L(ByteString byteString) {
        oy.j(byteString, "byteString");
        byteString.write$okio(this, 0, byteString.size());
    }

    public final void M(byte[] bArr, int i, int i2) {
        oy.j(bArr, "source");
        long j = i2;
        xi.f(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            th0 th0VarK = K(1);
            int iMin = Math.min(i3 - i, 8192 - th0VarK.c);
            int i4 = i + iMin;
            g7.X(bArr, th0VarK.c, th0VarK.a, i, i4);
            th0VarK.c += iMin;
            i = i4;
        }
        this.c += j;
    }

    public final void N(uj0 uj0Var) {
        oy.j(uj0Var, "source");
        while (uj0Var.d(8192L, this) != -1) {
        }
    }

    public final void O(int i) {
        th0 th0VarK = K(1);
        int i2 = th0VarK.c;
        th0VarK.c = i2 + 1;
        th0VarK.a[i2] = (byte) i;
        this.c++;
    }

    public final void P(long j) {
        if (j == 0) {
            O(48);
            return;
        }
        long j2 = (j >>> 1) | j;
        long j3 = j2 | (j2 >>> 2);
        long j4 = j3 | (j3 >>> 4);
        long j5 = j4 | (j4 >>> 8);
        long j6 = j5 | (j5 >>> 16);
        long j7 = j6 | (j6 >>> 32);
        long j8 = j7 - ((j7 >>> 1) & 6148914691236517205L);
        long j9 = ((j8 >>> 2) & 3689348814741910323L) + (j8 & 3689348814741910323L);
        long j10 = ((j9 >>> 4) + j9) & 1085102592571150095L;
        long j11 = j10 + (j10 >>> 8);
        long j12 = j11 + (j11 >>> 16);
        int i = (int) ((((j12 & 63) + ((j12 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        th0 th0VarK = K(i);
        int i2 = th0VarK.c;
        for (int i3 = (i2 + i) - 1; i3 >= i2; i3--) {
            th0VarK.a[i3] = b.a[(int) (15 & j)];
            j >>>= 4;
        }
        th0VarK.c += i;
        this.c += (long) i;
    }

    public final void Q(int i) {
        th0 th0VarK = K(4);
        int i2 = th0VarK.c;
        byte[] bArr = th0VarK.a;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        bArr[i2 + 1] = (byte) ((i >>> 16) & 255);
        bArr[i2 + 2] = (byte) ((i >>> 8) & 255);
        bArr[i2 + 3] = (byte) (i & 255);
        th0VarK.c = i2 + 4;
        this.c += 4;
    }

    public final void R(String str) {
        oy.j(str, "string");
        S(str, 0, str.length());
    }

    public final void S(String str, int i, int i2) {
        char cCharAt;
        oy.j(str, "string");
        if (i < 0) {
            throw new IllegalArgumentException(hk.g(i, "beginIndex < 0: ").toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(hk.f(i2, i, "endIndex < beginIndex: ", " < ").toString());
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i2 + " > " + str.length()).toString());
        }
        while (i < i2) {
            char cCharAt2 = str.charAt(i);
            if (cCharAt2 < 128) {
                th0 th0VarK = K(1);
                int i3 = th0VarK.c - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                byte[] bArr = th0VarK.a;
                bArr[i + i3] = (byte) cCharAt2;
                while (true) {
                    i = i4;
                    if (i >= iMin || (cCharAt = str.charAt(i)) >= 128) {
                        break;
                    }
                    i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt;
                }
                int i5 = th0VarK.c;
                int i6 = (i3 + i) - i5;
                th0VarK.c = i5 + i6;
                this.c += (long) i6;
            } else {
                if (cCharAt2 < 2048) {
                    th0 th0VarK2 = K(2);
                    int i7 = th0VarK2.c;
                    byte[] bArr2 = th0VarK2.a;
                    bArr2[i7] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i7 + 1] = (byte) ((cCharAt2 & '?') | Uuid.SIZE_BITS);
                    th0VarK2.c = i7 + 2;
                    this.c += 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    th0 th0VarK3 = K(3);
                    int i8 = th0VarK3.c;
                    byte[] bArr3 = th0VarK3.a;
                    bArr3[i8] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i8 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | Uuid.SIZE_BITS);
                    bArr3[i8 + 2] = (byte) ((cCharAt2 & '?') | Uuid.SIZE_BITS);
                    th0VarK3.c = i8 + 3;
                    this.c += 3;
                } else {
                    int i9 = i + 1;
                    char cCharAt3 = i9 < i2 ? str.charAt(i9) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        O(63);
                        i = i9;
                    } else {
                        int i10 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        th0 th0VarK4 = K(4);
                        int i11 = th0VarK4.c;
                        byte[] bArr4 = th0VarK4.a;
                        bArr4[i11] = (byte) ((i10 >> 18) | 240);
                        bArr4[i11 + 1] = (byte) (((i10 >> 12) & 63) | Uuid.SIZE_BITS);
                        bArr4[i11 + 2] = (byte) (((i10 >> 6) & 63) | Uuid.SIZE_BITS);
                        bArr4[i11 + 3] = (byte) ((i10 & 63) | Uuid.SIZE_BITS);
                        th0VarK4.c = i11 + 4;
                        this.c += 4;
                        i += 2;
                    }
                }
                i++;
            }
        }
    }

    public final void T(int i) {
        String str;
        int i2 = 0;
        if (i < 128) {
            O(i);
            return;
        }
        if (i < 2048) {
            th0 th0VarK = K(2);
            int i3 = th0VarK.c;
            byte[] bArr = th0VarK.a;
            bArr[i3] = (byte) ((i >> 6) | 192);
            bArr[1 + i3] = (byte) ((i & 63) | Uuid.SIZE_BITS);
            th0VarK.c = i3 + 2;
            this.c += 2;
            return;
        }
        if (55296 <= i && i < 57344) {
            O(63);
            return;
        }
        if (i < 65536) {
            th0 th0VarK2 = K(3);
            int i4 = th0VarK2.c;
            byte[] bArr2 = th0VarK2.a;
            bArr2[i4] = (byte) ((i >> 12) | 224);
            bArr2[1 + i4] = (byte) (((i >> 6) & 63) | Uuid.SIZE_BITS);
            bArr2[2 + i4] = (byte) ((i & 63) | Uuid.SIZE_BITS);
            th0VarK2.c = i4 + 3;
            this.c += 3;
            return;
        }
        if (i <= 1114111) {
            th0 th0VarK3 = K(4);
            int i5 = th0VarK3.c;
            byte[] bArr3 = th0VarK3.a;
            bArr3[i5] = (byte) ((i >> 18) | 240);
            bArr3[1 + i5] = (byte) (((i >> 12) & 63) | Uuid.SIZE_BITS);
            bArr3[2 + i5] = (byte) (((i >> 6) & 63) | Uuid.SIZE_BITS);
            bArr3[3 + i5] = (byte) ((i & 63) | Uuid.SIZE_BITS);
            th0VarK3.c = i5 + 4;
            this.c += 4;
            return;
        }
        StringBuilder sb = new StringBuilder("Unexpected code point: 0x");
        if (i != 0) {
            char[] cArr = k9.a;
            char[] cArr2 = {cArr[(i >> 28) & 15], cArr[(i >> 24) & 15], cArr[(i >> 20) & 15], cArr[(i >> 16) & 15], cArr[(i >> 12) & 15], cArr[(i >> 8) & 15], cArr[(i >> 4) & 15], cArr[i & 15]};
            while (i2 < 8 && cArr2[i2] == '0') {
                i2++;
            }
            if (i2 < 0) {
                throw new IndexOutOfBoundsException(hk.h(i2, "startIndex: ", ", endIndex: 8, size: 8"));
            }
            if (i2 > 8) {
                throw new IllegalArgumentException(hk.h(i2, "startIndex: ", " > endIndex: 8"));
            }
            str = new String(cArr2, i2, 8 - i2);
        } else {
            str = "0";
        }
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean a() {
        return this.c == 0;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return jo0.d;
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) {
        th0 th0VarB;
        oy.j(p9Var, "source");
        if (p9Var == this) {
            throw new IllegalArgumentException("source == this");
        }
        xi.f(p9Var.c, 0L, j);
        while (j > 0) {
            th0 th0Var = p9Var.b;
            oy.g(th0Var);
            int i = th0Var.c;
            th0 th0Var2 = p9Var.b;
            oy.g(th0Var2);
            long j2 = i - th0Var2.b;
            int i2 = 0;
            if (j < j2) {
                th0 th0Var3 = this.b;
                th0 th0Var4 = th0Var3 != null ? th0Var3.g : null;
                if (th0Var4 != null && th0Var4.e) {
                    if ((((long) th0Var4.c) + j) - ((long) (th0Var4.d ? 0 : th0Var4.b)) <= 8192) {
                        th0 th0Var5 = p9Var.b;
                        oy.g(th0Var5);
                        th0Var5.d(th0Var4, (int) j);
                        p9Var.c -= j;
                        this.c += j;
                        return;
                    }
                }
                th0 th0Var6 = p9Var.b;
                oy.g(th0Var6);
                int i3 = (int) j;
                if (i3 <= 0 || i3 > th0Var6.c - th0Var6.b) {
                    throw new IllegalArgumentException("byteCount out of range");
                }
                if (i3 >= 1024) {
                    th0VarB = th0Var6.c();
                } else {
                    th0VarB = vh0.b();
                    int i4 = th0Var6.b;
                    g7.X(th0Var6.a, 0, th0VarB.a, i4, i4 + i3);
                }
                th0VarB.c = th0VarB.b + i3;
                th0Var6.b += i3;
                th0 th0Var7 = th0Var6.g;
                oy.g(th0Var7);
                th0Var7.b(th0VarB);
                p9Var.b = th0VarB;
            }
            th0 th0Var8 = p9Var.b;
            oy.g(th0Var8);
            long j3 = th0Var8.c - th0Var8.b;
            p9Var.b = th0Var8.a();
            th0 th0Var9 = this.b;
            if (th0Var9 == null) {
                this.b = th0Var8;
                th0Var8.g = th0Var8;
                th0Var8.f = th0Var8;
            } else {
                th0 th0Var10 = th0Var9.g;
                oy.g(th0Var10);
                th0Var10.b(th0Var8);
                th0 th0Var11 = th0Var8.g;
                if (th0Var11 == th0Var8) {
                    throw new IllegalStateException("cannot compact");
                }
                oy.g(th0Var11);
                if (th0Var11.e) {
                    int i5 = th0Var8.c - th0Var8.b;
                    th0 th0Var12 = th0Var8.g;
                    oy.g(th0Var12);
                    int i6 = 8192 - th0Var12.c;
                    th0 th0Var13 = th0Var8.g;
                    oy.g(th0Var13);
                    if (!th0Var13.d) {
                        th0 th0Var14 = th0Var8.g;
                        oy.g(th0Var14);
                        i2 = th0Var14.b;
                    }
                    if (i5 <= i6 + i2) {
                        th0 th0Var15 = th0Var8.g;
                        oy.g(th0Var15);
                        th0Var8.d(th0Var15, i5);
                        th0Var8.a();
                        vh0.a(th0Var8);
                    }
                }
            }
            p9Var.c -= j3;
            this.c += j3;
            j -= j3;
        }
    }

    public final Object clone() {
        p9 p9Var = new p9();
        if (this.c == 0) {
            return p9Var;
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        th0 th0VarC = th0Var.c();
        p9Var.b = th0VarC;
        th0VarC.g = th0VarC;
        th0VarC.f = th0VarC;
        for (th0 th0Var2 = th0Var.f; th0Var2 != th0Var; th0Var2 = th0Var2.f) {
            th0 th0Var3 = th0VarC.g;
            oy.g(th0Var3);
            oy.g(th0Var2);
            th0Var3.b(th0Var2.c());
        }
        p9Var.c = this.c;
        return p9Var;
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) {
        oy.j(p9Var, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        long j2 = this.c;
        if (j2 == 0) {
            return -1L;
        }
        if (j > j2) {
            j = j2;
        }
        p9Var.c(j, this);
        return j;
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        boolean z2 = false;
        if (!(obj instanceof p9)) {
            return false;
        }
        long j = this.c;
        p9 p9Var = (p9) obj;
        if (j != p9Var.c) {
            return false;
        }
        if (j == 0) {
            return true;
        }
        th0 th0Var = this.b;
        oy.g(th0Var);
        th0 th0Var2 = p9Var.b;
        oy.g(th0Var2);
        int i = th0Var.b;
        int i2 = th0Var2.b;
        long j2 = 0;
        while (j2 < this.c) {
            long jMin = Math.min(th0Var.c - i, th0Var2.c - i2);
            long j3 = 0;
            while (j3 < jMin) {
                int i3 = i + 1;
                boolean z3 = z;
                byte b = th0Var.a[i];
                int i4 = i2 + 1;
                boolean z4 = z2;
                if (b != th0Var2.a[i2]) {
                    return z4;
                }
                j3++;
                i2 = i4;
                i = i3;
                z = z3;
                z2 = z4;
            }
            boolean z5 = z;
            boolean z6 = z2;
            if (i == th0Var.c) {
                th0 th0Var3 = th0Var.f;
                oy.g(th0Var3);
                i = th0Var3.b;
                th0Var = th0Var3;
            }
            if (i2 == th0Var2.c) {
                th0Var2 = th0Var2.f;
                oy.g(th0Var2);
                i2 = th0Var2.b;
            }
            j2 += jMin;
            z = z5;
            z2 = z6;
        }
        return z;
    }

    public final int hashCode() {
        th0 th0Var = this.b;
        if (th0Var == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = th0Var.c;
            for (int i3 = th0Var.b; i3 < i2; i3++) {
                i = (i * 31) + th0Var.a[i3];
            }
            th0Var = th0Var.f;
            oy.g(th0Var);
        } while (th0Var != this.b);
        return i;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // defpackage.w9
    public final int o(bb0 bb0Var) throws EOFException {
        oy.j(bb0Var, "options");
        int iB = b.b(this, bb0Var, false);
        if (iB == -1) {
            return -1;
        }
        I(bb0Var.b[iB].size());
        return iB;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        oy.j(byteBuffer, "sink");
        th0 th0Var = this.b;
        if (th0Var == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), th0Var.c - th0Var.b);
        byteBuffer.put(th0Var.a, th0Var.b, iMin);
        int i = th0Var.b + iMin;
        th0Var.b = i;
        this.c -= (long) iMin;
        if (i == th0Var.c) {
            this.b = th0Var.a();
            vh0.a(th0Var);
        }
        return iMin;
    }

    public final byte t(long j) {
        xi.f(this.c, j, 1L);
        th0 th0Var = this.b;
        if (th0Var == null) {
            oy.g(null);
            throw null;
        }
        long j2 = this.c;
        if (j2 - j < j) {
            while (j2 > j) {
                th0Var = th0Var.g;
                oy.g(th0Var);
                j2 -= (long) (th0Var.c - th0Var.b);
            }
            return th0Var.a[(int) ((((long) th0Var.b) + j) - j2)];
        }
        long j3 = 0;
        while (true) {
            int i = th0Var.c;
            int i2 = th0Var.b;
            long j4 = ((long) (i - i2)) + j3;
            if (j4 > j) {
                return th0Var.a[(int) ((((long) i2) + j) - j3)];
            }
            th0Var = th0Var.f;
            oy.g(th0Var);
            j3 = j4;
        }
    }

    public final String toString() {
        long j = this.c;
        if (j <= 2147483647L) {
            return J((int) j).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.c).toString());
    }

    @Override // defpackage.v9
    public final /* bridge */ /* synthetic */ v9 u(String str) {
        R(str);
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        oy.j(byteBuffer, "source");
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            th0 th0VarK = K(1);
            int iMin = Math.min(i, 8192 - th0VarK.c);
            byteBuffer.get(th0VarK.a, th0VarK.c, iMin);
            i -= iMin;
            th0VarK.c += iMin;
        }
        this.c += (long) iRemaining;
        return iRemaining;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, defpackage.hj0
    public final void close() {
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() {
    }
}
