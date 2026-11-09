package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class lk extends p {
    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        byte[] bArr = this.E;
        int i = bArr[0] & 255;
        int length = bArr.length;
        int i2 = length - 1;
        byte b = bArr[i2];
        byte b2 = (byte) ((255 << i) & b);
        if (b == b2) {
            p50Var.G(3, z, bArr);
            return;
        }
        p50Var.J(3, z);
        p50Var.E(length);
        p50Var.D(bArr, 0, i2);
        p50Var.C(b2);
    }

    @Override // defpackage.g0
    public final boolean i() {
        return false;
    }

    @Override // defpackage.g0
    public final int j(boolean z) {
        return p50.x(this.E.length, z);
    }

    @Override // defpackage.p, defpackage.g0
    public final g0 m() {
        return this;
    }

    @Override // defpackage.p, defpackage.g0
    public final g0 n() {
        return this;
    }
}
