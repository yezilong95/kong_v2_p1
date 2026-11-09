package kotlin;

import defpackage.a10;
import defpackage.i80;
import defpackage.is;
import defpackage.t40;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class UnsafeLazyImpl<T> implements i80, Serializable {
    public a10 E;
    public Object F;

    public UnsafeLazyImpl(a10 a10Var) {
        t40.n(a10Var, "initializer");
        this.E = a10Var;
        this.F = is.O;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // defpackage.i80
    public T getValue() {
        if (this.F == is.O) {
            a10 a10Var = this.E;
            t40.k(a10Var);
            this.F = a10Var.invoke();
            this.E = null;
        }
        return (T) this.F;
    }

    public boolean isInitialized() {
        return this.F != is.O;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
