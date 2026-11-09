package com.google.android.material.appbar;

import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.f51;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {
    public final CoordinatorLayout E;
    public final View F;
    public final /* synthetic */ HeaderBehavior G;

    public e(HeaderBehavior headerBehavior, CoordinatorLayout coordinatorLayout, View view) {
        this.G = headerBehavior;
        this.E = coordinatorLayout;
        this.F = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HeaderBehavior headerBehavior;
        OverScroller overScroller;
        View view = this.F;
        if (view == null || (overScroller = (headerBehavior = this.G).H) == null) {
            return;
        }
        boolean zComputeScrollOffset = overScroller.computeScrollOffset();
        CoordinatorLayout coordinatorLayout = this.E;
        if (!zComputeScrollOffset) {
            headerBehavior.D(coordinatorLayout, view);
            return;
        }
        headerBehavior.F(coordinatorLayout, view, headerBehavior.H.getCurrY());
        WeakHashMap weakHashMap = f51.a;
        view.postOnAnimation(this);
    }
}
