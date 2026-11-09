package kotlin.jvm.internal;

import defpackage.ce0;
import defpackage.de0;
import defpackage.oy;
import defpackage.yu;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lambda<R> implements yu, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // defpackage.yu
    public int getArity() {
        return this.arity;
    }

    public String toString() {
        ce0.a.getClass();
        String strA = de0.a(this);
        oy.i(strA, "renderLambdaToString(...)");
        return strA;
    }
}
