package defpackage;

import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class nf extends n {
    public static final AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(nf.class, "_size");
    public static final AtomicReferenceFieldUpdater d = AtomicReferenceFieldUpdater.newUpdater(nf.class, Object.class, "core");
    private volatile int _size;
    public final ReferenceQueue b;
    private volatile Object core = new kf(this, 16);

    public nf(boolean z) {
        this.b = z ? new ReferenceQueue() : null;
    }

    public final synchronized Object a(Object obj, Object obj2) {
        Object objA;
        kf kfVarB = (kf) d.get(this);
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = kf.g;
            objA = kfVarB.a(obj, obj2, null);
            if (objA == k9.b) {
                kfVarB = kfVarB.b();
                d.set(this, kfVarB);
            }
        }
        return objA;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Iterator it = ((mf) keySet()).iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        kf kfVar = (kf) d.get(this);
        kfVar.getClass();
        int iHashCode = (obj.hashCode() * (-1640531527)) >>> kfVar.b;
        while (true) {
            rv rvVar = (rv) kfVar.d.get(iHashCode);
            if (rvVar == null) {
                return null;
            }
            Object obj2 = rvVar.get();
            if (obj.equals(obj2)) {
                Object obj3 = kfVar.e.get(iHashCode);
                return obj3 instanceof p40 ? ((p40) obj3).a : obj3;
            }
            if (obj2 == null) {
                kfVar.c(iHashCode);
            }
            if (iHashCode == 0) {
                iHashCode = kfVar.a;
            }
            iHashCode--;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        kf kfVar = (kf) d.get(this);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = kf.g;
        Object objA = kfVar.a(obj, obj2, null);
        if (objA == k9.b) {
            objA = a(obj, obj2);
        }
        if (objA == null) {
            c.incrementAndGet(this);
        }
        return objA;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        kf kfVar = (kf) d.get(this);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = kf.g;
        Object objA = kfVar.a(obj, null, null);
        if (objA == k9.b) {
            objA = a(obj, null);
        }
        if (objA != null) {
            c.decrementAndGet(this);
        }
        return objA;
    }
}
