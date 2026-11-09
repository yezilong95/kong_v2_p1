package defpackage;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class r9 extends ContinuationImpl {
    public /* synthetic */ Object e;
    public final /* synthetic */ s9 f;
    public int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r9(s9 s9Var, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.f = s9Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.e = obj;
        this.g |= Integer.MIN_VALUE;
        Object objU = this.f.u(null, 0, 0L, this);
        return objU == CoroutineSingletons.COROUTINE_SUSPENDED ? objU : new ub(objU);
    }
}
