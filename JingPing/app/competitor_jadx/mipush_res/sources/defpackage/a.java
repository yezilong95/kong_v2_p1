package defpackage;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class a implements GenericArrayType, Serializable {
    private static final long serialVersionUID = 0;
    public final Type E;

    public a(Type type) {
        this.E = qm.f(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && qm.m(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.E;
    }

    public final int hashCode() {
        return this.E.hashCode();
    }

    public final String toString() {
        return qm.K0(this.E) + "[]";
    }
}
