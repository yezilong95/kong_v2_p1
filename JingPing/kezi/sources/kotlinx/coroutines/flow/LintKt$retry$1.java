package kotlinx.coroutines.flow;

import defpackage.bq0;
import defpackage.fh;
import defpackage.ru;
import defpackage.si;
import kotlin.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@si(c = "kotlinx.coroutines.flow.LintKt$retry$1", f = "Lint.kt", l = {}, m = "invokeSuspend")
public final class LintKt$retry$1 extends SuspendLambda implements ru {
    public LintKt$retry$1(fh fhVar) {
        super(2, fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final fh create(Object obj, fh fhVar) {
        return new LintKt$retry$1(fhVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        a.b(obj);
        return Boolean.TRUE;
    }

    @Override // defpackage.ru
    public final Object invoke(Throwable th, fh fhVar) {
        return ((LintKt$retry$1) create(th, fhVar)).invokeSuspend(bq0.a);
    }
}
