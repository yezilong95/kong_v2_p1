package defpackage;

import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class sx0 extends a0 {
    public l4 E;
    public p F;

    public sx0(l4 l4Var, p pVar) {
        this.F = pVar;
        this.E = l4Var;
    }

    public static sx0 g(Object obj) {
        if (obj instanceof sx0) {
            return (sx0) obj;
        }
        if (obj == null) {
            return null;
        }
        j0 j0VarQ = j0.q(obj);
        sx0 sx0Var = new sx0();
        if (j0VarQ.size() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + j0VarQ.size());
        }
        Enumeration enumerationS = j0VarQ.s();
        sx0Var.E = l4.g(enumerationS.nextElement());
        sx0Var.F = p.q(enumerationS.nextElement());
        return sx0Var;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        uVar.c(this.E);
        uVar.c(this.F);
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }

    public sx0(l4 l4Var, a0 a0Var) {
        this.F = new lk(a0Var.b().f(), 0);
        this.E = l4Var;
    }

    public sx0(l4 l4Var, byte[] bArr) {
        this.F = new lk(bArr, 0);
        this.E = l4Var;
    }
}
