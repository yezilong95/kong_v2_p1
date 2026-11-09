package defpackage;

import java.math.BigInteger;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class yl extends a0 {
    public y E;
    public y F;
    public y G;

    public yl(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.E = new y(bigInteger);
        this.F = new y(bigInteger2);
        this.G = new y(bigInteger3);
    }

    public static yl g(Object obj) {
        if (obj instanceof yl) {
            return (yl) obj;
        }
        if (obj == null) {
            return null;
        }
        j0 j0VarQ = j0.q(obj);
        yl ylVar = new yl();
        if (j0VarQ.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + j0VarQ.size());
        }
        Enumeration enumerationS = j0VarQ.s();
        ylVar.E = y.o(enumerationS.nextElement());
        ylVar.F = y.o(enumerationS.nextElement());
        ylVar.G = y.o(enumerationS.nextElement());
        return ylVar;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(3);
        uVar.c(this.E);
        uVar.c(this.F);
        uVar.c(this.G);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
