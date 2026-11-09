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
import defpackage.cy0;
import defpackage.de0;
import defpackage.eh0;
import defpackage.f51;
import defpackage.hh0;
import defpackage.ih0;
import defpackage.l9;
import defpackage.ld0;
import defpackage.lv0;
import defpackage.ma0;
import defpackage.mc0;
import defpackage.nc0;
import defpackage.nd0;
import defpackage.o0;
import defpackage.ou;
import defpackage.pi;
import defpackage.q3;
import defpackage.s81;
import defpackage.t40;
import defpackage.tg0;
import defpackage.wp;
import defpackage.xp;
import defpackage.xu0;
import defpackage.y4;
import defpackage.yp;
import defpackage.zu0;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationView extends ScrimInsetsFrameLayout implements mc0 {
    public static final int[] d0 = {R.attr.state_checked};
    public static final int[] e0 = {-16842910};
    public static final int f0 = R$style.Widget_Design_NavigationView;
    public final tg0 L;
    public final eh0 M;
    public final int N;
    public final int[] O;
    public cy0 P;
    public final q3 Q;
    public boolean R;
    public boolean S;
    public int T;
    public final boolean U;
    public final int V;
    public final lv0 W;
    public final nd0 a0;
    public final ou b0;
    public final hh0 c0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new d();
        public Bundle G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readBundle(classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.G);
        }
    }

    public NavigationView(Context context) {
        this(context, null);
    }

    private MenuInflater getMenuInflater() {
        if (this.P == null) {
            this.P = new cy0(getContext());
        }
        return this.P;
    }

    @Override // defpackage.mc0
    public final void a() {
        int i = 0;
        Pair pairI = i();
        DrawerLayout drawerLayout = (DrawerLayout) pairI.first;
        nd0 nd0Var = this.a0;
        l9 l9Var = nd0Var.f;
        nd0Var.f = null;
        if (l9Var == null || Build.VERSION.SDK_INT < 34) {
            drawerLayout.b(this, true);
            return;
        }
        int i2 = ((DrawerLayout.LayoutParams) pairI.second).a;
        int i3 = yp.a;
        nd0Var.c(l9Var, i2, new xp(drawerLayout, i, this), new wp(i, drawerLayout));
    }

    @Override // defpackage.mc0
    public final void b(l9 l9Var) {
        int i = ((DrawerLayout.LayoutParams) i().second).a;
        nd0 nd0Var = this.a0;
        if (nd0Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        l9 l9Var2 = nd0Var.f;
        nd0Var.f = l9Var;
        float f = l9Var.c;
        if (l9Var2 != null) {
            nd0Var.d(f, i, l9Var.d == 0);
        }
        if (this.U) {
            this.T = y4.c(0, nd0Var.a.getInterpolation(f), this.V);
            h(getWidth(), getHeight());
        }
    }

    @Override // defpackage.mc0
    public final void c(l9 l9Var) {
        i();
        this.a0.f = l9Var;
    }

    @Override // defpackage.mc0
    public final void d() {
        i();
        this.a0.b();
        if (!this.U || this.T == 0) {
            return;
        }
        this.T = 0;
        h(getWidth(), getHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        lv0 lv0Var = this.W;
        if (lv0Var.b()) {
            Path path = lv0Var.e;
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
    public final void e(s81 s81Var) {
        eh0 eh0Var = this.M;
        eh0Var.getClass();
        int iD = s81Var.d();
        if (eh0Var.d0 != iD) {
            eh0Var.d0 = iD;
            int i = (eh0Var.F.getChildCount() <= 0 && eh0Var.b0) ? eh0Var.d0 : 0;
            NavigationMenuView navigationMenuView = eh0Var.E;
            navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
        }
        NavigationMenuView navigationMenuView2 = eh0Var.E;
        navigationMenuView2.setPadding(0, navigationMenuView2.getPaddingTop(), 0, s81Var.a());
        f51.b(eh0Var.F, s81Var);
    }

    public final ColorStateList f(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListY = t40.Y(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(R$attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateListY.getDefaultColor();
        int[] iArr = e0;
        return new ColorStateList(new int[][]{iArr, d0, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateListY.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public final InsetDrawable g(ou ouVar, ColorStateList colorStateList) {
        int i = R$styleable.NavigationView_itemShapeAppearance;
        TypedArray typedArray = (TypedArray) ouVar.G;
        ld0 ld0Var = new ld0(zu0.a(getContext(), typedArray.getResourceId(i, 0), typedArray.getResourceId(R$styleable.NavigationView_itemShapeAppearanceOverlay, 0)).a());
        ld0Var.m(colorStateList);
        return new InsetDrawable((Drawable) ld0Var, typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetStart, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetTop, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetEnd, 0), typedArray.getDimensionPixelSize(R$styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    public nd0 getBackHelper() {
        return this.a0;
    }

    public MenuItem getCheckedItem() {
        return this.M.I.d;
    }

    public int getDividerInsetEnd() {
        return this.M.X;
    }

    public int getDividerInsetStart() {
        return this.M.W;
    }

    public int getHeaderCount() {
        return this.M.F.getChildCount();
    }

    public Drawable getItemBackground() {
        return this.M.Q;
    }

    public int getItemHorizontalPadding() {
        return this.M.S;
    }

    public int getItemIconPadding() {
        return this.M.U;
    }

    public ColorStateList getItemIconTintList() {
        return this.M.P;
    }

    public int getItemMaxLines() {
        return this.M.c0;
    }

    public ColorStateList getItemTextColor() {
        return this.M.O;
    }

    public int getItemVerticalPadding() {
        return this.M.T;
    }

    public Menu getMenu() {
        return this.L;
    }

    public int getSubheaderInsetEnd() {
        return this.M.Z;
    }

    public int getSubheaderInsetStart() {
        return this.M.Y;
    }

    public final void h(int i, int i2) {
        if ((getParent() instanceof DrawerLayout) && (getLayoutParams() instanceof DrawerLayout.LayoutParams)) {
            if ((this.T > 0 || this.U) && (getBackground() instanceof ld0)) {
                int i3 = ((DrawerLayout.LayoutParams) getLayoutParams()).a;
                WeakHashMap weakHashMap = f51.a;
                boolean z = Gravity.getAbsoluteGravity(i3, getLayoutDirection()) == 3;
                ld0 ld0Var = (ld0) getBackground();
                xu0 xu0VarG = ld0Var.E.a.g();
                xu0VarG.c(this.T);
                if (z) {
                    xu0VarG.e = new o0(0.0f);
                    xu0VarG.h = new o0(0.0f);
                } else {
                    xu0VarG.f = new o0(0.0f);
                    xu0VarG.g = new o0(0.0f);
                }
                zu0 zu0VarA = xu0VarG.a();
                ld0Var.setShapeAppearanceModel(zu0VarA);
                lv0 lv0Var = this.W;
                lv0Var.c = zu0VarA;
                lv0Var.c();
                lv0Var.a(this);
                lv0Var.d = new RectF(0.0f, 0.0f, i, i2);
                lv0Var.c();
                lv0Var.a(this);
                lv0Var.b = true;
                lv0Var.a(this);
            }
        }
    }

    public final Pair i() {
        ViewParent parent = getParent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((parent instanceof DrawerLayout) && (layoutParams instanceof DrawerLayout.LayoutParams)) {
            return new Pair((DrawerLayout) parent, (DrawerLayout.LayoutParams) layoutParams);
        }
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        nc0 nc0Var;
        super.onAttachedToWindow();
        ma0.p0(this);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            ou ouVar = this.b0;
            if (((nc0) ouVar.F) != null) {
                DrawerLayout drawerLayout = (DrawerLayout) parent;
                hh0 hh0Var = this.c0;
                if (hh0Var == null) {
                    drawerLayout.getClass();
                } else {
                    ArrayList arrayList = drawerLayout.a0;
                    if (arrayList != null) {
                        arrayList.remove(hh0Var);
                    }
                }
                if (hh0Var != null) {
                    if (drawerLayout.a0 == null) {
                        drawerLayout.a0 = new ArrayList();
                    }
                    drawerLayout.a0.add(hh0Var);
                }
                if (!DrawerLayout.k(this) || (nc0Var = (nc0) ouVar.F) == null) {
                    return;
                }
                nc0Var.b((mc0) ouVar.G, (FrameLayout) ouVar.H, true);
            }
        }
    }

    @Override // com.google.android.material.internal.ScrimInsetsFrameLayout, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.Q);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            DrawerLayout drawerLayout = (DrawerLayout) parent;
            hh0 hh0Var = this.c0;
            if (hh0Var == null) {
                drawerLayout.getClass();
                return;
            }
            ArrayList arrayList = drawerLayout.a0;
            if (arrayList == null) {
                return;
            }
            arrayList.remove(hh0Var);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int i3 = this.N;
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
        super.onRestoreInstanceState(savedState.E);
        this.L.t(savedState.G);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.G = bundle;
        this.L.v(bundle);
        return savedState;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        h(i, i2);
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.S = z;
    }

    public void setCheckedItem(int i) {
        MenuItem menuItemFindItem = this.L.findItem(i);
        if (menuItemFindItem != null) {
            this.M.I.h((de0) menuItemFindItem);
        }
    }

    public void setDividerInsetEnd(int i) {
        eh0 eh0Var = this.M;
        eh0Var.X = i;
        eh0Var.j(false);
    }

    public void setDividerInsetStart(int i) {
        eh0 eh0Var = this.M;
        eh0Var.W = i;
        eh0Var.j(false);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ma0.n0(this, f);
    }

    public void setForceCompatClippingEnabled(boolean z) {
        lv0 lv0Var = this.W;
        if (z != lv0Var.a) {
            lv0Var.a = z;
            lv0Var.a(this);
        }
    }

    public void setItemBackground(Drawable drawable) {
        eh0 eh0Var = this.M;
        eh0Var.Q = drawable;
        eh0Var.j(false);
    }

    public void setItemBackgroundResource(int i) {
        setItemBackground(pi.b(getContext(), i));
    }

    public void setItemHorizontalPadding(int i) {
        eh0 eh0Var = this.M;
        eh0Var.S = i;
        eh0Var.j(false);
    }

    public void setItemHorizontalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        eh0 eh0Var = this.M;
        eh0Var.S = dimensionPixelSize;
        eh0Var.j(false);
    }

    public void setItemIconPadding(int i) {
        eh0 eh0Var = this.M;
        eh0Var.U = i;
        eh0Var.j(false);
    }

    public void setItemIconPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        eh0 eh0Var = this.M;
        eh0Var.U = dimensionPixelSize;
        eh0Var.j(false);
    }

    public void setItemIconSize(int i) {
        eh0 eh0Var = this.M;
        if (eh0Var.V != i) {
            eh0Var.V = i;
            eh0Var.a0 = true;
            eh0Var.j(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        eh0 eh0Var = this.M;
        eh0Var.P = colorStateList;
        eh0Var.j(false);
    }

    public void setItemMaxLines(int i) {
        eh0 eh0Var = this.M;
        eh0Var.c0 = i;
        eh0Var.j(false);
    }

    public void setItemTextAppearance(int i) {
        eh0 eh0Var = this.M;
        eh0Var.M = i;
        eh0Var.j(false);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        eh0 eh0Var = this.M;
        eh0Var.N = z;
        eh0Var.j(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        eh0 eh0Var = this.M;
        eh0Var.O = colorStateList;
        eh0Var.j(false);
    }

    public void setItemVerticalPadding(int i) {
        eh0 eh0Var = this.M;
        eh0Var.T = i;
        eh0Var.j(false);
    }

    public void setItemVerticalPaddingResource(int i) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(i);
        eh0 eh0Var = this.M;
        eh0Var.T = dimensionPixelSize;
        eh0Var.j(false);
    }

    @Override // android.view.View
    public void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        eh0 eh0Var = this.M;
        if (eh0Var != null) {
            eh0Var.f0 = i;
            NavigationMenuView navigationMenuView = eh0Var.E;
            if (navigationMenuView != null) {
                navigationMenuView.setOverScrollMode(i);
            }
        }
    }

    public void setSubheaderInsetEnd(int i) {
        eh0 eh0Var = this.M;
        eh0Var.Z = i;
        eh0Var.j(false);
    }

    public void setSubheaderInsetStart(int i) {
        eh0 eh0Var = this.M;
        eh0Var.Y = i;
        eh0Var.j(false);
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.R = z;
    }

    public NavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.material.R$attr.navigationViewStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01a0 A[PHI: r8
      0x01a0: PHI (r8v3 android.graphics.drawable.Drawable) = 
      (r8v2 android.graphics.drawable.Drawable)
      (r8v7 android.graphics.drawable.Drawable)
      (r8v2 android.graphics.drawable.Drawable)
     binds: [B:54:0x0162, B:60:0x0188, B:58:0x0172] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v25 */
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
        To view partially-correct add '--show-bad-code' argument
    */
    public NavigationView(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            Method dump skipped, instruction units count: 813
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckedItem(MenuItem menuItem) {
        MenuItem menuItemFindItem = this.L.findItem(menuItem.getItemId());
        if (menuItemFindItem != null) {
            this.M.I.h((de0) menuItemFindItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }

    public void setNavigationItemSelectedListener(ih0 ih0Var) {
    }
}
