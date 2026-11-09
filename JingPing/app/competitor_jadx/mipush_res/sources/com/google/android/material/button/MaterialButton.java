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
import defpackage.f51;
import defpackage.fa0;
import defpackage.gp0;
import defpackage.i01;
import defpackage.ip;
import defpackage.kv0;
import defpackage.la0;
import defpackage.ma0;
import defpackage.p50;
import defpackage.pd0;
import defpackage.qm;
import defpackage.rc0;
import defpackage.sc0;
import defpackage.t40;
import defpackage.tc0;
import defpackage.xu0;
import defpackage.zn;
import defpackage.zu0;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, kv0 {
    public static final int[] V = {R.attr.state_checkable};
    public static final int[] W = {R.attr.state_checked};
    public static final int a0 = R$style.Widget_MaterialComponents_Button;
    public final tc0 H;
    public final LinkedHashSet I;
    public sc0 J;
    public PorterDuff.Mode K;
    public ColorStateList L;
    public Drawable M;
    public String N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public boolean S;
    public boolean T;
    public int U;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public boolean G;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            this.G = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.G ? 1 : 0);
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
        tc0 tc0Var = this.H;
        return (tc0Var == null || tc0Var.o) ? false : true;
    }

    public void addOnCheckedChangeListener(rc0 rc0Var) {
        this.I.add(rc0Var);
    }

    public final void b() {
        int i = this.U;
        boolean z = true;
        if (i != 1 && i != 2) {
            z = false;
        }
        if (z) {
            setCompoundDrawablesRelative(this.M, null, null, null);
            return;
        }
        if (i == 3 || i == 4) {
            setCompoundDrawablesRelative(null, null, this.M, null);
        } else if (i == 16 || i == 32) {
            setCompoundDrawablesRelative(null, this.M, null, null);
        }
    }

    public final void c(boolean z) {
        Drawable drawable = this.M;
        if (drawable != null) {
            Drawable drawableMutate = drawable.mutate();
            this.M = drawableMutate;
            ip.h(drawableMutate, this.L);
            PorterDuff.Mode mode = this.K;
            if (mode != null) {
                ip.i(this.M, mode);
            }
            int intrinsicWidth = this.O;
            if (intrinsicWidth == 0) {
                intrinsicWidth = this.M.getIntrinsicWidth();
            }
            int intrinsicHeight = this.O;
            if (intrinsicHeight == 0) {
                intrinsicHeight = this.M.getIntrinsicHeight();
            }
            Drawable drawable2 = this.M;
            int i = this.P;
            int i2 = this.Q;
            drawable2.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.M.setVisible(true, z);
        }
        if (z) {
            b();
            return;
        }
        Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
        Drawable drawable3 = compoundDrawablesRelative[0];
        Drawable drawable4 = compoundDrawablesRelative[1];
        Drawable drawable5 = compoundDrawablesRelative[2];
        int i3 = this.U;
        if (((i3 == 1 || i3 == 2) && drawable3 != this.M) || (((i3 == 3 || i3 == 4) && drawable5 != this.M) || ((i3 == 16 || i3 == 32) && drawable4 != this.M))) {
            b();
        }
    }

    public final void d(int i, int i2) {
        if (this.M == null || getLayout() == null) {
            return;
        }
        int i3 = this.U;
        if (!(i3 == 1 || i3 == 2) && i3 != 3 && i3 != 4) {
            if (i3 == 16 || i3 == 32) {
                this.P = 0;
                if (i3 == 16) {
                    this.Q = 0;
                    c(false);
                    return;
                }
                int intrinsicHeight = this.O;
                if (intrinsicHeight == 0) {
                    intrinsicHeight = this.M.getIntrinsicHeight();
                }
                int iMax = Math.max(0, (((((i2 - getTextHeight()) - getPaddingTop()) - intrinsicHeight) - this.R) - getPaddingBottom()) / 2);
                if (this.Q != iMax) {
                    this.Q = iMax;
                    c(false);
                    return;
                }
                return;
            }
            return;
        }
        this.Q = 0;
        Layout.Alignment actualTextAlignment = getActualTextAlignment();
        int i4 = this.U;
        if (i4 == 1 || i4 == 3 || ((i4 == 2 && actualTextAlignment == Layout.Alignment.ALIGN_NORMAL) || (i4 == 4 && actualTextAlignment == Layout.Alignment.ALIGN_OPPOSITE))) {
            this.P = 0;
            c(false);
            return;
        }
        int intrinsicWidth = this.O;
        if (intrinsicWidth == 0) {
            intrinsicWidth = this.M.getIntrinsicWidth();
        }
        int textLayoutWidth = i - getTextLayoutWidth();
        WeakHashMap weakHashMap = f51.a;
        int paddingEnd = (((textLayoutWidth - getPaddingEnd()) - intrinsicWidth) - this.R) - getPaddingStart();
        if (actualTextAlignment == Layout.Alignment.ALIGN_CENTER) {
            paddingEnd /= 2;
        }
        if ((getLayoutDirection() == 1) != (this.U == 4)) {
            paddingEnd = -paddingEnd;
        }
        if (this.P != paddingEnd) {
            this.P = paddingEnd;
            c(false);
        }
    }

    public String getA11yClassName() {
        if (!TextUtils.isEmpty(this.N)) {
            return this.N;
        }
        tc0 tc0Var = this.H;
        return ((tc0Var == null || !tc0Var.q) ? Button.class : CompoundButton.class).getName();
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
            return this.H.g;
        }
        return 0;
    }

    public Drawable getIcon() {
        return this.M;
    }

    public int getIconGravity() {
        return this.U;
    }

    public int getIconPadding() {
        return this.R;
    }

    public int getIconSize() {
        return this.O;
    }

    public ColorStateList getIconTint() {
        return this.L;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.K;
    }

    public int getInsetBottom() {
        return this.H.f;
    }

    public int getInsetTop() {
        return this.H.e;
    }

    public ColorStateList getRippleColor() {
        if (a()) {
            return this.H.l;
        }
        return null;
    }

    public zu0 getShapeAppearanceModel() {
        if (a()) {
            return this.H.b;
        }
        throw new IllegalStateException("Attempted to get ShapeAppearanceModel from a MaterialButton which has an overwritten background.");
    }

    public ColorStateList getStrokeColor() {
        if (a()) {
            return this.H.k;
        }
        return null;
    }

    public int getStrokeWidth() {
        if (a()) {
            return this.H.h;
        }
        return 0;
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public ColorStateList getSupportBackgroundTintList() {
        return a() ? this.H.j : super.getSupportBackgroundTintList();
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        return a() ? this.H.i : super.getSupportBackgroundTintMode();
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.S;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (a()) {
            ma0.o0(this, this.H.b(false));
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 2);
        tc0 tc0Var = this.H;
        if (tc0Var != null && tc0Var.q) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, V);
        }
        if (this.S) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, W);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(this.S);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        tc0 tc0Var = this.H;
        accessibilityNodeInfo.setCheckable(tc0Var != null && tc0Var.q);
        accessibilityNodeInfo.setChecked(this.S);
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
        super.onRestoreInstanceState(savedState.E);
        setChecked(savedState.G);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.G = this.S;
        return savedState;
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (this.H.r) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.M != null) {
            if (this.M.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    public void removeOnCheckedChangeListener(rc0 rc0Var) {
        this.I.remove(rc0Var);
    }

    public void setA11yClassName(String str) {
        this.N = str;
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
        tc0 tc0Var = this.H;
        if (tc0Var.b(false) != null) {
            tc0Var.b(false).setTint(i);
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
        tc0 tc0Var = this.H;
        tc0Var.o = true;
        ColorStateList colorStateList = tc0Var.j;
        MaterialButton materialButton = tc0Var.a;
        materialButton.setSupportBackgroundTintList(colorStateList);
        materialButton.setSupportBackgroundTintMode(tc0Var.i);
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? qm.z(getContext(), i) : null);
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
            this.H.q = z;
        }
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        tc0 tc0Var = this.H;
        if (tc0Var == null || !tc0Var.q || !isEnabled() || this.S == z) {
            return;
        }
        this.S = z;
        refreshDrawableState();
        if (getParent() instanceof MaterialButtonToggleGroup) {
            MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) getParent();
            boolean z2 = this.S;
            if (!materialButtonToggleGroup.J) {
                materialButtonToggleGroup.b(getId(), z2);
            }
        }
        if (this.T) {
            return;
        }
        this.T = true;
        Iterator it = this.I.iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        this.T = false;
    }

    public void setCornerRadius(int i) {
        if (a()) {
            tc0 tc0Var = this.H;
            if (tc0Var.p && tc0Var.g == i) {
                return;
            }
            tc0Var.g = i;
            tc0Var.p = true;
            xu0 xu0VarG = tc0Var.b.g();
            xu0VarG.c(i);
            tc0Var.c(xu0VarG.a());
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
            this.H.b(false).l(f);
        }
    }

    public void setIcon(Drawable drawable) {
        if (this.M != drawable) {
            this.M = drawable;
            c(true);
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconGravity(int i) {
        if (this.U != i) {
            this.U = i;
            d(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void setIconPadding(int i) {
        if (this.R != i) {
            this.R = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i) {
        setIcon(i != 0 ? qm.z(getContext(), i) : null);
    }

    public void setIconSize(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (this.O != i) {
            this.O = i;
            c(true);
        }
    }

    public void setIconTint(ColorStateList colorStateList) {
        if (this.L != colorStateList) {
            this.L = colorStateList;
            c(false);
        }
    }

    public void setIconTintMode(PorterDuff.Mode mode) {
        if (this.K != mode) {
            this.K = mode;
            c(false);
        }
    }

    public void setIconTintResource(int i) {
        setIconTint(t40.Y(getContext(), i));
    }

    public void setInsetBottom(int i) {
        tc0 tc0Var = this.H;
        tc0Var.d(tc0Var.e, i);
    }

    public void setInsetTop(int i) {
        tc0 tc0Var = this.H;
        tc0Var.d(i, tc0Var.f);
    }

    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setOnPressedChangeListenerInternal(sc0 sc0Var) {
        this.J = sc0Var;
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        sc0 sc0Var = this.J;
        if (sc0Var != null) {
            ((MaterialButtonToggleGroup) ((p50) sc0Var).F).invalidate();
        }
        super.setPressed(z);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        if (a()) {
            tc0 tc0Var = this.H;
            if (tc0Var.l != colorStateList) {
                tc0Var.l = colorStateList;
                MaterialButton materialButton = tc0Var.a;
                if (materialButton.getBackground() instanceof RippleDrawable) {
                    ((RippleDrawable) materialButton.getBackground()).setColor(gp0.c(colorStateList));
                }
            }
        }
    }

    public void setRippleColorResource(int i) {
        if (a()) {
            setRippleColor(t40.Y(getContext(), i));
        }
    }

    @Override // defpackage.kv0
    public void setShapeAppearanceModel(zu0 zu0Var) {
        if (!a()) {
            throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
        }
        this.H.c(zu0Var);
    }

    public void setShouldDrawSurfaceColorStroke(boolean z) {
        if (a()) {
            tc0 tc0Var = this.H;
            tc0Var.n = z;
            tc0Var.f();
        }
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        if (a()) {
            tc0 tc0Var = this.H;
            if (tc0Var.k != colorStateList) {
                tc0Var.k = colorStateList;
                tc0Var.f();
            }
        }
    }

    public void setStrokeColorResource(int i) {
        if (a()) {
            setStrokeColor(t40.Y(getContext(), i));
        }
    }

    public void setStrokeWidth(int i) {
        if (a()) {
            tc0 tc0Var = this.H;
            if (tc0Var.h != i) {
                tc0Var.h = i;
                tc0Var.f();
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
        tc0 tc0Var = this.H;
        if (tc0Var.j != colorStateList) {
            tc0Var.j = colorStateList;
            if (tc0Var.b(false) != null) {
                ip.h(tc0Var.b(false), tc0Var.j);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (!a()) {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        tc0 tc0Var = this.H;
        if (tc0Var.i != mode) {
            tc0Var.i = mode;
            if (tc0Var.b(false) == null || tc0Var.i == null) {
                return;
            }
            ip.i(tc0Var.b(false), tc0Var.i);
        }
    }

    @Override // android.view.View
    public void setTextAlignment(int i) {
        super.setTextAlignment(i);
        d(getMeasuredWidth(), getMeasuredHeight());
    }

    public void setToggleCheckedStateOnClick(boolean z) {
        this.H.r = z;
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.S);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialButtonStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        int i2 = a0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.I = new LinkedHashSet();
        this.S = false;
        this.T = false;
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.MaterialButton, i, i2, new int[0]);
        this.R = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconPadding, 0);
        int i3 = typedArrayD.getInt(R$styleable.MaterialButton_iconTintMode, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.K = fa0.p0(i3, mode);
        this.L = la0.p(getContext(), typedArrayD, R$styleable.MaterialButton_iconTint);
        this.M = la0.s(getContext(), typedArrayD, R$styleable.MaterialButton_icon);
        this.U = typedArrayD.getInteger(R$styleable.MaterialButton_iconGravity, 1);
        this.O = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_iconSize, 0);
        tc0 tc0Var = new tc0(this, zu0.c(context2, attributeSet, i, i2).a());
        this.H = tc0Var;
        tc0Var.c = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetLeft, 0);
        tc0Var.d = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetRight, 0);
        tc0Var.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetTop, 0);
        tc0Var.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialButton_android_insetBottom, 0);
        if (typedArrayD.hasValue(R$styleable.MaterialButton_cornerRadius)) {
            int dimensionPixelSize = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_cornerRadius, -1);
            tc0Var.g = dimensionPixelSize;
            xu0 xu0VarG = tc0Var.b.g();
            xu0VarG.c(dimensionPixelSize);
            tc0Var.c(xu0VarG.a());
            tc0Var.p = true;
        }
        tc0Var.h = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_strokeWidth, 0);
        tc0Var.i = fa0.p0(typedArrayD.getInt(R$styleable.MaterialButton_backgroundTintMode, -1), mode);
        tc0Var.j = la0.p(getContext(), typedArrayD, R$styleable.MaterialButton_backgroundTint);
        tc0Var.k = la0.p(getContext(), typedArrayD, R$styleable.MaterialButton_strokeColor);
        tc0Var.l = la0.p(getContext(), typedArrayD, R$styleable.MaterialButton_rippleColor);
        tc0Var.q = typedArrayD.getBoolean(R$styleable.MaterialButton_android_checkable, false);
        tc0Var.t = typedArrayD.getDimensionPixelSize(R$styleable.MaterialButton_elevation, 0);
        tc0Var.r = typedArrayD.getBoolean(R$styleable.MaterialButton_toggleCheckedStateOnClick, true);
        WeakHashMap weakHashMap = f51.a;
        int paddingStart = getPaddingStart();
        int paddingTop = getPaddingTop();
        int paddingEnd = getPaddingEnd();
        int paddingBottom = getPaddingBottom();
        if (typedArrayD.hasValue(R$styleable.MaterialButton_android_background)) {
            tc0Var.o = true;
            setSupportBackgroundTintList(tc0Var.j);
            setSupportBackgroundTintMode(tc0Var.i);
        } else {
            tc0Var.e();
        }
        setPaddingRelative(paddingStart + tc0Var.c, paddingTop + tc0Var.e, paddingEnd + tc0Var.d, paddingBottom + tc0Var.f);
        typedArrayD.recycle();
        setCompoundDrawablePadding(this.R);
        c(this.M != null);
    }
}
