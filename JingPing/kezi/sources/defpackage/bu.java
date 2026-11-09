package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class bu implements qv, vg0, ls0 {
    public final ks0 b;
    public w10 c = null;
    public e5 d = null;

    public bu(jt jtVar, ks0 ks0Var) {
        this.b = ks0Var;
    }

    @Override // defpackage.vg0
    public final e5 b() {
        e();
        return (e5) this.d.d;
    }

    public final void c(Lifecycle$Event lifecycle$Event) {
        this.c.e(lifecycle$Event);
    }

    @Override // defpackage.ls0
    public final ks0 d() {
        e();
        return this.b;
    }

    public final void e() {
        if (this.c == null) {
            this.c = new w10(this);
            this.d = new e5(new ug0(this, new we(this, 2)));
        }
    }

    @Override // defpackage.u10
    public final p10 getLifecycle() {
        e();
        return this.c;
    }
}
