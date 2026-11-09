package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class o20 extends yh {
    public float g0 = -1.0f;
    public int h0 = -1;
    public int i0 = -1;
    public mh j0 = this.z;
    public int k0 = 0;

    public o20() {
        this.H.clear();
        this.H.add(this.j0);
        int length = this.G.length;
        for (int i = 0; i < length; i++) {
            this.G[i] = this.j0;
        }
    }

    @Override // defpackage.yh
    public final void B(j90 j90Var) {
        if (this.K == null) {
            return;
        }
        mh mhVar = this.j0;
        j90Var.getClass();
        int iM = j90.m(mhVar);
        if (this.k0 == 1) {
            this.P = iM;
            this.Q = 0;
            w(this.K.j());
            z(0);
            return;
        }
        this.P = 0;
        this.Q = iM;
        z(this.K.m());
        w(0);
    }

    public final void C(int i) {
        if (this.k0 == i) {
            return;
        }
        this.k0 = i;
        ArrayList arrayList = this.H;
        arrayList.clear();
        if (this.k0 == 1) {
            this.j0 = this.y;
        } else {
            this.j0 = this.z;
        }
        arrayList.add(this.j0);
        mh[] mhVarArr = this.G;
        int length = mhVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            mhVarArr[i2] = this.j0;
        }
    }

    @Override // defpackage.yh
    public final void a(j90 j90Var) {
        zh zhVar = (zh) this.K;
        if (zhVar == null) {
            return;
        }
        mh mhVarH = zhVar.h(ConstraintAnchor$Type.LEFT);
        mh mhVarH2 = zhVar.h(ConstraintAnchor$Type.RIGHT);
        yh yhVar = this.K;
        boolean z = yhVar != null && yhVar.J[0] == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
        if (this.k0 == 0) {
            mhVarH = zhVar.h(ConstraintAnchor$Type.TOP);
            mhVarH2 = zhVar.h(ConstraintAnchor$Type.BOTTOM);
            yh yhVar2 = this.K;
            z = yhVar2 != null && yhVar2.J[1] == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.h0 != -1) {
            hw0 hw0VarJ = j90Var.j(this.j0);
            j90Var.e(hw0VarJ, j90Var.j(mhVarH), this.h0, 8);
            if (z) {
                j90Var.f(j90Var.j(mhVarH2), hw0VarJ, 0, 5);
                return;
            }
            return;
        }
        if (this.i0 != -1) {
            hw0 hw0VarJ2 = j90Var.j(this.j0);
            hw0 hw0VarJ3 = j90Var.j(mhVarH2);
            j90Var.e(hw0VarJ2, hw0VarJ3, -this.i0, 8);
            if (z) {
                j90Var.f(hw0VarJ2, j90Var.j(mhVarH), 0, 5);
                j90Var.f(hw0VarJ3, hw0VarJ2, 0, 5);
                return;
            }
            return;
        }
        if (this.g0 != -1.0f) {
            hw0 hw0VarJ4 = j90Var.j(this.j0);
            hw0 hw0VarJ5 = j90Var.j(mhVarH2);
            float f = this.g0;
            m8 m8VarK = j90Var.k();
            m8VarK.d.j(hw0VarJ4, -1.0f);
            m8VarK.d.j(hw0VarJ5, f);
            j90Var.c(m8VarK);
        }
    }

    @Override // defpackage.yh
    public final boolean b() {
        return true;
    }

    @Override // defpackage.yh
    public final void f(yh yhVar, HashMap map) {
        super.f(yhVar, map);
        o20 o20Var = (o20) yhVar;
        this.g0 = o20Var.g0;
        this.h0 = o20Var.h0;
        this.i0 = o20Var.i0;
        C(o20Var.k0);
    }

    @Override // defpackage.yh
    public final mh h(ConstraintAnchor$Type constraintAnchor$Type) {
        switch (n20.a[constraintAnchor$Type.ordinal()]) {
            case 1:
            case 2:
                if (this.k0 == 1) {
                    return this.j0;
                }
                break;
            case 3:
            case 4:
                if (this.k0 == 0) {
                    return this.j0;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return null;
        }
        throw new AssertionError(constraintAnchor$Type.name());
    }
}
