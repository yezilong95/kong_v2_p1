package defpackage;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: classes.dex */
public abstract class k21 {
    public static final h21 A;
    public static final o8 B;
    public static final h21 a = new h21(Class.class, new k20(new j20(12), 2), 0);
    public static final h21 b = new h21(BitSet.class, new k20(new j20(22), 2), 0);
    public static final j20 c;
    public static final i21 d;
    public static final i21 e;
    public static final i21 f;
    public static final i21 g;
    public static final h21 h;
    public static final h21 i;
    public static final h21 j;
    public static final j20 k;
    public static final h21 l;
    public static final i21 m;
    public static final j20 n;
    public static final j20 o;
    public static final h21 p;
    public static final h21 q;
    public static final h21 r;
    public static final h21 s;
    public static final h21 t;
    public static final h21 u;
    public static final h21 v;
    public static final h21 w;
    public static final o8 x;
    public static final uf y;
    public static final h21 z;

    static {
        j20 j20Var = new j20(23);
        c = new j20(24);
        d = new i21(Boolean.TYPE, Boolean.class, j20Var);
        e = new i21(Byte.TYPE, Byte.class, new j20(25));
        f = new i21(Short.TYPE, Short.class, new j20(26));
        g = new i21(Integer.TYPE, Integer.class, new j20(27));
        h = new h21(AtomicInteger.class, new k20(new j20(28), 2), 0);
        i = new h21(AtomicBoolean.class, new k20(new j20(29), 2), 0);
        j = new h21(AtomicIntegerArray.class, new k20(new j20(2), 2), 0);
        k = new j20(3);
        l = new h21(Number.class, new j20(6), 0);
        m = new i21(Character.TYPE, Character.class, new j20(7));
        j20 j20Var2 = new j20(8);
        n = new j20(9);
        o = new j20(10);
        p = new h21(String.class, j20Var2, 0);
        q = new h21(StringBuilder.class, new j20(11), 0);
        r = new h21(StringBuffer.class, new j20(13), 0);
        s = new h21(URL.class, new j20(14), 0);
        t = new h21(URI.class, new j20(15), 0);
        u = new h21(InetAddress.class, new j20(16), 1);
        v = new h21(UUID.class, new j20(17), 0);
        w = new h21(Currency.class, new k20(new j20(18), 2), 0);
        x = new o8(5);
        y = new uf(3, new j20(19));
        z = new h21(Locale.class, new j20(20), 0);
        A = new h21(w50.class, new j20(21), 1);
        B = new o8(6);
    }
}
