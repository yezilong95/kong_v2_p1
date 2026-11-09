package kotlin.coroutines.jvm.internal;

import defpackage.ao0;
import defpackage.k10;
import defpackage.t40;
import defpackage.ui;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedSuspendLambda extends RestrictedContinuationImpl implements k10 {
    public final int F;

    public RestrictedSuspendLambda(int i, ui uiVar) {
        super(uiVar);
        this.F = i;
    }

    @Override // defpackage.k10
    public int getArity() {
        return this.F;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        yn0.a.getClass();
        String strA = ao0.a(this);
        t40.m(strA, "renderLambdaToString(...)");
        return strA;
    }

    public RestrictedSuspendLambda(int i) {
        this(i, null);
    }
}
