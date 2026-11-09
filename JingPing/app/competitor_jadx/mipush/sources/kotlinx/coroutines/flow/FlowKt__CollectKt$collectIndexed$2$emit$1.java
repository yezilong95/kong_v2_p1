package kotlinx.coroutines.flow;

import defpackage.qx;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__CollectKt$collectIndexed$2$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public FlowKt__CollectKt$collectIndexed$2$emit$1(qx qxVar, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
