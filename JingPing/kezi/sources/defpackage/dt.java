package defpackage;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class dt extends jo0 {
    public jo0 e;

    public dt(jo0 jo0Var) {
        oy.j(jo0Var, "delegate");
        this.e = jo0Var;
    }

    @Override // defpackage.jo0
    public final jo0 a() {
        return this.e.a();
    }

    @Override // defpackage.jo0
    public final jo0 b() {
        return this.e.b();
    }

    @Override // defpackage.jo0
    public final long c() {
        return this.e.c();
    }

    @Override // defpackage.jo0
    public final jo0 d(long j) {
        return this.e.d(j);
    }

    @Override // defpackage.jo0
    public final boolean e() {
        return this.e.e();
    }

    @Override // defpackage.jo0
    public final void f() throws InterruptedIOException {
        this.e.f();
    }

    @Override // defpackage.jo0
    public final jo0 g(long j) {
        oy.j(TimeUnit.MILLISECONDS, "unit");
        return this.e.g(j);
    }
}
