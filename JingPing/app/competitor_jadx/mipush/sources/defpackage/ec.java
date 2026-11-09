package defpackage;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes.dex */
public final class ec extends wo implements dc, mj, d71 {
    public static final /* synthetic */ AtomicIntegerFieldUpdater J = AtomicIntegerFieldUpdater.newUpdater(ec.class, "_decisionAndIndex$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater K = AtomicReferenceFieldUpdater.newUpdater(ec.class, Object.class, "_state$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater L = AtomicReferenceFieldUpdater.newUpdater(ec.class, Object.class, "_parentHandle$volatile");
    public final ui H;
    public final bj I;
    private volatile /* synthetic */ int _decisionAndIndex$volatile;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    public ec(int i, ui uiVar) {
        super(i);
        this.H = uiVar;
        this.I = uiVar.getContext();
        this._decisionAndIndex$volatile = 536870911;
        this._state$volatile = n3.a;
    }

    public static void p(ju0 ju0Var, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + ju0Var + ", already has " + obj).toString());
    }

    @Override // defpackage.d71
    public final void a(pd pdVar, int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = J;
            i2 = atomicIntegerFieldUpdater.get(this);
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, ((i2 >> 29) << 29) + i));
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = K;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof n3) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, pdVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                return;
            }
            if (obj instanceof ju0) {
                p(pdVar, obj);
                throw null;
            }
            if (!(obj instanceof og)) {
                if (!(obj instanceof mg)) {
                    if (pdVar != null) {
                        return;
                    }
                    t40.l(pdVar, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    throw new ClassCastException();
                }
                ((mg) obj).getClass();
                if (pdVar != null) {
                    return;
                }
                t40.l(pdVar, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                throw new ClassCastException();
            }
            og ogVar = (og) obj;
            ogVar.getClass();
            if (!og.b.compareAndSet(ogVar, 0, 1)) {
                p(pdVar, obj);
                throw null;
            }
            if (obj instanceof hc) {
                if (obj == null) {
                    ogVar = null;
                }
                Throwable th = ogVar != null ? ogVar.a : null;
                t40.l(pdVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                i(pdVar, th);
                return;
            }
            return;
        }
    }

    @Override // defpackage.wo
    public final void b(Object obj, CancellationException cancellationException) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = K;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof xh0) {
                throw new IllegalStateException("Not completed");
            }
            if (obj2 instanceof og) {
                return;
            }
            if (!(obj2 instanceof mg)) {
                mg mgVar = new mg(obj2, (lj0) null, cancellationException, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, mgVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj2) {
                        break;
                    }
                }
                return;
            }
            mg mgVar2 = (mg) obj2;
            if (mgVar2.d != null) {
                throw new IllegalStateException("Must be called at most once");
            }
            Object obj3 = mgVar2.a;
            if ((15 & 2) != 0) {
                mgVar2.getClass();
            }
            b10 b10Var = mgVar2.b;
            Object obj4 = mgVar2.c;
            Throwable th = (15 & 16) != 0 ? mgVar2.d : cancellationException;
            mgVar2.getClass();
            mg mgVar3 = new mg(obj3, b10Var, obj4, th);
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, mgVar3)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            b10 b10Var2 = mgVar2.b;
            if (b10Var2 != null) {
                h(b10Var2, cancellationException);
                return;
            }
            return;
        }
    }

    @Override // defpackage.wo
    public final ui c() {
        return this.H;
    }

    @Override // defpackage.wo
    public final Throwable d(Object obj) {
        Throwable thD = super.d(obj);
        if (thD != null) {
            return thD;
        }
        return null;
    }

    @Override // defpackage.wo
    public final Object e(Object obj) {
        return obj instanceof mg ? ((mg) obj).a : obj;
    }

    @Override // defpackage.wo
    public final Object g() {
        return K.get(this);
    }

    @Override // defpackage.mj
    public final mj getCallerFrame() {
        ui uiVar = this.H;
        if (uiVar instanceof mj) {
            return (mj) uiVar;
        }
        return null;
    }

    @Override // defpackage.ui
    public final bj getContext() {
        return this.I;
    }

    public final void h(b10 b10Var, Throwable th) {
        try {
            b10Var.invoke(th);
        } catch (Throwable th2) {
            z91.D(this.I, new CompletionHandlerException("Exception in resume onCancellation handler for " + this, th2));
        }
    }

    public final void i(ju0 ju0Var, Throwable th) {
        bj bjVar = this.I;
        int i = J.get(this) & 536870911;
        if (i == 536870911) {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
        try {
            ju0Var.g(i, bjVar);
        } catch (Throwable th2) {
            z91.D(bjVar, new CompletionHandlerException("Exception in invokeOnCancellation handler for " + this, th2));
        }
    }

    public final void j(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = K;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof xh0) {
                hc hcVar = new hc(this, th, obj instanceof ju0);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, hcVar)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                        break;
                    }
                }
                if (((xh0) obj) instanceof ju0) {
                    i((ju0) obj, th);
                }
                if (!o()) {
                    k();
                }
                l(this.G);
                return;
            }
            return;
        }
    }

    public final void k() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = L;
        ap apVar = (ap) atomicReferenceFieldUpdater.get(this);
        if (apVar == null) {
            return;
        }
        apVar.dispose();
        atomicReferenceFieldUpdater.set(this, wh0.a);
    }

    public final void l(int i) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        do {
            atomicIntegerFieldUpdater = J;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                boolean z = i == 4;
                ui uiVar = this.H;
                if (!z && (uiVar instanceof vo)) {
                    boolean z2 = i == 1 || i == 2;
                    int i4 = this.G;
                    if (z2 == (i4 == 1 || i4 == 2)) {
                        ej ejVar = ((vo) uiVar).H;
                        bj context = ((vo) uiVar).I.getContext();
                        if (ejVar.w()) {
                            ejVar.j(context, this);
                            return;
                        }
                        nu nuVarA = l01.a();
                        if (nuVarA.G >= 4294967296L) {
                            nuVarA.y(this);
                            return;
                        }
                        nuVarA.A(true);
                        try {
                            j31.X(this, uiVar, true);
                            do {
                            } while (nuVarA.B());
                        } finally {
                            try {
                            } finally {
                            }
                        }
                        return;
                    }
                }
                j31.X(this, uiVar, z);
                return;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, 1073741824 + (536870911 & i2)));
    }

    public final Object m() throws Throwable {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i;
        boolean zO = o();
        do {
            atomicIntegerFieldUpdater = J;
            i = atomicIntegerFieldUpdater.get(this);
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 != 2) {
                    throw new IllegalStateException("Already suspended");
                }
                if (zO) {
                    q();
                }
                Object obj = K.get(this);
                if (obj instanceof og) {
                    throw ((og) obj).a;
                }
                int i3 = this.G;
                if (i3 == 1 || i3 == 2) {
                    f50 f50Var = (f50) this.I.get(is.H);
                    if (f50Var != null && !f50Var.isActive()) {
                        CancellationException cancellationExceptionK = ((t50) f50Var).k();
                        b(obj, cancellationExceptionK);
                        throw cancellationExceptionK;
                    }
                }
                return e(obj);
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i, 536870912 + (536870911 & i)));
        if (((ap) L.get(this)) == null) {
            n();
        }
        if (zO) {
            q();
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    public final ap n() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        f50 f50Var = (f50) this.I.get(is.H);
        if (f50Var == null) {
            return null;
        }
        ap apVarY = j31.y(f50Var, new xd(this), 2);
        do {
            atomicReferenceFieldUpdater = L;
            if (atomicReferenceFieldUpdater.compareAndSet(this, null, apVarY)) {
                break;
            }
        } while (atomicReferenceFieldUpdater.get(this) == null);
        return apVarY;
    }

    public final boolean o() {
        if (this.G != 2) {
            return false;
        }
        ui uiVar = this.H;
        t40.l(uiVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        return vo.L.get((vo) uiVar) != null;
    }

    public final void q() {
        ui uiVar = this.H;
        Throwable th = null;
        vo voVar = uiVar instanceof vo ? (vo) uiVar : null;
        if (voVar != null) {
            loop0: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = vo.L;
                Object obj = atomicReferenceFieldUpdater.get(voVar);
                wy0 wy0Var = t40.d;
                if (obj == wy0Var) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(voVar, wy0Var, this)) {
                        if (atomicReferenceFieldUpdater.get(voVar) != wy0Var) {
                            break;
                        }
                    }
                    break loop0;
                } else {
                    if (!(obj instanceof Throwable)) {
                        throw new IllegalStateException(("Inconsistent state " + obj).toString());
                    }
                    while (!atomicReferenceFieldUpdater.compareAndSet(voVar, obj, null)) {
                        if (atomicReferenceFieldUpdater.get(voVar) != obj) {
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

    public final void r(Object obj, int i, lj0 lj0Var) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = K;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof xh0)) {
                if (obj2 instanceof hc) {
                    hc hcVar = (hc) obj2;
                    hcVar.getClass();
                    if (hc.c.compareAndSet(hcVar, 0, 1)) {
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
            if (!(obj instanceof og) && i != 1) {
            }
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj2, obj)) {
                if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    break;
                }
            }
            if (!o()) {
                k();
            }
            l(i);
            return;
        }
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        if (thM6exceptionOrNullimpl != null) {
            obj = new og(false, thM6exceptionOrNullimpl);
        }
        r(obj, this.G, null);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CancellableContinuation(");
        sb.append(qm.H0(this.H));
        sb.append("){");
        Object obj = K.get(this);
        sb.append(obj instanceof xh0 ? "Active" : obj instanceof hc ? "Cancelled" : "Completed");
        sb.append("}@");
        sb.append(qm.C(this));
        return sb.toString();
    }
}
