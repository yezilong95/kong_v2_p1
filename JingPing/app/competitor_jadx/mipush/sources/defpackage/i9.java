package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class i9 extends k0 {
    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.H(z, 49, this.E);
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        int iJ = z ? 4 : 3;
        for (t tVar : this.E) {
            iJ += tVar.b().j(true);
        }
        return iJ;
    }
}
