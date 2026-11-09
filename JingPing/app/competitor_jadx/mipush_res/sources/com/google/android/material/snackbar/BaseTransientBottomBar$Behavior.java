package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import defpackage.cw0;
import defpackage.is;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {
    public final is L;

    public BaseTransientBottomBar$Behavior() {
        is isVar = new is(10);
        this.I = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        this.J = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        this.H = 0;
        this.L = isVar;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        this.L.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (cw0.G == null) {
                    cw0.G = new cw0(0);
                }
                synchronized (cw0.G.F) {
                }
            }
        } else if (coordinatorLayout.t(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (cw0.G == null) {
                cw0.G = new cw0(0);
            }
            synchronized (cw0.G.F) {
            }
        }
        return super.k(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean w(View view) {
        this.L.getClass();
        return view instanceof BaseTransientBottomBar$SnackbarBaseLayout;
    }
}
