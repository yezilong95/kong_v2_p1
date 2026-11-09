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
import defpackage.e;
import defpackage.fj0;
import defpackage.ft0;
import defpackage.hl;
import defpackage.k9;
import defpackage.ki0;
import defpackage.lr0;
import defpackage.oy;
import defpackage.qm;
import defpackage.rp0;
import defpackage.s8;
import defpackage.t3;
import defpackage.t8;
import defpackage.u50;
import defpackage.u8;
import defpackage.un0;
import defpackage.w8;
import defpackage.x8;
import defpackage.xi;
import defpackage.xr0;
import defpackage.xv;
import defpackage.y50;
import defpackage.y8;
import defpackage.yf0;
import defpackage.z8;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBar extends Toolbar implements androidx.coordinatorlayout.widget.a {
    public static final int s0 = R$style.Widget_MaterialComponents_BottomAppBar;
    public static final int t0 = R$attr.motionDurationLong2;
    public static final int u0 = R$attr.motionEasingEmphasizedInterpolator;
    public Integer S;
    public final u50 T;
    public AnimatorSet U;
    public AnimatorSet V;
    public int W;
    public int a0;
    public int b0;
    public final int c0;
    public int d0;
    public int e0;
    public final boolean f0;
    public boolean g0;
    public final boolean h0;
    public final boolean i0;
    public final boolean j0;
    public boolean k0;
    public boolean l0;
    public Behavior m0;
    public int n0;
    public int o0;
    public int p0;
    public final t8 q0;
    public final u8 r0;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new b();
        public int d;
        public boolean e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readInt();
            this.e = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e ? 1 : 0);
        }
    }

    public BottomAppBar(Context context) {
        this(context, null);
    }

    public static void J(BottomAppBar bottomAppBar, View view) {
        c cVar = (c) view.getLayoutParams();
        cVar.d = 17;
        int i = bottomAppBar.b0;
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
        return this.n0;
    }

    private int getFabAlignmentAnimationDuration() {
        return k9.E(getContext(), t0, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getFabTranslationX() {
        return C(this.W);
    }

    private float getFabTranslationY() {
        if (this.b0 == 1) {
            return -getTopEdgeTreatment().o;
        }
        return A() != null ? (-((getMeasuredHeight() + getBottomInset()) - r0.getMeasuredHeight())) / 2 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getLeftInset() {
        return this.p0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRightInset() {
        return this.o0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public z8 getTopEdgeTreatment() {
        return (z8) this.T.b.a.i;
    }

    public final View A() {
        if (getParent() instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getParent();
            List list = (List) ((fj0) coordinatorLayout.c.b).getOrDefault(this, null);
            ArrayList<View> arrayList = coordinatorLayout.e;
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
        if (this.e0 != 1 && (i != 1 || !z)) {
            return 0;
        }
        boolean zI = rp0.I(this);
        int measuredWidth = zI ? getMeasuredWidth() : 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if ((childAt.getLayoutParams() instanceof Toolbar.LayoutParams) && (((Toolbar.LayoutParams) childAt.getLayoutParams()).a & 8388615) == 8388611) {
                measuredWidth = zI ? Math.min(measuredWidth, childAt.getLeft()) : Math.max(measuredWidth, childAt.getRight());
            }
        }
        int right = zI ? actionMenuView.getRight() : actionMenuView.getLeft();
        int i3 = zI ? this.o0 : -this.p0;
        if (getNavigationIcon() == null) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R$dimen.m3_bottomappbar_horizontal_padding);
            if (!zI) {
                dimensionPixelOffset = -dimensionPixelOffset;
            }
        }
        return measuredWidth - ((right + i3) + dimensionPixelOffset);
    }

    public final float C(int i) {
        boolean zI = rp0.I(this);
        if (i != 1) {
            return 0.0f;
        }
        View viewA = A();
        int i2 = zI ? this.p0 : this.o0;
        return ((getMeasuredWidth() / 2) - ((this.d0 == -1 || viewA == null) ? this.c0 + i2 : ((viewA.getMeasuredWidth() / 2) + this.d0) + i2)) * (zI ? -1 : 1);
    }

    public final boolean D() {
        View viewA = A();
        FloatingActionButton floatingActionButton = viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null;
        return floatingActionButton != null && floatingActionButton.i();
    }

    public final void E(int i, boolean z) {
        int i2 = 2;
        WeakHashMap weakHashMap = xr0.a;
        if (!isLaidOut()) {
            this.k0 = false;
            return;
        }
        AnimatorSet animatorSet = this.V;
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
                objectAnimatorOfFloat2.addListener(new x8(this, actionMenuView, i, z));
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playSequentially(objectAnimatorOfFloat2, objectAnimatorOfFloat);
                arrayList.add(animatorSet2);
            } else if (actionMenuView.getAlpha() < 1.0f) {
                arrayList.add(objectAnimatorOfFloat);
            }
        }
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(arrayList);
        this.V = animatorSet3;
        animatorSet3.addListener(new t8(this, i2));
        this.V.start();
    }

    public final void F() {
        ActionMenuView actionMenuView = getActionMenuView();
        if (actionMenuView == null || this.V != null) {
            return;
        }
        actionMenuView.setAlpha(1.0f);
        if (D()) {
            I(actionMenuView, this.W, this.l0, false);
        } else {
            I(actionMenuView, 0, false, false);
        }
    }

    public final void G() {
        getTopEdgeTreatment().p = getFabTranslationX();
        this.T.n((this.l0 && D() && this.b0 == 1) ? 1.0f : 0.0f);
        View viewA = A();
        if (viewA != null) {
            viewA.setTranslationY(getFabTranslationY());
            viewA.setTranslationX(getFabTranslationX());
        }
    }

    public final void H(int i) {
        float f = i;
        if (f != getTopEdgeTreatment().n) {
            getTopEdgeTreatment().n = f;
            this.T.invalidateSelf();
        }
    }

    public final void I(ActionMenuView actionMenuView, int i, boolean z, boolean z2) {
        y8 y8Var = new y8(this, actionMenuView, i, z);
        if (z2) {
            actionMenuView.post(y8Var);
        } else {
            y8Var.run();
        }
    }

    public void addOnScrollStateChangedListener(xv xvVar) {
        getBehavior().addOnScrollStateChangedListener(xvVar);
    }

    public ColorStateList getBackgroundTint() {
        return this.T.b.e;
    }

    public float getCradleVerticalOffset() {
        return getTopEdgeTreatment().o;
    }

    public int getFabAlignmentMode() {
        return this.W;
    }

    public int getFabAlignmentModeEndMargin() {
        return this.d0;
    }

    public int getFabAnchorMode() {
        return this.b0;
    }

    public int getFabAnimationMode() {
        return this.a0;
    }

    public float getFabCradleMargin() {
        return getTopEdgeTreatment().m;
    }

    public float getFabCradleRoundedCornerRadius() {
        return getTopEdgeTreatment().l;
    }

    public boolean getHideOnScroll() {
        return this.g0;
    }

    public int getMenuAlignmentMode() {
        return this.e0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.O(this, this.T);
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).setClipChildren(false);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            AnimatorSet animatorSet = this.V;
            if (animatorSet != null) {
                animatorSet.cancel();
            }
            AnimatorSet animatorSet2 = this.U;
            if (animatorSet2 != null) {
                animatorSet2.cancel();
            }
            G();
            View viewA = A();
            if (viewA != null) {
                WeakHashMap weakHashMap = xr0.a;
                if (viewA.isLaidOut()) {
                    viewA.post(new s8(viewA, 0));
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
        super.onRestoreInstanceState(savedState.b);
        this.W = savedState.d;
        this.l0 = savedState.e;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.d = this.W;
        savedState.e = this.l0;
        return savedState;
    }

    public void removeOnScrollStateChangedListener(xv xvVar) {
        getBehavior().removeOnScrollStateChangedListener(xvVar);
    }

    public void setBackgroundTint(ColorStateList colorStateList) {
        hl.h(this.T, colorStateList);
    }

    public void setCradleVerticalOffset(float f) {
        if (f != getCradleVerticalOffset()) {
            getTopEdgeTreatment().o(f);
            this.T.invalidateSelf();
            G();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        u50 u50Var = this.T;
        u50Var.l(f);
        int iH = u50Var.b.o - u50Var.h();
        Behavior behavior = getBehavior();
        behavior.i = iH;
        if (behavior.h == 1) {
            setTranslationY(behavior.g + iH);
        }
    }

    public void setFabAlignmentMode(int i) {
        int i2 = 1;
        this.k0 = true;
        E(i, this.l0);
        if (this.W != i) {
            WeakHashMap weakHashMap = xr0.a;
            if (isLaidOut()) {
                AnimatorSet animatorSet = this.U;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                ArrayList arrayList = new ArrayList();
                if (this.a0 == 1) {
                    View viewA = A();
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewA instanceof FloatingActionButton ? (FloatingActionButton) viewA : null, "translationX", C(i));
                    objectAnimatorOfFloat.setDuration(getFabAlignmentAnimationDuration());
                    arrayList.add(objectAnimatorOfFloat);
                } else {
                    View viewA2 = A();
                    FloatingActionButton floatingActionButton = viewA2 instanceof FloatingActionButton ? (FloatingActionButton) viewA2 : null;
                    if (floatingActionButton != null && !floatingActionButton.h()) {
                        floatingActionButton.hide(new w8(this, i));
                    }
                }
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(arrayList);
                animatorSet2.setInterpolator(k9.F(getContext(), u0, t3.a));
                this.U = animatorSet2;
                animatorSet2.addListener(new t8(this, i2));
                this.U.start();
            }
        }
        this.W = i;
    }

    public void setFabAlignmentModeEndMargin(int i) {
        if (this.d0 != i) {
            this.d0 = i;
            G();
        }
    }

    public void setFabAnchorMode(int i) {
        this.b0 = i;
        G();
        View viewA = A();
        if (viewA != null) {
            J(this, viewA);
            viewA.requestLayout();
            this.T.invalidateSelf();
        }
    }

    public void setFabAnimationMode(int i) {
        this.a0 = i;
    }

    public void setFabCornerSize(float f) {
        if (f != getTopEdgeTreatment().q) {
            getTopEdgeTreatment().q = f;
            this.T.invalidateSelf();
        }
    }

    public void setFabCradleMargin(float f) {
        if (f != getFabCradleMargin()) {
            getTopEdgeTreatment().m = f;
            this.T.invalidateSelf();
        }
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        if (f != getFabCradleRoundedCornerRadius()) {
            getTopEdgeTreatment().l = f;
            this.T.invalidateSelf();
        }
    }

    public void setHideOnScroll(boolean z) {
        this.g0 = z;
    }

    public void setMenuAlignmentMode(int i) {
        if (this.e0 != i) {
            this.e0 = i;
            ActionMenuView actionMenuView = getActionMenuView();
            if (actionMenuView != null) {
                I(actionMenuView, this.W, D(), false);
            }
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.S != null) {
            drawable = drawable.mutate();
            hl.g(drawable, this.S.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.S = Integer.valueOf(i);
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
        if (this.m0 == null) {
            this.m0 = new Behavior();
        }
        return this.m0;
    }

    public static class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        public final Rect n;
        public WeakReference o;
        public int p;
        public final a q;

        public Behavior() {
            this.q = new a(this);
            this.n = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            BottomAppBar bottomAppBar = (BottomAppBar) view;
            this.o = new WeakReference(bottomAppBar);
            int i2 = BottomAppBar.s0;
            View viewA = bottomAppBar.A();
            if (viewA != null) {
                WeakHashMap weakHashMap = xr0.a;
                if (!viewA.isLaidOut()) {
                    BottomAppBar.J(bottomAppBar, viewA);
                    this.p = ((ViewGroup.MarginLayoutParams) ((c) viewA.getLayoutParams())).bottomMargin;
                    if (viewA instanceof FloatingActionButton) {
                        FloatingActionButton floatingActionButton = (FloatingActionButton) viewA;
                        if (bottomAppBar.b0 == 0 && bottomAppBar.f0) {
                            lr0.s(floatingActionButton, 0.0f);
                            floatingActionButton.setCompatElevation(0.0f);
                        }
                        if (floatingActionButton.getShowMotionSpec() == null) {
                            floatingActionButton.setShowMotionSpecResource(R$animator.mtrl_fab_show_motion_spec);
                        }
                        if (floatingActionButton.getHideMotionSpec() == null) {
                            floatingActionButton.setHideMotionSpecResource(R$animator.mtrl_fab_hide_motion_spec);
                        }
                        floatingActionButton.c(bottomAppBar.q0);
                        floatingActionButton.d(new t8(bottomAppBar, 3));
                        floatingActionButton.e(bottomAppBar.r0);
                    }
                    viewA.addOnLayoutChangeListener(this.q);
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
            this.q = new a(this);
            this.n = new Rect();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BottomAppBar(Context context, AttributeSet attributeSet, int i) {
        int i2 = s0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        u50 u50Var = new u50();
        this.T = u50Var;
        this.k0 = false;
        this.l0 = true;
        this.q0 = new t8(this, 0);
        this.r0 = new u8(this);
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.BottomAppBar, i, i2, new int[0]);
        ColorStateList colorStateListS = xi.s(context2, typedArrayD, R$styleable.BottomAppBar_backgroundTint);
        if (typedArrayD.hasValue(R$styleable.BottomAppBar_navigationIconTint)) {
            setNavigationIconTint(typedArrayD.getColor(R$styleable.BottomAppBar_navigationIconTint, -1));
        }
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.BottomAppBar_elevation, 0);
        float dimensionPixelOffset = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleMargin, 0);
        float dimensionPixelOffset2 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleRoundedCornerRadius, 0);
        float dimensionPixelOffset3 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabCradleVerticalOffset, 0);
        this.W = typedArrayD.getInt(R$styleable.BottomAppBar_fabAlignmentMode, 0);
        this.a0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAnimationMode, 0);
        this.b0 = typedArrayD.getInt(R$styleable.BottomAppBar_fabAnchorMode, 1);
        this.f0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_removeEmbeddedFabElevation, true);
        this.e0 = typedArrayD.getInt(R$styleable.BottomAppBar_menuAlignmentMode, 0);
        this.g0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_hideOnScroll, false);
        this.h0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingBottomSystemWindowInsets, false);
        this.i0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingLeftSystemWindowInsets, false);
        this.j0 = typedArrayD.getBoolean(R$styleable.BottomAppBar_paddingRightSystemWindowInsets, false);
        this.d0 = typedArrayD.getDimensionPixelOffset(R$styleable.BottomAppBar_fabAlignmentModeEndMargin, -1);
        boolean z = typedArrayD.getBoolean(R$styleable.BottomAppBar_addElevationShadow, true);
        typedArrayD.recycle();
        this.c0 = getResources().getDimensionPixelOffset(R$dimen.mtrl_bottomappbar_fabOffsetEndMode);
        z8 z8Var = new z8();
        z8Var.q = -1.0f;
        z8Var.m = dimensionPixelOffset;
        z8Var.l = dimensionPixelOffset2;
        z8Var.o(dimensionPixelOffset3);
        z8Var.p = 0.0f;
        yf0 yf0Var = new yf0();
        yf0 yf0Var2 = new yf0();
        yf0 yf0Var3 = new yf0();
        yf0 yf0Var4 = new yf0();
        e eVar = new e(0.0f);
        e eVar2 = new e(0.0f);
        e eVar3 = new e(0.0f);
        e eVar4 = new e(0.0f);
        qm qmVar = new qm();
        qm qmVar2 = new qm();
        qm qmVar3 = new qm();
        ki0 ki0Var = new ki0();
        ki0Var.a = yf0Var;
        ki0Var.b = yf0Var2;
        ki0Var.c = yf0Var3;
        ki0Var.d = yf0Var4;
        ki0Var.e = eVar;
        ki0Var.f = eVar2;
        ki0Var.g = eVar3;
        ki0Var.h = eVar4;
        ki0Var.i = z8Var;
        ki0Var.j = qmVar;
        ki0Var.k = qmVar2;
        ki0Var.l = qmVar3;
        u50Var.setShapeAppearanceModel(ki0Var);
        if (z) {
            u50Var.q(2);
        } else {
            u50Var.q(1);
            if (Build.VERSION.SDK_INT >= 28) {
                setOutlineAmbientShadowColor(0);
                setOutlineSpotShadowColor(0);
            }
        }
        Paint.Style style = Paint.Style.FILL;
        u50Var.o();
        u50Var.k(context2);
        setElevation(dimensionPixelSize);
        hl.h(u50Var, colorStateListS);
        WeakHashMap weakHashMap = xr0.a;
        setBackground(u50Var);
        u8 u8Var = new u8(this);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.Insets, i, i2);
        boolean z2 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingBottomSystemWindowInsets, false);
        boolean z3 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingLeftSystemWindowInsets, false);
        boolean z4 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.Insets_paddingRightSystemWindowInsets, false);
        typedArrayObtainStyledAttributes.recycle();
        rp0.s(this, new ft0(z2, z3, z4, u8Var));
    }
}
