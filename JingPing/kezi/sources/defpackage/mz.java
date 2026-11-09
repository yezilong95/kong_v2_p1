package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class mz extends l7 {
    public final jz b;
    public p90 c;
    public final /* synthetic */ nz d;
    public final /* synthetic */ sx e;

    public mz(jz jzVar, nz nzVar, sx sxVar) {
        this.d = nzVar;
        this.e = sxVar;
        this.b = jzVar;
    }

    @Override // defpackage.l7
    public final void b(Object obj, Object obj2) {
        i30 i30Var = (i30) obj;
        boolean z = obj2 == null;
        jz jzVar = this.b;
        sx sxVar = z ? jzVar : this.c;
        if (sxVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i30.b;
            while (!atomicReferenceFieldUpdater.compareAndSet(i30Var, this, sxVar)) {
                if (atomicReferenceFieldUpdater.get(i30Var) != this) {
                    return;
                }
            }
            if (z) {
                p90 p90Var = this.c;
                oy.g(p90Var);
                jzVar.e(p90Var);
            }
        }
    }

    @Override // defpackage.l7
    public final nm c(Object obj) {
        if (this.d.o() == this.e) {
            return null;
        }
        return k9.e;
    }
}
