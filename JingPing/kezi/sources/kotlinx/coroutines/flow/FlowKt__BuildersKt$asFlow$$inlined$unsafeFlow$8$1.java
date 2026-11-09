package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.zq;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", f = "Builders.kt", l = {116}, m = "collect")
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1(zq zqVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
