package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class yv extends ut0 {
    public static final int[] k = new int[2];

    public static void m(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
        if (i8 <= i6) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else if (i9 <= i7) {
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x025e  */
    @Override // defpackage.fk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(defpackage.fk r23) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yv.a(fk):void");
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
            xkVar.d(mgVar5.m());
        }
        boolean z2 = xkVar.j;
        ik ikVar = this.i;
        ik ikVar2 = this.h;
        if (z2) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
            if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2 && (((mgVar2 = (mgVar = this.b).K) != null && mgVar2.J[0] == ConstraintWidget$DimensionBehaviour.FIXED) || mgVar2.J[0] == constraintWidget$DimensionBehaviour2)) {
                ut0.b(ikVar2, mgVar2.d.h, mgVar.y.c());
                ut0.b(ikVar, mgVar2.d.i, -this.b.A.c());
                return;
            }
        } else {
            mg mgVar6 = this.b;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = mgVar6.J[0];
            this.d = constraintWidget$DimensionBehaviour3;
            if (constraintWidget$DimensionBehaviour3 != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
                if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4 && (((mgVar4 = mgVar6.K) != null && mgVar4.J[0] == ConstraintWidget$DimensionBehaviour.FIXED) || mgVar4.J[0] == constraintWidget$DimensionBehaviour4)) {
                    int iM = (mgVar4.m() - this.b.y.c()) - this.b.A.c();
                    yv yvVar = mgVar4.d;
                    ut0.b(ikVar2, yvVar.h, this.b.y.c());
                    ut0.b(ikVar, yvVar.i, -this.b.A.c());
                    xkVar.d(iM);
                    return;
                }
                if (constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.FIXED) {
                    xkVar.d(mgVar6.m());
                }
            }
        }
        if (xkVar.j) {
            mg mgVar7 = this.b;
            if (mgVar7.a) {
                ag[] agVarArr = mgVar7.G;
                ag agVar = agVarArr[0];
                ag agVar2 = agVar.d;
                if (agVar2 != null && agVarArr[1].d != null) {
                    if (mgVar7.r()) {
                        ikVar2.f = this.b.G[0].c();
                        ikVar.f = -this.b.G[1].c();
                        return;
                    }
                    ik ikVarH = ut0.h(this.b.G[0]);
                    if (ikVarH != null) {
                        ut0.b(ikVar2, ikVarH, this.b.G[0].c());
                    }
                    ik ikVarH2 = ut0.h(this.b.G[1]);
                    if (ikVarH2 != null) {
                        ut0.b(ikVar, ikVarH2, -this.b.G[1].c());
                    }
                    ikVar2.b = true;
                    ikVar.b = true;
                    return;
                }
                if (agVar2 != null) {
                    ik ikVarH3 = ut0.h(agVar);
                    if (ikVarH3 != null) {
                        ut0.b(ikVar2, ikVarH3, this.b.G[0].c());
                        ut0.b(ikVar, ikVar2, xkVar.g);
                        return;
                    }
                    return;
                }
                ag agVar3 = agVarArr[1];
                if (agVar3.d != null) {
                    ik ikVarH4 = ut0.h(agVar3);
                    if (ikVarH4 != null) {
                        ut0.b(ikVar, ikVarH4, -this.b.G[1].c());
                        ut0.b(ikVar2, ikVar, -xkVar.g);
                        return;
                    }
                    return;
                }
                if ((mgVar7 instanceof vv) || mgVar7.K == null || mgVar7.h(ConstraintAnchor$Type.CENTER).d != null) {
                    return;
                }
                mg mgVar8 = this.b;
                ut0.b(ikVar2, mgVar8.K.d.h, mgVar8.n());
                ut0.b(ikVar, ikVar2, xkVar.g);
                return;
            }
        }
        if (this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            mg mgVar9 = this.b;
            int i = mgVar9.j;
            if (i == 2) {
                mg mgVar10 = mgVar9.K;
                if (mgVar10 != null) {
                    xk xkVar2 = mgVar10.e.e;
                    xkVar.l.add(xkVar2);
                    xkVar2.k.add(xkVar);
                    xkVar.b = true;
                    xkVar.k.add(ikVar2);
                    xkVar.k.add(ikVar);
                }
            } else if (i == 3) {
                if (mgVar9.k == 3) {
                    ikVar2.a = this;
                    ikVar.a = this;
                    dr0 dr0Var = mgVar9.e;
                    dr0Var.h.a = this;
                    dr0Var.i.a = this;
                    xkVar.a = this;
                    if (mgVar9.s()) {
                        xkVar.l.add(this.b.e.e);
                        this.b.e.e.k.add(xkVar);
                        dr0 dr0Var2 = this.b.e;
                        dr0Var2.e.a = this;
                        xkVar.l.add(dr0Var2.h);
                        xkVar.l.add(this.b.e.i);
                        this.b.e.h.k.add(xkVar);
                        this.b.e.i.k.add(xkVar);
                    } else if (this.b.r()) {
                        this.b.e.e.l.add(xkVar);
                        xkVar.k.add(this.b.e.e);
                    } else {
                        this.b.e.e.l.add(xkVar);
                    }
                } else {
                    xk xkVar3 = mgVar9.e.e;
                    xkVar.l.add(xkVar3);
                    xkVar3.k.add(xkVar);
                    this.b.e.h.k.add(xkVar);
                    this.b.e.i.k.add(xkVar);
                    xkVar.b = true;
                    xkVar.k.add(ikVar2);
                    xkVar.k.add(ikVar);
                    ikVar2.l.add(xkVar);
                    ikVar.l.add(xkVar);
                }
            }
        }
        mg mgVar11 = this.b;
        ag[] agVarArr2 = mgVar11.G;
        ag agVar4 = agVarArr2[0];
        ag agVar5 = agVar4.d;
        if (agVar5 != null && agVarArr2[1].d != null) {
            if (mgVar11.r()) {
                ikVar2.f = this.b.G[0].c();
                ikVar.f = -this.b.G[1].c();
                return;
            }
            ik ikVarH5 = ut0.h(this.b.G[0]);
            ik ikVarH6 = ut0.h(this.b.G[1]);
            ikVarH5.b(this);
            ikVarH6.b(this);
            this.j = 4;
            return;
        }
        if (agVar5 != null) {
            ik ikVarH7 = ut0.h(agVar4);
            if (ikVarH7 != null) {
                ut0.b(ikVar2, ikVarH7, this.b.G[0].c());
                c(ikVar, ikVar2, 1, xkVar);
                return;
            }
            return;
        }
        ag agVar6 = agVarArr2[1];
        if (agVar6.d != null) {
            ik ikVarH8 = ut0.h(agVar6);
            if (ikVarH8 != null) {
                ut0.b(ikVar, ikVarH8, -this.b.G[1].c());
                c(ikVar2, ikVar, -1, xkVar);
                return;
            }
            return;
        }
        if ((mgVar11 instanceof vv) || (mgVar3 = mgVar11.K) == null) {
            return;
        }
        ut0.b(ikVar2, mgVar3.d.h, mgVar11.n());
        c(ikVar, ikVar2, 1, xkVar);
    }

    @Override // defpackage.ut0
    public final void e() {
        ik ikVar = this.h;
        if (ikVar.j) {
            this.b.P = ikVar.g;
        }
    }

    @Override // defpackage.ut0
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.ut0
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT || this.b.j == 0;
    }

    public final void n() {
        this.g = false;
        ik ikVar = this.h;
        ikVar.c();
        ikVar.j = false;
        ik ikVar2 = this.i;
        ikVar2.c();
        ikVar2.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.b.Y;
    }
}
