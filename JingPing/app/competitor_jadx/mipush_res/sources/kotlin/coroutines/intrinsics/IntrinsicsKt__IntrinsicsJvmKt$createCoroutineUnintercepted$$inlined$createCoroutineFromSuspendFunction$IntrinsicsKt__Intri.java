package kotlin.coroutines.intrinsics;

import defpackage.b10;
import defpackage.bj;
import defpackage.l21;
import defpackage.t40;
import defpackage.ui;
import kotlin.a;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2 extends ContinuationImpl {
    public int H;
    public final /* synthetic */ b10 I;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2(ui uiVar, bj bjVar, b10 b10Var) {
        super(uiVar, bjVar);
        this.I = b10Var;
        t40.l(uiVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.H;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.H = 2;
            a.b(obj);
            return obj;
        }
        this.H = 1;
        a.b(obj);
        b10 b10Var = this.I;
        t40.l(b10Var, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
        l21.m(1, b10Var);
        return b10Var.invoke(this);
    }
}
