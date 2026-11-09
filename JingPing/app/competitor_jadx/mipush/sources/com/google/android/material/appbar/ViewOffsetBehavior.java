package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.t51;

/* JADX INFO: loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public t51 E;
    public int F;

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        y(coordinatorLayout, view, i);
        if (this.E == null) {
            this.E = new t51(view);
        }
        t51 t51Var = this.E;
        View view2 = t51Var.a;
        t51Var.b = view2.getTop();
        t51Var.c = view2.getLeft();
        this.E.a();
        int i2 = this.F;
        if (i2 == 0) {
            return true;
        }
        this.E.b(i2);
        this.F = 0;
        return true;
    }

    public int w() {
        t51 t51Var = this.E;
        if (t51Var != null) {
            return t51Var.d;
        }
        return 0;
    }

    public int x() {
        return w();
    }

    public void y(CoordinatorLayout coordinatorLayout, View view, int i) {
        coordinatorLayout.v(view, i);
    }

    public boolean z(int i) {
        t51 t51Var = this.E;
        if (t51Var != null) {
            return t51Var.b(i);
        }
        this.F = i;
        return false;
    }

    public ViewOffsetBehavior() {
        this.F = 0;
    }
}
