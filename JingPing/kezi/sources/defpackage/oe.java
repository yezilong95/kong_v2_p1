package defpackage;

import java.io.Serializable;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class oe implements Serializable {
    private static final long serialVersionUID = 0;
    public final nh[] b;

    public oe(nh[] nhVarArr) {
        this.b = nhVarArr;
    }

    private final Object readResolve() {
        nh nhVarPlus = EmptyCoroutineContext.INSTANCE;
        for (nh nhVar : this.b) {
            nhVarPlus = nhVarPlus.plus(nhVar);
        }
        return nhVarPlus;
    }
}
