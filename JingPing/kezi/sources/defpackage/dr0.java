package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class dr0 extends ut0 {
    public ik k;
    public i8 l;

    @Override // defpackage.fk
    public final void a(fk fkVar) {
        float f;
        float f2;
        float f3;
        int i;
        if (hk.o(this.j) == 3) {
            mg mgVar = this.b;
            l(mgVar.z, mgVar.B, 1);
            return;
        }
        xk xkVar = this.e;
        if (xkVar.c && !xkVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            mg mgVar2 = this.b;
            int i2 = mgVar2.k;
            if (i2 == 2) {
                mg mgVar3 = mgVar2.K;
                if (mgVar3 != null) {
                    if (mgVar3.e.e.j) {
                        xkVar.d((int) ((r1.g * mgVar2.r) + 0.5f));
                    }
                }
            } else if (i2 == 3) {
                xk xkVar2 = mgVar2.d.e;
                if (xkVar2.j) {
                    int i3 = mgVar2.O;
                    if (i3 == -1) {
                        f = xkVar2.g;
                        f2 = mgVar2.N;
                    } else if (i3 == 0) {
                        f3 = xkVar2.g * mgVar2.N;
                        i = (int) (f3 + 0.5f);
                        xkVar.d(i);
                    } else if (i3 != 1) {
                        i = 0;
                        xkVar.d(i);
                    } else {
                        f = xkVar2.g;
                        f2 = mgVar2.N;
                    }
                    f3 = f / f2;
                    i = (int) (f3 + 0.5f);
                    xkVar.d(i);
                }
            }
        }
        ik ikVar = this.h;
        if (ikVar.c) {
            ik ikVar2 = this.i;
            if (ikVar2.c) {
                if (ikVar.j && ikVar2.j && xkVar.j) {
                    return;
                }
                if (!xkVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    mg mgVar4 = this.b;
                    if (mgVar4.j == 0 && !mgVar4.s()) {
                        ik ikVar3 = (ik) ikVar.l.get(0);
                        ik ikVar4 = (ik) ikVar2.l.get(0);
                        int i4 = ikVar3.g + ikVar.f;
                        int i5 = ikVar4.g + ikVar2.f;
                        ikVar.d(i4);
                        ikVar2.d(i5);
                        xkVar.d(i5 - i4);
                        return;
                    }
                }
                if (!xkVar.j && this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && this.a == 1 && ikVar.l.size() > 0 && ikVar2.l.size() > 0) {
                    ik ikVar5 = (ik) ikVar.l.get(0);
                    int i6 = (((ik) ikVar2.l.get(0)).g + ikVar2.f) - (ikVar5.g + ikVar.f);
                    int i7 = xkVar.m;
                    if (i6 < i7) {
                        xkVar.d(i6);
                    } else {
                        xkVar.d(i7);
                    }
                }
                if (xkVar.j && ikVar.l.size() > 0 && ikVar2.l.size() > 0) {
                    ik ikVar6 = (ik) ikVar.l.get(0);
                    ik ikVar7 = (ik) ikVar2.l.get(0);
                    int i8 = ikVar6.g;
                    int i9 = ikVar.f + i8;
                    int i10 = ikVar7.g;
                    int i11 = ikVar2.f + i10;
                    float f4 = this.b.V;
                    if (ikVar6 == ikVar7) {
                        f4 = 0.5f;
                    } else {
                        i8 = i9;
                        i10 = i11;
                    }
                    ikVar.d((int) ((((i10 - i8) - xkVar.g) * f4) + i8 + 0.5f));
                    ikVar2.d(ikVar.g + xkVar.g);
                }
            }
        }
    }

    @Override // defpackage.ut0
    public final void d() {
        mg mgVar;
        mg mgVar2;
        mg mgVar3;
        mg mgVar4;
        mg mgVar5 = this.b;
        boolean z = mgVar5.a;
        xk xkVar = this.e;
        if (z) {
            xkVar.d(mgVar5.j());
        }
        boolean z2 = xkVar.j;
        ik ikVar = this.i;
        ik ikVar2 = this.h;
        if (!z2) {
            mg mgVar6 = this.b;
            this.d = mgVar6.J[1];
            if (mgVar6.w) {
                this.l = new i8(this);
            }
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
            if (constraintWidget$DimensionBehaviour != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_PARENT && (mgVar4 = this.b.K) != null && mgVar4.J[1] == ConstraintWidget$DimensionBehaviour.FIXED) {
                    int iJ = (mgVar4.j() - this.b.z.c()) - this.b.B.c();
                    dr0 dr0Var = mgVar4.e;
                    ut0.b(ikVar2, dr0Var.h, this.b.z.c());
                    ut0.b(ikVar, dr0Var.i, -this.b.B.c());
                    xkVar.d(iJ);
                    return;
                }
                if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.FIXED) {
                    xkVar.d(this.b.j());
                }
            }
        } else if (this.d == ConstraintWidget$DimensionBehaviour.MATCH_PARENT && (mgVar2 = (mgVar = this.b).K) != null && mgVar2.J[1] == ConstraintWidget$DimensionBehaviour.FIXED) {
            dr0 dr0Var2 = mgVar2.e;
            ut0.b(ikVar2, dr0Var2.h, mgVar.z.c());
            ut0.b(ikVar, dr0Var2.i, -this.b.B.c());
            return;
        }
        boolean z3 = xkVar.j;
        ik ikVar3 = this.k;
        if (z3) {
            mg mgVar7 = this.b;
            if (mgVar7.a) {
                ag[] agVarArr = mgVar7.G;
                ag agVar = agVarArr[2];
                ag agVar2 = agVar.d;
                if (agVar2 != null && agVarArr[3].d != null) {
                    if (mgVar7.s()) {
                        ikVar2.f = this.b.G[2].c();
                        ikVar.f = -this.b.G[3].c();
                    } else {
                        ik ikVarH = ut0.h(this.b.G[2]);
                        if (ikVarH != null) {
                            ut0.b(ikVar2, ikVarH, this.b.G[2].c());
                        }
                        ik ikVarH2 = ut0.h(this.b.G[3]);
                        if (ikVarH2 != null) {
                            ut0.b(ikVar, ikVarH2, -this.b.G[3].c());
                        }
                        ikVar2.b = true;
                        ikVar.b = true;
                    }
                    mg mgVar8 = this.b;
                    if (mgVar8.w) {
                        ut0.b(ikVar3, ikVar2, mgVar8.R);
                        return;
                    }
                    return;
                }
                if (agVar2 != null) {
                    ik ikVarH3 = ut0.h(agVar);
                    if (ikVarH3 != null) {
                        ut0.b(ikVar2, ikVarH3, this.b.G[2].c());
                        ut0.b(ikVar, ikVar2, xkVar.g);
                        mg mgVar9 = this.b;
                        if (mgVar9.w) {
                            ut0.b(ikVar3, ikVar2, mgVar9.R);
                            return;
                        }
                        return;
                    }
                    return;
                }
                ag agVar3 = agVarArr[3];
                if (agVar3.d != null) {
                    ik ikVarH4 = ut0.h(agVar3);
                    if (ikVarH4 != null) {
                        ut0.b(ikVar, ikVarH4, -this.b.G[3].c());
                        ut0.b(ikVar2, ikVar, -xkVar.g);
                    }
                    mg mgVar10 = this.b;
                    if (mgVar10.w) {
                        ut0.b(ikVar3, ikVar2, mgVar10.R);
                        return;
                    }
                    return;
                }
                ag agVar4 = agVarArr[4];
                if (agVar4.d != null) {
                    ik ikVarH5 = ut0.h(agVar4);
                    if (ikVarH5 != null) {
                        ut0.b(ikVar3, ikVarH5, 0);
                        ut0.b(ikVar2, ikVar3, -this.b.R);
                        ut0.b(ikVar, ikVar2, xkVar.g);
                        return;
                    }
                    return;
                }
                if ((mgVar7 instanceof vv) || mgVar7.K == null || mgVar7.h(ConstraintAnchor$Type.CENTER).d != null) {
                    return;
                }
                mg mgVar11 = this.b;
                ut0.b(ikVar2, mgVar11.K.e.h, mgVar11.o());
                ut0.b(ikVar, ikVar2, xkVar.g);
                mg mgVar12 = this.b;
                if (mgVar12.w) {
                    ut0.b(ikVar3, ikVar2, mgVar12.R);
                    return;
                }
                return;
            }
        }
        if (z3 || this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            xkVar.b(this);
        } else {
            mg mgVar13 = this.b;
            int i = mgVar13.k;
            if (i == 2) {
                mg mgVar14 = mgVar13.K;
                if (mgVar14 != null) {
                    xk xkVar2 = mgVar14.e.e;
                    xkVar.l.add(xkVar2);
                    xkVar2.k.add(xkVar);
                    xkVar.b = true;
                    xkVar.k.add(ikVar2);
                    xkVar.k.add(ikVar);
                }
            } else if (i == 3 && !mgVar13.s()) {
                mg mgVar15 = this.b;
                if (mgVar15.j != 3) {
                    xk xkVar3 = mgVar15.d.e;
                    xkVar.l.add(xkVar3);
                    xkVar3.k.add(xkVar);
                    xkVar.b = true;
                    xkVar.k.add(ikVar2);
                    xkVar.k.add(ikVar);
                }
            }
        }
        mg mgVar16 = this.b;
        ag[] agVarArr2 = mgVar16.G;
        ag agVar5 = agVarArr2[2];
        ag agVar6 = agVar5.d;
        if (agVar6 != null && agVarArr2[3].d != null) {
            if (mgVar16.s()) {
                ikVar2.f = this.b.G[2].c();
                ikVar.f = -this.b.G[3].c();
            } else {
                ik ikVarH6 = ut0.h(this.b.G[2]);
                ik ikVarH7 = ut0.h(this.b.G[3]);
                ikVarH6.b(this);
                ikVarH7.b(this);
                this.j = 4;
            }
            if (this.b.w) {
                c(ikVar3, ikVar2, 1, this.l);
            }
        } else if (agVar6 != null) {
            ik ikVarH8 = ut0.h(agVar5);
            if (ikVarH8 != null) {
                ut0.b(ikVar2, ikVarH8, this.b.G[2].c());
                c(ikVar, ikVar2, 1, xkVar);
                if (this.b.w) {
                    c(ikVar3, ikVar2, 1, this.l);
                }
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = this.d;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3) {
                    mg mgVar17 = this.b;
                    if (mgVar17.N > 0.0f) {
                        yv yvVar = mgVar17.d;
                        if (yvVar.d == constraintWidget$DimensionBehaviour3) {
                            yvVar.e.k.add(xkVar);
                            xkVar.l.add(this.b.d.e);
                            xkVar.a = this;
                        }
                    }
                }
            }
        } else {
            ag agVar7 = agVarArr2[3];
            if (agVar7.d != null) {
                ik ikVarH9 = ut0.h(agVar7);
                if (ikVarH9 != null) {
                    ut0.b(ikVar, ikVarH9, -this.b.G[3].c());
                    c(ikVar2, ikVar, -1, xkVar);
                    if (this.b.w) {
                        c(ikVar3, ikVar2, 1, this.l);
                    }
                }
            } else {
                ag agVar8 = agVarArr2[4];
                if (agVar8.d != null) {
                    ik ikVarH10 = ut0.h(agVar8);
                    if (ikVarH10 != null) {
                        ut0.b(ikVar3, ikVarH10, 0);
                        c(ikVar2, ikVar3, -1, this.l);
                        c(ikVar, ikVar2, 1, xkVar);
                    }
                } else if (!(mgVar16 instanceof vv) && (mgVar3 = mgVar16.K) != null) {
                    ut0.b(ikVar2, mgVar3.e.h, mgVar16.o());
                    c(ikVar, ikVar2, 1, xkVar);
                    if (this.b.w) {
                        c(ikVar3, ikVar2, 1, this.l);
                    }
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = this.d;
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                    if (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour5) {
                        mg mgVar18 = this.b;
                        if (mgVar18.N > 0.0f) {
                            yv yvVar2 = mgVar18.d;
                            if (yvVar2.d == constraintWidget$DimensionBehaviour5) {
                                yvVar2.e.k.add(xkVar);
                                xkVar.l.add(this.b.d.e);
                                xkVar.a = this;
                            }
                        }
                    }
                }
            }
        }
        if (xkVar.l.size() == 0) {
            xkVar.c = true;
        }
    }

    @Override // defpackage.ut0
    public final void e() {
        ik ikVar = this.h;
        if (ikVar.j) {
            this.b.Q = ikVar.g;
        }
    }

    @Override // defpackage.ut0
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.k.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.ut0
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT || this.b.k == 0;
    }

    public final void m() {
        this.g = false;
        ik ikVar = this.h;
        ikVar.c();
        ikVar.j = false;
        ik ikVar2 = this.i;
        ikVar2.c();
        ikVar2.j = false;
        ik ikVar3 = this.k;
        ikVar3.c();
        ikVar3.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "VerticalRun " + this.b.Y;
    }
}
