package defpackage;

import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class pr extends a0 {
    public j0 E;

    public pr(int i, BigInteger bigInteger, p pVar, q91 q91Var) {
        byte[] bArrA = oa.a((i + 7) / 8, bigInteger);
        u uVar = new u(4);
        uVar.c(new y(1L));
        uVar.c(new tk(bArrA));
        uVar.c(new j9(true, 0, (t) q91Var, 1));
        if (pVar != null) {
            uVar.c(new j9(true, 1, (t) pVar, 1));
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        this.E = wkVar;
    }

    public static pr g(g0 g0Var) {
        if (g0Var == null) {
            return null;
        }
        j0 j0VarQ = j0.q(g0Var);
        pr prVar = new pr();
        prVar.E = j0VarQ;
        return prVar;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        return this.E;
    }

    public final BigInteger h() {
        return new BigInteger(1, ((e0) this.E.r(1)).E);
    }

    public final p i() {
        g0 g0VarO;
        Enumeration enumerationS = this.E.s();
        while (true) {
            if (!enumerationS.hasMoreElements()) {
                g0VarO = null;
                break;
            }
            t tVar = (t) enumerationS.nextElement();
            if (tVar instanceof j9) {
                j9 j9Var = (j9) tVar;
                if (j9Var.F == 128 && j9Var.G == 1) {
                    g0VarO = j9Var.o(true, p.F);
                    break;
                }
            }
        }
        return (p) g0VarO;
    }
}
