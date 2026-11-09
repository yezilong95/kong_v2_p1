package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.oq;
import defpackage.ou;
import defpackage.oy;
import defpackage.qq;
import defpackage.ru;
import defpackage.si;
import defpackage.su;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6", f = "Zip.kt", l = {251}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combineTransform$6 extends SuspendLambda implements ru {
    public /* synthetic */ Object f;
    public final /* synthetic */ oq[] g;
    public final /* synthetic */ su h;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements ou {
        public final /* synthetic */ oq[] b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(oq[] oqVarArr) {
            super(0);
            this.b = oqVarArr;
        }

        @Override // defpackage.ou
        public final Object[] invoke() {
            int length = this.b.length;
            oy.R();
            throw null;
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$2, reason: invalid class name */
    @si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$6$2", f = "Zip.kt", l = {251}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends SuspendLambda implements su {
        public int f;
        public final /* synthetic */ su g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(su suVar, fh fhVar) {
            super(3, fhVar);
            this.g = suVar;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.f;
            if (i == 0) {
                a.b(obj);
                this.f = 1;
                if (this.g.invoke(null, null, this) == coroutineSingletons) {
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

        public final Object invokeSuspend$$forInline(Object obj) {
            this.g.invoke(null, null, this);
            return bq0.a;
        }

        @Override // defpackage.su
        public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
            oy.R();
            throw null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransform$6(oq[] oqVarArr, su suVar, fh fhVar) {
        super(2, fhVar);
        this.g = oqVarArr;
        this.h = suVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        FlowKt__ZipKt$combineTransform$6 flowKt__ZipKt$combineTransform$6 = new FlowKt__ZipKt$combineTransform$6(this.g, this.h, fhVar);
        flowKt__ZipKt$combineTransform$6.f = obj;
        return flowKt__ZipKt$combineTransform$6;
    }

    @Override // defpackage.ru
    public final Object invoke(qq qqVar, fh fhVar) {
        return ((FlowKt__ZipKt$combineTransform$6) create(qqVar, fhVar)).invokeSuspend(bq0.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        a.b(obj);
        oy.R();
        throw null;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        oy.R();
        throw null;
    }
}
