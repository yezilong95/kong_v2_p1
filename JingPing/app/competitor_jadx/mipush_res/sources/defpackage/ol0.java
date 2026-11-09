package defpackage;

import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public final class ol0 extends a0 {
    public y E;
    public l4 F;
    public e0 G;
    public k0 H;
    public p I;

    public ol0(l4 l4Var, a0 a0Var, k0 k0Var, byte[] bArr) {
        this.E = new y(bArr != null ? oa.b : oa.a);
        this.F = l4Var;
        this.G = new tk(a0Var.b().f());
        this.H = k0Var;
        this.I = bArr == null ? null : new lk(bArr, 0);
    }

    public static ol0 g(Object obj) {
        if (obj instanceof ol0) {
            return (ol0) obj;
        }
        if (obj == null) {
            return null;
        }
        j0 j0VarQ = j0.q(obj);
        ol0 ol0Var = new ol0();
        Enumeration enumerationS = j0VarQ.s();
        y yVarO = y.o(enumerationS.nextElement());
        ol0Var.E = yVarO;
        int iT = yVarO.t();
        if (iT < 0 || iT > 1) {
            throw new IllegalArgumentException("invalid version for private key info");
        }
        ol0Var.F = l4.g(enumerationS.nextElement());
        ol0Var.G = e0.o(enumerationS.nextElement());
        int i = -1;
        while (enumerationS.hasMoreElements()) {
            j9 j9Var = (j9) enumerationS.nextElement();
            int i2 = j9Var.G;
            if (i2 <= i) {
                throw new IllegalArgumentException("invalid optional field in private key info");
            }
            if (i2 == 0) {
                o oVar = k0.G;
                t40.j(j9Var);
                g0 g0VarO = j9Var.o(false, oVar);
                oVar.a(g0VarO);
                ol0Var.H = (k0) g0VarO;
            } else {
                if (i2 != 1) {
                    throw new IllegalArgumentException("unknown optional field in private key info");
                }
                if (iT < 1) {
                    throw new IllegalArgumentException("'publicKey' requires version v2(1) or later");
                }
                o oVar2 = p.F;
                t40.j(j9Var);
                g0 g0VarO2 = j9Var.o(false, oVar2);
                oVar2.a(g0VarO2);
                ol0Var.I = (p) g0VarO2;
            }
            i = i2;
        }
        return ol0Var;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(5);
        uVar.c(this.E);
        uVar.c(this.F);
        uVar.c(this.G);
        k0 k0Var = this.H;
        if (k0Var != null) {
            uVar.c(new j9(false, 0, (t) k0Var, 1));
        }
        p pVar = this.I;
        if (pVar != null) {
            uVar.c(new j9(false, 1, (t) pVar, 1));
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }

    public final tk h() {
        return new tk(this.G.E);
    }

    public final g0 i() {
        return g0.l(this.G.E);
    }
}
