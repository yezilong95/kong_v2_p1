package kotlinx.coroutines.flow;

import defpackage.bx;
import defpackage.cx0;
import defpackage.d10;
import defpackage.dx;
import defpackage.e10;
import defpackage.mm;
import defpackage.ui;
import defpackage.ux;
import defpackage.y21;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1", f = "Emitters.kt", l = {36}, m = "invokeSuspend")
public final class FlowKt__EmittersKt$transform$1 extends SuspendLambda implements d10 {
    public int I;
    public /* synthetic */ Object J;
    public final /* synthetic */ bx K;
    public final /* synthetic */ e10 L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__EmittersKt$transform$1(bx bxVar, e10 e10Var, ui uiVar) {
        super(2, uiVar);
        this.K = bxVar;
        this.L = e10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final ui create(Object obj, ui uiVar) {
        FlowKt__EmittersKt$transform$1 flowKt__EmittersKt$transform$1 = new FlowKt__EmittersKt$transform$1(this.K, this.L, uiVar);
        flowKt__EmittersKt$transform$1.J = obj;
        return flowKt__EmittersKt$transform$1;
    }

    @Override // defpackage.d10
    public final Object invoke(dx dxVar, ui uiVar) {
        return ((FlowKt__EmittersKt$transform$1) create(dxVar, uiVar)).invokeSuspend(y21.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.I;
        if (i == 0) {
            a.b(obj);
            ux uxVar = new ux(this.L, (dx) this.J);
            this.I = 1;
            if (((cx0) this.K).a(uxVar, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a.b(obj);
        }
        return y21.a;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        ((cx0) this.K).a(new ux(this.L, (dx) this.J), this);
        return y21.a;
    }
}
