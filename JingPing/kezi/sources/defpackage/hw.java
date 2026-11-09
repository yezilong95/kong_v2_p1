package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class hw extends cw {
    public boolean e;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        if (!this.e) {
            a();
        }
        this.c = true;
    }

    @Override // defpackage.cw, defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        oy.j(p9Var, "sink");
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.e) {
            return -1L;
        }
        long jD = super.d(8192L, p9Var);
        if (jD != -1) {
            return jD;
        }
        this.e = true;
        a();
        return -1L;
    }
}
