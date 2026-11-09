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
import defpackage.fa0;
import defpackage.i01;
import defpackage.is;
import defpackage.ou;
import defpackage.ya;
import defpackage.za;

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
        if (bottomNavigationMenuView.s0 != z) {
            bottomNavigationMenuView.setItemHorizontalTranslationEnabled(z);
            getPresenter().j(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(ya yaVar) {
        setOnItemReselectedListener(yaVar);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(za zaVar) {
        setOnItemSelectedListener(zaVar);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomNavigationStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        int i2 = R$style.Widget_Design_BottomNavigationView;
        super(context, attributeSet, i, i2);
        ou ouVarE = i01.e(getContext(), attributeSet, R$styleable.BottomNavigationView, i, i2, new int[0]);
        int i3 = R$styleable.BottomNavigationView_itemHorizontalTranslationEnabled;
        TypedArray typedArray = (TypedArray) ouVarE.G;
        setItemHorizontalTranslationEnabled(typedArray.getBoolean(i3, true));
        if (typedArray.hasValue(R$styleable.BottomNavigationView_android_minHeight)) {
            setMinimumHeight(typedArray.getDimensionPixelSize(R$styleable.BottomNavigationView_android_minHeight, 0));
        }
        typedArray.getBoolean(R$styleable.BottomNavigationView_compatShadowEnabled, true);
        ouVarE.S();
        fa0.B(this, new is(19));
    }
}
