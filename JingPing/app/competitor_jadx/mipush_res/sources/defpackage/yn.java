package defpackage;

import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class yn {
    public zh a;
    public boolean b;
    public boolean c;
    public zh d;
    public ArrayList e;
    public ha f;
    public ga g;
    public ArrayList h;

    public final void a(ao aoVar, int i, ArrayList arrayList, wp0 wp0Var) {
        q71 q71Var = aoVar.d;
        if (q71Var.c == null) {
            zh zhVar = this.a;
            if (q71Var == zhVar.d || q71Var == zhVar.e) {
                return;
            }
            if (wp0Var == null) {
                wp0Var = new wp0();
                wp0Var.a = null;
                wp0Var.b = new ArrayList();
                wp0Var.a = q71Var;
                arrayList.add(wp0Var);
            }
            q71Var.c = wp0Var;
            wp0Var.b.add(q71Var);
            ao aoVar2 = q71Var.h;
            for (xn xnVar : aoVar2.k) {
                if (xnVar instanceof ao) {
                    a((ao) xnVar, i, arrayList, wp0Var);
                }
            }
            ao aoVar3 = q71Var.i;
            for (xn xnVar2 : aoVar3.k) {
                if (xnVar2 instanceof ao) {
                    a((ao) xnVar2, i, arrayList, wp0Var);
                }
            }
            if (i == 1 && (q71Var instanceof i41)) {
                for (xn xnVar3 : ((i41) q71Var).k.k) {
                    if (xnVar3 instanceof ao) {
                        a((ao) xnVar3, i, arrayList, wp0Var);
                    }
                }
            }
            Iterator it = aoVar2.l.iterator();
            while (it.hasNext()) {
                a((ao) it.next(), i, arrayList, wp0Var);
            }
            Iterator it2 = aoVar3.l.iterator();
            while (it2.hasNext()) {
                a((ao) it2.next(), i, arrayList, wp0Var);
            }
            if (i == 1 && (q71Var instanceof i41)) {
                Iterator it3 = ((i41) q71Var).k.l.iterator();
                while (it3.hasNext()) {
                    a((ao) it3.next(), i, arrayList, wp0Var);
                }
            }
        }
    }

    public final void b(zh zhVar) {
        float f;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour6;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour7;
        Iterator it = zhVar.g0.iterator();
        while (it.hasNext()) {
            yh yhVar = (yh) it.next();
            ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr2 = yhVar.J;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour8 = constraintWidget$DimensionBehaviourArr2[0];
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour9 = constraintWidget$DimensionBehaviourArr2[1];
            if (yhVar.X == 8) {
                yhVar.a = true;
            } else {
                float f2 = yhVar.o;
                if (f2 < 1.0f && constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    yhVar.j = 2;
                }
                float f3 = yhVar.r;
                if (f3 < 1.0f && constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                    yhVar.k = 2;
                }
                if (yhVar.N > 0.0f) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour10 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                    if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour10 && (constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.FIXED)) {
                        yhVar.j = 3;
                    } else if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour10 && (constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.FIXED)) {
                        yhVar.k = 3;
                    } else if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour10 && constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour10) {
                        if (yhVar.j == 0) {
                            yhVar.j = 3;
                        }
                        if (yhVar.k == 0) {
                            yhVar.k = 3;
                        }
                    }
                }
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour11 = ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT;
                mh mhVar = yhVar.A;
                mh mhVar2 = yhVar.y;
                if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour11 && yhVar.j == 1 && (mhVar2.d == null || mhVar.d == null)) {
                    constraintWidget$DimensionBehaviour8 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                }
                mh mhVar3 = yhVar.B;
                mh mhVar4 = yhVar.z;
                if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour11 && yhVar.k == 1 && (mhVar4.d == null || mhVar3.d == null)) {
                    constraintWidget$DimensionBehaviour9 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                }
                g30 g30Var = yhVar.d;
                g30Var.d = constraintWidget$DimensionBehaviour8;
                int i = yhVar.j;
                g30Var.a = i;
                i41 i41Var = yhVar.e;
                i41Var.d = constraintWidget$DimensionBehaviour9;
                int i2 = yhVar.k;
                i41Var.a = i2;
                Iterator it2 = it;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour12 = ConstraintWidget$DimensionBehaviour.MATCH_PARENT;
                if ((constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour12 || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour8 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour12 || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.FIXED || constraintWidget$DimensionBehaviour9 == ConstraintWidget$DimensionBehaviour.WRAP_CONTENT)) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour13 = constraintWidget$DimensionBehaviour9;
                    int iM = yhVar.m();
                    if (constraintWidget$DimensionBehaviour8 == constraintWidget$DimensionBehaviour12) {
                        iM = (zhVar.m() - mhVar2.e) - mhVar.e;
                        constraintWidget$DimensionBehaviour8 = ConstraintWidget$DimensionBehaviour.FIXED;
                    }
                    int i3 = iM;
                    int iJ = yhVar.j();
                    if (constraintWidget$DimensionBehaviour13 == constraintWidget$DimensionBehaviour12) {
                        iJ = (zhVar.j() - mhVar4.e) - mhVar3.e;
                        constraintWidget$DimensionBehaviour13 = ConstraintWidget$DimensionBehaviour.FIXED;
                    }
                    f(yhVar, constraintWidget$DimensionBehaviour8, i3, constraintWidget$DimensionBehaviour13, iJ);
                    g30Var.e.d(yhVar.m());
                    i41Var.e.d(yhVar.j());
                    yhVar.a = true;
                } else {
                    mh[] mhVarArr = yhVar.G;
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour14 = constraintWidget$DimensionBehaviour8;
                    if (constraintWidget$DimensionBehaviour8 != constraintWidget$DimensionBehaviour11 || (constraintWidget$DimensionBehaviour9 != (constraintWidget$DimensionBehaviour7 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && constraintWidget$DimensionBehaviour9 != ConstraintWidget$DimensionBehaviour.FIXED)) {
                        f = f3;
                        constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour9;
                    } else if (i == 3) {
                        if (constraintWidget$DimensionBehaviour9 == constraintWidget$DimensionBehaviour7) {
                            f(yhVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour7, 0);
                        }
                        int iJ2 = yhVar.j();
                        int i4 = (int) ((iJ2 * yhVar.N) + 0.5f);
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour15 = ConstraintWidget$DimensionBehaviour.FIXED;
                        f(yhVar, constraintWidget$DimensionBehaviour15, i4, constraintWidget$DimensionBehaviour15, iJ2);
                        g30Var.e.d(yhVar.m());
                        i41Var.e.d(yhVar.j());
                        yhVar.a = true;
                    } else if (i == 1) {
                        f(yhVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour9, 0);
                        g30Var.e.m = yhVar.m();
                    } else {
                        constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour9;
                        if (i == 2) {
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour16 = zhVar.J[0];
                            f = f3;
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour17 = ConstraintWidget$DimensionBehaviour.FIXED;
                            if (constraintWidget$DimensionBehaviour16 == constraintWidget$DimensionBehaviour17 || constraintWidget$DimensionBehaviour16 == constraintWidget$DimensionBehaviour12) {
                                f(yhVar, constraintWidget$DimensionBehaviour17, (int) ((f2 * zhVar.m()) + 0.5f), constraintWidget$DimensionBehaviour, yhVar.j());
                                g30Var.e.d(yhVar.m());
                                i41Var.e.d(yhVar.j());
                                yhVar.a = true;
                            }
                        } else {
                            f = f3;
                            if (mhVarArr[0].d == null || mhVarArr[1].d == null) {
                                f(yhVar, constraintWidget$DimensionBehaviour7, 0, constraintWidget$DimensionBehaviour, 0);
                                g30Var.e.d(yhVar.m());
                                i41Var.e.d(yhVar.j());
                                yhVar.a = true;
                            }
                        }
                    }
                    if (constraintWidget$DimensionBehaviour != constraintWidget$DimensionBehaviour11 || (constraintWidget$DimensionBehaviour14 != (constraintWidget$DimensionBehaviour6 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT) && constraintWidget$DimensionBehaviour14 != ConstraintWidget$DimensionBehaviour.FIXED)) {
                        constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviour14;
                    } else if (i2 == 3) {
                        if (constraintWidget$DimensionBehaviour14 == constraintWidget$DimensionBehaviour6) {
                            f(yhVar, constraintWidget$DimensionBehaviour6, 0, constraintWidget$DimensionBehaviour6, 0);
                        }
                        int iM2 = yhVar.m();
                        float f4 = yhVar.N;
                        if (yhVar.O == -1) {
                            f4 = 1.0f / f4;
                        }
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour18 = ConstraintWidget$DimensionBehaviour.FIXED;
                        f(yhVar, constraintWidget$DimensionBehaviour18, iM2, constraintWidget$DimensionBehaviour18, (int) ((iM2 * f4) + 0.5f));
                        g30Var.e.d(yhVar.m());
                        i41Var.e.d(yhVar.j());
                        yhVar.a = true;
                    } else {
                        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour19 = constraintWidget$DimensionBehaviour;
                        if (i2 == 1) {
                            f(yhVar, constraintWidget$DimensionBehaviour14, 0, constraintWidget$DimensionBehaviour6, 0);
                            i41Var.e.m = yhVar.j();
                        } else {
                            constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviour14;
                            if (i2 == 2) {
                                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour20 = zhVar.J[1];
                                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour21 = ConstraintWidget$DimensionBehaviour.FIXED;
                                if (constraintWidget$DimensionBehaviour20 == constraintWidget$DimensionBehaviour21 || constraintWidget$DimensionBehaviour20 == constraintWidget$DimensionBehaviour12) {
                                    f(yhVar, constraintWidget$DimensionBehaviour2, yhVar.m(), constraintWidget$DimensionBehaviour21, (int) ((zhVar.j() * f) + 0.5f));
                                    g30Var.e.d(yhVar.m());
                                    i41Var.e.d(yhVar.j());
                                    yhVar.a = true;
                                }
                                constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour19;
                            } else {
                                if (mhVarArr[2].d == null || mhVarArr[3].d == null) {
                                    f(yhVar, constraintWidget$DimensionBehaviour6, 0, constraintWidget$DimensionBehaviour19, 0);
                                    g30Var.e.d(yhVar.m());
                                    i41Var.e.d(yhVar.j());
                                    yhVar.a = true;
                                }
                                constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviour19;
                            }
                        }
                    }
                    if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour11 && constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour11) {
                        if (i == 1 || i2 == 1) {
                            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour22 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                            f(yhVar, constraintWidget$DimensionBehaviour22, 0, constraintWidget$DimensionBehaviour22, 0);
                            g30Var.e.m = yhVar.m();
                            i41Var.e.m = yhVar.j();
                        } else if (i2 == 2 && i == 2 && (((constraintWidget$DimensionBehaviour3 = (constraintWidget$DimensionBehaviourArr = zhVar.J)[0]) == (constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.FIXED) || constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour4) && ((constraintWidget$DimensionBehaviour5 = constraintWidget$DimensionBehaviourArr[1]) == constraintWidget$DimensionBehaviour4 || constraintWidget$DimensionBehaviour5 == constraintWidget$DimensionBehaviour4))) {
                            f(yhVar, constraintWidget$DimensionBehaviour4, (int) ((f2 * zhVar.m()) + 0.5f), constraintWidget$DimensionBehaviour4, (int) ((f * zhVar.j()) + 0.5f));
                            g30Var.e.d(yhVar.m());
                            i41Var.e.d(yhVar.j());
                            yhVar.a = true;
                        }
                    }
                }
                it = it2;
            }
        }
    }

    public final void c() {
        ArrayList<q71> arrayList = this.e;
        arrayList.clear();
        zh zhVar = this.d;
        zhVar.d.f();
        i41 i41Var = zhVar.e;
        i41Var.f();
        arrayList.add(zhVar.d);
        arrayList.add(i41Var);
        HashSet hashSet = null;
        for (yh yhVar : zhVar.g0) {
            if (yhVar instanceof o20) {
                p20 p20Var = new p20(yhVar);
                yhVar.d.f();
                yhVar.e.f();
                p20Var.f = ((o20) yhVar).k0;
                arrayList.add(p20Var);
            } else {
                if (yhVar.r()) {
                    if (yhVar.b == null) {
                        yhVar.b = new xc(yhVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(yhVar.b);
                } else {
                    arrayList.add(yhVar.d);
                }
                if (yhVar.s()) {
                    if (yhVar.c == null) {
                        yhVar.c = new xc(yhVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(yhVar.c);
                } else {
                    arrayList.add(yhVar.e);
                }
                if (yhVar instanceof b30) {
                    arrayList.add(new a30(yhVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((q71) it.next()).f();
        }
        for (q71 q71Var : arrayList) {
            if (q71Var.b != zhVar) {
                q71Var.d();
            }
        }
        ArrayList arrayList2 = this.h;
        arrayList2.clear();
        zh zhVar2 = this.a;
        e(zhVar2.d, 0, arrayList2);
        e(zhVar2.e, 1, arrayList2);
        this.b = false;
    }

    public final int d(zh zhVar, int i) {
        ArrayList arrayList;
        int i2;
        int i3;
        long jMax;
        float f;
        zh zhVar2 = zhVar;
        ArrayList arrayList2 = this.h;
        int size = arrayList2.size();
        int i4 = 0;
        long jMax2 = 0;
        while (i4 < size) {
            q71 q71Var = ((wp0) arrayList2.get(i4)).a;
            if (!(q71Var instanceof xc) ? !(i != 0 ? (q71Var instanceof i41) : (q71Var instanceof g30)) : ((xc) q71Var).f != i) {
                ao aoVar = (i == 0 ? zhVar2.d : zhVar2.e).h;
                ao aoVar2 = (i == 0 ? zhVar2.d : zhVar2.e).i;
                boolean zContains = q71Var.h.l.contains(aoVar);
                ao aoVar3 = q71Var.i;
                boolean zContains2 = aoVar3.l.contains(aoVar2);
                long j = q71Var.j();
                ao aoVar4 = q71Var.h;
                if (zContains && zContains2) {
                    long jB = wp0.b(aoVar4, 0L);
                    ArrayList arrayList3 = arrayList2;
                    i2 = size;
                    long jA = wp0.a(aoVar3, 0L);
                    long j2 = jB - j;
                    int i5 = aoVar3.f;
                    arrayList = arrayList3;
                    i3 = i4;
                    if (j2 >= (-i5)) {
                        j2 += (long) i5;
                    }
                    long j3 = (-jA) - j;
                    long j4 = aoVar4.f;
                    long j5 = j3 - j4;
                    if (j5 >= j4) {
                        j5 -= j4;
                    }
                    yh yhVar = q71Var.b;
                    if (i == 0) {
                        f = yhVar.U;
                    } else if (i == 1) {
                        f = yhVar.V;
                    } else {
                        yhVar.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j2 / (1.0f - f)) + (j5 / f)) : 0L;
                    jMax = (((long) aoVar4.f) + ((((long) ((f2 * f) + 0.5f)) + j) + ((long) zn.d(1.0f, f, f2, 0.5f)))) - ((long) aoVar3.f);
                } else {
                    arrayList = arrayList2;
                    i2 = size;
                    i3 = i4;
                    jMax = zContains ? Math.max(wp0.b(aoVar4, aoVar4.f), ((long) aoVar4.f) + j) : zContains2 ? Math.max(-wp0.a(aoVar3, aoVar3.f), ((long) (-aoVar3.f)) + j) : (q71Var.j() + ((long) aoVar4.f)) - ((long) aoVar3.f);
                }
            } else {
                arrayList = arrayList2;
                i2 = size;
                i3 = i4;
                jMax = 0;
            }
            jMax2 = Math.max(jMax2, jMax);
            i4 = i3 + 1;
            zhVar2 = zhVar;
            size = i2;
            arrayList2 = arrayList;
        }
        return (int) jMax2;
    }

    public final void e(q71 q71Var, int i, ArrayList arrayList) {
        ao aoVar;
        Iterator it = q71Var.h.k.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            aoVar = q71Var.i;
            if (!zHasNext) {
                break;
            }
            xn xnVar = (xn) it.next();
            if (xnVar instanceof ao) {
                a((ao) xnVar, i, arrayList, null);
            } else if (xnVar instanceof q71) {
                a(((q71) xnVar).h, i, arrayList, null);
            }
        }
        for (xn xnVar2 : aoVar.k) {
            if (xnVar2 instanceof ao) {
                a((ao) xnVar2, i, arrayList, null);
            } else if (xnVar2 instanceof q71) {
                a(((q71) xnVar2).i, i, arrayList, null);
            }
        }
        if (i == 1) {
            for (xn xnVar3 : ((i41) q71Var).k.k) {
                if (xnVar3 instanceof ao) {
                    a((ao) xnVar3, i, arrayList, null);
                }
            }
        }
    }

    public final void f(yh yhVar, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, int i, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, int i2) {
        ga gaVar = this.g;
        gaVar.a = constraintWidget$DimensionBehaviour;
        gaVar.b = constraintWidget$DimensionBehaviour2;
        gaVar.c = i;
        gaVar.d = i2;
        ((b) this.f).a(yhVar, gaVar);
        yhVar.z(gaVar.e);
        yhVar.w(gaVar.f);
        yhVar.w = gaVar.h;
        int i3 = gaVar.g;
        yhVar.R = i3;
        yhVar.w = i3 > 0;
    }

    public final void g() {
        ea eaVar;
        for (yh yhVar : this.a.g0) {
            if (!yhVar.a) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = yhVar.J;
                boolean z = false;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
                int i = yhVar.j;
                int i2 = yhVar.k;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                boolean z2 = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && i == 1);
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT && i2 == 1)) {
                    z = true;
                }
                g30 g30Var = yhVar.d;
                so soVar = g30Var.e;
                boolean z3 = soVar.j;
                i41 i41Var = yhVar.e;
                so soVar2 = i41Var.e;
                boolean z4 = soVar2.j;
                if (z3 && z4) {
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.FIXED;
                    f(yhVar, constraintWidget$DimensionBehaviour4, soVar.g, constraintWidget$DimensionBehaviour4, soVar2.g);
                    yhVar.a = true;
                } else if (z3 && z) {
                    f(yhVar, ConstraintWidget$DimensionBehaviour.FIXED, soVar.g, constraintWidget$DimensionBehaviour3, soVar2.g);
                    if (constraintWidget$DimensionBehaviour2 == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                        i41Var.e.m = yhVar.j();
                    } else {
                        i41Var.e.d(yhVar.j());
                        yhVar.a = true;
                    }
                } else if (z4 && z2) {
                    f(yhVar, constraintWidget$DimensionBehaviour3, soVar.g, ConstraintWidget$DimensionBehaviour.FIXED, soVar2.g);
                    if (constraintWidget$DimensionBehaviour == ConstraintWidget$DimensionBehaviour.MATCH_CONSTRAINT) {
                        g30Var.e.m = yhVar.m();
                    } else {
                        g30Var.e.d(yhVar.m());
                        yhVar.a = true;
                    }
                }
                if (yhVar.a && (eaVar = i41Var.l) != null) {
                    eaVar.d(yhVar.R);
                }
            }
        }
    }
}
