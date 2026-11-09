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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.internal.VisibilityAwareImageButton;
import com.google.android.material.stateful.ExtendableSavedState;
import defpackage.a5;
import defpackage.c;
import defpackage.cq;
import defpackage.d5;
import defpackage.dq;
import defpackage.e5;
import defpackage.eq;
import defpackage.fj0;
import defpackage.gp;
import defpackage.jh;
import defpackage.jk;
import defpackage.ki0;
import defpackage.kq;
import defpackage.mq;
import defpackage.o70;
import defpackage.oy;
import defpackage.r8;
import defpackage.rp0;
import defpackage.t8;
import defpackage.u50;
import defpackage.u8;
import defpackage.un0;
import defpackage.vi0;
import defpackage.x70;
import defpackage.xi;
import defpackage.xo;
import defpackage.xr0;
import defpackage.y50;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButton extends VisibilityAwareImageButton implements xo, vi0, a {
    public static final int r = R$style.Widget_Design_FloatingActionButton;
    public ColorStateList c;
    public PorterDuff.Mode d;
    public ColorStateList e;
    public PorterDuff.Mode f;
    public ColorStateList g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean l;
    public final Rect m;
    public final Rect n;
    public final d5 o;
    public final c p;
    public mq q;

    public static class Behavior extends BaseBehavior<FloatingActionButton> {
        public Behavior() {
        }

        @Override // com.google.android.material.floatingactionbutton.FloatingActionButton.BaseBehavior
        public /* bridge */ /* synthetic */ void setInternalAutoHideListener(cq cqVar) {
            super.setInternalAutoHideListener(cqVar);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    private kq getImpl() {
        if (this.q == null) {
            this.q = new mq(this, new o70(20, this));
        }
        return this.q;
    }

    public final void c(AnimatorListenerAdapter animatorListenerAdapter) {
        kq impl = getImpl();
        if (impl.t == null) {
            impl.t = new ArrayList();
        }
        impl.t.add(animatorListenerAdapter);
    }

    public final void d(t8 t8Var) {
        kq impl = getImpl();
        if (impl.s == null) {
            impl.s = new ArrayList();
        }
        impl.s.add(t8Var);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        getImpl().j(getDrawableState());
    }

    public final void e(u8 u8Var) {
        kq impl = getImpl();
        dq dqVar = new dq(this, u8Var);
        if (impl.u == null) {
            impl.u = new ArrayList();
        }
        impl.u.add(dqVar);
    }

    public final int f(int i) {
        int i2 = this.i;
        if (i2 != 0) {
            return i2;
        }
        Resources resources = getResources();
        return i != -1 ? i != 1 ? resources.getDimensionPixelSize(R$dimen.design_fab_size_normal) : resources.getDimensionPixelSize(R$dimen.design_fab_size_mini) : Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470 ? f(1) : f(0);
    }

    public final void g(cq cqVar, boolean z) {
        kq impl = getImpl();
        e5 e5Var = cqVar == null ? null : new e5(this, 13, cqVar);
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
        WeakHashMap weakHashMap = xr0.a;
        FloatingActionButton floatingActionButton = impl.v;
        if (!floatingActionButton.isLaidOut() || floatingActionButton.isInEditMode()) {
            floatingActionButton.a(z ? 8 : 4, z);
            if (e5Var != null) {
                ((cq) e5Var.c).a((FloatingActionButton) e5Var.d);
                return;
            }
            return;
        }
        x70 x70Var = impl.n;
        AnimatorSet animatorSetB = x70Var != null ? impl.b(x70Var, 0.0f, 0.0f, 0.0f) : impl.c(0.0f, 0.4f, 0.4f, kq.F, kq.G);
        animatorSetB.addListener(new eq(impl, z, e5Var));
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
        return this.c;
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return this.d;
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
        return this.i;
    }

    public int getExpandedComponentIdHint() {
        return this.p.b;
    }

    public x70 getHideMotionSpec() {
        return getImpl().n;
    }

    @Deprecated
    public int getRippleColor() {
        ColorStateList colorStateList = this.g;
        if (colorStateList != null) {
            return colorStateList.getDefaultColor();
        }
        return 0;
    }

    public ColorStateList getRippleColorStateList() {
        return this.g;
    }

    public ki0 getShapeAppearanceModel() {
        ki0 ki0Var = getImpl().a;
        ki0Var.getClass();
        return ki0Var;
    }

    public x70 getShowMotionSpec() {
        return getImpl().m;
    }

    public int getSize() {
        return this.h;
    }

    public int getSizeDimension() {
        return f(this.h);
    }

    public ColorStateList getSupportBackgroundTintList() {
        return getBackgroundTintList();
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return getBackgroundTintMode();
    }

    public ColorStateList getSupportImageTintList() {
        return this.e;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        return this.f;
    }

    public boolean getUseCompatPadding() {
        return this.l;
    }

    public final boolean h() {
        kq impl = getImpl();
        if (impl.v.getVisibility() == 0) {
            if (impl.r != 1) {
                return false;
            }
        } else if (impl.r == 2) {
            return false;
        }
        return true;
    }

    public void hide(cq cqVar) {
        g(cqVar, true);
    }

    public final boolean i() {
        kq impl = getImpl();
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
        Rect rect2 = this.m;
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
        ColorStateList colorStateList = this.e;
        if (colorStateList == null) {
            drawable.clearColorFilter();
            return;
        }
        int colorForState = colorStateList.getColorForState(getDrawableState(), 0);
        PorterDuff.Mode mode = this.f;
        if (mode == null) {
            mode = PorterDuff.Mode.SRC_IN;
        }
        drawable.mutate().setColorFilter(a5.c(colorForState, mode));
    }

    public final void l(cq cqVar, boolean z) {
        kq impl = getImpl();
        e5 e5Var = cqVar == null ? null : new e5(this, 13, cqVar);
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
        WeakHashMap weakHashMap = xr0.a;
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
            if (e5Var != null) {
                ((cq) e5Var.c).b();
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
        x70 x70Var = impl.m;
        AnimatorSet animatorSetB = x70Var != null ? impl.b(x70Var, 1.0f, 1.0f, 1.0f) : impl.c(1.0f, 1.0f, 1.0f, kq.D, kq.E);
        animatorSetB.addListener(new gp(impl, z, e5Var));
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
        kq impl = getImpl();
        u50 u50Var = impl.b;
        FloatingActionButton floatingActionButton = impl.v;
        if (u50Var != null) {
            oy.O(floatingActionButton, u50Var);
        }
        if (impl instanceof mq) {
            return;
        }
        ViewTreeObserver viewTreeObserver = floatingActionButton.getViewTreeObserver();
        if (impl.B == null) {
            impl.B = new jh(1, impl);
        }
        viewTreeObserver.addOnPreDrawListener(impl.B);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        kq impl = getImpl();
        ViewTreeObserver viewTreeObserver = impl.v.getViewTreeObserver();
        jh jhVar = impl.B;
        if (jhVar != null) {
            viewTreeObserver.removeOnPreDrawListener(jhVar);
            impl.B = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        int sizeDimension = getSizeDimension();
        this.j = (sizeDimension - this.k) / 2;
        getImpl().r();
        int iMin = Math.min(View.resolveSize(sizeDimension, i), View.resolveSize(sizeDimension, i2));
        Rect rect = this.m;
        setMeasuredDimension(rect.left + iMin + rect.right, iMin + rect.top + rect.bottom);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof ExtendableSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        ExtendableSavedState extendableSavedState = (ExtendableSavedState) parcelable;
        super.onRestoreInstanceState(extendableSavedState.b);
        Bundle bundle = (Bundle) extendableSavedState.d.getOrDefault("expandableWidgetHelper", null);
        bundle.getClass();
        c cVar = this.p;
        cVar.getClass();
        cVar.a = bundle.getBoolean("expanded", false);
        cVar.b = bundle.getInt("expandedComponentIdHint", 0);
        if (cVar.a) {
            View view = cVar.c;
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
        fj0 fj0Var = extendableSavedState.d;
        c cVar = this.p;
        cVar.getClass();
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", cVar.a);
        bundle.putInt("expandedComponentIdHint", cVar.b);
        fj0Var.put("expandableWidgetHelper", bundle);
        return extendableSavedState;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            Rect rect = this.n;
            rect.set(0, 0, measuredWidth, measuredHeight);
            j(rect);
            mq mqVar = this.q;
            int i = -(mqVar.f ? Math.max((mqVar.k - mqVar.v.getSizeDimension()) / 2, 0) : 0);
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
        if (this.c != colorStateList) {
            this.c = colorStateList;
            kq impl = getImpl();
            u50 u50Var = impl.b;
            if (u50Var != null) {
                u50Var.setTintList(colorStateList);
            }
            r8 r8Var = impl.d;
            if (r8Var != null) {
                if (colorStateList != null) {
                    r8Var.m = colorStateList.getColorForState(r8Var.getState(), r8Var.m);
                }
                r8Var.p = colorStateList;
                r8Var.n = true;
                r8Var.invalidateSelf();
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.d != mode) {
            this.d = mode;
            u50 u50Var = getImpl().b;
            if (u50Var != null) {
                u50Var.setTintMode(mode);
            }
        }
    }

    public void setCompatElevation(float f) {
        kq impl = getImpl();
        if (impl.h != f) {
            impl.h = f;
            impl.k(f, impl.i, impl.j);
        }
    }

    public void setCompatElevationResource(int i) {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f) {
        kq impl = getImpl();
        if (impl.i != f) {
            impl.i = f;
            impl.k(impl.h, f, impl.j);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i) {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f) {
        kq impl = getImpl();
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
        if (i != this.i) {
            this.i = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        u50 u50Var = getImpl().b;
        if (u50Var != null) {
            u50Var.l(f);
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        if (z != getImpl().f) {
            getImpl().f = z;
            requestLayout();
        }
    }

    public void setExpandedComponentIdHint(int i) {
        this.p.b = i;
    }

    public void setHideMotionSpec(x70 x70Var) {
        getImpl().n = x70Var;
    }

    public void setHideMotionSpecResource(int i) {
        setHideMotionSpec(x70.b(getContext(), i));
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (getDrawable() != drawable) {
            super.setImageDrawable(drawable);
            kq impl = getImpl();
            float f = impl.p;
            impl.p = f;
            Matrix matrix = impl.A;
            impl.a(f, matrix);
            impl.v.setImageMatrix(matrix);
            if (this.e != null) {
                k();
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.o.j(i);
        k();
    }

    public void setMaxImageSize(int i) {
        this.k = i;
        kq impl = getImpl();
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
        kq impl = getImpl();
        impl.g = z;
        impl.r();
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        getImpl().o(ki0Var);
    }

    public void setShowMotionSpec(x70 x70Var) {
        getImpl().m = x70Var;
    }

    public void setShowMotionSpecResource(int i) {
        setShowMotionSpec(x70.b(getContext(), i));
    }

    public void setSize(int i) {
        this.i = 0;
        if (i != this.h) {
            this.h = i;
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
        if (this.e != colorStateList) {
            this.e = colorStateList;
            k();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        if (this.f != mode) {
            this.f = mode;
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
        if (this.l != z) {
            this.l = z;
            getImpl().i();
        }
    }

    @Override // com.google.android.material.internal.VisibilityAwareImageButton, android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void show(cq cqVar) {
        l(cqVar, true);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingActionButtonStyle);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (this.g != colorStateList) {
            this.g = colorStateList;
            getImpl().n(this.g);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = r;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.m = new Rect();
        this.n = new Rect();
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.FloatingActionButton, i, i2, new int[0]);
        this.c = xi.s(context2, typedArrayD, R$styleable.FloatingActionButton_backgroundTint);
        this.d = rp0.O(typedArrayD.getInt(R$styleable.FloatingActionButton_backgroundTintMode, -1), null);
        this.g = xi.s(context2, typedArrayD, R$styleable.FloatingActionButton_rippleColor);
        this.h = typedArrayD.getInt(R$styleable.FloatingActionButton_fabSize, -1);
        this.i = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_fabCustomSize, 0);
        int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_borderWidth, 0);
        float dimension = typedArrayD.getDimension(R$styleable.FloatingActionButton_elevation, 0.0f);
        float dimension2 = typedArrayD.getDimension(R$styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0f);
        float dimension3 = typedArrayD.getDimension(R$styleable.FloatingActionButton_pressedTranslationZ, 0.0f);
        this.l = typedArrayD.getBoolean(R$styleable.FloatingActionButton_useCompatPadding, false);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.mtrl_fab_min_touch_target);
        setMaxImageSize(typedArrayD.getDimensionPixelSize(R$styleable.FloatingActionButton_maxImageSize, 0));
        x70 x70VarA = x70.a(context2, typedArrayD, R$styleable.FloatingActionButton_showMotionSpec);
        x70 x70VarA2 = x70.a(context2, typedArrayD, R$styleable.FloatingActionButton_hideMotionSpec);
        ki0 ki0VarA = ki0.d(context2, attributeSet, i, i2, ki0.m).a();
        boolean z = typedArrayD.getBoolean(R$styleable.FloatingActionButton_ensureMinTouchTargetSize, false);
        setEnabled(typedArrayD.getBoolean(R$styleable.FloatingActionButton_android_enabled, true));
        typedArrayD.recycle();
        d5 d5Var = new d5(this);
        this.o = d5Var;
        d5Var.f(attributeSet, i);
        this.p = new c(this);
        getImpl().o(ki0VarA);
        getImpl().g(this.c, this.d, this.g, dimensionPixelSize);
        getImpl().k = dimensionPixelSize2;
        kq impl = getImpl();
        if (impl.h != dimension) {
            impl.h = dimension;
            impl.k(dimension, impl.i, impl.j);
        }
        kq impl2 = getImpl();
        if (impl2.i != dimension2) {
            impl2.i = dimension2;
            impl2.k(impl2.h, dimension2, impl2.j);
        }
        kq impl3 = getImpl();
        if (impl3.j != dimension3) {
            impl3.j = dimension3;
            impl3.k(impl3.h, impl3.i, dimension3);
        }
        getImpl().m = x70VarA;
        getImpl().n = x70VarA2;
        getImpl().f = z;
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public static class BaseBehavior<T extends FloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        public Rect b;
        public cq c;
        public final boolean d;

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingActionButton_Behavior_Layout);
            this.d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean e(Rect rect, View view) {
            FloatingActionButton floatingActionButton = (FloatingActionButton) view;
            int left = floatingActionButton.getLeft();
            Rect rect2 = floatingActionButton.m;
            rect.set(left + rect2.left, floatingActionButton.getTop() + rect2.top, floatingActionButton.getRight() - rect2.right, floatingActionButton.getBottom() - rect2.bottom);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final void g(androidx.coordinatorlayout.widget.c cVar) {
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
                if (layoutParams instanceof androidx.coordinatorlayout.widget.c ? ((androidx.coordinatorlayout.widget.c) layoutParams).a instanceof BottomSheetBehavior : false) {
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
                    if ((layoutParams instanceof androidx.coordinatorlayout.widget.c ? ((androidx.coordinatorlayout.widget.c) layoutParams).a instanceof BottomSheetBehavior : false) && x(view2, floatingActionButton)) {
                        break;
                    }
                } else {
                    if (w(coordinatorLayout, (AppBarLayout) view2, floatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.v(floatingActionButton, i);
            Rect rect = floatingActionButton.m;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0) {
                androidx.coordinatorlayout.widget.c cVar = (androidx.coordinatorlayout.widget.c) floatingActionButton.getLayoutParams();
                int i4 = floatingActionButton.getRight() >= coordinatorLayout.getWidth() - ((ViewGroup.MarginLayoutParams) cVar).rightMargin ? rect.right : floatingActionButton.getLeft() <= ((ViewGroup.MarginLayoutParams) cVar).leftMargin ? -rect.left : 0;
                if (floatingActionButton.getBottom() >= coordinatorLayout.getHeight() - ((ViewGroup.MarginLayoutParams) cVar).bottomMargin) {
                    i2 = rect.bottom;
                } else if (floatingActionButton.getTop() <= ((ViewGroup.MarginLayoutParams) cVar).topMargin) {
                    i2 = -rect.top;
                }
                if (i2 != 0) {
                    WeakHashMap weakHashMap = xr0.a;
                    floatingActionButton.offsetTopAndBottom(i2);
                }
                if (i4 != 0) {
                    WeakHashMap weakHashMap2 = xr0.a;
                    floatingActionButton.offsetLeftAndRight(i4);
                }
            }
            return true;
        }

        public void setInternalAutoHideListener(cq cqVar) {
            this.c = cqVar;
        }

        public final boolean w(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, FloatingActionButton floatingActionButton) {
            if (!(this.d && ((androidx.coordinatorlayout.widget.c) floatingActionButton.getLayoutParams()).f == appBarLayout.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (this.b == null) {
                this.b = new Rect();
            }
            Rect rect = this.b;
            jk.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                floatingActionButton.g(this.c, false);
            } else {
                floatingActionButton.l(this.c, false);
            }
            return true;
        }

        public final boolean x(View view, FloatingActionButton floatingActionButton) {
            if (!(this.d && ((androidx.coordinatorlayout.widget.c) floatingActionButton.getLayoutParams()).f == view.getId() && floatingActionButton.getUserSetVisibility() == 0)) {
                return false;
            }
            if (view.getTop() < (floatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((androidx.coordinatorlayout.widget.c) floatingActionButton.getLayoutParams())).topMargin) {
                floatingActionButton.g(this.c, false);
            } else {
                floatingActionButton.l(this.c, false);
            }
            return true;
        }

        public BaseBehavior() {
            this.d = true;
        }
    }
}
