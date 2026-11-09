package defpackage;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes.dex */
public final class c implements WildcardType, Serializable {
    private static final long serialVersionUID = 0;
    public final Type E;
    public final Type F;

    public c(Type[] typeArr, Type[] typeArr2) {
        j31.j(typeArr2.length <= 1);
        j31.j(typeArr.length == 1);
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            qm.g(typeArr[0]);
            this.F = null;
            this.E = qm.f(typeArr[0]);
            return;
        }
        typeArr2[0].getClass();
        qm.g(typeArr2[0]);
        j31.j(typeArr[0] == Object.class);
        this.F = qm.f(typeArr2[0]);
        this.E = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && qm.m(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.F;
        return type != null ? new Type[]{type} : qm.a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.E};
    }

    public final int hashCode() {
        Type type = this.F;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.E.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.F;
        if (type != null) {
            return "? super " + qm.K0(type);
        }
        Type type2 = this.E;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + qm.K0(type2);
    }
}
