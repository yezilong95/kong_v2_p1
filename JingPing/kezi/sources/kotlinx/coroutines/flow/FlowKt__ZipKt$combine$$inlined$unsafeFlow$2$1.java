package kotlinx.coroutines.flow;

import defpackage.fh;
import defpackage.ks;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$1 extends ContinuationImpl {
    public /* synthetic */ Object e;

    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$2$1(ks ksVar, fh fhVar) {
        super(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.e = obj;
        throw null;
    }
}
