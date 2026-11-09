package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ch {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(ch.class, Object.class, "_next$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(ch.class, Object.class, "_prev$volatile");
    private volatile /* synthetic */ Object _next$volatile;
    private volatile /* synthetic */ Object _prev$volatile;

    public ch(ju0 ju0Var) {
        this._prev$volatile = ju0Var;
    }

    public final void a() {
        b.set(this, null);
    }

    public final ch b() {
        Object obj = a.get(this);
        if (obj == l21.m) {
            return null;
        }
        return (ch) obj;
    }

    public abstract boolean c();

    public final void d() {
        ch chVarB;
        if (b() == null) {
            return;
        }
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
            ch chVar = (ch) atomicReferenceFieldUpdater.get(this);
            while (chVar != null && chVar.c()) {
                chVar = (ch) atomicReferenceFieldUpdater.get(chVar);
            }
            ch chVarB2 = b();
            t40.k(chVarB2);
            while (chVarB2.c() && (chVarB = chVarB2.b()) != null) {
                chVarB2 = chVarB;
            }
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(chVarB2);
                ch chVar2 = ((ch) obj) == null ? null : chVar;
                while (!atomicReferenceFieldUpdater.compareAndSet(chVarB2, obj, chVar2)) {
                    if (atomicReferenceFieldUpdater.get(chVarB2) != obj) {
                        break;
                    }
                }
            }
            if (chVar != null) {
                a.set(chVar, chVarB2);
            }
            if (!chVarB2.c() || chVarB2.b() == null) {
                if (chVar == null || !chVar.c()) {
                    return;
                }
            }
        }
    }
}
