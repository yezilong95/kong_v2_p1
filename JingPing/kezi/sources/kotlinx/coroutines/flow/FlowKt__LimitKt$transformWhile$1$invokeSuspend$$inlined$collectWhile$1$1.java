package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.qr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1", f = "Limit.kt", l = {142}, m = "emit")
public final class FlowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__LimitKt$transformWhile$1$invokeSuspend$$inlined$collectWhile$1$1(qr qrVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
