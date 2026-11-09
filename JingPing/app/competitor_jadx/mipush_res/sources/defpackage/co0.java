package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class co0 extends e21 {
    public final qi0 a;
    public final LinkedHashMap b;

    public co0(qi0 qi0Var, LinkedHashMap linkedHashMap) {
        this.a = qi0Var;
        this.b = linkedHashMap;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) throws IOException {
        if (a60Var.O() == JsonToken.NULL) {
            a60Var.K();
            return null;
        }
        Object objQ = this.a.q();
        try {
            a60Var.l();
            while (a60Var.B()) {
                bo0 bo0Var = (bo0) this.b.get(a60Var.I());
                if (bo0Var == null || !bo0Var.c) {
                    a60Var.T();
                } else {
                    Object objA = bo0Var.f.a(a60Var);
                    if (objA != null || !bo0Var.i) {
                        bo0Var.d.set(objQ, objA);
                    }
                }
            }
            a60Var.z();
            return objQ;
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (IllegalStateException e2) {
            throw new JsonSyntaxException(e2);
        }
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        if (obj == null) {
            f60Var.C();
            return;
        }
        f60Var.w();
        try {
            for (bo0 bo0Var : this.b.values()) {
                boolean z = bo0Var.b;
                Field field = bo0Var.d;
                if (z && field.get(obj) != obj) {
                    f60Var.A(bo0Var.a);
                    Object obj2 = field.get(obj);
                    boolean z2 = bo0Var.e;
                    e21 g21Var = bo0Var.f;
                    if (!z2) {
                        g21Var = new g21(bo0Var.g, g21Var, bo0Var.h.b);
                    }
                    g21Var.b(f60Var, obj2);
                }
            }
            f60Var.z();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }
}
