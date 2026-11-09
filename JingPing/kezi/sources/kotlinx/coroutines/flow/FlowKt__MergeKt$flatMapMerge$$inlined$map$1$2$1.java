package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.sr;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2", f = "Merge.kt", l = {223, 223}, m = "emit")
public final class FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__MergeKt$flatMapMerge$$inlined$map$1$2$1(sr srVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
