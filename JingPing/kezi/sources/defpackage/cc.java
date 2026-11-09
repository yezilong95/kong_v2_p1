package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class cc extends cz {
    public final ma f;

    public cc(ma maVar) {
        this.f = maVar;
    }

    @Override // defpackage.pu
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) throws IllegalAccessException, InvocationTargetException {
        j((Throwable) obj);
        return bq0.a;
    }

    @Override // defpackage.jz
    public final void j(Throwable th) throws IllegalAccessException, InvocationTargetException {
        nz nzVarI = i();
        ma maVar = this.f;
        maVar.getClass();
        CancellationException cancellationExceptionL = nzVarI.l();
        if (maVar.o()) {
            fh fhVar = maVar.e;
            oy.h(fhVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
            yk ykVar = (yk) fhVar;
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = yk.i;
                Object obj = atomicReferenceFieldUpdater.get(ykVar);
                nm nmVar = oy.c;
                if (!oy.c(obj, nmVar)) {
                    if (!(obj instanceof Throwable)) {
                        while (!atomicReferenceFieldUpdater.compareAndSet(ykVar, obj, null)) {
                            if (atomicReferenceFieldUpdater.get(ykVar) != obj) {
                                break;
                            }
                        }
                        break loop0;
                    }
                    return;
                }
                while (!atomicReferenceFieldUpdater.compareAndSet(ykVar, nmVar, cancellationExceptionL)) {
                    if (atomicReferenceFieldUpdater.get(ykVar) != nmVar) {
                        break;
                    }
                }
                return;
            }
        }
        maVar.j(cancellationExceptionL);
        if (maVar.o()) {
            return;
        }
        maVar.k();
    }
}
