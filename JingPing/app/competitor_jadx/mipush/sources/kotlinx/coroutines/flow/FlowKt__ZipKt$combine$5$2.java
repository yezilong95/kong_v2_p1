package kotlinx.coroutines.flow;

import defpackage.d10;
import defpackage.dx;
import defpackage.e10;
import defpackage.mm;
import defpackage.t40;
import defpackage.ui;
import defpackage.y21;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$5$2", f = "Zip.kt", l = {234, 234}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$5$2 extends SuspendLambda implements e10 {
    public int I;
    public final /* synthetic */ d10 J;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$5$2(d10 d10Var, ui uiVar) {
        super(3, uiVar);
        this.J = d10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.I;
        if (i == 0) {
            a.b(obj);
            this.I = 1;
            if (this.J.invoke(null, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                a.b(obj);
                return y21.a;
            }
            a.b(obj);
        }
        this.I = 2;
        throw null;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        this.J.invoke(null, this);
        throw null;
    }

    @Override // defpackage.e10
    public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
        t40.n1();
        throw null;
    }
}
