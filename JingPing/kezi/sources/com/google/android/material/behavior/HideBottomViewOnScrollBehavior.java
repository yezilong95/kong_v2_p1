package com.google.android.material.behavior;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R$attr;
import defpackage.d1;
import defpackage.hk;
import defpackage.k9;
import defpackage.mp;
import defpackage.t3;
import defpackage.xv;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public static final int k = R$attr.motionDurationLong2;
    public static final int l = R$attr.motionDurationMedium4;
    public static final int m = R$attr.motionEasingEmphasizedInterpolator;
    public final LinkedHashSet b;
    public int c;
    public int d;
    public TimeInterpolator e;
    public TimeInterpolator f;
    public int g;
    public int h;
    public int i;
    public ViewPropertyAnimator j;

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new LinkedHashSet();
        this.g = 0;
        this.h = 2;
        this.i = 0;
    }

    public void addOnScrollStateChangedListener(xv xvVar) {
        this.b.add(xvVar);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.g = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.c = k9.E(view.getContext(), k, 225);
        this.d = k9.E(view.getContext(), l, 175);
        Context context = view.getContext();
        mp mpVar = t3.d;
        int i2 = m;
        this.e = k9.F(context, i2, mpVar);
        this.f = k9.F(view.getContext(), i2, t3.c);
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        LinkedHashSet linkedHashSet = this.b;
        if (i > 0) {
            if (this.h == 1) {
                return;
            }
            ViewPropertyAnimator viewPropertyAnimator = this.j;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                view.clearAnimation();
            }
            this.h = 1;
            Iterator it = linkedHashSet.iterator();
            if (it.hasNext()) {
                throw hk.e(it);
            }
            this.j = view.animate().translationY(this.g + this.i).setInterpolator(this.f).setDuration(this.d).setListener(new d1(6, this));
            return;
        }
        if (i >= 0 || this.h == 2) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.j;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
            view.clearAnimation();
        }
        this.h = 2;
        Iterator it2 = linkedHashSet.iterator();
        if (it2.hasNext()) {
            throw hk.e(it2);
        }
        this.j = view.animate().translationY(0).setInterpolator(this.e).setDuration(this.c).setListener(new d1(6, this));
    }

    public void removeOnScrollStateChangedListener(xv xvVar) {
        this.b.remove(xvVar);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        return i == 2;
    }

    public HideBottomViewOnScrollBehavior() {
        this.b = new LinkedHashSet();
        this.g = 0;
        this.h = 2;
        this.i = 0;
    }
}
