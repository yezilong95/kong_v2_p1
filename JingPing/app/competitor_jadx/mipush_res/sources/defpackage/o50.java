package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class o50 extends oa0 implements s40, ap, w30 {
    public t50 d;

    @Override // defpackage.ap
    public final void dispose() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2;
        t50 t50VarH = h();
        while (true) {
            Object objN = t50VarH.n();
            if (objN instanceof o50) {
                if (objN != this) {
                    return;
                }
                pt ptVar = j31.v;
                do {
                    atomicReferenceFieldUpdater2 = t50.E;
                    if (atomicReferenceFieldUpdater2.compareAndSet(t50VarH, objN, ptVar)) {
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(t50VarH) == objN);
            } else {
                if (!(objN instanceof w30) || ((w30) objN).getList() == null) {
                    return;
                }
                while (true) {
                    Object objE = e();
                    if (objE instanceof ko0) {
                        oa0 oa0Var = ((ko0) objE).a;
                        return;
                    }
                    if (objE == this) {
                        return;
                    }
                    t40.l(objE, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    oa0 oa0Var2 = (oa0) objE;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = oa0.c;
                    ko0 ko0Var = (ko0) atomicReferenceFieldUpdater3.get(oa0Var2);
                    if (ko0Var == null) {
                        ko0Var = new ko0(oa0Var2);
                        atomicReferenceFieldUpdater3.set(oa0Var2, ko0Var);
                    }
                    do {
                        atomicReferenceFieldUpdater = oa0.a;
                        if (atomicReferenceFieldUpdater.compareAndSet(this, objE, ko0Var)) {
                            oa0Var2.c();
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == objE);
                }
            }
        }
    }

    @Override // defpackage.w30
    public final th0 getList() {
        return null;
    }

    public f50 getParent() {
        return h();
    }

    public final t50 h() {
        t50 t50Var = this.d;
        if (t50Var != null) {
            return t50Var;
        }
        t40.o1("job");
        throw null;
    }

    @Override // defpackage.w30
    public final boolean isActive() {
        return true;
    }

    @Override // defpackage.oa0
    public final String toString() {
        return getClass().getSimpleName() + '@' + qm.C(this) + "[job@" + qm.C(h()) + ']';
    }
}
