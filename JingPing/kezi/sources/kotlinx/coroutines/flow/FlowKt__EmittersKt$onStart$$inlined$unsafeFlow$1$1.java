package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.gr;
import defpackage.si;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1", f = "Emitters.kt", l = {117, 121}, m = "collect")
public final class FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1$1(gr grVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
