package defpackage;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class lb0 implements AlgorithmParameterSpec {
    public static final HashMap b;
    public final String a;

    static {
        lb0 lb0Var = new lb0("ML-KEM-512");
        lb0 lb0Var2 = new lb0("ML-KEM-768");
        lb0 lb0Var3 = new lb0("ML-KEM-1024");
        HashMap map = new HashMap();
        b = map;
        map.put("ml-kem-512", lb0Var);
        map.put("ml-kem-768", lb0Var2);
        map.put("ml-kem-1024", lb0Var3);
        map.put("kyber512", lb0Var);
        map.put("kyber768", lb0Var2);
        map.put("kyber1024", lb0Var3);
    }

    public lb0(String str) {
        this.a = str;
    }

    public static lb0 a(String str) {
        lb0 lb0Var = (lb0) b.get(jx0.c(str));
        if (lb0Var != null) {
            return lb0Var;
        }
        throw new IllegalArgumentException("unknown parameter name: ".concat(str));
    }
}
