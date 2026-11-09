package defpackage;

import io.nats.client.support.NatsConstants;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ParameterizedType, Serializable {
    private static final long serialVersionUID = 0;
    public final Type E;
    public final Type F;
    public final Type[] G;

    public b(Type type, Type type2, Type... typeArr) {
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            boolean z = true;
            boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
            if (type == null && !z2) {
                z = false;
            }
            j31.j(z);
        }
        this.E = type == null ? null : qm.f(type);
        this.F = qm.f(type2);
        Type[] typeArr2 = (Type[]) typeArr.clone();
        this.G = typeArr2;
        int length = typeArr2.length;
        for (int i = 0; i < length; i++) {
            this.G[i].getClass();
            qm.g(this.G[i]);
            Type[] typeArr3 = this.G;
            typeArr3[i] = qm.f(typeArr3[i]);
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && qm.m(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.G.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.E;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.F;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.G) ^ this.F.hashCode();
        Type type = this.E;
        return iHashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        Type[] typeArr = this.G;
        int length = typeArr.length;
        Type type = this.F;
        if (length == 0) {
            return qm.K0(type);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(qm.K0(type));
        sb.append("<");
        sb.append(qm.K0(typeArr[0]));
        for (int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(qm.K0(typeArr[i]));
        }
        sb.append(NatsConstants.GREATER_THAN);
        return sb.toString();
    }
}
