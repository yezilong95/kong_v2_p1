package kotlin;

import defpackage.i10;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class InitializedLazyImpl<T> implements i10, Serializable {
    public final Object b;

    public InitializedLazyImpl(T t) {
        this.b = t;
    }

    @Override // defpackage.i10
    public T getValue() {
        return (T) this.b;
    }

    public boolean isInitialized() {
        return true;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
