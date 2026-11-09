package defpackage;

import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class sl0 implements x80 {
    public static final sl0 M = new sl0();
    public int E;
    public int F;
    public Handler I;
    public boolean G = true;
    public boolean H = true;
    public final z80 J = new z80(this);
    public final e K = new e(25, this);
    public final z8 L = new z8(28, this);

    public final void b() {
        int i = this.F + 1;
        this.F = i;
        if (i == 1) {
            if (this.G) {
                this.J.e(Lifecycle$Event.ON_RESUME);
                this.G = false;
            } else {
                Handler handler = this.I;
                t40.k(handler);
                handler.removeCallbacks(this.K);
            }
        }
    }

    @Override // defpackage.x80
    public final s80 getLifecycle() {
        return this.J;
    }
}
