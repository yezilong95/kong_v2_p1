package defpackage;

import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: loaded from: classes.dex */
public final class ro implements uj0 {
    public final uj0 b;
    public final long c;
    public long d;
    public boolean e;
    public boolean f;
    public boolean g;
    public final /* synthetic */ so h;

    public ro(so soVar, uj0 uj0Var, long j) {
        oy.j(uj0Var, "delegate");
        this.h = soVar;
        this.b = uj0Var;
        this.c = j;
        this.e = true;
        if (j == 0) {
            t(null);
        }
    }

    public final void a() throws IOException {
        this.b.close();
    }

    @Override // defpackage.uj0
    public final jo0 b() {
        return this.b.b();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.g) {
            return;
        }
        this.g = true;
        try {
            a();
            t(null);
        } catch (IOException e) {
            throw t(e);
        }
    }

    @Override // defpackage.uj0
    public final long d(long j, p9 p9Var) throws IOException {
        oy.j(p9Var, "sink");
        if (this.g) {
            throw new IllegalStateException("closed");
        }
        try {
            long jD = this.b.d(8192L, p9Var);
            if (this.e) {
                this.e = false;
                so soVar = this.h;
                soVar.getClass();
                oy.j(soVar.a, "call");
            }
            if (jD == -1) {
                t(null);
                return -1L;
            }
            long j2 = this.d + jD;
            long j3 = this.c;
            if (j3 == -1 || j2 <= j3) {
                this.d = j2;
                if (j2 == j3) {
                    t(null);
                }
                return jD;
            }
            throw new ProtocolException("expected " + j3 + " bytes but received " + j2);
        } catch (IOException e) {
            throw t(e);
        }
    }

    public final IOException t(IOException iOException) {
        if (this.f) {
            return iOException;
        }
        this.f = true;
        so soVar = this.h;
        if (iOException == null && this.e) {
            this.e = false;
            soVar.getClass();
            oy.j(soVar.a, "call");
        }
        return soVar.a(true, false, iOException);
    }

    public final String toString() {
        return ro.class.getSimpleName() + '(' + this.b + ')';
    }
}
