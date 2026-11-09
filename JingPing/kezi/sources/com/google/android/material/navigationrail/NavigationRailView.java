package com.google.android.material.navigationrail;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import defpackage.d90;
import defpackage.po;
import defpackage.rp0;
import defpackage.t3;
import defpackage.un0;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailView extends NavigationBarView {
    public final int g;
    public final View h;
    public final Boolean i;
    public final Boolean j;
    public final Boolean k;

    public NavigationRailView(Context context) {
        this(context, null);
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new NavigationRailMenuView(context);
    }

    public View getHeaderView() {
        return this.h;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView) getMenuView()).getItemMinimumHeight();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        NavigationRailMenuView navigationRailMenuView = getNavigationRailMenuView();
        View view = this.h;
        int i5 = 0;
        boolean z2 = (view == null || view.getVisibility() == 8) ? false : true;
        int i6 = this.g;
        if (z2) {
            int bottom = this.h.getBottom() + i6;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i5 = bottom - top;
            }
        } else if ((navigationRailMenuView.J.gravity & 112) == 48) {
            i5 = i6;
        }
        if (i5 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i5, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i5);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i) != 1073741824 && suggestedMinimumWidth > 0) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), getPaddingRight() + getPaddingLeft() + suggestedMinimumWidth), 1073741824);
        }
        super.onMeasure(i, i2);
        View view = this.h;
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        measureChild(getNavigationRailMenuView(), i, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.h.getMeasuredHeight()) - this.g, Integer.MIN_VALUE));
    }

    public void setItemMinimumHeight(int i) {
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(i);
    }

    public void setMenuGravity(int i) {
        getNavigationRailMenuView().setMenuGravity(i);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.navigationRailStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public NavigationRailView(Context context, AttributeSet attributeSet, int i) {
        int i2 = R$style.Widget_MaterialComponents_NavigationRailView;
        super(context, attributeSet, i, i2);
        this.i = null;
        this.j = null;
        this.k = null;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.mtrl_navigation_rail_margin);
        this.g = dimensionPixelSize;
        Context context2 = getContext();
        po poVarE = un0.e(context2, attributeSet, R$styleable.NavigationRailView, i, i2, new int[0]);
        int i3 = R$styleable.NavigationRailView_headerLayout;
        TypedArray typedArray = (TypedArray) poVarE.d;
        int resourceId = typedArray.getResourceId(i3, 0);
        if (resourceId != 0) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(resourceId, (ViewGroup) this, false);
            View view = this.h;
            if (view != null) {
                removeView(view);
                this.h = null;
            }
            this.h = viewInflate;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 49;
            layoutParams.topMargin = dimensionPixelSize;
            addView(viewInflate, 0, layoutParams);
        }
        setMenuGravity(typedArray.getInt(R$styleable.NavigationRailView_menuGravity, 49));
        if (typedArray.hasValue(R$styleable.NavigationRailView_itemMinHeight)) {
            setItemMinimumHeight(typedArray.getDimensionPixelSize(R$styleable.NavigationRailView_itemMinHeight, -1));
        }
        if (typedArray.hasValue(R$styleable.NavigationRailView_paddingTopSystemWindowInsets)) {
            this.i = Boolean.valueOf(typedArray.getBoolean(R$styleable.NavigationRailView_paddingTopSystemWindowInsets, false));
        }
        if (typedArray.hasValue(R$styleable.NavigationRailView_paddingBottomSystemWindowInsets)) {
            this.j = Boolean.valueOf(typedArray.getBoolean(R$styleable.NavigationRailView_paddingBottomSystemWindowInsets, false));
        }
        if (typedArray.hasValue(R$styleable.NavigationRailView_paddingStartSystemWindowInsets)) {
            this.k = Boolean.valueOf(typedArray.getBoolean(R$styleable.NavigationRailView_paddingStartSystemWindowInsets, false));
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.m3_navigation_rail_item_padding_top_with_large_font);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R$dimen.m3_navigation_rail_item_padding_bottom_with_large_font);
        float fB = t3.b(0.0f, 1.0f, 0.3f, 1.0f, context2.getResources().getConfiguration().fontScale - 1.0f);
        float fC = t3.c(getItemPaddingTop(), fB, dimensionPixelOffset);
        float fC2 = t3.c(getItemPaddingBottom(), fB, dimensionPixelOffset2);
        setItemPaddingTop(Math.round(fC));
        setItemPaddingBottom(Math.round(fC2));
        poVarE.P();
        rp0.s(this, new d90((Object) this));
    }
}
