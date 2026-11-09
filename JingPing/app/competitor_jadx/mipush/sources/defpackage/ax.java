package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class ax {
    public int a;
    public mh d;
    public mh e;
    public mh f;
    public mh g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int q;
    public final /* synthetic */ cx r;
    public yh b = null;
    public int c = 0;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public int p = 0;

    public ax(cx cxVar, int i, mh mhVar, mh mhVar2, mh mhVar3, mh mhVar4, int i2) {
        this.r = cxVar;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.q = 0;
        this.a = i;
        this.d = mhVar;
        this.e = mhVar2;
        this.f = mhVar3;
        this.g = mhVar4;
        this.h = cxVar.m0;
        this.i = cxVar.i0;
        this.j = cxVar.n0;
        this.k = cxVar.j0;
        this.q = i2;
    }

    public final void a(yh yhVar) {
        int i = this.a;
        cx cxVar = this.r;
        if (i == 0) {
            int iF = cxVar.F(yhVar, this.q);
            if (yhVar.J[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                this.p++;
                iF = 0;
            }
            this.l = iF + (yhVar.X != 8 ? cxVar.F0 : 0) + this.l;
            int iE = cxVar.E(yhVar, this.q);
            if (this.b == null || this.c < iE) {
                this.b = yhVar;
                this.c = iE;
                this.m = iE;
            }
        } else {
            int iF2 = cxVar.F(yhVar, this.q);
            int iE2 = cxVar.E(yhVar, this.q);
            if (yhVar.J[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                this.p++;
                iE2 = 0;
            }
            this.m = iE2 + (yhVar.X != 8 ? cxVar.G0 : 0) + this.m;
            if (this.b == null || this.c < iF2) {
                this.b = yhVar;
                this.c = iF2;
                this.l = iF2;
            }
        }
        this.o++;
    }

    public final void b(int i, boolean z, boolean z2) {
        cx cxVar;
        int i2;
        yh yhVar;
        char c;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = this.o;
        int i8 = 0;
        while (true) {
            cxVar = this.r;
            if (i8 >= i7 || (i6 = this.n + i8) >= cxVar.R0) {
                break;
            }
            yh yhVar2 = cxVar.Q0[i6];
            if (yhVar2 != null) {
                yhVar2.u();
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
            if (i12 >= cxVar.R0) {
                break;
            }
            if (cxVar.Q0[i12].X == 0) {
                if (i9 == -1) {
                    i9 = i11;
                }
                i10 = i11;
            }
        }
        if (this.a != 0) {
            yh yhVar3 = this.b;
            yhVar3.Z = cxVar.t0;
            int i13 = this.h;
            if (i > 0) {
                i13 += cxVar.F0;
            }
            mh mhVar = yhVar3.y;
            mh mhVar2 = yhVar3.A;
            if (z) {
                mhVar2.a(this.f, i13);
                if (z2) {
                    mhVar.a(this.d, this.j);
                }
                if (i > 0) {
                    this.f.b.y.a(mhVar2, 0);
                }
            } else {
                mhVar.a(this.d, i13);
                if (z2) {
                    mhVar2.a(this.f, this.j);
                }
                if (i > 0) {
                    this.d.b.A.a(mhVar, 0);
                }
            }
            int i14 = 0;
            yh yhVar4 = null;
            while (i14 < i7) {
                int i15 = this.n + i14;
                if (i15 >= cxVar.R0) {
                    return;
                }
                yh yhVar5 = cxVar.Q0[i15];
                if (i14 == 0) {
                    yhVar5.d(yhVar5.z, this.e, this.i);
                    int i16 = cxVar.u0;
                    float f = cxVar.A0;
                    if (this.n == 0) {
                        int i17 = cxVar.w0;
                        i2 = -1;
                        if (i17 != -1) {
                            f = cxVar.C0;
                        }
                        i16 = i17;
                        yhVar5.a0 = i16;
                        yhVar5.V = f;
                    } else {
                        i2 = -1;
                    }
                    if (z2 && (i17 = cxVar.y0) != i2) {
                        f = cxVar.E0;
                        i16 = i17;
                    }
                    yhVar5.a0 = i16;
                    yhVar5.V = f;
                }
                if (i14 == i7 - 1) {
                    yhVar5.d(yhVar5.B, this.g, this.k);
                }
                if (yhVar4 != null) {
                    mh mhVar3 = yhVar5.z;
                    int i18 = cxVar.G0;
                    mh mhVar4 = yhVar4.B;
                    mhVar3.a(mhVar4, i18);
                    mh mhVar5 = yhVar5.z;
                    if (i14 == i9) {
                        int i19 = this.i;
                        if (mhVar5.f()) {
                            mhVar5.f = i19;
                        }
                    }
                    mhVar4.a(mhVar5, 0);
                    if (i14 == i10 + 1) {
                        int i20 = this.k;
                        if (mhVar4.f()) {
                            mhVar4.f = i20;
                        }
                    }
                }
                if (yhVar5 != yhVar3) {
                    if (z) {
                        int i21 = cxVar.H0;
                        if (i21 == 0) {
                            yhVar5.A.a(mhVar2, 0);
                        } else if (i21 == 1) {
                            yhVar5.y.a(mhVar, 0);
                        } else if (i21 == 2) {
                            yhVar5.y.a(mhVar, 0);
                            yhVar5.A.a(mhVar2, 0);
                        }
                    } else {
                        int i22 = cxVar.H0;
                        if (i22 == 0) {
                            yhVar5.y.a(mhVar, 0);
                        } else if (i22 == 1) {
                            yhVar5.A.a(mhVar2, 0);
                        } else if (i22 == 2) {
                            if (z3) {
                                yhVar5.y.a(this.d, this.h);
                                yhVar5.A.a(this.f, this.j);
                            } else {
                                yhVar5.y.a(mhVar, 0);
                                yhVar5.A.a(mhVar2, 0);
                            }
                        }
                    }
                }
                i14++;
                yhVar4 = yhVar5;
            }
            return;
        }
        yh yhVar6 = this.b;
        yhVar6.a0 = cxVar.u0;
        int i23 = this.i;
        if (i > 0) {
            i23 += cxVar.G0;
        }
        mh mhVar6 = this.e;
        mh mhVar7 = yhVar6.z;
        mhVar7.a(mhVar6, i23);
        mh mhVar8 = yhVar6.B;
        if (z2) {
            mhVar8.a(this.g, this.k);
        }
        if (i > 0) {
            this.e.b.B.a(mhVar7, 0);
        }
        if (cxVar.I0 != 3 || yhVar6.w) {
            yhVar = yhVar6;
        } else {
            for (int i24 = 0; i24 < i7; i24++) {
                int i25 = this.n + (z ? (i7 - 1) - i24 : i24);
                if (i25 >= cxVar.R0) {
                    break;
                }
                yhVar = cxVar.Q0[i25];
                if (yhVar.w) {
                    break;
                }
            }
            yhVar = yhVar6;
        }
        int i26 = 0;
        yh yhVar7 = null;
        while (i26 < i7) {
            int i27 = z ? (i7 - 1) - i26 : i26;
            int i28 = this.n + i27;
            if (i28 >= cxVar.R0) {
                return;
            }
            yh yhVar8 = cxVar.Q0[i28];
            if (i26 == 0) {
                yhVar8.d(yhVar8.y, this.d, this.h);
            }
            if (i27 == 0) {
                int i29 = cxVar.t0;
                float f2 = cxVar.z0;
                if (this.n == 0) {
                    int i30 = cxVar.v0;
                    i3 = i29;
                    i4 = -1;
                    if (i30 != -1) {
                        f2 = cxVar.B0;
                    }
                    i5 = i30;
                    yhVar8.Z = i5;
                    yhVar8.U = f2;
                } else {
                    i3 = i29;
                    i4 = -1;
                }
                if (!z2 || (i30 = cxVar.x0) == i4) {
                    i5 = i3;
                    yhVar8.Z = i5;
                    yhVar8.U = f2;
                } else {
                    f2 = cxVar.D0;
                    i5 = i30;
                    yhVar8.Z = i5;
                    yhVar8.U = f2;
                }
            }
            if (i26 == i7 - 1) {
                yhVar8.d(yhVar8.A, this.f, this.j);
            }
            if (yhVar7 != null) {
                mh mhVar9 = yhVar8.y;
                int i31 = cxVar.F0;
                mh mhVar10 = yhVar7.A;
                mhVar9.a(mhVar10, i31);
                mh mhVar11 = yhVar8.y;
                if (i26 == i9) {
                    int i32 = this.h;
                    if (mhVar11.f()) {
                        mhVar11.f = i32;
                    }
                }
                mhVar10.a(mhVar11, 0);
                if (i26 == i10 + 1) {
                    int i33 = this.j;
                    if (mhVar10.f()) {
                        mhVar10.f = i33;
                    }
                }
            }
            if (yhVar8 != yhVar6) {
                int i34 = cxVar.I0;
                c = 3;
                if (i34 == 3 && yhVar.w && yhVar8 != yhVar && yhVar8.w) {
                    yhVar8.C.a(yhVar.C, 0);
                } else if (i34 == 0) {
                    yhVar8.z.a(mhVar7, 0);
                } else if (i34 == 1) {
                    yhVar8.B.a(mhVar8, 0);
                } else if (z3) {
                    yhVar8.z.a(this.e, this.i);
                    yhVar8.B.a(this.g, this.k);
                } else {
                    yhVar8.z.a(mhVar7, 0);
                    yhVar8.B.a(mhVar8, 0);
                }
            } else {
                c = 3;
            }
            i26++;
            yhVar7 = yhVar8;
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
            cx cxVar = this.r;
            if (i7 >= cxVar.R0) {
                break;
            }
            yh yhVar = cxVar.Q0[i6 + i5];
            if (this.a == 0) {
                if (yhVar != null) {
                    ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = yhVar.J;
                    if (constraintWidget$DimensionBehaviourArr[0] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && yhVar.j == 0) {
                        cxVar.G(yhVar, ConstraintWidget$DimensionBehaviour.FIXED, i4, constraintWidget$DimensionBehaviourArr[1], yhVar.j());
                    }
                }
            } else if (yhVar != null) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr2 = yhVar.J;
                if (constraintWidget$DimensionBehaviourArr2[1] == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && yhVar.k == 0) {
                    int i8 = i4;
                    cxVar.G(yhVar, constraintWidget$DimensionBehaviourArr2[0], yhVar.m(), ConstraintWidget$DimensionBehaviour.FIXED, i8);
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
            cx cxVar2 = this.r;
            if (i11 >= cxVar2.R0) {
                return;
            }
            yh yhVar2 = cxVar2.Q0[i11];
            if (this.a == 0) {
                int iM = yhVar2.m();
                int i12 = cxVar2.F0;
                if (yhVar2.X == 8) {
                    i12 = 0;
                }
                this.l = iM + i12 + this.l;
                int iE = cxVar2.E(yhVar2, this.q);
                if (this.b == null || this.c < iE) {
                    this.b = yhVar2;
                    this.c = iE;
                    this.m = iE;
                }
            } else {
                int iF = cxVar2.F(yhVar2, this.q);
                int iE2 = cxVar2.E(yhVar2, this.q);
                int i13 = cxVar2.G0;
                if (yhVar2.X == 8) {
                    i13 = 0;
                }
                this.m = iE2 + i13 + this.m;
                if (this.b == null || this.c < iF) {
                    this.b = yhVar2;
                    this.c = iF;
                    this.l = iF;
                }
            }
        }
    }

    public final void f(int i, mh mhVar, mh mhVar2, mh mhVar3, mh mhVar4, int i2, int i3, int i4, int i5, int i6) {
        this.a = i;
        this.d = mhVar;
        this.e = mhVar2;
        this.f = mhVar3;
        this.g = mhVar4;
        this.h = i2;
        this.i = i3;
        this.j = i4;
        this.k = i5;
        this.q = i6;
    }
}
