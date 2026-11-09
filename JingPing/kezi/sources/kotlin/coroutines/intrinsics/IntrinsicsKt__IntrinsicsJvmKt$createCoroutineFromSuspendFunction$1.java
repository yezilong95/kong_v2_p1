package kotlin.coroutines.intrinsics;

import defpackage.fh;
import defpackage.oy;
import defpackage.pu;
import kotlin.a;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1 extends RestrictedContinuationImpl {
    public int c;
    public final /* synthetic */ pu d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1(fh fhVar, pu puVar) {
        super(fhVar);
        this.d = puVar;
        oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i = this.c;
        if (i == 0) {
            this.c = 1;
            a.b(obj);
            return this.d.invoke(this);
        }
        if (i != 1) {
            throw new IllegalStateException("This coroutine had already completed");
        }
        this.c = 2;
        a.b(obj);
        return obj;
    }
}
