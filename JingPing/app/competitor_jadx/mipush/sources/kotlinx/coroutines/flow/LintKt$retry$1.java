package kotlinx.coroutines.flow;

import defpackage.d10;
import defpackage.mm;
import defpackage.ui;
import defpackage.y21;
import kotlin.a;
import kotlin.coroutines.jvm.internal.SuspendLambda;

/* JADX INFO: loaded from: classes.dex */
@mm(c = "kotlinx.coroutines.flow.LintKt$retry$1", f = "Lint.kt", l = {}, m = "invokeSuspend")
public final class LintKt$retry$1 extends SuspendLambda implements d10 {
    public LintKt$retry$1(ui uiVar) {
        super(2, uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final ui create(Object obj, ui uiVar) {
        return new LintKt$retry$1(uiVar);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        a.b(obj);
        return Boolean.TRUE;
    }

    @Override // defpackage.d10
    public final Object invoke(Throwable th, ui uiVar) {
        return ((LintKt$retry$1) create(th, uiVar)).invokeSuspend(y21.a);
    }
}
