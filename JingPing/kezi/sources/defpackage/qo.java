package defpackage;

import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: loaded from: classes.dex */
public final class qo implements hj0 {
    public final hj0 b;
    public final long c;
    public boolean d;
    public long e;
    public boolean f;
    public final /* synthetic */ so g;

    public qo(so soVar, hj0 hj0Var, long j) {
        oy.j(hj0Var, "delegate");
        this.g = soVar;
        this.b = hj0Var;
        this.c = j;
    }

    public final void C() {
        this.b.flush();
    }

    public final void a() {
        this.b.close();
    }

    @Override // defpackage.hj0
    public final jo0 b() {
        return this.b.b();
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) throws IOException {
        if (this.f) {
            throw new IllegalStateException("closed");
        }
        long j2 = this.c;
        if (j2 == -1 || this.e + j <= j2) {
            try {
                this.b.c(j, p9Var);
                this.e += j;
                return;
            } catch (IOException e) {
                throw t(e);
            }
        }
        throw new ProtocolException("expected " + j2 + " bytes but received " + (this.e + j));
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f) {
            return;
        }
        this.f = true;
        long j = this.c;
        if (j != -1 && this.e != j) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            a();
            t(null);
        } catch (IOException e) {
            throw t(e);
        }
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() throws IOException {
        try {
            C();
        } catch (IOException e) {
            throw t(e);
        }
    }

    public final IOException t(IOException iOException) {
        if (this.d) {
            return iOException;
        }
        this.d = true;
        return this.g.a(false, true, iOException);
    }

    public final String toString() {
        return qo.class.getSimpleName() + '(' + this.b + ')';
    }
}
