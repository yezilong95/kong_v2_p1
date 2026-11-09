package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes.dex */
public final class ma extends zk implements la, yh, rt0 {
    public static final AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(ma.class, "_decisionAndIndex");
    public static final AtomicReferenceFieldUpdater h = AtomicReferenceFieldUpdater.newUpdater(ma.class, Object.class, "_state");
    public static final AtomicReferenceFieldUpdater i = AtomicReferenceFieldUpdater.newUpdater(ma.class, Object.class, "_parentHandle");
    private volatile int _decisionAndIndex;
    private volatile Object _parentHandle;
    private volatile Object _state;
    public final fh e;
    public final nh f;

    public ma(int i2, fh fhVar) {
        super(i2);
        this.e = fhVar;
        this.f = fhVar.getContext();
        this._decisionAndIndex = 536870911;
        this._state = s1.a;
    }

    public static void p(uh0 uh0Var, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + uh0Var + ", already has " + obj).toString());
    }

    @Override // defpackage.rt0
    public final void a(vb vbVar, int i2) throws IllegalAccessException, InvocationTargetException {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i3;
        do {
            atomicIntegerFieldUpdater = g;
            i3 = atomicIntegerFieldUpdater.get(this);
            if ((i3 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, ((i3 >> 29) << 29) + i2));
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof s1) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, vbVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return;
            }
            if (obj instanceof uh0) {
                p(vbVar, obj);
                throw null;
            }
            if (!(obj instanceof ue)) {
                if (!(obj instanceof se)) {
                    if (vbVar != null) {
                        return;
                    }
                    oy.h(vbVar, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    throw new ClassCastException();
                }
                ((se) obj).getClass();
                if (vbVar != null) {
                    return;
                }
                oy.h(vbVar, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                throw new ClassCastException();
            }
            ue ueVar = (ue) obj;
            ueVar.getClass();
            if (!ue.b.compareAndSet(ueVar, 0, 1)) {
                p(vbVar, obj);
                throw null;
            }
            if (obj instanceof pa) {
                if (obj == null) {
                    ueVar = null;
                }
                Throwable th = ueVar != null ? ueVar.a : null;
                oy.h(vbVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                i(vbVar, th);
                return;
            }
            return;
        }
    }

    @Override // defpackage.zk
    public final void b(Object obj, CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof r90) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof ue) {
                return;
            }
            if (!(obj2 instanceof se)) {
                se seVar = new se(obj2, (wa0) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, seVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                return;
            }
            se seVar2 = (se) obj2;
            if (seVar2.d != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            Object obj3 = seVar2.a;
            if ((15 & 2) != 0) {
                seVar2.getClass();
            }
            pu puVar = seVar2.b;
            Object obj4 = seVar2.c;
            Throwable th = (15 & 16) != 0 ? seVar2.d : cancellationException;
            seVar2.getClass();
            se seVar3 = new se(obj3, puVar, obj4, th);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, seVar3)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            pu puVar2 = seVar2.b;
            if (puVar2 != null) {
                h(puVar2, cancellationException);
                return;
            }
            return;
        }
    }

    @Override // defpackage.zk
    public final fh c() {
        return this.e;
    }

    @Override // defpackage.zk
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // defpackage.zk
    public final Object e(Object obj) {
        return obj instanceof se ? ((se) obj).a : obj;
    }

    @Override // defpackage.zk
    public final Object g() {
        return h.get(this);
    }

    @Override // defpackage.yh
    public final yh getCallerFrame() {
        fh fhVar = this.e;
        if (fhVar instanceof yh) {
            return (yh) fhVar;
        }
        return null;
    }

    @Override // defpackage.fh
    public final nh getContext() {
        return this.f;
    }

    public final void h(pu puVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        try {
            puVar.invoke(th);
        } catch (Throwable th2) {
            rp0.E(this.f, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public final void i(uh0 uh0Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        nh nhVar = this.f;
        int i2 = g.get(this) & 536870911;
        if (i2 == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            uh0Var.g(i2, nhVar);
        } catch (Throwable th2) {
            rp0.E(nhVar, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void j(Throwable th) throws IllegalAccessException, InvocationTargetException {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof r90) {
                pa paVar = new pa(this, th, obj instanceof uh0);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, paVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                if (((r90) obj) instanceof uh0) {
                    i((uh0) obj, th);
                }
                if (!o()) {
                    k();
                }
                l(this.d);
                return;
            }
            return;
        }
    }

    public final void k() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i;
        dl dlVar = (dl) atomicReferenceFieldUpdater.get(this);
        if (dlVar == null) {
            return;
        }
        dlVar.dispose();
        atomicReferenceFieldUpdater.set(this, q90.b);
    }

    public final void l(int i2) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i3;
        do {
            atomicIntegerFieldUpdater = g;
            i3 = atomicIntegerFieldUpdater.get(this);
            int i4 = i3 >> 29;
            if (i4 != 0) {
                if (i4 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z = i2 == 4;
                fh fhVar = this.e;
                if (!z && (fhVar instanceof yk)) {
                    boolean z2 = i2 == 1 || i2 == 2;
                    int i5 = this.d;
                    if (z2 == (i5 == 1 || i5 == 2)) {
                        qh qhVar = ((yk) fhVar).e;
                        nh context = ((yk) fhVar).f.getContext();
                        if (qhVar.D()) {
                            qhVar.t(context, this);
                            return;
                        }
                        oo ooVarA = xn0.a();
                        if (ooVarA.d >= 4294967296L) {
                            ooVarA.F(this);
                            return;
                        }
                        ooVarA.H(true);
                        try {
                            jq0.b0(this, fhVar, true);
                            do {
                            } while (ooVarA.I());
                        } finally {
                            try {
                            } finally {
                            }
                        }
                        return;
                    }
                }
                jq0.b0(this, fhVar, z);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i3, 1073741824 + (536870911 & i3)));
    }

    public final Object m() throws Throwable {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        boolean zO = o();
        do {
            atomicIntegerFieldUpdater = g;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zO) {
                    q();
                }
                Object obj = h.get(this);
                if (obj instanceof ue) {
                    throw ((ue) obj).a;
                }
                int i4 = this.d;
                if (i4 == 1 || i4 == 2) {
                    bz bzVar = (bz) this.f.get(qm.f);
                    if (bzVar != null && !bzVar.a()) {
                        CancellationException cancellationExceptionL = ((nz) bzVar).l();
                        b(obj, cancellationExceptionL);
                        throw cancellationExceptionL;
                    }
                }
                return e(obj);
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 536870912 + (536870911 & i2)));
        if (((dl) i.get(this)) == null) {
            n();
        }
        if (zO) {
            q();
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    public final dl n() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        bz bzVar = (bz) this.f.get(qm.f);
        if (bzVar == null) {
            return null;
        }
        dl dlVarF = rp0.F(bzVar, new cc(this), 2);
        do {
            atomicReferenceFieldUpdater = i;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, dlVarF)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return dlVarF;
    }

    public final boolean o() {
        if (this.d != 2) {
            return false;
        }
        fh fhVar = this.e;
        oy.h(fhVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return yk.i.get((yk) fhVar) != null;
    }

    public final void q() throws IllegalAccessException, InvocationTargetException {
        fh fhVar = this.e;
        Throwable th = null;
        yk ykVar = fhVar instanceof yk ? (yk) fhVar : null;
        if (ykVar != null) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = yk.i;
                Object obj = atomicReferenceFieldUpdater.get(ykVar);
                nm nmVar = oy.c;
                if (obj == nmVar) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(ykVar, nmVar, this)) {
                        if (atomicReferenceFieldUpdater.get(ykVar) != nmVar) {
                            break;
                        }
                    }
                    break loop0;
                } else {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(ykVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(ykVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = (Throwable) obj;
                }
            }
            if (th == null) {
                return;
            }
            k();
            j(th);
        }
    }

    public final void r(Object obj, int i2, wa0 wa0Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = h;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof r90)) {
                if (obj2 instanceof pa) {
                    pa paVar = (pa) obj2;
                    paVar.getClass();
                    if (pa.c.compareAndSet(paVar, 0, 1)) {
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            if (!(obj instanceof ue) && i2 != 1) {
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, obj)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (!o()) {
                k();
            }
            l(i2);
            return;
        }
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            obj = new ue(false, thM6exceptionOrNullimpl);
        }
        r(obj, this.d, null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CancellableContinuation(");
        sb.append(xi.R(this.e));
        sb.append("){");
        Object obj = h.get(this);
        sb.append(obj instanceof r90 ? "Active" : obj instanceof pa ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(xi.y(this));
        return sb.toString();
    }
}
