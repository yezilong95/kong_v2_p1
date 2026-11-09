package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class d1 extends z91 {
    public final AtomicReferenceFieldUpdater b0;
    public final AtomicReferenceFieldUpdater c0;
    public final AtomicReferenceFieldUpdater d0;
    public final AtomicReferenceFieldUpdater e0;
    public final AtomicReferenceFieldUpdater f0;

    public d1(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.b0 = atomicReferenceFieldUpdater;
        this.c0 = atomicReferenceFieldUpdater2;
        this.d0 = atomicReferenceFieldUpdater3;
        this.e0 = atomicReferenceFieldUpdater4;
        this.f0 = atomicReferenceFieldUpdater5;
    }

    @Override // defpackage.z91
    public final void b0(f1 f1Var, f1 f1Var2) {
        this.c0.lazySet(f1Var, f1Var2);
    }

    @Override // defpackage.z91
    public final void c0(f1 f1Var, Thread thread) {
        this.b0.lazySet(f1Var, thread);
    }

    @Override // defpackage.z91
    public final boolean i(g1 g1Var, c1 c1Var, c1 c1Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.e0;
            if (atomicReferenceFieldUpdater.compareAndSet(g1Var, c1Var, c1Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g1Var) == c1Var);
        return false;
    }

    @Override // defpackage.z91
    public final boolean j(g1 g1Var, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f0;
            if (atomicReferenceFieldUpdater.compareAndSet(g1Var, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g1Var) == obj);
        return false;
    }

    @Override // defpackage.z91
    public final boolean k(g1 g1Var, f1 f1Var, f1 f1Var2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.d0;
            if (atomicReferenceFieldUpdater.compareAndSet(g1Var, f1Var, f1Var2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(g1Var) == f1Var);
        return false;
    }
}
