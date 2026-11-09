package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import defpackage.rp0;

/* JADX INFO: loaded from: classes.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    public g d;
    public OverScroller e;
    public boolean f;
    public int g;
    public int h;
    public int i;
    public VelocityTracker j;

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = -1;
        this.i = -1;
    }

    public boolean A(View view) {
        return false;
    }

    public int B(View view) {
        return -view.getHeight();
    }

    public int C(View view) {
        return view.getHeight();
    }

    public void D(CoordinatorLayout coordinatorLayout, View view) {
    }

    public int E(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        int iN;
        int iW = w();
        if (i2 == 0 || iW < i2 || iW > i3 || iW == (iN = rp0.n(i, i2, i3))) {
            return 0;
        }
        z(iN);
        return iW - iN;
    }

    public final void F(CoordinatorLayout coordinatorLayout, View view, int i) {
        E(coordinatorLayout, view, i, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x008b  */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean k(androidx.coordinatorlayout.widget.CoordinatorLayout r7, android.view.View r8, android.view.MotionEvent r9) {
        /*
            r6 = this;
            int r0 = r6.i
            if (r0 >= 0) goto L12
            android.content.Context r0 = r7.getContext()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r0 = r0.getScaledTouchSlop()
            r6.i = r0
        L12:
            int r0 = r9.getActionMasked()
            r1 = 2
            r2 = 1
            r3 = -1
            r4 = 0
            if (r0 != r1) goto L40
            boolean r0 = r6.f
            if (r0 == 0) goto L40
            int r0 = r6.g
            if (r0 != r3) goto L25
            goto L8e
        L25:
            int r0 = r9.findPointerIndex(r0)
            if (r0 != r3) goto L2c
            goto L8e
        L2c:
            float r0 = r9.getY(r0)
            int r0 = (int) r0
            int r1 = r6.h
            int r1 = r0 - r1
            int r1 = java.lang.Math.abs(r1)
            int r5 = r6.i
            if (r1 <= r5) goto L40
            r6.h = r0
            return r2
        L40:
            int r0 = r9.getActionMasked()
            if (r0 != 0) goto L87
            r6.g = r3
            float r0 = r9.getX()
            int r0 = (int) r0
            float r1 = r9.getY()
            int r1 = (int) r1
            boolean r3 = r6.A(r8)
            if (r3 == 0) goto L60
            boolean r7 = r7.t(r8, r0, r1)
            if (r7 == 0) goto L60
            r7 = r2
            goto L61
        L60:
            r7 = r4
        L61:
            r6.f = r7
            if (r7 == 0) goto L87
            r6.h = r1
            int r7 = r9.getPointerId(r4)
            r6.g = r7
            android.view.VelocityTracker r7 = r6.j
            if (r7 != 0) goto L77
            android.view.VelocityTracker r7 = android.view.VelocityTracker.obtain()
            r6.j = r7
        L77:
            android.widget.OverScroller r7 = r6.e
            if (r7 == 0) goto L87
            boolean r7 = r7.isFinished()
            if (r7 != 0) goto L87
            android.widget.OverScroller r7 = r6.e
            r7.abortAnimation()
            return r2
        L87:
            android.view.VelocityTracker r7 = r6.j
            if (r7 == 0) goto L8e
            r7.addMovement(r9)
        L8e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.k(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00da A[ADDED_TO_REGION] */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean v(androidx.coordinatorlayout.widget.CoordinatorLayout r19, android.view.View r20, android.view.MotionEvent r21) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.v(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }
}
