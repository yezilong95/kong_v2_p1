package defpackage;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ku implements tl0 {
    public final Context b;
    public final String c;
    public final d5 d;
    public final boolean e;
    public final boolean f;
    public final im0 g;
    public boolean h;

    public ku(Context context, String str, d5 d5Var, boolean z, boolean z2) {
        oy.j(d5Var, "callback");
        this.b = context;
        this.c = str;
        this.d = d5Var;
        this.e = z;
        this.f = z2;
        this.g = oy.G(new ju(0, this));
    }

    public final ql0 a() {
        return ((iu) this.g.getValue()).a(true);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.g.c != qm.k) {
            ((iu) this.g.getValue()).close();
        }
    }
}
