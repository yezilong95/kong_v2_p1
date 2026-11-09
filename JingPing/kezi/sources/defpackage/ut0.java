package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public abstract class ut0 implements fk {
    public int a;
    public mg b;
    public bg0 c;
    public ConstraintWidget$DimensionBehaviour d;
    public final xk e = new xk(this);
    public int f = 0;
    public boolean g = false;
    public final ik h = new ik(this);
    public final ik i = new ik(this);
    public int j = 1;

    public ut0(mg mgVar) {
        this.b = mgVar;
    }

    public static void b(ik ikVar, ik ikVar2, int i) {
        ikVar.l.add(ikVar2);
        ikVar.f = i;
        ikVar2.k.add(ikVar);
    }

    public static ik h(ag agVar) {
        ag agVar2 = agVar.d;
        if (agVar2 == null) {
            return null;
        }
        int i = tt0.a[agVar2.c.ordinal()];
        mg mgVar = agVar2.b;
        if (i == 1) {
            return mgVar.d.h;
        }
        if (i == 2) {
            return mgVar.d.i;
        }
        if (i == 3) {
            return mgVar.e.h;
        }
        if (i == 4) {
            return mgVar.e.k;
        }
        if (i != 5) {
            return null;
        }
        return mgVar.e.i;
    }

    public static ik i(ag agVar, int i) {
        ag agVar2 = agVar.d;
        if (agVar2 == null) {
            return null;
        }
        mg mgVar = agVar2.b;
        ut0 ut0Var = i == 0 ? mgVar.d : mgVar.e;
        int i2 = tt0.a[agVar2.c.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return ut0Var.i;
        }
        return ut0Var.h;
    }

    public final void c(ik ikVar, ik ikVar2, int i, xk xkVar) {
        ikVar.l.add(ikVar2);
        ikVar.l.add(this.e);
        ikVar.h = i;
        ikVar.i = xkVar;
        ikVar2.k.add(ikVar);
        xkVar.k.add(ikVar);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        if (i2 == 0) {
            mg mgVar = this.b;
            int i3 = mgVar.n;
            int iMax = Math.max(mgVar.m, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax != i) {
                return iMax;
            }
        } else {
            mg mgVar2 = this.b;
            int i4 = mgVar2.q;
            int iMax2 = Math.max(mgVar2.p, i);
            if (i4 > 0) {
                iMax2 = Math.min(i4, i);
            }
            if (iMax2 != i) {
                return iMax2;
            }
        }
        return i;
    }

    public long j() {
        if (this.e.j) {
            return r0.g;
        }
        return 0L;
    }

    public abstract boolean k();

    public final void l(ag agVar, ag agVar2, int i) {
        ik ikVarH = h(agVar);
        ik ikVarH2 = h(agVar2);
        if (ikVarH.j && ikVarH2.j) {
            int iC = agVar.c() + ikVarH.g;
            int iC2 = ikVarH2.g - agVar2.c();
            int i2 = iC2 - iC;
            xk xkVar = this.e;
            if (!xkVar.j) {
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2) {
                    int i3 = this.a;
                    if (i3 == 0) {
                        xkVar.d(g(i2, i));
                    } else if (i3 == 1) {
                        xkVar.d(Math.min(g(xkVar.m, i), i2));
                    } else if (i3 == 2) {
                        mg mgVar = this.b;
                        mg mgVar2 = mgVar.K;
                        if (mgVar2 != null) {
                            if ((i == 0 ? mgVar2.d : mgVar2.e).e.j) {
                                xkVar.d(g((int) ((r6.g * (i == 0 ? mgVar.o : mgVar.r)) + 0.5f), i));
                            }
                        }
                    } else if (i3 == 3) {
                        mg mgVar3 = this.b;
                        ut0 ut0Var = mgVar3.d;
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ut0Var.d;
                        dr0 dr0Var = mgVar3.e;
                        if (constraintWidget$DimensionBehaviour3 != constraintWidget$DimensionBehaviour2 || ut0Var.a != 3 || dr0Var.d != constraintWidget$DimensionBehaviour2 || dr0Var.a != 3) {
                            if (i == 0) {
                                ut0Var = dr0Var;
                            }
                            if (ut0Var.e.j) {
                                float f = mgVar3.N;
                                xkVar.d(i == 1 ? (int) ((r6.g / f) + 0.5f) : (int) ((f * r6.g) + 0.5f));
                            }
                        }
                    }
                }
            }
            if (xkVar.j) {
                int i4 = xkVar.g;
                ik ikVar = this.i;
                ik ikVar2 = this.h;
                if (i4 == i2) {
                    ikVar2.d(iC);
                    ikVar.d(iC2);
                    return;
                }
                mg mgVar4 = this.b;
                float f2 = i == 0 ? mgVar4.U : mgVar4.V;
                if (ikVarH == ikVarH2) {
                    iC = ikVarH.g;
                    iC2 = ikVarH2.g;
                    f2 = 0.5f;
                }
                ikVar2.d((int) ((((iC2 - iC) - i4) * f2) + iC + 0.5f));
                ikVar.d(ikVar2.g + xkVar.g);
            }
        }
    }
}
