package defpackage;

import java.math.BigInteger;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class m10 {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        fk fkVar = new fk(10);
        fk fkVar2 = new fk(11);
        a = new Hashtable();
        b = new Hashtable();
        c = new Hashtable();
        b("wapip192v1", n10.b, fkVar2);
        b("wapi192v1", n10.c, fkVar2);
        b("sm2p256v1", n10.a, fkVar);
    }

    public static BigInteger a(String str) {
        return new BigInteger(1, c30.b(str));
    }

    public static void b(String str, d0 d0Var, t91 t91Var) {
        a.put(jx0.c(str), d0Var);
        c.put(d0Var, str);
        b.put(d0Var, t91Var);
    }
}
