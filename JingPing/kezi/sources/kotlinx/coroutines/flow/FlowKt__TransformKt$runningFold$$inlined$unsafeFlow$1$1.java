package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.js;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1", f = "Transform.kt", l = {115, 116}, m = "collect")
public final class FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__TransformKt$runningFold$$inlined$unsafeFlow$1$1(js jsVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
