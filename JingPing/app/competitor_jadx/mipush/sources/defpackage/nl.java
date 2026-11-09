package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class nl extends k0 {
    public int H;

    public nl(t[] tVarArr) {
        super(false, tVarArr);
        this.H = -1;
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(49, z);
        ml mlVarV = p50Var.v();
        t[] tVarArr = this.E;
        int length = tVarArr.length;
        int i = 0;
        if (this.H >= 0 || length > 16) {
            p50Var.E(r());
            while (i < length) {
                mlVarV.K(tVarArr[i].b());
                i++;
            }
            return;
        }
        g0[] g0VarArr = new g0[length];
        int iJ = 0;
        for (int i2 = 0; i2 < length; i2++) {
            g0 g0VarN = tVarArr[i2].b().n();
            g0VarArr[i2] = g0VarN;
            iJ += g0VarN.j(true);
        }
        this.H = iJ;
        p50Var.E(iJ);
        while (i < length) {
            mlVarV.K(g0VarArr[i]);
            i++;
        }
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(r(), z);
    }

    public final int r() {
        if (this.H < 0) {
            int iJ = 0;
            for (t tVar : this.E) {
                iJ += tVar.b().n().j(true);
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
