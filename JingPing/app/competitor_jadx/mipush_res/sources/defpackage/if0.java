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
public final class if0 {
    public zh a = new zh();
    public zh b = new zh();
    public d c = null;
    public d d = null;
    public int e;
    public int f;
    public final /* synthetic */ MotionLayout g;

    public if0(MotionLayout motionLayout) {
        this.g = motionLayout;
    }

    public static void b(zh zhVar, zh zhVar2) {
        ArrayList<yh> arrayList = zhVar.g0;
        HashMap map = new HashMap();
        map.put(zhVar, zhVar2);
        zhVar2.g0.clear();
        zhVar2.f(zhVar, map);
        for (yh yhVar : arrayList) {
            yh q9Var = yhVar instanceof q9 ? new q9() : yhVar instanceof o20 ? new o20() : yhVar instanceof cx ? new cx() : yhVar instanceof b30 ? new b30() : new yh();
            zhVar2.g0.add(q9Var);
            yh yhVar2 = q9Var.K;
            if (yhVar2 != null) {
                ((zh) yhVar2).g0.remove(q9Var);
                q9Var.K = null;
            }
            q9Var.K = zhVar2;
            map.put(yhVar, q9Var);
        }
        for (yh yhVar3 : arrayList) {
            ((yh) map.get(yhVar3)).f(yhVar3, map);
        }
    }

