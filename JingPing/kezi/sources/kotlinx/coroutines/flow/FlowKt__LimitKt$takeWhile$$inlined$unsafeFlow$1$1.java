package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.or;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1", f = "Limit.kt", l = {125}, m = "collect")
public final class FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$1(or orVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
