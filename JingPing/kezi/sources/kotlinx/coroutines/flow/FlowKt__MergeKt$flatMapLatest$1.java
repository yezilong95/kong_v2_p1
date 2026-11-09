package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
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
@si(c = "kotlinx.coroutines.flow.FlowKt__MergeKt$flatMapLatest$1", f = "Merge.kt", l = {193, 193}, m = "invokeSuspend")
public final class FlowKt__MergeKt$flatMapLatest$1 extends SuspendLambda implements su {
    public int f;
    public /* synthetic */ qq g;
    public /* synthetic */ Object h;
    public final /* synthetic */ ru i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__MergeKt$flatMapLatest$1(ru ruVar, fh fhVar) {
        super(3, fhVar);
        this.i = ruVar;
    }

    @Override // defpackage.su
    public final Object invoke(qq qqVar, Object obj, fh fhVar) {
        FlowKt__MergeKt$flatMapLatest$1 flowKt__MergeKt$flatMapLatest$1 = new FlowKt__MergeKt$flatMapLatest$1(this.i, fhVar);
        flowKt__MergeKt$flatMapLatest$1.g = qqVar;
        flowKt__MergeKt$flatMapLatest$1.h = obj;
        return flowKt__MergeKt$flatMapLatest$1.invokeSuspend(bq0.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        qq qqVar;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.f;
        bq0 bq0Var = bq0.a;
        if (i == 0) {
            a.b(obj);
            qqVar = this.g;
            Object obj2 = this.h;
            this.g = qqVar;
            this.f = 1;
            obj = this.i.invoke(obj2, this);
            if (obj != coroutineSingletons) {
            }
        }
        if (i != 1) {
            if (i == 2) {
                a.b(obj);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        qqVar = this.g;
        a.b(obj);
        this.g = null;
        this.f = 2;
        Object objA = ((ok0) ((oq) obj)).a(qqVar, this);
        if (objA != coroutineSingletons) {
            objA = bq0Var;
        }
        return objA == coroutineSingletons ? coroutineSingletons : bq0Var;
    }

    public final Object invokeSuspend$$forInline(Object obj) throws Throwable {
        ((ok0) ((oq) this.i.invoke(this.h, this))).a(this.g, this);
        return bq0.a;
    }
}
