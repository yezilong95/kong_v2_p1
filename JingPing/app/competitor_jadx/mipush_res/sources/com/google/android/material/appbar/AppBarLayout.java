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
import android.util.TypedValue;
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
import defpackage.c5;
import defpackage.d5;
import defpackage.e5;
import defpackage.f5;
import defpackage.f51;
import defpackage.i01;
import defpackage.i6;
import defpackage.j31;
import defpackage.jp;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.mh0;
import defpackage.pd0;
import defpackage.qi;
import defpackage.qm;
import defpackage.s81;
import defpackage.s90;
import defpackage.sf;
import defpackage.t41;
import defpackage.t51;
import defpackage.y4;
import defpackage.z8;
import defpackage.zn;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements androidx.coordinatorlayout.widget.a {
    public static final int g0 = R$style.Widget_Design_AppBarLayout;
    public int E;
    public int F;
    public int G;
    public int H;
    public boolean I;
    public int J;
    public s81 K;
    public ArrayList L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public int Q;
    public WeakReference R;
    public final boolean S;
    public ValueAnimator T;
    public final ValueAnimator.AnimatorUpdateListener U;
    public final ArrayList V;
    public final long W;
    public final TimeInterpolator a0;
    public int[] b0;
    public Drawable c0;
    public Integer d0;
    public final float e0;
    public Behavior f0;

    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public int a;
        public final i6 b;
        public final Interpolator c;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.a = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.a = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            this.b = typedArrayObtainStyledAttributes.getInt(R$styleable.AppBarLayout_Layout_layout_scrollEffect, 0) != 1 ? null : new i6(3);
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
            int iM;
            CoordinatorLayout.Behavior behavior = ((androidx.coordinatorlayout.widget.c) view2.getLayoutParams()).a;
            if (behavior instanceof BaseBehavior) {
                int bottom = (view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).N + this.I;
                if (this.J == 0) {
                    iM = 0;
                } else {
                    float fB = B(view2);
                    int i = this.J;
                    iM = s90.m((int) (fB * i), 0, i);
                }
                int i2 = bottom - iM;
                WeakHashMap weakHashMap = f51.a;
                view.offsetTopAndBottom(i2);
            }
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.P) {
                    appBarLayout.e(appBarLayout.f(view));
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void i(CoordinatorLayout coordinatorLayout, View view) {
            if (view instanceof AppBarLayout) {
                f51.o(coordinatorLayout, null);
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
                Rect rect3 = this.G;
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
            this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0);
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

    public void addLiftOnScrollListener(e5 e5Var) {
        this.V.add(e5Var);
    }

    public void addOnOffsetChangedListener(d5 d5Var) {
        if (this.L == null) {
            this.L = new ArrayList();
        }
        if (d5Var == null || this.L.contains(d5Var)) {
            return;
        }
        this.L.add(d5Var);
    }

    public final void b() {
        Behavior behavior = this.f0;
        BaseBehavior.SavedState savedStateK = (behavior == null || this.F == -1 || this.J != 0) ? null : behavior.K(AbsSavedState.F, this);
        this.F = -1;
        this.G = -1;
        this.H = -1;
        if (savedStateK != null) {
            Behavior behavior2 = this.f0;
            if (behavior2.Q != null) {
                return;
            }
            behavior2.Q = savedStateK;
        }
    }

    public final void c(int i) {
        this.E = i;
        if (!willNotDraw()) {
            WeakHashMap weakHashMap = f51.a;
            postInvalidateOnAnimation();
        }
        ArrayList arrayList = this.L;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                d5 d5Var = (d5) this.L.get(i2);
                if (d5Var != null) {
                    CollapsingToolbarLayout collapsingToolbarLayout = ((d) d5Var).a;
                    collapsingToolbarLayout.f0 = i;
                    s81 s81Var = collapsingToolbarLayout.h0;
                    int iD = s81Var != null ? s81Var.d() : 0;
                    int childCount = collapsingToolbarLayout.getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = collapsingToolbarLayout.getChildAt(i3);
                        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams();
                        t51 t51VarB = CollapsingToolbarLayout.b(childAt);
                        int i4 = layoutParams.a;
                        if (i4 == 1) {
                            t51VarB.b(s90.m(-i, 0, ((collapsingToolbarLayout.getHeight() - CollapsingToolbarLayout.b(childAt).b) - childAt.getHeight()) - ((FrameLayout.LayoutParams) ((CollapsingToolbarLayout.LayoutParams) childAt.getLayoutParams())).bottomMargin));
                        } else if (i4 == 2) {
                            t51VarB.b(Math.round((-i) * layoutParams.b));
                        }
                    }
                    collapsingToolbarLayout.d();
                    if (collapsingToolbarLayout.T != null && iD > 0) {
                        WeakHashMap weakHashMap2 = f51.a;
                        collapsingToolbarLayout.postInvalidateOnAnimation();
                    }
                    int height = collapsingToolbarLayout.getHeight();
                    WeakHashMap weakHashMap3 = f51.a;
                    int minimumHeight = (height - collapsingToolbarLayout.getMinimumHeight()) - iD;
                    float scrimVisibleHeightTrigger = height - collapsingToolbarLayout.getScrimVisibleHeightTrigger();
                    float f = minimumHeight;
                    float fMin = Math.min(1.0f, scrimVisibleHeightTrigger / f);
                    sf sfVar = collapsingToolbarLayout.O;
                    sfVar.d = fMin;
                    sfVar.e = zn.d(1.0f, fMin, 0.5f, fMin);
                    sfVar.f = collapsingToolbarLayout.f0 + minimumHeight;
                    sfVar.p(Math.abs(i) / f);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d(boolean z, boolean z2, boolean z3) {
        this.J = (z ? 1 : 2) | (z2 ? 4 : 0) | (z3 ? 8 : 0);
        requestLayout();
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.c0 == null || getTopInset() <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(0.0f, -this.E);
        this.c0.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.c0;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    public final boolean e(boolean z) {
        if (this.M || this.O == z) {
            return false;
        }
        this.O = z;
        refreshDrawableState();
        if (!(getBackground() instanceof ld0)) {
            return true;
        }
        if (this.S) {
            g(z ? 0.0f : 1.0f, z ? 1.0f : 0.0f);
            return true;
        }
        if (!this.P) {
            return true;
        }
        float f = this.e0;
        g(z ? 0.0f : f, z ? f : 0.0f);
        return true;
    }

    public final boolean f(View view) {
        int i;
        if (this.R == null && (i = this.Q) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.Q);
            }
            if (viewFindViewById != null) {
                this.R = new WeakReference(viewFindViewById);
            }
        }
        WeakReference weakReference = this.R;
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
        ValueAnimator valueAnimator = this.T;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, f2);
        this.T = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.W);
        this.T.setInterpolator(this.a0);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.U;
        if (animatorUpdateListener != null) {
            this.T.addUpdateListener(animatorUpdateListener);
        }
        this.T.start();
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
        this.f0 = behavior;
        return behavior;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getDownNestedPreScrollRange() {
        /*
            r11 = this;
            r0 = 5
            r1 = 8
            int r2 = r11.G
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
            java.util.WeakHashMap r7 = defpackage.f51.a
            int r7 = r6.getMinimumHeight()
        L3d:
            int r7 = r7 + r10
            goto L4e
        L3f:
            r7 = r9 & 2
            if (r7 == 0) goto L4c
            java.util.WeakHashMap r7 = defpackage.f51.a
            int r7 = r6.getMinimumHeight()
            int r7 = r8 - r7
            goto L3d
        L4c:
            int r7 = r10 + r8
        L4e:
            if (r2 != 0) goto L61
            java.util.WeakHashMap r9 = defpackage.f51.a
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
            r11.G = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public int getDownNestedScrollRange() {
        int i = this.H;
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
                    WeakHashMap weakHashMap = f51.a;
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int iMax = Math.max(0, minimumHeight);
        this.H = iMax;
        return iMax;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.Q;
    }

    public ld0 getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof ld0) {
            return (ld0) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        WeakHashMap weakHashMap = f51.a;
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
        return this.J;
    }

    public Drawable getStatusBarForeground() {
        return this.c0;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        s81 s81Var = this.K;
        if (s81Var != null) {
            return s81Var.d();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.F;
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
                    WeakHashMap weakHashMap = f51.a;
                    if (childAt.getFitsSystemWindows()) {
                        topInset -= getTopInset();
                    }
                }
                minimumHeight = topInset;
                if ((i3 & 2) != 0) {
                    WeakHashMap weakHashMap2 = f51.a;
                    minimumHeight -= childAt.getMinimumHeight();
                    break;
                }
            }
            i2++;
        }
        int iMax = Math.max(0, minimumHeight);
        this.F = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.p0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        if (this.b0 == null) {
            this.b0 = new int[4];
        }
        int[] iArr = this.b0;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.N;
        int i2 = R$attr.state_liftable;
        if (!z) {
            i2 = -i2;
        }
        iArr[0] = i2;
        iArr[1] = (z && this.O) ? R$attr.state_lifted : -R$attr.state_lifted;
        int i3 = R$attr.state_collapsible;
        if (!z) {
            i3 = -i3;
        }
        iArr[2] = i3;
        iArr[3] = (z && this.O) ? R$attr.state_collapsed : -R$attr.state_collapsed;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference weakReference = this.R;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.R = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = true;
        super.onLayout(z, i, i2, i3, i4);
        WeakHashMap weakHashMap = f51.a;
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
        this.I = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            }
            if (((LayoutParams) getChildAt(i5).getLayoutParams()).c != null) {
                this.I = true;
                break;
            }
            i5++;
        }
        Drawable drawable = this.c0;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.M) {
            return;
        }
        if (!this.P) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
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
        if (this.N != z2) {
            this.N = z2;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            WeakHashMap weakHashMap = f51.a;
            if (getFitsSystemWindows() && getChildCount() > 0) {
                View childAt = getChildAt(0);
                if (childAt.getVisibility() != 8 && !childAt.getFitsSystemWindows()) {
                    int measuredHeight = getMeasuredHeight();
                    if (mode == Integer.MIN_VALUE) {
                        measuredHeight = s90.m(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                    } else if (mode == 0) {
                        measuredHeight += getTopInset();
                    }
                    setMeasuredDimension(getMeasuredWidth(), measuredHeight);
                }
            }
        }
        b();
    }

    public void removeOnOffsetChangedListener(d5 d5Var) {
        ArrayList arrayList = this.L;
        if (arrayList == null || d5Var == null) {
            return;
        }
        arrayList.remove(d5Var);
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ma0.n0(this, f);
    }

    public void setExpanded(boolean z) {
        WeakHashMap weakHashMap = f51.a;
        d(z, isLaidOut(), true);
    }

    public void setLiftOnScroll(boolean z) {
        this.P = z;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.Q = -1;
        if (view != null) {
            this.R = new WeakReference(view);
            return;
        }
        WeakReference weakReference = this.R;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.R = null;
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.Q = i;
        WeakReference weakReference = this.R;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.R = null;
    }

    public void setLiftableOverrideEnabled(boolean z) {
        this.M = z;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.c0;
        if (drawable2 != drawable) {
            Integer numValueOf = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.c0 = drawableMutate;
            if (drawableMutate instanceof ld0) {
                numValueOf = Integer.valueOf(((ld0) drawableMutate).Y);
            } else {
                ColorStateList colorStateListS = qm.s(drawableMutate);
                if (colorStateListS != null) {
                    numValueOf = Integer.valueOf(colorStateListS.getDefaultColor());
                }
            }
            this.d0 = numValueOf;
            Drawable drawable3 = this.c0;
            boolean z = false;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.c0.setState(getDrawableState());
                }
                Drawable drawable4 = this.c0;
                WeakHashMap weakHashMap = f51.a;
                jp.b(drawable4, getLayoutDirection());
                this.c0.setVisible(getVisibility() == 0, false);
                this.c0.setCallback(this);
            }
            if (this.c0 != null && getTopInset() > 0) {
                z = true;
            }
            setWillNotDraw(!z);
            WeakHashMap weakHashMap2 = f51.a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(qm.z(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f) {
        j31.Z(this, f);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.c0;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.c0;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.appBarLayoutStyle);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return a(layoutParams);
    }

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        public int N;
        public int O;
        public ValueAnimator P;
        public SavedState Q;
        public WeakReference R;

        public static class SavedState extends AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new c();
            public boolean G;
            public boolean H;
            public int I;
            public float J;
            public boolean K;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.G = parcel.readByte() != 0;
                this.H = parcel.readByte() != 0;
                this.I = parcel.readInt();
                this.J = parcel.readFloat();
                this.K = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public final void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeByte(this.G ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.H ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.I);
                parcel.writeFloat(this.J);
                parcel.writeByte(this.K ? (byte) 1 : (byte) 0);
            }
        }

        public BaseBehavior() {
            this.J = -1;
            this.L = -1;
        }

        public static View G(BaseBehavior baseBehavior, CoordinatorLayout coordinatorLayout) {
            baseBehavior.getClass();
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if (((androidx.coordinatorlayout.widget.c) childAt.getLayoutParams()).a instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        public static View I(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof mh0) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x005d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static void M(androidx.coordinatorlayout.widget.CoordinatorLayout r8, com.google.android.material.appbar.AppBarLayout r9, int r10, int r11, boolean r12) {
            /*
                Method dump skipped, instruction units count: 209
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.M(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public final boolean A(View view) {
            WeakReference weakReference = this.R;
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
            L(coordinatorLayout, appBarLayout);
            if (appBarLayout.P) {
                appBarLayout.e(appBarLayout.f(I(coordinatorLayout)));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0154  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0156  */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int E(androidx.coordinatorlayout.widget.CoordinatorLayout r18, android.view.View r19, int r20, int r21, int r22) {
            /*
                Method dump skipped, instruction units count: 367
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.E(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public final void H(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
            int iAbs = Math.abs(x() - i);
            float fAbs = Math.abs(0.0f);
            int iRound = fAbs > 0.0f ? Math.round((iAbs / fAbs) * 1000.0f) * 3 : (int) (((iAbs / appBarLayout.getHeight()) + 1.0f) * 150.0f);
            int iX = x();
            if (iX == i) {
                ValueAnimator valueAnimator = this.P;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.P.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.P;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.P = valueAnimator3;
                valueAnimator3.setInterpolator(y4.e);
                this.P.addUpdateListener(new a(coordinatorLayout, this, appBarLayout));
            } else {
                valueAnimator2.cancel();
            }
            this.P.setDuration(Math.min(iRound, 600));
            this.P.setIntValues(iX, i);
            this.P.start();
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void J(androidx.coordinatorlayout.widget.CoordinatorLayout r9, com.google.android.material.appbar.AppBarLayout r10, android.view.View r11, int r12, int[] r13) {
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
                boolean r9 = r4.P
                if (r9 == 0) goto L37
                boolean r9 = r4.f(r11)
                r4.e(r9)
            L37:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.J(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int[]):void");
        }

        public final SavedState K(Parcelable parcelable, AppBarLayout appBarLayout) {
            int iW = w();
            int childCount = appBarLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + iW;
                if (childAt.getTop() + iW <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = AbsSavedState.F;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z = iW == 0;
                    savedState.H = z;
                    savedState.G = !z && (-iW) >= appBarLayout.getTotalScrollRange();
                    savedState.I = i;
                    WeakHashMap weakHashMap = f51.a;
                    savedState.K = bottom == appBarLayout.getTopInset() + childAt.getMinimumHeight();
                    savedState.J = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public final void L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
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
                        WeakHashMap weakHashMap = f51.a;
                        if (appBarLayout.getFitsSystemWindows() && childAt2.getFitsSystemWindows()) {
                            topInset -= appBarLayout.getTopInset();
                        }
                    }
                    if ((i3 & 2) == 2) {
                        WeakHashMap weakHashMap2 = f51.a;
                        minimumHeight += childAt2.getMinimumHeight();
                    } else if ((i3 & 5) == 5) {
                        WeakHashMap weakHashMap3 = f51.a;
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
                    H(coordinatorLayout, appBarLayout, s90.m(topInset + paddingTop, -appBarLayout.getTotalScrollRange(), 0));
                }
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            int iRound;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.l(coordinatorLayout, appBarLayout, i);
            int pendingAction = appBarLayout.getPendingAction();
            SavedState savedState = this.Q;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        int i2 = -appBarLayout.getUpNestedPreScrollRange();
                        if (z) {
                            H(coordinatorLayout, appBarLayout, i2);
                        } else {
                            F(coordinatorLayout, appBarLayout, i2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z) {
                            H(coordinatorLayout, appBarLayout, 0);
                        } else {
                            F(coordinatorLayout, appBarLayout, 0);
                        }
                    }
                }
            } else if (savedState.G) {
                F(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange());
            } else if (savedState.H) {
                F(coordinatorLayout, appBarLayout, 0);
            } else {
                View childAt = appBarLayout.getChildAt(savedState.I);
                int i3 = -childAt.getBottom();
                if (this.Q.K) {
                    WeakHashMap weakHashMap = f51.a;
                    iRound = appBarLayout.getTopInset() + childAt.getMinimumHeight() + i3;
                } else {
                    iRound = Math.round(childAt.getHeight() * this.Q.J) + i3;
                }
                F(coordinatorLayout, appBarLayout, iRound);
            }
            appBarLayout.J = 0;
            this.Q = null;
            z(s90.m(w(), -appBarLayout.getTotalScrollRange(), 0));
            M(coordinatorLayout, appBarLayout, w(), 0, true);
            appBarLayout.c(w());
            if (f51.d(coordinatorLayout) != null) {
                return true;
            }
            f51.o(coordinatorLayout, new b(coordinatorLayout, this, appBarLayout));
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
            J(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
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
            if (i3 == 0 && f51.d(coordinatorLayout2) == null) {
                f51.o(coordinatorLayout2, new b(coordinatorLayout2, this, appBarLayout));
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void r(View view, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                this.Q = (SavedState) parcelable;
            } else {
                this.Q = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final Parcelable s(View view) {
            android.view.AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            SavedState savedStateK = K(absSavedState, (AppBarLayout) view);
            return savedStateK == null ? absSavedState : savedStateK;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            ValueAnimator valueAnimator;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            boolean z = (i & 2) != 0 && (appBarLayout.P || (appBarLayout.getTotalScrollRange() != 0 && coordinatorLayout.getHeight() - view2.getHeight() <= appBarLayout.getHeight()));
            if (z && (valueAnimator = this.P) != null) {
                valueAnimator.cancel();
            }
            this.R = null;
            this.O = i2;
            return z;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void u(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.O == 0 || i == 1) {
                L(coordinatorLayout, appBarLayout);
                if (appBarLayout.P) {
                    appBarLayout.e(appBarLayout.f(view2));
                }
            }
            this.R = new WeakReference(view2);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public final int x() {
            return w() + this.N;
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Illegal instructions before constructor call */
    public AppBarLayout(Context context, AttributeSet attributeSet, int i) {
        Integer numValueOf;
        int iA;
        int i2 = g0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.F = -1;
        this.G = -1;
        this.H = -1;
        this.J = 0;
        this.V = new ArrayList();
        Context context2 = getContext();
        setOrientation(1);
        int i3 = Build.VERSION.SDK_INT;
        if (getOutlineProvider() == ViewOutlineProvider.BACKGROUND) {
            setOutlineProvider(ViewOutlineProvider.BOUNDS);
        }
        Context context3 = getContext();
        TypedArray typedArrayD = i01.d(context3, attributeSet, j31.K, i, i2, new int[0]);
        try {
            if (typedArrayD.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, typedArrayD.getResourceId(0, 0)));
            }
            typedArrayD.recycle();
            TypedArray typedArrayD2 = i01.d(context2, attributeSet, R$styleable.AppBarLayout, i, i2, new int[0]);
            Drawable drawable = typedArrayD2.getDrawable(R$styleable.AppBarLayout_android_background);
            WeakHashMap weakHashMap = f51.a;
            setBackground(drawable);
            final ColorStateList colorStateListP = la0.p(context2, typedArrayD2, R$styleable.AppBarLayout_liftOnScrollColor);
            this.S = colorStateListP != null;
            final ColorStateList colorStateListS = qm.s(getBackground());
            if (colorStateListS != null) {
                final ld0 ld0Var = new ld0();
                ld0Var.m(colorStateListS);
                if (colorStateListP != null) {
                    Context context4 = getContext();
                    TypedValue typedValueS = s90.S(context4, R$attr.colorSurface);
                    if (typedValueS != null) {
                        int i4 = typedValueS.resourceId;
                        if (i4 != 0) {
                            iA = qi.a(context4, i4);
                        } else {
                            iA = typedValueS.data;
                        }
                        numValueOf = Integer.valueOf(iA);
                    } else {
                        numValueOf = null;
                    }
                    final Integer num = numValueOf;
                    this.U = new ValueAnimator.AnimatorUpdateListener() { // from class: b5
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            Integer num2;
                            int i5 = AppBarLayout.g0;
                            AppBarLayout appBarLayout = this.a;
                            int iK0 = fa0.k0(colorStateListS.getDefaultColor(), ((Float) valueAnimator.getAnimatedValue()).floatValue(), colorStateListP.getDefaultColor());
                            ColorStateList colorStateListValueOf = ColorStateList.valueOf(iK0);
                            ld0 ld0Var2 = ld0Var;
                            ld0Var2.m(colorStateListValueOf);
                            if (appBarLayout.c0 != null && (num2 = appBarLayout.d0) != null && num2.equals(num)) {
                                ip.g(appBarLayout.c0, iK0);
                            }
                            ArrayList arrayList = appBarLayout.V;
                            if (arrayList.isEmpty()) {
                                return;
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                if (it.next() != null) {
                                    throw new ClassCastException();
                                }
                                if (ld0Var2.E.c != null) {
                                    throw null;
                                }
                            }
                        }
                    };
                    setBackground(ld0Var);
                } else {
                    ld0Var.k(context2);
                    this.U = new c5(this, ld0Var, 0);
                    setBackground(ld0Var);
                }
            }
            this.W = s90.U(context2, R$attr.motionDurationMedium2, getResources().getInteger(R$integer.app_bar_elevation_anim_duration));
            this.a0 = s90.V(context2, R$attr.motionEasingStandardInterpolator, y4.a);
            if (typedArrayD2.hasValue(R$styleable.AppBarLayout_expanded)) {
                d(typedArrayD2.getBoolean(R$styleable.AppBarLayout_expanded, false), false, false);
            }
            if (typedArrayD2.hasValue(R$styleable.AppBarLayout_elevation)) {
                j31.Z(this, typedArrayD2.getDimensionPixelSize(R$styleable.AppBarLayout_elevation, 0));
            }
            if (i3 >= 26) {
                if (typedArrayD2.hasValue(R$styleable.AppBarLayout_android_keyboardNavigationCluster)) {
                    setKeyboardNavigationCluster(typedArrayD2.getBoolean(R$styleable.AppBarLayout_android_keyboardNavigationCluster, false));
                }
                if (typedArrayD2.hasValue(R$styleable.AppBarLayout_android_touchscreenBlocksFocus)) {
                    setTouchscreenBlocksFocus(typedArrayD2.getBoolean(R$styleable.AppBarLayout_android_touchscreenBlocksFocus, false));
                }
            }
            this.e0 = getResources().getDimension(R$dimen.design_appbar_elevation);
            this.P = typedArrayD2.getBoolean(R$styleable.AppBarLayout_liftOnScroll, false);
            this.Q = typedArrayD2.getResourceId(R$styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
            setStatusBarForeground(typedArrayD2.getDrawable(R$styleable.AppBarLayout_statusBarForeground));
            typedArrayD2.recycle();
            t41.u(this, new z8(6, this));
        } catch (Throwable th) {
            typedArrayD.recycle();
            throw th;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void removeOnOffsetChangedListener(f5 f5Var) {
        removeOnOffsetChangedListener((d5) f5Var);
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

    public void addOnOffsetChangedListener(f5 f5Var) {
        addOnOffsetChangedListener((d5) f5Var);
    }
}
