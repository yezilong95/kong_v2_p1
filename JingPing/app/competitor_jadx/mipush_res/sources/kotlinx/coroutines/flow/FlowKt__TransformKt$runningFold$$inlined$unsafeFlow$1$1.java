package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ui;
import defpackage.xy;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1", f = "Transform.kt", l = {113, 114}, m = "collect")
public final class FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$1(xy xyVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
