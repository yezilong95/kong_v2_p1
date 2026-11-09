package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.cw0;
import defpackage.e2;
import defpackage.f51;
import defpackage.k51;
import defpackage.oy0;
import defpackage.py0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public k51 E;
    public boolean F;
    public boolean G;
    public int H = 2;
    public float I = 0.0f;
    public float J = 0.5f;
    public final oy0 K = new oy0(this);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean zT = this.F;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zT = coordinatorLayout.t(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.F = zT;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.F = false;
        }
        if (zT) {
            if (this.E == null) {
                this.E = new k51(coordinatorLayout.getContext(), coordinatorLayout, this.K);
            }
            if (!this.G && this.E.r(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = f51.a;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            f51.l(view, 1048576);
            f51.i(view, 0);
            if (w(view)) {
                f51.m(view, e2.l, null, new cw0(2, this));
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.E == null) {
            return false;
        }
        if (this.G && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.E.k(motionEvent);
        return true;
    }

    public boolean w(View view) {
        return true;
    }

    public void setListener(py0 py0Var) {
    }
}
