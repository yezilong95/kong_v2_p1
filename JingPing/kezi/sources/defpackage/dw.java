package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class dw implements hj0 {
    public final dt b;
    public boolean c;
    public final /* synthetic */ iw d;

    public dw(iw iwVar) {
        this.d = iwVar;
        this.b = new dt(((rc0) iwVar.e).b.b());
    }

    @Override // defpackage.hj0
    public final jo0 b() {
        return this.b;
    }

    @Override // defpackage.hj0
    public final void c(long j, p9 p9Var) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return;
        }
        iw iwVar = this.d;
        rc0 rc0Var = (rc0) iwVar.e;
        if (rc0Var.d) {
            throw new IllegalStateException("closed");
        }
        rc0Var.c.P(j);
        rc0Var.a();
        rc0 rc0Var2 = (rc0) iwVar.e;
        rc0Var2.u("\r\n");
        rc0Var2.c(j, p9Var);
        rc0Var2.u("\r\n");
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        ((rc0) this.d.e).u("0\r\n\r\n");
        iw iwVar = this.d;
        dt dtVar = this.b;
        iwVar.getClass();
        jo0 jo0Var = dtVar.e;
        dtVar.e = jo0.d;
        jo0Var.a();
        jo0Var.b();
        this.d.a = 3;
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final synchronized void flush() {
        if (this.c) {
            return;
        }
        ((rc0) this.d.e).flush();
    }
}
