package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ui;
import defpackage.ux;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1", f = "Emitters.kt", l = {38}, m = "emit")
public final class FlowKt__EmittersKt$transform$1$1$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object H;
    public final /* synthetic */ ux I;
    public int J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__EmittersKt$transform$1$1$emit$1(ux uxVar, ui uiVar) {
        super(uiVar);
        this.I = uxVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        this.J |= Integer.MIN_VALUE;
        return this.I.emit(null, this);
    }
}
