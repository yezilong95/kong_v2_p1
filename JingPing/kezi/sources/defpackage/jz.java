package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class jz extends i30 implements dl, sx, pu {
    public nz e;

    @Override // defpackage.sx
    public final boolean a() {
        return true;
    }

    @Override // defpackage.sx
    public final p90 c() {
        return null;
    }

    @Override // defpackage.dl
    public final void dispose() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2;
        nz nzVarI = i();
        while (true) {
            Object objO = nzVarI.o();
            if (objO instanceof jz) {
                if (objO != this) {
                    return;
                }
                xn xnVar = nv0.i;
                do {
                    atomicReferenceFieldUpdater2 = nz.b;
                    if (atomicReferenceFieldUpdater2.compareAndSet(nzVarI, objO, xnVar)) {
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(nzVarI) == objO);
            } else {
                if (!(objO instanceof sx) || ((sx) objO).c() == null) {
                    return;
                }
                while (true) {
                    Object objF = f();
                    if (objF instanceof ke0) {
                        i30 i30Var = ((ke0) objF).a;
                        return;
                    }
                    if (objF == this) {
                        return;
                    }
                    oy.h(objF, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
                    i30 i30Var2 = (i30) objF;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = i30.d;
                    ke0 ke0Var = (ke0) atomicReferenceFieldUpdater3.get(i30Var2);
                    if (ke0Var == null) {
                        ke0Var = new ke0(i30Var2);
                        atomicReferenceFieldUpdater3.lazySet(i30Var2, ke0Var);
                    }
                    do {
                        atomicReferenceFieldUpdater = i30.b;
                        if (atomicReferenceFieldUpdater.compareAndSet(this, objF, ke0Var)) {
                            i30Var2.d();
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(this) == objF);
                }
            }
        }
    }

    public bz getParent() {
        return i();
    }

    public final nz i() {
        nz nzVar = this.e;
        if (nzVar != null) {
            return nzVar;
        }
        oy.S("job");
        throw null;
    }

    public abstract void j(Throwable th);

    @Override // defpackage.i30
    public final String toString() {
        return getClass().getSimpleName() + '@' + xi.y(this) + "[job@" + xi.y(i()) + ']';
    }
}
