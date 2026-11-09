package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class gw implements hj0 {
    public final dt b;
    public boolean c;
    public final /* synthetic */ iw d;

    public gw(iw iwVar) {
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
        kq0.c(p9Var.c, 0L, j);
        ((rc0) this.d.e).c(j, p9Var);
    }

    @Override // defpackage.hj0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.c) {
            return;
        }
        this.c = true;
        iw iwVar = this.d;
        iwVar.getClass();
        dt dtVar = this.b;
        jo0 jo0Var = dtVar.e;
        dtVar.e = jo0.d;
        jo0Var.a();
        jo0Var.b();
        iwVar.a = 3;
    }

    @Override // defpackage.hj0, java.io.Flushable
    public final void flush() {
        if (this.c) {
            return;
        }
        ((rc0) this.d.e).flush();
    }
}
