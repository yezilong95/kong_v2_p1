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
import defpackage.b70;
import defpackage.c1;
import defpackage.i60;
import defpackage.k9;
import defpackage.ki0;
import defpackage.m60;
import defpackage.nn0;
import defpackage.o70;
import defpackage.rp0;
import defpackage.t3;
import defpackage.t7;
import defpackage.u50;
import defpackage.wb0;
import defpackage.xr0;
import java.util.HashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements b70 {
    public static final int[] G = {R.attr.state_checked};
    public static final int[] H = {-16842910};
    public int A;
    public ki0 B;
    public boolean C;
    public ColorStateList D;
    public b E;
    public i60 F;
    public final AutoTransition b;
    public final c1 c;
    public final wb0 d;
    public final SparseArray e;
    public int f;
    public NavigationBarItemView[] g;
    public int h;
    public int i;
    public ColorStateList j;
    public int k;
    public ColorStateList l;
    public final ColorStateList m;
    public int n;
    public int o;
    public boolean p;
    public Drawable q;
    public ColorStateList r;
    public int s;
    public final SparseArray t;
    public int u;
    public int v;
    public int w;
    public boolean x;
    public int y;
    public int z;

    public NavigationBarMenuView(Context context) {
        super(context);
        this.d = new wb0(5);
        this.e = new SparseArray(5);
        this.h = 0;
        this.i = 0;
        this.t = new SparseArray(5);
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.C = false;
        this.m = b();
        if (isInEditMode()) {
            this.b = null;
        } else {
            AutoTransition autoTransition = new AutoTransition();
            this.b = autoTransition;
            autoTransition.I(0);
            autoTransition.x(k9.E(getContext(), R$attr.motionDurationMedium4, getResources().getInteger(R$integer.material_motion_duration_long_1)));
            autoTransition.z(k9.F(getContext(), R$attr.motionEasingStandard, t3.b));
            autoTransition.F(new nn0());
        }
        this.c = new c1(3, this);
        WeakHashMap weakHashMap = xr0.a;
        setImportantForAccessibility(1);
    }

    public static boolean f(int i, int i2) {
        return i == -1 ? i2 > 3 : i == 0;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.d.a();
        return navigationBarItemView == null ? e(getContext()) : navigationBarItemView;
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView) {
        t7 t7Var;
        int id = navigationBarItemView.getId();
        if (id == -1 || (t7Var = (t7) this.t.get(id)) == null) {
            return;
        }
        navigationBarItemView.setBadge(t7Var);
    }

    public final void a() {
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.d.c(navigationBarItemView);
                    if (navigationBarItemView.G != null) {
                        ImageView imageView = navigationBarItemView.o;
                        if (imageView != null) {
                            navigationBarItemView.setClipChildren(true);
                            navigationBarItemView.setClipToPadding(true);
                            t7 t7Var = navigationBarItemView.G;
                            if (t7Var != null) {
                                if (t7Var.d() != null) {
                                    t7Var.d().setForeground(null);
                                } else {
                                    imageView.getOverlay().remove(t7Var);
                                }
                            }
                        }
                        navigationBarItemView.G = null;
                    }
                    navigationBarItemView.u = null;
                    navigationBarItemView.A = 0.0f;
                    navigationBarItemView.b = false;
                }
            }
        }
        if (this.F.f.size() == 0) {
            this.h = 0;
            this.i = 0;
            this.g = null;
            return;
        }
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.F.f.size(); i++) {
            hashSet.add(Integer.valueOf(this.F.getItem(i).getItemId()));
        }
        int i2 = 0;
        while (true) {
            SparseArray sparseArray = this.t;
            if (i2 >= sparseArray.size()) {
                break;
            }
            int iKeyAt = sparseArray.keyAt(i2);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                sparseArray.delete(iKeyAt);
            }
            i2++;
        }
        this.g = new NavigationBarItemView[this.F.f.size()];
        boolean zF = f(this.f, this.F.l().size());
        for (int i3 = 0; i3 < this.F.f.size(); i3++) {
            this.E.c = true;
            this.F.getItem(i3).setCheckable(true);
            this.E.c = false;
            NavigationBarItemView newItem = getNewItem();
            this.g[i3] = newItem;
            newItem.setIconTintList(this.j);
            newItem.setIconSize(this.k);
            newItem.setTextColor(this.m);
            newItem.setTextAppearanceInactive(this.n);
            newItem.setTextAppearanceActive(this.o);
            newItem.setTextAppearanceActiveBoldEnabled(this.p);
            newItem.setTextColor(this.l);
            int i4 = this.u;
            if (i4 != -1) {
                newItem.setItemPaddingTop(i4);
            }
            int i5 = this.v;
            if (i5 != -1) {
                newItem.setItemPaddingBottom(i5);
            }
            int i6 = this.w;
            if (i6 != -1) {
                newItem.setActiveIndicatorLabelPadding(i6);
            }
            newItem.setActiveIndicatorWidth(this.y);
            newItem.setActiveIndicatorHeight(this.z);
            newItem.setActiveIndicatorMarginHorizontal(this.A);
            newItem.setActiveIndicatorDrawable(d());
            newItem.setActiveIndicatorResizeable(this.C);
            newItem.setActiveIndicatorEnabled(this.x);
            Drawable drawable = this.q;
            if (drawable != null) {
                newItem.setItemBackground(drawable);
            } else {
                newItem.setItemBackground(this.s);
            }
            newItem.setItemRippleColor(this.r);
            newItem.setShifting(zF);
            newItem.setLabelVisibilityMode(this.f);
            m60 m60Var = (m60) this.F.getItem(i3);
            newItem.d(m60Var);
            newItem.setItemPosition(i3);
            SparseArray sparseArray2 = this.e;
            int i7 = m60Var.a;
            newItem.setOnTouchListener((View.OnTouchListener) sparseArray2.get(i7));
            newItem.setOnClickListener(this.c);
            int i8 = this.h;
            if (i8 != 0 && i7 == i8) {
                this.i = i3;
            }
            setBadgeIfNeeded(newItem);
            addView(newItem);
        }
        int iMin = Math.min(this.F.f.size() - 1, this.i);
        this.i = iMin;
        this.F.getItem(iMin).setChecked(true);
    }

    public final ColorStateList b() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListW = rp0.w(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i = typedValue.data;
        int defaultColor = colorStateListW.getDefaultColor();
        int[] iArr = H;
        return new ColorStateList(new int[][]{iArr, G, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListW.getColorForState(iArr, defaultColor), i, defaultColor});
    }

    @Override // defpackage.b70
    public final void c(i60 i60Var) {
        this.F = i60Var;
    }

    public final u50 d() {
        if (this.B == null || this.D == null) {
            return null;
        }
        u50 u50Var = new u50(this.B);
        u50Var.m(this.D);
        return u50Var;
    }

    public abstract NavigationBarItemView e(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.w;
    }

    public SparseArray<t7> getBadgeDrawables() {
        return this.t;
    }

    public ColorStateList getIconTintList() {
        return this.j;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.D;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.x;
    }

    public int getItemActiveIndicatorHeight() {
        return this.z;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.A;
    }

    public ki0 getItemActiveIndicatorShapeAppearance() {
        return this.B;
    }

    public int getItemActiveIndicatorWidth() {
        return this.y;
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.q : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.s;
    }

    public int getItemIconSize() {
        return this.k;
    }

    public int getItemPaddingBottom() {
        return this.v;
    }

    public int getItemPaddingTop() {
        return this.u;
    }

    public ColorStateList getItemRippleColor() {
        return this.r;
    }

    public int getItemTextAppearanceActive() {
        return this.o;
    }

    public int getItemTextAppearanceInactive() {
        return this.n;
    }

    public ColorStateList getItemTextColor() {
        return this.l;
    }

    public int getLabelVisibilityMode() {
        return this.f;
    }

    public i60 getMenu() {
        return this.F;
    }

    public int getSelectedItemId() {
        return this.h;
    }

    public int getSelectedItemPosition() {
        return this.i;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) o70.y(1, this.F.l().size(), 1).c);
    }

    public void setActiveIndicatorLabelPadding(int i) {
        this.w = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorLabelPadding(i);
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.j = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.D = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.x = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i) {
        this.z = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i) {
        this.A = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z) {
        this.C = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(ki0 ki0Var) {
        this.B = ki0Var;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(d());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i) {
        this.y = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.q = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(drawable);
            }
        }
    }

    public void setItemBackgroundRes(int i) {
        this.s = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemBackground(i);
            }
        }
    }

    public void setItemIconSize(int i) {
        this.k = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(i);
            }
        }
    }

    public void setItemPaddingBottom(int i) {
        this.v = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i);
            }
        }
    }

    public void setItemPaddingTop(int i) {
        this.u = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.r = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemTextAppearanceActive(int i) {
        this.o = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActive(i);
                ColorStateList colorStateList = this.l;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.p = z;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActiveBoldEnabled(z);
            }
        }
    }

    public void setItemTextAppearanceInactive(int i) {
        this.n = i;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceInactive(i);
                ColorStateList colorStateList = this.l;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.l = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.g;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
    }

    public void setLabelVisibilityMode(int i) {
        this.f = i;
    }

    public void setPresenter(b bVar) {
        this.E = bVar;
    }
}
