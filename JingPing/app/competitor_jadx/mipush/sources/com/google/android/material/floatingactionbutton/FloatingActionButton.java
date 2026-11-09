package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.a;
import androidx.coordinatorlayout.widget.c;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.stateful.ExtendableSavedState;
import defpackage.bo;
import defpackage.dv;
import defpackage.f51;
import defpackage.f6;
import defpackage.fa0;
import defpackage.i01;
import defpackage.i6;
import defpackage.kv0;
import defpackage.l0;
import defpackage.la0;
import defpackage.ld0;
import defpackage.ma0;
import defpackage.pd0;
import defpackage.pw;
import defpackage.qa;
import defpackage.qw;
import defpackage.rf0;
import defpackage.rw;
import defpackage.sa;
import defpackage.u;
import defpackage.uu;
import defpackage.vv0;
import defpackage.xi;
import defpackage.xw;
import defpackage.z8;
import defpackage.zu0;
import defpackage.zw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements uu, kv0, a {
    public static final int U = R$style.Widget_Design_FloatingActionButton;
    public ColorStateList F;
    public PorterDuff.Mode G;
    public ColorStateList H;
    public PorterDuff.Mode I;
    public ColorStateList J;
    public int K;
    public int L;
    public int M;
    public int N;
    public boolean O;
    public final Rect P;
    public final Rect Q;
    public final l0 R;
    public final u S;
    public zw T;

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(pw pwVar) {
            super.setInternalAutoHideListener(pwVar);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private xw getImpl() {
        if (this.T == null) {
            this.T = new zw(this, new z8(20, this));
        }
        return this.T;
    }

    public final void c(AnimatorListenerAdapter animatorListenerAdapter) {
        xw impl = getImpl();
        if (impl.t == null) {
            impl.t = new ArrayList();
        }
        impl.t.add(animatorListenerAdapter);
    }

    public final void d(sa saVar) {
        xw impl = getImpl();
        if (impl.s == null) {
            impl.s = new ArrayList();
        }
        impl.s.add(saVar);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().j(getDrawableState());
    }

    public final void e(z8 z8Var) {
        xw impl = getImpl();
        qw qwVar = new qw(this, z8Var);
        if (impl.u == null) {
            impl.u = new ArrayList();
        }
        impl.u.add(qwVar);
    }

    public final int f(int i) {
        int i2 = this.L;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        return i != -1 ? i != 1 ? resources.getDimensionPixelSize(R$dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R$dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? f(1) : f(0);
    }

    public final void g(pw pwVar, boolean z) {
        xw impl = getImpl();
        i6 i6Var = pwVar == null ? null : new i6(this, 16, pwVar);
        if (impl.v.getVisibility() == 0) {
            if (impl.r == 1) {
                return;
            }
        } else if (impl.r != 2) {
            return;
        }
        Animator animator = impl.l;
        if (animator != null) {
            animator.cancel();
        }
        WeakHashMap weakHashMap = f51.a;
        FloatingActionButton floatingActionButton = impl.v;
        if (!floatingActionButton.isLaidOut() || floatingActionButton.isInEditMode()) {
            floatingActionButton.a(z ? 8 : 4, z);
            if (i6Var != null) {
                ((pw) i6Var.F).a((FloatingActionButton) i6Var.G);
                return;
            }
            return;
        }
        rf0 rf0Var = impl.n;
        AnimatorSet animatorSetB = rf0Var != null ? impl.b(rf0Var, 0.0f, 0.0f, 0.0f) : impl.c(0.0f, 0.4f, 0.4f, xw.F, xw.G);
        animatorSetB.addListener(new rw(impl, z, i6Var));
        ArrayList arrayList = impl.t;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetB.addListener((Animator.AnimatorListener) it.next());
            }
        }
        animatorSetB.start();
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.F;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.G;
    }

    @Override // androidx.coordinatorlayout.widget.a
    public CoordinatorLayout.Behavior<FloatingActionButton> getBehavior() {
        return new Behavior();
    }

    public float getCompatElevation() {
        return getImpl().e();
    }

    public float getCompatHoveredFocusedTranslationZ() {
        return getImpl().i;
    }

    public float getCompatPressedTranslationZ() {
        return getImpl().j;
    }

    public Drawable getContentBackground() {
        return getImpl().e;
    }

    public int getCustomSize() {
        return this.L;
    }

    public int getExpandedComponentIdHint() {
        return this.S.b;
    }

    public rf0 getHideMotionSpec() {
        return getImpl().n;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.J;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.J;
    }

    public zu0 getShapeAppearanceModel() {
        zu0 zu0Var = getImpl().a;
        zu0Var.getClass();
        return zu0Var;
    }

    public rf0 getShowMotionSpec() {
        return getImpl().m;
    }

    public int getSize() {
        return this.K;
    }

    public int getSizeDimension() {
        return f(this.K);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.H;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.I;
    }

    public boolean getUseCompatPadding() {
        return this.O;
    }

    public final boolean h() {
        xw impl = getImpl();
        if (impl.v.getVisibility() == 0) {
            if (impl.r != 1) {
                return false;
            }
        } else if (impl.r == 2) {
            return false;
        }
        return true;
    }

    public void hide(pw pwVar) {
        g(pwVar, true);
    }

    public final boolean i() {
        xw impl = getImpl();
        if (impl.v.getVisibility() != 0) {
            if (impl.r != 2) {
                return false;
            }
        } else if (impl.r == 1) {
            return false;
        }
        return true;
    }

    public final void j(Rect rect) {
        int i = rect.left;
        Rect rect2 = this.P;
        rect.left = i + rect2.left;
        rect.top += rect2.top;
        rect.right -= rect2.right;
        rect.bottom -= rect2.bottom;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        getImpl().h();
    }

    public final void k() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        ColorStateList colorStateList = this.H;
        if (colorStateList == null) {
            drawable.clearColorFilter();
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.I;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(f6.c(colorForState, mode));
    }

    public final void l(pw pwVar, boolean z) {
        xw impl = getImpl();
        i6 i6Var = pwVar == null ? null : new i6(this, 16, pwVar);
        if (impl.v.getVisibility() != 0) {
            if (impl.r == 2) {
                return;
            }
        } else if (impl.r != 1) {
            return;
        }
        Animator animator = impl.l;
        if (animator != null) {
            animator.cancel();
        }
        boolean z2 = impl.m == null;
        WeakHashMap weakHashMap = f51.a;
        FloatingActionButton floatingActionButton = impl.v;
        boolean z3 = floatingActionButton.isLaidOut() && !floatingActionButton.isInEditMode();
        Matrix matrix = impl.A;
        if (!z3) {
            floatingActionButton.a(0, z);
            floatingActionButton.setAlpha(1.0f);
            floatingActionButton.setScaleY(1.0f);
            floatingActionButton.setScaleX(1.0f);
            impl.p = 1.0f;
            impl.a(1.0f, matrix);
            floatingActionButton.setImageMatrix(matrix);
            if (i6Var != null) {
                ((pw) i6Var.F).b();
                return;
            }
            return;
        }
        if (floatingActionButton.getVisibility() != 0) {
            floatingActionButton.setAlpha(0.0f);
            floatingActionButton.setScaleY(z2 ? 0.4f : 0.0f);
            floatingActionButton.setScaleX(z2 ? 0.4f : 0.0f);
            float f = z2 ? 0.4f : 0.0f;
            impl.p = f;
            impl.a(f, matrix);
            floatingActionButton.setImageMatrix(matrix);
        }
        rf0 rf0Var = impl.m;
        AnimatorSet animatorSetB = rf0Var != null ? impl.b(rf0Var, 1.0f, 1.0f, 1.0f) : impl.c(1.0f, 1.0f, 1.0f, xw.D, xw.E);
        animatorSetB.addListener(new dv(impl, z, i6Var));
        ArrayList arrayList = impl.s;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetB.addListener((Animator.AnimatorListener) it.next());
            }
        }
        animatorSetB.start();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        xw impl = getImpl();
        ld0 ld0Var = impl.b;
        FloatingActionButton floatingActionButton = impl.v;
        if (ld0Var != null) {
            ma0.o0(floatingActionButton, ld0Var);
        }
        if (impl instanceof zw) {
            return;
        }
        ViewTreeObserver viewTreeObserver = floatingActionButton.getViewTreeObserver();
        if (impl.B == null) {
            impl.B = new xi(1, impl);
        }
        viewTreeObserver.addOnPreDrawListener(impl.B);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        xw impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.v.getViewTreeObserver();
        xi xiVar = impl.B;
        if (xiVar != null) {
            viewTreeObserver.removeOnPreDrawListener(xiVar);
            impl.B = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.M = (sizeDimension - this.N) / 2;
        getImpl().r();
        int iMin = Math.min(View.resolveSize(sizeDimension, i), View.resolveSize(sizeDimension, i2));
        Rect rect = this.P;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.E);
        Bundle bundle = (Bundle) extendableSavedState.G.getOrDefault("expandableWidgetHelper", null);
        bundle.getClass();
        u uVar = this.S;
        uVar.getClass();
        uVar.a = bundle.getBoolean("expanded", false);
        uVar.b = bundle.getInt("expandedComponentIdHint", 0);
        if (uVar.a) {
            View view = (View) uVar.c;
            ViewParent parent = view.getParent();
            if (parent instanceof CoordinatorLayout) {
                ((CoordinatorLayout) parent).m(view);
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        if (parcelableOnSaveInstanceState == null) {
            parcelableOnSaveInstanceState = new Bundle();
        }
        ExtendableSavedState extendableSavedState = new ExtendableSavedState(parcelableOnSaveInstanceState);
        vv0 vv0Var = extendableSavedState.G;
        u uVar = this.S;
        uVar.getClass();
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", uVar.a);
        bundle.putInt("expandedComponentIdHint", uVar.b);
        vv0Var.put("expandableWidgetHelper", bundle);
        return extendableSavedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            Rect rect = this.Q;
            rect.set(0, 0, measuredWidth, measuredHeight);
            j(rect);
            zw zwVar = this.T;
            int i = -(zwVar.f ? Math.max((zwVar.k - zwVar.v.getSizeDimension()) / 2, 0) : 0);
            rect.inset(i, i);
            if (!rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return false;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        Log.i("FloatingActionButton", "Setting a custom background is not supported.");
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            xw impl = getImpl();
            ld0 ld0Var = impl.b;
            if (ld0Var != null) {
                ld0Var.setTintList(colorStateList);
            }
            qa qaVar = impl.d;
            if (qaVar != null) {
                if (colorStateList != null) {
                    qaVar.m = colorStateList.getColorForState(qaVar.getState(), qaVar.m);
                }
                qaVar.p = colorStateList;
                qaVar.n = true;
                qaVar.invalidateSelf();
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.G != mode) {
            this.G = mode;
            ld0 ld0Var = getImpl().b;
            if (ld0Var != null) {
                ld0Var.setTintMode(mode);
            }
        }
    }

    public void setCompatElevation(float f) {
        xw impl = getImpl();
        if (impl.h != f) {
            impl.h = f;
            impl.k(f, impl.i, impl.j);
        }
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f) {
        xw impl = getImpl();
        if (impl.i != f) {
            impl.i = f;
            impl.k(impl.h, f, impl.j);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f) {
        xw impl = getImpl();
        if (impl.j != f) {
            impl.j = f;
            impl.k(impl.h, impl.i, f);
        }
    }

    public void setCompatPressedTranslationZResource(int i) {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Custom size must be non-negative");
        }
        if (i != this.L) {
            this.L = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ld0 ld0Var = getImpl().b;
        if (ld0Var != null) {
            ld0Var.l(f);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().f) {
            getImpl().f = z;
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        this.S.b = i;
    }

    public void setHideMotionSpec(rf0 rf0Var) {
        getImpl().n = rf0Var;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(rf0.b(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            xw impl = getImpl();
            float f = impl.p;
            impl.p = f;
            Matrix matrix = impl.A;
            impl.a(f, matrix);
            impl.v.setImageMatrix(matrix);
            if (this.H != null) {
                k();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.R.p(i);
        k();
    }

    public void setMaxImageSize(int i) {
        this.N = i;
        xw impl = getImpl();
        if (impl.q != i) {
            impl.q = i;
            float f = impl.p;
            impl.p = f;
            Matrix matrix = impl.A;
            impl.a(f, matrix);
            impl.v.setImageMatrix(matrix);
        }
    }

    public void setRippleColor(int i) {
        setRippleColor(ColorStateList.valueOf(i));
    }

    @Override // android.view.View
    public void setScaleX(float f) {
        super.setScaleX(f);
        getImpl().l();
    }

    @Override // android.view.View
    public void setScaleY(float f) {
        super.setScaleY(f);
        getImpl().l();
    }

    public void setShadowPaddingEnabled(boolean z) {
        xw impl = getImpl();
        impl.g = z;
        impl.r();
    }

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        getImpl().o(zu0Var);
    }

    public void setShowMotionSpec(rf0 rf0Var) {
        getImpl().m = rf0Var;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(rf0.b(getContext(), i));
    }

    public void setSize(int i) {
        this.L = 0;
        if (i != this.K) {
            this.K = i;
            requestLayout();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        setBackgroundTintList(colorStateList);
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        setBackgroundTintMode(mode);
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            k();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.I != mode) {
            this.I = mode;
            k();
        }
    }

    @Override // android.view.View
    public void setTranslationX(float f) {
        super.setTranslationX(f);
        getImpl().m();
    }

    @Override // android.view.View
    public void setTranslationY(float f) {
        super.setTranslationY(f);
        getImpl().m();
    }

    @Override // android.view.View
    public void setTranslationZ(float f) {
        super.setTranslationZ(f);
        getImpl().m();
    }

    public void setUseCompatPadding(boolean z) {
        if (this.O != z) {
            this.O = z;
            getImpl().i();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void show(pw pwVar) {
        l(pwVar, true);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingActionButtonStyle);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.J != colorStateList) {
            this.J = colorStateList;
            getImpl().n(this.J);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = U;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.P = new Rect();
        this.Q = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.FloatingActionButton, i, i2, new int[0]);
        this.F = la0.p(context2, typedArrayD, R$styleable.FloatingActionButton_backgroundTint);
        this.G = fa0.p0(typedArrayD.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.J = la0.p(context2, typedArrayD, R$styleable.FloatingActionButton_rippleColor);
        this.K = typedArrayD.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.L = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_fabCustomSize, 0);
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayD.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = typedArrayD.getDimension(R$styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = typedArrayD.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.O = typedArrayD.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.mtrl_fab_min_touch_target);
        setMaxImageSize(typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_maxImageSize, 0));
        rf0 rf0VarA = rf0.a(context2, typedArrayD, R$styleable.FloatingActionButton_showMotionSpec);
        rf0 rf0VarA2 = rf0.a(context2, typedArrayD, R$styleable.FloatingActionButton_hideMotionSpec);
        zu0 zu0VarA = zu0.d(context2, attributeSet, i, i2, zu0.m).a();
        boolean z = typedArrayD.getBoolean(R$styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(typedArrayD.getBoolean(R$styleable.FloatingActionButton_android_enabled, true));
        typedArrayD.recycle();
        l0 l0Var = new l0(this);
        this.R = l0Var;
        l0Var.i(attributeSet, i);
        u uVar = new u();
        uVar.a = false;
        uVar.b = 0;
        uVar.c = this;
        this.S = uVar;
        getImpl().o(zu0VarA);
        getImpl().g(this.F, this.G, this.J, dimensionPixelSize);
        getImpl().k = dimensionPixelSize2;
        xw impl = getImpl();
        if (impl.h != dimension) {
            impl.h = dimension;
            impl.k(dimension, impl.i, impl.j);
        }
        xw impl2 = getImpl();
        if (impl2.i != dimension2) {
            impl2.i = dimension2;
            impl2.k(impl2.h, dimension2, impl2.j);
        }
        xw impl3 = getImpl();
        if (impl3.j != dimension3) {
            impl3.j = dimension3;
            impl3.k(impl3.h, impl3.i, dimension3);
        }
        getImpl().m = rf0VarA;
        getImpl().n = rf0VarA2;
        getImpl().f = z;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect E;
        public pw F;
        public final boolean G;

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.G = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean e(Rect rect, View view) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int left = floatingActionButton.getLeft();
            Rect rect2 = floatingActionButton.P;
            rect.set(left + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void g(c cVar) {
            if (cVar.h == 0) {
                cVar.h = 80;
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            if (view2 instanceof AppBarLayout) {
                w(coordinatorLayout, (AppBarLayout) view2, floatingActionButton);
            } else {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                if (layoutParams instanceof c ? ((c) layoutParams).a instanceof BottomSheetBehavior : false) {
                    x(view2, floatingActionButton);
                }
            }
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean l(CoordinatorLayout coordinatorLayout, View view, int i) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            ArrayList arrayListO = coordinatorLayout.o(floatingActionButton);
            int size = arrayListO.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                View view2 = (View) arrayListO.get(i3);
                if (!(view2 instanceof AppBarLayout)) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    if ((layoutParams instanceof c ? ((c) layoutParams).a instanceof BottomSheetBehavior : false) && x(view2, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (w(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.v(floatingActionButton, i);
            Rect rect = floatingActionButton.P;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0) {
                c cVar = (c) floatingActionButton.getLayoutParams();
                int i4 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) cVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) cVar).leftMargin ? -rect.left : 0;
                if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) cVar).bottomMargin) {
                    i2 = rect.bottom;
                } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) cVar).topMargin) {
                    i2 = -rect.top;
                }
                if (i2 != 0) {
                    WeakHashMap weakHashMap = f51.a;
                    floatingActionButton.offsetTopAndBottom(i2);
                }
                if (i4 != 0) {
                    WeakHashMap weakHashMap2 = f51.a;
                    floatingActionButton.offsetLeftAndRight(i4);
                }
            }
            return true;
        }

        public void setInternalAutoHideListener(pw pwVar) {
            this.F = pwVar;
        }

        public final boolean w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!(this.G && ((c) floatingActionButton.getLayoutParams()).f == appBarLayout.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (this.E == null) {
                this.E = new Rect();
            }
            Rect rect = this.E;
            bo.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.g(this.F, false);
            } else {
                floatingActionButton.l(this.F, false);
            }
            return true;
        }

        public final boolean x(View view, FloatingActionButton floatingActionButton) {
            if (!(this.G && ((c) floatingActionButton.getLayoutParams()).f == view.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((c) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.g(this.F, false);
            } else {
                floatingActionButton.l(this.F, false);
            }
            return true;
        }

        public BaseBehavior() {
            this.G = true;
        }
    }
}
