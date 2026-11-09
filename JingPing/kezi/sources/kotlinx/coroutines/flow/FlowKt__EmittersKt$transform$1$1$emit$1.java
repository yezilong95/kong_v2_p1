package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.hr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1", f = "Emitters.kt", l = {42}, m = "emit")
public final class FlowKt__EmittersKt$transform$1$1$emit$1 extends ContinuationImpl {
    public /* synthetic */ Object e;
    public final /* synthetic */ hr f;
    public int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__EmittersKt$transform$1$1$emit$1(hr hrVar, fh fhVar) {
        super(fhVar);
        this.f = hrVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        this.g |= Integer.MIN_VALUE;
        return this.f.emit(null, this);
    }
}
