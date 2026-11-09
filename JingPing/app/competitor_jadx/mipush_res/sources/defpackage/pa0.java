package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class pa0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(pa0.class, Object.class, "_cur$volatile");
    private volatile /* synthetic */ Object _cur$volatile = new ra0(8, false);

    public final boolean a(Runnable runnable) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            ra0 ra0Var = (ra0) atomicReferenceFieldUpdater.get(this);
            int iA = ra0Var.a(runnable);
            if (iA == 0) {
                return true;
            }
            if (iA == 1) {
                ra0 ra0VarC = ra0Var.c();
                while (!atomicReferenceFieldUpdater.compareAndSet(this, ra0Var, ra0VarC) && atomicReferenceFieldUpdater.get(this) == ra0Var) {
                }
            } else if (iA == 2) {
                return false;
            }
        }
    }

    public final void b() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            ra0 ra0Var = (ra0) atomicReferenceFieldUpdater.get(this);
            if (ra0Var.b()) {
                return;
            }
            ra0 ra0VarC = ra0Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, ra0Var, ra0VarC) && atomicReferenceFieldUpdater.get(this) == ra0Var) {
            }
        }
    }

    public final int c() {
        ra0 ra0Var = (ra0) a.get(this);
        ra0Var.getClass();
        long j = ra0.f.get(ra0Var);
        return (((int) ((j & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j))) & 1073741823;
    }

    public final Object d() {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
            ra0 ra0Var = (ra0) atomicReferenceFieldUpdater.get(this);
            Object objD = ra0Var.d();
            if (objD != ra0.g) {
                return objD;
            }
            ra0 ra0VarC = ra0Var.c();
            while (!atomicReferenceFieldUpdater.compareAndSet(this, ra0Var, ra0VarC) && atomicReferenceFieldUpdater.get(this) == ra0Var) {
            }
        }
    }
}
