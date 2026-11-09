package kotlinx.coroutines.flow;

import defpackage.cy;
import defpackage.mm;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1", f = "Limit.kt", l = {123}, m = "collect")
public final class FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__LimitKt$takeWhile$$inlined$unsafeFlow$1$1(cy cyVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
