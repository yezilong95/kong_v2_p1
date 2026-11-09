package defpackage;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class bx0 extends ContinuationImpl {
    public cx0 H;
    public dx I;
    public dx0 J;
    public f50 K;
    public Object L;
    public /* synthetic */ Object M;
    public final /* synthetic */ cx0 N;
    public int O;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public bx0(cx0 cx0Var, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.N = cx0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.M = obj;
        this.O |= Integer.MIN_VALUE;
        return this.N.a(null, this);
    }
}
