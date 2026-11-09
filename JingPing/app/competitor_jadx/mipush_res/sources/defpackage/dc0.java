package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class dc0 extends e21 {
    public final g21 a;
    public final g21 b;
    public final qi0 c;
    public final /* synthetic */ uf d;

    public dc0(uf ufVar, m20 m20Var, Type type, e21 e21Var, Type type2, e21 e21Var2, qi0 qi0Var) {
        this.d = ufVar;
        this.a = new g21(m20Var, e21Var, type);
        this.b = new g21(m20Var, e21Var2, type2);
        this.c = qi0Var;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) throws IOException {
        JsonToken jsonTokenO = a60Var.O();
        if (jsonTokenO == JsonToken.NULL) {
            a60Var.K();
            return null;
        }
        Map map = (Map) this.c.q();
        JsonToken jsonToken = JsonToken.BEGIN_ARRAY;
        g21 g21Var = this.b;
        g21 g21Var2 = this.a;
        e21 e21Var = g21Var.b;
        e21 e21Var2 = g21Var2.b;
        if (jsonTokenO == jsonToken) {
            a60Var.j();
            while (a60Var.B()) {
                a60Var.j();
                Object objA = e21Var2.a(a60Var);
                if (map.put(objA, e21Var.a(a60Var)) != null) {
                    throw new JsonSyntaxException("duplicate key: " + objA);
                }
                a60Var.y();
            }
            a60Var.y();
            return map;
        }
        a60Var.l();
        while (a60Var.B()) {
            di.G.getClass();
            int iX = a60Var.L;
            if (iX == 0) {
                iX = a60Var.x();
            }
            if (iX == 13) {
                a60Var.L = 9;
            } else if (iX == 12) {
                a60Var.L = 8;
            } else {
                if (iX != 14) {
                    throw new IllegalStateException("Expected a name but was " + a60Var.O() + a60Var.D());
                }
                a60Var.L = 10;
            }
            Object objA2 = e21Var2.a(a60Var);
            if (map.put(objA2, e21Var.a(a60Var)) != null) {
                throw new JsonSyntaxException("duplicate key: " + objA2);
            }
        }
        a60Var.z();
        return map;
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            f60Var.C();
            return;
        }
        this.d.getClass();
        g21 g21Var = this.b;
        f60Var.w();
        for (Map.Entry entry : map.entrySet()) {
            f60Var.A(String.valueOf(entry.getKey()));
            g21Var.b(f60Var, entry.getValue());
        }
        f60Var.z();
    }
}
