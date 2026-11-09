package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class ng0 implements s10, AutoCloseable {
    public final String b;
    public final mg0 c;
    public boolean d;

    public ng0(String str, mg0 mg0Var) {
        this.b = str;
        this.c = mg0Var;
    }

    @Override // defpackage.s10
    public final void a(u10 u10Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
            this.d = false;
            u10Var.getLifecycle().b(this);
        }
    }

    public final void t(e5 e5Var, p10 p10Var) {
        oy.j(e5Var, "registry");
        oy.j(p10Var, "lifecycle");
        if (this.d) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.d = true;
        p10Var.a(this);
        e5Var.M(this.b, (xe) this.c.a.f);
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
    }
}
