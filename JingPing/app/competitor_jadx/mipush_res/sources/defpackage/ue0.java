package defpackage;

import io.nats.client.support.Digester;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ue0 {
    public static final HashMap a;

    static {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        a = map2;
        map.put(vj0.d, "MD2");
        map.put(vj0.e, "MD4");
        map.put(vj0.f, "MD5");
        d0 d0Var = oi0.a;
        map.put(d0Var, "SHA-1");
        d0 d0Var2 = yf0.d;
        map.put(d0Var2, "SHA-224");
        d0 d0Var3 = yf0.a;
        map.put(d0Var3, Digester.DEFAULT_DIGEST_ALGORITHM);
        d0 d0Var4 = yf0.b;
        map.put(d0Var4, "SHA-384");
        d0 d0Var5 = yf0.c;
        map.put(d0Var5, "SHA-512");
        map.put(yf0.e, "SHA-512(224)");
        map.put(yf0.f, "SHA-512(256)");
        map.put(oz0.b, "RIPEMD-128");
        map.put(oz0.a, "RIPEMD-160");
        map.put(oz0.c, "RIPEMD-128");
        map.put(k30.b, "RIPEMD-128");
        map.put(k30.a, "RIPEMD-160");
        map.put(rj.a, "GOST3411");
        map.put(o10.a, "Tiger");
        map.put(k30.c, "Whirlpool");
        d0 d0Var6 = yf0.g;
        map.put(d0Var6, "SHA3-224");
        d0 d0Var7 = yf0.h;
        map.put(d0Var7, "SHA3-256");
        d0 d0Var8 = yf0.i;
        map.put(d0Var8, "SHA3-384");
        d0 d0Var9 = yf0.j;
        map.put(d0Var9, "SHA3-512");
        map.put(yf0.k, "SHAKE128");
        map.put(yf0.l, "SHAKE256");
        map.put(n10.d, "SM3");
        d0 d0Var10 = ze0.b;
        map.put(d0Var10, "BLAKE3-256");
        map2.put("SHA-1", new l4(d0Var, rk.E));
        map2.put("SHA-224", new l4(d0Var2));
        map2.put("SHA224", new l4(d0Var2));
        map2.put(Digester.DEFAULT_DIGEST_ALGORITHM, new l4(d0Var3));
        map2.put("SHA256", new l4(d0Var3));
        map2.put("SHA-384", new l4(d0Var4));
        map2.put("SHA384", new l4(d0Var4));
        map2.put("SHA-512", new l4(d0Var5));
        map2.put("SHA512", new l4(d0Var5));
        map2.put("SHA3-224", new l4(d0Var6));
        map2.put("SHA3-256", new l4(d0Var7));
        map2.put("SHA3-384", new l4(d0Var8));
        map2.put("SHA3-512", new l4(d0Var9));
        map2.put("BLAKE3-256", new l4(d0Var10));
    }
}
