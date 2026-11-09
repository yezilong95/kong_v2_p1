package defpackage;

import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class fl extends a0 {
    public y E;
    public y F;
    public y G;

    public fl(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.E = new y(bigInteger);
        this.F = new y(bigInteger2);
        this.G = i != 0 ? new y(i) : null;
    }

    public static fl g(j0 j0Var) {
        if (j0Var == null) {
            return null;
        }
        j0 j0VarQ = j0.q(j0Var);
        fl flVar = new fl();
        Enumeration enumerationS = j0VarQ.s();
        flVar.E = y.o(enumerationS.nextElement());
        flVar.F = y.o(enumerationS.nextElement());
        if (enumerationS.hasMoreElements()) {
            flVar.G = (y) enumerationS.nextElement();
            return flVar;
        }
        flVar.G = null;
        return flVar;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(3);
        uVar.c(this.E);
        uVar.c(this.F);
        if (h() != null) {
            uVar.c(this.G);
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }

    public final BigInteger h() {
        y yVar = this.G;
        if (yVar == null) {
            return null;
        }
        return yVar.p();
    }
}
