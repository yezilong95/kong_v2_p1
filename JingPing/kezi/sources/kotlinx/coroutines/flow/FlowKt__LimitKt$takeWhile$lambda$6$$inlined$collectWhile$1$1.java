package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.pr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1", f = "Limit.kt", l = {142, 143}, m = "emit")
public final class FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$1(pr prVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
