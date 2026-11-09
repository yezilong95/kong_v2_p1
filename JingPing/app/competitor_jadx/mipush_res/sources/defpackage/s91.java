package defpackage;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class s91 extends a0 implements w91 {
    public static final BigInteger K = BigInteger.valueOf(1);
    public final v91 E;
    public xq F;
    public u91 G;
    public BigInteger H;
    public BigInteger I;
    public byte[] J;

    public s91(xq xqVar, u91 u91Var, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.F = xqVar;
        this.G = u91Var;
        this.H = bigInteger;
        this.I = bigInteger2;
        this.J = z91.l(bArr);
        boolean z = xqVar.a.a() == 1;
        ew ewVar = xqVar.a;
        if (z) {
            BigInteger bigIntegerB = ewVar.b();
            v91 v91Var = new v91();
            v91Var.E = w91.a;
            v91Var.F = new y(bigIntegerB);
            this.E = v91Var;
            return;
        }
        if (ewVar.a() <= 1 || !ewVar.b().equals(qq.c) || !(ewVar instanceof d20)) {
            throw new IllegalArgumentException("'curve' is of an unsupported type");
        }
        int[] iArr = ((d20) ewVar).b.a;
        int[] iArr2 = iArr == null ? null : (int[]) iArr.clone();
        if (iArr2.length == 3) {
            this.E = new v91(iArr2[2], iArr2[1], 0, 0);
        } else {
            if (iArr2.length != 5) {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            }
            this.E = new v91(iArr2[4], iArr2[1], iArr2[2], iArr2[3]);
        }
    }

    public static s91 g(g0 g0Var) {
        v91 v91Var;
        int iT;
        int i;
        int iT2;
        xq vqVar;
        j0 j0Var;
        if (g0Var == null) {
            return null;
        }
        j0 j0VarQ = j0.q(g0Var);
        s91 s91Var = new s91();
        if (j0VarQ.r(0) instanceof y) {
            y yVar = (y) j0VarQ.r(0);
            byte[] bArr = yVar.E;
            int length = bArr.length;
            int i2 = yVar.F;
            if (length - i2 <= 4 && y.s(bArr, i2, -1) == 1) {
                BigInteger bigIntegerQ = ((y) j0VarQ.r(4)).q();
                s91Var.H = bigIntegerQ;
                if (j0VarQ.size() == 6) {
                    s91Var.I = ((y) j0VarQ.r(5)).q();
                }
                t tVarR = j0VarQ.r(1);
                if (tVarR instanceof v91) {
                    v91Var = (v91) tVarR;
                } else if (tVarR != null) {
                    j0 j0VarQ2 = j0.q(tVarR);
                    v91 v91Var2 = new v91();
                    v91Var2.E = d0.s(j0VarQ2.r(0));
                    v91Var2.F = j0VarQ2.r(1).b();
                    v91Var = v91Var2;
                } else {
                    v91Var = null;
                }
                BigInteger bigInteger = s91Var.I;
                j0 j0VarQ3 = j0.q(j0VarQ.r(2));
                d0 d0Var = v91Var.E;
                boolean zK = d0Var.k(w91.a);
                g0 g0Var2 = v91Var.F;
                if (zK) {
                    vqVar = new wq(((y) g0Var2).q(), new BigInteger(1, e0.o(j0VarQ3.r(0)).E), new BigInteger(1, e0.o(j0VarQ3.r(1)).E), bigIntegerQ, bigInteger, false);
                    j0Var = j0VarQ3;
                } else {
                    if (!d0Var.k(w91.b)) {
                        throw new IllegalArgumentException("This type of ECCurve is not implemented");
                    }
                    j0 j0VarQ4 = j0.q(g0Var2);
                    int iT3 = ((y) j0VarQ4.r(0)).t();
                    d0 d0Var2 = (d0) j0VarQ4.r(1);
                    if (d0Var2.k(w91.c)) {
                        i = 0;
                        iT2 = y.o(j0VarQ4.r(2)).t();
                        iT = 0;
                    } else {
                        if (!d0Var2.k(w91.d)) {
                            throw new IllegalArgumentException("This type of EC basis is not implemented");
                        }
                        j0 j0VarQ5 = j0.q(j0VarQ4.r(2));
                        int iT4 = y.o(j0VarQ5.r(0)).t();
                        int iT5 = y.o(j0VarQ5.r(1)).t();
                        iT = y.o(j0VarQ5.r(2)).t();
                        i = iT5;
                        iT2 = iT4;
                    }
                    j0Var = j0VarQ3;
                    vqVar = new vq(iT3, iT2, i, iT, new BigInteger(1, e0.o(j0VarQ3.r(0)).E), new BigInteger(1, e0.o(j0VarQ3.r(1)).E), bigIntegerQ, bigInteger);
                }
                byte[] bArrP = j0Var.size() == 3 ? ((lk) j0Var.r(2)).p() : null;
                s91Var.F = vqVar;
                t tVarR2 = j0VarQ.r(3);
                if (tVarR2 instanceof u91) {
                    s91Var.G = (u91) tVarR2;
                } else {
                    s91Var.G = new u91(vqVar, ((e0) tVarR2).E);
                }
                s91Var.J = z91.l(bArrP);
                return s91Var;
            }
        }
        throw new IllegalArgumentException("bad version in X9ECParameters");
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        d0 d0Var;
        u uVar = new u(6);
        uVar.c(new y(K));
        uVar.c(this.E);
        r91 r91Var = new r91();
        r91Var.G = null;
        xq xqVar = this.F;
        r91Var.E = xqVar;
        r91Var.F = z91.l(this.J);
        if (xqVar.a.a() == 1) {
            d0Var = w91.a;
        } else {
            ew ewVar = xqVar.a;
            if (ewVar.a() <= 1 || !ewVar.b().equals(qq.c) || !(ewVar instanceof d20)) {
                throw new IllegalArgumentException("This type of ECCurve is not implemented");
            }
            d0Var = w91.b;
        }
        r91Var.G = d0Var;
        uVar.c(r91Var);
        uVar.c(this.G);
        uVar.c(new y(this.H));
        BigInteger bigInteger = this.I;
        if (bigInteger != null) {
            uVar.c(new y(bigInteger));
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
