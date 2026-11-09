package defpackage;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class i7 implements hj0 {
    public final /* synthetic */ int b = 1;
    public final pj0 c;
    public final Object d;

    public i7(OutputStream outputStream, pj0 pj0Var) {
        this.d = outputStream;
        this.c = pj0Var;
    }

    @Override // defpackage.hj0
    public final jo0 b() {
        switch (this.b) {
        }
        return this.c;
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) throws IOException {
        switch (this.b) {
            case 0:
                xi.f(p9Var.c, 0L, j);
                long j2 = j;
                while (true) {
                    long j3 = 0;
                    if (j2 <= 0) {
                        return;
                    }
                    th0 th0Var = p9Var.b;
                    oy.g(th0Var);
                    while (true) {
                        if (j3 < 65536) {
                            j3 += (long) (th0Var.c - th0Var.b);
                            if (j3 >= j2) {
                                j3 = j2;
                            } else {
                                th0Var = th0Var.f;
                                oy.g(th0Var);
                            }
                        }
                    }
                    i7 i7Var = (i7) this.d;
                    pj0 pj0Var = this.c;
                    pj0Var.h();
                    try {
                        try {
                            i7Var.c(j3, p9Var);
                            if (pj0Var.i()) {
                                throw pj0Var.k(null);
                            }
                            j2 -= j3;
                        } catch (IOException e) {
                            if (!pj0Var.i()) {
                                throw e;
                            }
                            throw pj0Var.k(e);
                        }
                    } catch (Throwable th) {
                        pj0Var.i();
                        throw th;
                    }
                }
                break;
            default:
                xi.f(p9Var.c, 0L, j);
                while (j > 0) {
                    this.c.f();
                    th0 th0Var2 = p9Var.b;
                    oy.g(th0Var2);
                    int iMin = (int) Math.min(j, th0Var2.c - th0Var2.b);
                    ((OutputStream) this.d).write(th0Var2.a, th0Var2.b, iMin);
                    int i = th0Var2.b + iMin;
                    th0Var2.b = i;
                    long j4 = iMin;
                    j -= j4;
                    p9Var.c -= j4;
                    if (i == th0Var2.c) {
                        p9Var.b = th0Var2.a();
                        vh0.a(th0Var2);
                    }
                }
                return;
        }
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.b) {
            case 0:
                i7 i7Var = (i7) this.d;
                pj0 pj0Var = this.c;
                pj0Var.h();
                try {
                    i7Var.close();
                    if (pj0Var.i()) {
                        throw pj0Var.k(null);
                    }
                    return;
                } catch (IOException e) {
                    if (!pj0Var.i()) {
                        throw e;
                    }
                    throw pj0Var.k(e);
                } finally {
                    pj0Var.i();
                }
            default:
                ((OutputStream) this.d).close();
                return;
        }
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.b) {
            case 0:
                i7 i7Var = (i7) this.d;
                pj0 pj0Var = this.c;
                pj0Var.h();
                try {
                    i7Var.flush();
                    if (pj0Var.i()) {
                        throw pj0Var.k(null);
                    }
                    return;
                } catch (IOException e) {
                    if (!pj0Var.i()) {
                        throw e;
                    }
                    throw pj0Var.k(e);
                } finally {
                    pj0Var.i();
                }
            default:
                ((OutputStream) this.d).flush();
                return;
        }
    }

    public final String toString() {
        switch (this.b) {
            case 0:
                return "AsyncTimeout.sink(" + ((i7) this.d) + ')';
            default:
                return "sink(" + ((OutputStream) this.d) + ')';
        }
    }

    public i7(pj0 pj0Var, i7 i7Var) {
        this.c = pj0Var;
        this.d = i7Var;
    }
}
