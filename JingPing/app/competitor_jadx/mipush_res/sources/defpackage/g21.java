package defpackage;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes.dex */
public final class g21 extends e21 {
    public final m20 a;
    public final e21 b;
    public final Type c;

    public g21(m20 m20Var, e21 e21Var, Type type) {
        this.a = m20Var;
        this.b = e21Var;
        this.c = type;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        return this.b.a(a60Var);
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) {
        Type type = this.c;
        Type type2 = (obj == null || !(type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) ? type : obj.getClass();
        e21 e21Var = this.b;
        if (type2 != type) {
            e21 e21VarD = this.a.d(new m21(type2));
            if (!(e21VarD instanceof co0) || (e21Var instanceof co0)) {
                e21Var = e21VarD;
            }
        }
        e21Var.b(f60Var, obj);
    }
}
