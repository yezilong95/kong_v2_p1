package defpackage;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.c;
import androidx.constraintlayout.widget.d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class n70 {
    public ng a = new ng();
    public ng b = new ng();
    public d c = null;
    public d d = null;
    public int e;
    public int f;
    public final /* synthetic */ MotionLayout g;

    public n70(MotionLayout motionLayout) {
        this.g = motionLayout;
    }

    public static void b(ng ngVar, ng ngVar2) {
        ArrayList<mg> arrayList = ngVar.g0;
        HashMap map = new HashMap();
        map.put(ngVar, ngVar2);
        ngVar2.g0.clear();
        ngVar2.f(ngVar, map);
        for (mg mgVar : arrayList) {
            mg v7Var = mgVar instanceof v7 ? new v7() : mgVar instanceof kv ? new kv() : mgVar instanceof pq ? new pq() : mgVar instanceof vv ? new vv() : new mg();
            ngVar2.g0.add(v7Var);
            mg mgVar2 = v7Var.K;
            if (mgVar2 != null) {
                ((ng) mgVar2).g0.remove(v7Var);
                v7Var.K = null;
            }
            v7Var.K = ngVar2;
            map.put(mgVar, v7Var);
        }
        for (mg mgVar3 : arrayList) {
            ((mg) map.get(mgVar3)).f(mgVar3, map);
        }
    }

    public static mg c(ng ngVar, View view) {
        if (ngVar.W == view) {
            return ngVar;
        }
        ArrayList arrayList = ngVar.g0;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            mg mgVar = (mg) arrayList.get(i);
            if (mgVar.W == view) {
                return mgVar;
            }
        }
        return null;
    }

    public final void a() {
        int i;
        MotionLayout motionLayout = this.g;
        int childCount = motionLayout.getChildCount();
        motionLayout.A.clear();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = motionLayout.getChildAt(i3);
            motionLayout.A.put(childAt, new j70(childAt));
        }
        while (i2 < childCount) {
            View childAt2 = motionLayout.getChildAt(i2);
            j70 j70Var = (j70) motionLayout.A.get(childAt2);
            if (j70Var == null) {
                i = childCount;
            } else {
                if (this.c != null) {
                    mg mgVarC = c(this.a, childAt2);
                    if (mgVarC != null) {
                        d dVar = this.c;
                        s70 s70Var = j70Var.d;
                        s70Var.d = 0.0f;
                        s70Var.e = 0.0f;
                        j70Var.d(s70Var);
                        float fN = mgVarC.n();
                        float fO = mgVarC.o();
                        float fM = mgVarC.m();
                        i = childCount;
                        float fJ = mgVarC.j();
                        s70Var.f = fN;
                        s70Var.g = fO;
                        s70Var.h = fM;
                        s70Var.i = fJ;
                        c cVarG = dVar.g(j70Var.b);
                        s70Var.a(cVarG);
                        j70Var.j = cVarG.c.f;
                        j70Var.f.c(mgVarC, dVar, j70Var.b);
                    } else {
                        i = childCount;
                        if (motionLayout.K != 0) {
                            Log.e("MotionLayout", oy.v() + "no widget for  " + oy.x(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                } else {
                    i = childCount;
                }
                if (this.d != null) {
                    mg mgVarC2 = c(this.b, childAt2);
                    if (mgVarC2 != null) {
                        d dVar2 = this.d;
                        s70 s70Var2 = j70Var.e;
                        s70Var2.d = 1.0f;
                        s70Var2.e = 1.0f;
                        j70Var.d(s70Var2);
                        float fN2 = mgVarC2.n();
                        float fO2 = mgVarC2.o();
                        float fM2 = mgVarC2.m();
                        float fJ2 = mgVarC2.j();
                        s70Var2.f = fN2;
                        s70Var2.g = fO2;
                        s70Var2.h = fM2;
                        s70Var2.i = fJ2;
                        s70Var2.a(dVar2.g(j70Var.b));
                        j70Var.g.c(mgVarC2, dVar2, j70Var.b);
                    } else if (motionLayout.K != 0) {
                        Log.e("MotionLayout", oy.v() + "no widget for  " + oy.x(childAt2) + " (" + childAt2.getClass().getName() + ")");
                    }
                }
            }
            i2++;
            childCount = i;
        }
    }

    public final void d(d dVar, d dVar2) {
        this.c = dVar;
        this.d = dVar2;
        this.a = new ng();
        ng ngVar = new ng();
        this.b = ngVar;
        ng ngVar2 = this.a;
        int i = MotionLayout.A0;
        MotionLayout motionLayout = this.g;
        ng ngVar3 = motionLayout.d;
        m8 m8Var = ngVar3.j0;
        ngVar2.j0 = m8Var;
        ngVar2.i0.f = m8Var;
        m8 m8Var2 = ngVar3.j0;
        ngVar.j0 = m8Var2;
        ngVar.i0.f = m8Var2;
        ngVar2.g0.clear();
        this.b.g0.clear();
        b(motionLayout.d, this.a);
        b(motionLayout.d, this.b);
        if (motionLayout.E > 0.5d) {
            if (dVar != null) {
                f(this.a, dVar);
            }
            f(this.b, dVar2);
        } else {
            f(this.b, dVar2);
            if (dVar != null) {
                f(this.a, dVar);
            }
        }
        this.a.k0 = motionLayout.k();
        this.a.G();
        this.b.k0 = motionLayout.k();
        this.b.G();
        ViewGroup.LayoutParams layoutParams = motionLayout.getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width == -2) {
                ng ngVar4 = this.a;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                ngVar4.x(constraintWidget$DimensionBehaviour);
                this.b.x(constraintWidget$DimensionBehaviour);
            }
            if (layoutParams.height == -2) {
                ng ngVar5 = this.a;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                ngVar5.y(constraintWidget$DimensionBehaviour2);
                this.b.y(constraintWidget$DimensionBehaviour2);
            }
        }
    }

    public final void e() {
        ng ngVar;
        boolean z;
        MotionLayout motionLayout = this.g;
        int i = motionLayout.x;
        int i2 = motionLayout.y;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        motionLayout.o0 = mode;
        motionLayout.p0 = mode2;
        int optimizationLevel = motionLayout.getOptimizationLevel();
        if (motionLayout.v == motionLayout.getStartState()) {
            motionLayout.n(this.b, optimizationLevel, i, i2);
            if (this.c != null) {
                motionLayout.n(this.a, optimizationLevel, i, i2);
            }
        } else {
            if (this.c != null) {
                motionLayout.n(this.a, optimizationLevel, i, i2);
            }
            motionLayout.n(this.b, optimizationLevel, i, i2);
        }
        int i3 = 0;
        if (!(motionLayout.getParent() instanceof MotionLayout) || mode != 1073741824 || mode2 != 1073741824) {
            motionLayout.o0 = mode;
            motionLayout.p0 = mode2;
            if (motionLayout.v == motionLayout.getStartState()) {
                motionLayout.n(this.b, optimizationLevel, i, i2);
                if (this.c != null) {
                    motionLayout.n(this.a, optimizationLevel, i, i2);
                }
            } else {
                if (this.c != null) {
                    motionLayout.n(this.a, optimizationLevel, i, i2);
                }
                motionLayout.n(this.b, optimizationLevel, i, i2);
            }
            motionLayout.k0 = this.a.m();
            motionLayout.l0 = this.a.j();
            motionLayout.m0 = this.b.m();
            int iJ = this.b.j();
            motionLayout.n0 = iJ;
            motionLayout.j0 = (motionLayout.k0 == motionLayout.m0 && motionLayout.l0 == iJ) ? false : true;
        }
        int i4 = motionLayout.k0;
        int i5 = motionLayout.l0;
        int i6 = motionLayout.o0;
        if (i6 == Integer.MIN_VALUE || i6 == 0) {
            i4 = (int) ((motionLayout.q0 * (motionLayout.m0 - i4)) + i4);
        }
        int i7 = motionLayout.p0;
        if (i7 == Integer.MIN_VALUE || i7 == 0) {
            i5 = (int) ((motionLayout.q0 * (motionLayout.n0 - i5)) + i5);
        }
        ng ngVar2 = this.a;
        if (ngVar2.t0 || this.b.t0) {
            ngVar = ngVar2;
            z = true;
        } else {
            ngVar = ngVar2;
            z = false;
        }
        motionLayout.m(i, i2, i4, i5, z, ngVar.u0 || this.b.u0);
        int childCount = motionLayout.getChildCount();
        motionLayout.v0.a();
        motionLayout.I = true;
        motionLayout.getWidth();
        motionLayout.getHeight();
        v70 v70Var = motionLayout.r.c;
        int i8 = v70Var != null ? v70Var.p : -1;
        HashMap map = motionLayout.A;
        if (i8 != -1) {
            for (int i9 = 0; i9 < childCount; i9++) {
                j70 j70Var = (j70) map.get(motionLayout.getChildAt(i9));
                if (j70Var != null) {
                    j70Var.y = i8;
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            j70 j70Var2 = (j70) map.get(motionLayout.getChildAt(i10));
            if (j70Var2 != null) {
                motionLayout.r.e(j70Var2);
                j70Var2.e(motionLayout.getNanoTime());
            }
        }
        v70 v70Var2 = motionLayout.r.c;
        float f = v70Var2 != null ? v70Var2.i : 0.0f;
        if (f != 0.0f) {
            boolean z2 = ((double) f) < 0.0d;
            float fAbs = Math.abs(f);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            for (int i11 = 0; i11 < childCount; i11++) {
                j70 j70Var3 = (j70) map.get(motionLayout.getChildAt(i11));
                if (!Float.isNaN(j70Var3.j)) {
                    for (int i12 = 0; i12 < childCount; i12++) {
                        j70 j70Var4 = (j70) map.get(motionLayout.getChildAt(i12));
                        if (!Float.isNaN(j70Var4.j)) {
                            fMin = Math.min(fMin, j70Var4.j);
                            fMax = Math.max(fMax, j70Var4.j);
                        }
                    }
                    while (i3 < childCount) {
                        j70 j70Var5 = (j70) map.get(motionLayout.getChildAt(i3));
                        if (!Float.isNaN(j70Var5.j)) {
                            j70Var5.l = 1.0f / (1.0f - fAbs);
                            if (z2) {
                                j70Var5.k = fAbs - (((fMax - j70Var5.j) / (fMax - fMin)) * fAbs);
                            } else {
                                j70Var5.k = fAbs - (((j70Var5.j - fMin) * fAbs) / (fMax - fMin));
                            }
                        }
                        i3++;
                    }
                    return;
                }
                s70 s70Var = j70Var3.e;
                float f2 = s70Var.f;
                float f3 = s70Var.g;
                float f4 = z2 ? f3 - f2 : f3 + f2;
                fMin2 = Math.min(fMin2, f4);
                fMax2 = Math.max(fMax2, f4);
            }
            while (i3 < childCount) {
                j70 j70Var6 = (j70) map.get(motionLayout.getChildAt(i3));
                s70 s70Var2 = j70Var6.e;
                float f5 = s70Var2.f;
                float f6 = s70Var2.g;
                float f7 = z2 ? f6 - f5 : f6 + f5;
                j70Var6.l = 1.0f / (1.0f - fAbs);
                j70Var6.k = fAbs - (((f7 - fMin2) * fAbs) / (fMax2 - fMin2));
                i3++;
            }
        }
    }

    public final void f(ng ngVar, d dVar) {
        SparseArray sparseArray = new SparseArray();
        Constraints.LayoutParams layoutParams = new Constraints.LayoutParams();
        sparseArray.clear();
        sparseArray.put(0, ngVar);
        MotionLayout motionLayout = this.g;
        sparseArray.put(motionLayout.getId(), ngVar);
        for (mg mgVar : ngVar.g0) {
            sparseArray.put(mgVar.W.getId(), mgVar);
        }
        for (mg mgVar2 : ngVar.g0) {
            View view = mgVar2.W;
            int id = view.getId();
            HashMap map = dVar.c;
            if (map.containsKey(Integer.valueOf(id))) {
                ((c) map.get(Integer.valueOf(id))).a(layoutParams);
            }
            mgVar2.z(dVar.g(view.getId()).d.c);
            mgVar2.w(dVar.g(view.getId()).d.d);
            if (view instanceof ConstraintHelper) {
                ConstraintHelper constraintHelper = (ConstraintHelper) view;
                int id2 = constraintHelper.getId();
                HashMap map2 = dVar.c;
                if (map2.containsKey(Integer.valueOf(id2))) {
                    c cVar = (c) map2.get(Integer.valueOf(id2));
                    if (mgVar2 instanceof vv) {
                        constraintHelper.h(cVar, (vv) mgVar2, layoutParams, sparseArray);
                    }
                }
                if (view instanceof Barrier) {
                    ((Barrier) view).l();
                }
            }
            layoutParams.resolveLayoutDirection(motionLayout.getLayoutDirection());
            int i = MotionLayout.A0;
            motionLayout.a(false, view, mgVar2, layoutParams, sparseArray);
            if (dVar.g(view.getId()).b.c == 1) {
                mgVar2.X = view.getVisibility();
            } else {
                mgVar2.X = dVar.g(view.getId()).b.b;
            }
        }
        for (mg mgVar3 : ngVar.g0) {
            if (mgVar3 instanceof pq) {
                ConstraintHelper constraintHelper2 = (ConstraintHelper) mgVar3.W;
                vv vvVar = (vv) mgVar3;
                constraintHelper2.getClass();
                vvVar.h0 = 0;
                Arrays.fill(vvVar.g0, (Object) null);
                for (int i2 = 0; i2 < constraintHelper2.c; i2++) {
                    vvVar.C((mg) sparseArray.get(constraintHelper2.b[i2]));
                }
                pq pqVar = (pq) vvVar;
                for (int i3 = 0; i3 < pqVar.h0; i3++) {
                    mg mgVar4 = pqVar.g0[i3];
                }
            }
        }
    }
}
