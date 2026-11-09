package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.am0;
import defpackage.bm0;
import defpackage.cs0;
import defpackage.d90;
import defpackage.j0;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public cs0 b;
    public boolean c;
    public boolean d;
    public int e = 2;
    public float f = 0.0f;
    public float g = 0.5f;
    public final am0 h = new am0(this);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean zT = this.c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zT = coordinatorLayout.t(view, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.c = zT;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.c = false;
        }
        if (zT) {
            if (this.b == null) {
                this.b = new cs0(coordinatorLayout.getContext(), coordinatorLayout, this.h);
            }
            if (!this.d && this.b.r(motionEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
        WeakHashMap weakHashMap = xr0.a;
        if (view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(1);
            xr0.m(view, 1048576);
            xr0.i(view, 0);
            if (w(view)) {
                xr0.n(view, j0.l, null, new d90(this));
            }
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean v(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (this.b == null) {
            return false;
        }
        if (this.d && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.b.k(motionEvent);
        return true;
    }

    public boolean w(View view) {
        return true;
    }

    public void setListener(bm0 bm0Var) {
    }
}
