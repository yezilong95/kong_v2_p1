package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CompletionHandlerException;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.TimeoutCancellationException;

/* JADX INFO: loaded from: classes.dex */
public class nz implements bz, mb0 {
    public static final AtomicReferenceFieldUpdater b = AtomicReferenceFieldUpdater.newUpdater(nz.class, Object.class, "_state");
    public static final AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(nz.class, Object.class, "_parentHandle");
    private volatile Object _parentHandle;
    private volatile Object _state;

    public nz(boolean z) {
        this._state = z ? nv0.i : nv0.h;
    }

    public static ec t(i30 i30Var) {
        while (i30Var.h()) {
            i30 i30VarD = i30Var.d();
            if (i30VarD == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i30.c;
                Object obj = atomicReferenceFieldUpdater.get(i30Var);
                while (true) {
                    i30Var = (i30) obj;
                    if (!i30Var.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(i30Var);
                }
            } else {
                i30Var = i30VarD;
            }
        }
        while (true) {
            i30Var = i30Var.g();
            if (!i30Var.h()) {
                if (i30Var instanceof ec) {
                    return (ec) i30Var;
                }
                if (i30Var instanceof p90) {
                    return null;
                }
            }
        }
    }

    public static String y(Object obj) {
        if (!(obj instanceof lz)) {
            return obj instanceof sx ? ((sx) obj).a() ? "Active" : "New" : obj instanceof ue ? "Cancelled" : "Completed";
        }
        lz lzVar = (lz) obj;
        return lzVar.e() ? "Cancelling" : lz.c.get(lzVar) != 0 ? "Completing" : "Active";
    }

    @Override // defpackage.bz
    public boolean a() {
        Object objO = o();
        return (objO instanceof sx) && ((sx) objO).a();
    }

    public final boolean b(sx sxVar, p90 p90Var, jz jzVar) {
        char c2;
        mz mzVar = new mz(jzVar, this, sxVar);
        do {
            i30 i30VarD = p90Var.d();
            if (i30VarD == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = i30.c;
                Object obj = atomicReferenceFieldUpdater.get(p90Var);
                while (true) {
                    i30VarD = (i30) obj;
                    if (!i30VarD.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(i30VarD);
                }
            }
            i30.c.lazySet(jzVar, i30VarD);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i30.b;
            atomicReferenceFieldUpdater2.lazySet(jzVar, p90Var);
            mzVar.c = p90Var;
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(i30VarD, p90Var, mzVar)) {
                    c2 = mzVar.a(i30VarD) == null ? (char) 1 : (char) 2;
                } else if (atomicReferenceFieldUpdater2.get(i30VarD) != p90Var) {
                    c2 = 0;
                    break;
                }
            }
            if (c2 == 1) {
                return true;
            }
        } while (c2 != 2);
        return false;
    }

    public void d(Object obj) {
        c(obj);
    }

