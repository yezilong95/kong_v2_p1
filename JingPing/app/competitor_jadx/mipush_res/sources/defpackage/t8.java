package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class t8 extends nj0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(t8.class, Object.class, "_consensus$volatile");
    private volatile /* synthetic */ Object _consensus$volatile = j31.c;

    @Override // defpackage.nj0
    public final Object a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        wy0 wy0Var = j31.c;
        if (obj2 == wy0Var) {
            wy0 wy0VarC = c(obj);
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 == wy0Var) {
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, wy0Var, wy0VarC)) {
                        obj2 = wy0VarC;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != wy0Var) {
                        obj2 = atomicReferenceFieldUpdater.get(this);
                        break;
                    }
                }
            }
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(Object obj, Object obj2);

    public abstract wy0 c(Object obj);
}
