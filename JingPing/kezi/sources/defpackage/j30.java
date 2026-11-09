package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class j30 {
    public static final AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(j30.class, Object.class, "_cur");
    private volatile Object _cur = new l30(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            l30 l30Var = (l30) atomicReferenceFieldUpdater.get(this);
            int iA = l30Var.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                l30 l30VarC = l30Var.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, l30Var, l30VarC) && atomicReferenceFieldUpdater.get(this) == l30Var) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            l30 l30Var = (l30) atomicReferenceFieldUpdater.get(this);
            if (l30Var.b()) {
                return;
            }
            l30 l30VarC = l30Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, l30Var, l30VarC) && atomicReferenceFieldUpdater.get(this) == l30Var) {
            }
        }
    }

    public final int c() {
        l30 l30Var = (l30) a.get(this);
        l30Var.getClass();
        long j = l30.f.get(l30Var);
        return (((int) ((j & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            l30 l30Var = (l30) atomicReferenceFieldUpdater.get(this);
            Object objD = l30Var.d();
            if (objD != l30.g) {
                return objD;
            }
            l30 l30VarC = l30Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, l30Var, l30VarC) && atomicReferenceFieldUpdater.get(this) == l30Var) {
            }
        }
    }
}
