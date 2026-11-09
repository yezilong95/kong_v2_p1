package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.appcompat.R$attr;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import defpackage.ah;
import defpackage.b90;
import defpackage.e;
import defpackage.f90;
import defpackage.g90;
import defpackage.ii0;
import defpackage.ki0;
import defpackage.li0;
import defpackage.m60;
import defpackage.ol0;
import defpackage.oy;
import defpackage.po;
import defpackage.q80;
import defpackage.r7;
import defpackage.rp0;
import defpackage.tl;
import defpackage.u50;
import defpackage.ul;
import defpackage.v1;
import defpackage.vl;
import defpackage.w40;
import defpackage.w50;
import defpackage.wi0;
import defpackage.x40;
import defpackage.xr0;
import defpackage.xu0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements w40 {
    public static final int[] v = {R.attr.state_checked};
    public static final int[] w = {-16842910};
    public static final int x = R$style.Widget_Design_NavigationView;
    public final q80 i;
    public final b90 j;
    public final int k;
    public final int[] l;
    public ol0 m;
    public final v1 n;
    public boolean o;
    public boolean p;
    public final int q;
    public final wi0 r;
    public final w50 s;
    public final po t;
    public final f90 u;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new d();
        public Bundle d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.d);
        }
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.m == null) {
            this.m = new ol0(getContext());
        }
        return this.m;
    }

    @Override // defpackage.w40
    public final void a(r7 r7Var) {
        h();
        this.s.f = r7Var;
    }

    @Override // defpackage.w40
    public final void b() {
        int i = 0;
        Pair pairH = h();
        DrawerLayout drawerLayout = (DrawerLayout) pairH.first;
        w50 w50Var = this.s;
        r7 r7Var = w50Var.f;
        w50Var.f = null;
        if (r7Var == null || Build.VERSION.SDK_INT < 34) {
            drawerLayout.b(this, true);
            return;
        }
        int i2 = ((DrawerLayout.LayoutParams) pairH.second).a;
        int i3 = vl.a;
        w50Var.c(r7Var, i2, new ul(drawerLayout, i, this), new tl(i, drawerLayout));
    }

    @Override // defpackage.w40
    public final void c(r7 r7Var) {
        int i = ((DrawerLayout.LayoutParams) h().second).a;
        w50 w50Var = this.s;
        if (w50Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        r7 r7Var2 = w50Var.f;
        w50Var.f = r7Var;
        if (r7Var2 == null) {
            return;
        }
        w50Var.d(r7Var.c, i, r7Var.d == 0);
    }

    @Override // defpackage.w40
    public final void d() {
        h();
        this.s.b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        wi0 wi0Var = this.r;
        if (wi0Var.b()) {
            Path path = wi0Var.e;
            if (!path.isEmpty()) {
                canvas.save();
                canvas.clipPath(path);
                super.dispatchDraw(canvas);
                canvas.restore();
                return;
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout
    public final void e(xu0 xu0Var) {
        b90 b90Var = this.j;
        b90Var.getClass();
        int iD = xu0Var.d();
        if (b90Var.A != iD) {
            b90Var.A = iD;
            int i = (b90Var.c.getChildCount() <= 0 && b90Var.y) ? b90Var.A : 0;
            NavigationMenuView navigationMenuView = b90Var.b;
            navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
        }
        NavigationMenuView navigationMenuView2 = b90Var.b;
        navigationMenuView2.setPadding(0, navigationMenuView2.getPaddingTop(), 0, xu0Var.a());
        xr0.b(b90Var.c, xu0Var);
    }

    public final ColorStateList f(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListW = rp0.w(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListW.getDefaultColor();
        int[] iArr = w;
        return new ColorStateList(new int[][]{iArr, v, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListW.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public final InsetDrawable g(po poVar, ColorStateList colorStateList) {
        int i = R$styleable.NavigationView_itemShapeAppearance;
        TypedArray typedArray = (TypedArray) poVar.d;
        u50 u50Var = new u50(ki0.a(getContext(), typedArray.getResourceId(i, 0), typedArray.getResourceId(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).a());
        u50Var.m(colorStateList);
        return new InsetDrawable((Drawable) u50Var, typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetStart, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetTop, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetEnd, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public w50 getBackHelper() {
        return this.s;
    }

    public MenuItem getCheckedItem() {
        return this.j.f.d;
    }

    public int getDividerInsetEnd() {
        return this.j.u;
    }

    public int getDividerInsetStart() {
        return this.j.t;
    }

    public int getHeaderCount() {
        return this.j.c.getChildCount();
    }

    public Drawable getItemBackground() {
        return this.j.n;
    }

    public int getItemHorizontalPadding() {
        return this.j.p;
    }

    public int getItemIconPadding() {
        return this.j.r;
    }

    public ColorStateList getItemIconTintList() {
        return this.j.m;
    }

    public int getItemMaxLines() {
        return this.j.z;
    }

    public ColorStateList getItemTextColor() {
        return this.j.l;
    }

    public int getItemVerticalPadding() {
        return this.j.q;
    }

    public Menu getMenu() {
        return this.i;
    }

    public int getSubheaderInsetEnd() {
        return this.j.w;
    }

    public int getSubheaderInsetStart() {
        return this.j.v;
    }

    public final Pair h() {
        ViewParent parent = getParent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((parent instanceof DrawerLayout) && (layoutParams instanceof DrawerLayout.LayoutParams)) {
            return new Pair((DrawerLayout) parent, (DrawerLayout.LayoutParams) layoutParams);
        }
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
        ViewParent parent = getParent();
        if (!(parent instanceof DrawerLayout) || ((x40) this.t.c) == null) {
            return;
        }
        DrawerLayout drawerLayout = (DrawerLayout) parent;
        f90 f90Var = this.u;
        if (f90Var == null) {
            drawerLayout.getClass();
        } else {
            ArrayList arrayList = drawerLayout.u;
            if (arrayList != null) {
                arrayList.remove(f90Var);
            }
        }
        if (f90Var == null) {
            return;
        }
        if (drawerLayout.u == null) {
            drawerLayout.u = new ArrayList();
        }
        drawerLayout.u.add(f90Var);
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.n);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            DrawerLayout drawerLayout = (DrawerLayout) parent;
            f90 f90Var = this.u;
            if (f90Var == null) {
                drawerLayout.getClass();
                return;
            }
            ArrayList arrayList = drawerLayout.u;
            if (arrayList == null) {
                return;
            }
            arrayList.remove(f90Var);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int i3 = this.k;
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), i3), 1073741824);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        this.i.t(savedState.d);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.d = bundle;
        this.i.v(bundle);
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        ki0 ki0Var;
        ki0 ki0Var2;
        super.onSizeChanged(i, i2, i3, i4);
        if ((getParent() instanceof DrawerLayout) && (getLayoutParams() instanceof DrawerLayout.LayoutParams) && (i5 = this.q) > 0 && (getBackground() instanceof u50)) {
            int i6 = ((DrawerLayout.LayoutParams) getLayoutParams()).a;
            WeakHashMap weakHashMap = xr0.a;
            boolean z = Gravity.getAbsoluteGravity(i6, getLayoutDirection()) == 3;
            u50 u50Var = (u50) getBackground();
            ii0 ii0VarG = u50Var.b.a.g();
            ii0VarG.c(i5);
            if (z) {
                ii0VarG.e = new e(0.0f);
                ii0VarG.h = new e(0.0f);
            } else {
                ii0VarG.f = new e(0.0f);
                ii0VarG.g = new e(0.0f);
            }
            ki0 ki0VarA = ii0VarG.a();
            u50Var.setShapeAppearanceModel(ki0VarA);
            wi0 wi0Var = this.r;
            wi0Var.c = ki0VarA;
            boolean zIsEmpty = wi0Var.d.isEmpty();
            Path path = wi0Var.e;
            if (!zIsEmpty && (ki0Var2 = wi0Var.c) != null) {
                li0.a.a(ki0Var2, 1.0f, wi0Var.d, null, path);
            }
            wi0Var.a(this);
            RectF rectF = new RectF(0.0f, 0.0f, i, i2);
            wi0Var.d = rectF;
            if (!rectF.isEmpty() && (ki0Var = wi0Var.c) != null) {
                li0.a.a(ki0Var, 1.0f, wi0Var.d, null, path);
            }
            wi0Var.a(this);
            wi0Var.b = true;
            wi0Var.a(this);
        }
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.p = z;
    }

    public void setCheckedItem(int i) {
        MenuItem menuItemFindItem = this.i.findItem(i);
        if (menuItemFindItem != null) {
            this.j.f.h((m60) menuItemFindItem);
        }
    }

    public void setDividerInsetEnd(int i) {
        b90 b90Var = this.j;
        b90Var.u = i;
        b90Var.l(false);
    }

    public void setDividerInsetStart(int i) {
        b90 b90Var = this.j;
        b90Var.t = i;
        b90Var.l(false);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oy.N(this, f);
    }

    public void setForceCompatClippingEnabled(boolean z) {
        wi0 wi0Var = this.r;
        if (z != wi0Var.a) {
            wi0Var.a = z;
            wi0Var.a(this);
        }
    }

    public void setItemBackground(Drawable drawable) {
        b90 b90Var = this.j;
        b90Var.n = drawable;
        b90Var.l(false);
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(ah.b(getContext(), i));
    }

    public void setItemHorizontalPadding(int i) {
        b90 b90Var = this.j;
        b90Var.p = i;
        b90Var.l(false);
    }

    public void setItemHorizontalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        b90 b90Var = this.j;
        b90Var.p = dimensionPixelSize;
        b90Var.l(false);
    }

    public void setItemIconPadding(int i) {
        b90 b90Var = this.j;
        b90Var.r = i;
        b90Var.l(false);
    }

    public void setItemIconPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        b90 b90Var = this.j;
        b90Var.r = dimensionPixelSize;
        b90Var.l(false);
    }

    public void setItemIconSize(int i) {
        b90 b90Var = this.j;
        if (b90Var.s != i) {
            b90Var.s = i;
            b90Var.x = true;
            b90Var.l(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        b90 b90Var = this.j;
        b90Var.m = colorStateList;
        b90Var.l(false);
    }

    public void setItemMaxLines(int i) {
        b90 b90Var = this.j;
        b90Var.z = i;
        b90Var.l(false);
    }

    public void setItemTextAppearance(int i) {
        b90 b90Var = this.j;
        b90Var.j = i;
        b90Var.l(false);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        b90 b90Var = this.j;
        b90Var.k = z;
        b90Var.l(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        b90 b90Var = this.j;
        b90Var.l = colorStateList;
        b90Var.l(false);
    }

    public void setItemVerticalPadding(int i) {
        b90 b90Var = this.j;
        b90Var.q = i;
        b90Var.l(false);
    }

    public void setItemVerticalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        b90 b90Var = this.j;
        b90Var.q = dimensionPixelSize;
        b90Var.l(false);
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        b90 b90Var = this.j;
        if (b90Var != null) {
            b90Var.C = i;
            NavigationMenuView navigationMenuView = b90Var.b;
            if (navigationMenuView != null) {
                navigationMenuView.setOverScrollMode(i);
            }
        }
    }

    public void setSubheaderInsetEnd(int i) {
        b90 b90Var = this.j;
        b90Var.w = i;
        b90Var.l(false);
    }

    public void setSubheaderInsetStart(int i) {
        b90 b90Var = this.j;
        b90Var.v = i;
        b90Var.l(false);
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.o = z;
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:59:0x019f A[PHI: r8
      0x019f: PHI (r8v3 android.graphics.drawable.Drawable) = 
      (r8v2 android.graphics.drawable.Drawable)
      (r8v7 android.graphics.drawable.Drawable)
      (r8v2 android.graphics.drawable.Drawable)
     binds: [B:51:0x0161, B:57:0x0187, B:55:0x0171] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public NavigationView(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instruction units count: 790
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.i.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.j.f.h((m60) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public void setNavigationItemSelectedListener(g90 g90Var) {
    }
}
