package defpackage;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class ay implements uj0 {
    public final sc0 b;
    public final Inflater c;
    public int d;
    public boolean e;

    public ay(sc0 sc0Var, Inflater inflater) {
        this.b = sc0Var;
        this.c = inflater;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.b.b.b();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.e) {
            return;
        }
        this.c.end();
        this.e = true;
        this.b.close();
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        long j2;
        oy.j(p9Var, "sink");
        while (!this.e) {
            sc0 sc0Var = this.b;
            Inflater inflater = this.c;
            try {
                th0 th0VarK = p9Var.K(1);
                int iMin = (int) Math.min(8192L, 8192 - th0VarK.c);
                if (inflater.needsInput() && !sc0Var.a()) {
                    th0 th0Var = sc0Var.c.b;
                    oy.g(th0Var);
                    int i = th0Var.c;
                    int i2 = th0Var.b;
                    int i3 = i - i2;
                    this.d = i3;
                    inflater.setInput(th0Var.a, i2, i3);
                }
                int iInflate = inflater.inflate(th0VarK.a, th0VarK.c, iMin);
                int i4 = this.d;
                if (i4 != 0) {
                    int remaining = i4 - inflater.getRemaining();
                    this.d -= remaining;
                    sc0Var.L(remaining);
                }
                if (iInflate > 0) {
                    th0VarK.c += iInflate;
                    j2 = iInflate;
                    p9Var.c += j2;
                } else {
                    if (th0VarK.b == th0VarK.c) {
                        p9Var.b = th0VarK.a();
                        vh0.a(th0VarK);
                    }
                    j2 = 0;
                }
                if (j2 > 0) {
                    return j2;
                }
                if (inflater.finished() || inflater.needsDictionary()) {
                    return -1L;
                }
                if (sc0Var.a()) {
                    throw new EOFException("source exhausted prematurely");
                }
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        }
        throw new IllegalStateException("closed");
    }
}
