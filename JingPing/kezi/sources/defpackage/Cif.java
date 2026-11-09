package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: renamed from: if, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public abstract class Cif {
    public static final AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(Cif.class, Object.class, "_next");
    public static final AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(Cif.class, Object.class, "_prev");
    private volatile Object _next;
    private volatile Object _prev;

    public Cif(uh0 uh0Var) {
        this._prev = uh0Var;
    }

    public final void a() {
        b.lazySet(this, null);
    }

    public final Cif b() {
        Object obj = a.get(this);
        if (obj == nv0.b) {
            return null;
        }
        return (Cif) obj;
    }

    public abstract boolean c();

    public final void d() {
        Cif cifB;
        if (b() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
            Cif cif = (Cif) atomicReferenceFieldUpdater.get(this);
            while (cif != null && cif.c()) {
                cif = (Cif) atomicReferenceFieldUpdater.get(cif);
            }
            Cif cifB2 = b();
            oy.g(cifB2);
            while (cifB2.c() && (cifB = cifB2.b()) != null) {
                cifB2 = cifB;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(cifB2);
                Cif cif2 = ((Cif) obj) == null ? null : cif;
                while (!atomicReferenceFieldUpdater.compareAndSet(cifB2, obj, cif2)) {
                    if (atomicReferenceFieldUpdater.get(cifB2) != obj) {
                        break;
                    }
                }
            }
            if (cif != null) {
                a.set(cif, cifB2);
            }
            if (!cifB2.c() || cifB2.b() == null) {
                if (cif == null || !cif.c()) {
                    return;
                }
            }
        }
    }
}
