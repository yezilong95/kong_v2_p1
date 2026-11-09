package kotlin.coroutines.jvm.internal;

import defpackage.ao0;
import defpackage.k10;
import defpackage.t40;
import defpackage.ui;
import defpackage.yn0;

/* JADX INFO: loaded from: classes.dex */
public abstract class SuspendLambda extends ContinuationImpl implements k10 {
    public final int H;

    public SuspendLambda(int i, ui uiVar) {
        super(uiVar);
        this.H = i;
    }

    @Override // defpackage.k10
    public int getArity() {
        return this.H;
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

    public SuspendLambda(int i) {
        this(i, null);
    }
}
