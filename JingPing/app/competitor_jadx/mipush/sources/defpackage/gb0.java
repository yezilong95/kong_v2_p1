package defpackage;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class gb0 implements AlgorithmParameterSpec {
    public static final HashMap b;
    public final String a;

    static {
        gb0 gb0Var = new gb0("ML-DSA-44");
        gb0 gb0Var2 = new gb0("ML-DSA-65");
        gb0 gb0Var3 = new gb0("ML-DSA-87");
        gb0 gb0Var4 = new gb0("ML-DSA-44-WITH-SHA512");
        gb0 gb0Var5 = new gb0("ML-DSA-65-WITH-SHA512");
        gb0 gb0Var6 = new gb0("ML-DSA-87-WITH-SHA512");
        HashMap map = new HashMap();
        b = map;
        map.put("ml-dsa-44", gb0Var);
        map.put("ml-dsa-65", gb0Var2);
        map.put("ml-dsa-87", gb0Var3);
        map.put("ml-dsa-44-with-sha512", gb0Var4);
        map.put("ml-dsa-65-with-sha512", gb0Var5);
        map.put("ml-dsa-87-with-sha512", gb0Var6);
    }

    public gb0(String str) {
        this.a = str;
    }

    public static gb0 a(String str) {
        gb0 gb0Var = (gb0) b.get(jx0.c(str));
        if (gb0Var != null) {
            return gb0Var;
        }
        throw new IllegalArgumentException("unknown parameter name: ".concat(str));
    }
}
