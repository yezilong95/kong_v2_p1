package defpackage;

import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class fp extends a0 {
    public final y E;
    public final y F;
    public final y G;
    public final y H;
    public final n31 I;

    public fp(j0 j0Var) {
        if (j0Var.size() < 3 || j0Var.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + j0Var.size());
        }
        Enumeration enumerationS = j0Var.s();
        this.E = y.o(enumerationS.nextElement());
        this.F = y.o(enumerationS.nextElement());
        this.G = y.o(enumerationS.nextElement());
        n31 n31Var = null;
        t tVar = enumerationS.hasMoreElements() ? (t) enumerationS.nextElement() : null;
        if (tVar == null || !(tVar instanceof y)) {
            this.H = null;
        } else {
            this.H = y.o(tVar);
            tVar = enumerationS.hasMoreElements() ? (t) enumerationS.nextElement() : null;
        }
        if (tVar == null) {
            this.I = null;
            return;
        }
        g0 g0VarB = tVar.b();
        if (g0VarB != null) {
            j0 j0VarQ = j0.q(g0VarB);
            n31Var = new n31();
            if (j0VarQ.size() != 2) {
                throw new IllegalArgumentException("Bad sequence size: " + j0VarQ.size());
            }
            n31Var.E = p.q(j0VarQ.r(0));
            n31Var.F = y.o(j0VarQ.r(1));
        }
        this.I = n31Var;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(5);
        uVar.c(this.E);
        uVar.c(this.F);
        uVar.c(this.G);
        y yVar = this.H;
        if (yVar != null) {
            uVar.c(yVar);
        }
        n31 n31Var = this.I;
        if (n31Var != null) {
            uVar.c(n31Var);
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
