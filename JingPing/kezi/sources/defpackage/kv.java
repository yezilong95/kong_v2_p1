package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class kv extends mg {
    public float g0 = -1.0f;
    public int h0 = -1;
    public int i0 = -1;
    public ag j0 = this.z;
    public int k0 = 0;

    public kv() {
        this.H.clear();
        this.H.add(this.j0);
        int length = this.G.length;
        for (int i = 0; i < length; i++) {
            this.G[i] = this.j0;
        }
    }

    @Override // defpackage.mg
    public final void B(f20 f20Var) {
        if (this.K == null) {
            return;
        }
        ag agVar = this.j0;
        f20Var.getClass();
        int iM = f20.m(agVar);
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
        ag[] agVarArr = this.G;
        int length = agVarArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            agVarArr[i2] = this.j0;
        }
    }

    @Override // defpackage.mg
    public final void a(f20 f20Var) {
        ng ngVar = (ng) this.K;
        if (ngVar == null) {
            return;
        }
        ag agVarH = ngVar.h(ConstraintAnchor$Type.LEFT);
        ag agVarH2 = ngVar.h(ConstraintAnchor$Type.RIGHT);
        mg mgVar = this.K;
        boolean z = mgVar != null && mgVar.J[0] == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
        if (this.k0 == 0) {
            agVarH = ngVar.h(ConstraintAnchor$Type.TOP);
            agVarH2 = ngVar.h(ConstraintAnchor$Type.BOTTOM);
            mg mgVar2 = this.K;
            z = mgVar2 != null && mgVar2.J[1] == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
        }
        if (this.h0 != -1) {
            sj0 sj0VarJ = f20Var.j(this.j0);
            f20Var.e(sj0VarJ, f20Var.j(agVarH), this.h0, 8);
            if (z) {
                f20Var.f(f20Var.j(agVarH2), sj0VarJ, 0, 5);
                return;
            }
            return;
        }
        if (this.i0 != -1) {
            sj0 sj0VarJ2 = f20Var.j(this.j0);
            sj0 sj0VarJ3 = f20Var.j(agVarH2);
            f20Var.e(sj0VarJ2, sj0VarJ3, -this.i0, 8);
            if (z) {
                f20Var.f(sj0VarJ2, f20Var.j(agVarH), 0, 5);
                f20Var.f(sj0VarJ3, sj0VarJ2, 0, 5);
                return;
            }
            return;
        }
        if (this.g0 != -1.0f) {
            sj0 sj0VarJ4 = f20Var.j(this.j0);
            sj0 sj0VarJ5 = f20Var.j(agVarH2);
            float f = this.g0;
            e7 e7VarK = f20Var.k();
            e7VarK.d.i(sj0VarJ4, -1.0f);
            e7VarK.d.i(sj0VarJ5, f);
            f20Var.c(e7VarK);
        }
    }

    @Override // defpackage.mg
    public final boolean b() {
        return true;
    }

    @Override // defpackage.mg
    public final void f(mg mgVar, HashMap map) {
        super.f(mgVar, map);
        kv kvVar = (kv) mgVar;
        this.g0 = kvVar.g0;
        this.h0 = kvVar.h0;
        this.i0 = kvVar.i0;
        C(kvVar.k0);
    }

    @Override // defpackage.mg
    public final ag h(ConstraintAnchor$Type constraintAnchor$Type) {
        switch (jv.a[constraintAnchor$Type.ordinal()]) {
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
