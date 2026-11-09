package defpackage;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class yk extends zk implements yh, fh {
    public static final AtomicReferenceFieldUpdater i = AtomicReferenceFieldUpdater.newUpdater(yk.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;
    public final qh e;
    public final ContinuationImpl f;
    public Object g;
    public final Object h;

    public yk(qh qhVar, ContinuationImpl continuationImpl) {
        super(-1);
        this.e = qhVar;
        this.f = continuationImpl;
        this.g = oy.b;
        this.h = wn0.b(continuationImpl.getContext());
    }

    @Override // defpackage.zk
    public final void b(Object obj, CancellationException cancellationException) {
        if (obj instanceof ve) {
            ((ve) obj).getClass();
            throw null;
        }
    }

    @Override // defpackage.zk
    public final Object g() {
        Object obj = this.g;
        this.g = oy.b;
        return obj;
    }

    @Override // defpackage.yh
    public final yh getCallerFrame() {
        ContinuationImpl continuationImpl = this.f;
        if (continuationImpl != null) {
            return continuationImpl;
        }
        return null;
    }

    @Override // defpackage.fh
    public final nh getContext() {
        return this.f.getContext();
    }

    @Override // defpackage.fh
    public final void resumeWith(Object obj) {
        ContinuationImpl continuationImpl = this.f;
        nh context = continuationImpl.getContext();
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        Object ueVar = thM6exceptionOrNullimpl == null ? obj : new ue(false, thM6exceptionOrNullimpl);
        qh qhVar = this.e;
        if (qhVar.D()) {
            this.g = ueVar;
            this.d = 0;
            qhVar.t(context, this);
            return;
        }
        oo ooVarA = xn0.a();
        if (ooVarA.d >= 4294967296L) {
            this.g = ueVar;
            this.d = 0;
            ooVarA.F(this);
            return;
        }
        ooVarA.H(true);
        try {
            nh context2 = continuationImpl.getContext();
            Object objC = wn0.c(context2, this.h);
            try {
                continuationImpl.resumeWith(obj);
                while (ooVarA.I()) {
                }
            } finally {
                wn0.a(context2, objC);
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.e + ", " + xi.R(this.f) + ']';
    }

    @Override // defpackage.zk
    public final fh c() {
        return this;
    }
}
