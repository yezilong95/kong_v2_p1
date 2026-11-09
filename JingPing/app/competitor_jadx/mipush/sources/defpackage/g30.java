package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public final class g30 extends q71 {
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
    @Override // defpackage.xn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(defpackage.xn r23) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.g30.a(xn):void");
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
            soVar.d(yhVar5.m());
        }
        boolean z2 = soVar.j;
        ao aoVar = this.i;
        ao aoVar2 = this.h;
        if (z2) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
            if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2 && (((yhVar2 = (yhVar = this.b).K) != null && yhVar2.J[0] == ConstraintWidget$DimensionBehaviour.FIXED) || yhVar2.J[0] == constraintWidget$DimensionBehaviour2)) {
                q71.b(aoVar2, yhVar2.d.h, yhVar.y.c());
                q71.b(aoVar, yhVar2.d.i, -this.b.A.c());
                return;
            }
        } else {
            yh yhVar6 = this.b;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = yhVar6.J[0];
            this.d = constraintWidget$DimensionBehaviour3;
            if (constraintWidget$DimensionBehaviour3 != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
                if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4 && (((yhVar4 = yhVar6.K) != null && yhVar4.J[0] == ConstraintWidget$DimensionBehaviour.FIXED) || yhVar4.J[0] == constraintWidget$DimensionBehaviour4)) {
                    int iM = (yhVar4.m() - this.b.y.c()) - this.b.A.c();
                    g30 g30Var = yhVar4.d;
                    q71.b(aoVar2, g30Var.h, this.b.y.c());
                    q71.b(aoVar, g30Var.i, -this.b.A.c());
                    soVar.d(iM);
                    return;
                }
                if (constraintWidget$DimensionBehaviour3 == ConstraintWidget$DimensionBehaviour.FIXED) {
                    soVar.d(yhVar6.m());
                }
            }
        }
        if (soVar.j) {
            yh yhVar7 = this.b;
            if (yhVar7.a) {
                mh[] mhVarArr = yhVar7.G;
                mh mhVar = mhVarArr[0];
                mh mhVar2 = mhVar.d;
                if (mhVar2 != null && mhVarArr[1].d != null) {
                    if (yhVar7.r()) {
                        aoVar2.f = this.b.G[0].c();
                        aoVar.f = -this.b.G[1].c();
                        return;
                    }
                    ao aoVarH = q71.h(this.b.G[0]);
                    if (aoVarH != null) {
                        q71.b(aoVar2, aoVarH, this.b.G[0].c());
                    }
                    ao aoVarH2 = q71.h(this.b.G[1]);
                    if (aoVarH2 != null) {
                        q71.b(aoVar, aoVarH2, -this.b.G[1].c());
                    }
                    aoVar2.b = true;
                    aoVar.b = true;
                    return;
                }
                if (mhVar2 != null) {
                    ao aoVarH3 = q71.h(mhVar);
                    if (aoVarH3 != null) {
                        q71.b(aoVar2, aoVarH3, this.b.G[0].c());
                        q71.b(aoVar, aoVar2, soVar.g);
                        return;
                    }
                    return;
                }
                mh mhVar3 = mhVarArr[1];
                if (mhVar3.d != null) {
                    ao aoVarH4 = q71.h(mhVar3);
                    if (aoVarH4 != null) {
                        q71.b(aoVar, aoVarH4, -this.b.G[1].c());
                        q71.b(aoVar2, aoVar, -soVar.g);
                        return;
                    }
                    return;
                }
                if ((yhVar7 instanceof b30) || yhVar7.K == null || yhVar7.h(ConstraintAnchor$Type.CENTER).d != null) {
                    return;
                }
                yh yhVar8 = this.b;
                q71.b(aoVar2, yhVar8.K.d.h, yhVar8.n());
                q71.b(aoVar, aoVar2, soVar.g);
                return;
            }
        }
        if (this.d == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
            yh yhVar9 = this.b;
            int i = yhVar9.j;
            if (i == 2) {
                yh yhVar10 = yhVar9.K;
                if (yhVar10 != null) {
                    so soVar2 = yhVar10.e.e;
                    soVar.l.add(soVar2);
                    soVar2.k.add(soVar);
                    soVar.b = true;
                    soVar.k.add(aoVar2);
                    soVar.k.add(aoVar);
                }
            } else if (i == 3) {
                if (yhVar9.k == 3) {
                    aoVar2.a = this;
                    aoVar.a = this;
                    i41 i41Var = yhVar9.e;
                    i41Var.h.a = this;
                    i41Var.i.a = this;
                    soVar.a = this;
                    if (yhVar9.s()) {
                        soVar.l.add(this.b.e.e);
                        this.b.e.e.k.add(soVar);
                        i41 i41Var2 = this.b.e;
                        i41Var2.e.a = this;
                        soVar.l.add(i41Var2.h);
                        soVar.l.add(this.b.e.i);
                        this.b.e.h.k.add(soVar);
                        this.b.e.i.k.add(soVar);
                    } else if (this.b.r()) {
                        this.b.e.e.l.add(soVar);
                        soVar.k.add(this.b.e.e);
                    } else {
                        this.b.e.e.l.add(soVar);
                    }
                } else {
                    so soVar3 = yhVar9.e.e;
                    soVar.l.add(soVar3);
                    soVar3.k.add(soVar);
                    this.b.e.h.k.add(soVar);
                    this.b.e.i.k.add(soVar);
                    soVar.b = true;
                    soVar.k.add(aoVar2);
                    soVar.k.add(aoVar);
                    aoVar2.l.add(soVar);
                    aoVar.l.add(soVar);
                }
            }
        }
        yh yhVar11 = this.b;
        mh[] mhVarArr2 = yhVar11.G;
        mh mhVar4 = mhVarArr2[0];
        mh mhVar5 = mhVar4.d;
        if (mhVar5 != null && mhVarArr2[1].d != null) {
            if (yhVar11.r()) {
                aoVar2.f = this.b.G[0].c();
                aoVar.f = -this.b.G[1].c();
                return;
            }
            ao aoVarH5 = q71.h(this.b.G[0]);
            ao aoVarH6 = q71.h(this.b.G[1]);
            aoVarH5.b(this);
            aoVarH6.b(this);
            this.j = 4;
            return;
        }
        if (mhVar5 != null) {
            ao aoVarH7 = q71.h(mhVar4);
            if (aoVarH7 != null) {
                q71.b(aoVar2, aoVarH7, this.b.G[0].c());
                c(aoVar, aoVar2, 1, soVar);
                return;
            }
            return;
        }
        mh mhVar6 = mhVarArr2[1];
        if (mhVar6.d != null) {
            ao aoVarH8 = q71.h(mhVar6);
            if (aoVarH8 != null) {
                q71.b(aoVar, aoVarH8, -this.b.G[1].c());
                c(aoVar2, aoVar, -1, soVar);
                return;
            }
            return;
        }
        if ((yhVar11 instanceof b30) || (yhVar3 = yhVar11.K) == null) {
            return;
        }
        q71.b(aoVar2, yhVar3.d.h, yhVar11.n());
        c(aoVar, aoVar2, 1, soVar);
    }

    @Override // defpackage.q71
    public final void e() {
        ao aoVar = this.h;
        if (aoVar.j) {
            this.b.P = aoVar.g;
        }
    }

    @Override // defpackage.q71
    public final void f() {
        this.c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // defpackage.q71
    public final boolean k() {
        return this.d != ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT || this.b.j == 0;
    }

    public final void n() {
        this.g = false;
        ao aoVar = this.h;
        aoVar.c();
        aoVar.j = false;
        ao aoVar2 = this.i;
        aoVar2.c();
        aoVar2.j = false;
        this.e.j = false;
    }

    public final String toString() {
        return "HorizontalRun " + this.b.Y;
    }
}
