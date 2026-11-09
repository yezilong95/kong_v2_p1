package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class v7 extends vv {
    public int i0 = 0;
    public boolean j0 = true;
    public int k0 = 0;

    @Override // defpackage.mg
    public final void a(f20 f20Var) {
        boolean z;
        int i;
        int i2;
        ag[] agVarArr = this.G;
        ag agVar = this.y;
        agVarArr[0] = agVar;
        ag agVar2 = this.z;
        int i3 = 2;
        agVarArr[2] = agVar2;
        ag agVar3 = this.A;
        agVarArr[1] = agVar3;
        ag agVar4 = this.B;
        agVarArr[3] = agVar4;
        for (ag agVar5 : agVarArr) {
            agVar5.g = f20Var.j(agVar5);
        }
        int i4 = this.i0;
        if (i4 < 0 || i4 >= 4) {
            return;
        }
        ag agVar6 = agVarArr[i4];
        for (int i5 = 0; i5 < this.h0; i5++) {
            mg mgVar = this.g0[i5];
            if ((this.j0 || mgVar.b()) && ((((i2 = this.i0) == 0 || i2 == 1) && mgVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && mgVar.y.d != null && mgVar.A.d != null) || ((i2 == 2 || i2 == 3) && mgVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && mgVar.z.d != null && mgVar.B.d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = agVar.e() || agVar3.e();
        boolean z3 = agVar2.e() || agVar4.e();
        int i6 = (z || !(((i = this.i0) == 0 && z2) || ((i == 2 && z3) || ((i == 1 && z2) || (i == 3 && z3))))) ? 4 : 5;
        int i7 = 0;
        while (i7 < this.h0) {
            mg mgVar2 = this.g0[i7];
            if (this.j0 || mgVar2.b()) {
                sj0 sj0VarJ = f20Var.j(mgVar2.G[this.i0]);
                int i8 = this.i0;
                ag agVar7 = mgVar2.G[i8];
                agVar7.g = sj0VarJ;
                ag agVar8 = agVar7.d;
                int i9 = (agVar8 == null || agVar8.b != this) ? 0 : agVar7.e;
                if (i8 == 0 || i8 == i3) {
                    sj0 sj0Var = agVar6.g;
                    int i10 = this.k0 - i9;
                    e7 e7VarK = f20Var.k();
                    sj0 sj0VarL = f20Var.l();
                    sj0VarL.d = 0;
                    e7VarK.c(sj0Var, sj0VarJ, sj0VarL, i10);
                    f20Var.c(e7VarK);
                } else {
                    sj0 sj0Var2 = agVar6.g;
                    int i11 = this.k0 + i9;
                    e7 e7VarK2 = f20Var.k();
                    sj0 sj0VarL2 = f20Var.l();
                    sj0VarL2.d = 0;
                    e7VarK2.b(sj0Var2, sj0VarJ, sj0VarL2, i11);
                    f20Var.c(e7VarK2);
                }
                f20Var.e(agVar6.g, sj0VarJ, this.k0 + i9, i6);
            }
            i7++;
            i3 = 2;
        }
        int i12 = this.i0;
        if (i12 == 0) {
            f20Var.e(agVar3.g, agVar.g, 0, 8);
            f20Var.e(agVar.g, this.K.A.g, 0, 4);
            f20Var.e(agVar.g, this.K.y.g, 0, 0);
            return;
        }
        if (i12 == 1) {
            f20Var.e(agVar.g, agVar3.g, 0, 8);
            f20Var.e(agVar.g, this.K.y.g, 0, 4);
            f20Var.e(agVar.g, this.K.A.g, 0, 0);
        } else if (i12 == 2) {
            f20Var.e(agVar4.g, agVar2.g, 0, 8);
            f20Var.e(agVar2.g, this.K.B.g, 0, 4);
            f20Var.e(agVar2.g, this.K.z.g, 0, 0);
        } else if (i12 == 3) {
            f20Var.e(agVar2.g, agVar4.g, 0, 8);
            f20Var.e(agVar2.g, this.K.z.g, 0, 4);
            f20Var.e(agVar2.g, this.K.B.g, 0, 0);
        }
    }

    @Override // defpackage.mg
    public final boolean b() {
        return true;
    }

    @Override // defpackage.vv, defpackage.mg
    public final void f(mg mgVar, HashMap map) {
        super.f(mgVar, map);
        v7 v7Var = (v7) mgVar;
        this.i0 = v7Var.i0;
        this.j0 = v7Var.j0;
        this.k0 = v7Var.k0;
    }

    @Override // defpackage.mg
    public final String toString() {
        String strK = hk.k(new StringBuilder("[Barrier] "), this.Y, " {");
        for (int i = 0; i < this.h0; i++) {
            mg mgVar = this.g0[i];
            if (i > 0) {
                strK = hk.i(strK, ", ");
            }
            StringBuilder sbL = hk.l(strK);
            sbL.append(mgVar.Y);
            strK = sbL.toString();
        }
        return hk.i(strK, "}");
    }
}
