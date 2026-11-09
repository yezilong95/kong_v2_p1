package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class gk {
    public ng a;
    public boolean b;
    public boolean c;
    public ng d;
    public ArrayList e;
    public m8 f;
    public l8 g;
    public ArrayList h;

    public final void a(ik ikVar, int i, ArrayList arrayList, bg0 bg0Var) {
        ut0 ut0Var = ikVar.d;
        if (ut0Var.c == null) {
            ng ngVar = this.a;
            if (ut0Var == ngVar.d || ut0Var == ngVar.e) {
                return;
            }
            if (bg0Var == null) {
                bg0Var = new bg0();
                bg0Var.a = null;
                bg0Var.b = new ArrayList();
                bg0Var.a = ut0Var;
                arrayList.add(bg0Var);
            }
            ut0Var.c = bg0Var;
            bg0Var.b.add(ut0Var);
            ik ikVar2 = ut0Var.h;
            for (fk fkVar : ikVar2.k) {
                if (fkVar instanceof ik) {
                    a((ik) fkVar, i, arrayList, bg0Var);
                }
            }
            ik ikVar3 = ut0Var.i;
            for (fk fkVar2 : ikVar3.k) {
                if (fkVar2 instanceof ik) {
                    a((ik) fkVar2, i, arrayList, bg0Var);
                }
            }
            if (i == 1 && (ut0Var instanceof dr0)) {
                for (fk fkVar3 : ((dr0) ut0Var).k.k) {
                    if (fkVar3 instanceof ik) {
                        a((ik) fkVar3, i, arrayList, bg0Var);
                    }
                }
            }
            Iterator it = ikVar2.l.iterator();
            while (it.hasNext()) {
                a((ik) it.next(), i, arrayList, bg0Var);
            }
            Iterator it2 = ikVar3.l.iterator();
            while (it2.hasNext()) {
                a((ik) it2.next(), i, arrayList, bg0Var);
            }
            if (i == 1 && (ut0Var instanceof dr0)) {
                Iterator it3 = ((dr0) ut0Var).k.l.iterator();
                while (it3.hasNext()) {
                    a((ik) it3.next(), i, arrayList, bg0Var);
                }
            }
        }
    }

    public final void b(ng ngVar) {
        float f;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour6;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour7;
        Iterator it = ngVar.g0.iterator();
        while (it.hasNext()) {
            mg mgVar = (mg) it.next();
            ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr2 = mgVar.J;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour8 = constraintWidget$DimensionBehaviourArr2[0];
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour9 = constraintWidget$DimensionBehaviourArr2[1];
            if (mgVar.X == 8) {
                mgVar.a = true;
            } else {
                float f2 = mgVar.o;
                if (f2 < 1.0f && constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    mgVar.j = 2;
                }
                float f3 = mgVar.r;
                if (f3 < 1.0f && constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    mgVar.k = 2;
                }
                if (mgVar.N > 0.0f) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour10 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                    if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour10 && (constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.FIXED)) {
                        mgVar.j = 3;
                    } else if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour10 && (constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.FIXED)) {
                        mgVar.k = 3;
                    } else if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour10 && constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour10) {
                        if (mgVar.j == 0) {
                            mgVar.j = 3;
                        }
                        if (mgVar.k == 0) {
                            mgVar.k = 3;
                        }
                    }
                }
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour11 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                ag agVar = mgVar.A;
                ag agVar2 = mgVar.y;
                if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour11 && mgVar.j == 1 && (agVar2.d == null || agVar.d == null)) {
                    constraintWidget$DimensionBehaviour8 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                }
                ag agVar3 = mgVar.B;
                ag agVar4 = mgVar.z;
                if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour11 && mgVar.k == 1 && (agVar4.d == null || agVar3.d == null)) {
                    constraintWidget$DimensionBehaviour9 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                }
                yv yvVar = mgVar.d;
                yvVar.d = constraintWidget$DimensionBehaviour8;
                int i = mgVar.j;
                yvVar.a = i;
                dr0 dr0Var = mgVar.e;
                dr0Var.d = constraintWidget$DimensionBehaviour9;
                int i2 = mgVar.k;
                dr0Var.a = i2;
                Iterator it2 = it;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour12 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
                if ((constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour12 || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour12 || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT)) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour13 = constraintWidget$DimensionBehaviour9;
                    int iM = mgVar.m();
                    if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour12) {
                        iM = (ngVar.m() - agVar2.e) - agVar.e;
                        constraintWidget$DimensionBehaviour8 = ConstraintWidget$DimensionBehaviour.FIXED;
                    }
                    int i3 = iM;
                    int iJ = mgVar.j();
                    if (constraintWidget$DimensionBehaviour13 == constraintWidget$DimensionBehaviour12) {
                        iJ = (ngVar.j() - agVar4.e) - agVar3.e;
                        constraintWidget$DimensionBehaviour13 = ConstraintWidget$DimensionBehaviour.FIXED;
                    }
                    f(mgVar, constraintWidget$DimensionBehaviour8, i3, constraintWidget$DimensionBehaviour13, iJ);
                    yvVar.e.d(mgVar.m());
                    dr0Var.e.d(mgVar.j());
                    mgVar.a = true;
                } else {
                    ag[] agVarArr = mgVar.G;
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour14 = constraintWidget$DimensionBehaviour8;
                    if (constraintWidget$DimensionBehaviour8 != constraintWidget$DimensionBehaviour11 || (constraintWidget$DimensionBehaviour9 != (constraintWidget$DimensionBehaviour7 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && constraintWidget$DimensionBehaviour9 != ConstraintWidget$DimensionBehaviour.FIXED)) {
                        f = f3;
                        constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour9;
                    } else if (i == 3) {
                        if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour7) {
                            f(mgVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour7, 0);
                        }
                        int iJ2 = mgVar.j();
                        int i4 = (int) ((iJ2 * mgVar.N) + 0.5f);
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour15 = ConstraintWidget$DimensionBehaviour.FIXED;
                        f(mgVar, constraintWidget$DimensionBehaviour15, i4, constraintWidget$DimensionBehaviour15, iJ2);
                        yvVar.e.d(mgVar.m());
                        dr0Var.e.d(mgVar.j());
                        mgVar.a = true;
                    } else if (i == 1) {
                        f(mgVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour9, 0);
                        yvVar.e.m = mgVar.m();
                    } else {
                        constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour9;
                        if (i == 2) {
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour16 = ngVar.J[0];
                            f = f3;
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour17 = ConstraintWidget$DimensionBehaviour.FIXED;
                            if (constraintWidget$DimensionBehaviour16 == constraintWidget$DimensionBehaviour17 || constraintWidget$DimensionBehaviour16 == constraintWidget$DimensionBehaviour12) {
                                f(mgVar, constraintWidget$DimensionBehaviour17, (int) ((f2 * ngVar.m()) + 0.5f), constraintWidget$DimensionBehaviour, mgVar.j());
                                yvVar.e.d(mgVar.m());
                                dr0Var.e.d(mgVar.j());
                                mgVar.a = true;
                            }
                        } else {
                            f = f3;
                            if (agVarArr[0].d == null || agVarArr[1].d == null) {
                                f(mgVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour, 0);
                                yvVar.e.d(mgVar.m());
                                dr0Var.e.d(mgVar.j());
                                mgVar.a = true;
                            }
                        }
                    }
                    if (constraintWidget$DimensionBehaviour != constraintWidget$DimensionBehaviour11 || (constraintWidget$DimensionBehaviour14 != (constraintWidget$DimensionBehaviour6 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && constraintWidget$DimensionBehaviour14 != ConstraintWidget$DimensionBehaviour.FIXED)) {
                        constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviour14;
                    } else if (i2 == 3) {
                        if (constraintWidget$DimensionBehaviour14 == constraintWidget$DimensionBehaviour6) {
                            f(mgVar, constraintWidget$DimensionBehaviour6, 0, constraintWidget$DimensionBehaviour6, 0);
                        }
                        int iM2 = mgVar.m();
                        float f4 = mgVar.N;
                        if (mgVar.O == -1) {
                            f4 = 1.0f / f4;
                        }
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour18 = ConstraintWidget$DimensionBehaviour.FIXED;
                        f(mgVar, constraintWidget$DimensionBehaviour18, iM2, constraintWidget$DimensionBehaviour18, (int) ((iM2 * f4) + 0.5f));
                        yvVar.e.d(mgVar.m());
                        dr0Var.e.d(mgVar.j());
                        mgVar.a = true;
                    } else {
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour19 = constraintWidget$DimensionBehaviour;
                        if (i2 == 1) {
                            f(mgVar, constraintWidget$DimensionBehaviour14, 0, constraintWidget$DimensionBehaviour6, 0);
                            dr0Var.e.m = mgVar.j();
                        } else {
                            constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviour14;
                            if (i2 == 2) {
                                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour20 = ngVar.J[1];
                                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour21 = ConstraintWidget$DimensionBehaviour.FIXED;
                                if (constraintWidget$DimensionBehaviour20 == constraintWidget$DimensionBehaviour21 || constraintWidget$DimensionBehaviour20 == constraintWidget$DimensionBehaviour12) {
                                    f(mgVar, constraintWidget$DimensionBehaviour2, mgVar.m(), constraintWidget$DimensionBehaviour21, (int) ((ngVar.j() * f) + 0.5f));
                                    yvVar.e.d(mgVar.m());
                                    dr0Var.e.d(mgVar.j());
                                    mgVar.a = true;
                                }
                                constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour19;
                            } else {
                                if (agVarArr[2].d == null || agVarArr[3].d == null) {
                                    f(mgVar, constraintWidget$DimensionBehaviour6, 0, constraintWidget$DimensionBehaviour19, 0);
                                    yvVar.e.d(mgVar.m());
                                    dr0Var.e.d(mgVar.j());
                                    mgVar.a = true;
                                }
                                constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour19;
                            }
                        }
                    }
                    if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour11 && constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour11) {
                        if (i == 1 || i2 == 1) {
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour22 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                            f(mgVar, constraintWidget$DimensionBehaviour22, 0, constraintWidget$DimensionBehaviour22, 0);
                            yvVar.e.m = mgVar.m();
                            dr0Var.e.m = mgVar.j();
                        } else if (i2 == 2 && i == 2 && (((constraintWidget$DimensionBehaviour3 = (constraintWidget$DimensionBehaviourArr = ngVar.J)[0]) == (constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.FIXED) || constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4) && ((constraintWidget$DimensionBehaviour5 = constraintWidget$DimensionBehaviourArr[1]) == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour5 == constraintWidget$DimensionBehaviour4))) {
                            f(mgVar, constraintWidget$DimensionBehaviour4, (int) ((f2 * ngVar.m()) + 0.5f), constraintWidget$DimensionBehaviour4, (int) ((f * ngVar.j()) + 0.5f));
                            yvVar.e.d(mgVar.m());
                            dr0Var.e.d(mgVar.j());
                            mgVar.a = true;
                        }
                    }
                }
                it = it2;
            }
        }
    }

    public final void c() {
        ArrayList<ut0> arrayList = this.e;
        arrayList.clear();
        ng ngVar = this.d;
        ngVar.d.f();
        dr0 dr0Var = ngVar.e;
        dr0Var.f();
        arrayList.add(ngVar.d);
        arrayList.add(dr0Var);
        HashSet hashSet = null;
        for (mg mgVar : ngVar.g0) {
            if (mgVar instanceof kv) {
                lv lvVar = new lv(mgVar);
                mgVar.d.f();
                mgVar.e.f();
                lvVar.f = ((kv) mgVar).k0;
                arrayList.add(lvVar);
            } else {
                if (mgVar.r()) {
                    if (mgVar.b == null) {
                        mgVar.b = new eb(mgVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(mgVar.b);
                } else {
                    arrayList.add(mgVar.d);
                }
                if (mgVar.s()) {
                    if (mgVar.c == null) {
                        mgVar.c = new eb(mgVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(mgVar.c);
                } else {
                    arrayList.add(mgVar.e);
                }
                if (mgVar instanceof vv) {
                    arrayList.add(new uv(mgVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((ut0) it.next()).f();
        }
        for (ut0 ut0Var : arrayList) {
            if (ut0Var.b != ngVar) {
                ut0Var.d();
            }
        }
        ArrayList arrayList2 = this.h;
        arrayList2.clear();
        ng ngVar2 = this.a;
        e(ngVar2.d, 0, arrayList2);
        e(ngVar2.e, 1, arrayList2);
        this.b = false;
    }

    public final int d(ng ngVar, int i) {
        ArrayList arrayList;
        int i2;
        int i3;
        long jMax;
        float f;
        ng ngVar2 = ngVar;
        ArrayList arrayList2 = this.h;
        int size = arrayList2.size();
        int i4 = 0;
        long jMax2 = 0;
        while (i4 < size) {
            ut0 ut0Var = ((bg0) arrayList2.get(i4)).a;
            if (!(ut0Var instanceof eb) ? !(i != 0 ? (ut0Var instanceof dr0) : (ut0Var instanceof yv)) : ((eb) ut0Var).f != i) {
                ik ikVar = (i == 0 ? ngVar2.d : ngVar2.e).h;
                ik ikVar2 = (i == 0 ? ngVar2.d : ngVar2.e).i;
                boolean zContains = ut0Var.h.l.contains(ikVar);
                ik ikVar3 = ut0Var.i;
                boolean zContains2 = ikVar3.l.contains(ikVar2);
                long j = ut0Var.j();
                ik ikVar4 = ut0Var.h;
                if (zContains && zContains2) {
                    long jB = bg0.b(ikVar4, 0L);
                    ArrayList arrayList3 = arrayList2;
                    i2 = size;
                    long jA = bg0.a(ikVar3, 0L);
                    long j2 = jB - j;
                    int i5 = ikVar3.f;
                    arrayList = arrayList3;
                    i3 = i4;
                    if (j2 >= (-i5)) {
                        j2 += (long) i5;
                    }
                    long j3 = (-jA) - j;
                    long j4 = ikVar4.f;
                    long j5 = j3 - j4;
                    if (j5 >= j4) {
                        j5 -= j4;
                    }
                    mg mgVar = ut0Var.b;
                    if (i == 0) {
                        f = mgVar.U;
                    } else if (i == 1) {
                        f = mgVar.V;
                    } else {
                        mgVar.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j2 / (1.0f - f)) + (j5 / f)) : 0L;
                    jMax = (((long) ikVar4.f) + ((((long) ((f2 * f) + 0.5f)) + j) + ((long) hk.d(1.0f, f, f2, 0.5f)))) - ((long) ikVar3.f);
                } else {
                    arrayList = arrayList2;
                    i2 = size;
                    i3 = i4;
                    jMax = zContains ? Math.max(bg0.b(ikVar4, ikVar4.f), ((long) ikVar4.f) + j) : zContains2 ? Math.max(-bg0.a(ikVar3, ikVar3.f), ((long) (-ikVar3.f)) + j) : (ut0Var.j() + ((long) ikVar4.f)) - ((long) ikVar3.f);
                }
            } else {
                arrayList = arrayList2;
                i2 = size;
                i3 = i4;
                jMax = 0;
            }
            jMax2 = Math.max(jMax2, jMax);
            i4 = i3 + 1;
            ngVar2 = ngVar;
            size = i2;
            arrayList2 = arrayList;
        }
        return (int) jMax2;
    }

    public final void e(ut0 ut0Var, int i, ArrayList arrayList) {
        ik ikVar;
        Iterator it = ut0Var.h.k.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            ikVar = ut0Var.i;
            if (!zHasNext) {
                break;
            }
            fk fkVar = (fk) it.next();
            if (fkVar instanceof ik) {
                a((ik) fkVar, i, arrayList, null);
            } else if (fkVar instanceof ut0) {
                a(((ut0) fkVar).h, i, arrayList, null);
            }
        }
        for (fk fkVar2 : ikVar.k) {
            if (fkVar2 instanceof ik) {
                a((ik) fkVar2, i, arrayList, null);
            } else if (fkVar2 instanceof ut0) {
                a(((ut0) fkVar2).i, i, arrayList, null);
            }
        }
        if (i == 1) {
            for (fk fkVar3 : ((dr0) ut0Var).k.k) {
                if (fkVar3 instanceof ik) {
                    a((ik) fkVar3, i, arrayList, null);
                }
            }
        }
    }

    public final void f(mg mgVar, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, int i, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, int i2) {
        l8 l8Var = this.g;
        l8Var.a = constraintWidget$DimensionBehaviour;
        l8Var.b = constraintWidget$DimensionBehaviour2;
        l8Var.c = i;
        l8Var.d = i2;
        ((b) this.f).a(mgVar, l8Var);
        mgVar.z(l8Var.e);
        mgVar.w(l8Var.f);
        mgVar.w = l8Var.h;
        int i3 = l8Var.g;
        mgVar.R = i3;
        mgVar.w = i3 > 0;
    }

    public final void g() {
        i8 i8Var;
        for (mg mgVar : this.a.g0) {
            if (!mgVar.a) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = mgVar.J;
                boolean z = false;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
                int i = mgVar.j;
                int i2 = mgVar.k;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                yv yvVar = mgVar.d;
                xk xkVar = yvVar.e;
                boolean z3 = xkVar.j;
                dr0 dr0Var = mgVar.e;
                xk xkVar2 = dr0Var.e;
                boolean z4 = xkVar2.j;
                if (z3 && z4) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.FIXED;
                    f(mgVar, constraintWidget$DimensionBehaviour4, xkVar.g, constraintWidget$DimensionBehaviour4, xkVar2.g);
                    mgVar.a = true;
                } else if (z3 && z) {
                    f(mgVar, ConstraintWidget$DimensionBehaviour.FIXED, xkVar.g, constraintWidget$DimensionBehaviour3, xkVar2.g);
                    if (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                        dr0Var.e.m = mgVar.j();
                    } else {
                        dr0Var.e.d(mgVar.j());
                        mgVar.a = true;
                    }
                } else if (z4 && z2) {
                    f(mgVar, constraintWidget$DimensionBehaviour3, xkVar.g, ConstraintWidget$DimensionBehaviour.FIXED, xkVar2.g);
                    if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                        yvVar.e.m = mgVar.m();
                    } else {
                        yvVar.e.d(mgVar.m());
                        mgVar.a = true;
                    }
                }
                if (mgVar.a && (i8Var = dr0Var.l) != null) {
                    i8Var.d(mgVar.R);
                }
            }
        }
    }
}
