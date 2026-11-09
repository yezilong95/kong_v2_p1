package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.search.SearchBar;
import defpackage.f51;
import defpackage.s81;
import defpackage.s90;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    public final Rect G;
    public final Rect H;
    public int I;
    public int J;

    public HeaderScrollingViewBehavior() {
        this.G = new Rect();
        this.H = new Rect();
        this.I = 0;
    }

    public abstract AppBarLayout A(ArrayList arrayList);

    public float B(View view) {
        return 1.0f;
    }

    public int C(View view) {
        return view.getMeasuredHeight();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        AppBarLayout appBarLayoutA;
        s81 lastWindowInsets;
        int i4 = view.getLayoutParams().height;
        if ((i4 != -1 && i4 != -2) || (appBarLayoutA = A(coordinatorLayout.o(view))) == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size > 0) {
            WeakHashMap weakHashMap = f51.a;
            if (appBarLayoutA.getFitsSystemWindows() && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.a() + lastWindowInsets.d();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        int iC = size + C(appBarLayoutA);
        int measuredHeight = appBarLayoutA.getMeasuredHeight();
        if (this instanceof SearchBar.ScrollingViewBehavior) {
            view.setTranslationY(-measuredHeight);
        } else {
            view.setTranslationY(0.0f);
            iC -= measuredHeight;
        }
        coordinatorLayout.w(i, i2, View.MeasureSpec.makeMeasureSpec(iC, i4 == -1 ? 1073741824 : Integer.MIN_VALUE), view);
        return true;
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public final void y(CoordinatorLayout coordinatorLayout, View view, int i) {
        AppBarLayout appBarLayoutA = A(coordinatorLayout.o(view));
        int iM = 0;
        if (appBarLayoutA == null) {
            coordinatorLayout.v(view, i);
            this.I = 0;
            return;
        }
        androidx.coordinatorlayout.widget.c cVar = (androidx.coordinatorlayout.widget.c) view.getLayoutParams();
        int paddingLeft = coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) cVar).leftMargin;
        int bottom = appBarLayoutA.getBottom() + ((ViewGroup.MarginLayoutParams) cVar).topMargin;
        int width = (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) cVar).rightMargin;
        int bottom2 = ((appBarLayoutA.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) cVar).bottomMargin;
        Rect rect = this.G;
        rect.set(paddingLeft, bottom, width, bottom2);
        s81 lastWindowInsets = coordinatorLayout.getLastWindowInsets();
        if (lastWindowInsets != null) {
            WeakHashMap weakHashMap = f51.a;
            if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                rect.left = lastWindowInsets.b() + rect.left;
                rect.right -= lastWindowInsets.c();
            }
        }
        int i2 = cVar.c;
        if (i2 == 0) {
            i2 = 8388659;
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        Rect rect2 = this.H;
        Gravity.apply(i2, measuredWidth, measuredHeight, rect, rect2, i);
        if (this.J != 0) {
            float fB = B(appBarLayoutA);
            int i3 = this.J;
            iM = s90.m((int) (fB * i3), 0, i3);
        }
        view.layout(rect2.left, rect2.top - iM, rect2.right, rect2.bottom - iM);
        this.I = rect2.top - appBarLayoutA.getBottom();
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.G = new Rect();
        this.H = new Rect();
        this.I = 0;
    }
}
