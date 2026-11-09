package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class l7 extends ya0 {
    public static final AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(l7.class, Object.class, "_consensus");
    private volatile Object _consensus = oy.a;

    @Override // defpackage.ya0
    public final Object a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        nm nmVar = oy.a;
        if (obj2 == nmVar) {
            nm nmVarC = c(obj);
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 == nmVar) {
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, nmVar, nmVarC)) {
                        obj2 = nmVarC;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != nmVar) {
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

    public abstract nm c(Object obj);
}
