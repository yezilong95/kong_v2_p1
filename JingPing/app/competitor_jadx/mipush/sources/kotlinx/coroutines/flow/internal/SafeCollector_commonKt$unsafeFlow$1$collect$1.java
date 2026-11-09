package kotlinx.coroutines.flow.internal;

import defpackage.tq0;
import defpackage.ui;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector_commonKt$unsafeFlow$1$collect$1 extends ContinuationImpl {
    public /* synthetic */ Object H;

    public SafeCollector_commonKt$unsafeFlow$1$collect$1(tq0 tq0Var, ui uiVar) {
        super(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.H = obj;
        throw null;
    }
}
