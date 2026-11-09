package defpackage;

import java.io.Serializable;
import kotlin.InitializedLazyImpl;

/* JADX INFO: loaded from: classes.dex */
public final class xy0 implements i80, Serializable {
    public a10 E;
    public volatile Object F;
    public final Object G;

    public xy0(a10 a10Var) {
        t40.n(a10Var, "initializer");
        this.E = a10Var;
        this.F = is.O;
        this.G = this;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // defpackage.i80
    public final Object getValue() {
        Object objInvoke;
        Object obj = this.F;
        is isVar = is.O;
        if (obj != isVar) {
            return obj;
        }
        synchronized (this.G) {
            objInvoke = this.F;
            if (objInvoke == isVar) {
                a10 a10Var = this.E;
                t40.k(a10Var);
                objInvoke = a10Var.invoke();
                this.F = objInvoke;
                this.E = null;
            }
        }
        return objInvoke;
    }

    public final String toString() {
        return this.F != is.O ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
