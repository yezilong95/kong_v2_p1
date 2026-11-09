package defpackage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class ww implements uj0 {
    public final sc0 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;

    public ww(sc0 sc0Var) {
        oy.j(sc0Var, "source");
        this.b = sc0Var;
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.b.b.b();
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        int i;
        int iF;
        oy.j(p9Var, "sink");
        do {
            int i2 = this.f;
            sc0 sc0Var = this.b;
            if (i2 == 0) {
                sc0Var.L(this.g);
                this.g = 0;
                if ((this.d & 4) == 0) {
                    i = this.e;
                    int iT = kq0.t(sc0Var);
                    this.f = iT;
                    this.c = iT;
                    int iC = sc0Var.C() & 255;
                    this.d = sc0Var.C() & 255;
                    Logger logger = xw.e;
                    if (logger.isLoggable(Level.FINE)) {
                        ByteString byteString = jw.a;
                        logger.fine(jw.a(true, this.e, this.c, iC, this.d));
                    }
                    iF = sc0Var.F() & Integer.MAX_VALUE;
                    this.e = iF;
                    if (iC != 9) {
                        throw new IOException(iC + " != TYPE_CONTINUATION");
                    }
                }
            } else {
                long jD = sc0Var.d(Math.min(8192L, i2), p9Var);
                if (jD != -1) {
                    this.f -= (int) jD;
                    return jD;
                }
            }
            return -1L;
        } while (iF == i);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
