package defpackage;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class v extends g0 {
    public static final v[] F;
    public final byte[] E;

    static {
        new o(3, v.class);
        F = new v[12];
    }

    public v(byte[] bArr, boolean z) {
        if (y.u(bArr)) {
            throw new IllegalArgumentException("malformed enumerated");
        }
        int i = 0;
        if ((bArr[0] & 128) != 0) {
            throw new IllegalArgumentException("enumerated must be non-negative");
        }
        this.E = z ? z91.l(bArr) : bArr;
        int length = bArr.length - 1;
        while (i < length) {
            byte b = bArr[i];
            i++;
            if (b != (bArr[i] >> 7)) {
                return;
            }
        }
    }

    public static v o(byte[] bArr, boolean z) {
        if (bArr.length > 1) {
            return new v(bArr, z);
        }
        if (bArr.length == 0) {
            throw new IllegalArgumentException("ENUMERATED has zero length");
        }
        int i = bArr[0] & 255;
        if (i >= 12) {
            return new v(bArr, z);
        }
        v[] vVarArr = F;
        v vVar = vVarArr[i];
        if (vVar != null) {
            return vVar;
        }
        v vVar2 = new v(bArr, z);
        vVarArr[i] = vVar2;
        return vVar2;
    }

    @Override // defpackage.g0
    public final boolean g(g0 g0Var) {
        if (!(g0Var instanceof v)) {
            return false;
        }
        return Arrays.equals(this.E, ((v) g0Var).E);
    }

    @Override // defpackage.g0
    public final void h(p50 p50Var, boolean z) throws IOException {
        p50Var.G(10, z, this.E);
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
