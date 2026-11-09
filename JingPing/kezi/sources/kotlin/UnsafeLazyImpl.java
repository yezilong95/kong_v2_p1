package kotlin;

import defpackage.i10;
import defpackage.ou;
import defpackage.oy;
import defpackage.qm;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class UnsafeLazyImpl<T> implements i10, Serializable {
    public ou b;
    public Object c;

    public UnsafeLazyImpl(ou ouVar) {
        oy.j(ouVar, "initializer");
        this.b = ouVar;
        this.c = qm.k;
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // defpackage.i10
    public T getValue() {
        if (this.c == qm.k) {
            ou ouVar = this.b;
            oy.g(ouVar);
            this.c = ouVar.invoke();
            this.b = null;
        }
        return (T) this.c;
    }

    public boolean isInitialized() {
        return this.c != qm.k;
    }

    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
