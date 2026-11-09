package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.b;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class pq extends vv {
    public mg[] Q0;
    public int i0 = 0;
    public int j0 = 0;
    public int k0 = 0;
    public int l0 = 0;
    public int m0 = 0;
    public int n0 = 0;
    public boolean o0 = false;
    public int p0 = 0;
    public int q0 = 0;
    public final l8 r0 = new l8();
    public m8 s0 = null;
    public int t0 = -1;
    public int u0 = -1;
    public int v0 = -1;
    public int w0 = -1;
    public int x0 = -1;
    public int y0 = -1;
    public float z0 = 0.5f;
    public float A0 = 0.5f;
    public float B0 = 0.5f;
    public float C0 = 0.5f;
    public float D0 = 0.5f;
    public float E0 = 0.5f;
    public int F0 = 0;
    public int G0 = 0;
    public int H0 = 2;
    public int I0 = 2;
    public int J0 = 0;
    public int K0 = -1;
    public int L0 = 0;
    public final ArrayList M0 = new ArrayList();
    public mg[] N0 = null;
    public mg[] O0 = null;
    public int[] P0 = null;
    public int R0 = 0;

    @Override // defpackage.vv
    public final void D() {
        for (int i = 0; i < this.h0; i++) {
            mg mgVar = this.g0[i];
        }
    }

    public final int E(mg mgVar, int i) {
        mg mgVar2;
        if (mgVar == null) {
            return 0;
        }
        if (mgVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = mgVar.k;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (mgVar.r * i);
                if (i3 != mgVar.j()) {
                    G(mgVar, mgVar.J[0], mgVar.m(), ConstraintWidget$DimensionBehaviour.FIXED, i3);
                }
                return i3;
            }
            mgVar2 = mgVar;
            if (i2 == 1) {
                return mgVar2.j();
            }
            if (i2 == 3) {
                return (int) ((mgVar2.m() * mgVar2.N) + 0.5f);
            }
        } else {
            mgVar2 = mgVar;
        }
        return mgVar2.j();
    }

    public final int F(mg mgVar, int i) {
        mg mgVar2;
        if (mgVar == null) {
            return 0;
        }
        if (mgVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = mgVar.j;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (mgVar.o * i);
                if (i3 != mgVar.m()) {
                    G(mgVar, ConstraintWidget$DimensionBehaviour.FIXED, i3, mgVar.J[1], mgVar.j());
                }
                return i3;
            }
            mgVar2 = mgVar;
            if (i2 == 1) {
                return mgVar2.m();
            }
            if (i2 == 3) {
                return (int) ((mgVar2.j() * mgVar2.N) + 0.5f);
            }
        } else {
            mgVar2 = mgVar;
        }
        return mgVar2.m();
    }

    public final void G(mg mgVar, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, int i, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, int i2) {
        m8 m8Var;
        mg mgVar2;
        while (true) {
            m8Var = this.s0;
            if (m8Var != null || (mgVar2 = this.K) == null) {
                break;
            } else {
                this.s0 = ((ng) mgVar2).j0;
            }
        }
        l8 l8Var = this.r0;
        l8Var.a = constraintWidget$DimensionBehaviour;
        l8Var.b = constraintWidget$DimensionBehaviour2;
        l8Var.c = i;
        l8Var.d = i2;
        ((b) m8Var).a(mgVar, l8Var);
        mgVar.z(l8Var.e);
        mgVar.w(l8Var.f);
        mgVar.w = l8Var.h;
        int i3 = l8Var.g;
        mgVar.R = i3;
        mgVar.w = i3 > 0;
    }

    @Override // defpackage.mg
    public final void a(f20 f20Var) {
        mg mgVar;
        super.a(f20Var);
        mg mgVar2 = this.K;
        boolean z = mgVar2 != null ? ((ng) mgVar2).k0 : false;
        int i = this.J0;
        ArrayList arrayList = this.M0;
        if (i != 0) {
            if (i == 1) {
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    ((nq) arrayList.get(i2)).b(i2, z, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2 && this.P0 != null && this.O0 != null && this.N0 != null) {
                for (int i3 = 0; i3 < this.R0; i3++) {
                    this.Q0[i3].u();
                }
                int[] iArr = this.P0;
                int i4 = iArr[0];
                int i5 = iArr[1];
                mg mgVar3 = null;
                for (int i6 = 0; i6 < i4; i6++) {
                    mg mgVar4 = this.O0[z ? (i4 - i6) - 1 : i6];
                    if (mgVar4 != null && mgVar4.X != 8) {
                        ag agVar = mgVar4.y;
                        if (i6 == 0) {
                            mgVar4.d(agVar, this.y, this.m0);
                            mgVar4.Z = this.t0;
                            mgVar4.U = this.z0;
                        }
                        if (i6 == i4 - 1) {
                            mgVar4.d(mgVar4.A, this.A, this.n0);
                        }
                        if (i6 > 0) {
                            mgVar4.d(agVar, mgVar3.A, this.F0);
                            mgVar3.d(mgVar3.A, agVar, 0);
                        }
                        mgVar3 = mgVar4;
                    }
                }
                for (int i7 = 0; i7 < i5; i7++) {
                    mg mgVar5 = this.N0[i7];
                    if (mgVar5 != null && mgVar5.X != 8) {
                        ag agVar2 = mgVar5.z;
                        if (i7 == 0) {
                            mgVar5.d(agVar2, this.z, this.i0);
                            mgVar5.a0 = this.u0;
                            mgVar5.V = this.A0;
                        }
                        if (i7 == i5 - 1) {
                            mgVar5.d(mgVar5.B, this.B, this.j0);
                        }
                        if (i7 > 0) {
                            mgVar5.d(agVar2, mgVar3.B, this.G0);
                            mgVar3.d(mgVar3.B, agVar2, 0);
                        }
                        mgVar3 = mgVar5;
                    }
                }
                for (int i8 = 0; i8 < i4; i8++) {
                    for (int i9 = 0; i9 < i5; i9++) {
                        int i10 = (i9 * i4) + i8;
                        if (this.L0 == 1) {
                            i10 = (i8 * i5) + i9;
                        }
                        mg[] mgVarArr = this.Q0;
                        if (i10 < mgVarArr.length && (mgVar = mgVarArr[i10]) != null && mgVar.X != 8) {
                            mg mgVar6 = this.O0[i8];
                            mg mgVar7 = this.N0[i9];
                            if (mgVar != mgVar6) {
                                mgVar.d(mgVar.y, mgVar6.y, 0);
                                mgVar.d(mgVar.A, mgVar6.A, 0);
                            }
                            if (mgVar != mgVar7) {
                                mgVar.d(mgVar.z, mgVar7.z, 0);
                                mgVar.d(mgVar.B, mgVar7.B, 0);
                            }
                        }
                    }
                }
            }
        } else if (arrayList.size() > 0) {
            ((nq) arrayList.get(0)).b(0, z, true);
        }
        this.o0 = false;
    }

    @Override // defpackage.vv, defpackage.mg
    public final void f(mg mgVar, HashMap map) {
        super.f(mgVar, map);
        pq pqVar = (pq) mgVar;
        this.t0 = pqVar.t0;
        this.u0 = pqVar.u0;
        this.v0 = pqVar.v0;
        this.w0 = pqVar.w0;
        this.x0 = pqVar.x0;
        this.y0 = pqVar.y0;
        this.z0 = pqVar.z0;
        this.A0 = pqVar.A0;
        this.B0 = pqVar.B0;
        this.C0 = pqVar.C0;
        this.D0 = pqVar.D0;
        this.E0 = pqVar.E0;
        this.F0 = pqVar.F0;
        this.G0 = pqVar.G0;
        this.H0 = pqVar.H0;
        this.I0 = pqVar.I0;
        this.J0 = pqVar.J0;
        this.K0 = pqVar.K0;
        this.L0 = pqVar.L0;
    }
}
