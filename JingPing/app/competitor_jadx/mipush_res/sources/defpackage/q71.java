package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;

/* JADX INFO: loaded from: classes.dex */
public abstract class q71 implements xn {
    public int a;
    public yh b;
    public wp0 c;
    public ConstraintWidget$DimensionBehaviour d;
    public final so e = new so(this);
    public int f = 0;
    public boolean g = false;
    public final ao h = new ao(this);
    public final ao i = new ao(this);
    public int j = 1;

    public q71(yh yhVar) {
        this.b = yhVar;
    }

    public static void b(ao aoVar, ao aoVar2, int i) {
        aoVar.l.add(aoVar2);
        aoVar.f = i;
        aoVar2.k.add(aoVar);
    }

    public static ao h(mh mhVar) {
        mh mhVar2 = mhVar.d;
        if (mhVar2 == null) {
            return null;
        }
        int i = p71.a[mhVar2.c.ordinal()];
        yh yhVar = mhVar2.b;
        if (i == 1) {
            return yhVar.d.h;
        }
        if (i == 2) {
            return yhVar.d.i;
        }
        if (i == 3) {
            return yhVar.e.h;
        }
        if (i == 4) {
            return yhVar.e.k;
        }
        if (i != 5) {
            return null;
        }
        return yhVar.e.i;
    }

    public static ao i(mh mhVar, int i) {
        mh mhVar2 = mhVar.d;
        if (mhVar2 == null) {
            return null;
        }
        yh yhVar = mhVar2.b;
        q71 q71Var = i == 0 ? yhVar.d : yhVar.e;
        int i2 = p71.a[mhVar2.c.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 5) {
                        return null;
                    }
                }
            }
            return q71Var.i;
        }
        return q71Var.h;
    }

    public final void c(ao aoVar, ao aoVar2, int i, so soVar) {
        aoVar.l.add(aoVar2);
        aoVar.l.add(this.e);
        aoVar.h = i;
        aoVar.i = soVar;
        aoVar2.k.add(aoVar);
        soVar.k.add(aoVar);
    }

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public final int g(int i, int i2) {
        if (i2 == 0) {
            yh yhVar = this.b;
            int i3 = yhVar.n;
            int iMax = Math.max(yhVar.m, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            if (iMax != i) {
                return iMax;
            }
        } else {
            yh yhVar2 = this.b;
            int i4 = yhVar2.q;
            int iMax2 = Math.max(yhVar2.p, i);
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

    public final void l(mh mhVar, mh mhVar2, int i) {
        ao aoVarH = h(mhVar);
        ao aoVarH2 = h(mhVar2);
        if (aoVarH.j && aoVarH2.j) {
            int iC = mhVar.c() + aoVarH.g;
            int iC2 = aoVarH2.g - mhVar2.c();
            int i2 = iC2 - iC;
            so soVar = this.e;
            if (!soVar.j) {
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = this.d;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2) {
                    int i3 = this.a;
                    if (i3 == 0) {
                        soVar.d(g(i2, i));
                    } else if (i3 == 1) {
                        soVar.d(Math.min(g(soVar.m, i), i2));
                    } else if (i3 == 2) {
                        yh yhVar = this.b;
                        yh yhVar2 = yhVar.K;
                        if (yhVar2 != null) {
                            if ((i == 0 ? yhVar2.d : yhVar2.e).e.j) {
                                soVar.d(g((int) ((r6.g * (i == 0 ? yhVar.o : yhVar.r)) + 0.5f), i));
                            }
                        }
                    } else if (i3 == 3) {
                        yh yhVar3 = this.b;
                        q71 q71Var = yhVar3.d;
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = q71Var.d;
                        i41 i41Var = yhVar3.e;
                        if (constraintWidget$DimensionBehaviour3 != constraintWidget$DimensionBehaviour2 || q71Var.a != 3 || i41Var.d != constraintWidget$DimensionBehaviour2 || i41Var.a != 3) {
                            if (i == 0) {
                                q71Var = i41Var;
                            }
                            if (q71Var.e.j) {
                                float f = yhVar3.N;
                                soVar.d(i == 1 ? (int) ((r6.g / f) + 0.5f) : (int) ((f * r6.g) + 0.5f));
                            }
                        }
                    }
                }
            }
            if (soVar.j) {
                int i4 = soVar.g;
                ao aoVar = this.i;
                ao aoVar2 = this.h;
                if (i4 == i2) {
                    aoVar2.d(iC);
                    aoVar.d(iC2);
                    return;
                }
                yh yhVar4 = this.b;
                float f2 = i == 0 ? yhVar4.U : yhVar4.V;
                if (aoVarH == aoVarH2) {
                    iC = aoVarH.g;
                    iC2 = aoVarH2.g;
                    f2 = 0.5f;
                }
                aoVar2.d((int) ((((iC2 - iC) - i4) * f2) + iC + 0.5f));
                aoVar.d(aoVar2.g + soVar.g);
            }
        }
    }
}
