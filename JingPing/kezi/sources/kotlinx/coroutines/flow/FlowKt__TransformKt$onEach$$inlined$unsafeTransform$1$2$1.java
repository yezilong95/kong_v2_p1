package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.is;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {223, 224}, m = "emit")
public final class FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1$2$1(is isVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