    public final boolean e(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Throwable thJ = null;
        loop0: while (true) {
            Object objO = o();
            if (!(objO instanceof lz)) {
                if (!(objO instanceof sx)) {
                    obj2 = nv0.f;
                    break;
                }
                if (thJ == null) {
                    thJ = j(obj);
                }
                sx sxVar = (sx) objO;
                if (sxVar.a()) {
                    p90 p90VarN = n(sxVar);
                    if (p90VarN == null) {
                        continue;
                    } else {
                        lz lzVar = new lz(p90VarN, thJ);
                        do {
                            atomicReferenceFieldUpdater = b;
                            if (atomicReferenceFieldUpdater.compareAndSet(this, sxVar, lzVar)) {
                                u(p90VarN, thJ);
                                obj2 = nv0.c;
                                break loop0;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == sxVar);
                    }
                } else {
                    Object objZ = z(objO, new ue(false, thJ));
                    if (objZ == nv0.c) {
                        throw new IllegalStateException(("Cannot happen in " + objO).toString());
                    }
                    if (objZ != nv0.e) {
                        obj2 = objZ;
                        break;
                    }
                }
            } else {
                synchronized (objO) {
                    try {
                        lz lzVar2 = (lz) objO;
                        lzVar2.getClass();
                        if (lz.e.get(lzVar2) == nv0.g) {
                            obj2 = nv0.f;
                        } else {
                            boolean zE = ((lz) objO).e();
                            if (thJ == null) {
                                thJ = j(obj);
                            }
                            ((lz) objO).b(thJ);
                            Throwable thD = zE ? null : ((lz) objO).d();
                            if (thD != null) {
                                u(((lz) objO).b, thD);
                            }
                            obj2 = nv0.c;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
        if (obj2 == nv0.c || obj2 == nv0.d) {
            return true;
        }
        if (obj2 == nv0.f) {
            return false;
        }
        c(obj2);
        return true;
    }

    public final boolean f(Throwable th) {
        if (r()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        dc dcVar = (dc) c.get(this);
        return (dcVar == null || dcVar == q90.b) ? z : dcVar.b(th) || z;
    }

    @Override // defpackage.nh
    public final Object fold(Object obj, ru ruVar) {
        return k9.n(this, obj, ruVar);
    }

    public String g() {
        return "Job was cancelled";
    }

    @Override // defpackage.nh
    public final lh get(mh mhVar) {
        oy.j(mhVar, "key");
        if (oy.c(qm.f, mhVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.lh
    public final mh getKey() {
        return qm.f;
    }

    public boolean h(Throwable th) {
        return (th instanceof CancellationException) || e(th);
    }

    public final void i(sx sxVar, Object obj) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c;
        dc dcVar = (dc) atomicReferenceFieldUpdater.get(this);
        if (dcVar != null) {
            dcVar.dispose();
            atomicReferenceFieldUpdater.set(this, q90.b);
        }
        CompletionHandlerException completionHandlerException = null;
        ue ueVar = obj instanceof ue ? (ue) obj : null;
        Throwable th = ueVar != null ? ueVar.a : null;
        if (sxVar instanceof jz) {
            try {
                ((jz) sxVar).j(th);
                return;
            } catch (Throwable th2) {
                q(new CompletionHandlerException("Exception in completion handler " + sxVar + " for " + this, th2));
                return;
            }
        }
        p90 p90VarC = sxVar.c();
        if (p90VarC != null) {
            Object objF = p90VarC.f();
            oy.h(objF, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (i30 i30VarG = (i30) objF; !i30VarG.equals(p90VarC); i30VarG = i30VarG.g()) {
                if (i30VarG instanceof jz) {
                    jz jzVar = (jz) i30VarG;
                    try {
                        jzVar.j(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            nv0.c(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jzVar + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                q(completionHandlerException);
            }
        }
    }

    public final Throwable j(Object obj) {
        Throwable thD;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        nz nzVar = (nz) ((mb0) obj);
        Object objO = nzVar.o();
        if (objO instanceof lz) {
            thD = ((lz) objO).d();
        } else if (objO instanceof ue) {
            thD = ((ue) objO).a;
        } else {
            if (objO instanceof sx) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objO).toString());
            }
            thD = null;
        }
        CancellationException cancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        return cancellationException == null ? new JobCancellationException("Parent job is ".concat(y(objO)), thD, nzVar) : cancellationException;
    }

    public final Object k(lz lzVar, Object obj) throws IllegalAccessException, InvocationTargetException {
        Throwable thM;
        ue ueVar = obj instanceof ue ? (ue) obj : null;
        Throwable th = ueVar != null ? ueVar.a : null;
        synchronized (lzVar) {
            lzVar.e();
            ArrayList<Throwable> arrayListF = lzVar.f(th);
            thM = m(lzVar, arrayListF);
            if (thM != null && arrayListF.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListF.size()));
                for (Throwable th2 : arrayListF) {
                    if (th2 != thM && th2 != thM && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        nv0.c(thM, th2);
                    }
                }
            }
        }
        if (thM != null && thM != th) {
            obj = new ue(false, thM);
        }
        if (thM != null && (f(thM) || p(thM))) {
            oy.h(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            ue.b.compareAndSet((ue) obj, 0, 1);
        }
        v(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = b;
        Object txVar = obj instanceof sx ? new tx((sx) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, lzVar, txVar) && atomicReferenceFieldUpdater.get(this) == lzVar) {
        }
        i(lzVar, obj);
        return obj;
    }

    public final CancellationException l() {
        CancellationException jobCancellationException;
        Object objO = o();
        if (!(objO instanceof lz)) {
            if (objO instanceof sx) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(objO instanceof ue)) {
                return new JobCancellationException(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((ue) objO).a;
            jobCancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return jobCancellationException == null ? new JobCancellationException(g(), th, this) : jobCancellationException;
        }
        Throwable thD = ((lz) objO).d();
        if (thD == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        jobCancellationException = thD instanceof CancellationException ? (CancellationException) thD : null;
        if (jobCancellationException == null) {
            if (strConcat == null) {
                strConcat = g();
            }
            jobCancellationException = new JobCancellationException(strConcat, thD, this);
        }
        return jobCancellationException;
    }

    public final Throwable m(lz lzVar, ArrayList arrayList) {
        Object next;
        Object obj = null;
        if (arrayList.isEmpty()) {
            if (lzVar.e()) {
                return new JobCancellationException(g(), null, this);
            }
            return null;
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (!(((Throwable) next) instanceof CancellationException)) {
                break;
            }
        }
        Throwable th = (Throwable) next;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) arrayList.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                Throwable th3 = (Throwable) next2;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    obj = next2;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    @Override // defpackage.nh
    public final nh minusKey(mh mhVar) {
        return k9.y(this, mhVar);
    }

    public final p90 n(sx sxVar) {
        p90 p90VarC = sxVar.c();
        if (p90VarC != null) {
            return p90VarC;
        }
        if (sxVar instanceof xn) {
            return new p90();
        }
        if (sxVar instanceof jz) {
            x((jz) sxVar);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + sxVar).toString());
    }

    public final Object o() {
        while (true) {
            Object obj = b.get(this);
            if (!(obj instanceof ya0)) {
                return obj;
            }
            ((ya0) obj).a(this);
        }
    }

    public boolean p(Throwable th) {
        return false;
    }

    @Override // defpackage.nh
    public final nh plus(nh nhVar) {
        return k9.A(nhVar, this);
    }

    public boolean r() {
        return false;
    }

    public final Object s(Object obj) throws IllegalAccessException, InvocationTargetException {
        Object objZ;
        do {
            objZ = z(o(), obj);
            if (objZ == nv0.c) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                ue ueVar = obj instanceof ue ? (ue) obj : null;
                throw new IllegalStateException(str, ueVar != null ? ueVar.a : null);
            }
        } while (objZ == nv0.e);
        return objZ;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + '{' + y(o()) + '}');
        sb.append('@');
        sb.append(xi.y(this));
        return sb.toString();
    }

    public final void u(p90 p90Var, Throwable th) throws IllegalAccessException, InvocationTargetException {
        Object objF = p90Var.f();
        oy.h(objF, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (i30 i30VarG = (i30) objF; !i30VarG.equals(p90Var); i30VarG = i30VarG.g()) {
            if (i30VarG instanceof cz) {
                jz jzVar = (jz) i30VarG;
                try {
                    jzVar.j(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        nv0.c(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jzVar + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            q(completionHandlerException);
        }
        f(th);
    }

    public final void x(jz jzVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        p90 p90Var = new p90();
        jzVar.getClass();
        i30.c.lazySet(p90Var, jzVar);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = i30.b;
        atomicReferenceFieldUpdater2.lazySet(p90Var, jzVar);
        loop0: while (true) {
            if (jzVar.f() == jzVar) {
                while (!atomicReferenceFieldUpdater2.compareAndSet(jzVar, jzVar, p90Var)) {
                    if (atomicReferenceFieldUpdater2.get(jzVar) != jzVar) {
                        break;
                    }
                }
                p90Var.e(jzVar);
                break loop0;
            }
            break;
        }
        i30 i30VarG = jzVar.g();
        do {
            atomicReferenceFieldUpdater = b;
            if (atomicReferenceFieldUpdater.compareAndSet(this, jzVar, i30VarG)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == jzVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [T, java.lang.Throwable] */
    public final Object z(Object obj, Object obj2) throws IllegalAccessException, InvocationTargetException {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        if (!(obj instanceof sx)) {
            return nv0.c;
        }
        if (((obj instanceof xn) || (obj instanceof jz)) && !(obj instanceof ec) && !(obj2 instanceof ue)) {
            sx sxVar = (sx) obj;
            Object txVar = obj2 instanceof sx ? new tx((sx) obj2) : obj2;
            do {
                atomicReferenceFieldUpdater = b;
                if (atomicReferenceFieldUpdater.compareAndSet(this, sxVar, txVar)) {
                    v(obj2);
                    i(sxVar, obj2);
                    return obj2;
                }
            } while (atomicReferenceFieldUpdater.get(this) == sxVar);
            return nv0.e;
        }
        sx sxVar2 = (sx) obj;
        p90 p90VarN = n(sxVar2);
        if (p90VarN == null) {
            return nv0.e;
        }
        ec ecVarT = null;
        lz lzVar = sxVar2 instanceof lz ? (lz) sxVar2 : null;
        if (lzVar == null) {
            lzVar = new lz(p90VarN, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (lzVar) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = lz.c;
            if (atomicIntegerFieldUpdater.get(lzVar) != 0) {
                return nv0.c;
            }
            atomicIntegerFieldUpdater.set(lzVar, 1);
            if (lzVar != sxVar2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = b;
                while (!atomicReferenceFieldUpdater2.compareAndSet(this, sxVar2, lzVar)) {
                    if (atomicReferenceFieldUpdater2.get(this) != sxVar2) {
                        return nv0.e;
                    }
                }
            }
            boolean zE = lzVar.e();
            ue ueVar = obj2 instanceof ue ? (ue) obj2 : null;
            if (ueVar != null) {
                lzVar.b(ueVar.a);
            }
            ?? D = !zE ? lzVar.d() : 0;
            ref$ObjectRef.element = D;
            if (D != 0) {
                u(p90VarN, D);
            }
            ec ecVar = sxVar2 instanceof ec ? (ec) sxVar2 : null;
            if (ecVar == null) {
                p90 p90VarC = sxVar2.c();
                if (p90VarC != null) {
                    ecVarT = t(p90VarC);
                }
            } else {
                ecVarT = ecVar;
            }
            if (ecVarT != null) {
                while (rp0.F(ecVarT.f, new kz(this, lzVar, ecVarT, obj2), 1) == q90.b) {
                    ecVarT = t(ecVarT);
                    if (ecVarT == null) {
                    }
                }
                return nv0.d;
            }
            return k(lzVar, obj2);
        }
    }

    public void w() {
    }

    public void c(Object obj) {
    }

    public void q(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    public void v(Object obj) {
    }
}
