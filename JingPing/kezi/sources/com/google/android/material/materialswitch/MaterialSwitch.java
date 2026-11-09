package com.google.android.material.materialswitch;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.hl;
import defpackage.je;
import defpackage.k9;
import defpackage.po;
import defpackage.rp0;
import defpackage.un0;
import defpackage.y50;

/* JADX INFO: loaded from: classes.dex */
public class MaterialSwitch extends SwitchCompat {
    public static final int k0 = R$style.Widget_Material3_CompoundButton_MaterialSwitch;
    public static final int[] l0 = {R$attr.state_with_icon};
    public Drawable U;
    public Drawable V;
    public int W;
    public Drawable a0;
    public Drawable b0;
    public ColorStateList c0;
    public ColorStateList d0;
    public PorterDuff.Mode e0;
    public ColorStateList f0;
    public ColorStateList g0;
    public PorterDuff.Mode h0;
    public int[] i0;
    public int[] j0;

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public static void g(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        hl.g(drawable, je.b(colorStateList.getColorForState(iArr, 0), f, colorStateList.getColorForState(iArr2, 0)));
    }

    public final void e() {
        this.U = k9.l(this.U, this.c0, getThumbTintMode());
        this.V = k9.l(this.V, this.d0, this.e0);
        h();
        Drawable drawable = this.U;
        Drawable drawable2 = this.V;
        int i = this.W;
        super.setThumbDrawable(k9.k(drawable, drawable2, i, i));
        refreshDrawableState();
    }

    public final void f() {
        this.a0 = k9.l(this.a0, this.f0, getTrackTintMode());
        this.b0 = k9.l(this.b0, this.g0, this.h0);
        h();
        Drawable layerDrawable = this.a0;
        if (layerDrawable != null && this.b0 != null) {
            layerDrawable = new LayerDrawable(new Drawable[]{this.a0, this.b0});
        } else if (layerDrawable == null) {
            layerDrawable = this.b0;
        }
        if (layerDrawable != null) {
            setSwitchMinWidth(layerDrawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(layerDrawable);
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.U;
    }

    public Drawable getThumbIconDrawable() {
        return this.V;
    }

    public int getThumbIconSize() {
        return this.W;
    }

    public ColorStateList getThumbIconTintList() {
        return this.d0;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.e0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.c0;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.b0;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.g0;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.h0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.a0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.f0;
    }

    public final void h() {
        if (this.c0 == null && this.d0 == null && this.f0 == null && this.g0 == null) {
            return;
        }
        float thumbPosition = getThumbPosition();
        ColorStateList colorStateList = this.c0;
        if (colorStateList != null) {
            g(this.U, colorStateList, this.i0, this.j0, thumbPosition);
        }
        ColorStateList colorStateList2 = this.d0;
        if (colorStateList2 != null) {
            g(this.V, colorStateList2, this.i0, this.j0, thumbPosition);
        }
        ColorStateList colorStateList3 = this.f0;
        if (colorStateList3 != null) {
            g(this.a0, colorStateList3, this.i0, this.j0, thumbPosition);
        }
        ColorStateList colorStateList4 = this.g0;
        if (colorStateList4 != null) {
            g(this.b0, colorStateList4, this.i0, this.j0, thumbPosition);
        }
    }

    @Override // android.view.View
    public final void invalidate() {
        h();
        super.invalidate();
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.V != null) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, l0);
        }
        int[] iArr = new int[iArrOnCreateDrawableState.length];
        int i2 = 0;
        for (int i3 : iArrOnCreateDrawableState) {
            if (i3 != 16842912) {
                iArr[i2] = i3;
                i2++;
            }
        }
        this.i0 = iArr;
        this.j0 = k9.o(iArrOnCreateDrawableState);
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable) {
        this.U = drawable;
        e();
    }

    public void setThumbIconDrawable(Drawable drawable) {
        this.V = drawable;
        e();
    }

    public void setThumbIconResource(int i) {
        setThumbIconDrawable(rp0.y(getContext(), i));
    }

    public void setThumbIconSize(int i) {
        if (this.W != i) {
            this.W = i;
            e();
        }
    }

    public void setThumbIconTintList(ColorStateList colorStateList) {
        this.d0 = colorStateList;
        e();
    }

    public void setThumbIconTintMode(PorterDuff.Mode mode) {
        this.e0 = mode;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList colorStateList) {
        this.c0 = colorStateList;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode mode) {
        super.setThumbTintMode(mode);
        e();
    }

    public void setTrackDecorationDrawable(Drawable drawable) {
        this.b0 = drawable;
        f();
    }

    public void setTrackDecorationResource(int i) {
        setTrackDecorationDrawable(rp0.y(getContext(), i));
    }

    public void setTrackDecorationTintList(ColorStateList colorStateList) {
        this.g0 = colorStateList;
        f();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode mode) {
        this.h0 = mode;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable drawable) {
        this.a0 = drawable;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList colorStateList) {
        this.f0 = colorStateList;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintMode(PorterDuff.Mode mode) {
        super.setTrackTintMode(mode);
        f();
    }

    public MaterialSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSwitchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialSwitch(Context context, AttributeSet attributeSet, int i) {
        int i2 = k0;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.W = -1;
        Context context2 = getContext();
        this.U = super.getThumbDrawable();
        this.c0 = super.getThumbTintList();
        super.setThumbTintList(null);
        this.a0 = super.getTrackDrawable();
        this.f0 = super.getTrackTintList();
        super.setTrackTintList(null);
        po poVarE = un0.e(context2, attributeSet, R$styleable.MaterialSwitch, i, i2, new int[0]);
        this.V = poVarE.y(R$styleable.MaterialSwitch_thumbIcon);
        int i3 = R$styleable.MaterialSwitch_thumbIconSize;
        TypedArray typedArray = (TypedArray) poVarE.d;
        this.W = typedArray.getDimensionPixelSize(i3, -1);
        this.d0 = poVarE.w(R$styleable.MaterialSwitch_thumbIconTint);
        int i4 = typedArray.getInt(R$styleable.MaterialSwitch_thumbIconTintMode, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.e0 = rp0.O(i4, mode);
        this.b0 = poVarE.y(R$styleable.MaterialSwitch_trackDecoration);
        this.g0 = poVarE.w(R$styleable.MaterialSwitch_trackDecorationTint);
        this.h0 = rp0.O(typedArray.getInt(R$styleable.MaterialSwitch_trackDecorationTintMode, -1), mode);
        poVarE.P();
        setEnforceSwitchWidth(false);
        e();
        f();
    }
}
