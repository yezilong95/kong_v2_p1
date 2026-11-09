package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class r extends g0 {
    public static final r F;
    public static final r G;
    public final byte E;

    static {
        new o(2, r.class);
        F = new r((byte) 0);
        G = new r((byte) -1);
    }

    public r(byte b) {
        this.E = b;
    }

    public static r o(byte[] bArr) {
        if (bArr.length != 1) {
            throw new IllegalArgumentException("BOOLEAN value should have 1 byte in it");
        }
        byte b = bArr[0];
        return b != -1 ? b != 0 ? new r(b) : F : G;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        return (g0Var instanceof r) && p() == ((r) g0Var).p();
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.J(1, z);
        p50Var.E(1);
        p50Var.C(this.E);
    }

    @Override // defpackage.g0, defpackage.a0
    public final int hashCode() {
        return p() ? 1 : 0;
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(1, z);
    }

    @Override // defpackage.g0
    public final g0 m() {
        return p() ? G : F;
    }

    public final boolean p() {
        return this.E != 0;
    }

    public final String toString() {
        return p() ? "TRUE" : "FALSE";
    }
}
