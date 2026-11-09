package com.google.android.material.snackbar;

import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;
import defpackage.g2;
import defpackage.qm;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$Behavior extends SwipeDismissBehavior<View> {
    public final qm i;

    public BaseTransientBottomBar$Behavior() {
        qm qmVar = new qm();
        this.f = Math.min(Math.max(0.0f, 0.1f), 1.0f);
        this.g = Math.min(Math.max(0.0f, 0.6f), 1.0f);
        this.e = 0;
        this.i = qmVar;
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final boolean k(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        this.i.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                if (g2.b == null) {
                    g2.b = new g2();
                }
                synchronized (g2.b.a) {
                }
            }
        } else if (coordinatorLayout.t(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
            if (g2.b == null) {
                g2.b = new g2();
            }
            synchronized (g2.b.a) {
            }
        }
        return super.k(coordinatorLayout, view, motionEvent);
    }

    @Override // com.google.android.material.behavior.SwipeDismissBehavior
    public final boolean w(View view) {
        this.i.getClass();
        return view instanceof BaseTransientBottomBar$SnackbarBaseLayout;
    }
}
