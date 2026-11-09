package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.hs;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1$1(hs hsVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
