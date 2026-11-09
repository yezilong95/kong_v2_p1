package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class s50 extends t8 {
    public final o50 b;
    public th0 c;
    public final /* synthetic */ t50 d;
    public final /* synthetic */ w30 e;

    public s50(o50 o50Var, t50 t50Var, w30 w30Var) {
        this.d = t50Var;
        this.e = w30Var;
        this.b = o50Var;
    }

    @Override // defpackage.t8
    public final void b(Object obj, Object obj2) {
        oa0 oa0Var = (oa0) obj;
        boolean z = obj2 == null;
        o50 o50Var = this.b;
        w30 w30Var = z ? o50Var : this.c;
        if (w30Var != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = oa0.a;
            while (!atomicReferenceFieldUpdater.compareAndSet(oa0Var, this, w30Var)) {
                if (atomicReferenceFieldUpdater.get(oa0Var) != this) {
                    return;
                }
            }
            if (z) {
                th0 th0Var = this.c;
                t40.k(th0Var);
                o50Var.d(th0Var);
            }
        }
    }

    @Override // defpackage.t8
    public final wy0 c(Object obj) {
        if (this.d.n() == this.e) {
            return null;
        }
        return z91.L;
    }
}
