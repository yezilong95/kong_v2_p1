package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class t extends rp0 {
    public final AtomicReferenceFieldUpdater e;
    public final AtomicReferenceFieldUpdater f;
    public final AtomicReferenceFieldUpdater g;
    public final AtomicReferenceFieldUpdater h;
    public final AtomicReferenceFieldUpdater i;

    public t(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.e = atomicReferenceFieldUpdater;
        this.f = atomicReferenceFieldUpdater2;
        this.g = atomicReferenceFieldUpdater3;
        this.h = atomicReferenceFieldUpdater4;
        this.i = atomicReferenceFieldUpdater5;
    }

    @Override // defpackage.rp0
    public final void P(v vVar, v vVar2) {
        this.f.lazySet(vVar, vVar2);
    }

    @Override // defpackage.rp0
    public final void Q(v vVar, Thread thread) {
        this.e.lazySet(vVar, thread);
    }

    @Override // defpackage.rp0
    public final boolean j(w wVar, s sVar, s sVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.h;
            if (atomicReferenceFieldUpdater.compareAndSet(wVar, sVar, sVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(wVar) == sVar);
        return false;
    }

    @Override // defpackage.rp0
    public final boolean k(w wVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.i;
            if (atomicReferenceFieldUpdater.compareAndSet(wVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(wVar) == obj);
        return false;
    }

    @Override // defpackage.rp0
    public final boolean l(w wVar, v vVar, v vVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.g;
            if (atomicReferenceFieldUpdater.compareAndSet(wVar, vVar, vVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(wVar) == vVar);
        return false;
    }
}
