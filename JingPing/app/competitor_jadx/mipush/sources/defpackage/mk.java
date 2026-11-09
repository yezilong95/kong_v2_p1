package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class mk extends g0 {
    public d0 E;
    public y F;
    public g0 G;
    public int H;
    public g0 I;
    public final /* synthetic */ int J;

    static {
        new o(4, mk.class);
    }

    public /* synthetic */ mk() {
        this.J = 0;
    }

    public static g0 p(j0 j0Var, int i) {
        if (j0Var.size() > i) {
            return j0Var.r(i).b();
        }
        throw new IllegalArgumentException("too few objects in input sequence");
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        mk mkVar;
        Object obj;
        d0 d0Var;
        if (this == g0Var) {
            return true;
        }
        if (!(g0Var instanceof mk) || ((d0Var = this.E) != (obj = (mkVar = (mk) g0Var).E) && (d0Var == null || obj == null || !d0Var.equals(obj)))) {
            return false;
        }
        y yVar = this.F;
        y yVar2 = mkVar.F;
        if (yVar != yVar2 && (yVar == null || yVar2 == null || !yVar.equals(yVar2))) {
            return false;
        }
        g0 g0Var2 = this.G;
        g0 g0Var3 = mkVar.G;
        return (g0Var2 == g0Var3 || !(g0Var2 == null || g0Var3 == null || !g0Var2.equals(g0Var3))) && this.H == mkVar.H && this.I.k(mkVar.I);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(40, z);
        o().h(p50Var, false);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        d0 d0Var = this.E;
        int iHashCode = d0Var == null ? 0 : d0Var.hashCode();
        y yVar = this.F;
        int iHashCode2 = iHashCode ^ (yVar == null ? 0 : yVar.hashCode());
        g0 g0Var = this.G;
        return ((iHashCode2 ^ (g0Var != null ? g0Var.hashCode() : 0)) ^ this.H) ^ this.I.hashCode();
    }

    @Override // defpackage.g0
    public final boolean i() {
        return true;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return o().j(z);
    }

    @Override // defpackage.g0
    public g0 m() {
        switch (this.J) {
            case 0:
                return this;
            default:
                mk mkVar = new mk();
                mkVar.E = this.E;
                mkVar.F = this.F;
                mkVar.G = this.G;
                int i = this.H;
                if (i < 0 || i > 2) {
                    throw new IllegalArgumentException(zn.j(i, "invalid encoding value: "));
                }
                mkVar.H = i;
                g0 g0Var = this.I;
                if (i != 1) {
                    if (i == 2 && !p.class.isInstance(g0Var)) {
                        throw new IllegalStateException("unexpected object: ".concat(g0Var.getClass().getName()));
                    }
                } else if (!e0.class.isInstance(g0Var)) {
                    throw new IllegalStateException("unexpected object: ".concat(g0Var.getClass().getName()));
                }
                mkVar.I = g0Var;
                return mkVar;
        }
    }

    @Override // defpackage.g0
    public final g0 n() {
        int i = this.J;
        return this;
    }

    public final j0 o() {
        switch (this.J) {
            case 0:
                u uVar = new u(4);
                d0 d0Var = this.E;
                if (d0Var != null) {
                    uVar.c(d0Var);
                }
                y yVar = this.F;
                if (yVar != null) {
                    uVar.c(yVar);
                }
                g0 g0Var = this.G;
                if (g0Var != null) {
                    uVar.c(g0Var.m());
                }
                int i = this.H;
                uVar.c(new j9(i == 0, i, this.I, 1));
                wk wkVar = new wk(uVar, 0);
                wkVar.H = -1;
                return wkVar;
            default:
                u uVar2 = new u(4);
                d0 d0Var2 = this.E;
                if (d0Var2 != null) {
                    uVar2.c(d0Var2);
                }
                y yVar2 = this.F;
                if (yVar2 != null) {
                    uVar2.c(yVar2);
                }
                g0 g0Var2 = this.G;
                if (g0Var2 != null) {
                    uVar2.c(g0Var2.n());
                }
                int i2 = this.H;
                uVar2.c(new j9(i2 == 0, i2, this.I, 2));
                wk wkVar2 = new wk(uVar2, 1);
                wkVar2.H = -1;
                return wkVar2;
        }
    }

    public mk(j0 j0Var, int i) {
        int i2;
        g0 g0VarB;
        this.J = i;
        g0 g0VarP = p(j0Var, 0);
        if (g0VarP instanceof d0) {
            this.E = (d0) g0VarP;
            g0VarP = p(j0Var, 1);
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (g0VarP instanceof y) {
            this.F = (y) g0VarP;
            i2++;
            g0VarP = p(j0Var, i2);
        }
        if (!(g0VarP instanceof j9)) {
            this.G = g0VarP;
            i2++;
            g0VarP = p(j0Var, i2);
        }
        if (j0Var.size() != i2 + 1) {
            throw new IllegalArgumentException("input sequence too large");
        }
        if (!(g0VarP instanceof j9)) {
            throw new IllegalArgumentException("No tagged object found in sequence. Structure doesn't seem to be of type External");
        }
        j9 j9Var = (j9) g0VarP;
        int i3 = j9Var.G;
        if (i3 < 0 || i3 > 2) {
            throw new IllegalArgumentException(zn.j(i3, "invalid encoding value: "));
        }
        this.H = i3;
        t40.j(j9Var);
        int i4 = j9Var.G;
        if (i4 != 0) {
            if (i4 == 1) {
                o oVar = e0.F;
                t40.j(j9Var);
                g0 g0VarO = j9Var.o(false, oVar);
                oVar.a(g0VarO);
                g0VarB = (e0) g0VarO;
            } else {
                if (i4 != 2) {
                    throw new IllegalArgumentException("invalid tag: " + t40.p0(j9Var.F, i4));
                }
                o oVar2 = p.F;
                t40.j(j9Var);
                g0 g0VarO2 = j9Var.o(false, oVar2);
                oVar2.a(g0VarO2);
                g0VarB = (p) g0VarO2;
            }
        } else {
            if (!j9Var.q()) {
                throw new IllegalStateException("object implicit - explicit expected.");
            }
            t tVar = j9Var.H;
            g0VarB = (tVar instanceof a0 ? (a0) tVar : tVar.b()).b();
        }
        this.I = g0VarB;
    }
}
