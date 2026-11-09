package defpackage;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class j7 implements uj0 {
    public final /* synthetic */ int b;
    public final Object c;
    public final Object d;

    public /* synthetic */ j7(Object obj, int i, Object obj2) {
        this.b = i;
        this.c = obj;
        this.d = obj2;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        switch (this.b) {
            case 0:
                return (pj0) this.c;
            default:
                return (jo0) this.d;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        switch (this.b) {
            case 0:
                j7 j7Var = (j7) this.d;
                pj0 pj0Var = (pj0) this.c;
                pj0Var.h();
                try {
                    j7Var.close();
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
                ((InputStream) this.c).close();
                return;
        }
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        switch (this.b) {
            case 0:
                oy.j(p9Var, "sink");
                j7 j7Var = (j7) this.d;
                pj0 pj0Var = (pj0) this.c;
                pj0Var.h();
                try {
                    long jD = j7Var.d(8192L, p9Var);
                    if (pj0Var.i()) {
                        throw pj0Var.k(null);
                    }
                    return jD;
                } catch (IOException e) {
                    if (pj0Var.i()) {
                        throw pj0Var.k(e);
                    }
                    throw e;
                } finally {
                    pj0Var.i();
                }
            default:
                oy.j(p9Var, "sink");
                try {
                    ((jo0) this.d).f();
                    th0 th0VarK = p9Var.K(1);
                    int i = ((InputStream) this.c).read(th0VarK.a, th0VarK.c, (int) Math.min(8192L, 8192 - th0VarK.c));
                    if (i == -1) {
                        if (th0VarK.b == th0VarK.c) {
                            p9Var.b = th0VarK.a();
                            vh0.a(th0VarK);
                        }
                        return -1L;
                    }
                    th0VarK.c += i;
                    long j2 = i;
                    p9Var.c += j2;
                    return j2;
                } catch (AssertionError e2) {
                    if (za0.w(e2)) {
                        throw new IOException(e2);
                    }
                    throw e2;
                }
        }
    }

    public final String toString() {
        switch (this.b) {
            case 0:
                return "AsyncTimeout.source(" + ((j7) this.d) + ')';
            default:
                return "source(" + ((InputStream) this.c) + ')';
        }
    }
}
