package defpackage;

import io.nats.client.support.NatsConstants;
import java.math.BigInteger;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class gm extends a0 {
    public static final byte[] H = {-87, -42, -21, 69, -15, 60, 112, -126, -128, -60, -106, 123, 35, 31, 94, -83, -10, 88, -21, -92, -64, 55, 41, 29, 56, -39, 107, -16, 37, -54, 78, 23, -8, -23, 114, NatsConstants.CR, -58, 21, -76, NatsConstants.COLON, 40, -105, 95, 11, -63, -34, -93, 100, 56, -75, 100, -22, 44, 23, -97, -48, 18, 62, 109, -72, -6, -59, 121, 4};
    public final d0 E;
    public em F;
    public byte[] G = H;

    public gm(d0 d0Var) {
        this.E = d0Var;
    }

    public static gm g(j0 j0Var) {
        gm gmVar;
        em emVar;
        int i;
        j0 j0VarQ = j0.q(j0Var);
        if (j0VarQ.r(0) instanceof d0) {
            gmVar = new gm(d0.s(j0VarQ.r(0)));
        } else {
            t tVarR = j0VarQ.r(0);
            if (tVarR instanceof em) {
                emVar = (em) tVarR;
            } else {
                dm dmVar = null;
                if (tVarR != null) {
                    j0 j0VarQ2 = j0.q(tVarR);
                    em emVar2 = new em();
                    emVar2.E = BigInteger.valueOf(0L);
                    if (j0VarQ2.r(0) instanceof j9) {
                        j9 j9Var = (j9) j0VarQ2.r(0);
                        if (!j9Var.q() || j9Var.G != 0) {
                            throw new IllegalArgumentException("object parse error");
                        }
                        emVar2.E = y.o(j9Var).q();
                        i = 1;
                    } else {
                        i = 0;
                    }
                    t tVarR2 = j0VarQ2.r(i);
                    if (tVarR2 instanceof dm) {
                        dmVar = (dm) tVarR2;
                    } else if (tVarR2 != null) {
                        j0 j0VarQ3 = j0.q(tVarR2);
                        dmVar = new dm();
                        dmVar.E = y.o(j0VarQ3.r(0)).r();
                        if (j0VarQ3.r(1) instanceof y) {
                            dmVar.F = ((y) j0VarQ3.r(1)).r();
                        } else {
                            if (!(j0VarQ3.r(1) instanceof j0)) {
                                throw new IllegalArgumentException("object parse error");
                            }
                            j0 j0VarQ4 = j0.q(j0VarQ3.r(1));
                            dmVar.F = y.o(j0VarQ4.r(0)).r();
                            dmVar.G = y.o(j0VarQ4.r(1)).r();
                            dmVar.H = y.o(j0VarQ4.r(2)).r();
                        }
                    }
                    emVar2.F = dmVar;
                    emVar2.G = y.o(j0VarQ2.r(i + 1));
                    emVar2.H = e0.o(j0VarQ2.r(i + 2));
                    emVar2.I = y.o(j0VarQ2.r(i + 3));
                    emVar2.J = e0.o(j0VarQ2.r(i + 4));
                    emVar = emVar2;
                } else {
                    emVar = null;
                }
            }
            gmVar = new gm();
            gmVar.G = H;
            gmVar.F = emVar;
        }
        if (j0VarQ.size() == 2) {
            byte[] bArr = e0.o(j0VarQ.r(1)).E;
            gmVar.G = bArr;
            if (bArr.length != 64) {
                throw new IllegalArgumentException("object parse error");
            }
        }
        return gmVar;
    }

    @Override // defpackage.a0, defpackage.t
    public final g0 b() {
        u uVar = new u(2);
        t tVar = this.E;
        if (tVar == null) {
            tVar = this.F;
        }
        uVar.c(tVar);
        if (!Arrays.equals(this.G, H)) {
            uVar.c(new tk(this.G));
        }
        wk wkVar = new wk(uVar, 0);
        wkVar.H = -1;
        return wkVar;
    }
}
