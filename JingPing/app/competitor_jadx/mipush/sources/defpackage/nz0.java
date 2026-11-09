package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class nz0 {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        yp0 yp0Var = new yp0(20);
        yp0 yp0Var2 = new yp0(21);
        yp0 yp0Var3 = new yp0(22);
        yp0 yp0Var4 = new yp0(23);
        yp0 yp0Var5 = new yp0(24);
        yp0 yp0Var6 = new yp0(25);
        yp0 yp0Var7 = new yp0(26);
        yp0 yp0Var8 = new yp0(27);
        yp0 yp0Var9 = new yp0(28);
        yp0 yp0Var10 = new yp0(15);
        yp0 yp0Var11 = new yp0(16);
        yp0 yp0Var12 = new yp0(17);
        yp0 yp0Var13 = new yp0(18);
        yp0 yp0Var14 = new yp0(19);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        c("brainpoolP160r1", oz0.d, yp0Var);
        c("brainpoolP160t1", oz0.e, yp0Var2);
        c("brainpoolP192r1", oz0.f, yp0Var3);
        c("brainpoolP192t1", oz0.g, yp0Var4);
        c("brainpoolP224r1", oz0.h, yp0Var5);
        c("brainpoolP224t1", oz0.i, yp0Var6);
        c("brainpoolP256r1", oz0.j, yp0Var7);
        c("brainpoolP256t1", oz0.k, yp0Var8);
        c("brainpoolP320r1", oz0.l, yp0Var9);
        c("brainpoolP320t1", oz0.m, yp0Var10);
        c("brainpoolP384r1", oz0.n, yp0Var11);
        c("brainpoolP384t1", oz0.o, yp0Var12);
        c("brainpoolP512r1", oz0.p, yp0Var13);
        c("brainpoolP512t1", oz0.q, yp0Var14);
    }

    public static BigInteger a(String str) {
        return new BigInteger(1, c30.b(str));
    }

    public static u91 b(xq xqVar, String str) {
        u91 u91Var = new u91(xqVar, c30.b(str));
        qm.j(u91Var.g());
        return u91Var;
    }

    public static void c(String str, d0 d0Var, t91 t91Var) {
        a.put(jx0.c(str), d0Var);
        c.put(d0Var, str);
        b.put(d0Var, t91Var);
    }
}
