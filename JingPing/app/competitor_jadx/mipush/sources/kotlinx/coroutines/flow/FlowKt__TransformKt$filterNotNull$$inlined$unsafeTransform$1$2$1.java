package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ry;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {219}, m = "emit")
public final class FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1$2$1(ry ryVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
