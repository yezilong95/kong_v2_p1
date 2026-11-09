package kotlin.coroutines.intrinsics;

import defpackage.b10;
import defpackage.t40;
import defpackage.ui;
import kotlin.a;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1 extends RestrictedContinuationImpl {
    public int F;
    public final /* synthetic */ b10 G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1(ui uiVar, b10 b10Var) {
        super(uiVar);
        this.G = b10Var;
        t40.l(uiVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.F;
        if (i == 0) {
            this.F = 1;
            a.b(obj);
            return this.G.invoke(this);
        }
        if (i != 1) {
            throw new IllegalStateException("This coroutine had already completed");
        }
        this.F = 2;
        a.b(obj);
        return obj;
    }
}
