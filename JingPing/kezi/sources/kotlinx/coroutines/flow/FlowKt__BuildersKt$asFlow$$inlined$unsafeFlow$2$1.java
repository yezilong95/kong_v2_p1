package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.tq;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2", f = "Builders.kt", l = {114, 114}, m = "collect")
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$2$1(tq tqVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
