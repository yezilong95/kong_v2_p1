package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class dr {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        fk fkVar = new fk(3);
        fk fkVar2 = new fk(4);
        fk fkVar3 = new fk(5);
        fk fkVar4 = new fk(6);
        fk fkVar5 = new fk(7);
        fk fkVar6 = new fk(8);
        fk fkVar7 = new fk(9);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        c("GostR3410-2001-CryptoPro-A", rj.h, fkVar);
        c("GostR3410-2001-CryptoPro-B", rj.i, fkVar2);
        c("GostR3410-2001-CryptoPro-C", rj.j, fkVar3);
        c("GostR3410-2001-CryptoPro-XchA", rj.k, fkVar);
        c("GostR3410-2001-CryptoPro-XchB", rj.l, fkVar3);
        c("Tc26-Gost-3410-12-256-paramSetA", rp0.e, fkVar4);
        c("Tc26-Gost-3410-12-256-paramSetB", rp0.f, fkVar);
        c("Tc26-Gost-3410-12-256-paramSetC", rp0.g, fkVar2);
        c("Tc26-Gost-3410-12-256-paramSetD", rp0.h, fkVar3);
        c("Tc26-Gost-3410-12-512-paramSetA", rp0.i, fkVar5);
        c("Tc26-Gost-3410-12-512-paramSetB", rp0.j, fkVar6);
        c("Tc26-Gost-3410-12-512-paramSetC", rp0.k, fkVar7);
    }

    public static BigInteger a(String str) {
        return new BigInteger(1, c30.b(str));
    }

    public static u91 b(xq xqVar, BigInteger bigInteger, BigInteger bigInteger2) {
        or orVarC = xqVar.c(bigInteger, bigInteger2);
        qm.j(orVarC);
        return new u91(orVarC, false);
    }

    public static void c(String str, d0 d0Var, t91 t91Var) {
        a.put(str, d0Var);
        c.put(d0Var, str);
        b.put(d0Var, t91Var);
    }

    public static String d(d0 d0Var) {
        return (String) c.get(d0Var);
    }

    public static d0 e(String str) {
        return (d0) a.get(str);
    }
}
