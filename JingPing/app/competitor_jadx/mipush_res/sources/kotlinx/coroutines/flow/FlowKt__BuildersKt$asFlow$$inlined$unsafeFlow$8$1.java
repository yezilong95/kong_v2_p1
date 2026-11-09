package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.mx;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", f = "Builders.kt", l = {114}, m = "collect")
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1(mx mxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
