package kotlin.coroutines.intrinsics;

import defpackage.fh;
import defpackage.nh;
import defpackage.oy;
import defpackage.pu;
import kotlin.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$2 extends ContinuationImpl {
    public int e;
    public final /* synthetic */ pu f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$2(fh fhVar, nh nhVar, pu puVar) {
        super(fhVar, nhVar);
        this.f = puVar;
        oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i = this.e;
        if (i == 0) {
            this.e = 1;
            a.b(obj);
            return this.f.invoke(this);
        }
        if (i != 1) {
            throw new IllegalStateException("This coroutine had already completed");
        }
        this.e = 2;
        a.b(obj);
        return obj;
    }
}
