package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class q50 extends o50 {
    public final t50 e;
    public final r50 f;
    public final zd g;
    public final Object h;

    public q50(t50 t50Var, r50 r50Var, zd zdVar, Object obj) {
        this.e = t50Var;
        this.f = r50Var;
        this.g = zdVar;
        this.h = obj;
    }

    @Override // defpackage.s40
    public final void b(Throwable th) {
        zd zdVar = this.g;
        t50 t50Var = this.e;
        t50Var.getClass();
        zd zdVarT = t50.t(zdVar);
        r50 r50Var = this.f;
        Object obj = this.h;
        if (zdVarT != null) {
            while (j31.y(zdVarT.e, new q50(t50Var, r50Var, zdVarT, obj), 1) == wh0.a) {
                zdVarT = t50.t(zdVarT);
                if (zdVarT == null) {
                }
            }
            return;
        }
        t50Var.b(t50Var.j(r50Var, obj));
    }
}
