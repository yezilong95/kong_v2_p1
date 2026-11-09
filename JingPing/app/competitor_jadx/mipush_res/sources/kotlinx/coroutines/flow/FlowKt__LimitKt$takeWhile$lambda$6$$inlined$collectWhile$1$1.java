package kotlinx.coroutines.flow;

import defpackage.dy;
import defpackage.mm;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1", f = "Limit.kt", l = {139, 140}, m = "emit")
public final class FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__LimitKt$takeWhile$lambda$6$$inlined$collectWhile$1$1(dy dyVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
