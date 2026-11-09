package kotlinx.coroutines.flow;

import defpackage.bx;
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
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1", f = "Zip.kt", l = {269}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combineTransformUnsafe$1 extends SuspendLambda implements d10 {
    public /* synthetic */ Object I;
    public final /* synthetic */ bx[] J;
    public final /* synthetic */ e10 K;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1, reason: invalid class name */
    @mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransformUnsafe$1$1", f = "Zip.kt", l = {269}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends SuspendLambda implements e10 {
        public int I;
        public final /* synthetic */ e10 J;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(e10 e10Var, ui uiVar) {
            super(3, uiVar);
            this.J = e10Var;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.I;
            if (i == 0) {
                a.b(obj);
                this.I = 1;
                if (this.J.invoke(null, null, this) == coroutineSingletons) {
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
            this.J.invoke(null, null, this);
            return y21.a;
        }

        @Override // defpackage.e10
        public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
            t40.n1();
            throw null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransformUnsafe$1(bx[] bxVarArr, e10 e10Var, ui uiVar) {
        super(2, uiVar);
        this.J = bxVarArr;
        this.K = e10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final ui create(Object obj, ui uiVar) {
        FlowKt__ZipKt$combineTransformUnsafe$1 flowKt__ZipKt$combineTransformUnsafe$1 = new FlowKt__ZipKt$combineTransformUnsafe$1(this.J, this.K, uiVar);
        flowKt__ZipKt$combineTransformUnsafe$1.I = obj;
        return flowKt__ZipKt$combineTransformUnsafe$1;
    }

    @Override // defpackage.d10
    public final Object invoke(dx dxVar, ui uiVar) {
        return ((FlowKt__ZipKt$combineTransformUnsafe$1) create(dxVar, uiVar)).invokeSuspend(y21.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        a.b(obj);
        t40.n1();
        throw null;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        t40.n1();
        throw null;
    }
}
