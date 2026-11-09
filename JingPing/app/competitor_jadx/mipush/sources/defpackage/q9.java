package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import io.nats.client.support.JsonUtils;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q9 extends b30 {
    public int i0 = 0;
    public boolean j0 = true;
    public int k0 = 0;

    @Override // defpackage.yh
    public final void a(j90 j90Var) {
        boolean z;
        int i;
        int i2;
        mh[] mhVarArr = this.G;
        mh mhVar = this.y;
        mhVarArr[0] = mhVar;
        mh mhVar2 = this.z;
        int i3 = 2;
        mhVarArr[2] = mhVar2;
        mh mhVar3 = this.A;
        mhVarArr[1] = mhVar3;
        mh mhVar4 = this.B;
        mhVarArr[3] = mhVar4;
        for (mh mhVar5 : mhVarArr) {
            mhVar5.g = j90Var.j(mhVar5);
        }
        int i4 = this.i0;
        if (i4 < 0 || i4 >= 4) {
            return;
        }
        mh mhVar6 = mhVarArr[i4];
        for (int i5 = 0; i5 < this.h0; i5++) {
            yh yhVar = this.g0[i5];
            if ((this.j0 || yhVar.b()) && ((((i2 = this.i0) == 0 || i2 == 1) && yhVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && yhVar.y.d != null && yhVar.A.d != null) || ((i2 == 2 || i2 == 3) && yhVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && yhVar.z.d != null && yhVar.B.d != null))) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = mhVar.e() || mhVar3.e();
        boolean z3 = mhVar2.e() || mhVar4.e();
        int i6 = (z || !(((i = this.i0) == 0 && z2) || ((i == 2 && z3) || ((i == 1 && z2) || (i == 3 && z3))))) ? 4 : 5;
        int i7 = 0;
        while (i7 < this.h0) {
            yh yhVar2 = this.g0[i7];
            if (this.j0 || yhVar2.b()) {
                hw0 hw0VarJ = j90Var.j(yhVar2.G[this.i0]);
                int i8 = this.i0;
                mh mhVar7 = yhVar2.G[i8];
                mhVar7.g = hw0VarJ;
                mh mhVar8 = mhVar7.d;
                int i9 = (mhVar8 == null || mhVar8.b != this) ? 0 : mhVar7.e;
                if (i8 == 0 || i8 == i3) {
                    hw0 hw0Var = mhVar6.g;
                    int i10 = this.k0 - i9;
                    m8 m8VarK = j90Var.k();
                    hw0 hw0VarL = j90Var.l();
                    hw0VarL.d = 0;
                    m8VarK.c(hw0Var, hw0VarJ, hw0VarL, i10);
                    j90Var.c(m8VarK);
                } else {
                    hw0 hw0Var2 = mhVar6.g;
                    int i11 = this.k0 + i9;
                    m8 m8VarK2 = j90Var.k();
                    hw0 hw0VarL2 = j90Var.l();
                    hw0VarL2.d = 0;
                    m8VarK2.b(hw0Var2, hw0VarJ, hw0VarL2, i11);
                    j90Var.c(m8VarK2);
                }
                j90Var.e(mhVar6.g, hw0VarJ, this.k0 + i9, i6);
            }
            i7++;
            i3 = 2;
        }
        int i12 = this.i0;
        if (i12 == 0) {
            j90Var.e(mhVar3.g, mhVar.g, 0, 8);
            j90Var.e(mhVar.g, this.K.A.g, 0, 4);
            j90Var.e(mhVar.g, this.K.y.g, 0, 0);
            return;
        }
        if (i12 == 1) {
            j90Var.e(mhVar.g, mhVar3.g, 0, 8);
            j90Var.e(mhVar.g, this.K.y.g, 0, 4);
            j90Var.e(mhVar.g, this.K.A.g, 0, 0);
        } else if (i12 == 2) {
            j90Var.e(mhVar4.g, mhVar2.g, 0, 8);
            j90Var.e(mhVar2.g, this.K.B.g, 0, 4);
            j90Var.e(mhVar2.g, this.K.z.g, 0, 0);
        } else if (i12 == 3) {
            j90Var.e(mhVar2.g, mhVar4.g, 0, 8);
            j90Var.e(mhVar2.g, this.K.z.g, 0, 4);
            j90Var.e(mhVar2.g, this.K.B.g, 0, 0);
        }
    }

    @Override // defpackage.yh
    public final boolean b() {
        return true;
    }

    @Override // defpackage.b30, defpackage.yh
    public final void f(yh yhVar, HashMap map) {
        super.f(yhVar, map);
        q9 q9Var = (q9) yhVar;
        this.i0 = q9Var.i0;
        this.j0 = q9Var.j0;
        this.k0 = q9Var.k0;
    }

    @Override // defpackage.yh
    public final String toString() {
        String strO = zn.o(new StringBuilder("[Barrier] "), this.Y, " {");
        for (int i = 0; i < this.h0; i++) {
            yh yhVar = this.g0[i];
            if (i > 0) {
                strO = zn.m(strO, ", ");
            }
            StringBuilder sbP = zn.p(strO);
            sbP.append(yhVar.Y);
            strO = sbP.toString();
        }
        return zn.m(strO, JsonUtils.CLOSE);
    }
}
