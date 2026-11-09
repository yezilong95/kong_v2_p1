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
import defpackage.g8;
import defpackage.h8;
import defpackage.hl;
import defpackage.jr0;
import defpackage.k9;
import defpackage.ki0;
import defpackage.lr0;
import defpackage.rp0;
import defpackage.u50;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BaseTransientBottomBar$SnackbarBaseLayout extends FrameLayout {
    public static final g8 j = new g8(0);
    public final ki0 b;
    public int c;
    public final float d;
    public final float e;
    public final int f;
    public final int g;
    public ColorStateList h;
    public PorterDuff.Mode i;

    public BaseTransientBottomBar$SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
        Drawable drawable;
        super(y50.a(context, attributeSet, 0, 0), attributeSet);
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
            float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_elevation, 0);
            WeakHashMap weakHashMap = xr0.a;
            lr0.s(this, dimensionPixelSize);
        }
        this.c = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearance) || typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearanceOverlay)) {
            this.b = ki0.c(context2, attributeSet, 0, 0).a();
        }
        this.d = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
        setBackgroundTintList(xi.s(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
        setBackgroundTintMode(rp0.O(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
        this.e = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_android_maxWidth, -1);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_maxActionInlineWidth, -1);
        typedArrayObtainStyledAttributes.recycle();
        setOnTouchListener(j);
        setFocusable(true);
        if (getBackground() == null) {
            int iX = k9.x(k9.q(this, R$attr.colorSurface), getBackgroundOverlayColorAlpha(), k9.q(this, R$attr.colorOnSurface));
            ki0 ki0Var = this.b;
            if (ki0Var != null) {
                int i = h8.a;
                u50 u50Var = new u50(ki0Var);
                u50Var.m(ColorStateList.valueOf(iX));
                drawable = u50Var;
            } else {
                Resources resources = getResources();
                int i2 = h8.a;
                float dimension = resources.getDimension(R$dimen.mtrl_snackbar_background_corner_radius);
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(dimension);
                gradientDrawable.setColor(iX);
                drawable = gradientDrawable;
            }
            ColorStateList colorStateList = this.h;
            if (colorStateList != null) {
                hl.h(drawable, colorStateList);
            }
            WeakHashMap weakHashMap2 = xr0.a;
            setBackground(drawable);
        }
    }

    public float getActionTextColorAlpha() {
        return this.e;
    }

    public int getAnimationMode() {
        return this.c;
    }

    public float getBackgroundOverlayColorAlpha() {
        return this.d;
    }

    public int getMaxInlineActionWidth() {
        return this.g;
    }

    public int getMaxWidth() {
        return this.f;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        WeakHashMap weakHashMap = xr0.a;
        jr0.c(this);
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
        int i3 = this.f;
        if (i3 <= 0 || getMeasuredWidth() <= i3) {
            return;
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
    }

    public void setAnimationMode(int i) {
        this.c = i;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (drawable != null && this.h != null) {
            drawable = drawable.mutate();
            hl.h(drawable, this.h);
            hl.i(drawable, this.i);
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.h = colorStateList;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            hl.h(drawableMutate, colorStateList);
            hl.i(drawableMutate, this.i);
            if (drawableMutate != getBackground()) {
                super.setBackgroundDrawable(drawableMutate);
            }
        }
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        this.i = mode;
        if (getBackground() != null) {
            Drawable drawableMutate = getBackground().mutate();
            hl.i(drawableMutate, mode);
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
        setOnTouchListener(onClickListener != null ? null : j);
        super.setOnClickListener(onClickListener);
    }

    private void setBaseTransientBottomBar(h8 h8Var) {
    }
}
