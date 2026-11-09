package defpackage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class uf implements f21 {
    public final /* synthetic */ int E;
    public final Object F;

    public /* synthetic */ uf(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    public static e21 b(i6 i6Var, m20 m20Var, m21 m21Var, u50 u50Var) {
        e21 e21VarA;
        Object objQ = i6Var.x(new m21(u50Var.value())).q();
        if (objQ instanceof e21) {
            e21VarA = (e21) objQ;
        } else {
            if (!(objQ instanceof f21)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objQ.getClass().getName() + " as a @JsonAdapter for " + qm.K0(m21Var.b) + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            e21VarA = ((f21) objQ).a(m20Var, m21Var);
        }
        return (e21VarA == null || !u50Var.nullSafe()) ? e21VarA : new k20(e21VarA, 2);
    }

    @Override // defpackage.f21
    public final e21 a(m20 m20Var, m21 m21Var) {
        Class cls;
        Type[] actualTypeArguments;
        cls = Object.class;
        Object obj = this.F;
        switch (this.E) {
            case 0:
                Class cls2 = m21Var.a;
                if (!Collection.class.isAssignableFrom(cls2)) {
                    return null;
                }
                Type type = m21Var.b;
                if (type instanceof WildcardType) {
                    type = ((WildcardType) type).getUpperBounds()[0];
                }
                j31.j(Collection.class.isAssignableFrom(cls2));
                Type typeP0 = qm.p0(type, cls2, qm.B(type, cls2, Collection.class), new HashSet());
                if (typeP0 instanceof WildcardType) {
                    typeP0 = ((WildcardType) typeP0).getUpperBounds()[0];
                }
                cls = typeP0 instanceof ParameterizedType ? ((ParameterizedType) typeP0).getActualTypeArguments()[0] : Object.class;
                return new p8(m20Var, cls, m20Var.d(new m21(cls)), ((i6) obj).x(m21Var));
            case 1:
                u50 u50Var = (u50) m21Var.a.getAnnotation(u50.class);
                if (u50Var == null) {
                    return null;
                }
                return b((i6) obj, m20Var, m21Var, u50Var);
            case 2:
                if (!Map.class.isAssignableFrom(m21Var.a)) {
                    return null;
                }
                Type type2 = m21Var.b;
                Class clsI = qm.I(type2);
                if (type2 == Properties.class) {
                    actualTypeArguments = new Type[]{String.class, String.class};
                } else {
                    if (type2 instanceof WildcardType) {
                        type2 = ((WildcardType) type2).getUpperBounds()[0];
                    }
                    j31.j(Map.class.isAssignableFrom(clsI));
                    Type typeP02 = qm.p0(type2, clsI, qm.B(type2, clsI, Map.class), new HashSet());
                    actualTypeArguments = typeP02 instanceof ParameterizedType ? ((ParameterizedType) typeP02).getActualTypeArguments() : new Type[]{cls, cls};
                }
                Type type3 = actualTypeArguments[0];
                return new dc0(this, m20Var, actualTypeArguments[0], (type3 == Boolean.TYPE || type3 == Boolean.class) ? k21.c : m20Var.d(new m21(type3)), actualTypeArguments[1], m20Var.d(new m21(actualTypeArguments[1])), ((i6) obj).x(m21Var));
            default:
                Class cls3 = m21Var.a;
                if (cls3 == Calendar.class || cls3 == GregorianCalendar.class) {
                    return (j20) obj;
                }
                return null;
        }
    }

    public String toString() {
        switch (this.E) {
            case 3:
                return "Factory[type=" + Calendar.class.getName() + Marker.ANY_NON_NULL_MARKER + GregorianCalendar.class.getName() + ",adapter=" + ((j20) this.F) + "]";
            default:
                return super.toString();
        }
    }
}
