package defpackage;

import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class hh extends x0 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater F = AtomicIntegerFieldUpdater.newUpdater(hh.class, "_size$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater G = AtomicReferenceFieldUpdater.newUpdater(hh.class, Object.class, "core$volatile");
    public final ReferenceQueue E;
    private volatile /* synthetic */ int _size$volatile;
    private volatile /* synthetic */ Object core$volatile = new eh(this, 16);

    public hh(boolean z) {
        this.E = z ? new ReferenceQueue() : null;
    }

    public final synchronized Object a(Object obj, Object obj2) {
        Object objA;
        eh ehVarB = (eh) G.get(this);
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = eh.g;
            objA = ehVarB.a(obj, obj2, null);
            if (objA == j31.d) {
                ehVarB = ehVarB.b();
                G.set(this, ehVarB);
            }
        }
        return objA;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Iterator it = ((gh) keySet()).iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj == null) {
            return null;
        }
        eh ehVar = (eh) G.get(this);
        ehVar.getClass();
        int iHashCode = (obj.hashCode() * (-1640531527)) >>> ehVar.b;
        while (true) {
            y20 y20Var = (y20) ehVar.d.get(iHashCode);
            if (y20Var == null) {
                return null;
            }
            Object obj2 = y20Var.get();
            if (obj.equals(obj2)) {
                Object obj3 = ehVar.e.get(iHashCode);
                return obj3 instanceof fc0 ? ((fc0) obj3).a : obj3;
            }
            if (obj2 == null) {
                ehVar.c(iHashCode);
            }
            if (iHashCode == 0) {
                iHashCode = ehVar.a;
            }
            iHashCode--;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        eh ehVar = (eh) G.get(this);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = eh.g;
        Object objA = ehVar.a(obj, obj2, null);
        if (objA == j31.d) {
            objA = a(obj, obj2);
        }
        if (objA == null) {
            F.incrementAndGet(this);
        }
        return objA;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (obj == null) {
            return null;
        }
        eh ehVar = (eh) G.get(this);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = eh.g;
        Object objA = ehVar.a(obj, null, null);
        if (objA == j31.d) {
            objA = a(obj, null);
        }
        if (objA != null) {
            F.decrementAndGet(this);
        }
        return objA;
    }
}
