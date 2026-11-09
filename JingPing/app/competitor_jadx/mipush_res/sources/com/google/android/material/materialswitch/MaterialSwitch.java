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
import defpackage.dg;
import defpackage.fa0;
import defpackage.i01;
import defpackage.ip;
import defpackage.ou;
import defpackage.pd0;
import defpackage.qm;

/* JADX INFO: loaded from: classes.dex */
public class MaterialSwitch extends SwitchCompat {
    public static final int N0 = R$style.Widget_Material3_CompoundButton_MaterialSwitch;
    public static final int[] O0 = {R$attr.state_with_icon};
    public Drawable A0;
    public Drawable B0;
    public int C0;
    public Drawable D0;
    public Drawable E0;
    public ColorStateList F0;
    public ColorStateList G0;
    public PorterDuff.Mode H0;
    public ColorStateList I0;
    public ColorStateList J0;
    public PorterDuff.Mode K0;
    public int[] L0;
    public int[] M0;

    public MaterialSwitch(Context context) {
        this(context, null);
    }

    public static void g(Drawable drawable, ColorStateList colorStateList, int[] iArr, int[] iArr2, float f) {
        if (drawable == null || colorStateList == null) {
            return;
        }
        ip.g(drawable, dg.b(colorStateList.getColorForState(iArr, 0), f, colorStateList.getColorForState(iArr2, 0)));
    }

    public final void e() {
        this.A0 = qm.k(this.A0, this.F0, getThumbTintMode());
        this.B0 = qm.k(this.B0, this.G0, this.H0);
        h();
        Drawable drawable = this.A0;
        Drawable drawable2 = this.B0;
        int i = this.C0;
        super.setThumbDrawable(qm.i(drawable, drawable2, i, i));
        refreshDrawableState();
    }

    public final void f() {
        this.D0 = qm.k(this.D0, this.I0, getTrackTintMode());
        this.E0 = qm.k(this.E0, this.J0, this.K0);
        h();
        Drawable layerDrawable = this.D0;
        if (layerDrawable != null && this.E0 != null) {
            layerDrawable = new LayerDrawable(new Drawable[]{this.D0, this.E0});
        } else if (layerDrawable == null) {
            layerDrawable = this.E0;
        }
        if (layerDrawable != null) {
            setSwitchMinWidth(layerDrawable.getIntrinsicWidth());
        }
        super.setTrackDrawable(layerDrawable);
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getThumbDrawable() {
        return this.A0;
    }

    public Drawable getThumbIconDrawable() {
        return this.B0;
    }

    public int getThumbIconSize() {
        return this.C0;
    }

    public ColorStateList getThumbIconTintList() {
        return this.G0;
    }

    public PorterDuff.Mode getThumbIconTintMode() {
        return this.H0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getThumbTintList() {
        return this.F0;
    }

    public Drawable getTrackDecorationDrawable() {
        return this.E0;
    }

    public ColorStateList getTrackDecorationTintList() {
        return this.J0;
    }

    public PorterDuff.Mode getTrackDecorationTintMode() {
        return this.K0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public Drawable getTrackDrawable() {
        return this.D0;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public ColorStateList getTrackTintList() {
        return this.I0;
    }

    public final void h() {
        if (this.F0 == null && this.G0 == null && this.I0 == null && this.J0 == null) {
            return;
        }
        float thumbPosition = getThumbPosition();
        ColorStateList colorStateList = this.F0;
        if (colorStateList != null) {
            g(this.A0, colorStateList, this.L0, this.M0, thumbPosition);
        }
        ColorStateList colorStateList2 = this.G0;
        if (colorStateList2 != null) {
            g(this.B0, colorStateList2, this.L0, this.M0, thumbPosition);
        }
        ColorStateList colorStateList3 = this.I0;
        if (colorStateList3 != null) {
            g(this.D0, colorStateList3, this.L0, this.M0, thumbPosition);
        }
        ColorStateList colorStateList4 = this.J0;
        if (colorStateList4 != null) {
            g(this.E0, colorStateList4, this.L0, this.M0, thumbPosition);
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
        if (this.B0 != null) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, O0);
        }
        int[] iArr = new int[iArrOnCreateDrawableState.length];
        int i2 = 0;
        for (int i3 : iArrOnCreateDrawableState) {
            if (i3 != 16842912) {
                iArr[i2] = i3;
                i2++;
            }
        }
        this.L0 = iArr;
        this.M0 = qm.r(iArrOnCreateDrawableState);
        return iArrOnCreateDrawableState;
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbDrawable(Drawable drawable) {
        this.A0 = drawable;
        e();
    }

    public void setThumbIconDrawable(Drawable drawable) {
        this.B0 = drawable;
        e();
    }

    public void setThumbIconResource(int i) {
        setThumbIconDrawable(qm.z(getContext(), i));
    }

    public void setThumbIconSize(int i) {
        if (this.C0 != i) {
            this.C0 = i;
            e();
        }
    }

    public void setThumbIconTintList(ColorStateList colorStateList) {
        this.G0 = colorStateList;
        e();
    }

    public void setThumbIconTintMode(PorterDuff.Mode mode) {
        this.H0 = mode;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintList(ColorStateList colorStateList) {
        this.F0 = colorStateList;
        e();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setThumbTintMode(PorterDuff.Mode mode) {
        super.setThumbTintMode(mode);
        e();
    }

    public void setTrackDecorationDrawable(Drawable drawable) {
        this.E0 = drawable;
        f();
    }

    public void setTrackDecorationResource(int i) {
        setTrackDecorationDrawable(qm.z(getContext(), i));
    }

    public void setTrackDecorationTintList(ColorStateList colorStateList) {
        this.J0 = colorStateList;
        f();
    }

    public void setTrackDecorationTintMode(PorterDuff.Mode mode) {
        this.K0 = mode;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackDrawable(Drawable drawable) {
        this.D0 = drawable;
        f();
    }

    @Override // androidx.appcompat.widget.SwitchCompat
    public void setTrackTintList(ColorStateList colorStateList) {
        this.I0 = colorStateList;
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
        int i2 = N0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.C0 = -1;
        Context context2 = getContext();
        this.A0 = super.getThumbDrawable();
        this.F0 = super.getThumbTintList();
        super.setThumbTintList(null);
        this.D0 = super.getTrackDrawable();
        this.I0 = super.getTrackTintList();
        super.setTrackTintList(null);
        ou ouVarE = i01.e(context2, attributeSet, R$styleable.MaterialSwitch, i, i2, new int[0]);
        this.B0 = ouVarE.A(R$styleable.MaterialSwitch_thumbIcon);
        int i3 = R$styleable.MaterialSwitch_thumbIconSize;
        TypedArray typedArray = (TypedArray) ouVarE.G;
        this.C0 = typedArray.getDimensionPixelSize(i3, -1);
        this.G0 = ouVarE.y(R$styleable.MaterialSwitch_thumbIconTint);
        int i4 = typedArray.getInt(R$styleable.MaterialSwitch_thumbIconTintMode, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        this.H0 = fa0.p0(i4, mode);
        this.E0 = ouVarE.A(R$styleable.MaterialSwitch_trackDecoration);
        this.J0 = ouVarE.y(R$styleable.MaterialSwitch_trackDecorationTint);
        this.K0 = fa0.p0(typedArray.getInt(R$styleable.MaterialSwitch_trackDecorationTintMode, -1), mode);
        ouVarE.S();
        setEnforceSwitchWidth(false);
        e();
        f();
    }
}
