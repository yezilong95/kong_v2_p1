package defpackage;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class o8 implements f21 {
    public final /* synthetic */ int E;

    public /* synthetic */ o8(int i) {
        this.E = i;
    }

    @Override // defpackage.f21
    public final e21 a(m20 m20Var, m21 m21Var) {
        switch (this.E) {
            case 0:
                Type type = m21Var.b;
                boolean z = type instanceof GenericArrayType;
                if (!z && (!(type instanceof Class) || !((Class) type).isArray())) {
                    return null;
                }
                Type genericComponentType = z ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
                return new p8(m20Var, m20Var.d(new m21(genericComponentType)), qm.I(genericComponentType));
            case 1:
                if (m21Var.a == Date.class) {
                    return new vw0(2);
                }
                return null;
            case 2:
                if (m21Var.a == Object.class) {
                    return new si0(m20Var);
                }
                return null;
            case 3:
                if (m21Var.a == java.sql.Date.class) {
                    return new vw0(0);
                }
                return null;
            case 4:
                if (m21Var.a == Time.class) {
                    return new vw0(1);
                }
                return null;
            case 5:
                if (m21Var.a != Timestamp.class) {
                    return null;
                }
                m20Var.getClass();
                return new k20(m20Var.d(new m21(Date.class)), 3);
            default:
                Class superclass = m21Var.a;
                if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
                    return null;
                }
                if (!superclass.isEnum()) {
                    superclass = superclass.getSuperclass();
                }
                return new p8(superclass);
        }
    }
}
