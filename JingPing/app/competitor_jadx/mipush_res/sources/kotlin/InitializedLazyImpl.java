package kotlin;

import defpackage.i80;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class InitializedLazyImpl<T> implements i80, Serializable {
    public final Object E;

    public InitializedLazyImpl(T t) {
        this.E = t;
    }

    @Override // defpackage.i80
    public T getValue() {
        return (T) this.E;
    }

    public boolean isInitialized() {
        return true;
    }

    public String toString() {
        return String.valueOf(getValue());
    }
}
