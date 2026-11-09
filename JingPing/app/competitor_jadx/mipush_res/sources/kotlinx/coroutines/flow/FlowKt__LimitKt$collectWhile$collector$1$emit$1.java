package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ui;
import defpackage.zx;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$collectWhile$collector$1", f = "Limit.kt", l = {127}, m = "emit")
public final class FlowKt__LimitKt$collectWhile$collector$1$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__LimitKt$collectWhile$collector$1$emit$1(zx zxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
