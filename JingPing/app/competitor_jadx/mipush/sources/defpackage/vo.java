package defpackage;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class vo extends wo implements mj, ui {
    public static final /* synthetic */ AtomicReferenceFieldUpdater L = AtomicReferenceFieldUpdater.newUpdater(vo.class, Object.class, "_reusableCancellableContinuation$volatile");
    public final ej H;
    public final ContinuationImpl I;
    public Object J;
    public final Object K;
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;

    public vo(ej ejVar, ContinuationImpl continuationImpl) {
        super(-1);
        this.H = ejVar;
        this.I = continuationImpl;
        this.J = t40.c;
        this.K = k01.b(continuationImpl.getContext());
    }

    @Override // defpackage.wo
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof pg) {
            ((pg) obj).getClass();
            throw null;
        }
    }

    @Override // defpackage.wo
    public final Object g() {
        Object obj = this.J;
        this.J = t40.c;
        return obj;
    }

    @Override // defpackage.mj
    public final mj getCallerFrame() {
        ContinuationImpl continuationImpl = this.I;
        if (continuationImpl != null) {
            return continuationImpl;
        }
        return null;
    }

    @Override // defpackage.ui
    public final bj getContext() {
        return this.I.getContext();
    }

    @Override // defpackage.ui
    public final void resumeWith(Object obj) {
        ContinuationImpl continuationImpl = this.I;
        bj context = continuationImpl.getContext();
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        Object ogVar = thM6exceptionOrNullimpl == null ? obj : new og(false, thM6exceptionOrNullimpl);
        ej ejVar = this.H;
        if (ejVar.w()) {
            this.J = ogVar;
            this.G = 0;
            ejVar.j(context, this);
            return;
        }
        nu nuVarA = l01.a();
        if (nuVarA.G >= 4294967296L) {
            this.J = ogVar;
            this.G = 0;
            nuVarA.y(this);
            return;
        }
        nuVarA.A(true);
        try {
            bj context2 = continuationImpl.getContext();
            Object objC = k01.c(context2, this.K);
            try {
                continuationImpl.resumeWith(obj);
                while (nuVarA.B()) {
                }
            } finally {
                k01.a(context2, objC);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.H + ", " + qm.H0(this.I) + ']';
    }

    @Override // defpackage.wo
    public final ui c() {
        return this;
    }
}
