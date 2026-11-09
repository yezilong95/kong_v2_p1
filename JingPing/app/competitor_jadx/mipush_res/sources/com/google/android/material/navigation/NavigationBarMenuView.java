package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import androidx.transition.AutoTransition;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import defpackage.b01;
import defpackage.bl0;
import defpackage.de0;
import defpackage.f51;
import defpackage.ld0;
import defpackage.n9;
import defpackage.p50;
import defpackage.s90;
import defpackage.se0;
import defpackage.t40;
import defpackage.y2;
import defpackage.y4;
import defpackage.zd0;
import defpackage.zu0;
import java.util.HashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements se0 {
    public static final int[] m0 = {R.attr.state_checked};
    public static final int[] n0 = {-16842910};
    public final AutoTransition E;
    public final y2 F;
    public final bl0 G;
    public final SparseArray H;
    public int I;
    public NavigationBarItemView[] J;
    public int K;
    public int L;
    public ColorStateList M;
    public int N;
    public ColorStateList O;
    public final ColorStateList P;
    public int Q;
    public int R;
    public boolean S;
    public Drawable T;
    public ColorStateList U;
    public int V;
    public final SparseArray W;
    public int a0;
    public int b0;
    public int c0;
    public boolean d0;
    public int e0;
    public int f0;
    public int g0;
    public zu0 h0;
    public boolean i0;
    public ColorStateList j0;
    public b k0;
    public zd0 l0;

    public NavigationBarMenuView(Context context) {
        super(context);
        this.G = new bl0(5);
        this.H = new SparseArray(5);
        this.K = 0;
        this.L = 0;
        this.W = new SparseArray(5);
        this.a0 = -1;
        this.b0 = -1;
        this.c0 = -1;
        this.i0 = false;
        this.P = b();
        if (isInEditMode()) {
            this.E = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.E = autoTransition;
            autoTransition.L(0);
            autoTransition.A(s90.U(getContext(), R$attr.motionDurationMedium4, getResources().getInteger(R$integer.material_motion_duration_long_1)));
            autoTransition.C(s90.V(getContext(), R$attr.motionEasingStandard, y4.b));
            autoTransition.I(new b01());
        }
        this.F = new y2(3, this);
        WeakHashMap weakHashMap = f51.a;
        setImportantForAccessibility(1);
    }

    public static boolean f(int i, int i2) {
        return i == -1 ? i2 > 3 : i == 0;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.G.a();
        return navigationBarItemView == null ? e(getContext()) : navigationBarItemView;
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView) {
        n9 n9Var;
        int id = navigationBarItemView.getId();
        if (id == -1 || (n9Var = (n9) this.W.get(id)) == null) {
            return;
        }
        navigationBarItemView.setBadge(n9Var);
    }

    public final void a() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.G.c(navigationBarItemView);
                    if (navigationBarItemView.m0 != null) {
                        ImageView imageView = navigationBarItemView.R;
                        if (imageView != null) {
                            navigationBarItemView.setClipChildren(true);
                            navigationBarItemView.setClipToPadding(true);
                            n9 n9Var = navigationBarItemView.m0;
                            if (n9Var != null) {
                                if (n9Var.d() != null) {
                                    n9Var.d().setForeground(null);
                                } else {
                                    imageView.getOverlay().remove(n9Var);
                                }
                            }
                        }
                        navigationBarItemView.m0 = null;
                    }
                    navigationBarItemView.a0 = null;
                    navigationBarItemView.g0 = 0.0f;
                    navigationBarItemView.E = false;
                }
            }
        }
        if (this.l0.f.size() == 0) {
            this.K = 0;
            this.L = 0;
            this.J = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.l0.f.size(); i++) {
            hashSet.add(Integer.valueOf(this.l0.getItem(i).getItemId()));
        }
        int i2 = 0;
        while (true) {
            SparseArray sparseArray = this.W;
            if (i2 >= sparseArray.size()) {
                break;
            }
            int iKeyAt = sparseArray.keyAt(i2);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                sparseArray.delete(iKeyAt);
            }
            i2++;
        }
        this.J = new NavigationBarItemView[this.l0.f.size()];
        boolean zF = f(this.I, this.l0.l().size());
        for (int i3 = 0; i3 < this.l0.f.size(); i3++) {
            this.k0.F = true;
            this.l0.getItem(i3).setCheckable(true);
            this.k0.F = false;
            NavigationBarItemView newItem = getNewItem();
            this.J[i3] = newItem;
            newItem.setIconTintList(this.M);
            newItem.setIconSize(this.N);
            newItem.setTextColor(this.P);
            newItem.setTextAppearanceInactive(this.Q);
            newItem.setTextAppearanceActive(this.R);
            newItem.setTextAppearanceActiveBoldEnabled(this.S);
            newItem.setTextColor(this.O);
            int i4 = this.a0;
            if (i4 != -1) {
                newItem.setItemPaddingTop(i4);
            }
            int i5 = this.b0;
            if (i5 != -1) {
                newItem.setItemPaddingBottom(i5);
            }
            int i6 = this.c0;
            if (i6 != -1) {
                newItem.setActiveIndicatorLabelPadding(i6);
            }
            newItem.setActiveIndicatorWidth(this.e0);
            newItem.setActiveIndicatorHeight(this.f0);
            newItem.setActiveIndicatorMarginHorizontal(this.g0);
            newItem.setActiveIndicatorDrawable(d());
            newItem.setActiveIndicatorResizeable(this.i0);
            newItem.setActiveIndicatorEnabled(this.d0);
            Drawable drawable = this.T;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.V);
            }
            newItem.setItemRippleColor(this.U);
            newItem.setShifting(zF);
            newItem.setLabelVisibilityMode(this.I);
            de0 de0Var = (de0) this.l0.getItem(i3);
            newItem.d(de0Var);
            newItem.setItemPosition(i3);
            SparseArray sparseArray2 = this.H;
            int i7 = de0Var.a;
            newItem.setOnTouchListener((View.OnTouchListener) sparseArray2.get(i7));
            newItem.setOnClickListener(this.F);
            int i8 = this.K;
            if (i8 != 0 && i7 == i8) {
                this.L = i3;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.l0.f.size() - 1, this.L);
        this.L = iMin;
        this.l0.getItem(iMin).setChecked(true);
    }

    public final ColorStateList b() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListY = t40.Y(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i = typedValue.data;
        int defaultColor = colorStateListY.getDefaultColor();
        int[] iArr = n0;
        return new ColorStateList(new int[][]{iArr, m0, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListY.getColorForState(iArr, defaultColor), i, defaultColor});
    }

    @Override // defpackage.se0
    public final void c(zd0 zd0Var) {
        this.l0 = zd0Var;
    }

    public final ld0 d() {
        if (this.h0 == null || this.j0 == null) {
            return null;
        }
        ld0 ld0Var = new ld0(this.h0);
        ld0Var.m(this.j0);
        return ld0Var;
    }

    public abstract NavigationBarItemView e(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.c0;
    }

    public SparseArray<n9> getBadgeDrawables() {
        return this.W;
    }

    public ColorStateList getIconTintList() {
        return this.M;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.j0;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.d0;
    }

    public int getItemActiveIndicatorHeight() {
        return this.f0;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.g0;
    }

    public zu0 getItemActiveIndicatorShapeAppearance() {
        return this.h0;
    }

    public int getItemActiveIndicatorWidth() {
        return this.e0;
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.T : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.V;
    }

    public int getItemIconSize() {
        return this.N;
    }

    public int getItemPaddingBottom() {
        return this.b0;
    }

    public int getItemPaddingTop() {
        return this.a0;
    }

    public ColorStateList getItemRippleColor() {
        return this.U;
    }

    public int getItemTextAppearanceActive() {
        return this.R;
    }

    public int getItemTextAppearanceInactive() {
        return this.Q;
    }

    public ColorStateList getItemTextColor() {
        return this.O;
    }

    public int getLabelVisibilityMode() {
        return this.I;
    }

    public zd0 getMenu() {
        return this.l0;
    }

    public int getSelectedItemId() {
        return this.K;
    }

    public int getSelectedItemPosition() {
        return this.L;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) p50.B(1, this.l0.l().size(), 1).F);
    }

    public void setActiveIndicatorLabelPadding(int i) {
        this.c0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorLabelPadding(i);
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.M = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.j0 = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.d0 = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.f0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.g0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z) {
        this.i0 = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(zu0 zu0Var) {
        this.h0 = zu0Var;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.e0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.T = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i) {
        this.V = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i);
            }
        }
    }

    public void setItemIconSize(int i) {
        this.N = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i);
            }
        }
    }

    public void setItemPaddingBottom(int i) {
        this.b0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i);
            }
        }
    }

    public void setItemPaddingTop(int i) {
        this.a0 = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.U = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemTextAppearanceActive(int i) {
        this.R = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i);
                ColorStateList colorStateList = this.O;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.S = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActiveBoldEnabled(z);
            }
        }
    }

    public void setItemTextAppearanceInactive(int i) {
        this.Q = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i);
                ColorStateList colorStateList = this.O;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.O = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.J;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        this.I = i;
    }

    public void setPresenter(b bVar) {
        this.k0 = bVar;
    }
}
