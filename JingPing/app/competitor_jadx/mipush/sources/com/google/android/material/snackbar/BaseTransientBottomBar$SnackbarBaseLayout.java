package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$styleable;
import defpackage.ca;
import defpackage.da;
import defpackage.f51;
import defpackage.fa0;
import defpackage.ip;
import defpackage.la0;
import defpackage.ld0;
import defpackage.pd0;
import defpackage.r41;
import defpackage.t41;
import defpackage.zu0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {
    public static final ca M = new ca(0);
    public final zu0 E;
    public int F;
    public final float G;
    public final float H;
    public final int I;
    public final int J;
    public ColorStateList K;
    public PorterDuff.Mode L;

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        Drawable drawable;
        super(pd0.a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_elevation, 0);
            WeakHashMap weakHashMap = f51.a;
            t41.s(this, dimensionPixelSize);
        }
        this.F = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearance) || typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearanceOverlay)) {
            this.E = zu0.c(context2, attributeSet, 0, 0).a();
        }
        this.G = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(la0.p(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(fa0.p0(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.H = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_android_maxWidth, -1);
        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_maxActionInlineWidth, -1);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(M);
        setFocusable(true);
        if (getBackground() == null) {
            int iK0 = fa0.k0(fa0.P(this, R$attr.colorSurface), getBackgroundOverlayColorAlpha(), fa0.P(this, R$attr.colorOnSurface));
            zu0 zu0Var = this.E;
            if (zu0Var != null) {
                int i = da.a;
                ld0 ld0Var = new ld0(zu0Var);
                ld0Var.m(ColorStateList.valueOf(iK0));
                drawable = ld0Var;
            } else {
                Resources resources = getResources();
                int i2 = da.a;
                float dimension = resources.getDimension(R$dimen.mtrl_snackbar_background_corner_radius);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(dimension);
                gradientDrawable.setColor(iK0);
                drawable = gradientDrawable;
            }
            ColorStateList colorStateList = this.K;
            if (colorStateList != null) {
                ip.h(drawable, colorStateList);
            }
            WeakHashMap weakHashMap2 = f51.a;
            setBackground(drawable);
        }
    }

    public float getActionTextColorAlpha() {
        return this.H;
    }

    public int getAnimationMode() {
        return this.F;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.G;
    }

    public int getMaxInlineActionWidth() {
        return this.J;
    }

    public int getMaxWidth() {
        return this.I;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        WeakHashMap weakHashMap = f51.a;
        r41.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.I;
        if (i3 <= 0 || getMeasuredWidth() <= i3) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
    }

    public void setAnimationMode(int i) {
        this.F = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.K != null) {
            drawable = drawable.mutate();
            ip.h(drawable, this.K);
            ip.i(drawable, this.L);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.K = colorStateList;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            ip.h(drawableMutate, colorStateList);
            ip.i(drawableMutate, this.L);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.L = mode;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            ip.i(drawableMutate, mode);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        setOnTouchListener(onClickListener != null ? null : M);
        super.setOnClickListener(onClickListener);
    }

    private void setBaseTransientBottomBar(da daVar) {
    }
}
