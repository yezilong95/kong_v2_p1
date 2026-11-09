package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.fs;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$map$$inlined$unsafeTransform$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$map$$inlined$unsafeTransform$1$1(fs fsVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
