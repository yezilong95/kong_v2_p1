package kotlinx.coroutines.flow;

import defpackage.ui;
import defpackage.vx;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1$1(vx vxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
