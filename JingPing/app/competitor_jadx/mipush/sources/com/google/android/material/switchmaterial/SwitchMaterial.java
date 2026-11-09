package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.f51;
import defpackage.fa0;
import defpackage.i01;
import defpackage.pd0;
import defpackage.t41;
import defpackage.ts;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int E0 = R$style.Widget_MaterialComponents_CompoundButton_Switch;
    public static final int[][] F0 = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    public final ts A0;
    public ColorStateList B0;
    public ColorStateList C0;
    public boolean D0;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.B0 == null) {
            int iP = fa0.P(this, R$attr.colorSurface);
            int iP2 = fa0.P(this, R$attr.colorControlActivated);
            float dimension = getResources().getDimension(R$dimen.mtrl_switch_thumb_elevation);
            ts tsVar = this.A0;
            if (tsVar.a) {
                float fI = 0.0f;
                for (ViewParent parent = getParent(); parent instanceof View; parent = parent.getParent()) {
                    WeakHashMap weakHashMap = f51.a;
                    fI += t41.i((View) parent);
                }
                dimension += fI;
            }
            int iB = tsVar.b(iP, dimension);
            this.B0 = new ColorStateList(F0, new int[]{fa0.k0(iP, 1.0f, iP2), iB, fa0.k0(iP, 0.38f, iP2), iB});
        }
        return this.B0;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.C0 == null) {
            int iP = fa0.P(this, R$attr.colorSurface);
            int iP2 = fa0.P(this, R$attr.colorControlActivated);
            int iP3 = fa0.P(this, R$attr.colorOnSurface);
            this.C0 = new ColorStateList(F0, new int[]{fa0.k0(iP, 0.54f, iP2), fa0.k0(iP, 0.32f, iP3), fa0.k0(iP, 0.12f, iP2), fa0.k0(iP, 0.12f, iP3)});
        }
        return this.C0;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.D0 && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.D0 && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.D0 = z;
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        } else {
            setThumbTintList(null);
            setTrackTintList(null);
        }
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.switchStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        int i2 = E0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        this.A0 = new ts(context2);
        int[] iArr = R$styleable.SwitchMaterial;
        i01.a(context2, attributeSet, i, i2);
        i01.b(context2, attributeSet, iArr, i, i2, new int[0]);
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.D0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SwitchMaterial_useMaterialThemeColors, false);
        typedArrayObtainStyledAttributes.recycle();
    }
}
