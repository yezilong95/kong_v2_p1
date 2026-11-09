package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.ur;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2", f = "Reduce.kt", l = {142}, m = "emit")
public final class FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__ReduceKt$firstOrNull$$inlined$collectWhile$2$1(ur urVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
