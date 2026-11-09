package kotlin.coroutines;

import defpackage.lh;
import defpackage.mh;
import defpackage.nh;
import defpackage.oy;
import defpackage.ru;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class EmptyCoroutineContext implements nh, Serializable {
    public static final EmptyCoroutineContext INSTANCE = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;

    private final Object readResolve() {
        return INSTANCE;
    }

    @Override // defpackage.nh
    public <R> R fold(R r, ru ruVar) {
        oy.j(ruVar, "operation");
        return r;
    }

    @Override // defpackage.nh
    public <E extends lh> E get(mh mhVar) {
        oy.j(mhVar, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // defpackage.nh
    public nh minusKey(mh mhVar) {
        oy.j(mhVar, "key");
        return this;
    }

    @Override // defpackage.nh
    public nh plus(nh nhVar) {
        oy.j(nhVar, "context");
        return nhVar;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
