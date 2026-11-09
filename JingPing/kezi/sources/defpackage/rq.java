package defpackage;

import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.flow.internal.ChildCancelledException;

/* JADX INFO: loaded from: classes.dex */
public final class rq extends g implements yh {
    public final SuspendLambda e;

    public rq(nh nhVar, SuspendLambda suspendLambda) {
        super(nhVar, true);
        this.e = suspendLambda;
    }

    @Override // defpackage.nz
    public final void c(Object obj) {
        oy.K(oy.D(this.e), obj instanceof ue ? Result.m3constructorimpl(a.a(((ue) obj).a)) : Result.m3constructorimpl(obj));
    }

    @Override // defpackage.nz
    public final void d(Object obj) {
        this.e.resumeWith(obj instanceof ue ? Result.m3constructorimpl(a.a(((ue) obj).a)) : Result.m3constructorimpl(obj));
    }

    @Override // defpackage.yh
    public final yh getCallerFrame() {
        SuspendLambda suspendLambda = this.e;
        if (suspendLambda != null) {
            return suspendLambda;
        }
        return null;
    }

    @Override // defpackage.nz
    public final boolean h(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return e(th);
    }

    @Override // defpackage.nz
    public final boolean r() {
        return true;
    }
}
