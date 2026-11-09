package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class m0 extends g0 {
    public final byte[] E;

    static {
        new o(20, m0.class);
    }

    public m0(byte[] bArr) {
        byte b;
        byte b2;
        if (bArr.length < 2) {
            throw new IllegalArgumentException("UTCTime string too short");
        }
        this.E = bArr;
        if (bArr.length <= 0 || (b = bArr[0]) < 48 || b > 57 || bArr.length <= 1 || (b2 = bArr[1]) < 48 || b2 > 57) {
            throw new IllegalArgumentException("illegal characters in UTCTime string");
        }
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof m0)) {
            return false;
        }
        return Arrays.equals(this.E, ((m0) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(23, z, this.E);
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

    public final String toString() {
        return jx0.a(this.E);
    }
}
