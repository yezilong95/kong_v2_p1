package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class p91 {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        o91 o91Var = new o91(9);
        o91 o91Var2 = new o91(14);
        o91 o91Var3 = new o91(15);
        o91 o91Var4 = new o91(16);
        o91 o91Var5 = new o91(17);
        o91 o91Var6 = new o91(18);
        o91 o91Var7 = new o91(19);
        o91 o91Var8 = new o91(20);
        o91 o91Var9 = new o91(21);
        yp0 yp0Var = new yp0(29);
        o91 o91Var10 = new o91(0);
        o91 o91Var11 = new o91(1);
        o91 o91Var12 = new o91(2);
        o91 o91Var13 = new o91(3);
        o91 o91Var14 = new o91(4);
        o91 o91Var15 = new o91(5);
        o91 o91Var16 = new o91(6);
        o91 o91Var17 = new o91(7);
        o91 o91Var18 = new o91(8);
        o91 o91Var19 = new o91(10);
        o91 o91Var20 = new o91(11);
        o91 o91Var21 = new o91(12);
        o91 o91Var22 = new o91(13);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        c("prime192v1", w91.v, o91Var);
        c("prime192v2", w91.w, o91Var2);
        c("prime192v3", w91.x, o91Var3);
        c("prime239v1", w91.y, o91Var4);
        c("prime239v2", w91.z, o91Var5);
        c("prime239v3", w91.A, o91Var6);
        c("prime256v1", w91.B, o91Var7);
        c("c2pnb163v1", w91.f, o91Var8);
        c("c2pnb163v2", w91.g, o91Var9);
        c("c2pnb163v3", w91.h, yp0Var);
        c("c2pnb176w1", w91.i, o91Var10);
        c("c2tnb191v1", w91.j, o91Var11);
        c("c2tnb191v2", w91.k, o91Var12);
        c("c2tnb191v3", w91.l, o91Var13);
        c("c2pnb208w1", w91.m, o91Var14);
        c("c2tnb239v1", w91.n, o91Var15);
        c("c2tnb239v2", w91.o, o91Var16);
        c("c2tnb239v3", w91.p, o91Var17);
        c("c2pnb272w1", w91.q, o91Var18);
        c("c2pnb304w1", w91.r, o91Var19);
        c("c2tnb359v1", w91.s, o91Var20);
        c("c2pnb368w1", w91.t, o91Var21);
        c("c2tnb431r1", w91.u, o91Var22);
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
        a.put(str, d0Var);
        c.put(d0Var, str);
        b.put(d0Var, t91Var);
    }
}
