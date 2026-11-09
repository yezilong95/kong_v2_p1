package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class nq {
    public int a;
    public ag d;
    public ag e;
    public ag f;
    public ag g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int q;
    public final /* synthetic */ pq r;
    public mg b = null;
    public int c = 0;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public int p = 0;

    public nq(pq pqVar, int i, ag agVar, ag agVar2, ag agVar3, ag agVar4, int i2) {
        this.r = pqVar;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.q = 0;
        this.a = i;
        this.d = agVar;
        this.e = agVar2;
        this.f = agVar3;
        this.g = agVar4;
        this.h = pqVar.m0;
        this.i = pqVar.i0;
        this.j = pqVar.n0;
        this.k = pqVar.j0;
        this.q = i2;
    }

    public final void a(mg mgVar) {
        int i = this.a;
        pq pqVar = this.r;
        if (i == 0) {
            int iF = pqVar.F(mgVar, this.q);
            if (mgVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                this.p++;
                iF = 0;
            }
            this.l = iF + (mgVar.X != 8 ? pqVar.F0 : 0) + this.l;
            int iE = pqVar.E(mgVar, this.q);
            if (this.b == null || this.c < iE) {
                this.b = mgVar;
                this.c = iE;
                this.m = iE;
            }
        } else {
            int iF2 = pqVar.F(mgVar, this.q);
            int iE2 = pqVar.E(mgVar, this.q);
            if (mgVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                this.p++;
                iE2 = 0;
            }
            this.m = iE2 + (mgVar.X != 8 ? pqVar.G0 : 0) + this.m;
            if (this.b == null || this.c < iF2) {
                this.b = mgVar;
                this.c = iF2;
                this.l = iF2;
            }
        }
        this.o++;
    }

    public final void b(int i, boolean z, boolean z2) {
        pq pqVar;
        int i2;
        mg mgVar;
        char c;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.o;
        int i8 = 0;
        while (true) {
            pqVar = this.r;
            if (i8 >= i7 || (i6 = this.n + i8) >= pqVar.R0) {
                break;
            }
            mg mgVar2 = pqVar.Q0[i6];
            if (mgVar2 != null) {
                mgVar2.u();
            }
            i8++;
        }
        if (i7 == 0 || this.b == null) {
            return;
        }
        boolean z3 = z2 && i == 0;
        int i9 = -1;
        int i10 = -1;
        for (int i11 = 0; i11 < i7; i11++) {
            int i12 = this.n + (z ? (i7 - 1) - i11 : i11);
            if (i12 >= pqVar.R0) {
                break;
            }
            if (pqVar.Q0[i12].X == 0) {
                if (i9 == -1) {
                    i9 = i11;
                }
                i10 = i11;
            }
        }
        if (this.a != 0) {
            mg mgVar3 = this.b;
            mgVar3.Z = pqVar.t0;
            int i13 = this.h;
            if (i > 0) {
                i13 += pqVar.F0;
            }
            ag agVar = mgVar3.y;
            ag agVar2 = mgVar3.A;
            if (z) {
                agVar2.a(this.f, i13);
                if (z2) {
                    agVar.a(this.d, this.j);
                }
                if (i > 0) {
                    this.f.b.y.a(agVar2, 0);
                }
            } else {
                agVar.a(this.d, i13);
                if (z2) {
                    agVar2.a(this.f, this.j);
                }
                if (i > 0) {
                    this.d.b.A.a(agVar, 0);
                }
            }
            int i14 = 0;
            mg mgVar4 = null;
            while (i14 < i7) {
                int i15 = this.n + i14;
                if (i15 >= pqVar.R0) {
                    return;
                }
                mg mgVar5 = pqVar.Q0[i15];
                if (i14 == 0) {
                    mgVar5.d(mgVar5.z, this.e, this.i);
                    int i16 = pqVar.u0;
                    float f = pqVar.A0;
                    if (this.n == 0) {
                        int i17 = pqVar.w0;
                        i2 = -1;
                        if (i17 != -1) {
                            f = pqVar.C0;
                        }
                        i16 = i17;
                        mgVar5.a0 = i16;
                        mgVar5.V = f;
                    } else {
                        i2 = -1;
                    }
                    if (z2 && (i17 = pqVar.y0) != i2) {
                        f = pqVar.E0;
                        i16 = i17;
                    }
                    mgVar5.a0 = i16;
                    mgVar5.V = f;
                }
                if (i14 == i7 - 1) {
                    mgVar5.d(mgVar5.B, this.g, this.k);
                }
                if (mgVar4 != null) {
                    ag agVar3 = mgVar5.z;
                    int i18 = pqVar.G0;
                    ag agVar4 = mgVar4.B;
                    agVar3.a(agVar4, i18);
                    ag agVar5 = mgVar5.z;
                    if (i14 == i9) {
                        int i19 = this.i;
                        if (agVar5.f()) {
                            agVar5.f = i19;
                        }
                    }
                    agVar4.a(agVar5, 0);
                    if (i14 == i10 + 1) {
                        int i20 = this.k;
                        if (agVar4.f()) {
                            agVar4.f = i20;
                        }
                    }
                }
                if (mgVar5 != mgVar3) {
                    if (z) {
                        int i21 = pqVar.H0;
                        if (i21 == 0) {
                            mgVar5.A.a(agVar2, 0);
                        } else if (i21 == 1) {
                            mgVar5.y.a(agVar, 0);
                        } else if (i21 == 2) {
                            mgVar5.y.a(agVar, 0);
                            mgVar5.A.a(agVar2, 0);
                        }
                    } else {
                        int i22 = pqVar.H0;
                        if (i22 == 0) {
                            mgVar5.y.a(agVar, 0);
                        } else if (i22 == 1) {
                            mgVar5.A.a(agVar2, 0);
                        } else if (i22 == 2) {
                            if (z3) {
                                mgVar5.y.a(this.d, this.h);
                                mgVar5.A.a(this.f, this.j);
                            } else {
                                mgVar5.y.a(agVar, 0);
                                mgVar5.A.a(agVar2, 0);
                            }
                        }
                    }
                }
                i14++;
                mgVar4 = mgVar5;
            }
            return;
        }
        mg mgVar6 = this.b;
        mgVar6.a0 = pqVar.u0;
        int i23 = this.i;
        if (i > 0) {
            i23 += pqVar.G0;
        }
        ag agVar6 = this.e;
        ag agVar7 = mgVar6.z;
        agVar7.a(agVar6, i23);
        ag agVar8 = mgVar6.B;
        if (z2) {
            agVar8.a(this.g, this.k);
        }
        if (i > 0) {
            this.e.b.B.a(agVar7, 0);
        }
        if (pqVar.I0 != 3 || mgVar6.w) {
            mgVar = mgVar6;
        } else {
            for (int i24 = 0; i24 < i7; i24++) {
                int i25 = this.n + (z ? (i7 - 1) - i24 : i24);
                if (i25 >= pqVar.R0) {
                    break;
                }
                mgVar = pqVar.Q0[i25];
                if (mgVar.w) {
                    break;
                }
            }
            mgVar = mgVar6;
        }
        int i26 = 0;
        mg mgVar7 = null;
        while (i26 < i7) {
            int i27 = z ? (i7 - 1) - i26 : i26;
            int i28 = this.n + i27;
            if (i28 >= pqVar.R0) {
                return;
            }
            mg mgVar8 = pqVar.Q0[i28];
            if (i26 == 0) {
                mgVar8.d(mgVar8.y, this.d, this.h);
            }
            if (i27 == 0) {
                int i29 = pqVar.t0;
                float f2 = pqVar.z0;
                if (this.n == 0) {
                    int i30 = pqVar.v0;
                    i3 = i29;
                    i4 = -1;
                    if (i30 != -1) {
                        f2 = pqVar.B0;
                    }
                    i5 = i30;
                    mgVar8.Z = i5;
                    mgVar8.U = f2;
                } else {
                    i3 = i29;
                    i4 = -1;
                }
                if (!z2 || (i30 = pqVar.x0) == i4) {
                    i5 = i3;
                    mgVar8.Z = i5;
                    mgVar8.U = f2;
                } else {
                    f2 = pqVar.D0;
                    i5 = i30;
                    mgVar8.Z = i5;
                    mgVar8.U = f2;
                }
            }
            if (i26 == i7 - 1) {
                mgVar8.d(mgVar8.A, this.f, this.j);
            }
            if (mgVar7 != null) {
                ag agVar9 = mgVar8.y;
                int i31 = pqVar.F0;
                ag agVar10 = mgVar7.A;
                agVar9.a(agVar10, i31);
                ag agVar11 = mgVar8.y;
                if (i26 == i9) {
                    int i32 = this.h;
                    if (agVar11.f()) {
                        agVar11.f = i32;
                    }
                }
                agVar10.a(agVar11, 0);
                if (i26 == i10 + 1) {
                    int i33 = this.j;
                    if (agVar10.f()) {
                        agVar10.f = i33;
                    }
                }
            }
            if (mgVar8 != mgVar6) {
                int i34 = pqVar.I0;
                c = 3;
                if (i34 == 3 && mgVar.w && mgVar8 != mgVar && mgVar8.w) {
                    mgVar8.C.a(mgVar.C, 0);
                } else if (i34 == 0) {
                    mgVar8.z.a(agVar7, 0);
                } else if (i34 == 1) {
                    mgVar8.B.a(agVar8, 0);
                } else if (z3) {
                    mgVar8.z.a(this.e, this.i);
                    mgVar8.B.a(this.g, this.k);
                } else {
                    mgVar8.z.a(agVar7, 0);
                    mgVar8.B.a(agVar8, 0);
                }
            } else {
                c = 3;
            }
            i26++;
            mgVar7 = mgVar8;
        }
    }

    public final int c() {
        return this.a == 1 ? this.m - this.r.G0 : this.m;
    }

    public final int d() {
        return this.a == 0 ? this.l - this.r.F0 : this.l;
    }

    public final void e(int i) {
        int i2 = this.p;
        if (i2 == 0) {
            return;
        }
        int i3 = this.o;
        int i4 = i / i2;
        for (int i5 = 0; i5 < i3; i5++) {
            int i6 = this.n;
            int i7 = i6 + i5;
            pq pqVar = this.r;
            if (i7 >= pqVar.R0) {
                break;
            }
            mg mgVar = pqVar.Q0[i6 + i5];
            if (this.a == 0) {
                if (mgVar != null) {
                    ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = mgVar.J;
                    if (constraintWidget$DimensionBehaviourArr[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && mgVar.j == 0) {
                        pqVar.G(mgVar, ConstraintWidget$DimensionBehaviour.FIXED, i4, constraintWidget$DimensionBehaviourArr[1], mgVar.j());
                    }
                }
            } else if (mgVar != null) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr2 = mgVar.J;
                if (constraintWidget$DimensionBehaviourArr2[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && mgVar.k == 0) {
                    int i8 = i4;
                    pqVar.G(mgVar, constraintWidget$DimensionBehaviourArr2[0], mgVar.m(), ConstraintWidget$DimensionBehaviour.FIXED, i8);
                    i4 = i8;
                }
            }
        }
        this.l = 0;
        this.m = 0;
        this.b = null;
        this.c = 0;
        int i9 = this.o;
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = this.n + i10;
            pq pqVar2 = this.r;
            if (i11 >= pqVar2.R0) {
                return;
            }
            mg mgVar2 = pqVar2.Q0[i11];
            if (this.a == 0) {
                int iM = mgVar2.m();
                int i12 = pqVar2.F0;
                if (mgVar2.X == 8) {
                    i12 = 0;
                }
                this.l = iM + i12 + this.l;
                int iE = pqVar2.E(mgVar2, this.q);
                if (this.b == null || this.c < iE) {
                    this.b = mgVar2;
                    this.c = iE;
                    this.m = iE;
                }
            } else {
                int iF = pqVar2.F(mgVar2, this.q);
                int iE2 = pqVar2.E(mgVar2, this.q);
                int i13 = pqVar2.G0;
                if (mgVar2.X == 8) {
                    i13 = 0;
                }
                this.m = iE2 + i13 + this.m;
                if (this.b == null || this.c < iF) {
                    this.b = mgVar2;
                    this.c = iF;
                    this.l = iF;
                }
            }
        }
    }

    public final void f(int i, ag agVar, ag agVar2, ag agVar3, ag agVar4, int i2, int i3, int i4, int i5, int i6) {
        this.a = i;
        this.d = agVar;
        this.e = agVar2;
        this.f = agVar3;
        this.g = agVar4;
        this.h = i2;
        this.i = i3;
        this.j = i4;
        this.k = i5;
        this.q = i6;
    }
}
