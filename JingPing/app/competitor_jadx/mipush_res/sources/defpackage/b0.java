package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends g0 {
    public final pk E;

    static {
        new o(12, b0.class);
    }

    public b0(pk pkVar) {
        this.E = pkVar;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (g0Var instanceof b0) {
            return this.E.g(((b0) g0Var).E);
        }
        return false;
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(7, z);
        p50Var.G(25, false, this.E.E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return ~z91.E(this.E.E);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return this.E.j(z);
    }

    @Override // defpackage.g0
    public final g0 m() {
        this.E.getClass();
        return this;
    }

    @Override // defpackage.g0
    public final g0 n() {
        this.E.getClass();
        return this;
    }
}
