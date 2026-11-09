package defpackage;

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
public class t50 implements f50, gk0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater E = AtomicReferenceFieldUpdater.newUpdater(t50.class, Object.class, "_state$volatile");
    public static final /* synthetic */ AtomicReferenceFieldUpdater F = AtomicReferenceFieldUpdater.newUpdater(t50.class, Object.class, "_parentHandle$volatile");
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    public t50(boolean z) {
        this._state$volatile = z ? j31.v : j31.u;
    }

    public static zd t(oa0 oa0Var) {
        while (oa0Var.g()) {
            oa0 oa0VarC = oa0Var.c();
            if (oa0VarC == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = oa0.b;
                Object obj = atomicReferenceFieldUpdater.get(oa0Var);
                while (true) {
                    oa0Var = (oa0) obj;
                    if (!oa0Var.g()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(oa0Var);
                }
            } else {
                oa0Var = oa0VarC;
            }
        }
        while (true) {
            oa0Var = oa0Var.f();
            if (!oa0Var.g()) {
                if (oa0Var instanceof zd) {
                    return (zd) oa0Var;
                }
                if (oa0Var instanceof th0) {
                    return null;
                }
            }
        }
    }

    public static String y(Object obj) {
        if (!(obj instanceof r50)) {
            return obj instanceof w30 ? ((w30) obj).isActive() ? "Active" : "New" : obj instanceof og ? "Cancelled" : "Completed";
        }
        r50 r50Var = (r50) obj;
        return r50Var.c() ? "Cancelling" : r50.b.get(r50Var) != 0 ? "Completing" : "Active";
    }

    public final boolean a(w30 w30Var, th0 th0Var, o50 o50Var) {
        char c;
        s50 s50Var = new s50(o50Var, this, w30Var);
        do {
            oa0 oa0VarC = th0Var.c();
            if (oa0VarC == null) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = oa0.b;
                Object obj = atomicReferenceFieldUpdater.get(th0Var);
                while (true) {
                    oa0VarC = (oa0) obj;
                    if (!oa0VarC.g()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(oa0VarC);
                }
            }
            oa0.b.set(o50Var, oa0VarC);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = oa0.a;
            atomicReferenceFieldUpdater2.set(o50Var, th0Var);
            s50Var.c = th0Var;
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(oa0VarC, th0Var, s50Var)) {
                    c = s50Var.a(oa0VarC) == null ? (char) 1 : (char) 2;
                } else if (atomicReferenceFieldUpdater2.get(oa0VarC) != th0Var) {
                    c = 0;
                    break;
                }
            }
            if (c == 1) {
                return true;
            }
        } while (c != 2);
        return false;
    }

    public void c(Object obj) {
        b(obj);
    }

    public final boolean d(Object obj) {
        Object obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Throwable thI = null;
        loop0: while (true) {
            Object objN = n();
            if (!(objN instanceof r50)) {
                if (!(objN instanceof w30)) {
                    obj2 = j31.s;
                    break;
                }
                if (thI == null) {
                    thI = i(obj);
                }
                w30 w30Var = (w30) objN;
                if (w30Var.isActive()) {
                    th0 th0VarM = m(w30Var);
                    if (th0VarM == null) {
                        continue;
                    } else {
                        r50 r50Var = new r50(th0VarM, thI);
                        do {
                            atomicReferenceFieldUpdater = E;
                            if (atomicReferenceFieldUpdater.compareAndSet(this, w30Var, r50Var)) {
                                u(th0VarM, thI);
                                obj2 = j31.p;
                                break loop0;
                            }
                        } while (atomicReferenceFieldUpdater.get(this) == w30Var);
                    }
                } else {
                    Object objZ = z(objN, new og(false, thI));
                    if (objZ == j31.p) {
                        throw new IllegalStateException(("Cannot happen in " + objN).toString());
                    }
                    if (objZ != j31.r) {
                        obj2 = objZ;
                        break;
                    }
                }
            } else {
                synchronized (objN) {
                    try {
                        r50 r50Var2 = (r50) objN;
                        r50Var2.getClass();
                        if (r50.d.get(r50Var2) == j31.t) {
                            obj2 = j31.s;
                        } else {
                            boolean zC = ((r50) objN).c();
                            if (thI == null) {
                                thI = i(obj);
                            }
                            ((r50) objN).a(thI);
                            Throwable thB = zC ? null : ((r50) objN).b();
                            if (thB != null) {
                                u(((r50) objN).a, thB);
                            }
                            obj2 = j31.p;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
        if (obj2 == j31.p || obj2 == j31.q) {
            return true;
        }
        if (obj2 == j31.s) {
            return false;
        }
        b(obj2);
        return true;
    }

    public final boolean e(Throwable th) {
        if (r()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        yd ydVar = (yd) F.get(this);
        return (ydVar == null || ydVar == wh0.a) ? z : ydVar.a(th) || z;
    }

    public String f() {
        return "Job was cancelled";
    }

    @Override // defpackage.bj
    public final Object fold(Object obj, d10 d10Var) {
        return qm.p(this, obj, d10Var);
    }

    public boolean g(Throwable th) {
        return (th instanceof CancellationException) || d(th);
    }

    @Override // defpackage.bj
    public final zi get(aj ajVar) {
        t40.n(ajVar, "key");
        if (t40.f(is.H, ajVar)) {
            return this;
        }
        return null;
    }

    @Override // defpackage.zi
    public final aj getKey() {
        return is.H;
    }

    public final void h(w30 w30Var, Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = F;
        yd ydVar = (yd) atomicReferenceFieldUpdater.get(this);
        if (ydVar != null) {
            ydVar.dispose();
            atomicReferenceFieldUpdater.set(this, wh0.a);
        }
        CompletionHandlerException completionHandlerException = null;
        og ogVar = obj instanceof og ? (og) obj : null;
        Throwable th = ogVar != null ? ogVar.a : null;
        if (w30Var instanceof o50) {
            try {
                ((o50) w30Var).b(th);
                return;
            } catch (Throwable th2) {
                p(new CompletionHandlerException("Exception in completion handler " + w30Var + " for " + this, th2));
                return;
            }
        }
        th0 list = w30Var.getList();
        if (list != null) {
            Object objE = list.e();
            t40.l(objE, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (oa0 oa0VarF = (oa0) objE; !oa0VarF.equals(list); oa0VarF = oa0VarF.f()) {
                if (oa0VarF instanceof o50) {
                    o50 o50Var = (o50) oa0VarF;
                    try {
                        o50Var.b(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            l21.j(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + o50Var + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                p(completionHandlerException);
            }
        }
    }

    public final Throwable i(Object obj) {
        Throwable thB;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        t50 t50Var = (t50) ((gk0) obj);
        Object objN = t50Var.n();
        if (objN instanceof r50) {
            thB = ((r50) objN).b();
        } else if (objN instanceof og) {
            thB = ((og) objN).a;
        } else {
            if (objN instanceof w30) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + objN).toString());
            }
            thB = null;
        }
        CancellationException cancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        return cancellationException == null ? new JobCancellationException("Parent job is ".concat(y(objN)), thB, t50Var) : cancellationException;
    }

    @Override // defpackage.f50
    public boolean isActive() {
        Object objN = n();
        return (objN instanceof w30) && ((w30) objN).isActive();
    }

    public final Object j(r50 r50Var, Object obj) {
        Throwable thL;
        og ogVar = obj instanceof og ? (og) obj : null;
        Throwable th = ogVar != null ? ogVar.a : null;
        synchronized (r50Var) {
            r50Var.c();
            ArrayList<Throwable> arrayListD = r50Var.d(th);
            thL = l(r50Var, arrayListD);
            if (thL != null && arrayListD.size() > 1) {
                Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap(arrayListD.size()));
                for (Throwable th2 : arrayListD) {
                    if (th2 != thL && th2 != thL && !(th2 instanceof CancellationException) && setNewSetFromMap.add(th2)) {
                        l21.j(thL, th2);
                    }
                }
            }
        }
        if (thL != null && thL != th) {
            obj = new og(false, thL);
        }
        if (thL != null && (e(thL) || o(thL))) {
            t40.l(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
            og.b.compareAndSet((og) obj, 0, 1);
        }
        v(obj);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = E;
        Object x30Var = obj instanceof w30 ? new x30((w30) obj) : obj;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, r50Var, x30Var) && atomicReferenceFieldUpdater.get(this) == r50Var) {
        }
        h(r50Var, obj);
        return obj;
    }

    public final CancellationException k() {
        CancellationException jobCancellationException;
        Object objN = n();
        if (!(objN instanceof r50)) {
            if (objN instanceof w30) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            if (!(objN instanceof og)) {
                return new JobCancellationException(getClass().getSimpleName().concat(" has completed normally"), null, this);
            }
            Throwable th = ((og) objN).a;
            jobCancellationException = th instanceof CancellationException ? (CancellationException) th : null;
            return jobCancellationException == null ? new JobCancellationException(f(), th, this) : jobCancellationException;
        }
        Throwable thB = ((r50) objN).b();
        if (thB == null) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        String strConcat = getClass().getSimpleName().concat(" is cancelling");
        jobCancellationException = thB instanceof CancellationException ? (CancellationException) thB : null;
        if (jobCancellationException == null) {
            if (strConcat == null) {
                strConcat = f();
            }
            jobCancellationException = new JobCancellationException(strConcat, thB, this);
        }
        return jobCancellationException;
    }

    public final Throwable l(r50 r50Var, ArrayList arrayList) {
        Object next;
        Object obj = null;
        if (arrayList.isEmpty()) {
            if (r50Var.c()) {
                return new JobCancellationException(f(), null, this);
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

    public final th0 m(w30 w30Var) {
        th0 list = w30Var.getList();
        if (list != null) {
            return list;
        }
        if (w30Var instanceof pt) {
            return new th0();
        }
        if (w30Var instanceof o50) {
            x((o50) w30Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + w30Var).toString());
    }

    @Override // defpackage.bj
    public final bj minusKey(aj ajVar) {
        return qm.T(this, ajVar);
    }

    public final Object n() {
        while (true) {
            Object obj = E.get(this);
            if (!(obj instanceof nj0)) {
                return obj;
            }
            ((nj0) obj).a(this);
        }
    }

    public boolean o(Throwable th) {
        return false;
    }

    @Override // defpackage.bj
    public final bj plus(bj bjVar) {
        return qm.a0(bjVar, this);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final defpackage.ap q(boolean r9, boolean r10, defpackage.s40 r11) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.t50.q(boolean, boolean, s40):ap");
    }

    public boolean r() {
        return false;
    }

    public final Object s(Object obj) {
        Object objZ;
        do {
            objZ = z(n(), obj);
            if (objZ == j31.p) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                og ogVar = obj instanceof og ? (og) obj : null;
                throw new IllegalStateException(str, ogVar != null ? ogVar.a : null);
            }
        } while (objZ == j31.r);
        return objZ;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + '{' + y(n()) + '}');
        sb.append('@');
        sb.append(qm.C(this));
        return sb.toString();
    }

    public final void u(th0 th0Var, Throwable th) {
        Object objE = th0Var.e();
        t40.l(objE, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (oa0 oa0VarF = (oa0) objE; !oa0VarF.equals(th0Var); oa0VarF = oa0VarF.f()) {
            if (oa0VarF instanceof g50) {
                o50 o50Var = (o50) oa0VarF;
                try {
                    o50Var.b(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        l21.j(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + o50Var + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            p(completionHandlerException);
        }
        e(th);
    }

    public final void x(o50 o50Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        th0 th0Var = new th0();
        o50Var.getClass();
        oa0.b.set(th0Var, o50Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = oa0.a;
        atomicReferenceFieldUpdater2.set(th0Var, o50Var);
        loop0: while (true) {
            if (o50Var.e() == o50Var) {
                while (!atomicReferenceFieldUpdater2.compareAndSet(o50Var, o50Var, th0Var)) {
                    if (atomicReferenceFieldUpdater2.get(o50Var) != o50Var) {
                        break;
                    }
                }
                th0Var.d(o50Var);
                break loop0;
            }
            break;
        }
        oa0 oa0VarF = o50Var.f();
        do {
            atomicReferenceFieldUpdater = E;
            if (atomicReferenceFieldUpdater.compareAndSet(this, o50Var, oa0VarF)) {
                return;
            }
        } while (atomicReferenceFieldUpdater.get(this) == o50Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [T, java.lang.Throwable] */
    public final Object z(Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        if (!(obj instanceof w30)) {
            return j31.p;
        }
        if (((obj instanceof pt) || (obj instanceof o50)) && !(obj instanceof zd) && !(obj2 instanceof og)) {
            w30 w30Var = (w30) obj;
            Object x30Var = obj2 instanceof w30 ? new x30((w30) obj2) : obj2;
            do {
                atomicReferenceFieldUpdater = E;
                if (atomicReferenceFieldUpdater.compareAndSet(this, w30Var, x30Var)) {
                    v(obj2);
                    h(w30Var, obj2);
                    return obj2;
                }
            } while (atomicReferenceFieldUpdater.get(this) == w30Var);
            return j31.r;
        }
        w30 w30Var2 = (w30) obj;
        th0 th0VarM = m(w30Var2);
        if (th0VarM == null) {
            return j31.r;
        }
        zd zdVarT = null;
        r50 r50Var = w30Var2 instanceof r50 ? (r50) w30Var2 : null;
        if (r50Var == null) {
            r50Var = new r50(th0VarM, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (r50Var) {
            try {
                AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = r50.b;
                if (atomicIntegerFieldUpdater.get(r50Var) != 0) {
                    return j31.p;
                }
                atomicIntegerFieldUpdater.set(r50Var, 1);
                if (r50Var != w30Var2) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = E;
                    while (!atomicReferenceFieldUpdater2.compareAndSet(this, w30Var2, r50Var)) {
                        if (atomicReferenceFieldUpdater2.get(this) != w30Var2) {
                            return j31.r;
                        }
                    }
                }
                boolean zC = r50Var.c();
                og ogVar = obj2 instanceof og ? (og) obj2 : null;
                if (ogVar != null) {
                    r50Var.a(ogVar.a);
                }
                ?? B = !zC ? r50Var.b() : 0;
                ref$ObjectRef.element = B;
                if (B != 0) {
                    u(th0VarM, B);
                }
                zd zdVar = w30Var2 instanceof zd ? (zd) w30Var2 : null;
                if (zdVar == null) {
                    th0 list = w30Var2.getList();
                    if (list != null) {
                        zdVarT = t(list);
                    }
                } else {
                    zdVarT = zdVar;
                }
                if (zdVarT != null) {
                    while (j31.y(zdVarT.e, new q50(this, r50Var, zdVarT, obj2), 1) == wh0.a) {
                        zdVarT = t(zdVarT);
                        if (zdVarT == null) {
                        }
                    }
                    return j31.q;
                }
                return j(r50Var, obj2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void w() {
    }

    public void b(Object obj) {
    }

    public void p(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    public void v(Object obj) {
    }
}
