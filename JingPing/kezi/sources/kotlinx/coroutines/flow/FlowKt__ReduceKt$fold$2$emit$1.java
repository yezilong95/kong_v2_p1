package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.vr;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$fold$2", f = "Reduce.kt", l = {45}, m = "emit")
public final class FlowKt__ReduceKt$fold$2$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__ReduceKt$fold$2$emit$1(vr vrVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
