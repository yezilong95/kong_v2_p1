package com.google.android.material.appbar;

import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {
    public final CoordinatorLayout b;
    public final View c;
    public final /* synthetic */ HeaderBehavior d;

    public g(HeaderBehavior headerBehavior, CoordinatorLayout coordinatorLayout, View view) {
        this.d = headerBehavior;
        this.b = coordinatorLayout;
        this.c = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HeaderBehavior headerBehavior;
        OverScroller overScroller;
        View view = this.c;
        if (view == null || (overScroller = (headerBehavior = this.d).e) == null) {
            return;
        }
        boolean zComputeScrollOffset = overScroller.computeScrollOffset();
        CoordinatorLayout coordinatorLayout = this.b;
        if (!zComputeScrollOffset) {
            headerBehavior.D(coordinatorLayout, view);
            return;
        }
        headerBehavior.F(coordinatorLayout, view, headerBehavior.e.getCurrY());
        WeakHashMap weakHashMap = xr0.a;
        view.postOnAnimation(this);
    }
}
