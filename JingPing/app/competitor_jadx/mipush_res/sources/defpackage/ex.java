package defpackage;

import kotlin.Result;
import kotlin.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlinx.coroutines.flow.internal.ChildCancelledException;

/* JADX INFO: loaded from: classes.dex */
public final class ex extends q0 implements mj {
    public final SuspendLambda H;

    public ex(bj bjVar, SuspendLambda suspendLambda) {
        super(bjVar, true);
        this.H = suspendLambda;
    }

    @Override // defpackage.t50
    public final void b(Object obj) {
        t40.b1(t40.u0(this.H), obj instanceof og ? Result.m3constructorimpl(a.a(((og) obj).a)) : Result.m3constructorimpl(obj));
    }

    @Override // defpackage.t50
    public final void c(Object obj) {
        this.H.resumeWith(obj instanceof og ? Result.m3constructorimpl(a.a(((og) obj).a)) : Result.m3constructorimpl(obj));
    }

    @Override // defpackage.t50
    public final boolean g(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return d(th);
    }

    @Override // defpackage.mj
    public final mj getCallerFrame() {
        SuspendLambda suspendLambda = this.H;
        if (suspendLambda != null) {
            return suspendLambda;
        }
        return null;
    }

    @Override // defpackage.t50
    public final boolean r() {
        return true;
    }
}
