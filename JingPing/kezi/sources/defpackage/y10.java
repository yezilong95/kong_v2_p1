package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class y10 extends qh implements bk {
    public static final AtomicIntegerFieldUpdater h = AtomicIntegerFieldUpdater.newUpdater(y10.class, "runningWorkers");
    public final cq0 d;
    public final int e;
    public final j30 f;
    public final Object g;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public y10(cq0 cq0Var, int i) {
        this.d = cq0Var;
        this.e = i;
        if ((cq0Var instanceof bk ? (bk) cq0Var : null) == null) {
            int i2 = dj.a;
        }
        this.f = new j30();
        this.g = new Object();
    }

    @Override // defpackage.qh
    public final void C(nh nhVar, Runnable runnable) {
        Runnable runnableE;
        this.f.a(runnable);
        if (h.get(this) >= this.e || !F() || (runnableE = E()) == null) {
            return;
        }
        this.d.C(this, new m1(this, 6, runnableE));
    }

    public final Runnable E() {
        while (true) {
            Runnable runnable = (Runnable) this.f.d();
            if (runnable != null) {
                return runnable;
            }
            synchronized (this.g) {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = h;
                atomicIntegerFieldUpdater.decrementAndGet(this);
                if (this.f.c() == 0) {
                    return null;
                }
                atomicIntegerFieldUpdater.incrementAndGet(this);
            }
        }
    }

    public final boolean F() {
        synchronized (this.g) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = h;
            if (atomicIntegerFieldUpdater.get(this) >= this.e) {
                return false;
            }
            atomicIntegerFieldUpdater.incrementAndGet(this);
            return true;
        }
    }

    @Override // defpackage.qh
    public final void t(nh nhVar, Runnable runnable) {
        Runnable runnableE;
        this.f.a(runnable);
        if (h.get(this) >= this.e || !F() || (runnableE = E()) == null) {
            return;
        }
        this.d.t(this, new m1(this, 6, runnableE));
    }
}
