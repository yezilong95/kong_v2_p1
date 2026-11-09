package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class b90 extends ej implements tn {
    public static final /* synthetic */ AtomicIntegerFieldUpdater K = AtomicIntegerFieldUpdater.newUpdater(b90.class, "runningWorkers$volatile");
    public final z21 G;
    public final int H;
    public final pa0 I;
    public final Object J;
    private volatile /* synthetic */ int runningWorkers$volatile;

    /* JADX WARN: Multi-variable type inference failed */
    public b90(z21 z21Var, int i) {
        this.G = z21Var;
        this.H = i;
        if ((z21Var instanceof tn ? (tn) z21Var : null) == null) {
            int i2 = wm.a;
        }
        this.I = new pa0();
        this.J = new Object();
    }

    @Override // defpackage.ej
    public final void j(bj bjVar, Runnable runnable) {
        Runnable runnableX;
        this.I.a(runnable);
        if (K.get(this) >= this.H || !y() || (runnableX = x()) == null) {
            return;
        }
        this.G.j(this, new h3(this, 6, runnableX));
    }

    @Override // defpackage.ej
    public final void l(bj bjVar, Runnable runnable) {
        Runnable runnableX;
        this.I.a(runnable);
        if (K.get(this) >= this.H || !y() || (runnableX = x()) == null) {
            return;
        }
        this.G.l(this, new h3(this, 6, runnableX));
    }

    public final Runnable x() {
        while (true) {
            Runnable runnable = (Runnable) this.I.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.J) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = K;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.I.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean y() {
        synchronized (this.J) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = K;
            if (atomicIntegerFieldUpdater.get(this) >= this.H) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }
}
