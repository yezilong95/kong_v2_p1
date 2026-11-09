package defpackage;

import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public abstract class gk {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;
    public static final Hashtable d;
    public static final Vector e;

    static {
        l lVar = new l(11);
        l lVar2 = new l(22);
        l lVar3 = new l(26);
        l lVar4 = new l(27);
        l lVar5 = new l(28);
        l lVar6 = new l(29);
        fk fkVar = new fk(0);
        fk fkVar2 = new fk(1);
        fk fkVar3 = new fk(2);
        l lVar7 = new l(1);
        l lVar8 = new l(2);
        l lVar9 = new l(3);
        l lVar10 = new l(4);
        l lVar11 = new l(5);
        l lVar12 = new l(6);
        l lVar13 = new l(7);
        l lVar14 = new l(8);
        l lVar15 = new l(9);
        l lVar16 = new l(10);
        l lVar17 = new l(12);
        l lVar18 = new l(13);
        l lVar19 = new l(14);
        l lVar20 = new l(15);
        l lVar21 = new l(16);
        l lVar22 = new l(17);
        l lVar23 = new l(18);
        l lVar24 = new l(19);
        l lVar25 = new l(20);
        l lVar26 = new l(21);
        l lVar27 = new l(23);
        l lVar28 = new l(24);
        l lVar29 = new l(25);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        d = new Hashtable();
        e = new Vector();
        c("curve25519", qj.a, lVar);
        c("secp128r1", aq0.t, lVar2);
        c("secp160k1", aq0.i, lVar3);
        c("secp160r1", aq0.h, lVar4);
        c("secp160r2", aq0.v, lVar5);
        c("secp192k1", aq0.w, lVar6);
        d0 d0Var = aq0.F;
        c("secp192r1", d0Var, fkVar);
        c("secp224k1", aq0.x, fkVar2);
        d0 d0Var2 = aq0.y;
        c("secp224r1", d0Var2, fkVar3);
        c("secp256k1", aq0.j, lVar7);
        d0 d0Var3 = aq0.G;
        c("secp256r1", d0Var3, lVar8);
        d0 d0Var4 = aq0.z;
        c("secp384r1", d0Var4, lVar9);
        d0 d0Var5 = aq0.A;
        c("secp521r1", d0Var5, lVar10);
        c("sect113r1", aq0.d, lVar11);
        c("sect113r2", aq0.e, lVar12);
        c("sect131r1", aq0.n, lVar13);
        c("sect131r2", aq0.o, lVar14);
        d0 d0Var6 = aq0.a;
        c("sect163k1", d0Var6, lVar15);
        c("sect163r1", aq0.b, lVar16);
        d0 d0Var7 = aq0.k;
        c("sect163r2", d0Var7, lVar17);
        c("sect193r1", aq0.p, lVar18);
        c("sect193r2", aq0.q, lVar19);
        d0 d0Var8 = aq0.r;
        c("sect233k1", d0Var8, lVar20);
        d0 d0Var9 = aq0.s;
        c("sect233r1", d0Var9, lVar21);
        c("sect239k1", aq0.c, lVar22);
        d0 d0Var10 = aq0.l;
        c("sect283k1", d0Var10, lVar23);
        d0 d0Var11 = aq0.m;
        c("sect283r1", d0Var11, lVar24);
        d0 d0Var12 = aq0.B;
        c("sect409k1", d0Var12, lVar25);
        d0 d0Var13 = aq0.C;
        c("sect409r1", d0Var13, lVar26);
        d0 d0Var14 = aq0.D;
        c("sect571k1", d0Var14, lVar27);
        d0 d0Var15 = aq0.E;
        c("sect571r1", d0Var15, lVar28);
        c("sm2p256v1", n10.a, lVar29);
        b("B-163", d0Var7);
        b("B-233", d0Var9);
        b("B-283", d0Var11);
        b("B-409", d0Var13);
        b("B-571", d0Var15);
        b("K-163", d0Var6);
        b("K-233", d0Var8);
        b("K-283", d0Var10);
        b("K-409", d0Var12);
        b("K-571", d0Var14);
        b("P-192", d0Var);
        b("P-224", d0Var2);
        b("P-256", d0Var3);
        b("P-384", d0Var4);
        b("P-521", d0Var5);
    }

    public static u91 a(xq xqVar, String str) {
        u91 u91Var = new u91(xqVar, c30.b(str));
        qm.j(u91Var.g());
        return u91Var;
    }

    public static void b(String str, d0 d0Var) {
        Object obj = c.get(d0Var);
        if (obj == null) {
            throw new IllegalStateException();
        }
        String strC = jx0.c(str);
        b.put(strC, d0Var);
        a.put(strC, obj);
    }

    public static void c(String str, d0 d0Var, t91 t91Var) {
        e.addElement(str);
        d.put(d0Var, str);
        c.put(d0Var, t91Var);
        String strC = jx0.c(str);
        b.put(strC, d0Var);
        a.put(strC, t91Var);
    }
}
