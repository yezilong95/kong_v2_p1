package kotlin.jvm.internal;

import defpackage.ao0;
import defpackage.k10;
import defpackage.t40;
import defpackage.yn0;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lambda<R> implements k10, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // defpackage.k10
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        yn0.a.getClass();
        String strA = ao0.a(this);
        t40.m(strA, "renderLambdaToString(...)");
        return strA;
    }
}
