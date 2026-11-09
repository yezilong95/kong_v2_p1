package kotlin.coroutines.jvm.internal;

import defpackage.ce0;
import defpackage.de0;
import defpackage.fh;
import defpackage.oy;
import defpackage.yu;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedSuspendLambda extends RestrictedContinuationImpl implements yu {
    public final int c;

    public RestrictedSuspendLambda(int i, fh fhVar) {
        super(fhVar);
        this.c = i;
    }

    @Override // defpackage.yu
    public int getArity() {
        return this.c;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        ce0.a.getClass();
        String strA = de0.a(this);
        oy.i(strA, "renderLambdaToString(...)");
        return strA;
    }

    public RestrictedSuspendLambda(int i) {
        this(i, null);
    }
}
