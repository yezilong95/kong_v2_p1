package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$integer;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import defpackage.a4;
import defpackage.b4;
import defpackage.e5;
import defpackage.hk;
import defpackage.il;
import defpackage.j0;
import defpackage.j90;
import defpackage.jq0;
import defpackage.k9;
import defpackage.lr0;
import defpackage.ms0;
import defpackage.o70;
import defpackage.oy;
import defpackage.rp0;
import defpackage.t3;
import defpackage.u50;
import defpackage.un0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.xu0;
import defpackage.y3;
import defpackage.y50;
import defpackage.yd;
import defpackage.z3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements androidx.coordinatorlayout.widget.a {
    public static final int z = R$style.Widget_Design_AppBarLayout;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public int g;
    public xu0 h;
    public ArrayList i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public WeakReference o;
    public final ColorStateList p;
    public ValueAnimator q;
    public final ValueAnimator.AnimatorUpdateListener r;
    public final ArrayList s;
    public final long t;
    public final TimeInterpolator u;
    public int[] v;
    public Drawable w;
    public final float x;
    public Behavior y;

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public int a;
        public final e5 b;
        public final Interpolator c;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            this.b = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollEffect, 0) != 1 ? null : new e5(1);
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.AppBarLayout_Layout_layout_scrollInterpolator)) {
                this.c = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.AppBarLayout_Layout_layout_scrollInterpolator, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final AppBarLayout A(ArrayList arrayList) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                View view = (View) arrayList.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final float B(View view) {
            int i;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                CoordinatorLayout.Behavior behavior = ((androidx.coordinatorlayout.widget.c) appBarLayout.getLayoutParams()).a;
                int iX = behavior instanceof BaseBehavior ? ((BaseBehavior) behavior).x() : 0;
                if ((downNestedPreScrollRange == 0 || totalScrollRange + iX > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (iX / i) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public final int C(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : view.getMeasuredHeight();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean f(View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            int iN;
            CoordinatorLayout.Behavior behavior = ((androidx.coordinatorlayout.widget.c) view2.getLayoutParams()).a;
            if (behavior instanceof BaseBehavior) {
                int bottom = (view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).k + this.f;
                if (this.g == 0) {
                    iN = 0;
                } else {
                    float fB = B(view2);
                    int i = this.g;
                    iN = rp0.n((int) (fB * i), 0, i);
                }
                int i2 = bottom - iN;
                WeakHashMap weakHashMap = xr0.a;
                view.offsetTopAndBottom(i2);
            }
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.m) {
                    appBarLayout.e(appBarLayout.f(view));
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void i(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                xr0.l(coordinatorLayout, j0.h.a());
                xr0.m(coordinatorLayout, j0.i.a());
                xr0.i(coordinatorLayout, 0);
                xr0.p(coordinatorLayout, null);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean q(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout appBarLayout;
            ArrayList arrayListO = coordinatorLayout.o(view);
            int size = arrayListO.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    appBarLayout = null;
                    break;
                }
                View view2 = (View) arrayListO.get(i);
                if (view2 instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) view2;
                    break;
                }
                i++;
            }
            if (appBarLayout != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                int width = coordinatorLayout.getWidth();
                int height = coordinatorLayout.getHeight();
                Rect rect3 = this.d;
                rect3.set(0, 0, width, height);
                if (!rect3.contains(rect2)) {
                    appBarLayout.d(false, !z, true);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    public static LayoutParams a(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            LayoutParams layoutParams2 = new LayoutParams((LinearLayout.LayoutParams) layoutParams);
            layoutParams2.a = 1;
            return layoutParams2;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            LayoutParams layoutParams3 = new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
            layoutParams3.a = 1;
            return layoutParams3;
        }
        LayoutParams layoutParams4 = new LayoutParams(layoutParams);
        layoutParams4.a = 1;
        return layoutParams4;
    }

    private ColorStateList getBackgroundCSL() {
        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            return ColorStateList.valueOf(((ColorDrawable) background).getColor());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return z3.b(background);
        }
        return null;
    }

    public void addLiftOnScrollListener(a4 a4Var) {
        this.s.add(a4Var);
    }

    public void addOnOffsetChangedListener(y3 y3Var) {
        if (this.i == null) {
            this.i = new ArrayList();
        }
        if (y3Var == null || this.i.contains(y3Var)) {
            return;
        }
        this.i.add(y3Var);
    }

    public final void b() {
        Behavior behavior = this.y;
        BaseBehavior.SavedState savedStateJ = (behavior == null || this.c == -1 || this.g != 0) ? null : behavior.J(AbsSavedState.c, this);
        this.c = -1;
        this.d = -1;
        this.e = -1;
        if (savedStateJ != null) {
            Behavior behavior2 = this.y;
            if (behavior2.n != null) {
                return;
            }
            behavior2.n = savedStateJ;
        }
    }

    public final void c(int i) {
        this.b = i;
        if (!willNotDraw()) {
            WeakHashMap weakHashMap = xr0.a;
            postInvalidateOnAnimation();
        }
        ArrayList arrayList = this.i;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                y3 y3Var = (y3) this.i.get(i2);
                if (y3Var != null) {
                    CollapsingToolbarLayout collapsingToolbarLayout = ((f) y3Var).a;
                    collapsingToolbarLayout.z = i;
                    xu0 xu0Var = collapsingToolbarLayout.B;
                    int iD = xu0Var != null ? xu0Var.d() : 0;
                    int childCount = collapsingToolbarLayout.getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = collapsingToolbarLayout.getChildAt(i3);
                        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams();
                        ms0 ms0VarB = CollapsingToolbarLayout.b(childAt);
                        int i4 = layoutParams.a;
                        if (i4 == 1) {
                            ms0VarB.b(rp0.n(-i, 0, ((collapsingToolbarLayout.getHeight() - CollapsingToolbarLayout.b(childAt).b) - childAt.getHeight()) - ((FrameLayout.LayoutParams) ((CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams())).bottomMargin));
                        } else if (i4 == 2) {
                            ms0VarB.b(Math.round((-i) * layoutParams.b));
                        }
                    }
                    collapsingToolbarLayout.d();
                    if (collapsingToolbarLayout.q != null && iD > 0) {
                        WeakHashMap weakHashMap2 = xr0.a;
                        collapsingToolbarLayout.postInvalidateOnAnimation();
                    }
                    int height = collapsingToolbarLayout.getHeight();
                    WeakHashMap weakHashMap3 = xr0.a;
                    int minimumHeight = (height - collapsingToolbarLayout.getMinimumHeight()) - iD;
                    float scrimVisibleHeightTrigger = height - collapsingToolbarLayout.getScrimVisibleHeightTrigger();
                    float f = minimumHeight;
                    float fMin = Math.min(1.0f, scrimVisibleHeightTrigger / f);
                    yd ydVar = collapsingToolbarLayout.l;
                    ydVar.d = fMin;
                    ydVar.e = hk.d(1.0f, fMin, 0.5f, fMin);
                    ydVar.f = collapsingToolbarLayout.z + minimumHeight;
                    ydVar.p(Math.abs(i) / f);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d(boolean z2, boolean z3, boolean z4) {
        this.g = (z2 ? 1 : 2) | (z3 ? 4 : 0) | (z4 ? 8 : 0);
        requestLayout();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.w == null || getTopInset() <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(0.0f, -this.b);
        this.w.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.w;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final boolean e(boolean z2) {
        if (this.j || this.l == z2) {
            return false;
        }
        this.l = z2;
        refreshDrawableState();
        if (!this.m || !(getBackground() instanceof u50)) {
            return true;
        }
        if (this.p != null) {
            g(z2 ? 0.0f : 255.0f, z2 ? 255.0f : 0.0f);
            return true;
        }
        float f = this.x;
        g(z2 ? 0.0f : f, z2 ? f : 0.0f);
        return true;
    }

    public final boolean f(View view) {
        int i;
        if (this.o == null && (i = this.n) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.n);
            }
            if (viewFindViewById != null) {
                this.o = new WeakReference(viewFindViewById);
            }
        }
        WeakReference weakReference = this.o;
        View view2 = weakReference != null ? (View) weakReference.get() : null;
        if (view2 != null) {
            view = view2;
        }
        if (view != null) {
            return view.canScrollVertically(-1) || view.getScrollY() > 0;
        }
        return false;
    }

    public final void g(float f, float f2) {
        ValueAnimator valueAnimator = this.q;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        this.q = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.t);
        this.q.setInterpolator(this.u);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.r;
        if (animatorUpdateListener != null) {
            this.q.addUpdateListener(animatorUpdateListener);
        }
        this.q.start();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.a = 1;
        return layoutParams;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    @Override // androidx.coordinatorlayout.widget.a
    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        Behavior behavior = new Behavior();
        this.y = behavior;
        return behavior;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getDownNestedPreScrollRange() {
        /*
            r11 = this;
            r0 = 5
            r1 = 8
            int r2 = r11.d
            r3 = -1
            if (r2 == r3) goto L9
            return r2
        L9:
            int r2 = r11.getChildCount()
            int r2 = r2 + (-1)
            r4 = 0
            r5 = r4
        L11:
            if (r2 < 0) goto L68
            android.view.View r6 = r11.getChildAt(r2)
            int r7 = r6.getVisibility()
            if (r7 != r1) goto L1e
            goto L66
        L1e:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r7 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r7
            int r8 = r6.getMeasuredHeight()
            int r9 = r7.a
            r10 = r9 & 5
            if (r10 != r0) goto L63
            int r10 = r7.topMargin
            int r7 = r7.bottomMargin
            int r10 = r10 + r7
            r7 = r9 & 8
            if (r7 == 0) goto L3f
            java.util.WeakHashMap r7 = defpackage.xr0.a
            int r7 = r6.getMinimumHeight()
        L3d:
            int r7 = r7 + r10
            goto L4e
        L3f:
            r7 = r9 & 2
            if (r7 == 0) goto L4c
            java.util.WeakHashMap r7 = defpackage.xr0.a
            int r7 = r6.getMinimumHeight()
            int r7 = r8 - r7
            goto L3d
        L4c:
            int r7 = r10 + r8
        L4e:
            if (r2 != 0) goto L61
            java.util.WeakHashMap r9 = defpackage.xr0.a
            boolean r6 = r6.getFitsSystemWindows()
            if (r6 == 0) goto L61
            int r6 = r11.getTopInset()
            int r8 = r8 - r6
            int r7 = java.lang.Math.min(r7, r8)
        L61:
            int r5 = r5 + r7
            goto L66
        L63:
            if (r5 <= 0) goto L66
            goto L68
        L66:
            int r2 = r2 + r3
            goto L11
        L68:
            int r0 = java.lang.Math.max(r4, r5)
            r11.d = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public int getDownNestedScrollRange() {
        int i = this.e;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int minimumHeight = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + childAt.getMeasuredHeight();
                int i3 = layoutParams.a;
                if ((i3 & 1) == 0) {
                    break;
                }
                minimumHeight += measuredHeight;
                if ((i3 & 2) != 0) {
                    WeakHashMap weakHashMap = xr0.a;
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int iMax = Math.max(0, minimumHeight);
        this.e = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.n;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        WeakHashMap weakHashMap = xr0.a;
        int minimumHeight = getMinimumHeight();
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            minimumHeight = childCount >= 1 ? getChildAt(childCount - 1).getMinimumHeight() : 0;
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return (minimumHeight * 2) + topInset;
    }

    public int getPendingAction() {
        return this.g;
    }

    public Drawable getStatusBarForeground() {
        return this.w;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        xu0 xu0Var = this.h;
        if (xu0Var != null) {
            return xu0Var.d();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.c;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int minimumHeight = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i3 = layoutParams.a;
                if ((i3 & 1) == 0) {
                    break;
                }
                int topInset = measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + minimumHeight;
                if (i2 == 0) {
                    WeakHashMap weakHashMap = xr0.a;
                    if (childAt.getFitsSystemWindows()) {
                        topInset -= getTopInset();
                    }
                }
                minimumHeight = topInset;
                if ((i3 & 2) != 0) {
                    WeakHashMap weakHashMap2 = xr0.a;
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int iMax = Math.max(0, minimumHeight);
        this.c = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.v == null) {
            this.v = new int[4];
        }
        int[] iArr = this.v;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z2 = this.k;
        int i2 = R$attr.state_liftable;
        if (!z2) {
            i2 = -i2;
        }
        iArr[0] = i2;
        iArr[1] = (z2 && this.l) ? R$attr.state_lifted : -R$attr.state_lifted;
        int i3 = R$attr.state_collapsible;
        if (!z2) {
            i3 = -i3;
        }
        iArr[2] = i3;
        iArr[3] = (z2 && this.l) ? R$attr.state_collapsed : -R$attr.state_collapsed;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.o;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.o = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i, int i2, int i3, int i4) {
        boolean z3 = true;
        super.onLayout(z2, i, i2, i3, i4);
        WeakHashMap weakHashMap = xr0.a;
        if (getFitsSystemWindows() && getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (childAt.getVisibility() != 8 && !childAt.getFitsSystemWindows()) {
                int topInset = getTopInset();
                for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                    getChildAt(childCount).offsetTopAndBottom(topInset);
                }
            }
        }
        b();
        this.f = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            }
            if (((LayoutParams) getChildAt(i5).getLayoutParams()).c != null) {
                this.f = true;
                break;
            }
            i5++;
        }
        Drawable drawable = this.w;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.j) {
            return;
        }
        if (!this.m) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z3 = false;
                    break;
                }
                int i7 = ((LayoutParams) getChildAt(i6).getLayoutParams()).a;
                if ((i7 & 1) == 1 && (i7 & 10) != 0) {
                    break;
                } else {
                    i6++;
                }
            }
        }
        if (this.k != z3) {
            this.k = z3;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            WeakHashMap weakHashMap = xr0.a;
            if (getFitsSystemWindows() && getChildCount() > 0) {
                View childAt = getChildAt(0);
                if (childAt.getVisibility() != 8 && !childAt.getFitsSystemWindows()) {
                    int measuredHeight = getMeasuredHeight();
                    if (mode == Integer.MIN_VALUE) {
                        measuredHeight = rp0.n(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                    } else if (mode == 0) {
                        measuredHeight += getTopInset();
                    }
                    setMeasuredDimension(getMeasuredWidth(), measuredHeight);
                }
            }
        }
        b();
    }

    public void removeOnOffsetChangedListener(y3 y3Var) {
        ArrayList arrayList = this.i;
        if (arrayList == null || y3Var == null) {
            return;
        }
        arrayList.remove(y3Var);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oy.N(this, f);
    }

    public void setExpanded(boolean z2) {
        WeakHashMap weakHashMap = xr0.a;
        d(z2, isLaidOut(), true);
    }

    public void setLiftOnScroll(boolean z2) {
        this.m = z2;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.n = -1;
        if (view != null) {
            this.o = new WeakReference(view);
            return;
        }
        WeakReference weakReference = this.o;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.o = null;
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.n = i;
        WeakReference weakReference = this.o;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.o = null;
    }

    public void setLiftableOverrideEnabled(boolean z2) {
        this.j = z2;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.w;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.w = drawableMutate;
            boolean z2 = false;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.w.setState(getDrawableState());
                }
                Drawable drawable3 = this.w;
                WeakHashMap weakHashMap = xr0.a;
                il.b(drawable3, getLayoutDirection());
                this.w.setVisible(getVisibility() == 0, false);
                this.w.setCallback(this);
            }
            if (this.w != null && getTopInset() > 0) {
                z2 = true;
            }
            setWillNotDraw(true ^ z2);
            WeakHashMap weakHashMap2 = xr0.a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(rp0.y(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f) {
        jq0.c0(this, f);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z2 = i == 0;
        Drawable drawable = this.w;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.w;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.appBarLayoutStyle);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        public int k;
        public int l;
        public ValueAnimator m;
        public SavedState n;
        public WeakReference o;
        public boolean p;

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new e();
            public boolean d;
            public boolean e;
            public int f;
            public float g;
            public boolean h;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.d = parcel.readByte() != 0;
                this.e = parcel.readByte() != 0;
                this.f = parcel.readInt();
                this.g = parcel.readFloat();
                this.h = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.f);
                parcel.writeFloat(this.g);
                parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
            this.g = -1;
            this.i = -1;
        }

        public static View H(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof j90) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static void M(androidx.coordinatorlayout.widget.CoordinatorLayout r8, com.google.android.material.appbar.AppBarLayout r9, int r10, int r11, boolean r12) {
            /*
                Method dump skipped, instruction units count: 209
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.M(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final boolean A(View view) {
            WeakReference weakReference = this.o;
            if (weakReference == null) {
                return true;
            }
            View view2 = (View) weakReference.get();
            return (view2 == null || !view2.isShown() || view2.canScrollVertically(-1)) ? false : true;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int B(View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            return appBarLayout.getTopInset() + (-appBarLayout.getDownNestedScrollRange());
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final int C(View view) {
            return ((AppBarLayout) view).getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final void D(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            K(coordinatorLayout, appBarLayout);
            if (appBarLayout.m) {
                appBarLayout.e(appBarLayout.f(H(coordinatorLayout)));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0142  */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int E(androidx.coordinatorlayout.widget.CoordinatorLayout r18, android.view.View r19, int r20, int r21, int r22) {
            /*
                Method dump skipped, instruction units count: 335
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.E(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public final void G(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int iAbs = Math.abs(x() - i);
            float fAbs = Math.abs(0.0f);
            int iRound = fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / appBarLayout.getHeight()) + 1.0f) * 150.0f);
            int iX = x();
            if (iX == i) {
                ValueAnimator valueAnimator = this.m;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.m.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.m;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.m = valueAnimator3;
                valueAnimator3.setInterpolator(t3.e);
                this.m.addUpdateListener(new a(this, coordinatorLayout, appBarLayout));
            } else {
                valueAnimator2.cancel();
            }
            this.m.setDuration(Math.min(iRound, 600));
            this.m.setIntValues(iX, i);
            this.m.start();
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void I(androidx.coordinatorlayout.widget.CoordinatorLayout r9, com.google.android.material.appbar.AppBarLayout r10, android.view.View r11, int r12, int[] r13) {
            /*
                r8 = this;
                if (r12 == 0) goto L2b
                if (r12 >= 0) goto L11
                int r0 = r10.getTotalScrollRange()
                int r0 = -r0
                int r1 = r10.getDownNestedPreScrollRange()
                int r1 = r1 + r0
            Le:
                r6 = r0
                r7 = r1
                goto L18
            L11:
                int r0 = r10.getUpNestedPreScrollRange()
                int r0 = -r0
                r1 = 0
                goto Le
            L18:
                if (r6 == r7) goto L2b
                int r0 = r8.x()
                int r5 = r0 - r12
                r2 = r8
                r3 = r9
                r4 = r10
                int r9 = r2.E(r3, r4, r5, r6, r7)
                r10 = 1
                r13[r10] = r9
                goto L2c
            L2b:
                r4 = r10
            L2c:
                boolean r9 = r4.m
                if (r9 == 0) goto L37
                boolean r9 = r4.f(r11)
                r4.e(r9)
            L37:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.I(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int[]):void");
        }

        public final SavedState J(Parcelable parcelable, AppBarLayout appBarLayout) {
            int iW = w();
            int childCount = appBarLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + iW;
                if (childAt.getTop() + iW <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.c;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z = iW == 0;
                    savedState.e = z;
                    savedState.d = !z && (-iW) >= appBarLayout.getTotalScrollRange();
                    savedState.f = i;
                    WeakHashMap weakHashMap = xr0.a;
                    savedState.h = bottom == appBarLayout.getTopInset() + childAt.getMinimumHeight();
                    savedState.g = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public final void K(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            int paddingTop = appBarLayout.getPaddingTop() + appBarLayout.getTopInset();
            int iX = x() - paddingTop;
            int childCount = appBarLayout.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = appBarLayout.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if ((layoutParams.a & 32) == 32) {
                    top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                int i2 = -iX;
                if (top <= i2 && bottom >= i2) {
                    break;
                } else {
                    i++;
                }
            }
            if (i >= 0) {
                View childAt2 = appBarLayout.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.a;
                if ((i3 & 17) == 17) {
                    int topInset = -childAt2.getTop();
                    int minimumHeight = -childAt2.getBottom();
                    if (i == 0) {
                        WeakHashMap weakHashMap = xr0.a;
                        if (appBarLayout.getFitsSystemWindows() && childAt2.getFitsSystemWindows()) {
                            topInset -= appBarLayout.getTopInset();
                        }
                    }
                    if ((i3 & 2) == 2) {
                        WeakHashMap weakHashMap2 = xr0.a;
                        minimumHeight += childAt2.getMinimumHeight();
                    } else if ((i3 & 5) == 5) {
                        WeakHashMap weakHashMap3 = xr0.a;
                        int minimumHeight2 = childAt2.getMinimumHeight() + minimumHeight;
                        if (iX < minimumHeight2) {
                            topInset = minimumHeight2;
                        } else {
                            minimumHeight = minimumHeight2;
                        }
                    }
                    if ((i3 & 32) == 32) {
                        topInset += ((LinearLayout.LayoutParams) layoutParams2).topMargin;
                        minimumHeight -= ((LinearLayout.LayoutParams) layoutParams2).bottomMargin;
                    }
                    if (iX < (minimumHeight + topInset) / 2) {
                        topInset = minimumHeight;
                    }
                    G(coordinatorLayout, appBarLayout, rp0.n(topInset + paddingTop, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x009d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void L(androidx.coordinatorlayout.widget.CoordinatorLayout r12, com.google.android.material.appbar.AppBarLayout r13) {
            /*
                r11 = this;
                j0 r0 = defpackage.j0.h
                int r0 = r0.a()
                defpackage.xr0.l(r12, r0)
                j0 r0 = defpackage.j0.i
                int r0 = r0.a()
                defpackage.xr0.m(r12, r0)
                r0 = 0
                defpackage.xr0.i(r12, r0)
                int r1 = r13.getTotalScrollRange()
                if (r1 != 0) goto L1f
            L1c:
                r5 = r11
                goto Lb7
            L1f:
                int r1 = r12.getChildCount()
                r2 = r0
            L24:
                r3 = 0
                if (r2 >= r1) goto L3c
                android.view.View r4 = r12.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
                androidx.coordinatorlayout.widget.c r5 = (androidx.coordinatorlayout.widget.c) r5
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r5 = r5.a
                boolean r5 = r5 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r5 == 0) goto L39
                r8 = r4
                goto L3d
            L39:
                int r2 = r2 + 1
                goto L24
            L3c:
                r8 = r3
            L3d:
                if (r8 != 0) goto L40
                goto L1c
            L40:
                int r1 = r13.getChildCount()
                r2 = r0
            L45:
                if (r2 >= r1) goto L1c
                android.view.View r4 = r13.getChildAt(r2)
                android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
                int r4 = r4.a
                if (r4 == 0) goto Lb1
                android.view.View$AccessibilityDelegate r1 = defpackage.xr0.d(r12)
                if (r1 == 0) goto L5c
                goto L64
            L5c:
                com.google.android.material.appbar.b r1 = new com.google.android.material.appbar.b
                r1.<init>(r11)
                defpackage.xr0.p(r12, r1)
            L64:
                int r1 = r11.x()
                int r2 = r13.getTotalScrollRange()
                int r2 = -r2
                r10 = 1
                if (r1 == r2) goto L7b
                j0 r1 = defpackage.j0.h
                com.google.android.material.appbar.d r2 = new com.google.android.material.appbar.d
                r2.<init>(r13, r0)
                defpackage.xr0.n(r12, r1, r3, r2)
                r0 = r10
            L7b:
                int r1 = r11.x()
                if (r1 == 0) goto L9d
                r1 = -1
                boolean r1 = r8.canScrollVertically(r1)
                if (r1 == 0) goto L9f
                int r1 = r13.getDownNestedPreScrollRange()
                int r9 = -r1
                if (r9 == 0) goto L9d
                j0 r0 = defpackage.j0.i
                com.google.android.material.appbar.c r4 = new com.google.android.material.appbar.c
                r5 = r11
                r6 = r12
                r7 = r13
                r4.<init>(r5, r6, r7, r8, r9)
                defpackage.xr0.n(r6, r0, r3, r4)
                goto Lae
            L9d:
                r5 = r11
                goto Lad
            L9f:
                r5 = r11
                r6 = r12
                r7 = r13
                j0 r12 = defpackage.j0.i
                com.google.android.material.appbar.d r13 = new com.google.android.material.appbar.d
                r13.<init>(r7, r10)
                defpackage.xr0.n(r6, r12, r3, r13)
                goto Lae
            Lad:
                r10 = r0
            Lae:
                r5.p = r10
                return
            Lb1:
                r5 = r11
                r6 = r12
                r7 = r13
                int r2 = r2 + 1
                goto L45
            Lb7:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.L(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout):void");
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            int iRound;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.l(coordinatorLayout, appBarLayout, i);
            int pendingAction = appBarLayout.getPendingAction();
            SavedState savedState = this.n;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i2 = -appBarLayout.getUpNestedPreScrollRange();
                        if (z) {
                            G(coordinatorLayout, appBarLayout, i2);
                        } else {
                            F(coordinatorLayout, appBarLayout, i2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z) {
                            G(coordinatorLayout, appBarLayout, 0);
                        } else {
                            F(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState.d) {
                F(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState.e) {
                F(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState.f);
                int i3 = -childAt.getBottom();
                if (this.n.h) {
                    WeakHashMap weakHashMap = xr0.a;
                    iRound = appBarLayout.getTopInset() + childAt.getMinimumHeight() + i3;
                } else {
                    iRound = Math.round(childAt.getHeight() * this.n.g) + i3;
                }
                F(coordinatorLayout, appBarLayout, iRound);
            }
            appBarLayout.g = 0;
            this.n = null;
            z(rp0.n(w(), -appBarLayout.getTotalScrollRange(), 0));
            M(coordinatorLayout, appBarLayout, w(), 0, true);
            appBarLayout.c(w());
            L(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean m(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((androidx.coordinatorlayout.widget.c) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.w(i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), appBarLayout);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ void o(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            I(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void p(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
            CoordinatorLayout coordinatorLayout2;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (i3 < 0) {
                coordinatorLayout2 = coordinatorLayout;
                iArr[1] = E(coordinatorLayout2, appBarLayout, x() - i3, -appBarLayout.getDownNestedScrollRange(), 0);
            } else {
                coordinatorLayout2 = coordinatorLayout;
            }
            if (i3 == 0) {
                L(coordinatorLayout2, appBarLayout);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void r(View view, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                this.n = (SavedState) parcelable;
            } else {
                this.n = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final Parcelable s(View view) {
            android.view.AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState savedStateJ = J(absSavedState, (AppBarLayout) view);
            return savedStateJ == null ? absSavedState : savedStateJ;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = (i & 2) != 0 && (appBarLayout.m || (appBarLayout.getTotalScrollRange() != 0 && coordinatorLayout.getHeight() - view2.getHeight() <= appBarLayout.getHeight()));
            if (z && (valueAnimator = this.m) != null) {
                valueAnimator.cancel();
            }
            this.o = null;
            this.l = i2;
            return z;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void u(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.l == 0 || i == 1) {
                K(coordinatorLayout, appBarLayout);
                if (appBarLayout.m) {
                    appBarLayout.e(appBarLayout.f(view2));
                }
            }
            this.o = new WeakReference(view2);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public final int x() {
            return w() + this.k;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AppBarLayout(Context context, AttributeSet attributeSet, int i) {
        int i2 = z;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.c = -1;
        this.d = -1;
        this.e = -1;
        final int i3 = 0;
        this.g = 0;
        this.s = new ArrayList();
        Context context2 = getContext();
        final int i4 = 1;
        setOrientation(1);
        int i5 = Build.VERSION.SDK_INT;
        if (getOutlineProvider() == ViewOutlineProvider.BACKGROUND) {
            setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
        Context context3 = getContext();
        TypedArray typedArrayD = un0.d(context3, attributeSet, jq0.g, i, i2, new int[0]);
        try {
            if (typedArrayD.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, typedArrayD.getResourceId(0, 0)));
            }
            typedArrayD.recycle();
            TypedArray typedArrayD2 = un0.d(context2, attributeSet, R$styleable.AppBarLayout, i, i2, new int[0]);
            Drawable drawable = typedArrayD2.getDrawable(R$styleable.AppBarLayout_android_background);
            WeakHashMap weakHashMap = xr0.a;
            setBackground(drawable);
            ColorStateList colorStateListS = xi.s(context2, typedArrayD2, R$styleable.AppBarLayout_liftOnScrollColor);
            this.p = colorStateListS;
            ColorStateList backgroundCSL = getBackgroundCSL();
            if (backgroundCSL != null) {
                final u50 u50Var = new u50();
                u50Var.m(backgroundCSL);
                if (colorStateListS != null) {
                    u50Var.setAlpha(this.l ? 255 : 0);
                    u50Var.m(colorStateListS);
                    this.r = new ValueAnimator.AnimatorUpdateListener(this) { // from class: x3
                        public final /* synthetic */ AppBarLayout b;

                        {
                            this.b = this;
                        }

                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            u50 u50Var2 = u50Var;
                            AppBarLayout appBarLayout = this.b;
                            switch (i4) {
                                case 0:
                                    int i6 = AppBarLayout.z;
                                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    u50Var2.l(fFloatValue);
                                    Drawable drawable2 = appBarLayout.w;
                                    if (drawable2 instanceof u50) {
                                        ((u50) drawable2).l(fFloatValue);
                                    }
                                    Iterator it = appBarLayout.s.iterator();
                                    if (it.hasNext()) {
                                        throw hk.e(it);
                                    }
                                    return;
                                default:
                                    int i7 = AppBarLayout.z;
                                    int iFloatValue = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    u50Var2.setAlpha(iFloatValue);
                                    Iterator it2 = appBarLayout.s.iterator();
                                    while (it2.hasNext()) {
                                        if (it2.next() != null) {
                                            throw new ClassCastException();
                                        }
                                        ColorStateList colorStateList = u50Var2.b.c;
                                        if (colorStateList != null) {
                                            colorStateList.withAlpha(iFloatValue).getDefaultColor();
                                            throw null;
                                        }
                                    }
                                    return;
                            }
                        }
                    };
                } else {
                    u50Var.k(context2);
                    this.r = new ValueAnimator.AnimatorUpdateListener(this) { // from class: x3
                        public final /* synthetic */ AppBarLayout b;

                        {
                            this.b = this;
                        }

                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            u50 u50Var2 = u50Var;
                            AppBarLayout appBarLayout = this.b;
                            switch (i3) {
                                case 0:
                                    int i6 = AppBarLayout.z;
                                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    u50Var2.l(fFloatValue);
                                    Drawable drawable2 = appBarLayout.w;
                                    if (drawable2 instanceof u50) {
                                        ((u50) drawable2).l(fFloatValue);
                                    }
                                    Iterator it = appBarLayout.s.iterator();
                                    if (it.hasNext()) {
                                        throw hk.e(it);
                                    }
                                    return;
                                default:
                                    int i7 = AppBarLayout.z;
                                    int iFloatValue = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                                    u50Var2.setAlpha(iFloatValue);
                                    Iterator it2 = appBarLayout.s.iterator();
                                    while (it2.hasNext()) {
                                        if (it2.next() != null) {
                                            throw new ClassCastException();
                                        }
                                        ColorStateList colorStateList = u50Var2.b.c;
                                        if (colorStateList != null) {
                                            colorStateList.withAlpha(iFloatValue).getDefaultColor();
                                            throw null;
                                        }
                                    }
                                    return;
                            }
                        }
                    };
                }
                setBackground(u50Var);
            }
            this.t = k9.E(context2, R$attr.motionDurationMedium2, getResources().getInteger(R$integer.app_bar_elevation_anim_duration));
            this.u = k9.F(context2, R$attr.motionEasingStandardInterpolator, t3.a);
            if (typedArrayD2.hasValue(R$styleable.AppBarLayout_expanded)) {
                d(typedArrayD2.getBoolean(R$styleable.AppBarLayout_expanded, false), false, false);
            }
            if (typedArrayD2.hasValue(R$styleable.AppBarLayout_elevation)) {
                jq0.c0(this, typedArrayD2.getDimensionPixelSize(R$styleable.AppBarLayout_elevation, 0));
            }
            if (i5 >= 26) {
                if (typedArrayD2.hasValue(R$styleable.AppBarLayout_android_keyboardNavigationCluster)) {
                    setKeyboardNavigationCluster(typedArrayD2.getBoolean(R$styleable.AppBarLayout_android_keyboardNavigationCluster, false));
                }
                if (typedArrayD2.hasValue(R$styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
                    setTouchscreenBlocksFocus(typedArrayD2.getBoolean(R$styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
                }
            }
            this.x = getResources().getDimension(R$dimen.design_appbar_elevation);
            this.m = typedArrayD2.getBoolean(R$styleable.AppBarLayout_liftOnScroll, false);
            this.n = typedArrayD2.getResourceId(R$styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
            setStatusBarForeground(typedArrayD2.getDrawable(R$styleable.AppBarLayout_statusBarForeground));
            typedArrayD2.recycle();
            lr0.u(this, new o70(7, this));
        } catch (Throwable th) {
            typedArrayD.recycle();
            throw th;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void removeOnOffsetChangedListener(b4 b4Var) {
        removeOnOffsetChangedListener((y3) b4Var);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LinearLayout.LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.a = 1;
        return layoutParams;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final LinearLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addOnOffsetChangedListener(b4 b4Var) {
        addOnOffsetChangedListener((y3) b4Var);
    }
}
