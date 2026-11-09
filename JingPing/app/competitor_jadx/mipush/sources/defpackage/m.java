package defpackage;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    public static final Hashtable a;
    public static final Hashtable b;
    public static final Hashtable c;

    static {
        l lVar = new l(0);
        Hashtable hashtable = new Hashtable();
        a = hashtable;
        Hashtable hashtable2 = new Hashtable();
        b = hashtable2;
        Hashtable hashtable3 = new Hashtable();
        c = hashtable3;
        d0 d0Var = n.a;
        hashtable.put(jx0.c("FRP256v1"), d0Var);
        hashtable3.put(d0Var, "FRP256v1");
        hashtable2.put(d0Var, lVar);
    }
}
