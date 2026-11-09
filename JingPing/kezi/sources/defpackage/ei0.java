package defpackage;

import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class ei0 implements Runnable {
    public final w10 b;
    public final Lifecycle$Event c;
    public boolean d;

    public ei0(w10 w10Var, Lifecycle$Event lifecycle$Event) {
        oy.j(w10Var, "registry");
        oy.j(lifecycle$Event, "event");
        this.b = w10Var;
        this.c = lifecycle$Event;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.d) {
            return;
        }
        this.b.e(this.c);
        this.d = true;
    }
}
