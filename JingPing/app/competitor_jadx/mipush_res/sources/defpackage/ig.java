package defpackage;

import java.io.Serializable;
import kotlin.coroutines.EmptyCoroutineContext;

/* JADX INFO: loaded from: classes.dex */
public final class ig implements Serializable {
    private static final long serialVersionUID = 0;
    public final bj[] E;

    public ig(bj[] bjVarArr) {
        this.E = bjVarArr;
    }

    private final Object readResolve() {
        bj bjVarPlus = EmptyCoroutineContext.INSTANCE;
        for (bj bjVar : this.E) {
            bjVarPlus = bjVarPlus.plus(bjVar);
        }
        return bjVarPlus;
    }
}
