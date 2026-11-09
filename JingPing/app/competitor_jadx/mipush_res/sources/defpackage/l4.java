package defpackage;

/* JADX INFO: loaded from: classes.dex */
public final class l4 extends a0 {
    public d0 E;
    public t F;

    public l4(d0 d0Var) {
        this.E = d0Var;
    }

    public static l4 g(Object obj) {
        if (obj instanceof l4) {
            return (l4) obj;
        }
        if (obj == null) {
            return null;
        }
        j0 j0VarQ = j0.q(obj);
        l4 l4Var = new l4();
        if (j0VarQ.size() < 1 || j0VarQ.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + j0VarQ.size());
        }
        l4Var.E = d0.s(j0VarQ.r(0));
        if (j0VarQ.size() == 2) {
            l4Var.F = j0VarQ.r(1);
            return l4Var;
        }
        l4Var.F = null;
        return l4Var;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        uVar.c(this.E);
        t tVar = this.F;
        if (tVar != null) {
            uVar.c(tVar);
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }

    public l4(d0 d0Var, t tVar) {
        this.E = d0Var;
        this.F = tVar;
    }
}
