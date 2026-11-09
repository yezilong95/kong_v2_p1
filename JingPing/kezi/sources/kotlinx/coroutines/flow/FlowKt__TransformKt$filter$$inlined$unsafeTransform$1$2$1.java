package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.si;
import defpackage.wr;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {223, 223}, m = "emit")
public final class FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$filter$$inlined$unsafeTransform$1$2$1(wr wrVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
