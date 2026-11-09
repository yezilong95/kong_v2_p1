package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class pk extends g0 {
    public final byte[] E;

    static {
        new o(7, pk.class);
    }

    public pk(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("'contents' cannot be null");
        }
        this.E = bArr;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof pk)) {
            return false;
        }
        return Arrays.equals(this.E, ((pk) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(25, z, this.E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return z91.E(this.E);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(this.E.length, z);
    }
}
