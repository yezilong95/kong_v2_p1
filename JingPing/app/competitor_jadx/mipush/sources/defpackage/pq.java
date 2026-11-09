package defpackage;

import java.util.Enumeration;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class pq {
    public static final HashMap a;

    static {
        HashMap map = new HashMap();
        Enumeration enumerationElements = gk.e.elements();
        while (enumerationElements.hasMoreElements()) {
            String str = (String) enumerationElements.nextElement();
            d0 d0Var = (d0) p91.a.get(jx0.c(str));
            t91 t91Var = null;
            t91 t91Var2 = d0Var == null ? null : (t91) p91.b.get(d0Var);
            if (t91Var2 == null) {
                d0 d0Var2 = (d0) zp0.a.get(jx0.c(str));
                t91Var2 = d0Var2 == null ? null : (t91) zp0.b.get(d0Var2);
            }
            if (t91Var2 == null) {
                d0 d0Var3 = (d0) xf0.a.get(jx0.e(str));
                t91Var2 = d0Var3 != null ? (t91) zp0.b.get(d0Var3) : null;
            }
            if (t91Var2 == null) {
                d0 d0Var4 = (d0) nz0.a.get(jx0.c(str));
                t91Var2 = d0Var4 == null ? null : (t91) nz0.b.get(d0Var4);
            }
            if (t91Var2 == null) {
                d0 d0Var5 = (d0) m.a.get(jx0.c(str));
                t91Var2 = d0Var5 == null ? null : (t91) m.b.get(d0Var5);
            }
            if (t91Var2 == null) {
                d0 d0VarE = dr.e(str);
                t91Var2 = d0VarE == null ? null : (t91) dr.b.get(d0VarE);
            }
            if (t91Var2 == null) {
                d0 d0Var6 = (d0) m10.a.get(jx0.c(str));
                if (d0Var6 != null) {
                    t91Var = (t91) m10.b.get(d0Var6);
                }
            } else {
                t91Var = t91Var2;
            }
            if (t91Var != null) {
                xq xqVarC = t91Var.c();
                if (xqVarC.a.a() == 1) {
                    map.put(xqVarC, ((t91) gk.a.get(jx0.c(str))).c());
                }
            }
        }
        xq xqVarC2 = ((t91) gk.a.get(jx0.c("Curve25519"))).c();
        map.put(new wq(xqVarC2.a.b(), xqVarC2.b.h1(), xqVarC2.c.h1(), xqVarC2.d, xqVarC2.e, true), xqVarC2);
        a = map;
    }
}
