package defpackage;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class lb extends ContinuationImpl {
    public /* synthetic */ Object H;
    public final /* synthetic */ nb I;
    public int J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public lb(nb nbVar, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.I = nbVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.H = obj;
        this.J |= Integer.MIN_VALUE;
        Object objT = nb.t(this.I, this);
        return objT == CoroutineSingletons.COROUTINE_SUSPENDED ? objT : new od(objT);
    }
}
