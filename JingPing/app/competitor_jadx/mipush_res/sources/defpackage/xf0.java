package defpackage;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public abstract class xf0 {
    public static final Hashtable a = new Hashtable();
    public static final Hashtable b = new Hashtable();

    static {
        a("B-571", aq0.E);
        a("B-409", aq0.C);
        a("B-283", aq0.m);
        a("B-233", aq0.s);
        a("B-163", aq0.k);
        a("K-571", aq0.D);
        a("K-409", aq0.B);
        a("K-283", aq0.l);
        a("K-233", aq0.r);
        a("K-163", aq0.a);
        a("P-521", aq0.A);
        a("P-384", aq0.z);
        a("P-256", aq0.G);
        a("P-224", aq0.y);
        a("P-192", aq0.F);
    }

    public static void a(String str, d0 d0Var) {
        a.put(str, d0Var);
        b.put(d0Var, str);
    }
}
