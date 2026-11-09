package defpackage;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class ja extends za0 {
    public final Typeface p;
    public final xd q;
    public boolean r;

    public ja(xd xdVar, Typeface typeface) {
        this.p = typeface;
        this.q = xdVar;
    }

    @Override // defpackage.za0
    public final void G(int i) {
        if (this.r) {
            return;
        }
        this.q.a(this.p);
    }

    @Override // defpackage.za0
    public final void H(Typeface typeface, boolean z) {
        if (this.r) {
            return;
        }
        this.q.a(typeface);
    }
}
