package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ui;
import defpackage.wx;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$1$1", f = "Emitters.kt", l = {49}, m = "emit")
public final class FlowKt__EmittersKt$unsafeTransform$1$1$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__EmittersKt$unsafeTransform$1$1$emit$1(wx wxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
