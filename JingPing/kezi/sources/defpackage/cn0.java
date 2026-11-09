package defpackage;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class cn0 extends rp0 {
    public final /* synthetic */ za0 e;
    public final /* synthetic */ en0 f;

    public cn0(en0 en0Var, za0 za0Var) {
        this.f = en0Var;
        this.e = za0Var;
    }

    @Override // defpackage.rp0
    public final void L(int i) {
        this.f.m = true;
        this.e.G(i);
    }

    @Override // defpackage.rp0
    public final void M(Typeface typeface) {
        en0 en0Var = this.f;
        en0Var.n = Typeface.create(typeface, en0Var.c);
        en0Var.m = true;
        this.e.H(en0Var.n, false);
    }
}