    public static yh c(zh zhVar, View view) {
        if (zhVar.W == view) {
            return zhVar;
        }
        ArrayList arrayList = zhVar.g0;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            yh yhVar = (yh) arrayList.get(i);
            if (yhVar.W == view) {
                return yhVar;
            }
        }
        return null;
    }

    public final void a() {
        int i;
        MotionLayout motionLayout = this.g;
        int childCount = motionLayout.getChildCount();
        motionLayout.g0.clear();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = motionLayout.getChildAt(i3);
            motionLayout.g0.put(childAt, new ef0(childAt));
        }
        while (i2 < childCount) {
            View childAt2 = motionLayout.getChildAt(i2);
            ef0 ef0Var = (ef0) motionLayout.g0.get(childAt2);
            if (ef0Var == null) {
                i = childCount;
            } else {
                if (this.c != null) {
                    yh yhVarC = c(this.a, childAt2);
                    if (yhVarC != null) {
                        d dVar = this.c;
                        mf0 mf0Var = ef0Var.d;
                        mf0Var.G = 0.0f;
                        mf0Var.H = 0.0f;
                        ef0Var.d(mf0Var);
                        float fN = yhVarC.n();
                        float fO = yhVarC.o();
                        float fM = yhVarC.m();
                        i = childCount;
                        float fJ = yhVarC.j();
                        mf0Var.I = fN;
                        mf0Var.J = fO;
                        mf0Var.K = fM;
                        mf0Var.L = fJ;
                        c cVarG = dVar.g(ef0Var.b);
                        mf0Var.a(cVarG);
                        ef0Var.j = cVarG.c.f;
                        ef0Var.f.c(yhVarC, dVar, ef0Var.b);
                    } else {
                        i = childCount;
                        if (motionLayout.q0 != 0) {
                            Log.e("MotionLayout", t40.d0() + "no widget for  " + t40.g0(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                } else {
                    i = childCount;
                }
                if (this.d != null) {
                    yh yhVarC2 = c(this.b, childAt2);
                    if (yhVarC2 != null) {
                        d dVar2 = this.d;
                        mf0 mf0Var2 = ef0Var.e;
                        mf0Var2.G = 1.0f;
                        mf0Var2.H = 1.0f;
                        ef0Var.d(mf0Var2);
                        float fN2 = yhVarC2.n();
                        float fO2 = yhVarC2.o();
                        float fM2 = yhVarC2.m();
                        float fJ2 = yhVarC2.j();
                        mf0Var2.I = fN2;
                        mf0Var2.J = fO2;
                        mf0Var2.K = fM2;
                        mf0Var2.L = fJ2;
                        mf0Var2.a(dVar2.g(ef0Var.b));
                        ef0Var.g.c(yhVarC2, dVar2, ef0Var.b);
                    } else if (motionLayout.q0 != 0) {
                        Log.e("MotionLayout", t40.d0() + "no widget for  " + t40.g0(childAt2) + " (" + childAt2.getClass().getName() + ")");
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
        this.a = new zh();
        zh zhVar = new zh();
        this.b = zhVar;
        zh zhVar2 = this.a;
        int i = MotionLayout.d1;
        MotionLayout motionLayout = this.g;
        zh zhVar3 = motionLayout.G;
        ha haVar = zhVar3.j0;
        zhVar2.j0 = haVar;
        zhVar2.i0.f = haVar;
        ha haVar2 = zhVar3.j0;
        zhVar.j0 = haVar2;
        zhVar.i0.f = haVar2;
        zhVar2.g0.clear();
        this.b.g0.clear();
        b(motionLayout.G, this.a);
        b(motionLayout.G, this.b);
        if (motionLayout.k0 > 0.5d) {
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
                zh zhVar4 = this.a;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                zhVar4.x(constraintWidget$DimensionBehaviour);
                this.b.x(constraintWidget$DimensionBehaviour);
            }
            if (layoutParams.height == -2) {
                zh zhVar5 = this.a;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.WRAP_CONTENT;
                zhVar5.y(constraintWidget$DimensionBehaviour2);
                this.b.y(constraintWidget$DimensionBehaviour2);
            }
        }
    }

    public final void e() {
        zh zhVar;
        boolean z;
        MotionLayout motionLayout = this.g;
        int i = motionLayout.d0;
        int i2 = motionLayout.e0;
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        motionLayout.R0 = mode;
        motionLayout.S0 = mode2;
        int optimizationLevel = motionLayout.getOptimizationLevel();
        if (motionLayout.b0 == motionLayout.getStartState()) {
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
            motionLayout.R0 = mode;
            motionLayout.S0 = mode2;
            if (motionLayout.b0 == motionLayout.getStartState()) {
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
            motionLayout.N0 = this.a.m();
            motionLayout.O0 = this.a.j();
            motionLayout.P0 = this.b.m();
            int iJ = this.b.j();
            motionLayout.Q0 = iJ;
            motionLayout.M0 = (motionLayout.N0 == motionLayout.P0 && motionLayout.O0 == iJ) ? false : true;
        }
        int i4 = motionLayout.N0;
        int i5 = motionLayout.O0;
        int i6 = motionLayout.R0;
        if (i6 == Integer.MIN_VALUE || i6 == 0) {
            i4 = (int) ((motionLayout.T0 * (motionLayout.P0 - i4)) + i4);
        }
        int i7 = motionLayout.S0;
        if (i7 == Integer.MIN_VALUE || i7 == 0) {
            i5 = (int) ((motionLayout.T0 * (motionLayout.Q0 - i5)) + i5);
        }
        zh zhVar2 = this.a;
        if (zhVar2.t0 || this.b.t0) {
            zhVar = zhVar2;
            z = true;
        } else {
            zhVar = zhVar2;
            z = false;
        }
        motionLayout.m(i, i2, i4, i5, z, zhVar.u0 || this.b.u0);
        int childCount = motionLayout.getChildCount();
        motionLayout.Y0.a();
        motionLayout.o0 = true;
        motionLayout.getWidth();
        motionLayout.getHeight();
        pf0 pf0Var = motionLayout.U.c;
        int i8 = pf0Var != null ? pf0Var.p : -1;
        HashMap map = motionLayout.g0;
        if (i8 != -1) {
            for (int i9 = 0; i9 < childCount; i9++) {
                ef0 ef0Var = (ef0) map.get(motionLayout.getChildAt(i9));
                if (ef0Var != null) {
                    ef0Var.y = i8;
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            ef0 ef0Var2 = (ef0) map.get(motionLayout.getChildAt(i10));
            if (ef0Var2 != null) {
                motionLayout.U.e(ef0Var2);
                ef0Var2.e(motionLayout.getNanoTime());
            }
        }
        pf0 pf0Var2 = motionLayout.U.c;
        float f = pf0Var2 != null ? pf0Var2.i : 0.0f;
        if (f != 0.0f) {
            boolean z2 = ((double) f) < 0.0d;
            float fAbs = Math.abs(f);
            float fMax = -3.4028235E38f;
            float fMin = Float.MAX_VALUE;
            float fMax2 = -3.4028235E38f;
            float fMin2 = Float.MAX_VALUE;
            for (int i11 = 0; i11 < childCount; i11++) {
                ef0 ef0Var3 = (ef0) map.get(motionLayout.getChildAt(i11));
                if (!Float.isNaN(ef0Var3.j)) {
                    for (int i12 = 0; i12 < childCount; i12++) {
                        ef0 ef0Var4 = (ef0) map.get(motionLayout.getChildAt(i12));
                        if (!Float.isNaN(ef0Var4.j)) {
                            fMin = Math.min(fMin, ef0Var4.j);
                            fMax = Math.max(fMax, ef0Var4.j);
                        }
                    }
                    while (i3 < childCount) {
                        ef0 ef0Var5 = (ef0) map.get(motionLayout.getChildAt(i3));
                        if (!Float.isNaN(ef0Var5.j)) {
                            ef0Var5.l = 1.0f / (1.0f - fAbs);
                            if (z2) {
                                ef0Var5.k = fAbs - (((fMax - ef0Var5.j) / (fMax - fMin)) * fAbs);
                            } else {
                                ef0Var5.k = fAbs - (((ef0Var5.j - fMin) * fAbs) / (fMax - fMin));
                            }
                        }
                        i3++;
                    }
                    return;
                }
                mf0 mf0Var = ef0Var3.e;
                float f2 = mf0Var.I;
                float f3 = mf0Var.J;
                float f4 = z2 ? f3 - f2 : f3 + f2;
                fMin2 = Math.min(fMin2, f4);
                fMax2 = Math.max(fMax2, f4);
            }
            while (i3 < childCount) {
                ef0 ef0Var6 = (ef0) map.get(motionLayout.getChildAt(i3));
                mf0 mf0Var2 = ef0Var6.e;
                float f5 = mf0Var2.I;
                float f6 = mf0Var2.J;
                float f7 = z2 ? f6 - f5 : f6 + f5;
                ef0Var6.l = 1.0f / (1.0f - fAbs);
                ef0Var6.k = fAbs - (((f7 - fMin2) * fAbs) / (fMax2 - fMin2));
                i3++;
            }
        }
    }

    public final void f(zh zhVar, d dVar) {
        SparseArray sparseArray = new SparseArray();
        Constraints.LayoutParams layoutParams = new Constraints.LayoutParams();
        sparseArray.clear();
        sparseArray.put(0, zhVar);
        MotionLayout motionLayout = this.g;
        sparseArray.put(motionLayout.getId(), zhVar);
        for (yh yhVar : zhVar.g0) {
            sparseArray.put(yhVar.W.getId(), yhVar);
        }
        for (yh yhVar2 : zhVar.g0) {
            View view = yhVar2.W;
            int id = view.getId();
            HashMap map = dVar.c;
            if (map.containsKey(Integer.valueOf(id))) {
                ((c) map.get(Integer.valueOf(id))).a(layoutParams);
            }
            yhVar2.z(dVar.g(view.getId()).d.c);
            yhVar2.w(dVar.g(view.getId()).d.d);
            if (view instanceof ConstraintHelper) {
                ConstraintHelper constraintHelper = (ConstraintHelper) view;
                int id2 = constraintHelper.getId();
                HashMap map2 = dVar.c;
                if (map2.containsKey(Integer.valueOf(id2))) {
                    c cVar = (c) map2.get(Integer.valueOf(id2));
                    if (yhVar2 instanceof b30) {
                        constraintHelper.h(cVar, (b30) yhVar2, layoutParams, sparseArray);
                    }
                }
                if (view instanceof Barrier) {
                    ((Barrier) view).l();
                }
            }
            layoutParams.resolveLayoutDirection(motionLayout.getLayoutDirection());
            int i = MotionLayout.d1;
            motionLayout.d(false, view, yhVar2, layoutParams, sparseArray);
            if (dVar.g(view.getId()).b.c == 1) {
                yhVar2.X = view.getVisibility();
            } else {
                yhVar2.X = dVar.g(view.getId()).b.b;
            }
        }
        for (yh yhVar3 : zhVar.g0) {
            if (yhVar3 instanceof cx) {
                ConstraintHelper constraintHelper2 = (ConstraintHelper) yhVar3.W;
                b30 b30Var = (b30) yhVar3;
                constraintHelper2.getClass();
                b30Var.h0 = 0;
                Arrays.fill(b30Var.g0, (Object) null);
                for (int i2 = 0; i2 < constraintHelper2.F; i2++) {
                    b30Var.C((yh) sparseArray.get(constraintHelper2.E[i2]));
                }
                cx cxVar = (cx) b30Var;
                for (int i3 = 0; i3 < cxVar.h0; i3++) {
                    yh yhVar4 = cxVar.g0[i3];
                }
            }
        }
    }
}
