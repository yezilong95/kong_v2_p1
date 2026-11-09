package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class wk extends j0 {
    public final /* synthetic */ int G;
    public int H;

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        switch (this.G) {
            case 0:
                p50Var.J(48, z);
                uk ukVarU = p50Var.u();
                int length = this.E.length;
                int i = 0;
                if (this.H >= 0 || length > 16) {
                    p50Var.E(x());
                    while (i < length) {
                        this.E[i].b().m().h(ukVarU, true);
                        i++;
                    }
                } else {
                    g0[] g0VarArr = new g0[length];
                    int iJ = 0;
                    for (int i2 = 0; i2 < length; i2++) {
                        g0 g0VarM = this.E[i2].b().m();
                        g0VarArr[i2] = g0VarM;
                        iJ += g0VarM.j(true);
                    }
                    this.H = iJ;
                    p50Var.E(iJ);
                    while (i < length) {
                        g0VarArr[i].h(ukVarU, true);
                        i++;
                    }
                }
                break;
            default:
                p50Var.J(48, z);
                ml mlVarV = p50Var.v();
                int length2 = this.E.length;
                int i3 = 0;
                if (this.H >= 0 || length2 > 16) {
                    p50Var.E(y());
                    while (i3 < length2) {
                        mlVarV.K(this.E[i3].b());
                        i3++;
                    }
                } else {
                    g0[] g0VarArr2 = new g0[length2];
                    int iJ2 = 0;
                    for (int i4 = 0; i4 < length2; i4++) {
                        g0 g0VarN = this.E[i4].b().n();
                        g0VarArr2[i4] = g0VarN;
                        iJ2 += g0VarN.j(true);
                    }
                    this.H = iJ2;
                    p50Var.E(iJ2);
                    while (i3 < length2) {
                        mlVarV.K(g0VarArr2[i3]);
                        i3++;
                    }
                }
                break;
        }
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        switch (this.G) {
            case 0:
                return p50.x(x(), z);
            default:
                return p50.x(y(), z);
        }
    }

    @Override // defpackage.j0, defpackage.g0
    public g0 m() {
        switch (this.G) {
            case 0:
                return this;
            default:
                return super.m();
        }
    }

    @Override // defpackage.j0, defpackage.g0
    public final g0 n() {
        int i = this.G;
        return this;
    }

    @Override // defpackage.j0
    public final p t() {
        switch (this.G) {
            case 0:
                return new lk(b9.s(o()));
            default:
                return new jl(b9.s(o()));
        }
    }

    @Override // defpackage.j0
    public final mk u() {
        switch (this.G) {
            case 0:
                return new mk(this, 0);
            default:
                return new mk(this, 1);
        }
    }

    @Override // defpackage.j0
    public final e0 v() {
        switch (this.G) {
        }
        return new tk(e9.p(p()));
    }

    @Override // defpackage.j0
    public final k0 w() {
        switch (this.G) {
        }
        return new nl(this.E);
    }

    public int x() {
        if (this.H < 0) {
            int length = this.E.length;
            int iJ = 0;
            for (int i = 0; i < length; i++) {
                iJ += this.E[i].b().m().j(true);
            }
            this.H = iJ;
        }
        return this.H;
    }

    public int y() {
        if (this.H < 0) {
            int length = this.E.length;
            int iJ = 0;
            for (int i = 0; i < length; i++) {
                iJ += this.E[i].b().n().j(true);
            }
            this.H = iJ;
        }
        return this.H;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ wk(u uVar, int i) {
        super(uVar);
        this.G = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ wk(g0 g0Var, int i) {
        super(g0Var);
        this.G = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ wk(boolean z, t[] tVarArr) {
        super(tVarArr);
        this.G = 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public wk(t[] tVarArr, int i) {
        super(tVarArr);
        this.G = i;
        switch (i) {
            case 1:
                return;
            default:
                if (tVarArr != null) {
                    for (t tVar : tVarArr) {
                        if (tVar != null) {
                        }
                    }
                    this.E = tVarArr.length < 1 ? u.d : (t[]) tVarArr.clone();
                    this.H = -1;
                    return;
                }
                throw new NullPointerException("'elements' cannot be null, or contain null");
        }
    }
}
