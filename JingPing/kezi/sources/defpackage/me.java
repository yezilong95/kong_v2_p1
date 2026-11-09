package defpackage;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.a;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
public final class me extends SuspendLambda implements ru {
    public int f;
    public final /* synthetic */ oq[] g;
    public final /* synthetic */ int h;
    public final /* synthetic */ AtomicInteger i;
    public final /* synthetic */ s9 j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public me(oq[] oqVarArr, int i, AtomicInteger atomicInteger, s9 s9Var, fh fhVar) {
        super(2, fhVar);
        this.g = oqVarArr;
        this.h = i;
        this.i = atomicInteger;
        this.j = s9Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        return new me(this.g, this.h, this.i, this.j, fhVar);
    }

    @Override // defpackage.ru
    public final Object invoke(Object obj, Object obj2) {
        return ((me) create((xh) obj, (fh) obj2)).invokeSuspend(bq0.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.f;
        AtomicInteger atomicInteger = this.i;
        s9 s9Var = this.j;
        try {
            if (i == 0) {
                a.b(obj);
                oq[] oqVarArr = this.g;
                int i2 = this.h;
                oq oqVar = oqVarArr[i2];
                le leVar = new le(s9Var, i2);
                this.f = 1;
                if (((ok0) oqVar).a(leVar, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                a.b(obj);
            }
            if (atomicInteger.decrementAndGet() == 0) {
                za0.i(s9Var);
            }
            return bq0.a;
        } finally {
            if (atomicInteger.decrementAndGet() == 0) {
                za0.i(s9Var);
            }
        }
    }
}
