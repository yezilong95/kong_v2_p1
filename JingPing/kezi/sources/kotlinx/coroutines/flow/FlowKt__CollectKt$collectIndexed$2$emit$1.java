package kotlinx.coroutines.flow;

import defpackage.dr;
import defpackage.fh;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__CollectKt$collectIndexed$2$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__CollectKt$collectIndexed$2$emit$1(dr drVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
