package defpackage;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class g0 extends a0 {
    public static g0 l(byte[] bArr) throws IOException {
        x xVar = new x(bArr);
        try {
            g0 g0VarZ = xVar.z();
            if (xVar.available() == 0) {
                return g0VarZ;
            }
            throw new IOException("Extra data detected in stream");
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    @Override // defpackage.a0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof t) && g(((t) obj).b());
    }

    public abstract boolean g(g0 g0Var);

    public abstract void h(p50 p50Var, boolean z);

    @Override // defpackage.a0
    public abstract int hashCode();

    public abstract boolean i();

    public abstract int j(boolean z);

    public final boolean k(g0 g0Var) {
        return this == g0Var || g(g0Var);
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        return this;
    }

    public g0 m() {
        return this;
    }

    public g0 n() {
        return this;
    }
}
