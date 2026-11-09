package kotlinx.coroutines.flow;

import defpackage.cs;
import defpackage.fh;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$filterNot$$inlined$unsafeTransform$1$1(cs csVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
