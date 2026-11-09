package kotlinx.coroutines.flow;

import defpackage.bx;
import defpackage.d10;
import defpackage.dx;
import defpackage.e10;
import defpackage.i10;
import defpackage.j31;
import defpackage.mm;
import defpackage.rd;
import defpackage.ui;
import defpackage.y21;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5", f = "Zip.kt", l = {269}, m = "invokeSuspend")
public final class FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5 extends SuspendLambda implements d10 {
    public int I;
    public /* synthetic */ Object J;
    public final /* synthetic */ bx[] K;
    public final /* synthetic */ i10 L;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$1, reason: invalid class name */
    @mm(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5$1", f = "Zip.kt", l = {329}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends SuspendLambda implements e10 {
        public int I;
        public /* synthetic */ dx J;
        public /* synthetic */ Object[] K;
        public final /* synthetic */ i10 L;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ui uiVar, i10 i10Var) {
            super(3, uiVar);
            this.L = i10Var;
        }

        @Override // defpackage.e10
        public final Object invoke(dx dxVar, Object[] objArr, ui uiVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(uiVar, this.L);
            anonymousClass1.J = dxVar;
            anonymousClass1.K = objArr;
            return anonymousClass1.invokeSuspend(y21.a);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.I;
            if (i == 0) {
                a.b(obj);
                dx dxVar = this.J;
                Object[] objArr = this.K;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                Object obj5 = objArr[3];
                Object obj6 = objArr[4];
                this.I = 1;
                if (this.L.invoke(dxVar, obj2, obj3, obj4, obj5, obj6, this) == coroutineSingletons) {
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
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5(bx[] bxVarArr, ui uiVar, i10 i10Var) {
        super(2, uiVar);
        this.K = bxVarArr;
        this.L = i10Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final ui create(Object obj, ui uiVar) {
        FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5 flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5 = new FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5(this.K, uiVar, this.L);
        flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5.J = obj;
        return flowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5;
    }

    @Override // defpackage.d10
    public final Object invoke(dx dxVar, ui uiVar) {
        return ((FlowKt__ZipKt$combineTransform$$inlined$combineTransformUnsafe$FlowKt__ZipKt$5) create(dxVar, uiVar)).invokeSuspend(y21.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.I;
        if (i == 0) {
            a.b(obj);
            dx dxVar = (dx) this.J;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(null, this.L);
            this.I = 1;
            if (j31.l(dxVar, this.K, rd.G, anonymousClass1, this) == coroutineSingletons) {
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
}
