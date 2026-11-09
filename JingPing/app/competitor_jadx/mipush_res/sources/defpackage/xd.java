package defpackage;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class xd extends g50 {
    public final ec e;

    public xd(ec ecVar) {
        this.e = ecVar;
    }

    @Override // defpackage.s40
    public final void b(Throwable th) {
        t50 t50VarH = h();
        ec ecVar = this.e;
        ecVar.getClass();
        CancellationException cancellationExceptionK = t50VarH.k();
        if (ecVar.o()) {
            ui uiVar = ecVar.H;
            t40.l(uiVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            vo voVar = (vo) uiVar;
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = vo.L;
                Object obj = atomicReferenceFieldUpdater.get(voVar);
                wy0 wy0Var = t40.d;
                if (!t40.f(obj, wy0Var)) {
                    if (!(obj instanceof Throwable)) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(voVar, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(voVar) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    return;
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(voVar, wy0Var, cancellationExceptionK)) {
                    if (atomicReferenceFieldUpdater.get(voVar) != wy0Var) {
                        break;
                    }
                }
                return;
            }
        }
        ecVar.j(cancellationExceptionK);
        if (ecVar.o()) {
            return;
        }
        ecVar.k();
    }
}
