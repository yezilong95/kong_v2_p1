package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.hr;
import defpackage.ok0;
import defpackage.oq;
import defpackage.qq;
import defpackage.ru;
import defpackage.si;
import defpackage.su;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1", f = "Emitters.kt", l = {40}, m = "invokeSuspend")
public final class FlowKt__EmittersKt$transform$1 extends SuspendLambda implements ru {
    public int f;
    public /* synthetic */ Object g;
    public final /* synthetic */ oq h;
    public final /* synthetic */ su i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__EmittersKt$transform$1(oq oqVar, su suVar, fh fhVar) {
        super(2, fhVar);
        this.h = oqVar;
        this.i = suVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        FlowKt__EmittersKt$transform$1 flowKt__EmittersKt$transform$1 = new FlowKt__EmittersKt$transform$1(this.h, this.i, fhVar);
        flowKt__EmittersKt$transform$1.g = obj;
        return flowKt__EmittersKt$transform$1;
    }

    @Override // defpackage.ru
    public final Object invoke(qq qqVar, fh fhVar) {
        return ((FlowKt__EmittersKt$transform$1) create(qqVar, fhVar)).invokeSuspend(bq0.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.f;
        if (i == 0) {
            a.b(obj);
            hr hrVar = new hr(this.i, (qq) this.g);
            this.f = 1;
            if (((ok0) this.h).a(hrVar, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            a.b(obj);
        }
        return bq0.a;
    }

    public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
        ((ok0) this.h).a(new hr(this.i, (qq) this.g), this);
        return bq0.a;
    }
}
