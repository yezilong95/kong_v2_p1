package com.google.android.material.button;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.b50;
import defpackage.c50;
import defpackage.d50;
import defpackage.hk;
import defpackage.hl;
import defpackage.ii0;
import defpackage.ki0;
import defpackage.lf0;
import defpackage.o70;
import defpackage.oy;
import defpackage.rp0;
import defpackage.un0;
import defpackage.vi0;
import defpackage.xi;
import defpackage.xr0;
import defpackage.y50;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, vi0 {
    public static final int[] s = {R.attr.state_checkable};
    public static final int[] t = {R.attr.state_checked};
    public static final int u = R$style.Widget_MaterialComponents_Button;
    public final d50 e;
    public final LinkedHashSet f;
    public c50 g;
    public PorterDuff.Mode h;
    public ColorStateList i;
    public Drawable j;
    public String k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public boolean q;
    public int r;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean d;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            this.d = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.d ? 1 : 0);
        }
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private Layout.Alignment getActualTextAlignment() {
        int textAlignment = getTextAlignment();
        return textAlignment != 1 ? (textAlignment == 6 || textAlignment == 3) ? Layout.Alignment.ALIGN_OPPOSITE : textAlignment != 4 ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER : getGravityTextAlignment();
    }

    private Layout.Alignment getGravityTextAlignment() {
        int gravity = getGravity() & 8388615;
        return gravity != 1 ? (gravity == 5 || gravity == 8388613) ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_CENTER;
    }

    private int getTextHeight() {
        if (getLineCount() > 1) {
            return getLayout().getHeight();
        }
        TextPaint paint = getPaint();
        String string = getText().toString();
        if (getTransformationMethod() != null) {
            string = getTransformationMethod().getTransformation(string, this).toString();
        }
        Rect rect = new Rect();
        paint.getTextBounds(string, 0, string.length(), rect);
        return Math.min(rect.height(), getLayout().getHeight());
    }

    private int getTextLayoutWidth() {
        int lineCount = getLineCount();
        float fMax = 0.0f;
        for (int i = 0; i < lineCount; i++) {
            fMax = Math.max(fMax, getLayout().getLineWidth(i));
        }
        return (int) Math.ceil(fMax);
    }

    public final boolean a() {
        d50 d50Var = this.e;
        return (d50Var == null || d50Var.o) ? false : true;
    }

    public void addOnCheckedChangeListener(b50 b50Var) {
        this.f.add(b50Var);
    }

    public final void b() {
        int i = this.r;
        boolean z = true;
        if (i != 1 && i != 2) {
            z = false;
        }
        if (z) {
            setCompoundDrawablesRelative(this.j, null, null, null);
            return;
        }
        if (i == 3 || i == 4) {
            setCompoundDrawablesRelative(null, null, this.j, null);
        } else if (i == 16 || i == 32) {
            setCompoundDrawablesRelative(null, this.j, null, null);
        }
    }

    public final void c(boolean z) {
        Drawable drawable = this.j;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.j = drawableMutate;
            hl.h(drawableMutate, this.i);
            PorterDuff.Mode mode = this.h;
            if (mode != null) {
                hl.i(this.j, mode);
            }
            int intrinsicWidth = this.l;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.j.getIntrinsicWidth();
            }
            int intrinsicHeight = this.l;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.j.getIntrinsicHeight();
            }
            Drawable drawable2 = this.j;
            int i = this.m;
            int i2 = this.n;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.j.setVisible(true, z);
        }
        if (z) {
            b();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i3 = this.r;
        if (((i3 == 1 || i3 == 2) && drawable3 != this.j) || (((i3 == 3 || i3 == 4) && drawable5 != this.j) || ((i3 == 16 || i3 == 32) && drawable4 != this.j))) {
            b();
        }
    }

    public final void d(int i, int i2) {
        if (this.j == null || getLayout() == null) {
            return;
        }
        int i3 = this.r;
        if (!(i3 == 1 || i3 == 2) && i3 != 3 && i3 != 4) {
            if (i3 == 16 || i3 == 32) {
                this.m = 0;
                if (i3 == 16) {
                    this.n = 0;
                    c(false);
                    return;
                }
                int intrinsicHeight = this.l;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.j.getIntrinsicHeight();
                }
                int iMax = Math.max(0, (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.o) - getPaddingBottom()) / 2);
                if (this.n != iMax) {
                    this.n = iMax;
                    c(false);
                    return;
                }
                return;
            }
            return;
        }
        this.n = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i4 = this.r;
        if (i4 == 1 || i4 == 3 || ((i4 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i4 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.m = 0;
            c(false);
            return;
        }
        int intrinsicWidth = this.l;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.j.getIntrinsicWidth();
        }
        int textLayoutWidth = i - getTextLayoutWidth();
        WeakHashMap weakHashMap = xr0.a;
        int paddingEnd = (((textLayoutWidth - getPaddingEnd()) - intrinsicWidth) - this.o) - getPaddingStart();
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            paddingEnd /= 2;
        }
        if ((getLayoutDirection() == 1) != (this.r == 4)) {
            paddingEnd = -paddingEnd;
        }
        if (this.m != paddingEnd) {
            this.m = paddingEnd;
            c(false);
        }
    }

    public String getA11yClassName() {
        if (!TextUtils.isEmpty(this.k)) {
            return this.k;
        }
        d50 d50Var = this.e;
        return ((d50Var == null || !d50Var.q) ? Button.class : CompoundButton.class).getName();
    }

    @Override // android.view.View
    public ColorStateList getBackgroundTintList() {
        return getSupportBackgroundTintList();
    }

    @Override // android.view.View
    public PorterDuff.Mode getBackgroundTintMode() {
        return getSupportBackgroundTintMode();
    }

    public int getCornerRadius() {
        if (a()) {
            return this.e.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.j;
    }

    public int getIconGravity() {
        return this.r;
    }

    public int getIconPadding() {
        return this.o;
    }

    public int getIconSize() {
        return this.l;
    }

    public ColorStateList getIconTint() {
        return this.i;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.h;
    }

    public int getInsetBottom() {
        return this.e.f;
    }

    public int getInsetTop() {
        return this.e.e;
    }

    public ColorStateList getRippleColor() {
        if (a()) {
            return this.e.l;
        }
        return null;
    }

    public ki0 getShapeAppearanceModel() {
        if (a()) {
            return this.e.b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (a()) {
            return this.e.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (a()) {
            return this.e.h;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return a() ? this.e.j : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return a() ? this.e.i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.p;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (a()) {
            oy.O(this, this.e.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        d50 d50Var = this.e;
        if (d50Var != null && d50Var.q) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, s);
        }
        if (this.p) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, t);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(this.p);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        d50 d50Var = this.e;
        accessibilityNodeInfo.setCheckable(d50Var != null && d50Var.q);
        accessibilityNodeInfo.setChecked(this.p);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        setChecked(savedState.d);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.d = this.p;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (this.e.r) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.j != null) {
            if (this.j.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void removeOnCheckedChangeListener(b50 b50Var) {
        this.f.remove(b50Var);
    }

    public void setA11yClassName(String str) {
        this.k = str;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (!a()) {
            super.setBackgroundColor(i);
            return;
        }
        d50 d50Var = this.e;
        if (d50Var.b(false) != null) {
            d50Var.b(false).setTint(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (!a()) {
            super.setBackgroundDrawable(drawable);
            return;
        }
        if (drawable == getBackground()) {
            getBackground().setState(drawable.getState());
            return;
        }
        Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
        d50 d50Var = this.e;
        d50Var.o = true;
        ColorStateList colorStateList = d50Var.j;
        MaterialButton materialButton = d50Var.a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(d50Var.i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? rp0.y(getContext(), i) : null);
    }

    @Override // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    public void setCheckable(boolean z) {
        if (a()) {
            this.e.q = z;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        d50 d50Var = this.e;
        if (d50Var == null || !d50Var.q || !isEnabled() || this.p == z) {
            return;
        }
        this.p = z;
        refreshDrawableState();
        if (getParent() instanceof MaterialButtonToggleGroup) {
            MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
            boolean z2 = this.p;
            if (!materialButtonToggleGroup.g) {
                materialButtonToggleGroup.b(getId(), z2);
            }
        }
        if (this.q) {
            return;
        }
        this.q = true;
        Iterator it = this.f.iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        this.q = false;
    }

    public void setCornerRadius(int i) {
        if (a()) {
            d50 d50Var = this.e;
            if (d50Var.p && d50Var.g == i) {
                return;
            }
            d50Var.g = i;
            d50Var.p = true;
            ii0 ii0VarG = d50Var.b.g();
            ii0VarG.c(i);
            d50Var.c(ii0VarG.a());
        }
    }

    public void setCornerRadiusResource(int i) {
        if (a()) {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        if (a()) {
            this.e.b(false).l(f);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.j != drawable) {
            this.j = drawable;
            c(true);
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.r != i) {
            this.r = i;
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.o != i) {
            this.o = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? rp0.y(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.l != i) {
            this.l = i;
            c(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.i != colorStateList) {
            this.i = colorStateList;
            c(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.h != mode) {
            this.h = mode;
            c(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(rp0.w(getContext(), i));
    }

    public void setInsetBottom(int i) {
        d50 d50Var = this.e;
        d50Var.d(d50Var.e, i);
    }

    public void setInsetTop(int i) {
        d50 d50Var = this.e;
        d50Var.d(i, d50Var.f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(c50 c50Var) {
        this.g = c50Var;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        c50 c50Var = this.g;
        if (c50Var != null) {
            ((MaterialButtonToggleGroup) ((o70) c50Var).c).invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (a()) {
            d50 d50Var = this.e;
            if (d50Var.l != colorStateList) {
                d50Var.l = colorStateList;
                MaterialButton materialButton = d50Var.a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(lf0.c(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (a()) {
            setRippleColor(rp0.w(getContext(), i));
        }
    }

    @Override // defpackage.vi0
    public void setShapeAppearanceModel(ki0 ki0Var) {
        if (!a()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.e.c(ki0Var);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (a()) {
            d50 d50Var = this.e;
            d50Var.n = z;
            d50Var.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (a()) {
            d50 d50Var = this.e;
            if (d50Var.k != colorStateList) {
                d50Var.k = colorStateList;
                d50Var.f();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (a()) {
            setStrokeColor(rp0.w(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (a()) {
            d50 d50Var = this.e;
            if (d50Var.h != i) {
                d50Var.h = i;
                d50Var.f();
            }
        }
    }

    public void setStrokeWidthResource(int i) {
        if (a()) {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (!a()) {
            super.setSupportBackgroundTintList(colorStateList);
            return;
        }
        d50 d50Var = this.e;
        if (d50Var.j != colorStateList) {
            d50Var.j = colorStateList;
            if (d50Var.b(false) != null) {
                hl.h(d50Var.b(false), d50Var.j);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!a()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        d50 d50Var = this.e;
        if (d50Var.i != mode) {
            d50Var.i = mode;
            if (d50Var.b(false) == null || d50Var.i == null) {
                return;
            }
            hl.i(d50Var.b(false), d50Var.i);
        }
    }

    @Override // android.view.View
    public void setTextAlignment(int i) {
        super.setTextAlignment(i);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.e.r = z;
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.p);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = u;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.f = new LinkedHashSet();
        this.p = false;
        this.q = false;
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.MaterialButton, i, i2, new int[0]);
        this.o = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconPadding, 0);
        int i3 = typedArrayD.getInt(R$styleable.MaterialButton_iconTintMode, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.h = rp0.O(i3, mode);
        this.i = xi.s(getContext(), typedArrayD, R$styleable.MaterialButton_iconTint);
        this.j = xi.x(getContext(), typedArrayD, R$styleable.MaterialButton_icon);
        this.r = typedArrayD.getInteger(R$styleable.MaterialButton_iconGravity, 1);
        this.l = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconSize, 0);
        d50 d50Var = new d50(this, ki0.c(context2, attributeSet, i, i2).a());
        this.e = d50Var;
        d50Var.c = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        d50Var.d = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        d50Var.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        d50Var.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        if (typedArrayD.hasValue(R$styleable.MaterialButton_cornerRadius)) {
            int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_cornerRadius, -1);
            d50Var.g = dimensionPixelSize;
            ii0 ii0VarG = d50Var.b.g();
            ii0VarG.c(dimensionPixelSize);
            d50Var.c(ii0VarG.a());
            d50Var.p = true;
        }
        d50Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        d50Var.i = rp0.O(typedArrayD.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), mode);
        d50Var.j = xi.s(getContext(), typedArrayD, R$styleable.MaterialButton_backgroundTint);
        d50Var.k = xi.s(getContext(), typedArrayD, R$styleable.MaterialButton_strokeColor);
        d50Var.l = xi.s(getContext(), typedArrayD, R$styleable.MaterialButton_rippleColor);
        d50Var.q = typedArrayD.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        d50Var.t = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        d50Var.r = typedArrayD.getBoolean(R$styleable.MaterialButton_toggleCheckedStateOnClick, true);
        WeakHashMap weakHashMap = xr0.a;
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        if (typedArrayD.hasValue(R$styleable.MaterialButton_android_background)) {
            d50Var.o = true;
            setSupportBackgroundTintList(d50Var.j);
            setSupportBackgroundTintMode(d50Var.i);
        } else {
            d50Var.e();
        }
        setPaddingRelative(paddingStart + d50Var.c, paddingTop + d50Var.e, paddingEnd + d50Var.d, paddingBottom + d50Var.f);
        typedArrayD.recycle();
        setCompoundDrawablePadding(this.o);
        c(this.j != null);
    }
}
