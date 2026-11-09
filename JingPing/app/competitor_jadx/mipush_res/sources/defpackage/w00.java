package defpackage;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class w00 implements hy0 {
    public final Context E;
    public final String F;
    public final l0 G;
    public final boolean H;
    public final boolean I;
    public final xy0 J;
    public boolean K;

    public w00(Context context, String str, l0 l0Var, boolean z, boolean z2) {
        t40.n(l0Var, "callback");
        this.E = context;
        this.F = str;
        this.G = l0Var;
        this.H = z;
        this.I = z2;
        this.J = z91.N(new v00(0, this));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.J.F != is.O) {
            ((u00) this.J.getValue()).close();
        }
    }

    public final ey0 j() {
        return ((u00) this.J.getValue()).j(true);
    }
}
