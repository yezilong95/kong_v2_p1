package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.cy0;
import defpackage.f51;
import defpackage.i01;
import defpackage.ip;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.ou;
import defpackage.pd0;
import defpackage.qg0;
import defpackage.qm;
import defpackage.rg0;
import defpackage.se0;
import defpackage.sg0;
import defpackage.z8;
import defpackage.zu0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final /* synthetic */ int I = 0;
    public final qg0 E;
    public final NavigationBarMenuView F;
    public final b G;
    public cy0 H;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c();
        public Bundle G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readBundle(classLoader == null ? getClass().getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.G);
        }
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        b bVar = new b();
        bVar.F = false;
        this.G = bVar;
        Context context2 = getContext();
        ou ouVarE = i01.e(context2, attributeSet, R$styleable.NavigationBarView, i, i2, R$styleable.NavigationBarView_itemTextAppearanceInactive, R$styleable.NavigationBarView_itemTextAppearanceActive);
        qg0 qg0Var = new qg0(context2, getClass(), getMaxItemCount());
        this.E = qg0Var;
        NavigationBarMenuView navigationBarMenuViewA = a(context2);
        this.F = navigationBarMenuViewA;
        bVar.E = navigationBarMenuViewA;
        bVar.G = 1;
        navigationBarMenuViewA.setPresenter(bVar);
        qg0Var.b(bVar, qg0Var.a);
        getContext();
        bVar.E.l0 = qg0Var;
        int i3 = R$styleable.NavigationBarView_itemIconTint;
        TypedArray typedArray = (TypedArray) ouVarE.G;
        if (typedArray.hasValue(i3)) {
            navigationBarMenuViewA.setIconTintList(ouVarE.y(R$styleable.NavigationBarView_itemIconTint));
        } else {
            navigationBarMenuViewA.setIconTintList(navigationBarMenuViewA.b());
        }
        setItemIconSize(typedArray.getDimensionPixelSize(R$styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R$dimen.mtrl_navigation_bar_item_default_icon_size)));
        if (typedArray.hasValue(R$styleable.NavigationBarView_itemTextAppearanceInactive)) {
            setItemTextAppearanceInactive(typedArray.getResourceId(R$styleable.NavigationBarView_itemTextAppearanceInactive, 0));
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_itemTextAppearanceActive)) {
            setItemTextAppearanceActive(typedArray.getResourceId(R$styleable.NavigationBarView_itemTextAppearanceActive, 0));
        }
        setItemTextAppearanceActiveBoldEnabled(typedArray.getBoolean(R$styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        if (typedArray.hasValue(R$styleable.NavigationBarView_itemTextColor)) {
            setItemTextColor(ouVarE.y(R$styleable.NavigationBarView_itemTextColor));
        }
        Drawable background = getBackground();
        ColorStateList colorStateListS = qm.s(background);
        if (background == null || colorStateListS != null) {
            ld0 ld0Var = new ld0(zu0.c(context2, attributeSet, i, i2).a());
            if (colorStateListS != null) {
                ld0Var.m(colorStateListS);
            }
            ld0Var.k(context2);
            WeakHashMap weakHashMap = f51.a;
            setBackground(ld0Var);
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_itemPaddingTop)) {
            setItemPaddingTop(typedArray.getDimensionPixelSize(R$styleable.NavigationBarView_itemPaddingTop, 0));
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_itemPaddingBottom)) {
            setItemPaddingBottom(typedArray.getDimensionPixelSize(R$styleable.NavigationBarView_itemPaddingBottom, 0));
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_activeIndicatorLabelPadding)) {
            setActiveIndicatorLabelPadding(typedArray.getDimensionPixelSize(R$styleable.NavigationBarView_activeIndicatorLabelPadding, 0));
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_elevation)) {
            setElevation(typedArray.getDimensionPixelSize(R$styleable.NavigationBarView_elevation, 0));
        }
        ip.h(getBackground().mutate(), la0.o(context2, ouVarE, R$styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(typedArray.getInteger(R$styleable.NavigationBarView_labelVisibilityMode, -1));
        int resourceId = typedArray.getResourceId(R$styleable.NavigationBarView_itemBackground, 0);
        if (resourceId != 0) {
            navigationBarMenuViewA.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(la0.o(context2, ouVarE, R$styleable.NavigationBarView_itemRippleColor));
        }
        int resourceId2 = typedArray.getResourceId(R$styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (resourceId2 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId2, R$styleable.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(zu0.a(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).a());
            typedArrayObtainStyledAttributes.recycle();
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_menu)) {
            int resourceId3 = typedArray.getResourceId(R$styleable.NavigationBarView_menu, 0);
            bVar.F = true;
            getMenuInflater().inflate(resourceId3, qg0Var);
            bVar.F = false;
            bVar.j(true);
        }
        ouVarE.S();
        addView(navigationBarMenuViewA);
        qg0Var.e = new z8(25, this);
    }

    private MenuInflater getMenuInflater() {
        if (this.H == null) {
            this.H = new cy0(getContext());
        }
        return this.H;
    }

    public abstract NavigationBarMenuView a(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.F.getActiveIndicatorLabelPadding();
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.F.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.F.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.F.getItemActiveIndicatorMarginHorizontal();
    }

    public zu0 getItemActiveIndicatorShapeAppearance() {
        return this.F.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.F.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.F.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.F.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.F.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.F.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.F.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.F.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.F.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.F.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.F.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.F.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.F.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.E;
    }

    public se0 getMenuView() {
        return this.F;
    }

    public b getPresenter() {
        return this.G;
    }

    public int getSelectedItemId() {
        return this.F.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.p0(this);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        this.E.t(savedState.G);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.G = bundle;
        this.E.v(bundle);
        return savedState;
    }

    public void setActiveIndicatorLabelPadding(int i) {
        this.F.setActiveIndicatorLabelPadding(i);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ma0.n0(this, f);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.F.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.F.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.F.setItemActiveIndicatorHeight(i);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.F.setItemActiveIndicatorMarginHorizontal(i);
    }

    public void setItemActiveIndicatorShapeAppearance(zu0 zu0Var) {
        this.F.setItemActiveIndicatorShapeAppearance(zu0Var);
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.F.setItemActiveIndicatorWidth(i);
    }

    public void setItemBackground(Drawable drawable) {
        this.F.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i) {
        this.F.setItemBackgroundRes(i);
    }

    public void setItemIconSize(int i) {
        this.F.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.F.setIconTintList(colorStateList);
    }

    public void setItemPaddingBottom(int i) {
        this.F.setItemPaddingBottom(i);
    }

    public void setItemPaddingTop(int i) {
        this.F.setItemPaddingTop(i);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.F.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(int i) {
        this.F.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.F.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.F.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.F.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        NavigationBarMenuView navigationBarMenuView = this.F;
        if (navigationBarMenuView.getLabelVisibilityMode() != i) {
            navigationBarMenuView.setLabelVisibilityMode(i);
            this.G.j(false);
        }
    }

    public void setSelectedItemId(int i) {
        qg0 qg0Var = this.E;
        MenuItem menuItemFindItem = qg0Var.findItem(i);
        if (menuItemFindItem == null || qg0Var.q(menuItemFindItem, this.G, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public void setOnItemReselectedListener(rg0 rg0Var) {
    }

    public void setOnItemSelectedListener(sg0 sg0Var) {
    }
}
