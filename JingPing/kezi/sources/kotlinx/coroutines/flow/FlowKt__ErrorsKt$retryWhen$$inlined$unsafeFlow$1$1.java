package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.lr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1", f = "Errors.kt", l = {118, 120}, m = "collect")
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1(lr lrVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
