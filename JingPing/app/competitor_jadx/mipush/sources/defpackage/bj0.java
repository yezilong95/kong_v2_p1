package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class bj0 implements v80, cc {
    public final s80 E;
    public final a00 F;
    public cj0 G;
    public final /* synthetic */ ej0 H;

    public bj0(ej0 ej0Var, s80 s80Var, a00 a00Var) {
        t40.n(s80Var, "lifecycle");
        t40.n(a00Var, "onBackPressedCallback");
        this.H = ej0Var;
        this.E = s80Var;
        this.F = a00Var;
        s80Var.a(this);
    }

    @Override // defpackage.cc
    public final void cancel() {
        this.E.b(this);
        this.F.b.remove(this);
        cj0 cj0Var = this.G;
        if (cj0Var != null) {
            cj0Var.cancel();
        }
        this.G = null;
    }

    @Override // defpackage.v80
    public final void j(x80 x80Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_START) {
            ej0 ej0Var = this.H;
            a00 a00Var = this.F;
            t40.n(a00Var, "onBackPressedCallback");
            ej0Var.b.addLast(a00Var);
            cj0 cj0Var = new cj0(ej0Var, a00Var);
            a00Var.b.add(cj0Var);
            ej0Var.c();
            a00Var.c = new dj0(0, ej0Var, ej0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 1);
            this.G = cj0Var;
            return;
        }
        if (lifecycle$Event != Lifecycle$Event.ON_STOP) {
            if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                cancel();
            }
        } else {
            cj0 cj0Var2 = this.G;
            if (cj0Var2 != null) {
                cj0Var2.cancel();
            }
        }
    }
}
