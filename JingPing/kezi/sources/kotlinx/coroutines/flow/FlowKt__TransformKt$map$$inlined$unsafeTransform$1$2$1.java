package kotlinx.coroutines.flow;

import defpackage.es;
import defpackage.fh;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2", f = "Transform.kt", l = {223, 223}, m = "emit")
public final class FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2$1(es esVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
