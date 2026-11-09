package com.google.android.material.behavior;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import defpackage.f30;
import defpackage.s90;
import defpackage.sv;
import defpackage.y4;
import defpackage.z2;
import defpackage.zn;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int N = R$attr.motionDurationLong2;
    public static final int O = R$attr.motionDurationMedium4;
    public static final int P = R$attr.motionEasingEmphasizedInterpolator;
    public final LinkedHashSet E;
    public int F;
    public int G;
    public TimeInterpolator H;
    public TimeInterpolator I;
    public int J;
    public int K;
    public int L;
    public ViewPropertyAnimator M;

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.E = new LinkedHashSet();
        this.J = 0;
        this.K = 2;
        this.L = 0;
    }

    public void addOnScrollStateChangedListener(f30 f30Var) {
        this.E.add(f30Var);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.J = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.F = s90.U(view.getContext(), N, 225);
        this.G = s90.U(view.getContext(), O, 175);
        Context context = view.getContext();
        sv svVar = y4.d;
        int i2 = P;
        this.H = s90.V(context, i2, svVar);
        this.I = s90.V(view.getContext(), i2, y4.c);
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        LinkedHashSet linkedHashSet = this.E;
        if (i > 0) {
            if (this.K == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.M;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.K = 1;
            Iterator it = linkedHashSet.iterator();
            if (it.hasNext()) {
                throw zn.i(it);
            }
            this.M = view.animate().translationY(this.J + this.L).setInterpolator(this.I).setDuration(this.G).setListener(new z2(6, this));
            return;
        }
        if (i >= 0 || this.K == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.M;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
            view.clearAnimation();
        }
        this.K = 2;
        Iterator it2 = linkedHashSet.iterator();
        if (it2.hasNext()) {
            throw zn.i(it2);
        }
        this.M = view.animate().translationY(0).setInterpolator(this.H).setDuration(this.F).setListener(new z2(6, this));
    }

    public void removeOnScrollStateChangedListener(f30 f30Var) {
        this.E.remove(f30Var);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        return i == 2;
    }

    public HideBottomViewOnScrollBehavior() {
        this.E = new LinkedHashSet();
        this.J = 0;
        this.K = 2;
        this.L = 0;
    }
}
