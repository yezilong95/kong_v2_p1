package defpackage;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
public final class gg extends SuspendLambda implements d10 {
    public int I;
    public final /* synthetic */ bx[] J;
    public final /* synthetic */ int K;
    public final /* synthetic */ AtomicInteger L;
    public final /* synthetic */ nb M;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public gg(bx[] bxVarArr, int i, AtomicInteger atomicInteger, nb nbVar, ui uiVar) {
        super(2, uiVar);
        this.J = bxVarArr;
        this.K = i;
        this.L = atomicInteger;
        this.M = nbVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final ui create(Object obj, ui uiVar) {
        return new gg(this.J, this.K, this.L, this.M, uiVar);
    }

    @Override // defpackage.d10
    public final Object invoke(Object obj, Object obj2) {
        return ((gg) create((lj) obj, (ui) obj2)).invokeSuspend(y21.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.I;
        AtomicInteger atomicInteger = this.L;
        nb nbVar = this.M;
        try {
            if (i == 0) {
                a.b(obj);
                bx[] bxVarArr = this.J;
                int i2 = this.K;
                bx bxVar = bxVarArr[i2];
                fg fgVar = new fg(nbVar, i2);
                this.I = 1;
                if (((cx0) bxVar).a(fgVar, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                a.b(obj);
            }
            if (atomicInteger.decrementAndGet() == 0) {
                s90.n(nbVar);
            }
            return y21.a;
        } finally {
            if (atomicInteger.decrementAndGet() == 0) {
                s90.n(nbVar);
            }
        }
    }
}
