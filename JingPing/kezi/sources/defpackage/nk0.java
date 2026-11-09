package defpackage;

import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class nk0 extends ContinuationImpl {
    public ok0 e;
    public qq f;
    public pk0 g;
    public bz h;
    public Object i;
    public /* synthetic */ Object j;
    public final /* synthetic */ ok0 k;
    public int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public nk0(ok0 ok0Var, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.k = ok0Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.j = obj;
        this.l |= Integer.MIN_VALUE;
        return this.k.a(null, this);
    }
}
