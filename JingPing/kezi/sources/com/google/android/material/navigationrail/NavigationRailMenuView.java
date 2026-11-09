package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import defpackage.c90;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    public int I;
    public final FrameLayout.LayoutParams J;

    public NavigationRailMenuView(Context context) {
        super(context);
        this.I = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.J = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public final NavigationBarItemView e(Context context) {
        return new c90(context);
    }

    public final int g(int i, int i2, int i3, View view) {
        int iMakeMeasureSpec;
        int measuredHeight;
        if (view == null) {
            int iMax = i2 / Math.max(1, i3);
            int size = this.I;
            if (size == -1) {
                size = View.MeasureSpec.getSize(i);
            }
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(size, iMax), 0);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        }
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != view) {
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i, iMakeMeasureSpec);
                    measuredHeight = childAt.getMeasuredHeight();
                } else {
                    measuredHeight = 0;
                }
                i4 += measuredHeight;
            }
        }
        return i4;
    }

    public int getItemMinimumHeight() {
        return this.I;
    }

    public int getMenuGravity() {
        return this.J.gravity;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = 0;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i6;
                childAt.layout(0, i6, i5, measuredHeight);
                i6 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iG;
        int measuredHeight;
        int size = View.MeasureSpec.getSize(i2);
        int size2 = getMenu().l().size();
        if (size2 <= 1 || !NavigationBarMenuView.f(getLabelVisibilityMode(), size2)) {
            iG = g(i, size, size2, null);
        } else {
            View childAt = getChildAt(getSelectedItemPosition());
            if (childAt != null) {
                int iMax = size / Math.max(1, size2);
                int size3 = this.I;
                if (size3 == -1) {
                    size3 = View.MeasureSpec.getSize(i);
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(size3, iMax), 0);
                if (childAt.getVisibility() != 8) {
                    childAt.measure(i, iMakeMeasureSpec);
                    measuredHeight = childAt.getMeasuredHeight();
                } else {
                    measuredHeight = 0;
                }
                size -= measuredHeight;
                size2--;
            } else {
                measuredHeight = 0;
            }
            iG = g(i, size, size2, childAt) + measuredHeight;
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.resolveSizeAndState(iG, i2, 0));
    }

    public void setItemMinimumHeight(int i) {
        if (this.I != i) {
            this.I = i;
            requestLayout();
        }
    }

    public void setMenuGravity(int i) {
        FrameLayout.LayoutParams layoutParams = this.J;
        if (layoutParams.gravity != i) {
            layoutParams.gravity = i;
            setLayoutParams(layoutParams);
        }
    }
}
