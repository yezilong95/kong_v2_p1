package kotlin.coroutines;

import defpackage.aj;
import defpackage.bj;
import defpackage.d10;
import defpackage.t40;
import defpackage.zi;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class EmptyCoroutineContext implements bj, Serializable {
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return INSTANCE;
    }

    @Override // defpackage.bj
    public <R> R fold(R r, d10 d10Var) {
        t40.n(d10Var, "operation");
        return r;
    }

    @Override // defpackage.bj
    public <E extends zi> E get(aj ajVar) {
        t40.n(ajVar, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // defpackage.bj
    public bj minusKey(aj ajVar) {
        t40.n(ajVar, "key");
        return this;
    }

    @Override // defpackage.bj
    public bj plus(bj bjVar) {
        t40.n(bjVar, "context");
        return bjVar;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
