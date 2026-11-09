package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.rr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2", f = "Merge.kt", l = {223, 223}, m = "emit")
public final class FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__MergeKt$flatMapConcat$$inlined$map$1$2$1(rr rrVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
