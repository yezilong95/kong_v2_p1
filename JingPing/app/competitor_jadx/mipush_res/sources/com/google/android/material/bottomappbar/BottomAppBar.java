package com.google.android.material.bottomappbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.c;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$animator;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.f30;
import defpackage.f51;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ip;
import defpackage.is;
import defpackage.l61;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.o0;
import defpackage.p50;
import defpackage.pd0;
import defpackage.ra;
import defpackage.s90;
import defpackage.sa;
import defpackage.t41;
import defpackage.ua;
import defpackage.up0;
import defpackage.va;
import defpackage.vv0;
import defpackage.wa;
import defpackage.xa;
import defpackage.y4;
import defpackage.z8;
import defpackage.zu0;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements androidx.coordinatorlayout.widget.a {
    public static final int V0 = R$style.Widget_MaterialComponents_BottomAppBar;
    public static final int W0 = R$attr.motionDurationLong2;
    public static final int X0 = R$attr.motionEasingEmphasizedInterpolator;
    public AnimatorSet A0;
    public AnimatorSet B0;
    public int C0;
    public int D0;
    public int E0;
    public final int F0;
    public int G0;
    public int H0;
    public final boolean I0;
    public boolean J0;
    public final boolean K0;
    public final boolean L0;
    public final boolean M0;
    public boolean N0;
    public boolean O0;
    public Behavior P0;
    public int Q0;
    public int R0;
    public int S0;
    public final sa T0;
    public final z8 U0;
    public Integer y0;
    public final ld0 z0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new b();
        public int G;
        public boolean H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readInt();
            this.H = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G);
            parcel.writeInt(this.H ? 1 : 0);
        }
    }

    public BottomAppBar(Context context) {
        this(context, null);
    }

    public static void J(BottomAppBar bottomAppBar, View view) {
        c cVar = (c) view.getLayoutParams();
        cVar.d = 17;
        int i = bottomAppBar.E0;
        if (i == 1) {
            cVar.d = 49;
        }
        if (i == 0) {
            cVar.d |= 80;
        }
    }

    private ActionMenuView getActionMenuView() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof ActionMenuView) {
                return (ActionMenuView) childAt;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBottomInset() {
        return this.Q0;
    }

    private int getFabAlignmentAnimationDuration() {
        return s90.U(getContext(), W0, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return C(this.C0);
    }

    private float getFabTranslationY() {
        if (this.E0 == 1) {
            return -getTopEdgeTreatment().S;
        }
        return A() != null ? (-((getMeasuredHeight() + getBottomInset()) - r0.getMeasuredHeight())) / 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.S0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.R0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public xa getTopEdgeTreatment() {
        return (xa) this.z0.E.a.i;
    }

    public final View A() {
        if (getParent() instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getParent();
            List list = (List) ((vv0) coordinatorLayout.F.b).getOrDefault(this, null);
            ArrayList<View> arrayList = coordinatorLayout.H;
            arrayList.clear();
            if (list != null) {
                arrayList.addAll(list);
            }
            for (View view : arrayList) {
                if ((view instanceof FloatingActionButton) || (view instanceof ExtendedFloatingActionButton)) {
                    return view;
                }
            }
        }
        return null;
    }

    public final int B(ActionMenuView actionMenuView, int i, boolean z) {
        int dimensionPixelOffset = 0;
        if (this.H0 != 1 && (i != 1 || !z)) {
            return 0;
        }
        boolean zD0 = fa0.d0(this);
        int measuredWidth = zD0 ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).a & 8388615) == 8388611) {
                measuredWidth = zD0 ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        int right = zD0 ? actionMenuView.getRight() : actionMenuView.getLeft();
        int i3 = zD0 ? this.R0 : -this.S0;
        if (getNavigationIcon() == null) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.m3_bottomappbar_horizontal_padding);
            if (!zD0) {
                dimensionPixelOffset = -dimensionPixelOffset;
            }
        }
        return measuredWidth - ((right + i3) + dimensionPixelOffset);
    }

    public final float C(int i) {
        boolean zD0 = fa0.d0(this);
        if (i != 1) {
            return 0.0f;
        }
        View viewA = A();
        int i2 = zD0 ? this.S0 : this.R0;
        return ((getMeasuredWidth() / 2) - ((this.G0 == -1 || viewA == null) ? this.F0 + i2 : ((viewA.getMeasuredWidth() / 2) + this.G0) + i2)) * (zD0 ? -1 : 1);
    }

    public final boolean D() {
        View viewA = A();
        FloatingActionButton floatingActionButton = viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null;
        return floatingActionButton != null && floatingActionButton.i();
    }

    public final void E(int i, boolean z) {
        int i2 = 2;
        WeakHashMap weakHashMap = f51.a;
        if (!isLaidOut()) {
            this.N0 = false;
            return;
        }
        AnimatorSet animatorSet = this.B0;
        if (animatorSet != null) {
            animatorSet.cancel();
        }
        ArrayList arrayList = new ArrayList();
        if (!D()) {
            i = 0;
            z = false;
        }
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView != null) {
            float fabAlignmentAnimationDuration = getFabAlignmentAnimationDuration();
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(actionMenuView, "alpha", 1.0f);
            objectAnimatorOfFloat.setDuration((long) (0.8f * fabAlignmentAnimationDuration));
            if (Math.abs(actionMenuView.getTranslationX() - B(actionMenuView, i, z)) > 1.0f) {
                ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(actionMenuView, "alpha", 0.0f);
                objectAnimatorOfFloat2.setDuration((long) (fabAlignmentAnimationDuration * 0.2f));
                objectAnimatorOfFloat2.addListener(new va(this, actionMenuView, i, z));
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat);
                arrayList.add(animatorSet2);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                arrayList.add(objectAnimatorOfFloat);
            }
        }
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(arrayList);
        this.B0 = animatorSet3;
        animatorSet3.addListener(new sa(this, i2));
        this.B0.start();
    }

    public final void F() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.B0 != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (D()) {
            I(actionMenuView, this.C0, this.O0, false);
        } else {
            I(actionMenuView, 0, false, false);
        }
    }

    public final void G() {
        getTopEdgeTreatment().T = getFabTranslationX();
        this.z0.n((this.O0 && D() && this.E0 == 1) ? 1.0f : 0.0f);
        View viewA = A();
        if (viewA != null) {
            viewA.setTranslationY(getFabTranslationY());
            viewA.setTranslationX(getFabTranslationX());
        }
    }

    public final void H(int i) {
        float f = i;
        if (f != getTopEdgeTreatment().R) {
            getTopEdgeTreatment().R = f;
            this.z0.invalidateSelf();
        }
    }

    public final void I(ActionMenuView actionMenuView, int i, boolean z, boolean z2) {
        wa waVar = new wa(this, actionMenuView, i, z);
        if (z2) {
            actionMenuView.post(waVar);
        } else {
            waVar.run();
        }
    }

    public void addOnScrollStateChangedListener(f30 f30Var) {
        getBehavior().addOnScrollStateChangedListener(f30Var);
    }

    public ColorStateList getBackgroundTint() {
        return this.z0.E.e;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().S;
    }

    public int getFabAlignmentMode() {
        return this.C0;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.G0;
    }

    public int getFabAnchorMode() {
        return this.E0;
    }

    public int getFabAnimationMode() {
        return this.D0;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().Q;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().P;
    }

    public boolean getHideOnScroll() {
        return this.J0;
    }

    public int getMenuAlignmentMode() {
        return this.H0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.o0(this, this.z0);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            AnimatorSet animatorSet = this.B0;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = this.A0;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            G();
            View viewA = A();
            if (viewA != null) {
                WeakHashMap weakHashMap = f51.a;
                if (viewA.isLaidOut()) {
                    viewA.post(new ra(viewA, 0));
                }
            }
        }
        F();
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        this.C0 = savedState.G;
        this.O0 = savedState.H;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.G = this.C0;
        savedState.H = this.O0;
        return savedState;
    }

    public void removeOnScrollStateChangedListener(f30 f30Var) {
        getBehavior().removeOnScrollStateChangedListener(f30Var);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        ip.h(this.z0, colorStateList);
    }

    public void setCradleVerticalOffset(float f) {
        if (f != getCradleVerticalOffset()) {
            getTopEdgeTreatment().p(f);
            this.z0.invalidateSelf();
            G();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        ld0 ld0Var = this.z0;
        ld0Var.l(f);
        int iH = ld0Var.E.o - ld0Var.h();
        Behavior behavior = getBehavior();
        behavior.L = iH;
        if (behavior.K == 1) {
            setTranslationY(behavior.J + iH);
        }
    }

    public void setFabAlignmentMode(int i) {
        int i2 = 1;
        this.N0 = true;
        E(i, this.O0);
        if (this.C0 != i) {
            WeakHashMap weakHashMap = f51.a;
            if (isLaidOut()) {
                AnimatorSet animatorSet = this.A0;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                ArrayList arrayList = new ArrayList();
                if (this.D0 == 1) {
                    View viewA = A();
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null, "translationX", C(i));
                    objectAnimatorOfFloat.setDuration(getFabAlignmentAnimationDuration());
                    arrayList.add(objectAnimatorOfFloat);
                } else {
                    View viewA2 = A();
                    FloatingActionButton floatingActionButton = viewA2 instanceof FloatingActionButton ? (FloatingActionButton) viewA2 : null;
                    if (floatingActionButton != null && !floatingActionButton.h()) {
                        floatingActionButton.hide(new ua(this, i));
                    }
                }
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(arrayList);
                animatorSet2.setInterpolator(s90.V(getContext(), X0, y4.a));
                this.A0 = animatorSet2;
                animatorSet2.addListener(new sa(this, i2));
                this.A0.start();
            }
        }
        this.C0 = i;
    }

    public void setFabAlignmentModeEndMargin(int i) {
        if (this.G0 != i) {
            this.G0 = i;
            G();
        }
    }

    public void setFabAnchorMode(int i) {
        this.E0 = i;
        G();
        View viewA = A();
        if (viewA != null) {
            J(this, viewA);
            viewA.requestLayout();
            this.z0.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int i) {
        this.D0 = i;
    }

    public void setFabCornerSize(float f) {
        if (f != getTopEdgeTreatment().U) {
            getTopEdgeTreatment().U = f;
            this.z0.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f) {
        if (f != getFabCradleMargin()) {
            getTopEdgeTreatment().Q = f;
            this.z0.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        if (f != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().P = f;
            this.z0.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.J0 = z;
    }

    public void setMenuAlignmentMode(int i) {
        if (this.H0 != i) {
            this.H0 = i;
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                I(actionMenuView, this.C0, D(), false);
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.y0 != null) {
            drawable = drawable.mutate();
            ip.g(drawable, this.y0.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.y0 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence) {
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
    }

    public BottomAppBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.bottomAppBarStyle);
    }

    @Override // androidx.coordinatorlayout.widget.a
    public Behavior getBehavior() {
        if (this.P0 == null) {
            this.P0 = new Behavior();
        }
        return this.P0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        public final Rect Q;
        public WeakReference R;
        public int S;
        public final a T;

        public Behavior() {
            this.T = new a(this);
            this.Q = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.R = new WeakReference(bottomAppBar);
            int i2 = BottomAppBar.V0;
            View viewA = bottomAppBar.A();
            if (viewA != null) {
                WeakHashMap weakHashMap = f51.a;
                if (!viewA.isLaidOut()) {
                    BottomAppBar.J(bottomAppBar, viewA);
                    this.S = ((ViewGroup.MarginLayoutParams) ((c) viewA.getLayoutParams())).bottomMargin;
                    if (viewA instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) viewA;
                        if (bottomAppBar.E0 == 0 && bottomAppBar.I0) {
                            t41.s(floatingActionButton, 0.0f);
                            floatingActionButton.setCompatElevation(0.0f);
                        }
                        if (floatingActionButton.getShowMotionSpec() == null) {
                            floatingActionButton.setShowMotionSpecResource(R$animator.mtrl_fab_show_motion_spec);
                        }
                        if (floatingActionButton.getHideMotionSpec() == null) {
                            floatingActionButton.setHideMotionSpecResource(R$animator.mtrl_fab_hide_motion_spec);
                        }
                        floatingActionButton.c(bottomAppBar.T0);
                        floatingActionButton.d(new sa(bottomAppBar, 3));
                        floatingActionButton.e(bottomAppBar.U0);
                    }
                    viewA.addOnLayoutChangeListener(this.T);
                    bottomAppBar.G();
                }
            }
            coordinatorLayout.v(bottomAppBar, i);
            super.l(coordinatorLayout, bottomAppBar, i);
            return false;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean t(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            return bottomAppBar.getHideOnScroll() && super.t(coordinatorLayout, bottomAppBar, view2, view3, i, i2);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.T = new a(this);
            this.Q = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i) {
        int i2 = V0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        ld0 ld0Var = new ld0();
        this.z0 = ld0Var;
        int i3 = 0;
        this.N0 = false;
        this.O0 = true;
        this.T0 = new sa(this, i3);
        this.U0 = new z8(8, this);
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.BottomAppBar, i, i2, new int[0]);
        ColorStateList colorStateListP = la0.p(context2, typedArrayD, R$styleable.BottomAppBar_backgroundTint);
        if (typedArrayD.hasValue(R$styleable.BottomAppBar_navigationIconTint)) {
            setNavigationIconTint(typedArrayD.getColor(R$styleable.BottomAppBar_navigationIconTint, -1));
        }
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.C0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAlignmentMode, 0);
        this.D0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAnimationMode, 0);
        this.E0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAnchorMode, 1);
        this.I0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_removeEmbeddedFabElevation, true);
        this.H0 = typedArrayD.getInt(R$styleable.BottomAppBar_menuAlignmentMode, 0);
        this.J0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_hideOnScroll, false);
        this.K0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.L0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.M0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        this.G0 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabAlignmentModeEndMargin, -1);
        boolean z = typedArrayD.getBoolean(R$styleable.BottomAppBar_addElevationShadow, true);
        typedArrayD.recycle();
        this.F0 = getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fabOffsetEndMode);
        xa xaVar = new xa(i3);
        xaVar.U = -1.0f;
        xaVar.Q = dimensionPixelOffset;
        xaVar.P = dimensionPixelOffset2;
        xaVar.p(dimensionPixelOffset3);
        xaVar.T = 0.0f;
        up0 up0Var = new up0();
        up0 up0Var2 = new up0();
        up0 up0Var3 = new up0();
        up0 up0Var4 = new up0();
        o0 o0Var = new o0(0.0f);
        o0 o0Var2 = new o0(0.0f);
        o0 o0Var3 = new o0(0.0f);
        o0 o0Var4 = new o0(0.0f);
        int i4 = 0;
        is isVar = new is(i4);
        is isVar2 = new is(i4);
        is isVar3 = new is(i4);
        zu0 zu0Var = new zu0();
        zu0Var.a = up0Var;
        zu0Var.b = up0Var2;
        zu0Var.c = up0Var3;
        zu0Var.d = up0Var4;
        zu0Var.e = o0Var;
        zu0Var.f = o0Var2;
        zu0Var.g = o0Var3;
        zu0Var.h = o0Var4;
        zu0Var.i = xaVar;
        zu0Var.j = isVar;
        zu0Var.k = isVar2;
        zu0Var.l = isVar3;
        ld0Var.setShapeAppearanceModel(zu0Var);
        if (z) {
            ld0Var.q(2);
        } else {
            ld0Var.q(1);
            if (Build.VERSION.SDK_INT >= 28) {
                setOutlineAmbientShadowColor(0);
                setOutlineSpotShadowColor(0);
            }
        }
        Paint.Style style = Paint.Style.FILL;
        ld0Var.o();
        ld0Var.k(context2);
        setElevation(dimensionPixelSize);
        ip.h(ld0Var, colorStateListP);
        WeakHashMap weakHashMap = f51.a;
        setBackground(ld0Var);
        p50 p50Var = new p50(9, this);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i, i2);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        fa0.B(this, new l61(z2, z3, z4, p50Var));
    }
}
