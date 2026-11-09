package kotlinx.coroutines.flow;

import defpackage.mm;
import defpackage.ox;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1", f = "Builders.kt", l = {113}, m = "collect")
public final class FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1$1(ox oxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
