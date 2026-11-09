package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class sc0 implements w9 {
    public final uj0 b;
    public final p9 c;
    public boolean d;

    public sc0(uj0 uj0Var) {
        oy.j(uj0Var, "source");
        this.b = uj0Var;
        this.c = new p9();
    }

    @Override // defpackage.w9
    public final String A(Charset charset) {
        p9 p9Var = this.c;
        p9Var.N(this.b);
        return p9Var.H(p9Var.c, charset);
    }

    public final byte C() throws EOFException {
        K(1L);
        return this.c.C();
    }

    public final ByteString D(long j) throws EOFException {
        K(j);
        return this.c.E(j);
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d0, code lost:
    
        if (r13 == 0) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d2, code lost:
    
        r17 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d5, code lost:
    
        r3 = defpackage.k9.a;
        r4 = r3[(r15 >> 4) & 15];
        r3 = r3[r15 & 15];
        r5 = new char[2];
        r5[r21] = r4;
        r5[r16] = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f6, code lost:
    
        throw new java.lang.NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(new java.lang.String(r5)));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long E() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sc0.E():long");
    }

    public final int F() throws EOFException {
        K(4L);
        return this.c.F();
    }

    public final int G() throws EOFException {
        K(4L);
        int iF = this.c.F();
        return ((iF & 255) << 24) | (((-16777216) & iF) >>> 24) | ((16711680 & iF) >>> 8) | ((65280 & iF) << 8);
    }

    public final short H() throws EOFException {
        K(2L);
        return this.c.G();
    }

    public final String I(long j) {
        if (j < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j).toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jT = t((byte) 10, 0L, j2);
        p9 p9Var = this.c;
        if (jT != -1) {
            return b.a(jT, p9Var);
        }
        if (j2 < Long.MAX_VALUE && J(j2) && p9Var.t(j2 - 1) == 13 && J(j2 + 1) && p9Var.t(j2) == 10) {
            return b.a(j2, p9Var);
        }
        p9 p9Var2 = new p9();
        long jMin = Math.min(32, p9Var.c);
        long j3 = 0;
        p9Var.getClass();
        oy.j(p9Var2, "out");
        xi.f(p9Var.c, 0L, jMin);
        if (jMin != 0) {
            p9Var2.c += jMin;
            th0 th0Var = p9Var.b;
            while (true) {
                oy.g(th0Var);
                long j4 = th0Var.c - th0Var.b;
                if (j3 < j4) {
                    break;
                }
                j3 -= j4;
                th0Var = th0Var.f;
            }
            long j5 = j3;
            long j6 = jMin;
            while (j6 > 0) {
                oy.g(th0Var);
                th0 th0VarC = th0Var.c();
                int i = th0VarC.b + ((int) j5);
                th0VarC.b = i;
                th0VarC.c = Math.min(i + ((int) j6), th0VarC.c);
                th0 th0Var2 = p9Var2.b;
                if (th0Var2 == null) {
                    th0VarC.g = th0VarC;
                    th0VarC.f = th0VarC;
                    p9Var2.b = th0VarC;
                } else {
                    th0 th0Var3 = th0Var2.g;
                    oy.g(th0Var3);
                    th0Var3.b(th0VarC);
                }
                j6 -= (long) (th0VarC.c - th0VarC.b);
                th0Var = th0Var.f;
                j5 = 0;
            }
        }
        throw new EOFException("\\n not found: limit=" + Math.min(p9Var.c, j) + " content=" + p9Var2.E(p9Var2.c).hex() + (char) 8230);
    }

    public final boolean J(long j) {
        p9 p9Var;
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        do {
            p9Var = this.c;
            if (p9Var.c >= j) {
                return true;
            }
        } while (this.b.d(8192L, p9Var) != -1);
        return false;
    }

    public final void K(long j) throws EOFException {
        if (!J(j)) {
            throw new EOFException();
        }
    }

    public final void L(long j) throws EOFException {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            p9 p9Var = this.c;
            if (p9Var.c == 0 && this.b.d(8192L, p9Var) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, p9Var.c);
            p9Var.I(jMin);
            j -= jMin;
        }
    }

    public final boolean a() {
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        p9 p9Var = this.c;
        return p9Var.a() && this.b.d(8192L, p9Var) == -1;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.b.b();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (this.d) {
            return;
        }
        this.d = true;
        this.b.close();
        p9 p9Var = this.c;
        p9Var.I(p9Var.c);
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) {
        oy.j(p9Var, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        }
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        p9 p9Var2 = this.c;
        if (p9Var2.c == 0 && this.b.d(8192L, p9Var2) == -1) {
            return -1L;
        }
        return p9Var2.d(Math.min(j, p9Var2.c), p9Var);
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.d;
    }

    @Override // defpackage.w9
    public final int o(bb0 bb0Var) throws EOFException {
        oy.j(bb0Var, "options");
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            p9 p9Var = this.c;
            int iB = b.b(p9Var, bb0Var, true);
            if (iB != -2) {
                if (iB != -1) {
                    p9Var.I(bb0Var.b[iB].size());
                    return iB;
                }
            } else if (this.b.d(8192L, p9Var) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        oy.j(byteBuffer, "sink");
        p9 p9Var = this.c;
        if (p9Var.c == 0 && this.b.d(8192L, p9Var) == -1) {
            return -1;
        }
        return p9Var.read(byteBuffer);
    }

    public final long t(byte b, long j, long j2) {
        th0 th0Var;
        long j3;
        long j4;
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        long jMax = 0;
        if (0 > j2) {
            throw new IllegalArgumentException(("fromIndex=0 toIndex=" + j2).toString());
        }
        while (jMax < j2) {
            p9 p9Var = this.c;
            p9Var.getClass();
            long j5 = 0;
            if (0 > jMax || jMax > j2) {
                throw new IllegalArgumentException(("size=" + p9Var.c + " fromIndex=" + jMax + " toIndex=" + j2).toString());
            }
            long j6 = p9Var.c;
            long j7 = j2 > j6 ? j6 : j2;
            if (jMax == j7 || (th0Var = p9Var.b) == null) {
                j3 = -1;
                j4 = -1;
            } else if (j6 - jMax < jMax) {
                while (j6 > jMax) {
                    th0Var = th0Var.g;
                    oy.g(th0Var);
                    j6 -= (long) (th0Var.c - th0Var.b);
                }
                long j8 = jMax;
                while (j6 < j7) {
                    j3 = -1;
                    int iMin = (int) Math.min(th0Var.c, (((long) th0Var.b) + j7) - j6);
                    for (int i = (int) ((((long) th0Var.b) + j8) - j6); i < iMin; i++) {
                        if (th0Var.a[i] == b) {
                            j4 = ((long) (i - th0Var.b)) + j6;
                            break;
                        }
                    }
                    j8 = j6 + ((long) (th0Var.c - th0Var.b));
                    th0Var = th0Var.f;
                    oy.g(th0Var);
                    j6 = j8;
                }
                j3 = -1;
                j4 = -1;
            } else {
                j3 = -1;
                while (true) {
                    long j9 = ((long) (th0Var.c - th0Var.b)) + j5;
                    if (j9 > jMax) {
                        break;
                    }
                    th0Var = th0Var.f;
                    oy.g(th0Var);
                    j5 = j9;
                }
                long j10 = jMax;
                while (j5 < j7) {
                    long j11 = j10;
                    int iMin2 = (int) Math.min(th0Var.c, (((long) th0Var.b) + j7) - j5);
                    for (int i2 = (int) ((((long) th0Var.b) + j11) - j5); i2 < iMin2; i2++) {
                        if (th0Var.a[i2] == b) {
                            j4 = ((long) (i2 - th0Var.b)) + j5;
                            break;
                        }
                    }
                    j10 = ((long) (th0Var.c - th0Var.b)) + j5;
                    th0Var = th0Var.f;
                    oy.g(th0Var);
                    j5 = j10;
                }
                j4 = -1;
            }
            if (j4 != j3) {
                return j4;
            }
            long j12 = p9Var.c;
            if (j12 >= j2 || this.b.d(8192L, p9Var) == j3) {
                return j3;
            }
            jMax = Math.max(jMax, j12);
        }
        return -1L;
    }

    public final String toString() {
        return "buffer(" + this.b + ')';
    }
}
