package defpackage;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hq0 implements AlgorithmParameterSpec {
    public static final HashMap a;

    static {
        hq0 hq0Var = new hq0();
        hq0 hq0Var2 = new hq0();
        hq0 hq0Var3 = new hq0();
        hq0 hq0Var4 = new hq0();
        hq0 hq0Var5 = new hq0();
        hq0 hq0Var6 = new hq0();
        hq0 hq0Var7 = new hq0();
        hq0 hq0Var8 = new hq0();
        hq0 hq0Var9 = new hq0();
        hq0 hq0Var10 = new hq0();
        hq0 hq0Var11 = new hq0();
        hq0 hq0Var12 = new hq0();
        hq0 hq0Var13 = new hq0();
        hq0 hq0Var14 = new hq0();
        hq0 hq0Var15 = new hq0();
        hq0 hq0Var16 = new hq0();
        hq0 hq0Var17 = new hq0();
        hq0 hq0Var18 = new hq0();
        hq0 hq0Var19 = new hq0();
        hq0 hq0Var20 = new hq0();
        hq0 hq0Var21 = new hq0();
        hq0 hq0Var22 = new hq0();
        hq0 hq0Var23 = new hq0();
        hq0 hq0Var24 = new hq0();
        HashMap map = new HashMap();
        a = map;
        map.put("slh-dsa-sha2-128f", hq0Var);
        map.put("slh-dsa-sha2-128s", hq0Var2);
        map.put("slh-dsa-sha2-192f", hq0Var3);
        map.put("slh-dsa-sha2-192s", hq0Var4);
        map.put("slh-dsa-sha2-256f", hq0Var5);
        map.put("slh-dsa-sha2-256s", hq0Var6);
        map.put("sha2-128f", hq0Var);
        map.put("sha2-128s", hq0Var2);
        map.put("sha2-192f", hq0Var3);
        map.put("sha2-192s", hq0Var4);
        map.put("sha2-256f", hq0Var5);
        map.put("sha2-256s", hq0Var6);
        map.put("slh-dsa-shake-128f", hq0Var7);
        map.put("slh-dsa-shake-128s", hq0Var8);
        map.put("slh-dsa-shake-192f", hq0Var9);
        map.put("slh-dsa-shake-192s", hq0Var10);
        map.put("slh-dsa-shake-256f", hq0Var11);
        map.put("slh-dsa-shake-256s", hq0Var12);
        map.put("shake-128f", hq0Var7);
        map.put("shake-128s", hq0Var8);
        map.put("shake-192f", hq0Var9);
        map.put("shake-192s", hq0Var10);
        map.put("shake-256f", hq0Var11);
        map.put("shake-256s", hq0Var12);
        map.put("slh-dsa-sha2-128f-with-sha256", hq0Var13);
        map.put("slh-dsa-sha2-128s-with-sha256", hq0Var14);
        map.put("slh-dsa-sha2-192f-with-sha512", hq0Var15);
        map.put("slh-dsa-sha2-192s-with-sha512", hq0Var16);
        map.put("slh-dsa-sha2-256f-with-sha512", hq0Var17);
        map.put("slh-dsa-sha2-256s-with-sha512", hq0Var18);
        map.put("sha2-128f-with-sha256", hq0Var13);
        map.put("sha2-128s-with-sha256", hq0Var14);
        map.put("sha2-192f-with-sha512", hq0Var15);
        map.put("sha2-192s-with-sha512", hq0Var16);
        map.put("sha2-256f-with-sha512", hq0Var17);
        map.put("sha2-256s-with-sha512", hq0Var18);
        map.put("slh-dsa-shake-128f-with-shake128", hq0Var19);
        map.put("slh-dsa-shake-128s-with-shake128", hq0Var20);
        map.put("slh-dsa-shake-192f-with-shake256", hq0Var21);
        map.put("slh-dsa-shake-192s-with-shake256", hq0Var22);
        map.put("slh-dsa-shake-256f-with-shake256", hq0Var23);
        map.put("slh-dsa-shake-256s-with-shake256", hq0Var24);
        map.put("shake-128f-with-shake128", hq0Var19);
        map.put("shake-128s-with-shake128", hq0Var20);
        map.put("shake-192f-with-shake256", hq0Var21);
        map.put("shake-192s-with-shake256", hq0Var22);
        map.put("shake-256f-with-shake256", hq0Var23);
        map.put("shake-256s-with-shake256", hq0Var24);
    }

    public static hq0 a(String str) {
        hq0 hq0Var = (hq0) a.get(jx0.c(str));
        if (hq0Var != null) {
            return hq0Var;
        }
        throw new IllegalArgumentException("unknown parameter name: ".concat(str));
    }
}
