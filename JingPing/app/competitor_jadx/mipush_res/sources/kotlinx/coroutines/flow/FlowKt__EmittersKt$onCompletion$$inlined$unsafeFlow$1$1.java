package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.rx;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", l = {113, 120, 127}, m = "collect")
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1(rx rxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
