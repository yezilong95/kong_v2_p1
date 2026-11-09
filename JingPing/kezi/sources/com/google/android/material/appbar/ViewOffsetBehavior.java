package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.ms0;

/* JADX INFO: loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public ms0 b;
    public int c;

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        y(coordinatorLayout, view, i);
        if (this.b == null) {
            this.b = new ms0(view);
        }
        ms0 ms0Var = this.b;
        View view2 = ms0Var.a;
        ms0Var.b = view2.getTop();
        ms0Var.c = view2.getLeft();
        this.b.a();
        int i2 = this.c;
        if (i2 == 0) {
            return true;
        }
        this.b.b(i2);
        this.c = 0;
        return true;
    }

    public int w() {
        ms0 ms0Var = this.b;
        if (ms0Var != null) {
            return ms0Var.d;
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
        ms0 ms0Var = this.b;
        if (ms0Var != null) {
            return ms0Var.b(i);
        }
        this.c = i;
        return false;
    }

    public ViewOffsetBehavior() {
        this.c = 0;
    }
}
