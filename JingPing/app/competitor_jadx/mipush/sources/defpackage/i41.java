package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class i41 extends q71 {
    public ao k;
    public ea l;

    @Override // defpackage.xn
    public final void a(xn xnVar) {
        float f;
        float f2;
        float f3;
        int i;
        if (zn.w(this.j) == 3) {
            yh yhVar = this.b;
            l(yhVar.z, yhVar.B, 1);
            return;
        }
        so soVar = this.e;
        if (soVar.c && !soVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            yh yhVar2 = this.b;
            int i2 = yhVar2.k;
            if (i2 == 2) {
                yh yhVar3 = yhVar2.K;
                if (yhVar3 != null) {
                    if (yhVar3.e.e.j) {
                        soVar.d((int) ((r1.g * yhVar2.r) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                so soVar2 = yhVar2.d.e;
                if (soVar2.j) {
                    int i3 = yhVar2.O;
                    if (i3 == -1) {
                        f = soVar2.g;
                        f2 = yhVar2.N;
                    } else if (i3 == 0) {
                        f3 = soVar2.g * yhVar2.N;
                        i = (int) (f3 + 0.5f);
                        soVar.d(i);
                    } else if (i3 != 1) {
                        i = 0;
                        soVar.d(i);
                    } else {
                        f = soVar2.g;
                        f2 = yhVar2.N;
                    }
                    f3 = f / f2;
                    i = (int) (f3 + 0.5f);
                    soVar.d(i);
                }
            }
        }
        ao aoVar = this.h;
        if (aoVar.c) {
            ao aoVar2 = this.i;
            if (aoVar2.c) {
                if (aoVar.j && aoVar2.j && soVar.j) {
                    return;
                }
                if (!soVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    yh yhVar4 = this.b;
                    if (yhVar4.j == 0 && !yhVar4.s()) {
                        ao aoVar3 = (ao) aoVar.l.get(0);
                        ao aoVar4 = (ao) aoVar2.l.get(0);
                        int i4 = aoVar3.g + aoVar.f;
                        int i5 = aoVar4.g + aoVar2.f;
                        aoVar.d(i4);
                        aoVar2.d(i5);
                        soVar.d(i5 - i4);
                        return;
                    }
                }
                if (!soVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && this.a == 1 && aoVar.l.size() > 0 && aoVar2.l.size() > 0) {
                    ao aoVar5 = (ao) aoVar.l.get(0);
                    int i6 = (((ao) aoVar2.l.get(0)).g + aoVar2.f) - (aoVar5.g + aoVar.f);
                    int i7 = soVar.m;
                    if (i6 < i7) {
                        soVar.d(i6);
                    } else {
                        soVar.d(i7);
                    }
                }
                if (soVar.j && aoVar.l.size() > 0 && aoVar2.l.size() > 0) {
                    ao aoVar6 = (ao) aoVar.l.get(0);
                    ao aoVar7 = (ao) aoVar2.l.get(0);
                    int i8 = aoVar6.g;
                    int i9 = aoVar.f + i8;
                    int i10 = aoVar7.g;
                    int i11 = aoVar2.f + i10;
                    float f4 = this.b.V;
                    if (aoVar6 == aoVar7) {
                        f4 = 0.5f;
                    } else {
                        i8 = i9;
                        i10 = i11;
                    }
                    aoVar.d((int) ((((i10 - i8) - soVar.g) * f4) + i8 + 0.5f));
                    aoVar2.d(aoVar.g + soVar.g);
                }
            }
        }
    }

    @Override // defpackage.q71
    public final void d() {
        yh yhVar;
        yh yhVar2;
        yh yhVar3;
        yh yhVar4;
        yh yhVar5 = this.b;
        boolean z = yhVar5.a;
        so soVar = this.e;
        if (z) {
            soVar.d(yhVar5.j());
        }
        boolean z2 = soVar.j;
        ao aoVar = this.i;
        ao aoVar2 = this.h;
        if (!z2) {
            yh yhVar6 = this.b;
            this.d = yhVar6.J[1];
            if (yhVar6.w) {
                this.l = new ea(this);
            }
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
            if (constraintWidget$DimensionBehaviour != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_PARENT && (yhVar4 = this.b.K) != null && yhVar4.J[1] == ConstraintWidget$DimensionBehaviour.FIXED) {
                    int iJ = (yhVar4.j() - this.b.z.c()) - this.b.B.c();
                    i41 i41Var = yhVar4.e;
                    q71.b(aoVar2, i41Var.h, this.b.z.c());
                    q71.b(aoVar, i41Var.i, -this.b.B.c());
                    soVar.d(iJ);
                    return;
                }
                if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.FIXED) {
                    soVar.d(this.b.j());
                }
            }
        } else if (this.d == ConstraintWidget$DimensionBehaviour.MATCH_PARENT && (yhVar2 = (yhVar = this.b).K) != null && yhVar2.J[1] == ConstraintWidget$DimensionBehaviour.FIXED) {
            i41 i41Var2 = yhVar2.e;
            q71.b(aoVar2, i41Var2.h, yhVar.z.c());
            q71.b(aoVar, i41Var2.i, -this.b.B.c());
            return;
        }
        boolean z3 = soVar.j;
        ao aoVar3 = this.k;
        if (z3) {
            yh yhVar7 = this.b;
            if (yhVar7.a) {
                mh[] mhVarArr = yhVar7.G;
                mh mhVar = mhVarArr[2];
                mh mhVar2 = mhVar.d;
                if (mhVar2 != null && mhVarArr[3].d != null) {
                    if (yhVar7.s()) {
                        aoVar2.f = this.b.G[2].c();
                        aoVar.f = -this.b.G[3].c();
                    } else {
                        ao aoVarH = q71.h(this.b.G[2]);
                        if (aoVarH != null) {
                            q71.b(aoVar2, aoVarH, this.b.G[2].c());
                        }
                        ao aoVarH2 = q71.h(this.b.G[3]);
                        if (aoVarH2 != null) {
                            q71.b(aoVar, aoVarH2, -this.b.G[3].c());
                        }
                        aoVar2.b = true;
                        aoVar.b = true;
                    }
                    yh yhVar8 = this.b;
                    if (yhVar8.w) {
                        q71.b(aoVar3, aoVar2, yhVar8.R);
                        return;
                    }
                    return;
                }
                if (mhVar2 != null) {
                    ao aoVarH3 = q71.h(mhVar);
                    if (aoVarH3 != null) {
                        q71.b(aoVar2, aoVarH3, this.b.G[2].c());
                        q71.b(aoVar, aoVar2, soVar.g);
                        yh yhVar9 = this.b;
                        if (yhVar9.w) {
                            q71.b(aoVar3, aoVar2, yhVar9.R);
                            return;
                        }
                        return;
                    }
                    return;
                }
                mh mhVar3 = mhVarArr[3];
                if (mhVar3.d != null) {
                    ao aoVarH4 = q71.h(mhVar3);
                    if (aoVarH4 != null) {
                        q71.b(aoVar, aoVarH4, -this.b.G[3].c());
                        q71.b(aoVar2, aoVar, -soVar.g);
                    }
                    yh yhVar10 = this.b;
                    if (yhVar10.w) {
                        q71.b(aoVar3, aoVar2, yhVar10.R);
                        return;
                    }
                    return;
                }
                mh mhVar4 = mhVarArr[4];
                if (mhVar4.d != null) {
                    ao aoVarH5 = q71.h(mhVar4);
                    if (aoVarH5 != null) {
                        q71.b(aoVar3, aoVarH5, 0);
                        q71.b(aoVar2, aoVar3, -this.b.R);
                        q71.b(aoVar, aoVar2, soVar.g);
                        return;
                    }
                    return;
                }
                if ((yhVar7 instanceof b30) || yhVar7.K == null || yhVar7.h(ConstraintAnchor$Type.CENTER).d != null) {
                    return;
                }
                yh yhVar11 = this.b;
                q71.b(aoVar2, yhVar11.K.e.h, yhVar11.o());
                q71.b(aoVar, aoVar2, soVar.g);
                yh yhVar12 = this.b;
                if (yhVar12.w) {
                    q71.b(aoVar3, aoVar2, yhVar12.R);
                    return;
                }
                return;
            }
        }
        if (z3 || this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            soVar.b(this);
        } else {
            yh yhVar13 = this.b;
            int i = yhVar13.k;
            if (i == 2) {
                yh yhVar14 = yhVar13.K;
                if (yhVar14 != null) {
                    so soVar2 = yhVar14.e.e;
                    soVar.l.add(soVar2);
                    soVar2.k.add(soVar);
                    soVar.b = true;
                    soVar.k.add(aoVar2);
                    soVar.k.add(aoVar);
                }
            } else if (i == 3 && !yhVar13.s()) {
                yh yhVar15 = this.b;
                if (yhVar15.j != 3) {
                    so soVar3 = yhVar15.d.e;
                    soVar.l.add(soVar3);
                    soVar3.k.add(soVar);
                    soVar.b = true;
                    soVar.k.add(aoVar2);
                    soVar.k.add(aoVar);
                }
            }
        }
        yh yhVar16 = this.b;
        mh[] mhVarArr2 = yhVar16.G;
        mh mhVar5 = mhVarArr2[2];
        mh mhVar6 = mhVar5.d;
        if (mhVar6 != null && mhVarArr2[3].d != null) {
            if (yhVar16.s()) {
                aoVar2.f = this.b.G[2].c();
                aoVar.f = -this.b.G[3].c();
            } else {
                ao aoVarH6 = q71.h(this.b.G[2]);
                ao aoVarH7 = q71.h(this.b.G[3]);
                aoVarH6.b(this);
                aoVarH7.b(this);
                this.j = 4;
            }
            if (this.b.w) {
                c(aoVar3, aoVar2, 1, this.l);
            }
        } else if (mhVar6 != null) {
            ao aoVarH8 = q71.h(mhVar5);
            if (aoVarH8 != null) {
                q71.b(aoVar2, aoVarH8, this.b.G[2].c());
                c(aoVar, aoVar2, 1, soVar);
                if (this.b.w) {
                    c(aoVar3, aoVar2, 1, this.l);
                }
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = this.d;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    yh yhVar17 = this.b;
                    if (yhVar17.N > 0.0f) {
                        g30 g30Var = yhVar17.d;
                        if (g30Var.d == constraintWidget$DimensionBehaviour3) {
                            g30Var.e.k.add(soVar);
                            soVar.l.add(this.b.d.e);
                            soVar.a = this;
                        }
                    }
                }
            }
        } else {
            mh mhVar7 = mhVarArr2[3];
            if (mhVar7.d != null) {
                ao aoVarH9 = q71.h(mhVar7);
                if (aoVarH9 != null) {
                    q71.b(aoVar, aoVarH9, -this.b.G[3].c());
                    c(aoVar2, aoVar, -1, soVar);
                    if (this.b.w) {
                        c(aoVar3, aoVar2, 1, this.l);
                    }
                }
            } else {
                mh mhVar8 = mhVarArr2[4];
                if (mhVar8.d != null) {
                    ao aoVarH10 = q71.h(mhVar8);
                    if (aoVarH10 != null) {
                        q71.b(aoVar3, aoVarH10, 0);
                        c(aoVar2, aoVar3, -1, this.l);
                        c(aoVar, aoVar2, 1, soVar);
                    }
                } else if (!(yhVar16 instanceof b30) && (yhVar3 = yhVar16.K) != null) {
                    q71.b(aoVar2, yhVar3.e.h, yhVar16.o());
                    c(aoVar, aoVar2, 1, soVar);
                    if (this.b.w) {
                        c(aoVar3, aoVar2, 1, this.l);
                    }
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = this.d;
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                    if (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour5) {
                        yh yhVar18 = this.b;
                        if (yhVar18.N > 0.0f) {
                            g30 g30Var2 = yhVar18.d;
                            if (g30Var2.d == constraintWidget$DimensionBehaviour5) {
                                g30Var2.e.k.add(soVar);
                                soVar.l.add(this.b.d.e);
                                soVar.a = this;
                            }
                        }
                    }
                }
            }
        }
        if (soVar.l.size() == 0) {
            soVar.c = true;
        }
    }

    @Override // defpackage.q71
    public final void e() {
        ao aoVar = this.h;
        if (aoVar.j) {
            this.b.Q = aoVar.g;
        }
    }

    @Override // defpackage.q71
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.q71
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT || this.b.k == 0;
    }

    public final void m() {
        this.g = false;
        ao aoVar = this.h;
        aoVar.c();
        aoVar.j = false;
        ao aoVar2 = this.i;
        aoVar2.c();
        aoVar2.j = false;
        ao aoVar3 = this.k;
        aoVar3.c();
        aoVar3.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.b.Y;
    }
}
