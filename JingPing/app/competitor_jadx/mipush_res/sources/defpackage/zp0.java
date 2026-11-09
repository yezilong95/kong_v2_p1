package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class zp0 {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        fk fkVar = new fk(22);
        yp0 yp0Var = new yp0(3);
        yp0 yp0Var2 = new yp0(8);
        yp0 yp0Var3 = new yp0(9);
        yp0 yp0Var4 = new yp0(10);
        yp0 yp0Var5 = new yp0(11);
        yp0 yp0Var6 = new yp0(12);
        yp0 yp0Var7 = new yp0(13);
        yp0 yp0Var8 = new yp0(14);
        fk fkVar2 = new fk(12);
        fk fkVar3 = new fk(13);
        fk fkVar4 = new fk(14);
        fk fkVar5 = new fk(15);
        fk fkVar6 = new fk(16);
        fk fkVar7 = new fk(17);
        fk fkVar8 = new fk(18);
        fk fkVar9 = new fk(19);
        fk fkVar10 = new fk(20);
        fk fkVar11 = new fk(21);
        fk fkVar12 = new fk(23);
        fk fkVar13 = new fk(24);
        fk fkVar14 = new fk(25);
        fk fkVar15 = new fk(26);
        fk fkVar16 = new fk(27);
        fk fkVar17 = new fk(28);
        fk fkVar18 = new fk(29);
        yp0 yp0Var9 = new yp0(0);
        yp0 yp0Var10 = new yp0(1);
        yp0 yp0Var11 = new yp0(2);
        yp0 yp0Var12 = new yp0(4);
        yp0 yp0Var13 = new yp0(5);
        yp0 yp0Var14 = new yp0(6);
        yp0 yp0Var15 = new yp0(7);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        c("secp112r1", aq0.f, fkVar);
        c("secp112r2", aq0.g, yp0Var);
        c("secp128r1", aq0.t, yp0Var2);
        c("secp128r2", aq0.u, yp0Var3);
        c("secp160k1", aq0.i, yp0Var4);
        c("secp160r1", aq0.h, yp0Var5);
        c("secp160r2", aq0.v, yp0Var6);
        c("secp192k1", aq0.w, yp0Var7);
        c("secp192r1", aq0.F, yp0Var8);
        c("secp224k1", aq0.x, fkVar2);
        c("secp224r1", aq0.y, fkVar3);
        c("secp256k1", aq0.j, fkVar4);
        c("secp256r1", aq0.G, fkVar5);
        c("secp384r1", aq0.z, fkVar6);
        c("secp521r1", aq0.A, fkVar7);
        c("sect113r1", aq0.d, fkVar8);
        c("sect113r2", aq0.e, fkVar9);
        c("sect131r1", aq0.n, fkVar10);
        c("sect131r2", aq0.o, fkVar11);
        c("sect163k1", aq0.a, fkVar12);
        c("sect163r1", aq0.b, fkVar13);
        c("sect163r2", aq0.k, fkVar14);
        c("sect193r1", aq0.p, fkVar15);
        c("sect193r2", aq0.q, fkVar16);
        c("sect233k1", aq0.r, fkVar17);
        c("sect233r1", aq0.s, fkVar18);
        c("sect239k1", aq0.c, yp0Var9);
        c("sect283k1", aq0.l, yp0Var10);
        c("sect283r1", aq0.m, yp0Var11);
        c("sect409k1", aq0.B, yp0Var12);
        c("sect409r1", aq0.C, yp0Var13);
        c("sect571k1", aq0.D, yp0Var14);
        c("sect571r1", aq0.E, yp0Var15);
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
