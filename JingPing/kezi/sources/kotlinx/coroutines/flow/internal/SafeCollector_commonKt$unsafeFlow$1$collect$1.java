package kotlinx.coroutines.flow.internal;

import defpackage.fh;
import defpackage.gg0;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector_commonKt$unsafeFlow$1$collect$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public SafeCollector_commonKt$unsafeFlow$1$collect$1(gg0 gg0Var, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
