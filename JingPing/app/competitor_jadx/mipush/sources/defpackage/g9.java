package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class g9 extends j0 {
    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.H(z, 48, this.E);
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        int iJ = z ? 4 : 3;
        int length = this.E.length;
        for (int i = 0; i < length; i++) {
            iJ += this.E[i].b().j(true);
        }
        return iJ;
    }

    @Override // defpackage.j0
    public final p t() {
        return new b9(o());
    }

    @Override // defpackage.j0
    public final mk u() {
        return ((j0) n()).u();
    }

    @Override // defpackage.j0
    public final e0 v() {
        e0[] e0VarArrP = p();
        return new e9(e9.p(e0VarArrP), e0VarArrP);
    }

    @Override // defpackage.j0
    public final k0 w() {
        return new i9(false, this.E);
    }
}
