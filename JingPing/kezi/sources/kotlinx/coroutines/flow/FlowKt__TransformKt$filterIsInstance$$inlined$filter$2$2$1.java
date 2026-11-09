package kotlinx.coroutines.flow;

import defpackage.as;
import defpackage.fh;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$2$2", f = "Transform.kt", l = {223}, m = "emit")
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$2$2$1(as asVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
