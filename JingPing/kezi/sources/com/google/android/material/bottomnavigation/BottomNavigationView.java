package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import defpackage.a9;
import defpackage.b9;
import defpackage.po;
import defpackage.qm;
import defpackage.rp0;
import defpackage.un0;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends NavigationBarView {
    public BottomNavigationView(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public final NavigationBarMenuView a(Context context) {
        return new BottomNavigationMenuView(context);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 5;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        if (View.MeasureSpec.getMode(i2) != 1073741824 && suggestedMinimumHeight > 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i2), getPaddingBottom() + getPaddingTop() + suggestedMinimumHeight), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) getMenuView();
        if (bottomNavigationMenuView.M != z) {
            bottomNavigationMenuView.setItemHorizontalTranslationEnabled(z);
            getPresenter().l(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(a9 a9Var) {
        setOnItemReselectedListener(a9Var);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(b9 b9Var) {
        setOnItemSelectedListener(b9Var);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        int i2 = R$style.Widget_Design_BottomNavigationView;
        super(context, attributeSet, i, i2);
        po poVarE = un0.e(getContext(), attributeSet, R$styleable.BottomNavigationView, i, i2, new int[0]);
        int i3 = R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled;
        TypedArray typedArray = (TypedArray) poVarE.d;
        setItemHorizontalTranslationEnabled(typedArray.getBoolean(i3, true));
        if (typedArray.hasValue(R$styleable.BottomNavigationView_android_minHeight)) {
            setMinimumHeight(typedArray.getDimensionPixelSize(R$styleable.BottomNavigationView_android_minHeight, 0));
        }
        typedArray.getBoolean(R$styleable.BottomNavigationView_compatShadowEnabled, true);
        poVarE.P();
        rp0.s(this, new qm());
    }
}
