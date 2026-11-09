package kotlinx.coroutines.flow;

import defpackage.cr;
import defpackage.fh;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__CollectKt$collect$3$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__CollectKt$collect$3$emit$1(cr crVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
