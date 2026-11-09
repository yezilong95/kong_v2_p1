package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
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
import defpackage.b70;
import defpackage.hl;
import defpackage.ki0;
import defpackage.n80;
import defpackage.o70;
import defpackage.o80;
import defpackage.ol0;
import defpackage.oy;
import defpackage.p80;
import defpackage.po;
import defpackage.u50;
import defpackage.un0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final /* synthetic */ int f = 0;
    public final n80 b;
    public final NavigationBarMenuView c;
    public final b d;
    public ol0 e;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new c();
        public Bundle d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readBundle(classLoader == null ? getClass().getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.d);
        }
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        b bVar = new b();
        bVar.c = false;
        this.d = bVar;
        Context context2 = getContext();
        po poVarE = un0.e(context2, attributeSet, R$styleable.NavigationBarView, i, i2, R$styleable.NavigationBarView_itemTextAppearanceInactive, R$styleable.NavigationBarView_itemTextAppearanceActive);
        n80 n80Var = new n80(context2, getClass(), getMaxItemCount());
        this.b = n80Var;
        NavigationBarMenuView navigationBarMenuViewA = a(context2);
        this.c = navigationBarMenuViewA;
        bVar.b = navigationBarMenuViewA;
        bVar.d = 1;
        navigationBarMenuViewA.setPresenter(bVar);
        n80Var.b(bVar, n80Var.a);
        getContext();
        bVar.b.F = n80Var;
        int i3 = R$styleable.NavigationBarView_itemIconTint;
        TypedArray typedArray = (TypedArray) poVarE.d;
        if (typedArray.hasValue(i3)) {
            navigationBarMenuViewA.setIconTintList(poVarE.w(R$styleable.NavigationBarView_itemIconTint));
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
            setItemTextColor(poVarE.w(R$styleable.NavigationBarView_itemTextColor));
        }
        if (getBackground() == null || (getBackground() instanceof ColorDrawable)) {
            ki0 ki0VarA = ki0.c(context2, attributeSet, i, i2).a();
            u50 u50Var = new u50();
            Drawable background = getBackground();
            if (background instanceof ColorDrawable) {
                u50Var.m(ColorStateList.valueOf(((ColorDrawable) background).getColor()));
            }
            u50Var.k(context2);
            u50Var.setShapeAppearanceModel(ki0VarA);
            WeakHashMap weakHashMap = xr0.a;
            setBackground(u50Var);
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
        hl.h(getBackground().mutate(), xi.r(context2, poVarE, R$styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(typedArray.getInteger(R$styleable.NavigationBarView_labelVisibilityMode, -1));
        int resourceId = typedArray.getResourceId(R$styleable.NavigationBarView_itemBackground, 0);
        if (resourceId != 0) {
            navigationBarMenuViewA.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(xi.r(context2, poVarE, R$styleable.NavigationBarView_itemRippleColor));
        }
        int resourceId2 = typedArray.getResourceId(R$styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (resourceId2 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId2, R$styleable.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(ki0.a(context2, typedArrayObtainStyledAttributes.getResourceId(R$styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).a());
            typedArrayObtainStyledAttributes.recycle();
        }
        if (typedArray.hasValue(R$styleable.NavigationBarView_menu)) {
            int resourceId3 = typedArray.getResourceId(R$styleable.NavigationBarView_menu, 0);
            bVar.c = true;
            getMenuInflater().inflate(resourceId3, n80Var);
            bVar.c = false;
            bVar.l(true);
        }
        poVarE.P();
        addView(navigationBarMenuViewA);
        n80Var.e = new o70(29, this);
    }

    private MenuInflater getMenuInflater() {
        if (this.e == null) {
            this.e = new ol0(getContext());
        }
        return this.e;
    }

    public abstract NavigationBarMenuView a(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.c.getActiveIndicatorLabelPadding();
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.c.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.c.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.c.getItemActiveIndicatorMarginHorizontal();
    }

    public ki0 getItemActiveIndicatorShapeAppearance() {
        return this.c.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.c.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.c.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.c.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.c.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.c.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.c.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.c.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.c.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.c.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.c.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.c.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.c.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.b;
    }

    public b70 getMenuView() {
        return this.c;
    }

    public b getPresenter() {
        return this.d;
    }

    public int getSelectedItemId() {
        return this.c.getSelectedItemId();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        this.b.t(savedState.d);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.d = bundle;
        this.b.v(bundle);
        return savedState;
    }

    public void setActiveIndicatorLabelPadding(int i) {
        this.c.setActiveIndicatorLabelPadding(i);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        oy.N(this, f2);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.c.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.c.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.c.setItemActiveIndicatorHeight(i);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.c.setItemActiveIndicatorMarginHorizontal(i);
    }

    public void setItemActiveIndicatorShapeAppearance(ki0 ki0Var) {
        this.c.setItemActiveIndicatorShapeAppearance(ki0Var);
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.c.setItemActiveIndicatorWidth(i);
    }

    public void setItemBackground(Drawable drawable) {
        this.c.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i) {
        this.c.setItemBackgroundRes(i);
    }

    public void setItemIconSize(int i) {
        this.c.setItemIconSize(i);
    }

    public void setItemIconSizeRes(int i) {
        setItemIconSize(getResources().getDimensionPixelSize(i));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.c.setIconTintList(colorStateList);
    }

    public void setItemPaddingBottom(int i) {
        this.c.setItemPaddingBottom(i);
    }

    public void setItemPaddingTop(int i) {
        this.c.setItemPaddingTop(i);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.c.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(int i) {
        this.c.setItemTextAppearanceActive(i);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.c.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextAppearanceInactive(int i) {
        this.c.setItemTextAppearanceInactive(i);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.c.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i) {
        NavigationBarMenuView navigationBarMenuView = this.c;
        if (navigationBarMenuView.getLabelVisibilityMode() != i) {
            navigationBarMenuView.setLabelVisibilityMode(i);
            this.d.l(false);
        }
    }

    public void setSelectedItemId(int i) {
        n80 n80Var = this.b;
        MenuItem menuItemFindItem = n80Var.findItem(i);
        if (menuItemFindItem == null || n80Var.q(menuItemFindItem, this.d, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }

    public void setOnItemReselectedListener(o80 o80Var) {
    }

    public void setOnItemSelectedListener(p80 p80Var) {
    }
}
