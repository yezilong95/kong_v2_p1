package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class w extends g0 {
    public final byte[] E;

    static {
        new o(6, w.class);
    }

    public w(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("GeneralizedTime string too short");
        }
        this.E = bArr;
        if (!o(0) || !o(1) || !o(2) || !o(3)) {
            throw new IllegalArgumentException("illegal characters in GeneralizedTime string");
        }
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof w)) {
            return false;
        }
        return Arrays.equals(this.E, ((w) g0Var).E);
    }

    @Override // defpackage.g0
    public void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(24, z, this.E);
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
    public int j(boolean z) {
        return p50.x(this.E.length, z);
    }

    @Override // defpackage.g0
    public g0 m() {
        return new ok(this.E);
    }

    public final boolean o(int i) {
        byte b;
        byte[] bArr = this.E;
        return bArr.length > i && (b = bArr[i]) >= 48 && b <= 57;
    }
}
