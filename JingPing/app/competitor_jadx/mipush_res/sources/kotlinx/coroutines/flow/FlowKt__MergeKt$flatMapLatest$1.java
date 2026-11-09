package kotlinx.coroutines.flow;

import defpackage.bx;
import defpackage.cx0;
import defpackage.d10;
import defpackage.dx;
import defpackage.e10;
import defpackage.mm;
import defpackage.ui;
import defpackage.y21;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapLatest$1", f = "Merge.kt", l = {189, 189}, m = "invokeSuspend")
public final class FlowKt__MergeKt$flatMapLatest$1 extends SuspendLambda implements e10 {
    public int I;
    public /* synthetic */ dx J;
    public /* synthetic */ Object K;
    public final /* synthetic */ d10 L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__MergeKt$flatMapLatest$1(d10 d10Var, ui uiVar) {
        super(3, uiVar);
        this.L = d10Var;
    }

    @Override // defpackage.e10
    public final Object invoke(dx dxVar, Object obj, ui uiVar) {
        FlowKt__MergeKt$flatMapLatest$1 flowKt__MergeKt$flatMapLatest$1 = new FlowKt__MergeKt$flatMapLatest$1(this.L, uiVar);
        flowKt__MergeKt$flatMapLatest$1.J = dxVar;
        flowKt__MergeKt$flatMapLatest$1.K = obj;
        return flowKt__MergeKt$flatMapLatest$1.invokeSuspend(y21.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        dx dxVar;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.I;
        y21 y21Var = y21.a;
        if (i == 0) {
            a.b(obj);
            dxVar = this.J;
            Object obj2 = this.K;
            this.J = dxVar;
            this.I = 1;
            obj = this.L.invoke(obj2, this);
            if (obj != coroutineSingletons) {
            }
        }
        if (i != 1) {
            if (i == 2) {
                a.b(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        dxVar = this.J;
        a.b(obj);
        this.J = null;
        this.I = 2;
        Object objA = ((cx0) ((bx) obj)).a(dxVar, this);
        if (objA != coroutineSingletons) {
            objA = y21Var;
        }
        return objA == coroutineSingletons ? coroutineSingletons : y21Var;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        ((cx0) ((bx) this.L.invoke(this.K, this))).a(this.J, this);
        return y21.a;
    }
}
