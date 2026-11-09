package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.oq;
import defpackage.oy;
import defpackage.qq;
import defpackage.ru;
import defpackage.si;
import defpackage.su;
import defpackage.uu;
import defpackage.xb;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3", f = "Zip.kt", l = {273}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3 extends SuspendLambda implements ru {
    public int f;
    public /* synthetic */ Object g;
    public final /* synthetic */ oq[] h;
    public final /* synthetic */ uu i;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$1, reason: invalid class name */
    @si(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3$1", f = "Zip.kt", l = {333}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends SuspendLambda implements su {
        public int f;
        public /* synthetic */ qq g;
        public /* synthetic */ Object[] h;
        public final /* synthetic */ uu i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(fh fhVar, uu uuVar) {
            super(3, fhVar);
            this.i = uuVar;
        }

        @Override // defpackage.su
        public final Object invoke(qq qqVar, Object[] objArr, fh fhVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(fhVar, this.i);
            anonymousClass1.g = qqVar;
            anonymousClass1.h = objArr;
            return anonymousClass1.invokeSuspend(bq0.a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.f;
            if (i == 0) {
                a.b(obj);
                qq qqVar = this.g;
                Object[] objArr = this.h;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                this.f = 1;
                if (this.i.invoke(qqVar, obj2, obj3, obj4, this) == coroutineSingletons) {
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
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(oq[] oqVarArr, fh fhVar, uu uuVar) {
        super(2, fhVar);
        this.h = oqVarArr;
        this.i = uuVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3 = new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3(this.h, fhVar, this.i);
        flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3.g = obj;
        return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3;
    }

    @Override // defpackage.ru
    public final Object invoke(qq qqVar, fh fhVar) {
        return ((FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$3) create(qqVar, fhVar)).invokeSuspend(bq0.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.f;
        if (i == 0) {
            a.b(obj);
            qq qqVar = (qq) this.g;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null, this.i);
            this.f = 1;
            if (oy.m(qqVar, this.h, xb.d, anonymousClass1, this) == coroutineSingletons) {
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
}
