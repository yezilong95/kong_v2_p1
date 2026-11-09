package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.oy;
import defpackage.qq;
import defpackage.ru;
import defpackage.si;
import defpackage.su;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$6$2", f = "Zip.kt", l = {292, 292}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combine$6$2 extends SuspendLambda implements su {
    public int f;
    public final /* synthetic */ ru g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$6$2(ru ruVar, fh fhVar) {
        super(3, fhVar);
        this.g = ruVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.f;
        if (i == 0) {
            a.b(obj);
            this.f = 1;
            if (this.g.invoke(null, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                a.b(obj);
                return bq0.a;
            }
            a.b(obj);
        }
        this.f = 2;
        throw null;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        this.g.invoke(null, this);
        throw null;
    }

    @Override // defpackage.su
    public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
        oy.R();
        throw null;
    }
}
