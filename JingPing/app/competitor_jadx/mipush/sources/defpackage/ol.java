package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class ol extends k9 {
    public final boolean H;

    public ol(int i, int i2, boolean z, l0 l0Var) {
        super(i, i2, l0Var);
        this.H = z;
    }

    @Override // defpackage.k9, defpackage.u30
    public final g0 d() throws IOException {
        l0 l0Var = this.G;
        boolean z = this.H;
        int i = 4;
        int i2 = this.E;
        int i3 = this.F;
        if (!z) {
            return new j9(i, i2, i3, new tk(((sn) ((c90) l0Var.c)).l()), 2);
        }
        u uVarN = l0Var.n();
        if (uVarN.b != 1) {
            return new j9(i, i2, i3, ll.a(uVarN), 2);
        }
        return new j9(3, i2, i3, uVarN.d(0), 2);
    }
}
