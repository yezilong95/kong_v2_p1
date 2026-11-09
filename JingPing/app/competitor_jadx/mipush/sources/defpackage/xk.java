package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class xk extends k0 {
    public int H;

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(49, z);
        uk ukVarU = p50Var.u();
        t[] tVarArr = this.E;
        int length = tVarArr.length;
        int i = 0;
        if (this.H >= 0 || length > 16) {
            p50Var.E(r());
            while (i < length) {
                tVarArr[i].b().m().h(ukVarU, true);
                i++;
            }
            return;
        }
        g0[] g0VarArr = new g0[length];
        int iJ = 0;
        for (int i2 = 0; i2 < length; i2++) {
            g0 g0VarM = tVarArr[i2].b().m();
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

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(r(), z);
    }

    @Override // defpackage.k0, defpackage.g0
    public final g0 m() {
        return this.F != null ? this : super.m();
    }

    public final int r() {
        if (this.H < 0) {
            int iJ = 0;
            for (t tVar : this.E) {
                iJ += tVar.b().m().j(true);
            }
            this.H = iJ;
        }
        return this.H;
    }

    @Override // defpackage.k0, defpackage.g0
    public final g0 n() {
        return this;
    }
}
