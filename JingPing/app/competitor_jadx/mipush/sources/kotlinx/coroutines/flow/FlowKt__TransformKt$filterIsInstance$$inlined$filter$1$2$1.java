package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.my;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2", f = "Transform.kt", l = {219}, m = "emit")
public final class FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__TransformKt$filterIsInstance$$inlined$filter$1$2$1(my myVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
