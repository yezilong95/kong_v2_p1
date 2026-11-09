package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class uu0 implements Runnable {
    public final z80 E;
    public final Lifecycle$Event F;
    public boolean G;

    public uu0(z80 z80Var, Lifecycle$Event lifecycle$Event) {
        t40.n(z80Var, "registry");
        t40.n(lifecycle$Event, "event");
        this.E = z80Var;
        this.F = lifecycle$Event;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.G) {
            return;
        }
        this.E.e(this.F);
        this.G = true;
    }
}
