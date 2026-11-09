package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.b;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cx extends b30 {
    public yh[] Q0;
    public int i0 = 0;
    public int j0 = 0;
    public int k0 = 0;
    public int l0 = 0;
    public int m0 = 0;
    public int n0 = 0;
    public boolean o0 = false;
    public int p0 = 0;
    public int q0 = 0;
    public final ga r0 = new ga();
    public ha s0 = null;
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
    public yh[] N0 = null;
    public yh[] O0 = null;
    public int[] P0 = null;
    public int R0 = 0;

    @Override // defpackage.b30
    public final void D() {
        for (int i = 0; i < this.h0; i++) {
            yh yhVar = this.g0[i];
        }
    }

    public final int E(yh yhVar, int i) {
        yh yhVar2;
        if (yhVar == null) {
            return 0;
        }
        if (yhVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = yhVar.k;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (yhVar.r * i);
                if (i3 != yhVar.j()) {
                    G(yhVar, yhVar.J[0], yhVar.m(), ConstraintWidget$DimensionBehaviour.FIXED, i3);
                }
                return i3;
            }
            yhVar2 = yhVar;
            if (i2 == 1) {
                return yhVar2.j();
            }
            if (i2 == 3) {
                return (int) ((yhVar2.m() * yhVar2.N) + 0.5f);
            }
        } else {
            yhVar2 = yhVar;
        }
        return yhVar2.j();
    }

    public final int F(yh yhVar, int i) {
        yh yhVar2;
        if (yhVar == null) {
            return 0;
        }
        if (yhVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            int i2 = yhVar.j;
            if (i2 == 0) {
                return 0;
            }
            if (i2 == 2) {
                int i3 = (int) (yhVar.o * i);
                if (i3 != yhVar.m()) {
                    G(yhVar, ConstraintWidget$DimensionBehaviour.FIXED, i3, yhVar.J[1], yhVar.j());
                }
                return i3;
            }
            yhVar2 = yhVar;
            if (i2 == 1) {
                return yhVar2.m();
            }
            if (i2 == 3) {
                return (int) ((yhVar2.j() * yhVar2.N) + 0.5f);
            }
        } else {
            yhVar2 = yhVar;
        }
        return yhVar2.m();
    }

    public final void G(yh yhVar, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, int i, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, int i2) {
        ha haVar;
        yh yhVar2;
        while (true) {
            haVar = this.s0;
            if (haVar != null || (yhVar2 = this.K) == null) {
                break;
            } else {
                this.s0 = ((zh) yhVar2).j0;
            }
        }
        ga gaVar = this.r0;
        gaVar.a = constraintWidget$DimensionBehaviour;
        gaVar.b = constraintWidget$DimensionBehaviour2;
        gaVar.c = i;
        gaVar.d = i2;
        ((b) haVar).a(yhVar, gaVar);
        yhVar.z(gaVar.e);
        yhVar.w(gaVar.f);
        yhVar.w = gaVar.h;
        int i3 = gaVar.g;
        yhVar.R = i3;
        yhVar.w = i3 > 0;
    }

    @Override // defpackage.yh
    public final void a(j90 j90Var) {
        yh yhVar;
        super.a(j90Var);
        yh yhVar2 = this.K;
        boolean z = yhVar2 != null ? ((zh) yhVar2).k0 : false;
        int i = this.J0;
        ArrayList arrayList = this.M0;
        if (i != 0) {
            if (i == 1) {
                int size = arrayList.size();
                int i2 = 0;
                while (i2 < size) {
                    ((ax) arrayList.get(i2)).b(i2, z, i2 == size + (-1));
                    i2++;
                }
            } else if (i == 2 && this.P0 != null && this.O0 != null && this.N0 != null) {
                for (int i3 = 0; i3 < this.R0; i3++) {
                    this.Q0[i3].u();
                }
                int[] iArr = this.P0;
                int i4 = iArr[0];
                int i5 = iArr[1];
                yh yhVar3 = null;
                for (int i6 = 0; i6 < i4; i6++) {
                    yh yhVar4 = this.O0[z ? (i4 - i6) - 1 : i6];
                    if (yhVar4 != null && yhVar4.X != 8) {
                        mh mhVar = yhVar4.y;
                        if (i6 == 0) {
                            yhVar4.d(mhVar, this.y, this.m0);
                            yhVar4.Z = this.t0;
                            yhVar4.U = this.z0;
                        }
                        if (i6 == i4 - 1) {
                            yhVar4.d(yhVar4.A, this.A, this.n0);
                        }
                        if (i6 > 0) {
                            yhVar4.d(mhVar, yhVar3.A, this.F0);
                            yhVar3.d(yhVar3.A, mhVar, 0);
                        }
                        yhVar3 = yhVar4;
                    }
                }
                for (int i7 = 0; i7 < i5; i7++) {
                    yh yhVar5 = this.N0[i7];
                    if (yhVar5 != null && yhVar5.X != 8) {
                        mh mhVar2 = yhVar5.z;
                        if (i7 == 0) {
                            yhVar5.d(mhVar2, this.z, this.i0);
                            yhVar5.a0 = this.u0;
                            yhVar5.V = this.A0;
                        }
                        if (i7 == i5 - 1) {
                            yhVar5.d(yhVar5.B, this.B, this.j0);
                        }
                        if (i7 > 0) {
                            yhVar5.d(mhVar2, yhVar3.B, this.G0);
                            yhVar3.d(yhVar3.B, mhVar2, 0);
                        }
                        yhVar3 = yhVar5;
                    }
                }
                for (int i8 = 0; i8 < i4; i8++) {
                    for (int i9 = 0; i9 < i5; i9++) {
                        int i10 = (i9 * i4) + i8;
                        if (this.L0 == 1) {
                            i10 = (i8 * i5) + i9;
                        }
                        yh[] yhVarArr = this.Q0;
                        if (i10 < yhVarArr.length && (yhVar = yhVarArr[i10]) != null && yhVar.X != 8) {
                            yh yhVar6 = this.O0[i8];
                            yh yhVar7 = this.N0[i9];
                            if (yhVar != yhVar6) {
                                yhVar.d(yhVar.y, yhVar6.y, 0);
                                yhVar.d(yhVar.A, yhVar6.A, 0);
                            }
                            if (yhVar != yhVar7) {
                                yhVar.d(yhVar.z, yhVar7.z, 0);
                                yhVar.d(yhVar.B, yhVar7.B, 0);
                            }
                        }
                    }
                }
            }
        } else if (arrayList.size() > 0) {
            ((ax) arrayList.get(0)).b(0, z, true);
        }
        this.o0 = false;
    }

    @Override // defpackage.b30, defpackage.yh
    public final void f(yh yhVar, HashMap map) {
        super.f(yhVar, map);
        cx cxVar = (cx) yhVar;
        this.t0 = cxVar.t0;
        this.u0 = cxVar.u0;
        this.v0 = cxVar.v0;
        this.w0 = cxVar.w0;
        this.x0 = cxVar.x0;
        this.y0 = cxVar.y0;
        this.z0 = cxVar.z0;
        this.A0 = cxVar.A0;
        this.B0 = cxVar.B0;
        this.C0 = cxVar.C0;
        this.D0 = cxVar.D0;
        this.E0 = cxVar.E0;
        this.F0 = cxVar.F0;
        this.G0 = cxVar.G0;
        this.H0 = cxVar.H0;
        this.I0 = cxVar.I0;
        this.J0 = cxVar.J0;
        this.K0 = cxVar.K0;
        this.L0 = cxVar.L0;
    }
}
